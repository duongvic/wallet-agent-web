package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindTrueServiceRequestType extends MobiliserRequestType implements Serializable {
  private static final long serialVersionUID = 1L;

  protected String quickSearch;
  protected List<String> serviceTypes;
  private List<String> serviceCodes;
  protected Character active;
  protected Integer level;
  protected Character inTree;

  protected Date fromDate;
  protected Date endDate;
  protected Integer limit;
  protected Integer offset;

  public String getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(String quickSearch) {
    this.quickSearch = quickSearch;
  }

  public List<String> getServiceTypes() {
    return serviceTypes;
  }

  public void setServiceTypes(List<String> serviceTypes) {
    this.serviceTypes = serviceTypes;
  }
  
  public void setServiceCodes(List<String> serviceCodes) {
    this.serviceCodes = serviceCodes;
  }
  
  public List<String> getServiceCodes() {
    return serviceCodes;
  }

  public Character getActive() {
    return active;
  }

  public void setActive(Character active) {
    this.active = active;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
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

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Character getInTree() {
    return inTree;
  }

  public void setInTree(Character inTree) {
    this.inTree = inTree;
  }
}
