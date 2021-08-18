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
  <spring:message code="label.points.transfer"
                  var="label_viettel_pay"/>
  <title>
    ${label_viettel_pay}
  </title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">

<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification -->

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <jsp:param name="nav" value="menuPointTransfer"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message
          code="label.convenient.service"/></li>
      <li class="breadcrumb-item">${label_viettel_pay}</li>
    </ol>
    <h1 class="page-title">${label_viettel_pay}</h1>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-12">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <div class="row mb-20">
              <h4><spring:message code="label.real.amount"/></h4>
            </div>
            <div class="row mb-20">
              <div
                  class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                <i class="icon pe-check badge-success br-100 fs40"></i>
                <p class="mb-0">Bạn vừa thanh toán thành công.</p> </br>
                <br>
              </div>
              <div class="col-md-12 col-sm-12 text-center">
                <p>Transaction ID: ${transactionId}</p>
              </div>
            </div>

            <div class="clr"></div>

            <%--<div class="row justify-content-center">--%>
              <%--<div class="form-inline">--%>
                <%--<div class="form-group">--%>
                  <%--<button type="button" class="btn btn-primary btn-sm" onclick="printElement()">--%>
                    <%--In thường--%>
                  <%--</button>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                  <%--<a class="btn btn-primary btn-sm"--%>
                     <%--href="/dashboard/index"><spring:message code="common.btn.other.transactions"/>--%>
                  <%--</a>--%>
                <%--</div>--%>
              <%--</div>--%>
            <%--</div>--%>

            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <%--</form>--%>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>

<div class="printer">
  <%--bill_template--%>
  <jsp:include page="../include_page/bill_template.jsp"/>
  <%--bill_template--%>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>
<script>
</script>
</html>