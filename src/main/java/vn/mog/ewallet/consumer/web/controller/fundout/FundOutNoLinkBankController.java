package vn.mog.ewallet.consumer.web.controller.fundout;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.FUND_OUT;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.customer.CustomerController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.CreateBankItemRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.CreateBankItemResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.FundOutNoLinkToBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.FundOutNoLinkToBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.GetBankItemByCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.GetBankItemByCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.bean.CustomerBankItem;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.ewallet.consumer.web.util.tools.StringUtil;

@Controller
@RequestMapping(value = "/fundout/no-link-bank")

public class FundOutNoLinkBankController extends AbstractController {

  public static final String FUNDOUT_NOLINKBANK_CONTROLLER = "/fundout/no-link-bank";

  private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

  private static final String ACTION_VERIFY = "verify";
  private static final String ACTION_CONFIRM = "confirm";
  private static final String TRANSFER_TYPE_CARD = "CARD";
  private static final String TRANSFER_TYPE_ACCOUNT = "ACCOUNT";


  private static final String NAME_BANK_ATTRIBUTE = "NAME_BANK";
  private static final String TRANSFER_TYPE_ATTRIBUTE = "TRANSFER_TYPE";
  private static final String SO_TAI_KHOAN_ATTRIBUTE = "SO_TAI_KHOAN";
  private static final String BANK_CODE_ATTRIBUTE = "BANK_CODE";
  private static final String BANK_NAME_ATTRIBUTE = "BANK_NAME";
  private static final String BANK_DISPLAY_NAME_ATTRIBUTE = "BANK_DISPLAY_NAME";
  private static final String AMOUNT_ATTRIBUTE = "AMOUNT";
  private static final String REAL_AMOUNT_ATTRIBUTE = "REAL_AMOUNT";
  private static final String FEE_AMOUNT_ATTRIBUTE = "FEE_AMOUNT";
  private static final String WALLET_ID_ATTRIBUTE = "WALLET_ID";
  private static final String DESCRIPTION_ATTRIBUTE = "DESCRIPTION";
  private static final String VERIFY_REQUEST_ID_ATTRIBUTE = "VERIFY_REQUEST_ID";
  private static final String IS_OTP_REQUIRED_ATTRIBUTE = "IS_OTP_REQUIRED";
  private static final String ORDER_ID_ATTRIBUTE = "ORDER_ID";
  private static final String BANK_HOLDER_NAME_ATTRIBUTE = "BANK_HOLDER_NAME";

  private static final String BANK_ITEMS_ATTRIBUTE = "BANK_ITEMS";
  private static final String LIST_TRANSACTIONS_ATTRIBUTE = "LIST_TRANSACTIONS";
  private static final String CONFIRM_STATUS_ATTRIBUTE = "CONFIRM_STATUS";
  private static final String CARD_ISSUE_DATE1_ATTRIBUTE = "CARD_ISSUE_DATE1";
  private static final String CARD_ISSUE_DATE2_ATTRIBUTE = "CARD_ISSUE_DATE2";
  private static final String CODE_ERROR_ATTRIBUTE = "CODE_ERROR";

//  @GetMapping(value = "")
//  public String unlinkIndex(HttpServletRequest request, HttpServletResponse response, ModelMap map)
//      throws Exception {
//    try {
//      // get list bank
//      FindBankRequest findBankRequest = new FindBankRequest();
//      findBankRequest.setBankCode(null);
//      findBankRequest.setIsLinkBankSupport(null);
//      FindBankResponse baseResponseType = systemEndpoint.getListBank(findBankRequest);
//      // --- check base response type;
//      if (baseResponseType == null) {
//        throw new Exception("No response!");
//      }
//      if (baseResponseType.getStatus().getCode() != 0) {
//        throw new Exception(baseResponseType.getStatus().getValue());
//      }
//
//      // set get dữ liệu
//      List<Bank> listBank = new ArrayList<Bank>();
//      if (baseResponseType.getBanks().size() >= 1) {
//        listBank = baseResponseType.getBanks();
//      }
//      map.put("listBank", listBank);
//      return "/fundout/fundout_noLinkBank";
//    } catch (Exception e) {
//      e.printStackTrace();
//      return "/fundout/fundout_noLinkBank";
//    }
//  }

