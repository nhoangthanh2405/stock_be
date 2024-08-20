package net.javaguides.springboot.fileuploaddownload.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendCodeRequest {
  @NotBlank
  private String email;
}
