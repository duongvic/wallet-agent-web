<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>Mã xác thực tài khoản - <spring:message code="common.company"/></title>
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
<spring:message code="label.enter.code.authentication" var="enterCodeAuthen"/>
<div class="page page-email">
  <div class="page-header">
    <%--<div class="page-header-actions"><a href="/bank/manage">Hủy</a></div>--%>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage"><spring:message code="card.manage.label"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="common.btn.add"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="account.bank.title.form"/></h1></div>
  <div class="page-content">
    <div class="panel">
      <div class="panel-body">

        <div class="mb-20"><img alt="${bankCode}" src="/assets/images/bank/${bankCode}.png"
                                width="100" class="br3 pull-left">
          <h4 class="pull-left mt-15 ml-20">${bankDisplayName}</h4>
          <div class="clr"></div>
        </div>
        <form id="maxacthuc" name="maxacthuc" action="/bank/maXacThuc" method="post" autocomplete="off">
          <c:if test="${(codeErr != null && codeErr >= 1) || (param.codeErr != null && param.codeErr >= 1) }">
          <div class="tknganhang-errors alert alert-danger alert-dismissible">
            <button type="button" class="close" aria-label="Close" data-dismiss="alert"><span aria-hidden="true">×</span></button>
            <p>Lưu ý: </p>
            <ul></ul>
          </div>
          </c:if>

          <div class="form-group">
            <label class="form-control-label" for="codeOtp"><spring:message code="label.pl.enter.authen.code.sent.phone"/> </label>
            <input type="text" class="form-control" id="codeOtp" name="codeOtp" placeholder="${enterCodeAuthen}"/>
          </div>
          <div class="text-right">
            <button type="submit" class="btn btn-primary  btn-sm" id="validateButton3"><spring:message code="button.confirm"/> </button>
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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