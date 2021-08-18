package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class SecurityPaymentGetOTPRequest extends MobiliserRequestType {
  private String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}