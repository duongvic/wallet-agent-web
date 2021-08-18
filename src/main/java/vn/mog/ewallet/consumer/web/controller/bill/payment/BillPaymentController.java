package vn.mog.ewallet.consumer.web.controller.bill.payment;

import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_BARIA_VUNGTAU;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_BEN_THANH;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_BINH_DUONG;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_CAN_THO;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_CAN_THO_2;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_CAO_BANG;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_CHO_LON;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_DA_NANG;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_DONG_NAI;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_DONG_THAP;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_DVXD_DONGNAI;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_GIA_DINH;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_HAI_PHONG;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_HA_NAM;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_HUE;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_LONG_KHANH;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_NHA_BE;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_NHON_TRACH;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_PHU_HOA_TAN;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_PHU_MY;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_QUANG_NAM;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_SO2_HP;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_SO3_HN;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_SON_LA;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_TAN_HOA;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_THU_DUC;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_TP_HCM;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_TRUNG_AN;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_VINH_PHUC;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_VIWACO;
import static vn.mog.ewallet.consumer.web.common.PaymentCodeConstants.WATER_SERVICE_CODE_XI_NGHIEP_CAP_NUOC_SH_NT;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.mog.ewallet.consumer.web.common.PaymentCodeConstants;
import vn.mog.ewallet.consumer.web.configuration.GatewayUrlConfiguration;
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
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.InvoiceAttributeType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.AddressType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.ServiceType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.WaterBillPaymentType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.transaction.beans.Transaction;
import vn.mog.ewallet.consumer.web.util.tools.SessionUtil;


@Controller
@RequestMapping(value = "/bill-payment")
public class BillPaymentController extends AbstractController {

  public static final String BILL_PAYMENT_CONTROLLER = "/bill-payment";
  private static final Logger LOGGER = LogManager.getLogger(BillPaymentController.class);

  // Session attribute
  private static final String FULL_NAME_ATTRIBUTE = "FULL_NAME";
  private static final String ADDRESS_ATTRIBUTE = "ADDRESS";
  private static final String SSN_ATTRIBUTE = "SSN";
  private static final String OBJ_INVOICE_ATTRIBUTE = "OBJ_INVOICE";
  private static final String OBJ_INVOICE_ORDER_ATTRIBUTE = "OBJ_INVOICE_ORDER";
  private static final String OBJ_TRANSACTION_ATTRIBUTE = "OBJ_TRANSACTION";
  private static final String SERVICE_NAME_PROVIDER_ATTRIBUTE = "SERVICE_NAME_PROVIDER";
  private static final String CUSTOMER_PHONE_ATTRIBUTE = "CUSTOMER_PHONE";

  private static final String ELECTRIC_PREFIX = "electric";
  private static final String WATER_PREFIX = "water";
  private static final String INTERNET_PREFIX = "internet";
  private static final String CABLE_TELEVISION_PREFIX = "cable-television";

  private static final String FPT_PREFIX = "FPT";
  private static final String KPLUS_PREFIX = "KPlus";
  private static final String SPT_PREFIX = "SPT";
  private static final String SPT_PHONE_PREFIX = "SPT_Phone";
  private static final String SST_PREFIX = "SST";
  private static final String VETC_PREFIX = "VETC";


  private static final String SUCCESS = "success";
  private static final String FAILED = "failed";

  private static final List<String> PREFIXS = Arrays
      .asList(ELECTRIC_PREFIX, WATER_PREFIX, INTERNET_PREFIX, CABLE_TELEVISION_PREFIX, FPT_PREFIX,
          KPLUS_PREFIX, SPT_PREFIX, SPT_PHONE_PREFIX, SST_PREFIX , VETC_PREFIX);

  @Autowired
  GatewayUrlConfiguration gatewayUrlConfig;
  @Autowired
  GatewayServiceAPIClient gatewayAPIClient;
  @Autowired
  private IBillPaymentEndpoint billPaymentEndpoint;

