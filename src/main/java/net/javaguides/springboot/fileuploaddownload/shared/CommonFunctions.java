package net.javaguides.springboot.fileuploaddownload.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.javaguides.springboot.fileuploaddownload.config.ListToStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CommonFunctions {
  @Autowired
  private JavaMailSender mailSender;

  public void sendEmail(String toEmail, String subjeact, String body) {
    System.out.println("=============CODE========== " +"Email: " + toEmail +" "+ body);
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("letamxi87@gmail.com");
    message.setTo(toEmail);
    message.setText("Mã OTP của bạn: "+body);
    message.setSubject(subjeact);
    mailSender.send(message);
  }
  public boolean isValidEmail(String email) {
    String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public String code(String email) {
    int i = 0;
    String code = null;
    do {
      code = generateRandomNumber(email);
      if (i == 10) {
        break;
      }
      i += 1;
    } while (code.length() != 6);
    return code;
  }

  public String generateRandomNumber(String phoneOrEmail) {
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      int randomNumber = random.nextInt(10); // Generate a random number from 0 to 9
      sb.append(randomNumber);
    }
    return sb.toString();
  }

  public LocalDateTime dateConversionLocalDateTime(String date){
    // Chuyển đổi chuỗi thành LocalDate
    LocalDate localDate = LocalDate.parse(date);

    // Kết hợp LocalDate với LocalTime để tạo LocalDateTime
    return localDate.atStartOfDay();
  }

  public static List<String> convertStringToArray(String input) {
    // Loại bỏ dấu ngoặc vuông và khoảng trắng
    String cleanedStr = input.replaceAll("[\\[\\]\\s]", "");

    // Chia chuỗi thành mảng các phần tử
    return Arrays.asList(cleanedStr.split(","));
  }
}
