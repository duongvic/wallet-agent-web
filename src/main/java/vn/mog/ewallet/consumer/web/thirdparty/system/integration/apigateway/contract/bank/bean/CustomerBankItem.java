package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.bean;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerBankItem implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String cif;

  @JsonIgnore
  private Long customerId;

  private String itemType;

  private String itemHolderName;

  private String itemNumber;

  private String bankCode;

  private String branchCode;

  private String branchName;

  private String bankName;

  private Date registerDate;

  private String bankCountry;

  private String bankCity;

  private Boolean active;

  private boolean verify;

  private Date endDate;

  private String itemIssueDate;

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

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }
  
  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
  }

  public String getItemHolderName() {
    return itemHolderName;
  }

  public void setItemHolderName(String itemHolderName) {
    this.itemHolderName = itemHolderName;
  }

  public String getItemNumber() {
    return itemNumber;
  }

  public void setItemNumber(String itemNumber) {
    this.itemNumber = itemNumber;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public Date getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(Date registerDate) {
    this.registerDate = registerDate;
  }

  public String getBankCountry() {
    return bankCountry;
  }

  public void setBankCountry(String bankCountry) {
    this.bankCountry = bankCountry;
  }

  public String getBankCity() {
    return bankCity;
  }

  public void setBankCity(String bankCity) {
    this.bankCity = bankCity;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public boolean isVerify() {
    return verify;
  }

  public void setVerify(boolean verify) {
    this.verify = verify;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getItemIssueDate() {
    return itemIssueDate;
  }

  public void setItemIssueDate(String itemIssueDate) {
    this.itemIssueDate = itemIssueDate;
  }
}
