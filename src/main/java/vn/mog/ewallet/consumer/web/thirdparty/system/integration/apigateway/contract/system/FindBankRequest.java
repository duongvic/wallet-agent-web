package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindBankRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private String bankCode;//(String) - (Optional) - mã bank Code muốn lấy
  private Boolean isLinkBankSupport;// (Boolean) - (Optional) - có hỗ trợ link bank không ?"


  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public Boolean getIsLinkBankSupport() {
    return isLinkBankSupport;
  }

  public void setIsLinkBankSupport(Boolean linkBankSupport) {
    isLinkBankSupport = linkBankSupport;
  }
}