  @GetMapping(value = "/info")
  public String fundoutNoLinkBankInfoGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    map.put("listTransactions", SessionUtil.getAttribute(LIST_TRANSACTIONS_ATTRIBUTE));
    map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
    map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
    map.put("_soTaiKhoan", SessionUtil.getAttribute(SO_TAI_KHOAN_ATTRIBUTE));
    map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
    map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
    map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
    map.put("bankItemByCustomer", SessionUtil.getAttribute(BANK_ITEMS_ATTRIBUTE));
    map.put("transferType", SessionUtil.getAttribute(TRANSFER_TYPE_ATTRIBUTE));
    map.put("cardIssueDate1", SessionUtil.getAttribute(CARD_ISSUE_DATE1_ATTRIBUTE));
    map.put("cardIssueDate2", SessionUtil.getAttribute(CARD_ISSUE_DATE2_ATTRIBUTE));

    return "/fundout/fundout_noLinkBank_info";
  }

  @PostMapping(value = "/info")
  public String fundoutNoLinkBankInfo(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    GetBankItemByCustomerResponse getBankItemByCustomerResponseType;
    try {
      getTransaction(request, response, map);

      String cardIssueDate1 = request.getParameter("cardIssueDate1");
      if (StringUtils.isBlank(cardIssueDate1)) {
        cardIssueDate1 = null;
      }
      String cardIssueDate2 = request.getParameter("cardIssueDate2");
      if (StringUtils.isBlank(cardIssueDate2)) {
        cardIssueDate2 = null;
      }
      String _nameBank = request.getParameter("_nameBank");
      String _soTaiKhoan = request.getParameter("_soTaiKhoan");
      String amount = request.getParameter("amount");
//   if(StringUtils.isEmpty(_soTaiKhoan)) {
//     _soTaiKhoan = request.getParameter("_soTaiKhoan");
//   }
      String transferType = request.getParameter("transferType");
      if (transferType == null) {
        transferType = "CARD";
      }

      // Store to Session
      SessionUtil.setAttribute(NAME_BANK_ATTRIBUTE, _nameBank);
      SessionUtil.setAttribute(SO_TAI_KHOAN_ATTRIBUTE, _soTaiKhoan);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(TRANSFER_TYPE_ATTRIBUTE, transferType);
      SessionUtil.setAttribute(CARD_ISSUE_DATE1_ATTRIBUTE, cardIssueDate1);
      SessionUtil.setAttribute(CARD_ISSUE_DATE2_ATTRIBUTE, cardIssueDate2);

      // lấy thông tin theo nameBank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(_nameBank);
      findBankRequest.setIsLinkBankSupport(null);

      FindBankResponse findBankResponseType = systemEndpoint.getListBank(findBankRequest);
      if (findBankResponseType.getStatus().getCode() != 0 || findBankResponseType.getBanks() == null
          || findBankResponseType.getBanks().size() != 1) {
        throw new Exception("No data list link bank");
      }

      Bank bank = findBankResponseType.getBanks().get(0);
      String bankCode = bank.getBankCode();
      String bankName = bank.getBankName();
      String bankDisplayName = bank.getDisplayName();
      // Store to Session
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);

      map.put("_nameBank", _nameBank);
      map.put("amount", amount);
      map.put("_soTaiKhoan", _soTaiKhoan);
      map.put("bankCode", bankCode);
      map.put("bankName", bankName);
      map.put("bankDisplayName", bankDisplayName);

      GetBankItemByCustomerRequest getBankItemByCustomerRequest = new GetBankItemByCustomerRequest();
      getBankItemByCustomerRequest.setItemType(transferType);
      getBankItemByCustomerRequest.setBankCode(bankCode);
      getBankItemByCustomerRequest.setIsActive(true);

      getBankItemByCustomerResponseType = systemEndpoint
          .getBankItemByCustomer(getBankItemByCustomerRequest);
      if (getBankItemByCustomerResponseType != null
          && getBankItemByCustomerResponseType.getStatus().getCode() == 0) {
        List<CustomerBankItem> bankItems = getBankItemByCustomerResponseType.getBankItems();
        SessionUtil.setAttribute(BANK_ITEMS_ATTRIBUTE, bankItems);
        map.put("bankItemByCustomer", bankItems);
      }
      map.put("transferType", transferType);
      map.put("cardIssueDate1", cardIssueDate1);
      map.put("cardIssueDate2", cardIssueDate2);
      return "/fundout/fundout_noLinkBank_info";
    } catch (Exception e) {
      getTransaction(request, response, map);
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("_soTaiKhoan", SessionUtil.getAttribute(SO_TAI_KHOAN_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("bankItemByCustomer", SessionUtil.getAttribute(BANK_ITEMS_ATTRIBUTE));
      map.put("transferType", SessionUtil.getAttribute(TRANSFER_TYPE_ATTRIBUTE));
      map.put("cardIssueDate1", SessionUtil.getAttribute(CARD_ISSUE_DATE1_ATTRIBUTE));
      map.put("cardIssueDate2", SessionUtil.getAttribute(CARD_ISSUE_DATE2_ATTRIBUTE));
      String codeErr = e.getMessage();
      map.put("codeErr", codeErr);
      return "/fundout/fundout_noLinkBank_info";
    }
  }

  @GetMapping(value = "/verify")
  public String fundoutNoLinkBankInfoVerify(HttpServletRequest request,
      HttpServletResponse response, ModelMap map) throws Exception {

    getTransaction(request, response, map);
//    bankCode = _nameBank;
    map.put("transferType", SessionUtil.getAttribute(TRANSFER_TYPE_ATTRIBUTE));
    map.put("bankHolderName", SessionUtil.getAttribute(BANK_HOLDER_NAME_ATTRIBUTE));
    map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
    map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
    map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
    map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
    map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
    map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
    map.put("_soTaiKhoan", SessionUtil.getAttribute(SO_TAI_KHOAN_ATTRIBUTE));
    map.put("orderId", SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));
    map.put("isOtpRequired", SessionUtil.getAttribute(IS_OTP_REQUIRED_ATTRIBUTE));
    map.put("verifyRequestId", SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE));
    map.put("bankItemByCustomer", SessionUtil.getAttribute(BANK_ITEMS_ATTRIBUTE));
    map.put("description", SessionUtil.getAttribute(DESCRIPTION_ATTRIBUTE));
    map.put("cardIssueDate1", SessionUtil.getAttribute(CARD_ISSUE_DATE1_ATTRIBUTE));
    map.put("cardIssueDate2", SessionUtil.getAttribute(CARD_ISSUE_DATE2_ATTRIBUTE));
    try {
      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      String walletId = userLogin.getPhoneNumber();
      SessionUtil.setAttribute(WALLET_ID_ATTRIBUTE, walletId);

      return "/fundout/fundout_noLinkBank_info";
    } catch (Exception e) {
      map.put("codeErr", e.getMessage());

      return "/fundout/fundout_noLinkBank_info";
    }
  }

  @PostMapping(value = "/verify")
  public String fundoutNoLinkBankVerify(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      String cardIssueDate1 = request.getParameter("cardIssueDate1");
      String cardIssueDate2 = request.getParameter("cardIssueDate2");

      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      String walletId = userLogin.getPhoneNumber();
      // _nameBank = request.getParameter("_nameBank");
      String bankHolderName = request.getParameter("bankHolderName");
      String _soTaiKhoan = request.getParameter("_soTaiKhoan");
      String transferType = request.getParameter("transferType");

      String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
      Long feeAmount = NumberUtil.convertToLong(request.getParameter("feeAmount"));
      Long realAmount = NumberUtil.convertToLong(request.getParameter("realAmount"));
      String description = request.getParameter("description");

      Object nameBankAttr = SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE);
      String bankCode = nameBankAttr != null ? nameBankAttr.toString() : null;

      // Store to Session
      SessionUtil.setAttribute(WALLET_ID_ATTRIBUTE, walletId);
      SessionUtil.setAttribute(BANK_HOLDER_NAME_ATTRIBUTE, bankHolderName);
      SessionUtil.setAttribute(SO_TAI_KHOAN_ATTRIBUTE, _soTaiKhoan);
      SessionUtil.setAttribute(TRANSFER_TYPE_ATTRIBUTE, transferType);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(FEE_AMOUNT_ATTRIBUTE, feeAmount);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
      SessionUtil.setAttribute(DESCRIPTION_ATTRIBUTE, description);
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
      SessionUtil.setAttribute(CARD_ISSUE_DATE1_ATTRIBUTE, cardIssueDate1);
      SessionUtil.setAttribute(CARD_ISSUE_DATE2_ATTRIBUTE, cardIssueDate2);

      // Luu thong tin the tai khoan CREATE BANK ACCOUNT
