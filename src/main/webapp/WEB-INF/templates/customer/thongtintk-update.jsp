<%--suppress ALL --%>
<%@ page
        import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Location" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <title><spring:message code="account.update.info.customer"/> - <spring:message code="common.company"/></title>
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
<c:set var="DISTRICT" value="<%=Location.DISTRICT%>" scope="application"/>
<c:set var="COMMUNE" value="<%=Location.COMMUE%>" scope="application"/>
<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message code="label.account.info"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="account.info.user.info"/></h1></div>
    <div class="page-content container-fluid">
        <div class="row">
            <div class="col-xxl-9 col-lg-9 col-md-8 col-sm-12">
                <div class="panel panel-bordered">
                    <div class="panel-heading">
                        <h3 class="panel-title"><spring:message
                                code="account.edit.info.customer"/></h3></div>
                    <div class="panel-body py-10">
                        <form class="form-horizontal">
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label"><spring:message
                                        code="account.info.full.name"/>&nbsp;<span id=""
                                                                                   class="text-danger"></span>
                                    *: </label>
                                <div class="col-sm-4 input-group">
                                    <input type="text" class="form-control" id="last-name"
                                           style="width: 34%"
                                           required value="${lastName}" name="lastName"
                                           placeholder="Họ">
                                    <input type="text" class="form-control"
                                           required value="${firstName}" name="firstName"
                                           id="first-name" placeholder="Tên">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label"><spring:message code="account.info.sex"/> : </label>
                                <div class="col-sm-4">
                                    <div class="radio-custom radio-default radio-inline">
                                        <input type="radio" id="inputHorizontalMale" name="gender"
                                               value="0" ${(gender eq 0) ? 'checked' : ''}
                                               onclick="getGender(this)"/>
                                        <label for="inputHorizontalMale">${genderMale}</label>
                                    </div>
                                    <div class="radio-custom radio-default radio-inline">
                                        <input type="radio" id="inputHorizontalFemale" name="gender"
                                               value="1" ${(gender eq 1) ? 'checked' : ''}
                                               onclick="getGender(this)"/>
                                        <label for="inputHorizontalFemale">${genderFemale}</label>
                                    </div>
                                    <input type="hidden" id="hidden_gender" value="${gender}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label"><spring:message code="account.info.birth.date"/> : </label>
                                <div class="col-sm-4">
                                    <div class="input-group"><span class="input-group-addon">
                      <i class="icon wb-calendar" aria-hidden="true"></i>
                    </span>
                                        <input type="text" id="datepicker" class="form-control"
                                               data-plugin="datepicker"
                                               data-date-format="dd/mm/yyyy" name="dateOfBirth"
                                               value="${dateOfBirth}"></div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label"><spring:message
                                        code="label.email"/></label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control"
                                           pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[a-z]{2,3}$"
                                           id="email"
                                           value="${email}"
                                           name="email"
                                           placeholder="${placeholderEmail}" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">CMND: </label>
                                <div class="col-sm-4"><input disabled type="text"
                                                             class="form-control" id="cmt"
                                                             name="cmt" value="${cmt}"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label"><spring:message code="account.info.permanent.residence"/> : </label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control"
                                           placeholder="Địa chỉ chi tiết" id="residence_street1"
                                           name="residence_street1" value="${residence_street1}"/>
                                    <div class="row">
                                        <div class="col-sm-4 mt-20">
                                            <select class="form-control" id="province_selected"
                                                    name="residence_province">
                                                <option value="">Tỉnh/Thành Phố</option>
                                                <%--<c:forEach items="${listProvince}" var="item">--%>
                                                <c:forEach items="${listResProvince}" var="item">
                                                    <option value="${item.code}" ${residence_province eq item.code ? 'selected' : ''}>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-4 mt-20">
                                            <select class="form-control" id="district_selected"
                                                    name="residence_district">
                                                <option value="">Quận/Huyện</option>
                                                <c:forEach items="${listResDistrict}" var="item">
                                                    <option value="${item.code}" ${residence_district eq item.code ? 'selected' : ''}>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-4 mt-20">
                                            <select class="form-control" id="commune_selected"
                                                    name="residence_commune">
                                                <option value="">Phường/Xã</option>
                                                <c:forEach items="${listResCommune}" var="item">
                                                    <option value="${item.code}" ${residence_commune eq item.code ? 'selected' : ''}>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label"><spring:message code="account.info.store.address"/> : </label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control"
                                           placeholder="Địa chỉ chi tiết" id="livingAddress"
                                           name="livingAddress"
                                           value="${livingAddress}"/>
                                    <div class="row">
                                        <div class="col-sm-4 mt-20">
                                            <select class="form-control"
                                                    id="current_province_selected"
                                                    name="current_province">
                                                <option value="">Tỉnh/Thành Phố</option>
                                                <c:forEach items="${listProvince}" var="item">
                                                    <option value="${item.code}" ${current_province eq item.code ? 'selected' : ''}>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-4 mt-20">
                                            <select class="form-control"
                                                    id="current_district_selected"
                                                    name="current_district">
                                                <option value="">Quận/Huyện</option>
                                                <c:forEach items="${listDistrict}" var="item">
                                                    <option value="${item.code}" ${current_district eq item.code ? 'selected' : ''}>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-4 mt-20">
                                            <select class="form-control"
                                                    id="current_commune_selected"
                                                    name="current_commune">
                                                <option value="">Phường/Xã</option>
                                                <c:forEach items="${listCommune}" var="item">
                                                    <option value="${item.code}" ${current_commune eq item.code ? 'selected' : ''}>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label"><spring:message
                                        code="account.info.job"/> : </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="jobOccupation"
                                           name="occupation"
                                           value="${occupation}"/></div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label"><spring:message
                                        code="account.info.job.position"/> : </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="jobPosition"
                                           name="position"
                                           value="${position}"/></div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                        </form>
                    </div>
                    <div class="panel-footer py-10 text-right">
                        <div class="row">
                            <div class="col-sm-9 offset-sm-3">
                                <a href="/customer/account-info"
                                   class="btn btn-default btn-outline"><spring:message
                                        code="common.btn.back"/></a>
                                <button id="updateProfile"
                                        class="btn btn-primary ml-10"><spring:message
                                        code="label.button.update"/></button>
                            </div>
                        </div>
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

