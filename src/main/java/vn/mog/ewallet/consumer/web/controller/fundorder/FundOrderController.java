package vn.mog.ewallet.consumer.web.controller.fundorder;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.GetFundOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.GetFundOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.bean.FundOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindFundOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.FindFundOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrderAttachment;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

public abstract class FundOrderController extends AbstractController {

  protected static final String CASH_ON_HAND_PREFIX = "cash-on-hand";
  protected static final String BANK_TRANSFER_PREFIX = "bank-transfer";

  protected Map<String, Object> putOrderInfo(String requestId) throws Exception {
    Map<String, Object> map = new HashMap<>();

    GetFundOrderRequest getFundOrderRequest = new GetFundOrderRequest();
    getFundOrderRequest.setOrderId(NumberUtil.convertToLong(StringUtils.trimToEmpty(requestId)));
    GetFundOrderResponse getFundOrderResponse = walletEndpoint.getFundOrder(getFundOrderRequest);

    if (getFundOrderResponse == null || getFundOrderResponse.getStatus() == null) {
      throw new Exception("No response");
    } else if (getFundOrderResponse.getStatus().getCode() != 0) {
      throw new Exception(getFundOrderResponse.getStatus().getValue());
    } else {
      FundOrder fundOrder = getFundOrderResponse.getOrder();
      if (fundOrder != null) {
        Long amount = 0L;
        Long fee = 0L;
        Long total = 0L;
        total = fundOrder.getAmount() + fundOrder.getFee();
        map.put("order_channel", fundOrder.getOrderChannel());
        map.put("amount", fundOrder.getAmount());
        map.put("fee", fundOrder.getFee());
        map.put("total",total);
        map.put("remark", fundOrder.getInfo());
        map.put("command_code", fundOrder.getCommandCode());
        map.put("bank_code", fundOrder.getBankCode());
        map.put("stage", fundOrder.getStage());
        map.put("ref_txn_status", fundOrder.getRefTxnStatus());
        map.put("syntax", fundOrder.getSyntax());
      }
      List<FundOrderAttachment> attachmentsList = getFundOrderResponse.getAttachments();
      for (FundOrderAttachment attachment : attachmentsList) {
        byte[] content = attachment.getContent();
        if (attachment.getImageBase64() == null && content != null) {
          byte[] encodeBase64 = Base64.encodeBase64(content);
          String base64Encoded = new String(encodeBase64, "UTF-8");
          attachment.setImageBase64(base64Encoded);
        }
      }
      map.put("attachments", attachmentsList);
      map.put("attachments_size", attachmentsList.size());

      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      if (userLogin != null) {
        long balanceCurrentUser = userLogin.getBalance();
        map.put("current_balance", balanceCurrentUser);
        map.put("customer_type_id", userLogin.getCustomerTypeId());
      }
    }

    return map;
  }

  protected List<FundOrder> getRecentTransactions(String orderChannelId, String serviceTypeId) throws Exception {
    FindFundOrderRequest findFundOrderRequest = new FindFundOrderRequest();
    List<String> serviceTypeIds = new ArrayList<>();
    serviceTypeIds.add(serviceTypeId);
    findFundOrderRequest.setServiceTypeIds(serviceTypeIds);
    List<String> orderChannelIds = new ArrayList<>();
    if (CASH_ON_HAND_PREFIX.equals(orderChannelId)) {
      orderChannelIds.add("CASH_ON_HAND");
    } else if (BANK_TRANSFER_PREFIX.equals(orderChannelId)) {
      orderChannelIds.add("BANK_TRANSFER");
    }
    findFundOrderRequest.setOrderChannelIds(orderChannelIds);
    findFundOrderRequest.setOffset(0);
    findFundOrderRequest.setLimit(5);

    FindFundOrderResponse baseResponseType = walletEndpoint
        .findCustomerFundOrders(findFundOrderRequest);
    if (baseResponseType == null) {
      throw new Exception("No response!");
    }
    if (baseResponseType.getStatus().getCode() != 0) {
      throw new Exception(baseResponseType.getStatus().getValue());
    }

    return baseResponseType.getFundOrders();
  }

}
