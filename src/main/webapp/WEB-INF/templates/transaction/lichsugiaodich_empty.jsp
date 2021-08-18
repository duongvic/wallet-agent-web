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
  <title>Lịch sử giao dịch - <spring:message code="common.company"/></title>
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

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active">Lịch sử</li>
    </ol>
    <h1 class="page-title">Lịch sử giao dịch</h1></div>
  <div class="page-content text-center mt-40"><img src="/assets/images/placeholder100.png" class="br3 mb-20">
    <p>Bạn chưa thực hiện giao dịch nào
      <br>Thực hiện giao dịch đầu tiên tại đây </p>
    <div class="col-md-8 offset-md-2 mt-20">
      <div class="row">
        <div class="col-md-3 text-center">
          <a href="#"><img src="/assets/images/placeholder100.png" class="br3 mb-15" width="60">
            <p><spring:message code="label.fundin"/></p>
          </a>
        </div>
        <div class="col-md-3 text-center">
          <a href="#"><img src="/assets/images/placeholder100.png" class="br3 mb-15" width="60">
            <p><spring:message code="label.fundout"/></p>
          </a>
        </div>
        <div class="col-md-3 text-center">
          <a href="#"><img src="/assets/images/placeholder100.png" class="br3 mb-15" width="60">
            <p>Nạp điện thoại</p>
          </a>
        </div>
        <div class="col-md-3 text-center">
          <a href="#"><img src="/assets/images/placeholder100.png" class="br3 mb-15" width="60">
            <p><spring:message code="label.epin"/></p>
          </a>
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