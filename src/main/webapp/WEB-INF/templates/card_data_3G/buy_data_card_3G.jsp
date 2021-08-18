<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="label.game.code"/> - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">

<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<jsp:include page="../include_component/service_code_constants.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <jsp:param name="nav" value="maTheData"/>
</jsp:include>
<!-- /menu bar -->


<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><spring:message code="label.game.code.service"/></li>
      <li class="breadcrumb-item"><spring:message code="label.card.code.data"/></li>
    </ol>
    <h3 class="page-title"><spring:message code="label.card.code.data"/></h3>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">
            <form class="form-horizontal" method="post" action="/card-data/pin-pay-verify">
              <div class="form-group row mb-10">
                <div class="full-width">
                  <h4 class="panel-title pl-0"><spring:message code="label.order.info"/></h4>
                  <div class="clr"></div>
                </div>
              </div>

              <div class="row">
                <c:forEach var="enabledService" items="${enabledServices}">
                  <c:choose>
                    <c:when test="${'040118' eq enabledService.serviceCode}">
                      <c:set var="serviceName" value="3GViettel"/>
                    </c:when>
                    <c:when test="${'040117' eq enabledService.serviceCode}">
                      <c:set var="serviceName" value="3GMobi"/>
                    </c:when>
                    <c:when test="${'040116' eq enabledService.serviceCode}">
                      <c:set var="serviceName" value="3GVina"/>
                    </c:when>
                  </c:choose>
                  <div class="mb-10 item-card">
                    <span class="phoneCharge-choose">
                      <label ${(tenSP == null || tenSP eq serviceName) ? 'class="current"' : ''}>
                        <img alt="${serviceName}" src="/assets/images/${serviceName}.png">
                      </label>
                    </span>
                  </div>
                </c:forEach>
              </div>


              <div class="form-group row mb-10 pos-relative">
                <span class="select-group__lable"><spring:message code="epin.card.value"/>:</span>
                <select data-plugin="select2" class="form-control" id="faceValue" name="faceValue"
                        onchange="thanhToanThe(this)">
                </select>
              </div>
              <div class="form-group row mb-10 pos-relative">
                <div class="input-group">
                  <spring:message code="epin.card.validate.1to100" var="validate_max_card_string"/>
                  <span class="input-group-label"><spring:message code="label.quantity"/>:</span>
                  <input type="text" class="form-control"
                         pattern="^(([1-5]){1})"
                         style="padding-left: 6rem;color: black;font-weight: 400;" name="quantity"
                         id="quantity" value="1"
                         onkeypress="return isNumberKey(event)"
                         onchange="thanhToan(this)"
                         title="${validate_max_card_string}" required>
                  <div class="input-group-append">
                      <span class="input-group-text"><button type="button"
                                                             class="btn-add fa fa-minus"
                                                             onclick="quantityMinus()"></button></span>
                    <span class="input-group-text"><button type="button" class="btn-add fa fa-plus"
                                                           onclick="quantityPlus()"></button></span>
                  </div>
                </div>
              </div>
              <div class="row">
                <label><spring:message code="label.discount"/>:</label>&nbsp;
                <span class="text-amount" id="disCount">-- Zpoint</span>
              </div>
              <div class="row">
                <label><spring:message code="label.real.amount"/>:</label>&nbsp;
                <span class="text-amount" id="realAmount">-- Zpoint</span>
              </div>
              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit" class="btn btn-primary btn-sm pull-right"><spring:message
                      code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i>
                  </button>
                </div>
              </div>

              <%--request dữ liệu--%>
              <input type="hidden" id="hidden_serviceCode" name="serviceCodeCard"
                     value="${serviceCodeCard}">
              <input type="hidden" id="hidden_disCount" name="disCount" value="${disCount}">
              <input type="hidden" id="hidden_realAmount" name="realAmount" value="${realAmount}">
              <input type="hidden" id="hidden_tenSP" name="tenSP" value="${tenSP}">
              <input type="hidden" id="disCountPercent" name="disCountPercent"
                     value="${disCountPercent}">
              <%--end request dữ liệu--%>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import url="../include_component/frame_last_transaction_pincode.jsp"/>
        <!-- /Giao dịch gần nhất  -->
      </div>
    </div>
  </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>
