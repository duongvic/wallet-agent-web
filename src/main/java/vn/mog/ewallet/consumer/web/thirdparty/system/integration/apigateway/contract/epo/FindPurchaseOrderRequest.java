package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

@SuppressWarnings("serial")
public class FindPurchaseOrderRequest extends MobiliserRequestType {
  private String quickSearch;
  private List<String> provider;
  private List<Integer> workflowState;
  private List<String> status;

  private Date fromDate;
  private Date endDate;

  private int offset;
  private int limit;

  public String getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(String quickSearch) {
    this.quickSearch = quickSearch;
  }

  public List<String> getProvider() {
    return provider;
  }

  public void setProvider(List<String> provider) {
    this.provider = provider;
  }

  public List<Integer> getWorkflowState() {
    return workflowState;
  }

  public void setWorkflowState(List<Integer> workflowState) {
    this.workflowState = workflowState;
  }

  public List<String> getStatus() {
    return status;
  }

  public void setStatus(List<String> status) {
    this.status = status;
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
