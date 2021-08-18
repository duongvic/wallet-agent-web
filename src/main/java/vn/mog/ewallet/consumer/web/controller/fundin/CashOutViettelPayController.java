package vn.mog.ewallet.consumer.web.controller.fundin;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.fundorder.FundOrderController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerBalanceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerGetBalanceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.TrueServiceCommissionGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.TrueServiceCommissionGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.TrueServiceCommissionFeeDetail;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.*;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

@Controller
@RequestMapping(value = "/cashout-viettel-pay")
public class CashOutViettelPayController extends FundOrderController {

  private static final Logger LOGGER = LogManager.getLogger(CashOutViettelPayController.class);


  public static final String CASHOUT_VIETTEL_PAY_CONTROLLER = "/cashout-viettel-pay";
  public static final String CASHOUT_VIETTEL_PAY_INFO = CASHOUT_VIETTEL_PAY_CONTROLLER + "/info";

  // Session attribute
  private static final String CASH_OUT_SENDER_MSISDN_ATTRIBUTE = "CASH_OUT_SENDER_MSISDN_ATTRIBUTE";
  private static final String CASH_OUT_SENDER_NAME_ATTRIBUTE = "CASH_OUT_SENDER_NAME_ATTRIBUTE";
  private static final String CASH_OUT_RECEIVER_MSISDN_ATTRIBUTE = "CASH_OUT_RECEIVER_MSISDN_ATTRIBUTE";
  private static final String CASH_OUT_RECEIVER_NAME_ATTRIBUTE = "CASH_OUT_RECEIVER_NAME_ATTRIBUTE";
  private static final String CASH_OUT_AMOUNT_ATTRIBUTE = "CASH_OUT_AMOUNT_ATTRIBUTE";
  private static final String CASH_OUT_TRANS_CONTENT_ATTRIBUTE = "CASH_OUT_TRANS_CONTENT_ATTRIBUTE";
  private static final String CASH_OUT_FEE_ATTRIBUTE = "CASH_OUT_FEE_ATTRIBUTE";
  private static final String CASH_OUT_COMMISSION_ATTRIBUTE = "CASH_OUT_COMMISSION_ATTRIBUTE";

  private static final String CASH_OUT_ORDER_ID_ATTRIBUTE = "CASH_OUT_ORDER_ID_ATTRIBUTE";
  private static final String CASH_OUT_TOTAL_ATTRIBUTE = "CASH_OUT_TOTAL_ATTRIBUTE";
  private static final String CASH_OUT_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE = "CASH_OUT_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE";

  private static final String SUCCESS = "success";
  private static final String FAILED = "failed";

  private static final String SO_DU_VI_ATTRIBUTE = "SO_DU_VI";

  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig;
  @Autowired
  GatewayServiceAPIClient gatewayAPIClient;


  @PostMapping(value = "")
  public String oderViettelPay(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {
      return "/cashout_viettel_pay/viettel_payment_management";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/cashout_viettel_pay/viettel_payment_management";
    }
  }

  @GetMapping(value = "/info")
  public String oderInfoViettelPay(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {

      map.put("senderName", SessionUtil.getAttribute(CASH_OUT_SENDER_NAME_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_OUT_SENDER_NAME_ATTRIBUTE) : "");
      map.put("senderMsisdn", SessionUtil.getAttribute(CASH_OUT_SENDER_MSISDN_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_OUT_SENDER_MSISDN_ATTRIBUTE) : "");
      map.put("receiverName", SessionUtil.getAttribute(CASH_OUT_RECEIVER_NAME_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_OUT_RECEIVER_NAME_ATTRIBUTE) : "");
      map.put("receiverMsisdn", SessionUtil.getAttribute(CASH_OUT_RECEIVER_MSISDN_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_OUT_RECEIVER_MSISDN_ATTRIBUTE) : "");
      map.put("amount", SessionUtil.getAttribute(CASH_OUT_AMOUNT_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_OUT_AMOUNT_ATTRIBUTE) : "");
      map.put("transFee", SessionUtil.getAttribute(CASH_OUT_FEE_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_OUT_FEE_ATTRIBUTE) : "");
      map.put("totalCustomerAmount", SessionUtil.getAttribute(CASH_OUT_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_OUT_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE) : "");
      map.put("transContent", SessionUtil.getAttribute(CASH_OUT_TRANS_CONTENT_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_OUT_TRANS_CONTENT_ATTRIBUTE) : "");
      map.put("commission", SessionUtil.getAttribute(CASH_OUT_COMMISSION_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_OUT_COMMISSION_ATTRIBUTE) : "");
      map.put("total", SessionUtil.getAttribute(CASH_OUT_TOTAL_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_OUT_TOTAL_ATTRIBUTE) : "");

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
      String codeErr = ex.getMessage();
      map.put("codeErr", codeErr);
      return "/cashout_viettel_pay/viettel_payment_error";
    }
    return "/cashout_viettel_pay/viettel_payment_info";
  }


