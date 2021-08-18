package vn.mog.ewallet.consumer.web.controller.fundin;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerBalanceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerGetBalanceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignInRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.security.SignInResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.TopupTransactionRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.TopupTransactionResponse;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.framework.contract.base.BaseResponseType;

@Controller
@RequestMapping(value = "/fundin/topup")
public class FundInTopupController extends AbstractController {

  private static final String ACTION_VERIFY = "verify";
  private static final String ACTION_CONFIRM = "confirm";
  private static final String SERVICE_CODE_FIX = "060000";

  private static final String TAI_KHOAN_ATTRIBUTE = "TAI_KHOAN";
  private static final String PASSWORD_ATTRIBUTE = "PASSWORD";
  private static final String FACE_VALUE_ATTRIBUTE = "FACE_VALUE";
  private static final String REAL_AMOUNT_ATTRIBUTE = "REAL_AMOUNT";
  private static final String SO_DU_VI_ATTRIBUTE = "SO_DU_VI";
  private static final String REQUEST_ID_ATTRIBUTE = "REQUEST_ID";
  private static final String ORDER_ID_ATTRIBUTE = "ORDER_ID";
  private static final String IS_OTP_ATTRIBUTE = "IS_OTP";
  private static final String CODE_ERROR_ATTRIBUTE = "CODE_ERROR";


  @Autowired
  GatewayServiceAPIClient gatewayAPIClient;
  
  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig;


  @GetMapping(value = "")
  public String fundInTopup(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    return "/fundin/fundin_topup";
  }

  @GetMapping(value = "/logIn")
  public String getFundInTopUpLogIn(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    String faceValue = request.getParameter("faceValue").replaceAll("[^0-9]+", "");
    String realAmount = request.getParameter("realAmount").replaceAll("[^0-9]+", "");
    ;
    // Store to Session
    SessionUtil.setAttribute(FACE_VALUE_ATTRIBUTE, faceValue);
    SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);

    map.put("faceValue", faceValue);
    map.put("realAmount", realAmount);

