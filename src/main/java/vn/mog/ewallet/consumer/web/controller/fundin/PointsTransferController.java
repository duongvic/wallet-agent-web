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
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerBalanceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerGetBalanceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.TrueServiceCommissionGetRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.TrueServiceCommissionGetResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.TrueServiceCommissionFeeDetail;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.*;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.bean.WalletTransferOrder;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.ewallet.consumer.web.util.tools.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType.P2P_TRANSFER;

@Controller
@RequestMapping(value = "/points-transfer")
public class PointsTransferController extends FundOrderController {

  private static final Logger LOGGER = LogManager.getLogger(PointsTransferController.class);


  public static final String POINTS_TRANSFER_CONTROLLER = "/points-transfer";
  public static final String POINTS_TRANSFER_INFO = POINTS_TRANSFER_CONTROLLER + "/info";

  // Session attribute
  private static final String POINTS_TRANSFER_SENDER_MSISDN_ATTRIBUTE = "POINTS_TRANSFER_SENDER_MSISDN_ATTRIBUTE";
  private static final String POINTS_TRANSFER_SENDER_NAME_ATTRIBUTE = "POINTS_TRANSFER_SENDER_NAME_ATTRIBUTE";
  private static final String POINTS_TRANSFER_RECEIVER_MSISDN_ATTRIBUTE = "POINTS_TRANSFER_RECEIVER_MSISDN_ATTRIBUTE";
  private static final String POINTS_TRANSFER_RECEIVER_NAME_ATTRIBUTE = "POINTS_TRANSFER_RECEIVER_NAME_ATTRIBUTE";
  private static final String POINTS_TRANSFER_AMOUNT_ATTRIBUTE = "POINTS_TRANSFER_AMOUNT_ATTRIBUTE";
  private static final String POINTS_TRANSFER_TRANS_CONTENT_ATTRIBUTE = "POINTS_TRANSFER_TRANS_CONTENT_ATTRIBUTE";
  private static final String POINTS_TRANSFER_TRANS_FEE_ATTRIBUTE = "POINTS_TRANSFER_TRANS_FEE_ATTRIBUTE";
  private static final String POINTS_TRANSFER_COMMISSION_ATTRIBUTE = "POINTS_TRANSFER_COMMISSION_ATTRIBUTE";
  private static final String POINTS_TRANSFER_FEE_ATTRIBUTE = "POINTS_TRANSFER_FEE_ATTRIBUTE";


  private static final String POINTS_TRANSFER_KEY_OTP_FEE_ATTRIBUTE = "POINTS_TRANSFER_KEY_OTP_FEE_ATTRIBUTE";
  private static final String POINTS_TRANSFER_SENDER_OTP_ATTRIBUTE = "POINTS_TRANSFER_SENDER_OTP_ATTRIBUTE";

  private static final String POINTS_TRANSFER_ORDER_ID_ATTRIBUTE = "POINTS_TRANSFER_ORDER_ID_ATTRIBUTE";
  private static final String POINTS_TRANSFER_TOTAL_ATTRIBUTE = "POINTS_TRANSFER_TOTAL_ATTRIBUTE";
  private static final String POINTS_TRANSFER_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE = "POINTS_TRANSFER_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE";

  private static final String SUCCESS = "success";
  private static final String FAILED = "failed";


  private static final String SO_DU_VI_ATTRIBUTE = "SO_DU_VI";

  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig;
  @Autowired
  GatewayServiceAPIClient gatewayAPIClient;


  @PostMapping(value = "")
  public String oderPointTransfer(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {
      GetConsumerAccountResponse baseResponseType;
      try {
        baseResponseType = walletEndpoint.accountInfoGet();
        if (baseResponseType.getStatus().getCode() != 0) {
          throw new Exception(baseResponseType.getStatus().getValue());
        } else {
          map.put("kyc", baseResponseType.getCustomer().getKycStatus());
        }
      } catch (Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
      }
      return "/points_transfer/points_transfer_management";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/points_transfer/points_transfer_management";
    }
  }

