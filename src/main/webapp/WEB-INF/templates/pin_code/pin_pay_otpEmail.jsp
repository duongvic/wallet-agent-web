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
  <jsp:param name="nav" value="pinCode"/>
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
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <%--<form class="form-horizontal" method="post" action="/pin-code/pinPayTransactionSuccess">--%>
              <%--<form class="form-horizontal" method="post" action="/pin-code/pinPayTransactionErro">--%>
                <form class="form-horizontal" method="post" action="/pin-code/pinPayTransactionProcessing">
              <div class="row mb-20">
                <div class="">
                  <img src="/assets/images/placeholder200x120.png" width="100"
                       class="br3 pull-left">
                </div>
                <%--<div class="col-8 col-sm-7 mb-3">--%>
                  <%--<div class="col-10 col-sm-7 mb-3">--%>
                    <%--<span class="h4"><spring:message code="label.ewallet"/></span>--%>
                    <%--</br>--%>
                    <%--<span class="small txt-note-pin"><spring:message code="label.payment.free"/></span>--%>
                    <%--<div class="clr"></div>--%>
                  <%--</div>--%>
                  <%--<div class="clr"></div>--%>
                <%--</div>--%>
              </div>


              <div class="row">
                <div class="col-lg-12 col-md-8 mb-3">
                  <label>Vui lòng nhập mã OTP đã được gửi vào email
                    <span style="color: #27ADD0">manhtuongdz@gmail.com</span>
                  </label>
                </div>
                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="<spring:message code="label.enter.otp"/>">
                    <span class="input-group-btn">
                                                <button type="button"
                                                        class="btn btn-default btn-outline">
                                                    <i class="fa fa-rotate-left"></i> <spring:message code="common.btn.resend"/></button>
                                              </span>
                  </div>
                </div>
              </div>


              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit" class="btn btn-primary btn-sm pull-right"> <spring:message code="common.btn.confirm"/>
                  </button>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import url="../include_component/frame_information_transaction.jsp"/>
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
  function validateSTK() {
    var x = document.getElementById("taiKhoan").value;
    var y = x.replace(/[-]+/g, "");
    var z = y.replace(/[A-Za-z0-9]{4}(?!$)/g, "\$&" + "-");
    document.getElementById("taiKhoan").value = z;
  }
</script>
</html>