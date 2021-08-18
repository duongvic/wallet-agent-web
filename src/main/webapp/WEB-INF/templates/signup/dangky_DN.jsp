<%@ page import="static java.awt.SystemColor.window" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html class="no-js css-menubar" lang="en">

<head>
  <title>Đăng ký tài khoản Doanh Nghiệp - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>
<body class="animsition layout-full signup-page">
<spring:message code="common.title.company" var="title_company"></spring:message>
<div class="page vertical-align text-center" data-animsition-in="fade-in"
     data-animsition-out="fade-out">
  <div class="page-content vertical-align-middle">
    <div class="signup-box-body">
      <div class="row">
        <div class="col-md-12">
          <div class="pull-left">
            <a href="#"><img class="navbar-brand-logo" src="/assets/images/logo.png"
                             title="${title_company}">
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
      <form>
        <div class="form-row">
          <div class=" col-12 col-md-12 mb-12">
            <span><b>Đăng ký Ví điện tử doanh nghiệp</b></span>
          </div>
        </div>
        <div style="margin-top: 2rem;">
          <div class="form-row">
            <div class="col-lg-6 col-md-6 mb-3">
              <input type="text" class="form-control" id="validationDefault01"
                     placeholder="Số ví điện tử (là số điện thoại) *" required>
            </div>
            <div class="col-lg-6 col-md-6 mb-3">
              <input type="text" class="form-control" id="validationDefault2" placeholder="Email *"
                     required>
            </div>
          </div>
          <div class="form-row">
            <div class="col-lg-6 col-md-6 mb-3">
              <div class="form-group show-pass">
                <input type="password" class="form-control" id="showPassword" placeholder="<spring:message code="label.password"/> (6-18 ký tự, cả chữ và số) *
"
                       required>
                <span class="fa fa-eye show-icon" onclick="mouseoverPass('showPassword')"
                      onmouseout="mouseoutPass('showPassword')"></span>
              </div>

            </div>
            <div class="col-lg-6 col-md-6 mb-3">
              <div class="form-group show-pass">
                <input type="password" class="form-control" id="repeatPass" placeholder="Nhập lại mật khẩu *
"
                       required>
                <span class="fa fa-eye show-icon" onclick="mouseoverPass('repeatPass')"
                      onmouseout="mouseoutPass('repeatPass')"></span>
              </div>
            </div>
          </div>

          <div class="form-row">
            <div class="col-lg-4 col-md-4 mb-3">
              <input type="text" class="form-control" id="validationDefault11"
                     placeholder="Nhập Capcha *"
                     required>
            </div>
            <div class="col-lg-3 col-md-3 mb-3">
              <b>ABCDEF</b>
            </div>
          </div>

          <div class="row">
            <div class="myCheckBox">
              <input type="checkbox" value="" id="myCheckBox" name="check" checked required/>
              <label for="myCheckBox" style="margin-left: 0.8em;"></label>
            </div>
            <div class="col-8 mb-3" style="margin-left: 1em;">
              <label class="pull-left">Bằng việc đăng ký, bạn đã đồng ý với&nbsp;chính sách và
                điều khoản sử dụng của SmartPay</label>
            </div>
            <div class=""></div>
            <div class="col-3 mb-3">
              <button id="redirectAccountActive" class="btn btn-primary" type="submit"
                      onclick="window.location.href='/dangKyDoanhNghiepStep2'"><spring:message code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i>
              </button>
              </button>
            </div>
          </div>

          <div class="form-row">
            <div class="col">
            </div>
            <div class="col-3 mb-3">
              <div class="row">
                <label>Bạn đã có tài khoản?</label>&nbsp;
                <a class="" href="/login"><spring:message code="label.login"/></a>
              </div>
            </div>
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
