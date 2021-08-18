<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.fundin.UnLinkBankController.UNLINK_BANK_CONTROLLER_FUNDIN" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.fundin.UnLinkBankController.REDIRECT_FUNDIN_NOLNIKBANK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
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
  <jsp:param name="nav" value="foNoLinkbank"/>
</jsp:include>
<!-- /menu bar -->


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
        <h3 class="panel-title">Ngân hàng</h3>
      </div>
      <div class="panel-body">
        <div class="row">
          <c:forEach items="${listBank}" var="item">
            <div class="mb-10 item-card" name="itemCard">
                <span class="phoneCharge-choose">
                  <label>
                      <a href="/fundout/no-link-bank/info?_nameBank=${item.bankCode}">
                   <img alt="${item.bankName}" src="/assets/images/bank/${item.bankCode}.png">
                     </a>
                  </label>
                </span>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>

  </div>
</div>





<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

</html>