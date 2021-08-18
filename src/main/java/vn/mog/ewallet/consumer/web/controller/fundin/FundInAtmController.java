package vn.mog.ewallet.consumer.web.controller.fundin;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.FUND_IN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.mog.ewallet.consumer.web.common.SharedConstants;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.RequestChargingFundInOverPaymentGatewayV3Request;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.RequestChargingFundInOverPaymentGatewayV3Response;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.TrueFundPaymentGatewayPaymentType;
import vn.mog.ewallet.consumer.web.util.tools.HmacSHA256;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

@Controller
@RequestMapping(value = "/fundin/atm")
public class FundInAtmController extends AbstractController {

  public static final String FUNDIN_ATM_CONTROLLER = "/fundin/atm";
  public static final String FUNDIN_ATM_INFO = FUNDIN_ATM_CONTROLLER + "/info";

  private static final Logger LOGGER = LogManager.getLogger(FundInAtmController.class);
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

  //Session Attribute
  private static final String NAME_BANK_ATTRIBUTE = "NAME_BANK";
  private static final String BANK_CODE_ATTRIBUTE = "BANK_CODE";
  private static final String BANK_NAME_ATTRIBUTE = "BANK_NAME";
  private static final String BANK_DISPLAY_NAME_ATTRIBUTE = "BANK_DISPLAY_NAME";
  private static final String PHONE_NUMBER_ATTRIBUTE = "PHONE_NUMBER";
  private static final String AMOUNT_ATTRIBUTE = "AMOUNT";
  private static final String REAL_AMOUNT_ATTRIBUTE = "REAL_AMOUNT";
  private static final String SO_TAI_KHOAN_ATTRIBUTE = "SO_TAI_KHOAN";
  private static final String FEE_AMOUNT_ATTRIBUTE = "FEE_AMOUNT";
  private static final String CODE_ERROR_ATTRIBUTE = "CODE_ERROR";
  private static final String LIST_TRANSACTIONS_ATTRIBUTE = "LISTT_RANSACTIONS";

  @Autowired
  DashboardController dashboardController;

  // --------------ATM
  @GetMapping(value = "")
  public String fundInATM(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {
      // get list bank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(null);
      findBankRequest.setIsLinkBankSupport(null);
      FindBankResponse baseResponseType =
          systemEndpoint.getListBank(findBankRequest);

      if (baseResponseType == null || baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }
      // set get dữ liệu
      List<Bank> listBank = new ArrayList<Bank>();
      if (baseResponseType.getBanks().size() >= 1) {
        listBank = baseResponseType.getBanks();
      }

      map.put("listBank", listBank);
      return "/fundin/fundin_atm";
    } catch (Exception e) {
      e.printStackTrace();
      return "/fundin/fundin_atm";
    }

  }


  @GetMapping(value = "/info")
  public String fundInAtmInfoGet(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    map.put("listTransactions", SessionUtil.getAttribute(LIST_TRANSACTIONS_ATTRIBUTE));
    map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
    map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
    map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
    map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
    map.put("_soTaiKhoan", SessionUtil.getAttribute(SO_TAI_KHOAN_ATTRIBUTE));
    map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));

    return "/fundin/fundin_atm_info";
  }

  @PostMapping(value = "/info")
  public String fundInAtmInfoPost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String _nameBank = null;
      dashboardController.getPhoneTopUp(request, response, map);

      getTransaction(request, response, map);

      if (!StringUtils.isEmpty(request.getParameter("_nameBank"))) {
        _nameBank = request.getParameter("_nameBank");

      }
      String soTaiKhoan = request.getParameter("_soTaiKhoan");
      String amount = request.getParameter("amount");

      // lấy thôn tin theo nameBank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(_nameBank);
      findBankRequest.setIsLinkBankSupport(null);

      FindBankResponse findBankResponseType = systemEndpoint.getListBank(findBankRequest);

      if (findBankResponseType.getStatus().getCode() != 0 || findBankResponseType.getBanks() == null
          || findBankResponseType.getBanks().size() != 1) {
        throw new Exception("No data list link bank");
      }

      Bank bank = findBankResponseType.getBanks().get(0);
      String bankCode = bank.getBankCode();
      String bankName = bank.getBankName();
      String bankDisplayName = bank.getDisplayName();

      // Store to Session
      SessionUtil.setAttribute(NAME_BANK_ATTRIBUTE, _nameBank);
      SessionUtil.setAttribute(SO_TAI_KHOAN_ATTRIBUTE, soTaiKhoan);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);

      map.put("_nameBank", _nameBank);
      map.put("bankCode", bankCode);
      map.put("bankName", bankName);
      map.put("bankDisplayName", bankDisplayName);
      map.put("_soTaiKhoan", soTaiKhoan);
      map.put("amount", amount);

      return "/fundin/fundin_atm_info";
    } catch (Exception e) {
      e.printStackTrace();
      getTransaction(request, response, map);
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("_soTaiKhoan", SessionUtil.getAttribute(SO_TAI_KHOAN_ATTRIBUTE));
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      return "/fundin/fundin_atm_info";
    }
  }


  @GetMapping(value = "/request")
  public String fundInATMRequest(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      String phoneNumber = request.getParameter("phoneNumber");
      String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
      String feeAmount = request.getParameter("feeAmount").replaceAll("[^0-9]+", "");
      String realAmount = request.getParameter("realAmount").replaceAll("[^0-9]+", "");

      // Store to Session
      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(FEE_AMOUNT_ATTRIBUTE, feeAmount);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
      //--end--

      map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
      map.put("phoneNumber", phoneNumber);
      map.put("amount", amount);
      map.put("feeAmount", feeAmount);
      map.put("realAmount", realAmount);
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      return "/fundin/fundin_atm_request";
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      return "/fundin/fundin_atm_request";
    }

  }

  @PostMapping(value = "/request")
  public String fundInATMPost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      String phoneNumber = userLogin.getPhoneNumber();

