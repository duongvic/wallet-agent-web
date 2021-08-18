package vn.mog.ewallet.consumer.web.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SharedConstants {

  public static final String VERTICAL_BAR = "|";

  public static String PLATFORM_BACKEND_APIGATEWAY_SERVICE_API_ENDPOINT = StringUtils.EMPTY;

  /*-------------- AUTH BEGIN--------------*/
  public static String PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_UAA_SERVICE_CLIENT_ID = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_UAA_SERVICE_CLIENT_SECRET = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_UAA_SERVICE_IDENTIFIER_TYPE = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_UAA_SERVICE_CREDENTIAL_TYPE = StringUtils.EMPTY;

  /*-------------- AUTH END--------------*/
  public static int HTTP_CLIENT_CONNECT_TIMEOUT = 60000;// ms
  public static int HTTP_CLIENT_SOCKET_TIMEOUT = 900000;// ms
  public static int HTTP_CLIENT_MAX_CONNECTIONS_PER_HOST = 100;
  public static int HTTP_CLIENT_MAX_TOTAL_CONNECTIONS = 5000;

  public static long CONSUMER_MAINTENANCE_BALANCE = 50000L;
  public static long AGENT_MAINTENANCE_BALANCE = 50000L;
  public static long MERCHANT_MAINTENANCE_BALANCE = 50000L;
  /*----- FUND ORDER -----*/
  /*--- FUND IN ---*/
  public static Long FUND_IN_ORDER_MIN_VALUE_PER_ORDER = 0L;
  public static Long FUND_IN_ORDER_MAX_VALUE_PER_ORDER = 0L;
  public static Long FUND_IN_ORDER_TOTAL_VALUE_PER_DAY = 0L;
  public static String FUND_IN_ORDER_VIA_PAYMENT_GATEWAY_RESULT_URI = StringUtils.EMPTY;
  public static String FUND_IN_ORDER_VISA_PAYMENT_GATEWAY_RESULT_URI = StringUtils.EMPTY;
  /*--- FUND OUT ---*/
  public static Long FUND_OUT_ORDER_MIN_VALUE_PER_ORDER = 0L;
  public static Long FUND_OUT_ORDER_MAX_VALUE_PER_ORDER = 0L;
  public static Long FUND_OUT_ORDER_TOTAL_VALUE_PER_DAY = 0L;
  // ---//
  public static Long FUND_ORDER_TMV_MIN_VALUE = 0L;
  public static Long FUND_ORDER_TMV_MAX_VALUE = 0L;
  public static Long FUND_ORDER_TMV_TOTAL_VALUE = 0L;
  // --WALLET TRANSFER-//
  public static Long FUND_TRANSFER_MAX_MONEY = 0L;
  public static Long WALLET_TRANSFER_MAX_MONEY = 0L;

  /*-- Export Card - TMN---*/
  public static Integer QUANTITY_MPO_MAX_TMN = 0;

  /*-- Buy Card - TMN---*/
  public static List<String> EPIN_PHONE_AVAILABLE_SERVICE = new ArrayList<>();
  public static List<String> EPIN_TOPUP_PHONE_AVAILABLE_SERVICE = new ArrayList<>();
  public static List<String> EPIN_3GP_AVAILABLE_SERVICE = new ArrayList<>();
  public static List<String> EPIN_GAME_AVAILABLE_SERVICE = new ArrayList<>();
  public static List<String> BATCH_CARD_AVAILABLE_SERVICE = new ArrayList<>();

  /*Fund in local bank*/
  public static List<String> FUNDIN_AVAILABLE_SERVICE = new ArrayList<>();

  /*-- TripleDES Key---*/
//  public static String PARAM_REF_CONSUMER_WEB_SECURITY_CARD_ENCRYPT_KEY = StringUtils.EMPTY;

  @Value("${platform.backend.uaa.service.api.endpoint}")
  public void setPlatformUaaServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT = value;
  }

  @Value("${platform.backend.uaa.service.client.id}")
  public void setPlatformUaaServiceClientId(String value) {
    PLATFORM_BACKEND_UAA_SERVICE_CLIENT_ID = value;
  }

  @Value("${platform.backend.uaa.service.client.secret}")
  public void setPlatformUaaServiceClientSecret(String value) {
    PLATFORM_BACKEND_UAA_SERVICE_CLIENT_SECRET = value;
  }

  @Value("${platform.backend.uaa.service.identifier.type}")
  public void setPlatformUaaServiceIdentifierType(String value) {
    PLATFORM_BACKEND_UAA_SERVICE_IDENTIFIER_TYPE = value;
  }

  @Value("${platform.backend.uaa.service.credential.type}")
  public void setPlatformUaaServiceCredentialType(String value) {
    PLATFORM_BACKEND_UAA_SERVICE_CREDENTIAL_TYPE = value;
  }

  @Value("${platform.backend.apigateway.service.api.endpoint}")
  public void setPlatformApiGatewayServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_APIGATEWAY_SERVICE_API_ENDPOINT = value;
  }


  @Value("${consumer.maintenance.balance}")
  public void setConsumerMaintenanceBalance(Long value) {
    CONSUMER_MAINTENANCE_BALANCE = value;
  }

  @Value("${agent.maintenance.balance}")
  public void setAgentMaintenanceBalance(Long value) {
    AGENT_MAINTENANCE_BALANCE = value;
  }

  @Value("${merchant.maintenance.balance}")
  public void setMerchantMaintenanceBalance(Long value) {
    MERCHANT_MAINTENANCE_BALANCE = value;
  }

  @Value("${wallet.fundin.order.min.value.per.order}")
  public void setWalletFundInOderMinValuePerOrder(Long value) {
    FUND_IN_ORDER_MIN_VALUE_PER_ORDER = value;
  }

  @Value("${wallet.fundin.order.max.value.per.order}")
  public void setWalletFundInOderMaxValuePerOrder(Long value) {
    FUND_IN_ORDER_MAX_VALUE_PER_ORDER = value;
  }

  @Value("${wallet.fundin.order.total.value.per.day}")
  public void setWalletFundInOderTotalValuePerDay(Long value) {
    FUND_IN_ORDER_TOTAL_VALUE_PER_DAY = value;
  }

  @Value("${wallet.fundin.via.payment.gateway.result.uri}")
  public void setWalletFundInViaPaymentGatewayResultURI(String value) {
    FUND_IN_ORDER_VIA_PAYMENT_GATEWAY_RESULT_URI = value;
  }

  @Value("${wallet.fundin.visa.payment.gateway.result.uri}")
  public void setWalletFundInVisaPaymentGatewayResultURI(String value) {
    FUND_IN_ORDER_VISA_PAYMENT_GATEWAY_RESULT_URI = value;
  }


  @Value("${wallet.fundout.order.min.value.per.order}")
  public void setWalletFundOutOderMinValuePerOrder(Long value) {
    FUND_OUT_ORDER_MIN_VALUE_PER_ORDER = value;
  }

  @Value("${wallet.fundout.order.max.value.per.order}")
  public void setWalletFundOutOderMaxValuePerOrder(Long value) {
    FUND_OUT_ORDER_MAX_VALUE_PER_ORDER = value;
  }

  @Value("${wallet.fundout.order.total.value.per.day}")
  public void setWalletFundOutOderTotalValuePerDay(Long value) {
    FUND_OUT_ORDER_TOTAL_VALUE_PER_DAY = value;
  }

  @Value("${wallet.fund.order.tmv.min.value}")
  public void setWalletFundOderTMVMinValue(Long value) {
    FUND_ORDER_TMV_MIN_VALUE = value;
  }

  @Value("${wallet.fund.order.tmv.max.value}")
  public void setWalletFundOderTMVMaxValue(Long value) {
    FUND_ORDER_TMV_MAX_VALUE = value;
  }

  @Value("${wallet.fund.order.tmv.total.value}")
  public void setWalletFundOderTMVTotalValue(Long value) {
    FUND_ORDER_TMV_TOTAL_VALUE = value;
  }

  @Value("${transfer.fund.order.amount.level}")
  public void setEWalletFundInFundTransfer(Long value) {
    FUND_TRANSFER_MAX_MONEY = value;
  }

  @Value("${wallet.transfer.order.amount.level}")
  public void setEWalletWalletTransfer(Long value) {
    WALLET_TRANSFER_MAX_MONEY = value;
  }

  @Value("${topup.export.card.tmn.quantity.per.epo.max}")
  public void setQuantityMpoMaxTMN(Integer value) {
    QUANTITY_MPO_MAX_TMN = value;
  }

  @Value("#{'${epin.phone.available.list}'.split(',')}")
  public void setEpinAvailableService(List<String> value) {
    EPIN_PHONE_AVAILABLE_SERVICE = value;
  }

  @Value("#{'${epin.topUp.available.list}'.split(',')}")
  public void setEpinTopUpAvailableService(List<String> value) {
    EPIN_TOPUP_PHONE_AVAILABLE_SERVICE = value;
  }


  @Value("#{'${epin.3g.available.list}'.split(',')}")
  public void setEpin3GAvailableService(List<String> value) {
    EPIN_3GP_AVAILABLE_SERVICE = value;
  }


  @Value("#{'${epin.game.available.list}'.split(',')}")
  public void setEpinGameAvailableService(List<String> value) {
    EPIN_GAME_AVAILABLE_SERVICE = value;
  }

  @Value("#{'${batch.card.available.list}'.split(',')}")
  public void setBatchCardAvailableService(List<String> value) {
    BATCH_CARD_AVAILABLE_SERVICE = value;
  }

  @Value("#{'${fundin.available.list}'.split(',')}")
  public void setFundInAvailableService(List<String> value) {
    FUNDIN_AVAILABLE_SERVICE = value;
  }

  //  @Value("${param.ref.consumer.web.security.card.encrypt.key}")
