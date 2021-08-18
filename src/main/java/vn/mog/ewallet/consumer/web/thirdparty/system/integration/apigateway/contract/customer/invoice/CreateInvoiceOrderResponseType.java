package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice;

import java.io.Serializable;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean.InvoiceOrder;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class CreateInvoiceOrderResponseType extends MobiliserResponseType implements Serializable {
  private static final long serialVersionUID = 1L;

  protected InvoiceOrder invoiceOrder;

  public InvoiceOrder getInvoiceOrder() {
    return invoiceOrder;
  }

  public void setInvoiceOrder(InvoiceOrder invoiceOrder) {
    this.invoiceOrder = invoiceOrder;
  }
}
