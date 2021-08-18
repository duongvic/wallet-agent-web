package vn.mog.ewallet.consumer.web.controller;


import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.ADMIN_OPERATION;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetOTPConfirmRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.GetOTPConfirmResponse;

import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;

import vn.mog.framework.contract.base.MobiliserResponseType.Status;

@Controller
@RequestMapping(value = "")
public class CommonControler extends AbstractController {


  @PostMapping(value = "/getOtp")
  @ResponseBody
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','FINANCE','FINANCESUPPORT_LEADER')")
  public GetOTPConfirmResponse getOTPConfirm(HttpServletRequest request) throws Exception {
    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    if (fundOrderId > 0) {
      GetOTPConfirmRequest otpRes = new GetOTPConfirmRequest(fundOrderId);
      return walletEndpoint.getOTPConfirmWalletTransferOrder(otpRes);
    }
    GetOTPConfirmResponse response = new GetOTPConfirmResponse();
    response.setStatus(new Status(1, "Error"));
    return response;
  }

}
