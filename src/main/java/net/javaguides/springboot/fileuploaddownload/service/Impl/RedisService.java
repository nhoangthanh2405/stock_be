package net.javaguides.springboot.fileuploaddownload.service.Impl;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  // Thêm dữ liệu vào Redis với TTL
  public void addData(String key, String value, long ttlInSeconds) {
    redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlInSeconds));
  }
  // Lấy dữ liệu từ Redis
  public String getData(String key) {
    return (String) redisTemplate.opsForValue().get(key);
  }
  // Xóa dữ liệu từ Redis
  public void deleteData(String key) {
    redisTemplate.delete(key);
  }

  // Xóa tất cả dữ liệu (xóa toàn bộ cache)
  public void clearCache() {
    redisTemplate.getConnectionFactory().getConnection().flushDb();
  }
}
