package net.javaguides.springboot.fileuploaddownload.controller;
import net.javaguides.springboot.fileuploaddownload.model.ResponseModel;
import net.javaguides.springboot.fileuploaddownload.payload.request.StockRequest;
import net.javaguides.springboot.fileuploaddownload.service.Impl.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class StockController {

  @Autowired
  private StockService stockService;

  @GetMapping("")
  public ResponseModel createStock(@RequestBody StockRequest stock) {
    return new ResponseModel<Object>(true, stockService.createStock(stock));
  }
  @GetMapping("/{id}")
  public ResponseModel getStockById(@PathVariable Long id) {
    return new ResponseModel<Object>(true, stockService.getStockById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseModel deleteStockById(@PathVariable Long id) {
    return new ResponseModel<Object>(true, stockService.deleteStockById(id));
  }


  @GetMapping("/user/{userId}")
  public ResponseModel getStocksByUserId(@PathVariable Long userId) {
    return new ResponseModel<Object>(true, stockService.getStocksByUserId(userId));
  }
}
