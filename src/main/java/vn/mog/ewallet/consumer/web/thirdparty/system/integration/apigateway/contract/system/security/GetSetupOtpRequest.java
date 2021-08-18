package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.SecurityAccessActionType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.TypicalSecuritySetup;
import vn.mog.framework.contract.base.MobiliserRequestType;

@SuppressWarnings("serial")
public class GetSetupOtpRequest extends MobiliserRequestType {

  private String password;
  private SecurityAccessActionType actionType;
  private String key;
  private String reference;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public SecurityAccessActionType getActionType() {
    return actionType;
  }

  public void setActionType(SecurityAccessActionType actionType) {
    this.actionType = actionType;
  }

  public void setActionTypeByTypical(int typicalSecurity) {
    if (TypicalSecuritySetup.CHANGE_PASSWORD.code == typicalSecurity) {
      this.actionType = SecurityAccessActionType.CHANGE_PASSWORD;

    } else if (TypicalSecuritySetup.PAYMENT_PIN.code == typicalSecurity) {
      this.actionType = SecurityAccessActionType.SETUP_PAYMENT_PIN;
    }
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

}
