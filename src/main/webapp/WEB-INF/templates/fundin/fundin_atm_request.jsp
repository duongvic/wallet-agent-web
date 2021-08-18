<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <jsp:param name="nav" value="fiTopup"/>
</jsp:include>
<!-- /menu bar -->

<div class="page page-email">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item"><a href="/bank/manage"><spring:message
                    code="card.manage.label"/></a></li>
            <li class="breadcrumb-item active"><spring:message code="label.fundin.atm"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="label.fundin.atm"/></h1>
    </div>
    <div class="page-content container-fluid">
        <div class="row">
            <div class="col">
                <div class="panel panel-bordered">
                    <div class="panel-body py-10">
                        <form class="form-horizontal" method="post" action="/fundin/atm/confirm">
                            <div class="form-group row mb-10">
                                <div class="full-width">
                                    <img alt="${bankCode}"
                                         src="/assets/images/bank/${_nameBank}.png" align="left"
                                         width="100"
                                         style="margin-right:10px">
                                    <div class="text-left">
                                        <label>${bankDisplayName}</label>
                                        <br>
                                        <small>---</small>

                                        <input type="hidden" id="bankName" name="bankName"
                                               value="${bankName}">
                                        <input type="hidden" id="bankDisplayName"
                                               name="bankDisplayName"
                                               value="${bankDisplayName}">
                                    </div>
                                    <div class="clr"></div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-4 col-md-3">
                                    <label class="form-control-label"><spring:message
                                            code="account.bank.card.holder"/>:</label>
                                </div>
                                <div class="col-sm-8 col-md-9">
                                    <input type="text" disabled class="form-control ctk" id="chuThe"
                                           name="fullName"
                                           value="<sec:authentication property="principal.fullName" />"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-4 col-md-3">
                                    <label class="form-control-label" for="soThe"><spring:message code="account.bank.card.number"/> :</label>
                                </div>
                                <div class="col-sm-8 col-md-9">
                                    <input type="text" class="form-control
card-number-input" id="soThe"
                                           name="accountNumber" value="${accountNumber}" required/>
                                </div>
                            </div>
                            <div id="cardIssueDateForm" class="form-group row mb-10">
                                <div class="col-sm-4 col-md-3">
                                    <label class="form-control-label"><spring:message code="account.bank.card.effective"/> </label>
                                </div>
                                <div class="col-sm-8 col-md-9">
                                    <div class="row col-sm-12 col-md-12">
                                        <input type="text" id="cardIssueDate1"
                                               required class="form-control text-center"
                                               style="max-width: 50px;">
                                        <span style="margin-left: 6px; margin-right: 6px; font-size: 20px">⁄</span>
                                        <input type="text" class="form-control text-center"
                                               id="cardIssueDate2" required
                                               style="max-width: 50px;">
                                        <input type="hidden" id="hidden_cardIssueDate"
                                               name="cardIssueDate"
                                               value="${cardIssueDate}">
                                    </div>
                                </div>
                            </div>
                            <div class="row mb-10">
                                <div class="col text-right inline-block">
                                    <a href="${fundOutControllerURI}" class="btn btn-default mr-10"><spring:message
                                            code="common.btn.back"/></a>
                                    <button type="submit" class="btn btn-primary btn-sm mr-10"
                                            onclick="getCardIssueDate()"><spring:message
                                            code="label.fundin"/>
                                    </button>

                                </div>
                            </div>
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

</html>