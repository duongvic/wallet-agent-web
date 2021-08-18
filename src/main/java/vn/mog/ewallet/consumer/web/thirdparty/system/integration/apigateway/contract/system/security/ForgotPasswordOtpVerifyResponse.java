package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class ForgotPasswordOtpVerifyResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;
  
  private String key;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
