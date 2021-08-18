package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans;

public class TxnStatus {
  public static final int INITIAL = 0;
  public static final int OPENED = 1;
  public static final int FAILED = 3;
  public static final int REVERSED = 5;
  public static final int CANCELLED = 7;
  public static final int CLOSED = 10;
}
