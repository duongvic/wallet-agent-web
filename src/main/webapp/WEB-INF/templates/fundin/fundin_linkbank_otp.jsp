<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" prefix="ewallet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="label.fundin.link.bank"/> - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <jsp:param name="nav" value="fiLinkbank"/>
</jsp:include>
<!-- /menu bar -->


<div class="page page-email">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/fundin"><spring:message code="label.fundin"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="label.fundin.link.bank"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.fundin.link.bank"/></h1></div>
  <div class="page-content">
    <div class="panel">
      <div class="panel-body">
        <div class="row mb-20 ">
          <div class="col-xxl-3 col-lg-3 col-md-3 col-sm-12">
            <img src="/assets/images/bank/${bankCode}.png" width="100" class="br3 pull-left">
          </div>
          <div class="col-xxl-5 col-lg-5 col-md-5 col-sm-12 px-0">
            <div class="form-group row">
              <div class="col-sm-12">
                <h4 class="ml-10">${bankDisplayName}</h4></br>
              </div>
              <div class="col-sm-12">
                <h4 class="pull-left mt-15 ml-20 card-number-input">${bankAccountNumber}</h4>
              </div>
            </div>
          </div>
          <div class="clr"></div>
        </div>
        <form id="confirmFundInLinkBank" method="post"
              action="/fundin/confirm-fundin-link-bank">

          <c:if test="${codeErr != null}">
            <div class="col-md-12 mb-5 offset-md-0 text-danger error-message px-0">
              <c:choose>
                <c:when test="${codeErr =='307' || codeErr =='306' || codeErr =='305' || codeErr =='303' || codeErr =='302' || codeErr =='301' || codeErr =='300' || codeErr =='400'|| codeErr =='401'|| codeErr =='402'|| codeErr =='403'|| codeErr =='404'|| codeErr =='405'|| codeErr =='406'|| codeErr =='407'|| codeErr =='408'|| codeErr =='500'|| codeErr =='501'|| codeErr =='502'|| codeErr =='503'|| codeErr =='504'|| codeErr =='505'|| codeErr =='506'|| codeErr =='507'|| codeErr =='508'|| codeErr =='509'|| codeErr =='510'|| codeErr =='511'|| codeErr =='512'|| codeErr =='600'|| codeErr =='601'|| codeErr =='602'|| codeErr =='603'|| codeErr =='604'|| codeErr =='605'|| codeErr =='606'}">
                  <small><i class="fa fa-times-circle"></i>&nbsp; <spring:message code="error.bank.${codeErr}"/></small>
                </c:when>
                <c:otherwise>
                  <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                </c:otherwise>
              </c:choose>

            </div>
          </c:if>

          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message code="account.bank.holder"/>: </label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0 ctk ">${bankAccountName}</p>
            </div>
          </div>

          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message code="fundin.payee.wallet.number"/>: </label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0">${walletId}</p>
            </div>
          </div>
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message code="fundin.money"/>: </label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0">${ewallet:formatNumber(faceValue)} đ</p>
            </div>
          </div>
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message code="label.fee"/>: </label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0">${ewallet:formatNumber(feeAmount)} đ</p>
            </div>
          </div>
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message code="label.real.amount"/>: </label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0">${ewallet:formatNumber(realAmount)} đ</p>
            </div>
          </div>

          <c:if test="${(isOtpRequired==true)}">
            <div class="col-lg-12 col-md-8 mb-5 px-0">
              <label><spring:message code="label.otp"/>
                <span style="color: #27ADD0"><sec:authentication
                    property="principal.username"></sec:authentication></span>
                <span></span>
              </label>
            </div>

            <div class="col-md-12 mb-5 offset-md-0 px-0">
              <div class="input-group">
                <input type="text" id="codeOTP" name="codeOTP" class="form-control"
                       placeholder="<spring:message code="label.enter.otp"/>" value="${codeOTP}" data-plugin="formatter"
                       data-pattern="[[999999]]" required>
                <span class="input-group-btn hidden">
                                                <button type="button"
                                                        class="btn btn-default btn-outline">
                                                    <i class="fa fa-rotate-left"></i> <spring:message code="common.btn.resend"/> </button>
                                              </span>
              </div>
            </div>
          </c:if>

          <div class="text-right">
            <a href="/fundin/link-bank" class="btn btn-default mr-10"><spring:message code="common.btn.back"/></a>
            <button class="btn btn-primary"><spring:message code="common.btn.confirm"/></button>
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->

</body>

</html>