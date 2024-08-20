package net.javaguides.springboot.fileuploaddownload.service.Impl;

import java.util.ArrayList;
import java.util.List;
import net.javaguides.springboot.fileuploaddownload.exception.customexception.ExistingDataException;
import net.javaguides.springboot.fileuploaddownload.model.Stock;
import net.javaguides.springboot.fileuploaddownload.payload.request.StockRequest;
import net.javaguides.springboot.fileuploaddownload.payload.response.StockResponse;
import net.javaguides.springboot.fileuploaddownload.service.IStockService;
import net.javaguides.springboot.fileuploaddownload.repository.StockRepository;
import net.javaguides.springboot.fileuploaddownload.shared.CommonFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService implements IStockService {
  @Autowired
  private StockRepository stockRepository;
  @Autowired
  private CommonFunctions commonFunctions;

  private String URL_FILE =  "http://localhost:8080/api/files/";
  @Override
  public StockResponse createStock(StockRequest stockRequest) {
    Stock stock = new Stock();
    stock.setStock_code(stockRequest.getStockCode());
    stock.setReason_for_stock(stockRequest.getReasonForStockSelection());
    stock.setStarting_price(stockRequest.getStartingPrice());
    stock.setEnding_price(stockRequest.getEndingPrice());
    stock.setStart_date(commonFunctions.dateConversionLocalDateTime(stockRequest.getStartDate()));
    stock.setEnd_date(commonFunctions.dateConversionLocalDateTime(stockRequest.getEndDate()));
    stock.setImg_ids(stockRequest.getImgIds().toString());
    stock.setUser_id(stockRequest.getUserId());
    stockRepository.save(stock);
    return convertStockToStockResponse(stock);
  }

  @Override
  public StockResponse getStockById(Long id) {
    return convertStockToStockResponse(stockRepository.findById(id).orElse(null));
  }

  @Override
  public List<StockResponse> getStocksByUserId(Long userId) {
    List<StockResponse> stockResponses = new ArrayList<>();
    for (Stock stock: stockRepository.findByUserId(userId) ) {
      stockResponses.add(convertStockToStockResponse(stock));
    }
    return stockResponses;
  }

  @Override
  public Boolean deleteStockById(Long stockId) {
    stockRepository.deleteById(stockId);
    return true;
  }

  public StockResponse convertStockToStockResponse(Stock stock){
    StockResponse stockResponse = new StockResponse(stock);
    List<String> imgs = new ArrayList<>();
    for (String imgIds: CommonFunctions.convertStringToArray(stock.getImg_ids())) {
      String url = URL_FILE + imgIds;
      imgs.add(url);
    }
    stockResponse.setImg_ids(imgs);
    return stockResponse;
  }
}
