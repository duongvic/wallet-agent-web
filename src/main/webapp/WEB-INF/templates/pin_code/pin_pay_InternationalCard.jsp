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

            <form class="form-horizontal" method="post"
                  action="/pin-code/pinPayInternationalCardNextStep">
              <div class="form-group row mb-10">
                <div class="full-width">
                  <h4 class="panel-title pl-0"><spring:message code="label.real.amount"/></h4>
                  <div class="clr"></div>
                </div>
              </div>

              <div class="form-group row mb-10">
                <div class="myCheckBox">
                  <input type="checkbox" value="" id="chkPinPayWallet" name="chkPinPayWallet"
                  />
                  <label for="chkPinPayWallet" style="margin-left: 0.8em;"></label>
                </div>
                <div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">
                  <label class="pull-left"><spring:message code="label.payment.via.ewallet"/> </label>
                </div>
              </div>

              <div class="form-group row mb-10">
                <div class="myCheckBox">
                  <input type="checkbox" value="chkPinPayLinkedCard" id="chkPinPayLinkedCard"
                         name="chkPinPayLinkedCard" onclick="openPageLinkedCard()"/>
                  <label for="chkPinPayLinkedCard" style="margin-left: 0.8em;"></label>
                </div>
                <div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">
                  <label class="pull-left">Thanh toán qua thẻ gắn kết </label>
                </div>
              </div>

              <div class="form-group row mb-10">
                <div class="myCheckBox">
                  <input type="checkbox" value="chkPinPayLinkedCard" id="chkPinPayDomesticCard"
                         name="chkPinPayDomesticCard">
                  <label for="chkPinPayDomesticCard" style="margin-left: 0.8em;"></label>
                </div>
                <div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">
                  <label class="pull-left">Thanh toán qua thẻ nội địa </label>
                </div>
              </div>

              <div class="row mb-10">
                <div class="form-group row col">
                  <div class="myCheckBox">
                    <input type="checkbox" value="" id="chkPinPayInternationalCard"
                           name="chkPinPayInternationalCard" checked/>
                    <label for="chkPinPayInternationalCard" style="margin-left: 0.8em;"></label>
                  </div>
                  <div class="col col-6 col-sm-7 mb-3" style="margin-left: 1em;">
                    <label class="pull-left">Thanh toán qua thẻ quốc tế</label>
                  </div>
                </div>
              </div>

              <%--thẻ ngân hàng--%>
              <div class="row col">
                <div class="mb-10 item-bank" id="Visa">
                <span class="phoneCharge-choose">
                  <label>
                    <img alt="Visa" src="/assets/images/visa/Visa.png">
                  </label>
                </span>
                </div>
                <div class="mb-10 item-bank" id="PayPal">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="PayPal" src="/assets/images/visa/Paypal.png">
                  </label>
                </span>
                </div>
                <div class="mb-10 item-bank" id="MasterCard">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="MasterCard" src="/assets/images/visa/MasterCard.png">
                  </label>
                </span>
                </div>
                <div class="mb-10 item-bank" id="ebay">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="Ebay" src="/assets/images/visa/eBay.png">
                  </label>
                </span>
                </div>
                <div class="mb-10 item-bank" id="amazon">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="Amazon" src="/assets/images/visa/amazon.png">
                  </label>
                </span>
                </div>
                <div class="mb-10 item-bank" id="Maestro">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="Maestro" src="/assets/images/visa/Maestro.png">
                  </label>
                </span>
                </div>
              </div>
              <%--end--%>
              <div class="form-group row mb-10">
                <div class="col col-sm-12 mb-3">
                  <label class="pull-left name-bank">Bạn đang chọn ngân hàng <span
                      id="nameBank"></span>
                    <span>. Phí giao dịch là 0 đ</span></label>
                </div>
              </div>

              <div class="form-group row mb-10">
                <div class="col col-sm-6 mb-3 name-bank">
                  <input type="text" class="form-control" id="phone" name="phone"
                         placeholder="Số điện thoại" required>
                </div>
                <div class="col col-sm-6 mb-3 name-bank">
                  <input type="text" class="form-control" id="email" name="email"
                         placeholder="Email">
                </div>
              </div>

              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit" class="btn btn-primary btn-sm pull-right"> <spring:message code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i>
                  </button>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import url="../include_component/frame_information_transaction.jsp"/>
        <!-- /Giao dịch gần nhất  -->
      </div>
    </div>
  </div>
</div>

<!-- dialog -->
<c:import url="../dialog_modal/pin_code/dialog_addCard.jsp"/>
<!-- /dialog -->

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

  //  get alt img
  $(document).ready(function () {
    $(".item-bank label").click(function (e) {
      e.preventDefault();
      var alt = $(this).find("img").attr("alt");
      document.getElementById("nameBank").innerHTML = alt;
      $(".name-bank").css({"display": "block"});
    });
  });
  //end
</script>
</html>