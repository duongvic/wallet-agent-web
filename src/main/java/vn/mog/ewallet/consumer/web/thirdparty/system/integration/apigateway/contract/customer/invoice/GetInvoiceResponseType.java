package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice;

import java.io.Serializable;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean.Invoice;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetInvoiceResponseType extends MobiliserResponseType implements Serializable {
  private static final long serialVersionUID = 1L;
  protected Invoice invoice;

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }
}
