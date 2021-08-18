<%@ page import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.SendModeType" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="SMS_TYPE" value="<%=SendModeType.SEND_MODE_SMS.code%>"/>
<spring:message code="label.enter.phone" var="enterPhoneNumber"/>
<div class="modal fade" id="modalSendSms" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-center">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span><i
            class="fas fa-times"></i></span></button>
        <h4 class="modal-title"><spring:message code="label.send.sms"/> </h4>
      </div>

        <div class="modal-body">
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-12 mb-3">
                <div class="form-group my-inputGroup">
                  <input type="text" class="form-control" id="phone" name="receiver"
                         placeholder="${enterPhoneNumber}">
                  <span class="show-text-r small">Phí: 1.000đ/sms</span>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button class="btn btn-primary btn-sm pull-right ml-5 mr-5"
                  onclick="sendResult('${SMS_TYPE}', '${transactionsPinCode != null ? transactionsPinCode.id : transactionId}', 'modalSendSms')"><spring:message code="common.btn.submit"/>
          </button>
        </div>
    </div>
  </div>
</div>