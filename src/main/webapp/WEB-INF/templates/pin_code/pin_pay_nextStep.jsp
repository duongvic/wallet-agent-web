<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="label.epin"/> - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="pinCode"/>
</jsp:include>
<!-- /menu bar -->


<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><a href="quanlythe.html"><spring:message code="label.epin"/></a></li>
    </ol>
    <h3 class="page-title"><spring:message code="label.epin"/></h3>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <form class="form-horizontal">
              <div class="form-group row mb-10">
                <div class="full-width">
                  <h4 class="panel-title pl-0"><spring:message code="label.real.amount"/></h4>
                  <div class="clr"></div>
                </div>
              </div>

              <div class="form-group row mb-10 ">
                <div class="myCheckBox">
                  <input type="checkbox" value="" id="chkPinPayWallet" name="chkPinPayWallet" checked
                         required/>
                  <label for="chkPinPayWallet" style="margin-left: 0.8em;"></label>
                </div>
                <div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">
                  <label class="pull-left"><spring:message code="label.payment.via.ewallet"/></label>
                </div>
              </div>
              <c:if test="${(codeErr != null)}">
              <div class="text-danger error-message form-group row mb-10 " style="margin-left: 2.5em;">
                <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}
                </small>
              </div>
              </c:if>
              <div class="form-group row mb-10 ">
                <div class="col-6 col-sm-3 col-md-3">
                  <label class="control-label" style="margin-left: 2.5em;"><spring:message code="account.bank.number"/>:</label>
                </div>
                <div class="col-6 col-sm-7 col-md-9">
                  <label class="" name="taiKhoan">${userLogin.phoneNumber}</label>
                </div>
              </div>

              <div class="form-group row mb-10 ">
                <div class="col-6 col-sm-3 col-md-3">
                  <label class="" style="margin-left: 2.5em;" for="soDuVi"><spring:message code="account.wallet.amount"/>:</label>
                </div>
                <div class="col-6 col-sm-7 col-md-9">
                  <label class="" id="soDuVi" name="soDuVi">${ewallet:formatNumber(soDuVi)} đ</label>
                </div>
              </div>

              <div class="form-group row mb-10 hidden">
                <div class="myCheckBox">
                  <input type="checkbox" value="" id="chkPinPayLinkedCard" name="chkPinPayLinkedCard"
                         />
                  <label for="chkPinPayLinkedCard" style="margin-left: 0.8em;"></label>
                </div>
                <div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">
                  <label class="pull-left">Thanh toán qua thẻ gắn kết </label>
                </div>
              </div>

              <div class="form-group row mb-10 hidden">
                <div class="myCheckBox">
                  <input type="checkbox" value="" id="chkPinPayDomesticCard" name="chkPinPayDomesticCard"
                         />
                  <label for="chkPinPayDomesticCard" style="margin-left: 0.8em;"></label>
                </div>
                <div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">
                  <label class="pull-left">Thanh toán qua thẻ nội địa </label>
                </div>
              </div>

              <div class="form-group row mb-10 hidden">
                <div class="myCheckBox">
                  <input type="checkbox" value="" id="chkPinPayInternationalCard" name="chkPinPayInternationalCard"
                         />
                  <label for="chkPinPayInternationalCard" style="margin-left: 0.8em;"></label>
                </div>
                <div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">
                  <label class="pull-left">Thanh toán qua thẻ quốc tế</label>
                </div>
              </div>

              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <a class="btn btn-primary btn-sm pull-right" href="/pin-code/order-info">
                    <spring:message code="common.btn.back"/>
                  </a>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import url="../include_component/frame_information_transaction_pincode.jsp"/>
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
  function validateSTK() {
    var x = document.getElementById("taiKhoan").value;
    var y = x.replace(/[-]+/g, "");
    var z = y.replace(/[A-Za-z0-9]{4}(?!$)/g, "\$&" + "-");
    document.getElementById("taiKhoan").value = z;
  }
</script>
</html>