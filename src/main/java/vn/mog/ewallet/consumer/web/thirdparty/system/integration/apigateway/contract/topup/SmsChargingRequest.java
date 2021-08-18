package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.topup;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class SmsChargingRequest extends MobiliserRequestType implements Serializable {

  private String requestId;//"": ""{{$guid}}"" ,
  private String serviceCode;//"": ""120000"",
  private String amount;//"": 1000

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

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }
}
