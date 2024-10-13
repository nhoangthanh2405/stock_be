package net.javaguides.springboot.fileuploaddownload.service;

import java.util.List;
import net.javaguides.springboot.fileuploaddownload.model.ChartIndicator;
import net.javaguides.springboot.fileuploaddownload.payload.request.ChartIndicatorRequest;
import net.javaguides.springboot.fileuploaddownload.payload.response.ChartIndicatorResponse;
import net.javaguides.springboot.fileuploaddownload.payload.response.ResponsePage;

public interface IChartIndicatorService {
  ChartIndicatorResponse createChartIndicator(ChartIndicatorRequest chartIndicatorRequest);

  List<ChartIndicatorResponse> search(int pageNum, int  pageSize);
}
