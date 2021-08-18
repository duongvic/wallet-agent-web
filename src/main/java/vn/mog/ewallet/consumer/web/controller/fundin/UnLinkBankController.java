package vn.mog.ewallet.consumer.web.controller.fundin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.framework.contract.base.BaseResponseType;

@Controller
@RequestMapping(value = "/unlink")
public class UnLinkBankController extends AbstractController {

  public static final String UNLINK_BANK_CONTROLLER = "/unlink";
  public static final String UNLINK_BANK_CONTROLLER_FUNDIN =
      UNLINK_BANK_CONTROLLER + "/fundInNoLinkBank";

  public static final String REDIRECT_FUNDIN_NOLNIKBANK = "/unlink/fundInNoLinkBank";
  private static final Logger LOGGER = LogManager.getLogger(UnLinkBankController.class);
  private static final String BANK_NAME_ATTRIBUTE = "BANK_NAME";
  private static final String BANK_DISPLAY_NAME_ATTRIBUTE = "BANK_DISPLAY_NAME";
  private static final String BANK_CODE_ATTRIBUTE = "BANK_CODE";

  @GetMapping(value = "")
  public String unlinkIndex(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {

      String bankName = request.getParameter("bankName");
      String bankDisplayName = request.getParameter("bankDisplayName");
      String bankCode = request.getParameter("bankCode");

      // Store to Session
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);

      // get list bank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(null);
      findBankRequest.setIsLinkBankSupport(null);

      FindBankResponse findBankResponseType = systemEndpoint
          .getListBank(findBankRequest);

      List<Bank> listBank = new ArrayList<Bank>();

      if (findBankResponseType.getStatus().getCode() == 0
          && findBankResponseType.getBanks().size() >= 1) {
        listBank = findBankResponseType.getBanks();
      }
      if (listBank == null) {
        throw new Exception("No data list link bank");
      }

      map.put("bankName", bankName);
      map.put("bankDisplayName", bankDisplayName);
      map.put("bankCode", bankCode);
      map.put("listBank", listBank);

      return "/unlink/unLinkBank";
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
      return "/unlink/unLinkBank";
    }
  }

  @PostMapping(value = "/unlinkFundOutIndex")
  public String unlinkFundOutIndex(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String bankCode = request.getParameter("bankCode");
      String bankName = request.getParameter("bankName");
      String bankDisplayName = request.getParameter("bankDisplayName");
      // Store to Session
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);

      // get list bank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(null);
      findBankRequest.setIsLinkBankSupport(null);

      FindBankResponse findBankResponseType = systemEndpoint
          .getListBank(findBankRequest);

      List<Bank> listBank = new ArrayList<Bank>();

      if (findBankResponseType.getStatus().getCode() == 0
          && findBankResponseType.getBanks().size() >= 1) {
        listBank = findBankResponseType.getBanks();
      }
      if (listBank == null) {
        throw new Exception("No data list link bank");
      }
      map.put("bankCode", bankCode);
      map.put("bankName", bankName);
      map.put("bankDisplayName", bankDisplayName);
      map.put("listBank", listBank);

      return "/unlink/unLinkBank";
    } catch (Exception e) {
      e.printStackTrace();
      map.put("bankCode", SessionUtil.getAttribute(BANK_CODE_ATTRIBUTE));
      map.put("bankName", SessionUtil.getAttribute(BANK_NAME_ATTRIBUTE));
      map.put("bankDisplayName", SessionUtil.getAttribute(BANK_DISPLAY_NAME_ATTRIBUTE));
      return "/unlink/unLinkBank";
    }
  }

  @GetMapping(value = "/fundInNoLinkBank")
  public String fundInNoLinkBank(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      // get list bank
      String bankCode = request.getParameter("bankCode");
      String bankName = request.getParameter("bankName");
      String bankDisplayName = request.getParameter("bankDisplayName");

      // Store to Session
      SessionUtil.setAttribute(BANK_NAME_ATTRIBUTE, bankName);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);

      map.put("bankCode", bankCode);
      map.put("bankName", bankName);
      map.put("bankDisplayName", bankDisplayName);

      return "/fundin/fundin_unLinkBank";
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
      return "/fundin/fundin_unLinkBank";
    }
  }
}