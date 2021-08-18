package vn.mog.ewallet.consumer.web.controller.epo;

import static vn.mog.ewallet.consumer.web.exception.MessageNotify.ERROR_CODE;
import static vn.mog.ewallet.consumer.web.exception.MessageNotify.SESSION_MESSAGE_NOTIFY;
import static vn.mog.ewallet.consumer.web.exception.MessageNotify.SUCCESS_CODE;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;
import static vn.mog.ewallet.consumer.web.utils.ValidationUtil.MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS;
import static vn.mog.ewallet.consumer.web.utils.ValidationUtil.MESAGE_ORDER_FLOW_REJECT_PROCESS_SUCCESS;
import static vn.mog.ewallet.consumer.web.utils.ValidationUtil.MESAGE_SUCCESS;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import vn.mog.ewallet.consumer.web.common.SharedConstants;
import vn.mog.ewallet.consumer.web.contract.AjaxResponse;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.epo.beans.EpinPurchaseOrderDetailTmp;
import vn.mog.ewallet.consumer.web.controller.epo.beans.EpinPurchaseOrderFlowStage;
import vn.mog.ewallet.consumer.web.controller.epo.beans.EpinPurchaseOrderSummaryTmp;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.exception.MessageNotify;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetSpecialCustomerResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.CustomerAttributeType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.CustomerType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.card.GetAvailableCardRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.card.GetAvailableCardResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.FindCustomerAttributeRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.FindCustomerAttributeResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans.CustomerAttribute;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.BuyCardOfflineConfirmRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.BuyCardOfflineConfirmResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.CheckEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.CheckEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.CreateEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.CreateEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.ExportEpinPurchaseOrderFormRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.ExportEpinPurchaseOrderFormResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderAttachmentRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderAttachmentRequest.Action;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderAttachmentResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderOTPConfirmRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderOTPConfirmResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.UpdateEpinPurchaseOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.UpdateEpinPurchaseOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrder.StoreType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrderAttachment;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrderDetail;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowApproveRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowApproveResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowRejectRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.workflow.OrderFlowRejectResponse;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

public class BatchCardsController extends AbstractController {
  public static final String BATCH_CARDS_CONTROLLER = "/batch-cards";
  public static final String BATCH_CARDS_API_CONTROLLER = "/batch-cards/api-store";
  public static final String BATCH_CARDS_API_N02_CONTROLLER = "/batch-cards/api-store-n02";

  public static final String BATCH_CARDS_LIST = BATCH_CARDS_CONTROLLER + "/list";
  public static final String BATCH_CARDS_API_LIST = BATCH_CARDS_API_CONTROLLER + "/list";
  public static final String BATCH_CARDS_API_N02_LIST = BATCH_CARDS_API_N02_CONTROLLER + "/list";

  private static final Logger LOGGER = LogManager.getLogger(BatchCardsController.class);
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

  private static String MESSAGE_AVAILABLE = "service.exportcard.card.available";
  private static String MESSAGE_NOT_AVAILABLE = "service.exportcard.card.notavailable";
  private static String MESSAGE_NOT_ENOUGH = "service.exportcard.card.notenough";
  private static String MESSAGE_BUY_MPO_NOT_ENOUGH = "alert.controller.merchant-po.buy-mpo.notenough";
  private static String MESSAGE_NOT_ID = "alert.controller.merchant-po.not-id";

  private String codeErro;
  private MessageNotify messageNotify;

  private final String CODE_ERRO_RQP = "codeErro";
  private final String MESSAGE_ERRO_RQP = "mesErr";

  public static int NEAR_EXP_DAYS = 15;

  protected String batchCardList(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, String dashboardType) throws Exception {
    try {
      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);

      long balanceCurrentUser = userLogin.getBalance();

      Long balanceConfiguration = mainBalance();

      if (balanceCurrentUser < balanceConfiguration) {
        map.put("checkMerchantExportCard", false);
      } else {
        map.put("checkMerchantExportCard", true);
      }
      map.put("minBalance", NumberUtil.formatNumber(balanceConfiguration));
      map.put("dashboardType", dashboardType);

      try {
        GetSpecialCustomerResponse specialMerchant = epinEndpoint.getSpecialMerchant();
        GetSpecialCustomerResponse specialMerchantOffline = epinEndpoint.getSpecialMerchantOffline();
        GetSpecialCustomerResponse specialMerchantN02 = epinEndpoint.getSpecialMerchantN02();

        userLogin.setSpecialCustomerInfo(specialMerchant.getSpecialCustomer());
        userLogin.setSpecialCustomerInfoOffline(specialMerchantOffline.getSpecialCustomer());
        userLogin.setSpecialCustomerInfoN02(specialMerchantN02.getSpecialCustomer());
      } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
      }

