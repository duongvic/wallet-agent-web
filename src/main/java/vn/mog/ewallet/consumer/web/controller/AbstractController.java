package vn.mog.ewallet.consumer.web.controller;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.PARAM_ACCESS_TOKEN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCESS_TOKEN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.ACCOUNTING;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.ACCOUNTING_LEADER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.ADMIN_OPERATION;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.AGENT;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.AGGREATOR;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.BILLER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.CUSTOMER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.CUSTOMERCARE_MANAGER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.FA_MANAGER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.FINANCE;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.FINANCE_LEADER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.MERCHANT;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.RECONCILIATION;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.RECONCILIATION_LEADER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.SALE_ASM;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.SALE_DIRECTOR;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.SALE_EXCUTIVE;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.TECH_SUPPORT;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.authserver.impl.contract.security.beans.JwtRole.TELCO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import vn.mog.ewallet.consumer.web.contract.AjaxResponse;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.translog.TransactionController;
import vn.mog.ewallet.consumer.web.exception.MessageNotify;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ICustomerEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IEpinPurchaseOrderEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IProviderEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ISecurityService;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ISystemEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ITransactionEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IWalletEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerBalanceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.GetCustomerGetBalanceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean.Invoice;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean.InvoiceAttribute;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.FindTrueServiceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.FindTrueServiceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.service.bean.TrueService;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.InvoiceAttributeType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.CardType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.FundOrderAttachment;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.wallet.beans.WalletTransferOrderAttachment;
import vn.mog.ewallet.consumer.web.util.tools.DateUtil;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;
import vn.mog.ewallet.consumer.web.utils.CacheDataUtil;
import vn.mog.ewallet.consumer.web.utils.ValidationUtil;
import vn.mog.ewallet.consumer.web.utils.WebUtil;
import vn.mog.framework.contract.base.BaseResponseType;
import vn.mog.framework.contract.base.MobiliserResponseType;

public abstract class AbstractController {

  public static final String PATH_ERROR = "/error";
  public static final Map<String, String> WALLET_TRANSFER_STYPES = new LinkedHashMap<>();
  public static final Map<String, String> ACTOR_TYPES = new LinkedHashMap<>();


  protected static final String FULL_TIME = "Full Time";
  protected static final String BTN_REQUEST = "request";
  protected static final String BTN_BACK = "back";
  protected static final String BTN_CANCEL = "cancel";
  protected static final String BTN_SUBMIT = "submit";
  protected static final String BTN_APPROVE = "approve";
  protected static final String BTN_REJECT = "reject";
  protected static final String BTN_SAVE = "save";
  protected static final String BTN_DELETE = "delete";
  protected static final String BTN_CONFIRM_OTP = "Approve";
  protected static final String BTN_EXPORT = "export";

  protected static final String VISIBLE = "true";

  protected static final String ENABLED_SERVICES = "enabledServices";

  public static final String DASHBOARD_TYPE_API = "typeAPI";
  public static final String DASHBOARD_TYPE_EXPORT = "typeExport";
  public static final String DASHBOARD_TYPE_API_N02 = "typeAPIN02";
  protected static final Gson gson = new Gson();

  private static final Logger LOG = LogManager.getLogger(AbstractController.class);

  static {
    ACTOR_TYPES.put(StringUtils.EMPTY, "Specific");
    ACTOR_TYPES.put("PAYER", "Payer");
    ACTOR_TYPES.put("PAYEE", "Payee");

    WALLET_TRANSFER_STYPES.put("WALLET_TRANSFER", "Wallet Transfer");
    WALLET_TRANSFER_STYPES.put("FUND_TRANSFER", "Fund Transfer");
  }

  @Autowired
  protected ISecurityService securityService;
  @Autowired
  protected ICustomerEndpoint customerEndpoint;
  @Autowired
  protected IEpinPurchaseOrderEndpoint epinEndpoint;
  @Autowired
  protected IProviderEndpoint providerEndpoint;
  @Autowired
  protected ITransactionEndpoint transactionEndpoint;
  @Autowired
  protected IWalletEndpoint walletEndpoint;
  @Autowired
  protected ISystemEndpoint systemEndpoint;

