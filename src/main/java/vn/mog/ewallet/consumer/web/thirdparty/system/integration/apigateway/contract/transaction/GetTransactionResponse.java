package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction;

import java.util.Collection;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.TransactionEvent;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetTransactionResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Transaction transaction;
  protected Collection<TransactionEvent> transactionEvents;

  public Transaction getTransaction() {
    return (transaction == null) ? new Transaction() : transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public Collection<TransactionEvent> getTransactionEvents() {
    return transactionEvents;
  }

  public void setTransactionEvents(Collection<TransactionEvent> transactionEvents) {
    this.transactionEvents = transactionEvents;
  }
}
