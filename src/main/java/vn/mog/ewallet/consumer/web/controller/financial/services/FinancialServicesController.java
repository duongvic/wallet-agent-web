package vn.mog.ewallet.consumer.web.controller.financial.services;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN;

import java.util.List;
import java.util.Set;
import java.util.UUID;
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
import vn.mog.ewallet.consumer.web.common.FinancialServicesCodeConstants;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
import vn.mog.ewallet.consumer.web.contract.UserLogin;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.GatewayServiceAPIClient;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.IBillPaymentEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.GetConsumerAccountResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.account.info.bean.Customer;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.Address;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.CreateInvoiceOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.CreateInvoiceOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.GetInvoiceRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.GetInvoiceResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.PayInvoiceOrderRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.PayInvoiceOrderResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean.Invoice;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean.InvoiceAttribute;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.invoice.bean.InvoiceOrder;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.AddressType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;

@Controller
@RequestMapping(value = "/financial-services")
public class FinancialServicesController extends AbstractController {

  public static final String BILL_PAYMENT_CONTROLLER = "/financial-services";
  private static final Logger LOGGER = LogManager.getLogger(FinancialServicesController.class);

  // Session attribute
  private static final String FULL_NAME_ATTRIBUTE = "FULL_NAME";
  private static final String ADDRESS_ATTRIBUTE = "ADDRESS";
  private static final String SSN_ATTRIBUTE = "SSN";
  private static final String OBJ_INVOICE_ATTRIBUTE = "OBJ_INVOICE";
  private static final String OBJ_INVOICE_ORDER_ATTRIBUTE = "OBJ_INVOICE_ORDER";
  private static final String OBJ_TRANSACTION_ATTRIBUTE = "OBJ_TRANSACTION";
  private static final String SERVICE_NAME_PROVIDER_ATTRIBUTE = "SERVICE_NAME_PROVIDER";
  private static final String CUSTOMER_PHONE_ATTRIBUTE = "CUSTOMER_PHONE";

  private static final String SUCCESS = "success";
  private static final String FAILED = "failed";

  @Autowired GatewayUrlConfiguration gatewayUrlConfig;
  @Autowired GatewayServiceAPIClient gatewayAPIClient;
  @Autowired private IBillPaymentEndpoint billPaymentEndpoint;

  private static final String FE_CREDIT_PREFIX = "FEcredit";
  private static final String HOME_CREDIT_PREFIX = "Homecredit";
  private static final String ACS_PREFIX = "Acs";
  private static final String OCB_PREFIX = "Ocb";
  private static final String PRUDENTIAL_PREFIX = "Prudential";
  private static final String SHINHAN_PREFIX = "Shinhan";
  private static final String MCREDIT_PREFIX = "MCredit";
  private static final String MIRAEASSET_PREFIX = "MiraeAsset";
  private static final String ATM_ONLINE_PREFIX = "AtmOnline";
  private static final String DR_DONG_PREFIX = "DrDong";
  private static final String MARITIME_BANK_PREFIX = "Maritime";
  private static final String PTI_PREFIX = "PTI";

  @GetMapping(value = "/{financial_services_method}/management")
  public String billPaymentInfo(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @PathVariable("financial_services_method") String financialServicesMethod)
      throws Exception {
    if (!FE_CREDIT_PREFIX.equals(financialServicesMethod)
        && !HOME_CREDIT_PREFIX.equals(financialServicesMethod)
        && !ACS_PREFIX.equals(financialServicesMethod)
        && !OCB_PREFIX.equals(financialServicesMethod)
        && !PRUDENTIAL_PREFIX.equals(financialServicesMethod)
        && !SHINHAN_PREFIX.equals(financialServicesMethod)
        && !MCREDIT_PREFIX.equals(financialServicesMethod)
        && !MIRAEASSET_PREFIX.equals(financialServicesMethod)
        && !ATM_ONLINE_PREFIX.equals(financialServicesMethod)
        && !DR_DONG_PREFIX.equals(financialServicesMethod)
        && !MARITIME_BANK_PREFIX.equals(financialServicesMethod)
        && !PTI_PREFIX.equals(financialServicesMethod)) {
      return "/about/404";
    }
    try {
      map.put("financial_services_method", financialServicesMethod);

      return "/financial_services/financial_services_management";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/financial_services/financial_services_management";
    }
  }

