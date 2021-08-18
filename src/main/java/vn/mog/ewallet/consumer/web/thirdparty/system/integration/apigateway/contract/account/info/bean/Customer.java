package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.OutletStoreType;

public class Customer implements Serializable {
  protected Long id;

  protected Long parentId;

  protected String parentName;

  protected String prarentEmail;

  protected String parentCif;

  protected String parentMsisdn;

  protected List<Address> addresses;
  @JsonIgnore
  protected OrgUnit orgUnit;

  protected Integer blackListReason;
  @JsonIgnore
  protected Boolean active;
  @JsonIgnore
  protected Boolean test;
  protected String displayName;
  protected String timeZone;
  protected String language;
  protected String country;
  @JsonIgnore
  protected String securityQuestion;
  @JsonIgnore
  protected String securityAnswer;
  protected Date dateOfBirth;
  @JsonIgnore
  protected Integer riskCategoryId;
  protected Integer customerType;
  @JsonIgnore
  protected Integer cancellationReasonId;
  @JsonIgnore
  protected String referralCode;
  @JsonIgnore
  protected String txnText;
  @JsonIgnore
  protected Long limitSetId;
  @JsonIgnore
  protected Integer notificationModeId;
  @JsonIgnore
  protected Integer classificationId;

  // ----------------
  protected String cif;
  protected String msisdn;
  protected String email;
  protected String firstName;
  protected String lastName;
  protected Integer gender;
  protected String livingAddress;
  protected String province;
  protected String district;
  protected String commune;
  protected String state;
  protected String region;
  protected String jobOccupation;
  protected String jobPosition;
  protected String nickName;
  protected Integer kycStatus;

  @JsonIgnore
  protected Integer verifyType;
  @JsonIgnore
  protected Integer wfState;
  @JsonIgnore
  protected String provinceAddr;
  // ----------------
  @JsonIgnore
  protected Integer level;
  @JsonIgnore
  protected String refAccount;
  @JsonIgnore
  protected Integer walletTypeId;
  @JsonIgnore
  protected Integer userTypeId;
  protected Date created;
  protected Date updated;

  // ----------------
  @JsonIgnore
  Collection<CustomerRole> customerRoles;
  @JsonIgnore
  Collection<CustomerPrivilege> customerPrivileges;

  // ----------------

protected OutletStoreType outletStoreType;

protected  String storeDescription;

