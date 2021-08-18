package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer;

import java.util.Date;
import java.util.List;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateCustomerPersonalInfoRequest extends MobiliserRequestType {

  private String displayName;
  private Date dateOfBirth;
  private String msisdn;
  private String email;
  private String firstName;
  private String lastName;
  private Integer gender;
  private String livingAddress;
  private String province;
  private String district;
  private String commune;
  private String state;
  private String jobOccupation;
  private String jobPosition;
  private List<Address> addresses;

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
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

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(
      List<Address> addresses) {
    this.addresses = addresses;
  }
}
