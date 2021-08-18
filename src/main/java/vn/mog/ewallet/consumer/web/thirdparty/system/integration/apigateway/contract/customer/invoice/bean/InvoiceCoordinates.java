package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean;

public class InvoiceCoordinates {

  protected String invoiceReference;

  protected String customerReference;

  protected String invoiceTypeReference;

  public String getInvoiceReference() {
    return this.invoiceReference;
  }

  public void setInvoiceReference(String value) {
    this.invoiceReference = value;
  }

  public String getCustomerReference() {
    return this.customerReference;
  }

  public void setCustomerReference(String value) {
    this.customerReference = value;
  }

  public String getInvoiceTypeReference() {
    return this.invoiceTypeReference;
  }

  public void setInvoiceTypeReference(String value) {
    this.invoiceTypeReference = value;
  }
}
