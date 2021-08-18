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
  <title><spring:message code="payment.security.sms.title"/>-<spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->Đổi hạn mức OTP SMS
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<div class="page page-email">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item">
        <a href="/dashboard/index">
          <spring:message code="dashboard.home"/>
        </a>
      </li>
      <li class="breadcrumb-item active"><spring:message code="payment.security.setting"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="payment.security.sms.page.title"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="panel panel-bordered">
      <div class="panel-heading">
        <h3 class="panel-title">Đăng ký OTP SMS</h3></div>
      <div class="panel-body py-10">
        <p><spring:message code="payment.security.content"/></p>
        <div class="row">
          <div class="col-sm-3 form-group row mb-5">
            <img src="/assets/images/placeholder100.png" class="br3 mb-20">
          </div>
          <div class="col-sm-6 form-group row mb-5">
            OTP SMS<br>
            Số điện thoại nhận OTP: 0944896996
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 pl-0">
            Để đăng ký dịch vụ, vui lòng soạn tin nhắn theo cú pháp:<<br>
            SMARTPAY OTP [hạn mức] gửi 8100
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