package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;



import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetCustomerBalanceResponse extends MobiliserResponseType {
  private Long id;

  private Long customerId;

  private String customerCif;

  private String alias;

  private boolean active;

  private String currency;

  private Long creditBalance;

  private Long creditReserved;

  private Long debitBalance;

  private Long debitReserved;

  private Long maxBalance;

  private Long minBalance;

  private Integer customerTypeId;

  private Integer userTypeId;

  private Integer walletTypeId;

  private Long balance;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getCustomerCif() {
    return customerCif;
  }

  public void setCustomerCif(String customerCif) {
    this.customerCif = customerCif;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Long getCreditBalance() {
    return creditBalance;
  }

  public void setCreditBalance(Long creditBalance) {
    this.creditBalance = creditBalance;
  }

  public Long getCreditReserved() {
    return creditReserved;
  }

  public void setCreditReserved(Long creditReserved) {
    this.creditReserved = creditReserved;
  }

  public Long getDebitBalance() {
    return debitBalance;
  }

  public void setDebitBalance(Long debitBalance) {
    this.debitBalance = debitBalance;
  }

  public Long getDebitReserved() {
    return debitReserved;
  }

  public void setDebitReserved(Long debitReserved) {
    this.debitReserved = debitReserved;
  }

  public Long getMaxBalance() {
    return maxBalance;
  }

  public void setMaxBalance(Long maxBalance) {
    this.maxBalance = maxBalance;
  }

  public Long getMinBalance() {
    return minBalance;
  }

  public void setMinBalance(Long minBalance) {
    this.minBalance = minBalance;
  }

  public Integer getCustomerTypeId() {
    return customerTypeId;
  }

  public void setCustomerTypeId(Integer customerTypeId) {
    this.customerTypeId = customerTypeId;
  }

  public Integer getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(Integer userTypeId) {
    this.userTypeId = userTypeId;
  }

  public Integer getWalletTypeId() {
    return walletTypeId;
  }

  public void setWalletTypeId(Integer walletTypeId) {
    this.walletTypeId = walletTypeId;
  }

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }
}
