package vn.mog.ewallet.consumer.web.controller.bank;

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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.customer.CustomerController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.CustomerLinkBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.CustomerLinkBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.DeleteBankItemRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.DeleteBankItemResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.GetBankItemByCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.GetBankItemByCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.UnlinkBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.UnlinkBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.bean.CustomerBankItem;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.CustomerBankDirect;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.CreateBankAccountRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.BankAccount;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindCustomerBankDirectRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindCustomerBankDirectResponse;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

@Controller
@RequestMapping(value = "/bank")
public class BankController extends AbstractController {

  public static final String CARD_CONTROLLER = "/bank";
  public static final String REDIRECT_CARD_MANAGER = CARD_CONTROLLER + "/manage";
  public static final String CARD_CONTROLLER_INSERT = CARD_CONTROLLER + "/bank-inset";
  public static final String CARD_CONTROLLER_LINK_INFO_CONFIRM =
      CARD_CONTROLLER + "/link-bank-info-confirm";
  public static final String CARD_CONTROLLER_UNLINK = CARD_CONTROLLER + "/un-link-bank";
  public static final String REDIRECT_LINK_CARD_INSERT = CARD_CONTROLLER + "/link-bank-insert";
  public static final String BANK_CARD_DELETE = CARD_CONTROLLER + "/deleteSaveBank";

  public static final String PAGE_BANK_MA_XAC_THUC = "/bank/ma_xac_thuc";
  public static final String PAGE_BANK_LINK_BANK_NEW_VERIFY = "/bank/link_bank_new_verify";
  public static final String PAGE_BANK_THEM_THE_ATM ="/bank/them_the_atm";

  private static final String ACTION_VERIFY = "verify";
  private static final String ACTION_CONFIRM = "confirm";
  private static final String CHANNEL_WEB = "WEB";
  private static final String LINK_TYPE_CARD = "CARD";
  private static final String LINK_TYPE_ACCOUNT = "ACCOUNT";
  private static final String REDIRECT = "redirect:";

  private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

  private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

  // Session Attribute
  private static final String BANK_NAME_ATTRIBUTE = "BANK_NAME";
  private static final String VERIFY_REQUEST_ID_ATTRIBUTE = "VERIFY_REQUEST_ID";
  private static final String BANK_CODE_ATTRIBUTE = "BANK_CODE";
  private static final String WALLET_ID_ATTRIBUTE = "WALLET_ID";
  private static final String ACCOUNT_NUMBER_ATTRIBUTE = "ACCOUNT_NUMBER";
  private static final String FULL_NAME_ATTRIBUTE = "FULL_NAME";
  private static final String PHONE_NUMBER_ATTRIBUTE = "PHONE_NUMBER";
  private static final String BANK_LINK_TYPE_ATTRIBUTE = "BANK_LINK_TYPE";
  private static final String CARD_ISSUE_DATE_ATTRIBUTE = "CARD_ISSUE_DATE";
  private static final String BANK_ACCOUNT_NUMBER_ATTRIBUTE = "BANK_ACCOUNT_NUMBER";
  private static final String BANK_ACCOUNT_NAME_ATTRIBUTE = "BANK_ACCOUNT_NAME";
  private static final String SUBSCRIPTION_ID_ATTRIBUTE = "SUBSCRIPTION_ID";
  private static final String BANK_DISPLAY_NAME_ATTRIBUTE = "BANK_DISPLAY_NAME";
  private static final String SSN_ATTRIBUTE = "SSN";
  private static final String BRANCH_BANK_ATTRIBUTE = "BRANCH_BANK";
  private static final String IS_OTP_REQUIRED_ATTRIBUTE = "IS_OTP_REQUIRED";
  private static final String CODE_OTP_ATTRIBUTE = "CODE_OTP";
  private static final String ERROR_CODE_ATTRIBUTE = "ERROR_CODE";
  //  private static final String LIST_TRANSACTION_CARD_ATTRIBUTE = "LIST_TRANSACTION_CARD";
  private static final String LOAD_STATUS_ATTRIBUTE = "LOAD_STATUS";
  private static final String LIST_BANK_DIRECT_ATTRIBUTE = "LIST_BANK_DIRECT";
  private static final String CONFIRM_STATUS_ATTRIBUTE = "CONFIRM_STATUS";

  //Param
  private static final String SUBSCRIPTION_ID_RQP = "subscriptionId";
  private static final String BANK_NAME_RQP = "bankName";
  private static final String BANK_DISPLAY_NAME_RQP = "bankDisplayName";
  private static final String BANK_CODE_RQP = "bankCode";
  private static final String BANK_ACCOUNT_NUMBER_RQP = "bankAccountNumber";
  private static final String BANK_ACCOUNT_NAME_RQP = "bankAccountName";
  private static final String PHONE_NUMBER_RQP = "phoneNumber";
  private static final String WALLET_ID_RQP = "walletId";
  private static final String CARD_ISSUE_DATE_RQP = "cardIssueDate";
  private static final String SSN_RQP = "ssn";
  private static final String IS_OTP_REQUIRED_RQP = "isOtpRequired";
  private static final String CODE_OTP_RQP = "codeOtp";
  private static final String CODE_ERR_RQP = "codeErr";
  private static final String BRANCH_BANK_RQP = "branchBank";
  private static final String ACCOUNT_NUMBER_RQP = "accountNumber";
  private static final String LINK_TYPE_RQP = "linkType";
  private static final String FULL_NAME_RQP = "fullName";
  private static final String NAMEBANK_RQP = "_nameBank";
  private static final String MESSAGE_ERROR_RQP = "messageError";
  private static final String VERIFY_REQUESTID_RQP = "verifyRequestId";
  private static final String REQUEST_ID_RQP = "requestId";
  private static final String OTP_RQP = "otp";
  private static final String LIST_BANK_RQP= "listBank";

  //VALUE
  private static final String CONFIG_VALUE = "config";
  private static final String NO_RESPONSE_VALUE = "No response!";
  private static final String NO_DATA_LIST_LINK_BANK_VALUE = "No data list link bank";
  private static final String GUI_YEU_CAU_LIEN_KET_VALUE = "gui yeu cau lien ket";
//  @GetMapping(value = "/manage")
//  public String quanLyTheGet(HttpServletRequest request, HttpServletResponse response, ModelMap map)
//      throws Exception {
//    Object loadStatusAttr = SessionUtil.getAttribute(LOAD_STATUS_ATTRIBUTE);
//    boolean loadStatus = loadStatusAttr != null && (boolean) loadStatusAttr;
//    Object subscriptionId = SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE);
//    Object bankDisplayName = SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE);
//    Object bankAccountNumber = SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE);
//    Object bankAccountName = SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE);
//    Object ssn = SessionUtil.getAttribute(SSN_ATTRIBUTE);
//    Object walletId = SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE);
//    Object cardIssueDate = SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE);
//    Object listBankDirect = SessionUtil.getAttribute(LIST_BANK_DIRECT_ATTRIBUTE);
////    Object listTransactionsCard = SessionUtil.getAttribute(LIST_TRANSACTION_CARD_ATTRIBUTE);
//    if (loadStatus) {
//      map.put(SUBSCRIPTION_ID_RQP, subscriptionId);
//      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
//      map.put(BANK_DISPLAY_NAME_RQP, bankDisplayName);
//      map.put(BANK_CODE_RQP, SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
//      map.put(BANK_ACCOUNT_NUMBER_RQP, bankAccountNumber);
//      map.put(BANK_ACCOUNT_NAME_RQP, bankAccountName);
//      map.put(SSN_RQP, ssn);
//      map.put(WALLET_ID_RQP, walletId);
//      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
//      map.put("listBankDirect", listBankDirect);
////      map.put("listTransactionsCard", listTransactionsCard);
//    } else {
//      map.put(SUBSCRIPTION_ID_RQP, subscriptionId);
//      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
//      map.put(BANK_DISPLAY_NAME_RQP, bankDisplayName);
//      map.put(BANK_CODE_RQP, SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
//      map.put(BANK_ACCOUNT_NUMBER_RQP, bankAccountNumber);
//      map.put(BANK_ACCOUNT_NAME_RQP, bankAccountName);
//      map.put(SSN_RQP, ssn);
//      map.put(WALLET_ID_RQP, walletId);
//      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
//      map.put(CODE_ERR_RQP, SessionUtil.getAttribute(ERROR_CODE_ATTRIBUTE));
//    }
//
//    return "/bank/quanlythe";
//  }

