<%@ page import="static java.awt.SystemColor.window" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html class="no-js css-menubar" lang="en">
<spring:message code="common.title.company" var="title_company"/>
<head>
  <title>Liên kết ngân hàng - <spring:message code="common.company"/></title>
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

      <form>
        <div class="form-row">
          <div class=" col-12 col-md-12 mb-12">
            <span><h4>Liên kết ngân hàng</h4></span>
          </div>
        </div>
        <div style="margin-top: 2rem;">
          <div class="form-row">
            <div class="col-sm-12 mt-3 mb-3">
              <%--<select class="selectpicker" data-show-subtext="false" data-live-search="">--%>
              <%--<option data-subtext="Rep California">Tom Foolery</option>--%>
              <%--<option data-subtext="Sen California">Bill Gordon</option>--%>
              <%--<option data-subtext="Sen Massacusetts">Elizabeth Warren</option>--%>
              <%--<option data-subtext="Rep Alabama">Mario Flores</option>--%>
              <%--<option data-subtext="Rep Alaska">Don Young</option>--%>
              <%--</select>--%>
              <select data-plugin="select2" class="form-control">
                <option value="">Chọn ngân hàng</option>
                <option value="VCB">Ngân Hàng TMCP Ngoại Thương Việt Nam(VietcomBank)</option>
                <option value="TPB">Ngân Hàng TMCP Tiên Phong(TPBank)</option>
                <option value="VTB">Ngân Hàng TMCP Công Thương Việt Nam(VietinBank)</option>
                <option value="AGB">Ngân hàng Nông Nghiệp và Phát triển Nông Thôn Việt Nam(Agribank)
                </option>
                <option value="BIDV"> Ngân hàng TMCP Đầu tư và Phát triển Việt Nam(BIDV)
                </option>
              </select>
            </div>
            <%--<div class="col-lg-6 col-md-6 mb-3">--%>
            <%--<input type="text" class="form-control" id="validationDefault2" placeholder="Email *" required="">--%>
            <%--</div>--%>
          </div>

          <div class="row col-sm-12 mt-3 mb-3">
            <div class="checkbox-custom checkbox-default checkbox-inline">
              <input type="checkbox" class="custom-control-input" id="inputHorizontalCard"
                     name="" checked>
              <label for="inputHorizontalCard">Thẻ</label>
            </div>
            <div class="checkbox-custom checkbox-default checkbox-inline">
              <input type="checkbox" class="custom-control-input" id="inputHorizontalAccount"
                     name="">
              <label for="inputHorizontalAccount"><spring:message code="account.bank.account"/></label>
            </div>
          </div>

          <div class="form-row">
            <div class="col-sm-12 mt-3 mb-3">
              <input type="text" class="form-control" id="validationDefaultCTK"
                     placeholder="<spring:message code="account.bank.holder"/>"
                     required>
            </div>

          </div>

          <div class="form-row">
            <div class="col-sm-12 mt-3 mb-3">
              <input type="text" class="form-control" id="validationSTK"
                     placeholder="<spring:message code="account.bank.number"/>" required oninput="validateSTK()">
            </div>
          </div>

          <div class="row">
            <div class="myCheckBox">
              <input type="checkbox" value="" id="myCheckBox" name="check" checked="" required="">
              <label for="myCheckBox" style="margin-left: 0.8em;"></label>
            </div>
            <div class="col-8 mt-3 mb-3" style="margin-left: 1em;">
              <label class="pull-left">Bằng việc đăng ký, bạn đã đồng ý với&nbsp;chính sách và
                điều khoản sử dụng của SmartPay</label>
            </div>
            <div class=""></div>
            <div class="col-3 mt-3 mb-3">
              <button id="redirectAccountActive" class="btn btn-primary" type="submit"
                      onclick="window.location.href='/verifyLinkedBank'"><spring:message code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i>
              </button>

            </div>
          </div>

          <div class="form-row" style="font-size: 12px;font-weight: 200;">
            <div class="col-lg-12 col-sm-12 mt-3 mb-3 offset-lg-8 offset-sm-0
">
              <div class="row">
                <label>Chưa có tài khoản ngân hàng?</label>&nbsp;
                <a class="" href="/login">Để sau</a>
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

  function validateSTK() {
    var x = document.getElementById("validationSTK").value;
    var y = x.replace(/[-]+/g, "");
    var z = y.replace(/[A-Za-z0-9]{4}(?!$)/g, "\$&" + "-");
    document.getElementById("validationSTK").value = z;
  }

</script>

</body>
</html>