//  public static void setParamRefConsumerWebSecurityCardEncryptKey(String value) {
//    PARAM_REF_CONSUMER_WEB_SECURITY_CARD_ENCRYPT_KEY = value;
//  }

  // -- BASE URL --//
  public static String BASE_URL = StringUtils.EMPTY;

  @Value("${base.url}")
  public void setBaseURL(String value) {
    BASE_URL = value;
  }

  // -- FUNCTION SHOW/HIDE --//

  public static String MENU_ITEM_SHOW_FUNDIN = StringUtils.EMPTY;

  @Value("${menu.item.show.fundin}")
  public void setMenuItemShowFundin(String value) {
    MENU_ITEM_SHOW_FUNDIN = value;
  }

  public static String MENU_ITEM_SHOW_FUNDOUT = StringUtils.EMPTY;

  @Value("${menu.item.show.fundout}")
  public void setMenuItemShowFundout(String value) {
    MENU_ITEM_SHOW_FUNDOUT = value;
  }

  public static String MENU_ITEM_SHOW_MONEY_TRANSFER = StringUtils.EMPTY;

  @Value("${menu.item.show.money.transfer}")
  public void setMenuItemShowMoneyTransfer(String value) {
    MENU_ITEM_SHOW_MONEY_TRANSFER = value;
  }

  public static String MENU_ITEM_SHOW_PHONE_TOPUP = StringUtils.EMPTY;

  @Value("${menu.item.show.phone.topup}")
  public void setMenuItemShowPhoneTopup(String value) {
    MENU_ITEM_SHOW_PHONE_TOPUP = value;
  }

  public static String MENU_ITEM_SHOW_PHONE_EPIN = StringUtils.EMPTY;

  @Value("${menu.item.show.phone.epin}")
  public void setMenuItemShowPhoneEpin(String value) {
    MENU_ITEM_SHOW_PHONE_EPIN = value;
  }

  public static String MENU_ITEM_SHOW_GAME_EPIN = StringUtils.EMPTY;

  @Value("${menu.item.show.game.epin}")
  public void setMenuItemShowGameEpin(String value) {
    MENU_ITEM_SHOW_GAME_EPIN = value;
  }

  public static String MENU_ITEM_SHOW_PHONE_BATCH_CARD = StringUtils.EMPTY;

  @Value("${menu.item.show.phone.batch-card}")
  public void setMenuItemShowPhoneEpinBatch(String value) {
    MENU_ITEM_SHOW_PHONE_BATCH_CARD = value;
  }

  public static String MENU_ITEM_SHOW_PHONE_BATCH_CARD_API = StringUtils.EMPTY;

  @Value("${menu.item.show.phone.batch-card.api}")
  public void setMenuItemShowPhoneEpinBatchApi(String value) {
    MENU_ITEM_SHOW_PHONE_BATCH_CARD_API = value;
  }

  public static String MENU_ITEM_SHOW_ACCOUNT_INFO = StringUtils.EMPTY;

  @Value("${menu.item.show.account.info}")
  public void setMenuItemShowAccountInfo(String value) {
    MENU_ITEM_SHOW_ACCOUNT_INFO = value;
  }

  public static String MENU_ITEM_SHOW_CARD_MANAGEMENT = StringUtils.EMPTY;

  @Value("${menu.item.show.card.management}")
  public void setMenuItemShowCardManagement(String value) {
    MENU_ITEM_SHOW_CARD_MANAGEMENT = value;
  }

  public static String MENU_ITEM_SHOW_SETTING_SECURITY = StringUtils.EMPTY;

  @Value("${menu.item.show.setting.security}")
  public void setMenuItemShowSettingSecurity(String value) {
    MENU_ITEM_SHOW_SETTING_SECURITY = value;
  }

  public static String MENU_ITEM_SHOW_TRANSACTION_HISTORY = StringUtils.EMPTY;

  @Value("${menu.item.show.transaction.history}")
  public void setMenuItemShowTransactionHistory(String value) {
    MENU_ITEM_SHOW_TRANSACTION_HISTORY = value;
  }

  public static String MENU_ITEM_SHOW_BILL_PAYMENT = StringUtils.EMPTY;

  @Value("${menu.item.show.bill.payment}")
  public void setMenuItemShowBillPayment(String value) {
    MENU_ITEM_SHOW_BILL_PAYMENT = value;
  }

  public static String MENU_ITEM_SHOW_FINANCIAL_SERVICES = StringUtils.EMPTY;

  @Value("${menu.item.show.financial.services}")
  public void setMenuItemShowFinancialServices(String value) {
    MENU_ITEM_SHOW_FINANCIAL_SERVICES = value;
  }

  public static String MENU_ITEM_SHOW_CARD_DASHBOARD = StringUtils.EMPTY;

  @Value("${menu.item.show.card.dashboard}")
  public void setMenuItemShowCardDashboard(String value) {
    MENU_ITEM_SHOW_CARD_DASHBOARD = value;
  }

  public static String FUND_ORDER_SHOW_LINK_BANK = StringUtils.EMPTY;

  @Value("${fund.order.show.link-bank}")
  public void setFundOrderShowLinkBank(String value) {
    FUND_ORDER_SHOW_LINK_BANK = value;
  }

  public static String FUND_ORDER_SHOW_LOCAL_BANK = StringUtils.EMPTY;

  @Value("${fund.order.show.local-bank}")
  public void setFundOrderShowLocalBank(String value) {
    FUND_ORDER_SHOW_LOCAL_BANK = value;
  }

  public static String FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND = StringUtils.EMPTY;

  @Value("${fund.order.show.request.cash-on-hand}")
  public void setFundOrderShowRequestCashOnHand(String value) {
    FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND = value;
  }

  public static String FUND_ORDER_SHOW_REQUEST_BANK_TRANSFER = StringUtils.EMPTY;

  @Value("${fund.order.show.request.bank-transfer}")
  public void setFundOrderShowRequestBanktransfer(String value) {
    FUND_ORDER_SHOW_REQUEST_BANK_TRANSFER = value;
  }

  public static String MENU_ITEM_SHOW_CASHIN_VIETTEL_PAY = StringUtils.EMPTY;
  @Value("${menu.item.show.cashin.viettel.pay}")
  public void setMenuItemShowCashinViettelPay(String value) {
    MENU_ITEM_SHOW_CASHIN_VIETTEL_PAY = value;
  }

  public static String MENU_ITEM_SHOW_CASHOUT_VIETTEL_PAY = StringUtils.EMPTY;
  @Value("${menu.item.show.cashout.viettel.pay}")
  public void setMenuItemShowCashOutViettelPay(String value) {
    MENU_ITEM_SHOW_CASHOUT_VIETTEL_PAY = value;
  }

  public static String MENU_ITEM_SHOW_POINTS_TRANSFER = StringUtils.EMPTY;
  @Value("${menu.item.show.points.transfer}")
  public void setMenuItemShowPointsTransfer(String value) {
    MENU_ITEM_SHOW_POINTS_TRANSFER = value;
  }

  // 1fintech key
  public static String PAYMENT_SERVICE_ACCESS_KEY = StringUtils.EMPTY;

  @Value("${security.payment.service.access-key}")
  public void setPaymentServiceAccessKey(String value) {
    PAYMENT_SERVICE_ACCESS_KEY = value;
  }

  public static String PAYMENT_SERVICE_SECRET_KEY = StringUtils.EMPTY;

  @Value("${security.payment.service.secret-key}")
  public void setPaymentServiceSecretKey(String value) {
    PAYMENT_SERVICE_SECRET_KEY = value;
  }

}
