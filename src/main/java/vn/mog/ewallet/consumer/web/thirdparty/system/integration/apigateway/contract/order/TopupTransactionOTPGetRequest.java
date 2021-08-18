package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class TopupTransactionOTPGetRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private Long orderId;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }
}