  @Autowired
  protected ValidationUtil validation;
  @Autowired
  protected CacheDataUtil cacheData;

  @Autowired
  ResourceUrlProvider resourceUrlProvider;

  @ModelAttribute("urls")
  public ResourceUrlProvider urls() {
    return this.resourceUrlProvider;
  }

  //@formatter:off
  protected String redirectWalletRole() {
    Set<String> roles = WebUtil.getRolesOfUserLogin();

    boolean isAdmin = roles.contains(ADMIN_OPERATION);
    boolean isFinance =
        roles.contains(FINANCE) || roles.contains(FINANCE_LEADER) || roles.contains(FA_MANAGER);
    boolean isPlusCustomer =
        roles.contains(MERCHANT) || roles.contains(AGENT) || roles.contains(CUSTOMER) || roles
            .contains(TELCO) || roles.contains(AGGREATOR) || roles.contains(BILLER);

    if (isFinance || isAdmin) {
      /*return FundInController.FUND_IN_ORDER_LIST + "?menu=wal";*/
      return "";

    } else {
      return TransactionController.TRANSACTION_LIST + "?menu=ser";
    }
  }

  protected String redirectServiceRole() {
    Set<String> roles = WebUtil.getRolesOfUserLogin();

    boolean isDefault = roles.contains(ADMIN_OPERATION) || roles.contains(SALE_DIRECTOR) || roles
        .contains(SALE_ASM);
    boolean isReconciliation =
        roles.contains(RECONCILIATION) || roles.contains(RECONCILIATION_LEADER) || roles
            .contains(ACCOUNTING) || roles.contains(ACCOUNTING_LEADER);
    boolean isBusinessOperation =
        roles.contains(CUSTOMERCARE_MANAGER) || roles.contains(SALE_EXCUTIVE) || roles
            .contains(TECH_SUPPORT);

    if (isDefault || isBusinessOperation || isReconciliation) {
      return TransactionController.TRANSACTION_LIST + "?menu=ser";

    } else {
      return TransactionController.TRANSACTION_LIST + "?menu=ser";
    }
  }
  //@formatter:on


  protected ResponseEntity responseAjax(int coce, String value) {
    if (coce == 0) {
      return ResponseEntity.ok(new AjaxResponse(0, "Yêu cầu của bạn được thực hiện thành công!"));
    } else {
      return ResponseEntity.ok(new AjaxResponse(coce, value));
    }
  }

