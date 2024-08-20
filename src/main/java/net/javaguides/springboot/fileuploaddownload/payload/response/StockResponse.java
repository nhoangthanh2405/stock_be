package net.javaguides.springboot.fileuploaddownload.payload.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import net.javaguides.springboot.fileuploaddownload.model.Stock;


@Data
public class StockResponse implements Serializable {
  private Long id;
  private String stock_code;
  private String reason_for_stock;
  private float starting_price;
  private float ending_price;
  private String start_date;
  private String end_date;
  private List<String> img_ids;
  private Long user_id;
  public StockResponse() {
  }

  public StockResponse(Stock stock) {
    this.id = stock.getId();
    this.stock_code = stock.getStock_code();
    this.reason_for_stock =  stock.getReason_for_stock();
    this.starting_price = stock.getStarting_price();
    this.ending_price = stock.getEnding_price();
    this.start_date = stock.getStart_date().toString();
    this.end_date = stock.getEnd_date().toString();
    this.user_id = stock.getUser_id();
  }
}
