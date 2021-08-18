<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <title><spring:message code="label.epin"/> - <spring:message code="common.company"/></title>
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
    <jsp:param name="nav" value="batchCardsMenu"/>
</jsp:include>
<!-- /menu bar -->


<div class="page page-email">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="label.manage"/></li>
            <li class="breadcrumb-item"><spring:message
                    code="menu.batch.cards"/></li>
        </ol>
        <h3 class="page-title"><spring:message code="menu.batch.cards"/></h3>
    </div>
    <div class="page-content container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-bordered">
                    <div class="panel-body py-10">

                        <form class="form-horizontal" method="post"
                              action="/batch-cards/confirm-otp">

                            <div class="row mb-20">
                                <h4><spring:message code="lable.enter.otp.approve"/></h4>
                            </div>

                            <c:if test="${codeErro != null}">
                                <div class="col-md-12 mb-5 offset-md-0 text-danger error-message">
                                    <small><i class="fa fa-times-circle"></i>&nbsp;${codeErro}
                                    </small>
                                </div>
                            </c:if>
                            <div class="row mb-20">
                                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                                    <div class="input-group">
                                        <input type="text" class="form-control"
                                               placeholder="<spring:message code="label.enter.otp"/>"
                                               id="codeOTP"
                                               name="codeOTP" value="${codeOTP}" required>
                                        <%--<span class="input-group-btn">--%>
                                        <%--<button type="button"--%>
                                        <%--class="btn btn-default btn-outline">--%>
                                        <%--<i class="fa fa-rotate-left"></i> <spring:message--%>
                                        <%--code="common.btn.resend"/></button>--%>
                                        <%--</span>--%>
                                    </div>
                                </div>
                            </div>
                            <div class="clr"></div>
                            <div class="row mb-20">
                                <div class="col-lg-12 col-md-8 mb-3 offset-md-0">
                                    <div id="countdown" class="countdown pull-right"><spring:message
                                            code="label.limited.otp.time"/>&nbsp;<span id="clock"
                                                                                       class="text-danger"></span>
                                    </div>
                                    <small id="resendAjax" class="resend hidden pull-right">
                                        <spring:message code="label.could.not.get.code"/>
                                        <a href="#" id="resendOTP"><spring:message
                                                code="label.resend"/></a>
                                    </small>
                                </div>
                            </div>

                            <div class="clr"></div>

                            <div class="row form-group text-right">
                                <div class="col">
                                    <button type="button" id="btBack"
                                            class="btn btn-default btn-sm"><spring:message
                                            code="common.btn.back"/>
                                    </button>
                                    <button type="submit" class="btn btn-primary btn-sm">
                                        <spring:message
                                                code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i>
                                    </button>
                                </div>

                            </div>
                            <input type="hidden" name="poMerchantId" value="${poMerchantId}"/>
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<jsp:include page="../../templates/include_page/resent_otp_lib.jsp"/>
<!-- /footer -->
</body>
<script>
  $('#btBack').click(function () {
    window.location.href = '/batch-cards/list';
  });

  $('#resendOTP').click(function () {
    var id = "${poMerchantId}";
    if (id != null && id != '') {
      $.ajax({
        type: 'GET',
        url: '/ajax-controller/batch-cards/resend-otp/${poMerchantId}',
        success: function (data) {
          if (data.code == 0) {
            $('#countdown').show();
            $('#clock').countdown(get3MinutesFromNow());
            $('#resendAjax').attr("style", "display: none !important");
          } else {
            $.MessageBox({message: data.message});
          }
        },
        error: function (data) {
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