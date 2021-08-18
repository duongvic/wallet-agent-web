<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="label.epin"/> - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="batchCardsMenu"/>
</jsp:include>
<!-- /menu bar -->


<div class="page page-email">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message
          code="label.manage"/></li>
      <li class="breadcrumb-item"><spring:message
          code="menu.batch.cards"/></li>
    </ol>
    <h3 class="page-title"><spring:message code="menu.batch.cards"/></h3>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-12">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <form class="form-horizontal" method="" action="">

              <div class="row mb-20">
                <h4><spring:message code="label.export.card.fail"/> </h4>
              </div>
              <div class="row mb-20">
                <div class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                  <i class="icon pe-close-circle badge-danger br-100 fs40"></i>
                  <p class="mb-0">${codeErro != null && codeErro != '' ? codeErro : codeErro}</p>
                </div>
              </div>

              <div class="clr"></div>

              <div class="row">
                <div class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                  <a class="btn btn-primary btn-sm" href="/batch-cards/list" > <spring:message code="common.btn.back"/>
                  </a>
                </div>
              </div>

              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
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
<script>
</script>
</html>