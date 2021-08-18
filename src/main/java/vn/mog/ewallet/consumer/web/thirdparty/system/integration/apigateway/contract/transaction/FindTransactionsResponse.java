package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction;

import java.util.ArrayList;
import java.util.Collection;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindTransactionsResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private Collection<Transaction> transactions;

  private Long totalTxn;

  private Long all;

  private Long totalCapitalValue;

  private Long totalNetAmount;

  private Long totalCommision;

  private Long totalFee;

  public Collection<Transaction> getTransactions() {
    if (transactions == null) {
      transactions = new ArrayList<>();
    }
    return transactions;
  }

  public void setTransactions(Collection<Transaction> transactions) {
    this.transactions = transactions;
  }

  public Long getTotalTxn() {
    return totalTxn;
  }

  public void setTotalTxn(Long totalTxn) {
    this.totalTxn = totalTxn;
  }

  public Long getAll() {
    return all;
  }

  public void setAll(Long all) {
    this.all = all;
  }

  public Long getTotalCapitalValue() {
    return totalCapitalValue;
  }

  public void setTotalCapitalValue(Long totalCapitalValue) {
    this.totalCapitalValue = totalCapitalValue;
  }

  public Long getTotalNetAmount() {
    return totalNetAmount;
  }

  public void setTotalNetAmount(Long totalNetAmount) {
    this.totalNetAmount = totalNetAmount;
  }

  public Long getTotalCommision() {
    return totalCommision;
  }

  public void setTotalCommision(Long totalCommision) {
    this.totalCommision = totalCommision;
  }

  public Long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Long totalFee) {
    this.totalFee = totalFee;
  }
}
