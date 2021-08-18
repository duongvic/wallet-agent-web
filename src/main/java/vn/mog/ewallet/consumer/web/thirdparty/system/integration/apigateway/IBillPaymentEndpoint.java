package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.CreateInvoiceOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.CreateInvoiceOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.GetInvoiceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.GetInvoiceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.PayInvoiceOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.PayInvoiceOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.RecheckInvoiceOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.RecheckInvoiceOrderResponse;

public interface IBillPaymentEndpoint {

  GetInvoiceResponse getInvoice(GetInvoiceRequest request) throws Exception;

  CreateInvoiceOrderResponse createInvoiceOrder(CreateInvoiceOrderRequest request) throws Exception;

  PayInvoiceOrderResponse payInvoiceOrder(PayInvoiceOrderRequest request) throws Exception;

  RecheckInvoiceOrderResponse recheckInvoiceOrder(RecheckInvoiceOrderRequest request) throws Exception;
}
