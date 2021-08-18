<%@ page import="vn.mog.ewallet.consumer.web.controller.epo.BatchCardsController" %>
<%@ page import="vn.mog.ewallet.consumer.web.controller.epo.beans.EpinPurchaseOrderFlowStage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="batchCardsMenu"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><a href="#"><spring:message
                    code="label.manage"/></a></li>
            <li class="breadcrumb-item active"><a href="#"><spring:message
                    code="menu.batch.cards"/></a></li>
            <li class="breadcrumb-item"><a href="#"><spring:message
                    code="label.detail.po"/></a></li>
        </ol>
        <h1 class="page-title"><spring:message code="menu.batch.cards"/></h1>
    </div>
    <div class="page-content container-fluid">
        <div class="panel mb-0 panel-bordered">

            <c:url var="epinPoConUri" value="<%=BatchCardsController.BATCH_CARDS_CONTROLLER%>"/>
            <form action="${epinConURI}/epin-checkorder" method="post">
                <div class="panel-body tb-tool">
                    <div class="form-group row mb-10">
                        <div class="full-width">
                            <h4 class="panel-title pl-0"><spring:message
                                    code="label.po"/>&nbsp;${merchantPO.poCode}</h4>
                            <div class="clr"></div>
                        </div>
                    </div>

                    <input type="hidden" name="totalTelco" value="${listPOdetail.size()}"/>
                    <input type="hidden" name="poCode" value="${poCode}"/>


                    <section class="panel panel-default po-detail-line" style="box-shadow: none">
                        <div class="panel-body">
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row  mt-20">

                                <label class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label"><spring:message
                                        code="label.code.po"/> </label>
                                <p class="col-7	col-sm-5 col-md-5 col-lg-5 col-xl-5  form-control-label primary_color"
                                   align="right">${merchantPO.poCode}</p>


                                <label class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label"><spring:message
                                        code="label.merchantName"/> </label>
                                <p class="col-7	col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label primary_color"
                                   align="right">${merchantPO.merchantName}</p>


                                <label class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label"><spring:message
                                        code="label.phone.number"/> </label>
                                <p class="col-7	col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label primary_color"
                                   align="right">${merchantPO.keyHolder}</p>


                                <label class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label"><spring:message
                                        code="service.exportcard.confirm.summary.totalmoney"/> </label>
                                <p class="col-7	col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label secondary_color"
                                   align="right">${ewallet:formatNumber(merchantPO.totalValue)}&nbsp;(VND)</p>


                                <label class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label"> <spring:message
                                        code="service.exportcard.confirm.summary.quantity"/> </label>
                                <p class="col-7	col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label primary_color"
                                   align="right">${ewallet:formatNumber(merchantPO.totalQuantity)}</p>


                                <label class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label"> <spring:message
                                        code="label.actual.value"/> </label>
                                <p class="col-7	col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label primary_color"
                                   align="right">${ewallet:formatNumber(merchantPO.totalValue - merchantPO.totalCommission)}&nbsp;(VND)</p>

                                <label class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label"> <spring:message
                                        code="label.create.time"/> </label>
                                <p class="col-7	col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label primary_color" align="right">
                                    <fmt:formatDate
                                            pattern="HH:mm dd/MM/yyyy"
                                            value="${merchantPO.createdTime}"/></p>

                                <label class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label"> <spring:message
                                        code="service.exportcard.detail.info.status"/> </label>
                                <p class="col-7	col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label primary_color" align="right">
                                    <spring:message
                                            code="exportcard.api.status.${merchantPO.status}"/></p>

                                <label class="col-5 col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label"><spring:message
                                        code="service.exportcard.detail.info.remark"/></label>
                                <p class="col-7	col-sm-5 col-md-5 col-lg-5 col-xl-5 form-control-label primary_color"
                                   align="right">${merchantPO.info}</p>

                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                <c:set var="epinStageInit"
                                       value="<%=EpinPurchaseOrderFlowStage.INIT.code%>"/>
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
                                <button type="button" id="btn-back"
                                        class="btn btn-default col-lg-2 col-md-2 col-sm-3 mb-10">
                                    <i class="fa fa-arrow-left"></i>&nbsp;<spring:message
                                        code="common.btn.back"/>
                                </button>

                                <c:if test="${epinStageExportAllowed == merchantPO.stage}">
                                    <button type="button" id="btn-approve"
                                            class="btn btn-primary col-lg-2 col-md-2 col-sm-3 mb-10">
                                        <i class="fa fa-save"></i>&nbsp;<spring:message
                                            code="common.btn.confirm"/>
                                    </button>
                                </c:if>

                                <c:if test="${epinStageFinished == merchantPO.stage}">
                                    <button type="button" id="link-export"
                                            class="btn btn-primary col-lg-2 col-md-2 col-sm-3 mb-10">
                                        <i class="fa fa-download"></i>&nbsp;<spring:message
                                            code="common.btn.download.file"/>
                                    </button>

                                    <button type="button" id="resend-pass"
                                            class="btn btn-primary col-lg-3 col-md-2 col-sm-3 mb-10">
                                        <i class="fa fa-refresh"></i>&nbsp;<spring:message
                                            code="common.btn.resend.password"/>
                                    </button>
                                </c:if>

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
                                        <%--<th class="text-center"><spring:message--%>
                                        <%--code="service.exportcard.create.table.column.status"/></th>--%>
                                        <%--<th class="text-center"><spring:message--%>
                                        <%--code="service.exportcard.create.table.column.action"/></th>--%>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <spring:message code="service.exportcard.create.card.available"
                                                    var="langAvailable"/>
                                    <c:forEach var="item"
                                               items="${merchantPO.purchaseOrderDetails }"
                                               varStatus="varStatus">
                                        <tr>
                                            <td class="stt">${varStatus.count}</td>
                                            <td>${item.cardType }</td>
                                            <td class="text-right">${ewallet:formatNumber(item.faceValue)}</td>
                                            <td class="text-right">${ewallet:formatNumber(item.quantity)}</td>
                                            <td class="text-right">${ewallet:formatNumber(item.faceValue * item.quantity)}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </section>
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
  $(document).ready(function () {

    $('button#btn-back').click(function () {
      window.location.href = '<%=BatchCardsController.BATCH_CARDS_LIST%>';
    });

    $('button#btn-approve').click(function () {
      var id = "${poMerchantId}";
      var urlEpin = '/batch-cards/get-otp?id=' + id;
      window.location.href = urlEpin;
    });

    $('#link-export').click(function () {
      var id = "${poMerchantId}";
      $.MessageBox({
        buttonDone: '<spring:message code="popup.button.yes"/>',
        buttonFail: '<spring:message code="popup.button.no"/>',
        message: '<spring:message code="popup.message.confirm.download.file"/>'
      }).done(function () {
        var urlEpin = '/batch-cards/export-epin?poMerchantId=' + id;
        $.fileDownload(urlEpin)
        .done(function () {
          $.MessageBox({message: '<spring:message code="common.file.download.success"/>'});
        })
        .fail(function () {
          $.MessageBox({message: '<spring:message code="common.file.download.fail"/>'});
        });
      });
      return false;
    });

    $('#resend-pass').click(function () {
      var id = "${poMerchantId}";
      if (id != null && id != '') {
        $.MessageBox({
          buttonDone: '<spring:message code="popup.button.yes"/>',
          buttonFail: '<spring:message code="popup.button.no"/>',
          message: '<spring:message code="popup.message.confirm.reset.pass"/>'
        }).done(function () {
          $.ajax({
            type: 'GET',
            url: 'resend-pass',
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
  });


</script>
</html>