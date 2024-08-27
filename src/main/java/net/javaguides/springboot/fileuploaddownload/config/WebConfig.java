package net.javaguides.springboot.fileuploaddownload.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:4200",
            "https://stock-fe-nhoangthanh.vercel.app",
            "http://stock-fe-nhoangthanh.vercel.app")  // Các origin mà bạn muốn cho phép
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("*")
        .allowCredentials(true);  // Cho phép sử dụng credentials (cookie, authorization header)
  }
}
