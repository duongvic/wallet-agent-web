package vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import vn.mog.ewallet.consumer.web.common.SharedConstants;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.beans.AuthToken;
import vn.mog.ewallet.consumer.web.util.tools.HttpUtil;

@Service
public class AuthServerAPIClient extends AbstractAPIClient {

  private static Logger logger = LogManager.getLogger(AuthServerAPIClient.class);

  // --------------------------------
  @Override
  protected String getClientID() {
    return "authserver";
  }

  @Override
  protected String getBaseRequestURL() {
    return SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT;
  }

  @Override
  protected String getBearerHeaderPrefix() {
    return BEARER_HEADER_PREFIX;
  }

  @Override
  protected boolean isSessionStorage() {
    return true;
  }

  // --------------------------------
  public String authToken(String username, String password) throws FrontEndException {
    final String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT + "/api/v1/auth/token";
    HttpServletRequest httpServletRequest = HttpUtil.getCurrentHttpServletRequest();

    // Param
    final String grantType = "password";
    final String scope = "read write";
    final String identifierType = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_IDENTIFIER_TYPE;
    final String credentialType = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_CREDENTIAL_TYPE;
    final String clientId = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_CLIENT_ID; // "clientapp";
    final String clientSecret = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_CLIENT_SECRET; // "123456";
    final String basicAuthen = clientId + ":" + clientSecret; // "clientapp:123456";
    // request.
    // -----------
    final PostMethod method = new PostMethod(url);
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new NameValuePair("username", username));
      urlParameters.add(new NameValuePair("password", password));
      urlParameters.add(new NameValuePair("grant_type", grantType));
      urlParameters.add(new NameValuePair("scope", scope));
      urlParameters.add(new NameValuePair("client_secret", clientSecret));
      urlParameters.add(new NameValuePair("client_id", clientId));
      urlParameters.add(new NameValuePair("identifierType", identifierType));
      urlParameters.add(new NameValuePair("credentialType", credentialType));

      method.addParameters(urlParameters.toArray(new NameValuePair[urlParameters.size()]));

      String encoding = DatatypeConverter.printBase64Binary(basicAuthen.getBytes("UTF-8"));
      method.setRequestHeader("Authorization", "Basic " + encoding);
      method.setRequestHeader(USER_AGENT, httpServletRequest.getHeader(USER_AGENT));

      logger.debug(method.toString());
      int responseCode = HttpUtil.client.executeMethod(method);
      logger.debug("ResponseCode: " + responseCode);

      String json = method.getResponseBodyAsString();
      if (json != null) {
        AuthToken authToken = new ObjectMapper().readValue(json, AuthToken.class);

        // Login fail: invalid_grant
        if (StringUtils.isBlank(authToken.getAccessToken())) {
          throw new Exception("Not Authorized!");
        }

        setAccessToken(authToken.getAccessToken());
        return authToken.getAccessToken();
      }
    } catch (HttpStatusCodeException e) {
      int statusCode = e.getStatusCode().value();
      logger.error("RESPONE HTTP CODE: " + statusCode, e);
      if (statusCode == 401) {
        throw new FrontEndException(401, "Not Authorized!");
      }
      throw e;
    } catch (Exception e) {
      logger.error("Exception", e);
      throw new FrontEndException(401, "Not Authorized!");
    } finally {
      method.releaseConnection();
    }
    return null;
  }
}
