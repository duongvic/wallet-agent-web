package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.topup;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FundInTopupRequest extends MobiliserRequestType implements Serializable {

  @JsonProperty("terminal_id")
  private String terminalId;//"": ""WEB"", // Thiết bị sử dụng

  @JsonProperty("request_id")
  private String requestId;//"": ""{{$guid}}"", // Mã yêu cầu - unique

  @JsonProperty("service_code")
  private String serviceCode;//"": ""060000"", // Mã dịch vụ - Tra cứu phụ lục

  @JsonProperty("phone_number")
  private String phoneNumber;//"": ""0915759948"", // Số điện thoại nạp tiền
  private String price;//";//": 20000 // Số tiền

  @JsonProperty("order_id")
  private String orderId;//"": null, // mã order ở bước verify

  @JsonProperty("action_type")
  private String actionType;//"": ""verify"", // loại hành động
  private String otp;//"":null // otp gửi lên nếu có

}
