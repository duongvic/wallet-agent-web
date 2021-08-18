package vn.mog.ewallet.consumer.web.controller.transfer;

import static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_TRANSFER_MAX_MONEY;
import static vn.mog.ewallet.consumer.web.common.SharedConstants.WALLET_TRANSFER_MAX_MONEY;
import static vn.mog.ewallet.consumer.web.exception.MessageNotify.ERROR_CODE;
import static vn.mog.ewallet.consumer.web.exception.MessageNotify.SESSION_MESSAGE_NOTIFY;
import static vn.mog.ewallet.consumer.web.exception.MessageNotify.SUCCESS_CODE;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.FUND_TRANSFER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.WALLET_TRANSFER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.WalletTransferOrderFlowStage.WALLET_TRANSFER_STAGES;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.ADMIN_OPERATION;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.FA_MANAGER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.FINANCE;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.FINANCE_LEADER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.SALESUPPORT;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.STAFF;
import static vn.mog.ewallet.consumer.web.utils.ValidationUtil.MESAGE_ORDER_FLOW_APPROVE_PROCESS_ERROR;
import static vn.mog.ewallet.consumer.web.utils.ValidationUtil.MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS;
import static vn.mog.ewallet.consumer.web.utils.ValidationUtil.MESAGE_SUCCESS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.consumer.web.contract.AjaxResponse;
import vn.mog.ewallet.consumer.web.contract.CustomerForm;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.system.PaymentSecurityController;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.exception.MessageNotify;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans.Customer;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness.RecentnessGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness.RecentnessGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness.bean.Recentness;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetOTPConfirmRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetOTPConfirmResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.WalletTransferOrderFlowStage;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.CreateWalletTransferOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.CreateWalletTransferOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindWalletTransferOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindWalletTransferOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.GetWalletTransferOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.GetWalletTransferOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.MoneyTransferOTPGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.MoneyTransferOTPGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.MoneyTransferRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.MoneyTransferResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.UpdateWalletTransferOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.UpdateWalletTransferOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.WalletTransferOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.WalletTransferOrderAttachment;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowApproveRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowApproveResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowRejectRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowRejectResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowSubmitProcessRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowSubmitProcessResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.WalletTransferRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.WalletTransferResponse;
import vn.mog.ewallet.consumer.web.util.taglib.WalletTagLib;
import vn.mog.ewallet.consumer.web.util.tools.JsonUtil;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.framework.contract.base.BaseResponseType;

@Controller
@RequestMapping(value = "/transfer")
public class TransferController extends AbstractController {

  public static final String TRANSFER_CONTROLLER = "/transfer";
  public static final String TRANSFER_CONFIRM_ERROR =
      TRANSFER_CONTROLLER + "/confirm-error";
  public static final String REDIRECT_TRANSFER_INFO = TRANSFER_CONTROLLER + "/transfer_money";
  public static final String REDIRECT_TRANSFER_CONFIRM_OTP = TRANSFER_CONTROLLER + "/transfer_otp";
  public static final String REDIRECT_TRANSFER_SUCCESS = TRANSFER_CONTROLLER + "/transfer_success";
  public static final String REDIRECT_TRANSFER_ERROR = TRANSFER_CONTROLLER + "/transfer_error";

  public static final String TRANSFER_HISTORY_LIST = TRANSFER_CONTROLLER + "/movement-history";
  public static final String TRANSFER_REQUEST_LIST = TRANSFER_CONTROLLER + "/processing";

  public static final String TRANSFER_HISTORY_DETAIL = TRANSFER_HISTORY_LIST + "/detail";
  public static final String TRANSFER_VERIFY = TRANSFER_CONTROLLER + "/money-verify";
  public static final String FILE_PHIEU_YEU_CAU_NAP_SOF =
      "/template/BM_AN_01.Phieu_yeu_cau_Nap_SOF.docx";
  public static final String FILE_PHIEU_YEU_CAU_CHUYEN_VI =
      "/template/BM_AN_02.Phieu_yeu_cau_chuyen_vi.docx";
  public static final String PHIEU_YEU_CAU_NAP_SOF = "BM_AN_01";
  public static final String PHIEU_YEU_CAU_CHUYEN_VI = "BM_AN_02";
  private static final String REDIRECT_TRANSFER_LIST = "redirect:" + TRANSFER_REQUEST_LIST;
  private static final String RENDER_PAGE_REQUEST_TRANSFER = "/transfer/transfer_request";

  private static String MESSAGE_NOT_ID = "alert.controller.merchant-po.not-id";
  private static final Logger LOGGER = LogManager.getLogger(TransferController.class);

  private MessageNotify messageNotify;
  private int messageCode = -1;
  private String messageValue = StringUtils.EMPTY;
  private static final String ACTION_TYPE_VERIFY = "VERIFY";
  private static final String ACTION_TYPE_CONFIRM = "CONFIRM";

  private static final String TRANSACTION_ID_ATTRIBUTE = "TRANSACTION_ID";
  private static final String ERROR_CODE_ATTRIBUTE = "ERROR_CODE";
  private static final String PAYMENT_SECURITY_TYPE_ATTRIBUTE = "PAYMENT_SECURITY_TYPE";
  private static final String MONEY_TRANSFER_PAYEE_ATTRIBUTE = "MONEY_TRANSFER_PAYEE";
  private static final String AMOUNT_ATTRIBUTE = "AMOUNT";
  private static final String TRANSACTION_HISTORY_ATTRIBUTE = "TRANSACTION_HISTORY";

  //Param
  private static final String LIST_TRANSACTIONS_TRANSFER_RQP = "listTransactionsTransfer";
  private static final String PAYEE_PHONE_NUMBER_RQP = "payeePhoneNumber";
  private static final String AMOUNT_RQP = "amount";
  private static final String CODE_ERR_RQP = "codeErr";
  private static final String REGEX_NUMBER = "[^0-9]+";
  private static final String FEE_AMOUNT_RQP = "feeAmount";
  private static final String REAL_AMOUNT_RQP = "realAmount";
  private static final String PAYMENT_SECURITY_TYPE_RQP = "paymentSecurityType";
  private static final String ORDER_ID_RQP = "orderId";
  private static final String TRANSACTION_ID_RQP = "transactionId";
  private static final String SOURCE_ACCOUNT_RQP = "sourceAccount";
  private static final String TARGET_ACCOUNT_RQP = "targetAccount";
  private static final String CREATOR_NAME_RQP = "creatorName";
  private static final String ORDER_STAGE_RQP = "orderStage";
  private static final String REMARK_RQP = "remark";
  private static final String SERVICE_TYPE_RQP = "serviceType";
  private static final String ATTACHMENTS_RQP = "attachments";
  private static final String ATTACHMENT_NAME_RQP = "attachmentName";
  private static final String ACTION_RQP = "action";

  //Value
  private static final String API_TRANSACTION_FAILED_VALUE = "API transaction failed";

