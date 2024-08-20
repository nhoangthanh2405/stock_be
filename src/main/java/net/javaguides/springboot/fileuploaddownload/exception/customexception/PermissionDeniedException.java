package net.javaguides.springboot.fileuploaddownload.exception.customexception;

public class PermissionDeniedException extends RuntimeException {

  private final Integer codes;

  public PermissionDeniedException(String message, Integer codes) {
    super(message);
    this.codes = codes;
  }

  public Integer getCodes() {
    return codes;
  }
}
