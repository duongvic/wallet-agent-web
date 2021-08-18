package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class CreateWalletTransferOrderResponse extends MobiliserResponseType{

  private static final long serialVersionUID = 1L;

  private Long orderId;

  private Boolean isOtpRequired;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Boolean getOtpRequired() {
    return isOtpRequired;
  }

  public void setOtpRequired(Boolean otpRequired) {
    isOtpRequired = otpRequired;
  }
}
