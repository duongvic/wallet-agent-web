package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IWalletEndpoint;
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
import vn.mog.ewallet.consumer.web.utils.WebUtil;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Service
public class WalletEndpoint implements IWalletEndpoint {

  private String cif_hashcode = "";
  private String id_customer_hashcode = "";

  @Autowired
  private GatewayUrlConfiguration gatewayUrlConfig;

  @Autowired
  private GatewayServiceAPIClient gatewayAPIClient;

  @Override
  public long getBalanceOfUser() throws FrontEndException {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_BALANCEINQUIRY, new MobiliserRequestType(),
        Long.class);
  }

  @Override
  public FundInResponse confirmFundInOtp(FundInRequest request) throws FrontEndException {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_FUNDIN, request, FundInResponse.class);
  }

  @Override
  public FundOutResponse confirmFundOutOtp(FundOutRequest request) throws FrontEndException {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_FUNDOUT, request, FundOutResponse.class);
  }

  @Override
  public FindFundOrderResponse findFundOrders(FindFundOrderRequest request)
      throws FrontEndException {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_FUNDORDER_FIND, request,
        FindFundOrderResponse.class);
  }

  @Override
  public FindFundOrderResponse findCustomerFundOrders(
      FindFundOrderRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_FIND, request, WebUtil.defaultNullUriVariables(),
        FindFundOrderResponse.class);
  }

  @Override
  public CreateFundOrderResponse createFundOrder(CreateFundOrderRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_CREATE, mapHeader, request, WebUtil.defaultNullUriVariables(),
        CreateFundOrderResponse.class);
  }

  @Override
  public UpdateFundOrderResponse updateFundOrder(UpdateFundOrderRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_UPDATE, request, WebUtil.defaultNullUriVariables(),
        UpdateFundOrderResponse.class);
  }

  @Override
  public GetFundOrderResponse getFundOrder(GetFundOrderRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_GET, request, WebUtil.defaultNullUriVariables(),
        GetFundOrderResponse.class);
  }

  @Override
  public FundOrderFlowApproveResponse approveFundOrder(FundOrderFlowApproveRequest request)
      throws FrontEndException {

    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_FUNDORDER_WORKFLOW_APPROVE, request,
        FundOrderFlowApproveResponse.class);
  }

  @Override
  public FundOrderFlowRejectResponse rejectFundOrder(FundOrderFlowRejectRequest request)
      throws FrontEndException {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_FUNDORDER_WORKFLOW_REJECT, request,
        FundOrderFlowRejectResponse.class);
  }

  @Override
  public FundOrderFlowSubmitProcessResponse submitFundOrder(
      FundOrderFlowSubmitProcessRequest request) throws FrontEndException {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_FUNDORDER_WORKFLOW_SUBMIT, request,
        FundOrderFlowSubmitProcessResponse.class);
  }

  @Override
  public GetConfirmOtpOrderResponse getConfirmOtpOrder(GetConfirmOtpOrderRequest request)
      throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_OTP_GET, mapHeader, request, WebUtil.defaultNullUriVariables(),
        GetConfirmOtpOrderResponse.class);
  }

  @Override
  public FindCustomerBankDirectResponse findBankDirects(FindCustomerBankDirectRequest request)
      throws FrontEndException {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_BANK_FINDBANKDIRECTS, request,
        FindCustomerBankDirectResponse.class);
  }

  @Override
  public FindCustomerResponse findCustomers(FindCustomerRequest request) throws FrontEndException {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_FINDCUSTOMER, request,
        FindCustomerResponse.class);
  }

  @Override
  public StaticBalanceResponse staticBalance(StaticBalanceRequest request)
      throws FrontEndException {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_STATSBALANCE, request, StaticBalanceResponse.class);
  }

  @Override
  public GetCustomerBalanceResponse getBalanceBySelfCustomer(
      GetCustomerGetBalanceRequest request) throws Exception {
    return gatewayAPIClient.getForEntity(gatewayUrlConfig.PARA_MEWALLET_URL_CUSTOMER_BALANCE_GET, request, null,
        GetCustomerBalanceResponse.class);
  }

  @Override
  public StaffGetBalanceResponse getBalanceByStaff(StaffGetBalanceRequest request)
      throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("cif", cif_hashcode);
    return gatewayAPIClient.getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BALANCE, mapHeader, request,
        uriVariables, StaffGetBalanceResponse.class);
  }

  @Override
  public SelfCustomerChangeStatusWalletResponse changeStatusWalletBySellCustomer(
      SelfCustomerChangeStatusWalletRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("id", id_customer_hashcode);
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_UPDATE_BLACKLIST_REASON, mapHeader, request, uriVariables,
        SelfCustomerChangeStatusWalletResponse.class);
  }

  @Override
  public FindWalletTransferOrderResponse findWalletTransferOrder(
      FindWalletTransferOrderRequest request) {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_WALLET_URL_TRANSFERORDER_FIND, request,
        FindWalletTransferOrderResponse.class);
  }

  @Override
  public CreateWalletTransferOrderResponse createWalletTransferOrder(
      CreateWalletTransferOrderRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_MONEYTRANSFER_CHECK, mapHeader, request, WebUtil.defaultNullUriVariables(),
        CreateWalletTransferOrderResponse.class);
  }

  @Override
  public UpdateWalletTransferOrderResponse updateWalletTransferOrder(
      UpdateWalletTransferOrderRequest request) {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_TRANSFERORDER_UPDATE, request,
        UpdateWalletTransferOrderResponse.class);
  }

  @Override
  public GetWalletTransferOrderResponse getWalletTransferOrder(
      GetWalletTransferOrderRequest request) {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_TRANSFERORDER_GET, request,
        GetWalletTransferOrderResponse.class);
  }

  @Override
  public OrderFlowSubmitProcessResponse orderFlowSubmitProccess(
      OrderFlowSubmitProcessRequest request) {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_TRANSFERORDER_WORKFLOW_SUBMIT, request,
        OrderFlowSubmitProcessResponse.class);
  }

  @Override
  public GetOTPConfirmResponse getOTPConfirmWalletTransferOrder(GetOTPConfirmRequest request)
      throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_MONEYTRANSFER_OTP_GET, mapHeader, request, WebUtil.defaultNullUriVariables(),
        GetOTPConfirmResponse.class);
  }

  @Override
  public ConfirmWalletTransferOrderResponse confirmWalletTransferOrde(
      ConfirmWalletTransferOrderRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_MONEYTRANSFER_CONFIRM, mapHeader, request, WebUtil.defaultNullUriVariables(),
        ConfirmWalletTransferOrderResponse.class);
  }

  @Override
  public OrderFlowApproveResponse approveWalletTransferOrder(OrderFlowApproveRequest request) {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_TRANSFERORDER_WORKFLOW_APPROVE, request,
        OrderFlowApproveResponse.class);
  }

  @Override
  public OrderFlowRejectResponse rejectWalletTransferOrder(OrderFlowRejectRequest request) {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_TRANSFERORDER_WORKFLOW_REJECT, request,
        OrderFlowRejectResponse.class);
  }

  @Override
  public WalletTransferResponse orderConfirmOTP(WalletTransferRequest request) {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_WALLETTRANSFER_OPT, request,
        WalletTransferResponse.class);
  }

  @Override
  public CustomerLinkBankResponse customerLinkBank(
      CustomerLinkBankRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANK_LINK, request, WebUtil.defaultNullUriVariables(), CustomerLinkBankResponse.class);
  }

  @Override
  public UnlinkBankResponse customerUnLinkBank(UnlinkBankRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANK_LINK, request, WebUtil.defaultNullUriVariables(), UnlinkBankResponse.class);
  }

  @Override
  public SelfCustomerGetWalletLinkResponse getWalletLinkCustomer(
      SelfCustomerGetWalletLinkRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_CUSTOMTER_BANKDIRECT, request, WebUtil.defaultNullUriVariables(), SelfCustomerGetWalletLinkResponse.class);
  }

  @Override
  public FindCustomerBankDirectResponse findCustomerBankDirect(
      FindCustomerBankDirectRequest request) throws Exception {
    return gatewayAPIClient.getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_CUSTOMTER_BANKDIRECT, request, WebUtil.defaultNullUriVariables(), FindCustomerBankDirectResponse.class);
  }

  @Override
  public BuyCardTopupResponse buyCardTopup(BuyCardTopupRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_TOPUP_REQUEST, mapHeader, request, WebUtil.defaultNullUriVariables(),
        BuyCardTopupResponse.class);
  }

  @Override
  public TopupTransactionResponse pinCodeVerify(TopupTransactionRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_TOPUP_REQUEST, request, WebUtil.defaultNullUriVariables(), TopupTransactionResponse.class);
  }

  @Override
  public TopupTransactionResponse pinCodeConfirm(TopupTransactionRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_TOPUP_REQUEST, request, WebUtil.defaultNullUriVariables(), TopupTransactionResponse.class);
  }

  @Override
  public TransactionResultExportResponse sendTransactionResult(TransactionResultExportRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_RESULT_EXPORT, request, WebUtil.defaultNullUriVariables(), TransactionResultExportResponse.class);
  }

  @Override
  public FundInTopupResponse fundInTopup(FundInTopupRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_TOPUP_REQUEST, mapHeader, request, WebUtil.defaultNullUriVariables(),
        FundInTopupResponse.class);
  }

  @Override
  public FundInBankResponse checkLinkBank(
      FundInBankRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANK_FUNDIN, request, WebUtil.defaultNullUriVariables(), FundInBankResponse.class);
  }

  @Override
  public ConfirmFundOrderResponse confirmFundOrder(ConfirmFundOrderRequest request)
      throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANK_FUNDIN, mapHeader, request, WebUtil.defaultNullUriVariables(),
        ConfirmFundOrderResponse.class);
  }

  @Override
  public ConfirmFundOrderOtpResponse confirmFundOrderOTP(ConfirmFundOrderOtpRequest request)
      throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_OTP_CONFIRM, mapHeader, request, WebUtil.defaultNullUriVariables(),
        ConfirmFundOrderOtpResponse.class);
  }

  @Override
  public vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.CreateFundOrderResponse createFundOrderRequest(
      vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.CreateFundOrderRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_CREATE, request, WebUtil.defaultNullUriVariables(),
        vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.CreateFundOrderResponse.class);
  }

  @Override
  public RequestBankChargingFundInOverPaymentGatewayResponse fundInBankCharge(RequestBankChargingFundInOverPaymentGatewayRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_PAYMENTGATEWAY_BANKCHARGING_REQUEST_FUNDIN, request, WebUtil.defaultNullUriVariables(), RequestBankChargingFundInOverPaymentGatewayResponse.class);
  }

  @Override
  public FundInVisaChargeResponse fundInVisaCharge(FundInVisaChargeRequest request)
      throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_FINDIN_PAYMENTGATEWAY_VISACHARGING_REQUEST, mapHeader, request, WebUtil.defaultNullUriVariables(),
        FundInVisaChargeResponse.class);
  }

  @Override
  public RequestChargingFundInOverPaymentGatewayV3Response fundInChargeV3(
      RequestChargingFundInOverPaymentGatewayV3Request request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_FUNDIN_PAYMENT_GATE_WAY_CHARGING_V3_REQUEST, request, WebUtil.defaultNullUriVariables(), RequestChargingFundInOverPaymentGatewayV3Response.class);
  }

  @Override
  public FundOutToBankResponse fundOutToBank(FundOutToBankRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANK_FUNDOUT, request, WebUtil.defaultNullUriVariables(), FundOutToBankResponse.class);
  }

  @Override
  public FundOutToBankResponse confirmOtpFundOutToBank(
      FundOutToBankRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANK_FUNDOUT, request, WebUtil.defaultNullUriVariables(), FundOutToBankResponse.class);
  }

  @Override
  public CustomerBankFundOutTransferResponse createBankFundOutTransfer(
      CustomerBankFundOutTransferRequest request) throws Exception {
    Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANK_FUNDTRANSFER, mapHeader, request, WebUtil.defaultNullUriVariables(),
        CustomerBankFundOutTransferResponse.class);
  }


  // topUp
  @Override
  public TopupTransactionResponse walletByTopUpVerify(
      TopupTransactionRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_TOPUP_REQUEST, request, WebUtil.defaultNullUriVariables(), TopupTransactionResponse.class);
  }

  @Override
  public TopupTransactionResponse walletByTopUpConfirm(
      TopupTransactionRequest request) throws Exception {
    // Map<String, String> mapHeader = WebUtil.defaultMapHeader();
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_TOPUP_REQUEST, request, WebUtil.defaultNullUriVariables(), TopupTransactionResponse.class);
  }

  @Override
  public TopupTransactionOTPGetResponse resendOTP(
      TopupTransactionOTPGetRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_TOPUP_OTP_GET, request, WebUtil.defaultNullUriVariables(), TopupTransactionOTPGetResponse.class);
  }

  // Money Transfer
  @Override
  public MoneyTransferResponse moneyTransferVerify(MoneyTransferRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_WALLET_MONEYTRANSFER, request, WebUtil.defaultNullUriVariables(), MoneyTransferResponse.class);
  }

  @Override
  public MoneyTransferResponse moneyTransferConfirm(MoneyTransferRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_WALLET_MONEYTRANSFER, request, WebUtil.defaultNullUriVariables(), MoneyTransferResponse.class);
  }

  @Override
  public MoneyTransferOTPGetResponse moneyTransferGetOTP(
      MoneyTransferOTPGetRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_WALLET_MONEY_TRANSFER_OTP_GET, request, WebUtil.defaultNullUriVariables(), MoneyTransferOTPGetResponse.class);
  }

  @Override
  public SendGoogleAuthenticatorResponse sendGoogleAuthenticator(SendGoogleAuthenticatorRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SEND_GOOGLE_AUTHENTICATOR, request, WebUtil.defaultNullUriVariables(), SendGoogleAuthenticatorResponse.class);
  }


  @Override
  public FundOutNoLinkToBankResponse fundOutNoLinkBankVerify(
      FundOutNoLinkToBankRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANK_BANKFUNDOUTNOLINKTOBANK, request, WebUtil.defaultNullUriVariables(), FundOutNoLinkToBankResponse.class);
  }

  @Override
  public FundOutNoLinkToBankResponse fundOutNoLinkBankConfirm(
      FundOutNoLinkToBankRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANK_BANKFUNDOUTNOLINKTOBANK, request, WebUtil.defaultNullUriVariables(), FundOutNoLinkToBankResponse.class);
  }

  @Override
  public GetTransactionResponse transactionGet(GetTransactionRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_GET, request, WebUtil.defaultNullUriVariables(), GetTransactionResponse.class);
  }

  //Security

  @Override
  public SecurityPaymentGetResponse securityPaymentGet(
      SecurityPaymentGetRequest request) throws Exception {
    return gatewayAPIClient.getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_PAYMENT_SECURITY_GET, request, WebUtil.defaultNullUriVariables(), SecurityPaymentGetResponse.class);
  }

  @Override
  public SecurityPaymentGetOTPResponse securityPaymentGetOTP(
      SecurityPaymentGetOTPRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_PAYMENT_SECURITY_GET_OTP,
        request, WebUtil.defaultNullUriVariables(), SecurityPaymentGetOTPResponse.class);
  }

  @Override
  public ProfileCheckResponse profileCheck(ProfileCheckRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_PROFILE_CHECK,
        WebUtil.defaultUriVariables(), request, null, ProfileCheckResponse.class);
  }

  @Override
  public WalletCashInResponse cashIn(WalletCashInRequest request) throws Exception {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_CASHIN,
        request, WalletCashInResponse.class);
  }

  @Override
  public WalletCashInFeeGetResponse getFeecashIn(WalletCashInFeeGetRequest request) throws Exception {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_CASHIN_GET_FEE, request, WalletCashInFeeGetResponse.class);
  }

  @Override
  public WalletCashInOTPGetResponse getOtpcashIn(WalletCashInOTPGetRequest request) throws Exception {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_CASHIN_GET_OTP, request, WalletCashInOTPGetResponse.class);
  }

  @Override
  public WalletCashOutResponse cashOut(WalletCashOutRequest request) throws Exception {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_CASHOUT,
        request, WalletCashOutResponse.class);
  }

  @Override
  public WalletCashOutInfoGetResponse getInfoCashOut(WalletCashOutInfoGetRequest request) throws Exception {
    return gatewayAPIClient.callRequest(gatewayUrlConfig.PARAM_EWALLET_URL_CASHOUT_GET_INFO,
        request, WalletCashOutInfoGetResponse.class);
  }

  @Override
  public SecurityPaymentRegisterResponse securityPaymentRegister(
      SecurityPaymentRegisterRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_PAYMENT_SECURITY_REGISTER, request, WebUtil.defaultNullUriVariables(), SecurityPaymentRegisterResponse.class);
  }

  @Override
  public VerifySetupPaymentPinResponse verifySetupPaymentPin(
      VerifySetupPaymentPinRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_PAYMENT_PIN_CHANGE_VERIFY, request, WebUtil.defaultNullUriVariables(), VerifySetupPaymentPinResponse.class);
  }

  @Override
  public SetupPaymentPinResponse changePaymentPinSetup(SetupPaymentPinRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_PAYMENT_PIN_CHANGE, request, WebUtil.defaultNullUriVariables(), SetupPaymentPinResponse.class);
  }

  @Override
  public CheckPaymentPinResponse checkPaymentPin() throws Exception {
    return gatewayAPIClient.getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_PAYMENT_PIN_CHECK_EXIST, null, WebUtil.defaultNullUriVariables(), CheckPaymentPinResponse.class);
  }

  @Override
  public ForgetPasswordOtpGetResponse forgotPassGetOTP(ForgetPasswordOtpGetRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_FORGOTPASS_OTP_GET, request, WebUtil.defaultNullUriVariables(), ForgetPasswordOtpGetResponse.class);
  }

  @Override
  public ForgotPasswordOtpVerifyResponse forgotPassVerifyOTP(
      ForgotPasswordOtpVerifyRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_FORGOTPASS_OTP_VERIFY, request, WebUtil.defaultNullUriVariables(), ForgotPasswordOtpVerifyResponse.class);
  }

  @Override
  public ForgotPasswordSetPassResponse forgotPassSetPass(ForgotPasswordSetPassRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_FORGOTPASS_PASSWORD_SET, request, WebUtil.defaultNullUriVariables(), ForgotPasswordSetPassResponse.class);
  }

  @Override
  public GetSetupOtpResponse getSecurityOTP(GetSetupOtpRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_GET_SECURITY_OTP, request, WebUtil.defaultNullUriVariables(), GetSetupOtpResponse.class);
  }

  @Override
  public VerifyChangePasswordOtpResponse changePasswordVerifyOTP(
      VerifyChangePasswordOtpRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_CHANGE_PASSWORD_VERIFY_OTP, request, WebUtil.defaultNullUriVariables(), VerifyChangePasswordOtpResponse.class);
  }

  @Override
  public ChangePasswordSSOResponse changePasswordChange(
      ChangePasswordSSORequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_CHANGE_PASSWORD, request, WebUtil.defaultNullUriVariables(), ChangePasswordSSOResponse.class);
  }

  @Override
  public CheckLoginPasswordResponse checkLoginPassword(CheckLoginPasswordRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_PASSWORD_CHECK_EXACT, request, null, CheckLoginPasswordResponse.class);
  }

  @Override
  public ForgotPasswordOtpVerifyResponse getKeyByCurrentPassword(CheckLoginPasswordRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SECURITY_PASSWORD_CHECK_EXACT, request, null, ForgotPasswordOtpVerifyResponse.class);
  }

  /*----- CUSTOMER BANK -----*/
  @Override
  public CreateBankItemResponse customerBankItemCreate(
      CreateBankItemRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANKITEM_CREATE, request, WebUtil.defaultNullUriVariables(), CreateBankItemResponse.class);
  }

  @Override
  public DeleteBankItemResponse customerBankItemDelete(
      DeleteBankItemRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANKITEM_DELETE, request, WebUtil.defaultNullUriVariables(), DeleteBankItemResponse.class);
  }

  @Override
  public GetBankItemByCustomerResponse customerBankItemGetByCustomer(
      GetBankItemByCustomerRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANKITEM, request, WebUtil.defaultNullUriVariables(), GetBankItemByCustomerResponse.class);
  }

  /*----- SERVICE -----*/
  @Override
  public TrueServiceCommissionGetResponse getTrueServiceCommission(
      TrueServiceCommissionGetRequest request) throws Exception {

    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_COMMISSION_GET, request, WebUtil.defaultNullUriVariables(),
        TrueServiceCommissionGetResponse.class);
  }

  @Override
  public FindTrueServiceResponse findTrueService(FindTrueServiceRequest request)
      throws Exception {

    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_SERVICE_FIND,
        request, WebUtil.defaultNullUriVariables(), FindTrueServiceResponse.class);
  }

  @Override
  public RegisterPaymentOnlineResponse registerPaymentOnline(
      RegisterPaymentOnlineRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_BANK_REGISTER_PAYMENTONLINE, request, WebUtil.defaultNullUriVariables(),
        RegisterPaymentOnlineResponse.class);
  }

  /*Recentness*/
  @Override
  public RecentnessGetResponse recentnessGet(RecentnessGetRequest request)
      throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_RECENTNESS_GET, request, WebUtil.defaultNullUriVariables(),
        RecentnessGetResponse.class);
  }

  /*Account info*/
  @Override
  public GetConsumerAccountResponse accountInfoGet() throws Exception {
    return gatewayAPIClient.getForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_ACCOUNT_INFO_GET, null, WebUtil.defaultNullUriVariables(),
        GetConsumerAccountResponse.class);
  }

  @Override
  public CheckExistedAccountByPhoneResponse checkAccountExisted(
      CheckExistedAccountByPhoneRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_CHECK_ACCOUNT_EXISTED, request, WebUtil.defaultNullUriVariables(), CheckExistedAccountByPhoneResponse.class);
  }

  @Override
  public FindCustomerAttributeResponse findCustomerAttribute(
      FindCustomerAttributeRequest request) throws Exception {
    return gatewayAPIClient.postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_FIND_CUSTOMER_ATTRIBUTE, request, WebUtil.defaultNullUriVariables(), FindCustomerAttributeResponse.class);
  }

}

