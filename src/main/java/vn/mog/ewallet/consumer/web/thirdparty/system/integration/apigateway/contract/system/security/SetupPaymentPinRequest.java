package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class SetupPaymentPinRequest extends MobiliserRequestType {
  private String key;
  private String pin;

  public String getKey() {
    return key;
  }
  public void setKey(String key) {
    this.key = key;
  }

  public String getPin() {
    return pin;
  }
  public void setPin(String pin) {
    this.pin = pin;
  }

  



}
