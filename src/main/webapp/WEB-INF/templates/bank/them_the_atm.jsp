<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="account.bank.title.form"/> - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="themtaikhoan"/>
</jsp:include>
<!-- /menu bar -->

<div class="page page-email">
  <div class="page-header">
    <%--<div class="page-header-actions"><a href="/bank/manage">Hủy</a></div>--%>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage"><spring:message code="card.manage.label"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="common.btn.add"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="account.bank.title.form"/></h1>
  </div>
  <div class="page-content">
    <div class="panel">
      <div class="panel-body">
        <div class="mb-20"><img alt="${bankCode}" src="/assets/images/bank/${bankCode}.png"
                                width="100" class="br3 pull-left">
          <h4 class="pull-left mt-15 ml-20">${bankDisplayName}</h4>
          <div class="clr"></div>
        </div>
        <form id="themtknganhang" action="/bank/themTheAtm" autocomplete="off" method="post">
          <c:if
              test="${(codeErr != null && codeErr >= 1) || (param.codeErr != null && param.codeErr >= 1) }">
            <div class="tknganhang-errors alert alert-danger alert-dismissible">
              <button type="button" class="close" aria-label="Close" data-dismiss="alert"><span
                  aria-hidden="true">×</span></button>
              <p>Lưu ý: </p>
              <ul></ul>
            </div>
          </c:if>
          <div class="form-group"><input type="text" class="form-control" name="accountNumber"
                                         value="${accountNumber}"
                                         placeholder="<spring:message code="account.bank.number"/> ngân hàng"/></div>
          <div class="form-group"><input type="text" class="form-control" name="fullName"
                                         value="${fullName}" placeholder="<spring:message code="account.bank.holder"/>"/></div>
          <div class="form-group"><input type="text" class="form-control" name="branchBank"
                                         value="${branchBank}" placeholder="<spring:message code="account.bank.branch"/>"/></div>
          <div class="row mb-20">
            <div class="col-sm-2 mb-3">
              <input type="text" id="cardIssueDate1" data-plugin="formatter" data-pattern="[[99]]"
                     required class="form-control text-center" style="max-width: 50px;">
            </div>
            <div class="col-sm-1">
              <span style="font-size: 20px">⁄</span>
            </div>
            <div class="col-sm-2 mb-3">
              <input type="text" class="form-control text-center" id="cardIssueDate2" required
                     data-plugin="formatter" data-pattern="[[99]]" style="max-width: 50px;">
            </div>
          </div>
          <div class="form-group">
            <select class="form-control" name="cityLocation" data-fv-notempty="true">
              <option value="">Chọn thành phố</option>
              <option value="hn">Hà Nội</option>
              <option value="hcm">Hồ Chí Minh</option>
            </select>
          </div>
          <div class="text-right">
            <a href="/bank/bank-inset" class="btn btn-default mr-10"><spring:message code="common.btn.back"/></a>
            <button type="submit" class="btn btn-primary" id="validateButton3"
                    onclick="getcardIssueDate()"><spring:message code="common.btn.add"/>
            </button>
          </div>

          <input type="hidden" name="bankCode" value="${bankCode}"/>
          <input type="hidden" name="phoneNumber" value="${(userLogin.phoneNumber)}"/>
          <input type="hidden" id="hidden_cardIssueDate" name="cardIssueDate"
                 value="${cardIssueDate}">
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
<script>
  function getcardIssueDate() {
    document.getElementById('hidden_cardIssueDate').value = document.getElementById(
        'cardIssueDate1').value + "/" + document.getElementById('cardIssueDate2').value
  }
</script>
</html>