package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info;


import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.SpecialCustomerItem;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetSpecialCustomerResponseType extends MobiliserResponseType {

  private SpecialCustomerItem specialCustomer;

  public SpecialCustomerItem getSpecialCustomer() {
    return specialCustomer;
  }

  public void setSpecialCustomer(
      SpecialCustomerItem specialCustomer) {
    this.specialCustomer = specialCustomer;
  }
}
