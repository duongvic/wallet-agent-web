<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<%@ page import="vn.mog.ewallet.consumer.web.controller.epo.beans.EpinPurchaseOrderFlowStage" %>
<%@ page import="vn.mog.ewallet.consumer.web.controller.epo.beans.EpinPurchaseOrderStatus" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.epo.BatchCardsController.BATCH_CARDS_CONTROLLER" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.DASHBOARD_CONTROLLER" %>
<%@ page import="static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.DASHBOARD_TYPE_API" %>
<%@ page import="static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.DASHBOARD_TYPE_EXPORT" %>
<%@ page import="vn.mog.ewallet.consumer.web.contract.UserLogin" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN" %>
<%@ page
        import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrder.StoreType" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.AbstractController.DASHBOARD_TYPE_API_N02" %>


<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <title><spring:message code="menu.batch.cards"/></title>
    <!-- head libs  -->
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="import_epin" value="true"/>
        <jsp:param name="import_nav_tabs" value="true"/>
    </jsp:include>
    <!-- /head libs  -->

    <%
        UserLogin userLogin = (UserLogin) request.getSession().getAttribute(SESSION_ACCOUNT_LOGIN);
        if (userLogin == null) {
            userLogin = new UserLogin();
        }
    %>
    <c:set var="userLogin" value="<%=userLogin%>" scope="application"/>
    <c:url var="epinPoCon" value="<%=BATCH_CARDS_CONTROLLER%>"/>
    <c:set var="typeAPI" value="<%=DASHBOARD_TYPE_API%>"/>
    <c:set var="typeExport" value="<%=DASHBOARD_TYPE_EXPORT%>"/>
    <c:set var="typeAPIN02" value="<%=DASHBOARD_TYPE_API_N02%>"/>
    <c:set var="epoStoreType" value=""/>
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<c:set var="menu_param" value=""/>
<c:set var="page_title" value=""/>
<c:set var="tab_title" value="service.exportcard.title.content"/>
<c:if test="${dashboardType eq typeExport}">
    <c:set var="menu_param" value="batchCardsMenu"/>
    <c:set var="page_title" value="batch.cards.export.po.label"/>
    <c:set var="tab_title" value="service.exportcard.file.title.content"/>
    <c:set var="epoStoreType" value="<%=StoreType.OFFLINE_STORE%>"/>
</c:if>
<c:if test="${dashboardType eq typeAPIN02}">
    <c:set var="menu_param" value="batchCardsMenuAPIN02"/>
    <c:set var="page_title" value="batch.cards.api.po.label"/>
    <c:set var="tab_title" value="service.exportcard.api.title.content"/>
    <c:set var="epoStoreType" value="<%=StoreType.ONLINE_STORE_N02%>"/>
</c:if>
<c:if test="${dashboardType eq typeAPI}">
    <c:set var="menu_param" value="batchCardsMenuAPI"/>
    <c:set var="page_title" value="batch.cards.api.po.label"/>
    <c:set var="tab_title" value="service.exportcard.api.title.content"/>
    <c:set var="epoStoreType" value="<%=StoreType.ONLINE_STORE%>"/>
</c:if>

<jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="${menu_param}"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="label.manage"/></li>
            <li class="breadcrumb-item"><spring:message
                    code="${page_title}"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="${page_title}"/></h1>
    </div>
    <div class="page-content container-fluid">

        <c:set var="BatchCardsUrl" value="<%=BATCH_CARDS_CONTROLLER%>"/>
        <c:set var="CardDashBoardUrl" value="<%=DASHBOARD_CONTROLLER%>"/>

        <div class="tabs">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a onclick="openTab('export_epin');" href="#">
                        <spring:message code="${tab_title}"/>
                    </a>
                </li>
                <c:if test="${userLogin.specialCustomerInfoOffline != null && dashboardType eq typeExport}">
                    <li>
                        <a onclick="openTab('${typeExport}');" href="#">
                            <spring:message code="service.exportcard.title.card.store.export"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${userLogin.specialCustomerInfoN02 != null && dashboardType eq typeAPIN02}">
                    <li>
                        <a onclick="openTab('${typeAPIN02}');" href="#">
                            <spring:message code="service.exportcard.title.card.store.api"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${userLogin.specialCustomerInfo != null && dashboardType eq typeAPI}">
                    <li>
                        <a onclick="openTab('${typeAPI}');" href="#">
                            <spring:message code="service.exportcard.title.card.store.api"/>
                        </a>
                    </li>
                </c:if>
            </ul>
            <div class="tab-content pl-none pr-none">
                <div id="tab1" class="tab-pane active">
                    <div class="panel mb-0 panel-bordered">
                        <div class="panel-body">
                            <form action="/batch-cards/list" method="post">

                                <div class="form-group mb-20 pos-relative">
                                    <div class="input-group">
                                        <span class="fa fa-search input-group-search"></span>
                                        <spring:message code="batch.cards.api.search.place.holder"
                                                        var="search_place_holder"/>
                                        <input type="text" class="form-control search-entry" name="quickSearch"
                                               id="quickSearch"
                                               value="${param.quickSearch}"
                                               placeholder="${search_place_holder}"
                                               style="border-radius: 16px; padding-left: 3rem;">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12 col-md-3 col-lg-3 time">
                                        <div id="reportrange" class="form-control search_entry">
                                            <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp;
                                            <input type="hidden" name="range" id="reservation" value=""/>&nbsp;
                                            <span></span> <i class="fa fa-caret-down pull-right"></i>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-6 col-lg-6">
                                        <div class="row">
                                            <div class="col-xs search_entry mb-10 mx-10">
                                                <select id="stageIds" name="stageIds"
                                                        class="form-control" multiple="multiple">
                                                    <option value="0" ${(param.stageIds !=null && param.stageIds eq 0) ? 'selected':''}>
                                                        <spring:message
                                                                code="label.init"/></option>
                                                    <option value="2" ${(param.stageIds !=null && param.stageIds eq 2) ? 'selected':''}>
                                                        <spring:message
                                                                code="label.support.confirm.business"/></option>
                                                    <option value="4" ${(param.stageIds !=null && param.stageIds eq 4) ? 'selected':''}>
                                                        <spring:message
                                                                code="label.allow.exp.tags"/></option>
                                                    <option value="6" ${(param.stageIds !=null && param.stageIds eq 6) ? 'selected':''}>
                                                        <spring:message
                                                                code="label.the.end"/></option>
                                                </select>
                                            </div>

                                            <div class="col-xs search_entry mb-10 mx-10">
                                                <select id="statusIds" name="statusIds"
                                                        class="form-control" multiple="multiple">

                                                    <option value="0" ${(param.statusIds !=null && param.statusIds eq 0) ? 'selected':''}>
                                                        <spring:message
                                                                code="label.init"/></option>
                                                    <option value="2" ${(param.statusIds !=null && param.statusIds eq 2) ? 'selected':''}>
                                                        <spring:message
                                                                code="label.success"/></option>
                                                    <option value="1" ${(param.statusIds !=null && param.statusIds eq 1) ? 'selected':''}><spring:message
                                                            code="label.fail"/>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-3 col-lg-3 text-right search_entry mb-10">
                                        <button type="button" id="submit-search" class="btn btn-warning btn-sm mb-10" onclick="drawTableList()">
                                            <i class="fa fa-search"></i>&nbsp;<spring:message
                                                code="transaction.api.button.search"/>
                                        </button>
                                        <%--<c:choose>--%>
                                        <%--<c:when test="${checkMerchantExportCard eq true}">--%>
                                        <button type="button" class="btn btn-primary btn-sm mb-xs mt-xs mb-10"
                                                onclick="window.location.replace('request-po')">
                                            <i class="fa fa-plus"></i>&nbsp;<spring:message
                                                code="service.exportcard.create.subnavigate.btn.creatempo"/>
                                        </button>
                                        <%--</c:when>--%>
                                        <%--<c:otherwise>--%>
                                        <%--<p style="color: indianred" class="mb-xs mt-sm"><spring:message--%>
                                        <%--code="service.exportcard.list.subnavigate.title.creatempo"--%>
                                        <%--arguments="${minBalance}"/></p>--%>
                                        <%--</c:otherwise>--%>
                                        <%--</c:choose>--%>
                                    </div>

                                </div>
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                            </form>
                        </div>


                        <div class="panel-body tb-tool">
                            <spring:message var="colNo" code="service.exportcard.list.table.column.no"/>
                            <spring:message var="colCode" code="service.exportcard.list.table.column.code"/>
                            <spring:message var="coltotalAmount"
                                            code="service.exportcard.list.table.column.totalAmount"/>
                            <spring:message var="colKeyholder"
                                            code="service.exportcard.list.table.column.keyholder"/>
                            <spring:message var="colFacevalue"
                                            code="service.exportcard.list.table.column.facevalue"/>
                            <spring:message var="colQuantity"
                                            code="service.exportcard.list.table.column.quantity"/>
                            <spring:message var="colCommission"
                                            code="service.exportcard.list.table.column.commission"/>
                            <spring:message var="colCapitalValue"
                                            code="service.exportcard.list.table.column.capitalValue"/>
                            <spring:message var="colNetamount"
                                            code="service.exportcard.list.table.column.netamount"/>
                            <spring:message var="colPrebalance"
                                            code="service.exportcard.list.table.column.prebalance"/>
                            <spring:message var="colPostbalance"
                                            code="service.exportcard.list.table.column.postbalance"/>
                            <spring:message var="colCreatedtime"
                                            code="service.exportcard.list.table.column.createdtime"/>
                            <spring:message var="colStage" code="service.exportcard.list.table.column.stage"/>
                            <spring:message var="colStatus" code="service.exportcard.list.table.column.status"/>
                            <spring:message var="colAction" code="service.exportcard.list.table.column.action"/>

                            <spring:message var="actionDetail"
                                            code="service.exportcard.list.table.column.action.detail"/>
                            <spring:message var="titleDownfile"
                                            code="service.exportcard.list.table.column.action.downfile"/>
                            <spring:message var="titleResendPass"
                                            code="service.exportcard.list.table.column.action.repass"/>
                            <spring:message var="actionExportNewEPO"
                                            code="menu.left.service.submenu.exportcard"/>
                            <spring:message var="titleEditPo"
                                            code="service.exportcard.list.table.column.action.edit"/>
                            <spring:message var="titleApprove"
                                            code="service.exportcard.list.table.column.action.approve"/>

                            <c:set var="epinStatusInit" value="<%=EpinPurchaseOrderStatus.INIT.code%>"/>

                            <c:set var="epinStageInit" value="<%=EpinPurchaseOrderFlowStage.INIT.code%>"/>
                            <c:set var="epinStageSaleRejected"
                                   value="<%=EpinPurchaseOrderFlowStage.SALESUPPORT_REJECTED.code%>"/>
                            <c:set var="epinStageSaleVerify"
                                   value="<%=EpinPurchaseOrderFlowStage.SALESUPPORT_READY_TO_VERIFY.code%>"/>
                            <c:set var="epinStageMerchantCancel"
                                   value="<%=EpinPurchaseOrderFlowStage.MERCHANT_CANCEL_ORDER.code%>"/>
                            <c:set var="epinStageExportAllowed"
                                   value="<%=EpinPurchaseOrderFlowStage.EPIN_EXPORT_ALLOWED.code%>"/>
                            <c:set var="epinStageFinished"
                                   value="<%=EpinPurchaseOrderFlowStage.FINISHED.code%>"/>

                            <spring:message var="titleStageInit"
                                            code="service.exportcard.list.tbl.col.stage.init"/>
                            <spring:message var="titleStageSaleRejected"
                                            code="service.exportcard.list.tbl.col.stage.sale.reject"/>
                            <spring:message var="titleStageSaleVerify"
                                            code="service.exportcard.list.tbl.col.stage.sale.verify"/>
                            <spring:message var="titleStageSaleVerifyAPI"
                                            code="service.exportcard.api.list.tbl.col.stage.sale.verify"/>
                            <spring:message var="titleStageMerchantCancel"
                                            code="service.exportcard.list.tbl.col.stage.merchant.cancel"/>
                            <spring:message var="titleStageExportAllowed"
                                            code="service.exportcard.list.tbl.col.stage.export.allowed"/>
                            <spring:message var="titleStageFinish"
                                            code="service.exportcard.list.tbl.col.stage.finished"/>
                            <table class="table table-hover table-bordered table-striped mb-none dataTable no-footer w-full"
                                   id="tblTransaction">
                                <thead>
                                <tr>
                                    <th>${colNo}</th>
                                    <th>${colCode}</th>
                                    <th>${coltotalAmount}</th>
                                    <th>${colCreatedtime}</th>
                                    <th>${colStage}</th>
                                    <th>${colStatus}</th>
                                    <th>${colAction}</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
    </div>
            </div>
        </div>

    </div>
