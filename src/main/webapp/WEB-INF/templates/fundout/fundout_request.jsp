<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="label.fundin.atm"/> - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="foHistor"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage"><spring:message code="label.fundout"/></a></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.fundout"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">
            <form class="form-horizontal" method="post" action="/fundout/request">
              <div class="form-group row mb-10">
                <c:if test="${(codeErr != null && codeErr != 'Success' && codeErr != '')}">
                  <small class="text-danger error-message"><i
                      class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                </c:if>
              </div>
              <div class="form-group row mb-10">
                <div class="full-width">
                  <img src="/assets/images/bank/${bankCode}.png" align="left" width="100"
                       style="margin-right:10px">
                  <div class="text-left">
                    <label>${bankDisplayName}</label>
                    <br>
                    <small>${bankAccountNumber}</small>
                  </div>
                  <%--<input type="hidden" id="phoneNumber" name="phoneNumber" value="${phoneNumber}">--%>
                  <%--<input type="hidden" id="bankCode" name="bankCode" value="${bankCode}">--%>
                  <%--<input type="hidden" id="bankAccountName" name="bankAccountName" value="${bankAccountName}">--%>
                  <%--<input type="hidden" id="ssn" name="ssn" value="${ssn}">--%>
                  <%--<input type="hidden" id="subscriptionId" name="bankCode" value="${subscriptionId}">--%>
                  <div class="clr"></div>
                </div>
              </div>
              <div class="form-group row mb-10 pos-relative">
                <input type="text" class="form-control" placeholder="<spring:message code="account.bank.number"/>"
                       name="bankAccountNumber" value="${bankAccountNumber}" required/>
                <%--<label class="form-control-label px-0 label-control-right">Số tài khoản</label>--%>
                <div class="price-radio mt-5 hidden">
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdt1" name="numberdt" checked="">
                    <label for="sdt1"><span>0902244419</span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdt2" name="numberdt">
                    <label for="sdt2"><span>0936660633</span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdt3" name="numberdt">
                    <label for="sdt3"><span>0989980816</span></label>
                  </div>
                </div>
              </div>
              <div class="form-group row mb-10 pos-relative">
                <input type="text" class="form-control" name="bankAccountName"
                       value="${bankAccountName}" placeholder="<spring:message code="account.bank.holder"/>" required/>
              </div>
              <div class="form-group row mb-10 pos-relative">
                <input type="text" class="form-control" name="chiNhanhBank" value="${chiNhanhBank != null ? chiNhanhBank : ''}"
                       placeholder="<spring:message code="account.bank.branch"/>"/>
              </div>

              <div class="form-group row mb-10 pos-relative">
                <input type="text" class="form-control currency-input" id="amount" name="amount" value="${amount != null ? amount : '100.000 đ'}"
                       placeholder="<spring:message code="fundout.money"/>" required/>

                <label class="form-control-label px-0 label-control-right vnd"><span id="feeAmount"><spring:message code="label.fee"/>: 0</span></label>
                <input type="hidden" name="feeAmount" id="hidden_feeAmount" value="${feeAmount}">

                <div class="price-radio mt-5">
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt1" name="numberdt2" value="100000" ${(amount eq null || amount eq '100000') ? 'checked=/"checked/"' : ''}
                           onclick="getSoTienRut(this)">
                    <label for="sdtt1"><span>100.000 đ</span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt2" name="numberdt2" value="200000" ${amount eq '200000' ? 'checked=/"checked/"' : ''}
                           onclick="getSoTienRut(this)">
                    <label for="sdtt2"><span>200.000 đ</span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt3" name="numberdt2" value="500000" ${amount eq '500000' ? 'checked=/"checked/"' : ''}
                           onclick="getSoTienRut(this)">
                    <label for="sdtt3"><span>500.000 đ</span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt4" name="numberdt2" value="1000000" ${amount eq '1000000' ? 'checked=/"checked/"' : ''}
                           onclick="getSoTienRut(this)">
                    <label for="sdtt4"><span>1.000.000 đ </span></label>
                  </div>
                </div>
              </div>

              <div class="form-group row mb-10 pos-relative">
                <input type="text" class="form-control" name="moneyNumber" value="${moneyNumber != null ? moneyNumber : ''}"
                       placeholder="Lý do"/>
              </div>

              <div class="form-group row mb-10">
                <input type="text" class="form-control col-md-9" name="checkCode" value="${checkCode != null ? checkCode : ''}"
                       placeholder="<spring:message code="label.check.code"/>"/>
                <label class="form-control-label px-0 col-md-3 text-center"><b>ABCDF</b></label>
              </div>
              <div class="form-group row mb-0">
                <label class="form-control-label px-0"><spring:message code="label.amount"/>: <b class="text-amount" id="realAmount"></b></label>
                <input type="hidden" id="hidden_realAmount" name="realAmount" value="${realAmount}">
              </div>

              <div class="row mb-10">
                <div class="form-group row col-lg-7 col-md-6 col-sm-12">
                  <div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">
                  </div>
                </div>
                <div class="col-lg-5 col-md-6 col-sm-12 text-right inline-block">
                  <a class="btn btn-default btn-sm mr-10" href="/fundout/history"> <spring:message code="common.btn.back"/></a>
                  <button type="submit" class="btn btn-primary btn-sm mr-10"> <spring:message code="label.fundout"/></button>

                </div>
              </div>

              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import url="../include_component/frame_last_transaction_fundOut_linkBank.jsp"/>
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
  $(document).ready(function() {
	thanhToan();
  });
  function getSoTienRut(elem) {
    var x = elem.value;
    document.getElementById('amount').value = formatCurrency(x);
    thanhToan();
  }

  function thanhToan() {
    var realAmount = 0;
    var amount = 0;
    var feeAmount = 0;
    if (document.getElementById('amount').value === null) {
      document.getElementById('amount').value = 0;
    }
    amount = parseFloat(currencyToNumber(document.getElementById('amount').value));
    feeAmount.value = parseFloat(currencyToNumber(document.getElementById('feeAmount').innerText));
    if (feeAmount > amount) {
      document.getElementById('realAmount').innerText = 0 + ' đ';
    }
    else {
      realAmount = parseFloat(amount) - parseFloat(feeAmount);
    }

    document.getElementById('realAmount').innerText = formatCurrency(realAmount);
    //get value
    document.getElementById('hidden_realAmount').value = realAmount;
    document.getElementById('hidden_feeAmount').value = feeAmount;
  }
  $("#amount").on("input", function() {
  	thanhToan();
  });
</script>
</html>