<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="menu.fundout"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <link rel="stylesheet" href="<c:url value="/assets/development/css/epin.css"/>">
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification -->

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<c:if test="${'cash-on-hand' eq fund_out_method}">
  <spring:message code="fundout.cash.on.hand.request" var="fund_out_method_label"/>
</c:if>
<c:if test="${'bank-transfer' eq fund_out_method}">
  <spring:message code="fundout.list.bankTransfer.request" var="fund_out_method_label"/>
</c:if>
<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message
          code="label.manage"/></li>
      <li class="breadcrumb-item">${fund_out_method_label}</li>
    </ol>
    <h1 class="page-title">${fund_out_method_label}</h1>
  </div>
  <div class="page-content container-fluid">
    <div class="panel mb-0 panel-bordered">
      <div class="panel-body">

        <c:if test="${(codeErr != null && fn:length(codeErr) gt 0) }">
          <div class="row">
            <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
              <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
            </div>
          </div>
        </c:if>

        <div class="row">

          <div class="col-sm-12 col-md-3 col-lg-3 time">
            <span class="fa fa-search input-group-search"></span>
            <spring:message code="fundorder.search.place.holder"
                            var="search_place_holder"/>
            <input type="text" class="form-control search-entry" name="quickSearch"
                   id="quickSearch"
                   value="${param.quickSearch}"
                   placeholder="${search_place_holder}"
                   style="border-radius: 16px; padding-left: 3rem;">
          </div>
          <div class="col-sm-12 col-md-5 col-lg-5">
            <div class="row">
              <%--<div class="col-xs search_entry mb-10 mx-10">--%>
                <%--<select id="flow-stage" class="form-control" multiple="multiple">--%>
                  <%--<option value="0"><spring:message--%>
                      <%--code="fundorder.request.flow.stage.id.0"/></option>--%>
                  <%--<option value="1"><spring:message--%>
                      <%--code="fundorder.request.flow.stage.id.1"/></option>--%>
                  <%--<option value="2"><spring:message--%>
                      <%--code="fundorder.request.flow.stage.id.2"/></option>--%>
                  <%--<option value="3"><spring:message--%>
                      <%--code="fundorder.request.flow.stage.id.3"/></option>--%>
                  <%--<option value="4"><spring:message--%>
                      <%--code="fundorder.request.flow.stage.id.4"/></option>--%>
                  <%--<option value="5"><spring:message--%>
                      <%--code="fundorder.request.flow.stage.id.5"/></option>--%>
                  <%--<option value="6"><spring:message--%>
                      <%--code="fundorder.request.flow.stage.id.6"/></option>--%>
                  <%--<option value="8"><spring:message--%>
                      <%--code="fundorder.request.flow.stage.id.8"/></option>--%>
                <%--</select>--%>
              <%--</div>--%>

              <div class="col-xs search_entry mb-10 mx-10">
                <select id="txn-status" class="form-control" multiple="multiple">
                  <%--<option value="0"><spring:message--%>
                      <%--code="fundorder.request.txn.status.0"/></option>--%>
                  <%--<option value="1"><spring:message--%>
                      <%--code="fundorder.request.txn.status.1"/></option>--%>
                  <option value="3"><spring:message
                      code="fundorder.request.txn.status.3"/></option>
                  <%--<option value="5"><spring:message--%>
                      <%--code="fundorder.request.txn.status.5"/></option>--%>
                  <%--<option value="7"><spring:message--%>
                      <%--code="fundorder.request.txn.status.7"/></option>--%>
                  <option value="10"><spring:message
                      code="fundorder.request.txn.status.10"/></option>
                </select>
              </div>
            </div>
          </div>
          <div class="col-sm-12 col-md-4 col-lg-4 text-right search_entry mb-10">
            <button onclick="drawTableList()"
            id="submit-search" class="btn btn-warning btn-sm">
            <i class="fa fa-search"></i>&nbsp;<spring:message
            code="transaction.api.button.search"/>
            </button>
            <c:if test="${'cash-on-hand' eq fund_out_method}">
              <button type="button" class="btn btn-primary btn-sm mb-xs mt-xs"
                      onclick="window.location.replace('/fundout/${fund_out_method}/create')">
                <i class="fa fa-plus"></i>&nbsp;<spring:message
                  code="fundorder.request.create.label"/>
              </button>
            </c:if>
            <%--<c:if test="${'bank-transfer' eq fund_out_method}">--%>
            <%--<button type="button" class="btn btn-primary btn-sm mb-xs mt-xs"--%>
            <%--onclick="window.location.replace('/fundout/${fund_out_method}/create/info')">--%>
            <%--<i class="fa fa-plus"></i>&nbsp;<spring:message--%>
            <%--code="fundorder.request.create.label"/>--%>
            <%--</button>--%>
            <%--</c:if>--%>
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
        </div>
      </div>


      <div class="panel-body tb-tool">
        <table
            class="table table-hover table-bordered table-striped mb-none dataTable no-footer w-full"
            id="tblTransaction">
          <thead>
          <tr>
            <th><spring:message code="fundorder.table.header.label.no"/></th>
            <th><spring:message code="fundorder.table.header.label.request.id"/></th>
            <th><spring:message
                code="fundorder.table.header.label.request.method"/></th>
            <th><spring:message
                code="fundorder.table.header.label.request.amount"/></th>
            <th><spring:message
                code="fundorder.table.header.label.request.created"/></th>
            <th><spring:message
                code="fundorder.table.header.label.request.progress"/></th>
            <th><spring:message
                code="fundorder.table.header.label.request.status"/></th>
            <th><spring:message
                code="fundorder.table.header.label.request.action"/></th>
          </tr>
          </thead>
          <tbody>

          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<jsp:include page="../include_page/footer.jsp"/>
