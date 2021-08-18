package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.beans;

import java.util.Date;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;

public class ProviderService {

  private Long id;

  private Provider provider;

  private String serviceCode;

  private ServiceType serviceType;

  private String serviceName;

  private String serviceDesc;

  private String servicePrices;

  private Boolean active;

  private Date creationDate;

  private Date updateDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceDesc() {
    return serviceDesc;
  }

  public void setServiceDesc(String serviceDesc) {
    this.serviceDesc = serviceDesc;
  }

  public String getServicePrices() {
    return servicePrices;
  }

  public void setServicePrices(String servicePrices) {
    this.servicePrices = servicePrices;
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

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
