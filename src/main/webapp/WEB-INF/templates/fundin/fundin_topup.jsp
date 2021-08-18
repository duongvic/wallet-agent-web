<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="topup.page.title"/> - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp" />
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp" />
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <jsp:param name="nav" value="transMoney" />
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><a href="#"><spring:message code="topup.page.title"/></a></li>
    </ol>
    <h1 class="page-title"><spring:message code="topup.page.title"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">
            <form class="form-horizontal" method="get" action="/fundin/topup/logIn">
              <div class="form-group row mb-10">
                <div class="full-width">
                  <h3 class="panel-title pl-0">Thông tin nạp tiền điện thoại</h3>
                  <div class="clr"></div>
                </div>
              </div>
              <div class="form-group row mb-10 pos-relative">
                <input type="text" class="form-control" name="phoneNumber" value="${phoneNumber}" />
                <label class="form-control-label px-0 label-control-right">Số điện thoại được nạp</label>
                <div class="price-radio mt-5">
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
                <span class="select-group__lable">Mệnh giá nạp:</span>
                <select data-plugin="select2" class="form-control" id="faceValue" name="faceValue" onchange="thanhToan()">
                  <%--<option value="">Chọn mệnh giá nạp</option>--%>
                  <option value="100000" selected="selected">100.000đ</option>
                  <option value="10000">10.000đ</option>
                  <option value="20000">20.000đ</option>
                  <option value="50000">50.000đ</option>
                  <option value="200000">200.000đ</option>
                  <option value="500000">500.000đ</option>
                </select>
              </div>
              <div class="row">
                <label><spring:message code="money.transfer.fee"/> :</label>&nbsp;
                <span class="text-amount" id="disCount" name="disCount">1.000 đ</span>
              </div>
              <div class="row">
                <label><spring:message code="label.amount"/> </label>&nbsp;
                <span class="text-amount" id="realAmount" name="realAmount">99.000 đ</span>
              </div>

              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit" class="btn btn-primary btn-sm pull-right"> <spring:message code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i></button>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import url="../include_component/frame_last_transaction.jsp" />
        <!-- /Giao dịch gần nhất  -->
      </div>
    </div>

  </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp" />
<!-- /footer -->
</body>

<script>
  function thanhToan() {
    var realAmount = 0;
    var faceValue = document.getElementById('faceValue').value;
    var discount = parseInt(currencyToNumber(document.getElementById('disCount').innerText));
    realAmount = (parseFloat(faceValue))- (parseFloat(discount));
    document.getElementById('realAmount').innerText = formatCurrency(realAmount);
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
</script>
</html>