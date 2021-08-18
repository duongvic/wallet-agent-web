<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.fundout.FundOutController.FUND_OUT_REQUEST_GET" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="label.fundout"/> - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="foHistory"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item action"><a href="#"><spring:message code="label.fundout"/></a></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.fundout"/></h1></div>
  <div class="page-content container-fluid">
    <div class="panel">
      <div class="panel-heading">
        <h3 class="panel-title">Sử dụng gần đây</h3>
      </div>
      <div class="panel-body">
        <div class="row">
          <nav>
            <c:forEach var="item" items="${listBankFundOut}">
              <div class="col bank-direct-fundOut">
                <div class="card-id mb-10">
                  <div class="logo-card-l"><img alt="${item.bankCode}"
                                                src="/assets/images/bank/${item.bankCode}.png">
                  </div>
                  <div class="logo-card-r"><img alt="" src="/assets/images/delete.png" style="width: 20px" onclick="unLinkBank()"></div>
                  <div class="number-card card-number-input">${item.bankAccountNumber}</div>
                  <div class="name-date">
                    <div class="name">${item.bankAccountName}</div>
                    <div class="date">${item.cardIssueDate}</div>

                    <input type="hidden" id="bankName" name="bankName" value="${bankName}">
                    <input type="hidden" id="bankDisplayName" name="bankName" value="${bankDisplayName}">
                    <input type="hidden" id="bankAccountNumber" name="bankAccountNumber" value="${bankAccountNumber}">

                    <input type="hidden" id="phoneNumber" name="phoneNumber" value="${phoneNumber}">
                    <input type="hidden" id="bankCode" name="bankCode" value="${bankCode}">
                    <input type="hidden" id="bankAccountName" name="bankAccountName"
                           value="${bankAccountName}">
                    <input type="hidden" id="ssn" name="ssn" value="${ssn}">
                    <input type="hidden" id="subscriptionId" name="subscriptionId" value="${subscriptionId}">

                  </div>
                </div>
              </div>
            </c:forEach>

            <c:if test="${(listBankFundOut == null)}">
              <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                              class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            </c:if>
          </nav>
        </div>
      </div>
    </div>
    <div class="panel">
      <div class="panel-heading">
        <h3 class="panel-title">Ngân hàng rút tiền nhanh 24 / 7</h3>
      </div>
      <div class="panel-body">
        <div class="row">
          <nav>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
          </nav>
        </div>
      </div>
    </div>
    <div class="panel">
      <div class="panel-heading">
        <h3 class="panel-title">Ngân hàng rút tiền thông thường</h3></div>
      <div class="panel-body">
        <div class="row">
          <nav>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/fundout/request"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
          </nav>
        </div>
      </div>
    </div>

  </div>
</div>

<div class="modal fade modal-fall" id="examplePositionCenter" aria-hidden="true"
     aria-labelledby="examplePositionCenter" role="dialog" tabindex="-1">
  <div class="modal-dialog modal-simple modal-center">
    <div class="modal-content bg-0">
      <div class="modal-header">
        <button type="button" class="close close-bg-0" data-dismiss="modal" aria-label="Close"><span
            aria-hidden="true" class=""><i class="icon pe-close"></i></span></button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-sm-6 col-md-6">
            <div class="panel panel-bordered">
              <div class="panel-heading text-center">
                <h3 class="panel-title"><spring:message code="label.bank.account"/></h3></div>
              <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
              <div class="panel-body text-center pt-0">
                <button type="button" class="btn btn-primary btn-sm"> <spring:message code="common.btn.add"/><i
                    class="icon wb-arrow-right ml-10"></i></button>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-md-6">
            <div class="panel panel-bordered">
              <div class="panel-heading text-center">
                <h3 class="panel-title"><spring:message code="label.bank.card"/></h3></div>
              <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
              <div class="panel-body text-center pt-0">
                <button type="button" class="btn btn-primary btn-sm"> <spring:message code="common.btn.add"/><i
                    class="icon wb-arrow-right ml-10"></i></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>



<c:url var="fundOutReqUri" value="<%=FUND_OUT_REQUEST_GET%>"></c:url>
<c:import url="../dialog_modal/pin_code/dialog_addCard.jsp"/>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>
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
    $(".bank-direct-fundOut").click(function (e) {
      e.preventDefault();
      var alt = $(this).find("img").attr("alt");
      window.location.href='${fundOutReqUri}';
    });
  });
  //end
</script>
</html>