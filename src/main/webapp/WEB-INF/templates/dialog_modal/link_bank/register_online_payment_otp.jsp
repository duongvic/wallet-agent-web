<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal fade modal-fall" id="modalRegisterOnlinePaymentOtp"
     aria-hidden="true" aria-labelledby="examplePositionCenter"
     role="dialog" tabindex="-1">
  <div class="modal-dialog modal-simple modal-center">
    <div class="modal-content bg-0">
      <div class="modal-header">
        <button type="button" class="close close-bg-0" data-dismiss="modal"
                aria-label="Close">
          <span aria-hidden="true" class=""><i class="icon pe-close"></i></span>
        </button>
      </div>

      <div class="modal-body">
        <div class="row">
          <form id="form-register-payment-onile-otp">
            <div class="panel panel-bordered">
              <%--<div class="panel-heading">--%>

              <%--</div>--%>
              <div class="panel-body mb-10">
                <div class="form-group row justify-content-center">
                  <div class="col-sm-11">
                    <c:if test="${(codeErr != null && fn:length(codeErr) gt 0)}">
                      <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                        <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                      </div>
                    </c:if>

                  </div>
                </div>

                <div class="form-group row justify-content-center">
                  <div class="col-sm-11 mb-10">
                    <label>Mã bảo mật đã được gửi về số điện thoại
                      <span style="color: #27ADD0"><sec:authentication
                          property="principal.username"></sec:authentication></span>
                      <span>, vui lòng nhập mã để xác nhận giao dịch.</span>
                    </label>
                  </div>
                </div>


                <div class="form-group row justify-content-center">
                  <div class="col-sm-11 mb-10">
                    <div class="input-group">
                      <input type="text" id="codeOTP" name="codeOTP" class="form-control"
                             placeholder="Nhập mã kích hoạt" value="${codeOTP}"
                             data-plugin="formatter" required>
                      <span class="input-group-btn">
                                                <button type="button"
                                                        class="btn btn-default btn-outline">
                                                    <i class="fa fa-rotate-left"></i> <spring:message code="common.btn.resend"/></button>
                                              </span>
                    </div>
                  </div>
                </div>
                <div class="form-group row justify-content-center">
                  <div class="col-sm-11 mb-10">
                    <button type="button" class="btn btn-primary btn-sm full-width"
                            onclick="registerPaymentOnineConfirm()" data-dismiss="modal" for=""><spring:message code="common.btn.confirm"/>
                    </button>
                  </div>
                </div>
              </div>

            </div>


            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  function registerPaymentOnineConfirm() {
    $("#hidden_ssn").val(ssn);

    var formData = {
      "${_csrf.parameterName}": "${_csrf.token}",
      accountNumber: $('input[name=accountNumber]').val(),
      fullName: $('input[name=fullName]').val(),
      phoneNumber: "${userLogin.phoneNumber}",
      cardIssueDate: $("#cardIssueDate").val(),
      bankCode: $("#hidden_bankCode").val(),
      branchBank: $("#chiNhanh").val(),
      codeOTP: $("#codeOTP").val(),
      ssn : ssn
    };

    $.ajax({
      type: "GET",
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      url: "/ajax-controller/register-payment-online-confirm",
      beforeSend: function (xhr) {
        if ("${_csrf.parameterName}" && "${_csrf.token}") {
          xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
        }
      },
      data: formData,
      timeout: 60000,
      success: function (data) {
        if ("Success" === data.value) {
          $('#modalPaymentRegisterSuccess').modal('show');
        } else {
          $('#modalPaymentRegisterErro').modal('show');
        }
      },
    });
  }
</script>