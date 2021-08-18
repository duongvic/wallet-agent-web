<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
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
      <li class="breadcrumb-item active"><spring:message code="label.fundin.linkedFundin"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.fundin.link.bank"/></h1></div>
  <div class="page-content">
    <div class="panel">
      <div class="panel-body">
        <div class="row mb-20 ">
          <div class="col-xxl-3 col-lg-3 col-md-3 col-sm-12">
            <img src="/assets/images/bank/${bankName}.png" width="100" class="br3 pull-left">
          </div>
          <div class="col-xxl-5 col-lg-5 col-md-5 col-sm-12">
            <div class="form-group row">
              <div class="col-sm-12">
                <h4 class="pull-left mt-15 ml-20"><spring:message code="label.bank"/> ${bankCode}</h4></br>
              </div>
              <div class="col-sm-12">
                <h4 class="pull-left mt-15 ml-20">${bankAccountNumber}</h4>
              </div>
            </div>


          </div>
          <div class="clr"></div>
        </div>
        <form id="confirmFundInLinkBank" method="post"
              action="/fundin/linkbank/confirmFundInLinkBank">
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message code="fundin.payee.phone"/>: </label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0">${walletId}</p>
            </div>
          </div>
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message code="fundin.money"/>: </label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0">${faceValue}</p>
            </div>
          </div>
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message code="label.fee.fundin"/>: </label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0">${feeAmount}</p>
            </div>
          </div>
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0">Tổng thanh toán: </label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0">${realAmount}</p>
            </div>
          </div>

          <c:if test="${(isOtpRequired==true)}">
            <div class="col-lg-12 col-md-8 mb-5">
              <label><spring:message code="label.otp"/>
                <span style="color: #27ADD0"><sec:authentication
                    property="principal.username"></sec:authentication></span>
                <span></span>
              </label>
            </div>

            <div class="col-md-12 mb-5 offset-md-0">
              <div class="input-group">
                <input type="text" id="codeOTP" name="codeOTP" class="form-control"
                       placeholder="<spring:message code="label.enter.otp"/>" value="${codeOTP}" data-plugin="formatter"
                       data-pattern="[[9999]]-[[9999]]-[[9999]]-[[9999]]" required>
                <span class="input-group-btn">
                                                <button type="button"
                                                        class="btn btn-default btn-outline">
                                                    <i class="fa fa-rotate-left"></i> <spring:message code="common.btn.resend"/></button>
                                              </span>
              </div>
            </div>
            <c:if
                test="${(codeErr != null && codeErr >= 1) || (param.codeErr != null && param.codeErr >= 1) }">
              <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
              </div>
            </c:if>
          </c:if>

          <div class="text-right">
            <a href="/fundin/fundin_linkbank" class="btn btn-default mr-10"><spring:message code="common.btn.back"/></a>
            <button type="submit" class="btn btn-primary"><spring:message code="common.btn.confirm"/></button>
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