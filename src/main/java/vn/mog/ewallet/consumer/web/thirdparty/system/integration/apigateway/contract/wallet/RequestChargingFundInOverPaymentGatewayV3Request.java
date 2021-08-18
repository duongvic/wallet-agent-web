package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet;

import java.math.BigDecimal;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class RequestChargingFundInOverPaymentGatewayV3Request
    extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private BigDecimal amount;
  private String resultUrl;
  private String bankCode;
  private Integer paymentType;
  private String customerName;
  private String customerEmail;
  private String customerPhone;
  private String language;
  private String signature;

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getResultUrl() {
    return resultUrl;
  }

  public void setResultUrl(String resultUrl) {
    this.resultUrl = resultUrl;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public Integer getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(Integer paymentType) {
    this.paymentType = paymentType;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public String getCustomerPhone() {
    return customerPhone;
  }

  public void setCustomerPhone(String customerPhone) {
    this.customerPhone = customerPhone;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }
  
}
