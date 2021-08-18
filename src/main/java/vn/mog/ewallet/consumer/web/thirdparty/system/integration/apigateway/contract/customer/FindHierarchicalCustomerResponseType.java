package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans.HierarchicalCustomer;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindHierarchicalCustomerResponseType extends MobiliserResponseType implements
    Serializable {

  private static final long serialVersionUID = 1L;
  protected List<HierarchicalCustomer> customers;

  public List<HierarchicalCustomer> getCustomers() {
    if (this.customers == null) {
      this.customers = new ArrayList<HierarchicalCustomer>();
    }
    return this.customers;
  }
}
