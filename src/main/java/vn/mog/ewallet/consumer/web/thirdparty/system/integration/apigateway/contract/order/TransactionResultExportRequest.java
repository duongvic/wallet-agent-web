package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class TransactionResultExportRequest extends MobiliserRequestType {

  private String receivedAddress;
  private Long transactionId;
  private Integer sendMode;
  private String language;

  public String getReceivedAddress() {
    return receivedAddress;
  }

  public void setReceivedAddress(String receivedAddress) {
    this.receivedAddress = receivedAddress;
  }

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  public Integer getSendMode() {
    return sendMode;
  }

  public void setSendMode(Integer sendMode) {
    this.sendMode = sendMode;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }
}
