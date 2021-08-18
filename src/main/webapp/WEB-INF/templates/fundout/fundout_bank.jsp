<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_LINK_BANK" %>
<%@ page import="static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_LOCAL_BANK" %>
<%@ page import="static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND" %>
<%@ page import="static vn.mog.ewallet.consumer.web.common.SharedConstants.FUND_ORDER_SHOW_REQUEST_BANK_TRANSFER" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
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
  <jsp:param name="nav" value="fundOut"/>
</jsp:include>
<!-- /menu bar -->

<c:set var="FUND_ORDER_SHOW_LINK_BANK" value="<%=FUND_ORDER_SHOW_LINK_BANK%>"/>
<c:set var="FUND_ORDER_SHOW_LOCAL_BANK" value="<%=FUND_ORDER_SHOW_LOCAL_BANK%>"/>
<c:set var="FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND" value="<%=FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND%>"/>
<c:set var="FUND_ORDER_SHOW_REQUEST_BANK_TRANSFER" value="<%=FUND_ORDER_SHOW_REQUEST_BANK_TRANSFER%>"/>

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage"><spring:message code="label.manage"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="label.fundout"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.fundout"/></h1>
  </div>
  <div class="page-content container-fluid">

    <c:if test="${'true' eq FUND_ORDER_SHOW_LINK_BANK}">
      <div class="panel">
        <div class="panel-heading">
          <h3 class="panel-title"><spring:message code="label.link.bank"/></h3>
        </div>
        <div class="panel-body">
          <div class="row">
            <c:if test="${listBankDirect !=null}">
              <c:forEach items="${listBankDirect}" var="itemListBankDirect" varStatus="link_bank_id">
                <%--<div class="mb-10 item-bank" name="itemCard">--%>
                <%--<span class="phoneCharge-choose">--%>
                <%--<label>--%>
                <%--<a href="/fundout/link-bank?bankCode=${itemListBankDirect.bankCode}${tprice != null ? '&amount='.concat(tprice) : ''}">--%>
                <%--<img alt="${item.bankName}"--%>
                <%--src="/assets/images/bank/${itemListBankDirect.bankCode}.png">--%>
                <%--</a>--%>
                <%--</label>--%>
                <%--</span>--%>
                <%--<input type="hidden" name="bankAccountNumber" id="bankAccountNumber"--%>
                <%--value="${itemListBankDirect.bankAccountNumber}">--%>
                <%--<input type="hidden" name="bankDisplayName" id="bankDisplayName"--%>
                <%--value="${itemListBankDirect.bankDisplayName}">--%>
                <%--</div>--%>
                <form method="post" action="/fundout/link-bank">
                  <div class="mb-10 item-card" name="itemCard">
                    <input type="hidden" name="bankCode"
                           value="${itemListBankDirect.bankCode}">
                    <input type="hidden" name="amount"
                           value="${tprice}">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
                    <button class="hidden" id="link-bankSubmit${link_bank_id.count}"></button>
                    <span class="phoneCharge-choose">
                    <label for="link-bankSubmit${link_bank_id.count}">
                      <img alt="${item.bankName}"
                           src="/assets/images/bank/${itemListBankDirect.bankCode}.png">
                    </label>
                  </span>
                  </div>
                </form>
              </c:forEach>
            </c:if>
            <c:if test="${listBankDirect ==null}">
              <div class="col-md-6 col-sm-12">
                <a href="/bank/link-bank-account"><img src="/assets/images/the6.png" style="width: 318px;
    height: 155px;"></a>
              </div>
            </c:if>
          </div>
        </div>
      </div>
    </c:if>

    <c:if test="${'true' eq FUND_ORDER_SHOW_LOCAL_BANK}">
      <div class="panel">
        <div class="panel-heading">
          <h3 class="panel-title"><spring:message code="label.bank"/></h3>
        </div>
        <div class="panel-body">
          <div class="row">
            <c:forEach items="${listBank}" var="item" varStatus="item_id">
              <form method="post" action="/fundout/no-link-bank/info">
                <div class="mb-10 item-card" name="itemCard">

                  <input type="hidden" name="_nameBank"
                         value="${item.bankCode}" />
                  <input type="hidden" name="amount"
                         value="${tprice}" />
                  <input type="hidden" name="_soTaiKhoan"
                         value="${account}" />
                  <input type="hidden" name="${_csrf.parameterName}"
                         value="${_csrf.token}" />
                  <button class="hidden" id="bankSubmit${item_id.count}"></button>
                  <span class="phoneCharge-choose">
                  <label for="bankSubmit${item_id.count}">
                   <img alt="${item.bankName}" src="/assets/images/bank/${item.bankCode}.png">
                  </label>
                </span>
                </div>
              </form>
            </c:forEach>
          </div>
        </div>
      </div>
    </c:if>

    <div class="panel hidden">
      <div class="panel-heading"><h3 class="panel-title">Chọn phương thức khác</h3></div>
      <div class="panel-body">
        <div class="row">
          <div class="col-md-3 text-center">
            <a href="#"><img src="/assets/images/placeholder100.png" class="br3 mb-15" width="60">
              <p>Rút qua thẻ liên kết</p></a>
          </div>
          <div class="col-md-3 text-center">
            <a href="#"><img src="/assets/images/placeholder100.png" class="br3 mb-15" width="60">
              <p>Rút qua thẻ quốc tế</p></a>
          </div>
          <div class="col-md-3 text-center">
            <a href="#"><img src="/assets/images/placeholder100.png" class="br3 mb-15" width="60">
              <p>Rút chuyển khoản</p></a>
          </div>
          <div class="col-md-3 text-center">
            <a href="#"><img src="/assets/images/placeholder100.png" class="br3 mb-15" width="60">
              <p>Gửi thông báo nạp điểm</p></a>
          </div>
        </div>
      </div>
    </div>

    <c:if test="${'true' eq FUND_ORDER_SHOW_REQUEST_CASH_ON_HAND}">
      <div class="panel">
        <div class="panel-heading"><h3 class="panel-title"><spring:message code="fundout.send.request.money"/> </h3></div>
        <div class="panel-body">
          <div class="row">
            <div class="col-md-3 text-center">
              <a href="/fundout/cash-on-hand/management"><img src="/assets/images/icon/money_transfer.png" class="br3 mb-15" width="60" onerror="imgError(this)">
                <p><spring:message code="fundout.cash.on.hand"/></p></a>
            </div>
          </div>
        </div>
      </div>
    </c:if>
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

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

<%--Handle error images--%>
<script>
  function imgError(image) {
//    image.src = "/assets/images/placeholder100.png";
  }
</script>

</html>