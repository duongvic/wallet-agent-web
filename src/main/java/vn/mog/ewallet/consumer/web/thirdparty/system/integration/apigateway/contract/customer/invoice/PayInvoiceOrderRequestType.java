package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class PayInvoiceOrderRequestType extends MobiliserRequestType implements Serializable {
  private static final long serialVersionUID = 1L;

  private String requestId;
  private Long invoiceOrderId;
  private String otp;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public Long getInvoiceOrderId() {
    return invoiceOrderId;
  }

  public void setInvoiceOrderId(Long invoiceOrderId) {
    this.invoiceOrderId = invoiceOrderId;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }
}
