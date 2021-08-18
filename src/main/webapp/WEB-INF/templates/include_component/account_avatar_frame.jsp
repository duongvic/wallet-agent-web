<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="row col-xxl-12 col-lg-12 col-md-12 col-sm-12 mb-20 p-10 mx-0"
     style="border: solid 1px black">
  <div class="col-xxl-3 col-lg-3 col-md-12 col-sm-12" id="id-avatar">
    <input type="file" id="id-dropify-img" name="imgProfile"
           data-plugin="dropify" data-default-file=""
           class="br3 pull-left">
  </div>
  <div class="col-xxl-9 col-lg-9 col-md-12 col-sm-12">
    <div class="col">
      <div class="row col">
        <span class="h4">${displayName}</span>
        <br/>
      </div>
      <div class="row form-group col">
        <div class="">
          <span class="h5"><sec:authentication property="principal.username"/></span>
          <br/>
          <span class="h5"><spring:message code="label.last.login"/> ${lastLogin}</span>
          <br/>
        </div>
        <c:if test="${'true' eq param.btActionVerify}">
          <div class="col pull-right px-0">
            <c:if test="${kycStatus =='' || kycStatus eq null || kycStatus eq 3 || kycStatus eq 0}">
              <a href="javascript:void(0)" id="id_link_verify"
                 class="btn btn-primary btn-sm pull-right">
                <spring:message code="common.btn.register.member"/>
                <i class="icon wb-chevron-right ml-10"></i>
              </a>
            </c:if>
            <c:if test="${2 eq kycStatus}">
              <a href="javascript:void(0)" id=""
                 class="btn btn-warning btn-sm pull-right">
                <spring:message code="label.waiting.verification"/>
              </a>
            </c:if>
            <c:if test="${4 eq kycStatus}">
              <a href="javascript:void(0)" id=""
                 class="btn btn-success btn-sm pull-right">
                <spring:message code="label.account.verified"/>
              </a>
            </c:if>
          </div>
        </c:if>
        <input type="hidden" id="hidden_id_link_verify" value="${param.btActionVerify}">
      </div>
    </div>
  </div>
</div>

