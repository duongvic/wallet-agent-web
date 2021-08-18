package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.impl;

import java.util.Map;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClientTest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IPaymentSecurity;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentRegisterRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentRegisterResponse;
import vn.mog.ewallet.consumer.web.utils.WebUtil;

@Service
public class PaymentSecurityService implements IPaymentSecurity, InitializingBean {

  @Autowired
  GatewayServiceAPIClient gatewayAPIClient;

  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig;
  
  @Autowired
  GatewayServiceAPIClientTest gatewayServiceAPIClientTest;

  public static final String URI_GET_PAYMENT_SECURITY = "/risk/api/v1/customers/securityPayment/get";
  public static final String URI_REGISTER_PAYMENT_SECURITY = "/risk/api/v1/customers/securityPayment/register";

  @Override
  public void afterPropertiesSet() throws Exception {
    if (this.gatewayAPIClient == null) {
      throw new IllegalStateException("authServerAPIClient is required");
    }
  }

  @Override
  public SecurityPaymentGetResponse getSecurityPayment(SecurityPaymentGetRequest request)
      throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_PAYMENT_GET, mapHeader,
        request, WebUtil.defaultNullUriVariables(), SecurityPaymentGetResponse.class);
  }

  @Override
  public SecurityPaymentRegisterResponse registerSecurityPayment(
      SecurityPaymentRegisterRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_PAYMENT_REGISTER,
        mapHeader, request, WebUtil.defaultNullUriVariables(), SecurityPaymentRegisterResponse.class);
  }
}
