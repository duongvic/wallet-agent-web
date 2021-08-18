package vn.mog.ewallet.consumer.web.controller.account;


import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.customer.CustomerController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.Customer;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.CustomerVerifyKycRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Identity;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.SubmitKycRequestChangeRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.SubmitKycRequestChangeResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.VerifyKycRequestChangeRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.VerifyKycRequestChangeResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetAllOccupationRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetAllOccupationResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetAllPositionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetAllPositionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Location;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Occupation;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Position;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.AddressType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.IdentityType;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;


@Controller
@RequestMapping(value = "/account")
public class AccountVeirficationController extends AbstractController {

  @Autowired
  CustomerController customerController;

  private static final String ACCOUNT_CONTROLLER = "/account";

  public static final String REDIRECT_ACCOUNT_INFO = ACCOUNT_CONTROLLER + "/info";
  public static final String REDIRECT_ACCOUNT_VERIFY = ACCOUNT_CONTROLLER + "/verify";
  public static final String REDIRECT_ACCOUNT_VERIFY_KYC = ACCOUNT_CONTROLLER + "/verify-kyc";
  public static final String REDIRECT_ACCOUNT_VERIFY_KYC_SUCCESS =
      ACCOUNT_CONTROLLER + "/verify-kyc/success";
  public static final String REDIRECT_ACCOUNT_STORE_ADDRESS_NFO =
      ACCOUNT_CONTROLLER + "/store/info";
  private static final String REDIRECT = "redirect:";

  private static final String PAGE_ACCOUNT_INFO = "/account_veirfication/account_info";
  private static final String PAGE_ACCOUNT_VERIFY_SUCCESS = "/account_veirfication/account_verify_kyc_success";
  private static final String PAGE_ACCOUNT_VERIFY_ERROR = "/account_veirfication/account_verify_kyc_error";
  private static final String PAGE_ACCOUNT_VERIFY = "/account_veirfication/account_verify";


  private static final Logger LOGGER = LogManager.getLogger(AccountVeirficationController.class);
  private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
  private static final String DATE_TIME_FORMAT = "dd/MM/yyyy";
  private static final SimpleDateFormat SDF_DAY = new SimpleDateFormat(DATE_TIME_FORMAT);
  //Session Attribute
  public static final String DISPLAY_NAME_ATTRIBUTE = "DISPLAY_NAME";
  private static final String GENDER_ATTRIBUTE = "GENDER";
  private static final String DATE_OF_BIRTH_ATTRIBUTE = "DATE_OF_BIRTH";
  private static final String CMT_ATTRIBUTE = "CMT";
  private static final String EMAIL_ATTRIBUTE = "EMAIL";
  private static final String POSITION_ATTRIBUTE = "POSITION";
  private static final String OCCUPATION_ATTRIBUTE = "OCCUPATION";
  private static final String CURRENT_PROVINCE_ATTRIBUTE = "CURRENT_PROVINCE";
  private static final String CURRENT_DISTRICT_ATTRIBUTE = "CURRENT_DISTRICT";
  private static final String CURRENT_COMMUNE_ATTRIBUTE = "CURRENT_COMMUNE";

  // HỘ khẩu thường chú residence
  private static final String RESIDENCE_PROVINCE_ATTRIBUTE = "RESIDENCE_PROVINCE";
  private static final String RESIDENCE_DISTRICT_ATTRIBUTE = "RESIDENCE_DISTRICT";
  private static final String RESIDENCE_COMMUNE_ATTRIBUTE = "RESIDENCE_COMMUNE";
  private static final String RESIDENCE_STREET1_ATTRIBUTE = "RESIDENCE_STREET1";
  public static final String LIVING_ADDRESS_ATTRIBUTE = "LIVING_ADDRESS";
  private static final String LAST_LOGIN_ATTRIBUTE = "LAST_LOGIN";

  private static final String NICK_NAME_ATTRIBUTE = "NICK_NAME";
  private static final String STORE_INFORMATION_ATTRIBUTE = "STORE_INFORMATION";
  private static final String NATIONAL_ATTRIBUTE = "NATIONAL";
  private static final String ISSUE_DATE_ATTRIBUTE = "ISSUE_DATE_ATTRIBUTE";
  private static final String ISSUE_PLACE_ATTRIBUTE = "ISSUE_PLACE_ATTRIBUTE";

