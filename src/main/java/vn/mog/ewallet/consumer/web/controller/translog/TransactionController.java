package vn.mog.ewallet.consumer.web.controller.translog;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IBillPaymentEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.Customer;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.GetInvoiceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.GetInvoiceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean.Invoice;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean.InvoiceAttribute;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckLoginPasswordRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckLoginPasswordResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.AddressType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.ExportTransactionLogRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.ExportTransactionLogResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.LogFile;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction.Card;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.TransactionAttribute;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.TransactionAttributeType;
import vn.mog.ewallet.consumer.web.util.tools.AES;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.ewallet.consumer.web.utils.WebUtil;
import vn.mog.framework.security.api.ICallerUtils;

@Controller
@RequestMapping(value = "/trans-log")
public class TransactionController extends AbstractController {

  private static final Logger LOGGER = LogManager.getLogger(TransactionController.class);

  public static final String TRANSACTION_CONTROLLER = "/trans-log";
  public static final String TRANSACTION_LIST = TRANSACTION_CONTROLLER + "/transaction-history";
  public static final String TRANSACTION_DETAIL = TRANSACTION_CONTROLLER + "/detail";

  // Session attribute
  public static final String TRANSACTION_ID_ATTRIBUTE = "TRANSACTION_ID";
  public static final String CREATION_DATE_ATTRIBUTE = "CREATION_DATE";
  public static final String SERVICE_TYPE_ATTRIBUTE = "SERVICE_TYPE";
  public static final String SERVICE_NAME_ATTRIBUTE = "SERVICE_NAME";
  public static final String ORDER_INFO_ATTRIBUTE = "ORDER_INFO";
  public static final String AMOUNT_ATTRIBUTE = "AMOUNT";
  public static final String COMMISSION_ATTRIBUTE = "COMMISSION";
  public static final String REAL_AMOUNT_ATTRIBUTE = "REAL_AMOUNT";
  public static final String ERROR_MESSAGE_ATTRIBUTE = "ERROR_MESSAGE";
  public static final String PHONE_NUMBER_ATTRIBUTE = "PHONE_NUMBER";
  public static final String BILL_PAYMENT_CUSTOMER_REFERENCE_ATTRIBUTE = "BILL_PAYMENT_CUSTOMER_REFERENCE";
  public static final String BILL_PAYMENT_INVOICE_REFERENCE_ATTRIBUTE = "BILL_PAYMENT_INVOICE_REFERENCE";


  //Param
  private static final String TRANSACTION_ID_RQP = "transactionId";
  private static final String CREATION_DATE_RQP = "creationDate";
  private static final String SERVICE_TYPE_RQP = "serviceType";
  private static final String SERVICE_NAME_RQP = "serviceName";
  private static final String ORDER_INFO_RQP = "orderInfo";
  private static final String AMOUNT_RQP = "amount";
  private static final String COMMISSION_RQP = "commission";
  private static final String REAL_AMOUNT_RQP = "realAmount";
  private static final String PHONE_NUMBER_RQP = "phoneNumber";
  private static final String ERROR_MESSAGE_RQP = "errorMessage";

  @Autowired
  private GatewayServiceAPIClient gatewayAPIClient;
  @Autowired
  private IBillPaymentEndpoint billPaymentEndpoint;

  @Autowired
  protected ICallerUtils callerUtil;

  @GetMapping(value = "/transaction-history")
  public String lichSuGiaoDich(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    return "/transaction/lichsugiaodich";
  }

