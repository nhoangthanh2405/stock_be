package net.javaguides.springboot.fileuploaddownload.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {
  @NotBlank
  @Size(min = 6, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  @NotBlank
  @Size(min = 3, max = 60)
  private String full_name;

  @NotBlank
  @Size(min = 3, max = 60)
  private String verify_code;
}
