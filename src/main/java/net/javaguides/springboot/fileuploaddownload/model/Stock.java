package net.javaguides.springboot.fileuploaddownload.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "stock")
@Data
public class Stock implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String stock_code;
  private String reason_for_stock;
  private float starting_price;
  private float ending_price;
  private LocalDateTime start_date;
  private LocalDateTime end_date;
  private String img_ids;
  private Long user_id;

}
