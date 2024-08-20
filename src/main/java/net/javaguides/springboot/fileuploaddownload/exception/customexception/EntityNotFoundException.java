package net.javaguides.springboot.fileuploaddownload.exception.customexception;

public class EntityNotFoundException extends RuntimeException {

  private final Integer codes;

  public EntityNotFoundException(String message, Integer codes) {
    super(message);
    this.codes = codes;
  }

  public Integer getCodes() {
    return codes;
  }
}