  @GetMapping(value = "/manage")
  public String quanLyThe(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    //Lay thong tin saveBank
    getSaveBank(request, response, map);

    try {
      //lấy thông tin bankdirect
      String subscriptionId = request.getParameter(SUBSCRIPTION_ID_RQP);
      String bankName = request.getParameter(BANK_NAME_RQP);
      String bankDisplayName = request.getParameter(BANK_DISPLAY_NAME_RQP);
      String bankCode = request.getParameter(BANK_CODE_RQP);
      String bankAccountNumber = request.getParameter(BANK_ACCOUNT_NUMBER_RQP);
      String bankAccountName = request.getParameter(BANK_ACCOUNT_NAME_RQP);
      String phoneNumber = request.getParameter(PHONE_NUMBER_RQP);
      String walletId = request.getParameter(WALLET_ID_RQP);
      String cardIssueDate = request.getParameter(CARD_ISSUE_DATE_RQP);
      String ssn = request.getParameter(SSN_RQP);
      List<CustomerBankDirect> listBankDirect = new ArrayList<>();

      //Store to Session
      SessionUtil.setAttribute(SUBSCRIPTION_ID_ATTRIBUTE, subscriptionId);
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
      SessionUtil.setAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE, bankAccountNumber);
      SessionUtil.setAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE, bankAccountName);
      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
      SessionUtil.setAttribute(WALLET_ID_ATTRIBUTE, walletId);
      SessionUtil.setAttribute(CARD_ISSUE_DATE_ATTRIBUTE, cardIssueDate);
      SessionUtil.setAttribute(SSN_ATTRIBUTE, ssn);
      SessionUtil.setAttribute(LIST_BANK_DIRECT_ATTRIBUTE, listBankDirect);

      FindCustomerBankDirectRequest findCustomerBankDirectReq = new FindCustomerBankDirectRequest();
      FindCustomerBankDirectResponse findCustomerBankDirectBaseRespType = walletEndpoint
          .findCustomerBankDirect(findCustomerBankDirectReq);
      //--- check base response type;
      if (findCustomerBankDirectBaseRespType == null) {
        throw new Exception(NO_RESPONSE_VALUE);
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
        subscriptionId = customerBankDirect.getSubscriptionId();
        bankName = customerBankDirect.getBankName();
        bankDisplayName = customerBankDirect.getBankDisplayName();
        bankCode = customerBankDirect.getBankCode();
        bankAccountNumber = customerBankDirect.getBankAccountNumber();
        bankAccountName = customerBankDirect.getBankAccountName();
        phoneNumber = customerBankDirect.getPhone();
        walletId = customerBankDirect.getWalletId();
        cardIssueDate = customerBankDirect.getCardIssueDate();
        ssn = customerBankDirect.getSsn();
        listBankDirect = findCustomerBankDirectBaseRespType.getBankDirects();

        //Store to Session
        SessionUtil.setAttribute(SUBSCRIPTION_ID_ATTRIBUTE, subscriptionId);
        SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
        SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);
        SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
        SessionUtil.setAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE, bankAccountNumber);
        SessionUtil.setAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE, bankAccountName);
        SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
        SessionUtil.setAttribute(WALLET_ID_ATTRIBUTE, walletId);
        SessionUtil.setAttribute(CARD_ISSUE_DATE_ATTRIBUTE, cardIssueDate);
        SessionUtil.setAttribute(SSN_ATTRIBUTE, ssn);
        SessionUtil.setAttribute(LIST_BANK_DIRECT_ATTRIBUTE, listBankDirect);
      }

      map.put(SUBSCRIPTION_ID_RQP, subscriptionId);
      map.put(BANK_NAME_RQP, bankName);
      map.put(BANK_DISPLAY_NAME_RQP, bankDisplayName);
      map.put(BANK_CODE_RQP, bankCode);
      map.put(BANK_ACCOUNT_NUMBER_RQP, bankAccountNumber);
      map.put(BANK_ACCOUNT_NAME_RQP, bankAccountName);
      map.put(SSN_RQP, ssn);
      map.put(WALLET_ID_RQP, walletId);
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);

      map.put("listBankDirect", listBankDirect);
//end lấy thông tin listBankDirect

