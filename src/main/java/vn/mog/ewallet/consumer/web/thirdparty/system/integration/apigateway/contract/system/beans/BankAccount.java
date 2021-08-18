package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class BankAccount implements Serializable {


  private String cif;//"": ""0000000327"", // Thông tin định danh khách hàng  (M)

  @JsonProperty("account_number")
  private String accountNumber;//"": ""003368001"", // Số tài khoản (M)

  @JsonProperty("account_holder_name")
  private String accountHolderName;//"": ""VO DANG KHOA"", // Tên tài khoản (M)

  @JsonProperty("bank_code")
  private String bankCode;//"": ""TPBVVNVX"", // Swift code Ngân hàng (M)

  @JsonProperty("bank_name")
  private String bankName;//"": ""TPBANK"", // Tên ngân hàng

  @JsonProperty("branch_code")
  private String branchCode;//"": ""01020100"", // Brank code (Optional)

  @JsonProperty("branch_name")
  private String branchName;//"": ""Chi nhánh TP Bank Ba Đình"", // Tên chi nhánh

  @JsonProperty("register_date")
  private String registerDate;//"": 1497718800000, // Ngày đăng ký

  @JsonProperty("register_source")
  private String registerSource;//"": ""TPBVVNVX"", // Nguồn đăng ký
  private String active;//"": true, // Active
  private String verify;//"": true, // Đã được verify

  @JsonProperty("end_date")
  private String endDate;//"": 1497718800000,

  @JsonProperty("bank_country")
  private String bankCountry;//"": ""VN"" // Bank Country Code // fix (M)

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountHolderName() {
    return accountHolderName;
  }

  public void setAccountHolderName(String accountHolderName) {
    this.accountHolderName = accountHolderName;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public String getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(String registerDate) {
    this.registerDate = registerDate;
  }

  public String getRegisterSource() {
    return registerSource;
  }

  public void setRegisterSource(String registerSource) {
    this.registerSource = registerSource;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String getVerify() {
    return verify;
  }

  public void setVerify(String verify) {
    this.verify = verify;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getBankCountry() {
    return bankCountry;
  }

  public void setBankCountry(String bankCountry) {
    this.bankCountry = bankCountry;
  }
}
