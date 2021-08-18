package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.framework.contract.base.MobiliserResponseType;

@SuppressWarnings("serial")
public class PhoneResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  String phone;

  public PhoneResponse() {
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
