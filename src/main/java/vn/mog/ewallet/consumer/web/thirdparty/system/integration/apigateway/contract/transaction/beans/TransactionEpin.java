package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans;

import java.io.Serializable;
import java.util.Date;

public class TransactionEpin implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Date createdDate;
  private String amount;
  private String cardType;
  private String serviceName;

  private String orderInfo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getOrderInfo() {
    return orderInfo;
  }

  public void setOrderInfo(String orderInfo) {
    this.orderInfo = orderInfo;
  }
}