  @GetMapping(value = "/{financial_services_method}/management/detail-{requestId}")
  public String requestDetail(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @PathVariable("financial_services_method") String financialServicesMethod,
      @PathVariable("requestId") String requestId)
      throws Exception {
    if (!FE_CREDIT_PREFIX.equals(financialServicesMethod)
        && !HOME_CREDIT_PREFIX.equals(financialServicesMethod)
        && !ACS_PREFIX.equals(financialServicesMethod)
        && !OCB_PREFIX.equals(financialServicesMethod)
        && !PRUDENTIAL_PREFIX.equals(financialServicesMethod)
        && !SHINHAN_PREFIX.equals(financialServicesMethod)
        && !MCREDIT_PREFIX.equals(financialServicesMethod)
        && !MIRAEASSET_PREFIX.equals(financialServicesMethod)
        && !ATM_ONLINE_PREFIX.equals(financialServicesMethod)
        && !DR_DONG_PREFIX.equals(financialServicesMethod)
        && !MARITIME_BANK_PREFIX.equals(financialServicesMethod)
        && !PTI_PREFIX.equals(financialServicesMethod)) {
      return "/about/404";
    }
    try {

      GetInvoiceRequest getInvoiceRequest = new GetInvoiceRequest();
      if (FE_CREDIT_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.FE_CREDIT_CODE);
      }
      if (HOME_CREDIT_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.HOME_CREDIT_CODE);
      }
      if (ACS_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.ACS_CODE);
      }
      if (OCB_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.OCB_CODE);
      }
      if (PRUDENTIAL_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.PRUDENTIAL_CODE);
      }
      if (SHINHAN_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.SHINHAN_CODE);
      }
      if (MCREDIT_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.MCREDIT_CODE);
      }
      if (MIRAEASSET_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.MIRAE_ASSET_CODE);
      }
      if (ATM_ONLINE_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.ATM_ONLINE_CODE);
      }
      if (DR_DONG_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.DR_DONG_CODE);
      }
      if (MARITIME_BANK_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.MARITIME_BANK_CODE);
      }
      if (PTI_PREFIX.equals(financialServicesMethod)) {
        getInvoiceRequest.setServiceCode(FinancialServicesCodeConstants.PTI_CODE);
      }
      getInvoiceRequest.setInvoiceReference(requestId);
      GetInvoiceResponse getInvoiceResponse = billPaymentEndpoint.getInvoice(getInvoiceRequest);
      if (getInvoiceResponse == null) {
        return errorHandle(new Exception("Đã có lỗi xảy ra"), map, financialServicesMethod);
      }
      if (getInvoiceResponse != null && getInvoiceResponse.getStatus().getCode() != 0) {

        return errorHandle(
            new Exception(getInvoiceResponse.getStatus().getValue()), map, financialServicesMethod);
      }
      if (getInvoiceResponse != null && getInvoiceResponse.getStatus().getCode() == 0) {
        Invoice objInvoice = getInvoiceResponse.getInvoice();
        if (objInvoice != null) {
          Set<InvoiceAttribute> invoiceAttributes =
              getInvoiceResponse.getInvoice().getInvoiceAttributes();
          if (invoiceAttributes != null) {
            for (InvoiceAttribute invoiceAttribute : invoiceAttributes) {
              if ("CUS_ADDRESS".equals(invoiceAttribute.getInvoiceAttributeTypeId())) {
                map.put("address", invoiceAttribute.getValue());
                SessionUtil.setAttribute(ADDRESS_ATTRIBUTE, invoiceAttribute.getValue());
              } else if ("CUS_NAME".equals(invoiceAttribute.getInvoiceAttributeTypeId())) {
                map.put("full_name", invoiceAttribute.getValue());
                SessionUtil.setAttribute(FULL_NAME_ATTRIBUTE, invoiceAttribute.getValue());
              } else if ("CUS_SSN".equals(invoiceAttribute.getInvoiceAttributeTypeId())) {
                map.put("cmt", invoiceAttribute.getValue());
                SessionUtil.setAttribute(SSN_ATTRIBUTE, invoiceAttribute.getValue());
              }
            }
          }
        }

        map.put("is_partial_payment_allowed", objInvoice.getIsPartialPaymentAllowed());
        map.put("amount", objInvoice.getAmount());
        map.put("due_date", objInvoice.getDueDate());

        SessionUtil.setAttribute(OBJ_INVOICE_ATTRIBUTE, objInvoice);
      }

      map.put("financial_services_method", financialServicesMethod);
      map.put("request_id", requestId);

      return "/financial_services/financial_services_request_detail";
    } catch (Exception e) {

      return errorHandle(e, map, financialServicesMethod);
    }
  }

  @PostMapping(value = "/{financial_services_method}/management/confirm")
  public String confirmBill(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @PathVariable("financial_services_method") String financialServicesMethod)
      throws Exception {
    if (!FE_CREDIT_PREFIX.equals(financialServicesMethod)
        && !HOME_CREDIT_PREFIX.equals(financialServicesMethod)
        && !ACS_PREFIX.equals(financialServicesMethod)
        && !OCB_PREFIX.equals(financialServicesMethod)
        && !PRUDENTIAL_PREFIX.equals(financialServicesMethod)
        && !SHINHAN_PREFIX.equals(financialServicesMethod)
        && !MCREDIT_PREFIX.equals(financialServicesMethod)
        && !MIRAEASSET_PREFIX.equals(financialServicesMethod)
        && !ATM_ONLINE_PREFIX.equals(financialServicesMethod)
        && !DR_DONG_PREFIX.equals(financialServicesMethod)
        && !MARITIME_BANK_PREFIX.equals(financialServicesMethod)
        && !PTI_PREFIX.equals(financialServicesMethod)) {
      return "/about/404";
    }
    try {
      String customerPhone = request.getParameter("customerPhone");
      Invoice objInvoice = (Invoice) SessionUtil.getAttribute(OBJ_INVOICE_ATTRIBUTE);
      CreateInvoiceOrderRequest createInvoiceOrderRequest = new CreateInvoiceOrderRequest();
      createInvoiceOrderRequest.setInvoiceId(objInvoice.getInvoiceId());
      if (objInvoice.getIsPartialPaymentAllowed() == true) {
        String enterAmount = "0";
        if (StringUtils.isNotBlank(request.getParameter("enter_amount")))
          enterAmount = request.getParameter("enter_amount").replaceAll("[^0-9]+", "");
        if (Long.valueOf(enterAmount).longValue() <= 0
            || Long.valueOf(enterAmount).longValue() > objInvoice.getAmount()) {
          throw new Exception("Số tiền thanh toán phải lớn hơn 0 và không quá số tiền nợ");
        }
        createInvoiceOrderRequest.setAmount(Long.valueOf(enterAmount));
        if (Long.valueOf(enterAmount).longValue() < objInvoice.getAmount()) {
          createInvoiceOrderRequest.setPayAll(false);
        }
        if (Long.valueOf(enterAmount).longValue() == objInvoice.getAmount()) {
          createInvoiceOrderRequest.setPayAll(true);
        }
      } else if (objInvoice.getIsPartialPaymentAllowed() == false) {
        createInvoiceOrderRequest.setAmount(objInvoice.getAmount());
        createInvoiceOrderRequest.setPayAll(true);
      }

      createInvoiceOrderRequest.setCustomerPhone(String.valueOf(customerPhone));

      CreateInvoiceOrderResponse createInvoiceOrderResponse =
          billPaymentEndpoint.createInvoiceOrder(createInvoiceOrderRequest);
      InvoiceOrder objInvoiceOrder = null;
      if (createInvoiceOrderResponse != null
          && createInvoiceOrderResponse.getStatus().getCode() == 0) {
        objInvoiceOrder = createInvoiceOrderResponse.getInvoiceOrder();
        map.put(
            "agents_pay",
            objInvoiceOrder.getAmount()
                - ((objInvoiceOrder.getCommission() != null && objInvoiceOrder.getCommission() > 0)
                    ? objInvoiceOrder.getCommission()
                    : 0L));
        map.put("obj_invoice_order", objInvoiceOrder);
        SessionUtil.setAttribute(OBJ_INVOICE_ORDER_ATTRIBUTE, objInvoiceOrder);

      } else {
        map.put("codeErr", createInvoiceOrderResponse.getStatus().getValue());
        throw new Exception(createInvoiceOrderResponse.getStatus().getValue());
      }
      map.put("financial_services_method", financialServicesMethod);
      map.put(
          "invoice_reference",
          (StringUtils.isNotBlank(objInvoice.getInvoiceReference()))
              ? objInvoice.getInvoiceReference()
              : objInvoice.getCustomerReference());
      map.put("full_name", SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE));
      map.put("amount_to_pay", objInvoice.getAmount());
      map.put("due_date", objInvoice.getDueDate());
      map.put("paymentSecurityType", objInvoiceOrder.getPaymentSecurityType());

      map.put("serviceNameProvider", SessionUtil.getAttribute(SERVICE_NAME_PROVIDER_ATTRIBUTE));

      SessionUtil.setAttribute(CUSTOMER_PHONE_ATTRIBUTE, customerPhone);
      map.put("customer_phone", SessionUtil.getAttribute(CUSTOMER_PHONE_ATTRIBUTE));

      return "/financial_services/financial_services_create";
    } catch (Exception e) {

      return errorHandle(e, map, financialServicesMethod);
    }
  }

  @SuppressWarnings("Duplicates")
  @PostMapping(value = "/{financial_services_method}/management/pay")
  public String payBill(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @PathVariable("financial_services_method") String financialServicesMethod)
      throws Exception {
    if (!FE_CREDIT_PREFIX.equals(financialServicesMethod)
        && !HOME_CREDIT_PREFIX.equals(financialServicesMethod)
        && !ACS_PREFIX.equals(financialServicesMethod)
        && !OCB_PREFIX.equals(financialServicesMethod)
        && !PRUDENTIAL_PREFIX.equals(financialServicesMethod)
        && !SHINHAN_PREFIX.equals(financialServicesMethod)
        && !MCREDIT_PREFIX.equals(financialServicesMethod)
        && !MIRAEASSET_PREFIX.equals(financialServicesMethod)
        && !ATM_ONLINE_PREFIX.equals(financialServicesMethod)
        && !DR_DONG_PREFIX.equals(financialServicesMethod)
        && !MARITIME_BANK_PREFIX.equals(financialServicesMethod)
        && !PTI_PREFIX.equals(financialServicesMethod)) {
      return "/about/404";
    }
    try {

      InvoiceOrder objInvoiceOrder =
          (InvoiceOrder) SessionUtil.getAttribute(OBJ_INVOICE_ORDER_ATTRIBUTE);
      PayInvoiceOrderRequest payInvoiceOrderRequest = new PayInvoiceOrderRequest();
      payInvoiceOrderRequest.setRequestId(UUID.randomUUID().toString());
      payInvoiceOrderRequest.setInvoiceOrderId(objInvoiceOrder.getInvoiceOrderId());
      String paymentSecurityCode = request.getParameter("paymentSecurityCode");
      payInvoiceOrderRequest.setOtp(paymentSecurityCode);

      PayInvoiceOrderResponse payInvoiceOrderResponse =
          billPaymentEndpoint.payInvoiceOrder(payInvoiceOrderRequest);

      String redirectPath =
          "redirect:/financial-services/" + financialServicesMethod + "/management/pay/";

      if (payInvoiceOrderResponse != null) {
        if (payInvoiceOrderResponse.getStatus().getCode() == 0) {
          SessionUtil.setAttribute(
              OBJ_TRANSACTION_ATTRIBUTE, payInvoiceOrderResponse.getTransaction());
          getUserBalanceToSession(request);

          return redirectPath + SUCCESS;
        } else {
          map.put("codeErr", payInvoiceOrderResponse.getStatus().getValue());

          return redirectPath + FAILED;
        }
      } else {
        map.put("codeErr", "Can not connect to server!");

        return redirectPath + FAILED;
      }
    } catch (Exception e) {
      return errorHandle(e, map, financialServicesMethod);
    }
  }

  @SuppressWarnings("Duplicates")
  @GetMapping(value = "/{financial_services_method}/management/pay/{result}")
  public String payBill(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @PathVariable("financial_services_method") String financialServicesMethod,
      @PathVariable("result") String result)
      throws Exception {
    switch (result) {
      case SUCCESS:
        UserLogin userLogin = (UserLogin) SessionUtil.getAttribute(SESSION_ACCOUNT_LOGIN);
        Transaction transaction = (Transaction) SessionUtil.getAttribute(OBJ_TRANSACTION_ATTRIBUTE);
        Invoice invoice = (Invoice) SessionUtil.getAttribute(OBJ_INVOICE_ATTRIBUTE);
        map.put("obj_invoice", invoice);
        map.put("obj_transaction", transaction);
        map.put(
            "cus_reference",
            transaction.getAttributeValueByAttributeType("BILL_PAYMENT_CUSTOMER_REFERENCE"));
        map.put("cus_fullName", SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE));
        map.put("cus_ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
        map.put(
            "expired_date",
            invoice.getExpirationDate() != null
                ? invoice.getExpirationDate()
                : invoice.getDueDate());
        map.put(
            "invoice_reference",
            transaction.getAttributeValueByAttributeType("BILL_PAYMENT_INVOICE_REFERENCE"));

        map.put("financial_services_method", financialServicesMethod);

        // Put customer's info
        try {
          String alias = null;
          String street1Store = null;
          GetConsumerAccountResponse baseResponseType = walletEndpoint.accountInfoGet();
          if (baseResponseType.getStatus().getCode() != 0) {
            throw new Exception(baseResponseType.getStatus().getValue());
          }
          Customer customer = baseResponseType.getCustomer();
          List<Address> lstAddress = customer.getAddresses();
          if (lstAddress != null) {
            for (Address item : lstAddress) {
              if (item.getAddressType() == AddressType.OUTLET_ADDRESS.getCode()) {
                alias = item.getAlias();
                street1Store = item.getStreet1();
              }
            }
          }
          map.put("aliasStore", alias);
          map.put("street1Store", street1Store);
          map.put("displayName", customer.getDisplayName());
          map.put("livingAddress", customer.getLivingAddress());
        } catch (Exception ex) {
          LOGGER.error(ex.getMessage());
        }

        return "/financial_services/financial_services_success";

      case FAILED:
        map.put("codeErr", request.getParameter("codeErr"));

        return "/financial_services/financial_services_error";

      default:
        return "/about/404";
    }
  }

  private String errorHandle(Exception e, ModelMap map, String financialServicesMethod) {
    LOGGER.error(e.getMessage(), e);
    String codeErr = e.getMessage();
    map.put("codeErr", codeErr);
    map.put("financial_services_method", financialServicesMethod);

    return "/financial_services/financial_services_error";
  }
}
