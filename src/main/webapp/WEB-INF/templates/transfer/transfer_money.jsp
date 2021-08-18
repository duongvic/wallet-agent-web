<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="label.moneytransfer"/> - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="transMoney"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><a href="#"><spring:message code="label.moneytransfer"/></a></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.moneytransfer"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">
            <form class="form-horizontal" method="post" action="/transfer/money-verify">
              <div class="form-group row mb-10">
                <div class="full-width">
                  <h3 class="panel-title pl-0"><spring:message code="money.transfer.info"/></h3>
                  <div class="clr"></div>
                </div>
              </div>
              
            <c:if test="${(codeErr != null && fn:length(codeErr) gt 0) }">
              <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
              </div>
            </c:if>

              <div class="form-group row mb-10 pos-relative">
                <input type="text" class="form-control" id="payeePhoneNumber"
                       name="payeePhoneNumber" placeholder="<spring:message code="label.payee.phone.number.placeholder"/>"
                       value="${payeePhoneNumber != null ? payeePhoneNumber : (!empty listPhoneMoneyTransfer ? listPhoneMoneyTransfer[0].value : '')}" required/>
                <div class="price-radio mt-5">
                  <c:if test="${listPhoneMoneyTransfer != null}">
                    <c:forEach var="item" items="${listPhoneMoneyTransfer}" varStatus="rowId">
                      <div class="radio-custom radio-info radio-inline text-center">
                        <input type="radio" id="${rowId.count}" name="payeePhoneNumber" value="${item.value}"
                               onclick="getSoPhone(this)" ${(payeePhoneNumber == null && rowId.count eq '1') || (item.value eq payeePhoneNumber) ? 'checked' : ''}>
                        <label for="${rowId.count}"><span>${item.value}</span></label>
                      </div>
                    </c:forEach>
                  </c:if>
                </div>
              </div>

              <div class="form-group row mb-10 pos-relative">
                <input type="text" class="form-control num currency-input" id="amount" name="amount"
                       value="${(amount != null && amount ne '') ? amount : '100.000'}" placeholder="<spring:message code="money.transfer"/>"
                       required/>
                <label class="form-control-label px-0 label-control-right"><span><spring:message code="money.transfer.fee"/>:</span>&nbsp;<span
                    class="txt-note-pin vnd" style="padding-right: 0!important;" id="feeAmount">0</span>
                </label>
                <div class="price-radio mt-5">
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt1" name="numberdt2" value="100000" ${(amount == null || 100000 eq amount || amount eq '') ? 'checked' : ''}
                           onclick="getSoTienNap(this)">
                    <label for="sdtt1"><span>100.000 </span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt2" name="numberdt2" value="200000" ${200000 eq amount ? 'checked' : ''}
                           onclick="getSoTienNap(this)">
                    <label for="sdtt2"><span>200.000 </span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt3" name="numberdt2" value="500000" ${500000 eq amount ? 'checked' : ''}
                           onclick="getSoTienNap(this)">
                    <label for="sdtt3"><span>500.000 </span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt4" name="numberdt2" value="1000000" ${1000000 eq amount ? 'checked' : ''}
                           onclick="getSoTienNap(this)">
                    <label for="sdtt4"><span>1.000.000 </span></label>
                  </div>
                </div>
              </div>

              <div class="form-group row mb-0">
                <label class="form-control-label px-0"><spring:message code="label.amount"/>: <b class="text-amount"
                    id="realAmount">100.000đ</b></label>
              </div>
              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit" class="btn btn-primary btn-sm pull-right"> <spring:message code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i>
                  </button>
                </div>
              </div>
              <%--request dữ liệu--%>
              <input type="hidden" name="feeAmount" id="hidden_feeAmount" value="${feeAmount}">
              <input type="hidden" id="hidden_realAmount" name="realAmount" value="${realAmount}">
              <%--end request dữ liệu--%>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import url="../include_component/frame_last_transaction_transfer.jsp"/>
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
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
      thanhToan();
    });
  })(document, window, jQuery);

  function getSoPhone(elem) {
    var phone = elem.value;
    document.getElementById('payeePhoneNumber').value = phone;
    thanhToan();
  }

  function getSoTienNap(elem) {
    var x = elem.value;
    document.getElementById('amount').value = formatCurrency(x);
    thanhToan();
  }

  function thanhToan() {
    var realAmount = 0;
    var feeAmout = 0;
    var amount = 0;
    amount = parseFloat(currencyToNumber(document.getElementById('amount').value));
    feeAmout = parseFloat(currencyToNumber(document.getElementById('feeAmount').innerText));
    if (document.getElementById('amount').value == null || document.getElementById(
            'amount').value == 0) {
      realAmount = amount;
    }
    else {
      realAmount = amount + feeAmout;
    }
    document.getElementById('realAmount').innerText = formatCurrency(realAmount);

    document.getElementById('hidden_realAmount').value = realAmount;
    document.getElementById('hidden_feeAmount').value = feeAmout;
  }

  $("#amount").on("input", function () {
    thanhToan();
  });
</script>
</html>