<!-- /footer -->
<jsp:include page="../include_page/js_daterangepicker.jsp"/>

<spring:message code="select.choose.all" var="choose_all"/>
<spring:message code="select.choose.forder.process" var="choose_process"/>
<spring:message code="select.status" var="choose_status"/>

<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<spring:message code="data.table.header.paging.records" var="paging_records"/>

<spring:message code="fundorder.request.flow.stage.id.0" var="stage_id_0"/>
<spring:message code="fundorder.request.flow.stage.id.1" var="stage_id_1"/>
<spring:message code="fundorder.request.flow.stage.id.2" var="stage_id_2"/>
<spring:message code="fundorder.request.flow.stage.id.3" var="stage_id_3"/>
<spring:message code="fundorder.request.flow.stage.id.4" var="stage_id_4"/>
<spring:message code="fundorder.request.flow.stage.id.5" var="stage_id_5"/>
<spring:message code="fundorder.request.flow.stage.id.6" var="stage_id_6"/>
<spring:message code="fundorder.request.flow.stage.id.8" var="stage_id_8"/>

<spring:message code="fundorder.request.txn.status.0" var="txn_status_0"/>
<spring:message code="fundorder.request.txn.status.1" var="txn_status_1"/>
<spring:message code="fundorder.request.txn.status.3" var="txn_status_3"/>
<spring:message code="fundorder.request.txn.status.5" var="txn_status_5"/>
<spring:message code="fundorder.request.txn.status.7" var="txn_status_7"/>
<spring:message code="fundorder.request.txn.status.10" var="txn_status_10"/>

<spring:message code="fundorder.request.method.cash.on.hand" var="cash_on_hand_method"/>
<spring:message code="fundorder.request.method.bank.transfer" var="bank_transfer_method"/>

<spring:message code="label.detail" var="detail_label"/>
<spring:message code="label.button.title.edit" var="edit_label"/>

