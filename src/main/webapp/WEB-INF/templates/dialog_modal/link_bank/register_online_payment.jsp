<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<spring:message code="register.payment.online.card.holder.name" var="cardHolderName"/>
<spring:message code="account.card.number" var="cardNumber"/>
<spring:message code="register.online.issue.date" var="labelIssueDate"/>
<div class="modal fade modal-fall" id="modalRegisterOnlinePayment"
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
                    <form id="form-register-payment-onile">
                        <div class="panel panel-bordered">
                            <div class="panel-heading">
                                <h5 class="panel-title font-weight-bold"><spring:message code="register.online.payment"/> </h5>
                                <label class="panel-title text-secondary"><spring:message code="register.online.payment.to.use.service.via"/>&nbsp; ${serviceName != null ? serviceName : 'Vietinbank ATM-Card'}.
                                    <spring:message code="register.online.payment.pl.checking.card.info.next.to.register.online.payment"/> </label>
                            </div>
                            <div class="panel-body mb-10">
                                <div class="row justify-content-center">
                                    <label class="col-sm-11 form-control-label text-secondary">Card
                                        holder name</label>
                                </div>
                                <div class="form-group row justify-content-center">
                                    <div class="col-sm-11">
                                        <input type="text" class="form-control font-weight-bold"
                                               id="fullName" name="fullName"
                                               placeholder="${cardHolderName}"
                                               value="${fullName}" required>
                                    </div>
                                </div>
                                <div class="row justify-content-center">
                                    <label class="col-sm-11 form-control-label text-secondary">Card
                                        number</label>
                                </div>
                                <div class="form-group row justify-content-center">
                                    <div class="col-sm-11">
                                        <input type="text" class="form-control font-weight-bold"
                                               id="accountNumber" name="accountNumber"
                                               placeholder="${cardNumber}"
                                               value="${accountNumber}" required>
                                    </div>
                                </div>
                                <div class="row justify-content-center">
                                    <label class="col-sm-11 form-control-label text-secondary">Issue
                                        date</label>
                                </div>
                                <div class="form-group row justify-content-center">
                                    <div class="col-sm-11">
                                        <input type="text" class="form-control font-weight-bold"
                                               id="cardIssueDate" name="cardIssueDate"
                                               placeholder="${labelIssueDate}"
                                               value="${cardIssueDate}" required>
                                    </div>
                                </div>
                                <div class="row justify-content-center">
                                    <label class="col-sm-11 form-control-label text-secondary">Citizen
                                        ID</label>
                                </div>
                                <div class="form-group row justify-content-center">
                                    <div class="col-sm-11">
                                        <input type="text" class="form-control font-weight-bold"
                                               id="citizenId" name="ssn" placeholder="Citizen ID"
                                               value="${ssn}" required>
                                    </div>
                                </div>
                                <div class="form-group row justify-content-center">
                                    <button type="button" class="btn btn-primary btn-sm col-sm-11"
                                            onclick="verifyRegisterPaymentOnline()"
                                            data-dismiss="modal" for=""><spring:message
                                            code="button.next"/>
                                    </button>
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

<jsp:include page="./register_online_payment_otp.jsp"></jsp:include>
<jsp:include page="./register_success.jsp"></jsp:include>
<jsp:include page="./register_online_payment_erro.jsp"></jsp:include>

<script>
  var ssn;
  $(document).ready(function () {
    $("#modalRegisterOnlinePayment").on("show.bs.modal", function (event) {
      $("#fullName").val($("#hidden_fullName").val());
      $("#accountNumber").val($("#hidden_accountNumber").val());
      $("#cardIssueDate").val($("#hidden_cardIssueDate").val());
      $("#phoneNumber").val("${userLogin.phoneNumber}");
    });

    var accountNumber = $('input[name=accountNumber]').val();
    var cardIssueDate = $("#cardIssueDate").val();
    if (("" == "${codeErr}" || null == "${codeErr}")
        && (accountNumber != null && "" != accountNumber)
        && (cardIssueDate != null && "" != cardIssueDate)) {
      verifyRegisterPaymentOnline();
    }
  });

  function verifyRegisterPaymentOnline() {
    ssn = $("#citizenId").val();

    var formData = {
      "${_csrf.parameterName}": "${_csrf.token}",
      accountNumber: $('input[name=accountNumber]').val(),
      fullName: $('input[name=fullName]').val(),
      phoneNumber: "${userLogin.phoneNumber}",
      cardIssueDate: $("#cardIssueDate").val(),
      bankCode: $("#hidden_bankCode").val(),
      ssn: ssn

    };

    $.ajax({
      type: "GET",
      contentType: "application/json;charset=utf-8",
      dataType: "json",
      url: "/ajax-controller/register-payment-online-verify",
      beforeSend: function (xhr) {
        if ("${_csrf.parameterName}" && "${_csrf.token}") {
          xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
        }
      },
      data: formData,
      timeout: 60000,
      success: function (data) {
        if (data !== null) {
          if (data.is_otp_required === true) {
            $('#modalRegisterOnlinePaymentOtp').modal('show');
          } else {
            $('#modalPaymentRegisterSuccess').modal('show');
          }

        }
      }

    });
  }
</script>