<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="menu.fundout"/></title>
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
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/fundin"><spring:message code="label.fundin"/></a></li>
      <li class="breadcrumb-item"><spring:message code="fundin.cash.on.hand.request"/></li>
    </ol>
    <h3 class="page-title"><spring:message code="fundin.cash.on.hand.request"/></h3>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <%--<form class="form-horizontal" method="get" action="/fundout/${fund_out_method}/management">--%>

              <div class="row mb-20">
                <h4><spring:message code="label.real.amount"/></h4>
              </div>
              <div class="row mb-20">
                <div
                    class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                  <i class="icon pe-check badge-success br-100 fs40"></i>
                  <p class="mb-0"><spring:message code="fundorder.request.fundout.success"/></p> </br>
                  <br>
                </div>
                <div class="col-md-12 col-sm-12 text-center">
                  <p><spring:message code="fundorder.request.success.info"/></p>
                </div>
              </div>

              <div class="clr"></div>

              <div class="row justify-content-center">
                <div class="form-inline">
                  <div class="form-group">
                    <a class="btn btn-primary btn-sm"
                    href="/fundout/${fund_out_method}/management"><spring:message code="common.btn.back.home"/>
                    </a>
                  </div>
                </div>
              </div>

              <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <%--</form>--%>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import url="../include_component/frame_fundout_last_transaction.jsp" />
        <!-- /Giao dịch gần nhất  -->
      </div>
    </div>
  </div>
</div>
<c:import url="../dialog_modal/pin_code/dialog_sendEmail.jsp"/>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>
<script>
</script>
</html>