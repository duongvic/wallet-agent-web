package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans;

public enum TrueFundPaymentGatewayPaymentType {
  BANK_CHARGING(0, "Bank charging"), VISA_CHARGING(1, "Visa charging");

  public Integer code;
  public String value;

  TrueFundPaymentGatewayPaymentType(Integer code, String value) {
    this.code = code;
    this.value = value;
  }

}
