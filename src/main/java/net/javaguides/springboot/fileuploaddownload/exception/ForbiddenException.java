package net.javaguides.springboot.fileuploaddownload.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public ForbiddenException(String message) {
    super(message);
  }

}