// Xử lý dữ liệu đầu vào
//      Long total = 0L;
//      Integer offset = 0;
//      Integer limit = 20;
//      Date fromDate = null;
//      Date endDate = null;
//      //---
//      if (request.getParameter("d-49520-p") != null) {
//        Integer p = Integer.parseInt(request.getParameter("d-49520-p"));
//        offset = limit * (p - 1);
//      }
//
//      String searchRange = request.getParameter("range");
//      if (StringUtils.isNotBlank(searchRange)) {
//        Date[] dates = parseDateRange(searchRange);
//        fromDate = dates[0];
//        endDate = dates[1];
//      }
//
//      // Search box
//      String quickSearch = StringUtils.trimToEmpty(request.getParameter("quickSearch"))
//          .replaceAll("\\s+", " ");
//
//      // Tạo request & set params vào request
//      FindTransactionsRequest transactionsRequest = new FindTransactionsRequest();
//
//      transactionsRequest.setOffset(offset);
//      transactionsRequest.setLimit(limit);
//
//      transactionsRequest.setTextSearch(quickSearch);
//      transactionsRequest.setFromDate(fromDate);
//      transactionsRequest.setEndDate(endDate);
//
//      // --
//
//      String[] serviceTypeId = request.getParameterValues("serviceTypeId");
//      if (serviceTypeId != null && serviceTypeId.length > 0 && !serviceTypeId[0]
//          .equals(StringUtils.EMPTY)) {
//        transactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
//      }
//      String[] serviceCode = request.getParameterValues("serviceCode");
//      if (serviceCode != null && serviceCode.length > 0 && !serviceCode[0]
//          .equals(StringUtils.EMPTY)) {
//        transactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
//      }
//
//      Collection<Transaction> listTransactionsCard;
//
//      FindTransactionsResponse baseTransResponseType = transactionEndpoint
//          .transactionFind(transactionsRequest);
//      if (baseTransResponseType == null || baseTransResponseType.getStatus().getCode() != 0) {
//        throw new Exception(
//            (baseTransResponseType != null && baseTransResponseType.getStatus() != null)
//                ? baseTransResponseType.getStatus().getValue() : "API transactionFind fail");
//      } else {
//        listTransactionsCard = baseTransResponseType.getTransactions();
//        SessionUtil.setAttribute(LIST_TRANSACTION_CARD_ATTRIBUTE, listTransactionsCard);
//      }
//
//      map.put("pagesize", limit);
//      map.put("offset", offset);
//      map.put("listTransactionsCard", listTransactionsCard);
//      map.put("total", total.intValue());
//      map.put("quickSearch", StringUtils.trimToNull(quickSearch));
//      map.put("range", StringUtils.trimToNull(searchRange));
      SessionUtil.setAttribute(LOAD_STATUS_ATTRIBUTE, true);

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();
      SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, codeErr);

      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NAME_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put(SSN_RQP, SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put(WALLET_ID_RQP, SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put(CARD_ISSUE_DATE_RQP, SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put(CODE_ERR_RQP, codeErr);

      SessionUtil.setAttribute(LOAD_STATUS_ATTRIBUTE, false);
    }

    return "/bank/quanlythe";
  }


  @GetMapping(value = "/un-link-bank") //verify
  public String infoUnLinkBank(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    boolean isOtpRequired = false;

    // Get data from Session
    Object subscriptionIdAttr = SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE);
    String subscriptionId =
        subscriptionIdAttr != null ? subscriptionIdAttr.toString() : StringUtils.EMPTY;
    Object walletIdAttr = SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE);
    String walletId = walletIdAttr != null ? walletIdAttr.toString() : StringUtils.EMPTY;
    Object cardIssueDateAttr = SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE);
    String cardIssueDate =
        cardIssueDateAttr != null ? cardIssueDateAttr.toString() : StringUtils.EMPTY;

    try {
      String requestId = UUID.randomUUID().toString();
      String requestTime = SDF.format(new Date());

      // Tạo request check unlink
      UnlinkBankRequest unlinkBankRequest = new UnlinkBankRequest();
      unlinkBankRequest.setRequestId(requestId);
      unlinkBankRequest.setRequestTime(requestTime);
      unlinkBankRequest.setAccesskey("accesskey");
      unlinkBankRequest.setSignature("signature");
      unlinkBankRequest.setSubscriptionId(subscriptionId);
      unlinkBankRequest.setWalletId(walletId);
      unlinkBankRequest.setIssueDate(cardIssueDate);
      Object bankCode = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      unlinkBankRequest.setBankCode(bankCode != null ? bankCode.toString() : StringUtils.EMPTY);
      unlinkBankRequest.setType(ACTION_VERIFY);
      unlinkBankRequest.setChannel(CHANNEL_WEB);

      UnlinkBankResponse bankResponse = walletEndpoint
          .customerUnLinkBank(unlinkBankRequest);
      if (bankResponse == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      } else if (bankResponse.getStatus().getCode() != 0) {
        throw new Exception(bankResponse.getStatus().getValue());
      } else {
        isOtpRequired = bankResponse.getIsOtp();
        SessionUtil.setAttribute(IS_OTP_REQUIRED_ATTRIBUTE, isOtpRequired);

        map.put(IS_OTP_REQUIRED_RQP, isOtpRequired);
        map.put(SUBSCRIPTION_ID_RQP, subscriptionId);
        map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
        map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
        map.put(BANK_CODE_RQP, bankCode);
        map.put(BANK_ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
        map.put(BANK_ACCOUNT_NAME_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
        map.put(SSN_RQP, SessionUtil.getAttribute(SSN_ATTRIBUTE));
        map.put(WALLET_ID_RQP, walletId);
        map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);

        return "/unlink/unLinkBank_Info";
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put(IS_OTP_REQUIRED_RQP, isOtpRequired);
      map.put(SUBSCRIPTION_ID_RQP, subscriptionId);
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NAME_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put(SSN_RQP, SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put(WALLET_ID_RQP, walletId);
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
      map.put(CODE_ERR_RQP, codeErr);

      SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, codeErr);

      return "/bank/quanlythe";
    }
  }

  @GetMapping(value = "/un-link-confirm") //unLinkConfirm
  public String unLinkConfirmGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    Object confirmStatusAttr = SessionUtil.getAttribute(CONFIRM_STATUS_ATTRIBUTE);
    boolean confirmStatus = confirmStatusAttr != null && (boolean) confirmStatusAttr;

    if (confirmStatus) {
      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NAME_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put(SSN_RQP, SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put(WALLET_ID_RQP, SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put(CARD_ISSUE_DATE_RQP, SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));

//      request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
      return REDIRECT + REDIRECT_CARD_MANAGER;

    } else {
      map.put(CODE_ERR_RQP, SessionUtil.getAttribute(ERROR_CODE_ATTRIBUTE));
      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NAME_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put(SSN_RQP, SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put(WALLET_ID_RQP, SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put(CARD_ISSUE_DATE_RQP, SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));

      return "/unlink/unLinkBank";
    }
  }

  @PostMapping(value = "/un-link-confirm") //unLinkConfirm
  public String unLinkConfirm(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    // Get data from Session
    Object subscriptionIdAttr = SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE);
    String subscriptionId =
        subscriptionIdAttr != null ? subscriptionIdAttr.toString() : StringUtils.EMPTY;
    Object walletIdAttr = SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE);
    String walletId = walletIdAttr != null ? walletIdAttr.toString() : StringUtils.EMPTY;
    Object cardIssueDateAttr = SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE);
    String cardIssueDate =
        cardIssueDateAttr != null ? cardIssueDateAttr.toString() : StringUtils.EMPTY;
    Object bankName = SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE);
    Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
    String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : StringUtils.EMPTY;
    Object bankDisplayName = SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE);
    Object bankAccountNumber = SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE);
    Object bankAccountName = SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE);
    Object ssn = SessionUtil.getAttribute(SSN_ATTRIBUTE);

    try {
      String requestId = UUID.randomUUID().toString();
      String requestTime = SDF.format(new Date());
      String codeOTP = request.getParameter("codeOTP");

// Tạo request check unlink
      UnlinkBankRequest checkLinkBankFundInRequest = new UnlinkBankRequest();
      checkLinkBankFundInRequest.setRequestId(requestId);
      checkLinkBankFundInRequest.setRequestTime(requestTime);
      checkLinkBankFundInRequest.setAccesskey("accesskey");
      checkLinkBankFundInRequest.setSignature("signature");
      checkLinkBankFundInRequest.setSubscriptionId(subscriptionId);
      checkLinkBankFundInRequest.setWalletId(walletId);
      checkLinkBankFundInRequest.setIssueDate(cardIssueDate);
      checkLinkBankFundInRequest.setBankCode(bankCode);
      checkLinkBankFundInRequest.setType(ACTION_CONFIRM);
      checkLinkBankFundInRequest.setOtp(codeOTP);
      checkLinkBankFundInRequest.setChannel(CHANNEL_WEB);

      UnlinkBankResponse checkLinkBankFundInResponseBaseRespType = walletEndpoint
          .customerUnLinkBank(checkLinkBankFundInRequest);
      if (checkLinkBankFundInResponseBaseRespType == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      } else if (checkLinkBankFundInResponseBaseRespType.getStatus().getCode() != 0) {
        throw new Exception(checkLinkBankFundInResponseBaseRespType.getStatus().getValue());
      } else {
        map.put(SUBSCRIPTION_ID_RQP, subscriptionId);
        map.put(BANK_NAME_RQP, bankName);
        map.put(BANK_DISPLAY_NAME_RQP, bankDisplayName);
        map.put(BANK_CODE_RQP, bankCode);
        map.put(BANK_ACCOUNT_NUMBER_RQP, bankAccountNumber);
        map.put(BANK_ACCOUNT_NAME_RQP, bankAccountName);
        map.put(SSN_RQP, ssn);
        map.put(WALLET_ID_RQP, walletId);
        map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);

        SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, true);

