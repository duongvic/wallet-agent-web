package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.impl;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.contract.CredentialType;
import vn.mog.ewallet.consumer.web.contract.IdentificationType;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClientTest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IEpinPurchaseOrderEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ISecurityService;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ISystemEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IWalletEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetSpecialCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.GetStoreS3UrlRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.GetStoreS3UrlResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.SelfCustomerActiveAccountRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.SelfCustomerActiveAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindLocationRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindLocationResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Location;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.JwtUserRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.JwtUserResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.RefreshTokenRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.RefreshTokenResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.RegisterWalletSecurityRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.RegisterWalletSecurityResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SelfChangeCustomerPasswordRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SelfChangeCustomerPasswordResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignInRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignInResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignOutRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignOutResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.StaffGetSecureCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.StaffGetSecureCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtUser;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.ewallet.consumer.web.utils.WebUtil;


@Service
public class SecurityService implements ISecurityService, InitializingBean {

  private static final Logger LOGGER = LogManager.getLogger(SecurityService.class);
  @Autowired
  GatewayServiceAPIClient gatewayAPIClient;

  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig;

  @Autowired
  GatewayServiceAPIClientTest gatewayServiceAPIClientTest;

  @Autowired
  CustomerEndpoint customerEndpoint;

  @Autowired
  IEpinPurchaseOrderEndpoint epinEndpoint;

  @Autowired
  protected IWalletEndpoint walletEndpoint;

  @Autowired
  protected ISystemEndpoint systemEndpoint;

  private String id_hascode = "";
  private String cif_hascode = "";

  @Override
  public void afterPropertiesSet() throws Exception {
    if (this.gatewayAPIClient == null) {
      throw new IllegalStateException("authServerAPIClient is required");
    }
  }

  @Override
  public String authToken(String username, String password) throws FrontEndException {
    return gatewayAPIClient.authToken(username, password);
  }

  @Override
  public void setAccessToken(String accessToken) {
    gatewayAPIClient.setAccessToken(accessToken);
  }

