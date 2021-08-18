<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-sm-6 col-md-12">
    <div class="panel panel-bordered">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="policy.security"/></h3>
            <ul class="panel-actions panel-actions-keep">
                <%--<li><a href="/system/payment-security?currentLimitation=${currentLimitation}"><spring:message code ="policy.limit.change"/></a></li> --%>
                <%--<li><a href="javascript:void(0)" onclick="openDialog()"><spring:message--%>
                        <%--code="policy.limit.change"/></a></li>--%>
                    <form method="post" action="/system/payment-security"
                          id="payment_security_form" class="hidden">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                <li><a href="javascript:void(0);" onclick="goToPaymentSec()"><spring:message
                        code="policy.limit.change"/></a></li>
            </ul>
        </div>
        <div class="panel-body mb-10">
        <img src="/assets/images/baomat.png" class="text-center overlay-figure hidden">
        <table class="table no-border fs13">
            <tbody>
            <tr>
                <td><spring:message code="policy.security.mode"/>:</td>
                <td class="text-right"><spring:message code="policy.otp.app"/></td>
            </tr>
            <tr>
                <td><spring:message code="policy.limit"/>:</td>
                <td class="text-right">${currentLimitation != null ? currentLimitation : '--'}</td>
            </tr>
            </tbody>
        </table>
        </div>
    </div>
</div>
<script>
//  function openDialog() {
//    var modalForm = $("#login-form");
//    var loginButton = $("#check-login-button");
//    modalForm.attr("action", "/system/payment-security");
//    loginButton.attr("onclick", "login()");
//    $('#modalPaymentSecurity').modal('show')
//  }
    function goToPaymentSec() {
      jQuery('#payment_security_form').submit();
    }
</script>