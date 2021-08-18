package vn.mog.ewallet.consumer.web.controller.topup;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.PHONE_TOPUP;
import static vn.mog.ewallet.consumer.web.util.tools.PhoneNumberUtil.getCardHolderByPhoneNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.consumer.web.common.SharedConstants;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.contract.AjaxResponse;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController;
import vn.mog.ewallet.consumer.web.controller.system.PaymentSecurityController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerBalanceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerGetBalanceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.TopupTransactionOTPGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.TopupTransactionOTPGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignInRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignInResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.AddressType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.TopupTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.TopupTransactionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.TransactionAttributeType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.TransactionTopup;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.PhoneNumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.framework.contract.base.BaseResponseType;

@Controller
@RequestMapping(value = "/topup")
public class TopUpController extends AbstractController {

  public static final String TOPUP_CONTROLLER = "/topup";
  public static final String TOPUP_INFO = TOPUP_CONTROLLER + "/topup_info";

  private static final Logger LOGGER = LogManager.getLogger(TopUpController.class);
  private static String MESSAGE_NOT_ID = "alert.controller.merchant-po.not-id";

  private static final String ACTION_VERIFY = "verify";
  private static final String ACTION_CONFIRM = "confirm";
  private static final String CHANNEL_WEB = "WEB";
  private static final String serviceCodeFix = "060000";

  // Session attribute
  private static final String ACCOUNT_ATTRIBUTE = "ACCOUNT";
  private static final String FACE_VALUE_ATTRIBUTE = "FACE_VALUE";
  private static final String REAL_AMOUNT_ATTRIBUTE = "REAL_AMOUNT";
  private static final String WALLET_BALANCE_ATTRIBUTE = "WALLET_BALANCE";
  private static final String REQUEST_ID_ATTRIBUTE = "REQUEST_ID";
  private static final String ORDER_ID_ATTRIBUTE = "ORDER_ID";
  private static final String PAYMENT_SECURITY_TYPE_ATTRIBUTE = "PAYMENT_SECURITY_TYPE";
  private static final String PHONE_NUMBER_ATTRIBUTE = "PHONE_NUMBER";
  private static final String PHONE_NUMBER_TOPUP_ATTRIBUTE = "PHONE_NUMBER_TOPUP";
  private static final String PRODUCT_NAME_ATTRIBUTE = "PRODUCT_NAME";
  private static final String DISCOUNT_PERCENT_ATTRIBUTE = "DISCOUNT_PERCENT";
  private static final String DISCOUNT_ATTRIBUTE = "DISCOUNT";
  private static final String CODE_ERROR_ATTRIBUTE = "CODE_ERROR";
  private static final String PRICE_TOPUP_ATTRIBUTE = "PRICE_TOPUP";
  private static final String TRANS_TOPUP_ATTRIBUTE = "TRANS_TOPUP";
  private static final String VERIFY_STATUS_ATTRIBUTE = "VERIFY_STATUS";
  private static final String CONFIRM_STATUS_ATTRIBUTE = "CONFIRM_STATUS";

  private static final String RQP_TEN_SP = "tenSP";
  private static final String RQP_SERVICE_CODE_CARD = "serviceCodeCard";

  private static final String TPRICE_PINCODE_ATTRIBUTE = "TPRICE_PINCODE";
  private static final String SERVICE_CODE_CARD_ATTRIBUTE = "SERVICE_CODE_CARD"; // code ma the nap vd viettel
  private static final String TELCOTYPE_ID_ATTRIBUTE = "TELCOTYPE_ID_ATTRIBUTE";

  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig;
  @Autowired
  GatewayServiceAPIClient gatewayAPIClient;

  @Autowired
  DashboardController dashboardController;

