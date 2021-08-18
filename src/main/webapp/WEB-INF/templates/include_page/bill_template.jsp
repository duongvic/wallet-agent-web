<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="<c:url value="/assets/development/css/bill_style.css"/>">

<div id="print_frame" style="margin-top: -18px">
  <table class="bill" cellspacing="0px" cellpadding="0px">
    <c:if test="${bill_payment_method ne 'electric'}">
      <tr class="content">
        <td style="max-height: 85px; height: 85px; text-align: center;" colspan="2">
          <strong>
            <img
                src="../../../../assets/images/icon/payment/${bill_payment_method}-bill-logo.png"
                style="max-width: 100%; max-height: 100%">
          </strong>
        </td>
      </tr>
    </c:if>
    <tr>
      <th class="title" colspan="2">
        <c:choose>
          <c:when test="${bill_payment_method eq 'SPT_Phone'}">
            <strong style="font-size: 16px;"> <spring:message
                code="label.bill.payment"/>&nbsp;<span><spring:message
                code="label.bill.SPT.Phone"/></span></strong>
          </c:when>
          <c:otherwise>
            <strong style="font-size: 16px;"> <spring:message
                code="label.bill.payment"/>&nbsp;<span><spring:message
                code="label.bill.${bill_payment_method}"/></span></strong>
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
        <%--<c:if test="${bill_payment_method eq 'electric'}">--%>
        <%--<strong style="font-size: 16px;">--%>
        <%--<spring:message code="label.unit"/>&nbsp;EVN HN</strong>--%>
        <%--</c:if>--%>
        <c:if test="${bill_payment_method eq 'water'}">
          <strong style="font-size: 16px;"> <spring:message
              code="label.unit"/>&nbsp;${obj_transaction.serviceName}</strong>
        </c:if>
      </td>
    </tr>
    <tr>
      <td class="content-sm" colspan="2">
        <strong style="font-size: 16px;">
          <spring:message
              code="label.code.customer"/>&nbsp;${cus_reference != null && cus_reference ne '' ? cus_reference : invoice_reference}
        </strong>
      </td>
    </tr>
    <tr>
      <td class="content-sm" colspan="2">
        <strong style="font-size: 16px;">
          <spring:message
              code="label.bill.payment.customer.fullname"/>:&nbsp; ${cus_fullName}
        </strong>
      </td>
    </tr>
    <tr>
      <td class="content-sm" colspan="2">
        <strong style="font-size: 16px;" class="vnd-bill">
          <spring:message code="label.bill.payment.amount.total"/>:&nbsp;
          <output class="number-input">${obj_transaction.amount}</output>
        </strong>
      </td>
    </tr>
    <tr>
      <td class="content-sm" colspan="2">
        <strong style="font-size: 16px;"> <spring:message
            code="label.bill.payment.invoice.period"/>:&nbsp;
          ${inv_period}
        </strong>
      </td>
    </tr>
    <tr>
      <td class="content-sm" colspan="2">
        <strong style="font-size: 16px;"> <spring:message
            code="bill.agent.address"/>:&nbsp;${cus_address}
        </strong>
      </td>
    </tr>

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
