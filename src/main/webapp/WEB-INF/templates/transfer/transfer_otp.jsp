<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.transfer.TransferController.TRANSFER_CONTROLLER" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">


<c:url var="txnControlUri" value="<%=TRANSFER_CONTROLLER%>"/>

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <title><spring:message code="label.fundin.link.bank"/> - <spring:message code="common.company"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"></jsp:include>
<!-- /menu bar -->

<div class="page page-email">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message code="label.moneytransfer"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="label.moneytransfer"/></h1></div>

    <div class="page-content">
        <div class="panel">
            <div class="panel-body">
                <div class="mb-20">
                    <h4 class="pull-left mt-15 ml-20"><spring:message
                            code="money.transfer.confirm"/></h4>
                    <div class="clr"></div>
                </div>
                <form name="" method="post" action="/transfer/money-confirm">
                    <div class="form-group row mb-5 offset-md-0">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="money.transfer.targetAccount"/>: </label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0"
                               id="payeePhoneNumber">${payeePhoneNumber}</p>
                            <input type="hidden" name="payeePhoneNumber"
                                   id="hidden_payeePhoneNumber"
                                   value="${payeePhoneNumber}">
                        </div>
                    </div>
                    <div class="form-group row mb-5 offset-md-0">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="money.transfer.payee"/> </label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0"
                               id="payeeAccount">${payeeFullname}</p>
                            <input type="hidden" name="payeeAccount" id="hidden_payeeAccount"
                                   value="${payeeFullname}">
                        </div>
                    </div>
                    <div class="form-group row mb-5 offset-md-0">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="money.transfer"/>: </label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0"
                               id="amount">${ewallet:formatNumber(amount)}</p>
                            <input type="hidden" name="amount" id="hidden_amount" value="${amount}">
                        </div>
                    </div>
                    <div class="form-group row mb-5 offset-md-0">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="money.transfer.fee"/>: </label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0"
                               id="feeAmount">${ewallet:formatNumber(feeAmount)} Zpoint</p>
                            <input type="hidden" name="feeAmount" id="hidden_feeAmount"
                                   value="${feeAmount}">
                        </div>
                    </div>
                    <div class="form-group row mb-5 offset-md-0">
                        <label class=" col-sm-3 col-form-label pb-0"><spring:message
                                code="label.amount"/>: </label>
                        <div class=" col-sm-9 ">
                            <p class="form-control-plaintext pb-0"
                               id="realAmount">${ewallet:formatNumber(realAmount)} Zpoint</p>
                            <input type="hidden" name="realAmount" id="hidden_realAmount"
                                   value="${realAmount}">
                        </div>
                    </div>


                    <c:if test="${'PIN' eq paymentSecurityType && paymentPinSetup eq true}">
                        <div class="col-lg-12 col-md-8 mb-5">
                            <label><spring:message code="label.pin"/>
                                <span style="color: #27ADD0"><sec:authentication
                                        property="principal.username"></sec:authentication></span>
                                <span>.</span>
                            </label>
                        </div>

                        <div class="col-md-12 mb-5 offset-md-0">
                            <div class="input-group">
                                <input type="password" id="codeOTP" name="codeOTP" class="form-control"
                                       placeholder="<spring:message code="label.enter.code"/>"
                                       value="${codeOTP}"
                                       required>
                                <span class="input-group-btn hidden">
                                                <button type="button"
                                                        class="btn btn-default btn-outline">
                                                    <i class="fa fa-rotate-left"></i> <spring:message
                                                        code="common.btn.resend"/></button>
                                              </span>
                            </div>
                        </div>
                        <c:if test="${(codeErr != null && fn:length(codeErr) gt 0)}">
                            <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                                <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                            </div>
                        </c:if>
                    </c:if>

                    <c:if test="${'OTP' eq paymentSecurityType}">
                        <div class="col-lg-12 col-md-8 mb-5">
                            <label><spring:message code="label.otp"/>
                                <span style="color: #27ADD0"><sec:authentication
                                        property="principal.username"></sec:authentication></span>
                                <span></span>
                            </label>
                        </div>

                        <div class="col-md-12 mb-5 offset-md-0">
                            <div class="input-group">
                                <input type="password" id="codeOTP" name="codeOTP" class="form-control"
                                       placeholder="<spring:message code="label.enter.otp"/>"
                                       value="${codeOTP}"
                                       required>
                                <%--<span class="input-group-btn">--%>
                                                <%--<button type="button" id="resend-otp"--%>
                                                        <%--class="btn btn-default btn-outline">--%>
                                                    <%--<i class="fa fa-rotate-left"></i> <spring:message--%>
                                                        <%--code="common.btn.resend"/></button>--%>
                                              <%--</span>--%>
                            </div>
                        </div>

                        <div class="clr"></div>
                        <div class="row mb-20">
                            <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                                <div id="countdown" class="countdown pull-right">
                                    <spring:message
                                            code="label.limited.otp.time"/>&nbsp;<span
                                        id="clock"
                                        class="text-danger"></span>
                                </div>
                                <small id="resend" class="resend hidden pull-right">
                                    <spring:message code="label.could.not.get.code"/>
                                    <a href="#" id="resendOTP"><spring:message
                                            code="label.resend"/></a>
                                </small>
                            </div>
                        </div>
                        <c:if test="${(codeErr != null && fn:length(codeErr) gt 0)}">
                            <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                                <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                            </div>
                        </c:if>
                    </c:if>
                    <%--duongdp 12/02.2019--%>
                    <c:if test="${'PAYMENT_PASSWORD' eq paymentSecurityType}">
                        <div class="col-lg-12 col-md-8 mb-5">
                            <label><spring:message code="label.payment.password"/>
                                <%--<span style="color: #27ADD0"><sec:authentication--%>
                                        <%--property="principal.username"></sec:authentication></span>--%>
                                <%--<span>.</span>--%>
                            </label>
                        </div>

                        <div class="col-md-12 mb-5 offset-md-0">
                            <div class="input-group">
                                <input type="password" id="codeOTP" name="codeOTP" class="form-control"
                                       placeholder="<spring:message code="label.enter.code"/>"
                                       value="${codeOTP}"
                                       required>
                                <%--<span class="input-group-btn hidden">--%>
                                                <%--<button type="button"--%>
                                                        <%--class="btn btn-default btn-outline">--%>
                                                    <%--<i class="fa fa-rotate-left"></i> <spring:message--%>
                                                        <%--code="common.btn.resend"/></button>--%>
                                              <%--</span>--%>
                            </div>
                        </div>
                        <c:if test="${(codeErr != null && fn:length(codeErr) gt 0)}">
                            <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                                <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                            </div>
                        </c:if>
                    </c:if>
                    <%--duongdp 12/02.2019--%>
                    <div class="col-md-12 mb-5 offset-md-0 text-right">
                        <a href="/transfer/money" class="btn btn-default mr-10"><spring:message
                                code="common.btn.back"/></a>
                        <button type="submit" class="btn btn-primary"><spring:message
                                code="common.btn.confirm"/></button>
                    </div>
                    <input type="hidden" id="hidden_orderId" name="orderId" value="${orderId}"/>
                    <input type="hidden" name="codeErr" value="${codeErr}"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<c:import url="../include_page/resent_otp_lib.jsp"/>
<!-- /footer -->
</body>
<script>
  $('#resendOTP').click(function () {
    var id = $('#hidden_orderId').val();
    if (id != null && id != '') {
      <%--$.MessageBox({--%>
        <%--buttonDone: '<spring:message code="popup.button.yes"/>',--%>
        <%--buttonFail: '<spring:message code="popup.button.no"/>',--%>
        <%--message: '<spring:message code="popup.message.confirm.reset.otp"/>'--%>
      <%--}).done(function () {--%>
        $.ajax({
          type: 'GET',
          url: 'resend-otp',
          data: {
            orderId: id
          },
          success: function (data) {
            if (data.code == 0) {
              $('#countdown').show();
              $('#clock').countdown(get3MinutesFromNow());
              $('#resend').attr("style", "display: none !important");
              <%--$.MessageBox(--%>
                  <%--{message: '<spring:message code="popup.message.confirm.resend.otp"/>'});--%>
            } else {
              $.MessageBox({message: data.message});
            }
          },
          error: function (data) {
            $.MessageBox({message: data.message});
          }
        });
//      });
//      return false;
    } else {
      $.MessageBox({message: "<spring:message code="common.data.error"/>"});
      return false;
    }
  });
</script>
</html>