package vn.mog.ewallet.consumer.web.controller.ajax;

import static vn.mog.ewallet.consumer.web.controller.system.PaymentSecurityController.REFERENCE_ID_ATTRIBUTE;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SECURE_KEY;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SECURE_REFERENCE_ID;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.mog.ewallet.consumer.web.contract.AjaxResponse;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.exception.MessageNotify;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IWalletEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.CustomerLinkBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.CustomerLinkBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.GetBankItemByCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.GetBankItemByCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.RegisterPaymentOnlineRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.RegisterPaymentOnlineResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.bean.CustomerBankItem;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.card.bean.PaymentOnlineRequired;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.GetStoreS3UrlRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.GetStoreS3UrlResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.UpdateAddressRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.UpdateAddressResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.UpdateCustomerPersonalInfoRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.UpdateCustomerPersonalInfoResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.OutletStoreType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.UpdateCustomerAdditionalInforRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.UpdateCustomerAdditionalInforResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.FindEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.FindEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderOTPConfirmRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderOTPConfirmResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.bean.FundOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetOTPRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetOTPResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.TrueServiceCommissionGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.TrueServiceCommissionGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.CardCommission;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.TrueServiceCommissionFeeDetail;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.DataTableResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindLocationRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindLocationResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.UpdateImageProfileCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.UpdateImageProfileCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Location;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgetPasswordOtpGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgetPasswordOtpGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.GetSetupOtpRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.GetSetupOtpResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.SecurityAccessActionType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.TypicalResendOtp;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.AddressType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.*;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.PhoneNumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.framework.contract.base.MobiliserResponseType;
import vn.mog.framework.contract.base.MobiliserResponseType.Status;

@RestController
@RequestMapping(value = "/ajax-controller")
public class AjaxController extends AbstractController {

  private static final String MESSAGE_OTP_ERROR = "Sending OTP function is error or invalid";

  private static final Logger LOGGER = LogManager.getLogger(AjaxController.class);
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

  private final String PHONE_NUMBER_REQ = "phoneNumber";
  private final String BANK_CODE_REQ = "bankCode";
  private final String ACCOUNT_NUMBER_REQ = "accountNumber";
  private final String FULL_NAME_REQ = "fullName";
  private final String CARD_ISSUE_DATE_REQ = "cardIssueDate";
  private final String CONFIG_REQ = "config";
  private final String QUICK_SEARCH_REQ = "quickSearch";
  private final String START_REQ = "start";
  private final String LENGTH_REQ = "length";
  private static final String REGEX_NUMBER = "[^0-9]+";

  //VALUE
  private static final String NO_RESPONSE_VALUE = "No response!";


  @Autowired
  private IWalletEndpoint walletEndpoint;

