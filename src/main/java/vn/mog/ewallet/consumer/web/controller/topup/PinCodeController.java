package vn.mog.ewallet.consumer.web.controller.topup;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.EPIN;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.consumer.web.common.SharedConstants;
import vn.mog.ewallet.consumer.web.contract.AjaxResponse;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController;
import vn.mog.ewallet.consumer.web.controller.system.PaymentSecurityController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.Customer;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerBalanceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerGetBalanceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.TopupTransactionOTPGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.TopupTransactionOTPGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.TransactionResultExportRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.TransactionResultExportResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.AddressType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.TopupTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.TopupTransactionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction.Card;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.TransactionAttributeType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.TransactionEpin;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.TxnStatus;
import vn.mog.ewallet.consumer.web.util.tools.AES;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.PhoneNumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

@Controller
@RequestMapping(value = "/pin-code")
public class PinCodeController extends AbstractController {

  public static final String PINCODE_CONTROLLER = "/pin-code";
  public static final String PINCODE_INFO = PINCODE_CONTROLLER + "/order-info";
  public static final String PINCODE_TRANSACTION_LIST =
      PINCODE_CONTROLLER + "/getListTransactionPinCode";
  public static final String PINCODE_TRANSACTION_SUCCESS =
      PINCODE_CONTROLLER + "/pin_pay_transactionSuccess";
  public static final String PINCODE_TRANSACTION_ERROR =
      PINCODE_CONTROLLER + "/pin_pay_transactionError";

  private static final Logger LOGGER = LogManager.getLogger(PinCodeController.class);
  private static String MESSAGE_NOT_ID = "alert.controller.merchant-po.not-id";
  private static String ERROR_OCCURS = "alert.controller.error.occurs";

  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

  private static final String ACTION_VERIFY = "verify";
  private static final String ACTION_CONFIRM = "confirm";

  private static final String SERVICE_CODE_CARD_ATTRIBUTE = "SERVICE_CODE_CARD"; // code ma the nap vd viettel
  private static final String FACE_VALUE_ATTRIBUTE = "FACE_VALUE";
  private static final String QUANTITY_ATTRIBUTE = "QUANTITY";
  private static final String DISCOUNT_ATTRIBUTE = "DISCOUNT";
  private static final String REAL_AMOUNT_ATTRIBUTE = "REAL_AMOUNT";
  private static final String TAIKHOAN_ATTRIBUTE = "TAI_KHOAN";
  private static final String SO_DU_VI_ATTRIBUTE = "SO_DU_VI";
  private static final String CODE_ERROR_ATTRIBUTE = "CODE_ERROR";
  private static final String ERROR_ATTRIBUTE = "ERROR";
  private static final String ORDER_ID_ATTRIBUTE = "ORDER_ID";
  private static final String DISCOUNT_PERCENT_ATTRIBUTE = "DISCOUNT_PERCENT";
  private static final String PAYMENT_SECURITY_TYPE_ATTRIBUTE = "PAYMENT_SECURITY_TYPE";
  private static final String TRANSACTION_ATTRIBUTE="TRANSACTION_ATTRIBUTE";

  private static final String TEN_SP_ATTRIBUTE = "TEN_SP"; // ma the dien thoai
  private static final String TPRICE_PINCODE_ATTRIBUTE = "TPRICE_PINCODE";

  private static final String LIST_TRANS_PINCODE_ATTRIBUTE = "LIST_TRANS_PINCODE";
  private static final String TRANS_EPIN_ATTRIBUTE = "TRANS_EPIN";
  private static final String VERIFY_STATUS_ATTRIBUTE = "VERIFY_STATUS";
  private static final String CONFIRM_STATUS_ATTRIBUTE = "CONFIRM_STATUS";
  private static final String RQP_QUICK_SEARCH = "quickSearch";
  private static final String RQP_TEN_SP = "tenSP";
  private static final String RQP_SERVICE_CODE_CARD = "serviceCodeCard";
  private static final String RQP_TPRICE_PINCODE = "tprice_pincode";
  private static final String RQP_VIETTEL = "Viettel";
  private static final String SERVICE_TYPE_ID = "serviceTypeId";
  private static final String SERVICE_CODE = "serviceCode";
  private static final String RQP_CODE_ERR = "codeErr";
  private static final String SERVICE_CODE_CARD_VT = "040101";
  private static final String RQP_FACEVALUE = "faceValue";
  private static final String NOT_NUMBER = "[^0-9]+";
  private static final String RQP_QUANTITY = "quantity";
  private static final String RQP_DISCOUNT = "disCount";
  private static final String RQP_REAL_AMOUNT = "realAmount";
  private static final String RQP_TAI_KHOAN = "taiKhoan";
  private static final String RQP_SO_DU_VI = "soDuVi";
  private static final String URL_PIN_PAY_NEXTSTEP = "/pin_code/pin_pay_nextStep";
  private static final String RQP_DISCOUNT_PERCENT = "disCountPercent";
  private static final String RQP_ORDER_ID = "orderId";

//  private Transaction transaction;

  @Autowired
  DashboardController dashboardController;

  @Autowired
  GatewayServiceAPIClient gatewayAPIClient;

  @GetMapping(value = "")
  public String pinCode() throws Exception {

    return "/pin_code/pin";
  }

