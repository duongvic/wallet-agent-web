<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ewallet" uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
	<%
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
<title><spring:message code="transaction.api.title.page"/> - <spring:message code="common.company"/> </title>
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

	<div class="page page-email">
		<div class="page-header">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
				<li class="breadcrumb-item active"><spring:message code="transaction.api.bread.crumb"/></li>
			</ol>
			<h1 class="page-title"><spring:message code="menu.transaction.history"/></h1>
		</div>
		<div class="page-content container-fluid">
			<div class="panel panel-bordered">
				<div class="panel-heading">
					<h3 class="panel-title"><spring:message code="menu.pincode.view.title"/> ${transactionId}</h3>
				</div>
				<div class="panel-body py-10">
					<form method="post" action="/trans-log/view-pincode">
						<div class="row">
							<div class="col-md-6 pl-0">
								<div class="col-md-12">
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="transaction.title.transaction.id"/>
										</label>
										<div class=" col-sm-6  text-right">
											<p class="form-control-plaintext pb-0">${transactionId}</p>
										</div>
									</div>
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="transaction.api.detail.time"/>
										</label>
										<div class=" col-sm-6 text-right">
											<p class="form-control-plaintext pb-0">${creationDate}</p>
										</div>
									</div>
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="transaction.api.detail.service"/>
										</label>
										<div class=" col-sm-6 text-right">
											<p class="form-control-plaintext pb-0">${serviceType}</p>
										</div>
									</div>
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="transaction.api.detail.product"/>
										</label>
										<div class=" col-sm-6 text-right">
											<p class="form-control-plaintext pb-0">${serviceName}</p>
										</div>
									</div>
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="transaction.api.detail.actor"/>
										</label>
										<div class=" col-sm-6 text-right">
											<p class="form-control-plaintext pb-0">${phoneNumber}</p>
										</div>
									</div>
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="transaction.api.detail.content"/>
										</label>
										<div class=" col-sm-6 text-right">
											<p class="form-control-plaintext pb-0">${orderInfo}</p>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6 pr-0">
								<div class="col-md-12">
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0"><spring:message code="label.face.value"/>:
										</label>
										<div class=" col-sm-6  text-right">
											<p class="form-control-plaintext pb-0 vnd">${ewallet:formatNumber(amount)}</p>
										</div>
									</div>
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="transaction.api.detail.commission"/>
										</label>
										<div class=" col-sm-6 text-right">
											<p class="form-control-plaintext pb-0">${ewallet:formatNumber(commision)}&nbsp;Zpoint</p>
										</div>
									</div>
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="label.fee.amount"/>:
										</label>
										<div class=" col-sm-6 text-right">
											<p class="form-control-plaintext pb-0"><spring:message code="label.free"/></p>
										</div>
									</div>
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="transaction.api.detail.total.amount"/>
										</label>
										<div class=" col-sm-6 text-right">
											<p class="form-control-plaintext pb-0">${ewallet:formatNumber(realAmount)}</p>
											<%--<p class="form-control-plaintext pb-0 vnd">${ewallet:formatNumber(realAmount)}</p>--%>
										</div>
									</div>
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="transaction.api.detail.method"/>
										</label>
										<div class=" col-sm-6 text-right">
											<p class="form-control-plaintext pb-0">
												<spring:message code="label.ewallet"/>
											</p>
										</div>
									</div>
									<div class="form-group row mb-5">
										<label class=" col-sm-6 col-form-label pb-0">
											<spring:message code="help.transaction.status"/>
										</label>
										<div class=" col-sm-6 text-right">
											<p class="form-control-plaintext pb-0">${errorMessage}</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<br/>
						<div class="row">
							<!-- Login form -->
							<jsp:include page="../include_component/frame_login_form.jsp"></jsp:include>
							<!-- /Login form -->
						</div>
						<%--<div class="form-group mt-30 hidden">--%>
							<%--<h4 class="example-title">Nhập mã OTP</h4>--%>
							<%--<p class="mb-5">--%>
								<%--Vui lòng nhập mã OTP gửi vào số điện thoại <a href="#">0902244419</a>--%>
							<%--</p>--%>
							<%--<div class="form-group mb-0">--%>
								<%--<div class="input-group">--%>
									<%--<input type="text" class="form-control" placeholder="Mã OTP">--%>
									<%--<span class="input-group-btn">--%>
										<%--<button type="button" class="btn btn-default btn-outline">--%>
											<%--<i class="fa fa-rotate-left"></i> <spring:message code="common.btn.resend"/>--%>
										<%--</button>--%>
									<%--</span>--%>
								<%--</div>--%>
							<%--</div>--%>
						<%--</div>--%>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div class="panel-footer py-10 text-right hidden">
							<button type="submit"
							   class="btn btn-primary btn-sm ml-10"><spring:message code="transaction.api.transaction.status.view.card.code"/></button>
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