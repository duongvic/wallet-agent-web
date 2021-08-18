package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;


import java.util.Map;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignInResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionResponse;

@Service
public class GatewayServiceAPIClientTest {

  public <T> T getForEntity(String requestURI, Map<String, String> mapHeader, Object request,
      Map<String, String> uriVariables, Class<T> clazz) throws Exception {
    return exchange(requestURI, HttpMethod.GET, mapHeader, request, uriVariables, clazz);
  }

  public <T> T postForEntity(String requestURI, Map<String, String> mapHeader, Object request,
      Map<String, String> uriVariables, Class<T> clazz) throws Exception {
    return exchange(requestURI, HttpMethod.POST, mapHeader, request, uriVariables, clazz);
  }

  public <T> T putForEntity(String requestURI, Map<String, String> mapHeader, Object request,
      Map<String, String> uriVariables, Class<T> clazz) throws Exception {
    return exchange(requestURI, HttpMethod.PUT, mapHeader, request, uriVariables, clazz);
  }

  public <T> T deleteForEntity(String requestURI, Map<String, String> mapHeader, Object request,
      Map<String, String> uriVariables, Class<T> clazz) throws Exception {
    return exchange(requestURI, HttpMethod.DELETE, mapHeader, request, uriVariables, clazz);
  }


  public <T> T exchange(String requestURI, HttpMethod method, Map<String, String> mapHeader,
      Object request, Map<String, String> uriVariables, Class<T> clazz) throws Exception {


    if (clazz.isInstance(new SignInResponse())) {
      SignInResponse response = new SignInResponse();
      response.setAccessToken("abc");
      return (T) response;
    } else if (clazz.isInstance(new GetTransactionResponse())) {
      GetTransactionResponse response = new GetTransactionResponse();
      response.setConversationId("abc");
      return (T) response;
    }
    return null;
  }
}
