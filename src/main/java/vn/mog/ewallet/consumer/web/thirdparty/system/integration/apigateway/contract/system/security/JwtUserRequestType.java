package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class JwtUserRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long customerId;
  protected String identifier;
  protected Integer identificationType;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public Integer getIdentificationType() {
    return identificationType;
  }

  public void setIdentificationType(Integer identificationType) {
    this.identificationType = identificationType;
  }

}
