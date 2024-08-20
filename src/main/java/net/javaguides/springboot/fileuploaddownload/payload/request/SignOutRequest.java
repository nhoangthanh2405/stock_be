package net.javaguides.springboot.fileuploaddownload.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignOutRequest {

  private Long id;

  @NotBlank
  @Size(min = 6, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
}
