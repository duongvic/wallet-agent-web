<%@ page
    import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Location" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.controller.account.AccountVeirficationController.REDIRECT_ACCOUNT_VERIFY" %>
<%@ page import="vn.mog.ewallet.consumer.web.controller.account.AccountVeirficationController" %>
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

  <style>
    .upload-image-kyc {
      min-width: 100% !important;
    }
  </style>
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

<c:set var="urlAccountVerify" value="<%=REDIRECT_ACCOUNT_VERIFY%>"></c:set>

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
                <form method="post" action="/account/verify-kyc" enctype="multipart/form-data">
                  <div class="form-group row mb-10">
                    <div class="col">
                      <p><spring:message code="account.verify.u.must.upload.photo"/></p>
                    </div>
                  </div>

                  <div class="form-group row mb-10">
                    <div class="col-xxl-4 col-lg-4 col-md-4 col-sm-12">
                      <fieldset class="scheduler-border">
                        <legend class="scheduler-border"><spring:message
                            code="account.verify.front.size.of.id.card"/></legend>
                        <%--<div class="col-xxl-2 col-lg-2 col-md-12 col-sm-12">--%>
                          <input type="file" name="fileBeforeUpload" id="imgBefore"
                                 data-plugin="dropify" required
                                 data-max-file-size="4M" data-show-remove="true"
                                 data-allowed-file-extensions="PNG JPE JPEG JPG png jpe jpeg jpg">
                        <%--</div>--%>
                      </fieldset>
                    </div>

                    <div class="col-xxl-4 col-lg-4 col-md-4 col-sm-12">
                      <fieldset class="scheduler-border">
                        <legend class="scheduler-border"><spring:message
                            code="account.verify.back.size.of.id.card"/></legend>
                        <%--<div class="col-xxl-2 col-lg-2 col-md-12 col-sm-12">--%>
                          <input type="file" name="fileAfterUpload" id="imgAfter"
                                 data-plugin="dropify" required
                                 data-max-file-size="4M" data-show-remove="true"
                                 data-allowed-file-extensions="PNG JPE JPEG JPG png jpe jpeg jpg">
                        <%--</div>--%>
                      </fieldset>
                    </div>

                    <div class="col-xxl-4 col-lg-4 col-md-4 col-sm-12">
                      <fieldset class="scheduler-border">
                        <legend class="scheduler-border"><spring:message
                            code="account.verify.selfie.photo"/></legend>
                        <%--<div class="col-xxl-2 col-lg-2 col-md-12 col-sm-12">--%>
                          <input type="file" name="fileSelfieUpload" id="imgselfie"
                                 data-plugin="dropify" required
                                 data-max-file-size="4M" data-show-remove="true"
                                 data-allowed-file-extensions="PNG JPE JPEG JPG png jpe jpeg jpg">
                        <%--</div>--%>
                      </fieldset>
                    </div>
                  </div>

                  <div class="form-group row">
                    <div class="col-xxl-4 col-lg-4 col-md-4 col-sm-12">
                      <p><spring:message code="account.bank.note"/></p>
                      <p>&#8226; &nbsp; <spring:message code="account.verify.only.accept.images"/> </p>
                      <p>&#8226; &nbsp; <spring:message code="account.verify.image.size.lower.than.4MB"/> </p>
                    </div>
                    <div class="col-xxl-4 col-lg-4 col-md-4 col-sm-12">
                      <div class="form-group row">
                        <div class=""></div>
                        <div class="">
                          <a href="javascript:void(0)" class="btn btn-secondary" id="id_bt_back">
                            <i class="icon wb-chevron-left mr-10"></i>
                            <spring:message code="button.back"/>
                          </a>
                          <button type="submit" class="btn btn-primary"><spring:message
                              code="label.button.verify"/></button>
                        </div>

                      </div>

                    </div>
                  </div>

                  <%--<input type="hidden" name="checkValueAttribute" value="true">--%>
                  <input type="hidden" name="${_csrf.parameterName}"
                         value="${_csrf.token}"/>
                  <input type="hidden" name="urlImage" value="${urlImage}">
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

      $('.dropify-wrapper').addClass('upload-image-kyc');
      $('#id-avatar .dropify-wrapper').removeClass('upload-image-kyc');
    });

  })(document, window, jQuery);

  jQuery(window).on("load", function () {
    loadImageProfile();
  });

  jQuery('#id_bt_back').on("click", function ()  {
    window.location.href = '<%=AccountVeirficationController.REDIRECT_ACCOUNT_INFO%>';
  })

  jQuery('#id-dropify-img').on('change', function (e) {
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
    var url = "${userLogin.s3Url}"
    if (url == "null" || url == '') {
      jQuery('#id-avatar .dropify-render').html(""
          + "<img src=\"${userLogin.dataImage}\">");
      jQuery('#id-avatar .dropify-wrapper').addClass("has-preview");
      jQuery('#id-avatar .dropify-loader').prop("style", "display: none;");
      jQuery('#id-avatar .dropify-preview').prop("style", "display: block;");
    } else {
      jQuery('#id-avatar .dropify-render').html(""
          + "<img src=\"${userLogin.s3Url}\">");
      jQuery('#id-avatar .dropify-wrapper').addClass("has-preview");
      jQuery('#id-avatar .dropify-loader').prop("style", "display: none;");
      jQuery('#id-avatar .dropify-preview').prop("style", "display: block;");
    }
  }

  jQuery('#updateProfile').on("click", function (e) {
    e.preventDefault();
    var displayName = $('#display-name').val();
    var gender = $('#hidden_gender').val();
    var dateOfBirth = $('input[name=dateOfBirth]').val();
    if (dateOfBirth == "") {
      dateOfBirth = null;
    }
    //hien tai
    var livingAddress = $('#livingAddress').val();
    var province = $('#current_province_selected').val();
    var district = $('#current_district_selected').val();
    var commune = $('#current_commune_selected').val();

    var jobOccupation = $('#jobOccupation').val();
    var jobPosition = $('#jobPosition').val();
    var email = $('#email').val();

    var residence_province = $('#province_selected').val();
    var residence_district = $('#district_selected').val();
    var residence_commune = $('#commune_selected').val();
    var residence_street1 = $('#residence_street1').val();

    $.MessageBox({
          buttonDone: '<spring:message code="popup.button.yes"/>',
          buttonFail: '<spring:message code="popup.button.no"/>',
          message: '<spring:message code="popup.message.do.u.want.update.info"/>'
        }
    ).done(function () {
      $.ajax({
        type: 'POST',
        url: '/ajax-controller/profile/update',
        beforeSend: function (xhr) {
          if ("${_csrf.parameterName}" && "${_csrf.token}") {
            xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
          }
        },
        data: {
          "${_csrf.parameterName}": "${_csrf.token}",
          displayName: displayName,
          gender: gender,
          dateOfBirth: dateOfBirth,
          livingAddress: livingAddress,
          province: province,
          district: district,
          commune: commune,
          jobOccupation: jobOccupation,
          jobPosition: jobPosition,
          email: email,
          residence_province: residence_province,
          residence_district: residence_district,
          residence_commune: residence_commune,
          residence_street1: residence_street1

        },
        timeout: 60000,
        cache: false,
        success: function (data) {
          if (data.status.code == 0) {
            window.location.replace("${urlAccountVerify}");
          }
          else {
            $.MessageBox({message: data.status.value});
          }
        }
      });
    }).fail(function () {
    });
  });

</script>
</html>