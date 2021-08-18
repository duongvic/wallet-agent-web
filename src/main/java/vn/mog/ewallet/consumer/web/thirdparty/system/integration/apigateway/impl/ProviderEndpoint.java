package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IProviderEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.ChangeProviderServiceStatusRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.ChangeProviderServiceStatusResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.ChangeProviderStatusRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.ChangeProviderStatusResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.FindProviderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.FindProviderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.GetProviderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.GetProviderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.UpdateProviderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.provider.UpdateProviderResponse;


@Service
public class ProviderEndpoint implements IProviderEndpoint {


  @Autowired
  GatewayServiceAPIClient gatewayServiceAPIClient;
  
  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig;


  @Override
  public FindProviderResponse findProviders(FindProviderRequest request) throws FrontEndException {
    return gatewayServiceAPIClient
        .callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_PROVIDER_FINDPROVIDERS, request, FindProviderResponse.class);
  }

  @Override
  public ChangeProviderStatusResponse changeProviderStatus(ChangeProviderStatusRequest request)
      throws FrontEndException {
    return gatewayServiceAPIClient
        .callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_PROVIDER_CHANGE_STATUS, request, ChangeProviderStatusResponse.class);
  }

  @Override
  public UpdateProviderResponse updateProvider(UpdateProviderRequest request) throws FrontEndException {
    return gatewayServiceAPIClient
        .callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_PROVIDER_UPDATEPROVIDER, request, UpdateProviderResponse.class);
  }

  @Override
  public GetProviderResponse getProvider(GetProviderRequest request) throws FrontEndException {
    return gatewayServiceAPIClient
        .callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_PROVIDER_GETPROVIDER, request, GetProviderResponse.class);
  }

  @Override
  public ChangeProviderServiceStatusResponse changeProviderServiceStatus(ChangeProviderServiceStatusRequest request)
      throws FrontEndException {
    return gatewayServiceAPIClient
        .callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_CHANGE_SERVICE_STATUS, request, ChangeProviderServiceStatusResponse.class);
  }
}
