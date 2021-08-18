package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans;

import java.io.Serializable;
import java.util.Date;

public class TransactionTopup implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Date createdDate;
  private String phoneNumber;
  private String amount;
  private String telco;

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

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getTelco() {
    return telco;
  }

  public void setTelco(String telco) {
    this.telco = telco;
  }


}