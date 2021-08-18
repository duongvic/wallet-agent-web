<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="account.bank.card.remove.page"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"></jsp:include>
<!-- /menu bar -->

<div class="page page-email">
  <div class="page-header">
    <div class="page-header-actions"></div>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage"><spring:message code="card.manage.label"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="account.bank.card.remove"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="account.bank.card.remove"/></h1></div>
  <div class="page-content">
    <div class="panel">
      <div class="panel-body">
        <div class="mb-20"><img alt="${bankCode}" src="/assets/images/bank/${bankCode}.png"
                                width="100" class="br3 pull-left">
          <h4 class="pull-left mt-15 ml-20" name="bankDisplayName">${bankDisplayName}</h4>
          <div class="clr"></div>
        </div>
        <form action="/bank/un-link-confirm" method="post">
          <div class="form-group row">
            <div class="col-md-6">
              <label class="form-control-label" for="bankAccountNumber"><spring:message code="account.bank.card.number"/></label>
              <input type="text" disabled class="form-control card-number-input" id="bankAccountNumber"
                     name="bankAccountNumber" value="${bankAccountNumber}"/>
            </div>
            <div class="col-md-6">
              <label class="form-control-label" for="bankAccountName"><spring:message code="account.bank.holder"/></label>
              <input type="text" disabled class="form-control ctk" id="bankAccountName"
                     name="bankAccountName" value="${bankAccountName}"/>
            </div>
          </div>
          <div class="form-group row">
            <div class="col-md-6">
              <label class="form-control-label" for="cardIssueDate">Năm phát hành</label>
              <input type="text" class="form-control" disabled id="cardIssueDate"
                     name="cardIssueDate" value="${cardIssueDate}" data-plugin="formatter"
                     data-pattern="[[99]]/[[99]]"/>
            </div>
            <div class="col-md-6">
              <label class="form-control-label" for="ssn">Số CVV</label>
              <input type="text" disabled class="form-control" id="ssn" name="ssn" value="${ssn}"/>
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
            <c:if test="${(codeErr != null && codeErr >= 1) || (param.codeErr != null && param.codeErr >= 1) }">
              <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
              </div>
            </c:if>
          </c:if>




          <div class="text-right">
            <a href="/bank/manage" class="btn btn-default mr-10"><spring:message code="common.btn.back"/></a>
            <button type="submit" class="btn btn-primary"><spring:message code="common.btn.confirm"/></button>
          </div>
          <input type="hidden" name="walletId" value="${walletId}"/>
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