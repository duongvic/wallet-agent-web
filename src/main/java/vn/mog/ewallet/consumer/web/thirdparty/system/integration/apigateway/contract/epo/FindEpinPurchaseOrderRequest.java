package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;


import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindEpinPurchaseOrderRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  private String quickSearch;
  private List<Long> customerIds;
  private List<Integer> statusIds;
  private List<Integer> stageIds;
  private List<String> storeTypes;

  private Date fromDate;
  private Date toDate;

  private String[] orderBy;
  private boolean[] desc;

  private int offset;
  private int limit;

  public String getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(String quickSearch) {
    this.quickSearch = quickSearch;
  }

  public List<Long> getCustomerIds() {
    return customerIds;
  }

  public void setCustomerIds(List<Long> customerIds) {
    this.customerIds = customerIds;
  }

  public List<Integer> getStatusIds() {
    return statusIds;
  }

  public void setStatusIds(List<Integer> statusIds) {
    this.statusIds = statusIds;
  }

  public List<Integer> getStageIds() {
    return stageIds;
  }

  public void setStageIds(List<Integer> stageIds) {
    this.stageIds = stageIds;
  }

  public List<String> getStoreTypes() {
    return storeTypes;
  }

  public void setStoreTypes(List<String> storeTypes) {
    this.storeTypes = storeTypes;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public String[] getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String[] orderBy) {
    this.orderBy = orderBy;
  }

  public boolean[] getDesc() {
    return desc;
  }

  public void setDesc(boolean[] desc) {
    this.desc = desc;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }
}
