package net.javaguides.springboot.fileuploaddownload.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.fileuploaddownload.model.ChartIndicator;
import net.javaguides.springboot.fileuploaddownload.model.Stock;
import net.javaguides.springboot.fileuploaddownload.payload.request.ChartIndicatorRequest;
import net.javaguides.springboot.fileuploaddownload.payload.request.StockRequest;
import net.javaguides.springboot.fileuploaddownload.payload.response.ChartIndicatorResponse;
import net.javaguides.springboot.fileuploaddownload.payload.response.ResponsePage;
import net.javaguides.springboot.fileuploaddownload.payload.response.StockResponse;
import net.javaguides.springboot.fileuploaddownload.repository.ChartIndicatorRepository;
import net.javaguides.springboot.fileuploaddownload.repository.StockRepository;
import net.javaguides.springboot.fileuploaddownload.service.IChartIndicatorService;
import net.javaguides.springboot.fileuploaddownload.service.IStockService;
import net.javaguides.springboot.fileuploaddownload.shared.CommonFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@Service
@Slf4j
public class ChartIndicatorService implements IChartIndicatorService {

  @Autowired
  private ChartIndicatorRepository chartIndicatorRepository;
  @Autowired
  private CommonFunctions commonFunctions;

  @Override
  public ChartIndicatorResponse createChartIndicator(ChartIndicatorRequest chartIndicatorRequest) {
    System.out.println(chartIndicatorRequest);
    ChartIndicator chartIndicator = new ChartIndicator();
    chartIndicator.setIndicator_theory(chartIndicatorRequest.getIndicatorTheory());
    chartIndicator.setImg_ids_chart(chartIndicatorRequest.getImgIdsChart().toString());
    chartIndicator.setImg_ids_indicator(chartIndicatorRequest.getImgIdsIndicator().toString());
    chartIndicatorRepository.save(chartIndicator);
    return convertChartIndiCatorToResponse(chartIndicator);
  }


  public ChartIndicatorResponse convertChartIndiCatorToResponse(ChartIndicator chartIndicator) {
    // Create the response object
    ChartIndicatorResponse chartIndicatorResponse = new ChartIndicatorResponse(chartIndicator);

    // Create lists to store the image URLs
    List<String> imgIdsChart = CommonFunctions.convertStringToArray(chartIndicator.getImg_ids_chart());
    List<String> imgIdsIndicator = CommonFunctions.convertStringToArray(chartIndicator.getImg_ids_indicator());

    // Asynchronously fetch image URLs for charts
    CompletableFuture<List<String>> imgsChartFuture = CompletableFuture.supplyAsync(() ->
        imgIdsChart.stream()
            .map(imgId -> commonFunctions.getUrlFileFromBe() + imgId)
            .collect(Collectors.toList())
    );

    // Asynchronously fetch image URLs for indicators
    CompletableFuture<List<String>> imgsIndicatorFuture = CompletableFuture.supplyAsync(() ->
        imgIdsIndicator.stream()
            .map(imgId -> commonFunctions.getUrlFileFromBe() + imgId)
            .collect(Collectors.toList())
    );

    // Combine the results and set them in the response object
    CompletableFuture.allOf(imgsChartFuture, imgsIndicatorFuture).join();

    try {
      // Wait for both futures to complete and get their results
      List<String> imgsChart = imgsChartFuture.get();
      List<String> imgsIndicator = imgsIndicatorFuture.get();

      // Set the image URLs in the response object
      chartIndicatorResponse.setImg_ids_chart(imgsChart);
      chartIndicatorResponse.setImg_ids_indicator(imgsIndicator); // Fixed typo here from img_ids_chart to img_ids_indicator

    } catch (Exception e) {
      // Handle exceptions appropriately
      log.error("Exception ", e);
    }

    return chartIndicatorResponse;
  }

  @Override
  public List<ChartIndicatorResponse> search(int pageNum, int pageSize) {
    Pageable pageable = PageRequest.of(pageNum, pageSize);
    Page<ChartIndicator> pageResult = chartIndicatorRepository.findAll(pageable);
    List<ChartIndicatorResponse> chartIndicatorResponses = new ArrayList<>();
    for (ChartIndicator c: pageResult.getContent()) {
        chartIndicatorResponses.add(convertChartIndiCatorToResponse(c));
    }
    return chartIndicatorResponses;
  }
}