  protected Date[] parseDateRange(String dateRange) {
    try {
      Date currentDate = new Date();
      if (StringUtils.isNotBlank(dateRange)) {
        String fulltime = validation.notify("daterange.locale.fulltime.nosign");
        if (fulltime.equals(dateRange)) {
          return new Date[]{null, null};
        } else {
          String[] range = dateRange.split("-");
          DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date fromDate = df.parse(StringUtils.trimToEmpty(range[0]));
          Date endDate = df.parse(StringUtils.trimToEmpty(range[1]));
          return new Date[]{fromDate, endDate};
        }
      } else {
        return new Date[]{DateUtil.getStartOfDay(currentDate), DateUtil.getEndOfDay(currentDate)};
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return new Date[]{null, null};
  }

  protected <T> List<T> getUploadAttachments(HttpServletRequest request, Object T) throws IOException {
    List<T> attachments = new ArrayList<>();
    MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest) request;
    List<MultipartFile> multiparts = multiPartRequest.getFiles("fileUpload");
    if (multiparts != null) {
      for (MultipartFile multipart : multiparts) {
        if (multipart != null && multipart.getBytes() != null && multipart.getBytes().length > 0) {

          String fileName = multipart.getOriginalFilename();
          byte[] bytes = multipart.getBytes();

          if (T instanceof FundOrderAttachment) {

            FundOrderAttachment attachment = (FundOrderAttachment) T;
            attachment.setName(fileName);
            attachment.setContentType("unknow");
            attachment.setContent(bytes);
            attachments.add((T) attachment);

          } else if (T instanceof WalletTransferOrderAttachment) {

            WalletTransferOrderAttachment attachment = new WalletTransferOrderAttachment();
            attachment.setName(fileName);
            attachment.setContentType("unknow");
            attachment.setContent(bytes);
            attachments.add((T) attachment);
          }
        }
      }
    }
    return attachments;
  }

  public static <T> T toResponse(BaseResponseType<T> responseType, Class<T> valueType)
      throws Exception {
    //--- check base response type;
    if (responseType == null) {
      throw new Exception("No response!");
    }
    if (responseType.getStatus() == null) {
      throw new Exception("No status!");
    }
    if (responseType.getStatus().getCode() != 0) {
      if (responseType.getStatus().getValue() == null) {
        throw new Exception("Service not support");
      }
      throw new Exception(responseType.getStatus().getValue());
    }

    //--- check status, data;
    String jsonString = gson.toJson(responseType.getData(), LinkedHashMap.class);

    //--- parse status, data to object;
    T response = new ObjectMapper().readValue(jsonString, valueType);
    if (response == null) {
      throw new Exception("No data!");
    }

    return response;
  }

  protected ResponseEntity<AjaxResponse> bodyResponseResendOtp(MobiliserResponseType response, String uuid) {
    if (response != null && MessageNotify.SUCCESS_CODE == response.getStatus().getCode()) {
      return ResponseEntity.ok(new AjaxResponse(MessageNotify.SUCCESS_CODE, uuid));
    } else {
      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, (response != null) ? response.getStatus().getValue() : MessageNotify.ERROR_NAME));
    }
  }

  protected void getUserBalanceToSession(HttpServletRequest request) {
    try {
      UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
      if (userLogin == null) {
        userLogin = new UserLogin();
      }
      try {
        GetCustomerBalanceResponse baseResponseType
            = walletEndpoint.getBalanceBySelfCustomer(new GetCustomerGetBalanceRequest());

        if (baseResponseType != null && baseResponseType.getStatus().getCode() == 0) {
          userLogin.setBalance(baseResponseType.getBalance());
          userLogin.setCustomerCif(baseResponseType.getCustomerCif());
        }
      } catch (Exception e) {
        LOG.error("Error", e);

      }
      SessionUtil.setAttribute(SESSION_ACCOUNT_LOGIN, userLogin);

      String accessToken = (String) SessionUtil.getAttribute(SESSION_ACCESS_TOKEN);
      request.setAttribute(PARAM_ACCESS_TOKEN, accessToken);
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
  }

  protected List<TrueService> findTrueServices(ModelMap map, List<String> findList) throws Exception {
    // Find enable Service
    FindTrueServiceRequest findTrueServiceRequest = new FindTrueServiceRequest();
    findTrueServiceRequest.setServiceCodes(findList);
    FindTrueServiceResponse serviceResponse
        = walletEndpoint.findTrueService(findTrueServiceRequest);

    if (serviceResponse == null) {
      throw new Exception("Can not connect to Server");
    }

    return serviceResponse.getServices();
  }

  protected void findEnabledServices(ModelMap map, List<String> findList,
      String serviceTypes) throws Exception {
    List<TrueService> trueServices = findTrueServices(map, findList);

    trueServices.sort(Comparator.comparing(TrueService::getServiceCode));

    List<TrueService> enabledServices = new ArrayList<>();
    if (trueServices != null) {
      for (TrueService trueService : trueServices) {
        if (new Character('Y').equals(trueService.getStatus())) {
          CardType cardType = CardType.getCardType(trueService.getServiceCode());
          if (cardType != null) {
            trueService.setServiceCodeName(cardType.getName());
          }
          enabledServices.add(trueService);
        }
      }
    }

    map.put(ENABLED_SERVICES, enabledServices);
    SessionUtil.setAttribute(ENABLED_SERVICES, enabledServices);
  }

  public InvoiceAttribute getInvoiceAttribute(Invoice invoice){
    for(InvoiceAttribute invoiceAttribute: invoice.getInvoiceAttributes()){
      if(invoiceAttribute.getInvoiceAttributeTypeId().equals(InvoiceAttributeType.INV_PERIOD.name())){
        return invoiceAttribute;
      }
    }
    return null;
  }

}
