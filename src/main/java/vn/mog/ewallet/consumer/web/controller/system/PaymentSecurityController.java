package vn.mog.ewallet.consumer.web.controller.system;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SECURE_KEY;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SECURE_REFERENCE_ID;

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IWalletEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentRegisterRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentRegisterResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckPaymentPinResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SetupPaymentPinRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SetupPaymentPinResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.VerifySetupPaymentPinRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.VerifySetupPaymentPinResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.OtpVerificationChannel;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

@Controller
@RequestMapping(value = "/system")
public class PaymentSecurityController extends AbstractController {

  private static final Logger LOGGER = LogManager
      .getLogger(PaymentSecurityController.class);

  public static final String DASH_BOARD = "/dashboard/index";

  public static final String PAYMENT_SECURITY_CONTROLLER = "/system";
  public static final String PAYMENT_SECURITY =
      PAYMENT_SECURITY_CONTROLLER + "/payment-security/sms/limitation";

  private static final String OLD_LIMITATION_ATTRIBUTE = "OLD_LIMITATION";
  public static final String REFERENCE_ID_ATTRIBUTE = "REFERENCE_ID";
  private static final String LIMITATION_ATTRIBUTE = "LIMITATION";
  private static final String IS_SETUP_PAYMENT_PIN_ATTRIBUTE = "IS_SETUP_PAYMENT_PIN";
  private static final String CODE_ERROR_ATTRIBUTE = "CODE_ERROR";

//  @GetMapping(value = "/payment-security/sms/limitation")
//  public String paymentSecurityInfo(HttpServletRequest request, HttpServletResponse response,
//      ModelMap map) throws Exception {
//    map.put("oldLimitation", oldLimitation);
//
//    if (StringUtils.isNotEmpty(oldLimitation.toString())) {
//      return "/payment_security/cai_dat_bao_mat";
//    } else {
//      return "/payment_security/payment_pin_setting";
//    }
//  }

  @SuppressWarnings("unchecked")
  @PostMapping(value = "/payment-security")
  public String caiDatBaoMat(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String oldLimitationString = request.getParameter("currentLimitation");
      getOldLimitation(oldLimitationString);
      map.put("oldLimitation", SessionUtil.getAttribute(OLD_LIMITATION_ATTRIBUTE));

      boolean paymenPinIsSetup = PaymentSecurityController.checkPaymentPIN(walletEndpoint);
      map.put("payment_pin_is_setup", paymenPinIsSetup);
      SessionUtil.setAttribute(IS_SETUP_PAYMENT_PIN_ATTRIBUTE, paymenPinIsSetup);

      if (paymenPinIsSetup) {
        GetConsumerAccountResponse accountInfo = getAccountInfo();
        String paymentPinLastChange = StringUtils.EMPTY;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm-dd/MM/yyyy");
        if (accountInfo != null && accountInfo.getExtraInfo() != null
            && accountInfo.getExtraInfo().getLastChangedPaymentPin() != null) {
          paymentPinLastChange = sdf.format(accountInfo.getExtraInfo().getLastChangedPaymentPin());
        }
        map.put("payment_pin_last_change", paymentPinLastChange);
      }

      return "/payment_security/cai_dat_bao_mat";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();
      map.put("codeErr", codeErr);

      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);

