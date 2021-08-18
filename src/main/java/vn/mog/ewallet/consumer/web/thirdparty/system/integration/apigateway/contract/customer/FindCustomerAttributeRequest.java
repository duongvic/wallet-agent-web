package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindCustomerAttributeRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  protected Long customerId;
  protected String cif;
  protected List<Integer> customerAttributeTypeIds;
  protected Character incInternal;

  public Long getCustomerId() {
    return customerId;
  }

  public List<Integer> getCustomerAttributeTypeIds() {
    return customerAttributeTypeIds;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public void setCustomerAttributeTypeIds(List<Integer> customerAttributeTypeIds) {
    this.customerAttributeTypeIds = customerAttributeTypeIds;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public Character getIncInternal() {
    return incInternal;
  }

  public void setIncInternal(Character incInternal) {
    this.incInternal = incInternal;
  }



}
