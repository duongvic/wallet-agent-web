package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;

import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.CreateBankItemRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.CreateBankItemResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.CustomerLinkBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.CustomerLinkBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.DeleteBankItemRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.DeleteBankItemResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.FundInBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.FundInBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.FundOutNoLinkToBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.FundOutNoLinkToBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.GetBankItemByCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.GetBankItemByCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.RegisterPaymentOnlineRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.RegisterPaymentOnlineResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.SelfCustomerGetWalletLinkRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.SelfCustomerGetWalletLinkResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.UnlinkBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.UnlinkBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.FindCustomerAttributeRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.FindCustomerAttributeResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerBalanceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerGetBalanceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.SelfCustomerChangeStatusWalletRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.SelfCustomerChangeStatusWalletResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.StaffGetBalanceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.StaffGetBalanceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.GetFundOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.GetFundOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.TopupTransactionOTPGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.TopupTransactionOTPGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.TransactionResultExportRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.TransactionResultExportResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.UpdateFundOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.UpdateFundOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetOTPRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetOTPResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentRegisterRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.payment.security.SecurityPaymentRegisterResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness.RecentnessGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.recentness.RecentnessGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.FindTrueServiceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.FindTrueServiceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.TrueServiceCommissionGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.TrueServiceCommissionGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetOTPConfirmRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetOTPConfirmResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.RequestBankChargingFundInOverPaymentGatewayRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.RequestBankChargingFundInOverPaymentGatewayResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ChangePasswordSSORequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ChangePasswordSSOResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckExistedAccountByPhoneRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckExistedAccountByPhoneResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckLoginPasswordRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckLoginPasswordResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.CheckPaymentPinResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgetPasswordOtpGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgetPasswordOtpGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgotPasswordOtpVerifyRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgotPasswordOtpVerifyResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgotPasswordSetPassRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ForgotPasswordSetPassResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.GetSetupOtpRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.GetSetupOtpResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ProfileCheckRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.ProfileCheckResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SetupPaymentPinRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SetupPaymentPinResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.VerifyChangePasswordOtpRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.VerifyChangePasswordOtpResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.VerifySetupPaymentPinRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.VerifySetupPaymentPinResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.topup.BuyCardTopupRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.topup.BuyCardTopupResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.topup.FundInTopupRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.topup.FundInTopupResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.GetTransactionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.TopupTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.TopupTransactionResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.*;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.FundOrderFlowApproveRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.FundOrderFlowApproveResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.FundOrderFlowRejectRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.FundOrderFlowRejectResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.FundOrderFlowSubmitProcessRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.FundOrderFlowSubmitProcessResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowApproveRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowApproveResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowRejectRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowRejectResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowSubmitProcessRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowSubmitProcessResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.WalletTransferRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.WalletTransferResponse;

/**
 * Created by duongnh on 24/09/2018.
 */
public interface IWalletEndpoint {

  /**
   * Business
   */
  long getBalanceOfUser() throws FrontEndException;

  FindCustomerResponse findCustomers(FindCustomerRequest request) throws FrontEndException;

  StaticBalanceResponse staticBalance(StaticBalanceRequest request) throws FrontEndException;

  GetCustomerBalanceResponse getBalanceBySelfCustomer(
      GetCustomerGetBalanceRequest request) throws Exception;

  StaffGetBalanceResponse getBalanceByStaff(StaffGetBalanceRequest request) throws Exception;

  SelfCustomerChangeStatusWalletResponse changeStatusWalletBySellCustomer(
      SelfCustomerChangeStatusWalletRequest request) throws Exception;

  FundInResponse confirmFundInOtp(FundInRequest request) throws FrontEndException;

  FundOutResponse confirmFundOutOtp(FundOutRequest request) throws FrontEndException;


  /****** Fund Order *********/

  FindFundOrderResponse findFundOrders(FindFundOrderRequest request) throws FrontEndException;

  FindFundOrderResponse findCustomerFundOrders(FindFundOrderRequest request)
      throws Exception;

  CreateFundOrderResponse createFundOrder(CreateFundOrderRequest request) throws Exception;

  UpdateFundOrderResponse updateFundOrder(UpdateFundOrderRequest request) throws Exception;

  GetFundOrderResponse getFundOrder(GetFundOrderRequest request) throws Exception;

  FundOrderFlowApproveResponse approveFundOrder(FundOrderFlowApproveRequest request)
      throws FrontEndException;

  FundOrderFlowRejectResponse rejectFundOrder(FundOrderFlowRejectRequest request)
      throws FrontEndException;

