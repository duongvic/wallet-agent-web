package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StaffResetCustomerPasswordRequest extends StaffResetCustomerPasswordRequestType {

  @JsonProperty("new_password")
  private String newPassword;


  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
