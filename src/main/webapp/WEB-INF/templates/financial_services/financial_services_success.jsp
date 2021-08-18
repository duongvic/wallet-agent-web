<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>
    <c:if test="${'FEcredit' eq financial_services_method}">
      <spring:message code="label.financial.fe.credit" var="label_financial_services_method"/>
    </c:if>
    <c:if test="${'Homecredit' eq financial_services_method}">
      <spring:message code="label.financial.home.credit" var="label_financial_services_method"/>
    </c:if>
  </title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">

<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<c:if test="${'FEcredit' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_FEcredit"/>
  </jsp:include>
</c:if>

<c:if test="${'Homecredit' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_Homecredit"/>
  </jsp:include>
</c:if>

<c:if test="${'Acs' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_Acs"/>
  </jsp:include>
  <spring:message code="label.financial.acs" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Ocb' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_OcsBank"/>
  </jsp:include>
  <spring:message code="label.financial.ocb" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Prudential' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_Prudential"/>
  </jsp:include>
  <spring:message code="label.financial.prudential" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Shinhan' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_Shinhan"/>
  </jsp:include>
  <spring:message code="label.financial.Shinhan" var="label_financial_services_method"/>
</c:if>

<c:if test="${'MCredit' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_MCredit"/>
  </jsp:include>
  <spring:message code="label.financial.mcredit" var="label_financial_services_method"/>
</c:if>

<c:if test="${'MiraeAsset' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_MiraeAsset"/>
  </jsp:include>
  <spring:message code="label.financial.mirae.asset" var="label_financial_services_method"/>
</c:if>

<c:if test="${'AtmOnline' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_AtmOnline"/>
  </jsp:include>
  <spring:message code="label.financial.atm.online" var="label_financial_services_method"/>
</c:if>

<c:if test="${'DrDong' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_DrDong"/>
  </jsp:include>
  <spring:message code="label.financial.dr.dong" var="label_financial_services_method"/>
</c:if>

<c:if test="${'PTI' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_PTI"/>
  </jsp:include>
  <spring:message code="label.financial.pti" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Maritime' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_Maritime"/>
  </jsp:include>
  <spring:message code="label.financial.maritime.bank" var="label_financial_services_method"/>
</c:if>
<!-- /menu bar -->



<div class="page page-email">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="label.manage"/></li>
      <li class="breadcrumb-item"><spring:message code="label.financial.services"/> </li>
    </ol>
    <h3 class="page-title">${label_financial_services_method}</h3>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

              <div class="row mb-20">
                <h4><spring:message code="label.real.amount"/></h4>
              </div>
              <div class="row mb-20">
                <div
                    class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                  <i class="icon pe-check badge-success br-100 fs40"></i>
                  <p class="mb-0">Bạn vừa thanh toán thành công.</p> </br>
                  <br>
                </div>
                <div class="col-md-12 col-sm-12 text-center">
                  <p>Transaction ID: ${obj_transaction.id}</p>
                </div>
              </div>

              <div class="clr"></div>

              <div class="row justify-content-center">
                <div class="form-inline">
                  <div class="form-group">
                    <button type="button" class="btn btn-primary btn-sm" onclick="printElement()">
                      In thường
                    </button>
                  </div>
                  <div class="form-group">
                    <a class="btn btn-primary btn-sm"
                    href="/dashboard/index"><spring:message code="common.btn.back.home"/>
                    </a>
                  </div>
                </div>
              </div>

              <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <%--</form>--%>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>

<div class="printer">
  <%--bill_template--%>
  <jsp:include page="../include_page/finance_bill_template.jsp"/>
  <%--bill_template--%>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>
<script>
</script>
</html>