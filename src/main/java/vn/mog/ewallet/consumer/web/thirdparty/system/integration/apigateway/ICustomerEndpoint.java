package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;

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
import vn.mog.framework.contract.base.BaseResponseType;


public interface ICustomerEndpoint {
  GetCustomerPersonalInfoResponse getCustomerBySelf(GetCustomerPersonalInfoRequest request) throws Exception;

  SelfChangeCustomerPasswordResponse changeCustomerPassByStaff(SelfChangeCustomerPasswordRequest request) throws Exception;

  FindFullCustomerResponse findCustomers(FindFullCustomerRequest request) throws Exception;

  FindFullCustomerResponse getAllCustomers() throws Exception;

  GetStoreS3UrlResponse accountImageProfileGet(GetStoreS3UrlRequest request)
      throws Exception;

  /*--- Verify KYC Request ---*/
  VerifyKycRequestChangeResponse kycCreateRequest(VerifyKycRequestChangeRequest request) throws Exception;

  VerifyKycRequestChangeResponse kycUpdateRequestImage(VerifyKycRequestChangeRequest request) throws Exception;

  UpdateCustomerAdditionalInforResponse kycUpdateAdditionalInfo(
      UpdateCustomerAdditionalInforRequest request) throws Exception;

  SubmitKycRequestChangeResponse kycSubmitRequest(SubmitKycRequestChangeRequest request) throws Exception;

  UpdateAddressResponse updateStoreAddress (UpdateAddressRequest request)throws Exception;
}
