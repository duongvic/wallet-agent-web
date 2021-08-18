package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;


public class FindLocationRequest extends MobiliserRequestType {

  private String parentCode;//(String): optional; parentCode"

  private Integer locationType;//(Integer):required
  // 0 - country, 1-province/city, 2- district, 3- commue

  protected List<String> codes;

  public String getParentCode() {
    return parentCode;
  }

  public void setParentCode(String parentCode) {
    this.parentCode = parentCode;
  }

  public Integer getLocationType() {
    return locationType;
  }

  public void setLocationTypeId(Integer locationType) {
    this.locationType = locationType;
  }

  public FindLocationRequest(Integer locationType) {
    this.locationType = locationType;
  }

  public FindLocationRequest() {}

  public List<String> getCodes() {
    return codes;
  }

  public void setCodes(List<String> codes) {
    this.codes = codes;
  }
}
