package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.topup;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;


public class BuyCardTopupRequest extends MobiliserRequestType implements Serializable {

  private String terminalId;//"": ""WEB"", // Kênh thiết bị: WEB/API
  private String requestId;//"": ""{{$guid}}"", // Mã yêu cầu - unique
  private String serviceCode;//"": ""040101"", // Mã dịch vụ - tra cứu Sheet Phụ lục
  private String price;//"": 10000, // Mệnh giá thẻ
  private String quantity;//"": 1 // Số lượng thẻ,
  private String orderId;//"": null, // mã order ở bước verify
  private String actionType;//"": ""verify"", // loại hành động
  private String otp;//"":null // otp gửi lên nếu có

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

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
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


}
