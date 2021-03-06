<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <spring:message code="label.bill.payment.${bill_payment_method}"
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
    <jsp:param name="nav" value="${bill_payment_method}"/>
</jsp:include>
<!-- /menu bar -->

<jsp:include page="../include_component/service_code_constants.jsp"/>

<spring:message code="common.invalid.input.text.4.16"
                var="label_invalid_text_number"></spring:message>
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
                    <div class="panel-body py-10">
                        <div class="form-group row mb-10">
                            <div class="full-width">
                                <h4 class="panel-title pl-0"><spring:message
                                        code="label.real.amount"/></h4>
                                <div class="clr"></div>
                            </div>
                        </div>

                        <div class="row col mb-20">
                            <div class="col-lg-1 col-4">
                                <img src="/assets/images/icon/payment/${bill_payment_method}.png"
                                     width="100"
                                     class="br3 pull-left" onerror="imgError(this)">
                            </div>
                            <div class="col-lg-11 col-8 mb-3">
                                <div class="col-lg-6 col-md-12 mb-3">
                    <span class="h4">
                        ${label_bill_payment_method}
                    </span>
                                    </br>
                                    <%--<span class="small txt-note-pin">EVN HCM</span>--%>
                                    <%--<div class="clr"></div>--%>
                                </div>
                                <div class="clr"></div>

                            </div>
                        </div>

                        <c:if test="${'water' eq bill_payment_method}">
                            <div class="row">
                                <div class="col-lg-12 col-md-8 mb-3">
                                    <label>
                                        <spring:message code="label.bill.payment.service.provider"/>
                                    </label>
                                </div>
                                <div class="col-lg-12 col-md-8 mb-3 offset-md-0 input-group">
                                    <select data-plugin="select2" class="form-control"
                                            id="serviceCodeWater"
                                            name="serviceCodeWater" required>
                                        <c:forEach var="enabledService" items="${enabledServices}">
                                            <option value="${enabledService.serviceCode}" ${serviceCodeWater eq enabledService.serviceCode ? 'selected' : ''}>${enabledService.serviceName}&nbsp;(${enabledService.serviceDesc})
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" id="hidden_serviceCodeWater"
                                           name="serviceCodeWater"
                                           value="${serviceCodeWater}">
                                    <input type="hidden" id="hidden_serviceCodeNameWater"
                                           name="serviceNameProvider"
                                           value="${serviceNameProvider}">
                                </div>
                            </div>
                        </c:if>

                        <div class="row">
                            <div class="errorInvoiceEnter"></div>
                            <div class="col-lg-12 col-md-8 mb-3">
                                <label>
                                    <spring:message code="label.bill.payment.enter.customer.code"/>
                                </label>
                            </div>
                            <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                                <div class="input-group">
                                    <input type="text" id="invoiceCode"
                                           name="invoiceCode"
                                           class="form-control"
                                    <%--pattern="([0-9])+(?:-?\d)--%>
                                    <%--pattern="[0-9]"--%>
                                           required onchange="getValueInvoice(this)"
                                           minlength="4" maxlength="16"
                                           title="${label_invalid_text_number}" required>

                                    <%--<input type="text" id="hidden_requestId" name="requestId"/>--%>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-9 offset-md-3">
                                <div class="pull-right">
                                    <button type="button" id="payment_btn"
                                            class="btn btn-primary btn-sm pull-right"
                                            autofocus="">
                                        <spring:message code="common.btn.next"/>
                                        <i class="icon wb-arrow-right ml-10"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- footer -->
<jsp:include page="../include_page/footer.jsp"/>
<!-- /footer -->
<jsp:include page="../include_page/js_daterangepicker.jsp"/>

</body>
<script type="text/javascript">
  $(document).ready(function () {
    var value = $(this).find(":selected").val()
    $('#hidden_serviceCodeWater').val(value);

    var valueName = $(this).find(":selected").text()
    $('#hidden_serviceCodeNameWater').val(valueName);
  });

  $('#serviceCodeWater').on('change', function () {
    var valueCode = $(this).find(":selected").val()
    $('#hidden_serviceCodeWater').val(valueCode);

    var valueName = $(this).find(":selected").text()
    $('#hidden_serviceCodeNameWater').val(valueName);

  });

  function getValueInvoice(elem) {
    var _requestId = elem.value;
    $('#invoiceCode').val(_requestId);

    var prServiceCode = $('#hidden_serviceCodeWater').val();

    if (_requestId.length < 4) {
      var $newErrorInvoiceEnter = $("<div id=\"new-invoice-error\">\n"
          + "              <div class=\"col-12\">\n"
          + "                <div class=\"text-danger error-message mb-10\">\n"
          + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;"
          + "${label_invalid_text_number}" + " </small>\n"
          + "                </div>\n"
          + "              </div>\n"
          + "            </div>");

      $('.errorInvoiceEnter').html($newErrorInvoiceEnter);
      $('button[type="button"]').attr("disabled", true);
    } else {
      $('.errorInvoiceEnter').empty();
      $('button[type="button"]').attr("disabled", false);
      $('#payment_btn').attr("onclick",
          "window.location.replace('/bill-payment/${bill_payment_method}/management/detail-".concat(
              _requestId).concat('?serviceCode=').concat(prServiceCode).concat("')"));
    }
  }
</script>

<%--Handle error images--%>
<script>
  function imgError(image) {
//    image.src = "/assets/images/placeholder100.png";
  }
</script>
</html>