  //Trạng thái KYC
  private static final String KYC_STATUS_ATTRIBUTE = "KYC_STATUS_ATTRIBUTE";

  public static final String GET_VALUE_ATTRIBUTE = "GET_VALUE_ATTRIBUTE";
  //=============

  //Param
  private static final String LAST_LOGIN_RQP = "lastLogin";
  private static final String KYC_STATUS_RQP = "kycStatus";
  private static final String DISPLAY_NAME_RQP = "displayName";
  private static final String CMT_RQP = "cmt";
  private static final String GENDER_RQP = "gender";
  private static final String DATEOFBIRTH_RQP = "dateOfBirth";
  private static final String EMAIL_RQP = "email";
  private static final String POSITION_RQP = "position";
  private static final String OCCUPATION_RQP = "occupation";
  private static final String CURRENT_PROVINCE_RQP = "current_province";
  private static final String CURRENT_DISTRICT_RQP = "current_district";
  private static final String CURRENT_COMMUNE_RQP = "current_commune";
  private static final String RESIDENCE_PROVINCE_RQP = "residence_province";
  private static final String RESIDENCE_DISTRICT_RQP = "residence_district";
  private static final String RESIDENCE_COMMUNE_RQP = "residence_commune";
  private static final String LIVING_ADDRESS_RQP = "livingAddress";
  private static final String LIST_RESPROVINCE_RQP = "listResProvince";
  private static final String LIST_PROVINCE_RQP = "listProvince";
  private static final String LIST_OCCUPATION_RQP = "listOccupation";
  private static final String LIST_POSITION_RQP = "listPosition";
  private static final String NICK_NAME_RQP = "nickName";


  private static final String NATIONAL_RQP = "national";
  private static final String ISSUE_DATE_RQP = "issueDate";
  private static final String ISSUE_PLACE_RQP = "issuePlace";
  private static final String RESIDENCE_STREET1_RQP = "residence_street1";
  private static final String LIST_RESDISTRICT_RQP = "listResDistrict";
  private static final String LIST_RESCOMMUNE_RQP = "listResCommune";

  //Value
  private static final String IMAGE_JPEG_VALUE = "image/jpeg";


