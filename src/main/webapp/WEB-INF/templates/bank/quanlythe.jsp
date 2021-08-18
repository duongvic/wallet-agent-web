<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.bank.BankController.CARD_CONTROLLER_UNLINK" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.bank.BankController.BANK_CARD_DELETE" %>
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
    <title><spring:message code="card.manage.title"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <!-- /head libs  -->

    <link rel="stylesheet" href="<c:url value="/assets/development/css/epin.css"/>">
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

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message code="card.manage.label"/></li>
        </ol>
        <div class="row">
            <div class="col-md-4">
                <h1 class="page-title"><spring:message code="card.manage.label"/></h1>
            </div>
            <div class="row col-md-8 col-sm-12 hidden">
                <div class="col-md-4 col-sm-12">
                    <span class="page-title active-linkBank"><a href="#"
                                                                onclick="showLinkBank()"><spring:message
                            code="account.bank.link"/> </a></span>
                    <span class="page-title"><a href="#">/</a></span>
                    <span class="page-title active-saveBank"><a href="#"
                                                                       onclick="showSaveBank()">Đã lưu</a></span>
                </div>
            </div>
        </div>
    </div>
    <div class="page-content container-fluid">
        <form action="/bank/manage" method="post" id="formBankManage">
            <div class="owl-carousel owl-carousel-shortcode thenganhang mt-15"
                 data-plugin="" data-center="true" data-dots="true" data-margin="5">
                <div class="form-group row link-bank">
                    <c:if test="${(listBankDirect!=null)}">
                        <div class="col-md-6 col-sm-12 p-0">
                                <%--<form action="/bank/un-link-bank" method="get">--%>
                            <c:forEach var="item" items="${listBankDirect}">
                                <div class="col">
                                    <div class="card-id mb-10">
                                        <div class="logo-card-l"><img alt="${item.bankCode}"
                                                                      src="/assets/images/bank/${item.bankCode}.png">
                                        </div>
                                        <div class="logo-card-r"><img alt=""
                                                                      src="/assets/images/delete.png"
                                                                      style="width: 20px"
                                                                      onclick="unLinkBank()">

                                            <input type="hidden" id="bankName" name="bankName"
                                                   value="${bankName}">
                                            <input type="hidden" id="bankDisplayName"
                                                   name="bankDisplayName"
                                                   value="${bankDisplayName}">
                                            <input type="hidden" id="bankAccountNumber"
                                                   name="bankAccountNumber"
                                                   value="${bankAccountNumber}">
                                            <input type="hidden" id="phoneNumber"
                                                   name="phoneNumber"
                                                   value="${phoneNumber}">
                                            <input type="hidden" id="bankCode" name="bankCode"
                                                   value="${bankCode}">
                                            <input type="hidden" id="bankAccountName"
                                                   name="bankAccountName"
                                                   value="${bankAccountName}">
                                            <input type="hidden" id="ssn" name="ssn"
                                                   value="${ssn}">
                                            <input type="hidden" id="walletId" name="walletId"
                                                   value="${walletId}">
                                            <input type="hidden" id="subscriptionId"
                                                   name="subscriptionId"
                                                   value="${subscriptionId}">

                                        </div>
                                        <div class="number-card card-number-input">${item.bankAccountNumber}</div>
                                        <div class="name-date">
                                            <div class="name">${item.bankAccountName}</div>
                                            <c:if test="${item.cardIssueDate != 'null'}">
                                                <div class="date">${item.cardIssueDate}</div>
                                            </c:if>

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                                <%--<input type="hidden" name="${_csrf.parameterName}"--%>
                                <%--value="${_csrf.token}"/>--%>
                                <%--</form>--%>
                        </div>
                    </c:if>
                    <c:if test="${(listBankDirect==null)}">
                        <div class="col-md-6 col-sm-12">
                            <a href="/bank/link-bank-account"><img src="/assets/images/the6.png"
                                                                   style="width: 318px;
    height: 155px;"></a>
                        </div>
                    </c:if>
                </div>

                <div class="form-group row save-bank">
                    <c:if test="${(listSaveBank!=null)}">
                        <c:forEach var="item" items="${listSaveBank}">
                            <div class="col-md-4 col-sm-12">
                                    <%--<form action="/bank/deleteSaveBank" method="get">--%>

                                <div class="card-id mb-10">
                                    <div class="logo-card-l"><img alt="${item.bankName}"
                                                                  src="/assets/images/bank/${item.bankCode}.png">

                                        <input type="hidden" name="itemType" id="itemType"
                                               value="${item.itemType}">
                                        <input type="hidden" name="bankItemId" id="bankItemId"
                                               value="${item.id}">
                                    </div>
                                    <div class="logo-card-r"><img alt=""
                                                                  src="/assets/images/delete.png"
                                                                  style="width: 20px"
                                                                  onclick="deleteSaveBank('${item.itemType}','${item.id}')">
                                    </div>
                                    <div class="number-card card-number-input">${item.itemNumber}</div>
                                    <div class="name-date">
                                        <div class="name">${item.itemHolderName}</div>
                                        <div class="date">${item.itemIssueDate}</div>
                                    </div>
                                </div>

                                    <%--<input type="hidden" name="${_csrf.parameterName}"--%>
                                    <%--value="${_csrf.token}"/>--%>
                                    <%--</form>--%>
                            </div>
                        </c:forEach>
                    </c:if>
                    <div class="col-md-6 col-sm-12">
                        <a href="/bank/link-bank-account"><img src="/assets/images/the6.png" style="width: 318px;
    height: 155px;"></a>
                    </div>
                </div>
            </div>


            <div class="mt-30">
                <div class="panel mb-0 panel-bordered">
                    <div class="panel-heading">
                        <div class="form-row row" style="
    margin: 1rem;
