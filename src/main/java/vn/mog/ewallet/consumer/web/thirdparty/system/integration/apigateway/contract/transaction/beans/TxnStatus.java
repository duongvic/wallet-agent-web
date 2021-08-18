package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans;

import java.util.LinkedHashMap;
import java.util.Map;


public class TxnStatus {

  public static final int INITIAL = 0;
  public static final int OPENED = 1;
  public static final int FAILED = 3;
  public static final int REVERSED = 5;
  public static final int CANCELLED = 7;
  public static final int CLOSED = 10;

  public static final Map<Integer, String> TXN_STATUS_LIST = new LinkedHashMap<>();
  public static final Map<Integer, String> TRANSACTION_STATUSES = new LinkedHashMap<>();

  static {
    TXN_STATUS_LIST.put(INITIAL, "txn.status.initial");
    TXN_STATUS_LIST.put(OPENED, "txn.status.opened");
    TXN_STATUS_LIST.put(FAILED, "txn.status.failed");
    TXN_STATUS_LIST.put(REVERSED, "txn.status.reversed");
    TXN_STATUS_LIST.put(CANCELLED, "txn.status.cancelled");
    TXN_STATUS_LIST.put(CLOSED, "txn.status.closed");

    TRANSACTION_STATUSES.put(TxnStatus.INITIAL, "transaction.api.search.status.init");
    TRANSACTION_STATUSES.put(TxnStatus.OPENED, "transaction.api.search.status.open");
    TRANSACTION_STATUSES.put(TxnStatus.CANCELLED, "transaction.api.search.status.cancel");
    TRANSACTION_STATUSES.put(TxnStatus.FAILED, "transaction.api.search.status.fail");
    TRANSACTION_STATUSES.put(TxnStatus.CLOSED, "transaction.api.search.status.close");
  }
}
