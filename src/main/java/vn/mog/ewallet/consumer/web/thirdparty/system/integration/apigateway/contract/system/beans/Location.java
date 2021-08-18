package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Location implements Serializable {

  private String id;
  private String name;
  private String code;
  private String parentCode;
  private String locationType;

  public static final Integer COUNTRY = 0;
  public static final Integer CITY = 1;
  public static final Integer DISTRICT = 2;
  public static final Integer COMMUE = 3;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getParentCode() {
    return parentCode;
  }

  public void setParentCode(String parentCode) {
    this.parentCode = parentCode;
  }

  public String getLocationType() {
    return locationType;
  }

  public void setLocationType(String locationType) {
    this.locationType = locationType;
  }
}