  @SuppressWarnings("unchecked")
  @PostMapping(value = "/view-pincode")
  public String xemMaTheGiaoDichStepTwo(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String passWord = request.getParameter("passWord");
      Boolean compareAccount = false;
      CheckLoginPasswordRequest checkLoginRequest = new CheckLoginPasswordRequest();
      checkLoginRequest.setPassword(passWord);

      CheckLoginPasswordResponse checkLoginResponse = walletEndpoint
          .checkLoginPassword(checkLoginRequest);

      if (checkLoginResponse == null || checkLoginResponse.getStatus() == null) {
        throw new Exception("No response!");
      }
      if (checkLoginResponse.getStatus().getCode() != 0) {
        throw new Exception(String.valueOf(checkLoginResponse.getStatus().getCode()));
      }

      Object transactionIdAttr = SessionUtil.getAttribute(TRANSACTION_ID_ATTRIBUTE);
      Long transactionId =
          transactionIdAttr != null ? Long.parseLong(String.valueOf(transactionIdAttr)) : null;

      GetTransactionRequest getTransactionRequest = new GetTransactionRequest();
      getTransactionRequest.setTxnId(transactionId);
      GetTransactionResponse transactionResponse = walletEndpoint
          .transactionGet(getTransactionRequest);
      if (transactionResponse != null && transactionResponse.getStatus().getCode() == 0) {

        Transaction transaction = transactionResponse.getTransaction();
        if (transaction != null) {
          if(callerUtil.getCallerInformation().getActorId().equals(transaction.getIdOwner())){
            compareAccount = true;
          }

          setSerialCardType(transaction, map);
        }
        map.put("compareAccount",compareAccount);
        map.put(TRANSACTION_ID_RQP, transactionId);
        map.put("transactionList", transaction);
        map.put("transactionsPinCode", transaction);
        // Put customer's info
        try {

          GetConsumerAccountResponse baseResponseType = walletEndpoint.accountInfoGet();
          if (baseResponseType.getStatus().getCode() != 0) {
            throw new Exception(baseResponseType.getStatus().getValue());
          }
          Customer customer = baseResponseType.getCustomer();
          String alias = null;
          String street1Store = null;
          List<Address> lstAddress = customer.getAddresses();
          if (lstAddress != null) {
            for (Address item : lstAddress) {
              if (item.getAddressType() == AddressType.OUTLET_ADDRESS.getCode()) {
                alias = item.getAlias();
                street1Store = item.getStreet1();
              }
            }
          }
          map.put("aliasStore", alias);
          map.put("street1Store",street1Store);
          map.put("displayName", customer.getDisplayName());
          map.put("livingAddress", customer.getLivingAddress());
        } catch (Exception ex) {
          LOGGER.error(ex.getMessage());
        }
        return "/transaction/xemmathegiaodich2";
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);

      map.put(TRANSACTION_ID_RQP, SessionUtil.getAttribute(TRANSACTION_ID_ATTRIBUTE));
      map.put(CREATION_DATE_RQP, SessionUtil.getAttribute(CREATION_DATE_ATTRIBUTE));
      map.put(SERVICE_TYPE_RQP, SessionUtil.getAttribute(SERVICE_TYPE_ATTRIBUTE));
      map.put(SERVICE_NAME_RQP, SessionUtil.getAttribute(SERVICE_NAME_ATTRIBUTE));
      map.put(ORDER_INFO_RQP, SessionUtil.getAttribute(ORDER_INFO_ATTRIBUTE));
      map.put(AMOUNT_RQP, SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put(COMMISSION_RQP, SessionUtil.getAttribute(COMMISSION_ATTRIBUTE));
      map.put(REAL_AMOUNT_RQP, SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put(ERROR_MESSAGE_RQP, SessionUtil.getAttribute(ERROR_MESSAGE_ATTRIBUTE));
      map.put("codeErr", e.getMessage());

      return "/transaction/xemmathegiaodich";
    }
    return "/transaction/xemmathegiaodich2";
  }

  private void setSerialCardType(Transaction transaction, ModelMap map) throws Exception {
    List<Card> serials = transaction.getSerials();
    if (serials != null) {
      String para_accessToken = null;
      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      if (userLogin != null) {
        para_accessToken = userLogin.getAccessToken();
      }
      for (Card serial : serials) {
        serial.setPin(AES.decryptTripleDES(para_accessToken, serial.getPin()));
        serial.setCardType(transaction
            .getAttributeValueByAttributeType(TransactionAttributeType.PTU_CARD_TYPE.name()));
        switch (serial.getCardType()) {
          case "VNP":
            serial.setCardType("Vinaphone");
            break;
          case "VNM":
            serial.setCardType("Vietnamobile");
            break;
          case "VMS":
            serial.setCardType("MobiFone");
            break;
          case "GMOBILE":
            serial.setCardType("GMobile");
            break;
          case "VTM":
            serial.setCardType("Viettel");
            break;
          case "ZING":
            serial.setCardType("Zing");
            break;
          case "GATE":
            serial.setCardType("Gate");
            break;
          case "GARENA":
            serial.setCardType("Garena");
            break;
          case "VCOIN":
            serial.setCardType("Vcoin");
            break;
          case "VINPLAY":
            serial.setCardType("Vinplay");
            break;
          case "ONCASH":
            serial.setCardType("Oncash");
            break;
          case "BIT":
            serial.setCardType("Bit");
            break;
          case "APPOTA":
            serial.setCardType("Appota");
            break;
          case "SOHA":
            serial.setCardType("Soha");
            break;
          case "FUNCARD":
            serial.setCardType("Funcard");
            break;
          case "SCOIN":
            serial.setCardType("Scoin");
            break;
          case "ANPAY":
            serial.setCardType("Anpay");
            break;
          default:
            break;
        }
        map.put("tenSP", serial.getCardType());
      }

      map.put("cardSize", transaction.getSerials().size());
    }
  }

  @PostMapping(value = "/view-pincode-login")
  public String xemMaTheGiaoDichLogin(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    Object phoneNumberAttr = SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE);
    String phoneNumber = phoneNumberAttr != null ? phoneNumberAttr.toString() : null;
    if (StringUtils.isBlank(phoneNumber) && userLogin != null) {
      phoneNumber = userLogin.getPhoneNumber();
      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
    }

//    phoneNumber = request.getParameter(PHONE_NUMBER_RQP);
    String transactionId = request.getParameter(TRANSACTION_ID_RQP);
    String creationDate = request.getParameter(CREATION_DATE_RQP);
    if (creationDate != null) {
      Date date = new Date(Long.parseLong(creationDate));
      SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      creationDate = sdf2.format(date);
    }

    String serviceType = request.getParameter(SERVICE_TYPE_RQP);
    String serviceName = request.getParameter(SERVICE_NAME_RQP);
    String orderInfo = request.getParameter(ORDER_INFO_RQP);
    String amount = request.getParameter(AMOUNT_RQP);
    String commission = request.getParameter(COMMISSION_RQP);
    String realAmount = request.getParameter(REAL_AMOUNT_RQP);
    String errorMessage = request.getParameter(ERROR_MESSAGE_RQP);

    // Store data to Session
    SessionUtil.setAttribute(TRANSACTION_ID_ATTRIBUTE, transactionId);
    SessionUtil.setAttribute(CREATION_DATE_ATTRIBUTE, creationDate);
    SessionUtil.setAttribute(SERVICE_TYPE_ATTRIBUTE, serviceType);
    SessionUtil.setAttribute(SERVICE_NAME_ATTRIBUTE, serviceName);
    SessionUtil.setAttribute(ORDER_INFO_ATTRIBUTE, orderInfo);
    SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
    SessionUtil.setAttribute(COMMISSION_ATTRIBUTE, commission);
    SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
    SessionUtil.setAttribute(ERROR_MESSAGE_ATTRIBUTE, errorMessage);

    map.put(PHONE_NUMBER_RQP, phoneNumber);
    map.put(TRANSACTION_ID_RQP, transactionId);
    map.put(CREATION_DATE_RQP, creationDate);
    map.put(SERVICE_TYPE_RQP, serviceType);
    map.put(SERVICE_NAME_RQP, serviceName);
    map.put(ORDER_INFO_RQP, orderInfo);
    map.put(AMOUNT_RQP, amount);
    map.put(COMMISSION_RQP, commission);
    map.put(REAL_AMOUNT_RQP, realAmount);
    map.put(ERROR_MESSAGE_RQP, errorMessage);

    return "/transaction/xemmathegiaodich";
  }

