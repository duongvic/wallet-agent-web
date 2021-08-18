package vn.mog.ewallet.consumer.web.controller.dashboard;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.consumer.web.common.util.Utils;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.Customer;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.GetCustomerPersonalInfoRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.GetCustomerPersonalInfoResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.CustomerBankDirect;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetCustomerCardDashboardRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetCustomerCardDashboardResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.CardDashBoardItem;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.CardSummaryWarning;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.ChartRowItem;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.PieChartItem;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.Stage;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.bean.StoreNotfInfo;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness.RecentnessGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness.RecentnessGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness.bean.Recentness;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.AddressType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.CardStatus;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.CardType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindCustomerBankDirectRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindCustomerBankDirectResponse;
import vn.mog.ewallet.consumer.web.util.tools.CommonUtil;
import vn.mog.ewallet.consumer.web.util.tools.JsonUtil;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.PhoneNumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.framework.contract.base.BaseResponseType;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends AbstractController {

  private static final Logger LOGGER = LogManager.getLogger(DashboardController.class);

  public static final String DASHBOARD_CONTROLLER = "/dashboard";
  public static final String DASHBOARD_LIST = DASHBOARD_CONTROLLER + "/index";
  public static final String PAGE_DASHBOARD = "/dashboard/index";
  public static final String TRANSLOG_CONTROLLER = "/trans-log";
  public static final String HISTORY_LIST = TRANSLOG_CONTROLLER + "/transaction-history";

  private final String CODE_ERRO_RQP = "codeError";
  private final String MESSAGE_ERRO_RQP = "mesErr";

  /*###*/
  private static final String CARD_INPUTTED = "cardInputted";
  private static final String CARD_SOLD = "cardSold";
  private static final String CARD_ACTIVATE = "cardActivate";
  private static final String CARD_DEACTIVATE = "cardDeactivate";
  private static final String CARD_EXPIRED = "cardExpired";
  private static final String CARD_PRE_EXPIRED = "cardPreExpired";

  private static final String NUMBER = "number";
  private static final String VALUE = "value";
  private static final String CAPITAL = "capital";

  public static int NEAR_EXP_DAYS = 15;

  @GetMapping(value = "/index")
  public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {
      boolean isPMSaccount = false;
      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      if (userLogin != null) {
        GetConsumerAccountResponse baseResponseType = walletEndpoint.accountInfoGet();
        if (baseResponseType != null && baseResponseType.getStatus().getCode() == 0) {
          if(Objects.isNull(baseResponseType.getCustomer().getParentId())){
            isPMSaccount = true;
          }
          userLogin.setLivingAddress(baseResponseType.getCustomer().getLivingAddress());
        }
        SessionUtil.setAttribute(SESSION_ACCOUNT_LOGIN, userLogin);
      }
      String productName = PhoneNumberUtil.getCardHolderByPhoneNumber(userLogin.getPhoneNumber());
      map.put("pin_code_service_name", productName);

      /*Lấy thông tin bankDirect*/
      getCustomerBankDirect(request, response, map);

      /*lấy phone topup gần nhất*/
      getPhoneTopUp(request, response, map);

      // lấy lịch sử giao dịch nạp tiền
      // Xử lý dữ liệu đầu vào
      Long total = 0L;
      Integer offset = 0;
      Integer limit = 11;
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
      FindTransactionsRequest transactionsRequest = new FindTransactionsRequest();

      transactionsRequest.setOffset(offset);
      transactionsRequest.setLimit(limit);

      transactionsRequest.setTextSearch(quickSearch);
      transactionsRequest.setFromDate(fromDate);
      transactionsRequest.setEndDate(endDate);

      // --

      String[] serviceTypeId = request.getParameterValues("serviceTypeId");
      if (serviceTypeId != null
          && serviceTypeId.length > 0
          && !serviceTypeId[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceTypeIds(Arrays.asList(serviceTypeId));
      } else {
        transactionsRequest.setServiceTypeIds(
            Arrays.asList(
                FUND_IN.name(),
                FUND_OUT.name(),
                PHONE_TOPUP.name(),
                EPIN.name(), /*WALLET_TRANSFER.name(),*/
                P2P_TRANSFER.name(),
                EXPORT_EPIN.name(),
                BILL_PAYMENT.name(),
                WALLET_CASH_IN.name(),
                WALLET_CASH_OUT.name()
                ));
      }
      String[] serviceCode = request.getParameterValues("serviceCode");
      if (serviceCode != null
          && serviceCode.length > 0
          && !serviceCode[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
      }
      transactionsRequest.setIsOrderInclude(true);

      SecurityPaymentGetRequest securityPaymentGetRequest = new SecurityPaymentGetRequest();

      SecurityPaymentGetResponse securityPaymentGetResponse = new SecurityPaymentGetResponse();
      securityPaymentGetResponse = walletEndpoint.securityPaymentGet(securityPaymentGetRequest);
      if (securityPaymentGetResponse == null) {
        throw new Exception("No response");
      }
      if (securityPaymentGetResponse.getStatus().getCode() != 0) {
        throw new Exception(securityPaymentGetResponse.getStatus().getValue());
      } else {

        String oldLimitation = "--";
        if (securityPaymentGetResponse.getSecurityPaymentThreshold() != null) {
          oldLimitation =
              CommonUtil.formatNumber(
                  "###,### đ", securityPaymentGetResponse.getSecurityPaymentThreshold());
        }
        map.put("currentLimitation", oldLimitation);
      }

      Collection<Transaction> listTransactions = new ArrayList<>();

      FindTransactionsResponse baseTransResponseType = new FindTransactionsResponse();
      baseTransResponseType = transactionEndpoint.transactionFind(transactionsRequest);
      if (baseTransResponseType == null
          || baseTransResponseType.getStatus() == null
          || baseTransResponseType.getStatus().getCode() != 0) {
        throw new Exception("API transactionFind fail");
      } else {
        if (baseTransResponseType != null) {
          listTransactions = baseTransResponseType.getTransactions();
          for (Transaction item : listTransactions) {
            if (item.getBankCode() != null) {
              item.setBankName(getBankInfo(item.getBankCode()));
            }
          }
          map.put("listTransactions", listTransactions);
        }
      }
      // end lấy lịch sử giao dịch nạp tiền

      // check thông tin cửa hàng
      Address address = getStoreInfomation();
      map.put("storeInfo", address);
      map.put("isPMSaccount", isPMSaccount);
      SessionUtil.setAttribute("isPMSaccount", isPMSaccount);

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }

    return DASHBOARD_LIST;
  }

  public Address getStoreInfomation() {
    Customer objCus = new Customer();
    try {
      GetConsumerAccountResponse baseResponseType = walletEndpoint.accountInfoGet();
      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      } else if (baseResponseType.getStatus().getCode() == 0) {
        objCus = baseResponseType.getCustomer();
        if (objCus.getAddresses() != null) {
          List<Address> lstAddress = objCus.getAddresses();
          for (Address item : lstAddress) {
            if (item.getAddressType() == AddressType.OUTLET_ADDRESS.getCode()) {
              return item;
            }
          }
        }
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
    return new Address();
  }

  private String getBankInfo(String bankCode) throws Exception {
    List<Bank> banks = new ArrayList<>();
    try {
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(bankCode);
      findBankRequest.setIsLinkBankSupport(null);
      FindBankResponse findBankResponse = systemEndpoint.findBanks(findBankRequest);
      if (findBankResponse.getStatus().getCode() == 0
          && findBankResponse.getBanks() != null
          && findBankResponse.getBanks().size() >= 1) {
        banks = findBankResponse.getBanks();
        return banks.get(0).getBankName();
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    return bankCode;
  }

  public void getPhoneTopUp(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) {
    try {
      // Tạo request & set params vào request
      RecentnessGetRequest rq = new RecentnessGetRequest();
      rq.setOffset(0);
      rq.setLimit(2);
      rq.setRecentnessKey("PHONE_TOPUP");

      // --

      List<Recentness> listPhoneTopUp = new ArrayList<>();
      RecentnessGetResponse baseResType = walletEndpoint.recentnessGet(rq);
      if (baseResType == null
          || baseResType.getStatus() == null
          || baseResType.getStatus().getCode() != 0) {
        LOGGER.error(" API transactionFind fail");
      } else {
        listPhoneTopUp = baseResType.getRecentnesses();
      }
      map.put("listPhoneTopUp", listPhoneTopUp);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

  public void getCustomerBankDirect(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) {
    List<CustomerBankDirect> lstCusBankDirect = new ArrayList<CustomerBankDirect>();
    try {
      // lấy thông tin bankdirect
      FindCustomerBankDirectRequest findCustomerBankDirectReq = new FindCustomerBankDirectRequest();
      FindCustomerBankDirectResponse findCustomerBankDirectBaseRespType =
          walletEndpoint.findCustomerBankDirect(findCustomerBankDirectReq);
      // --- check base response type;
      if (findCustomerBankDirectBaseRespType == null) {
        throw new Exception("No response!");
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
        lstCusBankDirect = null;
      } else {
        lstCusBankDirect = findCustomerBankDirectBaseRespType.getBankDirects();
      }
      map.put("lstCusBankDirect", lstCusBankDirect);
    } catch (Exception e) {
      map.put("lstCusBankDirect", lstCusBankDirect);
      LOGGER.error(e.getMessage());
    }
  }

  @PostMapping(value = "/card-dashboard")
  public String cardDashBoardPost(
      HttpServletRequest httpRequest, HttpServletResponse response, ModelMap map) throws Exception {
    return cardDashBoard(httpRequest, response, map);
  }

  @GetMapping(value = "/card-dashboard")
  public String cardDashBoard(
      HttpServletRequest httpRequest, HttpServletResponse response, ModelMap map) throws Exception {
    try {
      String dashboeardType = httpRequest.getParameter("dashboardType");
      BaseResponseType<List<CardType>> cardTypeResponse;
      if (DASHBOARD_TYPE_API.equals(dashboeardType)) {
        cardTypeResponse = epinEndpoint.epoGetListOperator();
      } else if (DASHBOARD_TYPE_EXPORT.equals(dashboeardType)) {
        cardTypeResponse = epinEndpoint.epoGetListOperatorOffline();
      } else if (DASHBOARD_TYPE_API_N02.equals(dashboeardType)) {
        cardTypeResponse = epinEndpoint.epoGetListOperatorN02();
      } else {
        return "/about/404";
      }

      if (cardTypeResponse == null) {
        throw new Exception("Can not connect to Server");
      }
      map.put("cardTypes", cardTypeResponse.getData());
      map.put("nearExpDays", NEAR_EXP_DAYS);
      map.put("dashboardType", dashboeardType);
      return "/batch_cards/card_dashboard";
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      map.put(CODE_ERRO_RQP, 1);
      map.put(MESSAGE_ERRO_RQP, e.getMessage());

      return "/batch_cards/card_dashboard";
    }
  }

  @GetMapping(value = "/card-dashboard/statistic")
  @ResponseBody
  public String statisticDashboard(HttpServletRequest httpRequest)
      throws FrontEndException, ParseException {
    JSONObject jDashboard = new JSONObject();
    try {
      String dashboeardType = httpRequest.getParameter("dashboardType");
      if (!(DASHBOARD_TYPE_API.equals(dashboeardType)
          || DASHBOARD_TYPE_EXPORT.equals(dashboeardType)
          || DASHBOARD_TYPE_API_N02.equals(dashboeardType))) {
        return "/about/404";
      }

      String[] faceValues = httpRequest.getParameterValues("faceValues[]");
      List<Integer> faceValueList = new ArrayList<>();
      if (faceValues != null) {
        for (String faceValue : faceValues) {
          try {
            faceValueList.add(Integer.parseInt(faceValue));
          } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e);
          }
        }
      }

      String[] cardTypes = httpRequest.getParameterValues("cardTypes[]");
      List<String> cardTypeList = new ArrayList<>();
      if (cardTypes != null) {
        for (String cardType : cardTypes) {
          if (StringUtils.isNotBlank(cardType)) {
            cardTypeList.add(cardType);
          }
        }
      }

      GetCustomerCardDashboardRequest dashboardRequest = new GetCustomerCardDashboardRequest();
      dashboardRequest.setCardTypes(cardTypeList);
      dashboardRequest.setFaceValues(faceValueList);

      long startRequest = new Date().getTime();

      GetCustomerCardDashboardResponse dashBoardResponse = null;
      if (DASHBOARD_TYPE_API.equals(dashboeardType)) {
        dashBoardResponse = epinEndpoint.epoGetCardDashBoard(dashboardRequest);
      } else if (DASHBOARD_TYPE_EXPORT.equals(dashboeardType)) {
        dashBoardResponse = epinEndpoint.epoGetCardDashBoardOffline(dashboardRequest);
      } else if (DASHBOARD_TYPE_API_N02.equals(dashboeardType)) {
        dashBoardResponse = epinEndpoint.epoGetCardDashBoardN02(dashboardRequest);
      }

      if (dashBoardResponse == null || dashBoardResponse.getStatus() == null) {
        return jDashboard.toString();
      }

      if (dashBoardResponse.getStatus().getCode() == 205) {
        return JsonUtil.objectToJson(dashBoardResponse.getStatus());
      }

      Map<String, Map<Integer, StoreNotfInfo>> warningLevelResult =
          dashBoardResponse.getWarningLevelResult();
      if (warningLevelResult == null) {
        warningLevelResult = new HashMap<>();
      }
      long endResponse = new Date().getTime();
      if (dashBoardResponse.getCardDashBoardItems() != null) {
        chartFollowStatus(dashBoardResponse.getCardDashBoardItems(), jDashboard);
        chartFollowCardType(
            dashBoardResponse.getCardDashBoardItems(), warningLevelResult, jDashboard);
      }
      long calulateTime = new Date().getTime();
      LOGGER.debug("time request---" + (endResponse - startRequest));
      LOGGER.debug("time caculator-" + (calulateTime - endResponse));
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }

    return jDashboard.toString();
  }

  /** chartFollowStatus */
  private void chartFollowStatus(List<CardDashBoardItem> items, JSONObject jDashboard) {

    HashMap<String, JSONObject> hashMap = sumCountData(items);

    jDashboard.put(CARD_INPUTTED, hashMap.get(CARD_INPUTTED));
    jDashboard.put(CARD_SOLD, hashMap.get(CARD_SOLD));
    jDashboard.put(CARD_ACTIVATE, hashMap.get(CARD_ACTIVATE));
    jDashboard.put(CARD_DEACTIVATE, hashMap.get(CARD_DEACTIVATE));
    jDashboard.put(CARD_EXPIRED, hashMap.get(CARD_EXPIRED));
    jDashboard.put(CARD_PRE_EXPIRED, hashMap.get(CARD_PRE_EXPIRED));

    List<PieChartItem> pieNumbers = new ArrayList<>();
    pieNumbers.add(new PieChartItem("Sold", hashMap.get(CARD_SOLD).getLong(NUMBER))); // Thẻ đã bán
    pieNumbers.add(
        new PieChartItem("Active", hashMap.get(CARD_ACTIVATE).getLong(NUMBER))); // Thẻ còn tồn
    pieNumbers.add(
        new PieChartItem("Inactive", hashMap.get(CARD_DEACTIVATE).getLong(NUMBER))); // Thẻ còn tồn

    jDashboard.put("pieDataNumberCards", JsonUtil.objectToJson(pieNumbers));

    List<PieChartItem> pieValues = new ArrayList<>();
    pieValues.add(new PieChartItem("Sold", hashMap.get(CARD_SOLD).getLong(VALUE))); // Thẻ đã bán
    pieValues.add(
        new PieChartItem("Active", hashMap.get(CARD_ACTIVATE).getLong(VALUE))); // Thẻ còn tồn
    pieValues.add(
        new PieChartItem("Inactive", hashMap.get(CARD_DEACTIVATE).getLong(VALUE))); // Thẻ còn tồn

    jDashboard.put("pieDataFaceValues", JsonUtil.objectToJson(pieValues));
  }

  /** chartFollowCardType */
  private void chartFollowCardType(
      List<CardDashBoardItem> itemTables,
      Map<String, Map<Integer, StoreNotfInfo>> warningLevelResult,
      JSONObject jsonDashboard) {

    HashMap<String, List<CardDashBoardItem>> rawData = new HashMap<>();

    // sort by Card Type
    for (CardDashBoardItem item : itemTables) {
      if (rawData.containsKey(item.getCardType().name)) {
        List<CardDashBoardItem> chartRowItems = rawData.get(item.getCardType().name);
        chartRowItems.add(item);
        rawData.put(item.getCardType().name, chartRowItems);
      } else {
        List<CardDashBoardItem> rowItems = new ArrayList<>();
        rowItems.add(item);
        rawData.put(item.getCardType().name, rowItems);
      }
    }

    // summary card type
    HashMap<String, List<CardSummaryWarning>> cardSumaryWarning = new HashMap<>();

    // sum value && count
    HashMap<String, List<ChartRowItem>> chartDataStatict = new HashMap<>();

    ChartRowItem chartRowItem;
    CardSummaryWarning cardSummaryWarning;

    for (Map.Entry<String, List<CardDashBoardItem>> rawItem : rawData.entrySet()) {
      String cardType = rawItem.getKey();
      HashMap<String, JSONObject> hashCardType = caculatorByFacevalue(rawItem.getValue());

      List<ChartRowItem> chartRowItems = new ArrayList<>();
      List<CardSummaryWarning> cardSummaryWarnings = new ArrayList<>();

      Set<String> faceValueExistSet = new HashSet<>();
      for (Map.Entry<String, JSONObject> item : hashCardType.entrySet()) {
        JSONObject sumValueCard = item.getValue();
        chartRowItem =
            new ChartRowItem(
                rawItem.getKey(),
                item.getKey(),
                sumValueCard.isNull(CARD_INPUTTED) ? 0L : sumValueCard.getLong(CARD_INPUTTED),
                sumValueCard.isNull(CARD_SOLD) ? 0L : sumValueCard.getLong(CARD_SOLD),
                sumValueCard.isNull(CARD_ACTIVATE) ? 0L : sumValueCard.getLong(CARD_ACTIVATE),
                sumValueCard.isNull(CARD_DEACTIVATE) ? 0L : sumValueCard.getLong(CARD_DEACTIVATE),
                sumValueCard.isNull(CARD_PRE_EXPIRED) ? 0L : sumValueCard.getLong(CARD_PRE_EXPIRED),
                sumValueCard.isNull(CARD_EXPIRED) ? 0L : sumValueCard.getLong(CARD_EXPIRED));

        // Calculate warning here
        String faceValue = chartRowItem.getFaceValue();
        faceValueExistSet.add(faceValue);
        long level =
            checkCardWarning(cardType, NumberUtil.convertToInt(faceValue), warningLevelResult);
        chartRowItem.setLeverWarning(level);

        chartRowItems.add(chartRowItem);

        cardSummaryWarning = new CardSummaryWarning(NumberUtil.formatNumber(faceValue), level);
        cardSummaryWarnings.add(cardSummaryWarning);
      }

      chartDataStatict.put(cardType, chartRowItems);
      cardSumaryWarning.put(cardType, cardSummaryWarnings);
    }

    jsonDashboard.put("cardSumaryWarning", JsonUtil.objectToJson(cardSumaryWarning));
    jsonDashboard.put("cardRowItemes", JsonUtil.objectToJson(chartDataStatict));
  }

  /**
   * chartFollowStatus đếm số lượng thẻ, INPUT CARD , ... trên 1 face value, trên 1nhà mạng trong
   * màn hình tổng quát, hàng 1
   */
  private HashMap<String, JSONObject> sumCountData(List<CardDashBoardItem> items) {

    HashMap<String, JSONObject> hashMap = new HashMap<>();

    Long numberSold = 0L;
    Long valueSold = 0L;
    Long capitalSold = 0L;

    Long numberActivate = 0L;
    Long valueActivate = 0L;
    Long capitalActivate = 0L;

    Long numberDeactivate = 0L;
    Long valueDeactivate = 0L;
    Long capitalDeactivate = 0L;

    Long numberNearExpired = 0L;
    Long valueNearExpired = 0L;
    Long capitalNearExpired = 0L;

    Long numberExpired = 0L;
    Long valueExpired = 0L;
    Long capitalExpired = 0L;

    for (CardDashBoardItem item : items) {
      if (CardStatus.IN_STOCK.code == item.getStatus().code && Stage.ON == item.getStage()) {
        numberActivate += item.getTotal();
        valueActivate += item.getFaceValue() * item.getTotal();
        capitalActivate += item.getCapital();

      } else if (CardStatus.IN_STOCK.code == item.getStatus().code
          && Stage.OFF == item.getStage()) {
        numberDeactivate += item.getTotal();
        valueDeactivate += item.getFaceValue() * item.getTotal();
        capitalDeactivate += item.getCapital();

      } else if (CardStatus.SOLD.code == item.getStatus().code) {
        numberSold += item.getTotal();
        valueSold += item.getFaceValue() * item.getTotal();
        capitalSold += item.getCapital();

      } else if (CardStatus.EXPIRED.code == item.getStatus().code) {
        numberExpired += item.getTotal();
        valueExpired += item.getFaceValue() * item.getTotal();
        capitalExpired += item.getCapital();

      } else if (CardStatus.NEAR_EXP.code == item.getStatus().code) {
        numberNearExpired += item.getTotal();
        valueNearExpired += item.getFaceValue() * item.getTotal();
        capitalNearExpired += item.getCapital();
      }
    }
    Long numberInputted = numberActivate + numberDeactivate + numberSold;
    Long valueInputted = valueActivate + valueDeactivate + valueSold;
    Long capitalInputted = capitalActivate + capitalDeactivate + capitalSold;

    JSONObject cardInputted = new JSONObject();
    cardInputted.put(NUMBER, numberInputted);
    cardInputted.put(VALUE, valueInputted);
    cardInputted.put(CAPITAL, capitalInputted);

    JSONObject cardSold = new JSONObject();
    cardSold.put(NUMBER, numberSold);
    cardSold.put(VALUE, valueSold);
    cardSold.put(CAPITAL, capitalSold);

    JSONObject cardActivate = new JSONObject();
    cardActivate.put(NUMBER, numberActivate);
    cardActivate.put(VALUE, valueActivate);
    cardActivate.put(CAPITAL, capitalActivate);

    JSONObject cardDeactivate = new JSONObject();
    cardDeactivate.put(NUMBER, numberDeactivate);
    cardDeactivate.put(VALUE, valueDeactivate);
    cardDeactivate.put(CAPITAL, capitalDeactivate);

    JSONObject cardExpired = new JSONObject();
    cardExpired.put(NUMBER, numberExpired);
    cardExpired.put(VALUE, valueExpired);
    cardExpired.put(CAPITAL, capitalExpired);

    JSONObject cardPreExpired = new JSONObject();
    cardPreExpired.put(NUMBER, numberNearExpired);
    cardPreExpired.put(VALUE, valueNearExpired);
    cardPreExpired.put(CAPITAL, capitalNearExpired);

    hashMap.put(CARD_INPUTTED, cardInputted);
    hashMap.put(CARD_SOLD, cardSold);
    hashMap.put(CARD_ACTIVATE, cardActivate);
    hashMap.put(CARD_DEACTIVATE, cardDeactivate);
    hashMap.put(CARD_EXPIRED, cardExpired);
    hashMap.put(CARD_PRE_EXPIRED, cardPreExpired);

    return hashMap;
  }

  private long checkCardWarning(
      String cardTypeWarning,
      Integer faceValue,
      Map<String, Map<Integer, StoreNotfInfo>> warningLevelResult) {
    if (warningLevelResult.containsKey(cardTypeWarning)) {
      Map<Integer, StoreNotfInfo> integerIntegerMap = warningLevelResult.get(cardTypeWarning);
      if (integerIntegerMap.containsKey(faceValue)) {
        StoreNotfInfo storeNotfInfo = integerIntegerMap.get(faceValue);
        return storeNotfInfo.getWarnLevel() + 1L;
      }
    }
    return 0;
  }

  /** chartFollowCardType Tính toán tổng các Facevalue */
  private HashMap<String, JSONObject> caculatorByFacevalue(
      List<CardDashBoardItem> cardDashBoardItems) {
    HashMap<String, JSONObject> hashMapCardDashBoardItem = new HashMap<>();
    for (CardDashBoardItem itemCardType : cardDashBoardItems) {

      String cardStatus = StringUtils.EMPTY;

      if (CardStatus.IN_STOCK.code == itemCardType.getStatus().code
          && Stage.ON == itemCardType.getStage()) {
        cardStatus = CARD_ACTIVATE;
      } else if (CardStatus.IN_STOCK.code == itemCardType.getStatus().code
          && Stage.OFF == itemCardType.getStage()) {
        cardStatus = CARD_DEACTIVATE;
      } else if (CardStatus.SOLD.code == itemCardType.getStatus().code) {
        cardStatus = CARD_SOLD;
      } else if (CardStatus.EXPIRED.code == itemCardType.getStatus().code) {
        cardStatus = CARD_EXPIRED;
      } else if (CardStatus.NEAR_EXP.code == itemCardType.getStatus().code) {
        cardStatus = CARD_PRE_EXPIRED;
      }

      sumFacevalue(
          hashMapCardDashBoardItem,
          String.valueOf(itemCardType.getFaceValue()),
          cardStatus,
          itemCardType.getTotal());
    }

    for (Map.Entry<String, JSONObject> item : hashMapCardDashBoardItem.entrySet()) {
      JSONObject value = item.getValue();
      value.put(
          CARD_INPUTTED,
          (value.isNull(CARD_SOLD) ? 0L : value.getLong(CARD_SOLD))
              + (value.isNull(CARD_ACTIVATE) ? 0L : value.getLong(CARD_ACTIVATE))
              + (value.isNull(CARD_DEACTIVATE) ? 0L : value.getLong(CARD_DEACTIVATE)));
      hashMapCardDashBoardItem.put(item.getKey(), value);
    }

    return hashMapCardDashBoardItem;
  }

  private void sumFacevalue(
      HashMap<String, JSONObject> hashMap, String faceValue, String cardType, long total) {
    if (hashMap.containsKey(faceValue)) {
      JSONObject jFaceValue = hashMap.get(faceValue);
      if (jFaceValue.isNull(cardType)) {
        jFaceValue.put(cardType, total);
      } else {
        long aLong = jFaceValue.getLong(cardType);
        aLong += total;
        jFaceValue.put(cardType, aLong);
      }
      hashMap.put(faceValue, jFaceValue);
    } else {
      JSONObject jCardType = new JSONObject();
      jCardType.put(cardType, total);
      hashMap.put(faceValue, jCardType);
    }
  }
}
