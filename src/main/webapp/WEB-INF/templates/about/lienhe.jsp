<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="label.contact"/> - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="label.contact"/></li>
    </ol>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-xxl-12 col-lg-12 col-md-12 col-sm-12">
        <div class="panel panel-bordered">
          <div class="panel-body contact">
            <div class="row">
              <div class="col-lg-3"><img src="/assets/images/logo_zota3.png"></div>
              <div class="col-lg-9">
                <div class="ct-logo pl-15">
                  <h4> Công ty cổ phần ZO-TA</h4>
                  </br>
                  <p class="mb-1"><spring:message code="common.address.company"/> </p>
                  <a href="javascript:void(0)" class="mb-1">MST: 0108459991</a>

                  <div class="form-group row">
                    <div class="col-md-3 col-sm-6">
                      <a href="#" class="mr-40"><i class="icon pe-call"></i>&nbsp;<spring:message code="common.phone.support"/></a>
                    </div>
                    <div class="col-md-3 col-sm-6">
                      <a href="info@zo-ta.com" class=""><i class="icon pe-mail"></i>&nbsp;info@zo-ta.com</a>
                    </div>
                  </div>

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

</html>