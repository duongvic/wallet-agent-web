package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerBankDirect implements Serializable {
  private static final long serialVersionUID = 1L;

  private String subscriptionId;
  /*    */

  private Date createdTime;

  private Long creatorId;

  private Date lastUpdatedTime;

  private Long lastUpdatedId;
  
  /*    */
  private String cif;

  private String bankName;

  private String bankDisplayName;

  private String bankCode;

  private String bankAccountNumber;

  private String bankAccountName;

  private Date linkedDate;

  private Date unlinkedDate;

  private String status;

  private String ssn;

  private String phone;

  private String walletId;

  private String description;

  private String linkType;

  private String cardIssueDate;

  private String channel;

  public CustomerBankDirect() {
    super();
  }

  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Long getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Long creatorId) {
    this.creatorId = creatorId;
  }

  public Date getLastUpdatedTime() {
    return lastUpdatedTime;
  }

  public void setLastUpdatedTime(Date lastUpdatedTime) {
    this.lastUpdatedTime = lastUpdatedTime;
  }

  public Long getLastUpdatedId() {
    return lastUpdatedId;
  }

  public void setLastUpdatedId(Long lastUpdatedId) {
    this.lastUpdatedId = lastUpdatedId;
  }

  public Date getLinkedDate() {
    return linkedDate;
  }

  public void setLinkedDate(Date linkedDate) {
    this.linkedDate = linkedDate;
  }

  public Date getUnlinkedDate() {
    return unlinkedDate;
  }

  public void setUnlinkedDate(Date unlinkedDate) {
    this.unlinkedDate = unlinkedDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getBankAccountNumber() {
    return bankAccountNumber;
  }

  public void setBankAccountNumber(String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }

  public String getBankAccountName() {
    return bankAccountName;
  }

  public void setBankAccountName(String bankAccountName) {
    this.bankAccountName = bankAccountName;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getWalletId() {
    return walletId;
  }

  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLinkType() {
    return linkType;
  }

  public void setLinkType(String linkType) {
    this.linkType = linkType;
  }

  public String getCardIssueDate() {
    return cardIssueDate;
  }

  public void setCardIssueDate(String cardIssueDate) {
    this.cardIssueDate = cardIssueDate;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBankDisplayName() {
    return bankDisplayName;
  }

  public void setBankDisplayName(String bankDisplayName) {
    this.bankDisplayName = bankDisplayName;
  }
}
