package vn.mog.ewallet.consumer.web.controller.fundin;


import static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_LINK_BANK;
import static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_LOCAL_BANK;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.FUND_IN;
import java.text.SimpleDateFormat;
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
import vn.mog.ewallet.consumer.web.common.SharedConstants;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.customer.CustomerController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.FundInBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.FundInBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.UnlinkBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.UnlinkBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.CustomerBankDirect;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.TrueService;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindCustomerBankDirectRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindCustomerBankDirectResponse;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;


@Controller

@RequestMapping(value = "/fundin")
public class FundInLinkBankController extends AbstractController {

  public static final String FUNDIN_LINKBANK_PREFIX = "/fundin";
  public static final String FUNDIN_LINKBANK_CONTROLLER = FUNDIN_LINKBANK_PREFIX + "/link-bank";
  public static final String FUNDIN_LINKBANK_INFO = FUNDIN_LINKBANK_CONTROLLER + "/infoLinkBank";
  public static final String FUNDIN_LINKBANK = FUNDIN_LINKBANK_CONTROLLER;
  public static final String FUNDIN_LINKBANK_VERIFY =
      FUNDIN_LINKBANK_PREFIX + "/verify-link-bank";

  public static final String UNLINK_BANK_INFO = FUNDIN_LINKBANK_CONTROLLER + "/infoUnLink";


  private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

  private static final String ACTION_VERIFY = "verify";
  private static final String ACTION_CONFIRM = "confirm";
  private static final String CHANNEL_WEB = "WEB";

  private static final String FEE_AMOUNT_ATTRIBUTE = "FEE_AMOUNT";
  private static final String VERIFY_REQUEST_ID_ATTRIBUTE = "VERIFY_REQUEST_ID";
  private static final String SUBSCRIPTION_ID_ATTRIBUTE = "SUBSCRIPTION_ID";
  private static final String PHONE_NUMBER_ATTRIBUTE = "PHONE_NUMBER";
  private static final String FACE_VALUE_ATTRIBUTE = "FACE_VALUE";
  private static final String BANK_CODE_ATTRIBUTE = "BANK_CODE";
  private static final String WALLET_ID_ATTRIBUTE = "WALLET_ID";
  private static final String IS_OTP_REQUIRED_ATTRIBUTE = "IS_OTP_REQUIRED";
  private static final String CARD_ISSUE_DATE_ATTRIBUTE = "CARD_ISSUE_DATE";
  private static final String BANK_ACCOUNT_NUMBER_ATTRIBUTE = "BANK_ACCOUNT_NUMBER";
  private static final String SSN_ATTRIBUTE = "SSN";
  private static final String BANK_ACCOUNT_NAME_ATTRIBUTE = "BANK_ACCOUNT_NAME";
  private static final String REAL_AMOUNT_ATTRIBUTE = "REAL_AMOUNT";
  private static final String BANK_NAME_ATTRIBUTE = "BANK_NAME";
  private static final String BANK_DISPLAY_NAME_ATTRIBUTE = "BANK_DISPLAY_NAME";
  private static final String CODE_OTP_ATTRIBUTE = "CODE_OTP";
  private static final String NAME_BANK_ATTRIBUTE = "NAME_BANK";
  private static final String CODE_ERROR_ATTRIBUTE = "CODE_ERROR";
  private static final String PRICE_ATTRIBUTE = "PRICE";
  private static final String ACCOUNT_ATTRIBUTE = "ACCOUNT";
  private static final String LIST_BANK_ATTRIBUTE = "LIST_BANK";
  private static final String LIST_BANK_DIRECT_ATTRIBUTE = "LIST_BANK_DIRECT";
  private static final String LST_CUS_BANK_DIRECT_DIRECT_ATTRIBUTE = "LST_CUS_BANK_DIRECT";
  private static final String LIST_PHONE_FUNDIN_ATTRIBUTE = "LIST_PHONE_FUNDIN";
  private static final String LIST_TRANSACTIONS_ATTRIBUTE = "LIST_TRANSACTIONS";
  private static final String CONFIRM_STATUS_ATTRIBUTE = "CONFIRM_STATUS";

