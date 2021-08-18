package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ITransactionEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.ExportTransactionLogRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.ExportTransactionLogResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetLastTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetLastTransactionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.framework.contract.base.BaseResponseType;

@Service
public class TransactionEndpoint implements ITransactionEndpoint {

 public static String URI_LAST_TRANS_USER = "/api/v1/transaction/lastTransUser";

 public static String URI_GET_TXN_INQ = "/api/v1/transaction/getTxnInquiry";
 public static String URI_CREATE_TXN_INQ = "/api/v1/transaction/createTxnInquiry";
 public static String URI_UPDATE_TXN_INQ = "/api/v1/transaction/updateTxnInquiry";
 public static String URI_FIND_INQ = "/api/v1/transaction/findInquiries";
 public static String URI_GET_INQ_ATTCK = "/api/v1/transaction/getInquiryAttachment";

 private static final String URL_FIND_TRANSACTION_RULE = "/api/v1/system/findTransactionRules";
 private static final String URL_GET_TRANSACTION_RULE = "/api/v1/system/getTransactionRule";

 @Autowired
 GatewayServiceAPIClient gatewayAPIClient;
 @Autowired
 GatewayUrlConfiguration gatewayUrlConfig;

 public GetTransactionResponse getTransaction(GetTransactionRequest request)
   throws FrontEndException {
   return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_TRANSACTION_GETTRANSACTION, request, GetTransactionResponse.class);
 }

 public FindTransactionsResponse findTransactions(FindTransactionsRequest request)
   throws FrontEndException {
  return gatewayAPIClient
    .callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_TRANSACTION_FINDTRANSACTIONS, request, FindTransactionsResponse.class);
 }

 public FindTransactionsResponse transactionFind(
   FindTransactionsRequest request) throws Exception {
  Map<String, String> uriVariables = null;
  return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_FIND, request, uriVariables, FindTransactionsResponse.class);
 }


 @Override
 public GetLastTransactionResponse getLastTransactionUser(GetLastTransactionRequest request)
   throws FrontEndException {
  return gatewayAPIClient
    .callRequest(URI_LAST_TRANS_USER, request, GetLastTransactionResponse.class);
 }

 @Override
 public ExportTransactionLogResponse exportTransaction(ExportTransactionLogRequest request)
   throws FrontEndException {
  return gatewayAPIClient
    .callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_TRANSACTION_EXPORT_TRANSACTIONLOG, request, ExportTransactionLogResponse.class);
 }
}
