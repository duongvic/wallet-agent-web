<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.transfer.TransferController.TRANSFER_CONFIRM_ERROR" %>
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
  <title><spring:message code="label.fundout"/> - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">

<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"></jsp:include>
<!-- /menu bar -->

<c:url var="moneyTransfConfirmErrorUri" value="<%=TRANSFER_CONFIRM_ERROR%>"/>


<div class="page page-email">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="label.fundout"/></li>
    </ol>
    <h3 class="page-title"><spring:message code="label.fundout"/></h3>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-12">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <%--<form class="form-horizontal" method="post" action="/fundout/no-link-bank/verify">--%>
              <div class="row mb-20">
                <div class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                  <i class="icon pe-close-circle badge-danger br-100 fs40"></i>
                  <p class="mb-0"><spring:message code="label.transaction.error"/></p>
                  <c:if test="${codeErr != null}">
                    <p class="mb-0">${codeErr}</p>
                  </c:if>
                </div>
              </div>
              <div class="clr"></div>
              <div class="row">
                <div class="col-md-4 col-sm-6 text-center offset-lg-4 offset-md-3 offset-sm-3">
                  <a href="/fundout/no-link-bank/verify" class="btn btn-primary btn-sm">
                    <spring:message code="common.btn.try.again"/>
                  </a>
                </div>
              </div>
              <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <%--</form>--%>
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