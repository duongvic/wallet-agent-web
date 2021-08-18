package vn.mog.ewallet.consumer.web.contract;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.SpecialCustomerItem;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtUser;

@SuppressWarnings("serial")
public class UserLogin implements Serializable {

  private String accessToken;
  private String username;
  private Long customerId;
  private String customerCif;
  private Integer customerTypeId;
  private Long balance;
  private String fullName;
  private String email;
  private String livingAddress;

  private String provinceName;
  private String districtName;
  private String communeName;

  private Set<String> roles;
  private Set<String> privileges;

  private String s3Url;
  private String dataImage;

  private SpecialCustomerItem specialCustomerInfo;
  private SpecialCustomerItem specialCustomerInfoOffline;
  private SpecialCustomerItem specialCustomerInfoN02;

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  private String phoneNumber;

  public UserLogin() {
    this.accessToken = "";
    this.username = "";
    this.customerId = 0L;
    this.customerCif = "";
    this.customerTypeId = 0;
    this.roles = new HashSet<>();
    this.privileges = new HashSet<>();
    this.phoneNumber = "";
    this.fullName = "";
    this.s3Url = "";
    this.email = "";
    this.livingAddress = "";
    this.communeName="";
    this.districtName="";
    this.provinceName="";
    this.dataImage = "";
  }

  public UserLogin(long balance) {
    this.balance = balance;
  }

  public UserLogin(JwtUser jwtUser, String accessToken) {
    this.accessToken = accessToken;
    this.username = jwtUser.getUsername();
    this.customerId = jwtUser.getCustomerId();
    this.customerCif = jwtUser.getCustomerCif();
    this.customerTypeId = jwtUser.getCustomerTypeId();
    this.roles = jwtUser.getRoles();
    this.privileges = jwtUser.getPrivileges();
    this.phoneNumber = jwtUser.getPhoneNumber();
    this.fullName = jwtUser.getFullName();
    this.email = jwtUser.getEmail();
    this.livingAddress = jwtUser.getLivingAddress();
    this.communeName=jwtUser.getCommuneName();
    this.districtName=jwtUser.getDistrictName();
    this.provinceName=jwtUser.getProvinceName();
    this.s3Url = jwtUser.getS3Url();
    this.dataImage = jwtUser.getDataImage();
  }


  public Long getBalance() {
    if (balance == null) {
      balance = 0L;
    }
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public int getCustomerTypeId() {
    return customerTypeId;
  }

  public void setCustomerTypeId(Integer customerTypeId) {
    this.customerTypeId = customerTypeId;
  }

  public void setCustomerTypeId(int customerTypeId) {
    this.customerTypeId = customerTypeId;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  public Set<String> getPrivileges() {
    return privileges;
  }

  public void setPrivileges(Set<String> privileges) {
    this.privileges = privileges;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getS3Url() {
    return s3Url;
  }

  public void setS3Url(String s3Url) {
    this.s3Url = s3Url;
  }

  public String getDataImage() {
    return dataImage;
  }

  public void setDataImage(String dataImage) {
    this.dataImage = dataImage;
  }

  public String getLivingAddress() {
    return livingAddress;
  }

  public void setLivingAddress(String livingAddress) {
    this.livingAddress = livingAddress;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }

  public String getCommuneName() {
    return communeName;
  }

  public void setCommuneName(String communeName) {
    this.communeName = communeName;
  }

  public SpecialCustomerItem getSpecialCustomerInfo() {
    return specialCustomerInfo;
  }

  public void setSpecialCustomerInfo(
      SpecialCustomerItem specialCustomerInfo) {
    this.specialCustomerInfo = specialCustomerInfo;
  }

  public SpecialCustomerItem getSpecialCustomerInfoOffline() {
    return specialCustomerInfoOffline;
  }

  public void setSpecialCustomerInfoOffline(
      SpecialCustomerItem specialCustomerInfoOffline) {
    this.specialCustomerInfoOffline = specialCustomerInfoOffline;
  }

  public SpecialCustomerItem getSpecialCustomerInfoN02() {
    return specialCustomerInfoN02;
  }

  public void setSpecialCustomerInfoN02(
      SpecialCustomerItem specialCustomerInfoN02) {
    this.specialCustomerInfoN02 = specialCustomerInfoN02;
  }

  @Override
  public String toString() {
    return "UserLogin{" +
        "accessToken='" + accessToken + '\'' +
        ", username='" + username + '\'' +
        ", customerId=" + customerId +
        ", customerCif='" + customerCif + '\'' +
        ", customerTypeId=" + customerTypeId +
        ", balance=" + balance +
        ", fullName='" + fullName + '\'' +
        ", email='" + email + '\'' +
        ", livingAddress='" + livingAddress + '\'' +
        ", provinceName='" + provinceName + '\'' +
        ", districtName='" + districtName + '\'' +
        ", communeName='" + communeName + '\'' +
        ", roles=" + roles +
        ", privileges=" + privileges +
        ", s3Url='" + s3Url + '\'' +
        ", dataImage='" + dataImage + '\'' +
        ", specialCustomerInfo=" + specialCustomerInfo +
        ", specialCustomerInfoOffline=" + specialCustomerInfoOffline +
        ", specialCustomerInfoN02=" + specialCustomerInfoN02 +
        ", phoneNumber='" + phoneNumber + '\'' +
        '}';
  }
}
