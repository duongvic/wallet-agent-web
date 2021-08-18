package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class UpdateCustomerAttributeResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  String infor;

  public UpdateCustomerAttributeResponse() {
  }

  public String getInfor() {
    return infor;
  }

  public void setInfor(String infor) {
    this.infor = infor;
  }
}
