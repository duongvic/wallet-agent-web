<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib
    uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
    prefix="ewallet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.HISTORY_LIST" %>
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
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<c:url var="dashBoardControlUri" value="<%=HISTORY_LIST%>"></c:url>

<%--<div class="page page-email">--%>
<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message
          code="transaction.api.bread.crumb"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="menu.transaction.history"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="panel panel-bordered col-md-12 col-small-12">
        <div class="panel-body">

          <form class="form-horizontal" method="get">
            <div class="form-group row mb-10">
              <h4 class="panel-title pl-0"><spring:message
                  code="menu.pincode.view.title"/> ${transactionId}</h4>
            </div>
            <div class="form-inline pull-right">
              <%--<div class="form-group">--%>
              <%--<button type="button" class="btn btn-primary btn-sm">--%>
              <%--<del>Xuất</del></button>--%>
              <%--</div>--%>
              <div class="form-group">
                <button type="button" class="btn btn-primary btn-sm" ${compareAccount eq false ? 'disabled' : ''}
                        onclick="printElement()">
                  In thường
                </button>
              </div>
              <%--<div class="form-group">--%>
              <%--<button type="button" class="btn btn-primary btn-sm">--%>
              <%--<del>In nhiệt</del></button>--%>
              <%--</div>--%>
              <div class="form-group">
                <!-- data-toggle="modal" data-target="#modalSendEmail" -->
                <button type="button" class="btn btn-primary btn-sm"
                        data-toggle="modal"
                        data-target="#modalSendEmail"><spring:message
                    code="common.btn.send.email"/>
                </button>
              </div>
              <%--<c:if test="${1 == cardSize}">--%>
              <%--<div class="form-group">--%>
              <%--<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"--%>
              <%--data-target="#modalSendSms"><spring:message code="common.btn.send.sms"/>--%>
              <%--</button>--%>
              <%--</div>--%>
              <%--</c:if>--%>
            </div>
            <div class="clr"></div>

            <div class="panel-body tb-tool">
              <div class="">
                <table class="table table-hover table-bordered dataTable table-striped w-full "
                       id="transactionPinCode">
                  <thead>
                  <tr>
                    <th class="hidden">
                      <div class="my-checkBox">
                        <input type="checkbox" name="select_all"
                               id="select_all">
                        <label for="select_all"></label>
                      </div>
                    </th>
                    <th>STT</th>
                    <th class="text-center"><spring:message
                        code="transaction.log.type"/></th>
                    <th class="text-center"><spring:message
                        code="epin.card.value"/></th>
                    <th class="text-center"><spring:message
                        code="epin.card.code"/></th>
                    <th class="text-center"><spring:message
                        code="epin.card.serial"/></th>
                    <th class="text-center"><spring:message
                        code="epin.card.expire.date"/></th>
                  </tr>
                  </thead>

                  <tbody>
                  <c:forEach var="item" items="${transactionList.serials}"
                             varStatus="rowId">
                    <tr>
                      <td class="hidden">
                        <div class="my-checkBox">
                          <input type="checkbox"
                                 name="checkbox${rowId.count}"
                                 id="checkbox${rowId.count}"> <label
                            for="checkbox${rowId.count}"></label>
                        </div>
                      </td>
                      <td><span id="row${rowId.count}" class="rowid">
															<c:out value="${rowId.count}"/>
													</span></td>
                      <td class="text-center">${item.cardType}</td>
                      <td class="text-right vnd">${ewallet:formatNumber(item.price)}</td>
                      <td class="text-center">${ewallet:printRegex(item.pin)}</td>

                      <td class="text-center">${item.serial}</td>
                      <td class="text-right"><fmt:formatDate
                          value="${item.expiredDate}"
                          pattern="dd/MM/yyyy"/></td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="row">
              <%--<div class="col col-sm-4 col-md-3">--%>
              <%--<button type="button" class="btn btn-primary btn-sm">--%>
              <%--<del><spring:message code="epin.card.view.selected.card"/></del></button>--%>
              <%--</div>--%>
              <div class="col-12 pull-right">
                <button type="button" class="btn btn-default btn-sm pull-right"
                        onclick="window.location.href='${dashBoardControlUri}';">
                  <i class="fa fa-arrow-left"></i>
                  <spring:message code="common.btn.back"/></button>

              </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
          </form>

        </div>
      </div>
    </div>
  </div>
</div>
<c:import url="../dialog_modal/pin_code/dialog_sendEmail.jsp"/>
<c:import url="../dialog_modal/pin_code/dialog_sendSms.jsp"/>

