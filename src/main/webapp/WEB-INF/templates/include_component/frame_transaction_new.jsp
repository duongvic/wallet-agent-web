<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-xxl-12 col-lg-12">
    <style scoped>
        .table th, .table td {
            padding: 0 !important;
            vertical-align: top;
            border-top: 1px solid #e4eaec;
        }
    </style>

    <div class="panel panel-bordered">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="menu.transaction.recent"/></h3>
            <ul class="panel-actions panel-actions-keep">
                <li><a href="/trans-log/transaction-history"><spring:message
                        code="transaction.detail"/></a></li>
                <%--<input type="hidden" name="serviceTypeId" value="FUND_IN">--%>
            </ul>
        </div>

        <spring:message code="transaction.api.action.success" var="success_title"/>
        <spring:message code="transaction.api.action.fail" var="fail_title"/>
        <spring:message code="label.unknown" var="unknown_title"/>
        <spring:message code="label.bank.type.linkbank" var="linkbank_title"/>
        <spring:message code="label.bank.type.other" var="localbank_title"/>
        <spring:message code="label.bank.type.cash.on.hand" var="cashonhand_title"/>
        <spring:message code="label.wallet.transfer.payer" var="payer"/>
        <spring:message code="label.wallet.transfer.payee" var="payee"/>
        <spring:message code="label.face.value" var="faceValue"/>
        <spring:message code="label.provider.card" var="providerCard"/>
        <div class="panel-body mb-10">
            <table class="table no-border fs13">
                <tbody>
                <c:forEach items="${listTransactions}" var="item">
                    <c:choose>
                        <c:when test="${'FUND_IN' eq item.serviceType || 'WALLET_CASH_OUT' eq item.serviceType}">
                            <c:set var="amount_var" value="+"/>
                            <c:set var="amount_css_var" value="green-600"/>
                        </c:when>
                        <c:when test="${'P2P_TRANSFER' eq item.serviceType}">
                            <c:set var="amount_var"
                                   value="${userLogin.phoneNumber eq item.payerMsisdn ? '-' : '+'}"/>
                            <c:set var="amount_css_var"
                                   value="${userLogin.phoneNumber eq item.payeeMsisdn ? 'green-600' : 'red-600'}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="amount_var" value="-"/>
                            <c:set var="amount_css_var" value="red-600"/>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${'FUND_IN' eq item.serviceType || 'FUND_OUT' eq item.serviceType}">
                            <c:choose>
                                <c:when test="${'LINK_BANK' eq item.orderChannel}">
                                    <c:set var="detail_1" value="${linkbank_title}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="detail_1" value="${localbank_title}"/>
                                    <c:if test="${'Fund-in Cash on hand' eq item.serviceName}">
                                        <c:set var="detail_1" value="${cashonhand_title}"/>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                            <c:set var="detail_2" value="${('LINK_BANK' eq item.orderChannel
                             && item.bankCode != null && item.bankCode ne '') ? item.bankName : unknown_title}"/>
                            <c:if test="${'FUND_IN' eq item.serviceType}">
                                <c:set var="icon_src_var"
                                       value="/assets/images/icon/${unknown_title eq detail_2 ? 'fundin' : item.bankCode}.png"/>
                            </c:if>
                            <c:if test="${'FUND_OUT' eq item.serviceType}">
                                <c:set var="icon_src_var"
                                       value="/assets/images/icon/${unknown_title eq detail_2 ? 'fundout' : item.bankCode}.png"/>
                            </c:if>
                        </c:when>

                        <c:when test="${'PHONE_TOPUP' eq item.serviceType}">
                            <c:forEach var="attribute" items="${item.attributes}">
                                <c:if test="${attribute.transactionAttributeType eq 'PTU_TELCO'}">
                                    <%--<spring:message code="label.provider.name.${attribute.transactionAttributeValue}" var="detail_1_val"/>--%>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/${attribute.transactionAttributeValue}.png"/>
                                </c:if>
                                <c:if test="${attribute.transactionAttributeType eq 'PTU_MSISDN'}">
                                    <%--<c:set var="detail_2" value="${attribute.transactionAttributeValue != null ? attribute.transactionAttributeValue : unknown_title}"/>--%>
                                </c:if>
                            </c:forEach>
                            <c:set var="detail_1"
                                   value="${(detail_1_val != null && detail_1_val ne '') ? detail_1_val : unknown_title}"/>
                        </c:when>

                        <c:when test="${'EPIN' eq item.serviceType}">
                            <c:forEach var="attribute" items="${item.attributes}">
                                <c:if test="${attribute.transactionAttributeType eq 'PTU_CARD_TYPE'}">
                                    <%--<spring:message code="label.provider.name.${attribute.transactionAttributeValue}" var="detail_1_val"/>--%>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/${attribute.transactionAttributeValue}.png"/>
                                </c:if>
                                <c:if test="${attribute.transactionAttributeType eq 'PTU_CARD_FACE_VALUE'}">
                                    <%--<c:set var="detail_2_val" value="${ewallet:formatNumber(attribute.transactionAttributeValue)} Zpoint"/>--%>
                                </c:if>
                            </c:forEach>
                            <%--<spring:message code="transaction.api.detail.card.amount" var="detail_2_label"/>--%>
                            <%--<c:set var="detail_1" value="${(detail_1_val != null && detail_1_val ne '') ? detail_1_val : unknown_title}"/>--%>
                            <%--<c:set var="detail_2" value="${detail_2_val != null && detail_2_val ne '' ? (detail_2_label.concat(' ').concat(detail_2_val)) : unknown_title}"/>--%>
                        </c:when>

                        <c:when test="${'P2P_TRANSFER' eq item.serviceType}">
                            <%--<c:set var="detail_1" value="${payer}: ${item.payerMsisdn}"/>--%>
                            <%--<c:set var="detail_2" value="${payee}: ${item.payeeMsisdn}"/>--%>

                            <c:set var="icon_src_var"
                                   value="/assets/images/icon/money-transfer.png"/>
                        </c:when>

                        <c:when test="${'EXPORT_EPIN' eq item.serviceType}">
                            <%--<c:set var="detail_1" value="${providerCard}: --"/>--%>
                            <%--<c:set var="detail_2" value="${faceValue}: --"/>--%>

                            <c:set var="icon_src_var" value="/assets/images/icon/export_epin.svg"/>
                        </c:when>

                        <c:when test="${'WALLET_CASH_IN' eq item.serviceType || 'WALLET_CASH_OUT' eq item.serviceType}">
                            <%--<c:set var="detail_1" value="${providerCard}: --"/>--%>
                            <%--<c:set var="detail_2" value="${faceValue}: --"/>--%>

                            <c:set var="icon_src_var" value="/assets/images/icon/button/viettelPay.png"/>
                        </c:when>

                        <c:when test="${'BILL_PAYMENT' eq item.serviceType}">
                            <c:choose>
                                <c:when test="${FECreditFinancialCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.FeCredit"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/FEcredit.png"/>
                                </c:when>
                                <c:when test="${HomeCreditFinancialCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.HomeCredit"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/Homecredit.png"/>
                                </c:when>
                                <%--<c:when test="${PrudentialFinancialCode eq item.serviceCode}">--%>
                                    <%--<spring:message code="label.provider.name.Prudential"--%>
                                                    <%--var="detail_1_val"/>--%>
                                    <%--<c:set var="icon_src_var"--%>
                                           <%--value="/assets/images/icon/payment/Prudential.png"/>--%>
                                <%--</c:when>--%>
                                <c:when test="${ShinhanCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.Shinhan"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/Shinhan.png"/>
                                </c:when>
                                <c:when test="${AcsFinancialCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.Acs"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/Acs.png"/>
                                </c:when>
                                <c:when test="${OcbFinancialCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.Ocb"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/Ocb.png"/>
                                </c:when>
                                <c:when test="${MCreditFinancialCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MCredit"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/MCredit.png"/>
                                </c:when>
                                <c:when test="${MiraeAssetFinancialCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/MiraeAsset.png"/>
                                </c:when>

                                <c:when test="${AtmOnlineFinancialCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/AtmOnline.png"/>
                                </c:when>
                                <c:when test="${DrDongFinancialCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/DrDong.png"/>
                                </c:when>
                                <c:when test="${MaritimeBankFinancialCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/Maritime.png"/>
                                </c:when>
                                <c:when test="${PTIFinancialCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/PTI.png"/>
                                </c:when>

                                <c:when test="${FPTCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/FPT.png"/>
                                </c:when>
                                <c:when test="${KPLUSCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/KPlus.png"/>
                                </c:when>
                                <c:when test="${SPTCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/SPT.png"/>
                                </c:when>
                                <c:when test="${SPTPHONECode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/SPT_Phone.png"/>
                                </c:when>
                                <c:when test="${SSTCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/SST.png"/>
                                </c:when>
                                <c:when test="${VETCCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.MiraeAsset"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/VETC.png"/>
                                </c:when>

                                <c:when test="${electricPaymentCode eq item.serviceCode}">
                                    <spring:message code="label.provider.name.ELECTRIC"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/electric_2x.png"/>
                                </c:when>
                                
                                <c:when test="${waterPaymentCodeTrungAn eq item.serviceCode || waterPaymentCodeNhaBe eq item.serviceCode || waterPaymentCodeDongNai eq item.serviceCode || waterPaymentCodeQuangNam eq item.serviceCode || waterPaymentCodeTPHCM eq item.serviceCode || waterPaymentCodeVinhPhuc eq item.serviceCode ||  waterPaymentCodeBaRiaVungTau eq item.serviceCode || waterPaymentCodeDVXDongNai eq item.serviceCode || waterPaymentCodeNhonTrach eq item.serviceCode || waterPaymentCodeBinhDuong eq item.serviceCode || waterPaymentCodeCanTho eq item.serviceCode || waterPaymentCodeBenThanh eq item.serviceCode || waterPaymentCodeChoLon eq item.serviceCode || waterPaymentCodeDaNang eq item.serviceCode || waterPaymentCodeHaiPhong eq item.serviceCode || waterPaymentCodeGiaDinh eq item.serviceCode || waterPaymentCodeTanHoa eq item.serviceCode || waterPaymentCodeHue eq item.serviceCode || waterPaymentCodePhuHoaTan eq item.serviceCode || waterPaymentCodeThuDuc eq item.serviceCode || waterPaymentCodeXNCapNcSHNT eq item.serviceCode || waterPaymentCodeLongKhanh eq item.serviceCode || waterPaymentCodeCaoBang eq item.serviceCode || waterPaymentCodeViwaCo eq item.serviceCode ||  waterPaymentCodeSo3HN eq item.serviceCode ||   waterPaymentCodeHaNam eq item.serviceCode ||  waterPaymentCodeSo2HP eq item.serviceCode || waterPaymentCodeCanTho2 eq item.serviceCode || waterPaymentCodeDongThap eq item.serviceCode || waterPaymentCodePhuMy eq item.serviceCode || waterPaymentCodeSonLa eq item.serviceCode  }">
                                    <spring:message code="label.provider.name.water"
                                                    var="detail_1_val"/>
                                    <c:set var="icon_src_var"
                                           value="/assets/images/icon/payment/water.png"/>
                                </c:when>
                            </c:choose>

                            <%--<c:set var="detail_2" value="${item.payerFullname != null ? item.payerFullname : unknown_title}"/>--%>
                            <%--<c:set var="detail_1" value="${(detail_1_val != null && detail_1_val ne '') ? detail_1_val : unknown_title}"/>--%>
                        </c:when>

                        <c:otherwise>
                            <c:set var="detail_1" value="${unknown_title}"/>
                            <c:set var="detail_2" value="${unknown_title}"/>

                            <c:set var="icon_src_var" value="/assets/images/placeholder100.png"/>
                        </c:otherwise>
                    </c:choose>

                    <c:set var="status_var"
                           value="${item.transactionStatus eq '10' ? success_title : fail_title}"/>
                    <c:set var="status_css_var"
                           value="${item.transactionStatus eq '10' ? 'green-600' : 'red-600'}"/>

                    <tr>
                        <td rowspan="3" style="vertical-align:top;">
                            <img id="trans_icon" src="${icon_src_var}"
                                 style="width: 30px; height: 30px; border: 1px solid #ced8dc; border-radius: 30px;"
                                 onerror="imgError(this)">
                        </td>
                        <td><spring:message code="label.service.name.${item.serviceType}"/></td>
                        <td class="text-right vndZpoint ${amount_css_var}">
                            <b>${amount_var.concat(' ').concat(ewallet:formatNumber(item.realAmount))}</b>
                        </td>
                    </tr>
                    <tr>
                            <%--<td>${detail_1}</td>--%>
                        <td><spring:message code="reim.label.time"></spring:message> <fmt:formatDate
                                value="${item.creationDate}" pattern="dd/MM/yyyy"/></td>
                        <td class="text-right ${status_css_var}">${status_var}</td>
                    </tr>
                    <tr>
                        <td colspan="2">${detail_2}</td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <hr/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%--Handle error images--%>
<script>
  function imgError(image) {
//    image.src = "/assets/images/placeholder100.png";
  }
</script>