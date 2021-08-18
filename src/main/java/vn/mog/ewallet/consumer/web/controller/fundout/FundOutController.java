package vn.mog.ewallet.consumer.web.controller.fundout;

import static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_LINK_BANK;
import static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_LOCAL_BANK;
import static vn.mog.ewallet.consumer.web.exception.MessageNotify.ERROR_CODE;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.FUND_OUT;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.ADMIN_OPERATION;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.consumer.web.controller.fundorder.FundOrderController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.CustomerBankDirect;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.CreateFundOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.CreateFundOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.UpdateFundOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.UpdateFundOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.bean.FundOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankProfileRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankProfileResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetOTPConfirmRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetOTPConfirmResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.FundOrderChannelType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindCustomerBankDirectRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindCustomerBankDirectResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FundOutToBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FundOutToBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrderAttachment;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.framework.contract.base.MobiliserResponseType.Status;

@Controller
@RequestMapping(value = "/fundout")
public class FundOutController extends FundOrderController {

  private static final String FUND_OUT_CONTROLLER = "/fundout";
  public static final String FUND_OUT_REQUEST_GET = FUND_OUT_CONTROLLER + "/requestGet";
  private static final Logger LOGGER = LogManager.getLogger(FundOutController.class);
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

  private static final String ACTION_VERIFY = "verify";
  private static final String ACTION_CONFIRM = "confirm";
  private static final String CHANNEL_WEB = "WEB";

  // Session attribute
  private static final String AMOUNT_ATTRIBUTE = "AMOUNT";
  private static final String FEE_AMOUNT_ATTRIBUTE = "FEE_AMOUNT";
  private static final String VERIFY_REQUEST_ID_ATTRIBUTE = "VERIFY_REQUEST_ID";
  private static final String SUBSCRIPTION_ID_ATTRIBUTE = "SUBSCRIPTION_ID";
  private static final String PHONE_NUMBER_ATTRIBUTE = "PHONE_NUMBER";
  private static final String BANK_CODE_ATTRIBUTE = "BANK_CODE";
  private static final String WALLET_ID_ATTRIBUTE = "WALLET_ID";
  private static final String IS_OTP_ATTRIBUTE = "IS_OTP";
  private static final String CARD_ISSUE_DATE_ATTRIBUTE = "CARD_ISSUE_DATE";
  private static final String BANK_ACCOUNT_NUMBER_ATTRIBUTE = "BANK_ACCOUNT_NUMBER";
  private static final String SSN_ATTRIBUTE = "SSN";
  private static final String BANK_ACCOUNT_NAME_ATTRIBUTE = "BANK_ACCOUNT_NAME";
  private static final String REAL_AMOUNT_ATTRIBUTE = "REAL_AMOUNT";
  private static final String BANK_NAME_ATTRIBUTE = "BANK_NAME";
  private static final String BANK_DISPLAY_NAME_ATTRIBUTE = "BANK_DISPLAY_NAME";
  private static final String CHI_NHANH_BANK_ATTRIBUTE = "CHI_NHANH_BANK";
  private static final String DESCRIPTION_ATTRIBUTE = "DESCRIPTION";
  private static final String CHECK_CODE_ATTRIBUTE = "CHECK_CODE";
  private static final String CODE_OTP_ATTRIBUTE = "CODE_OTP";
  private static final String ACCOUNT_ATTRIBUTE = "ACCOUNT";
  private static final String TPRICE_ATTRIBUTE = "TPRICE";
  private static final String LIST_BANK_DIRECT_ATTRIBUTE = "LIST_BANK_DIRECT";
  private static final String LIST_BANK_ATTRIBUTE = "LIST_BANK";
  private static final String LIST_TRANSACTIONS_ATTRIBUTE = "LIST_TRANSACTIONS";
  private static final String REQUEST_STATUS_ATTRIBUTE = "REQUEST_STATUS";
  private static final String CONFIRM_STATUS_ATTRIBUTE = "CONFIRM_STATUS";