  @GetMapping(value = "/{bill_payment_method}/management")
  public String billPaymentInfo(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("bill_payment_method") String billPaymentMethod)
      throws Exception {

    if (!ELECTRIC_PREFIX.equals(billPaymentMethod) && !WATER_PREFIX.equals(billPaymentMethod) && !FPT_PREFIX.equals(billPaymentMethod)
        && !KPLUS_PREFIX.equals(billPaymentMethod)
        && !SPT_PREFIX.equals(billPaymentMethod)
        && !SPT_PHONE_PREFIX.equals(billPaymentMethod)
        && !SST_PREFIX.equals(billPaymentMethod)
        && !VETC_PREFIX.equals(billPaymentMethod)) {
      return "/about/404";
    }
    try {
      if (WATER_PREFIX.equals(billPaymentMethod)) {
        // Find enable Service
        super.findEnabledServices(map, Arrays
                .asList(WATER_SERVICE_CODE_TRUNG_AN,
                    WATER_SERVICE_CODE_NHA_BE,
                    WATER_SERVICE_CODE_DONG_NAI,
                    WATER_SERVICE_CODE_QUANG_NAM,
                    WATER_SERVICE_CODE_TP_HCM,
                    WATER_SERVICE_CODE_VINH_PHUC,
                    WATER_SERVICE_CODE_BARIA_VUNGTAU,
                    WATER_SERVICE_CODE_DVXD_DONGNAI,
                    WATER_SERVICE_CODE_NHON_TRACH,
                    WATER_SERVICE_CODE_BINH_DUONG,
                    WATER_SERVICE_CODE_CAN_THO,
                    WATER_SERVICE_CODE_BEN_THANH,
                    WATER_SERVICE_CODE_CHO_LON,
                    WATER_SERVICE_CODE_DA_NANG,
                    WATER_SERVICE_CODE_HAI_PHONG,
                    WATER_SERVICE_CODE_GIA_DINH,
                    WATER_SERVICE_CODE_TAN_HOA,
                    WATER_SERVICE_CODE_HUE,
                    WATER_SERVICE_CODE_PHU_HOA_TAN,
                    WATER_SERVICE_CODE_THU_DUC,
                    WATER_SERVICE_CODE_XI_NGHIEP_CAP_NUOC_SH_NT,
                    WATER_SERVICE_CODE_LONG_KHANH,
                    WATER_SERVICE_CODE_CAO_BANG,
                    WATER_SERVICE_CODE_VIWACO,
                    WATER_SERVICE_CODE_SO3_HN,
                    WATER_SERVICE_CODE_HA_NAM,
                    WATER_SERVICE_CODE_SO2_HP,
                    WATER_SERVICE_CODE_CAN_THO_2,
                    WATER_SERVICE_CODE_DONG_THAP,
                    WATER_SERVICE_CODE_PHU_MY,
                    WATER_SERVICE_CODE_SON_LA),
            ServiceType.BILL_PAYMENT.name());

      }
      map.put("bill_payment_method", billPaymentMethod);

      return "/bill_payment/bill_payment_management";
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      String codeErr = e.getMessage();

      map.put("codeErr", codeErr);

      return "/bill_payment/bill_payment_management";
    }
  }

