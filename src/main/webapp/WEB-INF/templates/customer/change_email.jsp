<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>Đổi email - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="infoCus"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active">Thông tin người dùng</li>
    </ol>
    <h1 class="page-title">Thông tin người dùng</h1></div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-xxl-9 col-lg-9 col-md-8 col-sm-12">
        <div class="panel panel-bordered">
          <div class="panel-heading">
            <h3 class="panel-title">Đổi Email</h3></div>
          <div class="panel-body">
            <div class="px-10">
              <p>Với email đã xác thực, quý khách phải thực hiện xác thực lại email mới. Trong trường hợp không khác thực email, hệ thống sẽ đăng ký dịch vụ bảo mật thanh toán OTP SMS cho quý
                khách</p>
            </div>
            <form class="form-horizontal">
              <div class="form-group row mb-10">
                <label class="col-sm-4 form-control-label">Email hiện tại: </label>
                <div class="col-sm-8">
                  <p class="form-control-plaintext pb-0">nguyenvana@gmail.com</p>
                </div>
              </div>
              <div class="form-group row">
                <label class="col-sm-4 form-control-label">Email mới: </label>
                <div class="col-sm-8">
                  <input type="text" class="form-control"/></div>
              </div>

              <div class="form-group row mb-0">
                <label class="col-sm-4 form-control-label">Nhập mã OTP: </label>
                <div class="col-sm-8">
                  <div class="form-group mb-0">
                    <div class="input-group">
                      <input type="text" class="form-control" placeholder="Mã OTP">
                      <span class="input-group-btn">
                        <button type="button" class="btn btn-default btn-outline"><i class="fa fa-rotate-left"></i> <spring:message code="common.btn.resend"/></button>
                      </span>
                    </div>
                  </div>
                  <span class="text-help mb-0">Hệ thống đã gửi mã OTP vào email <a href="#">nguyenvana@gmail.com</a></span>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
          </div>
          <div class="panel-footer py-10 text-right">
            <div class="row">
              <div class="col-sm-9 offset-sm-3">
                <a href="/customer/account-info" class="btn btn-default btn-sm btn-outline"><spring:message code="common.btn.back"/></a>
                <a href="/customer/xacThucTaiKhoan" class="btn btn-primary btn-sm ml-10">Cập nhật </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xxl-3 col-lg-3 col-md-4 col-sm-12">

        <!-- information customer -->
        <c:import url="frame_custumer.jsp"/>
        <!-- /information customer -->

        <div class="row">

          <c:import url="../include_component/frame_car_account.jsp"/>

          <c:import url="../include_component/frame_config_policy.jsp"/>

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