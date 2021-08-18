package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetCustomerByIdentificationRequestType extends MobiliserRequestType implements
    Serializable {

  private static final long serialVersionUID = 1L;
  protected String orgUnit;
  protected String identification;
  protected int identificationType;

  public String getOrgUnit() {
    return orgUnit;
  }

  public void setOrgUnit(String orgUnit) {
    this.orgUnit = orgUnit;
  }

  public String getIdentification() {
    return this.identification;
  }

  public void setIdentification(String value) {
    this.identification = value;
  }

  public int getIdentificationType() {
    return this.identificationType;
  }

  public void setIdentificationType(int value) {
    this.identificationType = value;
  }
}
