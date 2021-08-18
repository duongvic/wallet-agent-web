<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <title><spring:message code="transaction.api.title.page"/>- <spring:message
            code="common.company"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/development/css/epin.css"/>">
    <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="historyTrans"/>
</jsp:include>
<!-- /menu bar -->

<jsp:include page="../include_component/service_code_constants.jsp"/>

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="transaction.api.bread.crumb"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="transaction.api.page.title"/></h1>
    </div>
    <div class="page-content container-fluid">
        <form action="/trans-log/transaction-history" method="get">

            <div class="panel mb-0 panel-bordered">
                <div class="panel-body">
                    <div class="form-group mb-20 pos-relative">
                        <div class="input-group">
                            <span class="fa fa-search input-group-search"></span>
                            <spring:message code="transaction.api.search.place.holder"
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
                                <div class="col-sm-12 col-md-3 col-lg-4 search_entry mb-10">
                                    <select id="serviceTypeId" name="serviceTypeId"
                                            class="form-control" multiple="multiple">
                                        <option value="FUND_IN"><spring:message
                                                code="label.service.name.FUND_IN"/></option>
                                        <option value="FUND_OUT"><spring:message
                                                code="label.service.name.FUND_OUT"/></option>
                                        <option value="P2P_TRANSFER"><spring:message
                                                code="label.service.name.P2P_TRANSFER"/></option>
                                        <option value="EPIN"><spring:message
                                                code="label.service.name.EPIN"/></option>
                                        <option value="PHONE_TOPUP"><spring:message
                                                code="label.service.name.PHONE_TOPUP"/></option>
                                        <%--<option value="EXPORT_EPIN"><spring:message--%>
                                        <%--code="label.service.name.EXPORT_EPIN"/></option>--%>
                                        <option value="BILL_PAYMENT"><spring:message
                                                code="label.service.name.BILL_PAYMENT"/></option>
                                        <option value="CASH_BACK"><spring:message
                                                code="label.service.name.CASH_BACK"/></option>
                                        <option value="WALLET_CASH_IN"><spring:message
                                            code="label.service.name.WALLET_CASH_IN"/></option>
                                        <option value="WALLET_CASH_OUT"><spring:message
                                            code="label.service.name.WALLET_CASH_OUT"/></option>
                                        <option value="TXN_REVERSAL"><spring:message
                                                code="label.service.name.TXN_REVERSAL"/></option>

                                    </select>
                                </div>

                                <div class="col-sm-12 col-md-4 col-lg-5 search_entry mb-10">
                                    <div id="dd" class="wrapper-dropdown-5" tabindex="1">
                                        <span id="status"><spring:message
                                                code="transaction.api.transaction.status.init"/></span>
                                        <ul class="dropdown">
                                            <li onClick="onSelect('10')"><a href="#">
                                                <span id="successStatus"><spring:message
                                                        code="transaction.api.transaction.status.success"/></span>
                                                <p class="btn btn-xs btn-info pull-right">
                                                    <spring:message
                                                            code="transaction.api.transaction.status.view.card.code"/>
                                                </p>
                                            </a>
                                            </li>
                                            <li onClick="onSelect('3')"><a href="#">
                                                <span id="lostStatus"><spring:message
                                                        code="transaction.api.transaction.status.fail"/></span>
                                                <p class="btn btn-xs btn-warning pull-right"
                                                   style="width: 92.2px;">
                                                    <spring:message
                                                            code="transaction.api.transaction.status.rerun"/>
                                                </p>
                                            </a></li>
                                            <li class="hidden"><a href="#"><p id="progessStatus">
                                                <spring:message
                                                        code="transaction.api.transaction.status.processing"/></p>
                                            </a></li>
                                            <li class="hidden"><a href="#">
                                                <button class="btn btn-xs btn-warning">
                                                    <spring:message
                                                            code="transaction.api.transaction.status.rerun"/>
                                                </button>
                                                <p id="returnStatus"><spring:message
                                                        code="transaction.api.transaction.status.cashback"/></p>
                                            </a></li>
                                            <li onClick="onSelect('')"><a href="#"><p
                                                    id="allStatus"><spring:message
                                                    code="transaction.api.transaction.status.all"/></p>
                                            </a></li>
                                        </ul>
                                        <input type="hidden" id="statusValue" name="status"
                                               value=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-3 col-lg-3 text-right search_entry mb-10">
                            <button type="button" onclick="drawTableTransactionList()"
                                    id="submit-search"
                                    class="btn btn-warning btn-sm mb-xs mt-xs mb-10">
                                <i class="fa fa-search"></i>&nbsp;<spring:message
                                    code="transaction.api.button.search"/>
                            </button>
                            <button type="button"
                                    class="export-log btn btn-primary btn-sm mb-xs mt-xs mb-10">
                                <i class="fa fa-file-excel-o"></i>&nbsp;Export log
                            </button>
                        </div>
                    </div>
                </div>

                <div class="pl-20 pr-20">
                    <spring:message code="transaction.api.navigate.transaction"/>:&nbsp;
                    <span id="totalTransaction"
                          class="primary_color text-semibold">${ewallet:formatNumber(total)}</span>&nbsp;|&nbsp;
                    <spring:message code="transaction.log.real.amount"/>:&nbsp;<span
                        id="totalRequestAmount"
                        class="primary_color text-semibold vnd-bill">${ewallet:formatNumber(totalRequestAmount)}</span>&nbsp;|&nbsp;
                    <spring:message code="transaction.log.total.commission"/>:&nbsp;<span
                        id="totalCommissionTxt"
                        class="primary_color text-semibold vnd-bill">${ewallet:formatNumber(totalCommission)}</span>
                </div>

                <spring:message code="transaction.api.action.success" var="success_title"/>
                <spring:message code="transaction.api.action.fail" var="fail_title"/>
                <spring:message code="transaction.api.action.reversed" var="reversed_title"/>
                <spring:message code="transaction.api.action.pending" var="pending_title"/>

                <div class="panel-body tb-tool">
                    <table
                            class="table table-hover table-bordered table-striped mb-none dataTable no-footer w-full"
                            id="tblTransaction">
                        <thead>
                        <tr>
                            <th><spring:message code="transaction.api.table.date"/></th>
                            <th><spring:message code="transaction.api.table.order.id"/></th>
                            <th><spring:message code="transaction.api.table.service"/></th>
                            <th><spring:message code="transaction.api.table.terminal"/></th>
                            <th><spring:message code="transaction.api.table.product"/></th>
                            <%--<th class=""><spring:message code="transaction.api.table.actor"/></th>--%>
                            <th><spring:message code="transaction.api.table.content"/></th>
                            <th><spring:message code="transaction.api.table.realamount"/></th>
                            <th><spring:message code="transaction.api.table.status"/></th>
                            <th><spring:message code="transaction.api.table.action"/></th>
                            <th><spring:message code="transaction.api.table.amount"/></th>
                            <th><spring:message code="transaction.api.table.commission"/></th>
                            <th><spring:message code="transaction.api.table.pay.fee"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>


    </div>
