<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal fade modal-fall" id="modalPaymentSecurityOTP" aria-hidden="true"
     aria-labelledby="examplePositionCenter" role="dialog" tabindex="-1">
  <div class="modal-dialog modal-simple modal-sm modal-center">
    <div class="modal-content bg-0">
      <div class="modal-header">
        <button type="button" class="close close-bg-0" data-dismiss="modal" aria-label="Close"><span
            aria-hidden="true" class=""><i class="icon pe-close"></i></span></button>
      </div>
      <div class="modal-body">
        <div class="row">
            <div class="panel panel-bordered">
              <div class="panel-heading text-center">
                <h3 class="panel-title"><spring:message code="payment.security.dialog.otp.title"/></h3></div>
              <div class="panel-body text-center mb-10">
                <div class="form-group row show-pass">
                  <label class="col-sm-4 form-control-label"><spring:message code="payment.security.enter"/></label>
                  <div class="col-sm-8"><input type="text" class="form-control" id="codeOTP"
                                               name="codeOTP"  value="${codeOTP}"
                                               required></div>
                </div>
                <button type="submit" class="btn btn-primary btn-sm"><spring:message code="payment.security.dialog.confirm"/><i
                    class="icon wb-arrow-right ml-10"></i></button>
              </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </div>
      </div>
    </div>
  </div>
</div>
