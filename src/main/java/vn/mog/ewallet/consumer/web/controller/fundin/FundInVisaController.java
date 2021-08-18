package vn.mog.ewallet.consumer.web.controller.fundin;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import javax.print.attribute.standard.MediaSize.NA;
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
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.bean.CustomerBankItem;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.RequestChargingFundInOverPaymentGatewayV3Request;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.RequestChargingFundInOverPaymentGatewayV3Response;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.TrueFundPaymentGatewayPaymentType;
import vn.mog.ewallet.consumer.web.util.tools.HmacSHA256;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

@Controller
@RequestMapping(value = "/fundin/visa")
public class FundInVisaController extends AbstractController {

  public static final String FUND_IN_VISA_CONTROLLER = "/fundin/visa";

  private static final Logger LOGGER = LogManager.getLogger(FundInVisaController.class);

  private static final String NAME_BANK_ATTRIBUTE = "NAME_BANK";
  private static final String BANK_CODE_ATTRIBUTE = "BANK_CODE";
  private static final String PHONE_NUMBER_ATTRIBUTE = "PHONE_NUMBER";
  private static final String AMOUNT_ATTRIBUTE = "AMOUNT";
  private static final String REAL_AMOUNT_ATTRIBUTE = "REAL_AMOUNT";
  private static final String FEE_AMOUNT_ATTRIBUTE = "FEE_AMOUNT";
  private static final String FULL_NAME_ATTRIBUTE = "FULL_NAME";
  private static final String SO_TAI_KHOAN_ATTRIBUTE = "SO_TAI_KHOAN";
  private static final String EMAIL_ATTRIBUTE = "EMAIL";
  private static final String CODE_ERROR_ATTRIBUTE = "CODE_ERROR";
  private static final String LIST_TRANSACTIONS_ATTRIBUTE = "LIST_TRANSACTIONS";

//  private static final Collection<Transaction> listTransactions;

  @Autowired
  DashboardController dashboardController;

  @Autowired
  FundInAtmController fundInAtmController;

  @GetMapping(value = "/info")
  public String fundInVisa(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    map.put("listTransactions", SessionUtil.getAttribute(LIST_TRANSACTIONS_ATTRIBUTE));
    map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
    map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
    map.put("_soTaiKhoan", SessionUtil.getAttribute(SO_TAI_KHOAN_ATTRIBUTE));
    map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
    map.put("email", SessionUtil.getAttribute(EMAIL_ATTRIBUTE));

    return "/fundin/fundin_visa_info";
  }

  @PostMapping(value = "/info")
  public String fundInVisaInfoPost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    try {
      dashboardController.getPhoneTopUp(request, response, map);

      fundInAtmController.getTransaction(request, response, map);
      String _nameBank = request.getParameter("_nameBank");
      if (!StringUtils.isEmpty(_nameBank)) {
        _nameBank = request.getParameter("_nameBank");
      }
      String soTaiKhoan = request.getParameter("_soTaiKhoan");
      String amount = request.getParameter("amount");

      // Store to Session
      SessionUtil.setAttribute(NAME_BANK_ATTRIBUTE, _nameBank);
      SessionUtil.setAttribute(SO_TAI_KHOAN_ATTRIBUTE, soTaiKhoan);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);

