package vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway;

import java.util.List;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetSpecialCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.SpecialCustomerItem;
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
import vn.mog.framework.contract.base.BaseResponseType;


public interface IEpinPurchaseOrderEndpoint {

  CheckEpinPurchaseOrderResponse checkEpinPurchaseOrder(CheckEpinPurchaseOrderRequest request)
      throws Exception;

  CreateEpinPurchaseOrderResponse createEpinPO(CreateEpinPurchaseOrderRequest request)
      throws Exception;

  UpdateEpinPurchaseOrderResponse updateEpinPO(UpdateEpinPurchaseOrderRequest request)
      throws Exception;

  GetEpinPurchaseOrderResponse getEpinPO(GetEpinPurchaseOrderRequest request)
      throws Exception;

  FindEpinPurchaseOrderResponse findEpinPOs(FindEpinPurchaseOrderRequest request)
      throws Exception;


  GetEpinPurchaseOrderAttachmentResponse getMPOAttachment(GetEpinPurchaseOrderAttachmentRequest request) throws Exception;

  GetEpinPurchaseOrderOTPConfirmResponse getOTPConfirm(GetEpinPurchaseOrderOTPConfirmRequest request) throws Exception;

  GetAvailableCardResponse getAvailableCard(GetAvailableCardRequest request) throws Exception;

  BuyCardOfflineConfirmResponse buyCardOfflineConfirm(BuyCardOfflineConfirmRequest request)
      throws Exception;

  GetReportEpinPurchaseOrderResponse getReportEpin(GetReportEpinPurchaseOrderRequest request)
      throws Exception;

  OrderFlowApproveResponse epinPurchaseOrderWorkflowApporve(OrderFlowApproveRequest request) throws Exception;

  OrderFlowRejectResponse epinPurchaseOrderWorkflowReject(OrderFlowRejectRequest request) throws Exception;

  GetCustomerCardDashboardResponse epoGetCardDashBoard(
      GetCustomerCardDashboardRequest request) throws Exception;

  @SuppressWarnings("unchecked")
  BaseResponseType<List<CardType>> epoGetListOperator() throws Exception;

  GetSpecialCustomerResponse getSpecialMerchant() throws Exception;

  GetCustomerCardDashboardResponse epoGetCardDashBoardOffline(GetCustomerCardDashboardRequest request) throws Exception;

  BaseResponseType<List<CardType>> epoGetListOperatorOffline() throws Exception;

  GetSpecialCustomerResponse getSpecialMerchantOffline() throws Exception;

  /*card n02*/
  GetSpecialCustomerResponse getSpecialMerchantN02() throws Exception;

  BaseResponseType<List<CardType>> epoGetListOperatorN02() throws Exception;

  GetCustomerCardDashboardResponse epoGetCardDashBoardN02(GetCustomerCardDashboardRequest request) throws Exception;

  ExportEpinPurchaseOrderFormResponse exportEpoNewCreate (ExportEpinPurchaseOrderFormRequest request) throws Exception;

}
