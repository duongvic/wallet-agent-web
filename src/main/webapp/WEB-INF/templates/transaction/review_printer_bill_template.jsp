<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="<c:url value="/assets/development/css/bill_style.css"/>">


<div id="print_frame" style="margin-top: -18px">
  <table class="bill" cellspacing="0px" cellpadding="0px">
    <c:if test="${obj_transaction.serviceCode ne electricPaymentCode}">
      <tr>
        <td style="max-height: 85px; height: 85px; text-align: center;" colspan="2">
          <strong>
            <c:choose>
              <c:when test="${obj_transaction.serviceCode eq FECreditFinancialCode}">
                <c:set var="img" value="FEcredit" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq HomeCreditFinancialCode}">
                <c:set var="img" value="Homecredit" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq PrudentialFinancialCode}">
                <c:set var="img" value="Prudential" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq ShinhanCode}">
                <c:set var="img" value="Shinhan" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq AcsFinancialCode}">
                <c:set var="img" value="Acs" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq OcbFinancialCode}">
                <c:set var="img" value="Ocb" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq MCreditFinancialCode}">
                <c:set var="img" value="MCredit" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq MiraeAssetFinancialCode}">
                <c:set var="img" value="MiraeAsset" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq AtmOnlineFinancialCode}">
                <c:set var="img" value="AtmOnline" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq DrDongFinancialCode}">
                <c:set var="img" value="DrDong" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq MaritimeBankFinancialCode}">
                <c:set var="img" value="Maritimebank" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq PTIFinancialCode}">
                <c:set var="img" value="PTI" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq FPTCode}">
                <c:set var="img" value="FPT" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq KPLUSCode}">
                <c:set var="img" value="KPlus" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq SPTCode}">
                <c:set var="img" value="SPT" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq SPTPHONECode}">
                <c:set var="img" value="SPT_Phone" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq SSTCode}">
                <c:set var="img" value="SST" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq VETCCode}">
                <c:set var="img" value="VETC" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq VETCCode}">
                <c:set var="img" value="VETC" scope="request"></c:set>
              </c:when>
              <c:when test="${obj_transaction.serviceCode eq electricPaymentCode}">
                <c:set var="img" value="electric" scope="request"></c:set>
              </c:when>
              <c:otherwise>
                <c:set var="img" value="water" scope="request"></c:set>
              </c:otherwise>
            </c:choose>
            <img
                src="../../../../assets/images/icon/payment/${img}-bill-logo.png"
                style="max-width: 100%; max-height: 100%">
          </strong>
        </td>
      </tr>
    </c:if>
    <tr>
      <th class="title" colspan="2">
        <c:choose>
          <c:when test="${obj_transaction.serviceCode eq FECreditFinancialCode
                                 || obj_transaction.serviceCode eq HomeCreditFinancialCode
                                 || obj_transaction.serviceCode eq PrudentialFinancialCode
                                 || obj_transaction.serviceCode eq ShinhanCode
                                 || obj_transaction.serviceCode eq AcsFinancialCode
                                 || obj_transaction.serviceCode eq OcbFinancialCode
                                 || obj_transaction.serviceCode eq MCreditFinancialCode
                                 || obj_transaction.serviceCode eq MiraeAssetFinancialCode
                                 || obj_transaction.serviceCode eq AtmOnlineFinancialCode
                                 || obj_transaction.serviceCode eq DrDongFinancialCode
                                 || obj_transaction.serviceCode eq MaritimeBankFinancialCode
                                 || obj_transaction.serviceCode eq PTIFinancialCode
                                 || obj_transaction.serviceCode eq FPTCode
                                 || obj_transaction.serviceCode eq KPLUSCode
                                 || obj_transaction.serviceCode eq SPTCode
                                 || obj_transaction.serviceCode eq SPTPHONECode
                                 || obj_transaction.serviceCode eq SSTCode
                                 || obj_transaction.serviceCode eq VETCCode
                                 || obj_transaction.serviceCode eq electricPaymentCode
                                 }">
            <strong style="font-size: 16px;">
              <spring:message
                  code="label.service.product.content.${obj_transaction.serviceCode}"/>
            </strong>
          </c:when>
          <c:otherwise>
            <strong style="font-size: 16px;"> <spring:message
                code="label.bill.payment.water"/>
            </strong>
          </c:otherwise>
        </c:choose>
      </th>
    </tr>
    <tr>
      <th class="title" colspan="2">
        <strong style="font-size: 18px;"> <spring:message
            code="label.bill.payment.pay"/></strong>
      </th>
    </tr>
    <tr>
      <td class="content-sm" colspan="2">
        <c:if test="${bill_payment_method eq 'water'}">
          <strong style="font-size: 16px;"> <spring:message
              code="label.unit"/>&nbsp;${obj_transaction.serviceName}</strong>
        </c:if>
      </td>
    </tr>
    <tr>
      <td class="" colspan="2">
        <strong style="font-size: 16px;">
          <spring:message
              code="label.code.customer"/>&nbsp;${cus_reference != null && cus_reference ne '' ? cus_reference : invoice_reference}
        </strong>
      </td>
    </tr>
    <c:if test="${cus_fullName ne null}">
      <tr>
        <td class="" colspan="2">
          <strong style="font-size: 16px;">
            <spring:message
                code="label.bill.payment.customer.fullname"/>:&nbsp; ${cus_fullName}
          </strong>
        </td>
      </tr>
    </c:if>

    <c:if test="${obj_transaction.amount != 0 || obj_transaction.amount != null}">
      <tr>
        <td class="" colspan="2">
          <strong style="font-size: 16px;" class="vnd-bill">
            <spring:message code="label.bill.payment.amount.total"/>:&nbsp;
            <output class="number-input">${obj_transaction.amount}</output>
          </strong>
        </td>
      </tr>
    </c:if>

    <c:if test="${inv_period ne null}">
      <tr>
        <td class="" colspan="2">
          <strong style="font-size: 16px;"> <spring:message
              code="label.bill.payment.invoice.period"/>:&nbsp;${inv_period}
          </strong>
        </td>
      </tr>
    </c:if>

    <c:if test="${cus_ssn ne null}">
      <tr class="">
        <td class="" colspan="2">
          <strong style="font-size: 16px;"> <spring:message
              code="label.bill.payment.customer.ssn"/>:&nbsp;${cus_ssn}
          </strong>
        </td>
      </tr>
    </c:if>

    <c:if test="${cus_address ne null }">
      <tr>
        <td class="" colspan="2">
          <strong style="font-size: 16px;"> <spring:message
              code="bill.agent.address"/>:&nbsp;${cus_address}
          </strong>
        </td>
      </tr>
    </c:if>

    <%--2--%>
    <tr style="font-size: small; border-top: dashed 1px black;">
      <td class="" colspan="2">
        <strong style="font-size: 16px;" class="vnd-bill"> <spring:message
            code="label.bill.payment.amount"/>:&nbsp;<output
            class="number-input">${obj_transaction.amount}</output>
        </strong>
      </td>
    </tr>
    <tr>
      <td class="" colspan="2">
        <strong style="font-size: 16px;" class="vnd-bill"> <spring:message
            code="label.bill.payment.fees.transaction"/>:&nbsp;${obj_transaction.fee}
        </strong>
      </td>
    </tr>
    <tr>
      <td class="" colspan="2">
        <c:set var="totalAmountTransaction"
               value="${(obj_transaction.amount) + (obj_transaction.fee)}" scope="page"/>
        <strong style="font-size: 18px; font-weight:bold;" class="vnd-bill"> <spring:message
            code="label.bill.payment.amount.total.transaction"/>:&nbsp;<output
            class="number-input">${totalAmountTransaction}</output>
        </strong>
      </td>
    </tr>
    <tr>
      <td class="" colspan="2">
        <strong style="font-size: 16px;"> <spring:message
            code="label.bill.payment.transaction.time"/>:
          <output id="transaction-date"><fmt:formatDate
              value="${obj_transaction.creationDate}"
              pattern="HH:mm:ss dd/MM/yyyy"/></output>
        </strong>
        <br/>
        <strong style="font-size: 16px;">
          <spring:message code="label.bill.payment.transaction.id"/>:
          <output id="transaction-id">${obj_transaction.id}</output>
        </strong>
      </td>
    </tr>

    <%--3--%>
    <tr style="border-top: dashed 1px black;">
      <td class="" colspan="2">
        <strong style="font-size: 16px;"> <spring:message code="bill.agent.name"/>:&nbsp;<output
            id="agent-name">${aliasStore == null || aliasStore == '' ? 'N/A' : aliasStore}</output>
        </strong>
        <br/>
        <%--<strong style="font-size: 16px;"><output id="address"><spring:message code="bill.agent.address"/>:&nbsp;${userLogin.livingAddress}&#44;&nbsp;${userLogin.communeName}&#44;<br>--%>
        <%--${userLogin.districtName}&#44;&nbsp;${userLogin.provinceName}</output>--%>
        <%--</strong>--%>
        <strong style="font-size: 16px;">
          <output id="address"><spring:message code="bill.agent.address"/>:&nbsp;
            ${street1Store}</output>
        </strong>
        <br/>
        <strong style="font-size: 16px;"><spring:message code="bill.agent.phone"/>:&nbsp;<output
            id="payerMsisdn">${obj_transaction.payerMsisdn == null || obj_transaction.payerMsisdn == '' ? 'N/A' : obj_transaction.payerMsisdn}</output>
        </strong>
      </td>
    </tr>

    <%--4--%>
    <%--<tr style="border-top: dashed 1px black;">--%>
    <%--<td class="text-center" colspan="2">--%>
    <%--<strong style="font-size: 16px;"> <spring:message--%>
    <%--code="bill.support"/>:&nbsp;<spring:message--%>
    <%--code="company.phone.support"/>--%>
    <%--</strong>--%>
    <%--<br/>--%>
    <%--<strong style="font-size: 16px;"><spring:message--%>
    <%--code="company.website"/><br/></strong>--%>
    <%--</td>--%>
    <%--</tr>--%>
  </table>
</div>
<script>
  $(document).ready(function () {
    var agentName = $('#agent-name');
    var address = $('#address');
    var cusFullName = $('#cus-fullName');

    agentName.val(utfToNormal(agentName.val()));
    address.val(utfToNormal(address.val()));
    cusFullName.val(utfToNormal(cusFullName.val()));
  })
</script>