  @PostMapping(value = "/info")
  public String oderInfoViettelPayPost(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {

    WalletCashOutInfoGetRequest walletCashOutInfoGetRequest = new WalletCashOutInfoGetRequest();
    WalletCashOutInfoGetResponse walletCashOutInfoGetResponse = new WalletCashOutInfoGetResponse();
    try {

      Long amount = 0L;
      String receiverMsisdn = request.getParameter("receiverMsisdn");
      String receiverName = request.getParameter("receiverName");
      String receiptCode = request.getParameter("receiptCode");
      String prm_amount = request.getParameter("amount");
      if (StringUtils.isNotBlank(prm_amount)) {
        amount = Long.valueOf(prm_amount.replaceAll("[^0-9]+", ""));
      }

      walletCashOutInfoGetRequest.setReceiptCode(receiptCode);
      walletCashOutInfoGetRequest.setReceiverName(receiverName);
      walletCashOutInfoGetRequest.setReceiverMsisdn(receiverMsisdn);
      walletCashOutInfoGetRequest.setAmount(Long.valueOf(amount));

      walletCashOutInfoGetResponse = walletEndpoint.getInfoCashOut(walletCashOutInfoGetRequest);

      if (walletCashOutInfoGetResponse != null && walletCashOutInfoGetResponse.getStatus().getCode() == 0) {
        map.put("orderId", walletCashOutInfoGetResponse.getOrderId());
        SessionUtil.setAttribute(CASH_OUT_ORDER_ID_ATTRIBUTE, walletCashOutInfoGetResponse.getOrderId());

        map.put("senderName", walletCashOutInfoGetResponse.getSender().getName());
        SessionUtil.setAttribute(CASH_OUT_SENDER_NAME_ATTRIBUTE, walletCashOutInfoGetResponse.getSender().getName());

        map.put("senderMsisdn", walletCashOutInfoGetResponse.getSender().getMsisdn());
        SessionUtil.setAttribute(CASH_OUT_SENDER_MSISDN_ATTRIBUTE, walletCashOutInfoGetResponse.getSender().getMsisdn());

        map.put("receiverName", walletCashOutInfoGetResponse.getReceiver().getName());
        SessionUtil.setAttribute(CASH_OUT_RECEIVER_NAME_ATTRIBUTE, walletCashOutInfoGetResponse.getReceiver().getName());

        map.put("receiverMsisdn", walletCashOutInfoGetResponse.getReceiver().getMsisdn());
        SessionUtil.setAttribute(CASH_OUT_RECEIVER_MSISDN_ATTRIBUTE, walletCashOutInfoGetResponse.getReceiver().getMsisdn());

      } else {
        String codeErr = walletCashOutInfoGetResponse.getStatus().getValue();
        map.put("codeErr", codeErr);
        return "/cashout_viettel_pay/viettel_payment_error";
      }

      map.put("amount", amount);
      SessionUtil.setAttribute(CASH_OUT_AMOUNT_ATTRIBUTE, amount);

      Long commission = getCardCommission(Long.valueOf(amount));
      map.put("commission", commission);
      SessionUtil.setAttribute(CASH_OUT_COMMISSION_ATTRIBUTE, commission);

      Long fee =(Long) SessionUtil.getAttribute(CASH_OUT_FEE_ATTRIBUTE);
      map.put("fee", fee);

      Long total = (Long.valueOf(amount) + commission - fee);
      SessionUtil.setAttribute(CASH_OUT_TOTAL_ATTRIBUTE, total);
      map.put("total", total);

      return "/cashout_viettel_pay/viettel_payment_info";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/cashout_viettel_pay/viettel_payment_error";
    }
  }

  private Long getCardCommission(Long amount) {
    Long hoaHong = 0L;
    Long fee = 0L;
    TrueServiceCommissionGetRequest trueServiceCommissionGetRequest = new TrueServiceCommissionGetRequest();
    try {
      trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.WALLET_CASH_OUT.name());
      TrueServiceCommissionGetResponse baseResponseType = walletEndpoint.getTrueServiceCommission(trueServiceCommissionGetRequest);
      if (baseResponseType != null && baseResponseType.getStatus().getCode() == 0) {
        Map<Integer, TrueServiceCommissionFeeDetail> commissionFeeMap = baseResponseType.getCommissionFeeMap();
        if (commissionFeeMap.size() > 0) {
          TrueServiceCommissionFeeDetail trueServiceCommissionFeeDetail = commissionFeeMap.get(0);
          if (trueServiceCommissionFeeDetail.getFee() != 0.0) {
            fee = (new BigDecimal((amount * trueServiceCommissionFeeDetail.getFee() / 100 + trueServiceCommissionFeeDetail.getFeeFixedAmount()))).longValueExact();
          } else {
            fee = (new BigDecimal(trueServiceCommissionFeeDetail.getFeeFixedAmount())).longValueExact();
          }
          SessionUtil.setAttribute(CASH_OUT_FEE_ATTRIBUTE, fee);
          hoaHong = (new BigDecimal((amount * trueServiceCommissionFeeDetail.getCommision() / 100 + trueServiceCommissionFeeDetail.getCommisionFixedAmount()))).longValueExact();

        }
      }
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
    }
    return hoaHong;
  }


