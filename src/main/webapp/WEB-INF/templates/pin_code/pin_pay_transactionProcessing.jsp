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
  <title><spring:message code="label.epin"/> - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="pinCodeMenu"/>
</jsp:include>
<!-- /menu bar -->


<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><a href="quanlythe.html"><spring:message code="label.epin"/></a></li>
    </ol>
    <h3 class="page-title"><spring:message code="label.epin"/></h3>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-12">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <form class="form-horizontal" method="post" action="/pin-code/pinPayTransactionSuccess">

              <div class="row mb-20">
                <h4><spring:message code="label.real.amount"/></h4>
              </div>
              <div class="row mb-20">
                <div
                    class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                  <i class="icon pe-attention badge-warning br-100 fs40"></i>
                  <p class="mb-0">Giao dịch đang được xử lý</p>
                  <br>
                </div>
              </div>

              <div class="clr"></div>

              <div class="row justify-content-center">
                <div class="form-inline">
                  <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-sm"> Hỗ trợ qua hotline
                    </button>
                  </div>
                  <div class="form-group">
                    <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalHelpTransaction"> Hỗ trợ qua email
                    </button>
                  </div>
                </div>
              </div>

              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<c:import url="../dialog_modal/pin_code/dialog_helpTransaction.jsp"/>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>
<script>


  var $tbl = $('#exampleTableTools');
  var $bodychk = $tbl.find('tbody input:checkbox');

  $bodychk.on('change', function () {
    if ($(this).is(':checked')) {
      $(this).closest('tr').addClass('bgCheckbox');
    }
    else {
      $(this).closest('tr').removeClass('bgCheckbox');
    }
  });

  //  check all dataTable
  $tbl.find('thead input:checkbox').change(function () {
    var c = this.checked;
    $bodychk.prop('checked', c);
    $bodychk.trigger('change');
  });


</script>
</html>