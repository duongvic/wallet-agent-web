package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction;



import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.framework.contract.base.MobiliserResponseType;


public class TopupTransactionResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private Transaction transaction;

  private Long orderId;

  private Boolean isOtpRequired;

  private String paymentSecurityType;

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Boolean getIsOtpRequired() {
    return isOtpRequired;
  }

  public void setIsOtpRequired(Boolean isOtpRequired) {
    this.isOtpRequired = isOtpRequired;
  }

  public String getPaymentSecurityType() {
    return paymentSecurityType;
  }

  public void setPaymentSecurityType(String paymentSecurityType) {
    this.paymentSecurityType = paymentSecurityType;
  }
}
