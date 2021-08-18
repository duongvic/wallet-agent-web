<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.system.PaymentSecurityController.DASH_BOARD" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <title><spring:message code="payment.pin.change.panel.title"/></title>
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

<c:url var="dashBoardControlUri" value="<%=DASH_BOARD%>"></c:url>

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
            <li class="breadcrumb-item active">
                <a href="javascript:void(0)">
                    <spring:message code="payment.security.setting"/>
                </a>
            </li>
        </ol>
        <h1 class="page-title"><spring:message code="payment.security.setting"/></h1>
    </div>
    <div class="page-content container-fluid">
        <div class="panel panel-bordered">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <c:choose>
                        <c:when test="${'true' eq payment_pin_is_setup}">
                            <spring:message code="payment.pin.change.panel.title"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="payment.pin.create.panel.title"/>
                        </c:otherwise>
                    </c:choose>
                </h3>
            </div>
            <div class="panel-body py-10">
                <c:if test="${(codeErr != null && fn:length(codeErr) gt 0)}">
                    <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                        <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                    </div>
                </c:if>
                <spring:message code="label.otp" var="enter_otp_title_label"/>
                <spring:message code="label.enter.otp" var="enter_payment_pin_string"/>
                <p>${enter_otp_title_label}&nbsp;${(userLogin.phoneNumber)}</p>
                <form method="post" action="/system/payment-security/enter-payment-pin">
                    <div class="row mb-10">
                        <%--<spring:message code="payment.security.limitation.tooltip"--%>
                        <%--var="limitation_tooltip"/>--%>
                        <div class="form-group row mb-10 col-md-12" style="padding-right: 0;">
                            <input class="form-control number-only-input" required
                                   placeholder="${enter_payment_pin_string}"
                                   id="secure_OTP" name="secure_OTP">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 pl-0 text-left">
                            <label class="btn btn-primary" for="limitation_submit">
                                <spring:message code="payment.security.back"/>
                            </label>
                        </div>
                        <div class="col-md-6 pl-0 text-right">
                            <button class="btn btn-primary">
                                <spring:message code="payment.pin.change.button.label"/>
                            </button>
                        </div>
                    </div>
                    <!-- /footer -->
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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

</html>