  @GetMapping(value = "")
  public String fundInTopupGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws Exception {
    dashboardController.getPhoneTopUp(request, response, map);
    map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
    map.put("priceTopup", SessionUtil.getAttribute(PRICE_TOPUP_ATTRIBUTE));
    map.put("tenSP", SessionUtil.getAttribute(PRODUCT_NAME_ATTRIBUTE));
    map.put("listTransactionsTopUp", SessionUtil.getAttribute(TRANS_TOPUP_ATTRIBUTE));
    map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
    map.put(ENABLED_SERVICES, SessionUtil.getAttribute(ENABLED_SERVICES));
    return TOPUP_INFO;
  }

  @PostMapping(value = "")
  public String fundInTopupPost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws Exception {
    try {
      String tenSP = request.getParameter(RQP_TEN_SP);
      String phoneNumber = request.getParameter("phoneNumber");
      if (StringUtils.isBlank(phoneNumber)) {
        UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
        if (userLogin != null) {
          phoneNumber = userLogin.getPhoneNumber();
        }
      }
      if (tenSP == null) {
        String productName = getCardHolderByPhoneNumber(phoneNumber);
        tenSP = productName;
      }

      String priceTopup = request.getParameter("priceTopup");

      String serviceCodeCard = PhoneNumberUtil.getTopUpServiceIdByProductName(tenSP);

      // Store to Session
      SessionUtil.setAttribute(SERVICE_CODE_CARD_ATTRIBUTE, serviceCodeCard);

      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
      SessionUtil.setAttribute(PRODUCT_NAME_ATTRIBUTE, tenSP);
      SessionUtil.setAttribute(PRICE_TOPUP_ATTRIBUTE, priceTopup);

      //lấy danh sách phone gần nhất
      dashboardController.getPhoneTopUp(request, response, map);

      // Xử lý dữ liệu đầu vào
      // Long total = 0L;
      Integer offset = 0;
      Integer limit = 5;
      // Date fromDate = null;
      // Date endDate = null;
      // ---
      if (request.getParameter("d-49520-p") != null) {
        Integer p = Integer.parseInt(request.getParameter("d-49520-p"));
        offset = limit * (p - 1);
      }

      // String searchRange = request.getParameter("range");
      // if (StringUtils.isNotBlank(searchRange)) {
      // Date[] dates = parseDateRange(searchRange);
      // fromDate = dates[0];
      // endDate = dates[1];
      // }

      // Search box
      // String quickSearch = StringUtils.trimToEmpty(request.getParameter("quickSearch"))
      // .replaceAll("\\s+", " ");

      // Tạo request & set params vào request
      FindTransactionsRequest transactionsRequest =
          new FindTransactionsRequest();

      transactionsRequest.setOffset(offset);
      transactionsRequest.setLimit(limit);

      transactionsRequest.setTextSearch(null);
      transactionsRequest.setFromDate(null);
      transactionsRequest.setEndDate(null);

      // --

      String[] serviceTypeId = request.getParameterValues("serviceTypeId");
      if (serviceTypeId != null && serviceTypeId.length > 0
          && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
      } else {
        transactionsRequest.setServiceTypeIds(Collections.singletonList(PHONE_TOPUP.name()));
      }
      String[] serviceCode = request.getParameterValues("serviceCode");
      if (serviceCode != null && serviceCode.length > 0
          && !serviceCode[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
      }

      map.put("phoneNumber", phoneNumber);
      map.put("priceTopup", priceTopup);
      map.put("tenSP", tenSP);

      Collection<Transaction> listTransactionsTopUp;
      List<TransactionTopup> transTopup = new ArrayList<>();

      FindTransactionsResponse baseTransResponseType = transactionEndpoint
          .transactionFind(transactionsRequest);
      if (baseTransResponseType == null || baseTransResponseType.getStatus() == null) {
        throw new Exception("No response!");
      } else if (baseTransResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseTransResponseType.getStatus().getValue());
      } else {
        listTransactionsTopUp = baseTransResponseType.getTransactions();
        for (Transaction item : listTransactionsTopUp) {
          TransactionTopup tran = new TransactionTopup();
          tran.setId(item.getId());
          tran.setPhoneNumber(
              item.getAttributeValueByAttributeType(TransactionAttributeType.PTU_MSISDN.name()));
          tran.setAmount(
              item.getAttributeValueByAttributeType(TransactionAttributeType.PTU_AMOUNT.name()));
          tran.setTelco(
              item.getAttributeValueByAttributeType(TransactionAttributeType.PTU_TELCO.name()));
          tran.setCreatedDate(item.getCreationDate());
          transTopup.add(tran);
        }
        map.put("listTransactionsTopUp", transTopup);
        SessionUtil.setAttribute(TRANS_TOPUP_ATTRIBUTE, transTopup);

        map.put(RQP_SERVICE_CODE_CARD, serviceCodeCard);

        // Find enable Service
        super.findEnabledServices(map,
            SharedConstants.EPIN_TOPUP_PHONE_AVAILABLE_SERVICE, ServiceType.PHONE_TOPUP.name());

        return TOPUP_INFO;
      }

    } catch (Exception e) {
      map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));

