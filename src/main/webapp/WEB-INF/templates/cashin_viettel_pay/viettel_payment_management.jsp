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
  <spring:message code="label.fundin.viettel.pay"
                  var="label_bill_payment_method"/>
  <title>
    ${label_bill_payment_method}
  </title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->

  <style>
    .select2-container--default .select2-selection--single .select2-selection__rendered {
      padding-left: 0 !important;
    }

  </style>
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification -->

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <jsp:param name="nav" value="menuViettelPay"/>
</jsp:include>
<!-- /menu bar -->

<jsp:include page="../include_component/service_code_constants.jsp"/>

<spring:message code="common.invalid.input.text.4.16" var="label_invalid_text_number"></spring:message>
<spring:message code="common.invalid.phone" var="label_invalid_phone"></spring:message>
<spring:message code="label.content.transfers" var="label_content"></spring:message>

<spring:message code="label.content" var="label_invalid_content"></spring:message>

<spring:message code="lable.enter.into" var="label_invalid_input"/>
<spring:message code="label.payer.name" var="label_payer_name"/>
<spring:message code="label.invalid.amount" var="label_invalid_amount"></spring:message>
<spring:message code="label.enter.otp" var="label_enter_otp"></spring:message>

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message
          code="label.convenient.service"/></li>
      <li class="breadcrumb-item">${label_bill_payment_method}</li>
    </ol>
    <h1 class="page-title">${label_bill_payment_method}</h1>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-12">
        <div class="panel panel-bordered">
          <form id="myForm" action="/cashin-viettel-pay/info" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="panel-body py-10">

              <div class="row mb-10">
                <div class="col-lg-12 col-md-8 mb-3">
                  <label>
                    <strong style="color: red">*</strong>&nbsp;
                    <spring:message code="label.payer.name"/>
                  </label>
                </div>
                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                  <div class="input-group">
                    <input type="text" id="senderName"
                           name="senderName"
                           class="form-control"
                           onchange="getValuePayer(this)"
                           required
                    >
                  </div>
                </div>
                <div class="errorPayer"></div>
              </div>

              <div class="row mb-10">
                <div class="col-lg-12 col-md-8 mb-3">
                  <label>
                    <strong style="color: red">*</strong>&nbsp;
                    <spring:message code="label.payer.is.phone.number"/>
                  </label>
                </div>
                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                  <div class="input-group">
                    <input type="text" id="senderMsisdn"
                           name="senderMsisdn"
                           class="form-control"
                           onchange="getValuePayerPhone(this)"
                           pattern="[0-9]{10}"
                           maxlength="10" onkeypress="return isNumberKey(event)"
                           required
                    />
                  </div>
                </div>
                <div class="errorPayerPhone"></div>
              </div>

              <div class="form-group row mb-10">
                <div class="myCheckBox">
                  <input type="checkbox" value="" id="chkPayerPhone" name="chkPayerPhone"/>
                  <label for="chkPayerPhone" style="margin-left: 0.8em;"></label>
                </div>
                <div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">
                  <label class="pull-left"><spring:message code="label.cash.in.payee"/> </label>
                </div>
              </div>

              <div class="row mb-10">
                <div class="col-lg-12 col-md-8 mb-3">
                  <label>
                    <strong style="color: red">*</strong>&nbsp;
                    <spring:message code="label.transfer.to.phone"/>
                  </label>
                </div>
                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                  <div class="input-group">
                    <input type="text" id="hidden_ReceiverMsisdn"
                           name="hidden_ReceiverMsisdn"
                           class="form-control"
                           onchange="getValuePayeePhone(this)"
                           pattern="[0-9]{10}"
                           maxlength="10" onkeypress="return isNumberKey(event)"
                           required
                    />

                    <input type="hidden" id="receiverMsisdn"
                           name="receiverMsisdn"
                           class="form-control"
                           <%--onchange="getValuePayeePhone(this)"--%>
                           pattern="[0-9]{10}"
                           maxlength="10" onkeypress="return isNumberKey(event)"
                           required
                    />

                  </div>
                </div>
                <div class="errorPayeePhone"></div>
              </div>

              <div class="row mb-10">
                <div class="col-lg-12 col-md-8 mb-3">
                  <label>
                    <strong style="color: red">*</strong>&nbsp;
                    <spring:message code="fundin.order.request"/>
                  </label>
                </div>
                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                  <div class="form-group row mb-10 pos-relative">
                    <input type="text" class="form-control currency-input" name="amount"
                           style="margin: 0 10px;"
                           id="amount"
                           value="${(amount != null && amount ne '') ? amount : '50000'}"
                           placeholder="<spring:message code="fundin.money"/>"
                           required/>
                    <label class="form-control-label px-0 label-control-right mr-10" data-toggle="tooltip"
                           title="${label_invalid_amount}"><i class="fa fa-question-circle"></i></label>
                  </div>
                  <div class="price-radio mt-5">
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt0" name="numberdt2"
                             value="50000" ${amount eq '50000' || amount == null || amount eq '' ? 'checked' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt0"><span>50.000 </span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt1" name="numberdt2"
                             value="100000" ${amount eq '100000' ? 'checked' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt1"><span>100.000 </span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt2" name="numberdt2"
                             value="200000" ${amount eq '200000' ? 'checked' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt2"><span>200.000 </span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt3" name="numberdt2"
                             value="500000" ${amount eq '500000' ? 'checked' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt3"><span>500.000 </span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt4" name="numberdt2"
                             value="1000000" ${amount eq '1000000' ? 'checked' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt4"><span>1.000.000 </span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt5" name="numberdt2"
                             value="2000000" ${amount eq '2000000' ? 'checked' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt5"><span>2.000.000 </span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt6" name="numberdt2"
                             value="5000000" ${amount eq '5000000' ? 'checked' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt6"><span>5.000.000 </span></label>
                    </div>
                  </div>
                </div>
                <div class="errorAmount"></div>
              </div>


              <div class="row mb-10">
                <div class="col-lg-12 col-md-8 mb-3">
                  <label>
                    <strong style="color: red">*</strong>&nbsp;
                    <spring:message code="label.content"/>
                  </label>
                </div>
                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                  <textarea class="form-control" rows="5" cols="50" placeholder="${label_content}"
                            onchange="getValueContent(this)"
                            onkeypress="return isNumberKeyPreventVietName(event)"
                            id="transContent" name="transContent" maxlength="255" required></textarea>
                </div>
                <div class="errorContent"></div>
              </div>

              <div class="row mb-10">
                <div class="col-md-9 offset-md-3">
                  <div class="pull-right">
                    <button type="button" id="payment_btn"
                    <%--data-toggle="modal" data-dismiss="modal" for=""--%>
                    <%--data-target="#exampleModalCenter"--%>
                            onclick="paymentClick()"
                            class="btn btn-primary btn-sm pull-right">
                      <spring:message code="common.btn.next"/>
                      <i class="icon wb-arrow-right ml-10"></i>
                    </button>
                  </div>
                </div>
              </div>

              <div class="modal fade" id="dialogCashIn"
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

              <div class="modal fade" id="dialogErrorCashIn"
                   tabindex="-1" role="dialog"
                   aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body">
                      <div class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                        <i class="icon pe-close-circle badge-danger br-100 fs40"></i>
                      </div>
                      <div class="row mb-10">
                        <input type="hidden" id="error" name="error" class="col-12">
                        <label class="col-12 text-center control-label" id="lblError"></label>
                      </div>
                    </div>
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
<c:import url="../include_page/footer_js.jsp"/>
<!-- /footer -->
<jsp:include page="../include_page/js_daterangepicker.jsp"/>

</body>
<script type="text/javascript">
  $(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();

    // var value = $(this).find(":selected").val()
    // $('#hidden_serviceCodeWater').val(value);
    //
    // var valueName = $(this).find(":selected").text()
    // $('#hidden_serviceCodeNameWater').val(valueName);

  });

  // $('#serviceCodeWater').on('change', function () {
  //   var valueCode = $(this).find(":selected").val()
  //   $('#hidden_serviceCodeWater').val(valueCode);
  //
  //   var valueName = $(this).find(":selected").text()
  //   $('#hidden_serviceCodeNameWater').val(valueName);
  // });

  var booleanSendMe = false;

  $('#chkPayerPhone').on('change', function () {
    var valPayeePhone = document.getElementById("senderMsisdn").value;
    if ($(this).is(':checked')) {
      booleanSendMe = true;
      $('#chkPayerPhone').prop('checked', true);
      $('#hidden_ReceiverMsisdn').attr("disabled", true);
      document.getElementById("receiverMsisdn").value = valPayeePhone;
      document.getElementById("hidden_ReceiverMsisdn").value = valPayeePhone;
      if (valPayeePhone.length === 10) {
        $('.errorPayeePhone').empty();
      }
    }
    else {
      booleanSendMe = false;
      $('#chkPayerPhone').prop('checked', false);
      $('#hidden_ReceiverMsisdn').attr("disabled", false);
      document.getElementById("receiverMsisdn").value = "";
      document.getElementById("hidden_ReceiverMsisdn").value = "";
    }
  });


  //-----------isValid dữ liệu-----------------

  function getValuePayer(elem) {
    var _requestId = elem.value;
    if (_requestId.length === 0) {
      var $newErrorPayer = $("<div id=\"new-payer-error\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_invalid_input} ${label_payer_name}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorPayer').html($newErrorPayer);
    }

    else {
      $('.errorPayer').empty();
    }
  }

  function getValuePayerPhone(elem) {
    var _requestId = elem.value.trim();
    if (_requestId != "" && (_requestId === document.getElementById("receiverMsisdn").value.trim())) {
      booleanSendMe = true;
      $('#hidden_ReceiverMsisdn').attr("disabled", true);
      $('#chkPayerPhone').prop('checked', true);
      console.log('booleanSendMe:', booleanSendMe);
    } else {
      booleanSendMe = false;
      $('#hidden_ReceiverMsisdn').attr("disabled", false);
      $('#chkPayerPhone').prop('checked', false);
      console.log($('#chkPayerPhone').is(':checked'));
    }
    if (_requestId.length < 10) {
      var $newErrorPayerPhone = $("<div id=\"new-payer-phone-error\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_invalid_phone}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorPayerPhone').html($newErrorPayerPhone);
    }
    else {
      $('.errorPayerPhone').empty();
    }
  }

  function getValuePayeePhone(elem) {
    var _requestId = elem.value.trim();
    document.getElementById("receiverMsisdn").value =_requestId;
    if (_requestId != "" && (_requestId === document.getElementById("senderMsisdn").value.trim())) {
      booleanSendMe = true;
      $('#hidden_ReceiverMsisdn').attr("disabled", true);

      $('#chkPayerPhone').prop('checked', true);

      console.log($('#chkPayerPhone').is(':checked'));
    } else {
      booleanSendMe = false;
      $('#hidden_ReceiverMsisdn').attr("disabled", false);
      $('#chkPayerPhone').prop('checked', false);
    }
    if (_requestId.length < 10) {
      var $newErrorPayeePhone = $("<div id=\"new-payee-phone-error\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_invalid_phone}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorPayeePhone').html($newErrorPayeePhone);
    }
    else {
      $('.errorPayeePhone').empty();
    }
  }

  function getValueContent(elem) {
    var _requestId = elem.value;
    if (_requestId.trim().length === 0) {
      var $newErrorContent = $("<div id=\"new-content-error\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_invalid_input} ${label_invalid_content}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorContent').html($newErrorContent);
    }
    else {
      $('.errorContent').empty();
    }
  }

  function getValueSenderOTP(elem) {
    var _requestId = elem.value;
    $('#senderOtp').val(_requestId);

    if (_requestId.trim().length === 0) {
      var $newErrorOTP = $("<div id=\"new-error-otp\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_enter_otp}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorSenderOtp').html($newErrorOTP);
    }
    else {
      $('.errorSenderOtp').empty();
    }
  }

  //-----------isValid dữ liệu-----------------

  function paymentClick() {

    var inpPayerPhone = document.getElementById("senderMsisdn").value;
    var inpPayer = document.getElementById("senderName").value;
    var inpPayeePhone = document.getElementById("receiverMsisdn").value;
    var inpContent = document.getElementById("transContent").value;
    var inpAmount = document.getElementById("amount").value;

    if (checkLength(inpPayer) == false) {
      var $newErrorPayer = $("<div id=\"new-payer-error\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_invalid_input} ${label_payer_name}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorPayer').html($newErrorPayer);
      return false;
    }

    if (checkPhone(inpPayerPhone) == false) {
      var $newErrorPayerPhone = $("<div id=\"new-payer-phone-error\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_invalid_phone}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorPayerPhone').html($newErrorPayerPhone);
      return false;
    }

    if (checkPhone(inpPayeePhone) == false) {
      var $newErrorPayeePhone = $("<div id=\"new-payee-phone-error\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_invalid_phone}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorPayeePhone').html($newErrorPayeePhone);
      return false;
    }

    if (checkAmount(inpAmount) == false) {
      var $newErrorAmount = $("<div id=\"new-amount-error\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_invalid_amount}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorAmount').html($newErrorAmount);
      return false;
    }

    if (checkContentTransfers(inpContent) == false) {
      var $newErrorContent = $("<div id=\"new-content-error\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_invalid_input} ${label_invalid_content}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorContent').html($newErrorContent);
      return false;
    }
    if (booleanSendMe) {
      $.ajax({
        type: 'POST',
        url: '/ajax-controller/wallet/cash-in/otp/get',
        beforeSend: function (xhr) {
          if ("${_csrf.parameterName}" && "${_csrf.token}") {
            xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
          }
        },
        data: {
          "${_csrf.parameterName}": "${_csrf.token}",
          senderName: inpPayer,
          senderMsisdn: inpPayerPhone,
          receiverMsisdn: inpPayeePhone,
          amount: inpAmount,
          transContent: inpContent
        },
        timeout: 60000,
        cache: false,
        success: function (data) {
          if (data.status.code == 0) {
            document.getElementById("keyOtpFee").value = data.keyOtpFee;
            document.getElementById('enterOPTPhone').innerHTML = "Nhập OTP được gửi đến số điện thoại " + inpPayerPhone + " để xác nhận chuyển tiền";
            document.getElementById("btnCashInForMe").value = "btnCashInForMe";
            $('#dialogCashIn').modal('show');
          }
          else {
            // $.MessageBox({message: 'API wallet cash in otp get error'});
            document.getElementById("error").value = data.status.value;
            document.getElementById('lblError').innerHTML = data.status.value;
            $('#dialogErrorCashIn').modal('show');
          }
        }
      });
    } else {
      $('#myForm').submit();
    }
  };

  function cashInForMe() {
    var inpPayer = document.getElementById("senderName").value;
    var inpPayerPhone = document.getElementById("senderMsisdn").value;
    var inpPayeePhone = document.getElementById("receiverMsisdn").value;
    var inpContent = document.getElementById("transContent").value;
    var inpAmount = document.getElementById("amount").value;
    var inpSenderOTP = document.getElementById("senderOtp").value;
    var inpKeyOtpFee = document.getElementById("keyOtpFee").value;
    var $newErrorOTP = "";
    if (inpSenderOTP.length === 0) {
      $newErrorOTP = $("<div id=\"new-error-otp\">\n"
        + "              <div class=\"col-12\">\n"
        + "                <div class=\"text-danger error-message mb-10\">\n"
        + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
        + "${label_enter_otp}" + " </small>\n"
        + "                </div>\n"
        + "              </div>\n"
        + "            </div>");

      $('.errorSenderOtp').html($newErrorOTP);
    }
    else {
      $.ajax({
        type: 'POST',
        url: '/ajax-controller/wallet/cash-in/fee/get',
        beforeSend: function (xhr) {
          if ("${_csrf.parameterName}" && "${_csrf.token}") {
            xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
          }
        },
        data: {
          "${_csrf.parameterName}": "${_csrf.token}",
          senderName: inpPayer,
          senderMsisdn: inpPayerPhone,
          receiverMsisdn: inpPayeePhone,
          amount: inpAmount,
          transContent: inpContent,
          keyOtpFee: inpKeyOtpFee,
          senderOtp: inpSenderOTP,
        },
        timeout: 60000,
        success: function (data) {
          if (data.status.code !== 0) {
            $newErrorOTP = $("<div id=\"new-error-otp\">\n"
              + "              <div class=\"col-12\">\n"
              + "                <div class=\"text-danger error-message mb-10\">\n"
              + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
              + data.status.value + " </small>\n"
              + "                </div>\n"
              + "              </div>\n"
              + "            </div>");

            $('.errorCashInFeeGet').html($newErrorOTP);

            // document.getElementById("orderId").value = data.orderId;
            // document.getElementById("receiverName").value = data.receiverName;
            // document.getElementById("transFee").value = data.transFee;
            //
            //
            // var url = '/cashin-viettel-pay/info'
            // window.location.replace(url) ;
          }
          else {
            document.getElementById("orderIdForMe").value = data.orderId;
            document.getElementById("receiverNameForMe").value = data.receiverName;
            document.getElementById("transFeeForMe").value = data.transFee;

            // var url = '/cashin-viettel-pay/info'
            // window.location.replace(url) ;
            $('#myForm').submit();
          }
        }
      });
      // $('#myForm').submit();
    }

  }


  function closeCashInForMe() {
    $('.errorSenderOtp').empty();
    $('.errorCashInFeeGet').empty();
    document.getElementById("senderOtp").value = "";
    $('#dialogCashIn').modal('hide');
  };

  function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
    else {
      return true;
    }
  }

  function checkLength(inpPayer) {
    if (inpPayer.length <= 4) return false;
    return true;
  }

  function checkPhone(inpPayerPhone) {
    if (inpPayerPhone.length < 10 && inpPayerPhone.length === 0) return false;
    return true;
  }

  function checkContentTransfers(inpContent) {
    if (inpContent.trim().length === 0 || inpContent.trim().length > 255) return false;
    return true;
  }

  function checkAmount(inpAmount) {
    inpAmount = parseFloat(currencyToNumber(document.getElementById('amount').value));
    if (inpAmount === 0 || inpAmount < 50000) return false;
    return true;
  }

  function getSoTienNap(elem) {
    var x = elem.value;
    document.getElementById('amount').value = formatCurrency(x);
  }

  function isNumberKeyPreventVietName(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 191)
      return false;
    else {
      return true;
    }
  }

</script>

</html>