</div>
<spring:message code="select.choose.all" var="choose_all"/>
<spring:message code="select.choose.forder.process" var="choose_process"/>
<spring:message code="select.status" var="choose_status"/>

<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>

<spring:message code="exportcard.api.stage.0" var="card_stage_0"/>
<spring:message code="exportcard.api.stage.1" var="card_stage_1"/>
<spring:message code="exportcard.api.stage.2" var="card_stage_2"/>
<spring:message code="exportcard.api.stage.3" var="card_stage_3"/>
<spring:message code="exportcard.api.stage.4" var="card_stage_4"/>
<spring:message code="exportcard.api.stage.6" var="card_stage_6"/>

<spring:message code="exportcard.api.status.0" var="card_status_0"/>
<spring:message code="exportcard.api.status.1" var="card_status_1"/>
<spring:message code="exportcard.api.status.2" var="card_status_2"/>

<spring:message code="popup.button.yes" var="button_yes"/>
<spring:message code="popup.button.no" var="button_no"/>
<spring:message code="popup.message.confirm.download.file" var="button_confirm_download_file"/>
<spring:message code="common.file.download.success" var="download_success"/>
<spring:message code="common.file.download.fail" var="download_fail"/>
<spring:message code="popup.message.confirm.reset.pass" var="confirm_reset_pass"/>
<spring:message code="popup.message.confirm.receive.pass" var="confirm_receive_pass"/>
<spring:message code="common.data.error" var="data_error"/>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
<c:import url="../include_page/js_daterangepicker.jsp"/>