  @GetMapping(value = "/info")
  public String info(ModelMap map, HttpServletRequest request) throws Exception {

    String lastLogin = request.getParameter(LAST_LOGIN_RQP);
    String displayName = request.getParameter(DISPLAY_NAME_RQP);
    String kycStatus = request.getParameter(KYC_STATUS_RQP);
    String gender = request.getParameter(GENDER_RQP);
    String dateOfBirth = request.getParameter(DATEOFBIRTH_RQP);
    String nickName = request.getParameter(NICK_NAME_RQP);

    String national = request.getParameter(NATIONAL_RQP);
    String email = request.getParameter(EMAIL_RQP);
    String position = request.getParameter(POSITION_RQP);
    String occupation = request.getParameter(OCCUPATION_RQP);
    String current_province = request.getParameter(CURRENT_PROVINCE_RQP);
    String current_district = request.getParameter(CURRENT_DISTRICT_RQP);
    String current_commune = request.getParameter(CURRENT_COMMUNE_RQP);
    String livingAddress = request.getParameter(LIVING_ADDRESS_RQP);

    customerController.getCustomerBankDirect(map);
     /*Lấy giá trị value payment security*/
    customerController.getValuePaymentSecurity(map);

    Collection<Location> lstProvince = getProvince();
    map.put(LIST_RESPROVINCE_RQP, lstProvince);
    map.put(LIST_PROVINCE_RQP, lstProvince);

    map.put(LIST_OCCUPATION_RQP, getOccupation());
    map.put(LIST_POSITION_RQP, getPosition());

    GetConsumerAccountResponse baseResponseType;

    if (SessionUtil.getAttribute(GET_VALUE_ATTRIBUTE) == null
        || !(boolean) SessionUtil.getAttribute(GET_VALUE_ATTRIBUTE)) {

      try {
        baseResponseType = walletEndpoint.accountInfoGet();
        if (baseResponseType.getStatus().getCode() != 0) {
          throw new Exception(baseResponseType.getStatus().getValue());
        }

        GetConsumerAccountResponse responseObj = baseResponseType;

        displayName = responseObj.getCustomer().getDisplayName();
        if (responseObj.getCustomer().getGender() != null) {
          gender = responseObj.getCustomer().getGender().toString();
        }

        if (responseObj.getCustomer().getDateOfBirth() != null) {
          dateOfBirth = SDF_DAY.format(responseObj.getCustomer().getDateOfBirth());
        }
        nickName = baseResponseType.getCustomer().getNickName();

        national = baseResponseType.getCustomer().getCountry();
        email = baseResponseType.getCustomer().getEmail();
        position = baseResponseType.getCustomer().getJobPosition();
        occupation = baseResponseType.getCustomer().getJobOccupation();
        current_province = baseResponseType.getCustomer().getProvince();
        current_district = baseResponseType.getCustomer().getDistrict();
        current_commune = baseResponseType.getCustomer().getCommune();
        livingAddress = baseResponseType.getCustomer().getLivingAddress();
        Date responseLastLogin = baseResponseType.getExtraInfo().getLastLogin();
        lastLogin = responseLastLogin != null ?
            sdf.format(baseResponseType.getExtraInfo().getLastLogin()) : StringUtils.EMPTY;

        //kyc status
        Integer responseKycStatus = baseResponseType.getCustomer().getKycStatus();
        kycStatus = responseKycStatus != null ? String
            .valueOf(baseResponseType.getCustomer().getKycStatus()) : null;

        // Store to Session
        SessionUtil.setAttribute(DISPLAY_NAME_ATTRIBUTE, displayName);
        SessionUtil.setAttribute(GENDER_ATTRIBUTE, gender);
        SessionUtil.setAttribute(DATE_OF_BIRTH_ATTRIBUTE, dateOfBirth);
        SessionUtil.setAttribute(EMAIL_ATTRIBUTE, email);
        SessionUtil.setAttribute(POSITION_ATTRIBUTE, position);
        SessionUtil.setAttribute(OCCUPATION_ATTRIBUTE, occupation);
        SessionUtil.setAttribute(CURRENT_PROVINCE_ATTRIBUTE, current_province);
        SessionUtil.setAttribute(CURRENT_DISTRICT_ATTRIBUTE, current_district);
        SessionUtil.setAttribute(CURRENT_COMMUNE_ATTRIBUTE, current_commune);
        SessionUtil.setAttribute(LIVING_ADDRESS_ATTRIBUTE, livingAddress);

        SessionUtil.setAttribute(NICK_NAME_ATTRIBUTE, nickName);

        SessionUtil.setAttribute(NATIONAL_ATTRIBUTE, national);
        SessionUtil.setAttribute(KYC_STATUS_ATTRIBUTE, kycStatus);
        SessionUtil.setAttribute(LAST_LOGIN_ATTRIBUTE, lastLogin);

        //get personal info
        putCustomerInfo(baseResponseType, map, request);

      } catch (Exception ex) {
        LOGGER.error(ex.getMessage());
      }
    } else {
      SessionUtil.setAttribute(GET_VALUE_ATTRIBUTE, false);

      // get Session
      displayName = String.valueOf(SessionUtil.getAttribute(DISPLAY_NAME_ATTRIBUTE));
      gender = String.valueOf(SessionUtil.getAttribute(GENDER_ATTRIBUTE));
      dateOfBirth = String.valueOf(SessionUtil.getAttribute(DATE_OF_BIRTH_ATTRIBUTE));
      map.put(CMT_RQP, SessionUtil.getAttribute(CMT_ATTRIBUTE));
      map.put(ISSUE_DATE_RQP, SessionUtil.getAttribute(ISSUE_DATE_ATTRIBUTE));
      map.put(ISSUE_PLACE_RQP, SessionUtil.getAttribute(ISSUE_PLACE_ATTRIBUTE));
      map.put(RESIDENCE_STREET1_RQP, SessionUtil.getAttribute(RESIDENCE_STREET1_ATTRIBUTE));
      map.put(RESIDENCE_PROVINCE_RQP, SessionUtil.getAttribute(RESIDENCE_PROVINCE_ATTRIBUTE));
      map.put(RESIDENCE_DISTRICT_RQP, SessionUtil.getAttribute(RESIDENCE_DISTRICT_ATTRIBUTE));
      map.put(RESIDENCE_COMMUNE_RQP, SessionUtil.getAttribute(RESIDENCE_COMMUNE_ATTRIBUTE));

      email = String.valueOf(SessionUtil.getAttribute(EMAIL_ATTRIBUTE));
      nickName = String.valueOf(SessionUtil.getAttribute(NICK_NAME_ATTRIBUTE));

      livingAddress = String.valueOf(SessionUtil.getAttribute(LIVING_ADDRESS_ATTRIBUTE));
      national = String.valueOf(SessionUtil.getAttribute(NATIONAL_ATTRIBUTE));
      current_province = String.valueOf(SessionUtil.getAttribute(CURRENT_PROVINCE_ATTRIBUTE));
      current_district = String.valueOf(SessionUtil.getAttribute(CURRENT_DISTRICT_ATTRIBUTE));
      current_commune = String.valueOf(SessionUtil.getAttribute(CURRENT_COMMUNE_ATTRIBUTE));
      position = String.valueOf(SessionUtil.getAttribute(POSITION_ATTRIBUTE));
      occupation = String.valueOf(SessionUtil.getAttribute(OCCUPATION_ATTRIBUTE));

      kycStatus = String.valueOf(SessionUtil.getAttribute(KYC_STATUS_ATTRIBUTE));
    }

    map.put(LIST_RESDISTRICT_RQP,
        getDistrict(String.valueOf(SessionUtil.getAttribute(RESIDENCE_PROVINCE_ATTRIBUTE))));
    map.put(LIST_RESCOMMUNE_RQP,
        getCommune(String.valueOf(SessionUtil.getAttribute(RESIDENCE_DISTRICT_ATTRIBUTE))));
    map.put(RESIDENCE_DISTRICT_RQP,
        String.valueOf(SessionUtil.getAttribute(RESIDENCE_DISTRICT_ATTRIBUTE)));
    map.put(RESIDENCE_COMMUNE_RQP,
        String.valueOf(SessionUtil.getAttribute(RESIDENCE_COMMUNE_ATTRIBUTE)));

    map.put("listDistrict", getDistrict(current_province));
    map.put("listCommune", getCommune(current_district));
    map.put(DISPLAY_NAME_RQP, displayName);
    map.put(GENDER_RQP, gender);
    map.put(DATEOFBIRTH_RQP, dateOfBirth);
    map.put(EMAIL_RQP, email);
    map.put(POSITION_RQP, position);
    map.put(OCCUPATION_RQP, occupation);
    map.put(CURRENT_PROVINCE_RQP, current_province);
    map.put(CURRENT_DISTRICT_RQP, current_district);
    map.put(CURRENT_COMMUNE_RQP, current_commune);
    map.put(LIVING_ADDRESS_RQP, livingAddress);
    map.put(LAST_LOGIN_RQP, lastLogin);
    map.put(NICK_NAME_RQP, nickName);

    map.put(NATIONAL_RQP, national);
    map.put(KYC_STATUS_RQP, kycStatus);

    return PAGE_ACCOUNT_INFO;
  }

