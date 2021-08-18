<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.system.PaymentSecurityController.PAYMENT_SECURITY" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <title><spring:message code="payment.security.sms.title"/>-<spring:message code="common.company"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp">
    <jsp:param name="secure_page" value="true"/>
</jsp:include>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<c:url var="dashBoardControlUri" value="<%=PAYMENT_SECURITY%>"></c:url>

<spring:message code="payment.security.limitation.current" var="limitation_current"/>
<spring:message code="payment.security.limitation.new" var="limitation_new"/>
<spring:message code="payment.security.limitation.old" var="limitation_old"/>

<div class="page page-email">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/dashboard/index">
                    <spring:message code="dashboard.home"/>
                </a>
            </li>
            <li class="breadcrumb-item active"><spring:message
                    code="payment.security.setting"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="payment.security.setting"/></h1>
    </div>
    <div class="page-content container-fluid">
        <div class="panel panel-bordered">
            <div class="panel-heading">
                <h3 class="panel-title"><spring:message
                        code="payment.security.sms.panel.title.change.otp"/></h3>
            </div>
            <div class="panel-body py-10">
                <c:if test="${(codeErr != null && fn:length(codeErr) gt 0)}">
                    <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                        <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                    </div>
                </c:if>
                <p><spring:message code="payment.security.content"/></p>
                <div class="row">
                    <div class="col-sm-3 form-group row mb-5">
                        <img src="/assets/images/placeholder100.png" class="br3 mb-10">
                    </div>
                    <table class="col-sm-9 form-group">
                        <tbody>
                        <tr>
                            <td><spring:message code="payment.security.sms.panel.otp"/></td>
                            <td>${(userLogin.phoneNumber)}</td>
                        </tr>
                        <tr>
                            <td id="currentLimitationLabel">${limitation_current}</td>
                            <td id="currentLimitationValue">${currentLimitation != null ? ewallet:formatNumber(currentLimitation) : '--'} đ</td>
                        </tr>
                        <tr>
                            <td id="newLimitationLabel">${limitation_new}</td>
                            <td id="newLimitationValue">${newLimitation != null ? ewallet:formatNumber(newLimitation) : '--'} đ</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <form method="post" action="/system/payment-security/sms/change-limitation">
                    <div class="row mb-10">
                        <spring:message code="payment.security.limitation.placeholder"
                                        var="enter_limitation"/>
                        <spring:message code="payment.security.limitation.tooltip"
                                        var="limitation_tooltip"/>
                        <div class="form-group row mb-10 col-md-12" style="padding-right: 0;">
                            <input class="form-control currency-input number-only-input"
                                   placeholder="${enter_limitation}"
                                   type="text" id="limitation" name="limitation"
                                   value="${newLimitation}">
                            <label class="form-control-label px-0 label-control-right">
                                <i class="fa  fa-question-circle"
                                   data-placement="top" data-toggle="tooltip"
                                   data-original-title="${limitation_tooltip}"></i></label>
                            <input type="hidden"  id="referenceId" name="referenceId" value="${referenceId}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 pl-0 text-left">
                            <label class="btn btn-primary" for="limitation_submit">
                                <spring:message code="payment.security.back"/>
                            </label>
                        </div>
                        <div class="col-md-6 pl-0 text-right">
                            <c:if test="${referenceId!= null}">
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#modalPaymentSecurityOTP">
                                    <spring:message code="payment.security.phone.change.limitation"/>
                                </button>
                            </c:if>
                        </div>
                    </div>
                    <!-- /footer -->
                    <c:import
                            url="../dialog_modal/payment_security/dialog_paymentSecurity_confirmOTP.jsp"/>
                </form>
                <form method="post" action="/system/payment-security">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button id="limitation_submit" class="hidden"></button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
</body>
<script>
  $(document).ready(
      function () {
        var newLimitation = $("#newLimitationValue").html();
        if (newLimitation === null || "" === newLimitation
            || "-- đ" === newLimitation) {
          $("#currentLimitationLabel").html("${limitation_current}");
          $("#newLimitationLabel").html("${limitation_new}");
        } else {
          $("#currentLimitationLabel").html("${limitation_old}");
          $("#newLimitationLabel").html("${limitation_current}");
        }
      }
  );
</script>

</html>