      return "/batch_cards/list";
    } catch (Exception e) {
      map.put("dashboardType", dashboardType);
      LOGGER.error(e.getMessage());

      return "/batch_cards/list";
    }
  }

  protected String epinPoDetail(HttpServletRequest request, ModelMap map, String dashboardType)
      throws Exception {

    String poCode = StringUtils.trimToEmpty(request.getParameter("poCode"));
    Long poMerchantId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));

    if (poMerchantId > 0 || poCode.length() > 0) {
      GetEpinPurchaseOrderRequest epinRequest = new GetEpinPurchaseOrderRequest(poMerchantId,
          poCode, true);

      GetEpinPurchaseOrderResponse baseResponseType = epinEndpoint
          .getEpinPO(epinRequest);

      if (baseResponseType == null || baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }

      map.put("merchantPO", baseResponseType.getPurchaseOrder());
      map.put("poMerchantId", poMerchantId);
      map.put("dashboardType", dashboardType);
    } else {
      return "redirect:" + BATCH_CARDS_LIST;
    }

    return "/batch_cards/mpo_detail_page";
  }

  /**
   * redirect page, create MPO
   */
  protected String requestPO(ModelMap map, String dashboardType) throws FrontEndException {

    try {
      // Find enable Service
      super.findEnabledServices(map,
          SharedConstants.BATCH_CARD_AVAILABLE_SERVICE, ServiceType.EXPORT_EPIN.name());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    map.put("dashboardType", dashboardType);

    return "/batch_cards/mpo_stage_request";
  }
  
  protected String requestSubmitMPOCommon(
      HttpServletRequest request, ModelMap map, String dashboardType) throws Exception {
    long totalQuantity = 0L;
    long totalMoney = 0L;
    long balance = 0L;
    long totalDiscount = 0L;
    long totalFeeAmount = 0L;

//    String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
//    String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);

    Integer totalTelco = NumberUtil.convertToInt(request.getParameter("totalTelco"));
    List<EpinPurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
    List<EpinPurchaseOrderDetailTmp> listPOdetailTmp = new ArrayList<>();

//    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
//    String payerCif= userLogin.getCustomerCif();
//    GetAvailableCardRequest cardReq = new GetAvailableCardRequest(null,payerCif);
//    GetAvailableCardResponse baseResponseType = epinEndpoint
//        .getAvailableCard(cardReq);

//    GetAvailableCardResponse baseResponseType = epinEndpoint
//        .getAvailableCard(new GetAvailableCardRequest());

    Boolean availableStatus = true;
    for (int i = 1; i < totalTelco + 1; i++) {
      EpinPurchaseOrderDetail epinPOrderDetail = new EpinPurchaseOrderDetail();
      EpinPurchaseOrderDetailTmp epinPOrderDetailTmp = new EpinPurchaseOrderDetailTmp();

      String cardType = request.getParameter("telco_" + i);
      Integer faceValue = NumberUtil.convertToInt(request.getParameter("value_" + i));
      Integer quantity = NumberUtil.convertToInt(request.getParameter("quantity_" + i));
      String status = request.getParameter("status_" + i);

      epinPOrderDetail.setCardType(cardType);
      epinPOrderDetail.setFaceValue(faceValue);
      epinPOrderDetail.setQuantity(quantity);

      epinPOrderDetailTmp.setCardType(cardType);
      epinPOrderDetailTmp.setFaceValue(faceValue);
      epinPOrderDetailTmp.setQuantity(quantity);

//      if (messageNotAvaliable.equals(status)) {
//        availableStatus = false;
//      } else {
//        Map<Integer, Long> mcardType = baseResponseType.getAvailableCard().get(cardType);
//        Long quantityCard = mcardType.get(faceValue);
//        if (quantity > quantityCard) {
//          availableStatus = false;
//          status = messageNotEnough;
//        }
//      }

      epinPOrderDetailTmp.setStatus(status);

      totalQuantity += quantity;
      totalMoney += (long) quantity * faceValue;
      purchaseOrderDetails.add(epinPOrderDetail);
      listPOdetailTmp.add(epinPOrderDetailTmp);
    }

    CheckEpinPurchaseOrderRequest checkEpinRequest = new CheckEpinPurchaseOrderRequest();
    checkEpinRequest.setPurchaseOrderDetails(purchaseOrderDetails);
    CheckEpinPurchaseOrderResponse checkEpinBaseResponse = epinEndpoint
        .checkEpinPurchaseOrder(checkEpinRequest);
    if (checkEpinBaseResponse == null || checkEpinBaseResponse.getStatus().getCode() != 0) {
      throw new Exception(checkEpinBaseResponse.getStatus().getValue());
    }

//    if (checkEpinResponse != null) {
    balance = checkEpinBaseResponse.getBalance();
    totalDiscount = checkEpinBaseResponse.getTotalDiscountAmount();
    totalFeeAmount = checkEpinBaseResponse.getTotalFeeAmount();
//    }

    EpinPurchaseOrderDetailTmp.order(listPOdetailTmp);

    map.put("totalQuantity", totalQuantity);
    map.put("totalMoney", totalMoney);
    map.put("balance", balance);
    map.put("totalCommmision", totalDiscount);
    map.put("totalPayable", totalMoney + totalFeeAmount - totalDiscount);
    map.put("totalFeeAmount", totalFeeAmount);
    map.put("currentpay", balance - totalMoney);
    map.put("listPOdetail", listPOdetailTmp);
    map.put("pageRequest", "create");

    Long balanceConfiguration = mainBalance();
    //old: totalMoney + totalFeeAmount - totalDiscount - balance < 0
//    if (totalMoney + totalFeeAmount - balance < 0
//        && balance > balanceConfiguration) {
//      map.put("disabledNext", "");
//    } else {
//      map.put("disabledNext", "disabled");
//      map.put("codeErr", 1);
//      map.put("mesErr", validation.notify(MESSAGE_BUY_MPO_NOT_ENOUGH));
//    }
    map.put("disabledNext", "");
    map.put("dashboardType", dashboardType);
//    if (!availableStatus) {
//      map.put("disabledNext", "disabled");
//    }

    return "/batch_cards/mpo_stage_request_checkorder";
  }

  /**
   * Redirect page, edit epo
   */
  public String editPO(HttpServletRequest request, ModelMap map, String dashboardType)
      throws Exception {
// Find enable Service
    super.findEnabledServices(map,
        SharedConstants.BATCH_CARD_AVAILABLE_SERVICE, ServiceType.EXPORT_EPIN.name());

    Long epinId = NumberUtil.convertToLong(request.getParameter("id"));
    if (epinId == 0) {
      epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    }

    if (epinId > 0) {
      GetEpinPurchaseOrderRequest epoRequest = new GetEpinPurchaseOrderRequest(epinId);
      GetEpinPurchaseOrderResponse epoResponse = null;
      try {
        epoResponse = epinEndpoint.getEpinPO(epoRequest);
        if (epoResponse == null || epoResponse.getStatus().getCode() != 0) {
          throw new Exception(epoResponse.getStatus().getValue());
        }

        List<EpinPurchaseOrderDetail> epinDetails = epoResponse.getPurchaseOrder()
            .getPurchaseOrderDetails();
        List<EpinPurchaseOrderDetailTmp> epinDetailTmps = sumaryEpinInfo(epinDetails);

        map.put("purchaseOrder", epoResponse.getPurchaseOrder());
        map.put("purchaseOrderDetails", epinDetailTmps);
        map.put("poMerchantId", epinId);
        map.put("dashboardType", dashboardType);

        return "/batch_cards/edit_po";
      } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
      }
    }

    return "redirect:list";
  }

  protected String editConfirmPO(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, String dashboardType) throws Exception {

//    String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
//    String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);

    Long epinId = NumberUtil.convertToLong(request.getParameter("id"));
    if (epinId == 0) {
      epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    }
    if (epinId > 0) {
      long totalQuantity = 0L;
      long totalMoney = 0L;
      long balance = 0L;
      long totalDiscount = 0L;
      long totalFeeAmount = 0L;

      Boolean availableStatus = true;

      Integer totalTelco = NumberUtil.convertToInt(request.getParameter("totalTelco"));
      List<EpinPurchaseOrderDetail> epinDetails = new ArrayList<>();
      List<EpinPurchaseOrderDetailTmp> epinDetailTmps = new ArrayList<>();

      for (int i = 1; i < totalTelco + 1; i++) {
        EpinPurchaseOrderDetail epinDetail = new EpinPurchaseOrderDetail();
        EpinPurchaseOrderDetailTmp epinDetailTmp = new EpinPurchaseOrderDetailTmp();

        String cardType = request.getParameter("telco_" + i);
        Integer faceValue = NumberUtil.convertToInt(request.getParameter("value_" + i));
        Integer quantity = NumberUtil.convertToInt(request.getParameter("quantity_" + i));
        Long amount = NumberUtil.convertToLong(request.getParameter("amount_" + i));
        String status = request.getParameter("status_" + i);

        epinDetail.setCardType(cardType);
        epinDetail.setFaceValue(faceValue);
        epinDetail.setQuantity(quantity);
        epinDetail.setAmount(amount);

        epinDetailTmp.setCardType(cardType);
        epinDetailTmp.setFaceValue(faceValue);
        epinDetailTmp.setQuantity(quantity);

//        if (messageNotAvaliable.equals(status)) {
//          availableStatus = false;
//        } else {
          UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
          String payerCif= userLogin.getCustomerCif();
          GetAvailableCardRequest cardReq = new GetAvailableCardRequest(null,payerCif);
          GetAvailableCardResponse baseResponseType = epinEndpoint
              .getAvailableCard(cardReq);

//          GetAvailableCardResponse baseResponseType = epinEndpoint
//              .getAvailableCard(new GetAvailableCardRequest());

//          Map<Integer, Long> mcardType = baseResponseType.getAvailableCard()
//              .get(cardType);
//          Long quantityCard = mcardType.get(faceValue);
//          if (quantity > quantityCard) {
//            availableStatus = false;
//            status = messageNotEnough;
//          }
//        }

        epinDetailTmp.setStatus(status);

        totalQuantity += quantity;
        totalMoney += (long) quantity * faceValue;
        epinDetails.add(epinDetail);
        epinDetailTmps.add(epinDetailTmp);
      }

      CheckEpinPurchaseOrderRequest checkEpinRequest = new CheckEpinPurchaseOrderRequest();
      checkEpinRequest.setPurchaseOrderDetails(epinDetails);
      CheckEpinPurchaseOrderResponse checkEpinResponse = epinEndpoint
          .checkEpinPurchaseOrder(checkEpinRequest);
      if (checkEpinResponse == null || checkEpinResponse.getStatus().getCode() != 0) {
        throw new Exception(checkEpinResponse.getStatus().getValue());
      }
      balance = checkEpinResponse.getBalance();
      totalDiscount = checkEpinResponse.getTotalDiscountAmount();
      totalFeeAmount = checkEpinResponse.getTotalFeeAmount();

      EpinPurchaseOrderDetailTmp.order(epinDetailTmps);

      //param of sumary page
      map.put("totalQuantity", totalQuantity);
      map.put("totalMoney", totalMoney);
      map.put("balance", balance);
      map.put("totalCommmision", totalDiscount);
      map.put("totalPayable", totalMoney + totalFeeAmount - totalDiscount);
      map.put("totalFeeAmount", totalFeeAmount);
      map.put("currentpay", balance - totalMoney);
      map.put("listPOdetail", epinDetailTmps);
      map.put("pageRequest", "edit");
      map.put("poMerchantId", epinId);
      map.put("poCode", request.getParameter("poCode"));
//      if (totalMoney + totalFeeAmount - totalDiscount - balance < 0) {
//        map.put("disabledNext", "");
//      } else {
//        map.put("disabledNext", "disabled");
//        map.put("codeErr", 1);
//        map.put("mesErr", validation.notify(MESSAGE_BUY_MPO_NOT_ENOUGH));
//      }
      map.put("disabledNext", "");
      map.put("dashboardType", dashboardType);
//      if (!availableStatus) {
//        map.put("disabledNext", "disabled");
//      }

      return "/batch_cards/mpo_stage_request_checkorder";
    } else {

      return "redirect:list";
    }
  }

  protected String epinCheckOrder(HttpServletRequest request, ModelMap map, String dashboardType)
      throws Exception {

    Long epinId = NumberUtil.convertToLong(request.getParameter("epinId"));
    if (epinId > 0) {

      GetEpinPurchaseOrderRequest epoRequest = new GetEpinPurchaseOrderRequest(epinId, true);
      GetEpinPurchaseOrderResponse baseResponseType = epinEndpoint
          .getEpinPO(epoRequest);
      if (baseResponseType == null || baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }

      List<EpinPurchaseOrderDetail> epinDetails = baseResponseType.getPurchaseOrder()
          .getPurchaseOrderDetails();
      List<EpinPurchaseOrderDetailTmp> epinDetailTmps = sumaryEpinInfo(epinDetails);
      EpinPurchaseOrder epinPo = baseResponseType.getPurchaseOrder();

      long totalMoney = epinPo.getTotalValue();
      long totalDiscount = epinPo.getTotalCommission();
      long totalFeeAmount = epinPo.getTotalFee();

      map.put("purchaseOrder", epinPo);
      map.put("totalQuantity", NumberUtil.formatNumber(epinPo.getTotalQuantity()));
      map.put("totalMoney", NumberUtil.formatNumber(totalMoney));
      map.put("totalCommmision", NumberUtil.formatNumber(epinPo.getTotalCommission()));
      map.put("totalPayable",
          NumberUtil.formatNumber(totalMoney + totalFeeAmount - totalDiscount));
      map.put("totalFeeAmount", NumberUtil.formatNumber(totalFeeAmount));

      map.put("poCode", epinPo.getPoCode());
      map.put("listPOdetail", epinDetailTmps);

      map.put("pageRequest", "edit");
      map.put("poMerchantId", epinId);
      map.put("dashboardType", dashboardType);

      return "/batch_cards/mpo_stage_request_checkorder";
    }

    return "redirect:list";
  }

  protected String epinCheckorderPost(HttpServletRequest request, ModelMap map, String dashboardType)
      throws Exception {

    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    String keyHolder = userLogin.getPhoneNumber();

    String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
    String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);

    Long totalQuantity = 0L;
    Long totalMoney = 0L;
    Integer totalTelco = NumberUtil.convertToInt(request.getParameter("totalTelco"));
    String buttonAction = request.getParameter("action");

    List<EpinPurchaseOrderDetail> epinDetails = new ArrayList<>();
    List<EpinPurchaseOrderDetailTmp> epinDetailTmps = new ArrayList<>();

    Boolean availableStatus = true;

    for (int i = 1; i < totalTelco + 1; i++) {
      EpinPurchaseOrderDetail epinDetail = new EpinPurchaseOrderDetail();
      EpinPurchaseOrderDetailTmp epinDetailTmp = new EpinPurchaseOrderDetailTmp();

      String cardType = request.getParameter("telco_" + i);
      Integer faceValue = NumberUtil.convertToInt(request.getParameter("value_" + i));
      Integer quantity = NumberUtil.convertToInt(request.getParameter("quantity_" + i));
      String status = request.getParameter("status_" + i);

      epinDetailTmp.setCardType(cardType);
      epinDetailTmp.setFaceValue(faceValue);
      epinDetailTmp.setQuantity(quantity);

      if (messageNotAvaliable.equals(status) || messageNotEnough.equals(status)) {
        availableStatus = false;
      }

      epinDetailTmp.setStatus(status);
      epinDetailTmps.add(epinDetailTmp);

      epinDetail.setCardType(cardType);
      epinDetail.setFaceValue(faceValue);
      epinDetail.setQuantity(quantity);
      epinDetails.add(epinDetail);

      totalQuantity += quantity;
      totalMoney += (long) quantity * faceValue;
    }

    String pageRequest = request.getParameter("pageRequest");
    if (StringUtils.isEmpty(pageRequest)) {
      return "redirect:list";
    }

    if (pageRequest.equals("create")) {
      EpinPurchaseOrder epin = new EpinPurchaseOrder(epinDetails, totalMoney, totalQuantity,
          keyHolder);
      String storeType = null;
      if (DASHBOARD_TYPE_API.equals(dashboardType)) {
        storeType = StoreType.ONLINE_STORE.name();
      } else if (DASHBOARD_TYPE_EXPORT.equals(dashboardType)) {
        storeType = StoreType.OFFLINE_STORE.name();
      }
      epin.setStoreType(storeType);
      CreateEpinPurchaseOrderRequest cEpinRequest = new CreateEpinPurchaseOrderRequest(epin);

      if (buttonAction.equals("next")) {
        cEpinRequest.setAction(CreateEpinPurchaseOrderRequest.Action.NEXT);
      } else {
        cEpinRequest.setAction(CreateEpinPurchaseOrderRequest.Action.SAVE);
      }
      CreateEpinPurchaseOrderResponse cEpinResponse = epinEndpoint
          .createEpinPO(cEpinRequest);
      int responseCreateCode = cEpinResponse.getStatus().getCode();
      map.put("mPoCreate", cEpinRequest);

      if (responseCreateCode == 0) {
        if (buttonAction.equals("next")) {
          messageNotify = new MessageNotify(SUCCESS_CODE,
              MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
        } else {
          messageNotify = new MessageNotify(SUCCESS_CODE, MESAGE_SUCCESS);
        }
        SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
        return "redirect:list";
      } else {
        map.put("codeErr", responseCreateCode);
        map.put("mesErr", validation
            .notify("error.code." + responseCreateCode, cEpinResponse.getStatus().getValue()));

        long balance = 0L;
        long totalDiscount = 0L;
        long totalFeeAmount = 0L;

        CheckEpinPurchaseOrderRequest checkEpinRequest = new CheckEpinPurchaseOrderRequest();
        checkEpinRequest.setPurchaseOrderDetails(epinDetails);
        CheckEpinPurchaseOrderResponse checkEpinPurchaseOrderResponse = epinEndpoint
            .checkEpinPurchaseOrder(checkEpinRequest);
        if (checkEpinPurchaseOrderResponse == null
            || checkEpinPurchaseOrderResponse.getStatus().getCode() != 0) {
          throw new Exception(checkEpinPurchaseOrderResponse.getStatus().getValue());
        }
        balance = checkEpinPurchaseOrderResponse.getBalance();
        totalDiscount = checkEpinPurchaseOrderResponse.getTotalDiscountAmount();
        totalFeeAmount = checkEpinPurchaseOrderResponse.getTotalFeeAmount();

        map.put("totalQuantity", totalQuantity);
        map.put("totalMoney", totalMoney);
        map.put("balance", balance);
        map.put("totalCommmision", totalDiscount);
        map.put("totalPayable", totalMoney + totalFeeAmount - totalDiscount);
        map.put("totalFeeAmount", totalFeeAmount);
        map.put("currentpay", balance - totalMoney);
        map.put("listPOdetail", epinDetailTmps);
        map.put("pageRequest", "create");
        map.put("dashboardType", dashboardType);

        if (!availableStatus) {
          map.put("disabledNext", "disabled");
        }

        return "/batch_cards/mpo_stage_request_checkorder";
      }

    } else if (pageRequest.equals("edit")) {
      long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));

      EpinPurchaseOrder epin = new EpinPurchaseOrder(epinId, totalMoney, totalQuantity,
          epinDetails);
      String storeType = null;
      if (DASHBOARD_TYPE_API.equals(dashboardType)) {
        storeType = StoreType.ONLINE_STORE.name();
      } else if (DASHBOARD_TYPE_EXPORT.equals(dashboardType)) {
        storeType = StoreType.OFFLINE_STORE.name();
      }
      epin.setStoreType(storeType);
      UpdateEpinPurchaseOrderRequest updateEpinRequest = new UpdateEpinPurchaseOrderRequest(epin);

      if (buttonAction.equals("next")) {
        updateEpinRequest.setAction(UpdateEpinPurchaseOrderRequest.Action.NEXT);
      } else {
        updateEpinRequest.setAction(UpdateEpinPurchaseOrderRequest.Action.SAVE);
      }
      UpdateEpinPurchaseOrderResponse updateEpinResponse = epinEndpoint
          .updateEpinPO(updateEpinRequest);
      map.put("mPoCreate", updateEpinRequest);

      int responseCode = updateEpinResponse.getStatus().getCode();

      if (responseCode == 0) {
        if (buttonAction.equals("next")) {
          messageNotify = new MessageNotify(SUCCESS_CODE,
              MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
        } else {
          messageNotify = new MessageNotify(SUCCESS_CODE, MESAGE_SUCCESS);
        }
        SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
        return "redirect:list";

      } else {
        map.put("codeErr", responseCode);
        map.put("mesErr", validation
            .notify("error.code." + responseCode, updateEpinResponse.getStatus().getValue()));

        long balance = 0L;
        long totalDiscount = 0L;
        long totalFeeAmount = 0L;

        CheckEpinPurchaseOrderRequest checkEpinReq = new CheckEpinPurchaseOrderRequest();
        checkEpinReq.setPurchaseOrderDetails(epinDetails);
        CheckEpinPurchaseOrderResponse checkEpinRes = epinEndpoint
            .checkEpinPurchaseOrder(checkEpinReq);
        if (checkEpinRes == null || checkEpinRes.getStatus().getCode() != 0) {
          throw new Exception(checkEpinRes.getStatus().getValue());
        }

        balance = checkEpinRes.getBalance();
        totalDiscount = checkEpinRes.getTotalDiscountAmount();
        totalFeeAmount = checkEpinRes.getTotalFeeAmount();

        //param of sumary page
        map.put("totalQuantity", totalQuantity);
        map.put("totalMoney", totalMoney);
        map.put("balance", balance);
        map.put("totalCommmision", totalDiscount);
        map.put("totalPayable", (totalMoney + totalFeeAmount - totalDiscount));
        map.put("totalFeeAmount", totalFeeAmount);
        map.put("currentpay", balance - totalMoney);
        map.put("listPOdetail", epinDetailTmps);
        map.put("pageRequest", "edit");
        map.put("poMerchantId", epinId);
        map.put("poCode", request.getParameter("poCode"));
        map.put("dashboardType", dashboardType);

        if (!availableStatus) {
          map.put("disabledNext", "disabled");
        }

        return "/batch_cards/mpo_stage_request_checkorder";
      }
    }
    return "redirect:list";
  }

  protected String epinExportAllowed(HttpServletRequest request, ModelMap model, String dashboardType)
      throws Exception {

    Long epinId = NumberUtil.convertToLong(request.getParameter("epinId"));
    if (epinId > 0) {

      GetEpinPurchaseOrderRequest ePinRequest = new GetEpinPurchaseOrderRequest(epinId, true);
      GetEpinPurchaseOrderResponse ePinResponse = epinEndpoint
          .getEpinPO(ePinRequest);

      if (ePinResponse == null || ePinResponse.getStatus().getCode() != 0) {
        throw new Exception(ePinResponse.getStatus().getValue());
      }
      EpinPurchaseOrder epin = ePinResponse.getPurchaseOrder();
      if (epin != null && epin.getStage()
          .equals(EpinPurchaseOrderFlowStage.EPIN_EXPORT_ALLOWED.code)) {

        List<EpinPurchaseOrderDetail> epinDetails = epin.getPurchaseOrderDetails();
        List<EpinPurchaseOrderDetailTmp> epinDetailTmps = sumaryEpinInfo(epinDetails);

        boolean checkEpinAvaliable = checkEpinAvaliable(epinDetailTmps);

        long balance = 0L;
        long totalDiscount = 0L;
        long totalFeeAmount = 0L;
        long totalMoney = epin.getTotalValue();

        CheckEpinPurchaseOrderRequest checkEpinReq = new CheckEpinPurchaseOrderRequest();
        checkEpinReq.setPurchaseOrderDetails(epinDetails);
        CheckEpinPurchaseOrderResponse checkEpinRes = epinEndpoint
            .checkEpinPurchaseOrder(checkEpinReq);
        if (checkEpinRes == null || checkEpinRes.getStatus().getCode() != 0) {
          throw new Exception(checkEpinRes.getStatus().getValue());
        }

        balance = checkEpinRes.getBalance();
        totalDiscount = checkEpinRes.getTotalDiscountAmount();
        totalFeeAmount = checkEpinRes.getTotalFeeAmount();

        model.put("totalQuantity", NumberUtil.formatNumber(epin.getTotalQuantity()));
        model.put("totalMoney", NumberUtil.formatNumber(totalMoney) + " VND");
        model.put("balance", NumberUtil.formatNumber(balance) + " VND");
        model.put("totalCommmision", NumberUtil.formatNumber(totalDiscount) + " VND");
        model.put("totalPayable",
            NumberUtil.formatNumber(totalMoney + totalFeeAmount - totalDiscount) + " VND");
        model.put("totalFeeAmount", totalFeeAmount + " VND");
        model.put("currentpay", balance - totalMoney);
        model.put("listPOdetail", epinDetailTmps);
        model.put("pageRequest", "create");

        model.put("epinId", epinId);

        Long balanceConfiguration = mainBalance();
        if (totalMoney + totalFeeAmount - balance < 0
            && balance > balanceConfiguration) {
          model.put("disabledNext", "");
        } else {
          model.put("disabledNext", "disabled");
          model.put("codeErr", 1);
          model.put("mesErr", validation.notify(MESSAGE_BUY_MPO_NOT_ENOUGH));
        }

        if (!checkEpinAvaliable) {
          model.put("disabledNext", "disabled");
        }
        model.put("dashboardType", dashboardType);
      }
      return "/batch_cards/mpo_stage_export";
    }

    return "redirect:list";
  }

  protected String epinExportAllowedPost(HttpServletRequest request, ModelMap model, String dashboardType)
      throws Exception {

    Long epinId = NumberUtil.convertToLong(request.getParameter("epinId"));
    String action = request.getParameter("action");
    if (epinId > 0) {
      GetEpinPurchaseOrderRequest epinRequest = new GetEpinPurchaseOrderRequest(epinId, false);
      GetEpinPurchaseOrderResponse epinResponse = epinEndpoint
          .getEpinPO(epinRequest);
      if (epinResponse == null || epinResponse.getStatus().getCode() != 0) {
        throw new Exception(epinResponse.getStatus().getValue());
      }
      EpinPurchaseOrder epin = epinResponse.getPurchaseOrder();
      if (epin != null && epin.getStage()
          .equals(EpinPurchaseOrderFlowStage.EPIN_EXPORT_ALLOWED.code)) {
        if (BTN_EXPORT.equals(action)) {

          GetEpinPurchaseOrderOTPConfirmRequest geoRequest = new GetEpinPurchaseOrderOTPConfirmRequest();
          geoRequest.setOrderId(epinId);
          GetEpinPurchaseOrderOTPConfirmResponse gepResonse = epinEndpoint
              .getOTPConfirm(geoRequest);
          if (gepResonse.getStatus().getCode() == 0) {
            model.put("id", epinId);
            return "redirect:request-otp";
          }
        } else if (BTN_REJECT.equals(action)) {

          OrderFlowRejectRequest ofrRequest = new OrderFlowRejectRequest(epinId);
          OrderFlowRejectResponse ofrResponse = epinEndpoint
              .epinPurchaseOrderWorkflowReject(ofrRequest);
          if (ofrResponse.getStatus().getCode() == 0) {
            messageNotify = new MessageNotify(SUCCESS_CODE,
                MESAGE_ORDER_FLOW_REJECT_PROCESS_SUCCESS);
          } else {
            messageNotify = new MessageNotify(ERROR_CODE, ofrResponse.getStatus().getValue());
          }
          SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
        }
      }
    }

    return "redirect:list";
  }

  protected String requestOTP() throws Exception {

    return "/batch_cards/mpo_stage_export_otp";
  }

  protected String requestOTPsubmit(HttpServletRequest request, ModelMap model) throws Exception {
    String otp = request.getParameter("otp");
    Long epinId = NumberUtil.convertToLong(request.getParameter("id"));

    if (StringUtils.isNotBlank(otp) && epinId > 0) {
      BuyCardOfflineConfirmRequest confirmReq = new BuyCardOfflineConfirmRequest();
      confirmReq.setOrderId(epinId);
      confirmReq.setOtp(otp);
      BuyCardOfflineConfirmResponse confirmRes = epinEndpoint
          .buyCardOfflineConfirm(confirmReq);
      if (confirmRes.getStatus().getCode() == 0) {
        model.put("id", epinId);
        return "redirect:" + BATCH_CARDS_CONTROLLER + "/export-epo";
      } else {
        model.put("codeErr", confirmRes.getStatus().getCode());
        model.put("mesErr", confirmRes.getStatus().getValue());
        model.put("id", epinId);
        return "/batch_cards/mpo_stage_export_otp";
      }
    }

    return "/batch_cards/mpo_stage_export_otp";
  }

  protected String epinApprove(HttpServletRequest request, ModelMap model) throws Exception {
    Long epinId = NumberUtil.convertToLong(request.getParameter("epinId"));
    if (epinId > 0) {

      GetEpinPurchaseOrderRequest gEpinRequest = new GetEpinPurchaseOrderRequest(epinId, true);
      GetEpinPurchaseOrderResponse gEpinResponse = epinEndpoint
          .getEpinPO(gEpinRequest);

      if (gEpinResponse == null || gEpinResponse.getStatus().getCode() != 0) {
        throw new Exception(gEpinResponse.getStatus().getValue());
      }
      EpinPurchaseOrder ePin = gEpinResponse.getPurchaseOrder();

      if (ePin != null && ePin.getStage()
          .equals(EpinPurchaseOrderFlowStage.SALESUPPORT_READY_TO_VERIFY.code)) {

        List<EpinPurchaseOrderDetail> epinDetails = ePin.getPurchaseOrderDetails();
        List<EpinPurchaseOrderDetailTmp> epinDetailTmps = sumaryEpinInfo(epinDetails);

        long totalDiscount = 0L;
        long totalFeeAmount = 0L;
        long totalMoney = ePin.getTotalValue();

        CheckEpinPurchaseOrderRequest checkEpinReq = new CheckEpinPurchaseOrderRequest();
        checkEpinReq.setPurchaseOrderDetails(epinDetails);
        CheckEpinPurchaseOrderResponse checkEpinRes = epinEndpoint
            .checkEpinPurchaseOrder(checkEpinReq);
        if (checkEpinRes.getStatus().getCode() != 0) {
          throw new Exception(checkEpinRes.getStatus().getValue());
        }

        totalDiscount = checkEpinRes.getTotalDiscountAmount();
        totalFeeAmount = checkEpinRes.getTotalFeeAmount();

        model.put("totalQuantity", NumberUtil.formatNumber(ePin.getTotalQuantity()));
        model.put("totalMoney", NumberUtil.formatNumber(totalMoney) + " VND");
        model.put("totalCommmision", NumberUtil.formatNumber(ePin.getTotalCommission()) + " VND");
        model.put("totalPayable",
            NumberUtil.formatNumber(totalMoney + totalFeeAmount - totalDiscount) + " VND");
        model.put("totalFeeAmount", totalFeeAmount + " VND");
        model.put("listPOdetail", epinDetailTmps);
        model.put("epinId", epinId);

        return "/batch_cards/mpo_stage_approve";
      }
    }

    return "redirect:" + BATCH_CARDS_LIST;
  }

  protected String epinApprovePost(HttpServletRequest request, ModelMap model) throws Exception {

    Long epinId = NumberUtil.convertToLong(request.getParameter("epinId"));
    String remark = request.getParameter("remark");
    String action = request.getParameter("action");
    if (epinId > 0) {

      if (BTN_APPROVE.equals(action)) {

        OrderFlowApproveRequest ofaRequest = new OrderFlowApproveRequest(epinId, remark);
        OrderFlowApproveResponse ofaResponse = epinEndpoint
            .epinPurchaseOrderWorkflowApporve(ofaRequest);

        if (ofaResponse.getStatus().getCode() == 0) {
          messageNotify = new MessageNotify(SUCCESS_CODE,
              MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
        } else {
          messageNotify = new MessageNotify(ERROR_CODE, ofaResponse.getStatus().getValue());
        }

      } else if (BTN_REJECT.equals(action)) {

        OrderFlowRejectRequest ofrRrequest = new OrderFlowRejectRequest(epinId, remark);
        OrderFlowRejectResponse ofrResponse = epinEndpoint
            .epinPurchaseOrderWorkflowReject(ofrRrequest);

        if (ofrResponse.getStatus().getCode() == 0) {
          messageNotify = new MessageNotify(SUCCESS_CODE, MESAGE_ORDER_FLOW_REJECT_PROCESS_SUCCESS);
        } else {
          messageNotify = new MessageNotify(ERROR_CODE, ofrResponse.getStatus().getValue());
        }
      }

      SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY, messageNotify);
    }
    return "redirect:" + BATCH_CARDS_LIST;
  }

  protected String updateRequestGetPO(ModelMap model, String dashboardType) throws Exception {
    model.put("dashboardType", dashboardType);

    return "redirect:" + BATCH_CARDS_LIST;
  }

  protected String updateRequestPO
      (HttpServletRequest request, ModelMap model, String dashboardType) throws Exception {

    String pageRequest = request.getParameter("pageRequest");
    if (pageRequest == null || StringUtils.EMPTY.equals(pageRequest)) {
      return "redirect:" + BATCH_CARDS_LIST;
    }

    Long totalMoney = 0L;
    String poCode = request.getParameter("poCode");
    Integer totalTelco = NumberUtil.convertToInt(request.getParameter("totalTelco"));
    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));

    List<EpinPurchaseOrderDetailTmp> epinDetailTmps = new ArrayList<>();
    for (int i = 1; i < totalTelco + 1; i++) {
      EpinPurchaseOrderDetailTmp epinDetailTmp = new EpinPurchaseOrderDetailTmp();

      String cardType = request.getParameter("telco_" + i);
      Integer faceValue = NumberUtil.convertToInt(request.getParameter("value_" + i));
      Integer quantity = NumberUtil.convertToInt(request.getParameter("quantity_" + i));
      Long amount = NumberUtil.convertToLong(request.getParameter("amount_" + i));
      String status = request.getParameter("status_" + i);

      epinDetailTmp.setCardType(cardType);
      epinDetailTmp.setFaceValue(faceValue);
      epinDetailTmp.setQuantity(quantity);
      epinDetailTmp.setAmount(amount);
      epinDetailTmp.setStatus(status);

      totalMoney += faceValue * quantity;
      epinDetailTmps.add(epinDetailTmp);
    }

    EpinPurchaseOrderDetailTmp.order(epinDetailTmps);

    model.put("purchaseOrderDetails", epinDetailTmps);
    model.put("dashboardType", dashboardType);

    if (pageRequest.equals("create")) {
      model.put("totalTelco", totalTelco);
      model.put("totalValue", totalMoney);
      return "/batch_cards/mpo_stage_request";

    } else if (pageRequest.equals("edit")) {

      model.put("poCode", poCode);
      model.put("poMerchantId", epinId);
      model.put("totalTelco", totalTelco);
      model.put("totalValue", totalMoney);
      return "/batch_cards/edit_po";
    }
    return "redirect:" + BATCH_CARDS_LIST;
  }

  protected String exportMPO() throws Exception {

    return "/batch_cards/mpo_stage_export_download";
  }

  /**
   * Tải file về
   */
  protected String exportMPOsubmit(HttpServletRequest request, HttpServletResponse response,
      ModelMap model) throws Exception {

    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));

    if (epinId > 0) {
      GetEpinPurchaseOrderAttachmentRequest attachmentReq = new GetEpinPurchaseOrderAttachmentRequest(
          epinId, Action.GET_FILE);
      GetEpinPurchaseOrderAttachmentResponse attachmentRes = epinEndpoint
          .getMPOAttachment(attachmentReq);

      if (attachmentRes.getStatus().getCode() == 0) {
        EpinPurchaseOrderAttachment attachment = attachmentRes.getAttachment();
        exportAttach(response, attachment);
      } else {
        model.put("codeErr", attachmentRes.getStatus().getCode());
        model.put("mesErr", attachmentRes.getStatus().getValue());
        model.put("id", epinId);
      }
    } else {
      model.put("codeErr", 1);
      model.put("mesErr", validation.notify(MESSAGE_NOT_ID));
      return "/batch_cards/list";
    }
    return "/batch_cards/mpo_stage_export_download";
  }

  protected void exportEpin(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
      if (epinId > 0) {
        GetEpinPurchaseOrderAttachmentRequest atRequest = new GetEpinPurchaseOrderAttachmentRequest(
            epinId, Action.GET_FILE);
        GetEpinPurchaseOrderAttachmentResponse attachmentResponse = epinEndpoint
            .getMPOAttachment(atRequest);
        if (attachmentResponse == null) {
          throw new Exception("No response!");
        } else if (attachmentResponse.getStatus().getCode() != 0) {
          throw new Exception(attachmentResponse.getStatus().getValue());
        }

        if (attachmentResponse.getAttachment() != null) {
          exportAttach(response, attachmentResponse.getAttachment());
        }
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

  //verify
  protected String getOtpMPO(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {

    String codeErro = request.getParameter(CODE_ERRO_RQP);
    Long epinId = null;
    GetEpinPurchaseOrderOTPConfirmResponse baseResponseType;
    try {
      epinId = NumberUtil.convertToLong(request.getParameter("id"));
      if (StringUtils.isBlank(codeErro)) {
        GetEpinPurchaseOrderOTPConfirmRequest geoRequest = new GetEpinPurchaseOrderOTPConfirmRequest();
        geoRequest.setOrderId(epinId);
        baseResponseType = epinEndpoint.getOTPConfirm(geoRequest);
        if (baseResponseType == null) {
          throw new Exception("No response!");
        } else if (baseResponseType.getStatus().getCode() != 0) {
          throw new Exception(baseResponseType.getStatus().getValue());
        }
      }

      this.codeErro = codeErro;
      map.put(CODE_ERRO_RQP, codeErro);
      map.put("poMerchantId", epinId);

      return "/batch_cards/mpo_stage_export_otp";
    } catch (Exception e) {
      LOGGER.error(e.getMessage());

      this.codeErro = e.getMessage();
      map.put(CODE_ERRO_RQP, this.codeErro);
      map.put("poMerchantId", epinId);

      return "/batch_cards/mpo_stage_export_error";
    }
  }

  //confirm
  protected String postOtpMPO(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    codeErro = request.getParameter(CODE_ERRO_RQP);

    Long epinId = NumberUtil.convertToLong(request.getParameter("id"));

    EpinPurchaseOrderSummaryTmp epinSummaryTmp = getSumary(request);

    if (epinSummaryTmp != null) {
      if (epinSummaryTmp.getBalance() < (epinSummaryTmp.getTotalMoney() - epinSummaryTmp.getTotalDiscountAmount()) ) {
        codeErro = validation.notify(MESSAGE_BUY_MPO_NOT_ENOUGH);
        map.put(CODE_ERRO_RQP, codeErro);
        map.put("poMerchantId", epinId);
        return "redirect:/batch-cards/get-otp?id=" + epinId;
      }
    }

    try {
      // Check Customer's attribute before confirm
      FindCustomerAttributeRequest findCustomerAttributeRequest = new FindCustomerAttributeRequest();
      List<Integer> attributeTypeIds = new ArrayList<>();
      attributeTypeIds.add(CustomerAttributeType.MSISDN_OTP);
      attributeTypeIds.add(CustomerAttributeType.MSISDN_PASSWORD);
      findCustomerAttributeRequest.setCustomerAttributeTypeIds(attributeTypeIds);

      FindCustomerAttributeResponse findCustomerAttributeResponseType = walletEndpoint.findCustomerAttribute(findCustomerAttributeRequest);
      List<CustomerAttribute> customerAttributes = findCustomerAttributeResponseType.getCustomerAttributes();
      if (customerAttributes == null || customerAttributes.size() < 2) {
        codeErro = "Missing phone to receive OTP or file password!";
        map.put(CODE_ERRO_RQP, codeErro);
        return "redirect:/batch-cards/get-otp?id=" + epinId;
      }

      String otp = request.getParameter("codeOTP");
      if (epinId == 0) {
        epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
      }

      if (StringUtils.isNotBlank(otp)) {
        BuyCardOfflineConfirmRequest geoRequest = new BuyCardOfflineConfirmRequest();
        geoRequest.setOrderId(epinId);
        geoRequest.setOtp(otp);
        BuyCardOfflineConfirmResponse buyCardOfflineConfirmResponse = epinEndpoint
            .buyCardOfflineConfirm(geoRequest);
        if (buyCardOfflineConfirmResponse == null) {
          throw new Exception("No response!");
        } else if (buyCardOfflineConfirmResponse.getStatus().getCode() != 0) {
          throw new Exception(buyCardOfflineConfirmResponse.getStatus().getValue());
        }
      }
      map.put("poMerchantId", epinId);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      codeErro = e.getMessage();
      map.put(CODE_ERRO_RQP, codeErro);

      return "redirect:/batch-cards/get-otp?id=" + epinId;
    }
    return "/batch_cards/mpo_stage_export_success";
  }

//  @RequestMapping(value = "/getOtp", method = RequestMethod.POST)
//  public ResponseEntity<?> getOtp(HttpServletRequest request) throws Exception {
//
//    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
//    if (epinId > 0) {
//      GetEpinPurchaseOrderOTPConfirmRequest geoRequest = new GetEpinPurchaseOrderOTPConfirmRequest(
//          epinId);
//      BaseResponseType<GetEpinPurchaseOrderOTPConfirmResponse> gepResonse = epinEndpoint
//          .getOTPConfirm(geoRequest);
//      return ResponseEntity.ok(new AjaxResponse(gepResonse.getStatus().getCode(),
//          gepResonse.getStatus().getValue()));
//    } else {
//      return ResponseEntity.ok(new AjaxResponse(1, validation.notify(MESSAGE_NOT_ID)));
//    }
//  }

  protected ResponseEntity<?> resendPass(HttpServletRequest httpRequest) throws Exception {
    Long epinId = NumberUtil.convertToLong(httpRequest.getParameter("poMerchantId"));
    if (epinId > 0) {
      GetEpinPurchaseOrderAttachmentRequest request = new GetEpinPurchaseOrderAttachmentRequest(
          epinId, Action.GET_PASSWORD);
      GetEpinPurchaseOrderAttachmentResponse response = epinEndpoint
          .getMPOAttachment(request);
      return ResponseEntity
          .ok(new AjaxResponse(response.getStatus().getCode(), response.getStatus().getValue()));
    } else {
      return ResponseEntity.ok(new AjaxResponse(1, validation.notify(MESSAGE_NOT_ID)));
    }
  }

  protected EpinPurchaseOrderSummaryTmp getSumary(HttpServletRequest request) throws Exception {

    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    if (epinId > 0) {
      long totalQuantity = 0L;
      long totalMoney = 0L;

      String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
      String messageAvaliable = validation.notify(MESSAGE_AVAILABLE);
      String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);

      GetEpinPurchaseOrderRequest epinRequest = new GetEpinPurchaseOrderRequest(epinId);
      GetEpinPurchaseOrderResponse epinResponse = epinEndpoint
          .getEpinPO(epinRequest);
      if (epinResponse == null || epinResponse.getStatus().getCode() != 0) {
        throw new Exception(epinResponse.getStatus().getValue());
      }
      List<EpinPurchaseOrderDetail> epinDetails = epinResponse.getPurchaseOrder()
          .getPurchaseOrderDetails();
      List<EpinPurchaseOrderDetailTmp> epinDetailTmps = new ArrayList<>();

      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      String payerCif= userLogin.getCustomerCif();
      GetAvailableCardRequest cardReq = new GetAvailableCardRequest(null,payerCif);
      GetAvailableCardResponse responseAvailableType = epinEndpoint
          .getAvailableCard(cardReq);

//      GetAvailableCardResponse responseAvailableType = epinEndpoint
//          .getAvailableCard(new GetAvailableCardRequest());

      CheckEpinPurchaseOrderRequest checkBalanceReq = new CheckEpinPurchaseOrderRequest();
      checkBalanceReq.setPurchaseOrderDetails(epinDetails);
      CheckEpinPurchaseOrderResponse checkBalanceRes = epinEndpoint
          .checkEpinPurchaseOrder(checkBalanceReq);
      if (checkBalanceRes == null || checkBalanceRes.getStatus().getCode() != 0) {
        throw new Exception(checkBalanceRes.getStatus().getValue());
      }
      for (EpinPurchaseOrderDetail item : epinDetails) {
        EpinPurchaseOrderDetailTmp epinDetailTmp = new EpinPurchaseOrderDetailTmp();
        epinDetailTmp.setCardType(item.getCardType());
        epinDetailTmp.setFaceValue(item.getFaceValue());
        epinDetailTmp.setQuantity(item.getQuantity());

        Long quantity = (long) item.getQuantity();
        totalQuantity += quantity;
        totalMoney += quantity * item.getFaceValue();

        if (responseAvailableType.getAvailableCard() != null) {
          Map<String, Map<Integer, Long>> availableCard = responseAvailableType.getAvailableCard();
          if (availableCard.get(item.getCardType()) != null) {
            Map<Integer, Long> cardType = availableCard.get(item.getCardType());
            if (cardType.get(item.getFaceValue()) != null) {
              Long quantityCard = cardType.get(item.getFaceValue());
              if (quantityCard >= quantity) {
                epinDetailTmp.setStatus(messageAvaliable);
              } else {
                epinDetailTmp.setStatus(messageNotEnough);
              }
            } else {
              epinDetailTmp.setStatus(messageNotAvaliable);
            }
          } else {
            epinDetailTmp.setStatus(messageNotAvaliable);
          }
        } else {
          epinDetailTmp.setStatus(messageNotAvaliable);
        }

        epinDetailTmps.add(epinDetailTmp);
      }

      EpinPurchaseOrderDetailTmp.order(epinDetailTmps);

      EpinPurchaseOrderSummaryTmp epinSummaryTmp = new EpinPurchaseOrderSummaryTmp();
      epinSummaryTmp.setPurchaseOrderDetails(epinDetailTmps);
      epinSummaryTmp.setBalance(checkBalanceRes.getBalance());
      epinSummaryTmp.setTotalDiscountAmount(checkBalanceRes.getTotalDiscountAmount());
      epinSummaryTmp.setTotalFeeAmount(checkBalanceRes.getTotalFeeAmount());
      epinSummaryTmp.setTotalQuantity(totalQuantity);
      epinSummaryTmp.setTotalMoney(totalMoney);

      return epinSummaryTmp;
    } else {
      return null;
    }
  }

  protected GetEpinPurchaseOrderResponse getDetail(HttpServletRequest request) throws Exception {
    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    if (epinId > 0) {
      GetEpinPurchaseOrderRequest req = new GetEpinPurchaseOrderRequest(epinId);
      GetEpinPurchaseOrderResponse baseResponseType = epinEndpoint.getEpinPO(req);
      if (baseResponseType == null || baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }
      return baseResponseType;
    } else {
      return null;
    }
  }

  protected ResponseEntity<?> getAvailabelCard(HttpServletRequest request,
      HttpServletResponse response, ModelMap map) throws Exception {

    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    String payerCif= userLogin.getCustomerCif();
    String cardType = request.getParameter("cardtype");
    List<String> listCard = Collections.singletonList(cardType);

    GetAvailableCardRequest cardReq = new GetAvailableCardRequest(listCard,payerCif);
    GetAvailableCardResponse baseResponseType = epinEndpoint
        .getAvailableCard(cardReq);

    Map<Integer, Long> listAvailabel = null;
    listAvailabel = baseResponseType.getAvailableCard().get(cardType);

    return ResponseEntity.ok(listAvailabel);
  }

  protected String apiDocument() throws Exception {
    return "/batch_cards/api_document";
  }

  private List<EpinPurchaseOrderDetailTmp> sumaryEpinInfo(
      List<EpinPurchaseOrderDetail> epinDetails) {
    List<EpinPurchaseOrderDetailTmp> epinDetailTmps = new ArrayList<>();
    String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
    String messageAvaliable = validation.notify(MESSAGE_AVAILABLE);
    String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);

    GetAvailableCardRequest requestAvailable = new GetAvailableCardRequest();
    GetAvailableCardResponse responseAvailable = null;
    try {
      responseAvailable = epinEndpoint.getAvailableCard(requestAvailable);

      for (EpinPurchaseOrderDetail item : epinDetails) {
        long cardStock = 0L;
        EpinPurchaseOrderDetailTmp epinDetailTmp = new EpinPurchaseOrderDetailTmp();
        epinDetailTmp.setCardType(item.getCardType());
        epinDetailTmp.setFaceValue(item.getFaceValue());
        epinDetailTmp.setQuantity(item.getQuantity());

        if (responseAvailable.getAvailableCard() != null) {
          Map<String, Map<Integer, Long>> availableCard = responseAvailable
              .getAvailableCard();

          if (availableCard.get(item.getCardType()) != null) {

            Map<Integer, Long> cardType = availableCard.get(item.getCardType());

            if (cardType.get(item.getFaceValue()) != null) {
              Long quantity = cardType.get(item.getFaceValue());
              cardStock += quantity;

              if (quantity >= item.getQuantity()) {
                epinDetailTmp.setStatus(messageAvaliable);
              } else {
                epinDetailTmp.setStatus(messageNotEnough);
              }
            } else {
              epinDetailTmp.setStatus(messageNotAvaliable);
            }
          } else {
            epinDetailTmp.setStatus(messageNotAvaliable);
          }
        } else {
          epinDetailTmp.setStatus(messageNotAvaliable);
        }

        epinDetailTmp.setCardStock(cardStock);

        epinDetailTmps.add(epinDetailTmp);
      }

      EpinPurchaseOrderDetailTmp.order(epinDetailTmps);

      return epinDetailTmps;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }

    return null;
  }

  private boolean checkEpinAvaliable(List<EpinPurchaseOrderDetailTmp> epinDetailTmps) {
    String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
    String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);
    for (EpinPurchaseOrderDetailTmp item : epinDetailTmps) {
      if (messageNotEnough.equals(item.getStatus()) || messageNotAvaliable
          .equals(item.getStatus())) {
        return false;
      }
    }
    return true;
  }

  private void exportAttach(HttpServletResponse response, EpinPurchaseOrderAttachment attachment) {
    try {
      response.setContentType("application/vnd.ms-excel");
      response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
      FileCopyUtils.copy(attachment.getContent(), response.getOutputStream());
      response.flushBuffer();
    } catch (IOException e) {
      LOGGER.error("Error writing file to output stream. Filename was '{}'" + attachment.getName(),
          e);
      throw new IllegalArgumentException("IOError writing file to output stream");
    }
  }

  private Long mainBalance(){
    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    if (userLogin != null) {
      if(CustomerType.CUSTOMER.equals(userLogin.getCustomerTypeId())){
        return SharedConstants.CONSUMER_MAINTENANCE_BALANCE;
      }
      if(CustomerType.AGENT.equals(userLogin.getCustomerTypeId())){
        return SharedConstants.AGENT_MAINTENANCE_BALANCE;
      }
      if(CustomerType.MERCHANT.equals(userLogin.getCustomerTypeId())){
        return SharedConstants.MERCHANT_MAINTENANCE_BALANCE;
      }
    }

    return 0L;
  }

  protected void exportCreateNewEpin(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    try {
      if (epinId > 0) {
        ExportEpinPurchaseOrderFormRequest atRequest = new ExportEpinPurchaseOrderFormRequest(
            epinId);
        ExportEpinPurchaseOrderFormResponse attachmentResponse = epinEndpoint.exportEpoNewCreate(atRequest);

        if (attachmentResponse.getStatus().getCode() == 0) {
          response.setContentType("application/vnd.ms-excel");
          response.setHeader("Content-Disposition",
              "attachment; filename=" + attachmentResponse.getName());
          FileCopyUtils.copy(attachmentResponse.getContent(), response.getOutputStream());
          response.flushBuffer();
        }
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
    }
  }

}
