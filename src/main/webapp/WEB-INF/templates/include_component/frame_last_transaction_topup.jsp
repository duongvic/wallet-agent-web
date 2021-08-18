<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="panel panel-bordered">
  <div class="panel-body py-10">
    <div class="form-group row mb-10">
      <label><spring:message code="label.transaction.recent"/></label>
    </div>
    <div class="form-group row mb-10 small">

      <c:forEach var="item" items="${listTransactionsTopUp}">
        <div class="col-6 bg-gray py-10">
          <div class="row form-group">
            <div class="col-3">
              <img src="/assets/images/icon/${item.telco}.png" style="width: 30px; height: 30px; border: 1px solid #ced8dc; border-radius: 30px;" onerror="imgError(this)">
            </div>
            <div class="col">
              <spring:message code="content.topUp"/>
              <br/>
              <spring:message code="fundin.payee.phone"/> ${item.phoneNumber}
            </div>

          </div>

        </div>
        <div class="col-6 bg-gray py-10 text-right">
          <b>${ewallet:formatNumber(item.amount)}&nbsp;Zpoint</b>
          <br/>
          <b><fmt:formatDate value="${item.createdDate}" pattern="dd/MM/yyyy HH:mm:ss"/></b>

        </div>
      </c:forEach>
    </div>
  </div>
</div>

<%--Handle error images--%>
<script>
  function imgError(image) {
//    image.src = "/assets/images/placeholder100.png";
  }
</script>