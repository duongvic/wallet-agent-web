package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

public class Address implements Serializable {
  private static final long serialVersionUID = 1L;
  protected Long id;

  protected Long customerId;

  protected int addressType;

  protected int addressStatus;

  protected Integer gender;
  @JsonIgnore
  protected String title;
  @JsonIgnore
  protected String firstName;
  @JsonIgnore
  protected String middleName;
  @JsonIgnore
  protected String lastName;
  protected String company;
  @JsonIgnore
  protected String position;
  protected String street1;
  @JsonIgnore
  protected String street2;

  protected String houseNumber;
  protected String city;
  @JsonIgnore
  protected String state;
  @JsonIgnore
  protected String region;
  @JsonIgnore
  protected String zip;
  protected String country;
  @JsonIgnore
  protected String privatePhone;

  protected String businessPhone;

  protected String businessCode;
  protected String fax;
  protected String email;
  @JsonIgnore
  protected String url;
  @JsonIgnore
  protected String company2;
  protected String companyShortName;
  protected String province;
  protected String district;
  protected String companyProduct;
  protected String commune;
  
  @JsonIgnore
  protected String remark;
  
  protected Date created;

  @JsonIgnore
  protected Date addressSince;

  protected OutletStoreType outletStoreType; // nhóm cửa hàng
  protected String storeDescription;

  protected String alias;

  public Long getId() {
    return id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public int getAddressType() {
    return addressType;
  }

  public int getAddressStatus() {
    return addressStatus;
  }

  public Integer getGender() {
    return gender;
  }

  public String getTitle() {
    return title;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCompany() {
    return company;
  }

  public String getPosition() {
    return position;
  }

  public String getStreet1() {
    return street1;
  }

  public String getStreet2() {
    return street2;
  }

  public String getHouseNumber() {
    return houseNumber;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getRegion() {
    return region;
  }

  public String getZip() {
    return zip;
  }

  public String getCountry() {
    return country;
  }

  public String getPrivatePhone() {
    return privatePhone;
  }

  public String getBusinessPhone() {
    return businessPhone;
  }

  public String getBusinessCode() {
    return businessCode;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public String getUrl() {
    return url;
  }

  public String getCompany2() {
    return company2;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public String getProvince() {
    return province;
  }

  public String getDistrict() {
    return district;
  }

  public String getCompanyProduct() {
    return companyProduct;
  }

  public String getCommune() {
    return commune;
  }

  public String getRemark() {
    return remark;
  }

  public Date getCreated() {
    return created;
  }

  public Date getAddressSince() {
    return addressSince;
  }

  public String getAlias() {
    return alias;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public void setAddressType(int addressType) {
    this.addressType = addressType;
  }

  public void setAddressStatus(int addressStatus) {
    this.addressStatus = addressStatus;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setPrivatePhone(String privatePhone) {
    this.privatePhone = privatePhone;
  }

  public void setBusinessPhone(String businessPhone) {
    this.businessPhone = businessPhone;
  }

  public void setBusinessCode(String businessCode) {
    this.businessCode = businessCode;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setCompany2(String company2) {
    this.company2 = company2;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public void setCompanyProduct(String companyProduct) {
    this.companyProduct = companyProduct;
  }

  public void setCommune(String commune) {
    this.commune = commune;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public void setAddressSince(Date addressSince) {
    this.addressSince = addressSince;
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

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String textOutLetStoreType() {
    if(this.outletStoreType != null){
      return String.valueOf(OutletStoreType.getOutLetStoreType(this.outletStoreType.getCode()));
    }else {
      return StringUtils.EMPTY;
    }

  }

}
