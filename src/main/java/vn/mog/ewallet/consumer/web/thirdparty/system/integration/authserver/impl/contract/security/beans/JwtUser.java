package vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import vn.mog.framework.security.api.ICustomerAware;

public class JwtUser implements UserDetails, ICustomerAware, Serializable {

  private static final long serialVersionUID = 1L;
  private String accessToken;
  private Long customerId;
  private String customerCif;
  private int customerTypeId;
  private String username;
  private String password;
  private Boolean active;
  private String email;
  private String s3Url;
  private String phoneNumber;
  private String fullName;
  private Set<String> roles;
  private Set<String> privileges;
  private String dataImage;
  private String livingAddress;
  private String provinceName;
  private String districtName;
  private String communeName;

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

  public String getLivingAddress() {
    return livingAddress;
  }

  public void setLivingAddress(String livingAddress) {
    this.livingAddress = livingAddress;
  }

  public String getDataImage() {
    return dataImage;
  }

  public void setDataImage(String dataImage) {
    this.dataImage = dataImage;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  @Override
  public String getCustomerCif() {
    return customerCif;
  }

  public void setCustomerCif(String customerCif) {
    this.customerCif = customerCif;
  }

  @Override
  public int getCustomerTypeId() {
    return customerTypeId;
  }

  public void setCustomerTypeId(int customerTypeId) {
    this.customerTypeId = customerTypeId;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }


  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }


  @JsonIgnore
  public boolean isSetRoles() {
    return roles != null;
  }

  public void addRole(String role) {
    if (roles == null) {
      roles = new HashSet<>();
    }
    roles.add(role);
  }

  public Set<String> getPrivileges() {
    return privileges;
  }

  public void setPrivileges(Set<String> privileges) {
    this.privileges = privileges;
  }

  @JsonIgnore
  public boolean isSetPrivileges() {
    return privileges != null;
  }

  public void addPrivilege(String privilege) {
    if (privileges == null) {
      privileges = new HashSet<>();
    }
    privileges.add(privilege);
  }

  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> set = new HashSet<>();
    if (isSetRoles()) {
      for (String role : roles) {
        set.add(new SimpleGrantedAuthority(role));
      }
    }
    if (isSetPrivileges()) {
      for (String privilege : privileges) {
        set.add(new SimpleGrantedAuthority(privilege));
      }
    }
    return set;
  }


  public String getS3Url() {
    return s3Url;
  }

  public void setS3Url(String s3Url) {
    this.s3Url = s3Url;
  }
  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return active;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return active;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return active;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return active;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