">
                            <div class="col-lg-6 col-md-6 col-sm-12  mt-20">
                                <div class="time">
                                    <div id="reportrange">
                                        <i class="fa fa-calendar"></i>&nbsp;
                                        <input type="hidden" name="range" id="reservation"
                                               value=""/>&nbsp;
                                        <span></span>
                                        <i class="fa fa-caret-down pull-right"></i>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12  mt-20 text-right">
                                <button type="button" class="btn btn-primary btn-sm"><i
                                        class="fa fa-file"></i>&nbsp;<spring:message
                                        code="account.bank.report.export"/>
                                </button>
                                <button type="button" class="btn btn-primary btn-sm"><i
                                        class="fa fa-file-text"></i>&nbsp;<spring:message
                                        code="account.bank.report.print"/>
                                </button>
                            </div>
                        </div>
                    </div>

                    <spring:message code="transaction.api.action.success" var="success_title"/>
                    <spring:message code="transaction.api.action.fail" var="fail_title"/>
                    <div class="panel-body tb-tool">
                        <table class="table table-hover table-bordered table-striped mb-none dataTable no-footer w-full"
                               id="tblTransaction">
                            <thead>
                            <tr>
                                <th><spring:message code="transaction.api.table.date"/></th>
                                <th><spring:message code="transaction.api.table.order.id"/></th>
                                <th><spring:message code="transaction.api.table.service"/></th>
                                <th><spring:message code="transaction.api.table.status"/></th>
                                <th class="text-center"><spring:message
                                        code="table.card.amout"/></th>
                                <th class="text-center"><spring:message
                                        code="table.card.discount"/></th>
                                <th class="text-center"><spring:message
                                        code="table.card.payment"/></th>


                            </tr>
                            </thead>
                            <tbody>
                            <%--<c:forEach items="${listTransactionsCard}" var="item">--%>
                            <%--<tr>--%>
                            <%--<td><fmt:formatDate value="${item.creationDate}"--%>
                            <%--pattern="dd/MM/yyyy"/></td>--%>
                            <%--<td>${item.id}</td>--%>
                            <%--<td>${item.serviceType}</td>--%>
                            <%--<td class="text-center">--%>
                            <%--<c:if test="${item.transactionStatus eq '10'}">--%>
                            <%--<spring:message code="label.success"/>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${item.transactionStatus eq '3'}">--%>
                            <%--<spring:message code="label.fail"/>--%>
                            <%--</c:if>--%>
                            <%--</td>--%>
                            <%--<td class="text-right">${ewallet:formatNumber(item.realAmount)}</td>--%>
                            <%--<td class="text-center">${ewallet:formatNumber(item.commission)}</td>--%>
                            <%--<td class="text-right">${ewallet:formatNumber(item.amount)}</td>--%>
                            <%--</tr>--%>
                            <%--</c:forEach>--%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <%--<button type="submit" id="submit-search" class="hidden"/>--%>
        </form>
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
                            <div class="panel-body text-center pt-0"><a
                                    href="/bank/link-bank-account"
                                    class="btn btn-primary btn-sm"> <spring:message
                                    code="common.btn.add"/><i
                                    class="icon wb-arrow-right ml-10"></i> </a></div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <div class="panel panel-bordered">
                            <div class="panel-heading text-center">
                                <h3 class="panel-title"><spring:message
                                        code="label.bank.card"/></h3></div>
                            <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
                            <div class="panel-body text-center pt-0"><a href="/bank/themTheNganHang"
                                                                        class="btn btn-primary btn-sm">
                                <spring:message code="common.btn.add"/><i
                                    class="icon wb-arrow-right ml-10"></i> </a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<c:import url="../dialog_modal/pin_code/dialog_addCard.jsp"/>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->