  FundOrderFlowSubmitProcessResponse submitFundOrder(FundOrderFlowSubmitProcessRequest request)
      throws FrontEndException;

  GetConfirmOtpOrderResponse getConfirmOtpOrder(GetConfirmOtpOrderRequest request) throws Exception;

  FundInBankResponse checkLinkBank(FundInBankRequest request)
      throws Exception;

  ConfirmFundOrderResponse confirmFundOrder(ConfirmFundOrderRequest request) throws Exception;

  ConfirmFundOrderOtpResponse confirmFundOrderOTP(ConfirmFundOrderOtpRequest request)
      throws Exception;

  vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.CreateFundOrderResponse createFundOrderRequest(
      vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.CreateFundOrderRequest request)
      throws Exception;

  /****** Fund In *********/
  RequestBankChargingFundInOverPaymentGatewayResponse fundInBankCharge(
      RequestBankChargingFundInOverPaymentGatewayRequest request)
      throws Exception;

  FundInVisaChargeResponse fundInVisaCharge(FundInVisaChargeRequest request) throws Exception;

  RequestChargingFundInOverPaymentGatewayV3Response fundInChargeV3(
      RequestChargingFundInOverPaymentGatewayV3Request request)
      throws Exception;

  /****** Fund Out *********/
  FundOutToBankResponse fundOutToBank(FundOutToBankRequest request)
      throws Exception;

  FundOutToBankResponse confirmOtpFundOutToBank(FundOutToBankRequest request)
      throws Exception;

  CustomerBankFundOutTransferResponse createBankFundOutTransfer(
      CustomerBankFundOutTransferRequest request) throws Exception;


  FindCustomerBankDirectResponse findBankDirects(FindCustomerBankDirectRequest request)
      throws FrontEndException;


  /*--- WALLET TRANSFER ORDER ---*/
  FindWalletTransferOrderResponse findWalletTransferOrder(FindWalletTransferOrderRequest request);


  CreateWalletTransferOrderResponse createWalletTransferOrder(
      CreateWalletTransferOrderRequest request) throws Exception;

  UpdateWalletTransferOrderResponse updateWalletTransferOrder(
      UpdateWalletTransferOrderRequest request);

  GetWalletTransferOrderResponse getWalletTransferOrder(GetWalletTransferOrderRequest request);

  OrderFlowSubmitProcessResponse orderFlowSubmitProccess(OrderFlowSubmitProcessRequest request);

  GetOTPConfirmResponse getOTPConfirmWalletTransferOrder(GetOTPConfirmRequest request)
      throws Exception;

  ConfirmWalletTransferOrderResponse confirmWalletTransferOrde(
      ConfirmWalletTransferOrderRequest request) throws Exception;


  /*--- WALLET ORDER flow ---*/

  OrderFlowApproveResponse approveWalletTransferOrder(OrderFlowApproveRequest request);

  OrderFlowRejectResponse rejectWalletTransferOrder(OrderFlowRejectRequest request);

  WalletTransferResponse orderConfirmOTP(WalletTransferRequest request);

  /*-- Payment security ---**/

  /*--  linkbank ---**/
  CustomerLinkBankResponse customerLinkBank(CustomerLinkBankRequest request)
      throws Exception;

  UnlinkBankResponse customerUnLinkBank(UnlinkBankRequest request)
      throws Exception;

  SelfCustomerGetWalletLinkResponse getWalletLinkCustomer(
      SelfCustomerGetWalletLinkRequest request) throws Exception;

  FindCustomerBankDirectResponse findCustomerBankDirect(
      FindCustomerBankDirectRequest request) throws Exception;
  /*-- Topup---**/

  BuyCardTopupResponse buyCardTopup(BuyCardTopupRequest request) throws Exception;

  FundInTopupResponse fundInTopup(FundInTopupRequest request) throws Exception;

  TopupTransactionResponse walletByTopUpVerify(TopupTransactionRequest request)
      throws Exception;

  TopupTransactionResponse walletByTopUpConfirm(TopupTransactionRequest request)
      throws Exception;

  TopupTransactionOTPGetResponse resendOTP(TopupTransactionOTPGetRequest request)
      throws Exception;

  // PinCode
  TopupTransactionResponse pinCodeVerify(TopupTransactionRequest request)
      throws Exception;

  TopupTransactionResponse pinCodeConfirm(TopupTransactionRequest request)
      throws Exception;

  TransactionResultExportResponse sendTransactionResult(TransactionResultExportRequest request)
      throws Exception;

  // Money Transfer
  MoneyTransferResponse moneyTransferVerify(MoneyTransferRequest request)
      throws Exception;

