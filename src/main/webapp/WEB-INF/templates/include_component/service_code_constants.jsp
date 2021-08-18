
<%@ page import="vn.mog.ewallet.consumer.web.common.ServiceCodeConstants" %>
<%@ page import="vn.mog.ewallet.consumer.web.common.PaymentCodeConstants" %>
<%@ page import="vn.mog.ewallet.consumer.web.common.FinancialServicesCodeConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="viettelServiceCode" value="<%=ServiceCodeConstants.VIETTEL_SERVICE_CODE%>" scope="application"/>
<c:set var="vinaPhoneServiceCode" value="<%=ServiceCodeConstants.VINAPHONE_SERVICE_CODE%>" scope="application"/>
<c:set var="mobiPhoneServiceCode" value="<%=ServiceCodeConstants.MOBIFONE_SERVICE_CODE%>" scope="application"/>
<c:set var="gMobileServiceCode" value="<%=ServiceCodeConstants.GMOBILE_SERVICE_CODE%>" scope="application"/>
<c:set var="vietnamMobileServiceCode" value="<%=ServiceCodeConstants.VIETNAMOBILE_SERVICE_CODE%>" scope="application"/>

<c:set var="viettel3GServiceCode" value="<%=ServiceCodeConstants.VIETTEL_3G_SERVICE_CODE%>" scope="application"/>
<c:set var="vina3GServiceCode" value="<%=ServiceCodeConstants.VINAPHONE_3G_SERVICE_CODE%>" scope="application"/>
<c:set var="mobile3GServiceCode" value="<%=ServiceCodeConstants.MOBIFONE_3G_SERVICE_CODE%>" scope="application"/>

<c:set var="garenaServiceCode" value="<%=ServiceCodeConstants.GARENA_SERVICE_CODE%>" scope="application"/>
<c:set var="gateServiceCode" value="<%=ServiceCodeConstants.GATE_SERVICE_CODE%>" scope="application"/>
<c:set var="zingServiceCode" value="<%=ServiceCodeConstants.ZING_SERVICE_CODE%>" scope="application"/>
<c:set var="vcoidServiceCode" value="<%=ServiceCodeConstants.VCOIN_SERVICE_CODE%>" scope="application"/>
<c:set var="oncashServiceCode" value="<%=ServiceCodeConstants.ONCASH_SERVICE_CODE%>" scope="application"/>
<c:set var="anpayServiceCode" value="<%=ServiceCodeConstants.ANPAY_SERVICE_CODE%>" scope="application"/>
<c:set var="vinplayServiceCode" value="<%=ServiceCodeConstants.VINPLAY_SERVICE_CODE%>" scope="application"/>

<c:set var="electricPaymentCode" value="<%=PaymentCodeConstants.ELECTRIC_SERVICE_CODE%>" scope="application"/>

<c:set var="waterPaymentCodeTrungAn" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_TRUNG_AN%>" scope="application"/>
<c:set var="waterPaymentCodeNhaBe" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_NHA_BE%>" scope="application"/>
<c:set var="waterPaymentCodeDongNai" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_DONG_NAI%>" scope="application"/>
<c:set var="waterPaymentCodeQuangNam" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_QUANG_NAM%>" scope="application"/>
<c:set var="waterPaymentCodeTPHCM" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_TP_HCM%>" scope="application"/>
<c:set var="waterPaymentCodeVinhPhuc" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_VINH_PHUC%>" scope="application"/>
<c:set var="waterPaymentCodeBaRiaVungTau" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_BARIA_VUNGTAU%>" scope="application"/>
<c:set var="waterPaymentCodeDVXDongNai" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_DVXD_DONGNAI%>" scope="application"/>
<c:set var="waterPaymentCodeNhonTrach" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_NHON_TRACH%>" scope="application"/>
<c:set var="waterPaymentCodeBinhDuong" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_BINH_DUONG%>" scope="application"/>
<c:set var="waterPaymentCodeCanTho" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_CAN_THO%>" scope="application"/>

