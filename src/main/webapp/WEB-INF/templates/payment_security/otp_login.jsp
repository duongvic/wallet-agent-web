<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="static vn.mog.ewallet.consumer.web.controller.system.PaymentSecurityController.PAYMENT_SECURITY"%>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
	<%
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
<title>Cài đặt bảo mật EMAIL - <spring:message code="common.company"/></title>
<!-- head libs  -->
<c:import url="../include_page/head.jsp" />
<!-- /head libs  -->
</head>

<body class="animsition">
	<!-- notification -->
	<jsp:include page="../include_page/notification.jsp" />
	<!-- /notification --

<!-- menu bar -->
	<jsp:include page="../include_page/menu_bar.jsp" />
	<!-- /menu bar -->

	<c:url var="dashBoardControlUri" value="<%=PAYMENT_SECURITY%>"></c:url>

	<div class="page page-email">
		<div class="page-header">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/dashboard/index">Trang
						chủ</a></li>
				<li class="breadcrumb-item active">Cài đặt bảo mật</li>
			</ol>
			<h1 class="page-title">Cài đặt bảo mật</h1>
		</div>
		<div class="page-content container-fluid">
			<div class="panel panel-bordered">
				<div class="panel-heading">
					<h3 class="panel-title">Đổi hạn mức OTP SMS</h3>
				</div>
				<div class="panel-body py-10">
					<form method="get"
						action="/system/payment-security/sms/change-limitation">
						<div class="row">
							<!-- Login form -->
							<jsp:include page="../include_component/frame_login_form.jsp"></jsp:include>
							<!-- /Login form -->
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<c:import url="../include_page/footer.jsp" />
	<!-- /footer -->
</body>

</html>