<spring:message code="select.choose.forder.process" var="choose_process"/>
<spring:message code="select.status" var="choose_status"/>
</body>
<script type="text/javascript">
  $(document).ready(function () {
    drawTableList();
  });

  $('#quickSearch').on('keyup', function (event) {
    event.preventDefault();
    // Number 13 is the "Enter" key on the keyboard
    if (event.keyCode === 13) {
      drawTableList();
    }
  });

  $('#flow-stage').on('change', function () {
    drawTableList();
  });

  $('#txn-status').on('change', function () {
    drawTableList();
  });

  Date.prototype.hmdmy = function () {
    var hh = this.getHours();
    var minute = this.getMinutes();
    var dd = this.getDate();
    var mm = this.getMonth() + 1;

    return (hh > 9 ? '' : '0') + hh + ":" + (minute > 9 ? '' : '0') + minute + " " + (dd > 9 ? ''
        : '0') + dd + "-" + (mm > 9 ? '' : '0') + mm + "-"
        + this.getFullYear();
  };

  $('#flow-stage').multiselect({
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

  $('#txn-status').multiselect({
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

  function drawTableList() {
    var quickSearch = $('#quickSearch').val();
    var flowStage = $('#flow-stage').val();
    var txnStatus = $('#txn-status').val();
    var reservation = $('#reservation').val();

    var serviceTypeId = ['FUND_OUT'];
    var orderChanelId = [('cash-on-hand' === '${fund_out_method}') ? 'CASH_ON_HAND'
        : 'BANK_TRANSFER'];

    var listTable = $('#tblTransaction').DataTable({
      responsive: true,
      destroy: true,
      "paging": true,
      "serverSide": true,
      "iDisplayStart": 0,
      "pageLength": 5,
      "lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
      "searching": false,
      "ordering": false,
      "bProcessing": true,
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
        "url": "/ajax-controller/fundOrder/find",
        "type": "POST",
        "data": {
          "${_csrf.parameterName}": "${_csrf.token}",
          service_type_id: serviceTypeId,
          order_channel_id: orderChanelId,
          flow_stage: flowStage,
          txn_status: txnStatus,
          quickSearch: quickSearch,
          date_time: reservation
        },
        dataSrc: 'dataList',
        error: function (jqXHR, textStatus, errorThrown) {
          if (jqXHR.status == 404) {
            alert('Cannot connect to Server!');
          } else if (jqXHR.status == 405) {
            alert('Method not Allowed!');
          } else if (jqXHR.status == 422) {
            alert('You must fill all required field!');
          } else {
            alert('There is an error, please try again!');
          }
        }
      },
      "columns": [
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var index = meta.settings.oAjaxData.start + meta.row + 1;

            return index;
          }
        },
        {"data": "id"},
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var orderChannel = "";
            switch (data.orderChannel) {
              case "CASH_ON_HAND":
                orderChannel = "${cash_on_hand_method}";
                break;
              case "BANK_TRANSFER":
                orderChannel = "${bank_transfer_method}";
                break;
            }

            return orderChannel;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = "<div class='text-right'>" + formatCurrency(data.amount) + "</div>";

            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            if (data.createdTime !== null) {
              var createdDate = new Date(data.createdTime);
              return createdDate.hmdmy();
            }
            return "";
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var stageString = "";
            switch (data.stage) {
              case 0: {
                stageString = "${stage_id_0}";
                break;
              }
              case 1: {
                stageString = "${stage_id_1}";
                break;
              }
              case 2: {
                stageString = "${stage_id_2}";
                break;
              }
              case 3: {
                stageString = "${stage_id_3}";
                break;
              }
              case 4: {
                stageString = "${stage_id_4}";
                break;
              }
              case 5: {
                stageString = "${stage_id_5}";
                break;
              }
              case 6: {
                stageString = "${stage_id_6}";
                break;
              }
              case 8: {
                stageString = "${stage_id_8}";
                break;
              }
            }

            return stageString;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var status = "";
            var stageString = data.stage;
            switch (data.refTxnStatus) {
              case 0: {
                status = "${txn_status_0}";
                break;
              }
              case 1: {
                status = "${txn_status_1}";
                break;
              }
              case 3: {
                status = "${txn_status_3}";
                break;
              }
              case 5: {
                status = "${txn_status_5}";
                break;
              }
              case 7: {
                status = "${txn_status_7}";
                break;
              }
              case 10: {
                status = "${txn_status_10}";
                break;
              }
            }
            if(stageString == 1 || stageString ==3 || stageString ==5){
              status = "${txn_status_3}";
            }
            return status;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = ""
                + "<a href=\"/fundout/${fund_out_method}/management/detail/" + data.id
                + "\">${detail_label}</a>";

            if (data.stage == 0 && data.refTxnStatus == 0) {
              htmlCode += ""
                  + "<a class=\"ml-10\" href=\"/fundout/${fund_out_method}/management/detail/"
                  + data.id
                  + "/edit\">${edit_label}</a>";
            }

            return htmlCode;
          }
        }
      ]
    });
    new $.fn.dataTable.FixedHeader(listTable);
  }
</script>
</html>