//      String soTaiKhoan = request.getParameter("soTaiKhoan");
//      soTaiKhoan = phoneNumber;

      String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
      String feeAmount = request.getParameter("feeAmount").replaceAll("[^0-9]+", "");
      String realAmount = request.getParameter("realAmount").replaceAll("[^0-9]+", "");
      String bankCode = request.getParameter("bankCode");

      // Store to Session
      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(FEE_AMOUNT_ATTRIBUTE, feeAmount);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);

      String resultUrl =
          SharedConstants.BASE_URL + SharedConstants.FUND_IN_ORDER_VIA_PAYMENT_GATEWAY_RESULT_URI;
      String signatureData = amount + SharedConstants.VERTICAL_BAR + resultUrl;
      HmacSHA256 hMac = HmacSHA256.getInstance(SharedConstants.PAYMENT_SERVICE_SECRET_KEY);

      RequestChargingFundInOverPaymentGatewayV3Request fundinRequest =
          new RequestChargingFundInOverPaymentGatewayV3Request();
      fundinRequest.setAmount(new BigDecimal(amount));
      fundinRequest.setResultUrl(resultUrl);
      fundinRequest.setBankCode(bankCode);
      fundinRequest.setPaymentType(TrueFundPaymentGatewayPaymentType.BANK_CHARGING.code);
      fundinRequest.setCustomerEmail(userLogin.getEmail());
      fundinRequest.setCustomerName(userLogin.getFullName());
      fundinRequest.setCustomerPhone(phoneNumber);
      fundinRequest.setLanguage(LocaleContextHolder.getLocale().getLanguage());
      fundinRequest.setSignature(hMac.sign(signatureData));

      RequestChargingFundInOverPaymentGatewayV3Response fundinResponseType = walletEndpoint
          .fundInChargeV3(fundinRequest);
      if (fundinResponseType == null || fundinResponseType.getStatus() == null) {
        throw new Exception("No Response !");
      }
      if (fundinResponseType.getStatus().getCode() != 0) {
        throw new Exception(fundinResponseType.getStatus().getValue());
      }

      map.put("payUrl", fundinResponseType.getPayUrl());
      return "redirect:/fundin/atm/fundin-redirect";
    } catch (Exception e) {
      String codeErr = e.getMessage();
      LOGGER.error(codeErr);
      map.put("codeErr", codeErr);
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      map.put("success", "false");

      return "redirect:/fundin/atm/fundin-atm-result";
    }

  }

  @GetMapping(value = "/fundin-redirect")
  public String fundInRedirect(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    String payUrl = request.getParameter("payUrl");

    if (StringUtils.isNotBlank(payUrl)) {
      map.put("payUrl", payUrl);

      return "/fundin/fundin_redirect";
    }

    map.put("success", "false");

    return "redirect:/fundin/atm/fundin-atm-result";
  }

  @PostMapping(value = "/confirm")
  public String fundInATMConfirm(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      String phoneNumber = request.getParameter("phoneNumber");
      String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
      String feeAmount = request.getParameter("feeAmount").replaceAll("[^0-9]+", "");
      String realAmount = request.getParameter("realAmount").replaceAll("[^0-9]+", "");

      // Store to Session
      SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(FEE_AMOUNT_ATTRIBUTE, feeAmount);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);

      map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
      map.put("phoneNumber", phoneNumber);
      map.put("amount", amount);
      map.put("feeAmount", feeAmount);
      map.put("realAmount", realAmount);
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      return "/fundin/fundin_atm_request";
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      return "/fundin/fundin_atm_request";
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
        transactionsRequest.setServiceTypeIds(Arrays.asList(FUND_IN.name()));
      }
      String[] serviceCode = request.getParameterValues("serviceCode");
      if (serviceCode != null && serviceCode.length > 0
          && !serviceCode[0].equals(StringUtils.EMPTY)) {
        transactionsRequest.setServiceCodes(Arrays.asList(serviceCode));
      }

      Collection<Transaction> listTransactions = new ArrayList<>();

      FindTransactionsResponse baseTransResponseType = transactionEndpoint
          .transactionFind(transactionsRequest);
      if (baseTransResponseType == null || baseTransResponseType.getStatus() == null) {
        throw new Exception("API transactionFind fail");
      }
      if (baseTransResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseTransResponseType.getStatus().getValue());
      }
      listTransactions = baseTransResponseType.getTransactions();

      SessionUtil.setAttribute(LIST_TRANSACTIONS_ATTRIBUTE, listTransactions);
      map.put("pagesize", limit);
      map.put("offset", offset);
      map.put("listTransactions", listTransactions);
      map.put("total", total.intValue());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(),e);
    }
  }

  @GetMapping(value = "/fundin-atm-result")
  public String fundInATMSuccess(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    String phoneNumber = userLogin.getPhoneNumber();

//    String signature = request.getParameter("signature");
    String success = request.getParameter("success");

    String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
    String card_name = request.getParameter("card_name");
    String card_type = request.getParameter("card_type");
    String order_id = request.getParameter("order_id");
    String message = request.getParameter("message");

    map.put("phoneNumber", phoneNumber);
    map.put("amount", amount);
    map.put("card_name", card_name);
    map.put("card_type", card_type);
    map.put("order_id", order_id);
    map.put("message", message);

    if ("true".equalsIgnoreCase(success)) {
      return "/fundin/fundin_atm_transactionSuccess";
    }

    return "/fundin/fundin_atm_transactionError";
  }

}
