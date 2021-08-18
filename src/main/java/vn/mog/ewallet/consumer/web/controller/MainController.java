package vn.mog.ewallet.consumer.web.controller;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SECURE_KEY;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SECURE_REFERENCE_ID;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController;
import vn.mog.ewallet.consumer.web.exception.MessageNotify;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IWalletEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.CustomerType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ChangePasswordSSORequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ChangePasswordSSOResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckExistedAccountByPhoneRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckLoginPasswordRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckLoginPasswordResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgetPasswordOtpGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgetPasswordOtpGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgotPasswordOtpVerifyRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgotPasswordOtpVerifyResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgotPasswordSetPassRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgotPasswordSetPassResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ProfileCheckRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ProfileCheckResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.OtpVerificationChannel;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.SecurityAccessActionType;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

@Controller
public class MainController extends AbstractController {

  private static final Logger LOG = LogManager.getLogger(MainController.class);

  private static final String PAGE_LOGIN = "/auth/login";

  @Autowired protected IWalletEndpoint walletEndpoint;

  private String errCode;

  @GetMapping(value = "/")
  public String index() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null
        && auth.isAuthenticated()
        && auth instanceof UsernamePasswordAuthenticationToken) {
      return "redirect:" + DashboardController.DASHBOARD_LIST;
    } else {
      return PAGE_LOGIN;
    }
  }

  @GetMapping(value = "/login")
  public String login(HttpServletRequest request, ModelMap map) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null
        && auth.isAuthenticated()
        && auth instanceof UsernamePasswordAuthenticationToken) {

      return "redirect:" + DashboardController.DASHBOARD_LIST;
    }
    String errorCode = request.getParameter("error");
    map.put("codeErr", errorCode);

    return PAGE_LOGIN;
  }

  @GetMapping(value = "/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null
        && auth.isAuthenticated()
        && auth instanceof UsernamePasswordAuthenticationToken) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login"; // You can redirect wherever you want, but generally it's a good
    // practice to show login screen again.
  }

  @GetMapping(value = "/forgot-password")
  public String forgotpass(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    map.put("user_name", request.getParameter("user_name"));
    map.put("codeErr", errCode);
    errCode = "";

    return "/auth/forget_pass";
  }

  @PostMapping(value = "/forgot-password")
  public String forgotpassPost(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {

    return "/auth/forget_pass";
  }

  @PostMapping(value = "/change-default-password")
  public String changeDefaultPasswordByPost(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
    Boolean isDefaultPassword = (Boolean) SessionUtil.getAttribute("USING_DEFAULT_PASSWORD");
    if( Objects.isNull(isDefaultPassword) || isDefaultPassword == false){
      return "redirect:/change-default-password?action=failed";
    }
    String password = (String) SessionUtil.getAttribute("password");
    CheckLoginPasswordRequest checkLoginRequest = new CheckLoginPasswordRequest();
    checkLoginRequest.setPassword(password);
    checkLoginRequest.setActionType(SecurityAccessActionType.CHANGE_PASSWORD);
    String key = getKeyFromCurrentPassword(checkLoginRequest);
    String newPass = StringUtils.trimToEmpty(request.getParameter("new-password"));
    ChangePasswordSSORequest changePasswordSSORequest = new ChangePasswordSSORequest();
    if (!key.isEmpty()) changePasswordSSORequest.setKey(key);
    if (!newPass.isEmpty()) changePasswordSSORequest.setNewPassword(newPass);
    if (changeDefaultPassword(changePasswordSSORequest) == true) {
      return "redirect:/login?action=success";
    } else {
      return "redirect:/change-default-password?action=failed";
    }
  }

  public String getKeyFromCurrentPassword(CheckLoginPasswordRequest checkLoginRequest) {
    try {
      ForgotPasswordOtpVerifyResponse checkLoginResponse =
          walletEndpoint.getKeyByCurrentPassword(checkLoginRequest);
      if (checkLoginResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        LOG.error(
            "Call api get key from current password failed: "
                + checkLoginResponse.getStatus().getValue());
      }
      return checkLoginResponse.getKey();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "";
  }

  public boolean changeDefaultPassword(ChangePasswordSSORequest changePasswordSSORequest) {
    try {
      ChangePasswordSSOResponse changePasswordSSOResponse =
          walletEndpoint.changePasswordChange(changePasswordSSORequest);
      if (changePasswordSSOResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        return false;
      }
      return true;
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return false;
  }

  @GetMapping(value = "/change-default-password")
  public String changeDefaultPassword(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {

    return "/auth/change_default_pass";
  }

  @PostMapping(value = "/forgot-password/confirm-otp")
  public String lookupUsername(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
    String userName = request.getParameter("user_name");
    try {
      if (StringUtils.isBlank(errCode)) {

        ProfileCheckRequest profileCheckRequest = new ProfileCheckRequest();
        profileCheckRequest.setPhoneNumber(userName);

        ProfileCheckResponse profileCheckResponse =
            walletEndpoint.profileCheck(profileCheckRequest);

        if (profileCheckResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
          throw new Exception(profileCheckResponse.getStatus().getValue());
        }

        if (!CustomerType.AGENT.equals(profileCheckResponse.getCustomerTypeId())) {
          throw new Exception("Invalid account type");
        }

        ForgetPasswordOtpGetRequest getForgetPasswordOtpRequest = new ForgetPasswordOtpGetRequest();
        getForgetPasswordOtpRequest.setPhoneNumber(userName);
        ForgetPasswordOtpGetResponse responseType =
            walletEndpoint.forgotPassGetOTP(getForgetPasswordOtpRequest);
        // --- check base response type;
        if (responseType == null) {
          throw new Exception("No response!");
        }
        if (responseType.getStatus() == null) {
          throw new Exception("No status!");
        }
        if (responseType.getStatus().getCode() != 0) {
          errCode = responseType.getStatus().getValue();
          throw new Exception(responseType.getStatus().getValue());
        }
        SessionUtil.setAttribute(SECURE_REFERENCE_ID, responseType.getReference());
      } else {
        map.put("codeErr", errCode);
        errCode = "";
      }
      map.put("user_name", userName);
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);

      errCode = ex.getMessage();

      return "redirect:/forgot-password";
    }

    return "/auth/forget_pass_otp_verify";
  }

  @PostMapping(value = "/forgot-password/change-password")
  public String changePass(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    String userName = request.getParameter("user_name");
    String reference = SessionUtil.getAttribute(SECURE_REFERENCE_ID).toString();
    String otp = request.getParameter("otp");
    OtpVerificationChannel type = OtpVerificationChannel.SMS;

    try {
      if (StringUtils.isBlank(errCode)) {
        ForgotPasswordOtpVerifyRequest verifyForgotPasswordOtpRequest =
            new ForgotPasswordOtpVerifyRequest();
        verifyForgotPasswordOtpRequest.setPhoneNumber(userName);
        verifyForgotPasswordOtpRequest.setOtp(otp);
        verifyForgotPasswordOtpRequest.setReference(reference);
        verifyForgotPasswordOtpRequest.setType(type);
        ForgotPasswordOtpVerifyResponse verifyForgotPasswordOtpResponse =
            walletEndpoint.forgotPassVerifyOTP(verifyForgotPasswordOtpRequest);

        if (verifyForgotPasswordOtpResponse == null
            || verifyForgotPasswordOtpResponse.getStatus() == null) {
          throw new Exception("Cannot get response from Server!");
        }
        if (verifyForgotPasswordOtpResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
          throw new Exception(verifyForgotPasswordOtpResponse.getStatus().getValue());
        }

        SessionUtil.setAttribute(SECURE_KEY, verifyForgotPasswordOtpResponse.getKey());
      } else {
        map.put("codeErr", errCode);
        errCode = "";
      }
      map.put("user_name", userName);
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);

      errCode = ex.getMessage();
      map.put("codeErr", errCode);

      request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

      return "redirect:/forgot-password/confirm-otp";
    }

    return "/auth/change_pass";
  }

  @PostMapping(value = "/forgot-password/confirm")
  public String changePassConfirm(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
    try {
      String userName = request.getParameter("user_name");
      String key = SessionUtil.getAttribute(SECURE_KEY).toString();
      String password = request.getParameter("password");
      String rePassword = request.getParameter("re_password");

      if (!password.equals(rePassword)) {
        throw new Exception("Password not match");
      }
      ForgotPasswordSetPassRequest ForgotPasswordSetPassRequest =
          new ForgotPasswordSetPassRequest();
      ForgotPasswordSetPassRequest.setPhoneNumber(userName);
      ForgotPasswordSetPassRequest.setKey(key);
      ForgotPasswordSetPassRequest.setNewPassword(password);
      ForgotPasswordSetPassResponse responseType =
          walletEndpoint.forgotPassSetPass(ForgotPasswordSetPassRequest);
      // --- check base response type;
      if (responseType == null) {
        throw new Exception("No response!");
      }
      if (responseType.getStatus() == null) {
        throw new Exception("No status!");
      }
      if (responseType.getStatus().getCode() != 0) {
        if (responseType.getStatus().getValue() == null) {
          throw new Exception("Service not support");
        }
        throw new Exception(responseType.getStatus().getValue());
      }
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);

      errCode = ex.getMessage();
      map.put("codeErr", errCode);

      request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

      return "redirect:/forgot-password/change-password";
    }

    return "/auth/change_pass_success";
  }

  @GetMapping(value = "/access_denied")
  public String accessDenied() throws Exception {
    return "/about/trogiup";
  }

  @GetMapping(value = "/access_denied/kyc")
  public String accessDeniedKyc() throws Exception {
    return "/about/kyc_error";
  }

  @GetMapping(value = "/troGiup")
  public String troGiup() throws Exception {
    return "/about/trogiup";
  }

  @GetMapping(value = "/gioiThieu")
  public String gioiThieu() throws Exception {
    return "/about/gioithieu";
  }

  @GetMapping(value = "/lienHe")
  public String lienHe() throws Exception {
    return "/about/lienhe";
  }

  @GetMapping(value = "/chinhSach")
  public String chinhSach() throws Exception {
    return "/about/chinhsach";
  }

  @GetMapping(value = "/dieuKhoan")
  public String dieuKhoan() throws Exception {
    return "/about/dieukhoan";
  }

  @GetMapping(value = "/dangKyCaNhan")
  public String dangkyCN() throws Exception {
    return "/signup/dangky_CN";
  }

  @GetMapping(value = "/dangKyDoanhNghiep")
  public String dangkyDN() throws Exception {
    return "/signup/dangky_DN";
  }

  @GetMapping(value = "/dangKyDoanhNghiepStep2")
  public String dangkyDN_Step2() throws Exception {
    return "/signup/dangky_DN_step2";
  }

  @GetMapping(value = "/kickHoatTaiKhoan")
  public String accountActive() throws Exception {
    return "/signup/account_active";
  }

  @GetMapping(value = "/linkedBank")
  public String linkedBank() throws Exception {
    return "/signup/bank_link";
  }

  @GetMapping(value = "/verifyLinkedBank")
  public String verifyLinkedBank() throws Exception {
    return "/signup/bank_link_verify";
  }
}