  public Long getId() {
    return this.id;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getParentId() {
    return this.parentId;
  }

  public void setParentId(Long value) {
    this.parentId = value;
  }

  public String getParentName() {
    return parentName;
  }

  public String getPrarentEmail() {
    return prarentEmail;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public void setPrarentEmail(String prarentEmail) {
    this.prarentEmail = prarentEmail;
  }

  public String getParentCif() {
    return parentCif;
  }

  public void setParentCif(String parentCif) {
    this.parentCif = parentCif;
  }

  public String getParentMsisdn() {
    return parentMsisdn;
  }

  public void setParentMsisdn(String parentMsisdn) {
    this.parentMsisdn = parentMsisdn;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public OrgUnit getOrgUnit() {
    return orgUnit;
  }

  public void setOrgUnit(OrgUnit orgUnit) {
    this.orgUnit = orgUnit;
  }

  public Integer getBlackListReason() {
    return this.blackListReason;
  }

  public void setBlackListReason(Integer value) {
    this.blackListReason = value;
  }

  public Boolean isActive() {
    return this.active;
  }

  public void setActive(Boolean value) {
    this.active = value;
  }

  public Boolean isTest() {
    return this.test;
  }

  public void setTest(Boolean value) {
    this.test = value;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public void setDisplayName(String value) {
    this.displayName = value;
  }

  public String getTimeZone() {
    return this.timeZone;
  }

  public void setTimeZone(String value) {
    this.timeZone = value;
  }

  public String getLanguage() {
    return this.language;
  }

  public void setLanguage(String value) {
    this.language = value;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String value) {
    this.country = value;
  }

  public String getSecurityQuestion() {
    return this.securityQuestion;
  }

  public void setSecurityQuestion(String value) {
    this.securityQuestion = value;
  }

  public String getSecurityAnswer() {
    return this.securityAnswer;
  }

  public void setSecurityAnswer(String value) {
    this.securityAnswer = value;
  }

  public Date getDateOfBirth() {
    return this.dateOfBirth;
  }

  public void setDateOfBirth(Date value) {
    this.dateOfBirth = value;
  }

  public Integer getRiskCategoryId() {
    return riskCategoryId;
  }

  public Integer getCustomerType() {
    return customerType;
  }

  public void setRiskCategoryId(Integer riskCategoryId) {
    this.riskCategoryId = riskCategoryId;
  }

  public void setCustomerType(Integer customerType) {
    this.customerType = customerType;
  }

  public Integer getCancellationReasonId() {
    return this.cancellationReasonId;
  }

  public void setCancellationReasonId(Integer value) {
    this.cancellationReasonId = value;
  }

  public String getReferralCode() {
    return this.referralCode;
  }

  public void setReferralCode(String value) {
    this.referralCode = value;
  }

  public String getTxnText() {
    return this.txnText;
  }

  public void setTxnText(String value) {
    this.txnText = value;
  }

  public Long getLimitSetId() {
    return this.limitSetId;
  }

  public void setLimitSetId(Long value) {
    this.limitSetId = value;
  }

  public Integer getNotificationModeId() {
    return this.notificationModeId;
  }

  public void setNotificationModeId(Integer value) {
    this.notificationModeId = value;
  }

  // ----------------

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public String getLivingAddress() {
    return livingAddress;
  }

  public void setLivingAddress(String livingAddress) {
    this.livingAddress = livingAddress;
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

  public String getCommune() {
    return commune;
  }

  public void setCommune(String commune) {
    this.commune = commune;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getRegion() {
    return region;
  }

  public Integer getWfState() {
    return wfState;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public void setWfState(Integer wfState) {
    this.wfState = wfState;
  }

  public String getJobOccupation() {
    return jobOccupation;
  }

  public void setJobOccupation(String jobOccupation) {
    this.jobOccupation = jobOccupation;
  }

  public String getJobPosition() {
    return jobPosition;
  }

  public void setJobPosition(String jobPosition) {
    this.jobPosition = jobPosition;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public Integer getKycStatus() {
    return kycStatus;
  }

  public void setKycStatus(Integer kycStatus) {
    this.kycStatus = kycStatus;
  }

  public Integer getVerifyType() {
    return verifyType;
  }

  public void setVerifyType(Integer verifyType) {
    this.verifyType = verifyType;
  }

  // ----------------

  public String getRefAccount() {
    return refAccount;
  }

  public void setRefAccount(String refAccount) {
    this.refAccount = refAccount;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date value) {
    this.created = value;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Boolean getActive() {
    return active;
  }

  public String getProvinceAddr() {
    return provinceAddr;
  }

  public void setProvinceAddr(String provinceAddr) {
    this.provinceAddr = provinceAddr;
  }

  public Integer getClassificationId() {
    return classificationId;
  }

  public void setClassificationId(Integer classificationId) {
    this.classificationId = classificationId;
  }

  // ----------------
  public Collection<CustomerRole> getCustomerRoles() {
    return customerRoles;
  }

  @JsonIgnore
  public boolean isSetCustomerRoles() {
    return customerRoles != null;
  }

  public void setCustomerRoles(Collection<CustomerRole> customerRoles) {
    this.customerRoles = customerRoles;
  }

  public Collection<CustomerPrivilege> getCustomerPrivileges() {
    return customerPrivileges;
  }

  @JsonIgnore
  public boolean isSetCustomerPrivileges() {
    return customerPrivileges != null;
  }

  public void setCustomerPrivileges(Collection<CustomerPrivilege> customerPrivileges) {
    this.customerPrivileges = customerPrivileges;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getWalletTypeId() {
    return walletTypeId;
  }

  public Integer getUserTypeId() {
    return userTypeId;
  }

  public void setWalletTypeId(Integer walletTypeId) {
    this.walletTypeId = walletTypeId;
  }

  public void setUserTypeId(Integer userTypeId) {
    this.userTypeId = userTypeId;
  }

  public OutletStoreType getOutletStoreType() {
    return outletStoreType;
  }

  public void setOutletStoreType(
      OutletStoreType outletStoreType) {
    this.outletStoreType = outletStoreType;
  }

  public String getStoreDescription() {
    return storeDescription;
  }

  public void setStoreDescription(String storeDescription) {
    this.storeDescription = storeDescription;
  }
}
