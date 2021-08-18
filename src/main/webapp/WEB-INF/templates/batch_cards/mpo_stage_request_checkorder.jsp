<%@ page import="vn.mog.ewallet.consumer.web.controller.epo.BatchCardsController" %>
<%@ page
        import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrder.StoreType" %>
<%@ page import="static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.DASHBOARD_TYPE_API" %>
<%@ page import="static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.DASHBOARD_TYPE_EXPORT" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.AbstractController.DASHBOARD_TYPE_API_N02" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
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
    <%--batch cards--%>
    <link rel="stylesheet" href="<c:url value="/assets/development/css/epin.css"/>">

    <title><spring:message code="menu.batch.cards"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <!-- /head libs  -->

    <c:set var="typeAPI" value="<%=DASHBOARD_TYPE_API%>"/>
    <c:set var="typeExport" value="<%=DASHBOARD_TYPE_EXPORT%>"/>
    <c:set var="typeAPIN02" value="<%=DASHBOARD_TYPE_API_N02%>"/>
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
<c:url var="epinConURI" value="<%=BatchCardsController.BATCH_CARDS_CONTROLLER%>"/>

<div class="page">

    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="label.manage"/></li>
            <li class="breadcrumb-item active"><spring:message
                    code="${page_title}"/></li>
            <li class="breadcrumb-item"><spring:message
                    code="label.send.request"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="${page_title}"/></h1>
    </div>
    <div class="page-content container-fluid">
        <%--<form action="/batch-cards" method="post">--%>
        <div class="panel mb-0 panel-bordered">

            <form name="telco" id="telco" action="epin-checkorder" method="post">

                <input type="hidden" name="totalTelco" value="${listPOdetail.size()}"/>
                <input type="hidden" name="poCode" value="${poCode}"/>

                <div class="panel-body tb-tool">
                    <div class="form-group row mb-10">
                        <c:if test="${null ne mesErr}">
                            <h4 class="mb-xs mt-sm secondary_color">${mesErr}</h4>
                            <div class="clr"></div>
                        </c:if>
                        <div class="full-width">
                            <h4 class="panel-title pl-0"><spring:message
                                    code="label.confirm.po"/></h4>
                            <div class="clr"></div>
                        </div>
                    </div>

                    <section class="panel panel-default po-detail-line" style="box-shadow: none">

                        <div class="panel-body">
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row  mt-20">

                                <label class="col-5 form-control-label"><spring:message
                                        code="service.exportcard.confirm.summary.totalmoney"/> </label>
                                <p class="col-5 form-control-label ${currentpay > 0 ? 'primary_color' : 'secondary_color'}"
                                   align="right"><span
                                        id="totalMoney">${ewallet:formatNumber(totalMoney)}</span>&nbsp;(VND)
                                </p></p>

                                <label class="col-5 form-control-label"> <spring:message
                                        code="service.exportcard.confirm.summary.quantity"/> </label>
                                <p class="col-5 form-control-label primary_color"
                                   align="right"><span
                                        id="totalQuantity">${ewallet:formatNumber(totalQuantity)}</span>
                                </p>


                                <label class="col-5 form-control-label"> <spring:message
                                        code="service.exportcard.confirm.summary.commission"/> </label>
                                <p class="col-5 form-control-label primary_color total-am"
                                   align="right"><span
                                        id="totalCommmision">${(totalCommmision != null && totalCommmision ne '') ? ewallet:formatNumber(totalCommmision) : 0}</span>&nbsp;(VND)
                                </p>

                                <label class="col-5 form-control-label"> <spring:message
                                        code="service.exportcard.confirm.summary.totalpay"/> </label>
                                <p class="col-5 form-control-label primary_color"
                                   align="right"><span
                                        id="totalPayable"> ${ewallet:formatNumber(totalPayable)}</span>&nbsp;(VND)
                                </p>


                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-bordered table-striped mb-none">
                                    <thead>
                                    <tr>
                                        <th><spring:message
                                                code="service.exportcard.create.table.column.no"/></th>
                                        <th style="width:20%"><spring:message
                                                code="service.exportcard.create.table.column.cardtype"/></th>
                                        <th class="text-right" style="width: 20%"><spring:message
                                                code="service.exportcard.create.table.column.facevalue"/></th>
                                        <th class="text-right" style="width: 20%"><spring:message
                                                code="service.exportcard.create.table.column.quantity"/></th>
                                        <th class="text-center"><spring:message
                                                code="service.exportcard.create.table.column.total.amount"/></th>
                                        <th class="text-center"><spring:message
                                                code="service.exportcard.create.table.column.action"/></th>
                                        <%--<th class="text-center"><spring:message--%>
                                        <%--code="service.exportcard.create.table.column.status"/></th>--%>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%--<spring:message code="service.exportcard.create.card.available"--%>
                                    <%--var="langAvailable"/>--%>
                                    <c:forEach var="item" items="${listPOdetail}"
                                               varStatus="rowId">
                                        <%--<tr class="${item.cardType }">--%>
                                        <%--<input type="hidden" name="telco_${varStatus.index}"--%>
                                        <%--value="${item.cardType }"/>--%>
                                        <%--<input type="hidden" name="value_${varStatus.index}"--%>
                                        <%--value="${item.faceValue }"/>--%>
                                        <%--<input type="hidden" name="quantity_${varStatus.index}"--%>
                                        <%--value="${item.quantity }"/>--%>
                                        <%--<input type="hidden" name="status_${varStatus.index}"--%>
                                        <%--value="${item.status }"/>--%>
                                        <%--<td class="stt">${varStatus.index}</td>--%>
                                        <%--<td>${item.cardType }</td>--%>
                                        <%--<td class="text-right">${ewallet:formatNumber(item.faceValue)}</td>--%>
                                        <%--<td class="text-right">${ewallet:formatNumber(item.quantity)}</td>--%>
                                        <%--<td class="text-right">${ewallet:formatNumber(item.faceValue * item.quantity)}</td>--%>
                                        <%--&lt;%&ndash;<td class="text-center ${item.status eq langAvailable ? 'primary_color' : 'secondary_color' }">${item.status }</td>&ndash;%&gt;--%>
                                        <%--<td class="action_icon text-center">--%>
                                        <%--<a href="javascript:;" style="color: red;"><i class="fa fa-times"></i></a>--%>
                                        <%--</td>--%>
                                        <%--</tr>--%>
                                        <tr>

                                                <%--<input type="hidden" name="status_${varStatus.index}"--%>
                                                <%--value="${item.status }"/>--%>

                                            <td class="stt">${rowId.count}</td>
                                            <td>${item.cardType} <input type="hidden"
                                                                        name="telco_${rowId.count}"
                                                                        value="${item.cardType }"/>
                                            </td>
                                            <td class="text-right">${ewallet:formatNumber(item.faceValue)}
                                                <input type="hidden" name="value_${rowId.count}"
                                                       value="${item.faceValue }"/></td>
                                            <td class="text-right">${ewallet:formatNumber(item.quantity)}
                                                <input type="hidden" name="quantity_${rowId.count}"
                                                       value="${item.quantity }"/></td>
                                            <td class="text-right">${ewallet:formatNumber(item.faceValue * item.quantity)}
                                                <input type="hidden" name="amount_${rowId.count}"
                                                       value="${ewallet:formatNumber(item.faceValue * item.quantity)}"/>
                                            </td>
                                                <%--<td class="text-center ${item.status eq langAvailable ? 'primary_color' : 'secondary_color' } hidden">${item.status }</td>--%>
                                            <td class="action_icon text-center">
                                                <a href="javascript:void(0);" style="color: red;"><i
                                                        class="fa fa-times"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </section>
                    <div class="row content-body-wrap">
                        <div class="col-md-12 col-lg-12 col-xl-12 text-right">

                            <div class="panel-body form-group">
                                <spring:message code="service.exportcard.otp.waiting"
                                                var="waiting"/>

                                <input type="hidden" name="action" value=""/>
                                <input type="hidden" name="pageRequest" value="${pageRequest}"/>
                                <input type="hidden" name="poMerchantId" value="${poMerchantId }"/>

                                <button type="button" value="previous" id="stepprevious"
                                        class="btn btn-default">
                                    <i class="fa fa-arrow-left"></i>&nbsp;<spring:message
                                        code="common.btn.back"/>
                                </button>

                                <button type="submit" value="save"
                                        class="btn btn-primary btn-sm mb-xs mt-xs" ${disabledNext}
                                        style="${disabledNext eq 'disabled' ? 'opacity: 0.5;' : ''}"
                                        data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waiting}">
                                    <i class="fa fa-save"></i>&nbsp;<spring:message
                                        code="common.btn.save"/>
                                </button>

                                <button type="submit" value="next"
                                        class="btn btn-primary" ${disabledNext}
                                        style="${disabledNext eq 'disabled' ? 'opacity: 0.5;' : ''}"
                                        data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waiting}">
                                    <spring:message code="common.btn.request"/>&nbsp;<i
                                        class="fa fa-arrow-right"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>


        </div>

        <%--</form>--%>
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
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
<c:import url="../include_page/js_daterangepicker.jsp"/>