</div>
<spring:message code="select.choose.all" var="choose_all"/>
<spring:message code="select.choose.service" var="choose_service"/>
<spring:message code="select.choose.product" var="choose_product"/>

<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
<c:import url="../include_page/js_daterangepicker.jsp"/>

</body>
<spring:message code="common.file.export.success" var="export_success"/>
<spring:message code="common.file.export.fail" var="export_fail"/>
<spring:message code="popup.button.yes" var="button_yes"/>
<spring:message code="popup.button.no" var="button_no"/>
<spring:message code="popup.message.confirm.export.file" var="button_confirm_export_file"/>
<spring:message code="label.choose.date" var="choose_date"/>

<spring:message code="transaction.api.detail.time" var="time"/>
<spring:message code="transaction.api.detail.tran-id" var="tranId"/>
<spring:message code="transaction.api.detail.service" var="service"/>
<spring:message code="transaction.api.detail.product" var="product"/>
<spring:message code="transaction.api.detail.actor" var="actor"/>
<spring:message code="transaction.api.detail.content" var="content"/>
<spring:message code="transaction.api.detail.card.amount" var="amount"/>
<spring:message code="transaction.api.detail.commission" var="commision"/>
<spring:message code="transaction.api.detail.fee" var="fee"/>
<spring:message code="transaction.api.detail.free" var="free"/>
<spring:message code="transaction.api.detail.total.amount" var="realAmount"/>
<spring:message code="transaction.api.detail.method" var="method"/>
<spring:message code="transaction.api.detail.status" var="status"/>
<spring:message code="transaction.api.transaction.status.view.card.code" var="viewCardCode"/>
<spring:message code="transaction.api.transaction.status.view.bill" var="viewBill"/>
<spring:message code="transaction.api.transaction.status.view.detail" var="viewBatchCardDetail"/>
<spring:message code="transaction.api.transaction.status.printer.detail" var="action_printer"/>
<spring:message code="transaction.api.transaction.status.rerun" var="rerun"/>