//        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return REDIRECT + REDIRECT_CARD_MANAGER;
      }
    } catch (Exception e) {
      String codeErr = e.getMessage();

      map.put(CODE_ERR_RQP, codeErr);
      map.put(SUBSCRIPTION_ID_RQP, subscriptionId);
      map.put(BANK_NAME_RQP, bankName);
      map.put(BANK_DISPLAY_NAME_RQP, bankDisplayName);
      map.put(BANK_CODE_RQP, bankCode);
      map.put(BANK_ACCOUNT_NUMBER_RQP, bankAccountNumber);
      map.put(BANK_ACCOUNT_NAME_RQP, bankAccountName);
      map.put(SSN_RQP, ssn);
      map.put(WALLET_ID_RQP, walletId);
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);

      SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, codeErr);
      SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, false);

      return "/unlink/unLinkBank";
    }
  }


  @GetMapping(value = "/deleteSaveBank")
  public String deleteSaveBank(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      String itemType = request.getParameter("itemType");
      String bankItemId = request.getParameter("bankItemId");

// Tạo request
      DeleteBankItemRequest deleteBankItemRq = new DeleteBankItemRequest();
      deleteBankItemRq.setItemType(itemType);
      deleteBankItemRq.setBankItemId(Long.valueOf(bankItemId));

      DeleteBankItemResponse baseResponseType = walletEndpoint
          .customerBankItemDelete(deleteBankItemRq);

      //--- check base response type;
      if (baseResponseType == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      }

      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }

//   getSaveBank(request,response,map);
      return REDIRECT + REDIRECT_CARD_MANAGER;

    } catch (Exception e) {
      e.printStackTrace();
//   getSaveBank(request,response,map);
      return REDIRECT + REDIRECT_CARD_MANAGER;
    }
  }

  public Boolean checkListCustomerDirect(HttpServletRequest request,
      HttpServletResponse response, ModelMap map) {
    try {
      FindCustomerBankDirectRequest findCustomerBankDirectReq = new FindCustomerBankDirectRequest();
      FindCustomerBankDirectResponse baseResponseType = walletEndpoint
          .findCustomerBankDirect(findCustomerBankDirectReq);
      //--- check base response type;
      if (baseResponseType == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      }

      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }
//      CustomerBankDirect customerBankDirect = null;
      if (baseResponseType.getStatus().getCode() == 0
          && baseResponseType.getBankDirects() != null
          && baseResponseType.getBankDirects().size() == 1) {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }


  public void getSaveBank(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
    List<CustomerBankItem> listSaveBank = new ArrayList<>();
    try {
      GetBankItemByCustomerRequest bankItemByCusRq = new GetBankItemByCustomerRequest();
      bankItemByCusRq.setItemType(null);
      bankItemByCusRq.setBankCode(null);
      bankItemByCusRq.setIsActive(true);
      GetBankItemByCustomerResponse baseResponseType = walletEndpoint
          .customerBankItemGetByCustomer(bankItemByCusRq);
      //--- check base response type;
      if (baseResponseType == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      }
      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }
//      CustomerBankDirect customerBankDirect = null;
      if (baseResponseType.getStatus().getCode() == 0
          && baseResponseType.getBankItems() != null
          && baseResponseType.getBankItems().size() >= 1) {
        listSaveBank = baseResponseType.getBankItems();
      }
      map.put("listSaveBank", listSaveBank);
    } catch (Exception e) {
      map.put("listSaveBank", listSaveBank);
      LOGGER.error(e.getMessage(), e);
    }
  }

  /*theo nghiệp vụ mới*/
  @RequestMapping(value = "/link-bank-account", method = {RequestMethod.GET, RequestMethod.POST})
  public String linkBankAccount(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String messageError = request.getParameter(MESSAGE_ERROR_RQP);
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(null);
      findBankRequest.setIsLinkBankSupport(true);

      FindBankResponse findBankResponse = systemEndpoint.findBanks(findBankRequest);

      List<Bank> listBank = null;
      if (findBankResponse.getStatus().getCode() == 0) {
        listBank = findBankResponse.getBanks();
      }
      if (listBank == null) {
        throw new Exception(NO_DATA_LIST_LINK_BANK_VALUE);
      }

      map.put(LIST_BANK_RQP, listBank);
      map.put(MESSAGE_ERROR_RQP, messageError);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();
      SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, codeErr);

      map.put(CODE_ERR_RQP, codeErr);
    }

    return "/bank/account_bank";
  }

  @RequestMapping(value = "/link-bank-insert", method = {RequestMethod.GET, RequestMethod.POST})
  // Link Bank (verify)
  public String linkCardInsert(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    // Get data from Session
    Object cardIssueDate = SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE);
    try {
//      String accountNumber = request.getParameter(ACCOUNT_NUMBER_RQP);
//      if (StringUtils.isNotBlank(accountNumber)) {
//        accountNumber = accountNumber.replaceAll("[^0-9]+", "");
//      }
//      cardIssueDate = request.getParameter(CARD_ISSUE_DATE_RQP);
//      if(cardIssueDate == "/" || StringUtils.isEmpty(cardIssueDate)){
//        cardIssueDate="";
//      }
      String codeErr = request.getParameter(CODE_ERR_RQP);

      String messageError;
      Boolean checkCustomerBankDirect = checkListCustomerDirect(request, response, map);
      if (checkCustomerBankDirect.equals(Boolean.TRUE)) {
        messageError = "Existed Link Bank! You must unlink";
        map.put(MESSAGE_ERROR_RQP, messageError);
        return REDIRECT + CARD_CONTROLLER_INSERT;
      }
      String _nameBank = request.getParameter(NAMEBANK_RQP);
//   if(_nameBank.equalsIgnoreCase("ICBVVNVX"))
//   //check bank payment online
//   checkRegisterPaymentOnline = checkBankRegisterPaymentOnline(request, response, map);

      //lấy thông tin theo nameBank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(_nameBank);
      findBankRequest.setIsLinkBankSupport(null);

      FindBankResponse findBankResponseType = systemEndpoint
          .getListBank(findBankRequest);
      if (findBankResponseType.getStatus().getCode() != 0
          || findBankResponseType.getBanks() == null
          || findBankResponseType.getBanks().size() != 1) {
        throw new Exception(NO_DATA_LIST_LINK_BANK_VALUE);
      }

      Bank bank = findBankResponseType.getBanks().get(0);
      String bankCode = bank.getBankCode();
      String bankDisplayName = bank.getDisplayName();

      // Store to Session
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, _nameBank);
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);

      map.put(NAMEBANK_RQP, _nameBank);
      map.put(BANK_CODE_RQP, bankCode);
      map.put(BANK_DISPLAY_NAME_RQP, bankDisplayName);
