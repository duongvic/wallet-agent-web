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
<c:if test="${'FEcredit' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_FEcredit"/>
    </jsp:include>
    <spring:message code="label.financial.fe.credit" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Homecredit' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_Homecredit"/>
    </jsp:include>
    <spring:message code="label.financial.home.credit" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Acs' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_Acs"/>
    </jsp:include>
    <spring:message code="label.financial.acs" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Ocb' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_OcsBank"/>
    </jsp:include>
    <spring:message code="label.financial.ocb" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Prudential' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_Prudential"/>
    </jsp:include>
    <spring:message code="label.financial.prudential" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Shinhan' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_Shinhan"/>
    </jsp:include>
    <spring:message code="label.financial.Shinhan" var="label_financial_services_method"/>
</c:if>

<c:if test="${'MCredit' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_MCredit"/>
    </jsp:include>
    <spring:message code="label.financial.mcredit" var="label_financial_services_method"/>
</c:if>

<c:if test="${'MiraeAsset' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_MiraeAsset"/>
    </jsp:include>
    <spring:message code="label.financial.mirae.asset" var="label_financial_services_method"/>
</c:if>

<c:if test="${'AtmOnline' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_AtmOnline"/>
    </jsp:include>
    <spring:message code="label.financial.atm.online" var="label_financial_services_method"/>
</c:if>

<c:if test="${'DrDong' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_DrDong"/>
    </jsp:include>
    <spring:message code="label.financial.dr.dong" var="label_financial_services_method"/>
</c:if>

<c:if test="${'PTI' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_PTI"/>
    </jsp:include>
    <spring:message code="label.financial.pti" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Maritime' eq financial_services_method}">
    <jsp:include page="../include_page/menu_bar.jsp">
        <jsp:param name="nav" value="dv_Maritime"/>
    </jsp:include>
    <spring:message code="label.financial.maritime.bank" var="label_financial_services_method"/>
</c:if>
<!-- /menu bar -->

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="label.manage"/></li>
            <li class="breadcrumb-item"><spring:message code="label.financial.services"/></li>
        </ol>
        <h1 class="page-title">${label_financial_services_method}</h1>
    </div>
    <div class="page-content container-fluid">
        <div class="panel mb-0 panel-bordered">
            <form class="form-horizontal" method="post"
                  action="/financial-services/${financial_services_method}/management/pay">
                <div class="panel-body">
                    <%--<div class="form-group row mb-10">--%>
                    <%--<div class="full-width">--%>
                    <%--<h3 class="panel-title pl-0">SOURCE OF FUND</h3>--%>
                    <%--<div class="clr"></div>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<!-- Balance -->--%>
                    <%--<div class="form-group row mb-5">--%>
                    <%--<label class=" col-sm-3 col-form-label pb-0">TRUE MONEY</label>--%>
                    <%--<div class=" col-sm-9 ">--%>
                    <%--<p class="form-control-plaintext pb-0 currency-input">${ewallet:formatNumber(userLogin.balance)}</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <!-- Dich vụ -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="common.title.service"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0"><spring:message
                                    code="label.financial.services.finance.bill"/></p>
                        </div>
                    </div>

                    <!-- Nhà cung cấp -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.provider"/>: </label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0">${label_financial_services_method}</p>
                        </div>
                    </div>

                    <!-- Mã hóa đơn -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.invoice"/>: </label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0">${invoice_reference}</p>
                        </div>
                    </div>

                    <!-- Customer -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.customer"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0">${full_name}</p>
                        </div>
                    </div>


                    <!-- Phone -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.phone.number"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0">${customer_phone}</p>
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
                            </div>
                        </div>
                    </c:if>

                    <!-- payment amount need -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.amount.need.to.pay"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0 currency-input">${amount_to_pay}</p>
                        </div>
                    </div>
                </div>

                <div class="panel-body">
                    <!-- payment amount -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.amount"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0 currency-input">${obj_invoice_order.amount}</p>
                        </div>
                    </div>
                    <!-- fees -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.fees.transaction"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0 currency-input">${obj_invoice_order.fee}</p>
                        </div>
                    </div>
                    <%--tổng tiền giao dịch--%>
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.amount.total.transaction"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0 currency-input">${(obj_invoice_order.amount) + (obj_invoice_order.fee)}</p>
                        </div>
                    </div>

                    <!-- commission -->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.agent.commission"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0 currency-input">${obj_invoice_order.commission}</p>
                        </div>
                    </div>

                </div>

                <div class="panel-body">
                    <!-- Đại lý trả-->
                    <div class="form-group row mb-5">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.bill.payment.agents.pay"/></label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0">${ewallet:formatNumber(agents_pay)}</p>
                        </div>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${'PAYMENT_PIN' eq paymentSecurityType}">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12 col-md-8 mb-3">
                                    <label>
                                        <c:choose>
                                            <c:when test="${AGENT_TYPE eq userLoginType}">
                                                <spring:message code="label.payment.password"/>
                                            </c:when>
                                            <c:otherwise>
                                                <spring:message code="label.pin"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </label>
                                </div>
                                <c:if test="${(codeErr != null)}">
                                    <div class="text-danger error-message">
                                        <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}
                                        </small>
                                    </div>
                                </c:if>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="password" id="financial_paymentSecurityCode"
                                               name="paymentSecurityCode"
                                               class="form-control" required
                                               placeholder="<spring:message code="label.enter.code"/>">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
                <div class="panel-body">
                    <div class="form-group row mb-5">
                        <c:if test="${agents_pay > 0}">
                            <button type="submit" class="btn btn-primary btn-sm pull-right">
                                <spring:message
                                        code="common.btn.payment"/></button>
                        </c:if>
                        <c:if test="${agents_pay <= 0}">
                            <button type="submit" class="btn btn-primary btn-sm pull-right" disabled>
                                <spring:message
                                        code="common.btn.payment"/></button>
                        </c:if>
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