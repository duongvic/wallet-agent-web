<%@ page import="vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController" %>
<%@ page import="static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.*" %>
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
    <title><spring:message code="topup.page.title"/> - <spring:message
            code="common.company"/></title>
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
    <jsp:param name="nav" value="topUpMenu"/>
</jsp:include>
<!-- /menu bar -->


<div class="page page-email">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message code="topup.page.title"/></li>
        </ol>
        <h3 class="page-title"><spring:message code="topup.page.title"/></h3>
    </div>
    <div class="page-content container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-bordered">
                    <div class="panel-body py-10">

                        <form class="form-horizontal" method="get"
                              action="/topup/top-up-next-step-verify">

                            <div class="row mb-20">
                                <h4><spring:message code="label.real.amount"/></h4>
                            </div>
                            <div class="row mb-20">
                                <div class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                                    <spring:message code="label.transaction.error"
                                                    var="transaction_error"/>
                                    <c:if test="${error ne 4501}">
                                        <i class="icon pe-close-circle badge-danger br-100 fs40"></i>
                                        <p class="mb-0">${codeErr != null && codeErr ne '' ? codeErr : transaction_error}</p>
                                    </c:if>
                                    <c:if test="${error eq 4501}">
                                        <i class="icon pe-attention badge-warning br-100 fs40"></i>
                                        <p class="mb-0"><spring:message
                                                code="label.transaction.error.4501"/></p>
                                    </c:if>
                                    <br>
                                </div>
                            </div>

                            <div class="clr"></div>


                            <c:if test="${error ne 4501}">
                                <div class="row">
                                    <div class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                                        <button type="submit" class="btn btn-primary btn-sm">
                                            <spring:message code="common.btn.try.again"/>
                                        </button>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${error eq 4501}">
                                <div class="row">
                                    <div class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                                        <button type="button" class="btn btn-primary btn-sm" id="btnOther">
                                            <spring:message code="common.btn.other.transactions"/>
                                        </button>
                                    </div>
                                </div>
                            </c:if>
                             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
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

<script>
  jQuery('#btnOther').on("click", function ()  {
    window.location.href='<%=DASHBOARD_LIST%>';
  })
</script>
</html>