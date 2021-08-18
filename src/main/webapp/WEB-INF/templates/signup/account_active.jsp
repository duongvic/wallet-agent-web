
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html class="no-js css-menubar" lang="en">
<spring:message code="common.title.company" var="title_company"/>
<head>
  <title>Kích hoạt tài khoản - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>
<body class="animsition layout-full signup-page">
<div class="page vertical-align text-center" data-animsition-in="fade-in"
     data-animsition-out="fade-out">
  <div class="page-content account-active vertical-align-middle">
    <div class="signup-box-body">
      <div class="row">
        <div class="col-md-12">
          <div class="pull-left">
            <a href="#"><img class="navbar-brand-logo" src="/assets/images/logo.png"
                             title="${title_company}">
              <%--<span class="navbar-brand-text hidden-xs-down"> <spring:message code="common.company"/></span>--%>
            </a>
          </div>

          <div class="pull-right">
            <a title="Tiếng Việt" href="#"><span class="flag-icon flag-icon-vn"></span></a>
            <a class="disabled" title="English" href="#"><span
                class="flag-icon flag-icon-us"></span></a>
            <a class="disabled" title="Tiếng Trung" href="#"><span
                class="flag-icon flag-icon-cn"></span></a>
            <button id="redirectLogIn" class="btn btn-warning" type="submit"><spring:message code="label.login"/></button>

          </div>


        </div>
      </div>
      <br class="clr"/>
      <br action="">
      <div class="row">
        <div class="col-lg-12 col-md-8 mb-3">
          <h3>Kích hoạt tài khoản</h3>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-12 col-md-8 mb-3">
          <label>Hệ thống đã gửi mã kích hoạt tài khoản vào số
            <span style="color: #27ADD0">
              0946 438 891</span> và email <span style="color: #27ADD0">tuongbeo@gmail.com</span>
          </label>
        </div>
        <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Nhập mã kích hoạt">
            <span class="input-group-btn">
                                                <button type="button"
                                                        class="btn btn-default btn-outline">
                                                    <i class="fa fa-rotate-left"></i> <spring:message code="common.btn.resend"/></button>
                                              </span>
          </div>
        </div>
      </div>

      <div class="row pull-right mt-3">
        <div class="col-lg-12 col-md-8 mb-3">
          <button id="" class="btn btn-primary" type="submit"
                  onclick="window.location.href='/linkedBank'">Kích hoạt
          </button>
        </div>
      </div>


      </form>
      <br class="clr"/>
      </form>
    </div>

    <!-- /.login-box-body -->

    <footer class="page-copyright">
      <div class="row">
        <div class="col"><a class="" href="javascript:void(0)"><spring:message code="label.about.me"/></a></div>
        <div class="col"><a class="" href="javascript:void(0)"><spring:message code="label.contact"/></a></div>
        <div class="col"><a class="" href="javascript:void(0)"><spring:message code="label.policy"/></a></div>
        <div class="col"><a class="" href="javascript:void(0)"><spring:message code="label.term"/></a></div>
      </div>
    </footer>
  </div>
</div>

<c:import url="../include_page/signup.jsp"/>

<script type="text/javascript">
  $(document).ready(function(){
   $("#redirectLogIn").click(function(){
      var signUpURL ='http://localhost:11100';
      window.location.href = signUpURL;
   });
 });








</script>

</body>
</html>
