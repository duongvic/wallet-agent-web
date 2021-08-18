package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class RefreshTokenResponse extends MobiliserResponseType implements Serializable {

  @JsonProperty("access_token")
  private String accessToken;//access token

  @JsonProperty("expires_in")
  private Long expiresIn;//access token expiration

  @JsonProperty("refresh_token")
  private String refreshToken;//refresh token

  @JsonProperty("correlate_id")
  private String correlateId;//customer id

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public Long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Long expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getCorrelateId() {
    return correlateId;
  }

  public void setCorrelateId(String correlateId) {
    this.correlateId = correlateId;
  }

}