</body>
<script type="text/javascript">
  Date.prototype.hmdmy = function () {
    var hh = this.getHours();
    var minute = this.getMinutes();
    var dd = this.getDate();
    var mm = this.getMonth() + 1;

    return (hh > 9 ? '' : '0') + hh + ":" + (minute > 9 ? '' : '0') + minute + " " + (dd > 9 ? ''
        : '0') + dd + "-" + (mm > 9 ? '' : '0') + mm + "-"
        + this.getFullYear();
  };

  $(document).ready(function () {
    $('#stageIds').multiselect({
      includeSelectAllOption: true,
      dropUp: false,
      selectAllValue: '',
      selectAllText: '${choose_all}',
      maxHeight: 400,
      nonSelectedText: '${choose_process}',
      inheritClass: true,
      enableCaseInsensitiveFiltering: true,
      enableFiltering: true,
      numberDisplayed: 1
    });

    $('#statusIds').multiselect({
      includeSelectAllOption: true,
      dropUp: false,
      selectAllValue: '',
      selectAllText: '${choose_all}',
      maxHeight: 400,
      nonSelectedText: '${choose_status}',
      inheritClass: true,
      enableCaseInsensitiveFiltering: true,
      enableFiltering: true,
      numberDisplayed: 1
    });

    $('.multiselect, .dropdown-toggle, .btn btn-default').hover(function () {
      $(this).removeAttr('title');
    });

    $('.search_entry').on('keydown', function (event) {
      if (event.keyCode === 13) {
        event.preventDefault();
        $("button[type=button]#submit-search").trigger('click');
      }
    });

    drawTableList();

    $('.resend-pass').click(function () {
      var id = $(this).attr('poMerchantId');
      if (id != null && id != '') {
        $.MessageBox({
          buttonDone: '<spring:message code="popup.button.yes"/>',
          buttonFail: '<spring:message code="popup.button.no"/>',
          message: '<spring:message code="popup.message.confirm.reset.pass"/>'
        }).done(function () {
          $.ajax({
            type: 'POST',
            url: 'card-number-input',
            data: {
              poMerchantId: id
            },
            success: function (data) {
              if (data.code == 0) {
                $.MessageBox(
                    {message: '<spring:message code="popup.message.confirm.receive.pass"/>'});
              } else {
                $.MessageBox({message: data.message});
              }
            }
          });
        });
        return false;
      } else {
        $.MessageBox({message: "<spring:message code="common.data.error"/>"});
        return false;
      }
    });

    $('button.confirmRequest').click(function () {
      var id = $('form[name=sumary] input[name=poMerchantId]').val();
      if (id != null && id != '') {
        $(this).button('loading');
        $.post('getOtp', {poMerchantId: id}, function (data) {
          if (data.code == 0) {
            location.href = "request-otp?id=" + id;
          } else {
            $.MessageBox({message: data.message});
            $(this).button('reset');
          }
        });
      }
      return false;
    });
  });

  function drawTableList() {
    var quickSearch = $('#quickSearch').val();
    var range = $('#reservation').val();
    var statusIds = $('#statusIds').val();
    var stageIds = $('#stageIds').val();
    var storeType = '${epoStoreType}';

    $('#tblTransaction').dataTable({
      responsive: true,
      destroy: true,
      "paging": true,
      "serverSide": true,
      "iDisplayStart": 0,
      "pageLength": 20,
      "bLengthChange": false,
      "searching": false,
      "bPaginate": true,
      "processing": true,
      "serverSide": true,
      columnDefs: [{
        sortable: false,
        "class": "index",
        targets: 0
      }],
      "language": {
        "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
        "sLengthMenu": "_MENU_ ${paging_records}",
        "paginate": {
          "previous": "${paging_previous}",
          "next": "${paging_next}"
        },
        "processing": "<img src='../../assets/images/loading_airline.gif'/>"
      },
      "ajax": {
        "url": "/ajax-controller/batch-cards/list",
        beforeSend: function (xhr) {
          if ("${_csrf.parameterName}" && "${_csrf.token}") {
            xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
          }
        },
        "type": "POST",
        "data": {
          "${_csrf.parameterName}": "${_csrf.token}",
          quickSearch: quickSearch,
          range: range,
          statusIds: statusIds,
          stageIds: stageIds,
          storeType: storeType
        },
        dataSrc: 'dataList'
      },
      "columns": [
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var index = meta.settings.oAjaxData.start + meta.row + 1;
            var htmlCode = ""
                + "                    <span id=\"row" + data.purchaseOrderId
                + "\" class=\"rowid\">\n"
                + "                        " + index + "\n"
                + "                    </span>";

            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = ""
                + "<a href=\"detail?poMerchantId=" + data.purchaseOrderId
                + "\" class=\"detail-link link-active\"\n"
                + "      poMerchantId=\"" + data.purchaseOrderId + "\">" + data.poCode + "</a>";

            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var total = data.totalValue - data.totalCommission + data.totalFee;
            var htmlCode = ""
                + "<div class=\"text-right\">" + formatCurrency(total) + "</div>";

            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = ""
                + (new Date(data.createdTime)).hmdmy();

            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = "<div class=\"text-center\">";
            var secondStepLabel = "${typeExport eq dashboardType ? titleStageSaleVerify : titleStageSaleVerifyAPI}";

            switch (data.stage) {
              case ${epinStageInit}:
                htmlCode += ""
                    + "                <a href=\"edit-po?id=" + data.purchaseOrderId + "\" title=\"${titleStageInit}\">"
                    + "                     <i class=\"fa fa-warning warning_status\"></i>"
                    + "                </a>\n"
                    + "                <a title=\"${titleStageSaleRejected}\" class=\"status_number\">1</a>\n"
                    + "                <a title=\"" + secondStepLabel + "\" class=\"status_number\">2</a>\n"
                  if (${typeExport eq dashboardType}) {
                    htmlCode += ""
                    + "                <a title=\"${titleStageMerchantCancel}\" class=\"status_number\">3</a>\n"
                    + "                <a title=\"${titleStageExportAllowed}\" class=\"status_number\">4</a>";
                  }
                break;
              case 1:
                htmlCode += ""
                    + "                <a title=\"${titleStageInit}\"><i class=\"fa fa-check check_status\"></i></a>\n"
                    + "                <a href=\"edit-po?id=" + data.purchaseOrderId + "\" title=\"${titleStageSaleRejected}\">"
                    + "                     <i class=\"fa fa-times reject_status\"></i>"
                    + "                </a>\n"
                    + "                <a title=\"" + secondStepLabel + "\" class=\"status_number\">2</a>\n"
                if (${typeExport eq dashboardType}) {
                  htmlCode += ""
                    + "                <a title=\"${titleStageMerchantCancel}\" class=\"status_number\">3</a>\n"
                    + "                <a title=\"${titleStageExportAllowed}\" class=\"status_number\">4</a>";
                }
                break;
              case 2:
                htmlCode += ""
                    + "                <a title=\"${titleStageInit}\"><i class=\"fa fa-check check_status\"></i></a>\n"
                    + "                <a title=\"${titleStageSaleRejected}\" class=\"status_number\">1</a>\n"
                    + "                <a title=\"" + secondStepLabel + "\" class=\"not-role\">"
                    + "                     <i class=\"fa fa-warning warning_status\"></i>"
                    + "                </a>\n"
                if (${typeExport eq dashboardType}) {
                  htmlCode += ""
                    + "                <a title=\"${titleStageMerchantCancel}\" class=\"status_number\">3</a>\n"
                    + "                <a title=\"${titleStageExportAllowed}\" class=\"status_number\">4</a>";
                }
                break;
              case 3:
                htmlCode += ""
                    + "                <a title=\"${titleStageInit}\"><i class=\"fa fa-check check_status\"></i></a>\n"
                    + "                <a title=\"${titleStageSaleRejected}\" class=\"status_number\">1</a>\n"
                    + "                <a title=\"" + secondStepLabel + "\"><i class=\"fa fa-check check_status\"></i></a>\n"
                if (${typeExport eq dashboardType}) {
                  htmlCode += ""
                    + "                <a title=\"${titleStageMerchantCancel}\"><i class=\"fa fa-times reject_status\"></i></a>\n"
                    + "                <a title=\"${titleStageExportAllowed}\" class=\"status_number\">4</a>";
                }
                break;
              case 4:
                htmlCode += ""
                    + "                <a title=\"${titleStageInit}\"><i class=\"fa fa-check check_status\"></i></a>\n"
                    + "                <a title=\"${titleStageSaleRejected}\" class=\"status_number\">1</a>\n"
                    + "                <a title=\"" + secondStepLabel + "\"><i class=\"fa fa-check check_status\"></i></a>\n"
                if (${typeExport eq dashboardType}) {
                  htmlCode += ""
                    + "                <a title=\"${titleStageMerchantCancel}\" class=\"status_number\">3</a>\n"
                    + "                <a title=\"${titleStageExportAllowed}\" href=\"get-otp?id=" + data.purchaseOrderId + "\">\n"
                    + "                    <i class=\"fa fa-warning warning_status\"></i>\n"
                    + "                </a>";
                }
                break;
              case 6:
                htmlCode += ""
                    + "                <a title=\"${titleStageInit}\"><i class=\"fa fa-check check_status\"></i></a>\n"
                    + "                <a title=\"${titleStageSaleRejected}\" class=\"status_number\">1</a>\n"
                    + "                <a title=\"" + secondStepLabel + "\"><i class=\"fa fa-check check_status\"></i></a>\n"
                if (${typeExport eq dashboardType}) {
                  htmlCode += ""
                    + "                <a title=\"${titleStageMerchantCancel}\" class=\"status_number\">3</a>\n"
                    + "                <a title=\"${titleStageExportAllowed}\"><i class=\"fa fa-check check_status\"></i></a>";
                }
                break;
              default:
                htmlCode += ""
                    + "                <a title=\"${titleStageInit}\" class=\"status_number\">0</a>\n"
                    + "                <a title=\"${titleStageSaleRejected}\" class=\"status_number\">1</a>\n"
                    + "                <a title=\"" + secondStepLabel + "\" class=\"status_number\">2</a>\n"
                if (${typeExport eq dashboardType}) {
                  htmlCode += ""
                    + "                <a title=\"${titleStageMerchantCancel}\" class=\"status_number\">3</a>\n"
                    + "                <a title=\"${titleStageExportAllowed}\" class=\"status_number\">4</a>";
                }
                break
            }

            htmlCode += "</div>";

            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = "";

            switch (data.status) {
              case 0:
                htmlCode = "${card_status_0}";
                break;
              case 1:
                htmlCode = "${card_status_1}";
                break;
              case 2:
                htmlCode = "${card_status_2}";
                break;
            }

            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = "<div class=\"action_icon text-right\">";
            if (data.stage == "${epinStageFinished}" && ${typeExport eq dashboardType}) {
              htmlCode += ""
                  + "            <a href=\"#\" title=\"${titleDownfile}\" class=\"link-export\""
                  + "            poMerchantId=\"" + data.purchaseOrderId + "\">"
                  + "<i class=\"fa fa-download\" aria-hidden=\"true\"></i></a>"
                  + "            <a href=\"#\" title=\"${titleResendPass}\" class=\"resend-pass\""
                  + "            poMerchantId=\"" + data.purchaseOrderId + "\">"
                  + "<i class=\"fa fa-refresh\" aria-hidden=\"true\"></i></a>";
            }

            if (data.stage == "${epinStageInit}" && data.status == "${epinStatusInit}") {
              htmlCode += ""
                  + "<a href=\"edit-po?id=" + data.purchaseOrderId + "\" title=\"${titleEditPo}\">"
                  + "<i class=\"fa fa-pencil\" aria-hidden=\"true\"></i>"
                  + "</a>";
            }

            if (data.stage == "${epinStageExportAllowed}"
                && data.status == "${epinStatusInit}"
                && ${typeExport eq dashboardType}) {
              htmlCode += ""
                  + "<a href=\"get-otp?id=" + data.purchaseOrderId
                  + "\" title=\"${titleApprove}\" class=\"otp-link link active\">"
                  + "<i class=\"fa fa-warning\" aria-hidden=\"true\"></i>"
                  + "</a>";
            }

            htmlCode += ""
                + "         <a href=\"/batch-cards/detail?poMerchantId=" + data.purchaseOrderId
                + "\" title=\"${actionDetail}\" class=\"detail-link link-active\""
                + "poMerchantId=\"" + data.purchaseOrderId
                + "\"><i class=\"fa fa-info-circle \"></i></a>"

                + "            <a href=\"#\" title=\"${actionExportNewEPO}\" class=\"export-new-link\""
                + "            poMerchantId=\"" + data.purchaseOrderId + "\">"
                + "<i class=\"fa fa-file-excel-o\" style=\"color: #15b11be3\" aria-hidden=\"true\"></i></a>";

            +"</div>";

            if (meta.row == 0) {
              htmlCode += "\n"
                  + "<script>"
                  + "    $('.export-new-link').click(function () {\n"
                  + "      var id = $(this).attr('poMerchantId');\n"
                  + "      $.MessageBox({\n"
                  + "        buttonDone: '${button_yes}',\n"
                  + "        buttonFail: '${button_no}',\n"
                  + "        message: '${button_confirm_download_file}'\n"
                  + "      }).done(function () {\n"
                  + "        var urlEpin = '/batch-cards/export-po-new?poMerchantId=' + id;\n"
                  + "        $.fileDownload(urlEpin)\n"
                  + "        .done(function () {\n"
                  + "          $.MessageBox({message: '${download_success}'});\n"
                  + "        })\n"
                  + "        .fail(function () {\n"
                  + "          $.MessageBox({message: '${download_fail}'});\n"
                  + "        });\n"
                  + "      });\n"
                  + "      return false;\n"
                  + "    });"
                  + "\n"
                  + "    $('.link-export').click(function () {\n"
                  + "      var id = $(this).attr('poMerchantId');\n"
                  + "      $.MessageBox({\n"
                  + "        buttonDone: '${button_yes}',\n"
                  + "        buttonFail: '${button_no}',\n"
                  + "        message: '${button_confirm_download_file}'\n"
                  + "      }).done(function () {\n"
                  + "        var urlEpin = '/batch-cards/export-epin?poMerchantId=' + id;\n"
                  + "        $.fileDownload(urlEpin)\n"
                  + "        .done(function () {\n"
                  + "          $.MessageBox({message: '${download_success}'});\n"
                  + "        })\n"
                  + "        .fail(function () {\n"
                  + "          $.MessageBox({message: '${download_fail}'});\n"
                  + "        });\n"
                  + "      });\n"
                  + "      return false;\n"
                  + "    });"
                  + "\n"
                  + "    $('.resend-pass').click(function () {\n"
                  + "      var id = $(this).attr('poMerchantId');\n"
                  + "      if (id != null && id != '') {\n"
                  + "        $.MessageBox({\n"
                  + "          buttonDone: '${button_yes}',\n"
                  + "          buttonFail: '${button_no}',\n"
                  + "          message: '${confirm_reset_pass}'\n"
                  + "        }).done(function () {\n"
                  + "          $.ajax({\n"
                  + "            type: 'GET',\n"
                  + "            url: '/batch-cards/resend-pass',\n"
                  + "            data: {\n"
                  + "              poMerchantId: id\n"
                  + "            },\n"
                  + "            success: function (data) {\n"
                  + "              if (data.code == 0) {\n"
                  + "                $.MessageBox(\n"
                  + "                    {message: '${confirm_receive_pass}'});\n"
                  + "              } else {\n"
                  + "                $.MessageBox({message: data.message});\n"
                  + "              }\n"
                  + "            }\n"
                  + "          });\n"
                  + "        });\n"
                  + "        return false;\n"
                  + "      } else {\n"
                  + "        $.MessageBox({message: \"${data_error}\"});\n"
                  + "        return false;\n"
                  + "      }\n"
                  + "    });"
                  + "<\/script>";
            }

            return htmlCode;
          }
        }
      ]
    });
  }

  function openTab(paramValue) {
    if ('export_epin' === paramValue) {
      return false;
    } else {
      window.location.href = '${CardDashBoardUrl}'.concat('/').concat('card-dashboard').concat('?dashboardType=').concat(paramValue);
    }
  }

</script>
</html>