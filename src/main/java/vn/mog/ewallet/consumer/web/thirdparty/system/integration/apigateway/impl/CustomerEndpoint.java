package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ICustomerEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.GetCustomerPersonalInfoRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.GetCustomerPersonalInfoResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.GetStoreS3UrlRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.GetStoreS3UrlResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.UpdateAddressRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.UpdateAddressResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.SubmitKycRequestChangeRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.SubmitKycRequestChangeResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.UpdateCustomerAdditionalInforRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.UpdateCustomerAdditionalInforResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.VerifyKycRequestChangeRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.kyc.VerifyKycRequestChangeResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.FindFullCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SelfChangeCustomerPasswordRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SelfChangeCustomerPasswordResponse;
import vn.mog.ewallet.consumer.web.utils.WebUtil;
import vn.mog.framework.contract.base.BaseResponseType;


@Service
public class CustomerEndpoint implements ICustomerEndpoint {
  
  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig; 
  
  @Autowired
  GatewayServiceAPIClient gatewayServiceAPIClient;
  private String id_hashcode = "";


  @Override
  public GetCustomerPersonalInfoResponse getCustomerBySelf(GetCustomerPersonalInfoRequest  request) throws Exception {
    return gatewayServiceAPIClient.getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_GETINFO, request, WebUtil.defaultUriVariables(), GetCustomerPersonalInfoResponse.class);
  }

  @Override
  public SelfChangeCustomerPasswordResponse changeCustomerPassByStaff(SelfChangeCustomerPasswordRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("id", id_hashcode);
    return gatewayServiceAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_AUTH_SECURITY_PASSWORD_TEMPORARY, mapHeader, request, uriVariables, SelfChangeCustomerPasswordResponse.class);
  }

  @Override
  public FindFullCustomerResponse findCustomers(FindFullCustomerRequest request) throws FrontEndException {
    return gatewayServiceAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_FINDCUSTOMERS, request, FindFullCustomerResponse.class);
  }


  @Override
  public FindFullCustomerResponse getAllCustomers() throws FrontEndException {
    return gatewayServiceAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_FINDCUSTOMERS, new FindFullCustomerRequest(), FindFullCustomerResponse.class);
  }

  @Override
  public GetStoreS3UrlResponse accountImageProfileGet(
      GetStoreS3UrlRequest request) throws Exception {
    return gatewayServiceAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_IMAGE_PROFILE_GET, request, WebUtil.defaultUriVariables(), GetStoreS3UrlResponse.class);
  }

  @Override
  public VerifyKycRequestChangeResponse kycCreateRequest(VerifyKycRequestChangeRequest request)
      throws Exception {
    return gatewayServiceAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_PROFILE_KYC_CREATE_KYC_REQUEST, request, WebUtil.defaultUriVariables(), VerifyKycRequestChangeResponse.class);
  }

  @Override
  public VerifyKycRequestChangeResponse kycUpdateRequestImage(VerifyKycRequestChangeRequest request)
      throws Exception {
    return gatewayServiceAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_PROFILE_KYC_UPDATE_KYC_REQUEST_IMAGE, request, WebUtil.defaultUriVariables(), VerifyKycRequestChangeResponse.class);
  }

  @Override
  public UpdateCustomerAdditionalInforResponse kycUpdateAdditionalInfo(
      UpdateCustomerAdditionalInforRequest request) throws Exception {
    return gatewayServiceAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_PROFILE_KYC_UPDATE_KYC_ADDITIONAL_INFO, request, WebUtil.defaultUriVariables(), UpdateCustomerAdditionalInforResponse.class);
  }

  @Override
  public SubmitKycRequestChangeResponse kycSubmitRequest(SubmitKycRequestChangeRequest request) throws Exception  {
    return gatewayServiceAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_PROFILE_KYC_SUBMIT_KYC_REQUEST, request, WebUtil.defaultUriVariables(), SubmitKycRequestChangeResponse.class);
  }

  @Override
  public UpdateAddressResponse updateStoreAddress(UpdateAddressRequest request) throws Exception {
    return gatewayServiceAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_PROFILE_KYC_UPDATE_STORE_ADDRESS, request, WebUtil.defaultUriVariables(), UpdateAddressResponse.class);
  }
}
