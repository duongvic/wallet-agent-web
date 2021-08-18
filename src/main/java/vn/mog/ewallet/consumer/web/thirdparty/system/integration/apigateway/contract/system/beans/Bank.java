package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans;

import java.io.Serializable;

public class Bank implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * "- banks // List  // Danh sách các ngân hàng hỗ trợ việc link ví
   * - bankName // String // Short name of Bank
   * - bankCode // String // Mã định danh duy nhất của bank
   * - displayName // String // Tên hiển thị của bank
   * - dbActive // Character // Trạng thái ngân hàng // N = inactive, Y = active"
   * */

  private Long id;
  private String bankName;
  private String bankCode;
  private String displayName;
  private Character dbActive = Character.valueOf('N');
  private Character dbLinkBankSupport = Character.valueOf('N');
  private String bankBinCode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Character getDbActive() {
    return dbActive;
  }

  public void setDbActive(Character dbActive) {
    this.dbActive = dbActive;
  }

  public Character getDbLinkBankSupport() {
    return dbLinkBankSupport;
  }

  public void setDbLinkBankSupport(Character dbLinkBankSupport) {
    this.dbLinkBankSupport = dbLinkBankSupport;
  }

  public String getBankBinCode() {
    return bankBinCode;
  }

  public void setBankBinCode(String bankBinCode) {
    this.bankBinCode = bankBinCode;
  }
}