<spring:message code="label.county.district" var="CountyDistrict"/>
<spring:message code="label.commune" var="CommuneLanguage"/>
<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();

    });

  })(document, window, jQuery);

  $(document).ready(function () {

    jQuery('#province_selected').on("change", function () {
      var value = jQuery(this).val();
      if (value != null && value !== undefined && value !== '') {

        getLocation(value, '${DISTRICT}', 'district_selected',
            '<option value="">${CountyDistrict}</option>');
      }
    });

    jQuery('#district_selected').on("change", function () {
      var value = jQuery(this).val();
      if (value != null && value !== undefined && value !== '') {
        getLocation(value, '${COMMUNE}', 'commune_selected', '<option value="">Phường/Xã</option>');
      }
    });

    jQuery('#current_province_selected').on("change", function () {
      var value = jQuery(this).val();
      if (value != null && value !== undefined && value !== '') {

        getLocation(value, '${DISTRICT}', 'current_district_selected',
            '<option value="">${CountyDistrict}</option>');
      }
    });

    jQuery('#current_district_selected').on("change", function () {
      var value = jQuery(this).val();
      if (value != null && value !== undefined && value !== '') {
        getLocation(value, '${COMMUNE}', 'current_commune_selected',
            '<option value="">${CommuneLanguage}</option>');
      }
    });

    function getLocation(parent, id, selectionId, defaultTitle) {
      var selection = $('#'.concat(selectionId));
      if (selection != null && selection !== undefined) {
        $.ajax({
          type: 'GET',
          url: '/ajax-controller/get-location/' + parent + '/' + id,
          contentType: "application/json;charset=utf-8",
          beforeSend: function (xhr) {
            if ("${_csrf.parameterName}" && "${_csrf.token}") {
              xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
            }
          },
          dataType: 'json',
          timeout: 60000,
          success: function (data) {
            var htmlCode = defaultTitle;
            for (var i = 0; i < data.length; ++i) {
              htmlCode = htmlCode.concat(
                  "<option value=".concat(data[i].code).concat(">").concat(data[i].name).concat(
                      "</option>"));
            }
            selection.html(htmlCode);
          }
        });
      }
    };
  });

  jQuery(window).on("load", function () {
    loadImageProfile();
  });

  jQuery('input[type=file]').on('change', function () {
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
            $.MessageBox({message: 'API upload image error'});
          }
        }
      });
    }).fail(function () {
      loadImageProfile();
    });
    return false;
  });

  function loadImageNavbar() {
//    jQuery('.avatar.avatar-online img').attr('src', data);
    var image_avatar = $('.dropify-render img').attr('src');
    jQuery('.avatar.avatar-online').html('<div id="img-profile"><img src="' + image_avatar
        + '" style="height: 30px"></div>');
  }

  function loadImageProfile() {
    var url = "${userLogin.s3Url}";
    if (url == "null" || url == '') {
      jQuery('.dropify-wrapper').addClass("has-preview");
      jQuery('.dropify-loader').prop("style", "display: none;");
      jQuery('.dropify-preview').prop("style", "display: block;");
      jQuery('.dropify-render').html(""
          + "<img src=\"${userLogin.dataImage}\">");

    } else {
      <%--jQuery('.dropify-render img').attr("src", '${userLogin.s3Url}');--%>
      jQuery('.dropify-render').html(""
          + "<img src=\"${userLogin.s3Url}\">");
      jQuery('.dropify-wrapper').addClass("has-preview");
      jQuery('.dropify-loader').prop("style", "display: none;");
      jQuery('.dropify-preview').prop("style", "display: block;");
    }
  }

  function getGender(elem) {
    var gender = elem.value;
    $('#hidden_gender').val(gender);

  }

  jQuery('#updateProfile').on("click", function (e) {
    e.preventDefault();
    var firstName = $('#first-name').val();
    var lastName = $('#last-name').val();
    var displayName = firstName + lastName;
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
    console.log(province);
    console.log(residence_province);
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
          firstName: firstName,
          lastName: lastName,
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
            $.MessageBox(
                {message: '<spring:message code="popup.message.update.info.success"/>'});
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
</body>
</html>