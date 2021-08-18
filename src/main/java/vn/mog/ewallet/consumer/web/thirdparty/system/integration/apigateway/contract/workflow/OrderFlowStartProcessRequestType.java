package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class OrderFlowStartProcessRequestType extends MobiliserResponseType implements
    Serializable {

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
