<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>Đổi mật khẩu - <spring:message code="common.company"/></title>
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
            <h3 class="panel-title">Đổi mật khẩu</h3></div>
          <div class="panel-body">
            <form class="form-horizontal">
              <div class="form-group row">
                <label class="col-sm-4 form-control-label">Nhập mật khẩu mới: </label>
                <div class="col-sm-8">
                  <input type="text" class="form-control"/>
                  <span class="text-help mb-0"><spring:message code="label.password"/> từ 8-16 ký tự, bao gồm chữ hoa, chữ thường và số</span>
                </div>
              </div>
              <div class="form-group row mb-0">
                <label class="col-sm-4 form-control-label">Nhập lại mật khẩu mới: </label>
                <div class="col-sm-8"><input type="text" class="form-control"/></div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
          </div>
          <div class="panel-footer py-10 text-right">
            <div class="row">
              <div class="col-sm-9 offset-sm-3">
                <a href="/customer/account-info" class="btn btn-default btn-sm btn-outline"><spring:message code="common.btn.back"/></a>
                <a href="/customer/account-info" class="btn btn-primary btn-sm ml-10">Cập nhật </a></div>
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