package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class CheckPaymentPinResponse extends MobiliserResponseType {
  private Boolean isSetup;

  public Boolean getIsSetup() {
    return isSetup;
  }

  public void setIsSetup(Boolean isSetup) {
    this.isSetup = isSetup;
  }
  
  
}