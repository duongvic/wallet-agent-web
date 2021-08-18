package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans;

import java.io.Serializable;

public class Customer implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String cif;
  private String username;
  private String secret;
  private String email;
  private String msisdn;
  private String fullName;
  private Long balance;
  private int customerType;
  private int walletType;
  private int userType;
  private boolean active;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public int getCustomerType() {
    return customerType;
  }

  public void setCustomerType(int customerType) {
    this.customerType = customerType;
  }

  public int getWalletType() {
    return walletType;
  }

  public void setWalletType(int walletType) {
    this.walletType = walletType;
  }

  public int getUserType() {
    return userType;
  }

  public void setUserType(int userType) {
    this.userType = userType;
  }

  public boolean isActive() {
    return active;
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