<%--Service name--%>
<spring:message code="label.service.name.FUND_IN" var="FUND_IN_NAME"/>
<spring:message code="label.service.name.FUND_OUT" var="FUND_OUT_NAME"/>
<spring:message code="label.service.name.PHONE_TOPUP" var="PHONE_TOPUP_NAME"/>
<spring:message code="label.service.name.EPIN" var="EPIN_NAME"/>
<spring:message code="label.service.name.EXPORT_EPIN" var="EXPORT_EPIN_NAME"/>
<spring:message code="label.service.name.BILL_PAYMENT" var="BILL_PAYMENT_NAME"/>
<spring:message code="label.service.name.WALLET_TRANSFER" var="WALLET_TRANSFER_NAME"/>
<spring:message code="label.service.name.P2P_TRANSFER" var="P2P_TRANSFER_NAME"/>
<spring:message code="label.service.name.CASH_BACK" var="CASH_BACK_NAME"/>
<spring:message code="label.service.name.WALLET_CASH_IN" var="WALLET_CASH_IN_NAME"/>
<spring:message code="label.service.name.WALLET_CASH_OUT" var="WALLET_CASH_OUT_NAME"/>
<spring:message code="label.service.name.TXN_REVERSAL" var="TXN_REVERSAL_NAME"/>

<%--Service product content--%>
<spring:message code="label.service.product.content.${electricPaymentCode}"
                var="BILL_ELECTRIC_PROD_CONTENT"/>
<spring:message code="label.service.product.content.${waterPaymentCodeTrungAn}"
                var="BILL_WATER_TRUNG_AN_PROD_CONTENT"/>
<spring:message code="label.service.product.content.${waterPaymentCodeNhaBe}"
                var="BILL_WATER_NHA_BE_PROD_CONTENT"/>
<spring:message code="label.service.product.content.${FECreditFinancialCode}"
                var="BILL_FE_CREDIT_PROD_CONTENT"/>
<spring:message code="label.service.product.content.${HomeCreditFinancialCode}"
                var="BILL_HOME_CREDIT_PROD_CONTENT"/>
<spring:message code="label.service.product.content.${PrudentialFinancialCode}"
                var="BILL_PRUDENTIAL_PROD_CONTENT"/>
<spring:message code="label.service.product.content.${ShinhanCode}"
                var="BILL_SHINHAN_PROD_CONTENT"/>
<spring:message code="label.service.product.content.${AcsFinancialCode}"
                var="BILL_ACS_PROD_CONTENT"/>
<spring:message code="label.service.product.content.${OcbFinancialCode}"
                var="BILL_OCB_PROD_CONTENT"/>

<spring:message code="label.service.product.content.${MCreditFinancialCode}"
                var="BILL_MCREDIT_PROD_CONTENT"/>
<spring:message code="label.service.product.content.${MiraeAssetFinancialCode}"
                var="BILL_MIRAE_ASSET_PROD_CONTENT"/>

<spring:message code="label.service.product.content.fundin.CASH_ON_HAND" var="FUNDIN_CASH_ON_HAND"/>
<spring:message code="label.service.product.content.fundin.BANK_TRANSFER"
                var="FUNDIN_BANK_TRANSFER"/>

<spring:message code="label.service.product.content.fundout.CASH_ON_HAND"
                var="FUNDOUT_CASH_ON_HAND"/>
<spring:message code="label.service.product.content.fundout.BANK_TRANSFER"
                var="FUNDOUT_BANK_TRANSFER"/>

<spring:message code="label.service.content.topup" var="TOPUP_CONTENT"/>
<spring:message code="label.service.content.epin" var="EPIN_CONTENT"/>
<spring:message code="label.service.content.p2ptransfer" var="P2P_TRANSFER_CONTENT"/>
<spring:message code="label.service.content.wallettransfer" var="WALLET_TRANSFER_CONTENT"/>
<spring:message code="label.service.content.cashback" var="CASH_BACK_CONTENT"/>
<spring:message code="label.service.content.reversal" var="TXN_REVERSAL_CONTENT"/>