<c:set var="waterPaymentCodeBenThanh" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_BEN_THANH%>" scope="application"/>
<c:set var="waterPaymentCodeChoLon" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_CHO_LON%>" scope="application"/>
<c:set var="waterPaymentCodeDaNang" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_DA_NANG%>" scope="application"/>
<c:set var="waterPaymentCodeHaiPhong" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_HAI_PHONG%>" scope="application"/>
<c:set var="waterPaymentCodeGiaDinh" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_GIA_DINH%>" scope="application"/>
<c:set var="waterPaymentCodeTanHoa" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_TAN_HOA%>" scope="application"/>
<c:set var="waterPaymentCodeHue" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_HUE%>" scope="application"/>
<c:set var="waterPaymentCodePhuHoaTan" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_PHU_HOA_TAN%>" scope="application"/>
<c:set var="waterPaymentCodeThuDuc" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_THU_DUC%>" scope="application"/>
<c:set var="waterPaymentCodeXNCapNcSHNT" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_XI_NGHIEP_CAP_NUOC_SH_NT%>" scope="application"/>
<c:set var="waterPaymentCodeLongKhanh" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_LONG_KHANH%>" scope="application"/>
<c:set var="waterPaymentCodeCaoBang" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_CAO_BANG%>" scope="application"/>
<c:set var="waterPaymentCodeViwaCo" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_VIWACO%>" scope="application"/>
<c:set var="waterPaymentCodeSo3HN" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_SO3_HN%>" scope="application"/>
<c:set var="waterPaymentCodeHaNam" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_HA_NAM%>" scope="application"/>
<c:set var="waterPaymentCodeSo2HP" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_SO2_HP%>" scope="application"/>
<c:set var="waterPaymentCodeCanTho2" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_CAN_THO_2%>" scope="application"/>
<c:set var="waterPaymentCodeDongThap" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_DONG_THAP%>" scope="application"/>
<c:set var="waterPaymentCodePhuMy" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_PHU_MY%>" scope="application"/>
<c:set var="waterPaymentCodeSonLa" value="<%=PaymentCodeConstants.WATER_SERVICE_CODE_SON_LA%>" scope="application"/>

<c:set var="FECreditFinancialCode" value="<%=FinancialServicesCodeConstants.FE_CREDIT_CODE%>" scope="application"/>
<c:set var="HomeCreditFinancialCode" value="<%=FinancialServicesCodeConstants.HOME_CREDIT_CODE%>" scope="application"/>
<c:set var="PrudentialFinancialCode" value="<%=FinancialServicesCodeConstants.PRUDENTIAL_CODE%>" scope="application"/>
<c:set var="ShinhanCode" value="<%=FinancialServicesCodeConstants.SHINHAN_CODE%>" scope="application"/>
<c:set var="AcsFinancialCode" value="<%=FinancialServicesCodeConstants.ACS_CODE%>" scope="application"/>
<c:set var="OcbFinancialCode" value="<%=FinancialServicesCodeConstants.OCB_CODE%>" scope="application"/>
<c:set var="MCreditFinancialCode" value="<%=FinancialServicesCodeConstants.MCREDIT_CODE%>" scope="application"/>
<c:set var="MiraeAssetFinancialCode" value="<%=FinancialServicesCodeConstants.MIRAE_ASSET_CODE%>" scope="application"/>
<c:set var="AtmOnlineFinancialCode" value="<%=FinancialServicesCodeConstants.ATM_ONLINE_CODE%>" scope="application"/>
<c:set var="DrDongFinancialCode" value="<%=FinancialServicesCodeConstants.DR_DONG_CODE%>" scope="application"/>
<c:set var="MaritimeBankFinancialCode" value="<%=FinancialServicesCodeConstants.MARITIME_BANK_CODE%>" scope="application"/>
<c:set var="PTIFinancialCode" value="<%=FinancialServicesCodeConstants.PTI_CODE%>" scope="application"/>

<c:set var="FPTCode" value="<%=PaymentCodeConstants.FPT_CODE%>" scope="application"/>
<c:set var="KPLUSCode" value="<%=PaymentCodeConstants.KPLUS_CODE%>" scope="application"/>
<c:set var="SPTCode" value="<%=PaymentCodeConstants.SPT_CODE%>" scope="application"/>
<c:set var="SPTPHONECode" value="<%=PaymentCodeConstants.SPT_PHONE_CODE%>" scope="application"/>
<c:set var="SSTCode" value="<%=PaymentCodeConstants.SST_CODE%>" scope="application"/>
<c:set var="VETCCode" value="<%=PaymentCodeConstants.VETC_CODE%>" scope="application"/>