  @GetMapping(value = "/{bill_payment_method}/management/detail-{requestId}")
  public String requestDetail(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("bill_payment_method") String billPaymentMethod,
      @PathVariable("requestId") String requestId) throws Exception {
    String paramProviderName = null;

    if (!ELECTRIC_PREFIX.equals(billPaymentMethod) && !WATER_PREFIX.equals(billPaymentMethod) && !FPT_PREFIX.equals(billPaymentMethod)
        && !KPLUS_PREFIX.equals(billPaymentMethod)
        && !SPT_PREFIX.equals(billPaymentMethod)
        && !SPT_PHONE_PREFIX.equals(billPaymentMethod)
        && !SST_PREFIX.equals(billPaymentMethod)
        && !VETC_PREFIX.equals(billPaymentMethod)) {
      return "/about/404";
    }
    try {
      GetInvoiceRequest getInvoiceRequest = new GetInvoiceRequest();
      if (ELECTRIC_PREFIX.equals(billPaymentMethod)) {
        getInvoiceRequest.setServiceCode(PaymentCodeConstants.ELECTRIC_SERVICE_CODE);
      }
      if (FPT_PREFIX.equals(billPaymentMethod)) {
        getInvoiceRequest.setServiceCode(PaymentCodeConstants.FPT_CODE);
      }
      if (KPLUS_PREFIX.equals(billPaymentMethod)) {
        getInvoiceRequest.setServiceCode(PaymentCodeConstants.KPLUS_CODE);
      }
      if (SPT_PREFIX.equals(billPaymentMethod)) {
        getInvoiceRequest.setServiceCode(PaymentCodeConstants.SPT_CODE);
      }
      if (SPT_PHONE_PREFIX.equals(billPaymentMethod)) {
        getInvoiceRequest.setServiceCode(PaymentCodeConstants.SPT_PHONE_CODE);
      }
      if (SST_PREFIX.equals(billPaymentMethod)) {
        getInvoiceRequest.setServiceCode(PaymentCodeConstants.SST_CODE);
      }
      if (VETC_PREFIX.equals(billPaymentMethod)) {
        getInvoiceRequest.setServiceCode(PaymentCodeConstants.VETC_CODE);
      }
      if (WATER_PREFIX.equals(billPaymentMethod)) {
        String paramServiceCodeWater = request.getParameter("serviceCode");
        if (null != paramServiceCodeWater) {
          getInvoiceRequest.setServiceCode(paramServiceCodeWater);
        } else {
          getInvoiceRequest.setServiceCode(PaymentCodeConstants.WATER_SERVICE_CODE_TRUNG_AN);
        }
        paramProviderName = String
            .valueOf(WaterBillPaymentType.getWaterBillPaymentType(paramServiceCodeWater));
        map.put("serviceNameProvider", paramProviderName);
        SessionUtil.setAttribute(SERVICE_NAME_PROVIDER_ATTRIBUTE, paramProviderName);
      }

      getInvoiceRequest.setInvoiceReference(requestId);
      GetInvoiceResponse getInvoiceResponse = billPaymentEndpoint.getInvoice(getInvoiceRequest);

      if (getInvoiceResponse == null) {
        return errorHandle(new Exception("Đã có lỗi xảy ra"), map, billPaymentMethod);
      }
//      if (getInvoiceResponse != null && getInvoiceResponse.getStatus().getCode() != 0) {
//
//        return errorHandle(new Exception(getInvoiceResponse.getStatus().getValue()), map,
//            billPaymentMethod);
//      }

      if (getInvoiceResponse != null && getInvoiceResponse.getStatus().getCode() == 0) {
        Invoice objInvoice = getInvoiceResponse.getInvoice();
        if (objInvoice != null) {
          Set<InvoiceAttribute> invoiceAttributes = getInvoiceResponse.getInvoice()
              .getInvoiceAttributes();
          if(invoiceAttributes != null){
            for (InvoiceAttribute invoiceAttribute : invoiceAttributes) {
              if ("CUS_ADDRESS".equals(invoiceAttribute.getInvoiceAttributeTypeId())) {
                map.put("address", invoiceAttribute.getValue());
                SessionUtil.setAttribute(ADDRESS_ATTRIBUTE, invoiceAttribute.getValue());
              } else if ("CUS_NAME".equals(invoiceAttribute.getInvoiceAttributeTypeId())) {
                map.put("full_name", invoiceAttribute.getValue());
                SessionUtil.setAttribute(FULL_NAME_ATTRIBUTE, invoiceAttribute.getValue());
              } else if ("CUS_SSN".equals(invoiceAttribute.getInvoiceAttributeTypeId())) {
                SessionUtil.setAttribute(SSN_ATTRIBUTE, invoiceAttribute.getValue());
              }
            }
          }
        }
        map.put("amount", objInvoice.getAmount());
        map.put("due_date", objInvoice.getDueDate());

        SessionUtil.setAttribute(OBJ_INVOICE_ATTRIBUTE, objInvoice);
        map.put("bill_payment_method", billPaymentMethod);
        map.put("request_id", requestId);

        return "/bill_payment/bill_payment_request_detail";
      } else {
        map.put("codeErr", getInvoiceResponse.getStatus().getValue());
        map.put("error",getInvoiceResponse.getStatus().getCode());
        map.put("bill_payment_method", billPaymentMethod);
        return "/bill_payment/bill_payment_error";
      }
    } catch (Exception e) {
      return errorHandle(e, map, billPaymentMethod);
    }
  }

