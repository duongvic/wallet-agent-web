package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean;

public enum TypicalSecuritySetup {

  CHANGE_PASSWORD(1), PAYMENT_PIN(2), SMS(3), GG(4), FORGET_PASSWORD(5);

  public int code;

  TypicalSecuritySetup(int code) {
    this.code = code;
  }

  public static TypicalSecuritySetup invalidType(int type) {
    for (TypicalSecuritySetup item : TypicalSecuritySetup.values()) {
      if (item.code == type) {
        return item;
      }
    }
    return null;
  }

}
