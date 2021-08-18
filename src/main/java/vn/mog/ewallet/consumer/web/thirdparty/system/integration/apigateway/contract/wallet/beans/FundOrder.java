package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.FundOrderChannelType;

public class FundOrder implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private Long refTxnId;

  private String serviceType;
  private Long serviceId;
  private String serviceCode;

  private Long customerId;
  private String cif;
  private String username;

  private String orderChannel;//"BANK_TRANSFER"", // Hình thức nạp tiền / chuyển khoản ngân hàng
  private String extTxnId;

  private String bankCountryCode;//"VN"", // Country code

  private String bankCode;//""BFTVVNVX"", // Swift Code Ngân hàng

  private String bankName;//Sacom bank"", // Tên ngân hàng

  private String bankBranch;//Chi nhanh Ha Noi"", // Chi nhánh

  private String bankAccountNumber;//000111002797"", // Số tài khoản

  private String bankAccountName;//"Cong ty Fintech"", // Tên tài khoản

  private String commandCode;//So giao dich ngan hang
  private String syntax;
  private String info;// Thông tin thêm

  private Long amount;// Số tiền chuyển
  private Long fee;// Phí

  private String terminalId;//terminal_id"": ""WEB"" // Thiết bị:API, WEB, APP, EDC;

  private Integer stage;
  private Integer refTxnStatus;

  private Date createdTime;


  public FundOrder(Long id, String info) {
    this.id = id;
    this.info = info;
  }

  public FundOrder(Long id, Long amount, String info) {
    this.id = id;
    this.amount = amount;
    this.info = info;
  }

  public FundOrder() {

  }

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
    if (stage == null) {
      stage = 0;
    }
    return stage;
  }

  public void setStage(Integer stage) {
    this.stage = stage;
  }

  public Integer getRefTxnStatus() {
    return refTxnStatus;
  }

  public void setRefTxnStatus(Integer refTxnStatus) {
    this.refTxnStatus = refTxnStatus;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public String textOrderChannel(String orderChannel) {

    if (FundOrderChannelType.BANK_TRANSFER.code.equals(orderChannel)) {
      return FundOrderChannelType.BANK_TRANSFER.displayText;

    } else if (FundOrderChannelType.LINK_BANK.code.equals(orderChannel)) {
      return FundOrderChannelType.LINK_BANK.displayText;

    } else if (FundOrderChannelType.CASH_ON_HAND.code.equals(orderChannel)) {
      return FundOrderChannelType.CASH_ON_HAND.displayText;
    }
    return orderChannel;
  }

  public String getTextOrderChannel() {

    if (FundOrderChannelType.BANK_TRANSFER.code.equals(orderChannel)) {
      return FundOrderChannelType.BANK_TRANSFER.displayText;

    } else if (FundOrderChannelType.LINK_BANK.code.equals(orderChannel)) {
      return FundOrderChannelType.LINK_BANK.displayText;

    } else if (FundOrderChannelType.CASH_ON_HAND.code.equals(orderChannel)) {
      return FundOrderChannelType.CASH_ON_HAND.displayText;
    }
    return orderChannel;
  }

}
