package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindTransactionsRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private String textSearch;

  protected List<String> cifs;

  protected List<String> phoneNumbers;

  private List<Integer> statusIds;

  private List<String> serviceTypeIds;

  private List<String> serviceCodes;

  private List<String> providerCodes;

  private Date fromDate;

  private Date endDate;

  private Integer offset;

  private Integer limit;

  private Boolean isOrderInclude;

  public String getTextSearch() {
    return textSearch;
  }

  public void setTextSearch(String textSearch) {
    this.textSearch = textSearch;
  }

  public List<Integer> getStatusIds() {
    return statusIds;
  }

  public void setStatusIds(List<Integer> statusIds) {
    this.statusIds = statusIds;
  }

  public List<String> getServiceTypeIds() {
    return serviceTypeIds;
  }

  public void setServiceTypeIds(List<String> serviceTypeIds) {
    this.serviceTypeIds = serviceTypeIds;
  }

  public List<String> getServiceCodes() {
    return serviceCodes;
  }

  public void setServiceCodes(List<String> serviceCodes) {
    this.serviceCodes = serviceCodes;
  }

  public List<String> getProviderCodes() {
    return providerCodes;
  }

  public void setProviderCodes(List<String> providerCodes) {
    this.providerCodes = providerCodes;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
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

  public List<String> getCifs() {
    return cifs;
  }

  public void setCifs(List<String> cifs) {
    this.cifs = cifs;
  }

  public List<String> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(List<String> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public Boolean getIsOrderInclude() {
    return isOrderInclude;
  }

  public void setIsOrderInclude(Boolean isOrderInclude) {
    this.isOrderInclude = isOrderInclude;
  }
}
