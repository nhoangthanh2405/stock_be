package net.javaguides.springboot.fileuploaddownload.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class DatabaseInitializer {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @PostConstruct
  public void init() {
    String sql = "ALTER TABLE files MODIFY COLUMN data LONGBLOB";
    try {
      jdbcTemplate.execute(sql);
      System.out.println("Successfully modified column 'data' to LONGBLOB.");
    } catch (Exception e) {
      System.err.println("Failed to modify column 'data': " + e.getMessage());
    }
  }
}
