<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" prefix="ewallet" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="panel panel-bordered">
  <div class="panel-body ">
  <div class="form-group row mb-10">
    <h4 class="panel-title pl-0"><spring:message code="label.transaction.info"/></h4>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.transaction.type"/>:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.epin"/></label>
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.product"/>:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>${tenSP}</label>
      <input type="hidden" name="tenSP" id="tenSP" value="${tenSP}">
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.quantity"/>:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>${quantity}</label>
      <input type="hidden" name="quantity" id="quantity" value="${quantity}">
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.face.value"/>:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>${ewallet:formatNumber(faceValue)} Zpoint</label>
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.discount"/></label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>${ewallet:formatNumber(disCount)}&nbsp;Zpoint</label>
      <input type="hidden" name="disCount" id="disCount" value="${disCount}">
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.fee.amount"/>:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>0 Zpoint</label>
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.sum.amount"/>:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>${ewallet:formatNumber(realAmount)} Zpoint</label>
      <input type="hidden" name="realAmount" id="realAmount" value="${ewallet:formatNumber(realAmount)}">
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.payment.methods"/>:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.ewallet"/></label>
    </div>
  </div>
</div>

  <div class="panel-body ">
    <div class="form-group row mb-10">
      <h4 class="panel-title pl-0"><spring:message code="label.user.info"/></h4>
    </div>
    <div class="form-group row mb-10 ">
      <div class="col col-sm-6 col-md-6">
        <label><spring:message code="label.phone.number"/>:</label>
      </div>
      <div class="col col-sm-6 col-md-6">
        <label><sec:authentication property="principal.username" /></label>
      </div>
    </div>
    <div class="form-group row mb-10 ">
      <div class="col col-sm-6 col-md-6">
        <label><spring:message code="label.email"/>:</label>
      </div>
      <div class="col col-sm-6 col-md-6">
        <label>#NA</label>
      </div>
    </div>
  </div>
</div>