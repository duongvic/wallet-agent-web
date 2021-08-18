<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
  %>
  <title><spring:message code="label.game.code"/> - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">

<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
  <jsp:param name="nav" value="codeCardGame"/>
</jsp:include>
<!-- /menu bar -->


<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><spring:message code="label.game.code.service"/></li>
      <li class="breadcrumb-item"><spring:message code="label.game.code"/></li>
    </ol>
    <h3 class="page-title"><spring:message code="label.game.code"/></h3>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">

            <form id="confirm-form-game" class="form-horizontal" method="post"
                  action="/game-code/pin-pay-confirm">
              <div class="form-group row mb-10">
                <div class="full-width">
                  <h4 class="panel-title pl-0"><spring:message
                      code="label.payment"/></h4>
                  <div class="clr"></div>
                </div>
              </div>

              <div class="row col mb-20">
                <div class="">
                  <img src="/assets/images/${tenSP}.png" width="100"
                       class="br3 pull-left">
                </div>
                <%--<div class="col-8 col-sm-7 mb-3">--%>
                  <%--<div class="col-10 col-sm-7 mb-3">--%>
                                        <%--<span class="h4"><spring:message--%>
                                            <%--code="label.ewallet"/></span> <br> <span--%>
                      <%--class="small txt-note-pin"><spring:message--%>
                      <%--code="label.payment.free"/></span>--%>
                    <%--<div class="clr"></div>--%>
                  <%--</div>--%>
                <%--</div>--%>
              </div>

              <c:choose>
                <c:when test="${'OTP' eq paymentSecurityType || 'PAYMENT_PIN' eq paymentSecurityType}">
                  <div class="row">
                    <c:if test="${'OTP' eq paymentSecurityType}">
                      <div class="col-lg-12 col-md-8 mb-3">
                        <label><spring:message code="label.otp"/> <span
                            style="color: #27ADD0">${(userLogin.phoneNumber)}</span>
                        </label>
                      </div>
                    </c:if>

                    <c:if test="${'PAYMENT_PIN' eq paymentSecurityType}">
                      <div class="col-lg-12 col-md-8 mb-3">
                        <label>
                          <c:choose>
                            <c:when test="${AGENT_TYPE eq userLoginType}">
                              <spring:message code="label.payment.password"/>
                            </c:when>
                            <c:otherwise>
                              <spring:message code="label.pin"/>
                            </c:otherwise>
                          </c:choose>
                        </label>
                      </div>
                    </c:if>

                    <c:if test="${(codeErr != null)}">
                      <div class="text-danger error-message">
                        <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}
                        </small>
                      </div>
                    </c:if>
                    <%--<jsp:include page="../include_component/input_pin.jsp"/>--%>
                    <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                      <div class="input-group">
                        <input type="password" id="paymentSecurityCode"
                               name="paymentSecurityCode"
                               class="form-control" required
                               placeholder="<spring:message code="label.enter.password"/>">
                      </div>
                    </div>
                  </div>
                </c:when>

                <c:when test="${'PAYMENT_PASSWORD' eq paymentSecurityType}">
                  <div class="row">
                    <div class="col-lg-12 col-md-8 mb-3">
                      <label><spring:message code="label.payment.password"/>
                      </label>
                    </div>
                    <c:if test="${(codeErr != null)}">
                      <div class="text-danger error-message">
                        <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}
                        </small>
                      </div>
                    </c:if>
                    <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                      <div class="input-group">
                        <input type="password" id="paymentSecurityCode"
                               name="paymentSecurityCode"
                               class="form-control" required
                               placeholder="<spring:message code="label.enter.password"/>">
                      </div>
                    </div>
                  </div>
                </c:when>

                <c:when test="${'NONE' eq paymentSecurityType}">
                  <div class="row">
                    <div class="col-lg-12 col-md-8 mb-3">
                      <label><spring:message code="label.no.pin"/>
                      </label>
                    </div>
                  </div>
                </c:when>
              </c:choose>

              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit"
                          class="btn btn-primary btn-sm pull-right">
                    <spring:message code="common.btn.confirm"/></button>
                  <a href="/game-code/buy-game-card"
                     class="btn btn-default btn-sm pull-right mr-10">
                    <spring:message code="common.btn.back"/></a>
                </div>
              </div>

              <input type="hidden" id="disCountPercent" name="disCountPercent"
                     value="${disCountPercent}">
              <input type="hidden" id="hidden_orderId" name="${orderId}"
                     value="${orderId}"/>
              <input type="hidden" name="${_csrf.parameterName}"
                     value="${_csrf.token}"/>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import
            url="../include_component/frame_information_transaction_pincode_gamecard.jsp"/>
        <!-- /Giao dịch gần nhất  -->
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
          } else {
            $.MessageBox({message: data.message});
          }
        },
        error: function () {
          $.MessageBox({message: "<spring:message code="common.data.error"/>"});
        }
      });
    } else {
      $.MessageBox({message: "<spring:message code="common.data.error"/>"});
      return false;
    }
  });
</script>
</html>