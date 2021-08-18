package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SelfChangeCustomerPasswordRequest extends SelfChangeCustomerPasswordRequestType {

  @JsonProperty("old_password")
  private String oldPassword;

  @JsonProperty("new_password")
  private String newPassword;


  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
