package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;

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
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetAllPositionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.UpdateBankAccountRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.UpdateBankAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.UpdateImageProfileCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.UpdateImageProfileCustomerResponse;


public interface ISystemEndpoint {

  /*********** System Get Bank 1Pay ***********/
  FindBankProfileResponse getProfileBanks(FindBankProfileRequest request) throws Exception;

  /*********** System Get list Bank ***********/
  FindBankResponse findBanks(FindBankRequest request) throws Exception;

  FindBankResponse getListBank(FindBankRequest request) throws Exception;

  CreateBankAccountResponse createBankAccount( CreateBankAccountRequest request) throws Exception;

  UpdateBankAccountResponse updateBankAccount(UpdateBankAccountRequest request) throws Exception;

  DeleteBankAccountResponse deleteBankAccount(DeleteBankAccountRequest request) throws Exception;

  FindLocationResponse findLocations(FindLocationRequest request) throws Exception;

  GetBankItemByCustomerResponse getBankItemByCustomer(
      GetBankItemByCustomerRequest request) throws Exception;

  UpdateImageProfileCustomerResponse updateProfileImage(
      UpdateImageProfileCustomerRequest request) throws Exception;

  UpdateCustomerPersonalInfoResponse updateProfile(
      UpdateCustomerPersonalInfoRequest request) throws Exception;

  GetAllOccupationResponse getAllOccupation (GetAllOccupationRequest request ) throws  Exception;

  GetAllPositionResponse getAllPosition (GetAllPositionRequest request ) throws  Exception;

}
