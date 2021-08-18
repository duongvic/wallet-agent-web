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
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.CardCommission;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.TrueServiceCommissionFeeDetail;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.WalletCashInFeeGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.WalletCashInFeeGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.WalletCashInRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.WalletCashInResponse;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.ewallet.consumer.web.util.tools.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

@Controller
@RequestMapping(value = "/cashin-viettel-pay")
public class CashInViettelPayController extends FundOrderController {

  private static final Logger LOGGER = LogManager.getLogger(CashInViettelPayController.class);


  public static final String CASHIN_VIETTEL_PAY_CONTROLLER = "/cashin-viettel-pay";
  public static final String CASHIN_VIETTEL_PAY_INFO = CASHIN_VIETTEL_PAY_CONTROLLER + "/info";

  // Session attribute
  private static final String CASH_IN_SENDER_MSISDN_ATTRIBUTE = "CASH_IN_SENDER_MSISDN_ATTRIBUTE";
  private static final String CASH_IN_SENDER_NAME_ATTRIBUTE = "CASH_IN_SENDER_NAME_ATTRIBUTE";
  private static final String CASH_IN_RECEIVER_MSISDN_ATTRIBUTE = "CASH_IN_RECEIVER_MSISDN_ATTRIBUTE";
  private static final String CASH_IN_RECEIVER_NAME_ATTRIBUTE = "CASH_IN_RECEIVER_NAME_ATTRIBUTE";
  private static final String CASH_IN_AMOUNT_ATTRIBUTE = "CASH_IN_AMOUNT_ATTRIBUTE";
  private static final String CASH_IN_TRANS_CONTENT_ATTRIBUTE = "CASH_IN_TRANS_CONTENT_ATTRIBUTE";
  private static final String CASH_IN_TRANS_FEE_ATTRIBUTE = "CASH_IN_TRANS_FEE_ATTRIBUTE";
  private static final String CASH_IN_COMMISSION_ATTRIBUTE = "CASH_IN_COMMISSION_ATTRIBUTE";
  private static final String CASH_IN_FEE_ATTRIBUTE = "CASH_IN_FEE_ATTRIBUTE";



  private static final String CASH_IN_KEY_OTP_FEE_ATTRIBUTE = "CASH_IN_KEY_OTP_FEE_ATTRIBUTE";
  private static final String CASH_IN_SENDER_OTP_ATTRIBUTE = "CASH_IN_SENDER_OTP_ATTRIBUTE";

