<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib
	uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
	prefix="ewallet"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="col-md-12 col-small-12">
	<div class="panel-heading">
		<div class="full-width">
			<h4 class="panel-title"><spring:message code="label.login"/></h4>
			<div class="clr"></div>
		</div>
	</div>

	<%--<div class="form-group row mb-10">--%>
		<%--<label class="control-label col-sm-4 col-md-3 col-lg-3"--%>
			<%--style="margin-left: 2.5em;" for="taiKhoan"><spring:message code="account.bank.number"/>:</label>--%>
		<%--<div class="col-sm-6 col-md-6">--%>
			<%--<input type="text" class="form-control" id="taiKhoan"--%>
				<%--placeholder="<spring:message code="label.enter.account.number"/>" name="taiKhoan" value="${taiKhoan}"--%>
				<%--required>--%>
		<%--</div>--%>
	<%--</div>--%>

	<c:if test="${codeErr != null && codeErr ne ''}">
		<div class="text-danger error-message">
			<small style="margin-left: 2.5em;"><i class="fa fa-times-circle"></i> <spring:message code="error.code.${codeErr}"/></small>
		</div>
	</c:if>
	<div class="form-group row mb-10 show-pass">
		<label class="control-label col-sm-4 col-md-3 col-lg-3"
			style="margin-left: 2.5em;" for="showPassword"><spring:message code="label.password"/>:</label>
		<div class="col-sm-6 col-md-6">
			<input type="password" class="form-control"  id="showPassword"
				name="passWord" placeholder="<spring:message code="change.pass.enter.pass"/>" value="${passWord}"
				required> <span class="fa fa-eye show-icon"
				style="margin-right: 1em;" onclick="mouseoverPass()"
				onmouseout="mouseoutPass()"></span>
		</div>
	</div>
	<div class="row">
		<div class="col-md-9 offset-md-3">
			<button type="submit" class="btn btn-primary btn-sm pull-right"><spring:message code="label.login"/></button>
		</div>
	</div>
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</div>