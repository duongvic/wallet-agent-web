package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans.CustomerAttribute;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateCustomerAttributeRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private List<CustomerAttribute> customerAttributes;
  private long customerId;
  private String msisdn;
  private String cusApiIPs;
  private Boolean activeOrInactiveApi;

  public UpdateCustomerAttributeRequest() {
  }

  public UpdateCustomerAttributeRequest(List<CustomerAttribute> customerAttributes) {
    this.customerAttributes = customerAttributes;
  }

  public List<CustomerAttribute> getCustomerAttributes() {
    return customerAttributes;
  }

  public void setCustomerAttributes(List<CustomerAttribute> customerAttributes) {
    this.customerAttributes = customerAttributes;
  }

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getCusApiIPs() {
    return cusApiIPs;
  }

  public void setCusApiIPs(String cusApiIPs) {
    this.cusApiIPs = cusApiIPs;
  }

  public Boolean getActiveOrInactiveApi() {
    return activeOrInactiveApi;
  }

  public void setActiveOrInactiveApi(Boolean activeOrInactiveApi) {
    this.activeOrInactiveApi = activeOrInactiveApi;
  }
}
