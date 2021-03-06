<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>
    <c:if test="${'cash-on-hand' eq fund_in_method}">
      <spring:message code="fundin.cash.on.hand.request" var="fund_in_method_label"/>
    </c:if>
    <c:if test="${'bank-transfer' eq fund_in_method}">
      <spring:message code="fundin.list.bankTransfer.request" var="fund_in_method_label"/>
    </c:if>
  </title>
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

            <%--<form class="form-horizontal" method="get" action="/fundin/${fund_in_method}/management">--%>

              <div class="row mb-20">
                <h4><spring:message code="label.real.amount"/></h4>
              </div>
              <div class="row mb-20">
                <div
                    class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                  <i class="icon pe-check badge-success br-100 fs40"></i>
                  <p class="mb-0"><spring:message code="fundorder.request.fundin.success"/></p> </br>
                  <p class="mb-0"><spring:message code="fundorder.table.header.label.syntax"/>&nbsp;${syntax}</p> </br>
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
                    href="/fundin/${fund_in_method}/management"><spring:message code="button.back"/>
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
        <!-- Giao d???ch g???n nh???t -->
        <c:import url="../include_component/frame_fundout_last_transaction.jsp" />
        <!-- /Giao d???ch g???n nh???t  -->
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