  @GetMapping(value = "/money")
  public String tranferMoneyInfoGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      getPhoneMoneyTransfer(map);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    map.put(LIST_TRANSACTIONS_TRANSFER_RQP, SessionUtil.getAttribute(TRANSACTION_HISTORY_ATTRIBUTE));
    map.put(PAYEE_PHONE_NUMBER_RQP, SessionUtil.getAttribute(MONEY_TRANSFER_PAYEE_ATTRIBUTE));
    map.put(AMOUNT_RQP, SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
    map.put(CODE_ERR_RQP, SessionUtil.getAttribute(ERROR_CODE_ATTRIBUTE));

    // Remove used attribute
    SessionUtil.removeAttribute(ERROR_CODE_ATTRIBUTE);
    SessionUtil.removeAttribute(MONEY_TRANSFER_PAYEE_ATTRIBUTE);
    SessionUtil.removeAttribute(AMOUNT_ATTRIBUTE);

    return REDIRECT_TRANSFER_INFO;
  }

  @PostMapping(value = "/money")
  public String tranferMoneyInfo(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      getPhoneMoneyTransfer(map);

      String payeePhoneNumber = request.getParameter(PAYEE_PHONE_NUMBER_RQP);
      SessionUtil.setAttribute(MONEY_TRANSFER_PAYEE_ATTRIBUTE, payeePhoneNumber);
      String amount = request.getParameter(AMOUNT_RQP);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      String[] paramServiceTypeIds = request.getParameterValues("paramServiceTypeIds");
      String[] paramServiceCodes = request.getParameterValues("paramServiceCodes");
      int offset = 0;
      int limit = 5;
      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }
      // Tạo request & set params vào request
      FindTransactionsRequest tranferRequest =
          new FindTransactionsRequest();
      tranferRequest.setTextSearch(null);
      tranferRequest.setFromDate(null);
      tranferRequest.setEndDate(null);
      tranferRequest.setOffset(offset);
      tranferRequest.setLimit(limit);

      if (paramServiceTypeIds != null && paramServiceTypeIds.length > 0
          && StringUtils.isNotEmpty(paramServiceTypeIds[0])) {
        tranferRequest.setServiceTypeIds(Arrays.asList(paramServiceTypeIds));
      }
      // else {
      // tranferRequest.setServiceTypeIds(Arrays.asList(WALLET_TRANSFER.name()));
      // }

      if (paramServiceCodes != null && paramServiceCodes.length > 0
          && StringUtils.isNotEmpty(paramServiceCodes[0])) {
        tranferRequest.setServiceCodes(Arrays.asList(paramServiceCodes));
      }
      Collection<Transaction> listTransactionsTransfer = new ArrayList<>();

      FindTransactionsResponse baseTransResponseType = transactionEndpoint.transactionFind(tranferRequest);
      if (baseTransResponseType == null || baseTransResponseType.getStatus() == null
          || baseTransResponseType.getStatus().getCode() != 0) {
        String errorMsg = baseTransResponseType.getStatus() != null
            ? baseTransResponseType.getStatus().getValue() : API_TRANSACTION_FAILED_VALUE;
        LOGGER.error(errorMsg);
        throw new Exception(errorMsg);
      } else {
        if (baseTransResponseType != null) {
          listTransactionsTransfer = baseTransResponseType.getTransactions();
          SessionUtil.setAttribute(TRANSACTION_HISTORY_ATTRIBUTE, listTransactionsTransfer);
        }
      }

      map.put(LIST_TRANSACTIONS_TRANSFER_RQP, listTransactionsTransfer);
      map.put(PAYEE_PHONE_NUMBER_RQP, payeePhoneNumber);
      map.put(AMOUNT_RQP, amount);
    } catch (Exception e) {
      SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, e.getMessage());
      map.put(CODE_ERR_RQP, e.getMessage());

      return REDIRECT_TRANSFER_INFO;
    }

    return REDIRECT_TRANSFER_INFO;
  }

//  @GetMapping(value = "/money-verify") // verify
//  public String transferMoneyVerifyGet(HttpServletRequest request, HttpServletResponse response,
//      ModelMap map) throws Exception {
//    if (verifyStatus) {
//      map.put(PAYMENT_SECURITY_TYPE_RQP, paymentSecurityType);
//      map.put(ORDER_ID_RQP, orderId);
//      // map.put("isOtpRequired", isOtpRequired);
//      map.put(TRANSACTION_ID_RQP, transactionId);
//      map.put("payeeFullname", payeeFullname);
//      map.put(PAYEE_PHONE_NUMBER_RQP, payeePhoneNumber);
//      map.put(AMOUNT_RQP, amount);
//      map.put(FEE_AMOUNT_RQP, feeAmount);
//      map.put(REAL_AMOUNT_RQP, realAmount);
//      map.put("messagePhone", messagePhone);
//
//      return REDIRECT_TRANSFER_CONFIRM_OTP;
//    }
//
//    map.put(PAYMENT_SECURITY_TYPE_RQP, paymentSecurityType);
//    map.put(ORDER_ID_RQP, orderId);
//    // map.put("isOtpRequired", isOtpRequired);
//    map.put(TRANSACTION_ID_RQP, transactionId);
//    map.put(PAYEE_PHONE_NUMBER_RQP, payeePhoneNumber);
//    map.put(AMOUNT_RQP, amount);
//    map.put(FEE_AMOUNT_RQP, feeAmount);
//    map.put(REAL_AMOUNT_RQP, realAmount);
//    map.put(LIST_TRANSACTIONS_TRANSFER_RQP, listTransactionsTransfer);
//    map.put("listPhoneMoneyTransfer", listPhoneMoneyTransfer);
//    map.put(CODE_ERR_RQP, codeErr);
//
//    return "/transfer/transfer_money";
//  }

  @PostMapping(value = "/money-verify") // verify
  public String transferMoneyVerify(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    String orderId = StringUtils.EMPTY;
    String transactionId = StringUtils.EMPTY;
    String paymentSecurityType = StringUtils.EMPTY;
    String payeePhoneNumber = StringUtils.EMPTY;
    String amount = StringUtils.EMPTY;
    String feeAmount = StringUtils.EMPTY;
    String realAmount = StringUtils.EMPTY;
    try {
      payeePhoneNumber = request.getParameter(PAYEE_PHONE_NUMBER_RQP);
      SessionUtil.setAttribute(MONEY_TRANSFER_PAYEE_ATTRIBUTE, payeePhoneNumber);
      if (StringUtils.isNotBlank(payeePhoneNumber)) {
        WalletTagLib.validatePhone(payeePhoneNumber);
      }

      amount = request.getParameter(AMOUNT_RQP);
      amount = amount.replaceAll(REGEX_NUMBER, "");
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);

      feeAmount = request.getParameter(FEE_AMOUNT_RQP);
      realAmount = request.getParameter(REAL_AMOUNT_RQP);

      realAmount = realAmount.replaceAll(REGEX_NUMBER, "");
//      String serviceType = request.getParameter(SERVICE_TYPE_RQP);

      MoneyTransferRequest moneyTranRequest = new MoneyTransferRequest();

      // Khởi tạo dữ liệu
      vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.bean.WalletTransferOrder order =
          new vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.bean.WalletTransferOrder();
      order.setServiceType(WALLET_TRANSFER.name());
      order.setPayeePhoneNumber(payeePhoneNumber);
      order.setTerminalId("API");
      order.setInfo("Chuyen tien vi sang vi");
      order.setAmount(Long.valueOf(amount));
      // end Khởi tạo dữ liệu

      // set dữ liệu vào request
      moneyTranRequest.setOrder(order);
      moneyTranRequest.setActionType(ACTION_TYPE_VERIFY);
      // end
      MoneyTransferResponse moneyTranResponseType = walletEndpoint.moneyTransferVerify(moneyTranRequest);
      if (moneyTranResponseType == null || moneyTranResponseType.getStatus() == null
          || moneyTranResponseType.getStatus().getCode() != 0) {
        String errorMsg = moneyTranResponseType.getStatus() != null
            ? moneyTranResponseType.getStatus().getValue() : API_TRANSACTION_FAILED_VALUE;
        throw new Exception(errorMsg);
      } else {
        Boolean isPayeeActive = moneyTranResponseType.getIsPayeeActive();
        if (isPayeeActive == null || !isPayeeActive) {
          throw new Exception("Payee account is inactive!");
        }

        orderId = moneyTranResponseType.getOrderId().toString();
        // isOtpRequired = moneyTranResponse.getIsOtpRequired();
        String payeeFullname = moneyTranResponseType.getPayeeFullName();
        if (moneyTranResponseType.getTransactionId() == null) {
          transactionId = null;
        } else {
          transactionId = moneyTranResponseType.getTransactionId().toString();
          SessionUtil.setAttribute(TRANSACTION_ID_ATTRIBUTE, transactionId);
        }
        paymentSecurityType = moneyTranResponseType.getPaymentSecurityType();
        SessionUtil.setAttribute(PAYMENT_SECURITY_TYPE_ATTRIBUTE, paymentSecurityType);

        try {
          map.put("paymentPinSetup", PaymentSecurityController.checkPaymentPIN(walletEndpoint));
        } catch (Exception e) {
          LOGGER.error(e.getMessage(), e);
          SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, e.getMessage());
          map.put(CODE_ERR_RQP, e.getMessage());
        }
        map.put(PAYMENT_SECURITY_TYPE_RQP, paymentSecurityType);
        map.put(ORDER_ID_RQP, orderId);
        // map.put("isOtpRequired", isOtpRequired);
        map.put(TRANSACTION_ID_RQP, transactionId);
        map.put("payeeFullname", payeeFullname);
        map.put(PAYEE_PHONE_NUMBER_RQP, payeePhoneNumber);
        map.put(AMOUNT_RQP, amount);
        map.put(FEE_AMOUNT_RQP, feeAmount);
        map.put(REAL_AMOUNT_RQP, realAmount);

        return REDIRECT_TRANSFER_CONFIRM_OTP;
      }
    } catch (Exception e) {
      SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, e.getMessage());

      try {
        getPhoneMoneyTransfer(map);
      } catch (Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
      }
      map.put(PAYMENT_SECURITY_TYPE_RQP, paymentSecurityType);
      map.put(ORDER_ID_RQP, orderId);
      // map.put("isOtpRequired", isOtpRequired);
      map.put(TRANSACTION_ID_RQP, transactionId);
      map.put(PAYEE_PHONE_NUMBER_RQP, payeePhoneNumber);
      map.put(AMOUNT_RQP, amount);
      map.put(FEE_AMOUNT_RQP, feeAmount);
      map.put(REAL_AMOUNT_RQP, realAmount);
      map.put(LIST_TRANSACTIONS_TRANSFER_RQP, SessionUtil.getAttribute(TRANSACTION_HISTORY_ATTRIBUTE));
      map.put(CODE_ERR_RQP, e.getMessage());
    }

    return "redirect:/transfer/money";
  }

