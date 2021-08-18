<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage"><spring:message
          code="card.manage.label"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="common.btn.add"/></li>
    </ol>
    <c:if test="${bankCode !=null && bankCode eq 'BIDVVNVX'}">
      <h1 class="page-title"><spring:message code="account.bank.title.form"/></h1>
    </c:if>
    <c:if test="${bankCode !=null && bankCode ne 'BIDVVNVX'}">
      <h1 class="page-title">Thêm thẻ ngân hàng</h1>
    </c:if>
  </div>
  <div class="page-content">
    <div class="panel">
      <div class="panel-body">
        <div class="mb-20"><img alt="${bankCode}" src="/assets/images/bank/${bankCode}.png"
                                width="100" class="br3 pull-left">
          <h4 class="pull-left mt-15 ml-20">${bankDisplayName}</h4>
          <div class="clr"></div>
        </div>
        <form id="themtknganhang" action="/bank/linkBankNewConfirm" autocomplete="off"
              method="post">
          <%--<c:if--%>
          <%--test="${(codeErr != null && codeErr >= 1) || (param.codeErr != null && param.codeErr >= 1) }">--%>
          <%--<div class="tknganhang-errors alert alert-danger alert-dismissible">--%>
          <%--<button type="button" class="close" aria-label="Close" data-dismiss="alert"><span--%>
          <%--aria-hidden="true">×</span></button>--%>
          <%--<p>Sai OTP</p>--%>
          <%--</div>--%>
          <%--</c:if>--%>


          <%----%>
          <%--<c:if--%>
          <%--test="${(codeErr != null && codeErr != '')}">--%>
          <%--<div class="form-group row">--%>
          <%--<div class="col-md-12 mb-5 offset-md-0 text-danger error-message">--%>
          <%--<small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>--%>
          <%--</div>--%>
          <%--</div>--%>
          <%--</c:if>--%>

          <c:if test="${codeErr != null}">
            <div class="form-group row">
              <label class="col-sm-4 col-md-3"></label>
              <div class="col-sm-8 col-md-9 text-danger error-message mb-10">
                  <%--<c:choose>--%>
                  <%--<c:when test="${codeErr =='306'}">--%>
                  <%--<small><i class="fa fa-times-circle"></i>&nbsp; <spring:message code="error.OTP.invalid"/> </small>--%>
                  <%--</c:when>--%>
                  <%--<c:otherwise>--%>
                  <%--<small><i class="fa fa-times-circle"></i>&nbsp;<spring:message code="error.bank.${codeErr}"/></small>--%>
                  <%--</c:otherwise>--%>
                  <%--</c:choose>--%>
                <small><i class="fa fa-times-circle"></i>&nbsp;<spring:message
                    code="error.bank.${codeErr}"/></small>
              </div>
            </div>
          </c:if>

          <c:if test="${bankCode!=null && bankCode ne 'BIDVVNVX'}">
            <div class="linkItemCard">
              <div class="form-group row mb-10">
                <div class="col-sm-4 col-md-3">
                  <label class="form-control-label"><spring:message
                      code="account.bank.card.holder"/>:</label>
                </div>
                <div class="col-sm-8 col-md-9">
                  <input type="text" disabled class="form-control" id="fullNameCard"
                         value="<sec:authentication property="principal.fullName" />"/>
                </div>
              </div>
              <div class="form-group row mb-10">
                <div class="col-sm-4 col-md-3">
                  <label class="form-control-label"><spring:message
                      code="account.bank.card.number"/>:</label>
                </div>
                <div class="col-sm-8 col-md-9">
                  <input type="text" disabled class="form-control card-number-input" id="soThe"
                         value="${accountNumber}"/>
                </div>
              </div>
              <div id="cardIssueDateForm" class="form-group row mb-10">
                <div class="col-sm-4 col-md-3">
                  <label>Ngày hiệu lực:</label>
                </div>
                <div class="col-sm-8 col-md-9">
                  <input type="text" disabled class="form-control" disabled name="cardIssueDate"
                         value="${cardIssueDate}"/>
                </div>
              </div>
            </div>
          </c:if>

          <c:if test="${bankCode !=null && bankCode eq 'BIDVVNVX'}">
            <div class="linkAccount">
              <div class="form-group row">
                <div class="col-sm-4 col-md-3">
                  <label class="form-control-label"><spring:message
                      code="account.bank.holder"/>:</label>
                </div>
                <div class="col-sm-8 col-md-9">
                  <input type="text" disabled class="form-control ctk" id="fullNameAccount"
                         name="fullName"
                         value="<sec:authentication property="principal.fullName" />"/>
                </div>
              </div>
              <div class="form-group row">
                <div class="col-sm-4 col-md-3">
                  <label class="form-control-label" for="soTaiKhoan"><spring:message
                      code="account.bank.number"/>:</label>
                </div>
                <div class="col-sm-8 col-md-9">
                  <input type="text" disabled class="form-control card-number-input" id="soTaiKhoan"
                         value="${accountNumber}" required/>
                </div>
              </div>

              <c:choose>
                <c:when test="${linkType !=null && linkType eq 'CARD'}">
                  <div id="cardIssueDateForm" class="form-group row mb-10">
                    <div class="col-sm-4 col-md-3">
                      <label>Ngày hiệu lực:</label>
                    </div>
                    <div class="col-sm-8 col-md-9">
                      <input type="text" disabled class="form-control" disabled name="cardIssueDate"
                             value="${cardIssueDate}"/>
                    </div>
                  </div>
                </c:when>
                <c:when test="${linkType !=null && linkType eq 'ACCOUNT'}">
                  <div class="form-group row">
                    <div class="col-sm-4 col-md-3">
                      <label class="form-control-label"><spring:message
                          code="account.bank.branch"/>:</label>
                    </div>
                    <div class="col-sm-8 col-md-9">
                      <input type="text" disabled class="form-control" id="chiNhanh"
                             value="${branchBank}"></input>
                    </div>
                  </div>
                </c:when>
                <c:otherwise></c:otherwise>
              </c:choose>

            </div>
          </c:if>

          <%--<div class="form-group"><input type="text" class="form-control" name="accountNumber"--%>
          <%--value="${accountNumber}"--%>
          <%--placeholder="Số tài khoản ngân hàng"/></div>--%>
          <%--&lt;%&ndash;<div class="form-group"><input type="text" class="form-control" name="fullName"&ndash;%&gt;--%>
          <%--&lt;%&ndash;value="${fullName}" placeholder="Chủ tài khoản"/></div>&ndash;%&gt;--%>
          <%--<div class="form-group"><input type="text" class="form-control" name="branchBank"--%>
          <%--value="${branchBank}" placeholder="Chi nhánh"/></div>--%>

          <c:if test="${(isOtpRequired==true)}">
            <div class="form-group row">
              <div class="col-lg-12 col-md-8 mb-5">
                <label><spring:message code="label.otp"/>
                  <span style="color: #27ADD0"><sec:authentication
                      property="principal.username"></sec:authentication></span>
                  <span></span>
                </label>
              </div>
            </div>

            <div class="form-group row">
              <div class="col-md-12 mb-5 offset-md-0">
                <div class="input-group">
                  <input type="text" id="codeOtp" name="codeOtp" class="form-control"
                         placeholder="<spring:message code="label.enter.otp"/>" value="${codeOtp}"
                         data-plugin="formatter"
                         required>
                  <span class="input-group-btn">
                                                <button type="button"
                                                        class="btn btn-default btn-outline">
                                                    <i class="fa fa-rotate-left"></i> <spring:message
                                                    code="common.btn.resend"/></button>
                                              </span>
                </div>
              </div>

            </div>
          </c:if>

          <div class="text-right">
            <button type="submit" class="btn btn-primary"><spring:message
                code="common.btn.confirm"/>
            </button>
          </div>

          <input type="hidden" name="phoneNumber" value="${(userLogin.phoneNumber)}"/>
          <input type="hidden" id="hidden_fullName" name="fullName" value="${fullName}"/>
          <input type="hidden" id="hidden_ssn" name="ssn" value="${ssn}"/>
          <input type="hidden" id="hidden_accountNumber" name="accountNumber"
                 value="${accountNumber}"/>
          <input type="hidden" id="hidden_branchBank" name="branchBank" value="${branchBank}"/>
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