<%@ page import="static java.awt.SystemColor.window" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html class="no-js css-menubar" lang="en">
<spring:message code="common.title.company" var="title_company"/>
<head>
  <title>Đăng ký tài khoản Doanh Nghiệp - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>
<body class="animsition layout-full signup-page">
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
                     placeholder="Tên doanh nghiệp *" required>
            </div>
            <div class="col-lg-6 col-md-6 mb-3">
              <input type="text" class="form-control" id="validationDefault2" placeholder="SĐKKD *"
                     required>
            </div>
          </div>

          <div class="form-row">
            <div class="col-lg-6 col-md-6 mb-3">
              <input type="text" class="form-control" id="validationDefault03" placeholder="Tên giao dịch doanh nghiệp *
"
                     required>
            </div>
            <div class="col-lg-6 col-md-6 mb-3">
              <input type="text" class="form-control" id="validationDefault04" placeholder="Mã số thuế *
"
                     required>
            </div>
          </div>

          <div class="form-row">
            <div class="col-lg-6 col-md-6 mb-3">
              <select data-plugin="select2" class="form-control">
                <option value="">Nghành nghề KD *</option>
                <option value="hn">Nội Thất</option>
                <option value="hcm">Điện tử</option>
              </select>
            </div>
          </div>

          <div class="form-row">
            <div class="col-lg-6 col-md-6 mb-3">
              <select data-plugin="select2" class="form-control">
                <option value="">Tỉnh/Thành Phố</option>
                <option value="hn">Hà Nội</option>
                <option value="hcm">Hồ Chí Minh</option>
              </select>
            </div>
            <div class="col-lg-6 col-md-6 mb-3">
              <select data-plugin="select2" class="form-control">
                <option value="">Quận/Huyện</option>
                <option value="hn">Hai Bà Trưng</option>
                <option value="hcm">Hoàn Kiếm</option>
              </select>
            </div>
          </div>

          <div class="form-row">
            <div class="col-lg-6 col-md-6 mb-3">
              <select class="form-control">
                <option value="">Phường/Xã</option>
                <option value="hn">Đồng Nhân</option>
                <option value="hcm">Hàng Mã</option>
              </select>
            </div>
            <div class="col-lg-6 col-md-6 mb-3">
              <input type="text" class="form-control" id="validationDefault15" placeholder="Địa chỉ ĐKKD
"
                     required>
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
              <button id="" class="btn btn-primary" type="submit">Đăng ký</button>
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
        <div class="col"> <a class="" href="javascript:void(0)"><spring:message code="label.term"/></a></div>
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