  @GetMapping(value = "")
  public String fundOutBank(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    // lấy danh sách bank
    getListBank(request, response, map);

    map.put("account", SessionUtil.getAttribute(ACCOUNT_ATTRIBUTE));
    map.put("tprice", SessionUtil.getAttribute(TPRICE_ATTRIBUTE));
    map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
    map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
    map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
    map.put("listBankDirect", SessionUtil.getAttribute(LIST_BANK_DIRECT_ATTRIBUTE));
    map.put("listBank", SessionUtil.getAttribute(LIST_BANK_ATTRIBUTE));

    return "/fundout/fundout_bank";
  }

  @PostMapping(value = "")
  public String fundOutBankPost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      /* Lấy thông tin bankDirect */
      if (VISIBLE.equals(FUND_ORDER_SHOW_LINK_BANK)) {
        getCustomerBankDirect(request, response, map);
      }

      // lấy danh sách bank
      if (VISIBLE.equals(FUND_ORDER_SHOW_LOCAL_BANK)) {
        getListBank(request, response, map);
      }

      String account = request.getParameter("account");
      String tprice = request.getParameter("tprice");

      // Store to Session
      SessionUtil.setAttribute(ACCOUNT_ATTRIBUTE, account);
      SessionUtil.setAttribute(TPRICE_ATTRIBUTE, tprice);

