package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterWalletSecurityRequest extends RegisterWalletSecurityRequestType {

  /**
   * "- Security Type
   * // Sms(1, ""Sms""), //  Email(2, ""Email""), //  Voice(3, ""Voicde""), //  App(4, ""App""),//  Matrix(5, ""Matrix"");"
   * */

  @JsonProperty("security_type")
  private Integer securityType;
  @JsonProperty("min_amount")
  private Long minAmount;

  public Integer getSecurityType() {
    return securityType;
  }

  public void setSecurityType(Integer securityType) {
    this.securityType = securityType;
  }

  public Long getMinAmount() {
    return minAmount;
  }

  public void setMinAmount(Long minAmount) {
    this.minAmount = minAmount;
  }
}
