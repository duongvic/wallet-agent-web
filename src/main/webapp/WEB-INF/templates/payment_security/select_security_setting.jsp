<%@ page import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_AGENT" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="ID_AGENT" value="<%=ID_AGENT%>"/>
<div class="">
  <div class="page-content container-fluid">
    <div class="panel panel-bordered">
      <div class="panel-heading">
        <h3 class="panel-title"><spring:message code="payment.security.panel.title"/></h3>
      </div>
      <div class="panel-body">
        <p><b><spring:message code="payment.security.panel.content"/></b></p>
        <br>
        <c:choose>
          <c:when test="${'true' eq payment_pin_is_setup}">
            <p><spring:message code="payment.pin.change.panel.content"/> <span
                class="text-info">${payment_pin_last_change != null && payment_pin_last_change ne '' ? payment_pin_last_change : '00:00-00/00/0000'}</span>
            </p>
            <label class="btn btn-warning" onclick="openDialogSecure('payPin')">
              <spring:message code="payment.pin.change"/>
            </label>
          </c:when>
          <c:otherwise>
            <p><spring:message code="payment.security.pin.not.setup.content"/>
            </p>
            <label class="btn btn-warning" onclick="openDialogSecure('payPin')">
              <spring:message code="payment.pin.create"/>
            </label>
          </c:otherwise>
        </c:choose>
        <br>
        <br>

        <%--Đổi hạn mức--%>
        <c:if test="${ID_AGENT != userLogin.customerTypeId}">
          <p><b><spring:message code="payment.security.sms.panel.content"/></b></p>
          <div class="form-group row mb-5">
            <label class=" col col-form-label pb-0"><spring:message
                code="payment.security.phone.number"/></label>
            <div class=" col text-right">
              <p class="form-control-plaintext pb-0 text-info">${(userLogin.phoneNumber)}</p>
            </div>
          </div>
          <div class="form-group row mb-5">
            <label class="col col-form-label pb-0"><spring:message
                code="payment.security.phone.limitation"/>
              <spring:message code="payment.security.phone.tooltip"
                              var="tooltip_message"/>
              <i class="fa  fa-question-circle" data-placement="top"
                 data-toggle="tooltip"
                 data-original-title="${tooltip_message}"></i>
            </label>
            <div class=" col text-right">
              <p class="form-control-plaintext pb-0 currency-input-limitation text-info">${ewallet:formatNumber(oldLimitation)}
                đ</p>
            </div>
          </div>
          <label class="btn btn-warning" onclick="openDialogSecure('paySec')">
            <spring:message code="payment.security.phone.change.limitation"/>
          </label>
        </c:if>
        <%--End Đổi hạn mức--%>
      </div>
    </div>
  </div>
</div>

<%--dialog--%>
<c:import url="../dialog_modal/payment_security/dialog_paymentSecurity.jsp"/>
<%--end dialog--%>

<script>
  function openDialogSecure(param) {
    var modalForm = $("#login-form");
    var loginButton = $("#check-login-button");
    if ("paySec" === param) {
      loginButton.attr("onclick", "login()");
      modalForm.attr("action", "/system/payment-security/sms/change-limitation");
    } else {
      loginButton.attr("onclick", "getOTPForSecure()");
      modalForm.attr("action", "/system/payment-security/change-payment-pin");

    }
    $("#modalPaymentSecurity").modal('show');
  }
</script>
