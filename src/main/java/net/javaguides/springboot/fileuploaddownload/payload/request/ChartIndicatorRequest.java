package net.javaguides.springboot.fileuploaddownload.payload.request;

import java.util.List;
import lombok.Data;


@Data
public class ChartIndicatorRequest {
  private String indicatorTheory;
  private List<String> imgIdsIndicator;
  private List<String> imgIdsChart;
}