<%--
<script src="/assets/js/babel-external-helpers.js"></script>
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.js"></script>
<script src="/assets/js/animsition.js"></script>
<script src="/assets/js/jquery.mousewheel.js"></script>
<script src="/assets/js/jquery-asScrollbar.js"></script>
<script src="/assets/js/jquery-asScrollable.js"></script>
<script src="/assets/js/jquery-asHoverScroll.js"></script>
<script src="/assets/js/switchery.js"></script>
<script src="/assets/js/intro.js"></script>
<script src="/assets/js/screenfull.js"></script>
<script src="/assets/js/jquery-slidePanel.js"></script>
<script src="/assets/js/jquery.dataTables.js"></script>
<script src="/assets/js/dataTables.bootstrap4.js"></script>
<script src="/assets/js/dataTables.fixedHeader.js"></script>
<script src="/assets/js/dataTables.fixedColumns.js"></script>
<script src="/assets/js/dataTables.rowGroup.js"></script>
<script src="/assets/js/dataTables.scroller.js"></script>
<script src="/assets/js/dataTables.responsive.js"></script>
<script src="/assets/js/responsive.bootstrap4.js"></script>
<script src="/assets/js/dataTables.buttons.js"></script>
<script src="/assets/js/buttons.html5.js"></script>
<script src="/assets/js/buttons.flash.js"></script>
<script src="/assets/js/buttons.print.js"></script>
<script src="/assets/js/buttons.colVis.js"></script>
<script src="/assets/js/buttons.bootstrap4.js"></script>
<script src="/assets/js/owl.carousel.js"></script>
<script src="/assets/js/slick.js"></script>
<script src="/assets/js/Component.js"></script>
<script src="/assets/js/Plugin.js"></script>
<script src="/assets/js/Base.js"></script>
<script src="/assets/js/Config.js"></script>
<script src="/assets/js/Menubar.js"></script>
<script src="/assets/js/GridMenu.js"></script>
<script src="/assets/js/Sidebar.js"></script>
<script src="/assets/js/PageAside.js"></script>
<script src="/assets/js/menu.js"></script>
<script src="/assets/js/colors.js"></script>
<script src="/assets/js/tour.js"></script>
<script src="/assets/js/Site.js"></script>
<script src="/assets/js/asscrollable.js"></script>
<script src="/assets/js/slidepanel.js"></script>
<script src="/assets/js/switchery.js"></script>
<script src="/assets/js/owl-carousel.js"></script>
<script src="/assets/js/carousel.js"></script>
<script src="/assets/js/datatables.js"></script>
<script src="/assets/js/datatable.js"></script>
<script src="/assets/development/js/button/button-ripple.js"></script>
<script src="/assets/development/js/my_format_currency.js"></script>--%>

