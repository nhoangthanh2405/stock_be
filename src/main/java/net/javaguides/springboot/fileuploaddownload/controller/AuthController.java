package net.javaguides.springboot.fileuploaddownload.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.fileuploaddownload.constant.ErrorCodes;
import net.javaguides.springboot.fileuploaddownload.model.ResponseModel;
import net.javaguides.springboot.fileuploaddownload.model.User;
import net.javaguides.springboot.fileuploaddownload.payload.request.ForgotPasswordRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.LoginRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.SendCodeRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.SignOutRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.SignupRequest;
import net.javaguides.springboot.fileuploaddownload.service.Impl.UserService;
import net.javaguides.springboot.fileuploaddownload.repository.UserRepository;
import net.javaguides.springboot.fileuploaddownload.shared.CommonFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  UserRepository userRepository;
  @Autowired
  private CommonFunctions commonFunctions;
  @Autowired
  private UserService userService;

  @PostMapping("/signin")
  public ResponseModel authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    loginRequest.setUsername(loginRequest.getUsername().trim().toLowerCase());

    try{
      return userService.authenticateUser(loginRequest);
    }catch (Exception e){
      return new ResponseModel<Object>(false, null,
          ErrorCodes.USERNAME_OR_PASSWORD_IS_INCORRECT, ErrorCodes.ERROR_CODES.get(ErrorCodes.USERNAME_OR_PASSWORD_IS_INCORRECT));
    }
  }

  @PostMapping("/signup")
  public ResponseModel registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    signUpRequest.setEmail(signUpRequest.getEmail().trim().toLowerCase());
    signUpRequest.setUsername(signUpRequest.getUsername().trim().toLowerCase());

    if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
      return new ResponseModel<Object>(false, null,
          ErrorCodes.USERNAME_IS_ALREADY_TAKEN, ErrorCodes.ERROR_CODES.get(ErrorCodes.USERNAME_IS_ALREADY_TAKEN));
    }
    if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
      return new ResponseModel<Object>(false, null,
          ErrorCodes.EMAIL_IS_ALREADY_IN_USE, ErrorCodes.ERROR_CODES.get(ErrorCodes.EMAIL_IS_ALREADY_IN_USE));
    }
    return userService.registerUser(signUpRequest);
  }

  @PostMapping("/send-code")
  public ResponseModel sendCode(@Valid @RequestBody SendCodeRequest sendCodeRequest) {
    return new ResponseModel<Object>(true, userService.sendCode(sendCodeRequest));
  }

  @PostMapping("/forgot-password")
  public ResponseModel forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
    String email = forgotPasswordRequest.getEmail().trim().toLowerCase();

    User user = userRepository.findByEmail(email).orElse(null);
    if (user == null) {
      return new ResponseModel<Object>(false, null,
          ErrorCodes.EMAIL_DOES_NOT_EXIST, ErrorCodes.ERROR_CODES.get(ErrorCodes.EMAIL_DOES_NOT_EXIST));
    }
    return new ResponseModel<Object>(true, userService.forgotPassword(user, forgotPasswordRequest));
  }

  @PostMapping("/signout")
  public ResponseModel signOut(@Valid @RequestBody SignOutRequest SignOutRequest) {
    return new ResponseModel<Object>(true, userService.signOut(SignOutRequest));
  }
}
