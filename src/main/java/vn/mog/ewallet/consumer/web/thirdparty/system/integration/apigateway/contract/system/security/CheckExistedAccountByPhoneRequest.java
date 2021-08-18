package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class CheckExistedAccountByPhoneRequest extends MobiliserRequestType {

  private String msisdn;

  public CheckExistedAccountByPhoneRequest() {
  }

  public CheckExistedAccountByPhoneRequest(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

}
