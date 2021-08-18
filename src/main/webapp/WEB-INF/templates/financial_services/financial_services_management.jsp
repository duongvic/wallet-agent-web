<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="label.bill.payment"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->


  <style type="text/css">

    input:required:invalid, input:focus:invalid {
      background-image: url(/assets/images/invalid.png);
      background-position: right center;
      background-repeat: no-repeat;
    }

    input:required:valid {
      background-image: url(/assets/images/valid.png);
      background-position: right center;
      background-repeat: no-repeat;
    }


  </style>
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification -->

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

<spring:message code="common.invalid.input.text.4.22" var="label_invalid_text_number"></spring:message>

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message
          code="label.manage"/></li>
      <li class="breadcrumb-item"><spring:message code="label.financial.services"/> </li>
    </ol>
    <h1 class="page-title"><spring:message code="label.financial.services"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-12">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">
            <div class="row col mb-20">
              <div class="">
                <img src="/assets/images/icon/payment/${financial_services_method}.png" width="100"
                     class="br3 pull-left" onerror="imgError(this)">
              </div>
              <div class="col-8 col-sm-7 mb-3">
                <div class="col-10 col-sm-7 mb-3">
                    <span class="h4">
                        <spring:message code="label.bill.payment.service.provider"/>
                    </span>
                  </br>
                  <span class="small txt-note-pin">${label_financial_services_method}</span>
                  <div class="clr"></div>
                </div>
                <div class="clr"></div>
              </div>
            </div>

            <div class="row">
              <div class="col-lg-12 col-md-8 mb-3">
                <label>
                  <spring:message code="label.bill.payment.enter.invoice.code"/>
                </label>
              </div>
              <c:if test="${(codeErr != null)}">
                <div class="text-danger error-message">
                  <small><i class="fa fa-times-circle"></i>${codeErr}
                  </small>
                </div>
              </c:if>
              <c:choose>
                <c:when test="${'FEcredit' eq financial_services_method || 'Homecredit' eq financial_services_method }">
                  <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                    <div class="input-group">
                      <input type="text" id="invoiceCode"
                             name="invoiceCode"
                             class="form-control"
                             required onchange="getValueInvoice(this)"
                             maxlength="22" onkeypress="return isNumberKey(event)"
                             title="${label_invalid_text_number}" required>

                        <%--<input type="text" id="hidden_requestId" name="requestId"/>--%>
                    </div>
                  </div>
                </c:when>
                <c:otherwise>
                  <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                    <div class="input-group">
                      <input type="text" id="invoiceCode"
                             name="invoiceCode"
                             class="form-control"
                             required onchange="getValueInvoice(this)"
                             maxlength="22"
                             title="${label_invalid_text_number}" required>
                    </div>
                  </div>
                </c:otherwise>
              </c:choose>
            </div>

            <div class="row">
              <div class="col-md-9 offset-md-3">
                <div class="pull-right">
                  <button type="button" id="financial_btn" class="btn btn-primary btn-sm pull-right" autofocus="">
                    <spring:message code="common.btn.next"/>
                    <i class="icon wb-arrow-right ml-10"></i>
                  </button>
                </div>
              </div>
            </div>


          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<jsp:include page="../include_page/footer.jsp"/>
<!-- /footer -->
<jsp:include page="../include_page/js_daterangepicker.jsp"/>

</body>
<script type="text/javascript">
  $(document).ready(function () {
  });

  function getValueInvoice(elem) {
    var _requestId = elem.value;
    $('#invoiceCode').val(_requestId);
//    var k = $('#hidden_requestId').val(x);
    //    console.log(k[0].value)

    if (_requestId.length < 4) {
      var $newErrorInvoiceEnter = $("<div id=\"new-invoice-error\">\n"
          + "              <div class=\"col-12\">\n"
          + "                <div class=\"text-danger error-message mb-10\">\n"
          + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;" + "${label_invalid_text_number}" + " </small>\n"
          + "                </div>\n"
          + "              </div>\n"
          + "            </div>");

      $('.errorInvoiceEnter').html($newErrorInvoiceEnter);
      $('button[type="button"]').attr("disabled", true);
    } else {
      $('.errorInvoiceEnter').empty();
      $('button[type="button"]').attr("disabled", false);
      $('#financial_btn').attr("onclick", "window.location.replace('/financial-services/${financial_services_method}/management/detail-".concat(_requestId).concat("')"));
    }
  }

  function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
    else {
      return true;
    }
  }
</script>

<%--Handle error images--%>
<script>
  function imgError(image) {
//    image.src = "/assets/images/placeholder100.png";
  }
</script>
</html>