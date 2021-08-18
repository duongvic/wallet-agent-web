package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class ProfileCheckResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private Boolean profileExist;
  private Boolean isActive;
  private Boolean isTempPwdUsing;
  private Integer customerTypeId;

  public Boolean getProfileExist() {
    return profileExist;
  }

  public void setProfileExist(Boolean profileExist) {
    this.profileExist = profileExist;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public Boolean getIsTempPwdUsing() {
    return isTempPwdUsing;
  }

  public void setIsTempPwdUsing(Boolean isTempPwdUsing) {
    this.isTempPwdUsing = isTempPwdUsing;
  }

  public Integer getCustomerTypeId() {
    return customerTypeId;
  }

  public void setCustomerTypeId(Integer customerTypeId) {
    this.customerTypeId = customerTypeId;
  }
  
}
