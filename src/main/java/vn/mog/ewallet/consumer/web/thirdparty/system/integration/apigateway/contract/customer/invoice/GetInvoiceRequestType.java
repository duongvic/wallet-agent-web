package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetInvoiceRequestType extends MobiliserRequestType implements Serializable {
  private static final long serialVersionUID = 1L;

  private String serviceCode;
  private String invoiceReference;
  private String customerReference;

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public String getInvoiceReference() {
    return invoiceReference;
  }

  public void setInvoiceReference(String invoiceReference) {
    this.invoiceReference = invoiceReference;
  }

  public String getCustomerReference() {
    return customerReference;
  }

  public void setCustomerReference(String customerReference) {
    this.customerReference = customerReference;
  }
}
