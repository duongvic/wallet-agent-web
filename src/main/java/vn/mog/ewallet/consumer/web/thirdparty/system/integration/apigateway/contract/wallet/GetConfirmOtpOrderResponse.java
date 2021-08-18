package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetConfirmOtpOrderResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

  private String otp;
}
