package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.io.Serializable;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans.Customer;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetCustomerByIdentificationResponseType extends MobiliserResponseType implements
    Serializable {

  private static final long serialVersionUID = 1L;
  protected Customer customer;

  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer value) {
    this.customer = value;
  }
}
