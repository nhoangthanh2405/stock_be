package net.javaguides.springboot.fileuploaddownload.exception.customexception;

public class ExistingDataException extends RuntimeException {

  private final Integer codes;

  public ExistingDataException(String message, Integer codes) {
    super(message);
    this.codes = codes;
  }

  public Integer getCodes() {
    return codes;
  }
}