      return DashboardController.PAGE_DASHBOARD;
    }
  }

  private void getOldLimitation(String oldLimitationString) throws Exception {
    if (oldLimitationString == null) {
      SecurityPaymentGetRequest securityPaymentGetRequest = new SecurityPaymentGetRequest();

      SecurityPaymentGetResponse securityPaymentGetResponse = walletEndpoint
          .securityPaymentGet(securityPaymentGetRequest);
      if (securityPaymentGetResponse == null) {
        throw new Exception("No response");
      }
      if (securityPaymentGetResponse.getStatus().getCode() != 0) {
        throw new Exception(securityPaymentGetResponse.getStatus().getValue());
      } else {
        Long oldLimitation = securityPaymentGetResponse.getSecurityPaymentThreshold();
        SessionUtil.setAttribute(OLD_LIMITATION_ATTRIBUTE, oldLimitation);
      }
    } else {
      Long oldLimitation = Long.valueOf(oldLimitationString.replaceAll("[^0-9]", ""));
      SessionUtil.setAttribute(OLD_LIMITATION_ATTRIBUTE, oldLimitation);
    }

  }

  private GetConsumerAccountResponse getAccountInfo() throws Exception {
    GetConsumerAccountResponse response = walletEndpoint.accountInfoGet();

    if (response == null) {
      throw new Exception("No response");
    }
    if (response.getStatus().getCode() != 0) {
      throw new Exception(response.getStatus().getValue());
    }

    return response;
  }