  @GetMapping(value = "/info")
  public String oderInfoViettelPay(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {

      map.put("senderName", SessionUtil.getAttribute(POINTS_TRANSFER_SENDER_NAME_ATTRIBUTE) != null ? SessionUtil.getAttribute(POINTS_TRANSFER_SENDER_NAME_ATTRIBUTE) : "");
      map.put("senderMsisdn", SessionUtil.getAttribute(POINTS_TRANSFER_SENDER_MSISDN_ATTRIBUTE) != null ? SessionUtil.getAttribute(POINTS_TRANSFER_SENDER_MSISDN_ATTRIBUTE) : "");
      map.put("receiverName", SessionUtil.getAttribute(POINTS_TRANSFER_RECEIVER_NAME_ATTRIBUTE) != null ? SessionUtil.getAttribute(POINTS_TRANSFER_RECEIVER_NAME_ATTRIBUTE) : "");
      map.put("receiverMsisdn", SessionUtil.getAttribute(POINTS_TRANSFER_RECEIVER_MSISDN_ATTRIBUTE) != null ? SessionUtil.getAttribute(POINTS_TRANSFER_RECEIVER_MSISDN_ATTRIBUTE) : "");
      map.put("amount", SessionUtil.getAttribute(POINTS_TRANSFER_AMOUNT_ATTRIBUTE) != null ? SessionUtil.getAttribute(POINTS_TRANSFER_AMOUNT_ATTRIBUTE) : "");
      map.put("transFee", SessionUtil.getAttribute(POINTS_TRANSFER_TRANS_FEE_ATTRIBUTE) != null ? SessionUtil.getAttribute(POINTS_TRANSFER_TRANS_FEE_ATTRIBUTE) : "");
      map.put("totalCustomerAmount", SessionUtil.getAttribute(POINTS_TRANSFER_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE) != null ? SessionUtil.getAttribute(POINTS_TRANSFER_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE) : "");
      map.put("transContent", SessionUtil.getAttribute(POINTS_TRANSFER_TRANS_CONTENT_ATTRIBUTE) != null ? SessionUtil.getAttribute(POINTS_TRANSFER_TRANS_CONTENT_ATTRIBUTE) : "");
      map.put("commission", SessionUtil.getAttribute(POINTS_TRANSFER_COMMISSION_ATTRIBUTE) != null ? SessionUtil.getAttribute(POINTS_TRANSFER_COMMISSION_ATTRIBUTE) : "");
      map.put("total", SessionUtil.getAttribute(POINTS_TRANSFER_TOTAL_ATTRIBUTE) != null ? SessionUtil.getAttribute(POINTS_TRANSFER_TOTAL_ATTRIBUTE) : "");

    } catch (Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
      String codeErr = ex.getMessage();
      map.put("codeErr", codeErr);
      return "/points_transfer/viettel_payment_error";
    }
    return "/points_transfer/points_transfer_info";
  }


  @PostMapping(value = "/info")
  public String oderInfoPointTransferPost(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    Long transFee = 0L;
    Long amount = 0L;
    String receiverMsisdn = request.getParameter("receiverMsisdn");
    String prm_amount = request.getParameter("amount");
    if (StringUtils.isNotBlank(prm_amount)) {
      amount = Long.valueOf(prm_amount.replaceAll("[^0-9]+", ""));
    }
    String transContent = request.getParameter("transContent");


    MoneyTransferRequest moneyTranRequest = new MoneyTransferRequest();
    MoneyTransferResponse moneyTranResponse = new MoneyTransferResponse();
    WalletTransferOrder order = new WalletTransferOrder();
    try {

      order.setServiceType("P2P_TRANSFER");
      order.setPayeePhoneNumber(receiverMsisdn);
      order.setTerminalId("WEB");
      order.setInfo(StringUtils.isNotBlank(transContent) ? transContent : null);
      order.setAmount(amount);

      moneyTranRequest.setOrder(order);
      moneyTranRequest.setActionType("VERIFY");


      moneyTranResponse = walletEndpoint.moneyTransferVerify(moneyTranRequest);
      if (moneyTranResponse != null && moneyTranResponse.getStatus().getCode() == 0) {
        SessionUtil.setAttribute(POINTS_TRANSFER_ORDER_ID_ATTRIBUTE, moneyTranResponse.getOrderId());
        map.put("receiverName", moneyTranResponse.getPayeeFullName());
        SessionUtil.setAttribute(POINTS_TRANSFER_RECEIVER_NAME_ATTRIBUTE,  moneyTranResponse.getPayeeFullName());
        transFee = 0L;
      } else {
        String codeErr = moneyTranResponse.getStatus().getValue();
        map.put("codeErr", codeErr);
        return "/points_transfer/points_transfer_error";
      }


      map.put("receiverMsisdn", receiverMsisdn);
      SessionUtil.setAttribute(POINTS_TRANSFER_RECEIVER_MSISDN_ATTRIBUTE, receiverMsisdn);
      map.put("amount", amount);
      SessionUtil.setAttribute(POINTS_TRANSFER_AMOUNT_ATTRIBUTE, amount);
      map.put("transContent", transContent);
      SessionUtil.setAttribute(POINTS_TRANSFER_TRANS_CONTENT_ATTRIBUTE, transContent);
      map.put("transFee", transFee);
      SessionUtil.setAttribute(POINTS_TRANSFER_TRANS_FEE_ATTRIBUTE, transFee);

//      map.put("commission", getCardCommission(Long.valueOf(amount)));
//      SessionUtil.setAttribute(POINTS_TRANSFER_COMMISSION_ATTRIBUTE, getCardCommission(Long.valueOf(amount)));

      Long totalCustomerAmount = Long.valueOf(amount) + transFee;
      SessionUtil.setAttribute(POINTS_TRANSFER_TOTAL_CUSTOMER_AMOUNT_ATTRIBUTE, totalCustomerAmount);
      map.put("totalCustomerAmount", totalCustomerAmount);

      Long fee = SessionUtil.getAttribute(POINTS_TRANSFER_FEE_ATTRIBUTE) != null ? (Long) SessionUtil.getAttribute(POINTS_TRANSFER_FEE_ATTRIBUTE) : Long.valueOf(0);
      map.put("fee", fee);

//      Long total = (Long.valueOf(amount) + transFee) - getCardCommission(Long.valueOf(amount));
      Long total = (Long.valueOf(amount) + transFee);
      SessionUtil.setAttribute(POINTS_TRANSFER_TOTAL_ATTRIBUTE, total);
      map.put("total", total + fee);


      return "/points_transfer/points_transfer_info";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/points_transfer/points_transfer_info";
    }
  }

