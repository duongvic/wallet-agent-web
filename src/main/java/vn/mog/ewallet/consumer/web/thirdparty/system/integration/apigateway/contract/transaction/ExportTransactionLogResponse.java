package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction;


import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.LogFile;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class ExportTransactionLogResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private LogFile logFile;

  public LogFile getLogFile() {
    return logFile;
  }

  public void setLogFile(LogFile logFile) {
    this.logFile = logFile;
  }
}