</body>
<script type="text/javascript">

  /*add-tel-po*/
  function formatNumberSeparator(x, locale) {
    var locale = "vi";
    var separator = ",";
    if (locale == "vi") {
      separator = ".";
    }
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, separator);
  }

  $(document).ready(function () {
    $('form[name="telco"] button:submit').click(function () {

      // var value = $("#telco").context.activeElement.value;
      <%--if (value === "previous") {--%>
      <%--} else {--%>
      <%--if (!$('#checkboxExample3').is(':checked')) {--%>
      <%--$.MessageBox({message: '<spring:message code="popup.message.confirm.export.card"/>'});--%>
      <%--return false;--%>
      <%--}--%>
      <%--}--%>

      $('form[name="telco"] input[name=action]').val($(this).val());

      if (value === 'save') {
        $(this).button('loading');
      } else if (value === 'next') {
        <c:if test="${disabledNext eq '' or disabledNext eq null}">
        $(this).button('loading');
        </c:if>
      }

    });

    $("#stepprevious").click(function () {
      var param = "";
      var pageRequest = '${pageRequest}';
      if (pageRequest == 'edit') {
        param = "?id=${poMerchantId}";
      }
      $('#telco').attr(
          'action', 'update-request'
          + param);
      $('form[name=telco]').submit();
    });

    /*Remove row*/
    $(document).on('click', 'td.action_icon a', function () {
      $(this).closest('tr').remove();
      $('form[name=telco] input[name=totalTelco]').val(renameTable());
      if ($('input[name=totalTelco]').val() == 0) {
        $('form[name="telco"] button:submit').prop('disabled', true);
      }
    });

    function renameTable() {
      var istt = 0;
      var totalQuatity = 0;
      var totalMoney = 0;
      var commmision = 0;
      $('.po-detail-line table tbody tr').each(function (index, value) {
        istt = index + 1;
        $(value).find('td.stt').html(istt);
        $(value).find('input[name*=value_]').attr('name', 'value_' + istt);
        $(value).find('input[name*=telco_]').attr('name', 'telco_' + istt);
        $(value).find('input[name*=quantity_]').attr('name', 'quantity_' + istt);
        $(value).find('input[name*=status_]').attr('name', 'status_' + istt);

        var quantity = parseInt($(value).find('input[name*=quantity_]').val());
        var money = parseInt($(value).find('input[name*=value_]').val()) * quantity;
        totalQuatity += parseInt($(value).find('input[name*=quantity_]').val());
        totalMoney += money;
      });
      $("#totalQuantity").html(formatNumberSeparator(totalQuatity, "${locale}"));
      $("#totalMoney").html(formatNumberSeparator(totalMoney, "${locale}"));
      $("#totalPayable").html(formatNumberSeparator(totalMoney - commmision, "${locale}"));

//      var d = new Date();
//      var month = d.getMonth() + 1;
//      var textMoney = totalMoney / 1000;
//      var pocode = "" + d.getFullYear() + month + d.getDate()
//          + d.getHours() + d.getMinutes() + '-' + totalQuatity + '-' + textMoney;
//      $("b.b-pocode").html(pocode);
//      $("input[name=poCode]").val(pocode);
//      $("input[name=totalMoney]").val(totalMoney);

      return istt;
    }
  });
</script>
</html>