  @Override
  public SignOutResponse signOut(SignOutRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    Map<String, String> uriVariables = null;
    return gatewayAPIClient
        .getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_AUTH_SECURITY_SIGN_OUT, mapHeader, request,
            uriVariables,
            SignOutResponse.class);
  }

  @Override
  public RefreshTokenResponse refreshToken(RefreshTokenRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    Map<String, String> uriVariables = null;
    return gatewayAPIClient
        .getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_AUTH_SECURITY_REFRESH_TOKEN, mapHeader,
            request, uriVariables,
            RefreshTokenResponse.class);
  }

  @Override
  public JwtUserResponse getJwtUser(JwtUserRequest request) throws FrontEndException {
    return gatewayAPIClient
        .callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_GETJWTUSER, request,
            JwtUserResponse.class);
  }

  @Override
  public SelfChangeCustomerPasswordResponse changePasswordBySelfCustomer(
      SelfChangeCustomerPasswordRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    Map<String, String> uriVariables = null;
    return gatewayAPIClient
        .putForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_AUTH_SECURITY_CUSTOMERS_CHANGE_PASSWORD,
            mapHeader, request,
            uriVariables, SelfChangeCustomerPasswordResponse.class);
  }

  @Override
  public SelfCustomerActiveAccountResponse activeAccountBySelfCustomer(
      SelfCustomerActiveAccountRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("id", id_hascode);
    return gatewayAPIClient
        .putForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_UPDATE_BLACKLIST_REASON,
            mapHeader, request, uriVariables,
            SelfCustomerActiveAccountResponse.class);
  }

  @Override
  public RegisterWalletSecurityResponse registerWalletSecurity(
      RegisterWalletSecurityRequest request)
      throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("cif", cif_hascode);
    return gatewayAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURE_REGISTER, mapHeader, request,
            uriVariables, RegisterWalletSecurityResponse.class);
  }

  @Override
  public StaffGetSecureCustomerResponse getSecureCustomerByStaff(
      StaffGetSecureCustomerRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    String url = "/risk/api/v1/customers/{cif}/secure/info";
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("cif", cif_hascode);
    return gatewayAPIClient
        .getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURE_INFO, mapHeader, request,
            uriVariables, StaffGetSecureCustomerResponse.class);
  }

  @Override
  public JwtUser signIn(String username, String password) throws Exception {

    Map<String, String> mapHeader = new HashMap<>();
    mapHeader.put("Content-Type", "application/json");

    SignInRequest request = new SignInRequest();
    request.setIdentification(username);
    request.setCredential(password);
    request.setIdentificationTypeId(IdentificationType.ByMobileNo);
    request.setCredentialTypeId(CredentialType.ByPassword);

//    SignInResponse jwtResponse = gatewayServiceAPIClientTest.postForEntity(URI_LOGIN, mapHeader, request, uriVariables, SignInResponse.class);

    SignInResponse siResponse = gatewayAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_AUTH_SECURITY_SIGNIN, mapHeader, request,
            null, SignInResponse.class);
    if (siResponse != null && siResponse.getStatus() != null
        && siResponse.getStatus().getCode() == 0 && siResponse.getAccessToken() != null) {
      setAccessToken(siResponse.getAccessToken());
      JwtUser user = new JwtUser();
      user.setAccessToken(siResponse.getAccessToken());

      user.setCustomerId(siResponse.getId());
      user.setCustomerCif(siResponse.getCif());
      user.setCustomerTypeId(siResponse.getType());
      user.setActive(true);
      user.setEmail(siResponse.getEmail());
      user.setFullName(siResponse.getFullname());
//   user.setCustomerCif("00011");
//   if (siResponse.getCorrelateId() != null) {
//    String stringCusId = siResponse.getCorrelateId();
//    String [] parts = stringCusId.split("-");
//    String partCusId = parts[1];
//    user.setCustomerId(Long.valueOf(partCusId));
//   } else {
//    user.setCustomerId(10L);
//   }
//   setAccessToken("abc21444");
//   user.setCustomerId(10L);
//   user.setCustomerTypeId(1);

//   Set<String> privileges = new HashSet<>();
//   privileges.add("abc");
//   user.setPrivileges(privileges);

//   Set<String> roles = new HashSet<>();
//   roles.add("ROLE_OPERATION");
//   roles.add("ROLE_ADMIN_OPERATION");
//   roles.add("ROLE_STAFF");
//   user.setRoles(roles);

      user.setUsername(username);
      //get image
      GetStoreS3UrlRequest requestS3Url = new GetStoreS3UrlRequest();
      requestS3Url.setCif(null);
      requestS3Url.setCustomerId(null);
      GetStoreS3UrlResponse baseResS3Url = customerEndpoint.accountImageProfileGet(requestS3Url);
      if (baseResS3Url != null && baseResS3Url.getStatus() != null
          && baseResS3Url.getStatus().getCode() == 0) {
        if (baseResS3Url.getUsedS3() != null && baseResS3Url.getUsedS3()) {
          user.setS3Url(baseResS3Url.getS3Url());
        } else if (baseResS3Url.getUsedS3() != null && baseResS3Url.getAttachment() != null
            && baseResS3Url.getAttachment().getContentType() != null
            && baseResS3Url.getAttachment().getContent() != null) {
          user.setDataImage(
              "data:" + baseResS3Url.getAttachment().getContentType() + ";" + "base64,"
                  + baseResS3Url.getAttachment().getContent());
        }
      }

      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      if (userLogin == null) {
        userLogin = new UserLogin();
      }
      userLogin.setCustomerId(siResponse.getId());
      userLogin.setCustomerCif(siResponse.getCif());
      userLogin.setCustomerTypeId(siResponse.getType());

      if (StringUtils.isBlank(userLogin.getAccessToken())) {
        userLogin.setAccessToken(siResponse.getAccessToken());
      }

      if (StringUtils.isBlank(userLogin.getPhoneNumber())) {
        userLogin.setPhoneNumber(username);
      }

      if (StringUtils.isBlank(userLogin.getS3Url())) {
        userLogin.setS3Url(user.getS3Url());
      }
      if (StringUtils.isBlank(userLogin.getDataImage())) {
        userLogin.setDataImage(user.getDataImage());
      }

      if (StringUtils.isBlank(userLogin.getEmail())) {
        userLogin.setEmail(user.getEmail());
      }
      if (
          siResponse.getFullname() != null) {
        userLogin.setFullName(siResponse.getFullname());
      } else {
        user.setFullName("Guest");
      }
      try {
        GetSpecialCustomerResponse specialMerchantOffline = epinEndpoint
            .getSpecialMerchantOffline();
        GetSpecialCustomerResponse specialMerchant = epinEndpoint.getSpecialMerchant();
        GetSpecialCustomerResponse specialMerchantN02 = epinEndpoint.getSpecialMerchantN02();

        userLogin.setSpecialCustomerInfo(specialMerchant.getSpecialCustomer());
        userLogin.setSpecialCustomerInfoOffline(specialMerchantOffline.getSpecialCustomer());
        userLogin.setSpecialCustomerInfoN02(specialMerchantN02.getSpecialCustomer());
      } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
      }

      try{
        GetConsumerAccountResponse baseResponseType =  walletEndpoint.accountInfoGet();
        if (baseResponseType !=null && baseResponseType.getStatus().getCode() == 0){
          userLogin.setLivingAddress(baseResponseType.getCustomer().getLivingAddress());
          /*Lấy tên thành phố*/
          String province = baseResponseType.getCustomer().getProvince();
          String provinceName =getLocation(null, Arrays.asList((province)), Location.CITY);
          userLogin.setProvinceName(provinceName);
          /*Lấy tên quận huyện*/
          String district = baseResponseType.getCustomer().getDistrict();
          String districtName =getLocation(province, Arrays.asList((district)), Location.DISTRICT);
          userLogin.setDistrictName(districtName);
          /*Lấy tên phường xã*/
          String commue = baseResponseType.getCustomer().getCommune();
          String commueName =getLocation(district, Arrays.asList((commue)), Location.COMMUE);
          userLogin.setCommuneName(commueName);

        }
      }
      catch (Exception ex){
        LOGGER.error(ex.getMessage(),ex);
      }


      SessionUtil.setAttribute(SESSION_ACCOUNT_LOGIN, userLogin);

      return user;
    } else {
      throw new Exception(String.valueOf(siResponse.getStatus().getCode()));
    }
  }

  private String getLocation(String parentCode, List<String> codes , Integer locationTypeId) {
    String name = null;
    FindLocationRequest findLocationRequest = new FindLocationRequest();
    findLocationRequest.setLocationTypeId(locationTypeId);
    findLocationRequest.setParentCode(parentCode);
    findLocationRequest.setCodes(codes);
    try {
      FindLocationResponse findLocationBaseRespType = systemEndpoint.findLocations(findLocationRequest);
      Collection<Location> locations = findLocationBaseRespType.getLocations();
      if (locations != null) {
        for (Location location : locations) {
          name=location.getName();
        }
      }
      return name;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(),e);
    }
    return null;
  }
}
