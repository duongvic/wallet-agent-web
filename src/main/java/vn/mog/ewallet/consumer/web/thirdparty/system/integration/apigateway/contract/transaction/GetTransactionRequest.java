package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction;


import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetTransactionRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long txnId;

  public Long getTxnId() {
    return txnId;
  }

  public void setTxnId(Long txnId) {
    this.txnId = txnId;
  }

  public GetTransactionRequest(Long txnId) {
    this.txnId = txnId;
  }

  public GetTransactionRequest(String traceNo) {
    this.traceNo = traceNo;
  }

  public GetTransactionRequest() {

  }
}