<script type="text/javascript">
  $(document).ready(function () {

    drawTableTransactionList();

    $('#serviceTypeId').multiselect({
      includeSelectAllOption: true,
      dropUp: false,
      selectAllValue: '',
      selectAllText: '${choose_all}',
      maxHeight: 400,
      nonSelectedText: '${choose_service}',
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
  });

  function onSelect(value) {
    $("#statusValue").val(value);
  }

  Date.prototype.hmdmy = function () {
    var hh = this.getHours();
    var minute = this.getMinutes();
    var dd = this.getDate();
    var mm = this.getMonth() + 1;

    return (hh > 9 ? '' : '0') + hh + ":" + (minute > 9 ? '' : '0') + minute + " " + (dd > 9 ? ''
        : '0') + dd + "-" + (mm > 9 ? '' : '0') + mm + "-"
        + this.getFullYear();
  };

  Date.prototype.dmy = function () {
    var dd = this.getDate();
    var mm = this.getMonth() + 1;

    return (dd > 9 ? '' : '0') + dd + "-" + (mm > 9 ? '' : '0') + mm + "-" + this.getFullYear();
  };

  function drawTableTransactionList() {
    var quickSearch = $('#quickSearch').val();
    var date = $('#reservation').val();
    var serviceTypeId = $('#serviceTypeId').val();
    var status = $('#statusValue').val();

    $('#tblTransaction').dataTable({
      responsive: true,
      "paging": true,
      "serverSide": true,
      "iDisplayStart": 0,
      "pageLength": 20,
      "bLengthChange": false,
      "searching": false,
      "bPaginate": true,
      "processing": true,
      "serverSide": true,
      destroy: true,
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
        "url": "/ajax-controller/customer/transaction-history",
        beforeSend: function (xhr) {
          if ("${_csrf.parameterName}" && "${_csrf.token}") {
            xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
          }
        },
        cache: false,
        type: "POST",
        dataType: 'json',
        data: {
          "${_csrf.parameterName}": "${_csrf.token}",
          quickSearch: quickSearch,
          date: date,
          serviceTypeId: serviceTypeId,
          status: status
        },
        "dataSrc": 'dataList',
        dataFilter: function (reps) {
          var myObj = jQuery.parseJSON(reps);
          document.getElementById("totalTransaction").innerHTML = myObj?.recordsTotal;

          var totalAmount = formatNumberCurrency(myObj?.totalAmount);
          document.getElementById("totalRequestAmount").innerHTML = totalAmount;
          var totalCommission = formatNumberCurrency(myObj?.totalCommission);
          document.getElementById("totalCommissionTxt").innerHTML = totalCommission;
          return reps;
        },
        error: function (err) {
          console.log(err);
        }
      },
      "columns": [
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var date = new Date(data?.creationDate);
            var htmlCode = "<div>" + date?.hmdmy() + "</div>"

            return htmlCode;
          }
        },
        {"data": "id"},
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode;
            switch (data?.serviceType) {
              case "FUND_IN":
                htmlCode = "${FUND_IN_NAME}";
                break;

              case "FUND_OUT":
                htmlCode = "${FUND_OUT_NAME}";
                break;

              case "PHONE_TOPUP":
                htmlCode = "${PHONE_TOPUP_NAME}";
                break;

              case "EPIN":
                htmlCode = "${EPIN_NAME}";
                break;

              case "EXPORT_EPIN":
                htmlCode = "${EXPORT_EPIN_NAME}";
                break;

              case "BILL_PAYMENT":
                htmlCode = "${BILL_PAYMENT_NAME}";
                break;

              case "WALLET_TRANSFER":
                htmlCode = "${WALLET_TRANSFER_NAME}";
                break;

              case "P2P_TRANSFER":
                htmlCode = "${P2P_TRANSFER_NAME}";
                break;

              case "CASH_BACK":
                htmlCode = "${CASH_BACK_NAME}";
                break;

              case "WALLET_CASH_IN":
                htmlCode = "${WALLET_CASH_IN_NAME}";
                break;

              case "WALLET_CASH_OUT":
                htmlCode = "${WALLET_CASH_OUT_NAME}";
                break;

              case "TXN_REVERSAL":
                htmlCode = "${TXN_REVERSAL_NAME}";
                break;

              default:
                htmlCode = data?.serviceType;
                break;

            }
            return htmlCode;
          }
        },
        {"data": "terminalId"},
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode;
            switch (data?.serviceType) {
              case "FUND_IN":
                htmlCode = "${FUND_IN_NAME}";
                if ("CASH_ON_HAND" === data?.orderChannel) {
                  htmlCode = "${FUNDIN_CASH_ON_HAND}";
                } else if ("BANK_TRANSFER" === data?.orderChannel) {
                  htmlCode = "${FUNDIN_BANK_TRANSFER}";
                }
                break;

              case "FUND_OUT":
                htmlCode = "${FUND_OUT_NAME}";
                if ("CASH_ON_HAND" === data?.orderChannel) {
                  htmlCode = "${FUNDOUT_CASH_ON_HAND}";
                } else if ("BANK_TRANSFER" === data?.orderChannel) {
                  htmlCode = "${FUNDOUT_BANK_TRANSFER}";
                }
                break;

              case "PHONE_TOPUP":
                htmlCode = "${PHONE_TOPUP_NAME}".concat(" ").concat(
                    data?.attributeMap["PTU_TELCO"]).concat(" ").concat(
                    formatCurrency(data?.attributeMap["PTU_AMOUNT"]));
                break;

              case "EPIN":
                htmlCode = "${EPIN_NAME}".concat(" ").concat(
                    data?.attributeMap["PTU_CARD_TYPE"]).concat(" ").concat(
                    formatCurrency(data?.attributeMap["PTU_CARD_FACE_VALUE"]));
                break;

              case "EXPORT_EPIN":
                htmlCode = "${EXPORT_EPIN_NAME}";
                break;


                <%--case "BILL_PAYMENT":--%>
                <%--htmlCode = "${BILL_PAYMENT_NAME}";--%>

                <%--if (data.serviceCode === "${electricPaymentCode}") {--%>
                <%--htmlCode = "${BILL_ELECTRIC_PROD_CONTENT}";--%>
                <%--} else if (data.serviceCode === "${waterPaymentCodeTrungAn}") {--%>
                <%--htmlCode = "${BILL_WATER_TRUNG_AN_PROD_CONTENT}";--%>
                <%--} else if (data.serviceCode === "${waterPaymentCodeNhaBe}") {--%>
                <%--htmlCode = "${BILL_WATER_NHA_BE_PROD_CONTENT}";--%>
                <%--} else if (data.serviceCode === "${FECreditFinancialCode}") {--%>
                <%--htmlCode = "${BILL_FE_CREDIT_PROD_CONTENT}";--%>
                <%--} else if (data.serviceCode === "${HomeCreditFinancialCode}") {--%>
                <%--htmlCode = "${BILL_HOME_CREDIT_PROD_CONTENT}";--%>
                <%--} else if (data.serviceCode === "${PrudentialFinancialCode}") {--%>
                <%--htmlCode = "${BILL_PRUDENTIAL_PROD_CONTENT}";--%>
                <%--} else if (data.serviceCode === "${AcsFinancialCode}") {--%>
                <%--htmlCode = "${BILL_ACS_PROD_CONTENT}";--%>
                <%--} else if (data.serviceCode === "${OcbFinancialCode}") {--%>
                <%--htmlCode = "${BILL_OCB_PROD_CONTENT}";--%>
                <%--} else if (data.serviceCode === "${MCreditFinancialCode}") {--%>
                <%--htmlCode = "${BILL_MCREDIT_PROD_CONTENT}";--%>
                <%--} else if (data.serviceCode === "${MiraeAssetFinancialCode}") {--%>
                <%--htmlCode = "${BILL_MIRAE_ASSET_PROD_CONTENT}";--%>
                <%--}--%>

                <%--break;--%>

              case "WALLET_TRANSFER":
                htmlCode = "${WALLET_TRANSFER_NAME}";
                break;

              case "P2P_TRANSFER":
                htmlCode = "${P2P_TRANSFER_NAME}";
                break;

              case "CASH_BACK":
                htmlCode = "";
                break;

              case "TXN_REVERSAL":
                htmlCode = "";
                break;

              <spring:message code="label.service.cashin.viettel.pay" var="WALLET_CASH_IN"/>
              case "WALLET_CASH_IN":
                htmlCode = "${WALLET_CASH_IN}";
                break;

              default:
                htmlCode = data.serviceName;
                break;

            }
            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode;

            <spring:message code="label.service.content.topup" var="TOPUP_CONTENT"/>
            <spring:message code="label.service.content.epin" var="EPIN_CONTENT"/>
            <spring:message code="label.service.content.p2ptransfer.received" var="P2P_TRANSFER_CONTENT_RECEIVED"/>
            <spring:message code="label.service.content.wallettransfer" var="WALLET_TRANSFER_CONTENT"/>
            <spring:message code="label.service.content.cashin.viettel.pay" var="WALLET_CASH_IN"/>

            <spring:message code="transaction.api.detail.content" var="content_info"/>


            switch (data?.serviceType) {
              case "FUND_IN":
                htmlCode = "${FUND_IN_NAME}";
                break;

              case "FUND_OUT":
                htmlCode = "${FUND_OUT_NAME}";
                break;

              case "PHONE_TOPUP":
                htmlCode = "${TOPUP_CONTENT}".replace("[value]",
                    formatCurrency(data?.attributeMap["PTU_AMOUNT"])).replace("[phone_number]",
                    data?.attributeMap["PTU_MSISDN"]);
                break;

              case "EPIN":
                htmlCode = "${EPIN_CONTENT}".replace("[card_quantity]",
                    data?.attributeMap["PTU_CARD_QUANTITY"]).replace("[value]",
                    formatCurrency(data?.attributeMap["PTU_CARD_FACE_VALUE"]));
                break;

              case "EXPORT_EPIN":
                htmlCode = "${EXPORT_EPIN_NAME}";
                break;

              case "BILL_PAYMENT":
                htmlCode = "${BILL_PAYMENT_NAME}";
                var billReference = data?.attributeMap["BILL_PAYMENT_CUSTOMER_REFERENCE"];
                if (!billReference) {
                  billReference = data?.attributeMap["BILL_PAYMENT_INVOICE_REFERENCE"];
                }
                if (billReference) {
                  htmlCode = billReference;

                  if (data.payerFullname) {
                    htmlCode = billReference.concat(" / ").concat(data?.payerFullname);
                  }
                }
                break;

              case "WALLET_TRANSFER":
                htmlCode = "${WALLET_TRANSFER_CONTENT}".replace("[value]",
                    formatCurrency(data?.amount)).replace("[payee_msisdn]", data?.payeeMsisdn);
                break;

              case "P2P_TRANSFER":
                if(data?.payeeMsisdn === '${userLogin.phoneNumber}'){
                  htmlCode = "${P2P_TRANSFER_CONTENT_RECEIVED}".replace("[value]", formatCurrency(data.amount)).replace("[payer_msisdn]", data?.payerMsisdn).concat('.'.concat('${content_info}').concat(data?.orderInfo));
                  break;
                }else{
                  htmlCode = "${P2P_TRANSFER_CONTENT}".replace("[value]", formatCurrency(data.amount)).replace("[payee_msisdn]", data?.payeeMsisdn).concat('.'.concat('${content_info}').concat(data?.orderInfo));
                  break;
                }


              case "CASH_BACK":
                htmlCode = "";
                if (data?.attributes !== null && 'REF_TXN_ID'
                    === data?.attributes[1]?.transactionAttributeType) {
                  htmlCode = '${CASH_BACK_CONTENT}'.concat(": ").concat(
                      data?.attributes[1]?.transactionAttributeValue);
                }
                break;

              case "TXN_REVERSAL":
                htmlCode = "";
                if (data?.attributes !== null && 'REF_TXN_ID'
                    === data?.attributes[0]?.transactionAttributeType) {
                  htmlCode = '${TXN_REVERSAL_CONTENT}'.concat(": ").concat(
                      data?.attributes[0]?.transactionAttributeValue);
                }
                break;

              case "WALLET_CASH_IN":
                htmlCode = "";
                if (data?.attributes !== null && 'WALLET_CASH_RECEIVER_MSISND'
                  === data?.attributes[2]?.transactionAttributeType) {
                  htmlCode = '${WALLET_CASH_IN}'.concat(": ").concat(
                    data?.attributes[2]?.transactionAttributeValue);
                }
                break;



              default:
                htmlCode = data?.serviceName;
                break;

            }
            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var attribute = 'red-600';
            if(data?.serviceType === 'FUND_IN' || data?.serviceType === 'WALLET_CASH_OUT'){
              attribute = 'green-600';
            }
            // var attribute = ('FUND_IN') === data.serviceType ? 'green-600' : 'red-600';
            var htmlCode = "<div class=\"text-right " + attribute + "\">" + formatCurrency(
                data?.realAmount
                == null ? 0 : data?.realAmount)
                + "</div>";
            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var status = data?.transactionStatus;
            switch (status) {
              case 10:
                status = "${success_title}";
                break;

              case 9:
                status = "${pending_title}";
                break;

              case 5:
                status = "${reversed_title}";
                break;

              case 3:
                status = "${fail_title}";
                break;

              case 6:
                status = "${success_title}";
                break;

              default:
                status = "${pending_title}";
                break;
            }
            return status;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = "";

            if ('10' == data?.transactionStatus && 'EPIN' == data?.serviceType) {
              htmlCode = ""
                  + "<form method='post' action='/trans-log/view-pincode-login?transactionId="
                  + data?.id + "'>\n"
                  + "   <input type='hidden' name='creationDate' value='" + data?.creationDate
                  + "'>\n"
                  + "   <input type='hidden' name='serviceType' value='" + data?.serviceType + "'>\n"
                  + "   <input type='hidden' name='serviceName' value='" + data?.serviceName + "'>\n"
                  + "   <input type='hidden' name='orderInfo' value='" + data?.orderInfo + "'>\n"
                  + "   <input type='hidden' name='amount' value='" + data?.amount + "'>\n"
                  + "   <input type='hidden' name='commission' value='" + data?.commision + "'>\n"
                  + "   <input type='hidden' name='realAmount' value='" + data?.realAmount + "'>\n"
                  + "   <input type='hidden' name='errorMessage' value='" + data?.errorMessage
                  + "'>\n"
                  + "   <input type='hidden'  name='${_csrf.parameterName}' value='${_csrf.token}'>\n"
                  + "   <button type='submit' class='btn btn-xs btn-primary'>${viewCardCode}</button>\n"
                  + "</form>";
            } else if ('3' == data?.transactionStatus && 'EPIN' == data?.serviceType) {
              htmlCode = ""
                  + "<form method='post' action='/pin-code/order-info'>\n"
                  + "   <input type='hidden'  name='${_csrf.parameterName}' value='${_csrf.token}'>\n"
                  + "   <button class='btn btn-xs btn-warning'>" + "${rerun}" + "</button>";
              +"</form>";
            }
            else if ('3' == data?.transactionStatus && 'FUND_IN' == data?.serviceType && "WEB"
                == data?.terminalId) {
              htmlCode = ""
                  + "<form method='post' action='/fundin/link-bank'>\n"
                  + "   <input type='hidden' name='faceValue' value='" + data?.realAmount + "'>\n"
                  + "   <input type='hidden'  name='${_csrf.parameterName}' value='${_csrf.token}'>\n"
                  + "   <button class='btn btn-xs btn-warning'>" + "${rerun}" + "</button>";
              +"</form>";
            }
            else if ('10' == data?.transactionStatus && 'EXPORT_EPIN' == data?.serviceType) {
              if (data.attributes !== null && 'EPIN_PURCHASE_ORDER_CODE'
                  === data?.attributes[0]?.transactionAttributeType) {
                htmlCode = ""
                    + "<a class='btn btn-xs btn-primary' href='/batch-cards/detail?poCode="
                    + data?.attributes[0]?.transactionAttributeValue + "'>"
                    + "${viewBatchCardDetail}</a>";
              }
            }
            else if ('10' == data?.transactionStatus && 'BILL_PAYMENT' == data?.serviceType) {
              htmlCode = ""
                  + "<form method='post' action='/trans-log/view-bill'>\n"
                  + "   <input type='hidden' name='transactionId' value='" + data.id + "'>\n"
                  + "   <input type='hidden'  name='${_csrf.parameterName}' value='${_csrf.token}'>\n"
                  + "   <button type='submit' class='btn btn-xs btn-primary'>${viewBill}</button>\n"
                  + "</form>";
            }
            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var attribute = 'red-600';
            if(data?.serviceType === 'FUND_IN' || data?.serviceType === 'WALLET_CASH_OUT'){
              attribute = 'green-600';
            }
            // var attribute = ('FUND_IN') === data.serviceType ? 'green-600' : 'red-600';
            var htmlCode = "<div class=\"text-right " + attribute + "\">" + formatCurrency(
                data?.amount == null
                    ? 0 : data?.amount)
                + "</div>";
            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var attribute = 'green-600';
            var htmlCode = "<div class=\"text-right " + attribute + "\">" + formatCurrency(
                data?.commision
                == null ? 0 : data?.commision)
                + "</div>";
            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var attribute = 'red-600';
            var htmlCode = "<div class=\"text-right " + attribute + "\">" + formatCurrency(
                data?.fee == null
                    ? 0 : data?.fee)
                + "</div>";
            return htmlCode;
          }
        }
      ]
    });
  }

  function formatNumberCurrency(currency) {
    var strCurrency = currency.toString();
    if (strCurrency.length > 3) {
      var length = strCurrency.length;
      var newCurrency;
      var remainPart;

      newCurrency = strCurrency.substring(length - 3, length);
      remainPart = strCurrency.substring(0, length - newCurrency.length);
      remainPart = format(remainPart);

      return remainPart + "." + newCurrency;
    } else {
      return currency;
    }
  }

  <%--$('.export-log').click(function () {--%>

  <%--var url = '/ajax-controller/export-log';--%>
  <%--var textSearch = $('#quickSearch').val();--%>
  <%--if (textSearch = "") {--%>
  <%--$('#quickSearch').val = null;--%>
  <%--}--%>
  <%--var serviceTypeId = $('#serviceTypeId').val();--%>
  <%--var range = $('#reservation').val();--%>
  <%--var status = $('#status').val();--%>

  <%--if (range != null && range != '') {--%>
  <%--$.MessageBox({--%>
  <%--buttonDone: '${button_yes}',--%>
  <%--buttonFail: '${button_no}',--%>
  <%--message: '${button_confirm_export_file}'--%>
  <%--}).done(function () {--%>
  <%--$.ajax({--%>
  <%--type: 'GET',--%>
  <%--url: url,--%>
  <%--data: {--%>
  <%--textSearch: textSearch,--%>
  <%--serviceTypeId: serviceTypeId,--%>
  <%--range: range,--%>
  <%--status: status--%>
  <%--},--%>
  <%--success: function (res) {--%>

  <%--if (res.status.code == 0) {--%>
  <%--downloadFile(url, res.logFile)--%>
  <%--&lt;%&ndash;.done(function () {&ndash;%&gt;--%>
  <%--&lt;%&ndash;$.MessageBox(&ndash;%&gt;--%>
  <%--&lt;%&ndash;{message: '${export_success}'});&ndash;%&gt;--%>
  <%--&lt;%&ndash;})&ndash;%&gt;--%>
  <%--&lt;%&ndash;.fail(function () {&ndash;%&gt;--%>
  <%--&lt;%&ndash;$.MessageBox(&ndash;%&gt;--%>
  <%--&lt;%&ndash;{message: '${export_fail}'});&ndash;%&gt;--%>
  <%--&lt;%&ndash;});&ndash;%&gt;--%>
  <%--} else {--%>
  <%--$.MessageBox({message: res.status.value});--%>
  <%--}--%>
  <%--}--%>
  <%--});--%>
  <%--});--%>
  <%--//      return false;--%>
  <%--} else {--%>
  <%--$.MessageBox({message: "Chọn ngày tháng cụ thể"});--%>
  <%--return false;--%>
  <%--}--%>
  <%--});--%>

  $('.export-log').click(function () {

    var textSearch = $('#quickSearch').val();
    if (textSearch = "") {
      $('#quickSearch').val = null;
    }
    var serviceTypeId = $('#serviceTypeId').val();
    var range = $('#reservation').val();
    var status = $('#status').val();
    if (range != null && range != '') {
      $.MessageBox({
        buttonDone: '${button_yes}',
        buttonFail: '${button_no}',
        message: '${button_confirm_export_file}'
      }).done(function () {

        var url = '<%=request.getContextPath()%>/trans-log/export-log' + '?textSearch=' + textSearch
            + '&' + 'serviceTypeIds='
            + serviceTypeId + '&' + 'range=' + range + '&' + 'statusIds=' + status;
        $.fileDownload(url)
        .done(function () {
          $.MessageBox({message: '${export_success}'});
        })
        .fail(function () {
          $.MessageBox(
              {message: '${errorExportLogTransaction != null && errorExportLogTransaction ne '' ? errorExportLogTransaction : export_fail}'});
        });
      });
      return false;
    } else {
      $.MessageBox({message: '${choose_date}'});
      return false;
    }

  });

</script>
</html>