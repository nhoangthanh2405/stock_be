package net.javaguides.springboot.fileuploaddownload.payload.response;

import java.io.Serializable;
import java.util.List;

public class ResponsePage<T> implements Serializable {
  private int page;
  private int pageSize;
  private int totalElement;
  private List<T> data;

  public int getPage() {
    return this.page;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public int getTotalElement() {
    return this.totalElement;
  }

  public List<T> getData() {
    return this.data;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public void setTotalElement(int totalElement) {
    this.totalElement = totalElement;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof ResponsePage)) {
      return false;
    } else {
      ResponsePage<?> other = (ResponsePage)o;
      if (!other.canEqual(this)) {
        return false;
      } else if (this.getPage() != other.getPage()) {
        return false;
      } else if (this.getPageSize() != other.getPageSize()) {
        return false;
      } else if (this.getTotalElement() != other.getTotalElement()) {
        return false;
      } else {
        Object this$data = this.getData();
        Object other$data = other.getData();
        if (this$data == null) {
          if (other$data != null) {
            return false;
          }
        } else if (!this$data.equals(other$data)) {
          return false;
        }

        return true;
      }
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof ResponsePage;
  }


  public String toString() {
    int var10000 = this.getPage();
    return "ResponsePage(page=" + var10000 + ", pageSize=" + this.getPageSize() + ", totalElement=" + this.getTotalElement() + ", data=" + this.getData() + ")";
  }

  public ResponsePage(int page, int pageSize, int totalElement, List<T> data) {
    this.page = page;
    this.pageSize = pageSize;
    this.totalElement = totalElement;
    this.data = data;
  }

  public ResponsePage() {
  }
}