      map.put("account", account);
      map.put("tprice", tprice);
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));

      return "/fundout/fundout_bank";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));

      return "/fundout/fundout_bank";
    }
  }

  @GetMapping(value = "/history") // trang fundount
  public String fundOutHistoryVisa(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
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
        throw new Exception("No link bank direct found!");
      } else {
        String bankName = customerBankDirect.getBankName();
        String bankCode = customerBankDirect.getBankCode();
        String bankAccountNumber = customerBankDirect.getBankAccountNumber();
        String phoneNumber = customerBankDirect.getPhone();
        String walletId = customerBankDirect.getWalletId();
        String cardIssueDate = customerBankDirect.getCardIssueDate();
        String bankAccountName = customerBankDirect.getBankAccountName();
        String bankDisplayName = customerBankDirect.getBankDisplayName();
        String subscriptionId = customerBankDirect.getSubscriptionId();
        String ssn = customerBankDirect.getSsn();

        List<CustomerBankDirect> listBankFundOut = findCustomerBankDirectBaseRespType
            .getBankDirects();

        // Store to Session
        SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
        SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
        SessionUtil.setAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE, bankAccountNumber);
        SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
        SessionUtil.setAttribute(WALLET_ID_ATTRIBUTE, walletId);
        SessionUtil.setAttribute(CARD_ISSUE_DATE_ATTRIBUTE, cardIssueDate);
        SessionUtil.setAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE, bankAccountName);
        SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);
        SessionUtil.setAttribute(SUBSCRIPTION_ID_ATTRIBUTE, subscriptionId);
        SessionUtil.setAttribute(SSN_ATTRIBUTE, ssn);

        map.put("bankName", bankName);
        map.put("bankCode", bankCode);
        map.put("bankAccountNumber", bankAccountNumber);
        map.put("bankAccountName", bankAccountName);
        map.put("phoneNumber", phoneNumber);
        map.put("walletId", walletId);
        map.put("cardIssueDate", cardIssueDate);
        map.put("bankAccountNumber", bankAccountNumber);
        map.put("bankDisplayName", bankDisplayName);
        map.put("subscriptionId", subscriptionId);
        map.put("listBankFundOut", listBankFundOut);
      }

      return "/fundout/fundout_history";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/fundout/fundout_history";
    }

  }

  @GetMapping(value = "/requestGet")
  public String fundOutRequest(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      getTransaction(request, response, map);

      String amount = request.getParameter("amount");
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);

      map.put("amount", amount);
      map.put("chiNhanhBank", SessionUtil.getAttribute(CHI_NHANH_BANK_ATTRIBUTE));
      map.put("description", SessionUtil.getAttribute(DESCRIPTION_ATTRIBUTE));
      map.put("checkCode", SessionUtil.getAttribute(CHECK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));

      return "/fundout/fundout__request";
    } catch (Exception e) {
      LOGGER.error(e.getMessage());

      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("chiNhanhBank", SessionUtil.getAttribute(CHI_NHANH_BANK_ATTRIBUTE));
      map.put("description", SessionUtil.getAttribute(DESCRIPTION_ATTRIBUTE));
      map.put("checkCode", SessionUtil.getAttribute(CHECK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      getTransaction(request, response, map);

      return "/fundout/fundout__request";
    }
  }

  @GetMapping(value = "/request") // verify
  public String fundOutRequestSubmitGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    map.put("description", SessionUtil.getAttribute(DESCRIPTION_ATTRIBUTE));
    map.put("listTransactions", SessionUtil.getAttribute(LIST_TRANSACTIONS_ATTRIBUTE));

    map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
    map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
    map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));

    map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
    map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
    map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
    map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
    map.put("chiNhanhBank", SessionUtil.getAttribute(CHI_NHANH_BANK_ATTRIBUTE));
    map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));

    map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
    map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
    map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));

    map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
    map.put("isOTP", SessionUtil.getAttribute(IS_OTP_ATTRIBUTE));
    map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
    map.put("verifyRequestId", SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE));

    Object requestStatusAttr = SessionUtil.getAttribute(REQUEST_STATUS_ATTRIBUTE);
    boolean requestStatus = requestStatusAttr != null && (boolean) requestStatusAttr;

    return requestStatus ? "/fundout/fundout_otp" : "/fundout/fundout_linkbank";
  }

  @PostMapping(value = "/request") // verify
  public String fundOutRequestSubmit(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      getTransaction(request, response, map);

      String requestId = UUID.randomUUID().toString();
      String requestTime = sdf.format(new Date());
      String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
      Long feeAmount = NumberUtil.convertToLong(request.getParameter("feeAmount"));
      String realAmount = request.getParameter("realAmount").replaceAll("[^0-9]+", "");
      String chiNhanhBank = request.getParameter("chiNhanhBank");
      String description = request.getParameter("description");
      String checkCode = request.getParameter("checkCode");

      // Store to Session
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(FEE_AMOUNT_ATTRIBUTE, feeAmount);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
      SessionUtil.setAttribute(CHI_NHANH_BANK_ATTRIBUTE, chiNhanhBank);
      SessionUtil.setAttribute(DESCRIPTION_ATTRIBUTE, description);
      SessionUtil.setAttribute(CHECK_CODE_ATTRIBUTE, checkCode);

      // Get data from Session
      Object phoneNumberAttr = SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE);
      String phoneNumber = phoneNumberAttr != null ? phoneNumberAttr.toString() : StringUtils.EMPTY;
      Object subscriptionIdAttr = SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE);
      String subscriptionId =
          subscriptionIdAttr != null ? subscriptionIdAttr.toString() : StringUtils.EMPTY;
      Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : StringUtils.EMPTY;

      // tao request fund out
      FundOutToBankRequest checkLinkBankFundOutRequest = new FundOutToBankRequest();
      checkLinkBankFundOutRequest.setRequestId(requestId);
      checkLinkBankFundOutRequest.setRequestTime(requestTime);
      checkLinkBankFundOutRequest.setAccesskey("config");
      checkLinkBankFundOutRequest.setSignature("config");
      checkLinkBankFundOutRequest.setSecretkey("config");
      checkLinkBankFundOutRequest.setWalletId(phoneNumber);
      checkLinkBankFundOutRequest.setSubscriptionId(subscriptionId);
      checkLinkBankFundOutRequest.setBankCode(bankCode);
      checkLinkBankFundOutRequest.setAmount(amount);
      checkLinkBankFundOutRequest.setFeeAmount(feeAmount.toString());
      checkLinkBankFundOutRequest.setVerifyRequestId(null);
      checkLinkBankFundOutRequest.setType(ACTION_VERIFY);
      checkLinkBankFundOutRequest.setOtp(null);
      checkLinkBankFundOutRequest.setChannel(CHANNEL_WEB);

      FundOutToBankResponse checkLinkBankFundOutResponse = walletEndpoint
          .fundOutToBank(checkLinkBankFundOutRequest);
      if (checkLinkBankFundOutResponse == null
          || checkLinkBankFundOutResponse.getStatus() == null) {
        throw new Exception("No response!");
      }
      if(checkLinkBankFundOutResponse.getStatus().getCode() != 0) {
        throw new Exception(checkLinkBankFundOutResponse.getStatus().getValue());
      }
      String verifyRequestId = checkLinkBankFundOutResponse.getRequestId();
      Boolean isOTP = checkLinkBankFundOutResponse.getIsOtp();

      map.put("amount", amount);
      map.put("feeAmount", feeAmount);
      map.put("realAmount", realAmount);

      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankCode", bankCode);
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("chiNhanhBank", chiNhanhBank);
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));

      map.put("phoneNumber", phoneNumber);
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));

      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("isOTP", isOTP);
      map.put("subscriptionId", subscriptionId);
      map.put("verifyRequestId", verifyRequestId);

      SessionUtil.setAttribute(VERIFY_REQUEST_ID_ATTRIBUTE, verifyRequestId);
      SessionUtil.setAttribute(IS_OTP_ATTRIBUTE, isOTP);
      SessionUtil.setAttribute(REQUEST_STATUS_ATTRIBUTE, true);

      return "/fundout/fundout_otp";
    } catch (Exception e) {
      getTransaction(request, response, map);

      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));

      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("chiNhanhBank", SessionUtil.getAttribute(CHI_NHANH_BANK_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));

      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));

      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("isOTP", SessionUtil.getAttribute(IS_OTP_ATTRIBUTE));
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put("verifyRequestId", SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE));
      map.put("description", SessionUtil.getAttribute(DESCRIPTION_ATTRIBUTE));

      SessionUtil.setAttribute(REQUEST_STATUS_ATTRIBUTE, false);

      return "/fundout/fundout_linkbank";
    }
  }

  @GetMapping(value = "/fund-out-link-bank-confirm") // confirm
  public String fundOutLinkBankConfirmGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
    map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
    map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));

    map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
    map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
    map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
    map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
    map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
    map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
    map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
    map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
    map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
    map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
    map.put("verifyRequestId", SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE));
    map.put("codeOTP", SessionUtil.getAttribute(CODE_OTP_ATTRIBUTE));

    Object confirmStatusAttr = SessionUtil.getAttribute(CONFIRM_STATUS_ATTRIBUTE);
    boolean confirmStatus = confirmStatusAttr != null && (boolean) confirmStatusAttr;

    return confirmStatus ? "/fundout/fundout_success" : "/fundout/fundout_error";
  }

  @PostMapping(value = "/fund-out-link-bank-confirm") // confirm
  public String fundOutLinkBankConfirm(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String requestId = UUID.randomUUID().toString();
      String requestTime = sdf.format(new Date());
      String codeOTP = request.getParameter("codeOTP");
      String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
      Long feeAmount = NumberUtil.convertToLong(request.getParameter("feeAmount"));
      String realAmount = request.getParameter("realAmount").replaceAll("[^0-9]+", "");

      //Store to Session
      SessionUtil.setAttribute(CODE_OTP_ATTRIBUTE, codeOTP);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(FEE_AMOUNT_ATTRIBUTE, feeAmount);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);

      // Get data from Session
      Object walletIdAttr = SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE);
      String walletId = walletIdAttr != null ? walletIdAttr.toString() : null;
      Object subscriptionIdAttr = SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE);
      String subscriptionId = subscriptionIdAttr != null ? subscriptionIdAttr.toString() : null;
      Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : null;
      Object verifyRequestIdAttr = SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE);
      String verifyRequestId = verifyRequestIdAttr != null ? verifyRequestIdAttr.toString() : null;

      // tao request fund out
      FundOutToBankRequest checkLinkBankFundOutRequest = new FundOutToBankRequest();
      checkLinkBankFundOutRequest.setRequestId(requestId);
      checkLinkBankFundOutRequest.setRequestTime(requestTime);
      checkLinkBankFundOutRequest.setAccesskey("config");
      checkLinkBankFundOutRequest.setSignature("config");
      checkLinkBankFundOutRequest.setSecretkey("config");
      checkLinkBankFundOutRequest.setWalletId(walletId);
      checkLinkBankFundOutRequest.setSubscriptionId(subscriptionId);
      checkLinkBankFundOutRequest.setBankCode(bankCode);
      checkLinkBankFundOutRequest.setAmount(amount);
      checkLinkBankFundOutRequest.setFeeAmount(feeAmount.toString());
      checkLinkBankFundOutRequest.setVerifyRequestId(verifyRequestId);
      checkLinkBankFundOutRequest.setType(ACTION_CONFIRM);
      checkLinkBankFundOutRequest.setOtp(null);
      checkLinkBankFundOutRequest.setChannel(CHANNEL_WEB);

      FundOutToBankResponse checkLinkBankFundOutResponseBaseRespType =
          walletEndpoint.confirmOtpFundOutToBank(checkLinkBankFundOutRequest);
      if (checkLinkBankFundOutResponseBaseRespType == null
          || checkLinkBankFundOutResponseBaseRespType.getStatus().getCode() != 0) {
        throw new Exception("No response!");
      } else if(checkLinkBankFundOutResponseBaseRespType.getStatus().getCode() != 0) {
        throw new Exception(checkLinkBankFundOutResponseBaseRespType.getStatus().getValue());
      } else {
        if (checkLinkBankFundOutResponseBaseRespType.getStatus().getCode() != 0) {
          throw new Exception(
              checkLinkBankFundOutResponseBaseRespType.getStatus().getValue());
        } else {
          verifyRequestId = checkLinkBankFundOutResponseBaseRespType.getRequestId();
          Boolean isOTP = checkLinkBankFundOutResponseBaseRespType.getIsOtp();

          map.put("amount", amount);
          map.put("feeAmount", feeAmount);
          map.put("realAmount", realAmount);

          map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
          map.put("bankCode", bankCode);
          map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
          map.put("walletId", walletId);
          map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
          map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
          map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
          map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
          map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
          map.put("subscriptionId", subscriptionId);
          map.put("verifyRequestId", verifyRequestId);
          map.put("codeOTP", codeOTP);

          SessionUtil.setAttribute(VERIFY_REQUEST_ID_ATTRIBUTE, verifyRequestId);
          SessionUtil.setAttribute(IS_OTP_ATTRIBUTE, isOTP);
          SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, true);

          return "/fundout/fundout_success";
        }
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));

      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put("verifyRequestId", SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE));
      map.put("codeOTP", SessionUtil.getAttribute(CODE_OTP_ATTRIBUTE));

      SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, false);

      return "/fundout/fundout_error";
    }
  }


  @GetMapping(value = "/fundOutVisa")
  public String fundOutVisa() throws Exception {
    return "/fundout/fundout_visa";
  }

  @RequestMapping(value = "/getOtp", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION
      + "','FINANCE','FINANCESUPPORT_LEADER','MERCHANT','AGENT','CUSTOMER')")
  public GetOTPConfirmResponse getOTPConfirm(HttpServletRequest request) throws Exception {
    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    if (fundOrderId > 0) {
      return walletEndpoint.getOTPConfirmWalletTransferOrder(new GetOTPConfirmRequest(fundOrderId));
    }
    GetOTPConfirmResponse response = new GetOTPConfirmResponse();
    response.setStatus(new Status(ERROR_CODE, "Error"));
    return response;
  }

  @GetMapping(value = "/link-bank")
  public String fundOutLinkBankGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    map.put("listTransactions", SessionUtil.getAttribute(LIST_TRANSACTIONS_ATTRIBUTE));
    map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
    map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
    map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
    map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
    map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
    map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
    map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
    map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
    map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
    map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
    map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
    map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
    map.put("listBankDirect", SessionUtil.getAttribute(LIST_BANK_DIRECT_ATTRIBUTE));
    map.put("chiNhanhBank", SessionUtil.getAttribute(CHI_NHANH_BANK_ATTRIBUTE));
    map.put("description", SessionUtil.getAttribute(DESCRIPTION_ATTRIBUTE));

    return "/fundout/fundout_linkbank";
  }

  @PostMapping(value = "/link-bank")
  public String fundOutLinkBank(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
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
        throw new Exception("No link bank direct found!");
      } else {
        String bankName = customerBankDirect.getBankName();
        String bankCode = customerBankDirect.getBankCode();
        String phoneNumber = customerBankDirect.getPhone();
        String walletId = customerBankDirect.getWalletId();
        String cardIssueDate = customerBankDirect.getCardIssueDate();
        String bankAccountNumber = customerBankDirect.getBankAccountNumber();
        String bankAccountName = customerBankDirect.getBankAccountName();
        String bankDisplayName = customerBankDirect.getBankDisplayName();
        String ssn = customerBankDirect.getSsn();
        String subscriptionId = customerBankDirect.getSubscriptionId();
        List<CustomerBankDirect> listBankDirect = findCustomerBankDirectBaseRespType
            .getBankDirects();
        String amount = request.getParameter("amount");

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
        SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);

        getTransaction(request, response, map);
        map.put("amount", amount);
        map.put("bankName", bankName);
        map.put("bankDisplayName", bankDisplayName);
        map.put("bankAccountName", bankAccountName);
        map.put("bankCode", bankCode);
        map.put("phoneNumber", phoneNumber);
        map.put("walletId", walletId);
        map.put("ssn", ssn);
        map.put("cardIssueDate", cardIssueDate);
        map.put("bankAccountNumber", bankAccountNumber);
        map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
        map.put("subscriptionId", subscriptionId);
        map.put("listBankDirect", listBankDirect);

        return "/fundout/fundout_linkbank";
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      getTransaction(request, response, map);

      map.put("codeErr", codeErr);
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("bankAccountName", SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("walletId", SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put("ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put("cardIssueDate", SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put("bankAccountNumber", SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("subscriptionId", SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put("listBankDirect", SessionUtil.getAttribute(LIST_BANK_DIRECT_ATTRIBUTE));

      return "/fundout/fundout_linkbank";
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
        transactionsRequest.setServiceTypeIds(Collections.singletonList(FUND_OUT.name()));
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
        listTransactions = baseTransResponseType.getTransactions();
      }
      SessionUtil.setAttribute(LIST_TRANSACTIONS_ATTRIBUTE, listTransactions);

      map.put("pagesize", limit);
      map.put("offset", offset);
      map.put("listTransactions", listTransactions);
      map.put("total", total.intValue());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);
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
      List<Bank> listBank = new ArrayList<>();
      if (baseResponseType.getBanks().size() >= 1) {
        listBank = baseResponseType.getBanks();
      }
      map.put("listBank", listBank);
      SessionUtil.setAttribute(LIST_BANK_ATTRIBUTE, listBank);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);
    }
  }

  private void getCustomerBankDirect(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) {
    List<CustomerBankDirect> lstCusBankDirect = new ArrayList<>();
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
      if (customerBankDirect != null) {
        lstCusBankDirect = findCustomerBankDirectBaseRespType.getBankDirects();
      }
      SessionUtil.setAttribute(LIST_BANK_DIRECT_ATTRIBUTE, lstCusBankDirect);
      map.put("listBankDirect", lstCusBankDirect);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);
    }
  }

  public void getPhoneFundOut(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) {
    Collection<Transaction> listPhoneFundOut = new ArrayList<>();
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
        transactionsRequest.setServiceTypeIds(Collections.singletonList(FUND_OUT.name()));
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
        listPhoneFundOut = baseTransResponseType.getTransactions();
      }

      map.put("listPhoneFundOut", listPhoneFundOut);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);
      map.put("listPhoneFundOut", listPhoneFundOut);
    }
  }

  @GetMapping(value = "/{fund_out_method}/management")
  public String fundOutCashOnHand(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_out_method") String fundOutMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundOutMethod) && !BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
      return "/about/404";
    }

    try {
      map.put("fund_out_method", fundOutMethod);

      return "/fundout/fundout_request_management";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/fundout/fundout_request_management";
    }
  }

  @GetMapping(value = "/{fund_out_method}/management/detail/{requestId}")
  public String requestDetail(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_out_method") String fundOutMethod,
      @PathVariable("requestId") String requestId) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundOutMethod) && !BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
      return "/about/404";
    }

    try {
      map.put("fund_out_method", fundOutMethod);
      map.put("request_id", requestId);

      map.putAll(putOrderInfo(requestId));

      return "/fundout/fundout_request_detail";
    } catch (Exception e) {

      return handleError(e, map, fundOutMethod);
    }
  }

  @GetMapping(value = "/{fund_out_method}/management/detail/{requestId}/edit")
  public String requestDetailEdit(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_out_method") String fundOutMethod,
      @PathVariable("requestId") String requestId) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundOutMethod) && !BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
      return "/about/404";
    }

    try {
      map.put("fund_out_method", fundOutMethod);
      map.put("request_id", requestId);

      map.putAll(putOrderInfo(requestId));

      return "/fundout/fundout_request_detail_edit";
    } catch (Exception e) {

      return handleError(e, map, fundOutMethod);
    }
  }

  @PostMapping(value = "/{fund_out_method}/management/detail/{requestId}/edit-submit")
  public String requestDetailEditSubmit(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_out_method") String fundOutMethod,
      @PathVariable("requestId") String requestId) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundOutMethod) && !BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
      return "/about/404";
    }

    map.put("fund_out_method", fundOutMethod);
    map.put("request_id", requestId);
    UpdateFundOrderResponse updateFundOrderResponseType = new UpdateFundOrderResponse();
    try {
      UpdateFundOrderRequest updateFundOrderRequest = new UpdateFundOrderRequest();

      FundOrder fundOrder = new FundOrder();
      fundOrder.setId(Long.parseLong(requestId));
      if (CASH_ON_HAND_PREFIX.equals(fundOutMethod)) {
        fundOrder.setOrderChannel(FundOrderChannelType.CASH_ON_HAND.code);
      } else if (BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
        fundOrder.setOrderChannel(FundOrderChannelType.BANK_TRANSFER.code);
        fundOrder.setCommandCode(request.getParameter("command_code"));
        fundOrder.setBankCode(request.getParameter("bank_code"));
      }
      fundOrder.setAmount(
          NumberUtil.convertToLong(
              StringUtils.trimToEmpty(request.getParameter("amount").replaceAll("[^0-9]+", ""))));
      fundOrder.setInfo(request.getParameter("remark"));
      fundOrder.setServiceType(ServiceType.FUND_OUT.name());
      fundOrder.setTerminalId("WEB");

      List<FundOrderAttachment> attachments = getUploadAttachments(request,
          new FundOrderAttachment());

      updateFundOrderRequest.setOrder(fundOrder);
      updateFundOrderRequest.setAttachments(attachments);

      updateFundOrderResponseType = walletEndpoint.updateFundOrder(updateFundOrderRequest);
      if (updateFundOrderResponseType == null || updateFundOrderResponseType.getStatus() == null) {
        throw new Exception("No response!");
      } else if (updateFundOrderResponseType.getStatus().getCode() != 0) {
        throw new Exception(updateFundOrderResponseType.getStatus().getValue());
      }

      return "redirect:/fundout/" + fundOutMethod + "/management";
    } catch (Exception e) {

      return handleError(e, map, fundOutMethod);
    }
  }

  @GetMapping(value = "/{fund_out_method}/create")
  public String requestDetailCreate(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_out_method") String fundOutMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundOutMethod) && !BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
      return "/about/404";
    }

    try {
      map.put("fund_out_method", fundOutMethod);
      map.put("bank_code", request.getParameter("bank_code"));
      map.put("listTransactions", getRecentTransactions(fundOutMethod, "FUND_OUT"));

      return "/fundout/fundout__request";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/fundout/fundout__request";
    }
  }

  @PostMapping(value = "/{fund_out_method}/create")
  public String requestDetailCreatePost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_out_method") String fundOutMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundOutMethod) && !BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
      return "/about/404";
    }

    try {
      CreateFundOrderRequest createFundOrderRequest = new CreateFundOrderRequest();

      FundOrder fundOrder = new FundOrder();
      if (CASH_ON_HAND_PREFIX.equals(fundOutMethod)) {
        fundOrder.setOrderChannel(FundOrderChannelType.CASH_ON_HAND.code);

        fundOrder.setFee(
            NumberUtil.convertToLong(StringUtils
                .trimToEmpty(request.getParameter("feeAmount").replaceAll("[^0-9]+", ""))));
      } else if (BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
        fundOrder.setOrderChannel(FundOrderChannelType.BANK_TRANSFER.code);

        String bankCode = request.getParameter("bank_code");
        String commandCode = request.getParameter("command_code");
        fundOrder.setBankCode(bankCode);
        fundOrder.setCommandCode(commandCode);
      }
      fundOrder.setAmount(
          NumberUtil.convertToLong(
              StringUtils.trimToEmpty(request.getParameter("amount").replaceAll("[^0-9]+", ""))));
      fundOrder.setInfo(request.getParameter("remark"));
      fundOrder.setServiceType(ServiceType.FUND_OUT.name());
      List<FundOrderAttachment> attachments = getUploadAttachments(request,
          new FundOrderAttachment());

      createFundOrderRequest.setOrder(fundOrder);
      createFundOrderRequest.setAttachments(attachments);

      CreateFundOrderResponse createFundOrderResponseType = walletEndpoint
          .createFundOrderRequest(createFundOrderRequest);
      if(createFundOrderResponseType != null || createFundOrderResponseType.getStatus().getCode() ==0) {
        map.put("listTransactions", getRecentTransactions(fundOutMethod, "FUND_OUT"));

        return "redirect:/fundout/" + fundOutMethod + "/success";
      }
      else {
        String codeErr = createFundOrderResponseType.getStatus().getValue();
        map.put("codeErr", codeErr);

        return "redirect:/fundout/" + fundOutMethod + "/fail";
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "redirect:/fundout/" + fundOutMethod + "/fail";
    }
  }

  @GetMapping(value = "/{fund_out_method}/success")
  public String requestDetailSuccess(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_out_method") String fundOutMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundOutMethod) && !BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
      return "/about/404";
    }

    map.put("listTransactions", getRecentTransactions(fundOutMethod, "FUND_OUT"));

    return "/fundout/fundout_request_success";
  }

  @GetMapping(value = "/{fund_out_method}/fail")
  public String requestDetailFailed(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_out_method") String fundOutMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundOutMethod) && !BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
      return "/about/404";
    }

    map.put("listTransactions", getRecentTransactions(fundOutMethod, "FUND_OUT"));

    return "/fundout/fundout_request_Error";
  }

  @GetMapping(value = "/{fund_out_method}/create/info")
  public String requestDetailCreateInfo(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_out_method") String fundOutMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundOutMethod) && !BANK_TRANSFER_PREFIX.equals(fundOutMethod)) {
      return "/about/404";
    }

    try {
      map.put("fund_out_method", fundOutMethod);

      // get list bank
      FindBankProfileRequest findBankRequest = new FindBankProfileRequest();
      findBankRequest.setBankCode(null);
      FindBankProfileResponse baseResponseType =
          systemEndpoint.getProfileBanks(findBankRequest);
      if (baseResponseType == null || baseResponseType.getStatus() == null) {
        throw new Exception("No response!");
      } else if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }
      map.put("list_bank", baseResponseType.getBankProfiles());

      return "/fundout/fundout_request_bank_transfer_info";
    } catch (Exception e) {

      return handleError(e, map, fundOutMethod);
    }
  }

  private String handleError(Exception e, ModelMap map, String fundOutMethod) {
    LOGGER.error(e.getMessage(), e);
    String codeErr = e.getMessage();

    map.put("codeErr", codeErr);
    map.put("fund_out_method", fundOutMethod);

    return "/fundout/fundout_request_Error";
  }

}
