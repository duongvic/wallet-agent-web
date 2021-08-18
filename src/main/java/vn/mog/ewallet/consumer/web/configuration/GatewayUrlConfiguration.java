package vn.mog.ewallet.consumer.web.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GatewayUrlConfiguration {
  // --------------------------WalletEndpoint----------------------
  @Value("${param.ewallet.url.customer.balance.get}")
  public String PARA_MEWALLET_URL_CUSTOMER_BALANCE_GET;

  @Value("${param.ewallet.url.customer.commission.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_COMMISSION_GET;

  @Value("${param.ewallet.url.service.find}")
  public String PARAM_EWALLET_URL_SERVICE_FIND;

  @Value("${param.ewallet.url.transferOrder.workflow.reject}")
  public String PARAM_EWALLET_URL_TRANSFERORDER_WORKFLOW_REJECT;

  @Value("${param.ewallet.url.transferOrder.workflow.approve}")
  public String PARAM_EWALLET_URL_TRANSFERORDER_WORKFLOW_APPROVE;

  @Value("${param.ewallet.url.transferOrder.get}")
  public String PARAM_EWALLET_URL_TRANSFERORDER_GET;

  @Value("${param.ewallet.url.transferOrder.update}")
  public String PARAM_EWALLET_URL_TRANSFERORDER_UPDATE;

  @Value("${param.ewallet.url.transferOrder.find}")
  public String PARAM_WALLET_URL_TRANSFERORDER_FIND;

  @Value("${param.ewallet.url.walletTransfer.opt}")
  public String PARAM_EWALLET_URL_WALLETTRANSFER_OPT;

  @Value("${param.ewallet.url.transferOrder.workflow.submit}")
  public String PARAM_EWALLET_URL_TRANSFERORDER_WORKFLOW_SUBMIT;

  @Value("${param.ewallet.url.balanceInquiry}")
  public String PARAM_EWALLET_URL_BALANCEINQUIRY;

  @Value("${param.ewallet.url.fundIn}")
  public String PARAM_EWALLET_URL_FUNDIN;

  @Value("${param.ewallet.url.fundOut}")
  public String PARAM_EWALLET_URL_FUNDOUT;

  @Value("${param.ewallet.url.statsBalance}")
  public String PARAM_EWALLET_URL_STATSBALANCE;

  @Value("${param.ewallet.url.findCustomer}")
  public String PARAM_EWALLET_URL_FINDCUSTOMER;

  @Value("${param.ewallet.url.bank.findBankDirects}")
  public String PARAM_EWALLET_URL_BANK_FINDBANKDIRECTS;

  @Value("${param.ewallet.url.customer.balance}")
  public String PARAM_EWALLET_URL_CUSTOMER_BALANCE;

  @Value("${param.ewallet.url.fundOrder.find}")
  public String PARAM_EWALLET_URL_FUNDORDER_FIND;

  @Value("${param.ewallet.url.customer.fundOrder.find}")
  public String PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_FIND;

  @Value("${param.ewallet.url.customer.fundOrder.create}")
  public String PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_CREATE;

  @Value("${param.ewallet.url.customer.fundOrder.update}")
  public String PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_UPDATE;

  @Value("${param.ewallet.url.customer.fundOrder.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_GET;

  @Value("${param.ewallet.url.fundOrder.workflow.approve}")
  public String PARAM_EWALLET_URL_FUNDORDER_WORKFLOW_APPROVE;

  @Value("${param.ewallet.url.customer.fundOrder.otp.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_OTP_GET;

  @Value("${param.ewallet.url.fundOrder.workflow.reject}")
  public String PARAM_EWALLET_URL_FUNDORDER_WORKFLOW_REJECT;

  @Value("${param.ewallet.url.fundOrder.workflow.submit}")
  public String PARAM_EWALLET_URL_FUNDORDER_WORKFLOW_SUBMIT;

  @Value("${param.ewallet.url.customer.update.blacklist-reason}")
  public String PARAM_EWALLET_URL_CUSTOMER_UPDATE_BLACKLIST_REASON;

  @Value("${param.ewallet.url.customer.moneyTransfer.check}")
  public String PARAM_EWALLET_URL_CUSTOMER_MONEYTRANSFER_CHECK;

  @Value("${param.ewallet.url.customer.moneyTransfer.otp.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_MONEYTRANSFER_OTP_GET;

  @Value("${param.ewallet.url.customer.moneyTransfer.confirm}")
  public String PARAM_EWALLET_URL_CUSTOMER_MONEYTRANSFER_CONFIRM;

  @Value("${param.ewallet.url.customer.bank.link}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANK_LINK;

  @Value("${param.ewallet.url.customer.customterBankDirect}")
  public String PARAM_EWALLET_URL_CUSTOMER_CUSTOMTER_BANKDIRECT;

  @Value("${param.ewallet.url.customer.transaction.topup.request}")
  public String PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_TOPUP_REQUEST;

  @Value("${param.ewallet.url.customer.transaction.result.export}")
  public String PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_RESULT_EXPORT;

  @Value("${param.ewallet.url.customer.bank.fundin}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANK_FUNDIN;

  @Value("${param.ewallet.url.customer.fundOrder.otp.confirm}")
  public String PARAM_EWALLET_URL_CUSTOMER_FUNDORDER_OTP_CONFIRM;

  @Value("${param.ewallet.url.customer.paymentGateWay.bankCharging.request.fundIn}")
  public String PARAM_EWALLET_URL_CUSTOMER_PAYMENTGATEWAY_BANKCHARGING_REQUEST_FUNDIN;

  @Value("${param.ewallet.url.customer.findIn.paymentGateWay.visaCharging.request}")
  public String PARAM_EWALLET_URL_CUSTOMER_FINDIN_PAYMENTGATEWAY_VISACHARGING_REQUEST;

  @Value("${param.ewallet.url.customer.fund-in.payment-gate-way.charging.v3.request}")
  public String PARAM_EWALLET_URL_CUSTOMER_FUNDIN_PAYMENT_GATE_WAY_CHARGING_V3_REQUEST;

  @Value("${param.ewallet.url.customer.bank.fundout}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANK_FUNDOUT;

  @Value("${param.ewallet.url.customer.bank.fundtransfer}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANK_FUNDTRANSFER;

  @Value("${param.ewallet.url.customer.transaction.topup.otp.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_TOPUP_OTP_GET;

  @Value("${param.ewallet.url.customer.wallet.moneyTransfer}")
  public String PARAM_EWALLET_URL_CUSTOMER_WALLET_MONEYTRANSFER;

  @Value("${param.ewallet.url.customer.wallet.money-transfer.otp.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_WALLET_MONEY_TRANSFER_OTP_GET;

  @Value("${param.ewallet.url.send.google.authenticator}")
  public String PARAM_EWALLET_URL_SEND_GOOGLE_AUTHENTICATOR;

  @Value("${param.ewallet.url.customer.bank.bankFundOutNoLinkToBank}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANK_BANKFUNDOUTNOLINKTOBANK;

  @Value("${param.ewallet.url.customer.transaction.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_GET;

  @Value("${param.ewallet.url.customer.payment-security.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_PAYMENT_SECURITY_GET;

  @Value("${param.ewallet.url.customer.payment-security.get-otp}")
  public String PARAM_EWALLET_URL_CUSTOMER_PAYMENT_SECURITY_GET_OTP;

  @Value("${param.ewallet.url.customer.payment-security.register}")
  public String PARAM_EWALLET_URL_CUSTOMER_PAYMENT_SECURITY_REGISTER;

  @Value("${param.ewallet.url.security.payment-pin.change.verify}")
  public String PARAM_EWALLET_URL_SECURITY_PAYMENT_PIN_CHANGE_VERIFY;

  @Value("${param.ewallet.url.security.payment-pin.change}")
  public String PARAM_EWALLET_URL_SECURITY_PAYMENT_PIN_CHANGE;

  @Value("${param.ewallet.url.security.payment-pin.check-exist}")
  public String PARAM_EWALLET_URL_SECURITY_PAYMENT_PIN_CHECK_EXIST;

  @Value("${param.ewallet.url.security.forgotPass.otp.get}")
  public String PARAM_EWALLET_URL_SECURITY_FORGOTPASS_OTP_GET;

  @Value("${param.ewallet.url.security.forgotPass.otp.verify}")
  public String PARAM_EWALLET_URL_SECURITY_FORGOTPASS_OTP_VERIFY;

  @Value("${param.ewallet.url.security.forgotPass.password.set}")
  public String PARAM_EWALLET_URL_SECURITY_FORGOTPASS_PASSWORD_SET;

  @Value("${param.ewallet.url.security.get-security-otp}")
  public String PARAM_EWALLET_URL_SECURITY_GET_SECURITY_OTP;

  @Value("${param.ewallet.url.security.change-password.verify-otp}")
  public String PARAM_EWALLET_URL_SECURITY_CHANGE_PASSWORD_VERIFY_OTP;

  @Value("${param.ewallet.url.security.change-password}")
  public String PARAM_EWALLET_URL_SECURITY_CHANGE_PASSWORD;

  @Value("${param.ewallet.url.security.password.check-exact}")
  public String PARAM_EWALLET_URL_SECURITY_PASSWORD_CHECK_EXACT;

  @Value("${param.ewallet.url.customer.bankItem.create}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANKITEM_CREATE;

  @Value("${param.ewallet.url.customer.bankItem.delete}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANKITEM_DELETE;

  @Value("${param.ewallet.url.customer.bankItem}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANKITEM;

  @Value("${param.ewallet.url.customer.bank.registerPaymentOnline}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANK_REGISTER_PAYMENTONLINE;

  @Value("${param.ewallet.url.customer.recentness.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_RECENTNESS_GET;


  @Value("${param.ewallet.url.customer.account-info.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_ACCOUNT_INFO_GET;

  @Value("${param.ewallet.url.customer.check-account-existed}")
  public String PARAM_EWALLET_URL_CUSTOMER_CHECK_ACCOUNT_EXISTED;
  
  @Value("${param.ewallet.url.customer.profile-check}")
  public String PARAM_EWALLET_URL_CUSTOMER_PROFILE_CHECK;

  @Value("${param.ewallet.url.customer.findCustomerAttribute}")
  public String PARAM_EWALLET_URL_CUSTOMER_FIND_CUSTOMER_ATTRIBUTE;

  @Value("${param.ewallet.url.transaction.findTransactions}")
  public String PARAM_EWALLET_URL_TRANSACTION_FINDTRANSACTIONS;

  @Value("${param.ewallet.url.transaction.getTransaction}")
  public String PARAM_EWALLET_URL_TRANSACTION_GETTRANSACTION;

  @Value("${param.ewallet.url.transaction.exportTransactionLog}")
  public String PARAM_EWALLET_URL_TRANSACTION_EXPORT_TRANSACTIONLOG;

  @Value("${param.ewallet.url.customer.transaction.find}")
  public String PARAM_EWALLET_URL_CUSTOMER_TRANSACTION_FIND;


  //Viettel Pay
  @Value("${param.ewallet.url.cashin}")
  public String PARAM_EWALLET_URL_CASHIN;

  @Value("${param.ewallet.url.cashin.get.fee}")
  public String PARAM_EWALLET_URL_CASHIN_GET_FEE;

  @Value("${param.ewallet.url.cashin.get.otp}")
  public String PARAM_EWALLET_URL_CASHIN_GET_OTP;


  @Value("${param.ewallet.url.cashout}")
  public String PARAM_EWALLET_URL_CASHOUT;

  @Value("${param.ewallet.url.cashout.get.info}")
  public String PARAM_EWALLET_URL_CASHOUT_GET_INFO;

  // --------------------------SystemEndpointImpl----------------------

  @Value("${param.ewallet.url.system.bank-profile.find}")
  public String PARAM_EWALLET_URL_SYSTEM_BANK_PROFILE_FIND;

  @Value("${param.ewallet.url.system.banks.find}")
  public String PARAM_EWALLET_URL_SYSTEM_BANKS_FIND;

  @Value("${param.ewallet.url.customer.bankAccount.create}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANKACCOUNT_CREATE;

  @Value("${param.ewallet.url.customer.bankAccount.update}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANKACCOUNT_UPDATE;

  @Value("${param.ewallet.url.customer.bankAccount.delete}")
  public String PARAM_EWALLET_URL_CUSTOMER_BANKACCOUNT_DELETE;

  @Value("${param.ewallet.url.locations.find}")
  public String PARAM_EWALLET_URL_LOCATIONS_FIND;

  @Value("${param.ewallet.url.customer.profile.image-profile.change}")
  public String PARAM_EWALLET_URL_CUSTOMER_PROFILE_IMAGE_PROFILE_CHANGE;

  @Value("${param.ewallet.url.customer.updateInfo}")
  public String PARAM_EWALLET_URL_CUSTOMER_UPDATEINFO;

  @Value("${param.ewallet.url.system.occupations}")
  public String PARAM_EWALLET_URL_SYSTEM_OCCUPATIONS;

  @Value("${param.ewallet.url.system.positions}")
  public String PARAM_EWALLET_URL_SYSTEM_POSITIONS;

  // --------------------------SecurityService----------------------

  @Value("${param.ewallet.url.auth.security.signin}")
  public String PARAM_EWALLET_URL_AUTH_SECURITY_SIGNIN;

  @Value("${param.ewallet.url.auth.security.sign-out}")
  public String PARAM_EWALLET_URL_AUTH_SECURITY_SIGN_OUT;

  @Value("${param.ewallet.url.auth.security.refresh-token}")
  public String PARAM_EWALLET_URL_AUTH_SECURITY_REFRESH_TOKEN;

  @Value("${param.ewallet.url.security.getJwtUser}")
  public String PARAM_EWALLET_URL_SECURITY_GETJWTUSER;

  @Value("${param.ewallet.url.auth.security.customers.change-password}")
  public String PARAM_EWALLET_URL_AUTH_SECURITY_CUSTOMERS_CHANGE_PASSWORD;

  @Value("${param.ewallet.url.secure.register}")
  public String PARAM_EWALLET_URL_SECURE_REGISTER;

  @Value("${param.ewallet.url.secure.info}")
  public String PARAM_EWALLET_URL_SECURE_INFO;

  // --------------------------ProviderEndpoint----------------------

  @Value("${param.ewallet.url.changeServiceStatus}")
  public String PARAM_EWALLET_URL_CHANGE_SERVICE_STATUS;

  @Value("${param.ewallet.url.provider.findProviders}")
  public String PARAM_EWALLET_URL_PROVIDER_FINDPROVIDERS;

  @Value("${param.ewallet.url.provider.changeStatus}")
  public String PARAM_EWALLET_URL_PROVIDER_CHANGE_STATUS;

  @Value("${param.ewallet.url.provider.updateProvider}")
  public String PARAM_EWALLET_URL_PROVIDER_UPDATEPROVIDER;

  @Value("${param.ewallet.url.provider.getProvider}")
  public String PARAM_EWALLET_URL_PROVIDER_GETPROVIDER;

  // --------------------------PaymentSecurityService----------------------

  @Value("${param.ewallet.url.securityPayment.get}")
  public String PARAM_EWALLET_URL_SECURITY_PAYMENT_GET;

  @Value("${param.ewallet.url.securityPayment.register}")
  public String PARAM_EWALLET_URL_SECURITY_PAYMENT_REGISTER;

  // --------------------------EpinPurchaseOrderEndpoint----------------------

  @Value("${param.ewallet.url.customer.epo.check}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPO_CHECK;

  @Value("${param.ewallet.url.customer.epo.find}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPO_FIND;

  @Value("${param.ewallet.url.customer.epo.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPO_GET;

  @Value("${param.ewallet.url.customer.epo.create}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPO_CREATE;

  @Value("${param.ewallet.url.customer.epo.update}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPO_UPDATE;

  @Value("${param.ewallet.url.customer.epo.otp.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPO_OTP_GET;

  @Value("${param.ewallet.url.customer.epo.attachment.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPO_ATTACHMENT_GET;

  @Value("${param.ewallet.url.customer.epo.buyCard.confirm}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPO_BUYCARD_CONFIRM;

  @Value("${param.ewallet.url.customer.epo.availableCard.get}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPO_AVAILABLECARD_GET;

  @Value("${param.ewallet.url.customer.getMerchantPOReport}")
  public String PARAM_EWALLET_URL_CUSTOMER_GET_MERCHANTPOREPORT;

  @Value("${param.ewallet.url.customer.epinPurchaseOrder.workflow.approve}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPINPURCHASEORDER_WORKFLOW_APPROVE;

  @Value("${param.ewallet.url.customer.epinPurchaseOrder.workflow.reject}")
  public String PARAM_EWALLET_URL_CUSTOMER_EPINPURCHASEORDER_WORKFLOW_REJECT;

  @Value("${param.ewallet.url.card-store-offline.card.dashboard}")
  public String PARAM_EWALLET_URL_CARD_STORE_OFFLINE_CARD_DASHBOARD;

  @Value("${param.ewallet.url.card-store-offline.list.operator}")
  public String PARAM_EWALLET_URL_CARD_STORE_OFFLINE_LIST_OPERATOR;

  @Value("${param.ewallet.url.card-store-offline.get.special.merchant}")
  public String PARAM_EWALLET_URL_CARD_STORE_OFFLINE_GET_SPECIAL_MERCHANT;

  @Value("${param.ewallet.url.card-store.card.dashboard}")
  public String PARAM_EWALLET_URL_CARD_STORE_CARD_DASHBOARD;

  @Value("${param.ewallet.url.card-store.list.operator}")
  public String PARAM_EWALLET_URL_CARD_STORE_LIST_OPERATOR;

  @Value("${param.ewallet.url.card-store.get.special.merchant}")
  public String PARAM_EWALLET_URL_CARD_STORE_GET_SPECIAL_MERCHANT;

  /*card n02*/
  @Value("${param.ewallet.url.card-store-n02.card.dashboard}")
  public String PARAM_EWALLET_URL_CARD_STORE_N02_CARD_DASHBOARD;

  @Value("${param.ewallet.url.card-store-n02.list.operator}")
  public String PARAM_EWALLET_URL_CARD_STORE_N02_LIST_OPERATOR;

  @Value("${param.ewallet.url.card-store-n02.get.special.merchant}")
  public String PARAM_EWALLET_URL_CARD_STORE_N02_GET_SPECIAL_MERCHANT;


  @Value("${param.ewallet.url.customer.export.new.epin}")
  public String PARAM_EWALLET_URL_CUSTOMER_EXPORT_NEW_EPIN;

  // --------------------------CustomerEndpoint----------------------

  @Value("${param.ewallet.url.findCustomers}")
  public String PARAM_EWALLET_URL_FINDCUSTOMERS;

  @Value("${param.ewallet.url.customer.getInfo}")
  public String PARAM_EWALLET_URL_CUSTOMER_GETINFO;

  @Value("${param.ewallet.url.auth.security.password.temporary}")
  public String PARAM_EWALLET_URL_AUTH_SECURITY_PASSWORD_TEMPORARY;

  @Value("${param.ewallet.url.image-profile.get}")
  public String PARAM_EWALLET_URL_IMAGE_PROFILE_GET;

  @Value("${param.ewallet.url.profile.kyc.create-kyc-request}")
  public String PARAM_EWALLET_URL_PROFILE_KYC_CREATE_KYC_REQUEST;

  @Value("${param.ewallet.url.profile.kyc.update-kyc-request-image}")
  public String PARAM_EWALLET_URL_PROFILE_KYC_UPDATE_KYC_REQUEST_IMAGE;

  @Value("${param.ewallet.url.profile.kyc.update-kyc-additional-info}")
  public String PARAM_EWALLET_URL_PROFILE_KYC_UPDATE_KYC_ADDITIONAL_INFO;

  @Value("${param.ewallet.url.profile.kyc.submit-kyc-request}")
  public String PARAM_EWALLET_URL_PROFILE_KYC_SUBMIT_KYC_REQUEST;

  @Value("${param.ewallet.url.profile.kyc.update-store-address}")
  public String PARAM_EWALLET_URL_PROFILE_KYC_UPDATE_STORE_ADDRESS;

  // ------------------Bill Payment ----------------------------------
  @Value("${param.ewallet.url.bill.payment.invoice.get}")
  public String PARAM_EWALLET_URL_BILL_PAYMENT_INVOICE_GET;

  @Value("${param.ewallet.url.bill.payment.invoice.create}")
  public String PARAM_EWALLET_URL_BILL_PAYMENT_INVOICE_CREATE;

  @Value("${param.ewallet.url.bill.payment.invoice.pay.invoice}")
  public String PARAM_EWALLET_URL_BILL_PAYMENT_INVOICE_PAY_INVOICE;

  @Value("${param.ewallet.url.bill.payment.invoice.recheck.invoice}")
  public String PARAM_EWALLET_URL_BILL_PAYMENT_INVOICE_RECHECK_INVOICE;

}
