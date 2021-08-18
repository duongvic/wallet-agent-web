<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-header">
  <h1 class="page-title font-size-26 font-weight-100"><spring:message code="dashboard.user.communication"/>,&nbsp; <sec:authentication property="principal.fullName"></sec:authentication></h1>
</div>
