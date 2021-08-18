package vn.mog.ewallet.consumer.web.controller.fundin;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.gson.Gson;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.controller.customer.CustomerController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.FundOutNoLinkToBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.bank.FundOutNoLinkToBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.ewallet.consumer.web.util.tools.NumberUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.framework.contract.base.BaseResponseType;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Controller
@RequestMapping(value = "/fundin/nolinkbank")
public class FundInNoLinkBankController extends AbstractController {

  private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);
  private static final String ACTION_VERIFY = "verify";
  private static final String TRANSFER_TYPE_CARD = "CARD";
  private static final String TRANSFER_TYPE_ACCOUNT = "ACCOUNT";

  private static final String NAME_BANK_ATTRIBUTE = "NAME_BANK";
  private static final String SO_TAI_KHOAN_ATTRIBUTE = "SO_TAI_KHOAN";
  private static final String BANK_CODE_ATTRIBUTE = "BANK_CODE";
  private static final String BANK_DISPLAY_NAME_ATTRIBUTE = "BANK_DISPLAY_NAME";
  private static final String AMOUNT_ATTRIBUTE = "AMOUNT";
  private static final String WALLET_ID_ATTRIBUTE = "WALLET_ID";
  private static final String DESCRIPTION_ATTRIBUTE = "DESCRIPTION";

  @GetMapping(value = "")
  public String unlinkIndex(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    try {
// get list bank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(null);
      findBankRequest.setIsLinkBankSupport(null);
      FindBankResponse baseResponseType = systemEndpoint
          .getListBank(findBankRequest);
      //--- check base response type;
      if (baseResponseType == null) {
        throw new Exception("No response!");
      }

      if (baseResponseType.getStatus().getCode() != 0) {
        throw new Exception(baseResponseType.getStatus().getValue());
      }
      //set get dữ liệu
      List<Bank> listBank = new ArrayList<Bank>();
      if (baseResponseType.getBanks().size() >= 1) {
        listBank = baseResponseType.getBanks();
      }

      map.put("listBank", listBank);
      return "/fundin/fundin_noLinkBank";
    } catch (Exception e) {
      e.printStackTrace();
      return "/fundin/fundin_noLinkBank";
    }
  }


  @GetMapping(value = "/info")
  public String fundinNoLinkBankInfo(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws Exception {
    try {
      String _nameBank = request.getParameter("_nameBank");
      SessionUtil.setAttribute(NAME_BANK_ATTRIBUTE, _nameBank);
      //lấy thôn tin theo nameBank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(_nameBank);
      findBankRequest.setIsLinkBankSupport(null);

      FindBankResponse findBankResponseType = systemEndpoint
          .getListBank(findBankRequest);
      if (findBankResponseType.getStatus().getCode() != 0
          || findBankResponseType.getBanks() == null
          || findBankResponseType.getBanks().size() != 1) {
        throw new Exception("No data list link bank");
      }

      Bank bank = findBankResponseType.getBanks().get(0);
      String bankCode = bank.getBankCode();
      String bankDisplayName = bank.getDisplayName();

      // Store to Session
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);

      map.put("_nameBank", _nameBank);
      map.put("bankCode", bankCode);
      map.put("bankDisplayName", bankDisplayName);
      return "/fundin/fundin_noLinkBank_info";
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
      return "/fundin/fundin_noLinkBank_info";
    }
  }

  @PostMapping(value = "/verify")
  public String fundinNoLinkBankVerify(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws Exception {
    try {
      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      String walletId = userLogin.getPhoneNumber();
      String _nameBank = request.getParameter("_nameBank");
      String _soTaiKhoan = request.getParameter("_soTaiKhoan");
      String transferType = request.getParameter("transferType");
      Long amount = NumberUtil.convertToLong(request.getParameter("amount"));
      String description = request.getParameter("description");

      // Store to Session
      SessionUtil.setAttribute(WALLET_ID_ATTRIBUTE, walletId);
      SessionUtil.setAttribute(NAME_BANK_ATTRIBUTE, _nameBank);
      SessionUtil.setAttribute(SO_TAI_KHOAN_ATTRIBUTE, _soTaiKhoan);
      SessionUtil.setAttribute(AMOUNT_ATTRIBUTE, amount);
      SessionUtil.setAttribute(DESCRIPTION_ATTRIBUTE, description);
      // end

      FundOutNoLinkToBankRequest foNoLinkBankRequest = new FundOutNoLinkToBankRequest();
      foNoLinkBankRequest.setSignature("config");
      foNoLinkBankRequest.setBankCode("config");
      foNoLinkBankRequest.setWalletId(walletId);
      foNoLinkBankRequest.setBankCode(_nameBank);
      foNoLinkBankRequest.setAmount(amount.toString());
      foNoLinkBankRequest.setRequestId(UUID.randomUUID().toString());
      foNoLinkBankRequest.setType(ACTION_VERIFY);
      foNoLinkBankRequest.setTransferType(transferType);
      if (transferType == TRANSFER_TYPE_CARD) {
        foNoLinkBankRequest.setCardNumber(_soTaiKhoan);
      } else if ((transferType == TRANSFER_TYPE_ACCOUNT)) {
        foNoLinkBankRequest.setAccountNumber(_soTaiKhoan);
      }
      foNoLinkBankRequest.setDescription(description);

      FundOutNoLinkToBankResponse findBankResponseType = walletEndpoint
          .fundOutNoLinkBankVerify(foNoLinkBankRequest);

      if (findBankResponseType.getStatus().getCode() != 0) {
        throw new Exception("No data list link bank");
      }

      Bank bank = new Bank();
      String bankCode = bank.getBankCode();
      String bankDisplayName = bank.getDisplayName();
      // Store to Session
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);

      map.put("_nameBank", _nameBank);
      map.put("bankCode", bankCode);
      map.put("bankDisplayName", bankDisplayName);
      return "/fundin/fundin_noLinkBank";
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
      return "/fundin/fundin_noLinkBank";
    }
  }

  @PostMapping(value = "/confirm")
  public String fundinNoLinkBankconfirm(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws Exception {
    try {
      String _nameBank = request.getParameter("_nameBank");
      //lấy thôn tin theo nameBank
      FindBankRequest findBankRequest = new FindBankRequest();
      findBankRequest.setBankCode(_nameBank);
      findBankRequest.setIsLinkBankSupport(null);

      FindBankResponse findBankResponseType = systemEndpoint.getListBank(findBankRequest);
      if (findBankResponseType.getStatus().getCode() != 0
          || findBankResponseType.getBanks() == null
          || findBankResponseType.getBanks().size() != 1) {
        throw new Exception("No data list link bank");
      }
      Bank bank = findBankResponseType.getBanks().get(0);
      String bankCode = bank.getBankCode();
      String bankDisplayName = bank.getDisplayName();
      // Store to Session
      SessionUtil.setAttribute(BANK_CODE_ATTRIBUTE, bankCode);
      SessionUtil.setAttribute(BANK_DISPLAY_NAME_ATTRIBUTE, bankDisplayName);

      map.put("_nameBank", _nameBank);
      map.put("bankCode", bankCode);
      map.put("bankDisplayName", bankDisplayName);
      return "/fundin/fundin_noLinkBank";
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
      return "/fundin/fundin_noLinkBank";
    }
  }

}