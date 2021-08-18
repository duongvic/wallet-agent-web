package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IBillPaymentEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.CreateInvoiceOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.CreateInvoiceOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.GetInvoiceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.GetInvoiceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.PayInvoiceOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.PayInvoiceOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.RecheckInvoiceOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.RecheckInvoiceOrderResponse;
import vn.mog.ewallet.consumer.web.utils.WebUtil;

@Service
public class BillPaymentEndpoint implements IBillPaymentEndpoint {

  private String cif_hashcode = "";
  private String id_customer_hashcode = "";

  @Autowired
  private GatewayUrlConfiguration gatewayUrlConfig;

  @Autowired
  private GatewayServiceAPIClient gatewayAPIClient;

  @Override
  public GetInvoiceResponse getInvoice(GetInvoiceRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_BILL_PAYMENT_INVOICE_GET, request, WebUtil.defaultUriVariables(), GetInvoiceResponse.class);
  }

  @Override
  public CreateInvoiceOrderResponse createInvoiceOrder(CreateInvoiceOrderRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_BILL_PAYMENT_INVOICE_CREATE, request, WebUtil.defaultUriVariables(), CreateInvoiceOrderResponse.class);
  }

  @Override
  public PayInvoiceOrderResponse payInvoiceOrder(PayInvoiceOrderRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_BILL_PAYMENT_INVOICE_PAY_INVOICE, request, WebUtil.defaultUriVariables(), PayInvoiceOrderResponse.class);
  }

  @Override
  public RecheckInvoiceOrderResponse recheckInvoiceOrder(RecheckInvoiceOrderRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_BILL_PAYMENT_INVOICE_RECHECK_INVOICE, request, WebUtil.defaultUriVariables(), RecheckInvoiceOrderResponse.class);
  }
}
