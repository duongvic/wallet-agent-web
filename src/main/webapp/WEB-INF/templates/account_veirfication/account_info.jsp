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
  <title><spring:message code="account.info.page.title"/>-<spring:message
      code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <link rel="stylesheet" href="<c:url value="/assets/development/css/epin.css"/>">
  <!-- /head libs  -->

  <style>
    select:required:invalid {
      color: gray;
    }

    option[value=""][disabled] {
      display: none;
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
<spring:message code="label.gender.male" var="genderMale"/>
<spring:message code="label.gender.female" var="genderFemale"/>
<spring:message code="label.gender.other" var="genderOther"/>
<spring:message code="account.info.gender" var="label_gender"/>
<spring:message code="label.city" var="label_city"/>
<spring:message code="label.commune" var="label_commune"/>
<spring:message code="account.info.current.district" var="label_district"/>
<spring:message code="account.select.province.city" var="label_province_city"/>
<spring:message code="label.county.district" var="label_countyDistrict"/>

<c:set var="DISTRICT" value="<%=Location.DISTRICT%>" scope="application"/>
<c:set var="COMMUNE" value="<%=Location.COMMUE%>" scope="application"/>

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
              <jsp:param name="btActionVerify" value="true"/>
            </jsp:include>

            <%--personal information--%>
            <div class="form-group row">
              <div class="col-xxl-12 col-lg-12 col-md-12 col-sm-12">
                <form id="id-form-personal-info" method="get" action="/account/verify-kyc">
                  <div class="form-group row mb-10">
                    <div class="col-xxl-7 col-lg-7 col-md-12 col-sm-12">
                      <c:if test="${'true' ne param.btActionVerify && kycStatus ne 4}">
                        <h4 style="line-height: 3rem;"><spring:message
                            code="account.info.page.tab.personal.info.title"/></h4>
                      </c:if>
                    </div>
                    <c:if test="${'true' ne param.btActionVerify && kycStatus eq 0}">
                      <div class="col-xxl-5 col-lg-5 col-md-12 col-sm-12" id="btEditPersonInf">
                        <a href="javascript:void(0)" onclick="showBtEditPersonalInf()"
                           class="btn btn-primary pull-right">
                          <spring:message code="label.button.title.edit"/>
                          <i class="icon wb-chevron-right ml-10"></i>
                        </a>
                      </div>
                    </c:if>
                  </div>

                  <c:if test="${'true' ne param.btActionVerify && kycStatus ne 4}">
                    <div class="form-group row">
                      <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12">
                        <div class="form-group row mb-10">
                          <spring:message code="account.info.full.name" var="label_full_name"/>
                          <label class=" col-sm-3 col-form-label pb-0">${label_full_name}<span
                              class="text-danger">(*)</span></label>
                          <div class="col-sm-9 ">
                            <input placeholder="${label_full_name}" id="display-name"
                                   value="${displayName}"
                                   name="displayName" class="form-control pb-0" required/>
                          </div>
                        </div>

                        <div class="form-group row mb-15">
                          <label class=" col-sm-3 col-form-label pb-0">${label_gender}<span
                              class="text-danger">(*)</span></label>
                          <div class="col-sm-9">
                            <select class="form-control pb-0"
                                    id="gender"
                                    name="gender" placeholder="${label_gender}"
                                    onchange="getGender(this);">
                                <%--<select data-plugin="select2" placeholder="${label_gender}"--%>
                                <%--id="gender"  name="gender" class="form-control pb-0"--%>
                                <%--onchange="getGender(this);">--%>
                              <option
                                  value="0" ${(gender eq 0 ? 'selected' : '')}>${genderMale}</option>
                              <option
                                  value="1" ${(gender eq 1 ? 'selected' : '')}>${genderFemale}</option>
                            </select>
                            <input type="hidden" id="hidden_gender" value="${gender}">
                          </div>
                        </div>

                        <div class="form-group row mb-15">
                          <spring:message code="account.info.birth.date" var="label_dob"/>
                          <label class=" col-sm-3 col-form-label pb-0">${label_dob}<span
                              class="text-danger">(*)</span></label>
                          <div class="col-sm-9 time">
                            <div class="input-group">
                        <span class="input-group-addon">
                          <i class="icon wb-calendar" aria-hidden="true"></i>
                        </span>
                              <input type="text" id="datepickerDOB"
                                     class="form-control"
                                     data-plugin="datepicker"
                                     data-date-format="dd/mm/yyyy"
                                     name="dateOfBirth"
                                     value="${dateOfBirth}" required>
                            </div>
                          </div>
                        </div>

                        <div class="form-group row mb-5">
                          <spring:message code="account.info.identity.card.id.number"
                                          var="label_id_number"/>
                          <spring:message code="lable.only.input.numberic.8.9.12"
                                          var="number_only_warning_string"/>
                          <label class=" col-sm-3 col-form-label pb-0">${label_id_number}<span
                              class="text-danger">(*)</span></label>
                          <div class="col-sm-9">
                            <input placeholder="${label_id_number}"
                                   id="cmt"
                                   name="cmt"
                                   value="${cmt}"
                                   class="form-control pb-0"
                                   pattern="^[0-9]{8,9}$|^[0-9]{12}$" minlength="8" maxlength="12"
                                   onkeypress="return isNumberKey(event)"
                                   title="${number_only_warning_string}" required/>
                          </div>
                        </div>

                        <div class="form-group row mb-15">
                          <spring:message code="account.info.identity.card.issue.date"
                                          var="label_issue_date"/>
                          <label class=" col-sm-3 col-form-label pb-0">${label_issue_date}<span
                              class="text-danger">(*)</span></label>
                          <div class="col-sm-9 time">
                            <div class="input-group">
                        <span class="input-group-addon">
                          <i class="icon wb-calendar" aria-hidden="true"></i>
                        </span>
                              <input type="text" id="datepickerIssueDate"
                                     class="form-control"
                                     data-plugin="datepicker"
                                     data-date-format="dd/mm/yyyy"
                                     name="issueDate"
                                     value="${issueDate}" required>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12">
                        <div class="form-group row mb-10">
                          <spring:message code="account.info.identity.card.issue.place"
                                          var="label_issue_place"/>
                          <label class=" col-sm-3 col-form-label pb-0">${label_issue_place}<span
                              class="text-danger">(*)</span></label>
                          <div class="col-sm-9">
                            <input placeholder="${label_issue_place}"
                                   id="issuePlace" name="issuePlace" value="${issuePlace}"
                                   class="form-control pb-0" required/>
                          </div>
                        </div>

                        <div class="form-group row mb-10">
                          <spring:message code="account.info.permanent.residence"
                                          var="label_permanent_residence"/>
                          <label
                              class=" col-sm-3 col-form-label pb-0">${label_permanent_residence}<span
                              class="text-danger">(*)</span></label>
                          <div class="col-sm-9">
                            <input class="form-control pb-0"
                                   placeholder="${label_permanent_residence}"
                                   id="residence_street1"
                                   name="residence_street1" value="${residence_street1}" required/>
                          </div>
                        </div>

                        <div class="form-group row mb-10">

                          <label class=" col-sm-3 col-form-label pb-0">${label_province_city}<span
                              class="text-danger">(*)</span></label>
                          <div class="col-sm-9">
                            <select required class="form-control" id="province_selected"
                                    name="residence_province">
                              <option value="" disabled selected>${label_province_city}</option>
                              <c:forEach items="${listResProvince}" var="item">
                                <option
                                    value="${item.code}" ${residence_province eq item.code ? 'selected' : ''}>${item.name}</option>
                              </c:forEach>
                            </select>
                          </div>
                        </div>

                        <div class="form-group row mb-10">
                          <label class=" col-sm-3 col-form-label pb-0">${label_district}<span
                              class="text-danger">(*)</span></label>
                          <div class="col-sm-9">
                            <select required class="form-control" id="district_selected"
                                    name="residence_district">
                              <option value="" disabled selected>${label_district}</option>
                              <c:forEach items="${listResDistrict}" var="item">
                                <option
                                    value="${item.code}" ${residence_district eq item.code ? 'selected' : ''}>${item.name}</option>
                              </c:forEach>
                            </select>
                          </div>
                        </div>

                        <div class="form-group row mb-10">

                          <label class=" col-sm-3 col-form-label pb-0">${label_commune}<span
                              class="text-danger">(*)</span></label>
                          <div class="col-sm-9">
                            <select required class="form-control" id="commune_selected"
                                    name="residence_commune">
                              <option value="" disabled selected>${label_commune}</option>
                              <c:forEach items="${listResCommune}" var="item">
                                <option
                                    value="${item.code}" ${residence_commune eq item.code ? 'selected' : ''}>${item.name}</option>
                              </c:forEach>
                            </select>
                          </div>
                        </div>

                        <div class="form-group row mb-10">
                          <div class="col-9 offset-3">
                            <div class="row hidden pull-right mr-0"
                                 id="show-hidden-bt-edit-personal-info">
                              <a href="javascript:void(0)" onclick="hiddenBtEditPersonalInf()"
                                 class="btn btn-secondary mr-2">
                                <i class="icon wb-chevron-left ml-10"></i>
                                <spring:message code="label.button.cancel"/>
                              </a>

                                <%--<a href="javascript:void(0)" id="updateProfile"--%>
                                <%--class="btn btn-primary">--%>
                                <%--<spring:message code="label.button.title.save"/>--%>
                                <%--<i class="icon wb-chevron-right ml-10"></i>--%>
                                <%--</a>--%>
                                <%----%>
                              <button type="submit" id="updateProfile"
                                      class="btn btn-primary">
                                <spring:message code="label.button.title.save"/>
                                <i class="icon wb-chevron-right ml-10"></i>
                              </button>


                            </div>
                          </div>

                        </div>
                      </div>
                    </div>
                  </c:if>

                  <input type="hidden" name="${_csrf.parameterName}"
                         value="${_csrf.token}"/>
                  <input type="hidden" name="urlImage" value="${urlImage}">
                </form>
              </div>
            </div>
            <%--end personal information--%>

            <%--additional information--%>
            <div class="form-group row">
              <div class="col-xxl-12 col-lg-12 col-md-12 col-sm-12">
                <form id="id-form-addtional-info">
                  <div class="form-group row mb-10">
                    <div class="col-xxl-7 col-lg-7 col-md-12 col-sm-12">
                      <h4 style="line-height: 3rem;"><spring:message
                          code="account.info.page.tab.additional.info.title"/></h4>
                    </div>
                    <c:if test="${'true' ne param.btActionVerify}">
                      <div class="col-xxl-5 col-lg-5 col-md-12 col-sm-12" id="btEditAdditionalInf">
                        <a href="javascript:void(0)" onclick="showBtEditAdditionalInf()"
                           class="btn btn-primary pull-right">
                          <spring:message code="label.button.title.edit"/>
                          <i class="icon wb-chevron-right ml-10"></i>
                        </a>
                      </div>
                    </c:if>
                  </div>

                  <div class="form-group row mb-10">
                    <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12">
                      <div class="form-group row mb-10">
                        <spring:message code="account.info.job.email" var="label_email"/>
                        <label class=" col-sm-3 col-form-label pb-0">${label_email}</label>
                        <div class="col-sm-9 ">
                          <input placeholder="${label_email}" id="email"
                                 value="${email}"
                                 name="email" class="form-control pb-0" required/>
                        </div>
                      </div>
                    </div>

                    <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12">
                      <div class="form-group row mb-10">
                        <spring:message code="account.info.nick.name" var="label_nickName"/>
                        <label class=" col-sm-3 col-form-label pb-0">${label_nickName}</label>
                        <div class="col-sm-9 ">
                          <input placeholder="${label_nickName}" id="nickName"
                                 value="${nickName}"
                                 name="nickName" class="form-control pb-0" required/>
                        </div>
                      </div>
                    </div>
                  </div>


                  <div class="form-group row mb-10">
                    <div class="col-xxl-12 col-lg-12 col-md-12 col-sm-12">
                      <div class="my-checkBox">
                        <input type="checkbox" name="sameAddressPersonal"
                               id="same_address_personal">
                        <label for="same_address_personal">&nbsp;
                          <spring:message code="account.info.store.addr.wth.residence.addr"/>
                        </label>
                      </div>
                    </div>
                  </div>

                  <div class="form-group row">
                    <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12">
                      <div class="form-group row mb-10">
                        <spring:message code="account.info.current.address"
                                        var="label_current_address"/>
                        <label
                            class=" col-sm-3 col-form-label pb-0">${label_current_address}&nbsp;</label>
                        <div class="col-sm-9">
                          <input placeholder="${label_current_address}" class="form-control pb-0"
                                 id="livingAddress" name="livingAddress"
                                 value="${livingAddress}"/>
                        </div>
                      </div>
                      <div class="form-group row mb-10">
                        <label
                            class=" col-sm-3 col-form-label pb-0">${label_province_city}&nbsp;</label>
                        <div class="col-sm-9">
                          <select required class="form-control"
                                  id="current_province_selected"
                                  name="current_province">
                            <option value="" disabled selected>${label_province_city}</option>
                            <c:forEach items="${listProvince}" var="item">
                              <option
                                  value="${item.code}" ${current_province eq item.code ? 'selected' : ''}>${item.name}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                      <div class="form-group row mb-10">
                        <label class=" col-sm-3 col-form-label pb-0">${label_district}&nbsp;</label>
                        <div class="col-sm-9">
                          <select required class="form-control"
                                  id="current_district_selected"
                                  name="current_district">
                            <option value="" disabled selected>${label_district}</option>
                            <c:forEach items="${listDistrict}" var="item">
                              <option
                                  value="${item.code}" ${current_district eq item.code ? 'selected' : ''}>${item.name}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                      <div class="form-group row mb-10">
                        <label class=" col-sm-3 col-form-label pb-0">${label_commune}&nbsp;</label>
                        <div class="col-sm-9">
                          <select required class="form-control"
                                  id="current_commune_selected"
                                  name="current_commune">
                            <option value="" disabled selected>${label_commune}</option>
                            <c:forEach items="${listCommune}" var="item">
                              <option
                                  value="${item.code}" ${current_commune eq item.code ? 'selected' : ''}>${item.name}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                    </div>

                    <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12">

                      <div class="form-group row mb-10">
                        <spring:message code="account.info.nationaly" var="label_nationaly"/>
                        <label class=" col-sm-3 col-form-label pb-0">${label_nationaly}</label>
                        <div class="col-sm-9">
                          <input placeholder="${label_nationaly}"
                                 id="national" name="national" value="${national}"
                                 class="form-control pb-0"/>
                        </div>
                      </div>
                      <div class="form-group row mb-10">
                        <spring:message code="account.info.occupation" var="label_occupation"/>
                        <spring:message code="label.select.choose.occupation"
                                        var="label_select_occupation"/>

                        <label class=" col-sm-3 col-form-label pb-0">${label_occupation}</label>
                        <div class="col-sm-9">
                          <select required class="form-control"
                                  id="id-occupation"
                                  name="occupation">
                            <option value="" disabled selected>${label_select_occupation}</option>
                            <c:forEach items="${listOccupation}" var="item">
                              <option
                                  value="${item.id}" ${occupation != null && occupation != 'null' && occupation eq item.id ? 'selected' : ''}>${item.name}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                      <div class="form-group row mb-10">
                        <spring:message code="account.info.job.position" var="label_job_position"/>
                        <spring:message code="label.select.choose.job.position"
                                        var="label_select_position"/>

                        <label
                            class=" col-sm-3 col-form-label pb-0">${label_job_position}</label>
                        <div class="col-sm-9">
                          <select required class="form-control"
                                  id="id-position"
                                  name="position">
                            <option value="" disabled selected>${label_select_position}</option>
                            <c:forEach items="${listPosition}" var="item">
                              <option
                                  value="${item.id}" ${position != null && position != 'null' && position eq item.id ? 'selected' : ''}>${item.name}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                      <div class="form-group row mb-10">
                        <div class="col-9 offset-3">
                          <div class="row hidden pull-right mr-0"
                               id="show-hidden-bt-edit-additional-info">
                            <a href="javascript:void(0)" onclick="hiddenBtEditAdditionalInf()"
                               class="btn btn-secondary mr-2">
                              <i class="icon wb-chevron-left ml-10"></i>
                              <spring:message code="label.button.cancel"/>
                            </a>

                            <a href="javascript:void(0)" id="update_additional_info"
                               class="btn btn-primary">
                              <spring:message code="label.button.title.save"/>
                              <i class="icon wb-chevron-right ml-10"></i>
                            </a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>


                  <input type="hidden" id="hidden_current_province_selected" value="">
                  <input type="hidden" id="hidden_current_district_selected" value="">
                  <input type="hidden" id="hidden_current_commune_selected" value="">

                  <input type="hidden" name="${_csrf.parameterName}"
                         value="${_csrf.token}"/>
                  <input type="hidden" name="urlImage" value="${urlImage}">
                </form>
              </div>
            </div>
            <%--end additional information--%>
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
<!-- /footer -->
<c:import url="../include_page/js_singledatepicker.jsp"/>
<spring:message code="popup.button.yes" var="btYes"/>
<spring:message code="popup.button.no" var="btNo"/>
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
      $('#id-form-personal-info').find('input, textarea, select').prop('disabled', true);
      $('#id-form-addtional-info').find('input, textarea, select').prop('disabled', true);

      $('#hidden_current_province_selected').val($('#current_province_selected').val());
      $('#hidden_current_district_selected').val($('#current_district_selected').val());
      $('#hidden_current_commune_selected').val($('#current_commune_selected').val());

      jQuery('#province_selected').on("change", function () {
        var value = jQuery(this).val();
        if (value != null && value !== undefined && value !== '') {

          getLocation(value, '${DISTRICT}', 'district_selected',
              '<option value="">${label_countyDistrict}</option>');

          getLocation(value, '${COMMUNE}', 'commune_selected',
              '<option value="">${label_commune}</option>');
        }
      });

      jQuery('#current_province_selected').on("change", function () {
        var value = jQuery(this).val();
        if (value != null && value !== undefined && value !== '') {

          getLocation(value, '${DISTRICT}', 'current_district_selected',
              '<option value="">${label_countyDistrict}</option>');

          getLocation(value, '${COMMUNE}', 'current_commune_selected',
              '<option value="">${label_commune}</option>');
        }
      });

    });

    jQuery('#district_selected').on("change", function () {
      var value = jQuery(this).val();
      if (value != null && value !== undefined && value !== '') {
        getLocation(value, '${COMMUNE}', 'commune_selected',
            '<option value="">${label_commune}</option>');
      }
    });

    jQuery('#current_district_selected').on("change", function () {
      var value = jQuery(this).val();
      if (value != null && value !== undefined && value !== '') {
        getLocation(value, '${COMMUNE}', 'current_commune_selected',
            '<option value="">${label_commune}</option>');
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
  })(document, window, jQuery);

  <%--jQuery('#updateProfile').on("click", function (e) {--%>
  <%--e.preventDefault();--%>
  <%--var displayName = $('#display-name').val();--%>
  <%--var gender = $('#hidden_gender').val();--%>
  <%--var dateOfBirth = $('input[name=dateOfBirth]').val();--%>
  <%--if (dateOfBirth == "") {--%>
  <%--dateOfBirth = null;--%>
  <%--}--%>
  <%--//hien tai--%>
  <%--var livingAddress = $('#livingAddress').val();--%>
  <%--var province = $('#current_province_selected').val();--%>
  <%--var district = $('#current_district_selected').val();--%>
  <%--var commune = $('#current_commune_selected').val();--%>

  <%--var jobOccupation = $('#jobOccupation').val();--%>
  <%--var jobPosition = $('#jobPosition').val();--%>
  <%--var email = $('#email').val();--%>

  <%--var residence_province = $('#province_selected').val();--%>
  <%--var residence_district = $('#district_selected').val();--%>
  <%--var residence_commune = $('#commune_selected').val();--%>
  <%--var residence_street1 = $('#residence_street1').val();--%>

  <%--$.MessageBox({--%>
  <%--buttonDone: '<spring:message code="popup.button.yes"/>',--%>
  <%--buttonFail: '<spring:message code="popup.button.no"/>',--%>
  <%--message: '<spring:message code="popup.message.do.u.want.update.info"/>'--%>
  <%--}--%>
  <%--).done(function () {--%>
  <%--$.ajax({--%>
  <%--type: 'POST',--%>
  <%--url: '/ajax-controller/profile/update',--%>
  <%--beforeSend: function (xhr) {--%>
  <%--if ("${_csrf.parameterName}" && "${_csrf.token}") {--%>
  <%--xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");--%>
  <%--}--%>
  <%--},--%>
  <%--data: {--%>
  <%--"${_csrf.parameterName}": "${_csrf.token}",--%>
  <%--displayName: displayName,--%>
  <%--gender: gender,--%>
  <%--dateOfBirth: dateOfBirth,--%>
  <%--livingAddress: livingAddress,--%>
  <%--province: province,--%>
  <%--district: district,--%>
  <%--commune: commune,--%>
  <%--jobOccupation: jobOccupation,--%>
  <%--jobPosition: jobPosition,--%>
  <%--email: email,--%>
  <%--residence_province: residence_province,--%>
  <%--residence_district: residence_district,--%>
  <%--residence_commune: residence_commune,--%>
  <%--residence_street1: residence_street1--%>

  <%--},--%>
  <%--timeout: 60000,--%>
  <%--cache: false,--%>
  <%--success: function (data) {--%>
  <%--if (data.status.code == 0) {--%>
  <%--window.location.replace("${urlAccountVerify}");--%>
  <%--}--%>
  <%--else {--%>
  <%--$.MessageBox({message: data.status.value});--%>
  <%--}--%>
  <%--}--%>
  <%--});--%>
  <%--}).fail(function () {--%>
  <%--window.location.href = '<%=AccountVeirficationController.REDIRECT_ACCOUNT_INFO%>';--%>
  <%--});--%>
  <%--});--%>

  //Addition info hien tai
  var email = $('#email').val();
  var livingAddress = $('#livingAddress').val();
  var province = $('#current_province_selected').val();
  var commune = $('#current_commune_selected').val();
  var district = $('#current_district_selected').val();
  var jobOccupation = $('#id-occupation').val();
  var jobPosition = $('#id-position').val();
  var nickName = $('#nickName').val();
  var national = $('#national').val();

  //Persional info
  var displayName = $('#display-name').val();
  var gender = $('#gender').val();
  var dateOfBirth = $('input[name=dateOfBirth]').val();
  if (dateOfBirth == "") {
    dateOfBirth = null;
  }
  var cmt = $('#cmt').val();
  var issueDate = $('datepickerIssueDate').val();
  if (issueDate == "") {
    issueDate = null;
  }
  var issuePlace = $('#issuePlace').val();
  var residence_street1 = $('#residence_street1').val();
  var residence_province = $('#province_selected').val();
  var residence_district = $('#district_selected').val();
  var residence_commune = $('#commune_selected').val();

  jQuery('#update_additional_info').on("click", function (e) {
    e.preventDefault();
    var livingAddress = $('#livingAddress').val();
    var province = $('#current_province_selected').val();
    var commune = $('#current_commune_selected').val();
    var district = $('#current_district_selected').val();
    <%--if(--%>
        <%--livingAddress == null || livingAddress == "undefined" || livingAddress == "" ||--%>
        <%--province == null || province == "undefined" || province == "" ||--%>
        <%--commune == null || commune == "undefined" || commune == "" ||--%>
        <%--district == null || district == "undefined" || district == ""--%>
    <%--){--%>
      <%--$.MessageBox({message: '<spring:message code="popup.message.update.required.additional.info.title"/>'});--%>
    <%--}else{--%>
      $.MessageBox({
            buttonDone: '<spring:message code="popup.button.yes"/>',
            buttonFail: '<spring:message code="popup.button.no"/>',
            message: '<spring:message code="popup.message.do.u.want.update.info"/>'
          }
      ).done(function () {
        $.ajax({
          type: 'POST',
          url: '/ajax-controller/kyc/update/additional-info',
          beforeSend: function (xhr) {
            if ("${_csrf.parameterName}" && "${_csrf.token}") {
              xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
            }
          },
          data: {
            "${_csrf.parameterName}": "${_csrf.token}",
            email: $('#email').val(),
            livingAddress: $('#livingAddress').val(),
            province: $('#current_province_selected').val(),
            district: $('#current_district_selected').val(),
            commune: $('#current_commune_selected').val(),
            jobOccupation: $('#id-occupation').val(),
            jobPosition: $('#id-position').val(),
            nickName: $('#nickName').val(),
            national: $('#national').val()
          },
          timeout: 60000,
          cache: false,
          success: function (data) {
            if (data.status.code == 0) {
              $.MessageBox({message: '<spring:message code="label.success"/>'});
              refreshActionUpdateAdditionInfo()
            }
            else {
              $.MessageBox({message: data.status.value});
            }
          }
        });
      }).fail(function () {
        window.location.href = '<%=AccountVeirficationController.REDIRECT_ACCOUNT_INFO%>';
      });
    // }

  });

  function reLoadDataPersonalInfo() {
    $('#display-name').val(displayName);
    $('#gender').val(gender);
    //set lai gia tri input hidden_gender
    $('#hidden_gender').val($('#gender').val());

    $('input[name=dateOfBirth]').val(dateOfBirth);
    $('#cmt').val(cmt);
    $('#datepickerIssueDate').val(issueDate);
    $('#issuePlace').val(issuePlace);
    $('#residence_street1').val(residence_street1);
    $('#province_selected').val(residence_province);
    $('#district_selected').val(residence_district);
    $('#commune_selected').val(residence_commune);
  }

  function showBtEditPersonalInf() {
    jQuery('#btEditPersonInf').addClass('hidden');
    jQuery('#show-hidden-bt-edit-personal-info').removeClass('hidden');

    $('#id-form-personal-info').find('input, textarea, select').prop('disabled', false);
  }

  function hiddenBtEditPersonalInf() {
    jQuery('#btEditPersonInf').removeClass('hidden');
    jQuery('#show-hidden-bt-edit-personal-info').removeClass('show');

    jQuery('#btEditPersonInf').addClass('show');
    jQuery('#show-hidden-bt-edit-personal-info').addClass('hidden');

    $('#id-form-personal-info').find('input, textarea, select').prop('disabled', true);

    reLoadDataPersonalInfo();
  }

  function showBtEditAdditionalInf() {
    jQuery('#btEditAdditionalInf').addClass('hidden');
    jQuery('#show-hidden-bt-edit-additional-info').removeClass('hidden');

    $('#id-form-addtional-info').find('input, textarea, select').prop('disabled', false);

    if('${kycStatus}' === '4'){
      $('#email').prop('disabled', true);
    }else {
      $('#email').prop('disabled', false);
    }

  }

  function hiddenBtEditAdditionalInf() {

    refreshActionUpdateAdditionInfo()

    reLoadDataAdditionInfo();
  }

  function refreshActionUpdateAdditionInfo() {
    jQuery('#btEditAdditionalInf').removeClass('hidden');
    jQuery('#show-hidden-bt-edit-additional-info').removeClass('show');

    jQuery('#btEditAdditionalInf').addClass('show');
    jQuery('#show-hidden-bt-edit-additional-info').addClass('hidden');

    $('#id-form-addtional-info').find('input, textarea, select').prop('disabled', true);
  }

  function reLoadDataAdditionInfo() {
    $("#email").val(email);
    $("#nickName").val(nickName);
    $("#livingAddress").val(livingAddress);
    $('#current_province_selected').val(province);
    $('#current_commune_selected').val(commune);
    $('#current_district_selected').val(district);
    $('#id-occupation').val(jobOccupation);
    $('#id-position').val(jobPosition);
    $('#national').val(national);
  }

  function getGender(elem) {
    var gender1 = elem.value;
    $('#hidden_gender').val(gender1);
  }

  $('#id_link_verify').click(function () {
    if ('true' == $('#hidden_id_link_verify').val()) {
      $.MessageBox({
        buttonDone: {
          btYes: {
            text: "${btYes}",
            class: "custom_button",
            keyCode: 13
          }
        },
        buttonFail: "${btNo}",
        message: "<b>Thông báo!</b><br>Bạn cần phải cập nhật đầy đủ thông tin cá nhân trước khi xác thực tài khoản",
      }).done(function () {
        showBtEditPersonalInf();
      }).fail(function () {

      });
    }
  });

  $("input:checkbox[name='sameAddressPersonal']").on('change', function () {
    var $personalAddress = $('#residence_street1').val();
    var $personalProvince = $('#province_selected').val();
    var $personalDistrict = $('#district_selected').val();
    var $personalCommune = $('#commune_selected').val();

    if ($(this).is(':checked')) {
      //clone object option province, district, commune
      $('#province_selected option').clone().appendTo('#current_province_selected');
      $('#district_selected option').clone().appendTo('#current_district_selected');
      $('#commune_selected option').clone().appendTo('#current_commune_selected');

      $("#livingAddress").val($personalAddress);
      $('#current_province_selected').val($personalProvince);
      $('#current_district_selected').val($personalDistrict);
      $('#current_commune_selected').val($personalCommune);
    }
    else {
      $("#livingAddress").val('${livingAddress}');
      $('#current_province_selected').val($('#hidden_current_province_selected').val());
      $('#current_district_selected').val($('#hidden_current_district_selected').val());
      $('#current_commune_selected').val($('#hidden_current_commune_selected').val());
    }
  });

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
          + "<img src=\"${userLogin.dataImage}\">")
//      jQuery('.dropify-wrapper').removeClass("touch-fallback");
      jQuery('.dropify-wrapper').addClass("has-preview");
      jQuery('.dropify-loader').prop("style", "display: none;");
      jQuery('.dropify-preview').prop("style", "display: block;");
    } else {
      jQuery('.dropify-render').html(""
          + "<img src=\"${userLogin.s3Url}\">")
//      jQuery('.dropify-wrapper').removeClass("touch-fallback");
      jQuery('.dropify-wrapper').addClass("has-preview");
      jQuery('.dropify-loader').prop("style", "display: none;");
      jQuery('.dropify-preview').prop("style", "display: block;");
    }
  }
</script>
</html>