package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;

public enum PaymentSecurityType {

  NONE("NONE", "NONE"),
  OTP("OTP", "OTP"),
  PAYMENT_PASSWORD("PAYMENT_PASSWORD", "PAYMENT_PASSWORD"),
  PAYMENT_PIN("PAYMENT_PIN", "PAYMENT_PIN");

  private String code;
  private String name;

  PaymentSecurityType(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public static PaymentSecurityType getPaymentSecurityType(String label) {
    for (PaymentSecurityType item : PaymentSecurityType.values()) {
      if (item.name().equalsIgnoreCase(label) || item.code.equalsIgnoreCase(label)
          || item.name.equalsIgnoreCase(label))
        return item;
    }
    return null;
  }
}
