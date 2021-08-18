package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.PaymentSecurityType;

import java.io.Serializable;
import java.util.Date;

public class InvoiceOrder implements Serializable {  private static final long serialVersionUID = 1L;

  private Long invoiceOrderId;
  private Long serviceId;
  private Long invoiceId;
  private Long customerId;
  private String info;
  private Long amount;
  private Long cashBackAmount;
  private Long fee;
  private Long commission;
  private Long capitalValue;
  private Integer status = Integer.valueOf(0);
  private Long txnId;
  private Boolean isPayall;
  private Boolean otpRequired;
  private PaymentSecurityType paymentSecurityType;

  private Date createdTime;

  public Long getInvoiceOrderId() {
    return invoiceOrderId;
  }

  public void setInvoiceOrderId(Long invoiceOrderId) {
    this.invoiceOrderId = invoiceOrderId;
  }

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public Long getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(Long invoiceId) {
    this.invoiceId = invoiceId;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getCashBackAmount() {
    return cashBackAmount;
  }

  public void setCashBackAmount(Long cashBackAmount) {
    this.cashBackAmount = cashBackAmount;
  }

  public Long getFee() {
    return fee;
  }

  public void setFee(Long fee) {
    this.fee = fee;
  }

  public Long getCommission() {
    return commission;
  }

  public void setCommission(Long commission) {
    this.commission = commission;
  }

  public Long getCapitalValue() {
    return capitalValue;
  }

  public void setCapitalValue(Long capitalValue) {
    this.capitalValue = capitalValue;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getTxnId() {
    return txnId;
  }

  public void setTxnId(Long txnId) {
    this.txnId = txnId;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Boolean getIsPayall() {
    return isPayall;
  }

  public void setIsPayall(Boolean isPayall) {
    this.isPayall = isPayall;
  }

  public Boolean getOtpRequired() {
    return otpRequired;
  }

  public void setOtpRequired(Boolean otpRequired) {
    this.otpRequired = otpRequired;
  }

  public PaymentSecurityType getPaymentSecurityType() {
    return paymentSecurityType;
  }

  public void setPaymentSecurityType(
          PaymentSecurityType paymentSecurityType) {
    this.paymentSecurityType = paymentSecurityType;
  }}