  @GetMapping(value = "/order-info")
  public String pinInfoItemOrderGet(HttpServletRequest request, HttpServletResponse response,
                                    ModelMap map) throws Exception {
    map.put("listTransPinCode", SessionUtil.getAttribute(TRANS_EPIN_ATTRIBUTE));
    map.put(RQP_TEN_SP, SessionUtil.getAttribute(TEN_SP_ATTRIBUTE));
    map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
    map.put(RQP_TPRICE_PINCODE, SessionUtil.getAttribute(TPRICE_PINCODE_ATTRIBUTE));
    map.put(ENABLED_SERVICES, SessionUtil.getAttribute(ENABLED_SERVICES));

    return "/pin_code/pin_infoOder";
  }

  @PostMapping(value = "/order-info")
  public String pinInfoItemOrder(HttpServletRequest request, HttpServletResponse response,
                                 ModelMap map) throws Exception {
    try {
      String tenSP = request.getParameter(RQP_TEN_SP);
      String tprice_pincode = request.getParameter(RQP_TPRICE_PINCODE);

      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      if (StringUtils.isEmpty(tenSP) && userLogin != null) {
        tenSP = PhoneNumberUtil.getCardHolderByPhoneNumber(userLogin.getPhoneNumber());
      }
      if (StringUtils.isEmpty(tenSP)) {
        tenSP = RQP_VIETTEL;
      }
      String serviceCodeCard = PhoneNumberUtil.getPinCodeServiceIdByProductName(tenSP);

      // Store to Session
      SessionUtil.setAttribute(TEN_SP_ATTRIBUTE, tenSP);
      SessionUtil.setAttribute(TPRICE_PINCODE_ATTRIBUTE, tprice_pincode);
      SessionUtil.setAttribute(SERVICE_CODE_CARD_ATTRIBUTE, serviceCodeCard);

      // Xử lý dữ liệu đầu vào
      Long total = 0L;
      Integer offset = 0;
      Integer limit = 20;
      Date fromDate = null;
      Date endDate = null;
      // ---
      if (request.getParameter("d-49520-p") != null) {
        Integer p = Integer.parseInt(request.getParameter("d-49520-p"));
        offset = limit * (p - 1);
      }

      // Tạo request & set params vào request
      FindTransactionsRequest transactionsRequest = new FindTransactionsRequest();
      transactionsRequest.setOffset(offset);
      transactionsRequest.setLimit(limit);
      transactionsRequest.setTextSearch(null);
      transactionsRequest.setFromDate(null);
      transactionsRequest.setEndDate(null);
      String[] serviceTypeId = request.getParameterValues(SERVICE_TYPE_ID);
      if (serviceTypeId != null && serviceTypeId.length > 0
          && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
      } else {
        transactionsRequest.setServiceTypeIds(Arrays.asList((EPIN.name())));
      }

      String[] serviceCode = request.getParameterValues(SERVICE_CODE);
      if (serviceCode != null && serviceCode.length > 0
          && !serviceCode[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
      }
      transactionsRequest.setOffset(0);
      transactionsRequest.setLimit(5);

      Collection<Transaction> listTransPinCode;
      List<TransactionEpin> transEpin = new ArrayList<>();
      FindTransactionsResponse baseTransResponseType = transactionEndpoint
          .transactionFind(transactionsRequest);
      if (baseTransResponseType == null || baseTransResponseType.getStatus() == null
          || baseTransResponseType.getStatus().getCode() != 0) {
        LOGGER.error(" API transactionPinCodeFind fail");
      } else {
        if (baseTransResponseType != null) {
          listTransPinCode = baseTransResponseType.getTransactions();
          for (Transaction item : listTransPinCode) {
            TransactionEpin tran = new TransactionEpin();
            tran.setId(item.getId());
            tran.setAmount(
                item.getAttributeValueByAttributeType(
                    TransactionAttributeType.PTU_CARD_FACE_VALUE.name()));
            tran.setCardType(
                item.getAttributeValueByAttributeType(
                    TransactionAttributeType.PTU_CARD_TYPE.name()));
            tran.setCreatedDate(item.getCreationDate());
            tran.setServiceName(item.getServiceName());
            tran.setOrderInfo(item.getOrderInfo());
            transEpin.add(tran);
          }
        }
      }

      SessionUtil.setAttribute(TRANS_EPIN_ATTRIBUTE, transEpin);
      // map.put(RQP_TEN_SP, tenSP);
      // map.put(RQP_SERVICE_CODE_CARD, serviceCodeCard);
      // map.put(SERVICE_CODE, serviceCode);
      // map.put(RQP_FACEVALUE, faceValue);
      map.put("listTransPinCode", transEpin);
      map.put("total", total.intValue());
      map.put(RQP_TEN_SP, tenSP);
      map.put(RQP_SERVICE_CODE_CARD, serviceCodeCard);
      map.put(RQP_TPRICE_PINCODE, tprice_pincode);

      // Find enable Service
      super.findEnabledServices(map,
          SharedConstants.EPIN_PHONE_AVAILABLE_SERVICE, ServiceType.EPIN.name());

    } catch (Exception e) {
      String codeErr = e.getMessage();
      map.put(RQP_CODE_ERR, codeErr);
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
    }

    return "/pin_code/pin_infoOder";
  }

  @PostMapping(value = "/pinPayLogin")
  public String pinPayLogin(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    String tenSP = request.getParameter(RQP_TEN_SP);
    String serviceCodeCard = request.getParameter(RQP_SERVICE_CODE_CARD);
    if (StringUtils.isBlank(serviceCodeCard)) {
      serviceCodeCard = SERVICE_CODE_CARD_VT;
      tenSP = RQP_VIETTEL;
    }
    String faceValue = request.getParameter(RQP_FACEVALUE).replaceAll(NOT_NUMBER, "");
    String quantity = request.getParameter(RQP_QUANTITY);
    String disCount = request.getParameter(RQP_DISCOUNT);
    String realAmount = request.getParameter(RQP_REAL_AMOUNT);
    String taiKhoan = request.getParameter(RQP_TAI_KHOAN);

    // Store to Session
    SessionUtil.setAttribute(TEN_SP_ATTRIBUTE, tenSP);
    SessionUtil.setAttribute(SERVICE_CODE_CARD_ATTRIBUTE, serviceCodeCard);
    SessionUtil.setAttribute(FACE_VALUE_ATTRIBUTE, faceValue);
    SessionUtil.setAttribute(QUANTITY_ATTRIBUTE, quantity);
    SessionUtil.setAttribute(DISCOUNT_ATTRIBUTE, disCount);
    SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
    SessionUtil.setAttribute(TAIKHOAN_ATTRIBUTE, taiKhoan);

    map.put(RQP_TEN_SP, tenSP);
    map.put(RQP_SERVICE_CODE_CARD, serviceCodeCard);
    map.put(RQP_FACEVALUE, faceValue);
    map.put(RQP_QUANTITY, quantity);
    map.put(RQP_DISCOUNT, disCount);
    map.put(RQP_REAL_AMOUNT, realAmount);
    map.put(RQP_TAI_KHOAN, taiKhoan);

    return "/pin_code/pin_pay_logIn";
  }

  @GetMapping(value = "/pin-pay-next-step")
  public String getPinPayNextStep(HttpServletRequest request, HttpServletResponse response,
                                  ModelMap map) throws Exception {
    try {
      String tenSP = request.getParameter(RQP_TEN_SP);
      String serviceCodeCard = request.getParameter(RQP_SERVICE_CODE_CARD);
      if (StringUtils.isBlank(serviceCodeCard)) {
        serviceCodeCard = SERVICE_CODE_CARD_VT;
        tenSP = RQP_VIETTEL;
      }
      String faceValue = request.getParameter(RQP_FACEVALUE).replaceAll(NOT_NUMBER, "");
      String quantity = request.getParameter(RQP_QUANTITY);
      String disCount = request.getParameter(RQP_DISCOUNT).replaceAll(NOT_NUMBER, "");
      String realAmount = request.getParameter(RQP_REAL_AMOUNT).replaceAll(NOT_NUMBER, "");
      String taiKhoan = request.getParameter(RQP_TAI_KHOAN);
      String soDuVi = request.getParameter(RQP_SO_DU_VI);

      // Store to Session
      SessionUtil.setAttribute(TEN_SP_ATTRIBUTE, tenSP);
      SessionUtil.setAttribute(SERVICE_CODE_CARD_ATTRIBUTE, serviceCodeCard);
      SessionUtil.setAttribute(FACE_VALUE_ATTRIBUTE, faceValue);
      SessionUtil.setAttribute(QUANTITY_ATTRIBUTE, quantity);
      SessionUtil.setAttribute(DISCOUNT_ATTRIBUTE, disCount);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
      SessionUtil.setAttribute(TAIKHOAN_ATTRIBUTE, taiKhoan);
      SessionUtil.setAttribute(SO_DU_VI_ATTRIBUTE, soDuVi);

      GetCustomerBalanceResponse balanceResponseType = walletEndpoint
          .getBalanceBySelfCustomer(new GetCustomerGetBalanceRequest());
      if (balanceResponseType == null || balanceResponseType.getStatus().getCode() != 0) {
        throw new Exception("GetBalance failed");
      } else {
        soDuVi = balanceResponseType.getBalance().toString();
        Long amount = Long.valueOf(faceValue) * Long.valueOf(quantity);
        if (balanceResponseType.getBalance() < Long.valueOf(amount)) {
          String codeErr = "Số dư không đủ";
          SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
          throw new Exception(codeErr);
        }
      }

      map.put(RQP_TEN_SP, tenSP);
      map.put(RQP_SERVICE_CODE_CARD, serviceCodeCard);
      map.put(RQP_FACEVALUE, faceValue);
      map.put(RQP_QUANTITY, quantity);
      map.put(RQP_DISCOUNT, disCount);
      map.put(RQP_REAL_AMOUNT, realAmount);
      map.put(RQP_CODE_ERR, SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
      map.put(RQP_SO_DU_VI, soDuVi);
      map.put(RQP_TAI_KHOAN, taiKhoan);
      return URL_PIN_PAY_NEXTSTEP;
      // }
    } catch (Exception e) {
      e.printStackTrace();
      map.put(RQP_CODE_ERR, e.getMessage());
      map.put(RQP_TEN_SP, SessionUtil.getAttribute(TEN_SP_ATTRIBUTE));
      map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
      map.put(RQP_FACEVALUE, SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put(RQP_QUANTITY, SessionUtil.getAttribute(QUANTITY_ATTRIBUTE));
      map.put(RQP_DISCOUNT, SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
      map.put(RQP_REAL_AMOUNT, SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put(RQP_SO_DU_VI, SessionUtil.getAttribute(SO_DU_VI_ATTRIBUTE));
      map.put(RQP_TAI_KHOAN, SessionUtil.getAttribute(TAIKHOAN_ATTRIBUTE));

      return URL_PIN_PAY_NEXTSTEP;
    }

  }

  @GetMapping(value = "/pin-pay-verify") // verify
  public String pinPayNextStepGet(HttpServletRequest request, HttpServletResponse response,
                                  ModelMap map) throws Exception {
    Object verifyStatusAttr = SessionUtil.getAttribute(VERIFY_STATUS_ATTRIBUTE);
    boolean verifyStatus = verifyStatusAttr != null && (boolean) verifyStatusAttr;
    if (verifyStatus) {
      map.put(RQP_TEN_SP, SessionUtil.getAttribute(TEN_SP_ATTRIBUTE));
      map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
      map.put(RQP_FACEVALUE, SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put(RQP_QUANTITY, SessionUtil.getAttribute(QUANTITY_ATTRIBUTE));
      map.put(RQP_DISCOUNT, SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
      map.put(RQP_REAL_AMOUNT, SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put(RQP_SO_DU_VI, SessionUtil.getAttribute(SO_DU_VI_ATTRIBUTE));
      map.put(RQP_TAI_KHOAN, SessionUtil.getAttribute(TAIKHOAN_ATTRIBUTE));
      map.put(RQP_DISCOUNT_PERCENT, SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
//        map.put("isOtpRequired", isOtpRequired);
      map.put("paymentSecurityType", SessionUtil.getAttribute(PAYMENT_SECURITY_TYPE_ATTRIBUTE));
      map.put("paymentPinSetup", PaymentSecurityController.checkPaymentPIN(walletEndpoint));
      map.put(RQP_ORDER_ID, SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));

      return "/pin_code/pin_pay_otpPhone";
    }

    map.put(RQP_CODE_ERR, SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
    map.put(RQP_TEN_SP, SessionUtil.getAttribute(TEN_SP_ATTRIBUTE));
    map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
    map.put(RQP_FACEVALUE, SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
    map.put(RQP_QUANTITY, SessionUtil.getAttribute(QUANTITY_ATTRIBUTE));
    map.put(RQP_DISCOUNT, SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
    map.put(RQP_REAL_AMOUNT, SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
    map.put(RQP_SO_DU_VI, SessionUtil.getAttribute(SO_DU_VI_ATTRIBUTE));
    map.put(RQP_TAI_KHOAN, SessionUtil.getAttribute(TAIKHOAN_ATTRIBUTE));
    map.put(RQP_DISCOUNT_PERCENT, SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
//      map.put("isOtpRequired", isOtpRequired);
    map.put(RQP_ORDER_ID, SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));
    return URL_PIN_PAY_NEXTSTEP;
  }

  @PostMapping(value = "/pin-pay-verify") // verify
  public String pinPayNextStep(HttpServletRequest request, HttpServletResponse response,
                               ModelMap map) throws Exception {
    try {
      String tenSP = request.getParameter(RQP_TEN_SP);
      String serviceCodeCard = request.getParameter(RQP_SERVICE_CODE_CARD);
      if (StringUtils.isBlank(serviceCodeCard)) {
        serviceCodeCard = SERVICE_CODE_CARD_VT;
        tenSP = RQP_VIETTEL;
      }
      String faceValue = request.getParameter(RQP_FACEVALUE).replaceAll(NOT_NUMBER, "");
      String quantity = request.getParameter(RQP_QUANTITY);
      String disCount = request.getParameter(RQP_DISCOUNT).replaceAll(NOT_NUMBER, "");
      String realAmount = request.getParameter(RQP_REAL_AMOUNT).replaceAll(NOT_NUMBER, "");
      String taiKhoan = request.getParameter(RQP_TAI_KHOAN);
      String soDuVi = request.getParameter(RQP_SO_DU_VI);
      String disCountPercent = request.getParameter(RQP_DISCOUNT_PERCENT);

      // Store to Session
      SessionUtil.setAttribute(TEN_SP_ATTRIBUTE, tenSP);
      SessionUtil.setAttribute(SERVICE_CODE_CARD_ATTRIBUTE, serviceCodeCard);
      SessionUtil.setAttribute(FACE_VALUE_ATTRIBUTE, faceValue);
      SessionUtil.setAttribute(QUANTITY_ATTRIBUTE, quantity);
      SessionUtil.setAttribute(DISCOUNT_ATTRIBUTE, disCount);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
      SessionUtil.setAttribute(TAIKHOAN_ATTRIBUTE, taiKhoan);
      SessionUtil.setAttribute(SO_DU_VI_ATTRIBUTE, soDuVi);
      SessionUtil.setAttribute(DISCOUNT_PERCENT_ATTRIBUTE, disCountPercent);


      GetCustomerBalanceResponse balanceResponseType = walletEndpoint
          .getBalanceBySelfCustomer(new GetCustomerGetBalanceRequest());
      if (balanceResponseType == null) {
        throw new Exception("GetBalance failed");
      }
      if (balanceResponseType.getStatus().getCode() != 0) {
        throw new Exception(balanceResponseType.getStatus().getValue());
      } else {
        soDuVi = balanceResponseType.getBalance().toString();
        Long amount = Long.valueOf(faceValue) * Long.valueOf(quantity);
        if (balanceResponseType.getBalance() < Long.valueOf(amount)) {
          String codeErr = "Số dư không đủ";
          SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
          throw new Exception(codeErr);
        }
      }

      String requestId = UUID.randomUUID().toString();
      TopupTransactionRequest topupTransactionRequest = new TopupTransactionRequest();
      topupTransactionRequest.setTerminalId("WEB");
      topupTransactionRequest.setRequestId(requestId);
      topupTransactionRequest.setServiceCode(serviceCodeCard);
      topupTransactionRequest.setPrice(Long.valueOf(faceValue));
      topupTransactionRequest.setQuantity(Integer.valueOf(quantity));
      topupTransactionRequest.setOrderId(null);
      topupTransactionRequest.setActionType(ACTION_VERIFY);
      topupTransactionRequest.setOtp(null);

      TopupTransactionResponse pinCodeResponseType = walletEndpoint.pinCodeVerify(topupTransactionRequest);
      if (pinCodeResponseType == null) {
        throw new Exception("No response");
      }
      if (pinCodeResponseType.getStatus().getCode() != 0) {
        throw new Exception(pinCodeResponseType.getStatus().getValue());
      } else {
        String orderId = pinCodeResponseType.getOrderId().toString();
//        isOtpRequired = pinCodeResponse.getIsOtpRequired();
        String paymentSecurityType = pinCodeResponseType.getPaymentSecurityType();

        // Store to Session
        SessionUtil.setAttribute(ORDER_ID_ATTRIBUTE, orderId);
        SessionUtil.setAttribute(PAYMENT_SECURITY_TYPE_ATTRIBUTE, paymentSecurityType);

        map.put(RQP_TEN_SP, tenSP);
        map.put(RQP_SERVICE_CODE_CARD, serviceCodeCard);
        map.put(RQP_FACEVALUE, faceValue);
        map.put(RQP_QUANTITY, quantity);
        map.put(RQP_DISCOUNT, disCount);
        map.put(RQP_REAL_AMOUNT, realAmount);
        map.put(RQP_SO_DU_VI, soDuVi);
        map.put(RQP_TAI_KHOAN, taiKhoan);
        map.put(RQP_DISCOUNT_PERCENT, disCountPercent);
//        map.put("isOtpRequired", isOtpRequired);
        map.put("paymentSecurityType", paymentSecurityType);
        map.put(RQP_ORDER_ID, orderId);
//        try {
//          map.put("paymentPinSetup", PaymentSecurityController.checkPaymentPIN(walletEndpoint));
//        } catch (Exception e) {
//          LOGGER.error(e.getMessage(), e);
//          String codeErr = e.getMessage();
//          map.put(RQP_CODE_ERR, codeErr);
//          SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
//        }
        SessionUtil.setAttribute(VERIFY_STATUS_ATTRIBUTE, true);

        return "/pin_code/pin_pay_otpPhone";
      }
    } catch (Exception e) {
      String codeErr = e.getMessage();
      map.put(RQP_CODE_ERR, codeErr);
      map.put(RQP_TEN_SP, SessionUtil.getAttribute(TEN_SP_ATTRIBUTE));
      map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
      map.put(RQP_FACEVALUE, SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put(RQP_QUANTITY, SessionUtil.getAttribute(QUANTITY_ATTRIBUTE));
      map.put(RQP_DISCOUNT, SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
      map.put(RQP_REAL_AMOUNT, SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put(RQP_SO_DU_VI, SessionUtil.getAttribute(SO_DU_VI_ATTRIBUTE));
      map.put(RQP_TAI_KHOAN, SessionUtil.getAttribute(TAIKHOAN_ATTRIBUTE));
      map.put(RQP_DISCOUNT_PERCENT, SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
//      map.put("isOtpRequired", isOtpRequired);
      map.put(RQP_ORDER_ID, SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));
      SessionUtil.setAttribute(VERIFY_STATUS_ATTRIBUTE, false);
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);

      return URL_PIN_PAY_NEXTSTEP;
    }
  }

  @GetMapping(value = "/pin-pay-confirm") // confirm
  public String pinPayOtpPhoneGet(HttpServletRequest request, HttpServletResponse response,
                                  ModelMap map) throws Exception {
    Object confirmStatusAttr = SessionUtil.getAttribute(CONFIRM_STATUS_ATTRIBUTE);
    boolean confirmStatus = confirmStatusAttr != null && (boolean) confirmStatusAttr;

    Object transactionAttr = SessionUtil.getAttribute(TRANSACTION_ATTRIBUTE);
    Transaction transaction = transactionAttr != null ? (Transaction) transactionAttr : null;

    if (confirmStatus) {
      map.put("transactionsPinCode", transaction);
      map.put("cardSize", transaction.getSerials().size());
      map.put(RQP_TEN_SP, SessionUtil.getAttribute(TEN_SP_ATTRIBUTE));
      map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
      map.put(RQP_FACEVALUE, SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put(RQP_QUANTITY, SessionUtil.getAttribute(QUANTITY_ATTRIBUTE));
      map.put(RQP_DISCOUNT, SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
      map.put(RQP_REAL_AMOUNT, SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put(RQP_DISCOUNT_PERCENT, SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
      map.put("displayName", request.getParameter("displayName"));
      map.put("livingAddress", request.getParameter("livingAddress"));
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

      SessionUtil.removeAttribute(TEN_SP_ATTRIBUTE);
      SessionUtil.removeAttribute(SERVICE_CODE_CARD_ATTRIBUTE);
      SessionUtil.removeAttribute(FACE_VALUE_ATTRIBUTE);
      SessionUtil.removeAttribute(QUANTITY_ATTRIBUTE);
      SessionUtil.removeAttribute(DISCOUNT_ATTRIBUTE);
      SessionUtil.removeAttribute(REAL_AMOUNT_ATTRIBUTE);
      SessionUtil.removeAttribute(TAIKHOAN_ATTRIBUTE);
      SessionUtil.removeAttribute(SO_DU_VI_ATTRIBUTE);
      SessionUtil.removeAttribute(DISCOUNT_PERCENT_ATTRIBUTE);

      return "/pin_code/pin_pay_transactionSuccess";
    }
    map.put("error", SessionUtil.getAttribute(ERROR_ATTRIBUTE));
    map.put(RQP_CODE_ERR, SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
    map.put(RQP_TEN_SP, SessionUtil.getAttribute(TEN_SP_ATTRIBUTE));
    map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
    map.put(RQP_FACEVALUE, SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
    map.put(RQP_QUANTITY, SessionUtil.getAttribute(QUANTITY_ATTRIBUTE));
    map.put(RQP_DISCOUNT, SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
    map.put(RQP_REAL_AMOUNT, SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
    map.put(RQP_DISCOUNT_PERCENT, SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));

    return "/pin_code/pin_pay_transactionError";
  }

  @PostMapping(value = "/pin-pay-confirm") // confirm
  public String pinPayOtpPhone(HttpServletRequest request, HttpServletResponse response,
                               ModelMap map) throws Exception {
    String para_accessToken = null;
    TopupTransactionResponse pinCodeResponseType = new TopupTransactionResponse();
    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    if (userLogin != null) {
      para_accessToken = userLogin.getAccessToken();
    }
    try {
      String disCountPercent = request.getParameter(RQP_DISCOUNT_PERCENT);
      String paymentSecurityCode = request.getParameter("paymentSecurityCode");

      // Store to Session
      SessionUtil.setAttribute(DISCOUNT_PERCENT_ATTRIBUTE, disCountPercent);

      Object serviceCodeCardAttr = SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE);
      String serviceCodeCard = serviceCodeCardAttr != null ? serviceCodeCardAttr.toString() : null;
      Object faceValueAttr = SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE);
      String faceValue = faceValueAttr != null ? faceValueAttr.toString() : null;
      Object quantityAttr = SessionUtil.getAttribute(QUANTITY_ATTRIBUTE);
      String quantity = quantityAttr != null ? quantityAttr.toString() : null;
      Object orderIdAttr = SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE);
      String orderId = orderIdAttr != null ? orderIdAttr.toString() : null;
      Object paymentSecurityTypeAttr = SessionUtil.getAttribute(PAYMENT_SECURITY_TYPE_ATTRIBUTE);
      String paymentSecurityType = paymentSecurityTypeAttr != null ? paymentSecurityTypeAttr.toString() : null;

      String requestId = UUID.randomUUID().toString();
      TopupTransactionRequest topupTransactionRequest = new TopupTransactionRequest();
      topupTransactionRequest.setTerminalId("WEB");
      topupTransactionRequest.setRequestId(requestId);
      topupTransactionRequest.setServiceCode(serviceCodeCard);
      topupTransactionRequest.setPrice(Long.valueOf(faceValue));
      topupTransactionRequest.setQuantity(Integer.valueOf(quantity));
      topupTransactionRequest.setOrderId(Long.valueOf(orderId));
      topupTransactionRequest.setActionType(ACTION_CONFIRM);
      if (paymentSecurityType.equals("OTP")) {
        topupTransactionRequest.setOtp(paymentSecurityCode);
      }
//      duongdp 12022019
      else if (paymentSecurityType.equals("PAYMENT_PASSWORD")) {
        topupTransactionRequest.setOtp(paymentSecurityCode);
      }
//      duongdp 12022019
      else {
        if (PaymentSecurityController.checkPaymentPIN(walletEndpoint)) {
          topupTransactionRequest.setOtp(paymentSecurityCode);
        }
      }
      pinCodeResponseType = walletEndpoint.pinCodeConfirm(topupTransactionRequest);
      if (pinCodeResponseType == null || pinCodeResponseType.getStatus().getCode() != 0) {
        if (pinCodeResponseType.getStatus().getCode() == 4501) {
          SessionUtil.setAttribute(ERROR_ATTRIBUTE, pinCodeResponseType.getStatus().getCode());
        }
        throw new Exception(pinCodeResponseType.getStatus().getValue());
      }
      Transaction transaction = pinCodeResponseType.getTransaction();

      List<Card> serials = transaction.getSerials();
      for (Card serial : serials) {
        serial.setPin(AES.decryptTripleDES(
            para_accessToken, serial.getPin()));
      }
      map.put("transactionsPinCode", transaction);
      SessionUtil.setAttribute(TRANSACTION_ATTRIBUTE,transaction);

      map.put("cardSize", transaction.getSerials().size());
      // end get list transaction

      map.put(RQP_CODE_ERR, SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
      map.put(RQP_TEN_SP, SessionUtil.getAttribute(TEN_SP_ATTRIBUTE));
      map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
      map.put(RQP_FACEVALUE, SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put(RQP_QUANTITY, SessionUtil.getAttribute(QUANTITY_ATTRIBUTE));
      map.put(RQP_DISCOUNT, SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
      map.put(RQP_REAL_AMOUNT, SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put(RQP_DISCOUNT_PERCENT, SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
      map.put(RQP_ORDER_ID, SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));

      // Put customer's info
      try {
        GetConsumerAccountResponse baseResponseType = walletEndpoint.accountInfoGet();
        if (baseResponseType.getStatus().getCode() != 0) {
          throw new Exception(baseResponseType.getStatus().getValue());
        }
        Customer customer = baseResponseType.getCustomer();
        map.put("displayName", customer.getDisplayName());
        map.put("livingAddress", customer.getLivingAddress());
      } catch (Exception ex) {
        LOGGER.error(ex.getMessage());
      }

      SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, true);

    } catch (Exception e) {
      String codeErr = e.getMessage();
      map.put(RQP_CODE_ERR, codeErr);
      map.put(RQP_TEN_SP, SessionUtil.getAttribute(TEN_SP_ATTRIBUTE));
      map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
      map.put(RQP_FACEVALUE, SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put(RQP_QUANTITY, SessionUtil.getAttribute(QUANTITY_ATTRIBUTE));
      map.put(RQP_DISCOUNT, SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
      map.put(RQP_REAL_AMOUNT, SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put(RQP_DISCOUNT_PERCENT, SessionUtil.getAttribute(DISCOUNT_PERCENT_ATTRIBUTE));
      map.put(RQP_ORDER_ID, SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE));
      SessionUtil.setAttribute(CONFIRM_STATUS_ATTRIBUTE, false);

      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
    }

    return "redirect:" + "/pin-code/pin-pay-confirm";
  }

  @PostMapping(value = "/pinPayOtpEmail")
  public String pinPayOtpEmail() throws Exception {

    return "/pin_code/pin_pay_otpEmail";
  }

  @PostMapping(value = "/pinPayNoOTP")
  public String pinPayNoOTP() throws Exception {

    return "/pin_code/pin_pay_noOTP";
  }

  @GetMapping(value = "/pinPayTransactionSuccess")
  public String pinPayTransactionSuccess(HttpServletRequest request, HttpServletResponse response,
                                         ModelMap map) throws Exception {
    try {

      String codeErr = request.getParameter(RQP_CODE_ERR);

      // Xử lý dữ liệu đầu vào
      Long total = 0L;
      Integer offset = 0;
      Integer limit = 20;
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
          StringUtils.trimToEmpty(request.getParameter(RQP_QUICK_SEARCH)).replaceAll("\\s+", " ");

      // Tạo request & set params vào request
      FindTransactionsRequest transactionsRequest =
          new FindTransactionsRequest();

      transactionsRequest.setOffset(offset);
      transactionsRequest.setLimit(limit);

      transactionsRequest.setTextSearch(null);
      transactionsRequest.setFromDate(null);
      transactionsRequest.setEndDate(null);

      // --
      String[] serviceTypeId = request.getParameterValues(SERVICE_TYPE_ID);
      if (serviceTypeId != null && serviceTypeId.length > 0
          && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
      }
      String[] serviceCode = request.getParameterValues(SERVICE_CODE);
      if (serviceCode != null && serviceCode.length > 0
          && !serviceCode[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
      }

      Collection<Transaction> listTransactionsPinCode = new ArrayList<>();

      FindTransactionsResponse baseTransResponseType = new FindTransactionsResponse();
      baseTransResponseType = transactionEndpoint.transactionFind(transactionsRequest);
      if (baseTransResponseType == null || baseTransResponseType.getStatus() == null
          || baseTransResponseType.getStatus().getCode() != 0) {
        LOGGER.error(" API transactionFind fail");
      } else {
        if (baseTransResponseType != null) {
          listTransactionsPinCode = baseTransResponseType.getTransactions();
        }
      }

      SessionUtil.setAttribute(LIST_TRANS_PINCODE_ATTRIBUTE, listTransactionsPinCode);
      map.put("listTransactionsPinCode", listTransactionsPinCode);
      map.put(RQP_TEN_SP, SessionUtil.getAttribute(TEN_SP_ATTRIBUTE));
      map.put(RQP_SERVICE_CODE_CARD, SessionUtil.getAttribute(SERVICE_CODE_CARD_ATTRIBUTE));
      map.put(RQP_FACEVALUE, SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE));
      map.put(RQP_QUANTITY, SessionUtil.getAttribute(QUANTITY_ATTRIBUTE));
      map.put(RQP_DISCOUNT, SessionUtil.getAttribute(DISCOUNT_ATTRIBUTE));
      map.put(RQP_REAL_AMOUNT, SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));


      SessionUtil.removeAttribute(TEN_SP_ATTRIBUTE);
      SessionUtil.removeAttribute(SERVICE_CODE_CARD_ATTRIBUTE);
      SessionUtil.removeAttribute(FACE_VALUE_ATTRIBUTE);
      SessionUtil.removeAttribute(QUANTITY_ATTRIBUTE);
      SessionUtil.removeAttribute(DISCOUNT_ATTRIBUTE);
      SessionUtil.removeAttribute(REAL_AMOUNT_ATTRIBUTE);
      SessionUtil.removeAttribute(TAIKHOAN_ATTRIBUTE);
      SessionUtil.removeAttribute(SO_DU_VI_ATTRIBUTE);
      SessionUtil.removeAttribute(DISCOUNT_PERCENT_ATTRIBUTE);

      return PINCODE_TRANSACTION_SUCCESS;
    } catch (Exception e) {
      String codeErr = e.getMessage();
      map.put(RQP_CODE_ERR, codeErr);
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      e.printStackTrace();
    }
    return PINCODE_TRANSACTION_SUCCESS;
  }

  @PostMapping(value = "/pinPayTransactionError")
  public String pinPayTransactionError() throws Exception {

    return PINCODE_INFO;
  }

  @PostMapping(value = "/pinPayTransactionProcessing")
  public String pinPayTransactionProcessing() throws Exception {
    return "/pin_code/pin_pay_transactionProcessing";
  }

  @GetMapping(value = "/pinPayLinkedCard")
  public String pinPayLinkedCard() throws Exception {
    return "/pin_code/pin_pay_LinkedCard";
  }

  @PostMapping(value = "/pinPayLinkedCardOTPPhone")
  public String pinPayLinkedCardOTPPhone() throws Exception {
    return "/pin_code/pin_pay_linkedCard_otpPhone";
  }

  @GetMapping(value = "/pinPayDomesticCard")
  public String pinPayDomesticCard() throws Exception {
    return "/pin_code/pin_pay_DomesticCard";
  }

  @PostMapping(value = "/pinPayDomesticCardNextStep")
  public String pinPayDomesticCardNextStep() throws Exception {
    return "/pin_code/pin_pay_DomesticCard_nextStep";
  }

  @PostMapping(value = "/pinPayDomesticCardOTPPhone")
  public String pinPayDomesticCardOTPPhone() throws Exception {
    return "/pin_code/pin_pay_DomesticCard_otpPhone";
  }

  @GetMapping(value = "/pinPayInternationalCard")
  public String pinPayInternationalCard() throws Exception {
    return "/pin_code/pin_pay_InternationalCard";
  }

  @PostMapping(value = "/pinPayInternationalCardNextStep")
  public String pinPayInternationalCardNextStep() throws Exception {
    return "/pin_code/pin_pay_InternationalCard_nextStep";
  }

  @GetMapping(value = "/getData")
  public String getData(HttpServletRequest request, ModelMap map) throws Exception {

    return "/pin_code/objects.txt";
  }

  @GetMapping(value = "/getListTransactionPinCode")
  public String getListTransactionPinCode(HttpServletRequest request, HttpServletResponse response,
                                          ModelMap map) throws Exception {
    try {
      // Xử lý dữ liệu đầu vào
      Long total = 0L;
      Integer offset = 0;
      Integer limit = 20;
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
          StringUtils.trimToEmpty(request.getParameter(RQP_QUICK_SEARCH)).replaceAll("\\s+", " ");

      // Tạo request & set params vào request
      FindTransactionsRequest transactionsRequest =
          new FindTransactionsRequest();

      transactionsRequest.setOffset(offset);
      transactionsRequest.setLimit(limit);

      transactionsRequest.setTextSearch(null);
      transactionsRequest.setFromDate(null);
      transactionsRequest.setEndDate(null);

      // --

      String[] serviceTypeId = request.getParameterValues(SERVICE_TYPE_ID);
      if (serviceTypeId != null && serviceTypeId.length > 0
          && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
      }
      String[] serviceCode = request.getParameterValues(SERVICE_CODE);
      if (serviceCode != null && serviceCode.length > 0
          && !serviceCode[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
      }

      transactionsRequest.setStatusIds(Collections.singletonList(TxnStatus.CLOSED));

      Collection<Transaction> listTransactionsPinCode = new ArrayList<>();

      FindTransactionsResponse baseTransResponseType;
      baseTransResponseType = transactionEndpoint.transactionFind(transactionsRequest);
      if (baseTransResponseType == null || baseTransResponseType.getStatus() == null
          || baseTransResponseType.getStatus().getCode() != 0) {
        LOGGER.error(" API transactionFind fail");
      } else {
        if (baseTransResponseType != null) {
          listTransactionsPinCode = baseTransResponseType.getTransactions();
        }
      }

      map.put("pagesize", limit);
      map.put("offset", offset);
      map.put("listTransactions", listTransactionsPinCode);
      map.put("total", total.intValue());
      map.put(RQP_QUICK_SEARCH, StringUtils.trimToNull(quickSearch));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return "/pin_code/pin_pay_transactionSuccess";
  }

  @RequestMapping(value = "/resend-otp", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> resendOTP(HttpServletRequest httpRequest) throws Exception {
    Long orderId = NumberUtil.convertToLong(httpRequest.getParameter(RQP_ORDER_ID));
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

  @RequestMapping(value = "/send-result/{send_type}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> sendResult(HttpServletRequest httpRequest,
                                      @PathVariable("send_type") String sendType) throws Exception {
    Integer sendTypeI = NumberUtil.convertToInt(sendType);
    String language = LocaleContextHolder.getLocale().getLanguage();
    String transactionId = httpRequest.getParameter("transaction_id");
    try {
      if (StringUtils.isNotBlank(transactionId)) {
        TransactionResultExportRequest request = new TransactionResultExportRequest();
        request.setSendMode(sendTypeI);
        request.setLanguage(language);
        request.setReceivedAddress(httpRequest.getParameter("receiver"));
        request.setTransactionId(NumberUtil.convertToLong(transactionId));

        TransactionResultExportResponse response = walletEndpoint.sendTransactionResult(request);

        return ResponseEntity
            .ok(new AjaxResponse(response.getStatus().getCode(), response.getStatus().getValue()));
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }

    return ResponseEntity.ok(new AjaxResponse(1, validation.notify(ERROR_OCCURS)));
  }

}
