<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <title><spring:message code="topup.page.title"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="topUpMenu"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item"><spring:message code="label.convenient.service"/></li>
            <li class="breadcrumb-item active"><a href="#"><spring:message
                    code="topup.page.bread.crum"/></a></li>
        </ol>
        <h1 class="page-title"><spring:message code="topup.page.sub.title"/></h1>
    </div>
    <div class="page-content container-fluid">
        <div class="row">
            <div class="col-md-7">
                <div class="panel panel-bordered">
                    <div class="panel-body py-10">
                        <form class="form-horizontal" method="post"
                              action="/topup/top-up-next-step-verify">
                            <div class="form-group row mb-10">
                                <div class="full-width">
                                    <h3 class="panel-title pl-0"><spring:message
                                            code="topup.panel.title"/></h3>
                                    <div class="clr"></div>
                                </div>
                            </div>
                            <div class="form-group row mb-10 pos-relative">
                                <c:set var="usernameNumber"><sec:authentication
                                        property="principal.username"/></c:set>
                                <input type="text" class="form-control" id="phoneNumber"
                                       name="phoneNumber" required
                                       value="${(phoneNumber != null && phoneNumber ne '') ? phoneNumber : usernameNumber}"
                                       maxlength="10" onkeypress="return isNumberKey(event)"/>
                                <label class="form-control-label px-0 label-control-right"><spring:message
                                        code="topup.input.placeholder.phone.number"/></label>

                                <div class="price-radio mt-5">
                                    <div class="radio-custom radio-info radio-inline text-center">
                                        <input type="radio" id="sdt1" name="phoneNumber"
                                        ${(phoneNumber == null || phoneNumber eq '' || phoneNumber eq usernameNumber) ? 'checked' : ''}
                                               value="${usernameNumber}" onclick="getSoPhone(this)">
                                        <label for="sdt1"><span>${usernameNumber}</span></label>
                                    </div>

                                    <c:if test="${listPhoneTopUp != null}">
                                        <c:forEach var="item" items="${listPhoneTopUp}"
                                                   varStatus="rowId">
                                            <div class="radio-custom radio-info radio-inline text-center">
                                                <input type="radio" id="${rowId.count}"
                                                       name="phoneNumber"
                                                    ${item.value eq phoneNumber ? 'checked' : ''}
                                                       value="${item.value}"
                                                       onclick="getSoPhone(this)">
                                                <label for="${rowId.count}"><span>${item.value}</span></label>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>

                            </div>
                            <div id="select-value" class="form-group row mb-10 pos-relative">
                                <span class="select-group__lable"><spring:message
                                        code="topup.input.placeholder.select.price"/></span>
                                <select data-plugin="select2" class="form-control" id="faceValue"
                                        name="faceValue" required onchange="thanhToanThe(this)">
                                </select>
                            </div>
                            <div id="commission-value" class="row">
                                <label><spring:message code="label.discount"/>:</label>&nbsp;
                                <span class="text-amount" id="disCount">0&nbsp;Zpoint</span>
                            </div>
                            <div id="real-amount-value" class="row">
                                <label><spring:message code="label.real.amount"/>:</label>&nbsp;
                                <span class="text-amount" id="realAmount"></span>
                                <input type="hidden" id="hidden_realAmount" name="realAmount"
                                       value="${realAmount}">
                            </div>

                            <div id="next-step-button" class="row">
                                <div class="col-md-9 offset-md-3">
                                    <button type="submit" id="btNext"
                                            class="btn btn-primary btn-sm pull-right">
                                        <spring:message code="common.btn.next"/><i
                                            class="icon wb-arrow-right ml-10"></i></button>
                                </div>
                            </div>
                            <input type="hidden" id="hidden_disCount" name="disCount"
                                   value="${disCount}">
                            <input type="hidden" id="hidden_tenSP" name="tenSP" value="${tenSP}">
                            <input type="hidden" id="disCountPercent" name="disCountPercent"
                                   value="${disCountPercent}">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-5">
                <!-- Giao dịch gần nhất -->
                <c:import url="../include_component/frame_last_transaction_topup.jsp"/>
                <!-- /Giao dịch gần nhất  -->
            </div>
        </div>

    </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

<script>
  var currentCommission = {};

  $(document).ready(function () {
    loadPhoneCommission($("#phoneNumber").val(), $("#faceValue"), '',
        "${priceTopup != null ? priceTopup : 100000}", "${_csrf.parameterName}", "${_csrf.token}");
    confirmPhoneNumber($("#phoneNumber").val());
  });

  function thanhToan() {
    var sumAmount = $('#faceValue').val();
    var realAmount = sumAmount - currencyToNumber($('#disCount').html());
    $('#realAmount').html(formatCurrency(realAmount).concat(' ').concat('Zpoint'));
    $('#hidden_realAmount').val(realAmount);
    $('#hidden_disCount').val($('#disCount').html());
  }

  function getSoPhone(elem) {
    var x = elem.value;
    $('#phoneNumber').val(x);
    $('#hidden_realAmount').val($('#faceValue').val() - currencyToNumber($('#disCount').html()));
    loadPhoneCommission(x, $("#faceValue"), '', $("#faceValue").val(), "${_csrf.parameterName}",
        "${_csrf.token}");
    $('#btNext').attr('disabled', false);
  }

  function formatCurrency(input) {
    var currency = parseInt(input).toString();

    function format(currency) {
      if (currency.length > 3) {
        var length = currency.length;
        var newCurrency;
        var remainPart;

        newCurrency = currency.substring(length - 3, length);
        remainPart = currency.substring(0, length - newCurrency.length);
        remainPart = format(remainPart);

        return remainPart + "." + newCurrency;
      } else {
        return currency;
      }
    }

    return format(currency);
  }

  function currencyToNumber(currency) {
    return currency.replace(/[^0-9]+/g, "");
  }

  function thanhToanThe(price) {
    var commision = currentCommission[price.value].commision;
    $('#disCount').html(formatCurrency(parseInt(parseInt($('#faceValue').val()) * commision / 100
        + currentCommission[price.value].commisionFixedAmount)).concat(' ').concat('Zpoint'));
    $('#disCountPercent').val(commision);
    thanhToan();
  }

  function confirmPhoneNumber(phone) {
//    var phoneValue = phone;
    $('#phoneNumber').on('input', function () {
      if ($("#phoneNumber").val().length == phone.length) {
        loadPhoneCommission($("#phoneNumber").val(), $("#faceValue"), '', $("#faceValue").val(),
            "${_csrf.parameterName}", "${_csrf.token}");
        $('#btNext').attr('disabled', false);
      } else {
        $('#btNext').attr('disabled', true);
      }
    });
  }

  function onLoadCommissionToModel(commissionType, data, commissionFee, prices) {
    currentCommission = commissionFee;
    $('#disCount').html(formatCurrency(
        parseInt(parseInt($('#faceValue').val()) * currentCommission[prices[0]].commision / 100
            + currentCommission[prices[0]].commisionFixedAmount)).concat(' ').concat('Zpoint'));
    thanhToan();
    var serviceDesc = data.serviceDesc.split(" ");
    $('#disCountPercent').val(currentCommission[prices[0]].commision);
    // $('#hidden_tenSP').val(serviceDesc[serviceDesc.length - 1]);
  }

  function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
    else {
      return true;
    }
    confirmPhoneNumber($("#phoneNumber").val());
  }
</script>
</html>