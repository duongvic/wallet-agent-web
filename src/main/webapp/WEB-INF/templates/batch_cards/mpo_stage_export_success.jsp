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

                        <form class="form-horizontal" method="" action="">

                            <div class="row mb-20">
                                <h4><spring:message code="label.export.card.success"/></h4>
                            </div>
                            <div class="row mb-20">
                                <div
                                        class="col-md-4 col-sm-6 text-center my-15 offset-lg-4 offset-md-3 offset-sm-3">
                                    <i class="icon pe-check badge-success br-100 fs40"></i>
                                    <p class="mb-0"><spring:message
                                            code="label.sent.pass.file.tag.phone.customer"/></p> </br>
                                    <br>
                                </div>
                                <div class="col-md-12 col-sm-12 text-center">
                                    <p><spring:message
                                            code="label.please.load.file.pass.access"/></p>
                                </div>
                            </div>

                            <div class="clr"></div>

                            <div class="row form-group mx-15 mb-20">
                                <div class="col-5 mb-20 text-right">
                                    <button type="button" id="link-export"
                                            class="btn btn-primary">
                                        <i class="fa fa-download"></i>&nbsp;<spring:message
                                            code="common.btn.download.file"/>
                                    </button>
                                </div>
                                <div class="col-5 mb-20">
                                    <button type="button" id="resend-pass"
                                            class="btn btn-primary">
                                        <i class="fa fa-refresh"></i>&nbsp;<spring:message
                                            code="common.btn.resend.password"/>
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
<!-- /footer -->
</body>
<script>
  $(document).ready(function () {

    $('#link-export').click(function () {
      var id = "${poMerchantId}";
      $.MessageBox({
        buttonDone: '<spring:message code="popup.button.yes"/>',
        buttonFail: '<spring:message code="popup.button.no"/>',
        message: '<spring:message code="popup.message.confirm.download.file"/>'
      }).done(function () {
        var urlEpin = '/batch-cards/export-epin?poMerchantId=' + id;
        $.fileDownload(urlEpin)
        .done(function () {
          $.MessageBox({message: '<spring:message code="common.file.download.success"/>'});
        })
        .fail(function () {
          $.MessageBox({message: '<spring:message code="common.file.download.fail"/>'});
        });
      });
      return false;
    });

    $('#resend-pass').click(function () {
      var id = "${poMerchantId}";
      if (id != null && id != '') {
        $.MessageBox({
          buttonDone: '<spring:message code="popup.button.yes"/>',
          buttonFail: '<spring:message code="popup.button.no"/>',
          message: '<spring:message code="popup.message.confirm.reset.pass"/>'
        }).done(function () {
          $.ajax({
            type: 'GET',
            url: 'resend-pass',
            data: {
              poMerchantId: id
            },
            success: function (data) {
              if (data.code == 0) {
                $.MessageBox(
                    {message: '<spring:message code="popup.message.confirm.receive.pass"/>'});
              } else {
                $.MessageBox({message: data.message});
              }
            }
          });
        });
        return false;
      } else {
        $.MessageBox({message: "<spring:message code="common.data.error"/>"});
        return false;
      }
    });
  });
</script>
</html>