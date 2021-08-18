package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class RefreshTokenRequest extends MobiliserRequestType implements Serializable {

  @JsonProperty("refresh_token")
  private String refreshToken;

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