<c:import url="../include_page/js_daterangepicker.jsp"/>


<c:url var="unLinkURL" value="<%=CARD_CONTROLLER_UNLINK%>"></c:url>
<c:url var="deleteBankURL" value="<%=BANK_CARD_DELETE%>"></c:url>

<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<spring:message code="data.table.info.empty" var="info_empty"/>
<script>
  $(document).ready(function () {
     var listCustomerBank = "${listBankDirect}";
    if(listCustomerBank !=""){
      drawTableList();
    }


    <%--var t = $('#tblTransaction').DataTable({--%>
    <%--responsive: true,--%>
    <%--"searching": false, // bỏ auto search--%>
    <%--"bLengthChange": false, // không show row--%>
    <%--"order": [[1, 'desc']],--%>
    <%--"language": {--%>
    <%--"sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",--%>
    <%--"paginate": {--%>
    <%--"previous": "${paging_previous}",--%>
    <%--"next": "${paging_next}"--%>
    <%--}--%>
    <%--}--%>
    <%--});--%>
    <%--new $.fn.dataTable.FixedHeader(t);--%>
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

  Date.prototype.dmy = function () {
    var dd = this.getDate();
    var mm = this.getMonth() + 1;

    return (dd > 9 ? '' : '0') + dd + "-" + (mm > 9 ? '' : '0') + mm + "-" + this.getFullYear();
  };

  function drawTableList() {
    var date = $('#reservation').val();
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
        "infoEmpty": "${info_empty}",
        "processing": "<img src='../../assets/images/loading_airline.gif'/>"
      },
      "ajax": {
        "url": "/ajax-controller/bank-manage/list",
        beforeSend: function (xhr) {
          if ("${_csrf.parameterName}" && "${_csrf.token}") {
            xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
          }
        },
        "type": "POST",
        "data": {
          "${_csrf.parameterName}": "${_csrf.token}",
          quickSearch: null,
          range: date,
          serviceTypeId: [],
          serviceCode: []
        },
        dataSrc: 'dataList'
      },
      "columns": [
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var date = new Date(data.creationDate);
            var htmlCode = '<div>' + date.dmy() + '</div>'
            return htmlCode;
          }
        },
        {"data": "id"},
        {"data": "serviceType"},
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = "";
            var tranStatus = '10' == data.transactionStatus ? "${success_title}" : "${fail_title}";
            htmlCode = '<div>' + tranStatus + '</div>'
            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = '<div class="text-right"> ' + formatCurrency(
                data.realAmount == null ? 0 : data.realAmount) + ' </div>'
            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = '<div class="text-right">' + formatCurrency(
                data.commision == null ? 0 : data.commision) + ' </div>'
            return htmlCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var htmlCode = '<div class="text-right">' + formatCurrency(
                data.amount == null ? 0 : data.amount) + ' </div>'
            return htmlCode;
          }
        }
      ]
    });
  }

  function unLinkBank() {
    window.location.href = '${unLinkURL}';
  }

  function deleteSaveBank(type, id) {
    var result = confirm("Bạn muốn xóa ?");
    if (result) {
      //Logic to delete the item
      window.location.href = '${deleteBankURL}?itemType=' + type + '&bankItemId=' + id;
    }
  }

  function showLinkBank() {
    $(".save-bank").css({"display": "none"});
    $(".active-saveBank a").css({"text-decoration": "none"});
    $(".link-bank").css({"display": "inline-flex"});
    $(".active-linkBank a").css({"text-decoration": "underline"});
  }

  function showSaveBank() {
    $(".link-bank").css({"display": "none"});
    $(".active-linkBank a").css({"text-decoration": "none"});
    $(".save-bank").css({"display": "inline-flex"});
    $(".active-saveBank a").css({"text-decoration": "underline"});
  }

  $('#reportrange').on('apply.daterangepicker', function (e) {
    e.preventDefault();
    $('#reservation').change();

  });
  $('#reservation').change(function () {
//    $('#formBankManage').submit();
    drawTableList();
    console.log($('#reportrange input:hidden[name="range"]').val());
  });

</script>

</body>

</html>