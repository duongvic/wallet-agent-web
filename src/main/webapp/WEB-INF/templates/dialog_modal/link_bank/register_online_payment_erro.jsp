<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal fade modal-fall" id="modalPaymentRegisterErro" aria-hidden="true"
     aria-labelledby="examplePositionCenter" role="dialog" tabindex="-1">
  <div class="modal-dialog modal-simple modal-center">
    <div class="modal-content bg-0">
      <%--<div class="modal-header">--%>
      <%--<button type="button" class="close close-bg-0" data-dismiss="modal" aria-label="Close"><span--%>
      <%--aria-hidden="true" class=""><i class="icon pe-close"></i></span></button>--%>
      <%--</div>--%>
      <div class="modal-body">
        <div class="row justify-content-center">
          <div class="panel panel-bordered">
            <div class="panel-heading text-center">
              <h4 class="panel-title"><spring:message code="register.online.payment"/></h4>
            </div>
            <div class="panel-body text-center mb-10">
              <div class="form-group row">
                <label class="col-sm-12 form-control-label"><spring:message code="register.online.payment.try.again"/> </label>
              </div>
              <div class="form-group row justify-content-center">
                <button type="button" class="btn btn-warning btn-sm col-sm-11" data-dismiss="modal" for="">
                  TRY AGAIN</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>