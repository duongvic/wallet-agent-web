<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" prefix="ewallet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="col-sm-6 col-md-12">
  <div class="panel panel-bordered">
    <div class="panel-heading">
      <h3 class="panel-title"><spring:message code = "menu.transaction.recent"/></h3>
      <ul class="panel-actions panel-actions-keep">
        <li><a href="/trans-log/transaction-history"><spring:message code = "transaction.detail"/></a></li>
        <%--<input type="hidden" name="serviceTypeId" value="FUND_IN">--%>
      </ul>
    </div>
    <table class="table no-border fs13">
      <tbody>
      <c:forEach items="${listTransactions}" var="item">
      <tr>
        <td><spring:message code ="content.service"/>:</td>
        <td class="text-right">${item.serviceType}</td>
      </tr>
      <tr>
        <td><spring:message code ="content.face.value"/>:</td>
        <td class="text-right vnd">${ewallet:formatNumber(item.amount)}</td>
      </tr>
      <tr>
        <td><spring:message code ="content.phone.number"/>:</td>
        <td class="text-right">${item.payeeUsername}</td>
      </tr>
      <tr>
        <td><spring:message code ="content.date"/>:</td>
        <td class="text-right"><fmt:formatDate value="${item.creationDate}" pattern="dd/MM/yyyy"/></td>
      </tr>
      </tbody>
      </c:forEach>
    </table>
  </div>
</div>