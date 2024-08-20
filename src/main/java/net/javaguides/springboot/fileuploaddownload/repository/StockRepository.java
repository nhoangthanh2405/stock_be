package net.javaguides.springboot.fileuploaddownload.repository;

import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import net.javaguides.springboot.fileuploaddownload.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
  @Query("SELECT s FROM Stock s WHERE s.user_id = :userId")
  List<Stock> findByUserId(@Param("userId") Long userId);
}
