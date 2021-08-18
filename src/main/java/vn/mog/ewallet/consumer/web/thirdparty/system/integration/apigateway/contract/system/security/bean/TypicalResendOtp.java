package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean;

public enum TypicalResendOtp {

  CREATE_ACCOUNT(1), SIGN_IN_SMS(2), FORGOT_PASSWORD(3), CHANGE_PASSWORD(4), PAYMENT_PIN(5), TWO_FACTOR(6);

  private int code;

  TypicalResendOtp(int code) {
    this.code = code;
  }

  public static TypicalResendOtp invalidType(int type) {
    for (TypicalResendOtp item : TypicalResendOtp.values()) {
      if (item.code == type) {
        return item;
      }
    }
    return null;
  }

  public int getCode() {
    return code;
  }
}