  private static final String CASH_IN_ORDER_ID_ATTRIBUTE = "CASH_IN_ORDER_ID_ATTRIBUTE";
  private static final String CASH_IN_TOTAL_ATTRIBUTE = "CASH_IN_TOTAL_ATTRIBUTE";
  private static final String CASH_IN_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE = "CASH_IN_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE";

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
      return "/cashin_viettel_pay/viettel_payment_management";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/cashin_viettel_pay/viettel_payment_management";
    }
  }

  @GetMapping(value = "/info")
  public String oderInfoViettelPay(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {

      map.put("senderName", SessionUtil.getAttribute(CASH_IN_SENDER_NAME_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_IN_SENDER_NAME_ATTRIBUTE) : "");
      map.put("senderMsisdn", SessionUtil.getAttribute(CASH_IN_SENDER_MSISDN_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_IN_SENDER_MSISDN_ATTRIBUTE) : "");
      map.put("receiverName", SessionUtil.getAttribute(CASH_IN_RECEIVER_NAME_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_IN_RECEIVER_NAME_ATTRIBUTE) : "");
      map.put("receiverMsisdn", SessionUtil.getAttribute(CASH_IN_RECEIVER_MSISDN_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_IN_RECEIVER_MSISDN_ATTRIBUTE) : "");
      map.put("amount", SessionUtil.getAttribute(CASH_IN_AMOUNT_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_IN_AMOUNT_ATTRIBUTE) : "");
      map.put("transFee", SessionUtil.getAttribute(CASH_IN_TRANS_FEE_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_IN_TRANS_FEE_ATTRIBUTE) : "");
      map.put("totalCustomerAmount", SessionUtil.getAttribute(CASH_IN_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_IN_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE) : "");
      map.put("transContent", SessionUtil.getAttribute(CASH_IN_TRANS_CONTENT_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_IN_TRANS_CONTENT_ATTRIBUTE) : "");
      map.put("commission", SessionUtil.getAttribute(CASH_IN_COMMISSION_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_IN_COMMISSION_ATTRIBUTE) : "");
      map.put("total", SessionUtil.getAttribute(CASH_IN_TOTAL_ATTRIBUTE) != null ? SessionUtil.getAttribute(CASH_IN_TOTAL_ATTRIBUTE) : "");

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
      String codeErr = ex.getMessage();
      map.put("codeErr", codeErr);
      return "/cashin_viettel_pay/viettel_payment_error";
    }
    return "/cashin_viettel_pay/viettel_payment_info";
  }


  @PostMapping(value = "/info")
  public String oderInfoViettelPayPost(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    String btnCashInForMe = request.getParameter("btnCashInForMe");

    WalletCashInFeeGetRequest walletCashInFeeGetRequest = new WalletCashInFeeGetRequest();
    WalletCashInFeeGetResponse walletCashInFeeGetResponse = new WalletCashInFeeGetResponse();
    try {
      Long transFee = 0L;
      Long amount = 0L;
      String senderMsisdn = request.getParameter("senderMsisdn");
      String senderName = request.getParameter("senderName");
      String receiverMsisdn = request.getParameter("receiverMsisdn");
      String prm_amount = request.getParameter("amount");
      if (StringUtils.isNotBlank(prm_amount)) {
        amount = Long.valueOf(prm_amount.replaceAll("[^0-9]+", ""));
      }
      String transContent = request.getParameter("transContent");
      String keyOtpFee = request.getParameter("keyOtpFee");
      String senderOtp = request.getParameter("senderOtp");


      walletCashInFeeGetRequest.setSenderMsisdn(senderMsisdn);
      walletCashInFeeGetRequest.setSenderName(senderName);
      walletCashInFeeGetRequest.setReceiverMsisdn(receiverMsisdn);
      walletCashInFeeGetRequest.setAmount(Long.valueOf(amount));
      walletCashInFeeGetRequest.setTransContent(transContent);
      walletCashInFeeGetRequest.setKeyOtpFee(keyOtpFee);
      walletCashInFeeGetRequest.setSenderOtp(senderOtp);

      if (StringUtils.isBlank(btnCashInForMe)) {
        walletCashInFeeGetResponse = walletEndpoint.getFeecashIn(walletCashInFeeGetRequest);
        if (walletCashInFeeGetResponse != null && walletCashInFeeGetResponse.getStatus().getCode() == 0) {
          map.put("orderId", walletCashInFeeGetResponse.getOrderId());
          SessionUtil.setAttribute(CASH_IN_ORDER_ID_ATTRIBUTE, walletCashInFeeGetResponse.getOrderId());
          map.put("receiverName", walletCashInFeeGetResponse.getReceiverName());
          SessionUtil.setAttribute(CASH_IN_RECEIVER_NAME_ATTRIBUTE, walletCashInFeeGetResponse.getReceiverName());
          transFee = walletCashInFeeGetResponse.getTransFee();
        } else {
          String codeErr = walletCashInFeeGetResponse.getStatus().getValue();
          map.put("codeErr", codeErr);
          return "/cashin_viettel_pay/viettel_payment_error";
        }
      } else {
        String orderId = request.getParameter("orderIdForMe");
        map.put("orderId", orderId);
        SessionUtil.setAttribute(CASH_IN_ORDER_ID_ATTRIBUTE, orderId);
        String receiverName = request.getParameter("receiverNameForMe");
        map.put("receiverName", receiverName);
        SessionUtil.setAttribute(CASH_IN_RECEIVER_NAME_ATTRIBUTE, receiverName);
        String prm_transFee = request.getParameter("transFeeForMe");
        if (StringUtils.isNotBlank(prm_transFee)) {
          transFee = Long.valueOf(prm_transFee);
        }
      }


      map.put("senderMsisdn", senderMsisdn);
      SessionUtil.setAttribute(CASH_IN_SENDER_MSISDN_ATTRIBUTE, senderMsisdn); // Store data to Session
      map.put("senderName", senderName);
      SessionUtil.setAttribute(CASH_IN_SENDER_NAME_ATTRIBUTE, senderName);
      map.put("receiverMsisdn", receiverMsisdn);
      SessionUtil.setAttribute(CASH_IN_RECEIVER_MSISDN_ATTRIBUTE, receiverMsisdn);
      map.put("amount", amount);
      SessionUtil.setAttribute(CASH_IN_AMOUNT_ATTRIBUTE, amount);
      map.put("transContent", transContent);
      SessionUtil.setAttribute(CASH_IN_TRANS_CONTENT_ATTRIBUTE, transContent);
      map.put("keyOtpFee", keyOtpFee);
      SessionUtil.setAttribute(CASH_IN_KEY_OTP_FEE_ATTRIBUTE, keyOtpFee);
      map.put("senderOtp", senderOtp);
      SessionUtil.setAttribute(CASH_IN_SENDER_OTP_ATTRIBUTE, senderOtp);
      map.put("transFee", transFee);
      SessionUtil.setAttribute(CASH_IN_TRANS_FEE_ATTRIBUTE, transFee);

      map.put("commission", getCardCommission(Long.valueOf(amount)));
      SessionUtil.setAttribute(CASH_IN_COMMISSION_ATTRIBUTE, getCardCommission(Long.valueOf(amount)));

      Long totalCustomerAmount = Long.valueOf(amount) + transFee;
      SessionUtil.setAttribute(CASH_IN_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE, totalCustomerAmount);
      map.put("totalCustomerAmount", totalCustomerAmount);

      Long fee = SessionUtil.getAttribute(CASH_IN_FEE_ATTRIBUTE) != null ? (Long) SessionUtil.getAttribute(CASH_IN_FEE_ATTRIBUTE) : Long.valueOf(0);
      map.put("fee", fee);

      Long total = (Long.valueOf(amount) + transFee) - getCardCommission(Long.valueOf(amount));
      SessionUtil.setAttribute(CASH_IN_TOTAL_ATTRIBUTE, total);
      map.put("total", total + fee);



      return "/cashin_viettel_pay/viettel_payment_info";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/cashin_viettel_pay/viettel_payment_info";
    }
  }

  private Long getCardCommission(Long amount) {
    Long hoaHong = 0L;
    Long fee =0L;
    TrueServiceCommissionGetRequest trueServiceCommissionGetRequest = new TrueServiceCommissionGetRequest();
    try {
      trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.WALLET_CASH_IN.name());
      TrueServiceCommissionGetResponse baseResponseType = walletEndpoint.getTrueServiceCommission(trueServiceCommissionGetRequest);
      if (baseResponseType != null && baseResponseType.getStatus().getCode() == 0) {
        Map<Integer, TrueServiceCommissionFeeDetail> commissionFeeMap = baseResponseType.getCommissionFeeMap();
        if (commissionFeeMap.size() > 0) {
          TrueServiceCommissionFeeDetail trueServiceCommissionFeeDetail = commissionFeeMap.get(0);
          hoaHong = (new BigDecimal((amount * trueServiceCommissionFeeDetail.getCommision() / 100 + trueServiceCommissionFeeDetail.getCommisionFixedAmount()))).longValueExact();
          if(trueServiceCommissionFeeDetail.getFee() != 0.0){
            fee = (new BigDecimal((amount * trueServiceCommissionFeeDetail.getFee() / 100 + trueServiceCommissionFeeDetail.getFeeFixedAmount()))).longValueExact();
          }else {
            fee =(new BigDecimal(trueServiceCommissionFeeDetail.getFeeFixedAmount())).longValueExact();
          }
          SessionUtil.setAttribute(CASH_IN_FEE_ATTRIBUTE, fee);
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
      return "/cashin_viettel_pay/viettel_payment_verify";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/cashin_viettel_pay/viettel_payment_verify";
    }
  }


  @PostMapping(value = "/submit")
  public String submitViettelPay(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    String otp = request.getParameter("otp");
    WalletCashInRequest walletCashInRequest = new WalletCashInRequest();

    try {
      walletCashInRequest.setOrderId(Long.valueOf(String.valueOf(SessionUtil.getAttribute(CASH_IN_ORDER_ID_ATTRIBUTE))));
      walletCashInRequest.setKeyOtpFee(String.valueOf(SessionUtil.getAttribute(CASH_IN_KEY_OTP_FEE_ATTRIBUTE)));
      walletCashInRequest.setSenderOtp(String.valueOf(SessionUtil.getAttribute(CASH_IN_SENDER_OTP_ATTRIBUTE)));
      walletCashInRequest.setOtp(otp);

      WalletCashInResponse walletCashInResponse = walletEndpoint.cashIn(walletCashInRequest);
      if (walletCashInResponse != null && walletCashInResponse.getStatus().getCode() != 0) {
        String codeErr = walletCashInResponse.getStatus().getValue();
        map.put("codeErr", codeErr);
        return "/cashin_viettel_pay/viettel_payment_error";
      } else {
        map.put("transactionId", walletCashInResponse.getTransactionId());
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
      return "/cashin_viettel_pay/viettel_payment_error";
    }
    SessionUtil.removeAttribute(SO_DU_VI_ATTRIBUTE);
    SessionUtil.setAttribute(SESSION_ACCOUNT_LOGIN, userLogin);
    return "/cashin_viettel_pay/viettel_payment_success";
  }

}
