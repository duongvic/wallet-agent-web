package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type;

public enum SendModeType {

  SEND_MODE_SMS(1), SEND_MODE_EMAIL(2), SEND_MODE_BOTH(3);

  public Integer code;

  SendModeType(Integer code) {
    this.code = code;
  }

  public static SendModeType getModeById(Integer code) {
    if (code != null) {
      for (SendModeType sendModeType : SendModeType.values()) {
        if (code.equals(sendModeType.code)) {

          return sendModeType;
        }
      }
    }

    throw new NullPointerException("Do not have Send Mode of type #" + code);
  }

}
