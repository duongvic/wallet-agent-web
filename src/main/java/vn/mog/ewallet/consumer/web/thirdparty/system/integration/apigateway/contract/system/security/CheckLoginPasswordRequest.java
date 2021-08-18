package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.SecurityAccessActionType;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CheckLoginPasswordRequest extends MobiliserRequestType {

  private String password;

  private SecurityAccessActionType actionType;

  public SecurityAccessActionType getActionType() {
    return actionType;
  }

  public void setActionType(
      SecurityAccessActionType actionType) {
    this.actionType = actionType;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
