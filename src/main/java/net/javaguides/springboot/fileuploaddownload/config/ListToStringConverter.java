package net.javaguides.springboot.fileuploaddownload.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;
import java.util.List;

@Converter
public class ListToStringConverter implements AttributeConverter<List<String>, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(List<String> list) {
    try {
      return objectMapper.writeValueAsString(list);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Could not convert list to JSON", e);
    }
  }

  @Override
  public List<String> convertToEntityAttribute(String dbData) {
    try {
      return objectMapper.readValue(dbData, List.class);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not convert JSON to list", e);
    }
  }
}
