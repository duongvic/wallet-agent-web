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

    @media (min-width: 768px) and (max-width: 1366px) {
      .messagebox {
        max-width: 50%;
      }
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
          <form id="myFormCashOut" action="/cashout-viettel-pay/verify" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="panel-body py-10">
              <h4>Chi tiết giao dịch</h4>

              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6">
                  <label><spring:message code="common.service"/></label>
                </div>
                <div class="col col-sm-6 col-md-6">
                  <label>${label_viettel_pay}</label>
                </div>
              </div>

              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6">
                  <label><spring:message code="label.payer.name"/></label>
                </div>
                <div class="col col-sm-6 col-md-6">
                  <p>${senderName}</p>
                </div>
              </div>

              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6">
                  <label><spring:message code="label.payer.is.phone.number"/></label>
                </div>
                <div class="col col-sm-6 col-md-6">
                  <label>${senderMsisdn}</label>
                </div>
              </div>

              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6">
                  <label><spring:message code="label.amount.of.money"/></label>
                </div>
                <div class="col col-sm-6 col-md-6">
                  <p class="currency-input">${amount}</p>
                </div>
              </div>

              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6">
                  <label><spring:message code="label.payee.name"/></label>
                </div>
                <div class="col col-sm-6 col-md-6">
                  <label>${receiverName}</label>
                </div>
              </div>

              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6">
                  <label><spring:message code="label.payee.is.phone.number"/></label>
                </div>
                <div class="col col-sm-6 col-md-6">
                  <label>${receiverMsisdn}</label>
                </div>
              </div>
            </div>


            <div class="panel-body py-10">
              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6">
                  <label>Tổng tiền giao khách hàng</label>
                </div>
                <div class="col col-sm-6 col-md-6">
                  <label class="currency-input">${amount}</label>
                </div>
              </div>
            </div>

            <div class="panel-body py-10">
              <%--Hoa Hồng--%>
              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6">
                  <label><spring:message code="label.bill.payment.agent.commission"/></label>
                </div>
                <div class="col col-sm-6 col-md-6">
                  <p class="currency-input">${commission}</p>
                </div>
              </div>

              <%--Phí GD--%>
              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6">
                  <label><spring:message code="label.transaction.fees"/></label>
                </div>
                <div class="col col-sm-6 col-md-6">
                  <p class="currency-input">${fee}</p>
                </div>
              </div>
            </div>

            <div class="panel-body py-10">
              <%--Tổng thanh toán--%>
              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6">
                  <label>Tổng điểm cộng tài khoản</label>
                </div>
                <div class="col col-sm-6 col-md-6">
                  <p class="currency-input" style="color: red">${total}</p>
                </div>
              </div>
            </div>

            <div class="panel-body">
              <div class="form-group row mb-10 ">
                <div class="col col-sm-6 col-md-6 offset-6">
                  <button type="button" id="btn_action_dialog" class="btn btn-primary btn-sm">
                    <spring:message
                        code="common.btn.payment"/></button>
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
<c:import url="../include_page/footer_js.jsp"/>
<!-- /footer -->
<jsp:include page="../include_page/js_daterangepicker.jsp"/>

</body>
<script type="text/javascript">
  $(document).ready(function () {


  });

  $('#btn_action_dialog').click(function () {
    $.MessageBox({
      customClass: "messagebox",
        // customOverlayClass: "custom_messagebox_overlay",
        buttonFail: '<spring:message code="popup.button.no"/>',
        buttonDone: '<spring:message code="popup.button.yes"/>',
        message: "Xác nhận họ tên người nhận tiền: ${receiverName} trùng khớp với thông tin trong giấy tờ tùy thân khách hàng cung cấp ?",
        title: '<spring:message code="label.confirm.deposit"/>'
      }
    ).done(function () {
      $('#myFormCashOut').submit();
    }).fail(function () {

    });

  });


</script>

</html>