  private Long getCardCommission(Long amount) {
    Long hoaHong = 0L;
    Long fee = 0L;
    TrueServiceCommissionGetRequest trueServiceCommissionGetRequest = new TrueServiceCommissionGetRequest();
    try {
      trueServiceCommissionGetRequest.setServiceTypeId(ServiceType.WALLET_CASH_IN.name());
      TrueServiceCommissionGetResponse baseResponseType = walletEndpoint.getTrueServiceCommission(trueServiceCommissionGetRequest);
      if (baseResponseType != null && baseResponseType.getStatus().getCode() == 0) {
        Map<Integer, TrueServiceCommissionFeeDetail> commissionFeeMap = baseResponseType.getCommissionFeeMap();
        if (commissionFeeMap.size() > 0) {
          TrueServiceCommissionFeeDetail trueServiceCommissionFeeDetail = commissionFeeMap.get(0);
          hoaHong = (new BigDecimal((amount * trueServiceCommissionFeeDetail.getCommision() / 100 + trueServiceCommissionFeeDetail.getCommisionFixedAmount()))).longValueExact();
          if (trueServiceCommissionFeeDetail.getFee() != 0.0) {
            fee = (new BigDecimal((amount * trueServiceCommissionFeeDetail.getFee() / 100 + trueServiceCommissionFeeDetail.getFeeFixedAmount()))).longValueExact();
          } else {
            fee = (new BigDecimal(trueServiceCommissionFeeDetail.getFeeFixedAmount())).longValueExact();
          }
          SessionUtil.setAttribute(POINTS_TRANSFER_FEE_ATTRIBUTE, fee);
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
      return "/points_transfer/points_transfer_verify";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/points_transfer/points_transfer_verify";
    }
  }


  @PostMapping(value = "/submit")
  public String submitViettelPay(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
    String otp = request.getParameter("otp");
    MoneyTransferRequest moneyTranRequest = new MoneyTransferRequest();
    MoneyTransferResponse moneyTranResponse = new MoneyTransferResponse();

    try {
      moneyTranRequest.setActionType("CONFIRM");
      moneyTranRequest.setOrderId(Long.valueOf(String.valueOf(SessionUtil.getAttribute(POINTS_TRANSFER_ORDER_ID_ATTRIBUTE))));
      moneyTranRequest.setOtp(otp);

      moneyTranResponse = walletEndpoint.moneyTransferVerify(moneyTranRequest);
      if (moneyTranResponse != null && moneyTranResponse.getStatus().getCode() != 0) {
        String codeErr = moneyTranResponse.getStatus().getValue();
        map.put("codeErr", codeErr);
        return "/points_transfer/points_transfer_error";
      } else {
        map.put("transactionId", moneyTranResponse.getTransactionId());
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
      return "/points_transfer/points_transfer_error";
    }

    SessionUtil.removeAttribute(SO_DU_VI_ATTRIBUTE);
    SessionUtil.setAttribute(SESSION_ACCOUNT_LOGIN, userLogin);
    return "/points_transfer/points_transfer_success";
  }

}
