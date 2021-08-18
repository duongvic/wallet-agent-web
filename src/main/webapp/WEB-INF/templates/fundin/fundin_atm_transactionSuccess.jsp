<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="label.fundin.atm"/> - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="pinThongTinDH"/>
</jsp:include>
<!-- /menu bar -->


<div class="page page-email">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="label.fundin.atm"/></li>
    </ol>
    <h3 class="page-title"><spring:message code="label.fundin.atm"/></h3>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-12">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <%--<form class="form-horizontal" method="get" action="/dashboard/index">--%>

              <div class="row mb-20">
                <h4><spring:message code="label.real.amount"/></h4>
              </div>
              <div class="row mb-20">
                <div
                    class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                  <i class="icon pe-check badge-success br-100 fs40"></i>
                  <p class="mb-0"><spring:message code="label.transaction.success"/></p>
                  <br>
                </div>
              </div>

              <div class="clr"></div>

              <div class="row justify-content-center">
                <div class="form-inline">
                  <div class="form-group">
                    <a href="/dashboard/index" class="btn btn-primary btn-sm"><spring:message code="common.btn.back.home"/>
                    </a>
                  </div>
                </div>
              </div>

              <%--<input type="hidden" name="_nameBank" value="${card_type}"/>--%>
              <%--<input type="hidden" name="amount" value="${amount}"/>--%>
              <%--<input type="hidden" name="_soTaiKhoan" value="${phoneNumber}"/>--%>
              <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <%--</form>--%>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<c:import url="../dialog_modal/pin_code/dialog_sendEmail.jsp"/>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

</html>