  @GetMapping(value = "/verify")
  public String verify(ModelMap map) throws Exception {
    map.put(DISPLAY_NAME_RQP, SessionUtil.getAttribute(DISPLAY_NAME_ATTRIBUTE));
    map.put(LAST_LOGIN_RQP, SessionUtil.getAttribute(LAST_LOGIN_ATTRIBUTE));
    return PAGE_ACCOUNT_VERIFY;
  }

  // lấy thông tin thành phố
  private Collection<Location> getProvince() throws Exception {
    return customerController.getLocation(null, Location.CITY);
  }

  // lấy thông tin Quận/Huyện
  private Collection<Location> getDistrict(String parentId) throws Exception {
    return customerController.getLocation(parentId, Location.DISTRICT);
  }

  // lấy thông tin Phường/Xã
  private Collection<Location> getCommune(String parentId) throws Exception {
    return customerController.getLocation(parentId, Location.COMMUE);
  }

  //lấy danh sách nghề nghiệp
  private Collection<Occupation> getOccupation() throws Exception {
    GetAllOccupationResponse response = new GetAllOccupationResponse();
    try {
      response = systemEndpoint.getAllOccupation(new GetAllOccupationRequest());
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
    return response.getOccupations();
  }

  //lấy danh sách vị trí
  private Collection<Position> getPosition() throws Exception {
    GetAllPositionResponse response = new GetAllPositionResponse();
    try {
      response = systemEndpoint.getAllPosition(new GetAllPositionRequest());
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
    return response.getPositions();
  }

  /*KYC */
  @GetMapping(value = "/verify-kyc")
  public String verifyKYC(HttpServletRequest request, ModelMap map)
      throws Exception {
    try {
      String fullName = request.getParameter(DISPLAY_NAME_RQP);
      String gender = request.getParameter(GENDER_RQP);
      String dateOfBirth = request.getParameter(DATEOFBIRTH_RQP);
      String cmt = request.getParameter(CMT_RQP);
      String issueDate = request.getParameter(ISSUE_DATE_RQP);
      String issuePlace = request.getParameter(ISSUE_PLACE_RQP);
      String residence_street1 = request.getParameter(RESIDENCE_STREET1_RQP);
      String residence_province = request.getParameter(RESIDENCE_PROVINCE_RQP);
      String residence_district = request.getParameter(RESIDENCE_DISTRICT_RQP);
      String residence_commune = request.getParameter(RESIDENCE_COMMUNE_RQP);
      SessionUtil.setAttribute(DISPLAY_NAME_ATTRIBUTE, fullName);
      SessionUtil.setAttribute(GENDER_ATTRIBUTE, gender);
      SessionUtil.setAttribute(DATE_OF_BIRTH_ATTRIBUTE, dateOfBirth);
      SessionUtil.setAttribute(CMT_ATTRIBUTE, cmt);
      SessionUtil.setAttribute(ISSUE_DATE_ATTRIBUTE, issueDate);
      SessionUtil.setAttribute(ISSUE_PLACE_ATTRIBUTE, issuePlace);
      SessionUtil.setAttribute(RESIDENCE_STREET1_ATTRIBUTE, residence_street1);
      SessionUtil.setAttribute(RESIDENCE_PROVINCE_ATTRIBUTE, residence_province);
      SessionUtil.setAttribute(RESIDENCE_DISTRICT_ATTRIBUTE, residence_district);
      SessionUtil.setAttribute(RESIDENCE_COMMUNE_ATTRIBUTE, residence_commune);

      map.put(ISSUE_PLACE_RQP, SessionUtil.getAttribute(ISSUE_PLACE_ATTRIBUTE));
      map.put(DISPLAY_NAME_RQP, SessionUtil.getAttribute(DISPLAY_NAME_ATTRIBUTE));
      map.put(LAST_LOGIN_RQP, SessionUtil.getAttribute(LAST_LOGIN_ATTRIBUTE));

      map.put(RESIDENCE_DISTRICT_RQP, SessionUtil.getAttribute(RESIDENCE_DISTRICT_ATTRIBUTE));
      map.put(RESIDENCE_COMMUNE_RQP, SessionUtil.getAttribute(RESIDENCE_COMMUNE_ATTRIBUTE));

      SessionUtil.setAttribute(GET_VALUE_ATTRIBUTE, true);
      return PAGE_ACCOUNT_VERIFY;
    } catch (Exception ex) {

    }
    return PAGE_ACCOUNT_VERIFY;
  }


  /*KYC */
  @PostMapping(value = "/verify-kyc")
  public String postVerifyKYC(HttpServletRequest request, ModelMap map)
      throws Exception {
    Object dateOfBirthAttr = SessionUtil.getAttribute(DATE_OF_BIRTH_ATTRIBUTE);
    String dateOfBirthKYC = dateOfBirthAttr != null ? (dateOfBirthAttr.toString()) : null;

    Identity identity = new Identity();
    Object identityAttr = SessionUtil.getAttribute(CMT_ATTRIBUTE);
    String identityKYC = identityAttr != null ? (identityAttr.toString()) : null;

    Object fullNameAttr = SessionUtil.getAttribute(DISPLAY_NAME_ATTRIBUTE);
    String fullNameKYC = fullNameAttr != null ? (fullNameAttr.toString()) : null;

    Object genderAttr = SessionUtil.getAttribute(GENDER_ATTRIBUTE);
    Integer genderKYC = genderAttr != null ? Integer.parseInt((String) genderAttr) : null;

    Object issueDateAttr = SessionUtil.getAttribute(ISSUE_DATE_ATTRIBUTE);
    String issueDateKYC = issueDateAttr != null ? (issueDateAttr.toString()) : null;

    Object issuePlaceAttr = SessionUtil.getAttribute(ISSUE_PLACE_ATTRIBUTE);
    String issuePlaceKYC = issueDateAttr != null ? (issuePlaceAttr.toString()) : null;
    try {
      //Create KYC
      VerifyKycRequestChangeResponse verifyKycRequestChangeResponse = new VerifyKycRequestChangeResponse();
      VerifyKycRequestChangeRequest verifyKycRequestChangeRequest = new VerifyKycRequestChangeRequest();
      CustomerVerifyKycRequest objCstomerVerifyKyc = new CustomerVerifyKycRequest();

      identity.setIdentity(identityKYC);
      identity.setFullname(fullNameKYC);
      identity.setGenderId(genderKYC);
      identity.setDateIssued(new SimpleDateFormat(DATE_TIME_FORMAT).parse(issueDateKYC));
      identity.setIssuePlace(issuePlaceKYC);

      objCstomerVerifyKyc
          .setDateOfBirth(new SimpleDateFormat(DATE_TIME_FORMAT).parse(dateOfBirthKYC));
      objCstomerVerifyKyc.setIdentity(identity);
      objCstomerVerifyKyc.setPermanentResident(
          String.valueOf(SessionUtil.getAttribute(RESIDENCE_STREET1_ATTRIBUTE)));
      objCstomerVerifyKyc
          .setProvince(String.valueOf(SessionUtil.getAttribute(RESIDENCE_PROVINCE_ATTRIBUTE)));
      objCstomerVerifyKyc
          .setDistrict(String.valueOf(SessionUtil.getAttribute(RESIDENCE_DISTRICT_ATTRIBUTE)));

      MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest) request;

      //get before Image
      pushRequestImgBeforeKYC(multiPartRequest, objCstomerVerifyKyc);

      //get after Image
      pushRequestImgAfterKYC(multiPartRequest, objCstomerVerifyKyc);

      //get selfie Image
      pushRequestImgSelfieKYC(multiPartRequest, objCstomerVerifyKyc);

      verifyKycRequestChangeRequest.setCustomerVerifyRequest(objCstomerVerifyKyc);
      verifyKycRequestChangeResponse = customerEndpoint
          .kycCreateRequest(verifyKycRequestChangeRequest);
      if (verifyKycRequestChangeResponse != null
          && verifyKycRequestChangeResponse.getStatus().getCode() == 0) {
        //call submit request
        SubmitKycRequestChangeResponse requestChangeResponse = customerEndpoint
            .kycSubmitRequest(new SubmitKycRequestChangeRequest());
        if (requestChangeResponse == null || requestChangeResponse.getStatus().getCode() != 0) {
          return PAGE_ACCOUNT_VERIFY;
        }
      } else {
        map.put("error", verifyKycRequestChangeResponse.getStatus().getValue());
        return PAGE_ACCOUNT_VERIFY_ERROR;
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      return PAGE_ACCOUNT_VERIFY;
    }
    map.put(DISPLAY_NAME_RQP, SessionUtil.getAttribute(DISPLAY_NAME_ATTRIBUTE));
    map.put(LAST_LOGIN_RQP, SessionUtil.getAttribute(LAST_LOGIN_ATTRIBUTE));
    map.put(KYC_STATUS_RQP, 2);
    SessionUtil.setAttribute(KYC_STATUS_ATTRIBUTE, 2);
    map.put(RESIDENCE_DISTRICT_RQP, SessionUtil.getAttribute(RESIDENCE_DISTRICT_ATTRIBUTE));
    map.put(RESIDENCE_COMMUNE_RQP, SessionUtil.getAttribute(RESIDENCE_COMMUNE_ATTRIBUTE));
    return REDIRECT + REDIRECT_ACCOUNT_VERIFY_KYC_SUCCESS;
  }

  private void pushRequestImgSelfieKYC(MultipartHttpServletRequest multiPartRequest,
      CustomerVerifyKycRequest objCstomerVerifyKyc) throws Exception {
    try {
      List<MultipartFile> multipartsSelfie = multiPartRequest.getFiles("fileSelfieUpload");
      for (MultipartFile multipart : multipartsSelfie) {
        if (multipart != null && multipart.getBytes() != null && multipart.getBytes().length > 0) {
          String fileName = multipart.getOriginalFilename();
          byte[] bytes = multipart.getBytes();
          objCstomerVerifyKyc.setSelfieName(fileName);
          objCstomerVerifyKyc.setSelfieContentType(IMAGE_JPEG_VALUE);
          objCstomerVerifyKyc.setSelfieContent(Base64Utils.encodeToString(bytes));
        }
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
  }

  private void pushRequestImgBeforeKYC(MultipartHttpServletRequest multiPartRequest,
      CustomerVerifyKycRequest objCstomerVerifyKyc) throws Exception {
    try {
      List<MultipartFile> multiparts = multiPartRequest.getFiles("fileBeforeUpload");
      for (MultipartFile multipart : multiparts) {
        if (multipart != null && multipart.getBytes() != null && multipart.getBytes().length > 0) {
          String fileName = multipart.getOriginalFilename();
          byte[] bytes = multipart.getBytes();
          objCstomerVerifyKyc.setFrontName(fileName);
          objCstomerVerifyKyc.setFrontContentType(IMAGE_JPEG_VALUE);
          objCstomerVerifyKyc.setFrontContent((Base64Utils.encodeToString(bytes)));
        }
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
  }

  private void pushRequestImgAfterKYC(MultipartHttpServletRequest multiPartRequest,
      CustomerVerifyKycRequest objCstomerVerifyKyc) throws Exception {
    try {
      List<MultipartFile> multipartsAfter = multiPartRequest.getFiles("fileAfterUpload");
      for (MultipartFile multipart : multipartsAfter) {
        if (multipart != null && multipart.getBytes() != null && multipart.getBytes().length > 0) {
          String fileName = multipart.getOriginalFilename();
          byte[] bytes = multipart.getBytes();
          objCstomerVerifyKyc.setBackName(fileName);
          objCstomerVerifyKyc.setBackContentType(IMAGE_JPEG_VALUE);
          objCstomerVerifyKyc.setBackContent((Base64Utils.encodeToString(bytes)));
        }
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
  }

  @GetMapping(value = "/verify-kyc/success")
  private String verifyKycSuccess(HttpServletRequest request, ModelMap map)
      throws Exception {
    map.put(DISPLAY_NAME_RQP, SessionUtil.getAttribute(DISPLAY_NAME_ATTRIBUTE));
    map.put(LAST_LOGIN_RQP, SessionUtil.getAttribute(LAST_LOGIN_ATTRIBUTE));
    map.put(KYC_STATUS_RQP, SessionUtil.getAttribute(KYC_STATUS_ATTRIBUTE));
    map.put(RESIDENCE_DISTRICT_RQP, SessionUtil.getAttribute(RESIDENCE_DISTRICT_ATTRIBUTE));
    map.put(RESIDENCE_COMMUNE_RQP, SessionUtil.getAttribute(RESIDENCE_COMMUNE_ATTRIBUTE));
    return PAGE_ACCOUNT_VERIFY_SUCCESS;
  }

  private void putCustomerInfo(GetConsumerAccountResponse baseResponseType, ModelMap map,
      HttpServletRequest request)
      throws Exception {

    putAddressCustomerInfo(baseResponseType, map);

    putIdentityCustomerInfo(baseResponseType, map, request);

  }

  private void putIdentityCustomerInfo(GetConsumerAccountResponse baseResponseType, ModelMap map,
      HttpServletRequest request) {
    try {
      String cmt = request.getParameter(CMT_RQP);
      String issueDate = request.getParameter(ISSUE_DATE_RQP);
      String issuePlace = request.getParameter(ISSUE_PLACE_RQP);

      if (StringUtils.isBlank(cmt)) {
        List<Identity> identities = baseResponseType.getIdentities();
        if (identities != null) {
          for (Identity identity : identities) {
            if (IdentityType.IdentityCard.code == identity.getIdentityType()) {
              cmt = identity.getIdentity();
              issueDate = SDF_DAY.format(identity.getDateIssued());
              issuePlace = identity.getIssuePlace();
              break;
            }
            if (IdentityType.CitizenshipCard.code == identity.getIdentityType()) {
              cmt = identity.getIdentity();
              issueDate = SDF_DAY.format(identity.getDateIssued());
              issuePlace = identity.getIssuePlace();
            }
          }
        }
      }
      map.put(CMT_RQP, cmt);
      map.put(ISSUE_DATE_RQP, issueDate);
      map.put(ISSUE_PLACE_RQP, issuePlace);

      SessionUtil.setAttribute(CMT_ATTRIBUTE, cmt);
      SessionUtil.setAttribute(ISSUE_DATE_ATTRIBUTE, issueDate);
      SessionUtil.setAttribute(ISSUE_PLACE_ATTRIBUTE, issuePlace);
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
  }

  private void putAddressCustomerInfo(GetConsumerAccountResponse baseResponseType, ModelMap map)
      throws Exception {
    try {
      List<Address> addresses = baseResponseType.getCustomer().getAddresses();
      if (addresses != null) {
        for (Address address : addresses) {
          if (AddressType.RESIDENCE_ADDRESS.getCode() == address.getAddressType()) {

            String residence_province = address.getProvince();
            String residence_district = address.getDistrict();
            String residence_commune = address.getCommune();
            String residence_street1 = address.getStreet1();
            // Store to Session
            SessionUtil.setAttribute(RESIDENCE_PROVINCE_ATTRIBUTE, residence_province);
            SessionUtil.setAttribute(RESIDENCE_DISTRICT_ATTRIBUTE, residence_district);
            SessionUtil.setAttribute(RESIDENCE_COMMUNE_ATTRIBUTE, residence_commune);
            SessionUtil.setAttribute(RESIDENCE_STREET1_ATTRIBUTE, residence_street1);

            Object resProvinceAttr = SessionUtil.getAttribute(RESIDENCE_PROVINCE_ATTRIBUTE);
            String resProvince = resProvinceAttr != null ? resProvinceAttr.toString() : null;
            Object resDistrictAttr = SessionUtil.getAttribute(RESIDENCE_DISTRICT_ATTRIBUTE);
            String resDistrict = resDistrictAttr != null ? resDistrictAttr.toString() : null;

            map.put(RESIDENCE_PROVINCE_RQP, resProvince);
            map.put(RESIDENCE_DISTRICT_RQP, resDistrict);
            map.put(RESIDENCE_COMMUNE_RQP, SessionUtil.getAttribute(RESIDENCE_COMMUNE_ATTRIBUTE));
            map.put(RESIDENCE_STREET1_RQP, address.getStreet1());

            map.put(LIST_RESDISTRICT_RQP, getDistrict(resProvince));
            map.put(LIST_RESCOMMUNE_RQP, getCommune(resDistrict));
            map.put(LIST_RESPROVINCE_RQP, getProvince());
            break;
          }
        }
      }

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
  }


  @GetMapping(value = "/parent/info")
  private String parentInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {
      Customer objCus = new Customer();
      GetConsumerAccountResponse baseResponseType = walletEndpoint.accountInfoGet();
      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      } else if (baseResponseType.getStatus().getCode() == 0) {
        objCus = baseResponseType.getCustomer();
      }

      map.put("objCus", objCus);
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
    return "/account_veirfication/account_parent_info";
  }

  @GetMapping(value = "/store/info")
  private String storeInfo(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
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
              map.put("outletStoreType", item.textOutLetStoreType());
              map.put("street1Store", item.getStreet1());
              map.put("aliasStore", item.getAlias());
              map.put("businessPhoneStore", item.getBusinessPhone());
            }
          }
        }
      }

      map.put("objCus", objCus);
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
    return "/account_veirfication/account_store_info";
  }

}
