package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;

import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.SelfCustomerActiveAccountRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.SelfCustomerActiveAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.GetSetupOtpRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.GetSetupOtpResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.JwtUserRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.JwtUserResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.RefreshTokenRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.RefreshTokenResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.RegisterWalletSecurityRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.RegisterWalletSecurityResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SelfChangeCustomerPasswordRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SelfChangeCustomerPasswordResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignOutRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignOutResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.StaffGetSecureCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.StaffGetSecureCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtUser;
import vn.mog.framework.contract.base.BaseResponseType;

public interface ISecurityService {

  JwtUser signIn(String username, String password) throws Exception;

  SignOutResponse signOut(SignOutRequest request) throws Exception;

  RefreshTokenResponse refreshToken(RefreshTokenRequest request) throws Exception;

  String authToken(String username, String password) throws Exception;

  void setAccessToken(String accessToken);

  JwtUserResponse getJwtUser(JwtUserRequest request) throws Exception;

  SelfChangeCustomerPasswordResponse changePasswordBySelfCustomer(SelfChangeCustomerPasswordRequest request) throws Exception;

  SelfCustomerActiveAccountResponse activeAccountBySelfCustomer (SelfCustomerActiveAccountRequest request) throws Exception;

  RegisterWalletSecurityResponse registerWalletSecurity(RegisterWalletSecurityRequest request) throws Exception;

  StaffGetSecureCustomerResponse getSecureCustomerByStaff(StaffGetSecureCustomerRequest request) throws Exception;
}

