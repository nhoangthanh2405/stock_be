package net.javaguides.springboot.fileuploaddownload.controller;
import java.util.List;
import net.javaguides.springboot.fileuploaddownload.model.ChartIndicator;
import net.javaguides.springboot.fileuploaddownload.model.ResponseModel;
import net.javaguides.springboot.fileuploaddownload.payload.request.ChartIndicatorRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.StockRequest;
import net.javaguides.springboot.fileuploaddownload.payload.response.ChartIndicatorResponse;
import net.javaguides.springboot.fileuploaddownload.payload.response.ResponsePage;
import net.javaguides.springboot.fileuploaddownload.service.Impl.ChartIndicatorService;
import net.javaguides.springboot.fileuploaddownload.service.Impl.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://stock-fe-nhoangthanh-tma.vercel.app") // Thay đổi URL với frontend của bạn
@RequestMapping("/api/chart-indicator")
public class ChartIndicatorController {

  @Autowired
  private ChartIndicatorService chartIndicatorService;

  @PostMapping("")
  public ResponseModel createChartIndicator(@RequestBody ChartIndicatorRequest chartIndicatorRequest) {
    return new ResponseModel<Object>(true, chartIndicatorService.createChartIndicator(chartIndicatorRequest));
  }

  @GetMapping("/search")
  public ResponseModel search(
      @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
    List<ChartIndicatorResponse> response = chartIndicatorService.search(pageNum, pageSize);
    return new ResponseModel<Object>(true, response);
  }


}
