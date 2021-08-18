package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class TopupTransactionRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private String terminalId;

  private String requestId;

  private String serviceCode;

  private Long price;

  private Integer quantity;

  private String phoneNumber;

  private Long orderId;

  private String actionType;

  private String otp;

  private String telcoTypeId;

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getActionType() {
    return actionType;
  }

  public void setActionType(String actionType) {
    this.actionType = actionType;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

  public String getTelcoTypeId() {
    return telcoTypeId;
  }

  public void setTelcoTypeId(String telcoTypeId) {
    this.telcoTypeId = telcoTypeId;
  }

  @Override
  public String toString() {
    return "TopupTransactionRequest{" +
        "terminalId='" + terminalId + '\'' +
        ", requestId='" + requestId + '\'' +
        ", serviceCode='" + serviceCode + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", orderId=" + orderId +
        ", actionType='" + actionType + '\'' +
        ", otp='" + otp + '\'' +
        ", telcoTypeId='" + telcoTypeId + '\'' +
        '}';
  }
}
