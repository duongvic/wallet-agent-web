package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class SelfCustomerGetInfoRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Long customerId;
  protected String customerCif;


  public Long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(long value) {
    this.customerId = value;
  }

  public String getCustomerCif() {
    return customerCif;
  }

  public void setCustomerCif(String customerCif) {
    this.customerCif = customerCif;
  }

}
