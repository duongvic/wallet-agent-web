<%@ page
        import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Location" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.account.AccountVeirficationController.REDIRECT_ACCOUNT_VERIFY" %>
<%@ page import="vn.mog.ewallet.consumer.web.controller.account.AccountVeirficationController" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <title><spring:message code="account.info.page.title"/>-<spring:message
            code="common.company"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/development/css/epin.css"/>">
    <!-- /head libs  -->

    <style>
        select:required:invalid {
            color: gray;
        }

        option[value=""][disabled] {
            display: none;
        }
    </style>
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="infoAccountParentVeirfication"/>
</jsp:include>
<!-- /menu bar -->


<spring:message code="label.gender.male" var="genderMale"/>
<spring:message code="label.gender.female" var="genderFemale"/>
<spring:message code="label.gender.other" var="genderOther"/>
<spring:message code="account.info.gender" var="label_gender"/>
<spring:message code="label.city" var="label_city"/>
<spring:message code="label.commune" var="label_commune"/>
<spring:message code="account.info.current.district" var="label_district"/>
<spring:message code="account.select.province.city" var="label_province_city"/>
<spring:message code="label.county.district" var="label_countyDistrict"/>


<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="menu.account.parent.info"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="menu.account.parent.info"/></h1></div>
    <div class="page-content container-fluid">
        <div class="row">
            <div class="col-xxl-12 col-lg-12 col-md-12 col-sm-12">
                <div class="panel panel-bordered">
                    <div class="panel-body py-10">

                        <%--parent information--%>
                        <div class="form-group row">
                            <div class="col-xxl-12 col-lg-12 col-md-12 col-sm-12">
                                <form id="id-form-parent-info" action="">

                                    <div class="form-group row">
                                        <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12">
                                            <div class="form-group row mb-10">
                                                <spring:message code="account.info.full.name"
                                                                var="label_full_name"/>
                                                <label class=" col-sm-3 col-form-label pb-0">${label_full_name}<span
                                                        class=""></span></label>
                                                <div class="col-sm-9 ">
                                                    <input placeholder="${label_full_name}"
                                                           value="${objCus.parentName}"
                                                           class="form-control pb-0"/>
                                                </div>
                                            </div>

                                            <div class="form-group row mb-5">
                                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                                        code="content.phone.number"/><span
                                                        class=""></span></label>
                                                <div class="col-sm-9">
                                                    <input value="${objCus.parentMsisdn}"
                                                           class="form-control pb-0"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12">
                                            <div class="form-group row mb-10">
                                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                                        code="account.info.job.email"/><span
                                                        class=""></span></label>
                                                <div class="col-sm-9">
                                                    <input value="${objCus.prarentEmail}"
                                                           class="form-control pb-0"/>
                                                </div>
                                            </div>

                                            <div class="form-group row mb-10">
                                                <label
                                                        class=" col-sm-3 col-form-label pb-0">CIF<span
                                                        class=""></span></label>
                                                <div class="col-sm-9">
                                                    <input class="form-control pb-0"

                                                           value="${objCus.parentCif}"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                    <input type="hidden" name="urlImage" value="${urlImage}">
                                </form>
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

<style>
    .select2-container--default .select2-selection--single .select2-selection__rendered {
        padding-left: 0 !important;
    }
</style>

<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
      $('#id-form-parent-info').find('input, textarea, select').prop('disabled', true);
    });

  })(document, window, jQuery);
</script>
</html>