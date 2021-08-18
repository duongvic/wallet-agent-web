package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class TrueServiceCommissionGetRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private String serviceTypeId;
  private String orderChanelId;
  private String serviceCode;
  private String phoneNumber;
  private String bankCode;
  private String sourceOfFundId;

  public String getServiceTypeId() {
    return serviceTypeId;
  }

  public void setServiceTypeId(String serviceTypeId) {
    this.serviceTypeId = serviceTypeId;
  }

  public String getOrderChanelId() {
    return orderChanelId;
  }

  public void setOrderChanelId(String orderChanelId) {
    this.orderChanelId = orderChanelId;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getSourceOfFundId() {
    return sourceOfFundId;
  }

  public void setSourceOfFundId(String sourceOfFundId) {
    this.sourceOfFundId = sourceOfFundId;
  }

}