      return TOPUP_INFO;
    }
  }

  @GetMapping(value = "/logIn")
  public String getFundInTopUpLogIn(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    String faceValue = request.getParameter("faceValue").replaceAll("[^0-9]+", "");
    String realAmount = request.getParameter("realAmount");
    String phoneNumber = request.getParameter("phoneNumber");

    SessionUtil.setAttribute(FACE_VALUE_ATTRIBUTE, faceValue);
    SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
    SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
    SessionUtil.setAttribute(PHONE_NUMBER_TOPUP_ATTRIBUTE, phoneNumber);

    map.put("faceValue", faceValue);
    map.put("realAmount", realAmount);
    map.put("phoneNumberTopUp", phoneNumber);

    return "/topup/topup_logIn";
  }

  @SuppressWarnings("unchecked")
  @GetMapping(value = "/topUpNextStep")
  public String getTopUpNextStep(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String account = request.getParameter("account");
      String passWord = request.getParameter("passWord");
      String walletBalance = request.getParameter("walletBalance");
      String phoneNumber = request.getParameter("phoneNumber");
      // phoneNumberTopUp = request.getParameter("phoneNumberTopUp");
      // phoneNumberTopUp = phoneNumber;

      // Store to Session
      SessionUtil.setAttribute(ACCOUNT_ATTRIBUTE, account);
      SessionUtil.setAttribute(WALLET_BALANCE_ATTRIBUTE, walletBalance);
      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);

      Map<String, String> mapHeader = new HashMap<>();
      mapHeader.put("Content-Type", "application/json");

      SignInRequest signInRequest = new SignInRequest();
      signInRequest.setIdentification(account);
      signInRequest.setCredential(passWord);
      signInRequest.setIdentificationTypeId(0); // 0 sdt
      signInRequest.setCredentialTypeId(1);

      BaseResponseType<SignInResponse> signInResponseType = null;
      signInResponseType = gatewayAPIClient
          .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_AUTH_SECURITY_SIGNIN, mapHeader,
              signInRequest,
              null, BaseResponseType.class);

      if (signInResponseType == null || signInResponseType.getStatus().getCode() != 0) {
        throw new Exception("Login fail");
      } else {
        GetCustomerBalanceResponse balanceResponseType = new GetCustomerBalanceResponse();
        balanceResponseType = walletEndpoint
            .getBalanceBySelfCustomer(new GetCustomerGetBalanceRequest());
        if (balanceResponseType == null || balanceResponseType.getStatus().getCode() != 0) {
          throw new Exception("GetBalance fail");
        } else {
          walletBalance = balanceResponseType.getBalance().toString();
          SessionUtil.setAttribute(WALLET_BALANCE_ATTRIBUTE, walletBalance);
          Object faceValueAttr = SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE);
          String faceValue = faceValueAttr != null ? String.valueOf(faceValueAttr) : "0";
          Long amount = Long.valueOf(faceValue);
          if (balanceResponseType.getBalance() < amount) {
            throw new Exception("Số dư không đủ");
          }
        }

        map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
        map.put("walletBalance", walletBalance);
        map.put("account", account);
        map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
        map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
        map.put("phoneNumber", phoneNumber);
        map.put("phoneNumberTopUp", SessionUtil.getAttribute(PHONE_NUMBER_TOPUP_ATTRIBUTE));

        return "/topup/topup_nextStep";
      }
    } catch (Exception e) {
      e.printStackTrace();

      map.put("codeErr", e.getMessage());
      map.put("walletBalance", SessionUtil.getAttribute(WALLET_BALANCE_ATTRIBUTE));
      map.put("account", SessionUtil.getAttribute(ACCOUNT_ATTRIBUTE));
      map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("phoneNumberTopUp", SessionUtil.getAttribute(PHONE_NUMBER_TOPUP_ATTRIBUTE));

      return "/topup/opup_logIn";
    }

  }

  @GetMapping(value = "/top-up-next-step-verify") // verify
  public String topUpNextStepVerifyGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    Object verifyStatusAttr = SessionUtil.getAttribute(VERIFY_STATUS_ATTRIBUTE);
    boolean verifyStatus = verifyStatusAttr != null && (boolean) verifyStatusAttr;

    if (verifyStatus) {
      map.put("walletBalance", SessionUtil.getAttribute(WALLET_BALANCE_ATTRIBUTE));
      map.put("account", SessionUtil.getAttribute(ACCOUNT_ATTRIBUTE));
      map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
      map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("phoneNumberTopUp", SessionUtil.getAttribute(PHONE_NUMBER_TOPUP_ATTRIBUTE));
      map.put("tenSP", SessionUtil.getAttribute(PRODUCT_NAME_ATTRIBUTE));
      map.put("disCountPercent", SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
      map.put("paymentSecurityType", SessionUtil.getAttribute(PAYMENT_SECURITY_TYPE_ATTRIBUTE));
      map.put("disCount", SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
      map.put("orderId", SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));
      try {
        map.put("paymentPinSetup", PaymentSecurityController.checkPaymentPIN(walletEndpoint));
      } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
        String codeErr = e.getMessage();

        SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);

        map.put("codeErr", codeErr);
      }
      return "/topup/topup_otpPhone";
    }

    map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
    map.put("walletBalance", SessionUtil.getAttribute(WALLET_BALANCE_ATTRIBUTE));
    map.put("account", SessionUtil.getAttribute(ACCOUNT_ATTRIBUTE));
    map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
    map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
    map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
    map.put("tenSP", SessionUtil.getAttribute(PRODUCT_NAME_ATTRIBUTE));
    map.put("disCountPercent", SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
    map.put("phoneNumberTopUp", SessionUtil.getAttribute(PHONE_NUMBER_TOPUP_ATTRIBUTE));
    map.put("disCount", SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
    map.put("orderId", SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));

    return "/topup/topup_nextStep";
  }

  @PostMapping(value = "/top-up-next-step-verify") // verify
  public String topUpNextStepVerify(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    String tenSP = request.getParameter("tenSP");
    String serviceCodeCard = request.getParameter(RQP_SERVICE_CODE_CARD);
    String faceValue = request.getParameter("faceValue");
    String phoneNumber = request.getParameter("phoneNumber");
    String realAmount = request.getParameter("realAmount");
    try {
      String codeErr = request.getParameter("codeErr");
//      if (phoneNumber != null) {
//        String productName = getCardHolderByPhoneNumber(phoneNumber);
//        SessionUtil.setAttribute(PRODUCT_NAME_ATTRIBUTE, productName);
//      }
      String disCountPercent = request.getParameter("disCountPercent");
      String disCount = request.getParameter("disCount").replaceAll("[^0-9]+", "");

      // Store to Session
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      SessionUtil.setAttribute(FACE_VALUE_ATTRIBUTE, faceValue);
      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
      SessionUtil.setAttribute(DISCOUNT_PERCENT_ATTRIBUTE, disCountPercent);
      SessionUtil.setAttribute(DISCOUNT_ATTRIBUTE, disCount);
      SessionUtil.setAttribute(SERVICE_CODE_CARD_ATTRIBUTE, serviceCodeCard);

      GetCustomerBalanceResponse balanceResponseType = walletEndpoint
          .getBalanceBySelfCustomer(new GetCustomerGetBalanceRequest());
      if (balanceResponseType == null || balanceResponseType.getStatus() == null) {
        throw new Exception("Get Balance failed");
      }

      if (balanceResponseType.getStatus().getCode() != 0) {
        throw new Exception(balanceResponseType.getStatus().getValue());
      } else {
        String walletBalance = balanceResponseType.getBalance().toString();
        SessionUtil.setAttribute(WALLET_BALANCE_ATTRIBUTE, walletBalance);
        Long amount = Long.valueOf(faceValue);
        if (balanceResponseType.getBalance() < amount) {
          codeErr = "Số dư không đủ";

          throw new Exception(codeErr);
        }
      }
      String requestId = UUID.randomUUID().toString();
      SessionUtil.setAttribute(REQUEST_ID_ATTRIBUTE, requestId);

      TopupTransactionRequest topupTransactionRequest = new TopupTransactionRequest();
      topupTransactionRequest.setTerminalId(CHANNEL_WEB);
      topupTransactionRequest.setRequestId(requestId);
      topupTransactionRequest.setServiceCode(serviceCodeFix);

      String telcoTypeId = null;
      switch (serviceCodeCard) {
        case "040101":
          telcoTypeId = "VTM";
          break;
        case "040102":
          telcoTypeId = "VNP";
          break;
        case "040103":
          telcoTypeId = "VMS";
          break;
        case "040104":
          telcoTypeId = "GMOBILE";
          break;
        case "040105":
          telcoTypeId ="VNM";
          break;
        default:
          break;
      }
      SessionUtil.setAttribute(TELCOTYPE_ID_ATTRIBUTE, telcoTypeId);
      topupTransactionRequest.setTelcoTypeId(telcoTypeId);
      topupTransactionRequest.setPhoneNumber(phoneNumber);
      topupTransactionRequest.setPrice(Long.valueOf(faceValue));
      topupTransactionRequest.setOrderId(null);
      topupTransactionRequest.setActionType(ACTION_VERIFY);
      topupTransactionRequest.setOtp(null);

      TopupTransactionResponse topUpResponseType = walletEndpoint
          .walletByTopUpVerify(topupTransactionRequest);
      if (topUpResponseType == null) {
        throw new Exception("No response");
      }
      if (topUpResponseType.getStatus().getCode() != 0) {
        throw new Exception(topUpResponseType.getStatus().getValue());
      } else {
        String orderId = topUpResponseType.getOrderId().toString();
        String paymentSecurityType = topUpResponseType.getPaymentSecurityType();

        map.put("walletBalance", SessionUtil.getAttribute(WALLET_BALANCE_ATTRIBUTE));
        map.put("account", SessionUtil.getAttribute(ACCOUNT_ATTRIBUTE));
        map.put("codeErr", codeErr);
        map.put("faceValue", faceValue);
        map.put("realAmount", realAmount);
        map.put("phoneNumber", phoneNumber);
        map.put("phoneNumberTopUp", SessionUtil.getAttribute(PHONE_NUMBER_TOPUP_ATTRIBUTE));
        SessionUtil.setAttribute(PRODUCT_NAME_ATTRIBUTE, tenSP);
        map.put("tenSP", tenSP);
        map.put(RQP_SERVICE_CODE_CARD, serviceCodeCard);
        map.put("disCountPercent", disCountPercent);
        map.put("paymentSecurityType", paymentSecurityType);
        map.put("disCount", disCount);
        map.put("paymentPinSetup", PaymentSecurityController.checkPaymentPIN(walletEndpoint));
        map.put("orderId", orderId);

        SessionUtil.setAttribute(ORDER_ID_ATTRIBUTE, orderId);
        SessionUtil.setAttribute(PAYMENT_SECURITY_TYPE_ATTRIBUTE, paymentSecurityType);
        SessionUtil.setAttribute(VERIFY_STATUS_ATTRIBUTE, true);

        return "/topup/topup_otpPhone";
      }

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);
      map.put("walletBalance", SessionUtil.getAttribute(WALLET_BALANCE_ATTRIBUTE));
      map.put("account", SessionUtil.getAttribute(ACCOUNT_ATTRIBUTE));
      map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
//      map.put("tenSP", SessionUtil.getAttribute(PRODUCT_NAME_ATTRIBUTE));
      SessionUtil.setAttribute(PRODUCT_NAME_ATTRIBUTE, tenSP);
      map.put("tenSP", tenSP);
      map.put(RQP_SERVICE_CODE_CARD, serviceCodeCard);
      map.put("disCountPercent", SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
      map.put("phoneNumberTopUp", SessionUtil.getAttribute(PHONE_NUMBER_TOPUP_ATTRIBUTE));
      map.put("disCount", SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
      map.put("orderId", SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));


      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      SessionUtil.setAttribute(VERIFY_STATUS_ATTRIBUTE, false);

      return "/topup/topup_nextStep";
    }
  }

  @GetMapping(value = "/top-up-confirm") // Confirm
  public String topUpOtpPhoneConfirmGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    Object confirmStatusAttr = SessionUtil.getAttribute(CONFIRM_STATUS_ATTRIBUTE);
    boolean confirmStatus = confirmStatusAttr != null && (boolean) confirmStatusAttr;

    return confirmStatus ? "/topup/topup_transactionSuccess" : "/topup/topup_transactionError";
  }

  @PostMapping(value = "/top-up-confirm") // Confirm
  public String topUpOtpPhoneConfirm(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      // Get data from Session
      Object requestIdAttr = SessionUtil.getAttribute(REQUEST_ID_ATTRIBUTE);
      String requestId = requestIdAttr != null ? requestIdAttr.toString() : null;
      Object faceValueAttr = SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE);
      Long faceValue = faceValueAttr != null ? Long.parseLong(String.valueOf(faceValueAttr)) : null;
      Object phoneNumberAttr = SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE);
      String phoneNumber = phoneNumberAttr != null ? phoneNumberAttr.toString() : null;
      Object orderIdAttr = SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE);
      Long orderId = orderIdAttr != null ? Long.parseLong(String.valueOf(orderIdAttr)) : null;
      Object paymentSecurityTypeAttr = SessionUtil.getAttribute(PAYMENT_SECURITY_TYPE_ATTRIBUTE);
      String paymentSecurityType =
          paymentSecurityTypeAttr != null ? paymentSecurityTypeAttr.toString() : null;
      String paymentSecurityCode = request.getParameter("paymentSecurityCode");
      Object telcoTypeIdAtrr = SessionUtil.getAttribute(TELCOTYPE_ID_ATTRIBUTE);
      String telcoTypeId = telcoTypeIdAtrr != null ? telcoTypeIdAtrr.toString() : null;

      TopupTransactionRequest topupTransactionRequest = new TopupTransactionRequest();
      topupTransactionRequest.setTerminalId(CHANNEL_WEB);
      topupTransactionRequest.setRequestId(requestId);
      topupTransactionRequest.setServiceCode(serviceCodeFix);
      topupTransactionRequest.setTelcoTypeId(telcoTypeId);
      topupTransactionRequest.setPrice(faceValue);
      topupTransactionRequest.setPhoneNumber(phoneNumber);
      topupTransactionRequest.setOrderId(orderId);
      topupTransactionRequest.setActionType(ACTION_CONFIRM);
      if ("OTP".equals(paymentSecurityType)) {
        topupTransactionRequest.setOtp(paymentSecurityCode);
      }