      map.put("_nameBank", _nameBank);
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("_soTaiKhoan", soTaiKhoan);
      map.put("amount", amount);
      map.put("email", SessionUtil.getAttribute(EMAIL_ATTRIBUTE));
      return "/fundin/fundin_visa_info";
    } catch (Exception e) {
      e.printStackTrace();
      fundInAtmController.getTransaction(request, response, map);
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("_soTaiKhoan", SessionUtil.getAttribute(SO_TAI_KHOAN_ATTRIBUTE));
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("email", SessionUtil.getAttribute(EMAIL_ATTRIBUTE));
      return "/fundin/fundin_visa_info";
    }
  }

  @GetMapping(value = "/request")
  public String fundInVisaRequest(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String email = request.getParameter("email");
      String phoneNumber = request.getParameter("phoneNumber");
      String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
      String feeAmount = request.getParameter("feeAmount").replaceAll("[^0-9]+", "");
      String realAmount = request.getParameter("realAmount").replaceAll("[^0-9]+", "");
      // Store to Session
      SessionUtil.setAttribute(EMAIL_ATTRIBUTE, email);
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
      map.put("email", email);
      return "/fundin/fundin_visa_request";
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
      map.put("_soTaiKhoan", SessionUtil.getAttribute(SO_TAI_KHOAN_ATTRIBUTE));
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("_nameBank", SessionUtil.getAttribute(NAME_BANK_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("email", SessionUtil.getAttribute(EMAIL_ATTRIBUTE));
      return "/fundin/fundin_visa_request";
    }

  }

  @PostMapping(value = "/request")
  public String fundInVisaPost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      if (userLogin != null) {
        String phoneNumber = userLogin.getPhoneNumber();
        String fullName = userLogin.getFullName();
        // Store to Session
        SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE, phoneNumber);
        SessionUtil.setAttribute(FULL_NAME_ATTRIBUTE, fullName);
      }

      String soTaiKhoan = request.getParameter("_soTaiKhoan");
      Object phoneNumberAttr = SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE);
      soTaiKhoan = phoneNumberAttr != null ? phoneNumberAttr.toString() : null;

      String amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
      String feeAmount = request.getParameter("feeAmount").replaceAll("[^0-9]+", "");
      String realAmount = request.getParameter("realAmount").replaceAll("[^0-9]+", "");
      String bankCode = request.getParameter("bankCode");
      String email = request.getParameter("email");
      // Store to Session
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(FEE_AMOUNT_ATTRIBUTE, feeAmount);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
      SessionUtil.setAttribute(EMAIL_ATTRIBUTE, email);
      SessionUtil.setAttribute(SO_TAI_KHOAN_ATTRIBUTE, soTaiKhoan);

      String resultUrl =
          SharedConstants.BASE_URL + SharedConstants.FUND_IN_ORDER_VISA_PAYMENT_GATEWAY_RESULT_URI;
      String signatureData = amount + SharedConstants.VERTICAL_BAR + resultUrl;
      HmacSHA256 hMac = HmacSHA256.getInstance(SharedConstants.PAYMENT_SERVICE_SECRET_KEY);

      Object fullNameAttr = SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE);
      String fullName = fullNameAttr != null ? fullNameAttr.toString() : null;
      RequestChargingFundInOverPaymentGatewayV3Request fundinRequest =
          new RequestChargingFundInOverPaymentGatewayV3Request();
      fundinRequest.setAmount(new BigDecimal(amount));
      fundinRequest.setResultUrl(resultUrl);
      fundinRequest.setBankCode(bankCode);
      fundinRequest.setPaymentType(TrueFundPaymentGatewayPaymentType.VISA_CHARGING.code);
      fundinRequest.setCustomerName(fullName);
      if (email != null) {
        fundinRequest.setCustomerEmail(email);
      } else {
        fundinRequest.setCustomerEmail(userLogin.getEmail());
      }

      fundinRequest.setCustomerPhone(soTaiKhoan);
      fundinRequest.setLanguage(LocaleContextHolder.getLocale().getLanguage());
      fundinRequest.setSignature(hMac.sign(signatureData));

      RequestChargingFundInOverPaymentGatewayV3Response fundinResponseType = walletEndpoint
          .fundInChargeV3(fundinRequest);
      if (fundinResponseType == null || fundinResponseType.getStatus().getCode() != 0) {
        throw new Exception(fundinResponseType.getStatus().getValue());
      }

      map.put("payUrl", fundinResponseType.getPayUrl());
      return "redirect:/fundin/visa/fundin-redirect";
    } catch (Exception e) {
      String codeErr = e.getMessage();
      LOGGER.error(codeErr);
      map.put("codeErr", codeErr);
      map.put("phoneNumber", SessionUtil.getAttribute(PHONE_NUMBER_ATTRIBUTE));
      map.put("amount", SessionUtil.getAttribute(AMOUNT_ATTRIBUTE));
      map.put("feeAmount", SessionUtil.getAttribute(FEE_AMOUNT_ATTRIBUTE));
      map.put("realAmount", SessionUtil.getAttribute(REAL_AMOUNT_ATTRIBUTE));
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("email", SessionUtil.getAttribute(EMAIL_ATTRIBUTE));
      map.put("success", "false");

      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      SessionUtil.removeAttribute(codeErr);

      return "redirect:/fundin/visa/fundin-visa-result";
    }
  }

  @GetMapping(value = "/fundin-visa-result")
  public String fundInVisaSuccess(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    String phoneNumber = userLogin.getPhoneNumber();
    String signature = request.getParameter("signature");
    String success = request.getParameter("success");

    String amount = request.getParameter("amount");
    if (amount != null) {
      amount = request.getParameter("amount").replaceAll("[^0-9]+", "");
    } else {
      amount = "0";
    }
    // Store to Session
    SessionUtil.setAttribute(PHONE_NUMBER_ATTRIBUTE,phoneNumber);
    SessionUtil.setAttribute(AMOUNT_ATTRIBUTE,amount);
    //end
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
      return "/fundin/fundin_visa_transactionSuccess";
    }

    return "/fundin/fundin_visa_transactionError";
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

    return "redirect:/fundin/visa/fundin-visa-result";
  }
}
