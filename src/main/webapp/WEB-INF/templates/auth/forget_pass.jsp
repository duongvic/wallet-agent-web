<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html class="no-js css-menubar" lang="${pageContext.response.locale.language}">
<spring:message code="common.title.company" var="title_company"></spring:message>
<head>
  <title><spring:message code="changepass.nav.change.pass"/> - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>
<body class="animsition layout-full login-page" style="background-color: #fff">
<div class="row" style="margin: 0; height: 100%">
  <div class="col-md-8 pull-right"
       style="background-image: url(/assets/images/banner-login.png); background-repeat: no-repeat;">
  </div>
  <div class="col-md-4 pull-left" style="padding: 40px;position: relative; height: 100%;">
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
                  </a>
                </div>
                <div class="pull-right">
                  <a title="Tiếng Việt" href="lang=vi" id="langVi"><span
                      class="flag-icon flag-icon-vn"></span></a>
                  <a class="" title="English" href="lang=en" id="langEn"><span
                      class="flag-icon flag-icon-us"></span></a>
                </div>
              </div>
            </div>
            <br class="clr"/>

            <form id="forgot-password-form" action="/forgot-password/confirm-otp" method="post">
              <div class="text-left form-group">
                <b><spring:message code="label.account.forgot.password"/></b>
              </div>
              <c:if test="${codeErr != null && codeErr ne ''}">
                <div class="text-danger error-message">
                  <small><i class="fa fa-times-circle"></i> <spring:message code="label.account.no.exist"/></small>
                </div>
              </c:if>
              <div class="form-group has-feedback">
                <input type="text" usemap="registeredUser" class="form-control" name="user_name"
                       value="${user_name}" placeholder="<spring:message code="label.account.registered.phone.number"/>" required>
              </div>

              <div class="form-group">
                <div class="pull-right">
                  <button type="submit" class="btn btn-primary pull-right"><spring:message code="service.exportcard.confirm.btn.next"/></button>
                  <a href="/login" class="btn btn-default pull-right mr-10"><spring:message code="common.btn.back.login"/></a>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              <br class="clr"/>
            </form>

            <br class="clr"/>

            <%--<div class="text-center hidden">--%>
              <%--<div class="dropdown">--%>
                <%--<a data-toggle="dropdown" href="#" aria-expanded="false" data-animation="scale-up"--%>
                   <%--role="button"><spring:message code="login.form.question"/></a>--%>
                <%--<div class="dropdown-menu" role="menu">--%>
                  <%--<a class="dropdown-item" href="/dangKyCaNhan">--%>
                    <%--<spring:message code="login.form.register.personal"/></a>--%>
                  <%--<a class="dropdown-item" href="/dangKyDoanhNghiep">--%>
                    <%--<spring:message code="login.form.register.business"/></a>--%>
                <%--</div>--%>
              <%--</div>--%>
            <%--</div>--%>


            <div class="row">
              <div class="col-12">
                <div class="row">
                  <div class="col-12">
                    <div class="site-footer-legal" style="text-align: center;">
                      <nav>
                        <a href="https://zotahelp.freshdesk.com/en/support/solutions/articles/64000236271-giới-thiệu-về-zo-ta" target="_blank"><spring:message code="label.about.me"/></a>
                        <a href="/lienHe" target="_blank"><spring:message code="label.contact"/></a>
                        <a href="https://zotahelp.freshdesk.com/en/support/solutions/articles/64000228332-chính-sách-hợp-tác" target="_blank"><spring:message code="label.policy"/></a>
                        <a href="https://zotahelp.freshdesk.com/en/support/solutions/articles/64000236291-điều-khoản-sử-dụng-t-o-s-" target="_blank"><spring:message code="label.term"/></a>
                      </nav>
                    </div>
                  </div>
                  <div class="col-12">
                    <div class="site-footer-legal" style="text-align: center;">
                      <nav>
                        <a href="tel:0888612468"><i class="icon pe-call"></i><spring:message code="common.phone.support"/></a>
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
        <!-- /.login-box-body -->
        <%--<footer class="page-copyright" style="text-align: center;">--%>
          <%--<p>WEBSITE BY <a href="#"><spring:message code="common.company"/></a></p>--%>
          <%--<p>© 2018. All RIGHT RESERVED.</p>--%>
          <%--<div class="social">--%>
            <%--<a class="btn btn-icon btn-pure" href="javascript:void(0)"><i class="icon bd-facebook"--%>
                                                                          <%--aria-hidden="true"></i></a>--%>
            <%--<a class="btn btn-icon btn-pure" href="javascript:void(0)"><i--%>
                <%--class="icon bd-google-plus"--%>
                <%--aria-hidden="true"></i></a>--%>
            <%--<a class="btn btn-icon btn-pure" href="javascript:void(0)"><i class="icon bd-twitter"--%>
                                                                          <%--aria-hidden="true"></i></a>--%>
          <%--</div>--%>
        <%--</footer>--%>
      </div>
    </div>
  </div>

</div>


<!-- footer js-->
<c:import url="../include_page/footer_js.jsp"/>
<!-- /footer -->
</body>
</html>
