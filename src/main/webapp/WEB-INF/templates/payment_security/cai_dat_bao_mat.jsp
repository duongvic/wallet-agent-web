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
  <title><spring:message code="payment.security.title"/>-<spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">

<!-- notification -->
<jsp:include page="../include_page/notification.jsp">
  <jsp:param name="secure_page" value="true"/>
</jsp:include>
<!-- /notification -->

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <jsp:param name="nav" value="paySec"/>
</jsp:include>
  <%--<jsp:param name="nav" value="paySec"/>--%>

<!-- /menu bar -->

<div class="page page-email">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item">
        <a href="/dashboard/index">
          <spring:message code="dashboard.home"/>
        </a>
      </li>
      <li class="breadcrumb-item active">
        <a href="javascript:void(0)" for="bread_crumb_form_submit" style="">
          <spring:message code="payment.security.setting"/>
        </a>
      </li>
    </ol>
    <h1 class="page-title"><spring:message code="payment.security.setting"/></h1>
  </div>
  <%--<div class="page-content container-fluid">--%>
    <%--<div class="row">--%>

      <%--<!-- OTP SMS -->--%>
      <%--<jsp:include page="otp_sms.jsp"/>--%>
      <%--<!-- /OTP SMS -->--%>

      <%--<!-- OTP Email -->--%>
      <%--&lt;%&ndash;<jsp:include page="otp_email.jsp"/>&ndash;%&gt;--%>
      <%--<!-- /OTP Email -->--%>

      <%--<!-- OTP Application -->--%>
      <%--&lt;%&ndash;<jsp:include page="otp_application.jsp"/>&ndash;%&gt;--%>
      <%--<!-- /OTP Application -->--%>
    <%--</div>--%>
  <%--</div>--%>
  <div class="page-content">
    <%--<!-- OTP SMS -->--%>
    <%--<jsp:include page="otp_sms.jsp"/>--%>
    <%--<!-- /OTP SMS -->--%>
      <jsp:include page="select_security_setting.jsp"/>
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
    });
  })(document, window, jQuery);

  $(document).ready(function() {
	  $("#otp-application-btn").prop('disabled', true);
	  $('#otp-email-btn').click(function(e) {
		    e.preventDefault();
		});
  });
</script>

</body>

</html>