
<%@ page import="vn.mog.ewallet.consumer.web.controller.account.AccountVeirficationController" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="${pageContext.response.locale.language}">

<head>
    <title>Dashboard - <spring:message code="common.company"/></title>
    <!-- head libs css -->
    <c:import url="../include_page/head.jsp"/>
    <!-- /head libs css  -->
</head>

<body class="animsition">

<!-- notification -->
<c:import url="../include_page/notification.jsp"/>
<!-- /notification -->

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<div class="page">
    <!-- user name -->
    <jsp:include page="../include_page/user_name.jsp"/>
    <!-- /user name -->
    <div class="page-content container-fluid">
        <div class="row">

            <!-- Nạp tiền, Rút tiền  -->
            <jsp:include page="content.jsp"/>
            <!-- /Nạp tiền, Rút tiền -->

            <div class="col-xxl-3 col-lg-3 col-md-4 col-sm-12">

                <!-- News  -->
                <%--<c:import url="../include_component/frame_news.jsp"/>--%>
                <!-- /News  -->

                <div class="row">

                    <!-- Giao dịch gần đây  -->
                    <%--<c:import url="../include_component/frame_transaction.jsp"/>--%>
                    <!-- /Giao dịch gần đây  -->

                    <!-- Giao dịch gần đây  -->
                    <jsp:include page="../include_component/service_code_constants.jsp"/>
                    <c:import url="../include_component/frame_transaction_new.jsp"/>
                    <!-- /Giao dịch gần đây  -->


                    <!-- Bảo mật  -->
                    <%--<c:import url="../include_component/frame_config_policy.jsp"/>--%>
                    <!-- /Bảo mật  -->

                    <!-- Quản lý thẻ/ Tài khoảnQuản lý thẻ/ Tài khoản  -->
                    <%--<c:import url="../include_component/frame_car_account.jsp"/>--%>
                    <!-- /Quản lý thẻ/ Tài khoản  -->

                </div>
            </div>
        </div>
    </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->

<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
      <c:if test="${isPMSaccount == false}">
      var outletStoreType = '${storeInfo.textOutLetStoreType()}';
      var street1Store = '${storeInfo.street1}';
      var aliasStore = '${storeInfo.alias}';
      var businessPhoneStore = '${storeInfo.businessPhone}';
      if (outletStoreType == null || outletStoreType == "undefined" || outletStoreType == "" ||
          street1Store == null || street1Store == "undefined" || street1Store == "" ||
          aliasStore == null || aliasStore == "undefined" || aliasStore == "" ||
          businessPhoneStore == null || businessPhoneStore == "undefined" || businessPhoneStore == "") {
        $.MessageBox({
          buttonDone: "Yes",
          message: "Quý khách cần cập nhật địa chỉ kinh doanh để khách hàng có thể tìm được cửa hàng và gia tăng doanh số !!!"
        }).done(function () {
          window.location.href = '<%=AccountVeirficationController.REDIRECT_ACCOUNT_STORE_ADDRESS_NFO%>';
        })
      }
      </c:if>
    });
  })(document, window, jQuery);
</script>

</body>

</html>