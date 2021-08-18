<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.fundin.UnLinkBankController.UNLINK_BANK_CONTROLLER_FUNDIN" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="account.bank.title.form"/> - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="fiUnLinkbank"/>
</jsp:include>
<!-- /menu bar -->
<c:url var="pinCodeInfoUri" value="<%=UNLINK_BANK_CONTROLLER_FUNDIN%>"></c:url>

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage">Nạp thẻ</a></li>
      <li class="breadcrumb-item"><a href="/bank/manage">UnLinkBank</a></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.fundin.no.link.bank"/></h1>
  </div>
  <div class="page-content container-fluid">


    <div class="panel">
      <div class="panel-heading">
        <h3 class="panel-title"><spring:message code="label.bank"/></h3>
      </div>
      <div class="panel-body">
        <form action="/unlink/fundInUnLinkBank" method="get">
          <div class="row">
            <c:forEach items="${listBank}" var="item">
              <div class="mb-10 item-card">
                <span class="phoneCharge-choose">
                  <label>
                    <button type="submit">
                     <%--<a href="${pinCodeInfoUri}">--%>
                   <img alt="${item.bankName}" src="/assets/images/bank/${item.bankCode}.png">
                     <%--</a>--%>
                    <%--<label id="bankName"  name="bankName">${item.bankName}</label>--%>
                       <p id="bankName">${item.bankName}</p>
                      </button>

                  </label>
                </span>
              </div>
            </c:forEach>
            <input type="text" id="hidden_bankName" name="bankName" value="${bankName}">
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
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

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

<script>
  //  get serviceCode item by alt img
  $(document).ready(function () {
    $(".item-bank").click(function (e) {
      e.preventDefault();
      var alt = $(this).find("img").attr("alt");
      var x = document.getElementById('bankName').innerHTML;
      document.getElementById("hidden_bankName").value= x;

    });
  });
  //end
</script>
</html>