//      map.put(ACCOUNT_NUMBER_RQP, accountNumber);
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
      map.put(CODE_ERR_RQP, codeErr);

      return "/bank/link_item_card_insert";
    } catch (Exception e) {
      String codeErr = e.getMessage();

      map.put(NAMEBANK_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put(FULL_NAME_RQP, SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
      map.put(CODE_ERR_RQP, codeErr);

      return "/bank/link_item_card_insert";
    }
  }

  @PostMapping(value = "/link-bank-insert-confirm") // Link Bank (verify)
  public String linkBankNewVerify(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    String redirectUrl;

    try {
      String accountNumber = request.getParameter(ACCOUNT_NUMBER_RQP);
      if (StringUtils.isNotBlank(accountNumber)) {
        accountNumber = accountNumber.replaceAll("[^0-9]+", "");
      }
      //request truyen tu form
      String linkType = request.getParameter(LINK_TYPE_RQP);
      String branchBank = request.getParameter(BRANCH_BANK_RQP);
      String fullName = request.getParameter(FULL_NAME_RQP);
      String phoneNumber = request.getParameter(PHONE_NUMBER_RQP);
      String cardIssueDate = request.getParameter(CARD_ISSUE_DATE_RQP);
      if (cardIssueDate.equalsIgnoreCase("/") || StringUtils.isEmpty(cardIssueDate)) {
        cardIssueDate = "";
      }
      String ssn = request.getParameter(SSN_RQP);
//   soTaiKhoan = request.getParameter("soTaiKhoan");
//end

      // Store to Session
      SessionUtil.setAttribute(ACCOUNT_NUMBER_ATTRIBUTE, accountNumber);
      SessionUtil.setAttribute(BANK_LINK_TYPE_ATTRIBUTE, linkType);
      SessionUtil.setAttribute(BRANCH_BANK_ATTRIBUTE, branchBank);
      SessionUtil.setAttribute(FULL_NAME_ATTRIBUTE, fullName);
      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
      SessionUtil.setAttribute(CARD_ISSUE_DATE_ATTRIBUTE, cardIssueDate);
      SessionUtil.setAttribute(SSN_ATTRIBUTE, ssn);

      String requestId = UUID.randomUUID().toString();
      String requestTime = SDF.format(new Date());
      String verifyRequestId = request.getParameter(VERIFY_REQUESTID_RQP); // null
      String otp = request.getParameter(OTP_RQP); //null

      CustomerLinkBankRequest customerLinkBankRequest = new CustomerLinkBankRequest();
      customerLinkBankRequest.setRequestId(requestId);
      customerLinkBankRequest.setRequestTime(requestTime);
      customerLinkBankRequest.setAccesskey(CONFIG_VALUE);
      customerLinkBankRequest.setSignature(CONFIG_VALUE);
      customerLinkBankRequest.setSecretkey(CONFIG_VALUE);
      customerLinkBankRequest.setVerifyRequestId(null);
      Object bankCode = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      customerLinkBankRequest
          .setBankCode(bankCode != null ? bankCode.toString() : StringUtils.EMPTY);
      customerLinkBankRequest.setWalletId(phoneNumber); // wallet = phone
      customerLinkBankRequest.setAccountNumber(accountNumber);
      customerLinkBankRequest.setSsn(ssn);
      customerLinkBankRequest.setFullName(fullName);
      customerLinkBankRequest.setPhoneNumber(phoneNumber);
      customerLinkBankRequest.setLinkType(linkType);
      customerLinkBankRequest.setType(ACTION_VERIFY);
      if (StringUtils.isNotBlank(cardIssueDate)) {
        customerLinkBankRequest.setCardIssueDate(cardIssueDate);
      } else {
        customerLinkBankRequest.setCardIssueDate("null");
      }
      customerLinkBankRequest.setChannel(CHANNEL_WEB);
      customerLinkBankRequest.setDescription(phoneNumber + "gửi yêu cầu liên kết");
      customerLinkBankRequest.setOtp(null);
      CustomerLinkBankResponse baseResponseType = walletEndpoint
          .customerLinkBank(customerLinkBankRequest);
      //--- check base response type;
      if (baseResponseType == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      }
      // ---
      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }

      String _verifyRequestId = baseResponseType.getRequestId();
      boolean isOtpRequired = baseResponseType.getIsOtp();
      // Store to Session
      SessionUtil.setAttribute(VERIFY_REQUEST_ID_ATTRIBUTE, _verifyRequestId);
      SessionUtil.setAttribute(IS_OTP_REQUIRED_ATTRIBUTE, isOtpRequired);

      redirectUrl = baseResponseType.getRedirectUrl();

      map.put("redirectUrl", redirectUrl);
      map.put(IS_OTP_REQUIRED_RQP, isOtpRequired);
      map.put(NAMEBANK_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(REQUEST_ID_RQP, requestId);
      map.put(VERIFY_REQUESTID_RQP, verifyRequestId);
      map.put(BANK_CODE_RQP, bankCode);
      map.put(WALLET_ID_RQP, phoneNumber);
      map.put(ACCOUNT_NUMBER_RQP, accountNumber);
      map.put(SSN_RQP, ssn);
      map.put(FULL_NAME_RQP, fullName);
      map.put(PHONE_NUMBER_RQP, phoneNumber);
      map.put(LINK_TYPE_RQP, linkType);
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
      map.put(OTP_RQP, otp);
      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BRANCH_BANK_RQP, branchBank);
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));

      return PAGE_BANK_LINK_BANK_NEW_VERIFY;
    } catch (Exception e) {
      e.printStackTrace();
      String codeErr = e.getMessage();
      map.put(NAMEBANK_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(CODE_ERR_RQP, codeErr);

//      request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

      return REDIRECT + REDIRECT_LINK_CARD_INSERT;
    }
  }

  @PostMapping(value = "/linkBankNewConfirm") // confirm
  public String linkBankNewConfirm(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws Exception {
    checkListCustomerDirect(request, response, map);

    String phoneNumber = request.getParameter(PHONE_NUMBER_RQP);
    String codeOtp = request.getParameter(CODE_OTP_RQP);
    String requestId = UUID.randomUUID().toString();
    String requestTime = SDF.format(new Date());

    // Store to Session
    SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
    SessionUtil.setAttribute(CODE_OTP_ATTRIBUTE, codeOtp);

    // Get data from Session
    Object accountNumberAttr = SessionUtil.getAttribute(ACCOUNT_NUMBER_ATTRIBUTE);
    String accountNumber =
        accountNumberAttr != null ? accountNumberAttr.toString() : StringUtils.EMPTY;
    Object ssnAttr = SessionUtil.getAttribute(SSN_ATTRIBUTE);
    String ssn = ssnAttr != null ? ssnAttr.toString() : StringUtils.EMPTY;
    Object fullNameAttr = SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE);
    String fullName = fullNameAttr != null ? fullNameAttr.toString() : StringUtils.EMPTY;
    Object linkTypeAttr = SessionUtil.getAttribute(BANK_LINK_TYPE_ATTRIBUTE);
    String linkType = linkTypeAttr != null ? linkTypeAttr.toString() : StringUtils.EMPTY;
    Object cardIssueDateAttr = SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE);
    String cardIssueDate =
        cardIssueDateAttr != null ? cardIssueDateAttr.toString() : StringUtils.EMPTY;

    CustomerLinkBankRequest customerLinkBankRequest = new CustomerLinkBankRequest();
    customerLinkBankRequest.setRequestId(requestId);
    customerLinkBankRequest.setRequestTime(requestTime);
    customerLinkBankRequest.setAccesskey(CONFIG_VALUE);
    customerLinkBankRequest.setSignature(CONFIG_VALUE);
    customerLinkBankRequest.setSecretkey(CONFIG_VALUE);
    Object verifyRequestId = SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE);
    customerLinkBankRequest.setVerifyRequestId(
        verifyRequestId != null ? verifyRequestId.toString() : StringUtils.EMPTY);
    Object bankCode = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
    customerLinkBankRequest.setBankCode(bankCode != null ? bankCode.toString() : StringUtils.EMPTY);
    customerLinkBankRequest.setWalletId(phoneNumber);
    customerLinkBankRequest.setAccountNumber(accountNumber);
    customerLinkBankRequest.setSsn(ssn);
    customerLinkBankRequest.setFullName(fullName);
    customerLinkBankRequest.setPhoneNumber(phoneNumber);

    customerLinkBankRequest.setLinkType(linkType);
    customerLinkBankRequest.setType(ACTION_CONFIRM);
    if (StringUtils.isNotBlank(cardIssueDate)) {
      customerLinkBankRequest.setCardIssueDate(cardIssueDate);
    } else {
      customerLinkBankRequest.setCardIssueDate("null");
    }
    customerLinkBankRequest.setChannel(CHANNEL_WEB);
    customerLinkBankRequest
        .setDescription(phoneNumber + GUI_YEU_CAU_LIEN_KET_VALUE);
    if (codeOtp != null) {
      customerLinkBankRequest.setOtp(codeOtp);
    }

    CustomerLinkBankResponse baseResponseType;
    baseResponseType = walletEndpoint.customerLinkBank(customerLinkBankRequest);
    if (baseResponseType == null) {
      throw new Exception("No responsive");
    }
    if (baseResponseType.getStatus().getCode() == 0) {
      boolean isOtpRequired = baseResponseType.getIsOtp();
      SessionUtil.setAttribute(IS_OTP_REQUIRED_ATTRIBUTE, isOtpRequired);

      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, bankCode);
      map.put(BANK_ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NAME_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put(SSN_RQP, ssn);
      map.put(PHONE_NUMBER_RQP, phoneNumber);
      map.put(WALLET_ID_RQP, phoneNumber);
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
      map.put(IS_OTP_REQUIRED_RQP, isOtpRequired);
      map.put(ACCOUNT_NUMBER_RQP, accountNumber);
      map.put(CODE_OTP_RQP, codeOtp);
      map.put(FULL_NAME_RQP, fullName);

      SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, true);
