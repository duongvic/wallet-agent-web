<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.fundin.FundInLinkBankController.UNLINK_BANK_INFO" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:url var="unLinkBankUri" value="<%=UNLINK_BANK_INFO%>"></c:url>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
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
  <jsp:param name="nav" value="fiLinkbank"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/fundin"><spring:message code="label.fundin"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="label.fundin.link.bank"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.fundin.link.bank"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="form-group row mb-10 row hidden">
      <c:forEach var="item" items="${listBankDirect}">
        <div class="col bank-direct">
          <div class="card-id mb-10">
            <div class="logo-card-l"><img alt="${item.bankCode}"
                                          src="/assets/images/bank/${item.bankCode}.png"></div>
              <%--<div class="logo-bank-r unlink-bank">--%>
              <%--<img alt="" src="/assets/images/delete.png" style="width: 20px">--%>
              <%--</div>--%>
            <div class="number-card card-number-input">${item.bankAccountNumber}</div>
            <div class="name-date">
              <div class="name">${item.bankAccountName}</div>
              <div class="date">${item.cardIssueDate}</div>
            </div>
          </div>

        </div>
      </c:forEach>
      <div class="col">
        <div class="item the">
          <a href="/bank/link-bank-account"><img
              src="/assets/images/the6.png" style="width: 318px;
    height: 155px;"></a>
        </div>
      </div>

    </div>
    <%--<div class="owl-carousel owl-carousel-shortcode thenganhang pt-20 pl-40 mt-15"--%>
    <%--data-plugin="owlCarousel" data-center="true" data-dots="true" data-margin="5">--%>
    <%--<div class="item the">--%>
    <%--<div class="action"><a href="#"><i class="icon pe-close"></i></a></div>--%>
    <%--<a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img--%>
    <%--src="/assets/images/the2.png"></a>--%>
    <%--</div>--%>
    <%--<div class="item the">--%>
    <%--<div class="action"><a href="#"><i class="icon pe-link"></i></a></div>--%>
    <%--<a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img--%>
    <%--src="/assets/images/the3.png"></a>--%>
    <%--</div>--%>
    <%--<div class="item the">--%>
    <%--<div class="action"><a href="#"><i class="icon pe-diskette"></i></a></div>--%>
    <%--<a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img--%>
    <%--src="/assets/images/the4.png"></a>--%>
    <%--</div>--%>
    <%--<div class="item the">--%>
    <%--<a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img--%>
    <%--src="/assets/images/the5.png"></a>--%>
    <%--</div>--%>
    <%--<div class="item the">--%>
    <%--<a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img--%>
    <%--src="/assets/images/the1.png"></a>--%>
    <%--</div>--%>
    <%--</div>--%>


    <div class="mt-30">
      <div class="row">
        <div class="col-md-7">
          <div class="panel panel-bordered">
            <div class="panel-body py-10">
              <form class="form-horizontal" method="post" action="/fundin/verify-link-bank">
                <div class="form-group row mb-10 name-bank">
                  <div class="full-width">
                    <img alt="${bankCode}" src="/assets/images/bank/${bankCode}.png" align="left"
                         width="100"
                         style="margin-right:10px">
                    <div class="text-left">
                      <label>${bankDisplayName}</label>
                      <br>
                      <small class="card-number-input">${bankAccountNumber}</small>

                      <input type="hidden" id="bankName" name="bankName" value="${bankName}">
                      <input type="hidden" id="bankDisplayName" name="bankDisplayName"
                             value="${bankDisplayName}">
                      <input type="hidden" id="bankAccountNumber" name="bankAccountNumber"
                             value="${bankAccountNumber}">
                      <input type="hidden" id="phoneNumber" name="phoneNumber"
                             value="${phoneNumber}">
                      <input type="hidden" id="bankCode" name="bankCode" value="${bankCode}">
                      <input type="hidden" id="bankAccountName" name="bankAccountName"
                             value="${bankAccountName}">
                      <input type="hidden" id="ssn" name="ssn" value="${ssn}">
                      <input type="hidden" id="subscriptionId" name="subscriptionId"
                             value="${subscriptionId}">
                    </div>
                    <div class="clr"></div>
                  </div>
                </div>

                <div class="clr"></div>
                <div class="form-group row mb-10">
                  <c:if test="${(codeErr != null)}">
                    <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                      <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                    </div>
                  </c:if>
                </div>

                <div class="form-group row mb-10 pos-relative">
                  <input type="text" disabled class="form-control" name="phoneNumber"
                         value="${phoneNumber}"/>
                  <%--<label--%>
                  <%--class="form-control-label px-0 label-control-right">${userLogin.username}</label>--%>
                  <div class="price-radio mt-5 hidden">
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdt1" name="phoneNumber" checked=""
                             value="${(userLogin.phoneNumber)}" onclick="getSoPhone(this)">
                      <label for="sdt1"><span>${(userLogin.phoneNumber)} </span></label>
                    </div>
                    <c:if test="${listPhoneFundIn !=null}">
                      <c:forEach var="item" items="${listPhoneFundIn}" varStatus="rowId">
                        <div class="radio-custom radio-info radio-inline text-center">
                          <input type="radio" id="${rowId.count}" name="phoneNumber"
                                 value="${item.payeeUsername}"
                                 onclick="getSoPhone(this)">
                          <label for="${rowId.count}"><span>${item.payeeUsername}</span></label>
                        </div>
                      </c:forEach>
                    </c:if>
                  </div>
                </div>
                <div class="form-group row mb-10 pos-relative">
                  <input type="text" class="currency-input form-control" id="faceValue"
                         name="faceValue"
                         value="${faceValue != null && faceValue ne '' ? faceValue : '1000000'}"
                         placeholder="<spring:message code="fundin.money"/>"/>
                  <label class="form-control-label px-0 label-control-right"><span><spring:message code="label.fee.fundin"/>:</span>&nbsp;<span
                      class="txt-note-pin vnd" style="padding-right: 0!important;" id="feeAmount">0</span>
                    <input type="hidden" name="feeAmount" id="hidden_feeAmount"
                           value="${feeAmount}">
                  </label>
                  <div class="price-radio mt-5">
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt1" name="numberdt2"
                             value="100000" ${'100000' eq faceValue ? 'checked=/"checked/"' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt1"><span>100.000 đ</span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt2" name="numberdt2"
                             value="200000" ${'200000' eq faceValue ? 'checked=/"checked/"' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt2"><span>200.000 đ</span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt3" name="numberdt2"
                             value="500000" ${'500000' eq faceValue ? 'checked=/"checked/"' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt3"><span>500.000 đ</span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdtt4" name="numberdt2"
                             value="1000000" ${(faceValue == null || faceValue eq '' || '1000000' eq faceValue) ? 'checked=/"checked/"' : ''}
                             onclick="getSoTienNap(this)">
                      <label for="sdtt4"><span>1.000.000 đ </span></label>
                    </div>
                  </div>
                </div>
                <%--<div class="form-group row mb-10">--%>
                <%--<input type="text" class="form-control col-md-9" name="checkCode" value="" placeholder="<spring:message code="label.check.code"/>"/>--%>
                <%--<label class="form-control-label px-0 col-md-3 text-center"><b>ABCDF</b></label>--%>
                <%--</div>--%>
                <div class="form-group row mb-0">
                  <label class="form-control-label px-0"><spring:message code="label.amount"/>: <b class="text-amount"
                                                                        id="realAmount"></b></label>
                  <input type="hidden" id="hidden_realAmount" name="realAmount"
                         value="${realAmount}">
                </div>
                <div class="row">
                  <div class="col-md-9 offset-md-3">
                    <button type="submit" class="btn btn-primary btn-sm pull-right"> <spring:message code="label.fundin"/>
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
          <c:import url="../include_component/frame_last_transaction.jsp"/>
          <!-- /Giao dịch gần nhất  -->
        </div>
      </div>
    </div>
  </div>


  <div class="modal fade modal-fall" id="examplePositionCenter" aria-hidden="true"
       aria-labelledby="examplePositionCenter" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-simple modal-center">
      <div class="modal-content bg-0">
        <div class="modal-header">
          <button type="button" class="close close-bg-0" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" class=""><i class="icon pe-close"></i></span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-sm-6 col-md-6">
              <div class="panel panel-bordered">
                <div class="panel-heading text-center">
                  <h3 class="panel-title"><spring:message code="label.bank.account"/></h3></div>
                <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
                <div class="panel-body text-center pt-0"><a href="/bank/link-bank-account"
                                                            class="btn btn-primary btn-sm"> <spring:message code="common.btn.add"/><i
                    class="icon wb-arrow-right ml-10"></i> </a></div>
              </div>
            </div>
            <div class="col-sm-6 col-md-6">
              <div class="panel panel-bordered">
                <div class="panel-heading text-center">
                  <h3 class="panel-title"><spring:message code="label.bank.card"/></h3></div>
                <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
                <div class="panel-body text-center pt-0"><a href="/bank/themTheNganHang"
                                                            class="btn btn-primary btn-sm"> <spring:message code="common.btn.add"/><i
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
  <c:import url="../include_page/footer_js.jsp"/>
  <!-- /footer -->
  <%--<script src="/assets/js/babel-external-helpers.js"></script>--%>
  <%--<script src="/assets/js/jquery.js"></script>--%>
  <%--<script src="/assets/js/popper.min.js"></script>--%>
  <%--<script src="/assets/js/bootstrap.js"></script>--%>
  <%--<script src="/assets/js/animsition.js"></script>--%>
  <%--<script src="/assets/js/jquery.mousewheel.js"></script>--%>
  <%--<script src="/assets/js/jquery-asScrollbar.js"></script>--%>
  <%--<script src="/assets/js/jquery-asScrollable.js"></script>--%>
  <%--<script src="/assets/js/jquery-asHoverScroll.js"></script>--%>
  <%--<script src="/assets/js/switchery.js"></script>--%>
  <%--<script src="/assets/js/intro.js"></script>--%>
  <%--<script src="/assets/js/screenfull.js"></script>--%>
  <%--<script src="/assets/js/jquery-slidePanel.js"></script>--%>
  <%--<script src="/assets/js/jquery.dataTables.js"></script>--%>
  <%--<script src="/assets/js/dataTables.bootstrap4.js"></script>--%>
  <%--<script src="/assets/js/dataTables.fixedHeader.js"></script>--%>
  <%--<script src="/assets/js/dataTables.fixedColumns.js"></script>--%>
  <%--<script src="/assets/js/dataTables.rowGroup.js"></script>--%>
  <%--<script src="/assets/js/dataTables.scroller.js"></script>--%>
  <%--<script src="/assets/js/dataTables.responsive.js"></script>--%>
  <%--<script src="/assets/js/responsive.bootstrap4.js"></script>--%>
  <%--<script src="/assets/js/dataTables.buttons.js"></script>--%>
  <%--<script src="/assets/js/buttons.html5.js"></script>--%>
  <%--<script src="/assets/js/buttons.flash.js"></script>--%>
  <%--<script src="/assets/js/buttons.print.js"></script>--%>
  <%--<script src="/assets/js/buttons.colVis.js"></script>--%>
  <%--<script src="/assets/js/buttons.bootstrap4.js"></script>--%>
  <%--<script src="/assets/js/owl.carousel.js"></script>--%>
  <%--<script src="/assets/js/slick.js"></script>--%>
  <%--<script src="/assets/js/Component.js"></script>--%>
  <%--<script src="/assets/js/Plugin.js"></script>--%>
  <%--<script src="/assets/js/Base.js"></script>--%>
  <%--<script src="/assets/js/Config.js"></script>--%>
  <%--<script src="/assets/js/Menubar.js"></script>--%>
  <%--<script src="/assets/js/GridMenu.js"></script>--%>
  <%--<script src="/assets/js/Sidebar.js"></script>--%>
  <%--<script src="/assets/js/PageAside.js"></script>--%>
  <%--<script src="/assets/js/menu.js"></script>--%>
  <%--<script src="/assets/js/colors.js"></script>--%>
  <%--<script src="/assets/js/tour.js"></script>--%>
  <%--<script src="/assets/js/Site.js"></script>--%>
  <%--<script src="/assets/js/asscrollable.js"></script>--%>
  <%--<script src="/assets/js/slidepanel.js"></script>--%>
  <%--<script src="/assets/js/switchery.js"></script>--%>
  <%--<script src="/assets/js/owl-carousel.js"></script>--%>
  <%--<script src="/assets/js/carousel.js"></script>--%>
  <%--<script src="/assets/js/datatables.js"></script>--%>
  <%--<script src="/assets/js/datatable.js"></script>--%>
  <%--&lt;%&ndash;<script src="/assets/js/format.currency.js"></script>&ndash;%&gt;--%>
  <%--<script src="/assets/development/js/my_format_currency.js"></script>--%>

  <script>
    (function (document, window, $) {
      'use strict';
      var Site = window.Site;
      $(document).ready(function () {
        Site.run();
      });
    })(document, window, jQuery);

    //  get alt img
    $(document).ready(function () {
      $(".name-bank").css({"display": "block"});

      //$(".bank-direct").click(function (e) {
      //e.preventDefault();
      //var alt = $(this).find("img").attr("alt");
      //$(".name-bank").css({"display": "block"});
      //});
      thanhToan();
    });

    //end

    function getSoPhone(elem) {
      var x = elem.value;
      document.getElementById('_soTaiKhoan').value = x;
    }

    function getSoTienNap(elem) {
      var x = elem.value;
      document.getElementById('faceValue').value = formatCurrency(x);
      thanhToan();
    }

    function thanhToan() {
      var realAmount = 0;
      var faceValue = 0;
      if (document.getElementById('faceValue').value === null) {
        document.getElementById('faceValue').value = 0;
      }
      faceValue = parseFloat(currencyToNumber(document.getElementById('faceValue').value));
      var feeAmount = parseFloat(currencyToNumber(document.getElementById('feeAmount').innerText));
      if (feeAmount > faceValue) {
        realAmount = 0;
      }
      else {
        realAmount = faceValue + feeAmount;
      }

      document.getElementById('realAmount').innerText = formatCurrency(realAmount);
      //get value
      document.getElementById('hidden_realAmount').value = realAmount;
      document.getElementById('hidden_feeAmount').value = feeAmount;
    }

    $("#faceValue").on("input", function () {
      thanhToan();
    });
  </script>

</body>

</html>