package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class OrderFlowSubmitProcessRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long orderId;
  protected String remark;


  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
