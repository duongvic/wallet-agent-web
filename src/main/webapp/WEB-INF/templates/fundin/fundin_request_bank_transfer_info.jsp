<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.fundin.FundInAtmController.FUNDIN_ATM_CONTROLLER" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:url var="fundInControllerURI" value="<%=FUNDIN_ATM_CONTROLLER%>"></c:url>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
    <title><spring:message code="label.fundin.link.bank"/> - <spring:message code="common.company"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/development/css/custom_dropify.css"/>" media="none" onload="if(media!='all')media='all'">
    <!-- /head libs  -->
    <style>

        .select2-container--default .select2-selection--single .select2-selection__rendered {
            padding-left: 0 !important;
        }

        img {
            border: 0;
            max-width: 100%;
        }
        img.img-flag {
            margin: 0!important;
            display: inherit!important;
            height: 2rem;
            width: 4rem;
        }
    </style>
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<spring:message code="fundin.list.bankTransfer.request" var="fund_in_method_label"/>
<spring:message code="fundin.order.selectBank" var="select_bank"/>

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item"><a href="/fundin"><spring:message code="label.fundin"/></a>
            </li>
            <li class="breadcrumb-item">${fund_in_method_label}</li>
        </ol>
        <h1 class="page-title">${fund_in_method_label}</h1>
    </div>
    <div class="page-content container-fluid">
        <div class="mt-30">
            <div class="row">
                <div class="col-md-7">
                    <div class="panel panel-bordered">
                        <div class="panel-body py-10">
                            <form class="form-horizontal" action="/fundin/${fund_in_method}/create">

                                <div class="form-group row mb-10 pos-relative">
                                    <div class="col-md-1 col-lg-1"></div>
                                    <div class="col-md-3 col-lg-3">
                                        <label>${select_bank}:</label>
                                    </div>
                                    <div class="col-md-7 col-lg-7">
                                        <%--<span class="select-group__lable"><spring:message code="topup.input.placeholder.select.price"/></span>--%>
                                        <select data-plugin="select2" class="form-control js-example-templating" id="bank_select" required>
                                            <option value="">${select_bank}</option>
                                            <c:forEach items="${list_bank}" var="bankItem">
                                                <option value="${bankItem.bankAccountNumber},${bankItem.bankAccountName},${bankItem.bankBranch},${bankItem.bankCode}">${bankItem.bankName}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" id="bank_code" name="bank_code">
                                    </div>
                                </div>
                                <div class="form-group row mb-0">
                                    <div class="col-md-1 col-lg-1"></div>
                                    <div class="col-md-3 col-lg-3">
                                        <label class=""><span
                                                for="feeAmount"><spring:message code="request.BankTransfer.accountNo"/></span></label>
                                    </div>
                                    <div class="col-md-7 col-lg-7">
                                        <label class="px-0"><span
                                                id="account_number">${account_number}</span></label>
                                    </div>
                                </div>
                                <div class="form-group row mb-0">
                                    <div class="col-md-1 col-lg-1"></div>
                                    <div class="col-md-3 col-lg-3">
                                        <label class="form-control-label px-0">
                                            <spring:message code="request.BankTransfer.accountName"/>
                                        </label>
                                    </div>
                                    <div class="col-md-7 col-lg-7">
                                        <label id="bank_account_name"></label>
                                    </div>
                                </div>

                                <div class="form-group row mb-0">
                                    <div class="col-md-1 col-lg-1"></div>
                                    <div class="col-md-3 col-lg-3">
                                        <label class="form-control-label px-0">
                                            <spring:message code="request.BankTransfer.bankBranch"/>
                                        </label>
                                    </div>
                                    <div class="col-md-7 col-lg-7">
                                        <label id="bank_branch"></label>
                                    </div>
                                </div>

                                <%--<div class="form-group row mb-0">--%>
                                    <%--<div class="col-md-1 col-lg-1"></div>--%>
                                    <%--<div class="col-md-3 col-lg-3">--%>
                                        <%--<label class="form-control-label px-0">--%>
                                            <%--<spring:message code="transaction.api.detail.content"/>--%>
                                        <%--</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-md-7 col-lg-7">--%>
                                        <%--<label>TMN_TPGW_<spring:message code="request.transfer.phoneNo"/>_<spring:message code="account.bank.customer.code"/></label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <%--<div class="form-group row mb-0">--%>
                                    <%--<div class="col-md-1 col-lg-1"></div>--%>
                                    <%--<div class="col-md-3 col-lg-3">--%>
                                        <%--<label class="form-control-label px-0"></label>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-md-7 col-lg-7">--%>
                                        <%--<label class="text-primary">(<spring:message code="label.example"/>: TMN_TPGW_0973666888_abc123)</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <div class="form-group row mb-0">
                                    <div class="col-md-1 col-lg-1"></div>
                                    <div class="col-md-3 col-lg-10">
                                        <label class="form-control-label px-0">*<spring:message code="fundorder.request.bank.transfer.content.label"/></label>
                                    </div>
                                </div>

                                <div class="form-group row mt-5 mb-10">
                                    <div class="col-md-1 col-lg-1"></div>
                                    <div class="col-md-10 col-lg-10">
                                        <div class="col text-right inline-block">
                                            <a href="/fundin/${fund_in_method}/management"
                                               class="btn btn-default mr-10"><spring:message
                                                    code="common.btn.back"/></a>
                                            <button type="submit"
                                                    class="btn btn-primary btn-sm mr-10">
                                                <spring:message code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <!-- Giao d???ch g???n nh???t -->
                    <c:import
                            url="../include_component/frame_fundout_last_transaction.jsp"/>
                    <!-- /Giao d???ch g???n nh???t  -->
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="../dialog_modal/fundout_noLinkBank/dialog_saveBank.jsp"/>

<!-- footer -->
<c:import url="../include_page/footer_js.jsp"/>
<c:import url="../include_page/dropify.jsp"/>
<!-- /footer -->



<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();

      function formatState (state) {
        if (!state.id) {
          return state.text;
        }
        var baseUrl = "/assets/images/bank";
        var $state = $(
            '<span><img src="' + baseUrl + '/' + state.element.value.split(',')[3] + '.png" class="img-flag" /> ' + state.text + '</span>'
        );
        return $state;
      };

      $(".js-example-templating").select2({
        templateResult: formatState
      });

    });

  })(document, window, jQuery);

  jQuery('#bank_select').on("change", function () {
    var value = jQuery(this).val();
    if (value != null && value !== undefined && value !== '') {
      jQuery('#account_number').html(value.split(',')[0]);
      jQuery('#bank_account_name').html(value.split(',')[1]);
      jQuery('#bank_branch').html(value.split(',')[2]);
      jQuery('#bank_code').val(value.split(',')[3]);
    }
  });
</script>
</body>

</html>