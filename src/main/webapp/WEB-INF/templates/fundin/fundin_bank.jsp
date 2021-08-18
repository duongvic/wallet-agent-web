<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_LINK_BANK" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_LOCAL_BANK" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_REQUEST_BANK_TRANSFER" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <title><spring:message code="fundin.title.page"/></title>
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
    <jsp:param name="nav" value="fi"/>
</jsp:include>
<!-- /menu bar -->

<c:set var="FUND_ORDER_SHOW_LINK_BANK" value="<%=FUND_ORDER_SHOW_LINK_BANK%>"/>
<c:set var="FUND_ORDER_SHOW_LOCAL_BANK" value="<%=FUND_ORDER_SHOW_LOCAL_BANK%>"/>
<c:set var="FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND"
       value="<%=FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND%>"/>
<c:set var="FUND_ORDER_SHOW_REQUEST_BANK_TRANSFER"
       value="<%=FUND_ORDER_SHOW_REQUEST_BANK_TRANSFER%>"/>

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item"><a href="/bank/manage"><spring:message
                    code="label.manage"/></a></li>
            <li class="breadcrumb-item active"><spring:message code="label.fundin"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="label.fundin"/></h1>
    </div>
    <div class="page-content container-fluid">

        <c:if test="${'true' eq FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND || 'true' eq FUND_ORDER_SHOW_REQUEST_BANK_TRANSFER}">
            <div class="panel">
                <div class="panel-heading"><h3 class="panel-title"><spring:message
                        code="fundin.send.request.money"/></h3></div>
                <div class="panel-body">
                    <div class="row">
                        <c:if test="${'true' eq FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND}">
                            <div class="col-md-3 text-center">
                                <a href="/fundin/cash-on-hand/management"><img
                                        src="/assets/images/icon/money_transfer.png"
                                        class="br3 mb-15" width="60">
                                    <p><spring:message code="fundin.cash.on.hand"/></p></a>
                            </div>
                        </c:if>
                        <c:if test="${'true' eq FUND_ORDER_SHOW_REQUEST_BANK_TRANSFER}">
                            <div class="col-md-3 text-center">
                                <a href="/fundin/bank-transfer/management"><img
                                        src="/assets/images/icon/bank_transfer.png"
                                        class="br3 mb-15" width="60">
                                    <p><spring:message code="fundin.list.bankTransfer"/></p></a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${'true' eq FUND_ORDER_SHOW_LINK_BANK}">
            <div class="panel">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="label.link.bank"/></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <c:choose>
                            <c:when test="${listBankDirect !=null}">
                                <c:forEach items="${listBankDirect}" var="itemListBankDirect"
                                           varStatus="link_bank_id">
                                    <%--<div class="mb-10 item-bank" name="itemCard">--%>
                                    <%--<span class="phoneCharge-choose">--%>
                                    <%--<label>--%>
                                    <%--<a href="/fundin/link-bank?_nameBank=${itemListBankDirect.bankCode}${price != null ? '&amount='.concat(price) : ''}${account != null ? '&_soTaiKhoan='.concat(account) : ''}">--%>
                                    <%--<img alt="${item.bankName}"--%>
                                    <%--src="/assets/images/bank/${itemListBankDirect.bankCode}.png">--%>
                                    <%--</a>--%>
                                    <%--</label>--%>
                                    <%--</span>--%>
                                    <%--<input type="hidden" name="bankAccountNumber" id="bankAccountNumber"--%>
                                    <%--value="${itemListBankDirect.bankAccountNumber}">--%>
                                    <%--</div>--%>
                                    <form method="post" action="/fundin/link-bank">
                                        <div class="mb-10 item-card" name="itemCard">
                                            <input type="hidden" name="bankAccountNumber"
                                                   id="bankAccountNumber"
                                                   value="${itemListBankDirect.bankAccountNumber}">
                                            <input type="hidden" name="_nameBank"
                                                   value="${itemListBankDirect.bankCode}">
                                            <input type="hidden" name="amount"
                                                   value="${price}">
                                            <input type="hidden" name="_soTaiKhoan"
                                                   value="${account}">
                                            <input type="hidden" name="${_csrf.parameterName}"
                                                   value="${_csrf.token}"/>
                                            <button class="hidden"
                                                    id="link-bankSubmit${link_bank_id.count}"></button>
                                            <span class="phoneCharge-choose">
                  <label for="link-bankSubmit${link_bank_id.count}">
                   <img alt="${item.bankName}"
                        src="/assets/images/bank/${itemListBankDirect.bankCode}.png">
                  </label>
                  </span>
                                        </div>
                                    </form>
                                </c:forEach>
                            </c:when>
                            <c:when test="${itemListBankDirect ==null}">
                                <div class="col-md-6 col-sm-12">
                                    <a href="/bank/link-bank-account"><img
                                            src="/assets/images/the6.png" style="width: 318px;
    height: 155px;"></a>
                                </div>
                            </c:when>

                        </c:choose>


                    </div>
                </div>
            </div>
        </c:if>

        <c:forEach var="enabledService" items="${enabledServices}">
            <c:if test="${enabledService != null && enabledService.serviceCode eq '020400'}">
                <c:if test="${'true' eq FUND_ORDER_SHOW_LOCAL_BANK}">
                    <div class="panel">
                        <div class="panel-heading">
                            <h3 class="panel-title"><spring:message code="label.bank"/></h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <c:forEach items="${listBank}" var="item" varStatus="itemId">
                                    <form method="post" action="/fundin/atm/info">
                                        <div class="mb-10 item-card">
                                            <input type="hidden" name="_nameBank"
                                                   value="${item.bankCode}">
                                            <input type="hidden" name="amount" value="${price}">
                                            <input type="hidden" name="_soTaiKhoan" value="${account}">
                                            <span class="phoneCharge-choose">
				<input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button class="hidden" type="submit" id="bankSubmit${itemId.count}"></button>
                  <label for="bankSubmit${itemId.count}">
                   <img alt="${item.bankName}" src="/assets/images/bank/${item.bankCode}.png">
                  </label>
                </span>
                                        </div>
                                    </form>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:if>
        </c:forEach>



        <div class="panel hidden">
            <div class="panel-heading"><h3 class="panel-title"><spring:message
                    code="fundin.card.credit.debit"/></h3></div>
            <div class="panel-body">
                <div class="row">
                    <form method="post" action="/fundin/visa/info">
                        <div class="mb-10 item-card">
                         <span class="phoneCharge-choose">
                          <label for="visa">
                           <img src="/assets/images/visa/Visa.png">
                          </label>
                         </span>
                        </div>
                        <input type="hidden" name="_nameBank" value="Visa">
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <button class="hidden" type="submit" id="visa"></button>
                    </form>
                    <form method="post" action="/fundin/visa/info">
                        <div class="mb-10 item-card">
                    <span class="phoneCharge-choose">
                    <label for="masterCard">
                    <img src="/assets/images/visa/Maestro.png" name="masterCard">
                    </label>
                    </span>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <input type="hidden" name="_nameBank" value="Maestro">
                        <button class="hidden" type="submit" id="masterCard"></button>
                    </form>
                </div>
            </div>
        </div>


    </div>
</div>

<div class="modal fade modal-fall" id="examplePositionCenter" aria-hidden="true"
     aria-labelledby="examplePositionCenter" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-simple modal-center">
        <div class="modal-content bg-0">
            <div class="modal-header">
                <button type="button" class="close close-bg-0" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true" class=""><i class="icon pe-close"></i></span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-6 col-md-6">
                        <div class="panel panel-bordered">
                            <div class="panel-heading text-center">
                                <h3 class="panel-title"><spring:message
                                        code="label.bank.account"/></h3></div>
                            <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
                            <div class="panel-body text-center pt-0">
                                <button type="button" class="btn btn-primary btn-sm"><spring:message
                                        code="common.btn.add"/><i
                                        class="icon wb-arrow-right ml-10"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <div class="panel panel-bordered">
                            <div class="panel-heading text-center">
                                <h3 class="panel-title"><spring:message
                                        code="label.bank.card"/></h3></div>
                            <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
                            <div class="panel-body text-center pt-0">
                                <button type="button" class="btn btn-primary btn-sm"><spring:message
                                        code="common.btn.add"/><i
                                        class="icon wb-arrow-right ml-10"></i></button>
                            </div>
                        </div>
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