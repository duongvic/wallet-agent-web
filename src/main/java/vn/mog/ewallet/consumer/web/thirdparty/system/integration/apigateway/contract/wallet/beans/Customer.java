package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.CUSTOMER_STATUS_IDS;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.CUST_ACTIVE;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.CUST_IN_ACTIVE;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class Customer {

  private Long id;
  private Long parentId;
  private String cif;
  private String userName;
  private String displayName;
  private String timeZone;
  private String language;
  private String country;
  private Date dateOfBirth;
  private String securityQuestion;
  private String securityAnswer;
  private String customerType;
  private String customerStatus;
  private Date createdTime;
  private String walletTypeId;
  private String userTypeId;

  private CustomerTransactionBean lastTransaction;
  private Balance balance;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getDisplayName() {
    if (displayName == null) {
      displayName = StringUtils.EMPTY;
    }
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getSecurityQuestion() {
    return securityQuestion;
  }

  public void setSecurityQuestion(String securityQuestion) {
    this.securityQuestion = securityQuestion;
  }

  public String getSecurityAnswer() {
    return securityAnswer;
  }

  public void setSecurityAnswer(String securityAnswer) {
    this.securityAnswer = securityAnswer;
  }

  public String getCustomerType() {
    return customerType;
  }

  public void setCustomerType(String customerType) {
    this.customerType = customerType;
  }

  public String getCustomerStatus() {
    return customerStatus;
  }

  public void setCustomerStatus(String customerStatus) {
    this.customerStatus = customerStatus;
  }

  public String textCustoemrStatus() {
    if ("ACTIVED".equals(customerStatus)) {
      return CUSTOMER_STATUS_IDS.get(CUST_ACTIVE);
    } else {
      return CUSTOMER_STATUS_IDS.get(CUST_IN_ACTIVE);
    }
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public String getWalletTypeId() {
    return walletTypeId;
  }

  public void setWalletTypeId(String walletTypeId) {
    this.walletTypeId = walletTypeId;
  }

  public String getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(String userTypeId) {
    this.userTypeId = userTypeId;
  }

  public Balance getBalance() {
    return balance;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
  }

  public CustomerTransactionBean getLastTransaction() {
    return lastTransaction;
  }

  public void setLastTransaction(CustomerTransactionBean lastTransaction) {
    this.lastTransaction = lastTransaction;
  }

  public String getLastTransactionIdValue() {
    if (lastTransaction != null) {
//      return String.valueOf(lastTransaction.getTxnId());
      return String.valueOf(lastTransaction.getRefOrderId());
    }
    return StringUtils.EMPTY;
  }

  public String getLastTransactionService() {
    if (lastTransaction != null) {
      return String.valueOf(lastTransaction.getService());
    }
    return StringUtils.EMPTY;
  }
}