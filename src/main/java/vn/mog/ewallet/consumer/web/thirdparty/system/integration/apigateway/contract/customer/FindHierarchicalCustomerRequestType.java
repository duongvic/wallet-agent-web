package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindHierarchicalCustomerRequestType extends MobiliserRequestType implements
    Serializable {

  private static final long serialVersionUID = 1L;
  protected long agentId;
  protected Boolean active;
  protected String displayName;
  protected Integer customerType;
  protected int maxRecords;
  protected Integer minHierarchyLevel;
  protected Integer maxHierarchyLevel;

  public long getAgentId() {
    return this.agentId;
  }

  public void setAgentId(long value) {
    this.agentId = value;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public void setDisplayName(String value) {
    this.displayName = value;
  }

  public Integer getCustomerType() {
    return this.customerType;
  }

  public void setCustomerType(Integer value) {
    this.customerType = value;
  }

  public int getMaxRecords() {
    return this.maxRecords;
  }

  public void setMaxRecords(int value) {
    this.maxRecords = value;
  }

  public Integer getMinHierarchyLevel() {
    return this.minHierarchyLevel;
  }

  public void setMinHierarchyLevel(Integer value) {
    this.minHierarchyLevel = value;
  }

  public Integer getMaxHierarchyLevel() {
    return this.maxHierarchyLevel;
  }

  public void setMaxHierarchyLevel(Integer value) {
    this.maxHierarchyLevel = value;
  }
}