  @GetMapping(value = "/get-card-commission")
  public CardCommission getCardCommission(HttpServletRequest request) throws Exception {
    String serviceTypeId = request.getParameter("serviceTypeId");
    String cardName = request.getParameter("cardName");
    String serviceCodeCard = request.getParameter("serviceCodeCard");
    String phoneNumber = request.getParameter(PHONE_NUMBER_REQ);

    if (!StringUtils.isEmpty(cardName) && serviceTypeId.equals("pin_card")) {
      serviceCodeCard = PhoneNumberUtil.getPinCodeServiceIdByProductName(cardName);
    } else if (!StringUtils.isEmpty(cardName) && serviceTypeId.equals("top_up")) {
      serviceCodeCard = PhoneNumberUtil.getTopUpServiceIdByProductName(cardName);
    } else if (serviceTypeId.equals("game_card")) {
      serviceCodeCard = PhoneNumberUtil.getGameCardCodeServiceIdByProductName(cardName);
    } else if (serviceTypeId.equals("data_card")) {
      serviceCodeCard = PhoneNumberUtil.getDataCardCodeServiceIdByProductName(cardName);
    } else if (serviceTypeId.equals("epo_card")) {
      serviceCodeCard = PhoneNumberUtil.getEPOCardCodeServiceIdByProductName(cardName);
    } else if (StringUtils.isEmpty(serviceCodeCard)) {
      serviceCodeCard = "060000";
      if (StringUtils.isEmpty(phoneNumber)) {
        throw new Exception("You have to choose phone number!");
      }
    }
    CardCommission cardCommission = new CardCommission();
    TrueServiceCommissionGetRequest trueServiceCommissionGetRequest =
        new TrueServiceCommissionGetRequest();
    switch (serviceTypeId) {
      case "pin_card":
        trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.EPIN.name());
        break;
      case "game_card":
        trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.EPIN.name());
        break;
      case "data_card":
        trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.EPIN.name());
        break;
      case "top_up":
        trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.PHONE_TOPUP.name());
        break;
      case "epo_card":
        trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.EXPORT_EPIN.name());
        break;
      case "wallet_cash_in":
        trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.WALLET_CASH_IN.name());
        break;
      case "wallet_cash_out":
        trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.WALLET_CASH_OUT.name());
        break;
      default:
        trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.EPIN.name());
        break;
    }

    trueServiceCommissionGetRequest.setServiceCode(serviceCodeCard);
    if (!StringUtils.isEmpty(phoneNumber)) {
      trueServiceCommissionGetRequest.setPhoneNumber(phoneNumber);
    } else {
      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      if (StringUtils.isBlank(phoneNumber) && userLogin != null) {
        trueServiceCommissionGetRequest.setPhoneNumber(userLogin.getPhoneNumber());
      }
    }
    TrueServiceCommissionGetResponse baseResponseType = walletEndpoint.getTrueServiceCommission(trueServiceCommissionGetRequest);
    if (baseResponseType != null && baseResponseType.getStatus().getCode() == 0) {

      String[] pricesArrays = baseResponseType.getService().getServicePrices().split("([|])");
      List<String> prices = new ArrayList<>();
      for (String price : pricesArrays) {
        if (!StringUtils.isEmpty(price)) {
          prices.add(price);
        }
        cardCommission.setPrices(prices);
        Map<Integer, TrueServiceCommissionFeeDetail> commissionFeeMap
            = baseResponseType.getCommissionFeeMap();
        cardCommission.setCommissionFee(commissionFeeMap);
        cardCommission.setServiceDesc(baseResponseType.getService().getServiceDesc());
      }
    }
    return cardCommission;
  }

  @GetMapping(value = "/get-bank-item-by-customer")
  public List<CustomerBankItem> getBankItemByCustomer(HttpServletRequest request) throws Exception {
    List<CustomerBankItem> bankItems = new ArrayList<>();
    try {
      String transferType = request.getParameter("transferType");
      String bankCode = request.getParameter(BANK_CODE_REQ);
      GetBankItemByCustomerRequest getBankItemByCustomerRequest =
          new GetBankItemByCustomerRequest();
      getBankItemByCustomerRequest.setItemType(transferType);
      getBankItemByCustomerRequest.setBankCode(bankCode);
      getBankItemByCustomerRequest.setIsActive(true);
      getBankItemByCustomerRequest.setOffset(0);
      getBankItemByCustomerRequest.setLimit(3);

      GetBankItemByCustomerResponse getBankItemByCustomerResponseType = systemEndpoint
          .getBankItemByCustomer(getBankItemByCustomerRequest);
      if (getBankItemByCustomerResponseType != null
          && getBankItemByCustomerResponseType.getStatus().getCode() == 0) {
        bankItems = getBankItemByCustomerResponseType.getBankItems();
      }

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new Exception(e);
    }

    return bankItems;
  }

  @GetMapping(value = "/check-bank-payment-online")
  public PaymentOnlineRequired checkBankPaymentOnline(HttpServletRequest request) throws Exception {
    PaymentOnlineRequired onlineRequired = new PaymentOnlineRequired();
    onlineRequired.setValue(false);
    try {
      // request truyen tu form
      String accountNumber = request.getParameter(ACCOUNT_NUMBER_REQ);
      if (StringUtils.isNotBlank(accountNumber)) {
        accountNumber = accountNumber.replaceAll(REGEX_NUMBER, "");
      }
      String linkType = request.getParameter("linkType");
      String fullName = request.getParameter(FULL_NAME_REQ);
      String phoneNumber = request.getParameter(PHONE_NUMBER_REQ);
      String cardIssueDate = request.getParameter(CARD_ISSUE_DATE_REQ);
      if (cardIssueDate.equalsIgnoreCase("/") || StringUtils.isEmpty(cardIssueDate)) {
        cardIssueDate = "";
      }
      String bankCode = request.getParameter(BANK_CODE_REQ);
      String ssn = request.getParameter("ssn");

      String requestId = UUID.randomUUID().toString();
      String requestTime = sdf.format(new Date());

      CustomerLinkBankRequest customerLinkBankRequest = new CustomerLinkBankRequest();
      customerLinkBankRequest.setRequestId(requestId);
      customerLinkBankRequest.setRequestTime(requestTime);
      customerLinkBankRequest.setAccesskey(CONFIG_REQ);
      customerLinkBankRequest.setSignature(CONFIG_REQ);
      customerLinkBankRequest.setSecretkey(CONFIG_REQ);
      customerLinkBankRequest.setVerifyRequestId(null);
      customerLinkBankRequest.setBankCode(bankCode);
      customerLinkBankRequest.setWalletId(phoneNumber); // wallet = phone
      customerLinkBankRequest.setAccountNumber(accountNumber);
      customerLinkBankRequest.setSsn(ssn);
      customerLinkBankRequest.setFullName(fullName);
      customerLinkBankRequest.setPhoneNumber(phoneNumber);
//      if (bankCode.equalsIgnoreCase("BIDVVNVX")) {
//        customerLinkBankRequest.setLinkType("ACCOUNT");
//      } else {
//        customerLinkBankRequest.setLinkType("CARD");
//      }
      customerLinkBankRequest.setType("verify");
      customerLinkBankRequest.setLinkType(linkType);
      if (StringUtils.isNotBlank(cardIssueDate)) {
        customerLinkBankRequest.setCardIssueDate(cardIssueDate);
      } else {
        customerLinkBankRequest.setCardIssueDate("null");
      }
      customerLinkBankRequest.setChannel("WEB");
      customerLinkBankRequest.setDescription(phoneNumber + "gửi yêu cầu liên kết");
      customerLinkBankRequest.setOtp(null);
      CustomerLinkBankResponse baseResponseType = walletEndpoint
          .customerLinkBank(customerLinkBankRequest);

      // --- check base response type;
      if (baseResponseType == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      }

      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }
      Boolean paymentOnlineRequired = baseResponseType.getRegisterPaymentOnlineRequired();
      if (paymentOnlineRequired == null) {
        paymentOnlineRequired = false;
      }
      onlineRequired.setValue((paymentOnlineRequired) && bankCode.equalsIgnoreCase("ICBVVNVX"));

      // onlineRequired = true thì show form đăng ký payment online
      return onlineRequired;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    return onlineRequired;
  }

  @GetMapping(value = "/register-payment-online-verify")
  public RegisterPaymentOnlineResponse registerPaymentOnlineVerify(HttpServletRequest request,
                                                                   HttpServletResponse response, ModelMap map) throws Exception {
    RegisterPaymentOnlineResponse registerPaymentOnlineResponse = null;
    try {
      // request truyen tu form
      String accountNumber = request.getParameter(ACCOUNT_NUMBER_REQ).replaceAll(REGEX_NUMBER, "");
      String fullName = request.getParameter(FULL_NAME_REQ);
      String phoneNumber = request.getParameter(PHONE_NUMBER_REQ);
      String cardIssueDate = request.getParameter(CARD_ISSUE_DATE_REQ);
      String bankCode = request.getParameter(BANK_CODE_REQ);
      String ssn = request.getParameter("ssn");

      String requestId = UUID.randomUUID().toString();
      String requestTime = sdf.format(new Date());

      RegisterPaymentOnlineRequest registerPaymentOnlineRq = new RegisterPaymentOnlineRequest();
      registerPaymentOnlineRq.setWalletId(phoneNumber); // wallet = phone

      registerPaymentOnlineRq.setRequestTime(requestTime);

      registerPaymentOnlineRq.setRequestId(requestId);

      registerPaymentOnlineRq.setVerifyRequestId(null);

      registerPaymentOnlineRq.setSsn(ssn);

      registerPaymentOnlineRq.setFullName(fullName);

      registerPaymentOnlineRq.setAccountNumber(accountNumber);

      if (StringUtils.isNotBlank(cardIssueDate)) {
        registerPaymentOnlineRq.setCardIssueDate(cardIssueDate);
      } else {
        registerPaymentOnlineRq.setCardIssueDate("null");
      }
      registerPaymentOnlineRq.setOtp(null);
      registerPaymentOnlineRq.setBankCode(bankCode);
      registerPaymentOnlineRq.setChannel("WEB");

      registerPaymentOnlineRq.setType("verify");

      registerPaymentOnlineRq.setDescription(phoneNumber + "gửi yêu cầu liên kết");

      registerPaymentOnlineResponse = walletEndpoint.registerPaymentOnline(registerPaymentOnlineRq);

      /* check base response type;*/
      if (registerPaymentOnlineResponse == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      }

      if (registerPaymentOnlineResponse.getStatus().getCode() != 0) {
        throw new Exception(registerPaymentOnlineResponse.getStatus().getValue());
      }
      if (!registerPaymentOnlineResponse.getIsOtpRequired()) {
        registerPaymentOnlineConfirm(request, response, map);
      }

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new Exception(e);
    }
    return registerPaymentOnlineResponse;
  }

  @GetMapping(value = "/register-payment-online-confirm")
  public Status registerPaymentOnlineConfirm(HttpServletRequest request,
                                             HttpServletResponse response, ModelMap map) throws Exception {
    RegisterPaymentOnlineResponse baseResponseType;
    try {

      // request truyen tu form
      String codeOTP = request.getParameter("codeOTP");
      String accountNumber = request.getParameter(ACCOUNT_NUMBER_REQ).replaceAll(REGEX_NUMBER, "");
      String fullName = request.getParameter(FULL_NAME_REQ);
      String phoneNumber = request.getParameter(PHONE_NUMBER_REQ);
      String cardIssueDate = request.getParameter(CARD_ISSUE_DATE_REQ);
      String bankCode = request.getParameter(BANK_CODE_REQ);
      String ssn = request.getParameter("ssn");

      String requestId = UUID.randomUUID().toString();
      String requestTime = sdf.format(new Date());
      String verifyRequestId = UUID.randomUUID().toString();

      RegisterPaymentOnlineRequest registerPaymentOnlineRq = new RegisterPaymentOnlineRequest();
      registerPaymentOnlineRq.setWalletId(phoneNumber); // wallet = phone

      registerPaymentOnlineRq.setRequestTime(requestTime);

      registerPaymentOnlineRq.setRequestId(requestId);

      registerPaymentOnlineRq.setVerifyRequestId(verifyRequestId);

      registerPaymentOnlineRq.setSsn(ssn);

      registerPaymentOnlineRq.setFullName(fullName);

      registerPaymentOnlineRq.setAccountNumber(accountNumber);

      if (StringUtils.isNotBlank(cardIssueDate)) {
        registerPaymentOnlineRq.setCardIssueDate(cardIssueDate);
      } else {
        registerPaymentOnlineRq.setCardIssueDate("null");
      }
      if (StringUtils.isNotBlank(codeOTP)) {
        registerPaymentOnlineRq.setOtp(codeOTP);
      } else {
        registerPaymentOnlineRq.setOtp("123456");
      }
      registerPaymentOnlineRq.setBankCode(bankCode);
      registerPaymentOnlineRq.setChannel("WEB");

      registerPaymentOnlineRq.setType("confirm");

      registerPaymentOnlineRq.setDescription(phoneNumber + "gửi yêu cầu liên kết");

      baseResponseType = walletEndpoint.registerPaymentOnline(registerPaymentOnlineRq);

      // --- check base response type;
      if (baseResponseType == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      }
      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }

      return baseResponseType.getStatus();
    } catch (Exception e) {
      MobiliserResponseType.Status status = new MobiliserResponseType.Status();
      status.setCode(1);
      status.setValue("FAILED");

      return status;
    }
  }

  @SuppressWarnings("unchecked")
  @GetMapping(value = "/payment-security/sms/limitation")
  public SecurityPaymentGetOTPResponse paymentSec(HttpServletRequest request,
                                                  HttpServletResponse response, ModelMap map)
      throws Exception {

    String passWord = request.getParameter("passWord");
    SecurityPaymentGetOTPRequest securityPaymentGetOTPRequest = new SecurityPaymentGetOTPRequest();
    securityPaymentGetOTPRequest.setPassword(passWord);
    SecurityPaymentGetOTPResponse securityPaymentGetOTPResponse = walletEndpoint
        .securityPaymentGetOTP(securityPaymentGetOTPRequest);
    if (securityPaymentGetOTPResponse == null) {
      throw new Exception("Login failed");
    } else if (securityPaymentGetOTPResponse.getStatus().getCode() != 0) {
      throw new Exception(securityPaymentGetOTPResponse.getStatus().getValue());
    } else {
      map.put("referenceId", securityPaymentGetOTPResponse.getReferenceId());

      SessionUtil
          .setAttribute(REFERENCE_ID_ATTRIBUTE, securityPaymentGetOTPResponse.getReferenceId());
      return securityPaymentGetOTPResponse;
    }
  }

  @SuppressWarnings("unchecked")
  @GetMapping(value = "/payment-security/get-OTP-for-pin-pay")
  public GetSetupOtpResponse getOTPForPinPatSetup(HttpServletRequest request, HttpServletResponse response,
                                                  ModelMap map)
      throws Exception {
    GetSetupOtpResponse getSetupPaymentPinOtpResponse = new GetSetupOtpResponse();
    try {
      String passWord = request.getParameter("passWord");
      GetSetupOtpRequest getSetupOtpRequest = new GetSetupOtpRequest();
      getSetupOtpRequest.setActionType(SecurityAccessActionType.SETUP_PAYMENT_PIN);
      getSetupOtpRequest.setPassword(passWord);

      getSetupPaymentPinOtpResponse = walletEndpoint.getSecurityOTP(getSetupOtpRequest);

      SessionUtil.setAttribute(SECURE_REFERENCE_ID, getSetupPaymentPinOtpResponse.getReferenceId());
      SessionUtil.setAttribute(SECURE_KEY, getSetupPaymentPinOtpResponse.getKey());

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
    return getSetupPaymentPinOtpResponse;
  }

//  @SuppressWarnings("unchecked")
//  private GetSetupOtpResponse getOTPForSecure(SecurityAccessActionType actionType,
//      String password)
//      throws Exception {
//    GetSetupOtpRequest getSetupOtpRequest = new GetSetupOtpRequest();
//    getSetupOtpRequest.setActionType(actionType);
//    getSetupOtpRequest.setPassword(password);
//
//    return walletEndpoint.getSecurityOTP(getSetupOtpRequest);
//  }

  @SuppressWarnings("unchecked")
  @PostMapping(value = "/fundOrder/find")
  public DataTableResponse<FundOrder> getListFundOrder(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       ModelMap map) throws Exception {

//    Enumeration<String> params = request.getParameterNames();
//    while(params.hasMoreElements()){
//      String paramName = params.nextElement();
//      System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
//    }

    FindFundOrderRequest findFundOrderRequest = new FindFundOrderRequest();

    putFindFundOrderRequest(request, findFundOrderRequest);

    FindFundOrderResponse findFundOrderResponseType = walletEndpoint
        .findCustomerFundOrders(findFundOrderRequest);

    if (findFundOrderResponseType == null) {
      throw new Exception(NO_RESPONSE_VALUE);
    } else if (findFundOrderResponseType.getStatus().getCode() != 0) {
      throw new Exception(findFundOrderResponseType.getStatus().getValue());
    } else {

      DataTableResponse<FundOrder> responseData = new DataTableResponse<>();
      Long count = findFundOrderResponseType.getCount();
      count = count != null ? count : 0L;
      List<FundOrder> fundOrders = findFundOrderResponseType.getFundOrders();
      fundOrders = fundOrders != null ? fundOrders : Collections.emptyList();
      responseData.setRecordsTotal(count);
      responseData.setRecordsFiltered(count);
      responseData.setDataList(fundOrders);

      return responseData;
    }
  }

  private void putFindFundOrderRequest(HttpServletRequest request, FindFundOrderRequest findFundOrderRequest) throws Exception {
    String dateTime = request.getParameter("date_time");
    if (StringUtils.isNotBlank(dateTime)) {
      String fromString = dateTime.split(" - ")[0];
      String toString = dateTime.split(" - ")[1];

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
      findFundOrderRequest.setFromDate(sdf.parse(fromString));
      findFundOrderRequest.setEndDate(sdf.parse(toString));
    }

    String quickSearch = request.getParameter(QUICK_SEARCH_REQ);
    findFundOrderRequest.setSearchText(StringUtils.trimToNull(quickSearch));

    String[] stages = request.getParameterValues("flow_stage[]");
    List<Integer> stageList = new ArrayList<>();
    if (stages != null) {
      for (String stage : stages) {
        if (StringUtils.isNotBlank(stage)) {
          stageList.add(Integer.parseInt(stage));
        }
      }
      findFundOrderRequest.setStages(stageList);
    }

    String[] statusArray = request.getParameterValues("txn_status[]");
    List<Integer> statusList = new ArrayList<>();
    if (statusArray != null) {
      for (String status : statusArray) {
        if (StringUtils.isNotBlank(status)) {
          statusList.add(Integer.parseInt(status));
        }
      }
      findFundOrderRequest.setRefTxnStatusIds(statusList);
    }

    findFundOrderRequest
        .setServiceTypeIds(Arrays.asList(request.getParameterValues("service_type_id[]")));
    findFundOrderRequest
        .setOrderChannelIds(Arrays.asList(request.getParameterValues("order_channel_id[]")));
    findFundOrderRequest.setOffset(Integer.parseInt(request.getParameter(START_REQ)));
    findFundOrderRequest.setLimit(Integer.parseInt(request.getParameter(LENGTH_REQ)));

  }

  @RequestMapping(value = "/customer/transaction-history", method = RequestMethod.POST)
  public DataTableResponse<Transaction> findUmgrCustomers(HttpServletRequest request)
      throws Exception {
//    Enumeration<String> params = request.getParameterNames();
//    while (params.hasMoreElements()) {
//      String paramName = params.nextElement();
//      System.out.println(
//          "Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//    }
    String prmStatisticReport = request.getParameter("statisticReport");

    FindTransactionsRequest findTransactionsRequest = new FindTransactionsRequest();
    Date fromDate = null;
    Date endDate = null;
    String searchRange = request.getParameter("date");

    if (StringUtils.isNotBlank(searchRange)) {
      Date[] dates = parseDateRange(searchRange);
      fromDate = dates[0];
      endDate = dates[1];
    }
    String quickSearch =
        StringUtils.trimToEmpty(request.getParameter(QUICK_SEARCH_REQ)).replaceAll("\\s+", " ");

    String status = request.getParameter("status");

    findTransactionsRequest.setEndDate(endDate);
    findTransactionsRequest.setFromDate(fromDate);

    if (StringUtils.isNotEmpty(quickSearch)) {
      findTransactionsRequest.setTextSearch(quickSearch);
    }

    if ("true".equalsIgnoreCase(prmStatisticReport)) {
      findTransactionsRequest.setStatusIds(Arrays.asList(10));
    } else {
      if (StringUtils.isNotBlank(status)/* && !"-1".equals(status) */) {
        List<Integer> statusList = new ArrayList<>();
        statusList.add(Integer.parseInt(status));
        findTransactionsRequest.setStatusIds(statusList);
      }
    }
    findTransactionsRequest.setOffset(Integer.parseInt(request.getParameter(START_REQ)));
    findTransactionsRequest.setLimit(Integer.parseInt(request.getParameter(LENGTH_REQ)));
    String[] serviceTypeId = request.getParameterValues("serviceTypeId[]");
    if (serviceTypeId != null && serviceTypeId.length > 0
        && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
      findTransactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
    } else {
      if ("true".equalsIgnoreCase(prmStatisticReport)) {
        findTransactionsRequest.setServiceTypeIds(Arrays.asList(
            PHONE_TOPUP.name(), EPIN.name(),
            BILL_PAYMENT.name(), WALLET_CASH_IN.name(), WALLET_CASH_OUT.name()));
      } else {
        findTransactionsRequest.setServiceTypeIds(Arrays.asList(FUND_IN.name(), FUND_OUT.name(),
            PHONE_TOPUP.name(), EPIN.name(), /*WALLET_TRANSFER.name(),*/
            P2P_TRANSFER.name(), /*EXPORT_EPIN.name(),*/ BILL_PAYMENT.name(),
            CASH_BACK.name(), WALLET_CASH_IN.name(), WALLET_CASH_OUT.name(), TXN_REVERSAL.name()));
      }
    }
//    String[] serviceCode = request.getParameterValues("serviceCode");
//    if (serviceCode != null && serviceCode.length > 0
//        && !serviceCode[0].equals(StringUtils.EMPTY)) {
//      findTransactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
//    }

    FindTransactionsResponse findTransactionsResponse = transactionEndpoint.transactionFind(findTransactionsRequest);
    DataTableResponse<Transaction> response = new DataTableResponse<>();

    Long total = findTransactionsResponse.getTotalTxn();
    response.setRecordsTotal(total);
    response.setRecordsFiltered(total);
    response.setDataList(findTransactionsResponse.getTransactions());
    response.setTotalAmount(findTransactionsResponse.getTotalNetAmount() != null ? findTransactionsResponse.getTotalNetAmount() : 0);
    response.setTotalRealAmount(findTransactionsResponse.getTotalNetAmount() != null ? findTransactionsResponse.getTotalNetAmount() : 0);
    response.setTotalCommission(findTransactionsResponse.getTotalCommision() != null ? findTransactionsResponse.getTotalCommision() : 0);

    Long totalCashBackAmount = totalCashBackAmount(request);
    response.setTotalCashBackAmount(totalCashBackAmount);
    return response;
  }

  @RequestMapping(value = "/batch-cards/list", method = RequestMethod.POST)
  public DataTableResponse<EpinPurchaseOrder> findEpinPurchaseOrder(
      HttpServletRequest request)
      throws Exception {
    String paramText = request.getParameter(QUICK_SEARCH_REQ);
    String[] paramStatusIds = request.getParameterValues("statusIds[]");
    String[] paramStageIds = request.getParameterValues("stageIds[]");
    String[] paramCustomerIds = request.getParameterValues("customerIds[]");
    String storeType = request.getParameter("storeType");

    FindEpinPurchaseOrderRequest findRequest = new FindEpinPurchaseOrderRequest();

    Date fromDate = null;
    Date endDate = null;
    String searchRange = request.getParameter("range");

    if (StringUtils.isNotBlank(searchRange)) {
      Date[] dates = parseDateRange(searchRange);
      fromDate = dates[0];
      endDate = dates[1];
    }
    findRequest.setFromDate(fromDate);
    findRequest.setToDate(endDate);

    if (paramCustomerIds != null && paramCustomerIds.length > 0 && StringUtils
        .isNotEmpty(paramCustomerIds[0])) {
      findRequest.setCustomerIds(NumberUtil.convertListToLong(paramCustomerIds));
    }

    if (paramStatusIds != null && paramStatusIds.length > 0 && StringUtils
        .isNotEmpty(paramStatusIds[0])) {
      findRequest.setStatusIds(NumberUtil.convertListToInt(paramStatusIds));
    }

    if (paramStageIds != null && paramStageIds.length > 0 && StringUtils
        .isNotEmpty(paramStageIds[0])) {
      findRequest.setStageIds(NumberUtil.convertListToInt(paramStageIds));
    }

    findRequest.setStoreTypes(Collections.singletonList(storeType));

    findRequest.setQuickSearch(paramText);
    findRequest.setLimit(Integer.parseInt(request.getParameter(LENGTH_REQ)));
    findRequest.setOffset(Integer.parseInt(request.getParameter(START_REQ)));

    FindEpinPurchaseOrderResponse baseResponseType = epinEndpoint
        .findEpinPOs(findRequest);

    DataTableResponse<EpinPurchaseOrder> response = new DataTableResponse<>();

    Long total = baseResponseType.getCount();
    response.setRecordsTotal(total);
    response.setRecordsFiltered(total);
    response.setDataList(baseResponseType.getPurchaseOrders());

    return response;
  }

  @RequestMapping(value = "/customer/security/resend-otp/{otp_type}/{msisdn}", method = RequestMethod.GET)
  public ResponseEntity<?> securityResendOTP(HttpServletRequest servletRequest,
                                             @PathVariable("otp_type") Integer typeOtp,
                                             @PathVariable("msisdn") String msisdn) {

    try {
      String validMessage = PhoneNumberUtil.isValidPhoneNoBase(msisdn);
      if (StringUtils.isNotEmpty(validMessage)) {
        //Fail case
        return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, validMessage));
      }

      TypicalResendOtp typicalResendOtp = TypicalResendOtp.invalidType(typeOtp);
      if (typicalResendOtp != null) {

        String reference = UUID.randomUUID().toString();
        SessionUtil.setAttribute(SECURE_REFERENCE_ID, reference);
        /*if (typicalResendOtp == TypicalResendOtp.CREATE_ACCOUNT) {

          SendAccountCreationOtpRequest request = new SendAccountCreationOtpRequest(msisdn, reference);
          SendAccountCreationOtpResponse response = consumerEndpoint.sendAccountCreationOtp(request);
          return bodyResponseResendOtp(response, reference);

        } else if (typicalResendOtp == TypicalResendOtp.TWO_FACTOR) {

          GetLoginTwoFactorOtpRequest request = new GetLoginTwoFactorOtpRequest(msisdn, reference);
          GetLoginTwoFactorOtpResponse response = consumerEndpoint.getLoginTwoFactorOtp(request);
          return bodyResponseResendOtp(response, reference);

        } else*/
        if (typicalResendOtp == TypicalResendOtp.FORGOT_PASSWORD) {

          ForgetPasswordOtpGetRequest request = new ForgetPasswordOtpGetRequest();
          request.setPhoneNumber(msisdn);
          ForgetPasswordOtpGetResponse response = walletEndpoint.forgotPassGetOTP(request);
          return bodyResponseResendOtp(response, reference);

        } else if (typicalResendOtp == TypicalResendOtp.CHANGE_PASSWORD) {

          GetSetupOtpRequest request = new GetSetupOtpRequest();
          request.setActionType(SecurityAccessActionType.CHANGE_PASSWORD);
          Object key = SessionUtil.getAttribute(SECURE_KEY);
          request.setKey(key != null ? key.toString() : StringUtils.EMPTY);
          request.setReference(reference);
          GetSetupOtpResponse response = walletEndpoint.getSecurityOTP(request);

          return bodyResponseResendOtp(response, reference);
        } /*else if (typicalResendOtp == TypicalResendOtp.PAYMENT_PIN) {

          GetSetupOtpRequest request = new GetSetupOtpRequest();
          request.setActionType(SecurityAccessActionType.SETUP_PAYMENT_PIN);
          request.setKey(otpKey);
          request.setReference(reference);
          GetSetupPaymentPinOtpResponse response = walletEndpoint.getSetupPaymentPinOtp(request);
          return bodyResponseResendOtp(response, reference);
        }*/
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
    return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, MESSAGE_OTP_ERROR));
  }

  @RequestMapping(value = "/get-location/{parentCode}/{locationType}", method = RequestMethod.GET)
  public Collection<Location> getLocation(HttpServletRequest request,
                                          @PathVariable("parentCode") String parentCode,
                                          @PathVariable("locationType") Integer locationType) throws Exception {
    FindLocationRequest findLocationRequest = new FindLocationRequest();
    findLocationRequest.setParentCode(parentCode);
    findLocationRequest.setLocationTypeId(locationType);
    findLocationRequest.setCodes(null);
    FindLocationResponse findLocationBaseRespType = systemEndpoint
        .findLocations(findLocationRequest);

    return findLocationBaseRespType.getLocations();
  }

  @Async("false")
  @RequestMapping(value = "/profile/image-profile/update", method = RequestMethod.POST)
  public String updateProfileImage(HttpServletRequest request)
      throws Exception {
    String response = StringUtils.EMPTY;

    try {
      UpdateImageProfileCustomerRequest updateImageProfileCustomerRequest = new UpdateImageProfileCustomerRequest();
      String content64 = request.getParameter("content");
      String content = content64;
      String[] contentArray = content.split(",");
      if (contentArray.length > 1) {
        content = contentArray[1];
      }
      updateImageProfileCustomerRequest.setContent(content);
      updateImageProfileCustomerRequest.setContentType(request.getParameter("content_type"));
      updateImageProfileCustomerRequest.setName(request.getParameter("name"));
      UpdateImageProfileCustomerResponse updateImageProfileCustomer = systemEndpoint
          .updateProfileImage(updateImageProfileCustomerRequest);
      if (updateImageProfileCustomer.getStatus().getCode() != 0) {
        throw new Exception(updateImageProfileCustomer.getStatus().getValue());
      }
      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      if (userLogin != null) {
        if (updateImageProfileCustomer.getKeyS3() == null) {
          response = content64;
          userLogin.setDataImage(content64);
        } else {
          GetStoreS3UrlRequest requestS3Url = new GetStoreS3UrlRequest();
          requestS3Url.setCustomerId(null);
          requestS3Url.setCif(null);
          GetStoreS3UrlResponse baseResS3Url = customerEndpoint
              .accountImageProfileGet(requestS3Url);
          if (baseResS3Url != null && baseResS3Url.getStatus().getCode() == 0) {
            response = baseResS3Url.getS3Url();
            userLogin.setS3Url(baseResS3Url.getS3Url());
          }
        }

        SessionUtil.setAttribute(SESSION_ACCOUNT_LOGIN, userLogin);
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
    }

    return response;
  }

  @RequestMapping(value = "/profile/update", method = RequestMethod.POST)
  public UpdateCustomerPersonalInfoResponse updateProfile(HttpServletRequest request)
      throws Exception {
    UpdateCustomerPersonalInfoResponse updateImageProfileCustomerResponse = new UpdateCustomerPersonalInfoResponse();
    try {
      UpdateCustomerPersonalInfoRequest updateCustomerPersonalInfoRequest = new UpdateCustomerPersonalInfoRequest();
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String displayName = request.getParameter("displayName");
      String gender = request.getParameter("gender");
      String dateOfBirth = request.getParameter("dateOfBirth");
      String livingAddress = request.getParameter("livingAddress");
      String province = request.getParameter("province");
      String district = request.getParameter("district");
      String commune = request.getParameter("commune");
      String jobOccupation = request.getParameter("jobOccupation");
      String jobPosition = request.getParameter("jobPosition");
      String email = request.getParameter("email");

      String residenceProvince = request.getParameter("residence_province");
      String residenceDistrict = request.getParameter("residence_district");
      String residenceCommune = request.getParameter("residence_commune");
      String residenceStreet1 = request.getParameter("residence_street1");

      updateCustomerPersonalInfoRequest.setFirstName(firstName);
      updateCustomerPersonalInfoRequest.setLastName(lastName);
      updateCustomerPersonalInfoRequest.setDisplayName(displayName);
      updateCustomerPersonalInfoRequest.setGender(NumberUtil.convertToInt(gender));
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      if (StringUtils.isBlank(dateOfBirth)) {
        updateCustomerPersonalInfoRequest.setDateOfBirth(null);
      } else {
        updateCustomerPersonalInfoRequest.setDateOfBirth(sdf.parse(dateOfBirth));
      }
      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      if (userLogin != null) {
        updateCustomerPersonalInfoRequest.setMsisdn(userLogin.getPhoneNumber());
      }
      updateCustomerPersonalInfoRequest.setEmail(email);
      updateCustomerPersonalInfoRequest.setLivingAddress(livingAddress);
      updateCustomerPersonalInfoRequest.setProvince(province);
      updateCustomerPersonalInfoRequest.setDistrict(district);
      updateCustomerPersonalInfoRequest.setCommune(commune);
      updateCustomerPersonalInfoRequest.setState(null);
      updateCustomerPersonalInfoRequest.setJobOccupation(jobOccupation);
      updateCustomerPersonalInfoRequest.setJobPosition(jobPosition);

      Address address = new Address();
      address.setAddressType(AddressType.RESIDENCE_ADDRESS.getCode());
      address.setProvince(residenceProvince);
      address.setDistrict(residenceDistrict);
      address.setCommune(residenceCommune);
      address.setStreet1(residenceStreet1);

      List<Address> addresses = new ArrayList<>();
      addresses.add(address);

      updateCustomerPersonalInfoRequest.setAddresses(addresses);

      updateImageProfileCustomerResponse = systemEndpoint
          .updateProfile(updateCustomerPersonalInfoRequest);
      if (updateImageProfileCustomerResponse.getStatus().getCode() != 0) {
        throw new Exception(updateImageProfileCustomerResponse.getStatus().getValue());
      }

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
    }

    return updateImageProfileCustomerResponse;
  }

  @RequestMapping(value = "/bank-manage/list", method = RequestMethod.POST)
  public DataTableResponse<Transaction> findBankManageList(
      HttpServletRequest request)
      throws Exception {
    Collection<Transaction> listTransactionsCard;
    FindTransactionsRequest findRequest = new FindTransactionsRequest();
    Date fromDate = null;
    Date endDate = null;
    String searchRange = request.getParameter("range");
    if (StringUtils.isNotBlank(searchRange)) {
      Date[] dates = parseDateRange(searchRange);
      fromDate = dates[0];
      endDate = dates[1];
    }

    String quickSearch = StringUtils.trimToEmpty(request.getParameter(QUICK_SEARCH_REQ))
        .replaceAll("\\s+", " ");

    String[] serviceTypeId = request.getParameterValues("serviceTypeId[]");
    if (serviceTypeId != null && serviceTypeId.length > 0
        && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
      findRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
    }

    String[] serviceCode = request.getParameterValues("serviceCode[]");
    if (serviceCode != null && serviceCode.length > 0 && !serviceCode[0]
        .equals(StringUtils.EMPTY)) {
      findRequest.setServiceCodes(Arrays.asList(serviceCode));
    }

    findRequest.setOffset(Integer.parseInt(request.getParameter(START_REQ)));
    findRequest.setLimit(Integer.parseInt(request.getParameter(LENGTH_REQ)));
    findRequest.setFromDate(fromDate);
    findRequest.setEndDate(endDate);

    FindTransactionsResponse baseTransResponseType = transactionEndpoint
        .transactionFind(findRequest);
    if (baseTransResponseType == null || baseTransResponseType.getStatus().getCode() != 0) {
      throw new Exception(
          (baseTransResponseType != null && baseTransResponseType.getStatus() != null)
              ? baseTransResponseType.getStatus().getValue() : "API transactionFind fail");
    } else {
      listTransactionsCard = baseTransResponseType.getTransactions();
    }
    DataTableResponse<Transaction> response = new DataTableResponse<>();

    Long total = baseTransResponseType.getTotalTxn();
    response.setRecordsTotal(total);
    response.setRecordsFiltered(total);
    response.setDataList(listTransactionsCard);

    return response;
  }

  @RequestMapping(value = "/batch-cards/resend-otp/{poMerchantId}", method = RequestMethod.GET)
  public ResponseEntity batchCardResendOTP(HttpServletRequest request,
                                           @PathVariable("poMerchantId") Long epinId) throws Exception {
    String reference = UUID.randomUUID().toString();
    if (epinId > 0) {
      GetEpinPurchaseOrderOTPConfirmRequest geoRequest = new GetEpinPurchaseOrderOTPConfirmRequest();
      geoRequest.setOrderId(epinId);
      GetEpinPurchaseOrderOTPConfirmResponse response = epinEndpoint.getOTPConfirm(geoRequest);
      if (response == null) {
        throw new Exception(NO_RESPONSE_VALUE);
      } else if (response.getStatus().getCode() != 0) {
        throw new Exception(response.getStatus().getValue());
      }
      return bodyResponseResendOtp(response, reference);
    } else {
      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, MESSAGE_OTP_ERROR));
    }
  }


  /*KYC*/

  @RequestMapping(value = "/kyc/update/additional-info", method = RequestMethod.POST)
  public UpdateCustomerAdditionalInforResponse updateAdditionalInfo(HttpServletRequest request)
      throws Exception {
    UpdateCustomerAdditionalInforResponse updateKycAdditionInfResponse = new UpdateCustomerAdditionalInforResponse();
    UpdateCustomerAdditionalInforRequest updateCustomerAdditionalInforRequest = new UpdateCustomerAdditionalInforRequest();
    try {

      String email = request.getParameter("email");
      String livingAddress = request.getParameter("livingAddress");
      String province = request.getParameter("province");
      String commune = request.getParameter("commune");
      String district = request.getParameter("district");
      String jobOccupation = request.getParameter("jobOccupation");
      String jobPosition = request.getParameter("jobPosition");
      String national = request.getParameter("national");
      String nickName = request.getParameter("nickName");

      updateCustomerAdditionalInforRequest.setEmail(email);
      updateCustomerAdditionalInforRequest.setLivingAddress(livingAddress);
      updateCustomerAdditionalInforRequest.setProvince(province);
      updateCustomerAdditionalInforRequest.setCommune(commune);
      updateCustomerAdditionalInforRequest.setDistrict(district);
      if (StringUtils.isNotBlank(jobOccupation)) {
        updateCustomerAdditionalInforRequest
            .setJobOccupation(Integer.parseInt(jobOccupation));
      }
      if (StringUtils.isNotBlank(jobPosition)) {
        updateCustomerAdditionalInforRequest
            .setJobPosition(Integer.parseInt(jobPosition));
      }
      updateCustomerAdditionalInforRequest.setNationality(national);
      updateCustomerAdditionalInforRequest.setNickName(nickName);

      updateKycAdditionInfResponse = customerEndpoint
          .kycUpdateAdditionalInfo(updateCustomerAdditionalInforRequest);
      if (updateKycAdditionInfResponse.getStatus().getCode() != 0) {
        throw new Exception(updateKycAdditionInfResponse.getStatus().getValue());
      }

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
    }

    return updateKycAdditionInfResponse;
  }

  @RequestMapping(value = "/kyc/update/update-store-address-info", method = RequestMethod.POST)
  public UpdateAddressResponse updateStoreAddressInfo(HttpServletRequest request)
      throws Exception {
    UpdateAddressResponse response = new UpdateAddressResponse();
    UpdateAddressRequest updateAddressRequest = new UpdateAddressRequest();
    try {

      String aliasStore = request.getParameter("aliasStore");
      String businessPhoneStore = request.getParameter("businessPhoneStore");
      String street1Store = request.getParameter("street1Store");
      String outLetStoreType = request.getParameter("outletStoreType");

      Address address = new Address();
      address.setAddressType(AddressType.OUTLET_ADDRESS.getCode());
      address.setAlias(aliasStore);
      address.setBusinessPhone(businessPhoneStore);
      address.setStreet1(street1Store);
      address.setOutletStoreType(OutletStoreType.getOutLetStoreType(outLetStoreType));
      updateAddressRequest.setAddress(address);

      response = customerEndpoint.updateStoreAddress(updateAddressRequest);
      if (response.getStatus().getCode() != 0) {
        throw new Exception(response.getStatus().getValue());
      }

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
    }

    return response;
  }

  @RequestMapping(value = "/wallet/cash-in/otp/get", method = RequestMethod.POST)
  public WalletCashInOTPGetResponse walletCashInOtpGet(HttpServletRequest request, ModelMap map)
      throws Exception {
    WalletCashInOTPGetResponse response = new WalletCashInOTPGetResponse();
    WalletCashInOTPGetRequest walletCashInOTPGetRequest = new WalletCashInOTPGetRequest();
    try {

      String senderName = request.getParameter("senderName");
      String senderMsisdn = request.getParameter("senderMsisdn");
      String receiverMsisdn = request.getParameter("receiverMsisdn");
      String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
      String transContent = request.getParameter("transContent");

      walletCashInOTPGetRequest.setSenderName(senderName);
      walletCashInOTPGetRequest.setSenderMsisdn(senderMsisdn);
      walletCashInOTPGetRequest.setReceiverMsisdn(receiverMsisdn);
      walletCashInOTPGetRequest.setAmount(Long.valueOf(amount));
      walletCashInOTPGetRequest.setTransContent(transContent);

      response = walletEndpoint.getOtpcashIn(walletCashInOTPGetRequest);
      if (response.getStatus().getCode() != 0) {
        String error = response.getStatus().getValue();
        map.put("error", error);
      }

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
    return response;
  }

  @RequestMapping(value = "/wallet/cash-in/fee/get", method = RequestMethod.POST)
  public WalletCashInFeeGetResponse walletCashInFeeGet(HttpServletRequest request)
      throws Exception {
    WalletCashInFeeGetResponse response = new WalletCashInFeeGetResponse();
    WalletCashInFeeGetRequest walletCashInFeeGetRequest = new WalletCashInFeeGetRequest();
    try {

      String senderMsisdn = request.getParameter("senderMsisdn");
      String senderName = request.getParameter("senderName");
      String receiverMsisdn = request.getParameter("receiverMsisdn");
      String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
      String transContent = request.getParameter("transContent");
      String keyOtpFee = request.getParameter("keyOtpFee");
      String senderOtp = request.getParameter("senderOtp");


      walletCashInFeeGetRequest.setSenderMsisdn(senderMsisdn);
      walletCashInFeeGetRequest.setSenderName(senderName);
      walletCashInFeeGetRequest.setReceiverMsisdn(receiverMsisdn);
      walletCashInFeeGetRequest.setAmount(Long.valueOf(amount));
      walletCashInFeeGetRequest.setTransContent(transContent);
      walletCashInFeeGetRequest.setKeyOtpFee(keyOtpFee);
      walletCashInFeeGetRequest.setSenderOtp(senderOtp);

      response = walletEndpoint.getFeecashIn(walletCashInFeeGetRequest);
      if (response.getStatus().getCode() != 0) {
        throw new Exception(response.getStatus().getValue());
      }

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
    return response;
  }


  public Long totalCashBackAmount(HttpServletRequest request)
      throws Exception {
    Long totalCashBackAmount = 0L;

    FindTransactionsRequest findTransactionsRequest = new FindTransactionsRequest();
    Date fromDate = null;
    Date endDate = null;
    String searchRange = request.getParameter("date");

    if (StringUtils.isNotBlank(searchRange)) {
      Date[] dates = parseDateRange(searchRange);
      fromDate = dates[0];
      endDate = dates[1];
    }
    String quickSearch =
        StringUtils.trimToEmpty(request.getParameter(QUICK_SEARCH_REQ)).replaceAll("\\s+", " ");

    String status = request.getParameter("status");

    findTransactionsRequest.setEndDate(endDate);
    findTransactionsRequest.setFromDate(fromDate);

    if (StringUtils.isNotEmpty(quickSearch)) {
      findTransactionsRequest.setTextSearch(quickSearch);
    }

    if (StringUtils.isNotBlank(status)/* && !"-1".equals(status) */) {
      List<Integer> statusList = new ArrayList<>();
      statusList.add(Integer.parseInt(status));
      findTransactionsRequest.setStatusIds(statusList);
    }
    findTransactionsRequest.setOffset(Integer.parseInt(request.getParameter(START_REQ)));
    findTransactionsRequest.setLimit(Integer.parseInt(request.getParameter(LENGTH_REQ)));
    String[] serviceTypeId = request.getParameterValues("serviceTypeId[]");
    if (serviceTypeId != null && serviceTypeId.length > 0
        && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
      findTransactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
    } else {
      findTransactionsRequest.setServiceTypeIds(Arrays.asList(CASH_BACK.name()));
    }

    FindTransactionsResponse findTransactionsResponse = transactionEndpoint.transactionFind(findTransactionsRequest);
    if (findTransactionsResponse.getStatus().getCode() == 0 && findTransactionsResponse.getTransactions().size() > 0) {
      totalCashBackAmount = findTransactionsResponse.getTotalNetAmount().longValue();
    }

    return totalCashBackAmount.longValue();
  }

  /*Google Authenticator*/

  @RequestMapping(value = "/send-email/googleAuthenticator", method = RequestMethod.POST)
  public SendGoogleAuthenticatorResponse sendEmailQR(HttpServletRequest request)
      throws Exception {
    SendGoogleAuthenticatorResponse sendGoogleAuthenticatorResponse = new SendGoogleAuthenticatorResponse();
    SendGoogleAuthenticatorRequest sendGoogleAuthenticatorRequest = new SendGoogleAuthenticatorRequest();
    try {
      sendGoogleAuthenticatorResponse = walletEndpoint.sendGoogleAuthenticator(sendGoogleAuthenticatorRequest);
      if (sendGoogleAuthenticatorResponse.getStatus().getCode() != 0) {
        throw new Exception(sendGoogleAuthenticatorResponse.getStatus().getValue());
      }

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
    }

    return sendGoogleAuthenticatorResponse;
  }
}
