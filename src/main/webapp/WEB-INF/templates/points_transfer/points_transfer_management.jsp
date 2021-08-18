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
  <jsp:param name="nav" value="menuPointTransfer"/>
</jsp:include>
<!-- /menu bar -->

<jsp:include page="../include_component/service_code_constants.jsp"/>

<spring:message code="common.invalid.input.text.4.16" var="label_invalid_text_number"></spring:message>
<spring:message code="common.invalid.phone" var="label_invalid_phone"></spring:message>
<spring:message code="label.content.transfers" var="label_content"></spring:message>

<spring:message code="label.content" var="label_invalid_content"></spring:message>

<spring:message code="lable.enter.into" var="label_invalid_input"/>
<spring:message code="label.payer.name" var="label_payer_name"/>
<spring:message code="label.invalid.amount.points.transfer" var="label_invalid_amount"></spring:message>
<spring:message code="label.enter.otp" var="label_enter_otp"></spring:message>

<c:set var="isKYC" value="${kyc}" scope="page"/>

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
          <form id="myFormPointTransfer" action="/points-transfer/info" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="panel-body py-10">

              <%--<div class="row mb-10">--%>
              <%--<div class="col-lg-12 col-md-8 mb-3">--%>
              <%--<label>--%>
              <%--<strong style="color: red">*</strong>&nbsp;--%>
              <%--<spring:message code="label.payer.name"/>--%>
              <%--</label>--%>
              <%--</div>--%>
              <%--<div class="col-lg-12 col-md-8 mb-3 offset-md-0">--%>
              <%--<div class="input-group">--%>
              <%--<input type="text" id="senderName"--%>
              <%--name="senderName"--%>
              <%--class="form-control"--%>
              <%--onchange="getValuePayer(this)"--%>
              <%--required--%>
              <%-->--%>
              <%--</div>--%>
              <%--</div>--%>
              <%--<div class="errorPayer"></div>--%>
              <%--</div>--%>

              <%--<div class="row mb-10">--%>
              <%--<div class="col-lg-12 col-md-8 mb-3">--%>
              <%--<label>--%>
              <%--<strong style="color: red">*</strong>&nbsp;--%>
              <%--<spring:message code="label.payer.is.phone.number"/>--%>
              <%--</label>--%>
              <%--</div>--%>
              <%--<div class="col-lg-12 col-md-8 mb-3 offset-md-0">--%>
              <%--<div class="input-group">--%>
              <%--<input type="text" id="senderMsisdn"--%>
              <%--name="senderMsisdn"--%>
              <%--class="form-control"--%>
              <%--onchange="getValuePayerPhone(this)"--%>
              <%--pattern="[0-9]{10}"--%>
              <%--maxlength="10" onkeypress="return isNumberKey(event)"--%>
              <%--required--%>
              <%--/>--%>
              <%--</div>--%>
              <%--</div>--%>
              <%--<div class="errorPayerPhone"></div>--%>
              <%--</div>--%>

              <%--<div class="form-group row mb-10">--%>
              <%--<div class="myCheckBox">--%>
              <%--<input type="checkbox" value="" id="chkPayerPhone" name="chkPayerPhone"/>--%>
              <%--<label for="chkPayerPhone" style="margin-left: 0.8em;"></label>--%>
              <%--</div>--%>
              <%--<div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">--%>
              <%--<label class="pull-left"><spring:message code="label.cash.in.payee"/> </label>--%>
              <%--</div>--%>
              <%--</div>--%>

              <div class="row mb-10">
                <div class="col-lg-12 col-md-8 mb-3">
                  <label>
                    <strong style="color: red">*</strong>&nbsp;
                    <spring:message code="label.payee.phone.number"/>
                  </label>
                </div>
                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                  <div class="input-group">
                    <input type="text" id="receiverMsisdn"
                           name="receiverMsisdn"
                           class="form-control"
                           onchange="getValuePayeePhoneNumber(this)"
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
                    <spring:message code="label.number.points.transfer"/>
                  </label>
                </div>
                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                  <div class="form-group row mb-10 pos-relative">
                    <input type="text" class="form-control currency-input" name="amount"
                           style="margin: 0 10px;"
                           id="amount"
                           value="${(amount != null && amount ne '') ? amount : '100000'}"
                           placeholder="<spring:message code="fundin.money"/>"
                           required/>
                    <label class="form-control-label px-0 label-control-right mr-10" data-toggle="tooltip"
                           title="${label_invalid_amount}"><i class="fa fa-question-circle"></i></label>
                  </div>
                  <div class="price-radio mt-5">
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt0" name="numberdt2"
                             value="100000" ${amount eq '100000' || amount == null || amount eq '' ? 'checked' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt0"><span>100.000 </span></label>
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
                    <%--<strong style="color: red">*</strong>&nbsp;--%>
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
                            onclick="paymentPointsTransfer()"
                            class="btn btn-primary btn-sm pull-right">
                      <spring:message code="common.btn.next"/>
                      <i class="icon wb-arrow-right ml-10"></i>
                    </button>
                  </div>
                </div>
              </div>

              <div class="modal fade" id="dialogPointsTransfer"
                   tabindex="-1" role="dialog"
                   aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                  <div class="modal-content">
                    <div class="modal-body">
                      <div class="row mb-10">
                        <strong class="col-12 text-center control-label"><spring:message
                            code="notification.title"/> </strong>
                      </div>
                      <div class="row mb-10">
                        <label class="col-12 text-center"><spring:message
                            code="label.please.verify.acc.before.using.the.service"/></label>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <%--<button type="button" class="btn btn-secondary" onclick="closeCashInForMe()">Hủy</button>--%>
                      <%--<button type="button" class="btn btn-primary" onclick="actionVerifyKYC()"><spring:message--%>
                      <%--code="system.service.popup.delete.lable.yes"/></button>--%>

                      <a href="${pageContext.request.contextPath}/account/info" class="btn btn-primary"><spring:message
                          code="system.service.popup.delete.lable.yes"/></a>
                    </div>
                  </div>
                </div>
              </div>

              <%--<div class="modal fade" id="dialogErrorPointsTransfer"--%>
              <%--tabindex="-1" role="dialog"--%>
              <%--aria-labelledby="exampleModalCenterTitle" aria-hidden="true">--%>
              <%--<div class="modal-dialog modal-dialog-centered" role="document">--%>
              <%--<div class="modal-content">--%>
              <%--<div class="modal-header">--%>
              <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
              <%--<span aria-hidden="true">&times;</span>--%>
              <%--</button>--%>
              <%--</div>--%>
              <%--<div class="modal-body">--%>
              <%--<div class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">--%>
              <%--<i class="icon pe-close-circle badge-danger br-100 fs40"></i>--%>
              <%--</div>--%>
              <%--<div class="row mb-10">--%>
              <%--<input type="hidden" id="error" name="error" class="col-12">--%>
              <%--<label class="col-12 text-center control-label" id="lblError"></label>--%>
              <%--</div>--%>
              <%--</div>--%>
              <%--</div>--%>
              <%--</div>--%>
              <%--</div>--%>

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


  });

  //-----------isValid dữ liệu-----------------

  function getValuePayeePhoneNumber(elem) {
    var _requestId = elem.value.trim();
    document.getElementById("receiverMsisdn").value = _requestId;
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

  //-----------isValid dữ liệu-----------------

  function paymentPointsTransfer() {

    var isKYC = '${isKYC}';
    var inpPayeePhone = document.getElementById("receiverMsisdn").value;
    var inpContent = document.getElementById("transContent").value;
    var inpAmount = document.getElementById("amount").value;

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

    if (isKYC === '4') {
      console.log('KYC', ${isKYC});
      $('#myFormPointTransfer').submit();
    } else {
      $('#dialogPointsTransfer').modal('show');
    }
  };

  function actionVerifyKYC() {
  };


  // function closeCashInForMe() {
  //   $('#dialogPointsTransfer').modal('hide');
  // };

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
    if (inpContent.trim().length > 255) return false;
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