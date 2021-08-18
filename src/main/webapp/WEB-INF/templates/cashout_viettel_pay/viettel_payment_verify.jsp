<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.fundin.CashOutViettelPayController.CASHOUT_VIETTEL_PAY_INFO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="string" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <spring:message code="label.cashout.viettel.pay"
                  var="label_viettel_pay"/>
  <title>
    ${label_viettel_pay}
  </title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->

  <style>
    .select2-container--default .select2-selection--single .select2-selection__rendered {
      padding-left: 0 !important;
    }

    .custom_messagebox_overlay {
      background-color: rgba(220, 240, 175, 0.5);
      border: 10px solid rgba(150, 190, 85, 0.6);
    }

    .custom_messagebox {
      background-color: #ddf0b0;
      border: 1px solid #99bb55;
      border-radius: 20px;
    }

    .custom_messagebox,
    .custom_messagebox .messagebox_buttons {
      background-color: #ccea88;
    }

    .custom_messagebox .messagebox_buttons button {
      background-color: #ddf0b0;
      border-radius: 20px;
    }

    .messagebox_buttons {
      border-bottom-left-radius: 16px 20px;
      border-bottom-right-radius: 16px 20px;
    }
  </style>
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification -->

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <jsp:param name="nav" value="menuCashOutViettelPay"/>
</jsp:include>
<!-- /menu bar -->

<jsp:include page="../include_component/service_code_constants.jsp"/>

<spring:message code="common.invalid.input.text.4.16" var="label_invalid_text_number"></spring:message>
<spring:message code="common.invalid.phone" var="label_invalid_phone"></spring:message>
<spring:message code="label.content.transfers" var="label_invalid_content"></spring:message>
<spring:message code="lable.enter.into" var="label_invalid_input"/>
<spring:message code="label.payer.name" var="label_payer_name"/>
<spring:message code="label.invalid.amount" var="label_invalid_amount"></spring:message>

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message
          code="label.convenient.service"/></li>
      <li class="breadcrumb-item">${label_viettel_pay}</li>
    </ol>
    <h1 class="page-title">${label_viettel_pay}</h1>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-12">
        <div class="panel panel-bordered">
          <form id="myFormCashOutSubmit" action="/cashout-viettel-pay/submit" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="panel-body py-10">

              <div class="row mb-10">
                <div class="col-lg-12 col-md-8 mb-3">
                  <label><spring:message code="label.payment.password"/>
                  </label>
                </div>
                <c:if test="${(codeErr != null)}">
                  <div class="text-danger error-message">
                    <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}
                    </small>
                  </div>
                </c:if>
                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                  <div class="input-group">
                    <input type="password" id="otp"
                           name="otp"
                           class="form-control" required
                           placeholder="<spring:message code="label.enter.password"/>">
                  </div>
                </div>
              </div>


              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit"
                          class="btn btn-primary btn-sm pull-right">
                    <spring:message code="common.btn.confirm"/></button>
                  <a href="#"
                     onclick="verifyCashOut()"
                     class="btn btn-default btn-sm pull-right mr-10">
                    <spring:message code="common.btn.back"/></a>
                </div>
              </div>

            </div>


          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<c:import url="../include_page/resent_otp_lib.jsp"/>
<!-- /footer -->
<jsp:include page="../include_page/js_daterangepicker.jsp"/>

</body>
<script type="text/javascript">
  $(document).ready(function () {


  });

  function verifyCashOut() {
    window.location.href = '<%=CASHOUT_VIETTEL_PAY_INFO%>';
  };

  <%--$('#resendOTP').click(function () {--%>
  <%--var id = $('#hidden_orderId').val();--%>
  <%--if (id != null && id != '') {--%>
  <%--$.ajax({--%>
  <%--type: 'GET',--%>
  <%--url: 'resend-otp',--%>
  <%--data: {--%>
  <%--orderId: id--%>
  <%--},--%>
  <%--success: function (data) {--%>
  <%--if (data.code == 0) {--%>
  <%--$('#countdown').show();--%>
  <%--$('#clock').countdown(get3MinutesFromNow());--%>
  <%--$('#resend').attr("style", "display: none !important");--%>
  <%--} else {--%>
  <%--$.MessageBox({message: data.message});--%>
  <%--}--%>
  <%--},--%>
  <%--error: function () {--%>
  <%--$.MessageBox({message: "<spring:message code="common.data.error"/>"});--%>
  <%--}--%>
  <%--});--%>
  <%--} else {--%>
  <%--$.MessageBox({message: "<spring:message code="common.data.error"/>"});--%>
  <%--return false;--%>
  <%--}--%>
  <%--});--%>

  <%--$('#btn_action_dialog').click(function () {--%>
  <%--$.MessageBox({--%>
  <%--// customClass: "custom_messagebox",--%>
  <%--// customOverlayClass: "custom_messagebox_overlay",--%>
  <%--buttonFail: '<spring:message code="popup.button.no"/>',--%>
  <%--buttonDone: '<spring:message code="popup.button.yes"/>',--%>
  <%--message: "Bạn có chắc chắn muốn nạp 1.000.000 VNĐ vào tài khoản ViettelPay cho khách hàng Trần Việt Anh, SĐT 0988776655. Phí giao dịch 20.000 ?",--%>
  <%--title: '<spring:message code="label.confirm.deposit"/>'--%>
  <%--}--%>
  <%--).done(function () {--%>
  <%--$('#myForm').submit();--%>
  <%--}).fail(function () {--%>

  <%--});--%>

  <%--});--%>


</script>

</html>