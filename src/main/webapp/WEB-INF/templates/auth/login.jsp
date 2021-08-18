<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html class="no-js css-menubar" lang="${pageContext.response.locale.language}">

<head>
  <title><spring:message code="login.title.page"/> - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>
<body class="animsition layout-full login-page" style="background-color: #fff">
<div class="row" style="padding: 0; margin: 0; position: relative; overflow: hidden; height: 100%;">
  <div class="col-md-8 pull-right" style="background-image: url(/assets/images/banner-login.png); /*! background-size: 100%; */ background-repeat: no-repeat;">
  </div>

  <spring:message code="common.title.company" var="title_company"></spring:message>
  <div class="col-md-4 pull-left" style="padding: 40px;position: relative;height: 100%;overflow: auto;">
    <div class="page" data-animsition-in="fade-in"
         data-animsition-out="fade-out">
      <div class="page-content vertical-align-middle" style="width: 100%;
    background-color: #fff;">
        <div class="login-box">
          <div class="login-box-body">
            <div class="row">
              <div class="col-md-12">
                <div class="pull-left">
                  <a href="#"><img class="navbar-brand-logo" src="/assets/images/logo.png"
                                   title="${title_company}">
                    <%--<span class="navbar-brand-text hidden-xs-down"> ${title_company}</span>--%>
                  </a>
                </div>
                <div class="pull-right">
                  <a title="Tiếng Việt" href="?lang=vi" id="langVi"><span
                          class="flag-icon flag-icon-vn"></span></a>
                  <a class="" title="English" href="?lang=en" id="langEn"><span
                          class="flag-icon flag-icon-gb"></span></a>
                </div>
              </div>
            </div>
            <br class="clr"/>

            <form action="login.html" method="post">
              <div class="text-left form-group">
                <b><spring:message code="login.form.title"/></b>
              </div>
              <c:if test="${codeErr != null && codeErr ne ''}">
                <div class="text-danger error-message">
                  <small><i class="fa fa-times-circle"></i>&nbsp;<spring:message code="error.code.${codeErr}"/></small>
                </div>
              </c:if>
              <c:if test="${param.action == 'success'}">
                <div class="text-success error-message">
                  <small><i class="fa fa-times-circle"></i>&nbsp;<spring:message code="lable.change.pass.success"/>
                  </small>
                </div>
              </c:if>
              <div class="form-group has-feedback">
                <input type="text" id="username" name="username" class="form-control" placeholder="<spring:message code="account.bank.number"/>"
                       autocapitalize="off" autocorrect="off" onkeyup="validateNumber(this);"/>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
              </div>

              <div class="form-group show-pass">
                <input type="password" id="showPassword" name="password" class="form-control"
                       placeholder="<spring:message code="label.password"/>"/>
                <%--<span class="fa fa-eye showIcon" onclick="textpass( 'pass' );"></span> --%>
                <span class="fa fa-eye show-icon" onclick="mouseoverPass();"
                      onmouseout="mouseoutPass();"></span>
              </div>

              <div class="form-group">
                <div class="pull-left" valign="middle">
                  <a href="javascript:void(0)" onclick="toForgetPage()"><spring:message code="login.form.lost.pass"/> </a>
                </div>
                <div class="pull-right">
                  <button type="submit" class="btn btn-primary btn-sm pull-right" autofocus><spring:message
                          code="login.form.btn.signin"/></button>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              </div>
              <br class="clr"/>
              <div class="text-center">
                <div class="">
                  <a href="https://docs.google.com/forms/d/1zMXCKuZMQQXzHIbAy3ltxtAJKZ7_WebbmNuQ2w_Tl8c/viewform" target="_blank"><spring:message code="login.form.question"/></a>
                  <%--<div class="dropdown-menu" role="menu">--%>
                  <%--<a class="dropdown-item" href="/dangKyCaNhan">--%>
                  <%--<spring:message code="login.form.register.personal"/></a>--%>
                  <%--<a class="dropdown-item" href="/dangKyDoanhNghiep">--%>
                  <%--<spring:message code="login.form.register.business"/></a>--%>
                  <%--</div>--%>
                </div>
              </div>
            </form>

            <div class="row">
              <div class="col-12">
                <div class="row">
                  <div class="col-12">
                    <div class="site-footer-legal" style="text-align: center;">
                      <nav>
                        <%--gioithieu--%>
                        <a href="https://zotahelp.freshdesk.com/en/support/solutions/articles/64000236271-giới-thiệu-về-zo-ta" target="_blank"><spring:message code="label.about.me"/></a>
                        <%--lienhe--%>
                        <%--<a href="/lienHe" target="_blank"><spring:message code="label.contact"/></a>--%>
                        <%--chinhsach--%>
                        <a href="https://zotahelp.freshdesk.com/en/support/solutions/articles/64000228332-chính-sách-hợp-tác" target="_blank"><spring:message code="label.policy"/></a>
                        <%--dieukhoan--%>
                        <a href="https://zotahelp.freshdesk.com/en/support/solutions/articles/64000236291-điều-khoản-sử-dụng-t-o-s-" target="_blank"><spring:message code="label.term"/></a>
                      </nav>
                    </div>
                  </div>
                  <div class="col-12">
                    <div class="site-footer-legal" style="text-align: center;">
                      <nav>
                        <a href="tel:0888612468"><i class="icon pe-call"></i><spring:message code="common.phone.support"/></a><br>
                        <a href="#"><i class="icon pe-mail"></i>&nbsp;<spring:message code="common.mail.support"/></a>
                      </nav>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-12">
                <div class="appstore">
                  <a href="#"><img src="/assets/images/googleplay.png" class="text-center ml-10" style="float: none;"></a>
                  <a href="#"><img src="/assets/images/appstore.png" class="text-center" style="float: none;"></a>
                  <div class="clr"></div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>

</div>


<!-- footer js-->
<c:import url="../include_page/footer_js.jsp"/>
<!-- /footer -->
</body>
<script>
  function toForgetPage() {
    window.location = "/forgot-password?user_name=" + $('#username').val();
  }
</script>
</html>
