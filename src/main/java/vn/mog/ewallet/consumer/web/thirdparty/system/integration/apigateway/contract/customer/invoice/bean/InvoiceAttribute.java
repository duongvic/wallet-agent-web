package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean;

import java.io.Serializable;
import java.util.Date;

public class InvoiceAttribute implements Serializable {

  private static final long serialVersionUID = 1L;
  protected long invoiceId;
  protected String invoiceAttributeTypeId;
  protected String value;

  protected Date created;

  public long getInvoiceId() {
    return this.invoiceId;
  }

  public void setInvoiceId(long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public String getInvoiceAttributeTypeId() {
    return this.invoiceAttributeTypeId;
  }

  public void setInvoiceAttributeTypeId(String invoiceAttributeTypeId) {
    this.invoiceAttributeTypeId = invoiceAttributeTypeId;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date value) {
    this.created = value;
  }
}