  MoneyTransferResponse moneyTransferConfirm(MoneyTransferRequest request)
      throws Exception;

  MoneyTransferOTPGetResponse moneyTransferGetOTP(MoneyTransferOTPGetRequest request)
      throws Exception;

  SendGoogleAuthenticatorResponse sendGoogleAuthenticator(SendGoogleAuthenticatorRequest request) throws Exception;
  // FundOutNoLinkBank

  FundOutNoLinkToBankResponse fundOutNoLinkBankVerify(
      FundOutNoLinkToBankRequest request) throws Exception;

  FundOutNoLinkToBankResponse fundOutNoLinkBankConfirm(
      FundOutNoLinkToBankRequest request) throws Exception;

  GetTransactionResponse transactionGet(GetTransactionRequest request)
      throws Exception;

  // Security

  SecurityPaymentGetResponse securityPaymentGet(SecurityPaymentGetRequest request)
      throws Exception;

  SecurityPaymentRegisterResponse securityPaymentRegister(
      SecurityPaymentRegisterRequest request) throws Exception;

  VerifySetupPaymentPinResponse verifySetupPaymentPin(
      VerifySetupPaymentPinRequest request) throws Exception;

  SetupPaymentPinResponse changePaymentPinSetup(
      SetupPaymentPinRequest request) throws Exception;

  CheckPaymentPinResponse checkPaymentPin() throws Exception;

  ForgetPasswordOtpGetResponse forgotPassGetOTP(ForgetPasswordOtpGetRequest request)
      throws Exception;

  ForgotPasswordOtpVerifyResponse forgotPassVerifyOTP(ForgotPasswordOtpVerifyRequest request)
      throws Exception;

  ForgotPasswordSetPassResponse forgotPassSetPass(ForgotPasswordSetPassRequest request)
      throws Exception;

  GetSetupOtpResponse getSecurityOTP(GetSetupOtpRequest request) throws Exception;

  VerifyChangePasswordOtpResponse changePasswordVerifyOTP(VerifyChangePasswordOtpRequest request)
      throws Exception;

  ChangePasswordSSOResponse changePasswordChange(ChangePasswordSSORequest request) throws Exception;

  CheckLoginPasswordResponse checkLoginPassword(CheckLoginPasswordRequest request) throws Exception;

  ForgotPasswordOtpVerifyResponse getKeyByCurrentPassword(CheckLoginPasswordRequest request) throws Exception;


  /*----- CUSTOMER BANK -----*/
  CreateBankItemResponse customerBankItemCreate(
      CreateBankItemRequest request) throws Exception;

  DeleteBankItemResponse customerBankItemDelete(
      DeleteBankItemRequest request) throws Exception;

  GetBankItemByCustomerResponse customerBankItemGetByCustomer(
      GetBankItemByCustomerRequest request) throws Exception;

  /*----- SERVICE -----*/
  TrueServiceCommissionGetResponse getTrueServiceCommission(
      TrueServiceCommissionGetRequest request)
      throws Exception;

  FindTrueServiceResponse findTrueService(FindTrueServiceRequest request) throws Exception;

  /*RegisterPaymentOnline*/
  RegisterPaymentOnlineResponse registerPaymentOnline(
      RegisterPaymentOnlineRequest request) throws Exception;


  /*Recentness*/
  RecentnessGetResponse recentnessGet(RecentnessGetRequest request)
      throws Exception;

  /*Account info*/
  GetConsumerAccountResponse accountInfoGet() throws Exception;

  CheckExistedAccountByPhoneResponse checkAccountExisted(CheckExistedAccountByPhoneRequest request)
      throws Exception;

  FindCustomerAttributeResponse findCustomerAttribute(FindCustomerAttributeRequest request)
      throws Exception;

  SecurityPaymentGetOTPResponse securityPaymentGetOTP(
      SecurityPaymentGetOTPRequest request) throws Exception;

  ProfileCheckResponse profileCheck(ProfileCheckRequest request)throws Exception;

  /****** Viettel Pay *********/

  WalletCashInResponse cashIn(WalletCashInRequest request)throws Exception;

  WalletCashInFeeGetResponse getFeecashIn(WalletCashInFeeGetRequest request)throws Exception;

  WalletCashInOTPGetResponse getOtpcashIn(WalletCashInOTPGetRequest request)throws Exception;


  WalletCashOutResponse cashOut(WalletCashOutRequest request)throws Exception;

  WalletCashOutInfoGetResponse  getInfoCashOut (WalletCashOutInfoGetRequest request)throws Exception;

}
