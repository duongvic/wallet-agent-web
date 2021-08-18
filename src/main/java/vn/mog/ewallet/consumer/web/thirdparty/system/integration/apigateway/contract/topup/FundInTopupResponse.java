package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.topup;

import java.io.Serializable;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FundInTopupResponse extends MobiliserResponseType implements Serializable {

  private Transaction transaction;

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(
      Transaction transaction) {
    this.transaction = transaction;
  }


}
