package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class ExportTransactionLogRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  protected String textSearch;
  protected List<Integer> statusIds;
  protected List<String> serviceTypeIds;
  protected List<String> serviceCodes;
  protected List<String> providerCodes;

  protected List<Long> customerIds; // priority : 1st
  protected List<String> cifs; // priority : 2st
  protected List<String> phoneNumbers; // priority : 3st

  protected Date fromDate;
  protected Date endDate;

  protected Integer offset;
  protected Integer limit;

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

  public List<Long> getCustomerIds() {
    return customerIds;
  }

  public void setCustomerIds(List<Long> customerIds) {
    this.customerIds = customerIds;
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

}
