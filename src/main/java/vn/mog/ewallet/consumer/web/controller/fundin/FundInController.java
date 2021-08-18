package vn.mog.ewallet.consumer.web.controller.fundin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.mog.ewallet.consumer.web.controller.fundorder.FundOrderController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IWalletEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.CreateFundOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.CreateFundOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.UpdateFundOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.UpdateFundOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.order.bean.FundOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankProfileRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankProfileResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.BankProfile;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.FundOrderChannelType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrderAttachment;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

@Controller
@RequestMapping(value = "/fundin")
public class FundInController extends FundOrderController {

  private static final Logger LOGGER = LogManager.getLogger(FundInController.class);
  private static final String SYNTAX_ATTRIBUTE = "syntax";

  @Autowired
  private IWalletEndpoint walletEndpoint;

  @GetMapping(value = "/{fund_in_method}/management")
  public String fundInCashOnHand(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_in_method") String fundInMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundInMethod) && !BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
      return "/about/404";
    }

    try {
      map.put("fund_in_method", fundInMethod);

      return "/fundin/fundin_request_management";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/fundin/fundin_request_management";
    }
  }

  @GetMapping(value = "/{fund_in_method}/management/detail/{requestId}")
  public String requestDetail(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_in_method") String fundInMethod,
      @PathVariable("requestId") String requestId) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundInMethod) && !BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
      return "/about/404";
    }

    try {
      map.put("fund_in_method", fundInMethod);
      map.put("request_id", requestId);

      map.putAll(putOrderInfo(requestId));

      return "/fundin/fundin_request_detail";
    } catch (Exception e) {

      return errorHandle(e, map, fundInMethod);
    }
  }

  @GetMapping(value = "/{fund_in_method}/management/detail/{requestId}/edit")
  public String requestDetailEdit(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_in_method") String fundInMethod,
      @PathVariable("requestId") String requestId) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundInMethod) && !BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
      return "/about/404";
    }

    try {
      map.put("fund_in_method", fundInMethod);
      map.put("request_id", requestId);

      map.putAll(putOrderInfo(requestId));

      return "/fundin/fundin_request_detail_edit";
    } catch (Exception e) {

      return errorHandle(e, map, fundInMethod);
    }
  }

  @PostMapping(value = "/{fund_in_method}/management/detail/{requestId}/edit-submit")
  public String requestDetailEditSubmit(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_in_method") String fundInMethod,
      @PathVariable("requestId") String requestId) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundInMethod) && !BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
      return "/about/404";
    }

    map.put("fund_in_method", fundInMethod);
    map.put("request_id", requestId);
    try {
      UpdateFundOrderRequest updateFundOrderRequest = new UpdateFundOrderRequest();

      FundOrder fundOrder = new FundOrder();
      fundOrder.setId(Long.parseLong(requestId));
      if (CASH_ON_HAND_PREFIX.equals(fundInMethod)) {
        fundOrder.setOrderChannel(FundOrderChannelType.CASH_ON_HAND.code);
      } else if (BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
        fundOrder.setOrderChannel(FundOrderChannelType.BANK_TRANSFER.code);
        fundOrder.setCommandCode(request.getParameter("command_code"));
        fundOrder.setBankCode(request.getParameter("bank_code"));
      } else {
        return "/about/404";
      }
      fundOrder.setAmount(
          NumberUtil.convertToLong(
              StringUtils.trimToEmpty(request.getParameter("amount").replaceAll("[^0-9]+", ""))));
      fundOrder.setInfo(request.getParameter("remark"));
      fundOrder.setServiceType(ServiceType.FUND_IN.name());
      fundOrder.setTerminalId("WEB");

      List<FundOrderAttachment> attachments = getUploadAttachments(request,
          new FundOrderAttachment());

      updateFundOrderRequest.setOrder(fundOrder);
      updateFundOrderRequest.setAttachments(attachments);

      UpdateFundOrderResponse updateFundOrderResponseType = walletEndpoint
          .updateFundOrder(updateFundOrderRequest);
      if (updateFundOrderResponseType.getStatus().getCode() != 0) {
        return "/fundin/fundin_request_Error";
      }
      return "redirect:/fundin/" + fundInMethod + "/management";
    } catch (Exception e) {

      return errorHandle(e, map, fundInMethod);
    }
  }

  @GetMapping(value = "/{fund_in_method}/create")
  public String requestDetailCreate(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_in_method") String fundInMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundInMethod) && !BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
      return "/about/404";
    }

    try {
      map.put("fund_in_method", fundInMethod);
      map.put("bank_code", request.getParameter("bank_code"));
      map.put("listTransactions", getRecentTransactions(fundInMethod, "FUND_IN"));

      return "/fundin/fundin_request";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);

      return "/fundin/fundin_request";
    }
  }

  @PostMapping(value = "/{fund_in_method}/create")
  public String requestDetailCreatePost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_in_method") String fundInMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundInMethod) && !BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
      return "/about/404";
    }

    try {
      CreateFundOrderRequest createFundOrderRequest = new CreateFundOrderRequest();

      FundOrder fundOrder = new FundOrder();
      if (CASH_ON_HAND_PREFIX.equals(fundInMethod)) {
        fundOrder.setOrderChannel(FundOrderChannelType.CASH_ON_HAND.code);

        fundOrder.setFee(
            NumberUtil.convertToLong(StringUtils
                .trimToEmpty(request.getParameter("feeAmount").replaceAll("[^0-9]+", ""))));
      } else if (BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
        fundOrder.setOrderChannel(FundOrderChannelType.BANK_TRANSFER.code);

        String bankCode = request.getParameter("bank_code");
        String commandCode = request.getParameter("command_code");
        fundOrder.setBankCode(bankCode);
        fundOrder.setCommandCode(commandCode);
      }
      fundOrder.setAmount(
          NumberUtil.convertToLong(
              StringUtils.trimToEmpty(request.getParameter("amount").replaceAll("[^0-9]+", ""))));
      if(fundOrder.getAmount() < 100000){
        String codeErr = "So tien khong hop le. Gia tri toi thieu: 100.000 (VND), gia tri toi da: 100.000.000 (VND)";

        map.put("codeErr", codeErr);

        return "redirect:/fundin/" + fundInMethod + "/fail";
      }


      fundOrder.setInfo(request.getParameter("remark"));
      fundOrder.setServiceType(ServiceType.FUND_IN.name());
      List<FundOrderAttachment> attachments = getUploadAttachments(request,
          new FundOrderAttachment());

      createFundOrderRequest.setOrder(fundOrder);
      createFundOrderRequest.setAttachments(attachments);

      CreateFundOrderResponse createFundOrderResponseType = walletEndpoint
          .createFundOrderRequest(createFundOrderRequest);
      if (createFundOrderResponseType.getStatus().getCode() != 0) {
        String codeErr = createFundOrderResponseType.getStatus().getValue();

        map.put("codeErr", codeErr);

        return "redirect:/fundin/" + fundInMethod + "/fail";
      }
      map.put("syntax",createFundOrderResponseType.getSyntax());
      SessionUtil.setAttribute(SYNTAX_ATTRIBUTE, createFundOrderResponseType.getSyntax());
      return "redirect:/fundin/" + fundInMethod + "/success";
    } catch (Exception e) {
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "redirect:/fundin/" + fundInMethod + "/fail";
    }
  }

  @GetMapping(value = "/{fund_in_method}/success")
  public String requestDetailSuccess(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_in_method") String fundInMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundInMethod) && !BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
      return "/about/404";
    }
    map.put("syntax", SessionUtil.getAttribute(SYNTAX_ATTRIBUTE));
    map.put("listTransactions", getRecentTransactions(fundInMethod, "FUND_IN"));

    return "/fundin/fundin_request_success";
  }

  @GetMapping(value = "/{fund_in_method}/fail")
  public String requestDetailFailed(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_in_method") String fundInMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundInMethod) && !BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
      return "/about/404";
    }

    map.put("listTransactions", getRecentTransactions(fundInMethod, "FUND_IN"));

    return "/fundin/fundin_request_Error";
  }

  @GetMapping(value = "/{fund_in_method}/create/info")
  public String requestDetailCreateInfo(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("fund_in_method") String fundInMethod) throws Exception {
    if (!CASH_ON_HAND_PREFIX.equals(fundInMethod) && !BANK_TRANSFER_PREFIX.equals(fundInMethod)) {
      return "/about/404";
    }

    try {
      map.put("fund_in_method", fundInMethod);

      // get list bank
      FindBankProfileRequest findBankRequest = new FindBankProfileRequest();
      findBankRequest.setBankCode(null);
      FindBankProfileResponse baseResponseType = systemEndpoint.getProfileBanks(findBankRequest);
      if (baseResponseType == null) {
        throw new Exception("No response!");
      } else if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }
      List<BankProfile> listBank = baseResponseType.getBankProfiles();
      map.put("list_bank", listBank);
      map.put("listTransactions", getRecentTransactions(fundInMethod, "FUND_IN"));

      return "/fundin/fundin_request_bank_transfer_info";
    } catch (Exception e) {

      return errorHandle(e, map, fundInMethod);
    }
  }

  private String errorHandle(Exception e, ModelMap map, String fundInMethod) {
    LOGGER.error(e.getMessage(), e);
    String codeErr = e.getMessage();

    map.put("codeErr", codeErr);
    map.put("fund_in_method", fundInMethod);

    return "/fundin/fundin_request_Error";
  }

}
