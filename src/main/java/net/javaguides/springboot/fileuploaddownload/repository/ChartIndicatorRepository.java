package net.javaguides.springboot.fileuploaddownload.repository;

import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import net.javaguides.springboot.fileuploaddownload.model.ChartIndicator;
import net.javaguides.springboot.fileuploaddownload.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartIndicatorRepository extends JpaRepository<ChartIndicator, Long> {
}