//      String checkSaveBank = request.getParameter("chkSaveInfoBank");
//      if (checkSaveBank != null) {
      try {
        CustomerBankItem bankItem = new CustomerBankItem();
        bankItem.setId(null);
        bankItem.setItemNumber(_soTaiKhoan);
        bankItem.setItemHolderName(bankHolderName);
        bankItem.setBankCode(bankCode);
//        bankItem.setBankName(bankName);
        bankItem.setBankName(bankCode);
        bankItem.setBranchCode(null);
        bankItem.setBankName(null);
        bankItem.setBankCountry("VN");
        CreateBankItemRequest crBankItemRq = new CreateBankItemRequest();
        crBankItemRq.setItemType(transferType);
        crBankItemRq.setBankItem(bankItem);
        CreateBankItemResponse baseCrBankItemRes = walletEndpoint
            .customerBankItemCreate(crBankItemRq);
        if (baseCrBankItemRes == null) {
          throw new Exception("No response!");
        }

        if (baseCrBankItemRes.getStatus().getCode() != 0) {
          throw new Exception(baseCrBankItemRes.getStatus().getValue());
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
//      }
      // END CREATE BANK ACCOUNT
      String uuid = UUID.randomUUID().toString();

      FundOutNoLinkToBankRequest foNoLinkBankRequest = new FundOutNoLinkToBankRequest();
      foNoLinkBankRequest.setSignature("config");
      foNoLinkBankRequest.setAccesskey("config");
      foNoLinkBankRequest.setWalletId(walletId);
//      foNoLinkBankRequest.setBankCode("config");

      foNoLinkBankRequest.setBankCode(bankCode);
      foNoLinkBankRequest.setAmount(amount);
      foNoLinkBankRequest.setRequestId(uuid);
      String verifyRequestId = uuid;
      foNoLinkBankRequest.setType(ACTION_VERIFY);
      foNoLinkBankRequest.setTransferType(transferType);
      if (transferType.equalsIgnoreCase(TRANSFER_TYPE_CARD)) {
        foNoLinkBankRequest.setCardNumber(_soTaiKhoan);
      }
      if (transferType.equalsIgnoreCase(TRANSFER_TYPE_ACCOUNT)) {
        foNoLinkBankRequest.setAccountNumber(_soTaiKhoan);
      }
      foNoLinkBankRequest.setDescription(description);

      FundOutNoLinkToBankResponse foNoLinkBankResponseType = walletEndpoint
          .fundOutNoLinkBankVerify(foNoLinkBankRequest);

      // --- check base response type;
      if (foNoLinkBankResponseType == null) {
        throw new Exception("No response!");
      }
      if (foNoLinkBankResponseType.getStatus().getCode() != 0) {
        throw new Exception(foNoLinkBankResponseType.getStatus().getValue());
      }

      boolean isOtpRequired = foNoLinkBankResponseType.getIsOtpRequired();
      String orderId = foNoLinkBankResponseType.getOrderId().toString();

      // Store to Session
      SessionUtil.setAttribute(IS_OTP_REQUIRED_ATTRIBUTE, isOtpRequired);
      SessionUtil.setAttribute(ORDER_ID_ATTRIBUTE, orderId);
      SessionUtil.setAttribute(VERIFY_REQUEST_ID_ATTRIBUTE, verifyRequestId);

      // verifyRequestId = foNoLinkdBankResponse.getRequestId();

      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankHolderName", bankHolderName);
      map.put("bankCode", bankCode);
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("amount", amount);
      map.put("feeAmount", feeAmount);
      map.put("realAmount", realAmount);
      map.put("transferType", transferType);
      map.put("_soTaiKhoan", _soTaiKhoan);
      map.put("orderId", orderId);
      map.put("isOtpRequired", isOtpRequired);
      map.put("verifyRequestId", verifyRequestId);
      map.put("cardIssueDate1", cardIssueDate1);
      map.put("cardIssueDate2", cardIssueDate2);
      getTransaction(request, response, map);

      return "/fundout/fundout_noLinkBank_otp";
    } catch (Exception e) {
      getTransaction(request, response, map);
      map.put("bankHolderName", SessionUtil.getAttribute(BANK_HOLDER_NAME_ATTRIBUTE));
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("transferType", SessionUtil.getAttribute(TRANSFER_TYPE_ATTRIBUTE));
      map.put("_soTaiKhoan", SessionUtil.getAttribute(SO_TAI_KHOAN_ATTRIBUTE));
      map.put("orderId", SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));
      map.put("isOtpRequired", SessionUtil.getAttribute(IS_OTP_REQUIRED_ATTRIBUTE));
      map.put("verifyRequestId", SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE));
      map.put("cardIssueDate1", SessionUtil.getAttribute(CARD_ISSUE_DATE1_ATTRIBUTE));
      map.put("cardIssueDate2", SessionUtil.getAttribute(CARD_ISSUE_DATE2_ATTRIBUTE));
      map.put("codeErr", e.getMessage());

      return "/fundout/fundout_noLinkBank_info";
    }
  }

  @GetMapping(value = "/confirm")
  public String fundinNoLinkBankconfirmGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    Object confirmStatusAttr = SessionUtil.getAttribute(CONFIRM_STATUS_ATTRIBUTE);
    boolean confirmStatus = confirmStatusAttr != null && (boolean) confirmStatusAttr;
    return confirmStatus ? "/fundout/fundout_success" : "/fundout/fundout_noLinkBank_error";
  }

  @PostMapping(value = "/confirm")
  public String fundinNoLinkBankconfirm(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String cardIssueDate1 = request.getParameter("cardIssueDate1");
      String cardIssueDate2 = request.getParameter("cardIssueDate2");

      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      String codeOTP = request.getParameter("codeOTP");
      String walletId = userLogin.getPhoneNumber();
      String _soTaiKhoan = request.getParameter("_soTaiKhoan");
//      String transferType = request.getParameter("transferType");
      String amount = request.getParameter("amount").replaceAll("[^0-9]", "");
      String description = request.getParameter("description");

      // Store to Session
      SessionUtil.setAttribute(WALLET_ID_ATTRIBUTE, walletId);
      SessionUtil.setAttribute(SO_TAI_KHOAN_ATTRIBUTE, _soTaiKhoan);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(DESCRIPTION_ATTRIBUTE, description);
      SessionUtil.setAttribute(CARD_ISSUE_DATE1_ATTRIBUTE, cardIssueDate1);
      SessionUtil.setAttribute(CARD_ISSUE_DATE2_ATTRIBUTE, cardIssueDate2);

      Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : null;
      Object verifyRequestIdAttr = SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE);
      String verifyRequestId = verifyRequestIdAttr != null ? verifyRequestIdAttr.toString() : null;
      Object transferTypeAttr = SessionUtil.getAttribute(TRANSFER_TYPE_ATTRIBUTE);
      String transferType = transferTypeAttr != null ? transferTypeAttr.toString() : null;
      Object orderIdAttr = SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE);
      String orderId = orderIdAttr != null ? orderIdAttr.toString() : null;

      FundOutNoLinkToBankRequest foNoLinkBankRequest = new FundOutNoLinkToBankRequest();
      foNoLinkBankRequest.setSignature("config");
      foNoLinkBankRequest.setAccesskey("config");
      foNoLinkBankRequest.setWalletId(walletId);
      foNoLinkBankRequest.setBankCode(bankCode);
      foNoLinkBankRequest.setAmount(amount);
      foNoLinkBankRequest.setRequestId(UUID.randomUUID().toString());
      foNoLinkBankRequest.setVerifyRequestId(verifyRequestId);
      foNoLinkBankRequest.setType(ACTION_CONFIRM);
      foNoLinkBankRequest.setTransferType(transferType);
      if (TRANSFER_TYPE_CARD.equalsIgnoreCase(transferType)) {
        foNoLinkBankRequest.setCardNumber(_soTaiKhoan);
      }
      if (TRANSFER_TYPE_ACCOUNT.equalsIgnoreCase(transferType)) {
        foNoLinkBankRequest.setAccountNumber(_soTaiKhoan);
      }
      foNoLinkBankRequest.setDescription(description);
      if (StringUtils.isNotBlank(codeOTP)) {
        foNoLinkBankRequest.setOtp(codeOTP);
      } else {
//        foNoLinkBankRequest.setOtp("112233");
      }

      foNoLinkBankRequest.setOrderId(orderId);

      FundOutNoLinkToBankResponse foNoLinkBankResponseType = walletEndpoint
          .fundOutNoLinkBankConfirm(foNoLinkBankRequest);
      // --- check base response type;
      if (foNoLinkBankResponseType == null || foNoLinkBankResponseType.getStatus() == null) {
        throw new Exception("No response!");
      }
      if (foNoLinkBankResponseType.getStatus().getCode() != 0) {
        throw new Exception(foNoLinkBankResponseType.getStatus().getValue());
      }
      map.put("transferType", transferType);
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankCode", bankCode);
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("amount", amount);
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("_soTaiKhoan", _soTaiKhoan);
      map.put("orderId", orderId);
      map.put("cardIssueDate1", cardIssueDate1);
      map.put("cardIssueDate2", cardIssueDate2);
      SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, true);

      return "/fundout/fundout_success";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeError = e.getMessage();

      map.put("transferType", SessionUtil.getAttribute(TRANSFER_TYPE_ATTRIBUTE));
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("_soTaiKhoan", SessionUtil.getAttribute(SO_TAI_KHOAN_ATTRIBUTE));
      map.put("orderId", SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));
      map.put("cardIssueDate1", SessionUtil.getAttribute(CARD_ISSUE_DATE1_ATTRIBUTE));
      map.put("cardIssueDate2", SessionUtil.getAttribute(CARD_ISSUE_DATE2_ATTRIBUTE));
      map.put("codeErr", codeError);

      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeError);
      SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, false);
      return "/fundout/fundout_noLinkBank_error";
    }
  }

  @PostMapping(value = "/getBankItemByCustomer")
  public void getBankItemByCustomer(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws Exception {
    GetBankItemByCustomerResponse getBankItemByCustomerResponseType;
    try {
      Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : null;

      String transferType = request.getParameter("transferType");
      GetBankItemByCustomerRequest getBankItemByCustomerRequest = new GetBankItemByCustomerRequest();
      getBankItemByCustomerRequest.setItemType(transferType);
      getBankItemByCustomerRequest.setBankCode(bankCode);
      getBankItemByCustomerRequest.setIsActive(true);

      getBankItemByCustomerResponseType = systemEndpoint
          .getBankItemByCustomer(getBankItemByCustomerRequest);
      if (getBankItemByCustomerResponseType != null) {
        if (getBankItemByCustomerResponseType.getStatus().getCode() != 0) {
          map.put("bankItemByCustomer", SessionUtil.getAttribute(BANK_ITEMS_ATTRIBUTE));
        } else {
          List<CustomerBankItem> bankItems = getBankItemByCustomerResponseType.getBankItems();
          SessionUtil.setAttribute(BANK_ITEMS_ATTRIBUTE, bankItems);
          map.put("bankItemByCustomer", bankItems);
        }
      }

    } catch (Exception e) {
      map.put("bankItemByCustomer", SessionUtil.getAttribute(BANK_ITEMS_ATTRIBUTE));
      LOGGER.error(e.getMessage());
    }
  }

  public void getTransaction(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {
      // Xử lý dữ liệu đầu vào
      Long total = 0L;
      Integer offset = 0;
      Integer limit = 5;
      Date fromDate = null;
      Date endDate = null;
      // ---
      if (request.getParameter("d-49520-p") != null) {
        Integer p = Integer.parseInt(request.getParameter("d-49520-p"));
        offset = limit * (p - 1);
      }

      String searchRange = request.getParameter("range");
      if (StringUtils.isNotBlank(searchRange)) {
        Date[] dates = parseDateRange(searchRange);
        fromDate = dates[0];
        endDate = dates[1];
      }

      // Search box
      String quickSearch =
          StringUtils.trimToEmpty(request.getParameter("quickSearch")).replaceAll("\\s+", " ");

      // Tạo request & set params vào request
      FindTransactionsRequest transactionsRequest =
          new FindTransactionsRequest();

      transactionsRequest.setOffset(offset);
      transactionsRequest.setLimit(limit);

      transactionsRequest.setTextSearch(quickSearch);
      transactionsRequest.setFromDate(fromDate);
      transactionsRequest.setEndDate(endDate);

      // --

      String[] serviceTypeId = request.getParameterValues("serviceTypeId");
      if (serviceTypeId != null && serviceTypeId.length > 0
          && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
      } else {
        transactionsRequest.setServiceTypeIds(Arrays.asList(FUND_OUT.name()));
      }
      String[] serviceCode = request.getParameterValues("serviceCode");
      if (serviceCode != null && serviceCode.length > 0
          && !serviceCode[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
      }

      Collection<Transaction> listTransactions = new ArrayList<>();

      FindTransactionsResponse baseTransResponseType = transactionEndpoint
          .transactionFind(transactionsRequest);
      if (baseTransResponseType == null || baseTransResponseType.getStatus() == null
          || baseTransResponseType.getStatus().getCode() != 0) {
        LOGGER.error(" API transactionFind fail");
      } else {
        if (baseTransResponseType != null) {
          listTransactions = baseTransResponseType.getTransactions();
        }
      }

      SessionUtil.setAttribute(LIST_TRANSACTIONS_ATTRIBUTE, listTransactions);

      map.put("pagesize", limit);
      map.put("offset", offset);
      map.put("listTransactions", listTransactions);
      map.put("total", total.intValue());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
