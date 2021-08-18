package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.framework.contract.base.MobiliserRequestType;

@SuppressWarnings("serial")
public class GetEpinPurchaseOrderOTPConfirmRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private Long orderId;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }
}
