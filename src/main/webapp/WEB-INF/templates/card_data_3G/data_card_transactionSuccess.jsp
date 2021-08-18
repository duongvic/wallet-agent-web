<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.DASHBOARD_LIST" %>
<%@ page
        import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.SendModeType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="label.game.code"/> - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->

  <link rel="stylesheet" href="<c:url value="/assets/development/css/epin.css"/>">
</head>

<body class="animsition">

<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification -->

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <jsp:param name="nav" value="maTheData"/>
</jsp:include>
<!-- /menu bar -->

<c:url var="dashBoardControlUri" value="<%=DASHBOARD_LIST%>"></c:url>
<c:set var="SMS_TYPE" value="<%=SendModeType.SEND_MODE_SMS.code%>"/>

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><spring:message code="label.game.code.service"/></li>
      <li class="breadcrumb-item"><spring:message code="label.game.code"/></li>
    </ol>
    <h3 class="page-title"><spring:message code="label.game.code"/></h3>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <form class="form-horizontal">
              <div class="row mb-20">
                <div
                    class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                  <i class="icon pe-check badge-success br-100 fs40"></i>
                  <p class="mb-0"><spring:message code="label.transaction.success"/></p>
                  <br>
                </div>
              </div>

              <div class="form-inline pull-right">
                <div class="form-group">
                  <button type="button" class="btn btn-primary btn-sm" onclick="printElement('transactionPinCode')">
                    In thường
                  </button>
                </div>

                <div class="form-group">
                  <!-- data-toggle="modal" data-target="#modalSendEmail" -->
                  <button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                          data-target="#modalSendEmail"><spring:message code="common.btn.send.email"/>
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
                  <table class="table table-hover table-bordered table-striped mb-none dataTable no-footer w-full"
                         id="transactionPinCode">
                    <thead>
                    <tr>
                      <%--<th class="">--%>
                        <%--<div class="my-checkBox">--%>
                          <%--<input type="checkbox" name="select_all" id="select_all">--%>
                          <%--<label for="select_all"></label>--%>
                        <%--</div>--%>
                      <%--</th>--%>
                      <th>STT</th>
                      <th class="text-center"><spring:message code="transaction.table.type"/></th>
                      <th class="text-center"><spring:message code="transaction.table.face.value"/></th>
                      <th class="text-center"><spring:message code="transaction.table.code"/></th>
                      <th class="text-center"><spring:message code="transaction.api.detail.table.serial"/></th>
                      <th class="text-center"><spring:message code="transaction.table.expried.date"/></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="item" items="${transactionsPinCode.serials}" varStatus="rowId">
                      <tr>
                        <%--<td class="">--%>
                          <%--<div class="my-checkBox">--%>
                            <%--<input type="checkbox" name="checkbox${rowId.count}"--%>
                                   <%--id="checkbox${rowId.count}">--%>
                            <%--<label for="checkbox${rowId.count}"></label>--%>
                          <%--</div>--%>
                        <%--</td>--%>
                        <td>
							<span id="row${rowId.count}" class="rowid">
                        		<c:out value="${rowId.count}"/>
                    		</span>
                        </td>
                        <td class="text-left">${tenSP}</td>
                        <td class="text-right currency-input">${item.price}</td>
                        <td class="text-left">${ewallet:printRegex(item.pin)}</td>

                        <td class="text-left">${item.serial}</td>
                        <td class="text-right"><fmt:formatDate value="${item.expiredDate}"
                                                               pattern="dd/MM/yyyy"/></td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="row">
                <div class="col col-sm-4 col-md-3 hidden">
                  <button type="button" class="btn btn-primary">
                    <spring:message code="label.view.card.choose"/>
                  </button>
                </div>
                <div class="col col-sm-12 col-md-12">
                  <button type="button" class="btn btn-primary pull-right"
                          onclick="window.location.href='${dashBoardControlUri}';"><spring:message
                      code="common.btn.other.transactions"/>
                  </button>

                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import url="../include_component/frame_information_transaction_pincode_datacard.jsp"/>
        <!-- /Giao dịch gần nhất  -->
      </div>
    </div>
  </div>
</div>
<c:import url="../dialog_modal/pin_code/dialog_sendEmail.jsp"/>
<c:import url="../dialog_modal/pin_code/dialog_sendSms.jsp"/>

<div class="printer">
  <%--bill_template--%>
  <jsp:include page="../include_page/buy_card_template.jsp"/>
  <%--bill_template--%>
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

</body>
<script>

  var $tbl = $('#transactionPinCode');
  var $bodychk = $tbl.find('tbody input:checkbox');

  $bodychk.on('change', function () {
    if ($(this).is(':checked')) {
      $(this).closest('tr').addClass('bgCheckbox');
    }
    else {
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
      responsive: true,
      "searching": true,
      "bLengthChange": false,
      "searching": false,
      "order": [[1, 'desc']],
      "language": {
        "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
        "sLengthMenu": "_MENU_ ${paging_records}",
        "paginate": {
          "previous": "${paging_previous}",
          "next": "${paging_next}"
        }
      },
    });
    new $.fn.dataTable.FixedHeader(t);
  });

  function sendResult(sendType, transaction_id, modalId) {
    var receiver = '${SMS_TYPE}' == sendType ? $('#phone').val() : $('#email').val();
    if('${SMS_TYPE}' == sendType) {
      var phone = $('#phone');
      receiver = phone.val();
      if(!/^(0|\+\([0-9]{2}\)|\+ \([0-9]{2}\)|\+\([0-9]{2}\) |\+ \([0-9]{2}\) )[0-9]{6,}$/.test(receiver)) {
        $.MessageBox({
          message: '<spring:message code="popup.message.invalid.phone"/>',
          buttonDone: '<spring:message code="popup.message.confirm.try.again"/>'
        });

        return;
      }
    } else {
      var email = $('#email');
      receiver = email.val();
      if(!/^[a-zA-Z0-9.]+@[a-zA-Z0-9]+(?:\.[a-zA-Z]+)+$/.test(receiver)) {
        $.MessageBox({
          message: '<spring:message code="popup.message.invalid.email"/>',
          buttonDone: '<spring:message code="popup.message.confirm.try.again"/>'
        });

        return;
      }
    }
    $.ajax({
      type: 'POST',
      url: '/game-code/send-result/'.concat(sendType),
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
          }).done(function() {
            $('#'.concat(modalId)).modal('hide');
          });
        } else {
          $.MessageBox({
            message: '<spring:message code="popup.message.confirm.fail"/>',
            buttonDone: '<spring:message code="popup.message.confirm.try.again"/>'
          }).done(function() {
            $('#'.concat(modalId)).modal('hide');
          });
        }
      }
    });
    return false;
  };

</script>
</html>