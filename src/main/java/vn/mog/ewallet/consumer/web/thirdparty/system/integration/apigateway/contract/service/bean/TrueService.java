package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean;

import java.io.Serializable;
import java.util.Date;

public class TrueService implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String serviceCode;
    private String serviceName;
    private String serviceDesc;
    private String servicePrices;
    private String telcoType;
    private String telcoServiceType;
    private String serviceType;
    private Character status;
    private Date creationDate;
    private String serviceShortName;
    private String serviceCodeName;
    
    
    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }
    public String getServiceCode() {
      return serviceCode;
    }
    public void setServiceCode(String serviceCode) {
      this.serviceCode = serviceCode;
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
    public String getTelcoType() {
      return telcoType;
    }
    public void setTelcoType(String telcoType) {
      this.telcoType = telcoType;
    }
    public String getTelcoServiceType() {
      return telcoServiceType;
    }
    public void setTelcoServiceType(String telcoServiceType) {
      this.telcoServiceType = telcoServiceType;
    }
    public String getServiceType() {
      return serviceType;
    }
    public void setServiceType(String serviceType) {
      this.serviceType = serviceType;
    }
    public Character getStatus() {
      return status;
    }
    public void setStatus(Character status) {
      this.status = status;
    }
    public Date getCreationDate() {
      return creationDate;
    }
    public void setCreationDate(Date creationDate) {
      this.creationDate = creationDate;
    }

  public String getServiceShortName() {
    return serviceShortName;
  }

  public void setServiceShortName(String serviceShortName) {
    this.serviceShortName = serviceShortName;
  }

  public String getServiceCodeName() {
    return serviceCodeName;
  }

  public void setServiceCodeName(String serviceCodeName) {
    this.serviceCodeName = serviceCodeName;
  }
}
