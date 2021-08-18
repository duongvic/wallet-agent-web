package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean;

import java.util.Date;

public class Customer {
  private Long id;

  private String cif;

  private String email;

  private String msisdn;

  private String firstname;

  private String lastname;

  private Integer customerTypeId;

  private String address;
  private String commune;
  private String district;
  private String province;
  private String country;

  private Integer genderId;

  private Date dob;

  private String identityCard;

  private Date issueIdentityCardDate;

  private String issuePlaceIdentityCardDate;

  private String issuerPlaceIdentityCardDate;

  private String companyName;

  private String fullCompanyName;

  private String tradingName;

  private String businessType;

  private String businessCode;

  private String taxCode;

  private CustomerProfileStatus profileStatus;

  private Integer blackListReasonId;

  public String getEmail() {
    return email;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public Integer getCustomerTypeId() {
    return customerTypeId;
  }

  public String getAddress() {
    return address;
  }

  public String getCommune() {
    return commune;
  }

  public String getDistrict() {
    return district;
  }

  public String getProvince() {
    return province;
  }

  public String getCountry() {
    return country;
  }

  public Integer getGenderId() {
    return genderId;
  }

  public Date getDob() {
    return dob;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setCustomerTypeId(Integer customerTypeId) {
    this.customerTypeId = customerTypeId;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setCommune(String commune) {
    this.commune = commune;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setGenderId(Integer genderId) {
    this.genderId = genderId;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public Long getId() {
    return id;
  }

  public String getCif() {
    return cif;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public CustomerProfileStatus getProfileStatus() {
    return profileStatus;
  }

  public void setProfileStatus(CustomerProfileStatus profileStatus) {
    this.profileStatus = profileStatus;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getFullCompanyName() {
    return fullCompanyName;
  }

  public String getTradingName() {
    return tradingName;
  }

  public String getBusinessType() {
    return businessType;
  }

  public String getBusinessCode() {
    return businessCode;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setFullCompanyName(String fullCompanyName) {
    this.fullCompanyName = fullCompanyName;
  }

  public void setTradingName(String tradingName) {
    this.tradingName = tradingName;
  }

  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }

  public void setBusinessCode(String businessCode) {
    this.businessCode = businessCode;
  }

  public String getIdentityCard() {
    return identityCard;
  }

  public Date getIssueIdentityCardDate() {
    return issueIdentityCardDate;
  }

  public String getIssuePlaceIdentityCardDate() {
    return issuePlaceIdentityCardDate;
  }

  public String getIssuerPlaceIdentityCardDate() {
    return issuerPlaceIdentityCardDate;
  }

  public String getTaxCode() {
    return taxCode;
  }

  public void setIdentityCard(String identityCard) {
    this.identityCard = identityCard;
  }

  public void setIssueIdentityCardDate(Date issueIdentityCardDate) {
    this.issueIdentityCardDate = issueIdentityCardDate;
  }

  public void setIssuePlaceIdentityCardDate(String issuePlaceIdentityCardDate) {
    this.issuePlaceIdentityCardDate = issuePlaceIdentityCardDate;
  }

  public void setIssuerPlaceIdentityCardDate(String issuerPlaceIdentityCardDate) {
    this.issuerPlaceIdentityCardDate = issuerPlaceIdentityCardDate;
  }

  public void setTaxCode(String taxCode) {
    this.taxCode = taxCode;
  }

  public Integer getBlackListReasonId() {
    return blackListReasonId;
  }

  public void setBlackListReasonId(Integer blackListReasonId) {
    this.blackListReasonId = blackListReasonId;
  }
}