//  @GetMapping(value = "/money-confirm")
//  public String transferMoneyConfirmGet(HttpServletRequest request, HttpServletResponse response,
//      ModelMap map) throws Exception {
//    if (confirmStatus) {
//      return REDIRECT_TRANSFER_SUCCESS;
//    }
//
//    map.put(CODE_ERR_RQP, codeErr);
//    map.put(ORDER_ID_RQP, orderId);
//    map.put(PAYMENT_SECURITY_TYPE_RQP, paymentSecurityType);
//    // map.put("isOtpRequired", isOtpRequired);
//    map.put(TRANSACTION_ID_RQP, transactionId);
//    map.put(PAYEE_PHONE_NUMBER_RQP, payeePhoneNumber);
//    map.put(AMOUNT_RQP, amount);
//    map.put(FEE_AMOUNT_RQP, feeAmount);
//    map.put(REAL_AMOUNT_RQP, realAmount);
//
//    return REDIRECT_TRANSFER_ERROR;
//  }

  @PostMapping(value = "/money-confirm")
  public String transferMoneyConfirm(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    String orderId = StringUtils.EMPTY;
    String paymentSecurityType = StringUtils.EMPTY;
    String payeePhoneNumber = StringUtils.EMPTY;
    String amount = StringUtils.EMPTY;
    String feeAmount = StringUtils.EMPTY;
    String realAmount = StringUtils.EMPTY;
    try {
      String codeErr = request.getParameter(CODE_ERR_RQP);
      SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, codeErr);
      payeePhoneNumber = request.getParameter(PAYEE_PHONE_NUMBER_RQP);
      amount = request.getParameter(AMOUNT_RQP).replaceAll(REGEX_NUMBER, "");
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      feeAmount = request.getParameter(FEE_AMOUNT_RQP).replaceAll(REGEX_NUMBER, "");
      realAmount = request.getParameter(REAL_AMOUNT_RQP).replaceAll(REGEX_NUMBER, "");
      orderId = request.getParameter(ORDER_ID_RQP);
      String codeOTP = request.getParameter("codeOTP");

      MoneyTransferRequest moneyTranRequest = new MoneyTransferRequest();
      moneyTranRequest.setActionType(ACTION_TYPE_CONFIRM);
      moneyTranRequest.setOrderId(Long.valueOf(orderId));
      if (SessionUtil.getAttribute(PAYMENT_SECURITY_TYPE_ATTRIBUTE) != null) {
        paymentSecurityType = SessionUtil.getAttribute(PAYMENT_SECURITY_TYPE_ATTRIBUTE).toString();
      }
      if (paymentSecurityType.equals("OTP")) {
        moneyTranRequest.setOtp(codeOTP);
      }
//      duongdp 12/02.2019
      else if (paymentSecurityType.equals("PAYMENT_PASSWORD")) {
        moneyTranRequest.setOtp(codeOTP);
      }
//      duongdp 12/02.2019
      else {
        if (PaymentSecurityController.checkPaymentPIN(walletEndpoint)) {
          moneyTranRequest.setOtp(codeOTP);
        }
      }
      MoneyTransferResponse moneyTranResponseType = walletEndpoint.moneyTransferConfirm(moneyTranRequest);

      if (moneyTranResponseType == null || moneyTranResponseType.getStatus() == null
          || moneyTranResponseType.getStatus().getCode() != 0) {
        codeErr = moneyTranResponseType.getStatus().getValue() != null
            ? moneyTranResponseType.getStatus().getValue() : API_TRANSACTION_FAILED_VALUE;
        throw new Exception(codeErr);
      } else {

        return "redirect:/transfer/confirm-success";
      }
    } catch (Exception e) {
      SessionUtil.setAttribute(ERROR_CODE_ATTRIBUTE, e.getMessage());
      map.put(CODE_ERR_RQP, e.getMessage());
      map.put(ORDER_ID_RQP, orderId);
      // map.put("isOtpRequired", isOtpRequired);
      map.put(TRANSACTION_ID_RQP, SessionUtil.getAttribute(TRANSACTION_ID_ATTRIBUTE));
      map.put(PAYMENT_SECURITY_TYPE_RQP, paymentSecurityType);
      map.put(PAYEE_PHONE_NUMBER_RQP, payeePhoneNumber);
      map.put(AMOUNT_RQP, amount);
      map.put(FEE_AMOUNT_RQP, feeAmount);
      map.put(REAL_AMOUNT_RQP, realAmount);

      return "redirect:/transfer/confirm-error";
    }
  }

  private void getPhoneMoneyTransfer(ModelMap map) {
    try {
      // Tạo request & set params vào request
      RecentnessGetRequest rq = new RecentnessGetRequest();
      rq.setOffset(0);
      rq.setLimit(3);
      rq.setRecentnessKey("MONEY_TRANSFER");

      // --

      List<Recentness> listPhoneMoneyTransfer = new ArrayList<>();
      RecentnessGetResponse baseResType = walletEndpoint.recentnessGet(rq);
      if (baseResType == null || baseResType.getStatus() == null
          || baseResType.getStatus().getCode() != 0) {
        LOGGER.error(" API transactionFind fail");
      } else {
        if (baseResType != null) {
          listPhoneMoneyTransfer = baseResType.getRecentnesses();
        }
      }

      map.put("listPhoneMoneyTransfer", listPhoneMoneyTransfer);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

  @GetMapping(value = "/confirm-error")
  public String transactionError(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    map.put(CODE_ERR_RQP, SessionUtil.getAttribute(ERROR_CODE_ATTRIBUTE));
    SessionUtil.removeAttribute(ERROR_CODE_ATTRIBUTE);

    return REDIRECT_TRANSFER_ERROR;
  }

  @GetMapping(value = "/confirm-success")
  public String transactionSuccess(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    return REDIRECT_TRANSFER_SUCCESS;
  }

  @RequestMapping(value = "/movement-history", method = RequestMethod.GET)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + STAFF + "')")
  public String movementHistory(HttpServletRequest request, ModelMap map) throws FrontEndException {

    int offset = 0;
    int limit = 5;
    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    // Tạo request & set params vào request
    FindTransactionsRequest tranferRequest =
        new FindTransactionsRequest();
    tranferRequest.setFromDate(null);
    tranferRequest.setEndDate(null);
    tranferRequest.setTextSearch(null);
    tranferRequest.setOffset(offset);
    tranferRequest.setLimit(limit);

    // if (paramTxnStatusIds != null && paramTxnStatusIds.length > 0 &&
    // StringUtils.isNotEmpty(paramTxnStatusIds[0])) {
    // tranferRequest.setStatusIds(NumberUtil.convertListToInt(paramTxnStatusIds));
    // }
    //
    // if (paramTransferTypeIds != null && paramTransferTypeIds.length > 0
    // && StringUtils.isNotEmpty(paramTransferTypeIds[0])) {
    // tranferRequest.setServiceCodes(Arrays.asList(paramTransferTypeIds));
    // } else {
    // tranferRequest.setServiceCodes(Arrays.asList(WALLET_TRANSFER.name()));
    // }
    //
    // if (paramSourceMerchant != null && paramSourceMerchant.length > 0
    // && StringUtils.isNotEmpty(paramSourceMerchant[0])) {
    // ftRequest.setPayerIds(NumberUtil.convertListToLong(paramSourceMerchant));
    // }
    //
    // if (targetMerchant != null && targetMerchant.length > 0 &&
    // StringUtils.isNotEmpty(targetMerchant[0])) {
    // tranferRequest.setPayeeIds(NumberUtil.convertListToLong(targetMerchant));
    // }

    // FindTransactionsResponse ftRespone = transactionEndpoint.findTransactions(tranferRabequest);
    //
    // map.put("transactions", ftRespone.getTransactions());
    // map.put("total", ftRespone.getTotalTxn().intValue());
    // map.put("offset", offset);
    // map.put("pagesize", limit);
    // map.put("amountTransaction", NumberUtil.formatNumber(ftRespone.getTotalNetAmount()));
    //
    // map.put("txnStatuses", TxnStatus.TRANSACTION_STATUSES);
    //
    // List<Customer> sourceMerchants = getCustomers(CustomerType.ID_POOL,
    // CustomerType.ID_ZOTA);
    // List<Customer> targetMerchants = getCustomers(CustomerType.ID_SOF,
    // CustomerType.ID_ZOTA);
    //
    // map.put("sourceMerchants", sourceMerchants);
    // map.put("targetMerchants", targetMerchants);
    // map.put("transferTypes", WALLET_TRANSFER_STYPES);

    return "/transfer/movement_history";
  }

  @RequestMapping(value = "/movement-history/detail", method = RequestMethod.GET)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + STAFF + "')")
  public String movementHistoryDetail(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long txnId = NumberUtil.convertToLong(request.getParameter("txnId"));
    GetTransactionRequest gtRequest = new GetTransactionRequest(txnId);
    GetTransactionResponse gtResponse = transactionEndpoint.getTransaction(gtRequest);

    model.put("transaction", gtResponse.getTransaction());
    model.put("transactionEvents", gtResponse.getTransactionEvents());

    return "/transfer/movement_history_detail";
  }

  @RequestMapping(value = "/processing", method = RequestMethod.GET)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + STAFF + "')")
  public String process(HttpServletRequest request, ModelMap map) throws FrontEndException {

    int offset = 0;
    int limit = 20;
    String quickSearch = StringUtils.trimToEmpty(request.getParameter("search"));
    String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String[] paramPayerId = request.getParameterValues(SOURCE_ACCOUNT_RQP);
    String[] paramPayeeId = request.getParameterValues(TARGET_ACCOUNT_RQP);
    String[] paramTransferTypeIds = request.getParameterValues("transferTypeIds");

    String[] status = request.getParameterValues("procces");

    FindWalletTransferOrderRequest fwtRequest = new FindWalletTransferOrderRequest();
    if (StringUtils.isNotBlank(paramDateRange)) {
      Date[] dates = parseDateRange(paramDateRange);
      fwtRequest.setFromDate(dates[0]);
      fwtRequest.setEndDate(dates[1]);
    }

    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    if (paramPayerId != null && paramPayerId.length > 0
        && StringUtils.isNotEmpty(paramPayerId[0])) {
      fwtRequest.setPayerIds(NumberUtil.convertListToLong(paramPayerId));
    }

    if (paramPayeeId != null && paramPayeeId.length > 0
        && StringUtils.isNotEmpty(paramPayeeId[0])) {
      fwtRequest.setPayeeIds(NumberUtil.convertListToLong(paramPayeeId));
    }

    if (status != null && status.length > 0 && StringUtils.isNotEmpty(status[0])) {
      fwtRequest.setStages(NumberUtil.convertListToInt(status));
    }

    if (paramTransferTypeIds != null && paramTransferTypeIds.length > 0
        && StringUtils.isNotEmpty(paramTransferTypeIds[0])) {
      fwtRequest.setServiceTypes(Arrays.asList(paramTransferTypeIds));
    }

    fwtRequest.setSearchText(quickSearch);
    fwtRequest.setLimit(limit);
    fwtRequest.setOffset(offset);

    FindWalletTransferOrderResponse fwtResponse =
        walletEndpoint.findWalletTransferOrder(fwtRequest);

    map.put("walletTransferOrders", fwtResponse.getOrders());
    map.put("total", fwtResponse.getTotal().intValue());
    map.put("totalAmount", fwtResponse.getTotalAmount());
    map.put("pagesize", limit);
    map.put("offset", offset);

    map.put("transferTypes", WALLET_TRANSFER_STYPES);
    map.put("walletTransferStages", WALLET_TRANSFER_STAGES);

    List<Customer> sourceMerchants = getCustomers(CustomerType.ID_POOL, CustomerType.ID_ZOTA);
    List<Customer> targetMerchants = getCustomers(CustomerType.ID_SOF, CustomerType.ID_ZOTA);

    map.put("sourceMerchants", sourceMerchants);
    map.put("targetMerchants", targetMerchants);

    return "/transfer/wallet_process";
  }

  @RequestMapping(value = "/processing/{orderId}", method = RequestMethod.GET)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + STAFF + "')")
  public String processDetail(HttpServletRequest request, @PathVariable Long orderId,
      ModelMap model) throws FrontEndException {
    if (orderId > 0) {

      GetWalletTransferOrderRequest gwtRequest = new GetWalletTransferOrderRequest(orderId);
      GetWalletTransferOrderResponse gwtResponse =
          walletEndpoint.getWalletTransferOrder(gwtRequest);

      WalletTransferOrder walletOrder = gwtResponse.getOrder();

      model.put(ORDER_ID_RQP, walletOrder.getId());
      model.put(ORDER_STAGE_RQP, WALLET_TRANSFER_STAGES.get(walletOrder.getStage()));
      model.put(SERVICE_TYPE_RQP, walletOrder.getServiceType());
      model.put(AMOUNT_RQP, (walletOrder.getAmount()));
      model.put(REMARK_RQP, walletOrder.getInfo());
      List<WalletTransferOrderAttachment> attachments = gwtResponse.getAttachments();
      if (attachments != null && !attachments.isEmpty()) {
        model.put(ATTACHMENTS_RQP, attachments);
        model.put(ATTACHMENT_NAME_RQP, attachments.get(0).getName());
      }
      model.put(SOURCE_ACCOUNT_RQP, walletOrder.getPayerUsername());
      model.put(TARGET_ACCOUNT_RQP, walletOrder.getPayeeUsername());
      model.put(CREATOR_NAME_RQP, walletOrder.getCreatorUsername());

      return "/transfer/wallet_order_detail";
    }
    return REDIRECT_TRANSFER_LIST;
  }

  @RequestMapping(value = "/request-transfer", method = RequestMethod.GET) // mapping link jsp
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + SALESUPPORT + "')")
  public String requestTransfer(HttpServletRequest request, ModelMap model)
      throws FrontEndException {
    // transfer oder , transfer oder attachment
    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    if (orderId > 0) {

      GetWalletTransferOrderRequest gwtRequest = new GetWalletTransferOrderRequest(orderId);
      GetWalletTransferOrderResponse gwtResponse =
          walletEndpoint.getWalletTransferOrder(gwtRequest);

      // UserLogin userLogin = (UserLogin)
      // SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      WalletTransferOrder walletOrder = gwtResponse.getOrder();
      Integer stage = walletOrder.getStage();

      // TODO Xem lại check quyền của các account
      // walletOrder.getCreatorId().equals(userLogin.getCustomerId()) &&

      if (stage == WalletTransferOrderFlowStage.OPERATION_INIT
          || stage == WalletTransferOrderFlowStage.FINANCE_REJECTED) {

        model.put(ORDER_ID_RQP, walletOrder.getId());
        model.put(REMARK_RQP, walletOrder.getInfo());
        model.put(ORDER_STAGE_RQP, stage);
        List<WalletTransferOrderAttachment> attachments = gwtResponse.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
          model.put(ATTACHMENTS_RQP, attachments);
          model.put(ATTACHMENT_NAME_RQP, attachments.get(0).getName());
        }
      }
    }

    return RENDER_PAGE_REQUEST_TRANSFER;
  }

  @RequestMapping(value = "/request-transfer", method = RequestMethod.POST)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + SALESUPPORT + "')")
  public String requestTransferPost(HttpServletRequest request, ModelMap model) throws Exception {

    String action = request.getParameter(ACTION_RQP);
    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    Integer orderStage = NumberUtil.convertToInt(request.getParameter(ORDER_STAGE_RQP));
    String remark = request.getParameter(REMARK_RQP);
    remark = (remark != null) ? remark.trim() : StringUtils.EMPTY;

    // TODO validate data
    validation.requestSubmitTransfer();

    if (BTN_SAVE.equals(action) || BTN_SUBMIT.equals(action)) {
      if (orderId > 0) {

        WalletTransferOrder walletOrder = new WalletTransferOrder(orderId, remark);
        List<WalletTransferOrderAttachment> attachments =
            getUploadAttachments(request, new WalletTransferOrderAttachment());

        UpdateWalletTransferOrderRequest uwtRequest =
            new UpdateWalletTransferOrderRequest(walletOrder, attachments);
        UpdateWalletTransferOrderResponse uwtResponse =
            walletEndpoint.updateWalletTransferOrder(uwtRequest);

        if (uwtResponse.getStatus().getCode() == SUCCESS_CODE && BTN_SUBMIT.equals(action)) {

          if (orderStage == WalletTransferOrderFlowStage.FINANCE_REJECTED) {
            OrderFlowSubmitProcessRequest ofspRequest =
                new OrderFlowSubmitProcessRequest(orderId, remark);
            OrderFlowSubmitProcessResponse ofspResponse =
                walletEndpoint.orderFlowSubmitProccess(ofspRequest);
            messageCode = ofspResponse.getStatus().getCode();
            messageValue = ofspResponse.getStatus().getValue();

          } else if (orderStage == WalletTransferOrderFlowStage.OPERATION_INIT) {
            OrderFlowApproveRequest ofaRequest = new OrderFlowApproveRequest(orderId, remark);
            OrderFlowApproveResponse ofaResponse =
                walletEndpoint.approveWalletTransferOrder(ofaRequest);
            messageCode = ofaResponse.getStatus().getCode();
            messageValue = ofaResponse.getStatus().getValue();
          } else {
            messageCode = -1;

          }

          if (messageCode == SUCCESS_CODE) {
            messageNotify =
                new MessageNotify(SUCCESS_CODE, MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
          } else {
            messageNotify = new MessageNotify(ERROR_CODE, messageValue);
          }
        } else if (uwtResponse.getStatus().getCode() == SUCCESS_CODE) {
          messageNotify = new MessageNotify(SUCCESS_CODE, MESAGE_SUCCESS);

        } else if (uwtResponse.getStatus().getCode() == ERROR_CODE) {
          rendPageRequestTransfer(model, orderId, orderStage, remark,
              uwtResponse.getStatus().getValue());
          return RENDER_PAGE_REQUEST_TRANSFER;
        }

      } else {

        WalletTransferOrder walletOrder = new WalletTransferOrder(remark);
        List<WalletTransferOrderAttachment> attachments =
            getUploadAttachments(request, new WalletTransferOrderAttachment());

        // CreateWalletTransferOrderRequest cwtRequest = new
        // CreateWalletTransferOrderRequest(walletOrder, attachments);
        CreateWalletTransferOrderRequest cwtRequest = new CreateWalletTransferOrderRequest();
        CreateWalletTransferOrderResponse cwtResponse =
            walletEndpoint.createWalletTransferOrder(cwtRequest);

        if (cwtResponse.getStatus().getCode() == SUCCESS_CODE && BTN_SUBMIT.equals(action)) {

          OrderFlowApproveRequest ofaRequest =
              new OrderFlowApproveRequest(cwtResponse.getOrderId(), remark);
          OrderFlowApproveResponse ofaResponse =
              walletEndpoint.approveWalletTransferOrder(ofaRequest);

          if (ofaResponse.getStatus().getCode() == SUCCESS_CODE) {
            messageNotify =
                new MessageNotify(SUCCESS_CODE, MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
          } else {
            messageNotify = new MessageNotify(ERROR_CODE, ofaResponse.getStatus().getValue());
          }
        } else if (cwtResponse.getStatus().getCode() == SUCCESS_CODE) {
          messageNotify = new MessageNotify(SUCCESS_CODE, MESAGE_SUCCESS);
        } else {
          rendPageRequestTransfer(model, orderId, orderStage, remark,
              cwtResponse.getStatus().getValue());
          return RENDER_PAGE_REQUEST_TRANSFER;
        }
      }
    }
    SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
    return REDIRECT_TRANSFER_LIST;
  }

  @RequestMapping(value = "/order-initiate", method = RequestMethod.GET)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE + "')")
  public String orderInitiate(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));

    if (orderId > 0) {
      GetWalletTransferOrderRequest gwtRequest = new GetWalletTransferOrderRequest(orderId);
      GetWalletTransferOrderResponse gwtResponse =
          walletEndpoint.getWalletTransferOrder(gwtRequest);
      WalletTransferOrder walletOrder = gwtResponse.getOrder();

      if (walletOrder.getStage() == WalletTransferOrderFlowStage.FINANCE_READY_TO_VERIFIED
          || walletOrder.getStage() == WalletTransferOrderFlowStage.FINANCE_LEADER_REJECTED) {

        String serviceType = walletOrder.getServiceType();

        model.put(ORDER_ID_RQP, orderId);
        model.put(ORDER_STAGE_RQP, walletOrder.getStage());
        model.put(AMOUNT_RQP, NumberUtil.formatNumberComma(walletOrder.getAmount()));
        model.put(REMARK_RQP, walletOrder.getInfo());
        model.put(SERVICE_TYPE_RQP, serviceType);
        model.put("payerId", walletOrder.getPayerId());
        model.put("payeeId", walletOrder.getPayeeId());

        List<WalletTransferOrderAttachment> attachments = gwtResponse.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
          model.put(ATTACHMENTS_RQP, attachments);
          model.put(ATTACHMENT_NAME_RQP, attachments.get(0).getName());
        }

        if (ServiceType.FUND_TRANSFER.name().equals(serviceType)
            || serviceType.equals(StringUtils.EMPTY)) {

          model.put(SOURCE_ACCOUNT_RQP, getCustomers(CustomerType.ID_POOL));
          model.put(TARGET_ACCOUNT_RQP, getCustomers(CustomerType.ID_SOF));

        } else if (ServiceType.WALLET_TRANSFER.name().equals(serviceType)) {

          List<Customer> customers = getCustomers(CustomerType.ID_ZOTA);
          model.put(SOURCE_ACCOUNT_RQP, customers);
          model.put(TARGET_ACCOUNT_RQP, customers);
        }

        return "/transfer/transfer_initiate";
      }
    }

    return REDIRECT_TRANSFER_LIST;
  }

  @RequestMapping(value = "/order-initiate", method = RequestMethod.POST)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE + "')")
  public String orderInitiatePost(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    String action = request.getParameter(ACTION_RQP);

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    int orderStage = NumberUtil.convertToInt(request.getParameter(ORDER_STAGE_RQP));
    String remark = request.getParameter(REMARK_RQP);
    remark = (remark != null) ? remark.trim() : StringUtils.EMPTY;

    if (BTN_REJECT.equals(action)) {

      OrderFlowRejectRequest ofrRequest = new OrderFlowRejectRequest(orderId, remark);
      OrderFlowRejectResponse ofrResponse = walletEndpoint.rejectWalletTransferOrder(ofrRequest);
      if (ofrResponse.getStatus().getCode() == SUCCESS_CODE) {
        messageNotify = new MessageNotify(SUCCESS_CODE, MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
      } else {
        messageNotify = new MessageNotify(ERROR_CODE, ofrResponse.getStatus().getValue());
      }

    } else if (BTN_SAVE.equals(action) || BTN_SUBMIT.equals(action)) {
      // save or submit

      String typeTransfer = request.getParameter("typeTransfer");
      Long sourceAccount = NumberUtil.convertToLong(request.getParameter(SOURCE_ACCOUNT_RQP));
      Long targetAccount = NumberUtil.convertToLong(request.getParameter(TARGET_ACCOUNT_RQP));
      String amount = StringUtils.trimToEmpty(request.getParameter(AMOUNT_RQP));
      Long amountNumber = NumberUtil.convertToLong(amount.replaceAll(",", StringUtils.EMPTY));

      String serviceType = "fund".equals(typeTransfer) ? FUND_TRANSFER.name()
          : ("wallet".equals(typeTransfer) ? WALLET_TRANSFER.name() : StringUtils.EMPTY);

      validation.validateOrderInitiate();// TODO draf

      WalletTransferOrder order = new WalletTransferOrder();
      order.setId(orderId);
      order.setInfo(remark);
      order.setServiceType(serviceType);
      order.setPayerId(sourceAccount);
      order.setPayeeId(targetAccount);
      order.setAmount(amountNumber);

      UpdateWalletTransferOrderRequest uwtRequest = new UpdateWalletTransferOrderRequest(order);
      UpdateWalletTransferOrderResponse uwtResponse =
          walletEndpoint.updateWalletTransferOrder(uwtRequest);

      if (uwtResponse.getStatus().getCode() == SUCCESS_CODE && BTN_SUBMIT.equals(action)) {

        if (orderStage == WalletTransferOrderFlowStage.FINANCE_LEADER_REJECTED) {
          OrderFlowSubmitProcessRequest ofspRequest =
              new OrderFlowSubmitProcessRequest(orderId, remark);
          OrderFlowSubmitProcessResponse ofspResponse =
              walletEndpoint.orderFlowSubmitProccess(ofspRequest);
          messageCode = ofspResponse.getStatus().getCode();
          messageValue = ofspResponse.getStatus().getValue();
        } else if (orderStage == WalletTransferOrderFlowStage.FINANCE_READY_TO_VERIFIED) {
          OrderFlowApproveRequest ofaRequest = new OrderFlowApproveRequest(orderId, remark);
          OrderFlowApproveResponse ofaResponse =
              walletEndpoint.approveWalletTransferOrder(ofaRequest);
          messageCode = ofaResponse.getStatus().getCode();
          messageValue = ofaResponse.getStatus().getValue();
        } else {
          messageCode = -1;
        }

        if (messageCode == SUCCESS_CODE) {
          messageNotify =
              new MessageNotify(SUCCESS_CODE, MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
        } else {
          messageNotify = new MessageNotify(ERROR_CODE, messageValue);
        }
      } else if (uwtResponse.getStatus().getCode() == SUCCESS_CODE) {
        messageNotify = new MessageNotify(SUCCESS_CODE, MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
      } else {
        messageNotify = new MessageNotify(ERROR_CODE, uwtResponse.getStatus().getValue());
      }
    }
    SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
    return REDIRECT_TRANSFER_LIST;
  }

  @RequestMapping(value = "/order-review", method = RequestMethod.GET)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE_LEADER + "')")
  public String orderReview(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    GetWalletTransferOrderRequest gwtRequest = new GetWalletTransferOrderRequest(orderId);
    GetWalletTransferOrderResponse gwtResponse = walletEndpoint.getWalletTransferOrder(gwtRequest);
    WalletTransferOrder walletOrder = gwtResponse.getOrder();
    if ((walletOrder.getStage() == WalletTransferOrderFlowStage.FINANCE_LEADER_READY_TO_REVIEWED
        || walletOrder.getStage() == WalletTransferOrderFlowStage.FINANCE_MANAGER_REJECTED)) {

      model.put(ORDER_ID_RQP, walletOrder.getId());
      model.put(ORDER_STAGE_RQP, walletOrder.getStage());
      model.put(SERVICE_TYPE_RQP, walletOrder.getServiceType());
      model.put(AMOUNT_RQP, (walletOrder.getAmount()));
      model.put(REMARK_RQP, walletOrder.getInfo());
      List<WalletTransferOrderAttachment> attachments = gwtResponse.getAttachments();
      if (attachments != null && !attachments.isEmpty()) {
        model.put(ATTACHMENTS_RQP, attachments);
        model.put(ATTACHMENT_NAME_RQP, attachments.get(0).getName());
      }
      model.put(SOURCE_ACCOUNT_RQP, walletOrder.getPayerUsername());
      model.put(TARGET_ACCOUNT_RQP, walletOrder.getPayeeUsername());
      model.put(CREATOR_NAME_RQP, walletOrder.getCreatorUsername());

      return "/transfer/transfer_review";
    }

    SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
    return REDIRECT_TRANSFER_LIST;
  }

  @RequestMapping(value = "/order-review", method = RequestMethod.POST)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE_LEADER + "')")
  public String orderReviewPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    int orderStage = NumberUtil.convertToInt(request.getParameter(ORDER_STAGE_RQP));
    String remark = request.getParameter(REMARK_RQP);

    String action = request.getParameter(ACTION_RQP);
    if (BTN_APPROVE.equals(action)) {

      if (orderId > 0
          && orderStage == WalletTransferOrderFlowStage.FINANCE_LEADER_READY_TO_REVIEWED) {
        OrderFlowApproveRequest ofaRequest = new OrderFlowApproveRequest(orderId, remark);
        OrderFlowApproveResponse ofaResponse =
            walletEndpoint.approveWalletTransferOrder(ofaRequest);
        messageCode = ofaResponse.getStatus().getCode();
        messageValue = ofaResponse.getStatus().getValue();
      } else if (orderId > 0
          && orderStage == WalletTransferOrderFlowStage.FINANCE_MANAGER_REJECTED) {
        OrderFlowSubmitProcessRequest ofspRequest =
            new OrderFlowSubmitProcessRequest(orderId, remark);
        OrderFlowSubmitProcessResponse ofspResponse =
            walletEndpoint.orderFlowSubmitProccess(ofspRequest);
        messageCode = ofspResponse.getStatus().getCode();
        messageValue = ofspResponse.getStatus().getValue();
      } else {
        messageCode = -1;
      }

      if (messageCode == SUCCESS_CODE) {
        messageNotify = new MessageNotify(SUCCESS_CODE, MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
      } else {
        messageNotify = new MessageNotify(ERROR_CODE, messageValue);
      }

    } else if (BTN_REJECT.equals(action)) {

      messageNotify = rejectWalletTransferProcess(orderId, remark);

    }
    SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
    return REDIRECT_TRANSFER_LIST;
  }

  @RequestMapping(value = "/order-approve", method = RequestMethod.GET)
  @PreAuthorize(
      value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FA_MANAGER + "','" + FINANCE_LEADER + "')")
  public String orderApprove(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    if (orderId > 0) {
      GetWalletTransferOrderRequest gwtRequest = new GetWalletTransferOrderRequest(orderId);
      GetWalletTransferOrderResponse gwtResponse =
          walletEndpoint.getWalletTransferOrder(gwtRequest);
      WalletTransferOrder walletOrder = gwtResponse.getOrder();

      if (walletOrder
          .getStage() == WalletTransferOrderFlowStage.FINANCE_MANAGER_READY_TO_APPROVED) {

        UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
        Set<String> roles = userLogin.getRoles();
        boolean hasFinanceLead = roles.contains(FINANCE_LEADER);
        String serviceType = walletOrder.getServiceType();
        Long amount = walletOrder.getAmount();

        if (hasFinanceLead && (serviceType.equals(ServiceType.FUND_TRANSFER.name())
            && amount < FUND_TRANSFER_MAX_MONEY
            || serviceType.equals(ServiceType.WALLET_TRANSFER.name())
            && amount < WALLET_TRANSFER_MAX_MONEY)) {
          // nothing
        } else if (hasFinanceLead && (serviceType.equals(ServiceType.FUND_TRANSFER.name())
            && amount >= FUND_TRANSFER_MAX_MONEY
            || serviceType.equals(ServiceType.WALLET_TRANSFER.name())
            && amount >= WALLET_TRANSFER_MAX_MONEY)) {
          return REDIRECT_TRANSFER_LIST;
        }

        model.put(ORDER_ID_RQP, walletOrder.getId());
        model.put(SERVICE_TYPE_RQP, serviceType);
        model.put(AMOUNT_RQP, amount);
        model.put(REMARK_RQP, walletOrder.getInfo());
        List<WalletTransferOrderAttachment> attachments = gwtResponse.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
          model.put(ATTACHMENTS_RQP, attachments);
          model.put(ATTACHMENT_NAME_RQP, attachments.get(0).getName());
        }
        model.put(SOURCE_ACCOUNT_RQP, walletOrder.getPayerUsername());
        model.put(TARGET_ACCOUNT_RQP, walletOrder.getPayeeUsername());
        model.put(CREATOR_NAME_RQP, walletOrder.getCreatorUsername());

        return "/transfer/transfer_approve";
      }

    }

    SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
    return REDIRECT_TRANSFER_LIST;
  }

  @RequestMapping(value = "/order-approve", method = RequestMethod.POST)
  @PreAuthorize(
      value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FA_MANAGER + "','" + FINANCE_LEADER + "')")
  public String orderApprovePost(HttpServletRequest request, ModelMap model) throws Exception {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    String remark = request.getParameter(REMARK_RQP);
    String action = request.getParameter(ACTION_RQP);

    if (BTN_APPROVE.equals(action)) {

      WalletTransferOrder wallOrder = new WalletTransferOrder(orderId, remark);
      UpdateWalletTransferOrderRequest uwtRequest = new UpdateWalletTransferOrderRequest(wallOrder);
      walletEndpoint.updateWalletTransferOrder(uwtRequest);

      GetOTPConfirmRequest otpRequest = new GetOTPConfirmRequest(orderId);
      GetOTPConfirmResponse otpResponse =
          walletEndpoint.getOTPConfirmWalletTransferOrder(otpRequest);

      if (otpResponse.getStatus().getCode() == SUCCESS_CODE) {
        messageNotify = new MessageNotify(SUCCESS_CODE, MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
        model.put(ORDER_ID_RQP, orderId.intValue());
        model.put("perOtp", true);
        model.put("sentPhone", "demo");
        model.put("codeOtp", "1234");
        model.put("waiting", "1234");

        return "/transfer/transfer_otp";
      } else {
        messageNotify = new MessageNotify(ERROR_CODE, otpResponse.getStatus().getValue());
      }

    } else if (BTN_REJECT.equals(action)) {

      messageNotify = rejectWalletTransferProcess(orderId, remark);

    }

    SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
    return REDIRECT_TRANSFER_LIST;
  }

  @RequestMapping(value = "/order-otp", method = RequestMethod.POST)
  // Giám sát kế toán/ Giám đốc kế toán duyệt giao dịch
  @PreAuthorize(
      value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FA_MANAGER + "','" + FINANCE_LEADER + "')")
  public String orderOTPPost(HttpServletRequest request, ModelMap model) throws Exception {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    String action = request.getParameter(ACTION_RQP);
    String otp = request.getParameter("otp");

    if (BTN_CONFIRM_OTP.equals(action) && orderId > 0 && StringUtils.isNotBlank(otp)) {

      WalletTransferRequest confirmOtpRequest = new WalletTransferRequest(orderId, otp);
      WalletTransferResponse confirmOtpResponse = walletEndpoint.orderConfirmOTP(confirmOtpRequest);

      if (confirmOtpResponse.getStatus().getCode() == SUCCESS_CODE) {
        messageNotify = new MessageNotify(SUCCESS_CODE, MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
      } else if (confirmOtpResponse.getStatus().getCode() == 2062) {// INVALID_OTP(2062, "Invalid
        // OTP!");

        GetOTPConfirmRequest otpRequest = new GetOTPConfirmRequest(orderId);
        GetOTPConfirmResponse otpResponse =
            walletEndpoint.getOTPConfirmWalletTransferOrder(otpRequest);

        if (otpResponse.getStatus().getCode() == SUCCESS_CODE) {
          model.put(MessageNotify.CODE_ERR, 1);
          model.put(MessageNotify.MES_ERR, "Invalid OTP! please re-comfirm OTP");
          model.put(ORDER_ID_RQP, orderId.intValue());
          model.put("perOtp", true);
          model.put("sentPhone", "demo");
          model.put("codeOtp", "1234");
          model.put("waiting", "1234");
          return "/transfer/transfer_otp";
        }
      } else {
        messageNotify = new MessageNotify(ERROR_CODE, confirmOtpResponse.getStatus().getValue());
      }
    } else {
      messageNotify = new MessageNotify(ERROR_CODE, MESAGE_ORDER_FLOW_APPROVE_PROCESS_ERROR);
    }
    SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
    return REDIRECT_TRANSFER_LIST;
  }

  @RequestMapping(value = "/findCustomerByTypeWallet", method = RequestMethod.GET)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE + "')")
  @ResponseBody
  public ResponseEntity<?> findCustomerByTypeWallet(HttpServletRequest request)
      throws FrontEndException {

    JSONObject jObject = new JSONObject();
    String typeWallet = request.getParameter("typeWallet");
    if ("fund".equals(typeWallet)) {

      List<Customer> sourceCustomers = getCustomers(CustomerType.ID_POOL);
      List<Customer> desCustomers = getCustomers(CustomerType.ID_SOF);
      try {
        jObject.put("sourceCustomers", JsonUtil.objectToJson(convertCustomers(sourceCustomers)));
        jObject.put("desCustomers", JsonUtil.objectToJson(convertCustomers(desCustomers)));
      } catch (JSONException e) {
        e.printStackTrace();
      }

    } else if ("wallet".equals(typeWallet)) {

      List<Customer> sourceCustomers = getCustomers(CustomerType.ID_ZOTA);
      List<Customer> desCustomers = sourceCustomers;
      try {
        jObject.put("sourceCustomers", JsonUtil.objectToJson(convertCustomers(sourceCustomers)));
        jObject.put("desCustomers", JsonUtil.objectToJson(convertCustomers(desCustomers)));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    return ResponseEntity.ok(jObject.toString());
  }


  @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
  public void getFile(@PathVariable("file_name") String fileName, HttpServletResponse response)
      throws IOException {
    // TODO ROLE

    try {
      String contentType = StringUtils.EMPTY;
      InputStream inputStream = null;
      String extensionFile = StringUtils.EMPTY;

      if (PHIEU_YEU_CAU_NAP_SOF.equals(fileName)) {
        inputStream = new ClassPathResource(FILE_PHIEU_YEU_CAU_NAP_SOF).getInputStream();
        contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        extensionFile = "docx";
      } else if (PHIEU_YEU_CAU_CHUYEN_VI.equals(fileName)) {
        inputStream = new ClassPathResource(FILE_PHIEU_YEU_CAU_CHUYEN_VI).getInputStream();
        contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        extensionFile = "docx";
      }

      response.addHeader("Content-disposition",
          "attachment;filename=" + fileName + "." + extensionFile);
      response.setContentType(contentType);

      // Copy the stream to the response's output stream.
      IOUtils.copy(inputStream, response.getOutputStream());
      response.flushBuffer();

    } catch (IOException e) {
      LOGGER.error("Error writing file to output stream. Filename was '{}'" + fileName, e);
      throw new IllegalArgumentException("IOError writing file to output stream");
    }
  }

  private List<CustomerForm> convertCustomers(List<Customer> customers) {
    try {
      List<CustomerForm> customerForms = new ArrayList<>();
      CustomerForm customerForm;
      for (Customer item : customers) {
        customerForm = new CustomerForm(item);
        customerForms.add(customerForm);
      }
      return customerForms;
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  private MessageNotify rejectWalletTransferProcess(Long orderId, String remark) {
    OrderFlowRejectRequest ofrRequest = new OrderFlowRejectRequest(orderId, remark);
    OrderFlowRejectResponse ofrResponse = walletEndpoint.rejectWalletTransferOrder(ofrRequest);

    if (ofrResponse.getStatus().getCode() == SUCCESS_CODE) {
      return new MessageNotify(SUCCESS_CODE, MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
    } else {
      return new MessageNotify(ERROR_CODE, ofrResponse.getStatus().getValue());
    }
  }

  public void rendPageRequestTransfer(ModelMap model, Long orderId, Integer orderStage,
      String remark, String messageValue) {
    model.put(ORDER_ID_RQP, orderId);
    model.put(ORDER_STAGE_RQP, orderStage);
    model.put(REMARK_RQP, remark);
    model.put(MessageNotify.CODE_ERR, ERROR_CODE);
    model.put(MessageNotify.MES_ERR, messageValue);
  }

  private List<Customer> getCustomers(Integer... customerTypeIds) {
    try {
      FindFullCustomerRequest customerRequest = new FindFullCustomerRequest();
      customerRequest.setCustomerTypes(Arrays.asList(customerTypeIds));
      return customerEndpoint.findCustomers(customerRequest).getCustomers();
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }


  @RequestMapping(value = "/resend-otp", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> resendOTP(HttpServletRequest httpRequest) throws Exception {
    Long orderId = NumberUtil.convertToLong(httpRequest.getParameter(ORDER_ID_RQP));
    if (orderId > 0) {
      MoneyTransferOTPGetRequest request = new MoneyTransferOTPGetRequest();
      request.setOrderId(orderId);
      MoneyTransferOTPGetResponse response = walletEndpoint.moneyTransferGetOTP(request);
      return ResponseEntity.ok(new AjaxResponse(response.getStatus().getCode(), response.getStatus().getValue()));
    } else {
      return ResponseEntity.ok(new AjaxResponse(1, validation.notify(MESSAGE_NOT_ID)));
    }
  }
}
