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
  <title><spring:message code="menu.fundin"/></title>
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

<c:if test="${'cash-on-hand' eq fund_in_method}">
  <spring:message code="fundin.cash.on.hand.request" var="fund_in_method_label"/>
</c:if>
<c:if test="${'bank-transfer' eq fund_in_method}">
  <spring:message code="fundin.list.bankTransfer.request" var="fund_in_method_label"/>
</c:if>

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/fundin"><spring:message code="label.fundin"/></a></li>
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
              <form class="form-horizontal" method="post" enctype="multipart/form-data"
                    action="/fundin/${fund_in_method}/create">

                <div class="form-group row mb-10 pos-relative">
                  <div class="col-md-1 col-lg-1"></div>
                  <div class="form-group row col-11 px-0">
                    <div class="col-md-3 col-lg-3">
                      <label><spring:message code="fundorder.request.detail.edit.amount"/></label>
                    </div>
                    <div class="col-md-8 col-lg-8">
                      <input type="text" class="form-control currency-input" name="amount"
                             id="amount"
                             value="${(amount != null && amount ne '') ? amount : '100000'}"
                             placeholder="<spring:message code="fundin.money"/>"
                             required/>
                      <div class="price-radio mt-5">
                        <div class="radio-custom radio-info radio-inline text-center">
                          <input type="radio" id="sdtt1" name="numberdt2"
                                 value="100000" ${amount eq '100000' || amount == null || amount eq '' ? 'checked' : ''}
                                 onclick="getSoTienRut(this)">
                          <label for="sdtt1"><span>100.000 </span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center">
                          <input type="radio" id="sdtt2" name="numberdt2"
                                 value="200000" ${amount eq '200000' ? 'checked' : ''}
                                 onclick="getSoTienRut(this)">
                          <label for="sdtt2"><span>200.000 </span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center">
                          <input type="radio" id="sdtt3" name="numberdt2"
                                 value="500000" ${amount eq '500000' ? 'checked' : ''}
                                 onclick="getSoTienRut(this)">
                          <label for="sdtt3"><span>500.000 </span></label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <c:if test="${'cash-on-hand' eq fund_in_method}">
                  <div class="form-group row mb-0">
                    <div class="col-md-1 col-lg-1"></div>
                    <div class="form-row col">
                      <div class="col col-md-3 col-lg-3">
                        <label class=""><span
                            for="feeAmount"><spring:message code="label.fee.fundin"/></span></label>
                      </div>
                      <div class="col col-md-7 col-lg-7">
                        <label class="px-0  txt-note-pin"><span
                            id="feeAmount">0 Zpoint</span></label>
                        <input type="hidden" name="feeAmount" id="hidden_feeAmount"
                               value="${feeAmount}">
                      </div>
                    </div>
                  </div>
                  <div class="form-group row mb-0">
                    <div class="col-md-1 col-lg-1"></div>
                    <div class="form-row col">
                      <div class="col col-md-3 col-lg-3">
                        <label class="form-control-label px-0"><spring:message code="label.point"/>
                        </label>
                      </div>
                      <div class="col col-md-7 col-lg-7">
                        <label><b class="text-amount" id="realAmount"></b></label>
                        <input type="hidden" id="hidden_realAmount" name="realAmount"
                               value="${realAmount}">
                      </div>
                    </div>
                  </div>
                </c:if>
                <c:if test="${'bank-transfer' eq fund_in_method}">
                  <div class="form-group row mb-0 hidden">
                    <div class="col-md-1 col-lg-1"></div>
                    <div class="form-row col">
                      <div class="col col-md-3 col-lg-3">
                        <label class="form-control-label px-0"><spring:message code="account.bank.trans.id"/>
                        </label>
                      </div>
                      <div class="col col-md-7 col-lg-7">
                        <input type="text" class="form-control" name="command_code"
                               id="command-code"
                               value="${command_code}">
                        <input type="hidden" name="bank_code" value="${bank_code}">
                      </div>
                    </div>
                  </div>
                </c:if>

                <%--comment image--%>
                <%--<div class="form-group row mb-10">--%>
                  <%--<div class="col-md-1 col-lg-1"></div>--%>
                  <%--<div class="form-group row col-11">--%>

                      <%--<spring:message code="fundorder.request.detail.confirm.receipt.image.tooltip"--%>
                                      <%--var="receipt_image_tooltip"/>--%>
                      <%--<label class="col-md-3 col-lg-3">--%>
                        <%--<spring:message code="fundorder.request.detail.edit.request.confirm"/>--%>
                        <%--&nbsp;<i class="fa  fa-info-circle"--%>
                                 <%--data-placement="top" data-toggle="tooltip"--%>
                                 <%--data-original-title="${receipt_image_tooltip}"></i></label>--%>

                    <%--<div class="form-group row mb-0 col-md-8 px-0">--%>

                        <%--<div class="col-md-6 col-lg-6">--%>
                          <%--<input type="file" name="fileUpload" id="confirm_image"--%>
                                 <%--class="dropify" required--%>
                                 <%--data-max-file-size="5M" data-show-remove="true"--%>
                                 <%--data-allowed-file-extensions="PNG JPE JPEG JPG png jpe jpeg jpg">--%>
                        <%--</div>--%>
                        <%--<div class="col-md-6 col-lg-6">--%>
                          <%--<p>Chỉ chấp nhận các định dạng PNG, JPE, JPEG, JPG, tối đa <strong--%>
                              <%--class="text-amount">5mb </strong></p>--%>
                          <%--<p>Chỉ được phép tải lên 1 ảnh</p>--%>
                        <%--</div>--%>

                    <%--</div>--%>
                  <%--</div>--%>
                <%--</div>--%>
                <%--end comment image--%>

                <!-- Remark -->
                <div class="form-group row mb-5">
                  <div class="col-md-1 col-lg-1"></div>
                  <div class="form-group row col-11">
                  <label class="col-sm-3 pb-0"><spring:message
                      code="fundorder.request.detail.edit.remark"/></label>
                  <div class="col-md-8 col-lg-8">
                        <textarea class="form-control" rows="5" id="remark" name="remark"></textarea>
                  </div>
                  </div>
                </div>


                <%--<div class="form-group row mt-5">--%>
                  <%--<div class="col-md-1 col-lg-1"></div>--%>
                  <%--<div class="col-md-3 col-lg-3">--%>
                    <%--<label class="form-control-label px-0"></label>--%>
                  <%--</div>--%>
                  <%--<div class="col-md-7 col-lg-7 fileshow"></div>--%>
                <%--</div>--%>

                <div class="form-group row mt-5 mb-10">
                  <div class="col-md-1 col-lg-1"></div>
                  <div class="col-md-10 col-lg-10 text-right px-0">
                    <c:if test="${'cash-on-hand' eq fund_in_method}">
                      <a href="/fundin/${fund_in_method}/management"
                         class="btn btn-default mr-10"><spring:message
                          code="common.btn.back"/></a>
                    </c:if>
                    <c:if test="${'bank-transfer' eq fund_in_method}">
                      <a href="/fundin/bank-transfer/create/info"
                         class="btn btn-default mr-10"><spring:message
                          code="common.btn.back"/></a>
                    </c:if>
                    <button type="submit" class="btn btn-primary btn-sm mr-10">
                      <spring:message code="label.fundin"/>
                    </button>
                  </div>
                </div>
                <%--<input type="hidden" name="orderId" value="${orderId}"/>--%>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              </form>
            </div>
          </div>
        </div>
        <div class="col-md-5">
          <!-- Giao dịch gần nhất -->
          <c:import url="../include_component/frame_fundout_last_transaction.jsp"/>
          <!-- /Giao dịch gần nhất  -->
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
        $(".currency-input").val(formatCurrency(currencyToNumber($(".currency-input").val())));
        thanhToan();

        $('.dropify-wrapper').addClass('upload-image')

      });

    })(document, window, jQuery);

    function getSoTaiKhoan(elem) {
      var x = elem.value;
      document.getElementById('_soTaiKhoan').value = x;
    }

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

      document.getElementById('realAmount').innerText = formatCurrency(realAmount).concat(' ').concat('Zpoint');
      //get value
      document.getElementById('hidden_realAmount').value = realAmount;
      document.getElementById('hidden_feeAmount').value = feeAmount;
    }

    $('input[type="checkbox"]').on('change', function () {
      $('input[name="' + this.name + '"]').not(this).prop('checked', false);
    });

    function showCardIssueDate(obj) {
      if ("ACCOUNT" === $(obj).val()) {
        $("#cardIssueDateForm").addClass("hidden");
        $("#cardIssueDate1").attr("required", false);
        $("#cardIssueDate2").attr("required", false);
      } else {
        $("#cardIssueDateForm").removeClass("hidden");
        $("#cardIssueDate1").attr("required", true);
        $("#cardIssueDate2").attr("required", true);
      }
    }

    function openSaveBank() {
      $('#modalSaveBank').modal('show')
    }

    function arrowClick(itemHolderName, itemNumber) {
      $('#modalSaveBank').modal('hide');
      $("#bankHolderName").val(itemHolderName);
      $("#_soTaiKhoan").val(itemNumber);
    }

    $("#amount").on("input", function () {
      thanhToan();
    });
  </script>


</body>

</html>