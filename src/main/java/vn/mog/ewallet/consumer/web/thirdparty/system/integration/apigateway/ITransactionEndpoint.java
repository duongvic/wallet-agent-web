package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;

import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.ExportTransactionLogRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.ExportTransactionLogResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetLastTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetLastTransactionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.framework.contract.base.BaseResponseType;


public interface ITransactionEndpoint {

  /******API Transaction******/
  GetTransactionResponse getTransaction(GetTransactionRequest request) throws FrontEndException;

  FindTransactionsResponse findTransactions(FindTransactionsRequest request) throws FrontEndException;

  FindTransactionsResponse transactionFind(FindTransactionsRequest request) throws  Exception;

  GetLastTransactionResponse getLastTransactionUser(GetLastTransactionRequest request) throws FrontEndException;

  ExportTransactionLogResponse exportTransaction(ExportTransactionLogRequest request) throws FrontEndException;
}
