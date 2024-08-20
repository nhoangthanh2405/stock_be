package net.javaguides.springboot.fileuploaddownload.exception;

import java.io.Serial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class BadRequestException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String objectName, String fieldName, Object value) {
    super(objectName + " already exists with " + fieldName + ": " + value);
  }

}
