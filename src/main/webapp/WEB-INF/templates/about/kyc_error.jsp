<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 12/9/2020
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html class="no-js css-menubar" lang="en">
<head>
    <title>400 - <spring:message code="common.company"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <!-- /head libs  -->
</head>
<body class="animsition page-error page-error-400 layout-full">
<div class="page vertical-align text-center" data-animsition-in="fade-in" data-animsition-out="fade-out">
    <div class="page-content vertical-align-middle">
        <header>
            <h1 class="animation-slide-top">404</h1>
            <p>Page Not Found !</p>
        </header>
        <h3 class="error-advise"><strong><spring:message code="label.please.verify.acc.before.using.the.service"/></strong></h3>
        <a class="btn btn-primary btn-round" href="/dashboard/index">GO TO HOME PAGE</a>

        <footer class="page-copyright">
            <p>WEBSITE BY <a href=""><spring:message code="common.company"/></a></p>
            <p>© 2018. All RIGHT RESERVED.</p>
            <div class="social">
                <a class="btn btn-icon btn-pure" href="javascript:void(0)">
                    <i class="icon bd-facebook" aria-hidden="true"></i>
                </a>
                <a class="btn btn-icon btn-pure" href="javascript:void(0)">
                    <i class="icon bd-google-plus" aria-hidden="true"></i>
                </a>
                <a class="btn btn-icon btn-pure" href="javascript:void(0)">
                    <i class="icon bd-twitter" aria-hidden="true"></i>
                </a>
            </div>
        </footer>
    </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>
</html>


