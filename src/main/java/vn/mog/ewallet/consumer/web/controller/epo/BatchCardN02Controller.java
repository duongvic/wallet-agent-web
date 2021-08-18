package vn.mog.ewallet.consumer.web.controller.epo;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.ADMIN_OPERATION;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.AGENT;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.CUSTOMER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.MERCHANT;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.SALESUPPORT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.consumer.web.controller.epo.BatchCardsController;
import vn.mog.ewallet.consumer.web.controller.epo.beans.EpinPurchaseOrderSummaryTmp;
import vn.mog.ewallet.consumer.web.exception.FrontEndException;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.GetEpinPurchaseOrderResponse;

@Controller
@RequestMapping(value = "/batch-cards/api-store-n02")
public class BatchCardN02Controller extends BatchCardsController {

  @GetMapping(value = "/list")
  public String apiStoreList(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    return super.batchCardList(request, response, map, DASHBOARD_TYPE_API_N02);
  }

  @PostMapping(value = "/list")
  public String apiStoreListPost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    return apiStoreList(request, response, map);
  }

  @GetMapping(value = "/detail")
  public String epinPoDetail(HttpServletRequest request, ModelMap map)
      throws Exception {

    return super.epinPoDetail(request, map, DASHBOARD_TYPE_API_N02);
  }

  /**
   * redirect page, create MPO
   */
  @RequestMapping(value = "/request-po", method = RequestMethod.GET)
//  @PreAuthorize("hasAnyRole('" + MERCHANT + "','" + CUSTOMER + "')")
  public String requestPO(ModelMap map) throws FrontEndException {

    return super.requestPO(map, DASHBOARD_TYPE_API_N02);
  }

  @RequestMapping(value = "/request-po", method = RequestMethod.POST) //next PO
  public String requestSubmitMPO(HttpServletRequest request, ModelMap map)
      throws Exception {

    return requestSubmitMPOCommon(request, map, DASHBOARD_TYPE_API_N02);
  }

  /**
   * Redirect page, edit epo
   */
  @RequestMapping(value = "/edit-po", method = RequestMethod.GET)
//  @PreAuthorize("hasAnyRole('" + MERCHANT + "','" + CUSTOMER + "')")
  public String editPO(HttpServletRequest request, ModelMap map)
      throws Exception {

    return super.editPO(request, map, DASHBOARD_TYPE_API_N02);
  }

  @RequestMapping(value = "/edit-po", method = RequestMethod.POST)
//  @PreAuthorize("hasAnyRole('" + MERCHANT + "','" + CUSTOMER + "')")
  public String editConfirmPO(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {

    return super.editConfirmPO(request, response, map, DASHBOARD_TYPE_API_N02);
  }

  @RequestMapping(value = "/epin-checkorder", method = RequestMethod.GET)
  public String epinCheckOrder(HttpServletRequest request, ModelMap map) throws Exception {

    return super.epinCheckOrder(request, map, DASHBOARD_TYPE_API_N02);
  }

  @RequestMapping(value = "/epin-checkorder", method = RequestMethod.POST)
  //tao va chuyen yeu cau PO
  public String epinCheckorderPost(HttpServletRequest request, ModelMap map)
      throws Exception {

    return super.epinCheckorderPost(request, map, DASHBOARD_TYPE_API_N02);
  }

  @RequestMapping(value = "/epin-export", method = RequestMethod.GET)
  public String epinExportAllowed(HttpServletRequest request, ModelMap model)
      throws Exception {

    return super.epinExportAllowed(request, model, DASHBOARD_TYPE_API_N02);
  }

  @RequestMapping(value = "/epin-export", method = RequestMethod.POST)
  public String epinExportAllowedPost(HttpServletRequest request, ModelMap model)
      throws Exception {

    return super.epinExportAllowedPost(request, model, DASHBOARD_TYPE_API_N02);
  }

  @RequestMapping(value = "/request-otp", method = RequestMethod.GET)
  public String requestOTP() throws Exception {

    return super.requestOTP();
  }

  @RequestMapping(value = "/request-otp", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + MERCHANT + "','" + AGENT + "','" + CUSTOMER + "')")
  public String requestOTPsubmit(HttpServletRequest request, ModelMap model) throws Exception {

    return super.requestOTPsubmit(request, model);
  }

  @RequestMapping(value = "/epin-approve", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "','" + SALESUPPORT + "')")
  public String epinApprove(HttpServletRequest request, ModelMap model) throws Exception {

    return super.epinApprove(request, model);
  }

  @RequestMapping(value = "/epin-approve", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "','" + SALESUPPORT + "')")
  public String epinApprovePost(HttpServletRequest request, ModelMap model) throws Exception {

    return super.epinApprovePost(request, model);
  }

  @RequestMapping(value = "/update-request", method = RequestMethod.GET)
  public String updateRequestGetPO(ModelMap model) throws Exception {

    return super.updateRequestGetPO(model, DASHBOARD_TYPE_API_N02);
  }

  @RequestMapping(value = "/update-request", method = RequestMethod.POST)
  public String updateRequestPO(HttpServletRequest request, ModelMap model) throws Exception {

    return super.updateRequestPO(request, model, DASHBOARD_TYPE_API_N02);
  }

  @RequestMapping(value = "/export-epo", method = RequestMethod.GET)
  public String exportMPO() throws Exception {

    return "/about/404";
  }

  /**
   * Tải file về
   */
  @RequestMapping(value = "/export-epo", method = RequestMethod.POST)
  public String exportMPOsubmit(HttpServletRequest request, HttpServletResponse response,
      ModelMap model) throws Exception {

    return super.exportMPOsubmit(request, response, model);
  }

  @RequestMapping(value = "/export-epin", method = RequestMethod.GET)
  public void exportEpin(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    super.exportEpin(request, response);
  }

  @RequestMapping(value = "/get-otp", method = RequestMethod.GET) //verify
  public String getOtpMPO(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {

    return "/about/404";
  }

  @RequestMapping(value = "/confirm-otp", method = RequestMethod.POST) //confirm
  public String postOtpMPO(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {

    return "/about/404";
  }

  @RequestMapping(value = "/resend-pass", method = RequestMethod.GET)
  public ResponseEntity<?> resendPass(HttpServletRequest httpRequest) throws Exception {

    return null;
  }

  @RequestMapping(value = "/getSumary", method = RequestMethod.POST)
  @ResponseBody
  public EpinPurchaseOrderSummaryTmp getSumary(HttpServletRequest request) throws Exception {

    return super.getSumary(request);
  }

  @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
  @ResponseBody
  public GetEpinPurchaseOrderResponse getDetail(HttpServletRequest request) throws Exception {

    return super.getDetail(request);
  }

  @GetMapping(value = "/getAvailabelCard")
  public ResponseEntity<?> getAvailabelCard(HttpServletRequest request,
      HttpServletResponse response, ModelMap map) throws Exception {

    return super.getAvailabelCard(request, response, map);
  }

  @RequestMapping(value = "/api-document", method = RequestMethod.GET)
  public String apiDocument() throws Exception {

    return super.apiDocument();
  }

  @RequestMapping(value = "/export-po-new", method = RequestMethod.GET)
  public void exportCreateNewEpin(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    super.exportCreateNewEpin(request, response);
  }

}
