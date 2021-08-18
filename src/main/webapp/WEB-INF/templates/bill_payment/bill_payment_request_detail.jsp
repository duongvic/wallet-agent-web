<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <spring:message code="label.bill.payment.${bill_payment_method}"
                    var="label_bill_payment_method"/>
    <title>
        ${label_bill_payment_method}
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
    <jsp:param name="nav" value="${bill_payment_method}"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="label.convenient.service"/></li>
            <li class="breadcrumb-item">${label_bill_payment_method}</li>
        </ol>
        <h1 class="page-title">${label_bill_payment_method}</h1>
    </div>
    <div class="page-content container-fluid">
        <div class="panel mb-0 panel-bordered">
            <form class="form-horizontal" method="post"
                  action="/bill-payment/${bill_payment_method}/management/confirm">
                <div class="panel-body">
                    <div class="form-group row mb-10">
                        <div class="full-width">
                            <h3 class="panel-title pl-0"><spring:message
                                    code="label.bill.payment.customer.info"/></h3>
                            <div class="clr"></div>
                        </div>
                    </div>

                    <!-- Full Name -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.full.name"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0">${full_name}</p>
                        </div>
                    </div>

                    <!--Phone  -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.phone.number"/></label>
                        <div class=" col-sm-9">
                            <input type="text" id="customerPhone"
                                   name="customerPhone" maxlength="10"
                                   onkeypress="return isNumberKey(event)"
                                   class="form-control">
                        </div>
                    </div>

                    <!-- Hạn thanh toán -->
                    <c:if test="${due_date ne null}">
                        <div class="form-group row mb-5">
                            <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                    code="label.bill.payment.term"/></label>
                            <div class=" col-sm-9 ">
                                <p class="form-control-plaintext pb-0"><fmt:formatDate
                                        value="${due_date}" pattern="dd/MM/yyyy"/></p>
                                    <%--<input type="hidden" name="order_channel" value="${order_channel}">--%>
                            </div>
                        </div>
                    </c:if>

                    <!-- Address -->
                    <c:if test="${address ne null}">
                        <div class="form-group row mb-5">
                            <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                    code="label.bill.payment.address"/></label>
                            <div class=" col-sm-9 ">
                                <p class="form-control-plaintext pb-0">${address}</p>
                            </div>
                        </div>
                    </c:if>

                    <!-- payment amount need -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.amount.need.to.pay"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0 currency-input">${amount}</p>

                        </div>
                    </div>
                </div>


                <div class="panel-body">
                    <div class="form-group row mb-5">
                        <button type="submit" class="btn btn-primary btn-sm pull-right">
                            <spring:message
                                    code="common.btn.confirm"/></button>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

</html>