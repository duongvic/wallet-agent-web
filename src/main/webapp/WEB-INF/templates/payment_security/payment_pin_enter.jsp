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

<c:url var="dashBoardControlUri" value="<%=DASH_BOARD%>"/>

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
                            <spring:message code="payment.pin.security.content" var="enter_pin_title_label"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="payment.pin.create.panel.title"/>
                            <spring:message code="payment.security.panel.content" var="enter_pin_title_label"/>
                        </c:otherwise>
                    </c:choose>
                </h3>
            </div>
            <div class="panel-body py-10">
                <div class="col-md-12 mb-5 offset-md-0 text-danger error-message ${(codeErr != null && '' ne codeErr) ? '' : 'hidden'}">
                    <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                </div>
                <spring:message code="label.enter.otp" var="enter_payment_pin_string"/>
                <p>${enter_pin_title_label}</p>
                <form id="change_pin_form" method="post"
                      action="/system/payment-security/change-payment-pin/confirm">
                    <div class="row mb-10">
                        <%--<spring:message code="payment.security.limitation.tooltip"--%>
                        <%--var="limitation_tooltip"/>--%>
                        <div class="form-group row mb-10 col-md-12" style="padding-right: 0;">
                            <spring:message code="payment.pin.input.number.only.6" var="number_only_warning_string"/>
                            <input type="password" class="form-control" required
                                   placeholder="<spring:message code="payment.pin.new.pin.enter.label"/>"
                                   id="new_PIN" name="new_PIN"
                                   pattern="^([0-9]{6})"
                                   title="${number_only_warning_string}">
                            <input type="password" class="form-control" required
                                   placeholder="<spring:message code="payment.pin.new.pin.reenter.label"/>"
                                   id="confirm_new_PIN" name="confirm_new_PIN"
                                   pattern="^([0-9]{6})"
                                   title="${number_only_warning_string}">
                            <div class="col-md-12 mb-5 offset-md-0 text-danger error-message hidden" id="input_not_match">
                                <small><i class="fa fa-times-circle"></i>&nbsp;<spring:message code="payment.pin.input.not.match"/></small>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 pl-0 text-left">
                            <label class="btn btn-primary" for="limitation_submit">
                                <spring:message code="payment.security.back"/>
                            </label>
                        </div>
                        <div class="col-md-6 pl-0 text-right">
                            <button class="btn btn-primary" type="button" onclick="validatePin()">
                                <spring:message code="payment.pin.change.button.label"/>
                            </button>
                        </div>
                    </div>
                    <!-- /footer -->
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button id="submit_form_button" class="hidden"></button>
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
  function validatePin() {
    var password = jQuery("#new_PIN");
    var confirm_password = jQuery("#confirm_new_PIN");
    var warning = jQuery("#input_not_match");

    if (password.val() !== confirm_password.val()) {
      confirm_password.attr("style", "border-color: #f2353c");
      warning.removeClass("hidden");
    } else {
      confirm_password.attr("style", "border-color: #e4eaec");
      warning.addClass("hidden");
      jQuery('#submit_form_button').trigger('click');
    }
  }
</script>

</html>