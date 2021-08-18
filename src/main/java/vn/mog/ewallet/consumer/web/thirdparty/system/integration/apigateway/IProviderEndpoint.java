package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;

import vn.mog.ewallet.consumer.web.exception.FrontEndException;
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


public interface IProviderEndpoint {

  FindProviderResponse findProviders(FindProviderRequest request) throws FrontEndException;

  ChangeProviderStatusResponse changeProviderStatus(ChangeProviderStatusRequest request) throws FrontEndException;

  UpdateProviderResponse updateProvider(UpdateProviderRequest request) throws FrontEndException;

  GetProviderResponse getProvider(GetProviderRequest request) throws FrontEndException;

  ChangeProviderServiceStatusResponse changeProviderServiceStatus(ChangeProviderServiceStatusRequest request)
      throws FrontEndException;
}
