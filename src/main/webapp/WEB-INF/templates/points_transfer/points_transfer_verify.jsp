<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.fundin.CashInViettelPayController.CASHIN_VIETTEL_PAY_INFO" %>
<%@ page import="static vn.mog.ewallet.consumer.web.controller.fundin.PointsTransferController.POINTS_TRANSFER_INFO" %>
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
  <spring:message code="label.points.transfer"
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
  <jsp:param name="nav" value="menuPointTransfer"/>
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
          <form id="myForm" action="/points-transfer/submit" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="panel-body py-10">

              <div class="row mb-10">
                <div class="col-lg-12 col-md-8 mb-3">
                  <label><spring:message code="label.google.authenticator"/>
                  </label>
                </div>
                <c:if test="${(codeErr != null)}">
                  <div class="text-danger error-message">
                    <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}
                    </small>
                  </div>
                </c:if>
                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                  <div class="">
                    <input type="password" id="otp"
                           name="otp"
                           class="form-control" required
                           placeholder="<spring:message code="label.enter.google.authenticator"/>">
                  </div>
                </div>
              </div>

              <div class="row mb-10">
                <div class="col-lg-12 col-md-8 mb-3">
                  <a href="https://chart.googleapis.com/chart?chs=200x200&chld=M%7C0&cht=qr&chl=otpauth%3A%2F%2Ftotp%2FZoTaVN%3A0900000002%3Fsecret%3DJ4OAWGIJLACW6GRW%26issuer%3DZoTaVN"
                     target="_blank" class="btn-link" id="actionQR"><spring:message code="label.request.sending.code"/>
                  </a>
                </div>
                <div class="col-lg-12 col-md-8 mb-3">
                  <a href="https://id.zo-ta.com/tutorial" target="_blank" class="btn-link"><spring:message
                      code="label.help.google.authenticator"/>
                  </a>
                </div>
              </div>


              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit"
                          class="btn btn-primary btn-sm pull-right">
                    <spring:message code="common.btn.confirm"/></button>
                  <a href="#"
                     onclick="verifyTransfer()"
                     class="btn btn-default btn-sm pull-right mr-10">
                    <spring:message code="common.btn.back"/></a>
                </div>
              </div>

            </div>

            <div class="modal fade" id="dialogQR"
                 tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
              <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                  <%--<div class="modal-header">--%>
                  <%--<h5 class="modal-title" id="exampleModalLongTitle">Nhập mã xác nhận</h5>--%>
                  <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                  <%--<span aria-hidden="true">&times;</span>--%>
                  <%--</button>--%>
                  <%--</div>--%>
                  <div class="modal-body">
                    <div class="row mb-10">
                      <label class="col-12 text-center control-label">Nhập mã xác nhận</label>
                    </div>
                    <div class="row mb-10">
                      <div class="">
                        <span id="enterOPTPhone"></span>
                      </div>
                    </div>
                    <div class="row mb-10">
                      <div class="errorCashInFeeGet"></div>

                      <input type="password" name="senderOtp" id="senderOtp" class="col-12"
                             onchange="getValueSenderOTP(this)" placeholder="Nhập mã xác nhận (OTP)">
                      <div class="errorSenderOtp"></div>

                      <input type="hidden" name="keyOtpFee" id="keyOtpFee" class="col-12">
                      <input type="hidden" name="orderIdForMe" id="orderIdForMe" class="col-12">
                      <input type="hidden" name="receiverNameForMe" id="receiverNameForMe" class="col-12">
                      <input type="hidden" name="transFeeForMe" id="transFeeForMe" class="col-12">
                      <input type="hidden" id="btnCashInForMe" name="btnCashInForMe" value="" class="col-12">
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="closeCashInForMe()">Hủy</button>
                    <button type="button" class="btn btn-primary" onclick="cashInForMe()">Xác nhận</button>
                  </div>
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

  function verifyTransfer() {
    window.location.href = '<%=POINTS_TRANSFER_INFO%>';
  };

  jQuery('#actionQR').on("click", function (e) {
    e.preventDefault();
    var email = '${userLogin.email}';
    $.MessageBox({
        buttonFail: '<spring:message code="popup.button.no"/>',
        buttonDone: '<spring:message code="popup.button.yes"/>',
        message: "Bạn đang thực hiện gửi mã QR về email: " + email,
        title: '<spring:message code="common.btn.verify"/>'
      }
    ).done(function () {
      $.ajax({
        type: 'POST',
        url: '/ajax-controller/send-email/googleAuthenticator',
        beforeSend: function (xhr) {
          if ("${_csrf.parameterName}" && "${_csrf.token}") {
            xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
          }
        },
        data: {
          "${_csrf.parameterName}": "${_csrf.token}",
          email: $('#email').val(),
        },
        timeout: 60000,
        cache: false,
        success: function (data) {
          if (data.status.code == 0) {
            $.MessageBox({message: '<spring:message code="label.QR.code.sent.email"/>'});
          }
          else {
            $.MessageBox({message: data.status.value});
          }
        }
      });
    }).fail(function () {
    });
  });

</script>

</html>