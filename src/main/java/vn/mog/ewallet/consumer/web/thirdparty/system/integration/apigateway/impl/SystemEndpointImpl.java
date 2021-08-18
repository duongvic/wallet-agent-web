package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.impl;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.controller.fundin.FundInVisaController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetAllPositionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ISystemEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.GetBankItemByCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.GetBankItemByCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.UpdateCustomerPersonalInfoRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.UpdateCustomerPersonalInfoResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.CreateBankAccountRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.CreateBankAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.DeleteBankAccountRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.DeleteBankAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankProfileRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankProfileResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindLocationRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindLocationResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetAllOccupationRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetAllOccupationResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetAllPositionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.UpdateBankAccountRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.UpdateBankAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.UpdateImageProfileCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.UpdateImageProfileCustomerResponse;
import vn.mog.ewallet.consumer.web.utils.WebUtil;

@Service
public class SystemEndpointImpl implements ISystemEndpoint {


  private static final String URI_FIND_BANK = "/api/v1/wallet/findBanks";
  private static final String URI_FIND_BANK_PROFILE = "/api/v1/wallet/findBankProfiles";
  private static final Logger LOG = LogManager.getLogger(FundInVisaController.class);

  @Autowired
  private GatewayServiceAPIClient gatewayAPIClient;
  @Autowired
  private GatewayUrlConfiguration gatewayUrlConfig;

  @Override
  public FindBankProfileResponse getProfileBanks(FindBankProfileRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SYSTEM_BANK_PROFILE_FIND, request, WebUtil.defaultNullUriVariables(), FindBankProfileResponse.class);
  }

  @Override
  public  FindBankResponse findBanks(FindBankRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SYSTEM_BANKS_FIND, request, WebUtil.defaultNullUriVariables(), FindBankResponse.class);
  }

  @Override
  public  FindBankResponse getListBank(FindBankRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SYSTEM_BANKS_FIND, request, WebUtil.defaultNullUriVariables(), FindBankResponse.class);
  }

  @Override
  public CreateBankAccountResponse createBankAccount(CreateBankAccountRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANKACCOUNT_CREATE, mapHeader, request, WebUtil.defaultNullUriVariables(), CreateBankAccountResponse.class);
  }

  @Override
  public UpdateBankAccountResponse updateBankAccount(UpdateBankAccountRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANKACCOUNT_UPDATE, mapHeader, request, WebUtil.defaultNullUriVariables(), UpdateBankAccountResponse.class);
  }

  @Override
  public DeleteBankAccountResponse deleteBankAccount(DeleteBankAccountRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANKACCOUNT_DELETE, mapHeader, request, WebUtil.defaultNullUriVariables(), DeleteBankAccountResponse.class);
  }

  @Override
  public FindLocationResponse findLocations(FindLocationRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_LOCATIONS_FIND, request, WebUtil.defaultNullUriVariables(), FindLocationResponse.class);
  }

  @Override
  public  GetBankItemByCustomerResponse getBankItemByCustomer(GetBankItemByCustomerRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANKITEM, request, WebUtil.defaultNullUriVariables(), GetBankItemByCustomerResponse.class);
  }

  @Override
  public UpdateImageProfileCustomerResponse updateProfileImage(
      UpdateImageProfileCustomerRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_PROFILE_IMAGE_PROFILE_CHANGE, request, WebUtil.defaultNullUriVariables(), UpdateImageProfileCustomerResponse.class);
  }

  @Override
  public UpdateCustomerPersonalInfoResponse updateProfile(
      UpdateCustomerPersonalInfoRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_UPDATEINFO, request, WebUtil.defaultNullUriVariables(), UpdateCustomerPersonalInfoResponse.class);
  }

  @Override
  public GetAllOccupationResponse getAllOccupation(GetAllOccupationRequest request)
      throws Exception {
    return gatewayAPIClient.getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SYSTEM_OCCUPATIONS, request, WebUtil.defaultNullUriVariables(), GetAllOccupationResponse.class);
  }

  @Override
  public GetAllPositionResponse getAllPosition(GetAllPositionRequest request) throws Exception {
    return gatewayAPIClient.getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SYSTEM_POSITIONS, request, WebUtil.defaultNullUriVariables(), GetAllPositionResponse.class);
  }
}
