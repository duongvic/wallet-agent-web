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

            <form class="form-horizontal" method="post" action="/pin-code/pinPayDomesticCardNextStep">
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

              <div class="row mb-10">
                <div class="form-group row col">
                  <div class="myCheckBox">
                    <input type="checkbox" value="" id="chkPinPayDomesticCard"
                           name="chkPinPayDomesticCard" checked/>
                    <label for="chkPinPayDomesticCard" style="margin-left: 0.8em;"></label>
                  </div>
                  <div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">
                    <label class="pull-left">Thanh toán qua thẻ nội địa </label>
                  </div>
                </div>
                <div class="form-group row col pos-relative">
                  <div class="input-group">
                    <span class="fa fa-search input-group-search"></span>
                    <input type="text" class="form-control" name="search" id="search"
                           placeholder="Tìm kiếm" style="
    border-radius: 16px;
    padding-left: 3rem;
">
                  </div>
                </div>
              </div>
              <%--thẻ ngân hàng--%>
              <div class="row col">
                <div class="mb-10 item-bank" id="BIDV">
                <span class="phoneCharge-choose">
                  <label>
                    <img alt="BIDV" src="/assets/images/bank/BIDV.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="TPBank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="TPBank" src="/assets/images/bank/TPbank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="ABBank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="ABBank" src="/assets/images/bank/ABBank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="Agribank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="Agribank" src="/assets/images/bank/Agribank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="BaoViet">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="BaoViet" src="/assets/images/bank/BaoViet.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="DongABank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="DongABank" src="/assets/images/bank/DongABank.png">
                  </label>
                </span>
                </div>
              </div>

              <div class="row col">
                <div class="mb-10 item-bank" id="MB">
                <span class="phoneCharge-choose">
                  <label>
                    <img alt="MB" src="/assets/images/bank/MB.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="PGBank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="PGBank" src="/assets/images/bank/PGBank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="Maritimebank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="Maritimebank" src="/assets/images/bank/Maritimebank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="Oceanbank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="Oceanbank" src="/assets/images/bank/Oceanbank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="LienViet">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="LienViet" src="/assets/images/bank/LienViet.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="SacomBank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="SacomBank" src="/assets/images/bank/Sacombank.png">
                  </label>
                </span>
                </div>
              </div>

              <div class="row col">
                <div class="mb-10 item-bank" id="SeaBank">
                <span class="phoneCharge-choose">
                  <label>
                    <img alt="SeaBank" src="/assets/images/bank/SeaBank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="Techcombank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="Techcombank" src="/assets/images/bank/Techcombank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="Vietbank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="Vietbank" src="/assets/images/bank/Vietbank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="Vietcombank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="Vietcombank" src="/assets/images/bank/Vietcombank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="ViettinBank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="ViettinBank" src="/assets/images/bank/Vietinbank.png">
                  </label>
                </span>
                </div>

                <div class="mb-10 item-bank" id="VPBank">
                <span class="phoneCharge-choose">
                  <label>
                   <img alt="VPBank" src="/assets/images/bank/VPBank.png">
                  </label>
                </span>
                </div>
              </div>

              <%--end--%>
              <div class="form-group row mb-10">
                <div class="col col-sm-12 mb-3">
                  <label class="pull-left name-bank">Bạn đang chọn ngân hàng<span id="nameBank"></span>
                    <span>. Phí giao dịch là 1.000đ</span></label>
                </div>
              </div>

              <div class="form-group row mb-10">
                <div class="col col-sm-6 mb-3 name-bank">
                  <input type="text" class="form-control" id="phone" name="phone" placeholder="Số điện thoại" required>
                </div>
                <div class="col col-sm-6 mb-3 name-bank">
                  <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                </div>
              </div>

              <div class="row mb-10">
                <div class="form-group row col">
                  <div class="myCheckBox">
                    <input type="checkbox" value="" id="chkPinPayInternationalCard"
                           name="chkPinPayInternationalCard"/>
                    <label for="chkPinPayInternationalCard" style="margin-left: 0.8em;"></label>
                  </div>
                  <div class="col col-6 col-sm-7 mb-3" style="margin-left: 1em;">
                    <label class="pull-left">Thanh toán qua thẻ quốc tế</label>
                  </div>
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