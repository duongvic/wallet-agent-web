package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class VerifySetupPaymentPinResponse extends MobiliserResponseType {
  private String key;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
  
}
