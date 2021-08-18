package vn.mog.ewallet.consumer.web.controller.translog;

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
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.DataTableResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckLoginPasswordRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckLoginPasswordResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.AddressType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.*;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.TXN_REVERSAL;

@Controller
@RequestMapping(value = "/statistic")
public class StatisticController extends AbstractController {

  private static final Logger LOGGER = LogManager.getLogger(StatisticController.class);

  public static final String STATISTIC_CONTROLLER = "/statistic";

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

  @GetMapping(value = "")
  public String statisticReport(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    return "/statistic/statistic_report";
  }
}