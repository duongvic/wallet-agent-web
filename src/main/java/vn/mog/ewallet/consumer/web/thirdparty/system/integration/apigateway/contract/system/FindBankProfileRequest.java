package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindBankProfileRequest extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  private String bankCode;

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }
}
