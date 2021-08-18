<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.fundout.FundOutNoLinkBankController.FUNDOUT_NOLINKBANK_CONTROLLER" %>
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
  <title>Rút tiền không link bank - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp" />
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp" />
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"></jsp:include>
<!-- /menu bar -->
<c:url var="fundOutNoLinkBankUri" value="<%=FUNDOUT_NOLINKBANK_CONTROLLER%>"></c:url>

<div class="page page-email">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><a href="/bank/manage"><spring:message code="label.fundout"/></a></li>
    </ol>
    <h1 class="page-title"><spring:message code="common.btn.confirm"/> OTP</h1>
  </div>
  <div class="page-content">
    <div class="panel">
      <div class="panel-body">
        <div class="mb-20"><img src="/assets/images/bank/${_nameBank}.png" width="100" class="br3 pull-left">
          <h4 class="pull-left mt-15 ml-20">${bankDisplayName}</h4>
          <h4 class="pull-left mt-15 ml-20">${bankAccountNumber}</h4>
          <div class="clr"></div>
        </div>
        <form action="/fundout/no-link-bank/confirm" method="post">

          <div class="row">
            <div class="col-md-12">
              <div class="form-group row mb-5">
                <label class=" col-sm-6 col-form-label pb-0"><spring:message code="account.bank.number"/> / <spring:message code="account.card.number"/> : </label>
                <div class=" col-sm-6  text-right">
                  <p class="form-control-plaintext pb-0" name="_soTaiKhoan">${_soTaiKhoan}</p>
                  <input type="hidden" id="hidden_soTaiKhoan" name="_soTaiKhoan" value="${_soTaiKhoan}">
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-6 col-form-label pb-0">Tên chủ thẻ: </label>
                <div class=" col-sm-6 text-right">
                  <p class="form-control-plaintext pb-0" name="bankAccountName">${bankHolderName}</p>
                  <%--<input type="text" id="hidden_bankAccountName" name="bankAccountName" value="${bankAccountName}">--%>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-6 col-form-label pb-0"><spring:message code="account.bank.branch"/>: </label>
                <div class=" col-sm-6 text-right">
                  <p class="form-control-plaintext pb-0">ĐỐNG ĐA</p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-6 col-form-label pb-0"><spring:message code="label.city"/> : </label>
                <div class=" col-sm-6 text-right">
                  <p class="form-control-plaintext pb-0">Hà Nội </p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-6 col-form-label pb-0"><spring:message code="fundout.money"/>: </label>
                <div class=" col-sm-6 text-right">
                  <p class="form-control-plaintext pb-0" name="amount">${ewallet:formatNumber(amount)} đ </p>
                  <input type="hidden" id="hidden_amount" name="amount" value="${amount}">
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-6 col-form-label pb-0">Phí tiền rút: </label>
                <div class=" col-sm-6 text-right">
                  <p class="form-control-plaintext pb-0" name="feeAmount">0 đ</p>
                  <input type="hidden" name="feeAmount" id="hidden_feeAmount" value="${feeAmount}">
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-6 col-form-label pb-0">Tổng thanh toán: </label>
                <div class=" col-sm-6 text-right">
                  <p class="form-control-plaintext pb-0 "id="realAmount" name="realAmount">${ewallet:formatNumber(realAmount)} đ</p>
                  <input type="hidden" name="realAmount" id="hidden_realAmount" value="${realAmount}">
                </div>
              </div>
            </div>
          </div>

          <c:if test="${(isOtpRequired==true)}">
            <div class="col-lg-12 col-md-8 mb-5 pl-0">
              <label><spring:message code="label.otp"/>
                <span style="color: #27ADD0"><sec:authentication
                    property="principal.username"></sec:authentication></span>
                <span></span>
              </label>
            </div>

            <div class="col-md-12 mb-5 offset-md-0 pl-0">
              <div class="input-group">
                <input type="text" id="codeOTP" name="codeOTP" class="form-control"
                       placeholder="<spring:message code="label.enter.otp"/>" value="${codeOTP}" required>
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

          <div class="col-md-12 mb-5 offset-md-0 text-right">
            <a href="/fundout/no-link-bank/info" class="btn btn-default mr-10"><spring:message code="common.btn.back"/></a>
            <button type="submit" class="btn btn-primary"><spring:message code="common.btn.confirm"/></button>
          </div>
          <input type="hidden" name="orderId" value="${orderId}"/>
          <input type="hidden" name="codeErr" value="${codeErr}"/>
          <input type="hidden" name="cardIssueDate1" value="${cardIssueDate1}"/>
          <input type="hidden" name="cardIssueDate2" value="${cardIssueDate2}"/>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp" />
<!-- /footer -->
</body>

</html>