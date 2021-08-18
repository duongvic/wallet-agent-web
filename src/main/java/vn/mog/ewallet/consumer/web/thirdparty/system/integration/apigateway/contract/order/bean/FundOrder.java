package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.bean;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FundOrder implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private Long refTxnId;
  private String serviceType; //  FUND_OUT // FUND_IN //
  private Long serviceId;
  private String serviceCode;
  private Long customerId;
  private String cif;
  private String username;
  private String orderChannel; //CASH_ON_HAND/ BANK_TRANSFER
  private String terminalId;
  private String extTxnId;
  private String bankCountryCode;
  private String bankCode;
  private String bankName;
  private String bankBranch;
  private String bankAccountNumber;
  private String bankAccountName;
  private String commandCode;
  private String syntax;
  private String info;
  private Long amount;
  private Long fee;
  private Integer stage;
  private int refTxnStatus;
  private Date createdTime;
  private Date tradingDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRefTxnId() {
    return refTxnId;
  }

  public void setRefTxnId(Long refTxnId) {
    this.refTxnId = refTxnId;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
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

  public String getOrderChannel() {
    return orderChannel;
  }

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public void setOrderChannel(String orderChannel) {
    this.orderChannel = orderChannel;
  }

  public String getExtTxnId() {
    return extTxnId;
  }

  public void setExtTxnId(String extTxnId) {
    this.extTxnId = extTxnId;
  }

  public String getBankCountryCode() {
    return bankCountryCode;
  }

  public void setBankCountryCode(String bankCountryCode) {
    this.bankCountryCode = bankCountryCode;
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

  public String getBankBranch() {
    return bankBranch;
  }

  public void setBankBranch(String bankBranch) {
    this.bankBranch = bankBranch;
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

  public String getCommandCode() {
    return commandCode;
  }

  public void setCommandCode(String commandCode) {
    this.commandCode = commandCode;
  }

  public String getSyntax() {
    return syntax;
  }

  public void setSyntax(String syntax) {
    this.syntax = syntax;
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

  public Long getFee() {
    return fee;
  }

  public void setFee(Long fee) {
    this.fee = fee;
  }

  public Integer getStage() {
    return stage;
  }

  public void setStage(Integer stage) {
    this.stage = stage;
  }

  public int getRefTxnStatus() {
    return refTxnStatus;
  }

  public void setRefTxnStatus(int refTxnStatus) {
    this.refTxnStatus = refTxnStatus;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Date getTradingDate() {
    return tradingDate;
  }

  public void setTradingDate(Date tradingDate) {
    this.tradingDate = tradingDate;
  }

}