  @GetMapping(value = "")
  public String fundInBank(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    map.put("listBankDirect", SessionUtil.getAttribute(LIST_BANK_DIRECT_ATTRIBUTE));
    map.put("listBank", SessionUtil.getAttribute(LIST_BANK_ATTRIBUTE));
    map.put("price", SessionUtil.getAttribute(PRICE_ATTRIBUTE));
    map.put("account", SessionUtil.getAttribute(ACCOUNT_ATTRIBUTE));

    return "/fundin/fundin_bank";
  }

  @PostMapping(value = "")
  public String fundInBankPost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      /* Lấy thông tin bankDirect */
      if (VISIBLE.equals(FUND_ORDER_SHOW_LINK_BANK)) {
        getCustomerBankDirect(request, response, map);
      }

      super.findEnabledServices(map, SharedConstants.FUNDIN_AVAILABLE_SERVICE, ServiceType.FUND_IN.name());

      // lấy danh sách bank
      if (VISIBLE.equals(FUND_ORDER_SHOW_LOCAL_BANK)) {
          getListBank(request, response, map);
      }

      String price = request.getParameter("price");
      String account = request.getParameter("account");

      // Store to Session
      SessionUtil.setAttribute(PRICE_ATTRIBUTE, price);
      SessionUtil.setAttribute(ACCOUNT_ATTRIBUTE, account);

      map.put("price", price);
      map.put("account", account);

