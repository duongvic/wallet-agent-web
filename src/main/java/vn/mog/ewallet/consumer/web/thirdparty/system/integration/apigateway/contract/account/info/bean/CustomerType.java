package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean;

import java.io.Serializable;

public class CustomerType implements Serializable {
  private static final long serialVersionUID = 1L;

  public final static transient Integer CUSTOMER = 1;
  public final static transient Integer AGENT = 2;
  public final static transient Integer MERCHANT = 3;
  public final static transient Integer SALE = 4;
  public final static transient Integer TRUEMONEY = 5;

  /*
   * public final static transient Integer BANK = 6; public final static transient Integer FA = 7;
   */
  public final static transient Integer STAFF = 8;
  public final static transient Integer ADMIN = 9;
  public final static transient Integer SYSTEM = 10;
  public final static transient Integer PROVIDER = 11;

  protected Integer id;
  protected String name;
  protected String role;
  protected RiskCategory riskCategory;
  protected boolean internal;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer value) {
    this.id = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String value) {
    this.role = value;
  }

  public RiskCategory getRiskCategory() {
    return riskCategory;
  }

  public void setRiskCategory(RiskCategory riskCategory) {
    this.riskCategory = riskCategory;
  }

  public boolean isInternal() {
    return this.internal;
  }

  public void setInternal(boolean value) {
    this.internal = value;
  }
}
