package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans.Customer;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindFullCustomerResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected List<Customer> customers;
  protected Long total;

  public FindFullCustomerResponse() {
  }

  public List<Customer> getCustomers() {
    if (customers == null) {
      customers = new ArrayList<>();
    }
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public Long getTotal() {
    return (total == null) ? 0 : total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }
}
