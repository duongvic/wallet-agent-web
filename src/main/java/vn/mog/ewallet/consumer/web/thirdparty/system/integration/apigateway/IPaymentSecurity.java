package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentRegisterRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentRegisterResponse;

public interface IPaymentSecurity {

  SecurityPaymentGetResponse getSecurityPayment(SecurityPaymentGetRequest request) throws Exception;

  SecurityPaymentRegisterResponse registerSecurityPayment(SecurityPaymentRegisterRequest request)
      throws Exception;

}

