package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class CheckEpinPurchaseOrderResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private Long balance;
  private Long totalDiscountAmount;
  private Long totalFeeAmount;

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public Long getTotalDiscountAmount() {
    return totalDiscountAmount;
  }

  public void setTotalDiscountAmount(Long totalDiscountAmount) {
    this.totalDiscountAmount = totalDiscountAmount;
  }

  public Long getTotalFeeAmount() {
    return totalFeeAmount;
  }

  public void setTotalFeeAmount(Long totalFeeAmount) {
    this.totalFeeAmount = totalFeeAmount;
  }
}
