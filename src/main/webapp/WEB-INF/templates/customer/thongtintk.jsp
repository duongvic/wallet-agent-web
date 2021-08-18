<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <title><spring:message code="account.info.page.title"/>-<spring:message code="common.company"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="infoCus"/>
</jsp:include>
<!-- /menu bar -->

<spring:message code="label.gender.male" var="genderMale"/>
<spring:message code="label.gender.female" var="genderFemale"/>
<spring:message code="label.gender.other" var="genderOther"/>

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="account.info.bread.crumb"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="account.info.user.info"/></h1></div>
    <div class="page-content container-fluid">
        <div class="row">
            <div class="col-xxl-9 col-lg-9 col-md-8 col-sm-12">
                <div class="panel panel-bordered">
                    <div class="panel-heading">
                        <h3 class="panel-title"><spring:message
                                code="account.info.customer.confirm"/></h3>
                        <%--<ul class="panel-actions panel-actions-keep">--%>
                            <%--<li><a href=""><spring:message code="account.info.completed"/> 50%</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    </div>
                    <div class="panel-body py-10">
                        <div class="row">
                            <div class="col-md-3 col-sm-6 text-center my-15">
                                <spring:message code="account.info.link.tool.tip"
                                                var="link_tooltip"/>
                                <i class="icon pe-check badge-success br-100 fs40"></i>
                                <p class="mb-0"><spring:message code="account.info.link"/> <i
                                        class="fa  fa-question-circle" data-placement="top"
                                        data-toggle="tooltip"
                                        data-original-title="${link_tooltip}"></i></p>
                                <small>Đã liên kết</small>
                                <br>
                            </div>
                            <div class="col-md-3 col-sm-6 text-center my-15">
                                <spring:message code="account.info.email.tool.tip"
                                                var="email_tooltip"/>
                                <i class="icon pe-close-circle badge-danger br-100 fs40"></i>
                                <p class="mb-0"><spring:message code="account.info.email.confirm"/>
                                    <i class="fa  fa-question-circle" data-placement="top"
                                       data-toggle="tooltip"
                                       data-original-title="${email_tooltip}"></i></p>
                                <small>Chưa xác thực</small>
                                <br>
                                <button type="button" class="btn btn-warning btn-sm mt-10">
                                    <spring:message code="account.info.send.request"/></button>
                            </div>
                            <div class="col-md-3 col-sm-6 text-center my-15">
                                <spring:message code="account.info.account.tool.tip"
                                                var="account_tooltip"/>
                                <i class="icon pe-attention badge-warning br-100 fs40"></i>
                                <p class="mb-0"><spring:message
                                        code="account.info.account.confirm"/><i
                                        class="fa  fa-question-circle" data-placement="top"
                                        data-toggle="tooltip"
                                        data-original-title="${account_tooltip}"></i></p>
                                <small>Đã gửi yêu cầu xác thực</small>
                                <br>
                            </div>
                            <div class="col-md-3 col-sm-6 text-center my-15">
                                <spring:message code="account.info.payment.security.tool.tip"
                                                var="payment_security_tooltip"/>
                                <i class="icon pe-check badge-success br-100 fs40"></i>
                                <p class="mb-0"><spring:message
                                        code="account.info.payment.security"/><i
                                        class="fa  fa-question-circle" data-placement="top"
                                        data-toggle="tooltip"
                                        data-original-title="${payment_security_tooltip}"></i></p>
                                <small>Đã đăng ký</small>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-bordered">
                    <div class="panel-heading">
                        <h3 class="panel-title"><spring:message
                                code="account.info.panel.title"/></h3></div>
                    <div class="panel-body py-10">
                        <form>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="account.info.wallet.account"/>: </label>
                                <div class=" col-sm-9 ">
                                    <p class="form-control-plaintext pb-0" id="wallet">${wallet}</p>
                                </div>
                            </div>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="label.email"/>: </label>
                                <div class=" col-sm-9">
                                    <p class="form-control-plaintext pb-0">${email}<a
                                            href="/customer/changeEmail"
                                            class="btn btn-primary btn-xs pull-right"><spring:message
                                            code="account.info.email.change"/></a></p>
                                </div>
                            </div>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="label.password"/>: </label>
                                <div class=" col-sm-9">
                                    <p class="form-control-plaintext pb-0"><spring:message
                                            code="label.last.updated.on"/> ${lastChangedPassword}
                                        <a href="/customer/password-required"
                                           class="btn btn-primary btn-xs pull-right">
                                            <spring:message code="account.info.change.password"/>
                                        </a>
                                    </p>
                                </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                        </form>
                    </div>
                </div>
                <div class="panel panel-bordered">
                    <div class="panel-heading">
                        <h3 class="panel-title"><spring:message
                                code="account.info.private.information"/></h3></div>
                    <div class="panel-body py-10">
                        <form>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="account.info.full.name"/>: </label>
                                <div class=" col-sm-9 ">
                                    <p class="form-control-plaintext pb-0">${fullName}</p>
                                </div>
                            </div>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="account.info.sex"/>: </label>
                                <div class=" col-sm-9">
                                    <p class="form-control-plaintext pb-0">${gender == 0 ? genderMale : (gender == 1 ? genderFemale : '')}</p>
                                </div>
                            </div>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="account.info.birth.date"/>: </label>
                                <div class=" col-sm-9">
                                    <p class="form-control-plaintext pb-0">${dateOfBirth}</p>
                                </div>
                            </div>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="account.info.identity.card"/>: </label>
                                <div class=" col-sm-9">
                                    <p class="form-control-plaintext pb-0">${cmt}</p>
                                </div>
                            </div>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="account.info.permanent.residence"/>: </label>
                                <div class=" col-sm-9">
                                    <p class="form-control-plaintext pb-0">${residence_street1}</p>
                                </div>
                            </div>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="account.info.store.address"/>: </label>
                                <div class=" col-sm-9">
                                    <p class="form-control-plaintext pb-0">${livingAddress}</p>
                                </div>
                            </div>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="account.info.job"/>: </label>
                                <div class=" col-sm-9">
                                    <p class="form-control-plaintext pb-0">${occupation}</p>
                                </div>
                            </div>
                            <div class="form-group row mb-5">
                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                        code="account.info.job.position"/>: </label>
                                <div class=" col-sm-9">
                                    <p class="form-control-plaintext pb-0">${position}</p>
                                    <a href="/customer/info-update"
                                       class="btn btn-primary pull-right">
                                        <spring:message code="label.button.update"/>
                                    </a>
                                </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                            <input type="hidden" name="urlImage" value="${urlImage}">
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-xxl-3 col-lg-3 col-md-4 col-sm-12">

                <!-- information customer -->
                <c:import url="frame_custumer.jsp"/>
                <!-- /information customer -->

                <div class="row">
                    <!-- Bảo mật  -->
                    <c:import url="../include_component/frame_config_policy.jsp"/>
                    <!-- /Bảo mật  -->

                    <c:import url="../include_component/frame_car_account.jsp"/>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>
<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();

    });

  })(document, window, jQuery);

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
                {message: '<spring:message code="popup.message.upload.image.success"/>'});
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