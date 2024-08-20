package net.javaguides.springboot.fileuploaddownload.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
public class ResponseModel<T> implements Serializable {

  @Autowired
  @JsonIgnore
  private Gson gson;
  private T data;

  private Boolean success = false;
  private Object error = "";

  public ResponseModel() {
    this.data = null;
  }

  public ResponseModel(Boolean success, T oData) {
    this.data = oData;
    this.success = success;
    this.error = null;
  }

  public ResponseModel(Boolean success, T oData, String errorMessage, int errCode) {
    this.data = oData;
    this.success = success;
    Map<String, Object> errorObject = new HashMap<>();
    errorObject.put("message", errorMessage);
    errorObject.put("code", errCode);
    this.error = errorObject;
  }

  public Boolean getSuccess() {
    return success;
  }

  @Override
  public String toString() {
    return "ResponseModel {"
        + "success: "
        + success
        + ", error: "
        + gson.toJson(this.error)
        + ", data: "
        + gson.toJson(this.data)
        + "}";
  }
}
