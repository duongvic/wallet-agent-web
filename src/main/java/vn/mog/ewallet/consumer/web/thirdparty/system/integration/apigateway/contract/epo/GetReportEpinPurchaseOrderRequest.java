package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;


import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetReportEpinPurchaseOrderRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected List<Long> customerIds;
  protected List<String> statusIds;

  protected Date fromDate;
  protected Date endDate;

  public List<Long> getCustomerIds() {
    return customerIds;
  }

  public void setCustomerIds(List<Long> customerIds) {
    this.customerIds = customerIds;
  }

  public List<String> getStatusIds() {
    return statusIds;
  }

  public void setStatusIds(List<String> statusIds) {
    this.statusIds = statusIds;
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
}