      return "/fundin/fundin_bank";
    } catch (Exception e) {
      LOGGER.error("codeErr", e);
      return "/fundin/fundin_bank";
    }
  }


  @GetMapping(value = "/link-bank")
  public String fundInLinkbankGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
    map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
    map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
    map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
    map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
    map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
    map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
    map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
    map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
    map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
    map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
    map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
    map.put("listBankDirect", SessionUtil.getAttribute(LIST_BANK_DIRECT_ATTRIBUTE));
    map.put("listPhoneFundIn", SessionUtil.getAttribute(LIST_PHONE_FUNDIN_ATTRIBUTE));
    map.put("listTransactions", SessionUtil.getAttribute(LIST_TRANSACTIONS_ATTRIBUTE));

    return "/fundin/fundin_linkbank";
  }

  @PostMapping(value = "/link-bank")
  public String fundInLinkbank(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String _nameBank = request.getParameter("_nameBank");
      String bankName = request.getParameter("bankName");
      String bankDisplayName = request.getParameter("bankDisplayName");
      String faceValue = request.getParameter("faceValue");
      String realAmount = request.getParameter("realAmount");
      if (StringUtils.isEmpty(faceValue)) {
        faceValue = request.getParameter("amount");
      }

      // Store to Session
      SessionUtil.setAttribute(NAME_BANK_ATTRIBUTE, _nameBank);
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);
      SessionUtil.setAttribute(FACE_VALUE_ATTRIBUTE, faceValue);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);

      getPhoneFundIn(request, response, map);
      getTransaction(request, response, map);

      // lấy thông tin bankdirect
      FindCustomerBankDirectRequest findCustomerBankDirectReq = new FindCustomerBankDirectRequest();
      FindCustomerBankDirectResponse findCustomerBankDirectBaseRespType = walletEndpoint
          .findCustomerBankDirect(findCustomerBankDirectReq);
      CustomerBankDirect customerBankDirect = null;
      if (findCustomerBankDirectBaseRespType.getStatus().getCode() == 0
          && findCustomerBankDirectBaseRespType.getBankDirects() != null
          && findCustomerBankDirectBaseRespType.getBankDirects().size() == 1) {
        customerBankDirect = findCustomerBankDirectBaseRespType.getBankDirects().get(0);
      }
      if (customerBankDirect == null) {
        throw new Exception("No link bank direct found!");
      } else {
        bankName = customerBankDirect.getBankName();
        String bankCode = customerBankDirect.getBankCode();
        String phoneNumber = customerBankDirect.getPhone();
        String walletId = customerBankDirect.getWalletId();
        String cardIssueDate = customerBankDirect.getCardIssueDate();
        String bankAccountNumber = customerBankDirect.getBankAccountNumber();
        String bankAccountName = customerBankDirect.getBankAccountName();
        bankDisplayName = customerBankDirect.getBankDisplayName();
        String ssn = customerBankDirect.getSsn();
        String subscriptionId = customerBankDirect.getSubscriptionId();
        List<CustomerBankDirect> listBankDirect = findCustomerBankDirectBaseRespType
            .getBankDirects();

        // Store to Session
        SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
        SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
        SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
        SessionUtil.setAttribute(WALLET_ID_ATTRIBUTE, walletId);
        SessionUtil.setAttribute(CARD_ISSUE_DATE_ATTRIBUTE, cardIssueDate);
        SessionUtil.setAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE, bankAccountNumber);
        SessionUtil.setAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE, bankAccountName);
        SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);
        SessionUtil.setAttribute(SSN_ATTRIBUTE, ssn);
        SessionUtil.setAttribute(SUBSCRIPTION_ID_ATTRIBUTE, subscriptionId);
        SessionUtil.setAttribute(LIST_BANK_DIRECT_ATTRIBUTE, listBankDirect);

        map.put("bankName", bankName);
        map.put("bankDisplayName", bankDisplayName);
        map.put("bankAccountName", bankAccountName);
        map.put("bankCode", bankCode);
        map.put("phoneNumber", phoneNumber);
        map.put("walletId", walletId);
        map.put("ssn", ssn);
        map.put("cardIssueDate", cardIssueDate);
        map.put("bankAccountNumber", bankAccountNumber);
        map.put("faceValue", faceValue);
        map.put("realAmount", realAmount);
        map.put("subscriptionId", subscriptionId);
        map.put("listBankDirect", listBankDirect);

        return "/fundin/fundin_linkbank";
      }
    } catch (Exception e) {
      LOGGER.error("codeErr", e);
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));

      return "/fundin/fundin_linkbank";
    }

  }

  @GetMapping(value = "/infoLinkBank")
  public String infoLinkBank(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    // lấy thông tin bankdirect
    List<CustomerBankDirect> listBankDirect = new ArrayList<CustomerBankDirect>();
    try {
      String bankName = request.getParameter("bankName");
      String bankDisplayName = request.getParameter("bankDisplayName");
      String realAmount = request.getParameter("realAmount");

      // Store to Session
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);

      getTransaction(request, response, map);
      FindCustomerBankDirectRequest findCustomerBankDirectReq = new FindCustomerBankDirectRequest();
      FindCustomerBankDirectResponse findCustomerBankDirectBaseRespType = walletEndpoint
          .findCustomerBankDirect(findCustomerBankDirectReq);
      if (findCustomerBankDirectBaseRespType.getStatus()== null) {
        throw new Exception("No Responsive");
      }
      if (findCustomerBankDirectBaseRespType.getStatus().getCode() != 0) {
        throw new Exception(findCustomerBankDirectBaseRespType.getStatus().getValue());
      }

      CustomerBankDirect customerBankDirect = null;
      if (findCustomerBankDirectBaseRespType.getStatus().getCode() == 0
          && findCustomerBankDirectBaseRespType.getBankDirects() != null
          && findCustomerBankDirectBaseRespType.getBankDirects().size() == 1) {
        customerBankDirect = findCustomerBankDirectBaseRespType.getBankDirects().get(0);
      }
      if (customerBankDirect == null) {
        throw new Exception("No link bank direct found!");
      } else {
        bankName = customerBankDirect.getBankName();
        String bankCode = customerBankDirect.getBankCode();
        String phoneNumber = customerBankDirect.getPhone();
        String walletId = customerBankDirect.getWalletId();
        String cardIssueDate = customerBankDirect.getCardIssueDate();
        String bankAccountNumber = customerBankDirect.getBankAccountNumber();
        String bankAccountName = customerBankDirect.getBankAccountName();
        bankDisplayName = customerBankDirect.getBankDisplayName();
        String ssn = customerBankDirect.getSsn();
        String subscriptionId = customerBankDirect.getSubscriptionId();
        listBankDirect = findCustomerBankDirectBaseRespType.getBankDirects();

        // Store to Session
        SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
        SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
        SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
        SessionUtil.setAttribute(WALLET_ID_ATTRIBUTE, walletId);
        SessionUtil.setAttribute(CARD_ISSUE_DATE_ATTRIBUTE, cardIssueDate);
        SessionUtil.setAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE, bankAccountNumber);
        SessionUtil.setAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE, bankAccountName);
        SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);
        SessionUtil.setAttribute(SSN_ATTRIBUTE, ssn);
        SessionUtil.setAttribute(SUBSCRIPTION_ID_ATTRIBUTE, subscriptionId);
        SessionUtil.setAttribute(LIST_BANK_DIRECT_ATTRIBUTE, listBankDirect);

      }
      map.put("bankName", bankName);
      map.put("bankDisplayName", bankDisplayName);
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put("realAmount", realAmount);
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put("listBankDirect", listBankDirect);

      return "/fundin/fundin_linkbank";
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put("listBankDirect", listBankDirect);

      return "/fundin/fundin_linkbank";
    }
  }

  @GetMapping(value = "/verify-link-bank")
  public String linkBankVerifyGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
    map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
    map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
    map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
    map.put("isOtpRequired", SessionUtil.getAttribute(IS_OTP_REQUIRED_ATTRIBUTE));
    map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
    map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
    map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
    map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
    map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
    map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
    map.put("verifyRequestId", SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE));
    map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
    return "/fundin/fundin_linkbank_otp";
  }

  @PostMapping(value = "/verify-link-bank") // verify link bank
  public String linkBankVerify(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      if (!checkBankDirect(request, response, map)) {
        String codeErr = "Bạn chưa có tài khoản link Bank";
        map.put("codeErr", codeErr);
        SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
        return "/fundin/fundin_linkbank";
      }

      String realAmount = request.getParameter("realAmount");
      String feeAmount = request.getParameter("feeAmount");
      String faceValue = request.getParameter("faceValue").replaceAll("[^0-9]+", "");
      String bankName = request.getParameter("bankName");

      // Store to Session
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
      SessionUtil.setAttribute(FEE_AMOUNT_ATTRIBUTE, feeAmount);
      SessionUtil.setAttribute(FACE_VALUE_ATTRIBUTE, faceValue);
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);

      String requestId = UUID.randomUUID().toString();
      String requestTime = sdf.format(new Date());

      // Tạo request check fund in

      Object phoneNumberAttr = SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE);
      String phoneNumber = phoneNumberAttr != null ? phoneNumberAttr.toString() : StringUtils.EMPTY;
      Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : StringUtils.EMPTY;
      Object subscriptionIdAttr = SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE);
      String subscriptionId = subscriptionIdAttr != null ? subscriptionIdAttr.toString() : StringUtils.EMPTY;

      FundInBankRequest checkLinkBankFundInRequest = new FundInBankRequest();
      checkLinkBankFundInRequest.setRequestId(requestId);
      checkLinkBankFundInRequest.setRequestTime(requestTime);
      checkLinkBankFundInRequest.setAccesskey("config");
      checkLinkBankFundInRequest.setSignature("config");
      checkLinkBankFundInRequest.setSecretkey("config");
      checkLinkBankFundInRequest.setWalletId(phoneNumber);
      checkLinkBankFundInRequest.setSubscriptionId(subscriptionId);
      checkLinkBankFundInRequest.setBankCode(bankCode.toString());
      checkLinkBankFundInRequest.setAmount(faceValue);
      checkLinkBankFundInRequest.setFeeAmount(feeAmount.toString());
      checkLinkBankFundInRequest.setVerifyRequestId(null);
      checkLinkBankFundInRequest.setType(ACTION_VERIFY);
      checkLinkBankFundInRequest.setOtp(null);
      checkLinkBankFundInRequest.setChannel(CHANNEL_WEB);

      FundInBankResponse checkLinkBankFundInResponseBaseRespType = walletEndpoint.checkLinkBank(checkLinkBankFundInRequest);
      if (checkLinkBankFundInResponseBaseRespType == null || checkLinkBankFundInResponseBaseRespType.getStatus() ==null) {
        throw new Exception("No Responsive");
      } else {
        if (checkLinkBankFundInResponseBaseRespType.getStatus().getCode() != 0) {
          return "redirect:" + FUNDIN_LINKBANK;
        } else {
          String verifyRequestId = checkLinkBankFundInResponseBaseRespType.getRequestId();
          boolean isOtpRequired = checkLinkBankFundInResponseBaseRespType.getIsOtp();

          // Store to Session
          SessionUtil.setAttribute(VERIFY_REQUEST_ID_ATTRIBUTE, verifyRequestId);
          SessionUtil.setAttribute(IS_OTP_REQUIRED_ATTRIBUTE, isOtpRequired);

          map.put("realAmount", realAmount);
          map.put("faceValue", faceValue);
          map.put("feeAmount", feeAmount);
          map.put("isOtpRequired", isOtpRequired);
          map.put("bankCode", bankCode);
          map.put("bankName", bankName);
          map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
          map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
          map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
          map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
          map.put("verifyRequestId", verifyRequestId);

          return "/fundin/fundin_linkbank_otp";
        }
      }
    } catch (Exception e) {
      String codeErr = e.getMessage();
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
      map.put("isOtpRequired", SessionUtil.getAttribute(IS_OTP_REQUIRED_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("verifyRequestId", SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("codeErr", codeErr);
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
    }
    return "/fundin/fundin_linkbank";
  }


  @GetMapping(value = "/confirm-fundin-link-bank")
  public String getConfirmFundInLinkBank(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    Object confirmStatusAttr = SessionUtil.getAttribute(CONFIRM_STATUS_ATTRIBUTE);
    boolean confirmStatus = confirmStatusAttr != null && (boolean) confirmStatusAttr;

    if (confirmStatus) {
      return "/fundin/fundin_linkbank_transaction_success";
    } else {
      return "/fundin/fundin_linkbank_transaction_error";
    }
  }

  @PostMapping(value = "/confirm-fundin-link-bank")
  public String confirmFundInLinkBank(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    String requestId = UUID.randomUUID().toString();
    String requestTime = sdf.format(new Date());
    String codeOTP = request.getParameter("codeOTP"); // null

    // Store to Session
    SessionUtil.setAttribute(CODE_OTP_ATTRIBUTE, codeOTP);

    try {

      Object walletIdAttr = SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE);
      String walletId = walletIdAttr != null ? walletIdAttr.toString() : null;
      Object subscriptionIdAttr = SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE);
      String subscriptionId = subscriptionIdAttr != null ? subscriptionIdAttr.toString() : null;
      Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : null;
      Object faceValueAttr = SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE);
      String faceValue = faceValueAttr != null ? faceValueAttr.toString() : null;
      Object feeAmountAttr = SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE);
      String feeAmount = feeAmountAttr != null ? feeAmountAttr.toString() : null;
      Object verifyRequestIdAttr = SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE);
      String verifyRequestId = verifyRequestIdAttr != null ? verifyRequestIdAttr.toString() : null;

      FundInBankRequest checkLinkBankFundInRequest = new FundInBankRequest();
      checkLinkBankFundInRequest.setRequestId(requestId);
      checkLinkBankFundInRequest.setRequestTime(requestTime);
      checkLinkBankFundInRequest.setAccesskey("config");
      checkLinkBankFundInRequest.setSignature("config");
      checkLinkBankFundInRequest.setSecretkey("config");
      checkLinkBankFundInRequest.setWalletId(walletId);
      checkLinkBankFundInRequest.setSubscriptionId(subscriptionId);
      checkLinkBankFundInRequest.setBankCode(bankCode);
      checkLinkBankFundInRequest.setAmount(faceValue);
      checkLinkBankFundInRequest.setFeeAmount(feeAmount);
      checkLinkBankFundInRequest.setVerifyRequestId(verifyRequestId);
      checkLinkBankFundInRequest.setType(ACTION_CONFIRM);
      checkLinkBankFundInRequest.setOtp(codeOTP);
      checkLinkBankFundInRequest.setChannel(CHANNEL_WEB);

      FundInBankResponse checkLinkBankFundInResponseBaseRespType = walletEndpoint
          .checkLinkBank(checkLinkBankFundInRequest);
      if (checkLinkBankFundInResponseBaseRespType == null) {
        throw new Exception("No response!");
      }
      if (checkLinkBankFundInResponseBaseRespType.getStatus() == null) {
        throw new Exception("No response!");
      }
      if (checkLinkBankFundInResponseBaseRespType.getStatus().getCode() != 0) {
        throw new Exception(checkLinkBankFundInResponseBaseRespType.getStatus().getValue());
      }
      SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, true);

      return "/fundin/fundin_linkbank_transaction_success";
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      String codeErr = e.getMessage();
      map.put("codeErr", codeErr);
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));

      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put("verifyRequestId", SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE));
      map.put("codeOTP", SessionUtil.getAttribute(CODE_OTP_ATTRIBUTE));
      SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, false);

      if ("307".equals(codeErr) || "306".equals(codeErr) || "305".equals(codeErr) || "303"
          .equals(codeErr) || "302".equals(codeErr) || "301".equals(codeErr) || "300"
          .equals(codeErr) || "400".equals(codeErr) || "401".equals(codeErr) || "402"
          .equals(codeErr) || "403".equals(codeErr) || "404".equals(codeErr) || "405"
          .equals(codeErr) || "406".equals(codeErr) || "407".equals(codeErr) || "408"
          .equals(codeErr) || "500".equals(codeErr) || "501".equals(codeErr) || "502"
          .equals(codeErr) || "503".equals(codeErr) || "504".equals(codeErr) || "505"
          .equals(codeErr) || "506".equals(codeErr) || "507".equals(codeErr) || "508"
          .equals(codeErr) || "509".equals(codeErr) || "510".equals(codeErr) || "511"
          .equals(codeErr) || "512".equals(codeErr) || "600".equals(codeErr) || "601"
          .equals(codeErr) || "602".equals(codeErr) || "603".equals(codeErr) || "604"
          .equals(codeErr) || "605".equals(codeErr) || "606".equals(codeErr)) {
        return "redirect:" + FUNDIN_LINKBANK_VERIFY;
      } else {
        map.put("codeErr", codeErr);
        return "/fundin/fundin_linkbank_transaction_error";
      }
    }
  }

  @GetMapping(value = "/infoUnLink") // verify
  public String infoUnLinkBank(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      String requestId = UUID.randomUUID().toString();
      String requestTime = sdf.format(new Date());
      String subscriptionId = request.getParameter("subscriptionId");

      Object walletIdAttr = SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE);
      String walletId = walletIdAttr != null ? walletIdAttr.toString() : null;
      Object cardIssueDateAttr = SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE);
      String cardIssueDate = cardIssueDateAttr != null ? cardIssueDateAttr.toString() : null;
      Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : null;
      // Tạo request check unlink
      UnlinkBankRequest checkLinkBankFundInRequest = new UnlinkBankRequest();
      checkLinkBankFundInRequest.setRequestId(requestId);
      checkLinkBankFundInRequest.setRequestTime(requestTime);
      checkLinkBankFundInRequest.setAccesskey("accesskey");
      checkLinkBankFundInRequest.setSignature("signature");
      checkLinkBankFundInRequest.setSubscriptionId(subscriptionId);
      checkLinkBankFundInRequest.setWalletId(walletId);
      checkLinkBankFundInRequest.setWalletId(cardIssueDate);
      checkLinkBankFundInRequest.setBankCode(bankCode);
      checkLinkBankFundInRequest.setType(ACTION_VERIFY);
      checkLinkBankFundInRequest.setChannel(CHANNEL_WEB);

      UnlinkBankResponse checkLinkBankFundInResponseBaseRespType = walletEndpoint
          .customerUnLinkBank(checkLinkBankFundInRequest);
      if (checkLinkBankFundInResponseBaseRespType == null
          || checkLinkBankFundInResponseBaseRespType.getStatus().getCode() != 0) {
        throw new Exception(
            "Error" + checkLinkBankFundInResponseBaseRespType.getStatus().getValue());
      } else {
        if (checkLinkBankFundInResponseBaseRespType.getStatus().getCode() != 0) {
          return "/fundin/fundin_linkbank";
        } else {

          map.put("subscriptionId", subscriptionId);
          map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
          map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
          map.put("bankCode", bankCode);
          map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
          map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
          map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
          map.put("walletId", walletId);
          map.put("cardIssueDate", cardIssueDate);

          return "/unlink/unLinkBank";
        }

      }
    } catch (Exception e) {
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));

      return "/unlink/unLinkBank";
    }
  }


  @GetMapping(value = "/funInUnLinkBank")
  public String funInUnLinkBank(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      String requestId = UUID.randomUUID().toString();
      String requestTime = sdf.format(new Date());
      String subscriptionId = request.getParameter("subscriptionId");
      // Store to Session
      SessionUtil.setAttribute(SUBSCRIPTION_ID_ATTRIBUTE, subscriptionId);

      Object walletIdAttr = SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE);
      String walletId = walletIdAttr != null ? walletIdAttr.toString() : null;
      Object cardIssueDateAttr = SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE);
      String cardIssueDate = cardIssueDateAttr != null ? cardIssueDateAttr.toString() : null;
      Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : null;
      // Tạo request check unlink
      UnlinkBankRequest checkLinkBankFundInRequest = new UnlinkBankRequest();
      checkLinkBankFundInRequest.setRequestId(requestId);
      checkLinkBankFundInRequest.setRequestTime(requestTime);
      checkLinkBankFundInRequest.setAccesskey("accesskey");
      checkLinkBankFundInRequest.setSignature("signature");
      checkLinkBankFundInRequest.setSubscriptionId(subscriptionId);
      checkLinkBankFundInRequest.setWalletId(walletId);
      checkLinkBankFundInRequest.setWalletId(cardIssueDate);
      checkLinkBankFundInRequest.setBankCode(bankCode);
      checkLinkBankFundInRequest.setType(ACTION_VERIFY);
      checkLinkBankFundInRequest.setChannel(CHANNEL_WEB);

      UnlinkBankResponse checkLinkBankFundInResponseBaseRespType = walletEndpoint
          .customerUnLinkBank(checkLinkBankFundInRequest);
      if (checkLinkBankFundInResponseBaseRespType == null
          || checkLinkBankFundInResponseBaseRespType.getStatus().getCode() != 0) {
        throw new Exception(
            "Error" + checkLinkBankFundInResponseBaseRespType.getStatus().getValue());
      } else {
        if (checkLinkBankFundInResponseBaseRespType.getStatus().getCode() != 0) {
          return "/fundin/fundin_linkbank";
        } else {

          map.put("subscriptionId", subscriptionId);
          map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
          map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
          map.put("bankCode", bankCode);
          map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
          map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
          map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
          map.put("walletId", walletId);
          map.put("cardIssueDate", cardIssueDate);

          return "/fundin/fundin_unLinkBank";
        }

      }
    } catch (Exception e) {
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));

      return "/fundin/fundin_unLinkBank";
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

      String[] serviceTypeId = request.getParameterValues("serviceTypeId");
      if (serviceTypeId != null && serviceTypeId.length > 0
          && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
      } else {
        transactionsRequest.setServiceTypeIds(Arrays.asList(FUND_IN.name()));
      }
      String[] serviceCode = request.getParameterValues("serviceCode");
      if (serviceCode != null && serviceCode.length > 0
          && !serviceCode[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
      }

      FindTransactionsResponse baseTransResponseType = transactionEndpoint.transactionFind(transactionsRequest);
      if (baseTransResponseType == null || baseTransResponseType.getStatus() == null
          || baseTransResponseType.getStatus().getCode() != 0) {
        LOGGER.error(" API transactionFind fail");
      } else {
        Collection<Transaction> listTransactions = baseTransResponseType.getTransactions();
        SessionUtil.setAttribute(LIST_TRANSACTIONS_ATTRIBUTE, listTransactions);
      }

      map.put("pagesize", limit);
      map.put("offset", offset);
      map.put("listTransactions", SessionUtil.getAttribute(LIST_TRANSACTIONS_ATTRIBUTE));
      map.put("total", total.intValue());
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

  public boolean checkBankDirect(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) {
    CustomerBankDirect customerBankDirect = null;
    try {
      FindCustomerBankDirectRequest findCustomerBankDirectReq = new FindCustomerBankDirectRequest();
      FindCustomerBankDirectResponse findCustomerBankDirectBaseRespType = new FindCustomerBankDirectResponse();
      findCustomerBankDirectBaseRespType = walletEndpoint
          .findCustomerBankDirect(findCustomerBankDirectReq);
      if (findCustomerBankDirectBaseRespType.getStatus().getCode() != 0) {
        throw new Exception(findCustomerBankDirectBaseRespType.getStatus().getValue());
      }
      if (findCustomerBankDirectBaseRespType.getStatus().getCode() == 0
          && findCustomerBankDirectBaseRespType.getBankDirects() != null
          && findCustomerBankDirectBaseRespType.getBankDirects().size() == 1) {
        customerBankDirect = findCustomerBankDirectBaseRespType.getBankDirects().get(0);
      }
    } catch (Exception e) {

      LOGGER.error(e.getMessage());
    }
    return customerBankDirect != null;
  }

  public void getListLinkBank(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) {
    try {
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(null);
      findBankRequest.setIsLinkBankSupport(true);

      FindBankResponse findBankResponseType =
          systemEndpoint.getListBank(findBankRequest);
      if (findBankResponseType == null || findBankResponseType.getStatus().getCode() != 0) {
        throw new Exception(findBankResponseType.getStatus().getValue());
      }
      List<Bank> listLinkBank = new ArrayList<Bank>();
      if (findBankResponseType.getStatus().getCode() == 0
          && findBankResponseType.getBanks().size() >= 1) {
        listLinkBank = findBankResponseType.getBanks();
      }
      if (listLinkBank == null) {
        throw new Exception("No data list link bank");
      }

      map.put("listLinkBank", listLinkBank);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

  public void getListBank(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {
      // get list bank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(null);
      findBankRequest.setIsLinkBankSupport(null);
      FindBankResponse baseResponseType = systemEndpoint.getListBank(findBankRequest);
      // --- check base response type;
      if (baseResponseType == null) {
        throw new Exception("No response!");
      }
      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }
      // set get dữ liệu
      if (baseResponseType.getBanks().size() >= 1) {
        List<Bank> listBank = baseResponseType.getBanks();
        SessionUtil.setAttribute(LIST_BANK_ATTRIBUTE,listBank);
      }
      map.put("listBank", SessionUtil.getAttribute(LIST_BANK_ATTRIBUTE));

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

  public void getCustomerBankDirect(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) {
    List<CustomerBankDirect> lstCusBankDirect;
    try {
      // lấy thông tin bankdirect
      FindCustomerBankDirectRequest findCustomerBankDirectReq = new FindCustomerBankDirectRequest();
      FindCustomerBankDirectResponse findCustomerBankDirectBaseRespType = walletEndpoint
          .findCustomerBankDirect(findCustomerBankDirectReq);
      if (findCustomerBankDirectBaseRespType.getStatus().getCode() != 0) {
        throw new Exception(findCustomerBankDirectBaseRespType.getStatus().getValue());
      }

      CustomerBankDirect customerBankDirect = null;
      if (findCustomerBankDirectBaseRespType.getStatus().getCode() == 0
          && findCustomerBankDirectBaseRespType.getBankDirects() != null
          && findCustomerBankDirectBaseRespType.getBankDirects().size() == 1) {
        customerBankDirect = findCustomerBankDirectBaseRespType.getBankDirects().get(0);
      }
      if (customerBankDirect == null) {
        lstCusBankDirect = null;
      } else {
        lstCusBankDirect = findCustomerBankDirectBaseRespType.getBankDirects();
      }
      SessionUtil.setAttribute(LST_CUS_BANK_DIRECT_DIRECT_ATTRIBUTE,lstCusBankDirect);
      map.put("listBankDirect", lstCusBankDirect);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

  public void getPhoneFundIn(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) {
    try {
      // Tạo request & set params vào request
      FindTransactionsRequest transactionsRequest =
          new FindTransactionsRequest();
      transactionsRequest.setOffset(0);
      transactionsRequest.setLimit(2);
      transactionsRequest.setTextSearch(null);
      transactionsRequest.setFromDate(null);
      transactionsRequest.setEndDate(null);
      // --
      String[] serviceTypeId = request.getParameterValues("serviceTypeId");
      if (serviceTypeId != null && serviceTypeId.length > 0
          && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
      } else {
        transactionsRequest.setServiceTypeIds(Arrays.asList(FUND_IN.name()));
      }
      String[] serviceCode = request.getParameterValues("serviceCode");
      if (serviceCode != null && serviceCode.length > 0
          && !serviceCode[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
      }

      FindTransactionsResponse baseTransResponseType = transactionEndpoint.transactionFind(transactionsRequest);
      if (baseTransResponseType == null || baseTransResponseType.getStatus() == null
          || baseTransResponseType.getStatus().getCode() != 0) {
        LOGGER.error(" API transactionFind fail");
      } else {
        if (baseTransResponseType != null) {
          Collection<Transaction> listPhoneFundIn = baseTransResponseType.getTransactions();
          SessionUtil.setAttribute(LIST_PHONE_FUNDIN_ATTRIBUTE,listPhoneFundIn);
          map.put("listPhoneFundIn", listPhoneFundIn);
        }
      }
    } catch (Exception e) {
      map.put("listPhoneFundIn", SessionUtil.getAttribute(LIST_PHONE_FUNDIN_ATTRIBUTE));
      LOGGER.error(e.getMessage());
    }
  }

}
