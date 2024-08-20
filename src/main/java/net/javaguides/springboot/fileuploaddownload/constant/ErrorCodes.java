package net.javaguides.springboot.fileuploaddownload.constant;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {

  public static final int ERROR_CODE = 60000;

  public static final Map<String, Integer> ERROR_CODES = new HashMap<>();
  public static final String USERNAME_IS_ALREADY_TAKEN = "Error: Username is already taken.";
  public static final String EMAIL_IS_ALREADY_IN_USE = "Error: Email is already in use.";
  public static final String USERNAME_OR_PASSWORD_IS_INCORRECT = "Error: Username or password is incorrect.";

  public static final String USERNAME_DOES_NOT_EXIST = "Error: Username does not exist.";
  public static final String EMAIL_DOES_NOT_EXIST = "Error: Email does not exist.";
  public static final String THE_CODE_IS_INCORRECT = "Error: The code is incorrect.";
  public static final String FILE_UPLOAD_FAILED = "Error: File upload failed.";

  static {
    // Create a new HashMap and populate it with the error codes
    // Add more key-value pairs as needed

    // Make the errorCodes map immutable using Collections.unmodifiableMap
    ERROR_CODES.put(USERNAME_IS_ALREADY_TAKEN, 60001);
    ERROR_CODES.put(EMAIL_IS_ALREADY_IN_USE, 60002);
    ERROR_CODES.put(USERNAME_OR_PASSWORD_IS_INCORRECT, 60003);
    ERROR_CODES.put(USERNAME_DOES_NOT_EXIST, 60004);
    ERROR_CODES.put(EMAIL_DOES_NOT_EXIST, 60005);
    ERROR_CODES.put(THE_CODE_IS_INCORRECT, 60006);
    ERROR_CODES.put(FILE_UPLOAD_FAILED, 60007);
  }
}