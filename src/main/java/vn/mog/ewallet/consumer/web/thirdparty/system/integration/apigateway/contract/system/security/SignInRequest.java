package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class SignInRequest extends MobiliserRequestType {

  private String identification;// phone - email/username
  private String credential;//(password) required
  private Integer identificationTypeId; //(Integer):required; 0 - phone, 5 - email/username
  private Integer credentialTypeId; //:required; 1 - password"

  public String getIdentification() {
    return identification;
  }

  public void setIdentification(String identification) {
    this.identification = identification;
  }

  public String getCredential() {
    return credential;
  }

  public void setCredential(String credential) {
    this.credential = credential;
  }

  public Integer getIdentificationTypeId() {
    return identificationTypeId;
  }

  public void setIdentificationTypeId(Integer identificationTypeId) {
    this.identificationTypeId = identificationTypeId;
  }

  public Integer getCredentialTypeId() {
    return credentialTypeId;
  }

  public void setCredentialTypeId(Integer credentialTypeId) {
    this.credentialTypeId = credentialTypeId;
  }
}
