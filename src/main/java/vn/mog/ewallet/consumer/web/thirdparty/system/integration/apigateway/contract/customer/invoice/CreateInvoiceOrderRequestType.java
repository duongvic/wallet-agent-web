package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CreateInvoiceOrderRequestType extends MobiliserRequestType implements Serializable {
  private static final long serialVersionUID = 1L;

  protected long invoiceId;
  protected long amount;
  protected boolean isPayAll;
  private String customerPhone;

  public long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public boolean isPayAll() {
    return isPayAll;
  }

  public void setPayAll(boolean isPayAll) {
    this.isPayAll = isPayAll;
  }

  public String getCustomerPhone() {
    return customerPhone;
  }

  public void setCustomerPhone(String customerPhone) {
    this.customerPhone = customerPhone;
  }
}
