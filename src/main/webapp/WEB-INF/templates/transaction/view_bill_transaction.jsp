<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="transaction.api.title.page"/> - <spring:message
      code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<div class="page page-email">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message
          code="transaction.api.bread.crumb"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="menu.transaction.history"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="panel panel-bordered">
      <div class="panel-heading">
        <h3 class="panel-title"><spring:message
            code="menu.review.bill"/> ${obj_transaction.id}</h3>
      </div>
      <div class="panel-body py-10">
        <form method="" action="">
          <div class="row">
            <div class="col-md-6 pl-0">
              <div class="col-md-12">
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="transaction.title.transaction.id"/>
                  </label>
                  <div class=" col-sm-6  text-right">
                    <p class="form-control-plaintext pb-0">${obj_transaction.id}</p>
                  </div>
                </div>
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="transaction.api.detail.time"/>
                  </label>
                  <div class=" col-sm-6 text-right">
                    <p class="form-control-plaintext pb-0"><fmt:formatDate
                        value="${obj_transaction.creationDate}"
                        pattern="dd/MM/yyyy"/></p>
                  </div>
                </div>
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="transaction.api.detail.service"/>
                  </label>
                  <div class=" col-sm-6 text-right">
                    <p class="form-control-plaintext pb-0">${obj_transaction.serviceType}</p>
                  </div>
                </div>
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="transaction.api.detail.product"/>
                  </label>
                  <div class=" col-sm-6 text-right">
                    <p class="form-control-plaintext pb-0">${obj_transaction.serviceName}</p>
                  </div>
                </div>
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="transaction.api.detail.actor"/>
                  </label>
                  <div class=" col-sm-6 text-right">
                    <p class="form-control-plaintext pb-0">${cus_fullName}</p>
                  </div>
                </div>
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="transaction.api.detail.content"/>
                  </label>
                  <div class=" col-sm-6 text-right">
                    <p class="form-control-plaintext pb-0">${obj_transaction.orderInfo}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6 pr-0">
              <div class="col-md-12">
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0"><spring:message
                      code="label.face.value"/>:
                  </label>
                  <div class=" col-sm-6  text-right">
                    <p class="form-control-plaintext pb-0">${ewallet:formatNumber(obj_transaction.amount)}</p>
                  </div>
                </div>
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="transaction.api.detail.commission"/>
                  </label>
                  <div class=" col-sm-6 text-right">
                    <p class="form-control-plaintext pb-0">${ewallet:formatNumber(obj_transaction.commision)}</p>
                  </div>
                </div>
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="label.fee.amount"/>:
                  </label>
                  <div class=" col-sm-6 text-right">
                    <p class="form-control-plaintext pb-0">${ewallet:formatNumber(obj_transaction.fee)}</p>
                  </div>
                </div>
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="transaction.api.detail.total.amount"/>
                  </label>
                  <div class=" col-sm-6 text-right">
                    <p class="form-control-plaintext pb-0">${ewallet:formatNumber(obj_transaction.realAmount)}</p>
                  </div>
                </div>
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="transaction.api.detail.method"/>
                  </label>
                  <div class=" col-sm-6 text-right">
                    <p class="form-control-plaintext pb-0">
                      <spring:message code="label.ewallet"/>
                    </p>
                  </div>
                </div>
                <div class="form-group row mb-5">
                  <label class=" col-sm-6 col-form-label pb-0">
                    <spring:message code="help.transaction.status"/>
                  </label>
                  <div class=" col-sm-6 text-right">
                    <p class="form-control-plaintext pb-0">${obj_transaction.errorMessage}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <br/>

          <input type="hidden" name="${_csrf.parameterName}"
                 value="${_csrf.token}"/>
          <%--<c:if test="${allowPrinterElectricityDongNai ne true}">--%>
            <div class="panel-footer py-10 text-right">
              <button type="button" class="btn btn-primary btn-sm ml-10"
                      onclick="printElement()" ${compareAccount eq false ? 'disabled' : ''}><spring:message
                  code="transaction.api.transaction.status.printer.detail"/></button>
            </div>
          <%--</c:if>--%>
        </form>
      </div>
    </div>
  </div>
</div>


<div class="printer">
  <%--bill_template--%>
  <jsp:include page="review_printer_bill_template.jsp"/>
  <%--bill_template--%>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->

</body>

</html>