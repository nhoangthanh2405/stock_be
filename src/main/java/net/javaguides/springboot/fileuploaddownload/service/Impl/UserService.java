package net.javaguides.springboot.fileuploaddownload.service.Impl;


import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.fileuploaddownload.constant.ErrorCodes;
import net.javaguides.springboot.fileuploaddownload.exception.customexception.ExistingDataException;
import net.javaguides.springboot.fileuploaddownload.model.ERole;
import net.javaguides.springboot.fileuploaddownload.model.ResponseModel;
import net.javaguides.springboot.fileuploaddownload.model.Status;
import net.javaguides.springboot.fileuploaddownload.model.User;
import net.javaguides.springboot.fileuploaddownload.payload.request.ForgotPasswordRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.LoginRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.SendCodeRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.SignOutRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.SignupRequest;
import net.javaguides.springboot.fileuploaddownload.payload.response.JwtResponse;
import net.javaguides.springboot.fileuploaddownload.security.jwt.JwtUtils;
import net.javaguides.springboot.fileuploaddownload.security.services.UserDetailsImpl;
import net.javaguides.springboot.fileuploaddownload.service.IUserService;
import net.javaguides.springboot.fileuploaddownload.repository.UserRepository;
import net.javaguides.springboot.fileuploaddownload.shared.CommonFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  private CommonFunctions commonFunctions;
  @Autowired
  JwtUtils jwtUtils;
  @Autowired
  private RedisService redisService;
  @Override
  public ResponseModel authenticateUser(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .toList();

    return new ResponseModel<Object>(true, new JwtResponse(jwt,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        userDetails.getFull_name(),
        roles));
  }

  @Override
  public ResponseModel registerUser(SignupRequest signUpRequest) {
    // get data from redis
    String value = redisService.getData(signUpRequest.getEmail());
    if(!value.equals(signUpRequest.getVerify_code())){
      return new ResponseModel<Object>(false, null,
          ErrorCodes.THE_CODE_IS_INCORRECT, ErrorCodes.ERROR_CODES.get(ErrorCodes.THE_CODE_IS_INCORRECT));
    }
    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()), signUpRequest.getFull_name());

    user.setRoles(Collections.singletonList(ERole.USER.name()));
    user.setStatus(Status.ACTIVE.name());
    userRepository.save(user);
    redisService.deleteData(signUpRequest.getEmail());
    return new ResponseModel<Object>(true, user);
  }

  @Override
  public User forgotPassword(User user, ForgotPasswordRequest forgotPasswordRequest) {
    // get data from redis
    String value = redisService.getData(forgotPasswordRequest.getEmail());
    if(!value.equals(forgotPasswordRequest.getCode())){
      throw new ExistingDataException(ErrorCodes.THE_CODE_IS_INCORRECT, ErrorCodes.ERROR_CODES.get(ErrorCodes.THE_CODE_IS_INCORRECT));
    }
    user.setPassword(encoder.encode(forgotPasswordRequest.getPassword()));
    userRepository.save(user);
    redisService.deleteData(forgotPasswordRequest.getEmail());
    return user;
  }

  @Override
  public boolean signOut(SignOutRequest SignOutRequest) {
    return true;
  }

  @Override
  public ResponseModel sendCode(SendCodeRequest sendCodeRequest) {
    String maCode ="";
    String email = sendCodeRequest.getEmail().trim().toLowerCase();
    // get data from redis
    maCode= redisService.getData(sendCodeRequest.getEmail());
    if(maCode == null){
      maCode = commonFunctions.code(email);
    }
    // Thêm dữ liệu vào Redis
    redisService.addData(sendCodeRequest.getEmail(), maCode, 1800);
    commonFunctions.sendEmail(sendCodeRequest.getEmail(), "XÁC THỰC MÃ OTP",maCode);
    return null;
  }
}
