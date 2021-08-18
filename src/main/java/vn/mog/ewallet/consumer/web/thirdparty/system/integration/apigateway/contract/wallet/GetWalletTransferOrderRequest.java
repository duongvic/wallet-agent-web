package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetWalletTransferOrderRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long orderId;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public GetWalletTransferOrderRequest(Long orderId) {
    this.orderId = orderId;
  }

  public GetWalletTransferOrderRequest() {

  }
}