//      duongdp 12/2/2019
      else if ("PAYMENT_PASSWORD".equals(paymentSecurityType)) {
        topupTransactionRequest.setOtp(paymentSecurityCode);
      }
//      duongdp 12/2/2019
      else {
        if (PaymentSecurityController.checkPaymentPIN(walletEndpoint)) {
          topupTransactionRequest.setOtp(paymentSecurityCode);
        }
      }

      TopupTransactionResponse topUpResponseType = walletEndpoint
          .walletByTopUpVerify(topupTransactionRequest);
      if (topUpResponseType == null || topUpResponseType.getStatus() == null) {
        throw new Exception("No response");
      }

      if (topUpResponseType.getStatus().getCode() != 0) {
        if(topUpResponseType.getStatus().getCode() == 4501){
          map.put("error",topUpResponseType.getStatus().getCode());
        }
        throw new Exception(topUpResponseType.getStatus().getValue());
      } else {
        map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
        map.put("walletBalance", SessionUtil.getAttribute(WALLET_BALANCE_ATTRIBUTE));
        map.put("account", SessionUtil.getAttribute(ACCOUNT_ATTRIBUTE));
        map.put("faceValue", faceValue);
        map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
        map.put("phoneNumber", phoneNumber);
        map.put("phoneNumberTopUp", SessionUtil.getAttribute(PHONE_NUMBER_TOPUP_ATTRIBUTE));
        map.put("tenSP", SessionUtil.getAttribute(PRODUCT_NAME_ATTRIBUTE));
        map.put("disCountPercent", SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
        map.put("disCount", SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
        map.put("orderId", orderId);

        SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, true);

        getUserBalanceToSession(request);
        try {
          GetConsumerAccountResponse baseResponseType = walletEndpoint.accountInfoGet();
          if (baseResponseType.getStatus().getCode() != 0) {
            throw new Exception(baseResponseType.getStatus().getValue());
          } else if (baseResponseType.getStatus().getCode() == 0) {
            if (baseResponseType.getCustomer().getAddresses() != null) {
              List<Address> lstAddress = baseResponseType.getCustomer().getAddresses();
              for (Address item : lstAddress) {
                if (item.getAddressType() == AddressType.OUTLET_ADDRESS.getCode()) {
                  map.put("street1Store", item.getStreet1());
                  map.put("aliasStore", item.getAlias());
                }
              }
            }
          }
        } catch (Exception ex) {
          LOGGER.error(ex.getMessage());
        }
        return "/topup/topup_transactionSuccess";
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);

      map.put("codeErr", e.getMessage());
      map.put("walletBalance", SessionUtil.getAttribute(WALLET_BALANCE_ATTRIBUTE));
      map.put("account", SessionUtil.getAttribute(ACCOUNT_ATTRIBUTE));
      map.put("faceValue", SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("phoneNumberTopUp", SessionUtil.getAttribute(PHONE_NUMBER_TOPUP_ATTRIBUTE));
      map.put("tenSP", SessionUtil.getAttribute(PRODUCT_NAME_ATTRIBUTE));
      map.put("disCountPercent", SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
      map.put("disCount", SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
      map.put("orderId", SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));

      SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, false);

      return "/topup/topup_transactionError";
    }
  }

  @RequestMapping(value = "/resend-otp", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> resendOTP(HttpServletRequest httpRequest) throws Exception {
    Long orderId = NumberUtil.convertToLong(httpRequest.getParameter("orderId"));
    if (orderId > 0) {
      TopupTransactionOTPGetRequest request = new TopupTransactionOTPGetRequest();
      request.setOrderId(orderId);
      TopupTransactionOTPGetResponse response = walletEndpoint.resendOTP(request);
      return ResponseEntity
          .ok(new AjaxResponse(response.getStatus().getCode(), response.getStatus().getValue()));
    } else {
      return ResponseEntity.ok(new AjaxResponse(1, validation.notify(MESSAGE_NOT_ID)));
    }
  }
}
