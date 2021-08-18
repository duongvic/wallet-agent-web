<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.account.AccountVeirficationController.REDIRECT_ACCOUNT_INFO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="account.info.page.title"/>-<spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <link rel="stylesheet" href="<c:url value="/assets/development/css/epin.css"/>">
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <jsp:param name="nav" value="infoAccountVeirfication"/>
</jsp:include>
<!-- /menu bar -->

<c:set var="urlAccountInfo" value="<%=REDIRECT_ACCOUNT_INFO%>"></c:set>

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
              code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message
              code="account.info.bread.crumb"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="account.info.bread.crumb"/></h1></div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-xxl-12 col-lg-12 col-md-12 col-sm-12">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <jsp:include page="../include_component/account_avatar_frame.jsp">
              <jsp:param name="btActionVerify" value="false"/>
            </jsp:include>

            <div class="form-group row">
              <div class="col-xxl-12 col-lg-12 col-md-12 col-sm-12">
                <form class="form-horizontal" method="get" action="/account/info">
                  <div class="row mb-20">
                    <div class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                      <i class="icon pe-check badge-danger br-100 fs40"></i>
                      <p class="mb-0">${error}</p>
                      <br>
                    </div>
                  </div>

                  <div class="clr"></div>


                  <div class="row">
                    <div class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                      <button type="submit" class="btn btn-primary btn-sm"> <spring:message code="common.btn.try.again"/>
                      </button>
                    </div>
                  </div>
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      <%--<div class="col-xxl-3 col-lg-3 col-md-4 col-sm-12">--%>

      <%--<div class="row">--%>
      <%--<!-- Bảo mật  -->--%>
      <%--<c:import url="../include_component/frame_config_policy.jsp"/>--%>
      <%--<!-- /Bảo mật  -->--%>

      <%--<c:import url="../include_component/frame_car_account.jsp"/>--%>

      <%--</div>--%>
      <%--</div>--%>
    </div>
  </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<c:import url="../include_page/dropify.jsp"/>
<c:import url="../include_page/js_singledatepicker.jsp"/>

<!-- /footer -->

</body>
<style>
  .select2-container--default .select2-selection--single .select2-selection__rendered {
    padding-left: 0 !important;
  }
</style>

<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
    });
  })(document, window, jQuery);

  //load , update image profile
  jQuery(window).on("load", function () {
    loadImageProfile();
  });

  jQuery('input[type=file]').on('change', function (e) {
    var file = this.files[0];
    if (file == null || file == undefined) {
      e.preventDefault();
    }
    var nameFile = file.name;
    var typeFile = file.type;
    var reader = new FileReader();
    reader.readAsDataURL(file);
    $.MessageBox({
          buttonDone: '<spring:message code="popup.button.yes"/>',
          buttonFail: '<spring:message code="popup.button.no"/>',
          message: '<spring:message code="popup.message.upload.image"/>'
        }
    ).done(function () {
      $.ajax({
        async: false,
        type: 'POST',
        url: '/ajax-controller/profile/image-profile/update',
        beforeSend: function (xhr) {
          if ("${_csrf.parameterName}" && "${_csrf.token}") {
            xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
          }
        },
        data: {
          "${_csrf.parameterName}": "${_csrf.token}",
          name: nameFile,
          content: reader.result,
          content_type: typeFile
        },
        timeout: 60000,
        cache: false,
        success: function (data) {
          if (data != null || data != '') {
            $('#img-profile').remove();
            loadImageNavbar();
            $.MessageBox(
                {message: '<spring:message code="popup.message.upload.image.success"/>'})
          }
          else {
            $.MessageBox({message: 'API upload iamge error'});
          }
        }
      });
    }).fail(function () {
      loadImageProfile();
    });
    return false;
  });

  function loadImageNavbar() {
    var image_avatar = $('.dropify-render img').attr('src');
    jQuery('.avatar.avatar-online').html('<div id="img-profile"><img src="' + image_avatar
        + '" style="height: 30px"></div>');
  }

  function loadImageProfile() {
    var url = "${userLogin.s3Url}";
    if (url == "null" || url == '') {
      jQuery('.dropify-render').html(""
          + "<img src=\"${userLogin.dataImage}\">");
      jQuery('.dropify-wrapper').addClass("has-preview");
      jQuery('.dropify-loader').prop("style", "display: none;");
      jQuery('.dropify-preview').prop("style", "display: block;");
    } else {
      jQuery('.dropify-render').html(""
          + "<img src=\"${userLogin.s3Url}\">");
      jQuery('.dropify-wrapper').addClass("has-preview");
      jQuery('.dropify-loader').prop("style", "display: none;");
      jQuery('.dropify-preview').prop("style", "display: block;");
    }
  }
</script>
</html>