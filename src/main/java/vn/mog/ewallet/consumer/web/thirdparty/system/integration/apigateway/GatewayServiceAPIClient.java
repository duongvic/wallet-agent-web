package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;

import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.common.SharedConstants;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient;

@Service
public class GatewayServiceAPIClient extends AbstractAPIClient {

  @Override
  protected String getClientID() {
    return "apigateway";
  }

  @Override
  protected String getBaseRequestURL() {
    return SharedConstants.PLATFORM_BACKEND_APIGATEWAY_SERVICE_API_ENDPOINT;
  }

  @Override
  protected String getBearerHeaderPrefix() {
    return BEARER_HEADER_PREFIX;
  }

  @Override
  protected boolean isSessionStorage() {
    return true;
  }

  public String authToken(String username, String password) {
    return null;
  }
}
