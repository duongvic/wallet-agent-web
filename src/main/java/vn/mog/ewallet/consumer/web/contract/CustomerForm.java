package vn.mog.ewallet.consumer.web.contract;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans.Customer;

/**
 * Created by binhminh on 25/09/2017.
 */
public class CustomerForm {

  private Long id;
  private String fullName;

  public CustomerForm(Customer item) {
    this.id = item.getId();
    this.fullName = item.getFullName();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
}
