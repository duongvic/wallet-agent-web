package vn.mog.ewallet.consumer.web.exception;


public class MessageNotify {

  public static final int SUCCESS_CODE = 0;
  public static final String SUCCESS_NAME = "Success!";

  public static final int ERROR_CODE = 1;
  public static final String ERROR_NAME = "Error!";

  public static final String CODE_ERR = "codeErr";
  public static final String MES_ERR = "mesErr";
  public static final String SESSION_MESSAGE_NOTIFY = "message_notify";

  private String message;
  private int code;

  public MessageNotify(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public MessageNotify() {

  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
