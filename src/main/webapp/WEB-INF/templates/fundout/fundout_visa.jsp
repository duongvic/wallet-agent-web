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
  <title>Rút tiền qua VISA - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="foVisa"/>
</jsp:include>
<!-- /menu bar -->

<div class="page page-email">
  <div class="page-header">
    <%--<div class="page-header-actions"><a href="/bank/manage">Hủy</a></div>--%>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage"><spring:message code="card.manage.label"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="common.btn.add"/></li>
    </ol>
    <h1 class="page-title">Thêm thẻ ngân hàng</h1></div>
  <div class="page-content">
    <div class="panel">
      <div class="panel-body">
        <div class="mb-20"><img src="/assets/images/placeholder200x120.png" width="100" class="br3 pull-left">
          <h4 class="pull-left mt-15 ml-20">Ngân hàng TMCP Ngoại Thương - Vietcombank</h4>
          <div class="clr"></div>
        </div>
        <form action="themTheVisa" method="post">
          <div class="form-group row">
            <div class="col-md-6">
              <label class="form-control-label" for="inputCredit"><spring:message code="account.bank.card.number"/></label>
              <input type="text" class="form-control card-number-input" id="inputCredit" name="inputCredit" data-plugin="formatter" data-pattern="[[9999]]-[[9999]]-[[9999]]-[[9999]]"/>
              <p class="text-help">1234-1234-1234-1234</p>
            </div>
            <div class="col-md-6">
              <label class="form-control-label" for="ownAccount"><spring:message code="account.bank.holder"/></label>
              <input type="text" class="form-control ctk" id="ownAccount" name="ownAccount"/>
              <p class="text-help">VD: DANG MINH PHUONG</p>
            </div>
          </div>
          <div class="form-group row">
            <div class="col-md-6">
              <label class="form-control-label" for="releaseYear">Năm phát hành</label>
              <input type="text" class="form-control" id="releaseYear" name="releaseYear" data-plugin="formatter" data-pattern="[[99]]/[[99]]"/>
              <p class="text-help">DD/YY</p>
            </div>
            <div class="col-md-6">
              <label class="form-control-label" for="cvvNumber">Số CVV</label>
              <input type="text" class="form-control" id="cvvNumber" name="cvvNumber" data-plugin="formatter" data-pattern="[[999]] "/>
              <p class="text-help">CVV là 3 số đằng sau thẻ </p>
            </div>
          </div>
          <div class="text-right">
            <a href="/bank/manage" class="btn btn-default mr-10"><spring:message code="common.btn.back"/></a>
            <button type="submit" class="btn btn-primary"><spring:message code="common.btn.add"/></button>
            <%--<a href="/bank/maxacthuc" class="btn btn-primary"></a>--%>
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

</html>