  @PostMapping(value = "/verify")
  public String verifyViettelPayPost(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {

    try {
      return "/cashout_viettel_pay/viettel_payment_verify";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/cashout_viettel_pay/viettel_payment_verify";
    }
  }


  @PostMapping(value = "/submit")
  public String submitViettelPay(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    String otp = request.getParameter("otp");
    WalletCashOutRequest walletCashOutRequest = new WalletCashOutRequest();

    Long orderId = 0L;
    if (SessionUtil.getAttribute(CASH_OUT_ORDER_ID_ATTRIBUTE) != null) {
      orderId = (Long) SessionUtil.getAttribute(CASH_OUT_ORDER_ID_ATTRIBUTE);
    }
    try {
      walletCashOutRequest.setOrderId(Long.valueOf(orderId));
      walletCashOutRequest.setOtp(otp);

      WalletCashOutResponse walletCashOutResponse = walletEndpoint.cashOut(walletCashOutRequest);
      if (walletCashOutResponse != null && walletCashOutResponse.getStatus().getCode() != 0) {
        String codeErr = walletCashOutResponse.getStatus().getValue();
        map.put("codeErr", codeErr);
        return "/cashout_viettel_pay/viettel_payment_error";
      } else {
        map.put("transactionId", walletCashOutResponse.getTransactionId());
      }
      GetCustomerBalanceResponse balanceResponseType = walletEndpoint
          .getBalanceBySelfCustomer(new GetCustomerGetBalanceRequest());
      if (balanceResponseType == null && balanceResponseType.getStatus().getCode() !=0) {
        throw new Exception("GetBalance failed");
      }else {
        if (userLogin != null) {
          userLogin.setBalance(balanceResponseType.getBalance());
        }
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();
      map.put("codeErr", codeErr);
      return "/cashout_viettel_pay/viettel_payment_error";
    }
    SessionUtil.removeAttribute(SO_DU_VI_ATTRIBUTE);
    SessionUtil.setAttribute(SESSION_ACCOUNT_LOGIN, userLogin);
    return "/cashout_viettel_pay/viettel_payment_success";
  }

}
