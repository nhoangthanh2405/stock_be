package net.javaguides.springboot.fileuploaddownload.service;

import java.util.List;
import net.javaguides.springboot.fileuploaddownload.model.Stock;
import net.javaguides.springboot.fileuploaddownload.payload.request.StockRequest;
import net.javaguides.springboot.fileuploaddownload.payload.response.StockResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IStockService {
  StockResponse createStock(StockRequest stockRequest);
  StockResponse getStockById(Long id);
  List<StockResponse> getStocksByUserId(Long userId);
  Boolean deleteStockById(Long stockId);
}
