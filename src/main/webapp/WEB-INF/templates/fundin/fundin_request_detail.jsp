
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
    <title><spring:message code="fundorder.request.detail.window.title"/></title>
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
    <jsp:param name="nav" value="themthe"/>
</jsp:include>
<!-- /menu bar -->

<c:if test="${'cash-on-hand' eq fund_in_method}">
    <spring:message code="fundin.cash.on.hand.request" var="fund_in_method_label"/>
</c:if>
<c:if test="${'bank-transfer' eq fund_in_method}">
    <spring:message code="fundin.list.bankTransfer.request" var="fund_in_method_label"/>
</c:if>

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="fundorder.management.request.detail.breadcrumb"/></li>
            <li class="breadcrumb-item active"><spring:message
                    code="fundorder.fundin.fundin.breadcrumb"/></li>
            <li class="breadcrumb-item">${fund_in_method_label}</li>
            <li class="breadcrumb-item active">
                <%--<a href="/fundin/cash-on-hand/management/detail/${request_id}">--%>
                <spring:message code="fundorder.cash.on.hand.request.detail.breadcrumb"/>
                <%--</a>--%>
            </li>
        </ol>
        <h1 class="page-title"><spring:message
                code="fundorder.request.detail.page.title"/>&nbsp;${request_id}</h1>
    </div>
    <div class="page-content container-fluid">
        <div class="panel mb-0 panel-bordered">
            <%--<form class="form-horizontal" method="post" enctype="multipart/form-data"--%>
                  <%--action="/fundin/${fund_in_method}/management/detail/${request_id}/edit">--%>
                <div class="panel-body">
                    <!-- Account -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.account.label"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0"><spring:message code="user.info.customer.type.id.${customer_type_id}"/></p>
                        </div>
                    </div>

                    <!-- Transaction chanel -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.transaction.chanel.label"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0"><spring:message
                                    code="fundorder.request.method.${order_channel}"/></p>
                            <%--<input type="hidden" name="order_channel" value="${order_channel}">--%>
                        </div>
                    </div>

                    <!-- Fund in amount -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.fundin.total.label"/></label>
                        <div class=" col-sm-9 ">
                            <c:choose>
                                <c:when test="${null == amount || '' eq amount}">
                                    <p class="form-control-plaintext pb-0"><spring:message
                                            code="label.free"/></p>
                                </c:when>
                                <c:otherwise>
                                    <p class="form-control-plaintext pb-0 currency-input">${amount}</p>
                                </c:otherwise>
                            </c:choose>
                            <%--<input type="hidden" name="amount" value="${amount}">--%>
                        </div>
                    </div>

                    <!-- Fee -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.fee.label"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0 currency-input">${fee}</p>
                            <%--<c:choose>--%>
                                <%--<c:when test="${null == fee || '' eq fee}">--%>
                                    <%--<p class="form-control-plaintext pb-0"><spring:message--%>
                                            <%--code="label.free"/></p>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<p class="form-control-plaintext pb-0 currency-input">${fee}</p>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>

                            <%--<input type="hidden" name="fee" value="${fee}">--%>
                        </div>
                    </div>

                    <!-- Total -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.total.label"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0 currency-input">${total}</p>
                            <%--<input type="hidden" name="total" value="${total}">--%>
                        </div>
                    </div>

                    <!-- Amount -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.amount.label"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0 currency-input">${current_balance}</p>
                            <%--<input type="hidden" name="current_balance" value="${current_balance}">--%>
                        </div>
                    </div>

                    <!-- Description -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.remark.label"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0">${remark}</p>
                            <%--<input type="hidden" name="remark" value="${remark}">--%>
                        </div>
                    </div>

                </div>

                <div class="panel-body">
                    <!-- Receipt -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.receipt.label"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0">
                                <c:forEach var="item" items="${attachments}" varStatus="item_id">
                                    <p><img alt="${item.name}"
                                    src="data:image/png;base64, <c:out value='${item.imageBase64}'/>"
                                    style="max-width: 90%;"></p> <br/>
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                </div>

                <div class="panel-body">
                    <div class="form-group row mb-5">
                        <a class="btn btn-primary btn-sm mr-2"
                           href="/fundin/${fund_in_method}/management"><spring:message
                                code="label.button.title.back"/></a>

                        <c:if test="${0 == stage && 0 == ref_txn_status}">
                            <a class="btn btn-primary btn-sm"
                               href="/fundin/${fund_in_method}/management/detail/${request_id}/edit"><spring:message
                                    code="label.button.title.edit"/></a>
                        </c:if>
                    </div>
                </div>
                <%--<input type="hidden" name="command_code" value="${command_code}"/>--%>
                <%--<input type="hidden" name="bank_code" value="${bank_code}"/>--%>
                <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <%--</form>--%>
    </div>
</div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

</html>