package net.javaguides.springboot.fileuploaddownload.payload.response;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import net.javaguides.springboot.fileuploaddownload.model.ChartIndicator;


@Data
public class ChartIndicatorResponse implements Serializable {
  private Long id;
  private String indicator_theory;
  private List<String> img_ids_indicator;
  private List<String> img_ids_chart;
  public ChartIndicatorResponse() {
  }

  public ChartIndicatorResponse(ChartIndicator chartIndicator) {
    this.id = chartIndicator.getId();
    this.indicator_theory = chartIndicator.getIndicator_theory();
  }
}
