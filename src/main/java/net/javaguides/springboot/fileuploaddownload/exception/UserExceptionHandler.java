package net.javaguides.springboot.fileuploaddownload.exception;


import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.fileuploaddownload.exception.customexception.EntityNotFoundException;
import net.javaguides.springboot.fileuploaddownload.model.ResponseModel;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class UserExceptionHandler {

  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<ResponseModel<Void>> handleInvalidPathVariableException(
      IllegalArgumentException e) {
    log.error("Exception: " + e.getMessage() + " with type: " + e.getClass());
    return new ResponseEntity<>(
        new ResponseModel<>(false, null, "The path variable is invalid", 4000), HttpStatus.OK);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ResponseModel<Void>> handleException(Exception e) {
    int lastIndex = e.getClass().toString().lastIndexOf(".");
    String ExceptionConvertUUID = "";
    if (lastIndex != -1) {
      ExceptionConvertUUID = e.getClass().toString().substring(lastIndex + 1);
      if ("MethodArgumentTypeMismatchException".equals(ExceptionConvertUUID)) {
        return new ResponseEntity<>(
            new ResponseModel<>(false, null, "Invalid data", 1500), HttpStatus.OK);
      }
    }

    log.error("Exception: " + e.getMessage() + " with type: " + e.getClass());
    StackTraceElement[] stackTrace = e.getStackTrace();
    if (stackTrace.length > 0) {
      StackTraceElement errorElement = stackTrace[0];
      log.error(
          "Error occurred in: "
              + errorElement.getClassName()
              + ", method: "
              + errorElement.getMethodName()
              + ", line: "
              + errorElement.getLineNumber()
              + ", filename: "
              + errorElement.getFileName()
              + ", error: "
              + e.getMessage());
    }
    return new ResponseEntity<>(
        new ResponseModel<>(false, null, "Something went wrong. Please try again later.", 1500),
        HttpStatus.OK);
  }


  @ExceptionHandler(value = EntityNotFoundException.class)
  public ResponseEntity<ResponseModel<?>> handleObjectNotFoundException(
      EntityNotFoundException ex) {
    log.error("Error: Failure occurs with message: " + ex.getMessage());
    String errorMessage = ex.getMessage();
    ResponseModel<?> responseModel = new ResponseModel<>(false, null, errorMessage, ex.getCodes());
    return new ResponseEntity<>(responseModel, HttpStatus.OK);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseModel<Void>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    FieldError error = ex.getBindingResult().getFieldError();
    String fieldName = error.getField();
    String errorMessage = error.getDefaultMessage();
    return new ResponseEntity<>(
        new ResponseModel<>(false, null, fieldName + ": " + errorMessage, 1512), HttpStatus.OK);
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.OK)
  public ResponseModel<Object> handlerRequestException(NotFoundException ex) {
    return new ResponseModel<>(false, null, ex.getMessage(), HttpStatus.NOT_FOUND.value());
  }

  @ExceptionHandler({AuthenticationException.class})
  @ResponseStatus(HttpStatus.OK)
  public ResponseModel<Object> unauthorizedException(AuthenticationException ex) {
    return new ResponseModel<>(false, null, ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
  }

  @ExceptionHandler({ForbiddenException.class})
  @ResponseStatus(HttpStatus.OK)
  public ResponseModel<Object> forbiddenException(ForbiddenException ex) {
    return new ResponseModel<>(false, null, ex.getMessage(), HttpStatus.FORBIDDEN.value());
  }

}
