<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ewallet" uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<link rel="stylesheet" href="<c:url value="/assets/development/css/bill_style.css"/>">

<div id="print_frame" style="margin-top: -18px">
    <c:forEach var="item" items="${transactionsPinCode.serials}" varStatus="rowId">
        <table class="bill" cellspacing="0px" cellpadding="0px"
               style="border-collapse: collapse; margin-bottom: 10px">
            <tr>
                <td style="max-height: 85px; height: 85px; text-align: center;" colspan="2">
                    <strong><img src="../../../assets/images/icon/payment/${tenSP}-buycard-logo.png"
                         style="max-width: 100%; max-height: 100%"></strong>
                </td>
            </tr>
            <tr>
                <th class="title" colspan="2">
                    <c:set var="menhGia" value="${ewallet:formatNumber(item.price)}" scope="page"/>
                    <strong style="font-size: 24px; font-weight:bold;">${tenSP}&nbsp;${ewallet:splitNumber(menhGia)}k</strong>
                </th>
            </tr>
            <tr>
                <th class="" colspan="2">
                    <strong id="pin-code" style="font-size: 16px; font-weight:bold">
                        <spring:message code="epin.card.code"/>:&nbsp;${ewallet:printRegex(item.pin)}
                    </strong>
                </th>
            </tr>
            <tr>
                <th class="" colspan="2">
                    <strong style="font-size: 16px ;font-weight:bold">Serial:&nbsp;${item.serial}</strong>
                </th>
            </tr>
            <tr>
                <th class="" colspan="2" style="font-size: 18px">
                    HSD:&nbsp;<output id="card-expire-date"><fmt:formatDate
                        value="${item.expiredDate}"
                        pattern="dd/MM/yyyy"/></output>
                </th>
            </tr>
            <tr>
                <td class="content-sm" colspan="2" style="font-size: 15px">
                    <c:choose>
                        <c:when test="${item.cardType eq 'DT_VNP'}">
                            <c:if test="${item.price eq '20000'}">
                                <spring:message code="bill.pincard.code"/>:&nbsp;<spring:message code="bill.pincard.3G.Vina.VNP20"/>
                            </c:if>
                            <c:if test="${item.price eq '50000'}">
                                <spring:message code="bill.pincard.code"/>:&nbsp;<spring:message code="bill.pincard.3G.Vina.VNP50"/>
                            </c:if>
                            <c:if test="${item.price eq '100000'}">
                                <spring:message code="bill.pincard.code"/>:&nbsp;<spring:message code="bill.pincard.3G.Vina.VNP100"/>
                            </c:if>
                        </c:when>
                        <c:when test="${item.cardType eq 'DT_VMS'}">
                            <spring:message
                                    code="bill.pincard.code"/>:&nbsp;<spring:message code="bill.pincard.3G.Mobi"/>
                        </c:when>
                        <c:when test="${item.cardType eq 'DT_VTM'}">
                            <spring:message
                                    code="bill.pincard.code"/>:&nbsp;<spring:message code="bill.pincard.3G.Viettel"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message
                                    code="bill.pincard.code"/>:&nbsp;*100*${item.pin}#Call</span>
                        </c:otherwise>
                    </c:choose>

                </td>
            </tr>


            <tr style="border-top: dashed 1px black;">
                <td class="content-sm" colspan="2" style="font-size: 18px">
                    <spring:message code="transaction.api.detail.time"/>&nbsp;<output
                        id="transaction-date"><fmt:formatDate
                        value="${transactionsPinCode.creationDate}"
                        pattern="HH:mm:ss dd/MM/yyyy"/></output>
                    </br>
                    <spring:message code="label.bill.payment.transaction.id"/>:&nbsp;<output
                        id="transaction-id">${transactionsPinCode.id}</output>
                    <br/>

                </td>
            </tr>
            <tr style="border-top: dashed 1px black;">
                <td class="content-sm" colspan="2" style="font-size: 18px">
                    <spring:message code="bill.agent.name"/>:&nbsp;<output
                        id="agent-name">${aliasStore == null || aliasStore == '' ? 'N/A' : aliasStore}</output>
                    <br/>
                    <%--<strong style="font-size: 16px;"><output id="address"><spring:message code="bill.agent.address"/>:&nbsp;${userLogin.livingAddress}&#44;&nbsp;${userLogin.communeName}&#44;<br>--%>
                            <%--${userLogin.districtName}&#44;&nbsp;${userLogin.provinceName}</output>--%>
                    <%--</strong>--%>
                    <strong style="font-size: 16px;"><output id="address"><spring:message code="bill.agent.address"/>:&nbsp;
                            ${street1Store}</output>
                    </strong>
                    <br/>
                    <spring:message code="bill.agent.phone"/>:&nbsp;<output
                        id="payerMsisdn">${transactionsPinCode.payerMsisdn == null || transactionsPinCode.payerMsisdn == '' ? 'N/A' : transactionsPinCode.payerMsisdn}</output>
                    <br/>
                </td>
            </tr>
            <%--<tr style="border-top: dashed 1px black;">--%>
                <%--<td class="text-center" colspan="2">--%>
                    <%--<spring:message code="bill.support"/>:&nbsp;<spring:message--%>
                        <%--code="company.phone.support"/><br/>--%>
                    <%--<spring:message code="company.website"/><br/>--%>
                <%--</td>--%>
            <%--</tr>--%>
        </table>
    </c:forEach>
</div>
<script>
  $(document).ready(function () {
    var agentName = $('#agent-name');
    var address = $('#address');
    agentName.val(utfToNormal(agentName.val()));
    address.val(utfToNormal(address.val()));
  })
</script>