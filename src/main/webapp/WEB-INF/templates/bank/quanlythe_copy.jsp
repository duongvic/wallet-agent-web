<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="card.manage.title"/></title>
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
  <jsp:param name="nav" value="quanlythe"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="card.manage.label"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="card.manage.label"/></h1></div>
  <div class="page-content container-fluid">
    <div class="owl-carousel owl-carousel-shortcode thenganhang pt-20 pl-40 mt-15" data-plugin="owlCarousel" data-center="true" data-dots="true" data-margin="5">
      <div class="item the">
        <div class="action"><a href="#"><i class="icon pe-close"></i></a></div>
        <a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img src="/assets/images/the2.png"></a>
      </div>
      <div class="item the">
        <div class="action"><a href="#"><i class="icon pe-link"></i></a></div>
        <a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img src="/assets/images/the3.png"></a>
      </div>
      <div class="item the">
        <div class="action"><a href="#"><i class="icon pe-diskette"></i></a></div>
        <a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img src="/assets/images/the4.png"></a>
      </div>
      <div class="item the">
        <a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img src="/assets/images/the5.png"></a>
      </div>
      <div class="item the">
        <a href="#" data-target="#examplePositionCenter" data-toggle="modal"><img src="/assets/images/the1.png"></a>
      </div>
    </div>
    <div class="mt-30">

      <div class="panel mb-0 panel-bordered">
        <div class="panel-heading">
          <h3 class="panel-title">Giao dịch trong tháng</h3>
        </div>
        <div class="panel-body tb-tool">
          <display:table name="transactions" id="item"
                         requestURI="list"
                         pagesize="${pagesize}" partialList="true"
                         size="total"
                         sort="page"
                         class="table table-bordered table-striped mb-none">

            <%@ include file="../include_component/display_table.jsp" %>

            <display:column title="${colStt}">
                    <span id="row${list.id}" class="rowid">
                        <c:out value="${offset + item_rowNum}"/>
                    </span>
            </display:column>

            <display:column title="${colTime}" property="creationDate" format="{0,date,HH:mm:ss dd/MM/yyyy}"/>

            <display:column title="${colTransID}">
              <a class="app-name detail-link link-active" href="#" txnId="${item.id}">${item.id}</a>
            </display:column>

            <display:column title="${colRId}">
              ${fn:substring(item.orderId, 0, 16)}<br/>
              ${fn:substring(item.orderId, 16, item.orderId.length())}
            </display:column>

            <c:if test="${userLogin.customerTypeId == cusTypeAdmin || userLogin.customerTypeId == cusTypeStaff}">
              <display:column title="${colMerchant}" property="payerUsername"/>
              <display:column title="${colProvider}" property="providerCode"/>
            </c:if>

            <!-- STAFF, service, gia tri giao dich, cappital value, real amount -->
            <!-- MERCHANT, CUSTOMER gia tri giao dich, discount, real amount -->

            <sec:authorize access="hasAnyRole('ADMIN_OPERATION','STAFF','MERCHANT','AGENT','CUSTOMER')">
              <display:column title="${colService}">
                <spring:message code="${item.getService()}"/>
              </display:column>
            </sec:authorize>

            <!-- start transaction Attribute -->

            <!-- end transaction Attribute -->

            <display:column title="${colAmount}" class="col-number-header" headerClass="col-number-header" value="${ewallet:formatNumber(item.amount)}"/>

            <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR',
                                          'SALESUPPORT_LEADER', 'SALESUPPORT',
                                          'FINANCE',
                                          'RECONCILIATION','RECONCILIATION_LEADER')">
              <display:column title="${colCapitalVal }" class="col-number-header" headerClass="col-number-header" value="${ewallet:formatNumber(item.capitalValue)}"/>
            </sec:authorize>

            <sec:authorize access="hasAnyRole('ADMIN_OPERATION','MERCHANT','AGENT','CUSTOMER')">
              <display:column title="${colCommission}" class="col-number-header" headerClass="col-number-header">
                <c:if test="${item.commision != null}">+</c:if>&nbsp;${ewallet:formatNumber(item.commision)}
              </display:column>
              <display:column title="${colFee}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.fee)}</display:column>
            </sec:authorize>

            <display:column title="${colRealAmount}" class="col-number-header" headerClass="col-number-header">
              ${item.getTransactionType()}&nbsp;${ewallet:formatNumber(item.realAmount)}
            </display:column>

            <display:column title="${colBalBf}" class="col-number-header" headerClass="col-number-header">
              <c:if test="${item.preBalance != null}">+</c:if>&nbsp;${ewallet:formatNumber(item.preBalance)}
            </display:column>

            <display:column title="${colBalAf}" class="col-number-header" headerClass="col-number-header">
              <c:if test="${item.postBalance != null}">+</c:if>&nbsp;${ewallet:formatNumber(item.postBalance)}
            </display:column>

            <display:column title="${colStatus}">
              <spring:message code="${item.getStatus()}"/>
            </display:column>

            <!-- thong tin them -->
            <c:choose>
              <c:when test="${service.size() eq 1}">
                <!-- nothing -->
              </c:when>
              <c:otherwise>
                <spring:message var="colAttributeOther" code="transaction.api.table.attributeOther"/>
                <c:choose>
                  <c:when test="${item.attributes.size() > 3}">
                    <display:column title="${colAttributeOther}">
                      <c:forEach var="attribute" items="${item.attributes}" varStatus="statusRow" begin="3">
                        ${attribute.transactionAttributeType}_${attribute.transactionAttributeValue}
                        <br/>
                      </c:forEach>
                    </display:column>
                  </c:when>
                  <c:otherwise>
                    <display:column title="${colAttributeOther}"/>
                  </c:otherwise>
                </c:choose>
              </c:otherwise>
            </c:choose>

            <display:column title="${colAction}" class="action_icon center" headerClass="center">
              <a href="#" class="detail-link link-active" title="${actViewDetail}" txnId="${item.id}">
                <i class="fa fa-info-circle "></i>
              </a>
              <sec:authorize access="hasRole('ADMIN_OPERATION')">
                <spring:url var="reversalURI" value="${txnControlUri}/reversal?txnId=${item.id}"/>
                <a href="${reversalURI}" class="reversal-link link-active" title="${actViewReveral}">
                  <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                </a>
              </sec:authorize>
            </display:column>
          </display:table>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="modal fade modal-fall" id="examplePositionCenter" aria-hidden="true" aria-labelledby="examplePositionCenter" role="dialog" tabindex="-1">
  <div class="modal-dialog modal-simple modal-center">
    <div class="modal-content bg-0">
      <div class="modal-header">
        <button type="button" class="close close-bg-0" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" class=""><i class="icon pe-close"></i></span></button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-sm-6 col-md-6">
            <div class="panel panel-bordered">
              <div class="panel-heading text-center">
                <h3 class="panel-title"><spring:message code="label.bank.account"/></h3></div>
              <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
              <div class="panel-body text-center pt-0"><a href="/bank/bank-inset" class="btn btn-primary btn-sm"> <spring:message code="common.btn.add"/><i class="icon wb-arrow-right ml-10"></i> </a></div>
            </div>
          </div>
          <div class="col-sm-6 col-md-6">
            <div class="panel panel-bordered">
              <div class="panel-heading text-center">
                <h3 class="panel-title"><spring:message code="label.bank.card"/></h3></div>
              <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
              <div class="panel-body text-center pt-0"><a href="/bank/themTheNganHang" class="btn btn-primary btn-sm"> <spring:message code="common.btn.add"/><i class="icon wb-arrow-right ml-10"></i> </a></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- footer -->
<%--<c:import url="../include_page/footer.jsp"/>--%>
<!-- /footer -->
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
<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
    });
  })(document, window, jQuery);
</script>

</body>

</html>