//  @SuppressWarnings("unchecked")
//  @GetMapping(value = "/payment-security/sms/change-limitation")// form thay doi han muc
//  public String smsToChangeLimitationPage(HttpServletRequest request, HttpServletResponse response,
//      ModelMap map) throws Exception {
//    map.put("currentLimitation", oldLimitation == null ? "--" : oldLimitation);
//    map.put("newLimitation", limitation);
//    if (!confirmStatus) {
//      map.put("codeErr", codeErr);
//    }
//
//    return "/payment_security/otp_sms_change_limitation";
//  }


  @PostMapping(value = "/payment-security/sms/change-limitation")
  public String smsChangeLimitation(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    String codeOTP = request.getParameter("codeOTP");
    String referenceId = request.getParameter("referenceId");
    if(StringUtils.isBlank(referenceId))
      referenceId = (String) SessionUtil.getAttribute(REFERENCE_ID_ATTRIBUTE);
    try {
      Object limitationAttr = SessionUtil.getAttribute(LIMITATION_ATTRIBUTE);
      Long limitation = limitationAttr != null ? Long.parseLong(limitationAttr.toString()) : null;
      if (limitation != null) {
        SessionUtil.setAttribute(LIMITATION_ATTRIBUTE, limitation);
        SessionUtil.setAttribute(OLD_LIMITATION_ATTRIBUTE, limitation);
      } else {
        SessionUtil.removeAttribute(LIMITATION_ATTRIBUTE);
      }
      if (request.getParameter("limitation") != null) {
        limitation = Long
          .parseLong(request.getParameter("limitation").replaceAll("[^0-9]+", ""));

        SecurityPaymentRegisterRequest securityPaymentRegisterRequest =
            new SecurityPaymentRegisterRequest();
        securityPaymentRegisterRequest.setSecurityPaymentThreshold(limitation);
        securityPaymentRegisterRequest.setOtp(codeOTP);
        securityPaymentRegisterRequest.setReferenceId(referenceId);

        SecurityPaymentRegisterResponse securityPaymentRegisterResponse =
            walletEndpoint.securityPaymentRegister(securityPaymentRegisterRequest);
        if (securityPaymentRegisterResponse == null) {
          throw new Exception("No response");
        }
        if (securityPaymentRegisterResponse.getStatus().getCode() != 0) {
          throw new Exception(securityPaymentRegisterResponse.getStatus().getValue());
        } else {
          map.put("currentLimitation", SessionUtil.getAttribute(OLD_LIMITATION_ATTRIBUTE));
          map.put("newLimitation", limitation);
        }
      }
      else {
        map.put("currentLimitation", SessionUtil.getAttribute(OLD_LIMITATION_ATTRIBUTE));
        map.put("referenceId", referenceId);
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("currentLimitation", SessionUtil.getAttribute(OLD_LIMITATION_ATTRIBUTE));
      map.put("newLimitation", null);
      map.put("codeErr", codeErr);
      map.put("referenceId", referenceId);

      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      SessionUtil.removeAttribute(LIMITATION_ATTRIBUTE);
    }
    SessionUtil.removeAttribute(LIMITATION_ATTRIBUTE);
    return "/payment_security/otp_sms_change_limitation";
  }

  @GetMapping(value = "/paymentSec/login")
  public String otpLogin() throws Exception {
    return "/payment_security/otp_login";
  }


  @GetMapping(value = "/paymentSec/sms/register")
  public String smsRegister() throws Exception {
    return "/payment_security/otp_sms_register";
  }

  @GetMapping(value = "/paymentSec/email/register")
  public String emailRegister() throws Exception {
    return "/payment_security/otp_email_register";
  }

  @PostMapping(value = "/payment-security/change-payment-pin")
  public String changePaymentPin(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) {
    map.put("codeErr", request.getParameter("codeErr"));
    map.put("payment_pin_is_setup", SessionUtil.getAttribute(IS_SETUP_PAYMENT_PIN_ATTRIBUTE));

    return "/payment_security/payment_pin_change";
  }

  @PostMapping(value = "/payment-security/enter-payment-pin")
  public String enterPaymentPin(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) {
    try {
      String otpCode = request.getParameter("secure_OTP");

      VerifySetupPaymentPinRequest verifySetupPaymentPinRequest = new VerifySetupPaymentPinRequest();
      verifySetupPaymentPinRequest.setOtp(otpCode);
      verifySetupPaymentPinRequest.setType(OtpVerificationChannel.SMS);

      Object reference = SessionUtil.getAttribute(SECURE_REFERENCE_ID);
      Object key = SessionUtil.getAttribute(SECURE_KEY);
      if (reference != null && key != null) {
        verifySetupPaymentPinRequest.setReference(reference.toString());
        verifySetupPaymentPinRequest.setKey(key.toString());
      }

      VerifySetupPaymentPinResponse responseType =
          walletEndpoint.verifySetupPaymentPin(verifySetupPaymentPinRequest);
      if (responseType == null) {
        throw new Exception("No response");
      }
      if (responseType.getStatus().getCode() != 0) {
        throw new Exception(responseType.getStatus().getValue());
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);

      String codeErr = e.getMessage();
      map.put("codeErr", codeErr);
      map.put("payment_pin_is_setup", SessionUtil.getAttribute(IS_SETUP_PAYMENT_PIN_ATTRIBUTE));

      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      request.setAttribute(
          View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

      return "redirect:/system/payment-security/change-payment-pin";
    }
    map.put("payment_pin_is_setup", SessionUtil.getAttribute(IS_SETUP_PAYMENT_PIN_ATTRIBUTE));

    return "/payment_security/payment_pin_enter";
  }

  @PostMapping(value = "/payment-security/change-payment-pin/confirm")
  public String changePaymentPinConfirm(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) {
    request.setAttribute(
        View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

    try {
      Object key = SessionUtil.getAttribute(SECURE_KEY);
      String newPin = request.getParameter("new_PIN");
      if (key != null && StringUtils.isNotEmpty(newPin)) {
        SetupPaymentPinRequest setupPaymentPinRequest = new SetupPaymentPinRequest();
        setupPaymentPinRequest.setKey(key.toString());
        setupPaymentPinRequest.setPin(newPin);
        SetupPaymentPinResponse responseType = walletEndpoint
            .changePaymentPinSetup(setupPaymentPinRequest);
        if (responseType == null) {
          throw new Exception("No response");
        }
        if (responseType.getStatus().getCode() != 0) {
          throw new Exception(responseType.getStatus().getValue());
        }
      } else {
        throw new Exception("Can not setup Payment PIN");
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);

      String codeErr = e.getMessage();
      map.put("codeErr", codeErr);

      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);

      return "redirect:/system/payment-security/change-payment-pin";
    }

    return "redirect:/system/payment-security";
  }

  public static boolean checkPaymentPIN(IWalletEndpoint walletEndpoint) throws Exception {
    CheckPaymentPinResponse checkPaymentPinResponse = walletEndpoint.checkPaymentPin();
//    CheckPaymentPinResponse paymentPinCheckResponse = toResponse(checkPaymentPinResponse, CheckPaymentPinResponse.class);

    return checkPaymentPinResponse.getIsSetup();
  }
}
