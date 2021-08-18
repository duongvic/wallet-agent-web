package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetLastTransactionResponse extends MobiliserResponseType{

  private static final long serialVersionUID = 1L;

  private Long balance;

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

}
