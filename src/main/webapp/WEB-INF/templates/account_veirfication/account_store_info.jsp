<%@ page
        import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.consumer.bean.OutletStoreType" %>
<%@ page import="vn.mog.ewallet.consumer.web.controller.account.AccountVeirficationController" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <jsp:param name="nav" value="infoAccountStoreVeirfication"/>
</jsp:include>
<!-- /menu bar -->


<spring:message code="label.gender.male" var="genderMale"/>
<spring:message code="label.gender.female" var="genderFemale"/>
<spring:message code="label.gender.other" var="genderOther"/>
<spring:message code="account.info.gender" var="label_gender"/>
<spring:message code="label.city" var="label_city"/>
<spring:message code="label.commune" var="label_commune"/>
<spring:message code="account.info.current.district" var="label_district"/>
<spring:message code="account.select.province.city" var="label_province_city"/>
<spring:message code="label.county.district" var="label_countyDistrict"/>


<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="account.store.infor"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="account.store.infor"/></h1></div>
    <div class="page-content container-fluid">
        <div class="row">
            <div class="col-xxl-12 col-lg-12 col-md-12 col-sm-12">
                <div class="panel panel-bordered">
                    <div class="panel-body py-10">

                        <%--parent information--%>
                        <div class="form-group row">
                            <div class="col-xxl-12 col-lg-12 col-md-12 col-sm-12">
                                <form id="id-form-store-info" action="">

                                    <div class="form-group row">
                                        <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12">
                                            <div class="form-group row mb-10">
                                                <spring:message code="account.store.name"
                                                                var="label_store_name"/>
                                                <label class=" col-sm-3 col-form-label pb-0">${label_store_name}<span
                                                        class=""></span></label>
                                                <div class="col-sm-9 ">
                                                    <input placeholder="${label_store_name}"
                                                           id="aliasStore" name="aliasStore"
                                                           value="${aliasStore}"
                                                           class="form-control pb-0"/>
                                                </div>
                                            </div>

                                            <div class="form-group row mb-5">
                                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                                        code="content.phone.number"/><span
                                                        class=""></span></label>
                                                <div class="col-sm-9">
                                                    <input id="businessPhoneStore" name="businessPhoneStore" value="${businessPhoneStore}"
                                                           class="form-control pb-0"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xxl-6 col-lg-6 col-md-6 col-sm-12">
                                            <div class="form-group row mb-10">
                                                <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                                        code="account.info.store.address"/></label>
                                                <div class="col-sm-9">
                                                    <input id="street1Store" name="street1Store" value="${street1Store}"
                                                           class="form-control pb-0"/>
                                                </div>
                                            </div>

                                            <div class="form-group row mb-10">
                                                <label class=" col-sm-3 col-form-label pb-0">
                                                    <spring:message code="account.shop.group"/>
                                                </label>
                                                <div class="col-sm-9">

                                                    <c:set var="listOutLetStoreType"
                                                           value="<%=OutletStoreType.values() %>"/>
                                                    <select required class="form-control"
                                                            id="outletStoreType"
                                                            name="outletStoreType">
                                                        <option value="" disabled selected>---Chọn nhóm cửa hàng ---</option>
                                                        <c:forEach items="${listOutLetStoreType}" var="item">
                                                            <option value="${item.getCode()}" ${outletStoreType != null  && outletStoreType eq item.getCode() ? 'selected' : ''}>${item.getDisplayText()}</option>
                                                        </c:forEach>

                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                </form>
                            </div>
                        </div>

                        <div class="form-group row mb-10">
                            <div class="col-9 offset-3">
                                <div class="row pull-right mr-0">
                                    <a href="javascript:void(0)" id="update_store_info"
                                       class="btn btn-primary">
                                        <spring:message code="label.button.title.save"/>
                                        <i class="icon wb-chevron-right ml-10"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
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
      $('#id-form-store-info').find('input, textarea, select').prop('disabled', false);
    });

  })(document, window, jQuery);

  jQuery('#update_store_info').on("click", function (e) {
    e.preventDefault();
      $.MessageBox({
            buttonDone: '<spring:message code="popup.button.yes"/>',
            buttonFail: '<spring:message code="popup.button.no"/>',
            message: '<spring:message code="popup.message.do.u.want.update.info"/>'
          }
      ).done(function () {
        $.ajax({
          type: 'POST',
          url: '/ajax-controller/kyc/update/update-store-address-info',
          beforeSend: function (xhr) {
            if ("${_csrf.parameterName}" && "${_csrf.token}") {
              xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
            }
          },
          data: {
            "${_csrf.parameterName}": "${_csrf.token}",
            aliasStore: $('#aliasStore').val(),
            businessPhoneStore: $('#businessPhoneStore').val(),
            street1Store: $('#street1Store').val(),
            outletStoreType: $('#outletStoreType').val(),
          },
          timeout: 60000,
          cache: false,
          success: function (data) {
            if (data.status.code == 0) {
              $.MessageBox({message: '<spring:message code="label.success"/>'});
            }
            else {
              $.MessageBox({message: data.status.value});
            }
          }
        });
      }).fail(function () {
        window.location.href = '<%=AccountVeirficationController.REDIRECT_ACCOUNT_STORE_ADDRESS_NFO%>';
      });
  });
</script>
</html>