  @GetMapping(value = "/export-log")
  public void exportLogTransaction(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws Exception {
    Date fromDate = null;
    Date endDate = null;
    try {
      String txtSearch = request.getParameter("quickSearch");
      String[] paramServiceTypeIds = request.getParameterValues("serviceTypeIds");
      String searchRange = StringUtils.trimToEmpty(request.getParameter("range"));
      String[] paramTxnStatusIds = request.getParameterValues("txnStatusIds");

      ExportTransactionLogRequest etlRequest = new ExportTransactionLogRequest();

      etlRequest.setTextSearch(txtSearch);

      if (paramServiceTypeIds != null && paramServiceTypeIds.length > 0 && StringUtils
          .isNotEmpty(paramServiceTypeIds[0])) {
        etlRequest.setServiceTypeIds(Arrays.asList((paramServiceTypeIds)));
      }

      if (StringUtils.isNotBlank(searchRange)) {
        Date[] dates = parseDateRange(searchRange);
        fromDate = dates[0];
        endDate = dates[1];
      }
      etlRequest.setFromDate(fromDate);
      etlRequest.setEndDate(endDate);

      if (paramTxnStatusIds != null && paramTxnStatusIds.length > 0 && StringUtils
          .isNotEmpty(paramTxnStatusIds[0])) {
        etlRequest.setStatusIds(NumberUtil.convertListToInt(paramTxnStatusIds));
      }

      ExportTransactionLogResponse etlResponse = transactionEndpoint.exportTransaction(etlRequest);

      if (etlResponse.getStatus().getCode() == 0) {
        LogFile logFile = etlResponse.getLogFile();

        WebUtil.exportFile(response, logFile);
      } else {
        String errorExportLogTransaction = etlResponse.getStatus().getValue();
        map.put("errorExportLogTransaction", errorExportLogTransaction);
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
  }


  @PostMapping(value = "/view-bill")
  public String xemBillGiaoDich(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    Boolean compareAccount = false;

    String transactionId = request.getParameter("transactionId");
    GetTransactionRequest getTransactionRequest = new GetTransactionRequest();
    getTransactionRequest.setTxnId(Long.valueOf(transactionId));
    GetTransactionResponse transactionResponse = walletEndpoint
        .transactionGet(getTransactionRequest);
    if (transactionResponse != null && transactionResponse.getStatus().getCode() == 0) {
      if(callerUtil.getCallerInformation().getActorId().equals(transactionResponse.getTransaction().getIdOwner())){
        compareAccount = true;
      }
      map.put("compareAccount",compareAccount);
      Transaction transaction = transactionResponse.getTransaction();
      if (transaction != null) {
        setSerialCardType(transaction, map);
        List<TransactionAttribute> transactionAttributes = transaction.getAttributes();
        for (TransactionAttribute transactionAttribute : transactionAttributes) {
          if ("BILL_PAYMENT_CUSTOMER_REFERENCE"
              .equals(transactionAttribute.getTransactionAttributeType())) {
            SessionUtil.setAttribute(BILL_PAYMENT_CUSTOMER_REFERENCE_ATTRIBUTE,
                transactionAttribute.getTransactionAttributeValue());
            map.put("cus_reference",
                SessionUtil.getAttribute(BILL_PAYMENT_CUSTOMER_REFERENCE_ATTRIBUTE));
          }
          if ("BILL_PAYMENT_INVOICE_REFERENCE"
              .equals(transactionAttribute.getTransactionAttributeType())) {
            SessionUtil.setAttribute(BILL_PAYMENT_INVOICE_REFERENCE_ATTRIBUTE,
                transactionAttribute.getTransactionAttributeValue());
            map.put("invoice_reference",
                SessionUtil.getAttribute(BILL_PAYMENT_INVOICE_REFERENCE_ATTRIBUTE));
          }
        }
      }
      //get Invoice
      GetInvoiceRequest getInvoiceRequest = new GetInvoiceRequest();
      getInvoiceRequest.setServiceCode(transaction.getServiceCode());
      getInvoiceRequest.setInvoiceReference(
          String.valueOf(SessionUtil.getAttribute(BILL_PAYMENT_CUSTOMER_REFERENCE_ATTRIBUTE)));
      GetInvoiceResponse getInvoiceResponse = billPaymentEndpoint.getInvoice(getInvoiceRequest);

      if (getInvoiceResponse != null && getInvoiceResponse.getStatus().getCode() == 0) {
        Invoice objInvoice = getInvoiceResponse.getInvoice();
        if (objInvoice != null) {
          //Không cho in với HD điện Đồng Nai
          String  invoidElectricityDongNai = objInvoice.getCustomerReference();
          Boolean allowPrinterElectricityDongNai = invoidElectricityDongNai.startsWith("PK");
          map.put("allowPrinterElectricityDongNai", allowPrinterElectricityDongNai);

          Set<InvoiceAttribute> invoiceAttributes = getInvoiceResponse.getInvoice()
              .getInvoiceAttributes();
          if (invoiceAttributes != null) {
            for (InvoiceAttribute invoiceAttribute : invoiceAttributes) {
              if ("CUS_ADDRESS".equals(invoiceAttribute.getInvoiceAttributeTypeId())) {
                map.put("cus_address", invoiceAttribute.getValue());
              } else if ("CUS_NAME".equals(invoiceAttribute.getInvoiceAttributeTypeId())) {
                map.put("cus_fullName", invoiceAttribute.getValue());
              } else if ("CUS_SSN".equals(invoiceAttribute.getInvoiceAttributeTypeId())) {
                map.put("cus_ssn", invoiceAttribute.getValue());
              }
            }
          }
        }
        if(!Objects.isNull(getInvoiceAttribute(objInvoice)))
          map.put("inv_period", getInvoiceAttribute(objInvoice).getValue());

        map.put("amount", objInvoice.getAmount());
        map.put("obj_invoice", objInvoice);
        map.put("expired_date",
            objInvoice.getExpirationDate() != null ? objInvoice.getExpirationDate()
                : objInvoice.getDueDate());
      } else {
        map.put("amount", transaction.getAmount());
      }

      map.put("obj_transaction", transaction);

      // Put customer's info
      try {
        String alias = null;
        String street1Store = null;
        GetConsumerAccountResponse baseResponseType = walletEndpoint.accountInfoGet();
        if (baseResponseType.getStatus().getCode() != 0) {
          throw new Exception(baseResponseType.getStatus().getValue());
        }
        Customer customer = baseResponseType.getCustomer();
        if (customer.getAddresses() != null) {
          List<Address> lstAddress = customer.getAddresses();
          for (Address item : lstAddress) {
            if (item.getAddressType() == AddressType.OUTLET_ADDRESS.getCode()) {
              alias = item.getAlias();
              street1Store = item.getStreet1();
            }
          }
        }
        map.put("aliasStore", alias);
        map.put("street1Store",street1Store);
        map.put("displayName", customer.getDisplayName());
        map.put("livingAddress", customer.getLivingAddress());


      } catch (Exception ex) {
        LOGGER.error(ex.getMessage());
      }
    }
    return "/transaction/view_bill_transaction";
  }
}