    return "/fundin/fundin_topup_logIn";
  }

  @GetMapping(value = "/topUpNextStep")
  public String getTopUpNextStep(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      String taiKhoan = request.getParameter("taiKhoan");
      String passWord = request.getParameter("passWord");
      String soDuVi = request.getParameter("soDuVi");
      String realAmount = request.getParameter("realAmount").replaceAll("[^0-9]+", "");

      // Store to Session
      SessionUtil.setAttribute(TAI_KHOAN_ATTRIBUTE, taiKhoan);
      SessionUtil.setAttribute(PASSWORD_ATTRIBUTE, passWord);
      SessionUtil.setAttribute(SO_DU_VI_ATTRIBUTE, soDuVi);
      SessionUtil.setAttribute(REAL_AMOUNT_ATTRIBUTE, realAmount);

      Map<String, String> mapHeader = new HashMap<>();
      mapHeader.put("Content-Type", "application/json");

      Map<String, String> uriVariables = null;
      SignInRequest signInRequest = new SignInRequest();
      signInRequest.setIdentification(taiKhoan);
      signInRequest.setCredential(passWord);
      signInRequest.setIdentificationTypeId(0);  //0 sdt
      signInRequest.setCredentialTypeId(1);

      BaseResponseType<SignInResponse> signInResponseType = null;
      signInResponseType = gatewayAPIClient
          .postForEntity(gatewayUrlConfig.PARAM_EWALLET_URL_AUTH_SECURITY_SIGNIN, mapHeader, signInRequest, uriVariables, BaseResponseType.class);
      String jsonString = (new Gson()).toJson(signInResponseType.getData(), LinkedHashMap.class);
      SignInResponse siResponse = new ObjectMapper().readValue(jsonString, SignInResponse.class);

      if (signInResponseType == null || signInResponseType.getStatus().getCode() != 0) {
        throw new Exception("Login fail");
      } else {
        GetCustomerBalanceResponse balanceResponseType = walletEndpoint
            .getBalanceBySelfCustomer(new GetCustomerGetBalanceRequest());
        if (balanceResponseType == null || balanceResponseType.getStatus().getCode() != 0) {
          throw new Exception("GetBalance fail");
        } else {
          soDuVi = balanceResponseType.getBalance().toString();
          Object faceValueAttr = SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE);
          String faceValue = faceValueAttr != null ? faceValueAttr.toString() : null;
          Long amount = Long.valueOf(faceValue);
          if (balanceResponseType.getBalance() < Long.valueOf(amount)) {
            throw new Exception("Số dư không đủ");
          }
        }

        map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
        map.put("soDuVi", soDuVi);
        map.put("taiKhoan", taiKhoan);
        return "/fundin/fundin_topup_nextStep";
      }
    } catch (Exception e) {
      e.printStackTrace();
      String codeErr = e.getMessage();
      map.put("codeErr", codeErr);
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      map.put("soDuVi", SessionUtil.getAttribute(SO_DU_VI_ATTRIBUTE));
      map.put("taiKhoan", SessionUtil.getAttribute(TAI_KHOAN_ATTRIBUTE));

      return "/fundin/fundin_topup_logIn";
    }

  }

  @PostMapping(value = "/topUpNextStepVerify") //verify
  public String topUpNextStepVerify(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      String codeErr = request.getParameter("codeErr");
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);

      String requestId = UUID.randomUUID().toString();
      Object taiKhoanAttr = SessionUtil.getAttribute(TAI_KHOAN_ATTRIBUTE);
      String taiKhoan = taiKhoanAttr != null ? taiKhoanAttr.toString() : null;
      Object faceValueAttr = SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE);
      String faceValue = faceValueAttr != null ? faceValueAttr.toString() : null;

      TopupTransactionRequest topupTransactionRequest = new TopupTransactionRequest();
      topupTransactionRequest.setTerminalId("WEB");
      topupTransactionRequest.setRequestId(requestId);
      topupTransactionRequest.setServiceCode(SERVICE_CODE_FIX);
      topupTransactionRequest.setPhoneNumber(taiKhoan);
      topupTransactionRequest.setPrice(Long.valueOf(faceValue));
      topupTransactionRequest.setOrderId(null);
      topupTransactionRequest.setActionType(ACTION_VERIFY);
      topupTransactionRequest.setOtp(null);

      TopupTransactionResponse topUpResponseType = walletEndpoint
          .walletByTopUpVerify(topupTransactionRequest);
      if (topUpResponseType == null) {
        throw new Exception("No response");
      }
      if (topUpResponseType.getStatus().getCode() != 0) {
        throw new Exception(topUpResponseType.getStatus().getValue());
      } else {
        String orderId = topUpResponseType.getOrderId().toString();
        boolean isOTP = topUpResponseType.getIsOtpRequired();

        SessionUtil.setAttribute(ORDER_ID_ATTRIBUTE, orderId);
        SessionUtil.setAttribute(IS_OTP_ATTRIBUTE, isOTP);

        map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
        map.put("soDuVi", SessionUtil.getAttribute(SO_DU_VI_ATTRIBUTE));
        map.put("taiKhoan", taiKhoan);
        return "/fundin/fundin_topup_otpPhone";
      }


    } catch (Exception e) {
      e.printStackTrace();
      String codeErr = e.getMessage();
      map.put("codeErr", codeErr);
      map.put("soDuVi", SessionUtil.getAttribute(SO_DU_VI_ATTRIBUTE));
      map.put("taiKhoan", SessionUtil.getAttribute(TAI_KHOAN_ATTRIBUTE));

      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE, codeErr);
      return "/fundin/fundin_topup_nextStep";
    }
  }

  @PostMapping(value = "/topUpOtpPhone")  //Confirm
  public String topUpOtpPhoneConfirm(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      String codeOTP = request.getParameter("codeOTP");
      Object requestIdAtrr = SessionUtil.getAttribute(REQUEST_ID_ATTRIBUTE);
      String requestId = requestIdAtrr != null ? requestIdAtrr.toString() : null;
      Object faceValueAtrr = SessionUtil.getAttribute(FACE_VALUE_ATTRIBUTE);
      String  faceValue = faceValueAtrr != null ? faceValueAtrr.toString() : null;
      Object taiKhoanAtrr = SessionUtil.getAttribute(TAI_KHOAN_ATTRIBUTE);
      String  taiKhoan = taiKhoanAtrr != null ? taiKhoanAtrr.toString() : null;
      Object orderIdAtrr = SessionUtil.getAttribute(ORDER_ID_ATTRIBUTE);
      String  orderId = orderIdAtrr != null ? orderIdAtrr.toString() : null;

      TopupTransactionRequest topupTransactionRequest = new TopupTransactionRequest();
      topupTransactionRequest.setTerminalId("WEB");
      topupTransactionRequest.setRequestId(requestId);
      topupTransactionRequest.setServiceCode(SERVICE_CODE_FIX);
      topupTransactionRequest.setPrice(Long.valueOf(faceValue));
      topupTransactionRequest.setPhoneNumber(taiKhoan);
      topupTransactionRequest.setOrderId(Long.valueOf(orderId));
      topupTransactionRequest.setActionType(ACTION_CONFIRM);
      topupTransactionRequest.setOtp(codeOTP);

      TopupTransactionResponse topUpResponseType = walletEndpoint.walletByTopUpVerify(topupTransactionRequest);
      if (topUpResponseType == null) {
        throw new Exception("No response");
      }
      if (topUpResponseType.getStatus().getCode() != 0) {
        throw new Exception(topUpResponseType.getStatus().getValue());
      } else {
        map.put("codeErr", SessionUtil.getAttribute(CODE_ERROR_ATTRIBUTE));
        return "/fundin/fundin_topup_transactionSuccess";
      }

    } catch (Exception e) {
      String codeErr =  e.getMessage();
      map.put("codeErr", codeErr);
      SessionUtil.setAttribute(CODE_ERROR_ATTRIBUTE,codeErr);
      e.printStackTrace();
      return "/fundin/fundin_topup_transactionError";
    }
  }

}
