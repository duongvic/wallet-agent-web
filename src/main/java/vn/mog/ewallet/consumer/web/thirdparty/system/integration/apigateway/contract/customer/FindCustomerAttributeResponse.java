package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.util.List;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans.CustomerAttribute;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindCustomerAttributeResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;
  
  private List<CustomerAttribute> customerAttributes;

  public List<CustomerAttribute> getCustomerAttributes() {
    return customerAttributes;
  }

  public void setCustomerAttributes(List<CustomerAttribute> customerAttributes) {
    this.customerAttributes = customerAttributes;
  }

}
