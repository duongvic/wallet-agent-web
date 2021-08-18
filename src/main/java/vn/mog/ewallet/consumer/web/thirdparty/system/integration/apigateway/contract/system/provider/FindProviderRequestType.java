package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider;

import java.io.Serializable;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindProviderRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;
  protected String textSearch;
  protected List<String> serviceTypeIds;

  protected Boolean active;

  protected Integer offset;
  protected Integer limit;

  public String getTextSearch() {
    return textSearch;
  }

  public void setTextSearch(String textSearch) {
    this.textSearch = textSearch;
  }

  public List<String> getServiceTypeIds() {
    return serviceTypeIds;
  }

  public void setServiceTypes(List<String> serviceTypeIds) {
    this.serviceTypeIds = serviceTypeIds;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }
}
