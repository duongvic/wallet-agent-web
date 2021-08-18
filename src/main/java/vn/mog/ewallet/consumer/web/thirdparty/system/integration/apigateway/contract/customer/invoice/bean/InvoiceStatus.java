package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean;

public enum InvoiceStatus {
  ACTIVE, PAID, PAYMENT_REVERSED, PAYMENT_FAILED, CANCELLED_BY_CUSTOMER, CANCELLED_BY_ISSUER, EXPIRED, FAILED, NEW;

  public String value() {
    return name();
  }

  public static InvoiceStatus fromValue(String v) {
    return valueOf(v);
  }
}
