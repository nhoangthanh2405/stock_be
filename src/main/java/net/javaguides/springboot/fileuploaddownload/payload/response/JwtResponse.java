package net.javaguides.springboot.fileuploaddownload.payload.response;

import java.util.List;
import lombok.Data;

@Data
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private String full_name;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String username, String email, String fullName,List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.full_name = fullName;
    this.roles = roles;
  }
}
