package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IEpinPurchaseOrderEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetSpecialCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.card.GetAvailableCardRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.card.GetAvailableCardResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.BuyCardOfflineConfirmRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.BuyCardOfflineConfirmResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.CheckEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.CheckEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.CreateEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.CreateEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.ExportEpinPurchaseOrderFormRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.ExportEpinPurchaseOrderFormResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.FindEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.FindEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetCustomerCardDashboardRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetCustomerCardDashboardResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderAttachmentRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderAttachmentResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderOTPConfirmRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderOTPConfirmResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetReportEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetReportEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.UpdateEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.UpdateEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.CardType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowApproveRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowApproveResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowRejectRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowRejectResponse;
import vn.mog.ewallet.consumer.web.utils.WebUtil;
import vn.mog.framework.contract.base.BaseResponseType;


@Service
public class EpinPurchaseOrderEndpoint implements IEpinPurchaseOrderEndpoint {



//  private static final String URI_EPIN_FLOW_REJECT =
//      "/customer/api/v1/customer/epinPurchaseOrder/workflow/reject";

  @Autowired
  GatewayServiceAPIClient gatewayServiceAPIClient;
  
  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig;

  @Override
  public CheckEpinPurchaseOrderResponse checkEpinPurchaseOrder(
      CheckEpinPurchaseOrderRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPO_CHECK, request, WebUtil.defaultNullUriVariables(), CheckEpinPurchaseOrderResponse.class);
  }

  @Override
  public CreateEpinPurchaseOrderResponse createEpinPO(
      CreateEpinPurchaseOrderRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPO_CREATE, request, WebUtil.defaultNullUriVariables(), CreateEpinPurchaseOrderResponse.class);
  }

  @Override
  public UpdateEpinPurchaseOrderResponse updateEpinPO(
      UpdateEpinPurchaseOrderRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPO_UPDATE, request,  WebUtil.defaultNullUriVariables(), UpdateEpinPurchaseOrderResponse.class);
  }

  @Override
  public GetEpinPurchaseOrderResponse getEpinPO(
      GetEpinPurchaseOrderRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPO_GET, request, WebUtil.defaultNullUriVariables(), GetEpinPurchaseOrderResponse.class);

  }

  @Override
  public GetEpinPurchaseOrderAttachmentResponse getMPOAttachment(
      GetEpinPurchaseOrderAttachmentRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPO_ATTACHMENT_GET, request, WebUtil
            .defaultNullUriVariables(), GetEpinPurchaseOrderAttachmentResponse.class);
  }

  @Override
  public GetEpinPurchaseOrderOTPConfirmResponse getOTPConfirm(
      GetEpinPurchaseOrderOTPConfirmRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPO_OTP_GET, request, WebUtil.defaultNullUriVariables(), GetEpinPurchaseOrderOTPConfirmResponse.class);
  }

  @Override
  public GetAvailableCardResponse getAvailableCard(
      GetAvailableCardRequest request) throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPO_AVAILABLECARD_GET, request, WebUtil
            .defaultNullUriVariables(), GetAvailableCardResponse.class);

  }

  @Override
  public BuyCardOfflineConfirmResponse buyCardOfflineConfirm(
      BuyCardOfflineConfirmRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPO_BUYCARD_CONFIRM, request, WebUtil
            .defaultNullUriVariables(), BuyCardOfflineConfirmResponse.class);
  }


  @Override
  public FindEpinPurchaseOrderResponse findEpinPOs(
      FindEpinPurchaseOrderRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPO_FIND, request, WebUtil.defaultNullUriVariables(), FindEpinPurchaseOrderResponse.class);

  }

  @Override
  public GetReportEpinPurchaseOrderResponse getReportEpin(
      GetReportEpinPurchaseOrderRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_GET_MERCHANTPOREPORT, request, WebUtil
            .defaultNullUriVariables(), GetReportEpinPurchaseOrderResponse.class);
  }

  @Override
  public OrderFlowApproveResponse epinPurchaseOrderWorkflowApporve(
      OrderFlowApproveRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPINPURCHASEORDER_WORKFLOW_APPROVE, request, WebUtil
            .defaultNullUriVariables(), OrderFlowApproveResponse.class);
  }

  @Override
  public OrderFlowRejectResponse epinPurchaseOrderWorkflowReject(OrderFlowRejectRequest request)
      throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EPINPURCHASEORDER_WORKFLOW_REJECT, request, WebUtil
            .defaultNullUriVariables(), OrderFlowRejectResponse.class);
  }

  @Override
  public GetCustomerCardDashboardResponse epoGetCardDashBoard(
      GetCustomerCardDashboardRequest request) throws Exception {
    return gatewayServiceAPIClient.postForEntity(
        gatewayUrlConfig.PARAM_EWALLET_URL_CARD_STORE_CARD_DASHBOARD, request,
        WebUtil.defaultNullUriVariables(), GetCustomerCardDashboardResponse.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public BaseResponseType<List<CardType>> epoGetListOperator() throws Exception {
    return gatewayServiceAPIClient.getForEntity(
        gatewayUrlConfig.PARAM_EWALLET_URL_CARD_STORE_LIST_OPERATOR, null,
        WebUtil.defaultNullUriVariables(), BaseResponseType.class);
  }

  @Override
  public GetSpecialCustomerResponse getSpecialMerchant() throws Exception {
    return gatewayServiceAPIClient.getForEntity(
        gatewayUrlConfig.PARAM_EWALLET_URL_CARD_STORE_GET_SPECIAL_MERCHANT, null,
        WebUtil.defaultNullUriVariables(), GetSpecialCustomerResponse.class);
  }

  @Override
  public GetCustomerCardDashboardResponse epoGetCardDashBoardOffline(
      GetCustomerCardDashboardRequest request) throws Exception {
    return gatewayServiceAPIClient.postForEntity(
        gatewayUrlConfig.PARAM_EWALLET_URL_CARD_STORE_OFFLINE_CARD_DASHBOARD, request,
        WebUtil.defaultNullUriVariables(), GetCustomerCardDashboardResponse.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public BaseResponseType<List<CardType>> epoGetListOperatorOffline() throws Exception {
    return gatewayServiceAPIClient.getForEntity(
        gatewayUrlConfig.PARAM_EWALLET_URL_CARD_STORE_OFFLINE_LIST_OPERATOR, null,
        WebUtil.defaultNullUriVariables(), BaseResponseType.class);
  }

  @Override
  public GetSpecialCustomerResponse getSpecialMerchantOffline() throws Exception {
    return gatewayServiceAPIClient.getForEntity(
        gatewayUrlConfig.PARAM_EWALLET_URL_CARD_STORE_OFFLINE_GET_SPECIAL_MERCHANT, null,
        WebUtil.defaultNullUriVariables(), GetSpecialCustomerResponse.class);
  }

 /* Card N02*/
  @Override
  public GetSpecialCustomerResponse getSpecialMerchantN02() throws Exception {
    return gatewayServiceAPIClient.getForEntity(
        gatewayUrlConfig.PARAM_EWALLET_URL_CARD_STORE_N02_GET_SPECIAL_MERCHANT, null,
        WebUtil.defaultNullUriVariables(), GetSpecialCustomerResponse.class);
  }

  @Override
  public BaseResponseType<List<CardType>> epoGetListOperatorN02() throws Exception {
    return gatewayServiceAPIClient.getForEntity(
        gatewayUrlConfig.PARAM_EWALLET_URL_CARD_STORE_N02_LIST_OPERATOR, null,
        WebUtil.defaultNullUriVariables(), BaseResponseType.class);
  }

  @Override
  public GetCustomerCardDashboardResponse epoGetCardDashBoardN02(
      GetCustomerCardDashboardRequest request) throws Exception {
    return gatewayServiceAPIClient.postForEntity(
        gatewayUrlConfig.PARAM_EWALLET_URL_CARD_STORE_N02_CARD_DASHBOARD, request,
        WebUtil.defaultNullUriVariables(), GetCustomerCardDashboardResponse.class);
  }

  @Override
  public ExportEpinPurchaseOrderFormResponse exportEpoNewCreate(
      ExportEpinPurchaseOrderFormRequest request) throws Exception {
    return gatewayServiceAPIClient
        .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_CUSTOMER_EXPORT_NEW_EPIN, request, WebUtil
            .defaultNullUriVariables(), ExportEpinPurchaseOrderFormResponse.class);
  }


}
