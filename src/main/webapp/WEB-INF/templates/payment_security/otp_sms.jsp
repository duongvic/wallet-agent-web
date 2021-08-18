<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="">
  <%--<form action="/system/payment-security/sms/change-limitation" method="get">--%>
  <div class="panel panel-bordered">
    <div class="panel-heading">
      <h3 class="panel-title"><spring:message code="payment.security.sms.panel.title.otp"/></h3>
    </div>
    <div class="panel-body">
      <p><spring:message code="payment.security.content"/></p>
      <div class="row">
        <div class="col-sm-3 form-group row mb-5">
          <img src="/assets/images/placeholder100.png" class="br3 mb-10">
        </div>
        <div class="col-sm-9 pl-0">
          <div class="">
            <div class="form-group row mb-5">
              <label class=" col col-form-label pb-0"><spring:message code="payment.security.phone.number"/></label>
              <div class=" col text-right">
                <p class="form-control-plaintext pb-0">${(userLogin.phoneNumber)}</p>
              </div>
            </div>
            <div class="form-group row mb-5">
              <label class="col col-form-label pb-0"><spring:message code="payment.security.phone.limitation"/>
                <spring:message code="payment.security.phone.tooltip" var="tooltip_message"/>
                <i class="fa  fa-question-circle" data-placement="top" data-toggle="tooltip"
                   data-original-title="${tooltip_message}"></i>
              </label>
              <div class=" col text-right">
                <p class="form-control-plaintext pb-0 currency-input-limitation">${ewallet:formatNumber(oldLimitation)} đ</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="panel-footer vertical-align-middle col-sm-12">
      <!-- <a href="/system/paymentSec/sms/register" class="btn btn-danger">Đổi hạn mức</a> -->
      <%--<a href="/system/paymentSec/login" class="btn btn-danger">Đổi hạn mức</a>--%>
      <form method="post" action="/system/payment-security/sms/change-limitation">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button id="change_limitation_submit" class="hidden"></button>
      </form>
      <label for="change_limitation_submit" class="btn btn-danger"><spring:message code="payment.security.phone.change.limitation"/></label>
    </div>
  </div>
    <%--<input type="hidden" name="${_csrf.parameterName}"--%>
           <%--value="${_csrf.token}" />--%>
  <%--</form>--%>
</div>
