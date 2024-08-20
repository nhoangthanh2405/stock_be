package net.javaguides.springboot.fileuploaddownload.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import net.javaguides.springboot.fileuploaddownload.config.ListToStringConverter;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  @JsonIgnore
  private String password;

  @NotBlank
  @Size(max = 60)
  private String full_name;


  private String status;


  @Convert(converter = ListToStringConverter.class)
  private List<String> roles = new ArrayList<>();

  public User() {
  }

  public User(String username, String email, String password, String fullName) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.full_name = fullName;
  }
}
