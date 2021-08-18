package vn.mog.ewallet.consumer.web.controller.customer;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SECURE_KEY;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SECURE_REFERENCE_ID;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.CustomerBankDirect;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Identity;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindLocationRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindLocationResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Location;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ChangePasswordSSORequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ChangePasswordSSOResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.GetSetupOtpRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.GetSetupOtpResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.VerifyChangePasswordOtpRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.VerifyChangePasswordOtpResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.OtpVerificationChannel;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.bean.SecurityAccessActionType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.AddressType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.IdentityType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindCustomerBankDirectRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindCustomerBankDirectResponse;
import vn.mog.ewallet.consumer.web.util.tools.CommonUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends AbstractController {


  private static final String CUSTOMER_CONTROLLER = "/customer";
  private static final String PASSWORD_REQUIRED = CUSTOMER_CONTROLLER + "/password-required";
private static final String REDIRECT = "redirect:";

  private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);
  private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

  //Session Attribute
  private static final String FIRST_NAME_ATTRIBUTE = "FIRST_NAME";
  private static final String LAST_NAME_ATTRIBUTE = "LAST_NAME";
  private static final String FULL_NAME_ATTRIBUTE = "FULL_NAME";
  private static final String GENDER_ATTRIBUTE = "GENDER";
  private static final String DATE_OF_BIRTH_ATTRIBUTE = "DATE_OF_BIRTH";
  private static final String CMT_ATTRIBUTE = "CMT";
  private static final String EMAIL_ATTRIBUTE = "EMAIL";
  private static final String WALLET_ATTRIBUTE = "WALLET";
  private static final String POSITION_ATTRIBUTE = "POSITION";
  private static final String OCCUPATION_ATTRIBUTE = "OCCUPATION";
  private static final String MSISDN_ATTRIBUTE = "MSISDN";
  private static final String CURRENT_PROVINCE_ATTRIBUTE = "CURRENT_PROVINCE";
  private static final String CURRENT_DISTRICT_ATTRIBUTE = "CURRENT_DISTRICT";
  private static final String CURRENT_COMMUNE_ATTRIBUTE = "CURRENT_COMMUNE";

  // HỘ khẩu thường chú residence
  private static final String RESIDENCE_PROVINCE_ATTRIBUTE = "RESIDENCE_PROVINCE";
  private static final String RESIDENCE_DISTRICT_ATTRIBUTE = "RESIDENCE_DISTRICT";
  private static final String RESIDENCE_COMMUNE_ATTRIBUTE = "RESIDENCE_COMMUNE";
