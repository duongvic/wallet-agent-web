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
    <title><spring:message code="fundorder.request.detail.window.title"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/development/css/custom_dropify.css"/>" media="none" onload="if(media!='all')media='all'">
    <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

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
                    code="fundorder.management.request.detail.breadcrumb"/></li>
            <li class="breadcrumb-item active"><spring:message
                    code="fundorder.fundout.fundout.breadcrumb"/></li>
            <li class="breadcrumb-item">${fund_out_method_label}</li>
            <li class="breadcrumb-item active">
                <%--<a href="/fundout/cash-on-hand/management/detail/${request_id}">--%>
                <spring:message code="fundorder.cash.on.hand.request.detail.breadcrumb"/>
                <%--</a>--%>
            </li>
        </ol>
        <h1 class="page-title"><spring:message
                code="fundorder.request.detail.page.title"/>&nbsp;${request_id}</h1>
    </div>
    <div class="page-content container-fluid">
        <form method="post" enctype="multipart/form-data" id="mainform"
              action="/fundout/${fund_out_method}/management/detail/${requestId}/edit-submit">
            <div class="panel mb-0 panel-bordered">
                <div class="panel-body">
                    <!-- Account -->
                    <div class="form-group row mb-5">
                        <label class="col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.edit.amount"/></label>
                        <div class="col-sm-4 ">
                            <input type="text" class="form-control currency-input" name="amount"
                                   id="amount"
                                   value="${(amount != null && amount ne '') ? amount : '500000'}"
                                   placeholder="<spring:message code="fundout.money"/>"
                                   required/>
                        </div>
                    </div>
                    <div class="form-group row mb-5">
                        <label class="col-sm-3 col-form-label pb-0"></label>
                        <div class="col-sm-4 ">
                            <div id="mobilefundout" class="price-radio mt-5 item-responsive">
                                <div class="radio-custom radio-info radio-inline text-center">
                                    <input type="radio" id="sdtt1" name="numberdt2"
                                           value="500000" ${amount eq '500000' || amount == null || amount eq '' ? 'checked' : ''}
                                           onclick="getSoTienRut(this)">
                                    <label for="sdtt1"><span>500.000 đ</span></label>
                                </div>
                                <div class="radio-custom radio-info radio-inline text-center">
                                    <input type="radio" id="sdtt2" name="numberdt2"
                                           value="1000000" ${amount eq '1000000' ? 'checked' : ''}
                                           onclick="getSoTienRut(this)">
                                    <label for="sdtt2"><span>1000.000 đ</span></label>
                                </div>
                                <div class="radio-custom radio-info radio-inline text-center">
                                    <input type="radio" id="sdtt3" name="numberdt2"
                                           value="2000000" ${amount eq '2000000' ? 'checked' : ''}
                                           onclick="getSoTienRut(this)">
                                    <label for="sdtt3"><span>2000.000 đ</span></label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <c:if test="${'cash-on-hand' eq fund_out_method}">
                    <!-- Fee -->
                    <div class="form-group row mb-5">
                        <label class="col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.edit.fee"/></label>
                        <label class="col-sm-4 txt-note-pin"><span
                                id="feeAmount">0 đ</span></label>
                        <input type="hidden" name="feeAmount" id="hidden_feeAmount"
                               value="${feeAmount}">
                    </div>

                    <!-- Total -->
                    <div class="form-group row mb-5">
                        <label class="col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.edit.total"/></label>
                        <div class="col-sm-4">
                            <label><b class="text-amount" id="realAmount"></b></label>
                            <input type="hidden" id="hidden_realAmount" name="realAmount"
                                   value="${realAmount}">
                        </div>
                    </div>
                </c:if>

                <c:if test="${'bank-transfer' eq fund_out_method}">
                    <!-- Order Id -->
                    <div class="form-group row mb-5">
                        <label class="col-sm-3 col-form-label pb-0"><spring:message
                                code="account.bank.trans.id"/></label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="command_code"
                                id="command_code"
                                value="${command_code}"
                                placeholder="<spring:message code="account.bank.trans.id"/>"
                                required/>
                            <input type="hidden" name="bank_code" value="${bank_code}"/>
                        </div>
                    </div>
                </c:if>
                </div>

                <div class="panel-body">
                    <!-- Confirm request -->
                    <div class="form-group row mb-5">
                        <spring:message
                                code="fundorder.request.detail.confirm.receipt.image.tooltip"
                                var="receipt_image_tooltip"/>
                        <label class="col-sm-3 col-form-label pb-0">
                            <spring:message code="fundorder.request.detail.edit.request.confirm"/>
                            &nbsp;<i class="fa  fa-info-circle"
                                     data-placement="top" data-toggle="tooltip"
                                     data-original-title="${receipt_image_tooltip}"></i></label>
                        <div class="col-sm-2 mx-0">
                            <input type="file" name="fileUpload" id="confirm_image"
                                   class="dropify"
                                   data-max-file-size="5M" data-show-remove="true"
                                   data-allowed-file-extensions="PNG JPE JPEG JPG png jpe jpeg jpg">
                            <br/>
                            <c:forEach var="item" items="${attachments}">
                                <spring:message text="${item.content}" var="attachment_content"/>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="panel-body">
                    <!-- Remark -->
                    <div class="form-group row mb-5">
                        <label class="col-sm-3 col-form-label pb-0"><spring:message
                                code="fundorder.request.detail.edit.remark"/></label>
                        <div class="col-sm-4">
                        <textarea class="form-control" rows="5" id="remark" name="remark">${remark}</textarea>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="panel-body">
                    <div class="form-group row mb-5">
                        <label class="col-sm-3 col-form-label pb-0"></label>
                        <div class="col-sm-4">
                            <button class="btn btn-primary btn-sm pull-right"><spring:message
                                    code="label.button.title.save"/></button>
                            <a class="btn btn-primary btn-sm pull-right mr-2"
                               href="/fundout/${fund_out_method}/management/detail/${request_id}"><spring:message
                                    code="label.button.title.back"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<c:import url="../include_page/dropify.jsp"/>
<!-- /footer -->
</body>

<script type="text/javascript">
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
      thanhToan();

      $('.dropify-wrapper').addClass('upload-image')

    });

  })(document, window, jQuery);

  function getSoTienRut(elem) {
    var x = elem.value;
    document.getElementById('amount').value = formatCurrency(x);
    thanhToan();
  }

  function thanhToan() {
    var realAmount = 0;
    var amount = 0;
    if (document.getElementById('amount').value === null) {
      document.getElementById('amount').value = 0;
    }
    amount = parseFloat(currencyToNumber(document.getElementById('amount').value));
    var feeAmount = parseFloat(currencyToNumber(document.getElementById('feeAmount').innerText));
    if (feeAmount > amount) {
      realAmount = 0;
    }
    else {
      realAmount = parseFloat(amount) + parseFloat(feeAmount);
    }

    document.getElementById('realAmount').innerText = formatCurrency(realAmount);
    //get value
    document.getElementById('hidden_realAmount').value = realAmount;
    document.getElementById('hidden_feeAmount').value = feeAmount;
  }

  jQuery(window).on("load", function () {
    jQuery('.dropify-render').html(""
        + "<img alt=\"${item.name}\"\n"
        + "src=\"data:image/png;base64, ${attachment_content}\">");
    jQuery('.dropify-wrapper').addClass("has-preview");
    jQuery('.dropify-loader').prop("style", "display: none;");
    jQuery('.dropify-preview').prop("style", "display: block;");
  });
</script>

</html>
