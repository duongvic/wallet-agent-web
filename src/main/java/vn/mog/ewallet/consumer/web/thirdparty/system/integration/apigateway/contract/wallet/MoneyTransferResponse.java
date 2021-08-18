package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class MoneyTransferResponse extends MobiliserResponseType{

 private static final long serialVersionUID = 1L;

 private Long orderId;

 private Boolean isOtpRequired;

 private Long transactionId;

 private String payeeFullName;

 private Boolean isPayeeActive;

 protected String paymentSecurityType;

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

 public Long getTransactionId() {
  return transactionId;
 }

 public void setTransactionId(Long transactionId) {
  this.transactionId = transactionId;
 }

 public String getPayeeFullName() {
  return payeeFullName;
 }

 public void setPayeeFullName(String payeeFullName) {
  this.payeeFullName = payeeFullName;
 }

 public Boolean getIsPayeeActive() {
  return isPayeeActive;
 }

 public void setIsPayeeActive(Boolean isPayeeActive) {
  this.isPayeeActive = isPayeeActive;
 }

 public String getPaymentSecurityType() {
  return paymentSecurityType;
 }

 public void setPaymentSecurityType(String paymentSecurityType) {
  this.paymentSecurityType = paymentSecurityType;
 }

}