  @PostMapping(value = "/{bill_payment_method}/management/confirm")
  public String confirmBill(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("bill_payment_method") String billPaymentMethod)
      throws Exception {
    if (!ELECTRIC_PREFIX.equals(billPaymentMethod) && !WATER_PREFIX.equals(billPaymentMethod) && !FPT_PREFIX.equals(billPaymentMethod)
        && !KPLUS_PREFIX.equals(billPaymentMethod)
        && !SPT_PREFIX.equals(billPaymentMethod)
        && !SPT_PHONE_PREFIX.equals(billPaymentMethod)
        && !SST_PREFIX.equals(billPaymentMethod)
        && !VETC_PREFIX.equals(billPaymentMethod)) {
      return "/about/404";
    }
    try {
      String customerPhone = request.getParameter("customerPhone");

      Invoice objInvoice = (Invoice) SessionUtil.getAttribute(OBJ_INVOICE_ATTRIBUTE);
      if(!Objects.isNull(getInvoiceAttribute(objInvoice)))
        map.put("inv_period", getInvoiceAttribute(objInvoice).getValue());

      CreateInvoiceOrderRequest createInvoiceOrderRequest = new CreateInvoiceOrderRequest();
      createInvoiceOrderRequest.setInvoiceId(objInvoice.getInvoiceId());
      createInvoiceOrderRequest.setAmount(objInvoice.getAmount());
      if (objInvoice.getIsPartialPaymentAllowed() == false) {
        createInvoiceOrderRequest.setPayAll(true);
      }
      if (objInvoice.getIsPartialPaymentAllowed() == true) {
        createInvoiceOrderRequest.setPayAll(false);
      }

      createInvoiceOrderRequest.setCustomerPhone(String.valueOf(customerPhone));

      CreateInvoiceOrderResponse createInvoiceOrderResponse = billPaymentEndpoint
          .createInvoiceOrder(createInvoiceOrderRequest);
      if (createInvoiceOrderResponse != null
          && createInvoiceOrderResponse.getStatus().getCode() == 0) {
        InvoiceOrder objInvoiceOrder = createInvoiceOrderResponse.getInvoiceOrder();
        map.put("obj_invoice_order", objInvoiceOrder);
        map.put("paymentSecurityType", objInvoiceOrder.getPaymentSecurityType());
        SessionUtil.setAttribute(OBJ_INVOICE_ORDER_ATTRIBUTE, objInvoiceOrder);
        map.put("agents_pay", objInvoiceOrder.getAmount() - (
            (objInvoiceOrder.getCommission() != null && objInvoiceOrder.getCommission() > 0)
                ? objInvoiceOrder.getCommission() : 0L));
      } else {
        map.put("codeErr", createInvoiceOrderResponse.getStatus().getValue());
          return createInvoiceOrderErrorHandle(
              createInvoiceOrderResponse.getStatus().getCode(),
              createInvoiceOrderResponse.getStatus().getValue(),
              map,
              billPaymentMethod
          );
      }
      map.put("bill_payment_method", billPaymentMethod);
      map.put("customer_reference", objInvoice.getCustomerReference());
      map.put("full_name", SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE));
      map.put("amount_to_pay", objInvoice.getAmount());
      map.put("due_date", objInvoice.getDueDate());

      SessionUtil.setAttribute(CUSTOMER_PHONE_ATTRIBUTE, customerPhone);
      map.put("customer_phone", SessionUtil.getAttribute(CUSTOMER_PHONE_ATTRIBUTE));
      if (WATER_PREFIX.equals(billPaymentMethod)) {
        map.put("serviceNameProvider", SessionUtil.getAttribute(SERVICE_NAME_PROVIDER_ATTRIBUTE));
      }

      return "/bill_payment/bill_payment_create";
    } catch (Exception e) {

      return errorHandle(e, map, billPaymentMethod);
    }
  }

  @PostMapping(value = "/{bill_payment_method}/management/pay")
  public String payBill(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("bill_payment_method") String billPaymentMethod)
      throws Exception {
    if (!ELECTRIC_PREFIX.equals(billPaymentMethod) && !WATER_PREFIX.equals(billPaymentMethod) && !FPT_PREFIX.equals(billPaymentMethod)
        && !KPLUS_PREFIX.equals(billPaymentMethod)
        && !SPT_PREFIX.equals(billPaymentMethod)
        && !SPT_PHONE_PREFIX.equals(billPaymentMethod)
        && !SST_PREFIX.equals(billPaymentMethod)
        && !VETC_PREFIX.equals(billPaymentMethod)) {
      return "/about/404";
    }
    try {

      InvoiceOrder objInvoiceOrder = (InvoiceOrder) SessionUtil
          .getAttribute(OBJ_INVOICE_ORDER_ATTRIBUTE);
      PayInvoiceOrderRequest payInvoiceOrderRequest = new PayInvoiceOrderRequest();
      payInvoiceOrderRequest.setRequestId(UUID.randomUUID().toString());
      payInvoiceOrderRequest.setInvoiceOrderId(objInvoiceOrder.getInvoiceOrderId());
      String paymentSecurityCode = request.getParameter("paymentSecurityCode");
      payInvoiceOrderRequest.setOtp(paymentSecurityCode);

      PayInvoiceOrderResponse payInvoiceOrderResponse = billPaymentEndpoint
          .payInvoiceOrder(payInvoiceOrderRequest);

      String redirectPath = "redirect:/bill-payment/" + billPaymentMethod + "/management/pay/";

      map.put("bill_payment_method", billPaymentMethod);
      if (payInvoiceOrderResponse != null) {
        if (payInvoiceOrderResponse.getStatus().getCode() == 0) {
          SessionUtil
              .setAttribute(OBJ_TRANSACTION_ATTRIBUTE, payInvoiceOrderResponse.getTransaction());
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
      return errorHandle(e, map, billPaymentMethod);
    }
  }

  @GetMapping(value = "/{bill_payment_method}/management/pay/{result}")
  public String payBill(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("bill_payment_method") String billPaymentMethod,
      @PathVariable("result") String result) throws Exception {
    switch (result) {
      case SUCCESS:
        Transaction transaction = (Transaction) SessionUtil.getAttribute(OBJ_TRANSACTION_ATTRIBUTE);
        Invoice invoice = (Invoice) SessionUtil.getAttribute(OBJ_INVOICE_ATTRIBUTE);
        //invoice period
        if(!Objects.isNull(getInvoiceAttribute(invoice)))
          map.put("inv_period", getInvoiceAttribute(invoice).getValue());
        map.put("obj_invoice", invoice);
        map.put("obj_transaction", transaction);
        map.put("cus_reference",
            transaction.getAttributeValueByAttributeType("BILL_PAYMENT_CUSTOMER_REFERENCE"));
        map.put("cus_fullName", SessionUtil.getAttribute(FULL_NAME_ATTRIBUTE));
        map.put("cus_address", SessionUtil.getAttribute(ADDRESS_ATTRIBUTE));
        map.put("cus_ssn", SessionUtil.getAttribute(SSN_ATTRIBUTE));
        map.put("expired_date", invoice.getExpirationDate() != null ? invoice.getExpirationDate()
            : invoice.getDueDate());
        map.put("invoice_reference",
            transaction.getAttributeValueByAttributeType("BILL_PAYMENT_INVOICE_REFERENCE"));

        // Put customer's info
        try {
          GetConsumerAccountResponse baseResponseType = walletEndpoint.accountInfoGet();
          if (baseResponseType.getStatus().getCode() != 0) {
            throw new Exception(baseResponseType.getStatus().getValue());
          }
          Customer customer = baseResponseType.getCustomer();
          List<Address> lstAddress = customer.getAddresses();
          String alias = null;
          String street1Store = null;
          if (lstAddress != null) {
            for (Address item : lstAddress) {
              if (item.getAddressType() == AddressType.OUTLET_ADDRESS.getCode()) {
                street1Store = item.getStreet1();
                alias = item.getAlias();
              }
            }
          }
          map.put("aliasStore", alias);
          map.put("street1Store", street1Store);
          map.put("displayName", customer.getDisplayName());
          map.put("livingAddress", customer.getLivingAddress());

          //Không cho in với HD điện Đồng Nai
          String  invoidElectricityDongNai = invoice.getCustomerReference();
          Boolean allowPrinterElectricityDongNai = invoidElectricityDongNai.startsWith("PK");
          map.put("allowPrinterElectricityDongNai", allowPrinterElectricityDongNai);
        } catch (Exception ex) {
          LOGGER.error(ex.getMessage());
        }

        return "/bill_payment/bill_payment_success";

      case FAILED:
        map.put("codeErr", request.getParameter("codeErr"));

        return "/bill_payment/bill_payment_error";

      default:
        return "/about/404";
    }
  }


  private String errorHandle(Exception e, ModelMap map, String billPaymentMethod) {
    LOGGER.error(e.getMessage(), e);
    String codeErr = e.getMessage();

    map.put("codeErr", codeErr);
    map.put("error",codeErr);
    map.put("bill_payment_method", billPaymentMethod);

    return "/bill_payment/bill_payment_error";
  }

  private String createInvoiceOrderErrorHandle(Integer errorCode, String errorMessage, ModelMap map, String billPaymentMethod){
    LOGGER.error(errorMessage);

    map.put("codeErr", errorMessage);
    map.put("error",errorCode);
    map.put("bill_payment_method", billPaymentMethod);
    return "/bill_payment/bill_payment_error";
  }
}