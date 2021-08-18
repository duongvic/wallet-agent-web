package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class StaticBalance implements Serializable {

  private Balance balance;
  private List<Customer> customers;

  public Balance getBalance() {
    if (balance == null) {
      balance = new Balance();
    }
    return balance;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
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
}