//      request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
      return REDIRECT + REDIRECT_CARD_MANAGER;

    } else {
      String codeErr = baseResponseType.getStatus().getValue();
      map.put(CODE_ERR_RQP, codeErr);
      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, bankCode);
      map.put(BANK_ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NAME_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put(SSN_RQP, ssn);
      map.put(PHONE_NUMBER_RQP, phoneNumber);
      map.put(WALLET_ID_RQP, phoneNumber);
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
      map.put(IS_OTP_REQUIRED_RQP, SessionUtil.getAttribute(IS_OTP_REQUIRED_ATTRIBUTE));
      map.put(CODE_OTP_RQP, codeOtp);
      map.put(ACCOUNT_NUMBER_RQP, accountNumber);
      map.put(BRANCH_BANK_RQP, SessionUtil.getAttribute(BRANCH_BANK_ATTRIBUTE));
      map.put(FULL_NAME_RQP, fullName);

      return REDIRECT + CARD_CONTROLLER_LINK_INFO_CONFIRM;
    }
  }

  @GetMapping(value = "/link-bank-info-confirm") // Link Bank (verify)
  public String linkBankNewInfo(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws Exception {
    try {
      //request truyen tu form
      String accountNumber = request.getParameter(ACCOUNT_NUMBER_RQP);
      String branchBank = request.getParameter(BRANCH_BANK_RQP);
      String fullName = request.getParameter(FULL_NAME_RQP);
      String phoneNumber = request.getParameter(PHONE_NUMBER_RQP);
      String cardIssueDate = request.getParameter(CARD_ISSUE_DATE_RQP);
      String ssn = request.getParameter(CARD_ISSUE_DATE_RQP);
      //   soTaiKhoan = request.getParameter("soTaiKhoan");
      //end

      // Get data from Session
      Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : StringUtils.EMPTY;

      String requestId = UUID.randomUUID().toString();
      String requestTime = SDF.format(new Date());
      String verifyRequestId = request.getParameter(VERIFY_REQUESTID_RQP); // null
      String otp = request.getParameter(OTP_RQP); //null

      CustomerLinkBankRequest customerLinkBankRequest = new CustomerLinkBankRequest();
      customerLinkBankRequest.setRequestId(requestId);
      customerLinkBankRequest.setRequestTime(requestTime);
      customerLinkBankRequest.setAccesskey(CONFIG_VALUE);
      customerLinkBankRequest.setSignature(CONFIG_VALUE);
      customerLinkBankRequest.setSecretkey(CONFIG_VALUE);
      customerLinkBankRequest.setVerifyRequestId(null);
      customerLinkBankRequest.setBankCode(bankCode);
      customerLinkBankRequest.setWalletId(phoneNumber); // wallet = phone
      customerLinkBankRequest.setAccountNumber(accountNumber);
      customerLinkBankRequest.setSsn(ssn);
      customerLinkBankRequest.setFullName(fullName);
      customerLinkBankRequest.setPhoneNumber(phoneNumber);
      if (bankCode.equalsIgnoreCase("BIDVVNVX")) {
        customerLinkBankRequest.setLinkType(LINK_TYPE_ACCOUNT);
      } else {
        customerLinkBankRequest.setLinkType(LINK_TYPE_CARD);
      }
      customerLinkBankRequest.setType(ACTION_VERIFY);
      if (StringUtils.isNotBlank(cardIssueDate)) {
        customerLinkBankRequest.setCardIssueDate(cardIssueDate);
      } else {
        customerLinkBankRequest.setCardIssueDate("null");
      }
      customerLinkBankRequest.setChannel(CHANNEL_WEB);
      customerLinkBankRequest.setDescription(phoneNumber + "gửi yêu cầu liên kết");
      customerLinkBankRequest.setOtp(null);
      CustomerLinkBankResponse baseResponseType;
      baseResponseType = walletEndpoint.customerLinkBank(customerLinkBankRequest);

      //--- check base response type;
      if (baseResponseType == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      }

      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }

      String _verifyRequestId = baseResponseType.getRequestId();
      boolean isOtpRequired = baseResponseType.getIsOtp();

      // Set data to Session
      SessionUtil.setAttribute(ACCOUNT_NUMBER_ATTRIBUTE, accountNumber);
      SessionUtil.setAttribute(BRANCH_BANK_ATTRIBUTE, branchBank);
      SessionUtil.setAttribute(FULL_NAME_ATTRIBUTE, fullName);
      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
      SessionUtil.setAttribute(CARD_ISSUE_DATE_ATTRIBUTE, cardIssueDate);
      SessionUtil.setAttribute(SSN_ATTRIBUTE, ssn);
      SessionUtil.setAttribute(VERIFY_REQUEST_ID_ATTRIBUTE, _verifyRequestId);
      SessionUtil.setAttribute(IS_OTP_REQUIRED_ATTRIBUTE, isOtpRequired);

      map.put(IS_OTP_REQUIRED_RQP, isOtpRequired);
      map.put(NAMEBANK_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(REQUEST_ID_RQP, requestId);
      map.put(VERIFY_REQUESTID_RQP, verifyRequestId);

      map.put(BANK_CODE_RQP, bankCode);
      map.put(WALLET_ID_RQP, SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put(ACCOUNT_NUMBER_RQP, accountNumber);
      map.put(SSN_RQP, ssn);
      map.put(FULL_NAME_RQP, fullName);
      map.put(PHONE_NUMBER_RQP, phoneNumber);
      map.put(LINK_TYPE_RQP, SessionUtil.getAttribute(BANK_LINK_TYPE_ATTRIBUTE));
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
      map.put(OTP_RQP, otp);
      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BRANCH_BANK_RQP, branchBank);
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));

      return PAGE_BANK_LINK_BANK_NEW_VERIFY;
    } catch (Exception e) {

      String codeErr = e.getMessage();

      Object isOtpRequiredAttr = SessionUtil.getAttribute(IS_OTP_REQUIRED_ATTRIBUTE);
      boolean isOtpRequired = isOtpRequiredAttr != null && (boolean) isOtpRequiredAttr;

      map.put(IS_OTP_REQUIRED_RQP, isOtpRequired);
      map.put(NAMEBANK_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put(WALLET_ID_RQP, SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put(ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(SSN_RQP, SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put(FULL_NAME_RQP, SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE));
      map.put(PHONE_NUMBER_RQP, SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put(LINK_TYPE_RQP, SessionUtil.getAttribute(BANK_LINK_TYPE_ATTRIBUTE));
      map.put(CARD_ISSUE_DATE_RQP, SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BRANCH_BANK_RQP, SessionUtil.getAttribute(BRANCH_BANK_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(CODE_ERR_RQP, codeErr);

      SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, codeErr);

      return PAGE_BANK_LINK_BANK_NEW_VERIFY;
    }
  }

// bỏ

  @GetMapping(value = "/bank-inset")
  public String themTaiKhoanNganHang(HttpServletRequest request, ModelMap map) throws Exception {
    String messageError = request.getParameter(MESSAGE_ERROR_RQP);
    FindBankRequest findBankRequest = new FindBankRequest();
    findBankRequest.setBankCode(null);
    findBankRequest.setIsLinkBankSupport(true);

    FindBankResponse findBankResponse = systemEndpoint.getListBank(findBankRequest);
    List<Bank> listBank = new ArrayList<>();

    if (findBankResponse.getStatus().getCode() == 0
        && findBankResponse.getBanks().size() >= 1) {
      listBank = findBankResponse.getBanks();
    }

    if (listBank == null) {
      throw new Exception(NO_DATA_LIST_LINK_BANK_VALUE);
    }

    map.put(LIST_BANK_RQP, listBank);
    map.put(MESSAGE_ERROR_RQP, messageError);
    map.put(CODE_ERR_RQP, SessionUtil.getAttribute(ERROR_CODE_ATTRIBUTE));

    return "/bank/them_tai_khoan_ngan_hang";
  }

  @GetMapping(value = "/themTheVisa")
  public String themTheVisa() throws Exception {

    return "/bank/them_the_visa";
  }

  @PostMapping(value = "/themTheVisa")
  public String themTheVisaPost(HttpServletRequest request, ModelMap map) throws Exception {

//    String inputCredit = request.getParameter("inputCredit");
    String ownAccount = request.getParameter("ownAccount");
//    String releaseYear = request.getParameter("releaseYear");
//    String cvvNumber = request.getParameter("cvvNumber");

    BankAccount bankAccount = new BankAccount();
    bankAccount.setAccountHolderName(ownAccount);

    /*CreateBankAccountRequest cbaRequest = */
    new CreateBankAccountRequest(bankAccount);
//    CreateBankAccountResponse cbaResponse = systemEndpoint.createBankAccount(cbaRequest);
//    CreateBankAccountResponse cbaResponse = null;
    /*if (cbaResponse.getStatus().getCode() == SUCCESS_CODE) {

      return PAGE_BANK_MA_XAC_THUC;
    }*/

    return PAGE_BANK_MA_XAC_THUC;
  }


  @GetMapping(value = "/themTheAtm")
  public String themMotTaiKhoanNganHang(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String messageError;
      Boolean checkCustomerBankDirect = checkListCustomerDirect(request, response, map);
      if (checkCustomerBankDirect.equals(Boolean.TRUE)) {
        messageError = "Existed Link Bank! You must unlink";
        map.put(MESSAGE_ERROR_RQP, messageError);
        return REDIRECT + CARD_CONTROLLER_INSERT;
      }
      String _nameBank = request.getParameter(NAMEBANK_RQP);
      //lấy thôn tin theo nameBank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(_nameBank);
      findBankRequest.setIsLinkBankSupport(null);

      FindBankResponse findBankResponse = systemEndpoint
          .getListBank(findBankRequest);

      if (findBankResponse.getStatus().getCode() != 0
          || findBankResponse.getBanks() == null || findBankResponse.getBanks().size() != 1) {
        throw new Exception(NO_DATA_LIST_LINK_BANK_VALUE);
      }

      Bank bank = findBankResponse.getBanks().get(0);
      String bankCode = bank.getBankCode();
      String bankDisplayName = bank.getDisplayName();

      // Store to Session
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, _nameBank);
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);

      map.put(NAMEBANK_RQP, _nameBank);
      map.put(BANK_CODE_RQP, bankCode);
      map.put(BANK_DISPLAY_NAME_RQP, bankDisplayName);

      return PAGE_BANK_THEM_THE_ATM;
    } catch (Exception e) {
      e.printStackTrace();
      map.put(NAMEBANK_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));

      return PAGE_BANK_THEM_THE_ATM;
    }
  }

  @PostMapping(value = "/themTheAtm") // Link Bank (verify)
  public String themMotTaiKhoanNganHangPost(HttpServletRequest request,
      HttpServletResponse response, ModelMap map) throws Exception {
    try {
      //request truyen tu form
      String accountNumber = request.getParameter(ACCOUNT_NUMBER_RQP);
      String branchBank = request.getParameter(BRANCH_BANK_RQP); // chi nhanh ngan hang
      String fullName = request.getParameter(FULL_NAME_RQP);
      String phoneNumber = request.getParameter(PHONE_NUMBER_RQP);
      String cardIssueDate = request.getParameter(CARD_ISSUE_DATE_RQP);
      //end

      // Store to Session
      SessionUtil.setAttribute(ACCOUNT_NUMBER_ATTRIBUTE, accountNumber);
      SessionUtil.setAttribute(BRANCH_BANK_ATTRIBUTE, branchBank);
      SessionUtil.setAttribute(FULL_NAME_ATTRIBUTE, fullName);
      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
      SessionUtil.setAttribute(CARD_ISSUE_DATE_ATTRIBUTE, cardIssueDate);

      // Get data from Session
      Object bankCodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
      String bankCode = bankCodeAttr != null ? bankCodeAttr.toString() : StringUtils.EMPTY;
      Object ssnAttr = SessionUtil.getAttribute(SSN_ATTRIBUTE);
      String ssn = ssnAttr != null ? ssnAttr.toString() : StringUtils.EMPTY;

      String requestId = UUID.randomUUID().toString();
      String requestTime = SDF.format(new Date());
      String verifyRequestId = request.getParameter(VERIFY_REQUESTID_RQP); // null
      String otp = request.getParameter(OTP_RQP); //null

      CustomerLinkBankRequest customerLinkBankRequest = new CustomerLinkBankRequest();
      customerLinkBankRequest.setRequestId(requestId);
      customerLinkBankRequest.setRequestTime(requestTime);
      customerLinkBankRequest.setAccesskey(CONFIG_VALUE);
      customerLinkBankRequest.setSignature(CONFIG_VALUE);
      customerLinkBankRequest.setSecretkey(CONFIG_VALUE);
      customerLinkBankRequest.setVerifyRequestId(null);
      customerLinkBankRequest.setBankCode(bankCode);
      customerLinkBankRequest.setWalletId(phoneNumber); // wallet = phone
      customerLinkBankRequest.setAccountNumber(accountNumber);
      customerLinkBankRequest.setSsn(ssn);
      customerLinkBankRequest.setFullName(fullName);
      customerLinkBankRequest.setPhoneNumber(phoneNumber);
      customerLinkBankRequest.setLinkType(LINK_TYPE_CARD);
      customerLinkBankRequest.setType(ACTION_VERIFY);
      customerLinkBankRequest.setCardIssueDate(cardIssueDate);
      customerLinkBankRequest.setChannel(CHANNEL_WEB);
      customerLinkBankRequest.setDescription(phoneNumber + GUI_YEU_CAU_LIEN_KET_VALUE);
      customerLinkBankRequest.setOtp(null);

      CustomerLinkBankResponse baseResponseType = walletEndpoint
          .customerLinkBank(customerLinkBankRequest);

      //--- check base response type;
      if (baseResponseType == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      }

      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }

      String _verifyRequestId = baseResponseType.getRequestId();
      SessionUtil.setAttribute(VERIFY_REQUEST_ID_ATTRIBUTE, _verifyRequestId);
      map.put(NAMEBANK_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(REQUEST_ID_RQP, requestId);
      map.put(VERIFY_REQUESTID_RQP, verifyRequestId);
      map.put(BANK_CODE_RQP, bankCode);
      map.put(WALLET_ID_RQP, SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put(ACCOUNT_NUMBER_RQP, accountNumber);
      map.put(SSN_RQP, ssn);
      map.put(FULL_NAME_RQP, fullName);
      map.put(PHONE_NUMBER_RQP, phoneNumber);
      map.put(LINK_TYPE_RQP, SessionUtil.getAttribute(BANK_LINK_TYPE_ATTRIBUTE));
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
      map.put(OTP_RQP, otp);
      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BRANCH_BANK_RQP, branchBank);
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));

      return PAGE_BANK_MA_XAC_THUC;
    } catch (Exception e) {
      String codeErr = e.getMessage();

      map.put(NAMEBANK_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put(WALLET_ID_RQP, SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put(ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(SSN_RQP, SessionUtil.getAttribute(SSN_ATTRIBUTE));
      map.put(FULL_NAME_RQP, SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE));
      map.put(PHONE_NUMBER_RQP, SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put(LINK_TYPE_RQP, SessionUtil.getAttribute(BANK_LINK_TYPE_ATTRIBUTE));
      map.put(CARD_ISSUE_DATE_RQP, SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE));
      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BRANCH_BANK_RQP, SessionUtil.getAttribute(BRANCH_BANK_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(CODE_ERR_RQP, codeErr);

      SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, codeErr);

      return PAGE_BANK_THEM_THE_ATM;
    }

  }

  @PostMapping(value = "/maXacThuc") // confirm
  public String maXacThuc(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    checkListCustomerDirect(request, response, map);

    String codeOtp = request.getParameter(CODE_OTP_RQP);
    String branchBank = request.getParameter(BRANCH_BANK_RQP);

    // Set data to Session
    SessionUtil.setAttribute(CODE_OTP_ATTRIBUTE, codeOtp);
    SessionUtil.setAttribute(BRANCH_BANK_ATTRIBUTE, branchBank);

    // Get data from Session
    Object verifyRequestIdAttr = SessionUtil.getAttribute(VERIFY_REQUEST_ID_ATTRIBUTE);
    String verifyRequestId =
        verifyRequestIdAttr != null ? verifyRequestIdAttr.toString() : StringUtils.EMPTY;
    Object bankcodeAttr = SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE);
    String bankCode = bankcodeAttr != null ? bankcodeAttr.toString() : StringUtils.EMPTY;
    Object phoneNumberAttr = SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE);
    String phoneNumber = phoneNumberAttr != null ? phoneNumberAttr.toString() : StringUtils.EMPTY;
    Object accountNumberAttr = SessionUtil.getAttribute(ACCOUNT_NUMBER_ATTRIBUTE);
    String accountNumber =
        accountNumberAttr != null ? accountNumberAttr.toString() : StringUtils.EMPTY;
    Object ssnAttr = SessionUtil.getAttribute(SSN_ATTRIBUTE);
    String ssn = ssnAttr != null ? ssnAttr.toString() : StringUtils.EMPTY;
    Object fullNameAttr = SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE);
    String fullName = fullNameAttr != null ? fullNameAttr.toString() : StringUtils.EMPTY;
    Object cardIssueDateAttr = SessionUtil.getAttribute(CARD_ISSUE_DATE_ATTRIBUTE);
    String cardIssueDate =
        cardIssueDateAttr != null ? cardIssueDateAttr.toString() : StringUtils.EMPTY;

    String requestId = UUID.randomUUID().toString();
    String requestTime = SDF.format(new Date());

    CustomerLinkBankRequest customerLinkBankRequest = new CustomerLinkBankRequest();
    customerLinkBankRequest.setRequestId(requestId);
    customerLinkBankRequest.setRequestTime(requestTime);
    customerLinkBankRequest.setAccesskey(CONFIG_VALUE);
    customerLinkBankRequest.setSignature(CONFIG_VALUE);
    customerLinkBankRequest.setSecretkey(CONFIG_VALUE);
    customerLinkBankRequest.setVerifyRequestId(verifyRequestId);
    customerLinkBankRequest.setBankCode(bankCode);
    customerLinkBankRequest.setWalletId(phoneNumber);
    customerLinkBankRequest.setAccountNumber(accountNumber);
    customerLinkBankRequest.setSsn(ssn);
    customerLinkBankRequest.setFullName(fullName);
    customerLinkBankRequest.setPhoneNumber(phoneNumber);
    customerLinkBankRequest.setLinkType("CARD");
    customerLinkBankRequest.setType(ACTION_CONFIRM);
    customerLinkBankRequest.setCardIssueDate(cardIssueDate);
    customerLinkBankRequest.setChannel(CHANNEL_WEB);
    customerLinkBankRequest
        .setDescription(phoneNumber + GUI_YEU_CAU_LIEN_KET_VALUE);
    if (codeOtp != null) {
      customerLinkBankRequest.setOtp(codeOtp);
    } else {
      customerLinkBankRequest.setOtp("123456");
    }

    CustomerLinkBankResponse baseResponseType;
    baseResponseType = walletEndpoint.customerLinkBank(customerLinkBankRequest);

    if (baseResponseType.getStatus().getCode() == 0) {
      boolean isOtpRequired = baseResponseType.getIsOtp();

      SessionUtil.setAttribute(IS_OTP_REQUIRED_ATTRIBUTE, isOtpRequired);

      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, bankCode);
      map.put(BANK_ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NAME_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put(SSN_RQP, ssn);
      map.put(WALLET_ID_RQP, SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
      map.put(IS_OTP_REQUIRED_RQP, isOtpRequired);
      map.put(CODE_OTP_RQP, codeOtp);

      return REDIRECT + REDIRECT_CARD_MANAGER;
    } else {
      map.put(SUBSCRIPTION_ID_RQP, SessionUtil.getAttribute(SUBSCRIPTION_ID_ATTRIBUTE));
      map.put(BANK_NAME_RQP, SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put(BANK_DISPLAY_NAME_RQP, SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put(BANK_CODE_RQP, bankCode);
      map.put(BANK_ACCOUNT_NUMBER_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NUMBER_ATTRIBUTE));
      map.put(BANK_ACCOUNT_NAME_RQP, SessionUtil.getAttribute(BANK_ACCOUNT_NAME_ATTRIBUTE));
      map.put(SSN_RQP, ssn);
      map.put(WALLET_ID_RQP, SessionUtil.getAttribute(WALLET_ID_ATTRIBUTE));
      map.put(CARD_ISSUE_DATE_RQP, cardIssueDate);
      map.put(IS_OTP_REQUIRED_RQP, SessionUtil.getAttribute(IS_OTP_REQUIRED_ATTRIBUTE));
      map.put(CODE_OTP_RQP, codeOtp);

      return PAGE_BANK_MA_XAC_THUC;
    }
  }
}
