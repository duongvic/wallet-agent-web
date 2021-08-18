package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans;

import java.io.Serializable;

public class HierarchicalCustomer extends Customer implements Serializable {

  private static final long serialVersionUID = 1L;
  protected int hierarchylevel;

  public int getHierarchylevel() {
    return this.hierarchylevel;
  }

  public void setHierarchylevel(int value) {
    this.hierarchylevel = value;
  }
}
