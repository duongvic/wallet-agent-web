<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <p class="error-advise">YOU SEEM TO BE TRYING TO FIND HIS WAY HOME</p>
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
