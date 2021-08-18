package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

public class CustomerVerifyKycRequest {

  @JsonIgnore
  protected Long id;
  @JsonIgnore
  protected Long customerId;
  @JsonIgnore
  protected String cif;
  @JsonIgnore
  protected Integer requestStatusId;
  @JsonIgnore
  protected String remark;
  @JsonIgnore
  protected String msisdn;
  @JsonIgnore
  protected Integer kycStatus;
  
  protected String fistName;
  protected String lastName;
  
  protected Date dateOfBirth;

  protected Identity identity;
  protected String permanentResident;
  protected String province;
  protected String district;

  protected String frontName;
  protected String frontContentType;
  protected String frontContent;

  protected String backName;
  protected String backContentType;
  protected String backContent;

  protected String selfieName;
  protected String selfieContentType;
  protected String selfieContent;

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

  public Integer getRequestStatusId() {
    return requestStatusId;
  }

  public void setRequestStatusId(Integer requestStatusId) {
    this.requestStatusId = requestStatusId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getFistName() {
    return fistName;
  }

  public void setFistName(String fistName) {
    this.fistName = fistName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }
  
  public Integer getKycStatus() {
    return kycStatus;
  }

  public void setKycStatus(Integer kycStatus) {
    this.kycStatus = kycStatus;
  }

  public String getFrontName() {
    return frontName;
  }

  public void setFrontName(String frontName) {
    this.frontName = frontName;
  }

  public String getFrontContentType() {
    return frontContentType;
  }

  public void setFrontContentType(String frontContentType) {
    this.frontContentType = frontContentType;
  }

  public String getBackName() {
    return backName;
  }

  public void setBackName(String backName) {
    this.backName = backName;
  }

  public String getBackContentType() {
    return backContentType;
  }

  public void setBackContentType(String backContentType) {
    this.backContentType = backContentType;
  }

  public String getSelfieName() {
    return selfieName;
  }

  public void setSelfieName(String selfieName) {
    this.selfieName = selfieName;
  }

  public String getSelfieContentType() {
    return selfieContentType;
  }

  public void setSelfieContentType(String selfieContentType) {
    this.selfieContentType = selfieContentType;
  }

  public Identity getIdentity() {
    return identity;
  }

  public void setIdentity(Identity identity) {
    this.identity = identity;
  }

  public String getPermanentResident() {
    return permanentResident;
  }

  public void setPermanentResident(String permanentResident) {
    this.permanentResident = permanentResident;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public void setFrontContent(String frontContent) {
    this.frontContent = frontContent;
  }

  public void setBackContent(String backContent) {
    this.backContent = backContent;
  }

  public void setSelfieContent(String selfieContent) {
    this.selfieContent = selfieContent;
  }

  public String getFrontContent() {
    return frontContent;
  }

  public String getBackContent() {
    return backContent;
  }

  public String getSelfieContent() {
    return selfieContent;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
  
}
