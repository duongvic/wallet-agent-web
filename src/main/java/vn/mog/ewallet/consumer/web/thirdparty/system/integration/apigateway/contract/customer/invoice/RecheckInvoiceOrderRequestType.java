package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class RecheckInvoiceOrderRequestType extends MobiliserRequestType implements Serializable {
  private static final long serialVersionUID = 1L;

  private String invoiceReference;
  private String customerReference;
  private Long transactionId;
  private String requestedId;

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

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  public String getRequestedId() {
    return requestedId;
  }

  public void setRequestedId(String requestedId) {
    this.requestedId = requestedId;
  }
}
