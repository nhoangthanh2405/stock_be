package net.javaguides.springboot.fileuploaddownload.service;

import net.javaguides.springboot.fileuploaddownload.model.ResponseModel;
import net.javaguides.springboot.fileuploaddownload.model.User;
import net.javaguides.springboot.fileuploaddownload.payload.request.ForgotPasswordRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.LoginRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.SendCodeRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.SignOutRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.SignupRequest;

public interface IUserService {
  ResponseModel authenticateUser(LoginRequest loginRequest);

  ResponseModel registerUser(SignupRequest signUpRequest);

  User forgotPassword(User user, ForgotPasswordRequest forgotPasswordRequest);

  boolean signOut(SignOutRequest SignOutRequest);

  ResponseModel sendCode(SendCodeRequest sendCodeRequest);
}
