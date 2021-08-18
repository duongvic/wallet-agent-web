package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.beans;

import java.util.Date;
import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ProviderCode;

public class Provider {

  private Long id;

  private ProviderHealthy providerHealthy;

  private String name;

  private Double discount;

  private Double relationship;

  private ProviderCode providerCode;

  private Boolean isSupportCheckBalance;

  private Long balance;

  private Boolean active;

  private Boolean hidden;

  private List<ProviderService> services;

  private Date creationDate;

  private Date updateDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProviderHealthy getProviderHealthy() {
    return providerHealthy;
  }

  public void setProviderHealthy(ProviderHealthy providerHealthy) {
    this.providerHealthy = providerHealthy;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public Double getRelationship() {
    return relationship;
  }

  public void setRelationship(Double relationship) {
    this.relationship = relationship;
  }

  public ProviderCode getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(ProviderCode providerCode) {
    this.providerCode = providerCode;
  }

  public Boolean getIsSupportCheckBalance() {
    return isSupportCheckBalance;
  }

  public void setIsSupportCheckBalance(Boolean isSupportCheckBalance) {
    this.isSupportCheckBalance = isSupportCheckBalance;
  }

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }

  public List<ProviderService> getServices() {
    return services;
  }

  public void setServices(List<ProviderService> services) {
    this.services = services;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

}
