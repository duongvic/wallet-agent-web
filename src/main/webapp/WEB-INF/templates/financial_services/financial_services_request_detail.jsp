<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="fundorder.request.detail.window.title"/></title>
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
  <spring:message code="label.financial.fe.credit" var="label_financial_services_method"/>
</c:if>

<c:if test="${'Homecredit' eq financial_services_method}">
  <jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="dv_Homecredit"/>
  </jsp:include>
  <spring:message code="label.financial.home.credit" var="label_financial_services_method"/>
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

<spring:message code="error.amount.greater.balance" var="errorAmountGreaterBalance"/>

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message
          code="label.manage"/></li>
      <li class="breadcrumb-item"><spring:message code="label.financial.services"/> </li>
    </ol>
    <h1 class="page-title">${label_financial_services_method}</h1>
  </div>
  <div class="page-content container-fluid">
    <div class="panel mb-0 panel-bordered">
      <form class="form-horizontal" method="post" action="/financial-services/${financial_services_method}/management/confirm">
        <div class="panel-body">
          <div class="form-group row mb-10">
            <div class="full-width">
              <h3 class="panel-title pl-0"><spring:message code="label.bill.payment.customer.info"/></h3>
              <div class="clr"></div>
            </div>
          </div>

          <!-- Full Name -->
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message
                code="label.bill.payment.full.name"/></label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0">${full_name}</p>
            </div>
          </div>

          <!--Phone  -->
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message
                    code="label.phone.number"/></label>
            <div class=" col-sm-9">
              <input type="text" id="customerPhone"
                     name="customerPhone" maxlength="10"
                     onkeypress="return isNumberKey(event)"
                     class="form-control">
            </div>
          </div>

          <!-- payment amount need -->
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message
                code="label.bill.payment.amount.need.to.pay"/></label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0 currency-input">${amount}</p>
            </div>
          </div>

          <!-- Hạn thanh toán -->
          <c:if test="${due_date ne null}">
            <div class="form-group row mb-5">
              <label class=" col-sm-3 col-form-label pb-0"><spring:message
                      code="label.bill.payment.term"/></label>
              <div class=" col-sm-9 ">
                <p class="form-control-plaintext pb-0"><fmt:formatDate value="${due_date}" pattern="dd/MM/yyyy"/></p>
                  <%--<input type="hidden" name="order_channel" value="${order_channel}">--%>
              </div>
            </div>
          </c:if>


          <!-- CMND -->
          <div class="form-group row mb-5">
            <label class=" col-sm-3 col-form-label pb-0"><spring:message
                code="label.financial.services.cmt"/></label>
            <div class=" col-sm-9 ">
              <p class="form-control-plaintext pb-0">${cmt}</p>
            </div>
          </div>
        </div>
        <c:if test="${is_partial_payment_allowed eq true}">
          <%--Nhập số tiền thanh toán--%>
          <div class="panel-body">
            <div class="erroAccountNumber"></div>
            <div class="form-group row mb-5">
              <label class=" col-sm-3 col-form-label pb-0"><spring:message
                  code="label.enter.the.payment.amount"/></label>
              <div class=" col-sm-9">
                <input type="text" class="form-control currency-input" name="enter_amount" id="enter_amount"
                      <%--value="${ewallet:formatNumber(amount)}"--%>
                       value="${amount}"
                       placeholder=""
                       required/>
              </div>
            </div>
          </div>
        </c:if>

        <div class="panel-body">
          <div class="form-group row mb-5">
            <button type="submit" class="btn btn-primary btn-sm pull-right"><spring:message
                code="common.btn.confirm"/></button>
          </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden"  value="${ewallet:formatNumber(userLogin.balance)}" id="balanceUser"/>
      </form>
    </div>
  </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->

<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
      // $(".currency-input").val(formatCurrency(currencyToNumber($(".currency-input").val())));
        $("#enter_amount").change(function(){
      //alert(1);
            var enterAmount = parseFloat(currencyToNumber(document.getElementById('enter_amount').value));
            var balanceUser = parseFloat(currencyToNumber(document.getElementById('balanceUser').value));
            if(balanceUser-enterAmount<0){
                var $newErrorAmountGreaterBalance= $("<div id=\"new-account-error\" class=\"form-group row\">\n"
                    + "              <div class=\"col-sm-4 col-md-3\">\n"
                    + "                <label class=\"form-control-label\"></label>\n"
                    + "              </div>\n"
                    + "              <div class=\"col-sm-8 col-md-9\">\n"
                    + "                <div class=\"text-danger error-message mb-10\">\n"
                    + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;" + "${errorAmountGreaterBalance}" + " </small>\n"
                    + "                </div>\n"
                    + "              </div>\n"
                    + "            </div>");
                // $('.d').after($('.c').text($newAccountError));
                // $newAccountError.insertAfter($('.erroAccountNumber'));
                $('.erroAccountNumber').html($newErrorAmountGreaterBalance);
                $('button[type="submit"]').attr("disabled", true);
            }else{
                $('.erroAccountNumber').empty();
                $('button[type="submit"]').attr("disabled", false);
            }
        });
    });

  })(document, window, jQuery);
</script>
</body>
<spring:message code="error.amount.greater.balance" var="errorAmountGreaterBalance"/>
</html>