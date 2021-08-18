package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateCustomerAdditionalInforRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private String livingAddress;
  private String commune;
  private String province;
  private String district;
  private Integer jobOccupation;
  private Integer jobPosition;
  private String nationality;
  private String email;
  private String nickName;


  public String getLivingAddress() {
    return livingAddress;
  }

  public String getCommune() {
    return commune;
  }


  public void setCommune(String commune) {
    this.commune = commune;
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

  public Integer getJobOccupation() {
    return jobOccupation;
  }

  public void setJobOccupation(Integer jobOccupation) {
    this.jobOccupation = jobOccupation;
  }

  public Integer getJobPosition() {
    return jobPosition;
  }

  public void setJobPosition(Integer jobPosition) {
    this.jobPosition = jobPosition;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getNickName() {
    return nickName;
  }

}
