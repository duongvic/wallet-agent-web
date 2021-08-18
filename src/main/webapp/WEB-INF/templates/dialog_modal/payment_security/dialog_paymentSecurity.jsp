<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal fade modal-fall" id="modalPaymentSecurity" aria-hidden="true"
     aria-labelledby="examplePositionCenter" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-simple modal-sm modal-center">
        <div class="modal-content bg-0">
            <div class="modal-header">
                <button type="button" class="close close-bg-0" data-dismiss="modal"
                        aria-label="Close"><span
                    aria-hidden="true" class=""><i class="icon pe-close"></i></span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form id="login-form" method="post">
                        <div class="panel panel-bordered">
                            <div class="panel-heading text-center">
                                <h3 class="panel-title"><spring:message
                                    code="payment.security.dialog.setting"/></h3></div>
                            <div class="panel-body text-center mb-10">
                                <div class="form-group row show-pass">
                                    <div id="code-error"
                                         class="text-danger error-message form-group row mb-10 hidden"
                                         style="margin-left: 2.5em; margin-right: 2.5em;">
                                        <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}
                                        </small>
                                    </div>
                                </div>
                                <div class="form-group row show-pass">
                                    <label class="col-sm-4 form-control-label"><spring:message
                                        code="payment.security.dialog.password"/></label>
                                    <spring:message code="payment.security.dialog.password.input"
                                                    var="password_placeholder"/>
                                    <div class="col-sm-8"><input type="password"
                                                                 class="form-control" id="passWord"
                                                                 name="passWord"
                                                                 placeholder="${password_placeholder}"
                                                                 value="${passWord}"
                                                                 required> <span
                                        class="fa fa-eye show-icon"
                                        style="margin-right: 1em;"
                                        onclick="mouseoverPassDlg('passWord')"
                                        onmouseout="mouseoutPassDlg('passWord')"></span></div>
                                </div>

                                <div class="form-group row mb-10">
                                    <label class="col-sm-4 form-control-label"></label>
                                    <div class="col-sm-4">
                                        <button id="check-login-button"
                                                type="button" class="btn btn-primary btn-sm">
                                            <spring:message code="payment.security.dialog.confirm"/>
                                            <i class="icon wb-arrow-right ml-10"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <input type="hidden"  id="referenceId" name="referenceId" value=""/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<spring:message code="payment.security.dialog.error.message" var="error_code"/>
<script>
  //Function show pass
  function mouseoverPassDlg(obj) {
    document.getElementById(obj).type = "text";
  }

  function mouseoutPassDlg(obj) {
    document.getElementById(obj).type = "password";
  }
  function login() {
    var code_error_tag = $("#code-error");
    $.ajax({
      type: "GET",
      contentType: "application/json;charset=utf-8",
      url: "/ajax-controller/payment-security/sms/limitation",
      beforeSend: function (xhr) {
        if ("${_csrf.parameterName}" && "${_csrf.token}") {
          xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
        }
      },
      data: {
        "${_csrf.parameterName}": "${_csrf.token}",
        passWord: $("#passWord").val()
      },
      dataType: 'json',
      timeout: 60000,
      success: function (data) {
//        console.log(data);
        $('#login-form').submit();
        $("#referenceId").val(data.referenceId);
        code_error_tag.html("");
        code_error_tag.addClass("hidden");
      },
      error: function (e) {
        code_error_tag.html(
            "<small><i class=\"fa fa-times-circle\"></i>".concat("&nbsp;${error_code}").concat(
                "</small>"));
        code_error_tag.removeClass("hidden");
      }
    });
  }

  function getOTPForSecure() {
    var code_error_tag = $("#code-error");
    $.ajax({
      type: "GET",
      contentType: "application/json;charset=utf-8",
      url: "/ajax-controller/payment-security/get-OTP-for-pin-pay",
      beforeSend: function (xhr) {
        if ("${_csrf.parameterName}" && "${_csrf.token}") {
          xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
        }
      },
      data: {
        "${_csrf.parameterName}": "${_csrf.token}",
        passWord: $("#passWord").val()
      },
      dataType: 'json',
      timeout: 60000,
      success: function (data) {
        if(data.status.code!=0){
          code_error_tag.html(
              "<small><i class=\"fa fa-times-circle\"></i>".concat("&nbsp;${error_code}").concat(
                  "</small>"));
          code_error_tag.removeClass("hidden");
        }else{
          $('#referenceId').val(data.referenceId);
          $('#login-form').submit();
          code_error_tag.html("");
          code_error_tag.addClass("hidden");
        }
      },
      error: function (e) {
        code_error_tag.html(
            "<small><i class=\"fa fa-times-circle\"></i>".concat("&nbsp;${error_code}").concat(
                "</small>"));
        code_error_tag.removeClass("hidden");
        return false;
      }
    });
  }
</script>