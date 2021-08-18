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
  <title>Cài đặt bảo mật EMAIL - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<div class="page page-email">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active">Cài đặt bảo mật</li>
    </ol>
    <h1 class="page-title">Cài đặt bảo mật EMAIL</h1>
  </div>
  <div class="page-content container-fluid">
    <div class="panel panel-bordered">
      <div class="panel-heading">
        <h3 class="panel-title">Đăng ký OTP EMAIL</h3>
      </div>
      <div class="panel-body py-10">
        <p>Với mỗi giao dịch, hệ thống sẽ gửi mã xác thực vào email đã đăng kí</p>
        <div class="row">
          <div class="col-sm-3 form-group row mb-5">
            <img src="/assets/images/placeholder100.png" class="br3 mb-10">
          </div>
          <div class="col-sm-6 form-group row mb-5">
            <span>OTP Email</span><br>
            <span>Email nhận OTP: nguyenvana@gmail.com</span>
          </div>
        </div>
        <div class="row mb-10">
          <div class="col-md-12 pl-0 has-feedback">
            <div class="input-group">
              <input class="form-control" placeholder="Nhập hạn mức" type="text">

                <i class="fa  fa-question-circle form-control-feedback" data-placement="top" data-toggle="tooltip"
                                               data-original-title="Bạn phải liên kết tài khoản ví điện tử với tài khoản ngân hàng để thực hiện giao dịch trên ví Zo-ta"></i>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 pl-0 text-right">
            <button type="submit" class="btn btn-primary">Đăng ký</button>
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