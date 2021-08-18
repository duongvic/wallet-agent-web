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
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification -->

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <%--<jsp:param name="nav" value="payPin"/>--%>
</jsp:include>
  <%--<jsp:param name="nav" value="paySec"/>--%>

<!-- /menu bar -->

<div class="page page-email">
  <!-- user name -->
  <jsp:include page="../include_page/user_name.jsp"/>
  <!-- /user name -->

  <div class="page-content">
    <%--<!-- Paymen Pin -->--%>
    <jsp:include page="payment_pin_setting.jsp"/>
    <%--<!-- /Paymen Pin -->--%>
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