//  private static final String RESIDENCE_STREET1_ATTRIBUTE = "RESIDENCE_STREET1";
  private static final String LIVING_ADDRESS_ATTRIBUTE = "LIVING_ADDRESS";
  private static final String LAST_CHANGED_PASSWORD_ATTRIBUTE = "LAST_CHANGED_PASSWORD";
  private static final String LOAD_STATUS_ATTRIBUTE = "LOAD_STATUS";
  private static final String CODE_ERROR_ATTRIBUTE = "CODE_ERROR";
  private static final String PASSWORD_ATTRIBUTE = "PASSWORD";

  //Param
  private static final String FIRST_NAME_RQP = "firstName";
  private static final String LAST_NAME_RQP = "lastName";
  private static final String FULL_NAME_RQP = "fullName";
  private static final String CMT_RQP = "cmt";
  private static final String GENDER_RQP = "gender";
  private static final String DATE_OF_BIRTH_RQP = "dateOfBirth";
  private static final String EMAIL_RQP = "email";
  private static final String POSITION_RQP = "position";
  private static final String OCCUPATION_RQP ="occupation";
  private static final String CURRENT_PROVINCE_RQP = "current_province";
  private static final String CURRENT_DISTRICT_RQP = "current_district";
  private static final String CURRENT_COMMUNE_RQP = "current_commune";
  private static final String RESIDENCE_PROVINCE_RQP = "residence_province";
  private static final String RESIDENCE_DISTRICT_RQP = "residence_district";
  private static final String RESIDENCE_COMMUNE_RQP ="residence_commune";
  private static final String LAST_CHANGED_PASSWORD_RQP = "lastChangedPassword";
  private static final String LIVING_ADDRESS_RQP = "livingAddress";
  private static final String WALLET_RQP = "wallet";
  private static final String MSISDN_RQP = "msisdn";
  private static final String CODE_ERR_RQP = "codeErr";

  @GetMapping(value = "/account-info")
  public String thongTinTaiKhoanGet(ModelMap map) throws Exception {
   /*Lấy thông tin bankDirect*/
    getCustomerBankDirect(map);
    /*Lấy giá trị value payment security*/
    getValuePaymentSecurity(map);

    Object loadStatusAttribute = SessionUtil.getAttribute(LOAD_STATUS_ATTRIBUTE);
    boolean loadStatus = loadStatusAttribute != null && (boolean) loadStatusAttribute;

    map.put(FIRST_NAME_RQP, SessionUtil.getAttribute(FIRST_NAME_ATTRIBUTE));
    map.put(LAST_NAME_RQP, SessionUtil.getAttribute(LAST_NAME_ATTRIBUTE));
    map.put(FULL_NAME_RQP, SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE));
    map.put(CMT_RQP, SessionUtil.getAttribute(CMT_ATTRIBUTE));
    map.put(GENDER_RQP, SessionUtil.getAttribute(GENDER_ATTRIBUTE));
    map.put(DATE_OF_BIRTH_RQP, SessionUtil.getAttribute(DATE_OF_BIRTH_ATTRIBUTE));
    map.put(EMAIL_RQP, SessionUtil.getAttribute(EMAIL_ATTRIBUTE));
    map.put(POSITION_RQP, SessionUtil.getAttribute(POSITION_ATTRIBUTE));
    map.put(OCCUPATION_RQP, SessionUtil.getAttribute(OCCUPATION_ATTRIBUTE));
    map.put(CURRENT_PROVINCE_RQP, SessionUtil.getAttribute(CURRENT_PROVINCE_ATTRIBUTE));
    map.put(CURRENT_DISTRICT_RQP, SessionUtil.getAttribute(CURRENT_DISTRICT_ATTRIBUTE));
    map.put(CURRENT_COMMUNE_RQP, SessionUtil.getAttribute(CURRENT_COMMUNE_ATTRIBUTE));

    map.put(RESIDENCE_PROVINCE_RQP, SessionUtil.getAttribute(RESIDENCE_PROVINCE_ATTRIBUTE));
    map.put(RESIDENCE_DISTRICT_RQP, SessionUtil.getAttribute(RESIDENCE_DISTRICT_ATTRIBUTE));
    map.put(RESIDENCE_COMMUNE_RQP, SessionUtil.getAttribute(RESIDENCE_COMMUNE_ATTRIBUTE));
    map.put(LAST_CHANGED_PASSWORD_RQP, SessionUtil.getAttribute(LAST_CHANGED_PASSWORD_ATTRIBUTE));
    map.put(LIVING_ADDRESS_RQP, SessionUtil.getAttribute(LIVING_ADDRESS_ATTRIBUTE));
    if (!loadStatus) {
      map.put(CODE_ERR_RQP, SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
    }

    return "/customer/thongtintk";
  }

  @PostMapping(value = "/account-info")
  public String thongTinTaiKhoan(ModelMap map) throws Exception {
    getCustomerBankDirect(map);
    getValuePaymentSecurity(map);

    String gender = null;
    String dateOfBirth = null;
    GetConsumerAccountResponse baseResponseType;
    try {
      baseResponseType = walletEndpoint.accountInfoGet();
      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }

      String fullName = baseResponseType.getCustomer().getDisplayName();

      if (baseResponseType.getCustomer().getGender() != null) {
        gender = baseResponseType.getCustomer().getGender().toString();
      }
      if (baseResponseType.getCustomer().getDateOfBirth() != null) {
        dateOfBirth = SDF.format(baseResponseType.getCustomer().getDateOfBirth());
      }
      String email = baseResponseType.getCustomer().getEmail();
      String wallet = baseResponseType.getCustomer().getMsisdn();
      String position = baseResponseType.getCustomer().getJobPosition();
      String occupation = baseResponseType.getCustomer().getJobOccupation();
      String firstName = baseResponseType.getCustomer().getFirstName();
      String lastName = baseResponseType.getCustomer().getLastName();
      String current_province = baseResponseType.getCustomer().getProvince();
      String current_district = baseResponseType.getCustomer().getDistrict();
      String current_commune = baseResponseType.getCustomer().getCommune();
      String livingAddress = baseResponseType.getCustomer().getLivingAddress();
      String lastChangedPassword = SDF
          .format(baseResponseType.getExtraInfo().getLastChangedPassword());

      // Store to Session
      SessionUtil.setAttribute(FULL_NAME_ATTRIBUTE, fullName);
      SessionUtil.setAttribute(GENDER_ATTRIBUTE, gender);
      SessionUtil.setAttribute(DATE_OF_BIRTH_ATTRIBUTE, dateOfBirth);
      SessionUtil.setAttribute(EMAIL_ATTRIBUTE, email);
      SessionUtil.setAttribute(WALLET_ATTRIBUTE, wallet);
      SessionUtil.setAttribute(POSITION_ATTRIBUTE, position);
      SessionUtil.setAttribute(OCCUPATION_ATTRIBUTE, occupation);
      SessionUtil.setAttribute(FIRST_NAME_ATTRIBUTE, firstName);
      SessionUtil.setAttribute(LAST_NAME_ATTRIBUTE, lastName);
      SessionUtil.setAttribute(CURRENT_PROVINCE_ATTRIBUTE, current_province);
      SessionUtil.setAttribute(CURRENT_DISTRICT_ATTRIBUTE, current_district);
      SessionUtil.setAttribute(CURRENT_COMMUNE_ATTRIBUTE, current_commune);
      SessionUtil.setAttribute(LIVING_ADDRESS_ATTRIBUTE, livingAddress);
      SessionUtil.setAttribute(LAST_NAME_ATTRIBUTE, lastChangedPassword);

//      putCustomerInfo(baseResponseType, map);

      map.put(FIRST_NAME_RQP, firstName);
      map.put(LAST_NAME_RQP, lastName);
      map.put(FULL_NAME_RQP, fullName);
      map.put(GENDER_RQP, gender);
      map.put(DATE_OF_BIRTH_RQP, dateOfBirth);
      map.put(EMAIL_RQP, email);
      map.put(WALLET_RQP, wallet);
      map.put(POSITION_RQP, position);
      map.put(OCCUPATION_RQP, occupation);
      map.put(CURRENT_PROVINCE_RQP, current_province);
      map.put(CURRENT_DISTRICT_RQP, current_district);
      map.put(CURRENT_COMMUNE_RQP, current_commune);

      map.put(LIVING_ADDRESS_RQP, livingAddress);
      map.put(LAST_CHANGED_PASSWORD_RQP, lastChangedPassword);

      SessionUtil.setAttribute(LOAD_STATUS_ATTRIBUTE, true);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      String codeErr = e.getMessage();
      map.put(CODE_ERR_RQP, codeErr);

      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      SessionUtil.setAttribute(LOAD_STATUS_ATTRIBUTE, false);
    }

    return "/customer/thongtintk";
  }

  @GetMapping(value = "/xacThucTaiKhoan")
  public String xacThucTaiKhoan() throws Exception {
    return "/customer/xacthuctk";
  }

  @GetMapping(value = "/info-update")
  public String suaTaiKhoan(ModelMap map) throws Exception {
    GetConsumerAccountResponse baseResponseType;
    String gender = null;
    String dateOfBirth = null;
    try {

      baseResponseType = walletEndpoint.accountInfoGet();
      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }

      GetConsumerAccountResponse responseObj = baseResponseType;

      String fullName = responseObj.getCustomer().getDisplayName();
      if (responseObj.getCustomer().getGender() != null) {
        gender = responseObj.getCustomer().getGender().toString();
      }
      if (responseObj.getCustomer().getDateOfBirth() != null) {
        dateOfBirth = SDF.format(responseObj.getCustomer().getDateOfBirth());
      }
      String email = baseResponseType.getCustomer().getEmail();
      String wallet = baseResponseType.getCustomer().getMsisdn();
      String position = baseResponseType.getCustomer().getJobPosition();
      String occupation = baseResponseType.getCustomer().getJobOccupation();
      String firstName = baseResponseType.getCustomer().getFirstName();
      String lastName = baseResponseType.getCustomer().getLastName();
      String current_province = baseResponseType.getCustomer().getProvince();
      String current_district = baseResponseType.getCustomer().getDistrict();
      String current_commune = baseResponseType.getCustomer().getCommune();
      String livingAddress = baseResponseType.getCustomer().getLivingAddress();
      String lastChangedPassword = SDF
          .format(baseResponseType.getExtraInfo().getLastChangedPassword());

      // Store to Session
      SessionUtil.setAttribute(FULL_NAME_ATTRIBUTE, fullName);
      SessionUtil.setAttribute(GENDER_ATTRIBUTE, gender);
      SessionUtil.setAttribute(DATE_OF_BIRTH_ATTRIBUTE, dateOfBirth);
      SessionUtil.setAttribute(EMAIL_ATTRIBUTE, email);
      SessionUtil.setAttribute(WALLET_ATTRIBUTE, wallet);
      SessionUtil.setAttribute(POSITION_ATTRIBUTE, position);
      SessionUtil.setAttribute(OCCUPATION_ATTRIBUTE, occupation);
      SessionUtil.setAttribute(FIRST_NAME_ATTRIBUTE, firstName);
      SessionUtil.setAttribute(LAST_NAME_ATTRIBUTE, lastName);
      SessionUtil.setAttribute(CURRENT_PROVINCE_ATTRIBUTE, current_province);
      SessionUtil.setAttribute(CURRENT_DISTRICT_ATTRIBUTE, current_district);
      SessionUtil.setAttribute(CURRENT_COMMUNE_ATTRIBUTE, current_commune);
      SessionUtil.setAttribute(LIVING_ADDRESS_ATTRIBUTE, livingAddress);
      SessionUtil.setAttribute(LAST_NAME_ATTRIBUTE, lastChangedPassword);

//      putCustomerInfo(baseResponseType, map);

      getCustomerBankDirect(map);
     /*Lấy giá trị value payment security*/
      getValuePaymentSecurity(map);

    /*Lấy danh sách thành phố*/
      map.put("listProvince", getProvince());
      map.put("listDistrict", getDistrict(current_province));
      map.put("listCommune", getCommune(current_district));

      map.put(FIRST_NAME_RQP, firstName);
      map.put(LAST_NAME_RQP, lastName);
      map.put(FULL_NAME_RQP, fullName);
      map.put(GENDER_RQP, gender);
      map.put(DATE_OF_BIRTH_RQP, dateOfBirth);
      map.put(EMAIL_RQP, email);
      map.put(WALLET_RQP, wallet);
      map.put(POSITION_RQP, position);
      map.put(OCCUPATION_RQP, occupation);
      map.put(CURRENT_PROVINCE_RQP, current_province);
      map.put(CURRENT_DISTRICT_RQP, current_district);
      map.put(CURRENT_COMMUNE_RQP, current_commune);
      map.put(LIVING_ADDRESS_RQP, livingAddress);
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
    return "/customer/thongtintk-update";
  }

  @GetMapping(value = "/thongTinTaiKhoanDoanhNghiep")
  public String thongTinTaiKhoanDoanhNghiep() throws Exception {
    return "/customer/thongtintkdoanhnghiep-sua";
  }

  @GetMapping(value = "/changeEmail")
  public String changeEmail() throws Exception {
    return "/customer/change_email";
  }

  @GetMapping(value = "/password-required")
  public String changePassword(ModelMap map) throws Exception {
    map.put(CODE_ERR_RQP, SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
    SessionUtil.removeAttribute(CODE_ERROR_ATTRIBUTE);

    return "/auth/required_pass";
  }

  @PostMapping(value = "/change-password/otp-verify")
  public String changePassword(HttpServletRequest request, ModelMap map) throws Exception {

    int error = 0;
    String codeErr = request.getParameter(CODE_ERR_RQP);
    SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);

    String password = request.getParameter("password");
    SessionUtil.setAttribute(PASSWORD_ATTRIBUTE, password);

    String msisdn = request.getParameter(MSISDN_RQP);
    String email = request.getParameter(EMAIL_RQP);
    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    if (userLogin != null) {
      msisdn = userLogin.getPhoneNumber();
      email = userLogin.getEmail();
    }
    SessionUtil.setAttribute(MSISDN_ATTRIBUTE, msisdn);
    SessionUtil.setAttribute(EMAIL_ATTRIBUTE, email);

    try {
      if (StringUtils.isBlank(codeErr)) {
        GetSetupOtpRequest getSetupPaymentPinOtpRequest = new GetSetupOtpRequest();
        getSetupPaymentPinOtpRequest.setActionType(SecurityAccessActionType.CHANGE_PASSWORD);
        getSetupPaymentPinOtpRequest.setPassword(password);

        GetSetupOtpResponse baseResponseType = walletEndpoint
            .getSecurityOTP(getSetupPaymentPinOtpRequest);
        if (baseResponseType.getStatus().getCode() != 0) {
          error = baseResponseType.getStatus().getCode();
          map.put(CODE_ERR_RQP, baseResponseType.getStatus().getCode());
          SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, baseResponseType.getStatus().getCode());
          return REDIRECT + PASSWORD_REQUIRED;
        }

        String key = baseResponseType.getKey();
        String referenceId = baseResponseType.getReferenceId();
        SessionUtil.setAttribute(SECURE_KEY, key);
        SessionUtil.setAttribute(SECURE_REFERENCE_ID, referenceId);
      } else {
        map.put(CODE_ERR_RQP, SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
        SessionUtil.removeAttribute(CODE_ERROR_ATTRIBUTE);
      }

      map.put(MSISDN_RQP, msisdn);
      map.put(EMAIL_RQP, email);
      return "/auth/change_pass_otp_verify";

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      codeErr = String.valueOf(error);
      map.put(CODE_ERR_RQP, codeErr);

      return REDIRECT + PASSWORD_REQUIRED;
    }
  }

  @PostMapping(value = "/change-password")
  public String passwordChangeConfirmOTP(HttpServletRequest request, ModelMap map)
      throws Exception {
    String msisdn = request.getParameter(MSISDN_RQP);
    SessionUtil.setAttribute(MSISDN_ATTRIBUTE, msisdn);

    Object referenceIdAttr = SessionUtil.getAttribute(SECURE_REFERENCE_ID);
    String referenceId = referenceIdAttr != null ? referenceIdAttr.toString() : null;
    Object keyAttr = SessionUtil.getAttribute(SECURE_KEY);
    String key = keyAttr != null ? keyAttr.toString() : null;

    String otp = request.getParameter("otp");
    OtpVerificationChannel type = OtpVerificationChannel.SMS;
    try {
      Object codeErrAttr = SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE);
      if (codeErrAttr == null || StringUtils.isBlank(codeErrAttr.toString())) {
        VerifyChangePasswordOtpRequest verifyChangePasswordOtpRequest = new VerifyChangePasswordOtpRequest();
        verifyChangePasswordOtpRequest.setReference(referenceId);
        verifyChangePasswordOtpRequest.setType(type);
        verifyChangePasswordOtpRequest.setOtp(otp);
        verifyChangePasswordOtpRequest.setKey(key);
        VerifyChangePasswordOtpResponse baseResponseType;
        baseResponseType = walletEndpoint.changePasswordVerifyOTP(verifyChangePasswordOtpRequest);
        if (baseResponseType == null || baseResponseType.getStatus() == null) {
          throw new Exception("No response!");
        }
        if (baseResponseType.getStatus().getCode() != 0) {
          throw new Exception(baseResponseType.getStatus().getValue());
        }
        key = baseResponseType.getKey();
        SessionUtil.setAttribute(SECURE_KEY, key);

      } else {
        map.put(CODE_ERR_RQP, SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
        SessionUtil.removeAttribute(CODE_ERROR_ATTRIBUTE);
      }
      map.put(MSISDN_RQP, msisdn);
      return "/auth/resetup_pass";

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      String codeErr = e.getMessage();
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      map.put(CODE_ERR_RQP, codeErr);
      request.setAttribute(
          View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

      return REDIRECT + "/customer/change-password/otp-verify";
    }

  }

  @PostMapping(value = "/change-password/confirm")
  public String changePassConfirm(HttpServletRequest request, ModelMap map) throws Exception {

    Object keyAttr = SessionUtil.getAttribute(SECURE_KEY);
    String key = keyAttr != null ? keyAttr.toString() : null;

    String msisdn = request.getParameter(MSISDN_RQP);
    SessionUtil.setAttribute(MSISDN_ATTRIBUTE, msisdn);

    String password = request.getParameter("password");
    String rePassword = request.getParameter("re_password");

    try {
      if (!password.equals(rePassword)) {
        throw new Exception("Password not match");
      }
      ChangePasswordSSORequest changePasswordSSORequest = new ChangePasswordSSORequest();
      changePasswordSSORequest.setKey(key);
      changePasswordSSORequest.setNewPassword(password);
      ChangePasswordSSOResponse responseType = walletEndpoint
          .changePasswordChange(changePasswordSSORequest);
      //--- check base response type;
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
      LOGGER.error(ex.getMessage(), ex);

      String codeErr = ex.getMessage();
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      map.put(CODE_ERR_RQP, codeErr);

      request.setAttribute(
          View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

      return "redirect:/change-password";
    }

    return "/auth/change_pass_success";
  }

  public void getCustomerBankDirect(ModelMap map) {
    List<CustomerBankDirect> lstCusBankDirect = new ArrayList<>();
    FindCustomerBankDirectResponse findCustomerBankDirectBaseRespType;
    try {
      // lấy thông tin bankdirect
      FindCustomerBankDirectRequest findCustomerBankDirectReq = new FindCustomerBankDirectRequest();
      findCustomerBankDirectBaseRespType = walletEndpoint
          .findCustomerBankDirect(findCustomerBankDirectReq);
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

  public void getValuePaymentSecurity(ModelMap map) {
    String oldLimitation = "--";
    try {
      SecurityPaymentGetRequest securityPaymentGetRequest = new SecurityPaymentGetRequest();

      SecurityPaymentGetResponse securityPaymentGetResponse = walletEndpoint
          .securityPaymentGet(securityPaymentGetRequest);
      if (securityPaymentGetResponse == null) {
        throw new Exception("No response");
      }
      if (securityPaymentGetResponse.getStatus().getCode() != 0) {
        throw new Exception(securityPaymentGetResponse.getStatus().getValue());
      } else {

        if (securityPaymentGetResponse.getSecurityPaymentThreshold() != null) {
          oldLimitation = CommonUtil.formatNumber("###,### đ",
              securityPaymentGetResponse.getSecurityPaymentThreshold());
        }
        map.put("currentLimitation", oldLimitation);
      }
    } catch (Exception e) {
      map.put("currentLimitation", oldLimitation);
      LOGGER.error(e.getMessage());
    }
  }

  private Collection<Location> getProvince() throws Exception {
    // lấy thông tin thành phố
    return getLocation(null, Location.CITY);
  }

  private Collection<Location> getDistrict(String parentId) throws Exception {
    // lấy thông tin Quận/Huyện
    return getLocation(parentId, Location.DISTRICT);
  }

  private Collection<Location> getCommune(String parentId) throws Exception {
    // lấy thông tin Phường/Xã
    return getLocation(parentId, Location.COMMUE);
  }

  public Collection<Location> getLocation(String parentCode, Integer locationTypeId)
      throws Exception {
    FindLocationResponse findLocationBaseRespType = new FindLocationResponse();
    try {
      FindLocationRequest findLocationRequest = new FindLocationRequest();
      findLocationRequest.setParentCode(parentCode);
      findLocationRequest.setLocationTypeId(locationTypeId);
      findLocationRequest.setCodes(null);
      findLocationBaseRespType = systemEndpoint.findLocations(findLocationRequest);
      return findLocationBaseRespType.getLocations();
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
    return findLocationBaseRespType.getLocations();
  }

//  public void putCustomerInfo(GetConsumerAccountResponse baseResponseType, ModelMap map)
//      throws Exception {
//    List<Address> addresses = baseResponseType.getCustomer().getAddresses();
//    if (addresses != null) {
//      for (Address address : addresses) {
//        if (AddressType.RESIDENCE_ADDRESS.getCode() == address.getAddressType()) {
//
//          String residence_province = address.getProvince();
//          String residence_district = address.getDistrict();
//          String residence_commune = address.getCommune();
//          String residence_street1 = address.getStreet1();
//          // Store to Session
//          SessionUtil.setAttribute(RESIDENCE_PROVINCE_ATTRIBUTE, residence_province);
//          SessionUtil.setAttribute(RESIDENCE_DISTRICT_ATTRIBUTE, residence_district);
//          SessionUtil.setAttribute(RESIDENCE_COMMUNE_ATTRIBUTE, residence_commune);
//          SessionUtil.setAttribute(RESIDENCE_STREET1_ATTRIBUTE, residence_street1);
//
//          Object resProvinceAttr = SessionUtil.getAttribute(RESIDENCE_PROVINCE_ATTRIBUTE);
//          String resProvince = resProvinceAttr != null ? resProvinceAttr.toString() : null;
//          Object resDistrictAttr = SessionUtil.getAttribute(RESIDENCE_DISTRICT_ATTRIBUTE);
//          String resDistrict = resDistrictAttr != null ? resDistrictAttr.toString() : null;
//
//          map.put(RESIDENCE_PROVINCE_RQP, resProvince);
//          map.put(RESIDENCE_DISTRICT_RQP, resDistrict);
//          map.put(RESIDENCE_COMMUNE_RQP, SessionUtil.getAttribute(RESIDENCE_COMMUNE_ATTRIBUTE));
//          map.put("residence_street1", address.getStreet1());
//
//          map.put("listResDistrict", getDistrict(resProvince));
//          map.put("listResCommune", getCommune(resDistrict));
//          map.put("listResProvince", getProvince());
//          break;
//        }
//      }
//    }
//    String cmt = StringUtils.EMPTY;
//    List<Identity> identities = baseResponseType.getIdentities();
//    if (identities != null) {
//      for (Identity identity : identities) {
//        if (IdentityType.IdentityCard.code == identity.getIdentityType()) {
//          cmt = identity.getIdentity();
//          break;
//        }
//        if (IdentityType.CitizenshipCard.code == identity.getIdentityType()) {
//          cmt = identity.getIdentity();
//        }
//      }
//    }
//    SessionUtil.setAttribute(CMT_ATTRIBUTE, cmt);
//    map.put(CMT_RQP, cmt);
//  }

}
