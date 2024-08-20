package net.javaguides.springboot.fileuploaddownload.payload.request;

import java.util.List;
import lombok.Data;


@Data
public class StockRequest {
  private String stockCode;
  private String reasonForStockSelection;
  private float startingPrice;
  private float endingPrice;
  private String startDate;
  private String endDate;
  private List<String> imgIds;
  private Long userId;
}
