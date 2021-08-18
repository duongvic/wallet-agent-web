<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>Chưa có thẻ - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="emptyCard"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="card.manage.label"/></li>
    </ol>
    <h1 class="page-title">Chưa có thẻ</h1></div>
  <div class="page-content text-center mt-40">
    <h3>Bạn chưa có thẻ/ tài khoản nào</h3>
    <p>Việc lưu thông tin thẻ hoặc liên kết tài khoản sẽ giúp bạn thực hiện các thao tác nạp
      <br> chuyển, rút tiền, thanh toán nhanh chóng hơn do không phải nhập lại thông tin ngân hàng. </p>
    <a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img src="/assets/images/the6.png" class="overlay-figure max300"> </a>
  </div>
</div>

<div class="modal fade modal-fall" id="examplePositionCenter" aria-hidden="true" aria-labelledby="examplePositionCenter" role="dialog" tabindex="-1">
  <div class="modal-dialog modal-simple modal-center">
    <div class="modal-content bg-0">
      <div class="modal-header">
        <button type="button" class="close close-bg-0" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true" class=""><i class="icon pe-close"></i></span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-sm-6 col-md-6">
            <div class="panel panel-bordered">
              <div class="panel-heading text-center">
                <h3 class="panel-title"><spring:message code="label.bank.account"/></h3></div>
              <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
              <div class="panel-body text-center pt-0">
                <a href="/bank/link-bank-account" class="btn btn-primary btn-sm"> <spring:message code="common.btn.add"/><i class="icon wb-arrow-right ml-10"></i></a>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-md-6">
            <div class="panel panel-bordered">
              <div class="panel-heading text-center">
                <h3 class="panel-title"><spring:message code="label.bank.card"/></h3></div>
              <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
              <div class="panel-body text-center pt-0"><a href="/bank/themTheNganHang" class="btn btn-primary btn-sm"> <spring:message code="common.btn.add"/><i class="icon wb-arrow-right ml-10"></i> </a></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

</html>