<script>
  var currentCommission = {};
  //  get serviceCode item by alt img
  $(document).ready(function () {
    //  fix select2 padding group
    $('.select2-container--default .select2-selection--single .select2-selection__rendered ').addClass(
        'select-group');

    loadDataCardCommission("${tenSP != null ? tenSP : '3GViettel'}", $("#faceValue"), '',
        "${tprice_pincode != null ? tprice_pincode : 40000}", "${_csrf.parameterName}",
        "${_csrf.token}");

    $(".phoneCharge-choose label").click(function (e) {
      e.preventDefault();
      var alt = $(this).find("img").attr("alt");
      document.getElementById("hidden_tenSP").value = alt;
      if (alt == "3GViettel") {
        document.getElementById("hidden_serviceCode").value = "${viettel3GServiceCode}";
      }
      if (alt == "3GVina") {
        document.getElementById("hidden_serviceCode").value = "${vina3GServiceCode}";
      }
      if (alt == "3GMobi") {
        document.getElementById("hidden_serviceCode").value = "${mobile3GServiceCode}";
      }
      //thêm class khi thẻ active
      $('.phoneCharge-choose label.current').removeClass('current');
      $(this).closest('.phoneCharge-choose label').addClass('current');

      loadDataCardCommission(alt, $("#faceValue"), '', $("#faceValue").val(), "${_csrf.parameterName}",
          "${_csrf.token}");
    });

  });

  //end

  function thanhToan() {
    var sumAmount = $('#faceValue').val() * $('#quantity').val();
    $('#disCount').html(formatCurrency(sumAmount * currentCommission[$('#faceValue').val()].commision / 100).concat(' ').concat('Zpoint'));
    var realAmount =sumAmount - currencyToNumber($('#disCount').html());
    $('#realAmount').html(formatCurrency(realAmount).concat(' ').concat('Zpoint'));

    $('#hidden_disCount').val($('#disCount').html());
    $('#hidden_realAmount').val(realAmount);
  }

  function thanhToanThe(price) {
    var commision = currentCommission[price.value].commision;
    var realAmount = $('#faceValue').val() * $('#quantity').val();
    $('#disCount').html(formatCurrency(parseInt(parseInt(realAmount * commision / 100 + currentCommission[price.value].commisionFixedAmount))));
    $('#disCountPercent').val(commision);
    thanhToan();
  }

  function quantityMinus() {
    var vlQuantity = document.getElementById('quantity');
    if (vlQuantity.value === '' || vlQuantity.value === null) {
      vlQuantity.value = 1;
    } else {
      vlQuantity.value = parseInt(vlQuantity.value) - 1;
      if (vlQuantity.value <= 1) {
        vlQuantity.value = 1;
      }
    }
    thanhToan();
  }

  function quantityPlus() {
    var valQuantity = document.getElementById('quantity');
    if (valQuantity.value === '' || valQuantity.value === null) {
      valQuantity.value = 1;
    } else {
      valQuantity.value = parseInt(valQuantity.value) + 1;
    }
    thanhToan()
  }

  function onLoadCommissionToModel(commissionType, data, commissionFee, prices) {
    currentCommission = commissionFee;
    $('#disCount').html(formatCurrency(
        parseInt(parseInt($('#faceValue').val() * $('#quantity').val())
            * currentCommission[prices[0]].commision / 100
            + currentCommission[prices[0]].commisionFixedAmount)));
    thanhToan();
    var serviceDesc = data.serviceDesc.split(" ");
    $('#disCountPercent').val(currentCommission[prices[0]].commision);
  }

</script>
</html>