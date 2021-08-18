<%@ page import="static vn.mog.ewallet.consumer.web.controller.fundin.FundInLinkBankController.FUNDIN_LINKBANK_PREFIX" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:url var="fundInControllerURI" value="<%=FUNDIN_LINKBANK_PREFIX%>"></c:url>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
  <title><spring:message code="label.fundin.link.bank"/> - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="fiATM"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/fundin"><spring:message code="label.fundin"/></a></li>
      <li class="breadcrumb-item"><spring:message code="label.fundin.atm"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.fundin.atm"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="mt-30">
      <div class="row">
        <div class="col-md-7">
          <div class="panel panel-bordered">
            <div class="panel-body py-10">
              <form class="form-horizontal" method="post" action="/fundin/atm/request">
                <div class="form-group row mb-10">
                  <div class="full-width">
                    <img alt="${bankCode}" src="/assets/images/bank/${_nameBank}.png" align="left"
                         width="100"
                         style="margin-right:10px">
                    <div class="text-left">
                      <label>${bankDisplayName}</label>
                      <br>

                      <input type="hidden" id="bankName" name="bankName" value="${bankName}">
                      <input type="hidden" id="bankCode" name="bankCode" value="${bankCode}">
                      <input type="hidden" id="bankDisplayName" name="bankDisplayName"
                             value="${bankDisplayName}">
                    </div>
                    <div class="clr"></div>
                  </div>
                </div>
                <%--<div class="form-group row mb-10">
                  <input type="text" class="form-control" placeholder="Chủ tài khoản"
                         id="bankHolderName" name="bankHolderName" value="${bankHolderName}"
                         required/>
                </div>--%>
                <div class="form-group row mb-10 pos-relative">
                  <input type="text" class="form-control" placeholder="Số ví"
                         id="_soTaiKhoan" name="_soTaiKhoan" value="${_soTaiKhoan != null && _soTaiKhoan ne '' ? _soTaiKhoan : userLogin.phoneNumber}" required/>
                  <%--<label--%>
                  <%--class="form-control-label px-0 label-control-right">${userLogin.username}</label>--%>
                  <div class="price-radio mt-5 hidden">
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdt1" name="phoneNumber" ${(_soTaiKhoan == null || (_soTaiKhoan eq userLogin.phoneNumber)) ? 'checked' : ''}
                             value="${(userLogin.phoneNumber)}" onclick="getSoTaiKhoan(this)">
                      <label for="sdt1"><span>${(userLogin.phoneNumber)} </span></label>
                    </div>
                    <c:if test="${listPhoneTopUp !=null}">
                    <c:forEach var="item" items="${listPhoneTopUp}" varStatus="rowId">
                      <div class="radio-custom radio-info radio-inline text-center">
                        <input type="radio" id="${rowId.count}" name="phoneNumber" value="${item.value}"
                               onclick="getSoTaiKhoan(this)">
                        <label for="${rowId.count}"><span>${item.value}</span></label>
                      </div>
                    </c:forEach>
                    </c:if>
                  </div>
                </div>

                <div class="form-group row mb-10 pos-relative">
                  <input type="text" class="form-control currency-input" id="amount" name="amount"
                         value="${(amount != null && amount ne '') ? amount : '1000000'}" placeholder="<spring:message code="fundin.money"/>"
                         required/>
                  <label class="form-control-label px-0 label-control-right txt-note-pin"><span
                      id="feeAmount"><spring:message code="label.fee.fundin"/>: 0 </span></label>
                  <input type="hidden" name="feeAmount" id="hidden_feeAmount" value="${feeAmount}">

                  <div class="price-radio mt-5">
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt1" name="numberdt2" value="100000" ${amount eq '100000' ? 'checked' : ''}
                             onclick="getSoTienRut(this)">
                      <label for="sdtt1"><span>100.000 </span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt2" name="numberdt2" value="200000" ${amount eq '200000' ? 'checked' : ''}
                             onclick="getSoTienRut(this)">
                      <label for="sdtt2"><span>200.000 </span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt3" name="numberdt2" value="500000" ${amount eq '500000' ? 'checked' : ''}
                             onclick="getSoTienRut(this)">
                      <label for="sdtt3"><span>500.000 </span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt4" name="numberdt2" value="1000000" ${(amount == null || amount eq '' || amount eq '1000000') ? 'checked' : ''}
                             onclick="getSoTienRut(this)">
                      <label for="sdtt4"><span>1.000.000 </span></label>
                    </div>
                  </div>
                </div>

                <div class="form-group row mb-0">
                  <label class="form-control-label px-0"><spring:message code="label.amount"/>: <b class="text-amount" id="realAmount"></b></label>
                  <input type="hidden" id="hidden_realAmount" name="realAmount"
                         value="${realAmount}">
                </div>

                <div class="form-group row mb-10 hidden">
                  <input type="text" class="form-control col-md-9" name="checkCode" value=""
                         placeholder="<spring:message code="label.check.code"/>"/>
                  <label class="form-control-label px-0 col-md-3 text-center"><b>ABCDF</b></label>
                </div>

               <%-- <div id="cardIssueDateForm" class="form-group row mb-10">
                  <div class="">
                    <input type="text" id="cardIssueDate1" data-plugin="formatter"
                           data-pattern="[[99]]"
                           required class="form-control text-center" style="max-width: 50px;">
                  </div>
                  <div class="mr-3" style="margin-left: 3px">
                    <span style="font-size: 20px">⁄</span>
                  </div>
                  <div class="">
                    <input type="text" class="form-control text-center" id="cardIssueDate2" required
                           data-plugin="formatter" data-pattern="[[99]]" style="max-width: 50px;">
                  </div>
                  <input type="hidden" id="hidden_cardIssueDate" name="cardIssueDate"
                         value="${cardIssueDate}">
                </div>--%>



                <div class="row mb-10">
                  <div class="col text-right inline-block">
                    <a href="${fundInControllerURI}" class="btn btn-default mr-10"><spring:message code="common.btn.back"/></a>
                    <button type="submit" class="btn btn-primary btn-sm mr-10"
                            onclick="getCardIssueDate()"> <spring:message code="label.fundin"/>
                    </button>

                  </div>
                </div>
                <input type="hidden" name="orderId" value="${orderId}"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              </form>
            </div>
          </div>
        </div>
        <div class="col-md-5">
          <!-- Giao dịch gần nhất -->
          <c:import url="../include_component/frame_last_transaction_fundOut_noLinkBank.jsp"/>
          <!-- /Giao dịch gần nhất  -->
        </div>
      </div>
    </div>
  </div>


  <!-- footer -->
  <%--<c:import url="../include_page/footer.jsp"/>--%>
  <!-- /footer -->

  <c:import url="../dialog_modal/fundout_noLinkBank/dialog_saveBank.jsp"/>

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
  <script src="/assets/development/js/my_format_currency.js"></script>

  <script>
    (function (document, window, $) {
      'use strict';
      var Site = window.Site;
      $(document).ready(function () {
        Site.run();
        $(".currency-input").val(formatCurrency(currencyToNumber($(".currency-input").val())));
        thanhToan();
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

      document.getElementById('realAmount').innerText = formatCurrency(realAmount);
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

    function getCardIssueDate() {
      document.getElementById('hidden_cardIssueDate').value = document.getElementById(
          'cardIssueDate1').value + "/" + document.getElementById('cardIssueDate2').value
    }

    function openSaveBank() {
      $('#modalSaveBank').modal('show')
    }

    function arrowClick(itemHolderName, itemNumber) {
      $('#modalSaveBank').modal('hide');
      $("#bankHolderName").val(itemHolderName);
      $("#_soTaiKhoan").val(itemNumber);
    }
    $("#amount").on("input", function() {
      thanhToan();
    });
  </script>

</body>

</html>