<div class="printer">
  <c:choose>
    <c:when
        test="${ transactionsPinCode.serviceCode eq '04010600' || transactionsPinCode.serviceCode eq '04010700' ||
                 transactionsPinCode.serviceCode eq '04010800'|| transactionsPinCode.serviceCode eq '04010900' ||
                 transactionsPinCode.serviceCode eq '04011000' || transactionsPinCode.serviceCode eq '04011100' ||
                 transactionsPinCode.serviceCode eq '040112' || transactionsPinCode.serviceCode eq '040115' ||
                 transactionsPinCode.serviceCode eq '04011900' ||   transactionsPinCode.serviceCode eq '04012000' ||
                 transactionsPinCode.serviceCode eq '04012100' ||  transactionsPinCode.serviceCode eq '040126' }">
      <%--game_template--%>
      <jsp:include page="../include_page/game_card_template.jsp"/>
      <%--game_template--%>
    </c:when>
    <c:otherwise>
      <%--bill_template--%>
      <jsp:include page="../include_page/buy_card_template.jsp"/>
      <%--bill_template--%>
    </c:otherwise>
  </c:choose>

</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->

<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<spring:message code="data.table.header.search" var="search_showing"/>
<spring:message code="data.table.header.paging.records" var="paging_records"/>
<spring:message code="data.table.info.empty" var="info_empty"/>
</body>
<script>
  var $tbl = $('#transactionPinCode');
  var $bodychk = $tbl.find('tbody input:checkbox');

  $bodychk.on('change', function () {
    if ($(this).is(':checked')) {
      $(this).closest('tr').addClass('bgCheckbox');
    } else {
      $(this).closest('tr').removeClass('bgCheckbox');
    }
  });

  //  check all dataTable
  $tbl.find('thead input:checkbox').change(function () {
    var c = this.checked;
    $bodychk.prop('checked', c);
    $bodychk.trigger('change');
  });

  $(document).ready(function () {
    var t = $('#transactionPinCode').DataTable({
      "columnDefs": [{
        "searchable": false,
        "orderable": false,
        "targets": 0
      }],
      "order": [[1, 'asc']],
      "language": {
        "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
        "sLengthMenu": "_MENU_ ${paging_records}",
        "search": "${search_showing}",
        "paginate": {
          "previous": "${paging_previous}",
          "next": "${paging_next}"
        },
        "infoEmpty": "${info_empty}",
        "processing": "<img src='../../assets/images/loading_airline.gif'/>"
      },
    });
  });

  function sendResult(sendType, transaction_id, modalId) {
    var receiver = '${SMS_TYPE}' == sendType ? $('#phone').val() : $('#email').val();
    if ('${SMS_TYPE}' == sendType) {
      var phone = $('#phone');
      receiver = phone.val();
      if (!/^(0|\+\([0-9]{2}\)|\+ \([0-9]{2}\)|\+\([0-9]{2}\) |\+ \([0-9]{2}\) )[0-9]{6,}$/.test(
        receiver)) {
        $.MessageBox({
          message: '<spring:message code="popup.message.invalid.phone"/>',
          buttonDone: '<spring:message code="popup.message.confirm.try.again"/>'
        });

        return;
      }
    } else {
      var email = $('#email');
      receiver = email.val();
      if (!/^[a-zA-Z0-9.]+@[a-zA-Z0-9]+(?:\.[a-zA-Z]+)+$/.test(receiver)) {
        $.MessageBox({
          message: '<spring:message code="popup.message.invalid.email"/>',
          buttonDone: '<spring:message code="popup.message.confirm.try.again"/>'
        });

        return;
      }
    }
    $.ajax({
      type: 'POST',
      url: '/pin-code/send-result/'.concat(sendType),
      beforeSend: function (xhr) {
        if ("${_csrf.parameterName}" && "${_csrf.token}") {
          xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
        }
      },
      data: {
        "${_csrf.parameterName}": "${_csrf.token}",
        transaction_id: transaction_id,
        receiver: receiver
      },
      success: function (data) {
        if (data.code == 0) {
          $.MessageBox({
            message: '<spring:message code="popup.message.confirm.success"/>',
            buttonDone: '<spring:message code="popup.message.confirm.done"/>'
          }).done(function () {
            $('#'.concat(modalId)).modal('hide');
          });
        } else {
          $.MessageBox({
            message: '<spring:message code="popup.message.confirm.fail"/>',
            buttonDone: '<spring:message code="popup.message.confirm.try.again"/>'
          }).done(function () {
            $('#'.concat(modalId)).modal('hide');
          });
        }
      }
    });
    return false;
  }
</script>
</html>
