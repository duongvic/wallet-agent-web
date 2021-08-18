<%@ page import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.SendModeType" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="EMAIL_TYPE" value="<%=SendModeType.SEND_MODE_EMAIL.code%>"/>
<spring:message code="label.enter.email" var="enterEmail"/>
<div class="modal fade" id="modalSendEmail" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-center">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span><i
            class="fas fa-times"></i></span></button>
        <h4 class="modal-title"><spring:message code="help.transaction.send.email"/> </h4>
      </div>

        <div class="modal-body">
          <div class="form-group">
            <input type="email" class="form-control" id="email" name="receiver"
                   placeholder="${enterEmail}">
          </div>
        </div>

        <div class="modal-footer">
          <button class="btn btn-primary btn-sm pull-right ml-5 mr-5"
          onclick="sendResult('${EMAIL_TYPE}', '${transactionsPinCode != null ? transactionsPinCode.id : transactionId}', 'modalSendEmail')"><spring:message code="common.btn.submit"/>
          </button>
        </div>
    </div>
  </div>
</div>