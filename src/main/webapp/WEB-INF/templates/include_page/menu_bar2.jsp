<%@ page import="vn.mog.ewallet.consumer.web.contract.UserLogin" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_IS_MOBILE" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_CUSTOMER" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_MERCHANT" %>
<%@ page import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_AGENT" %>

<%@ page import="static vn.mog.ewallet.consumer.web.common.SharedConstants.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

<%
  String menu = (String) request.getSession().getAttribute("menu");
  UserLogin userLogin = (UserLogin) request.getSession().getAttribute(SESSION_ACCOUNT_LOGIN);
  if (userLogin == null) {
    userLogin = new UserLogin();
  }
%>
<c:set var="userLogin" value="<%=userLogin%>" scope="application"/>
<c:set var="isMobile" value="<%=(Long) request.getSession().getAttribute(SESSION_IS_MOBILE)%>"
       scope="application"/>


<%if (menu != null) {%>
<c:set var="menu" value="<%=menu%>" scope="page"/>
<%} else {%>
<c:set var="menu" value="${menu}" scope="page"/>
<%}%>

<c:set var="MENU_ITEM_SHOW_FUNDIN" value="<%=MENU_ITEM_SHOW_FUNDIN%>"/>
<c:set var="MENU_ITEM_SHOW_FUNDOUT" value="<%=MENU_ITEM_SHOW_FUNDOUT%>"/>
<c:set var="MENU_ITEM_SHOW_ACCOUNT_INFO" value="<%=MENU_ITEM_SHOW_ACCOUNT_INFO%>"/>
<c:set var="MENU_ITEM_SHOW_CARD_MANAGEMENT" value="<%=MENU_ITEM_SHOW_CARD_MANAGEMENT%>"/>
<c:set var="MENU_ITEM_SHOW_MONEY_TRANSFER" value="<%=MENU_ITEM_SHOW_MONEY_TRANSFER%>"/>
<c:set var="MENU_ITEM_SHOW_PHONE_EPIN" value="<%=MENU_ITEM_SHOW_PHONE_EPIN%>"/>
<c:set var="MENU_ITEM_SHOW_GAME_EPIN" value="<%=MENU_ITEM_SHOW_GAME_EPIN%>"/>
<c:set var="MENU_ITEM_SHOW_PHONE_BATCH_CARD" value="<%=MENU_ITEM_SHOW_PHONE_BATCH_CARD%>"/>
<c:set var="MENU_ITEM_SHOW_PHONE_TOPUP" value="<%=MENU_ITEM_SHOW_PHONE_TOPUP%>"/>
<c:set var="MENU_ITEM_SHOW_SETTING_SECURITY" value="<%=MENU_ITEM_SHOW_SETTING_SECURITY%>"/>
<c:set var="MENU_ITEM_SHOW_TRANSACTION_HISTORY" value="<%=MENU_ITEM_SHOW_TRANSACTION_HISTORY%>"/>
<c:set var="MENU_ITEM_SHOW_BILL_PAYMENT" value="<%=MENU_ITEM_SHOW_BILL_PAYMENT%>"/>
<c:set var="MENU_ITEM_SHOW_FINANCIAL_SERVICES" value="<%=MENU_ITEM_SHOW_FINANCIAL_SERVICES%>"/>

<c:set var="ID_CUSTOMER" value="<%=ID_CUSTOMER%>"/>
<c:set var="ID_MERCHANT" value="<%=ID_MERCHANT%>"/>
<c:set var="ID_AGENT" value="<%=ID_AGENT%>"/>
<link rel="stylesheet" href="<c:url value="/assets/development/css/site.min.css"/>">

<%--dialog--%>
<c:import url="../dialog_modal/payment_security/dialog_paymentSecurity.jsp"/>
<%--end dialog--%>

<div class="site-menubar">
  <div class="site-menubar-body">
    <div>
      <div>
        <ul class="site-menu" data-plugin="menu">
          <div class="ove"></div>
          <li class="site-menu-item has-sub open sd" style="height: 160px;">
            <a href="javascript:void(0)">
              <i class="site-menu-icon fa fa-money" aria-hidden="true"></i>
              <span class="site-menu-title"><spring:message code="menu.balance"/></span>
            </a>
            <ul class="site-menu-sub lh18">
              <div class="text-white fs24 pl-25 pt-5">${ewallet:formatNumber(userLogin.balance)}
                <span>đ</span></div>
              <li class="site-menu-item wallet-menu px-20 pt-5">
                <table>
                  <tbody>
                  <tr>
                    <td onclick="formSubmit('form_fundin_submit')">
                      <a class="fold-show" data-placement="bottom"
                         data-toggle="tooltip"
                         data-original-title="<spring:message code="menu.fundin"/>">
                        <span class="icon pe-plus" aria-hidden="true"></span>
                      </a>
                      <label onclick="formSubmit('form_fundin_submit')"><spring:message
                          code="menu.fundin"/></label>
                    </td>
                    <%--AGENT--%>
                    <td onclick="formSubmit('form_fundout_submit')" class="hidden">
                      <a data-placement="bottom" data-toggle="tooltip"
                         data-original-title="<spring:message code="menu.fundout"/>">
                        <span class="icon pe-less" aria-hidden="true"></span>
                      </a>
                      <label onclick="formSubmit('form_fundout_submit')"><spring:message
                          code="menu.fundout"/></label>
                    </td>
                    <%--end AGENT--%>
                    <c:if test="${ID_CUSTOMER == userLogin.customerTypeId}">
                      <td onclick="formSubmit('form_transfer_money_submit')">
                        <a data-placement="bottom" data-toggle="tooltip"
                           data-original-title="<spring:message code="menu.moneyTranfer"/>">
                          <span class="icon pe-repeat" aria-hidden="true"></span>
                        </a>
                        <label onclick="formSubmit('form_transfer_money_submit')"><spring:message
                            code="menu.moneyTranfer"/></label>
                      </td>
                    </c:if>
                  </tr>
                  </tbody>
                </table>
                <div class="clr"></div>
              </li>
            </ul>
          </li>
          </br>

          <%--Nạp tiền điện thoại--%>
          <c:if test="${'true' eq MENU_ITEM_SHOW_PHONE_TOPUP}">
            <c:set var="cssTopUp" value="${param.nav eq 'topUpMenu' ? 'active selected' : ''}"/>
            <li class="site-menu-item ${cssTopUp}">
              <form method="post" action="/topup" class="hidden">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button class="hidden" id="form_top_up_submit"></button>
              </form>
              <a onclick="formSubmit('form_top_up_submit')">
                <i class="site-menu-icon wb-mobile" aria-hidden="true"></i>
                <span class="site-menu-title"><spring:message code="menu.topUp"/></span>
              </a>
            </li>
          </c:if>
          <%--end--%>

          <%--Mã thẻ điện thoại--%>
          <c:if test="${'true' eq MENU_ITEM_SHOW_PHONE_EPIN}">
            <c:set var="cssPinCode" value="${param.nav eq 'pinCodeMenu' ? 'active selected' : ''}"/>
            <li class="site-menu-item ${cssPinCode}">
              <form method="post" action="/pin-code/order-info" class="hidden">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button class="hidden" id="form_pin_code_submit"></button>
              </form>
              <a onclick="formSubmit('form_pin_code_submit')">
                <i class="site-menu-icon wb-mobile" aria-hidden="true"></i>
                <span class="site-menu-title"><spring:message code="menu.epin"/></span>
              </a>
            </li>
          </c:if>
          <%--end--%>

          <%--Mã thẻ game--%>
          <c:if test="${'true' eq MENU_ITEM_SHOW_GAME_EPIN}">
            <c:set var="cssCodeCardGame" value="${param.nav eq 'codeCardGame' ? 'active selected' : ''}"/>
            <li class="site-menu-item ${cssCodeCardGame}">
              <form method="post" action="/game-code/buy-game-card" class="hidden">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button class="hidden" id="form_game_card_code_submit"></button>
              </form>
              <a onclick="formSubmit('form_game_card_code_submit')">
                <i class="site-menu-icon wb-mobile" aria-hidden="true"></i>
                <span class="site-menu-title"><spring:message code="menu.game.code"/></span>
              </a>
            </li>
          </c:if>
          <%--end--%>

          <%--Mua thẻ theo lô--%>
          <c:if test="${ID_MERCHANT == userLogin.customerTypeId}">
            <c:if test="${'true' eq MENU_ITEM_SHOW_PHONE_BATCH_CARD}">
              <c:set var="cssBatchCards"
                     value="${param.nav eq 'batchCardsMenu' ? 'active selected' : ''}"/>
              <li class="site-menu-item ${cssBatchCards}">
                <form method="post" action="/batch-cards/list" class="hidden">
                  <input type="hidden" name="${_csrf.parameterName}"
                         value="${_csrf.token}"/>
                  <button class="hidden" id="form_batchCards_submit"></button>
                </form>
                <a onclick="formSubmit('form_batchCards_submit')">
                  <i class="site-menu-icon wb-mobile" aria-hidden="true"></i>
                  <span class="site-menu-title"><spring:message code="menu.batch.cards"/></span>
                </a>
              </li>
            </c:if>
          </c:if>
          <%--end--%>

          <li class="site-menu-category"></li>

          <c:if test="${ID_AGENT == userLogin.customerTypeId}">
            <c:if test="${'true' eq MENU_ITEM_SHOW_BILL_PAYMENT}">
              <%--Hóa đơn--%>
              <c:set var="cssHoaDon" value="${param.nav eq 'hd_Electricity' || param.nav eq 'hd_Water'  ? 'open active' : ''}"/>
              <li class="site-menu-item has-sub ${cssHoaDon}">
                <a href="javascript:void(0)">
                  <i class="site-menu-icon wb-lock" aria-hidden="true"></i>
                  <span class="site-menu-title">Hóa đơn</span>
                  <span class="site-menu-arrow"></span>
                </a>
                <ul class="site-menu-sub">
                  <li class="site-menu-item ${param.nav eq 'hd_Electricity' ? 'active selected' : ''}">
                    <a class="animsition-link" href="${pageContext.request.contextPath}/bill-payment/electric/management"><span class="site-menu-title">Điện</span></a>
                  </li>
                    <%--<li class="site-menu-item ${param.nav eq 'hd_Water' ? 'active selected' : ''}">--%>
                    <%--<a class="animsition-link" href="${pageContext.request.contextPath}/bill-payment/water/management"><span class="site-menu-title">Nước</span></a>--%>
                    <%--</li>--%>
                </ul>
              </li>
              <%--end hóa đơn--%>
            </c:if>
            <%--Dịch vụ--%>
            <c:if test="${'true' eq MENU_ITEM_SHOW_FINANCIAL_SERVICES}">
              <c:set var="cssDichVu" value="${param.nav eq 'dv_FEcredit' || param.nav eq 'dv_Homecredit'  ? 'open active' : ''}"/>
              <li class="site-menu-item has-sub ${cssDichVu} ">
                <a href="javascript:void(0)">
                  <i class="site-menu-icon wb-lock" aria-hidden="true"></i>
                  <span class="site-menu-title">Dịch vụ tài chính</span>
                  <span class="site-menu-arrow"></span>
                </a>
                <ul class="site-menu-sub">
                  <li class="site-menu-item ${param.nav eq 'dv_FEcredit' ? 'active selected' : ''}">
                    <a class="animsition-link" href="${pageContext.request.contextPath}/financial-services/FEcredit/management"><span class="site-menu-title">FE credit</span></a>
                  </li>
                    <%--<li class="site-menu-item ${param.nav eq 'dv_Homecredit' ? 'active selected' : ''}">--%>
                    <%--<a class="animsition-link" href="${pageContext.request.contextPath}/financial-services/Homecredit/management"><span class="site-menu-title">Home credit</span></a>--%>
                    <%--</li>--%>
                </ul>
              </li>
            </c:if>
            <%--end Dịch vụ--%>
          </c:if>

          <li class="site-menu-category"></li>
          <%--Account veirfication info--%>
          <li class="site-menu-item ${param.nav =='infoAccountVeirfication'? 'active selected' : ''}">
            <%--<form method="post" action="${pageContext.request.contextPath}/account-veirfication/info" class="hidden">--%>
            <%--<input type="hidden" name="${_csrf.parameterName}"--%>
            <%--value="${_csrf.token}"/>--%>
            <%--<button class="hidden" id="form_account_Verification_info_submit"></button>--%>
            <%--</form>--%>
            <%--<a onclick="formSubmit('form_account_Verification_info_submit')">--%>
            <%--<i class="site-menu-icon fa fa-address-card" aria-hidden="true"></i>--%>
            <%--<span class="site-menu-title">Account</span>--%>
            <%--<div class="site-menu-badge"></div>--%>
            <%--</a>--%>
            <a href="${pageContext.request.contextPath}/account/info">
              <i class="site-menu-icon fa fa-address-card" aria-hidden="true"></i>
              <span class="site-menu-title"><spring:message code="menu.account.info"/></span>
              <div class="site-menu-badge"></div>
            </a>
          </li>
          <%--end Account veirfication Info--%>

          <%--Account info--%>
          <%--<c:if test="${'true' eq MENU_ITEM_SHOW_ACCOUNT_INFO}">--%>
          <%--<li class="site-menu-item ${param.nav =='infoCus'? 'active selected' : ''}">--%>
          <%--<form method="post" action="${pageContext.request.contextPath}/customer/account-info" class="hidden">--%>
          <%--<input type="hidden" name="${_csrf.parameterName}"--%>
          <%--value="${_csrf.token}"/>--%>
          <%--<button class="hidden" id="form_account_info_submit"></button>--%>
          <%--</form>--%>
          <%--<a onclick="formSubmit('form_account_info_submit')">--%>
          <%--<i class="site-menu-icon fa fa-address-card" aria-hidden="true"></i>--%>
          <%--<span class="site-menu-title"><spring:message code="menu.account.info"/></span>--%>
          <%--<div class="site-menu-badge"></div>--%>
          <%--</a>--%>
          <%--</li>--%>
          <%--</c:if>--%>
          <%--end Account Info--%>

          <%--Card--%>
          <c:if test="${'true' eq MENU_ITEM_SHOW_CARD_MANAGEMENT}">
            <c:set var="cssCardMa" value="${param.nav eq 'themtaikhoan' ||
                                            param.nav eq 'themthe' ? 'active selected' : ''}"/>
            <li class="site-menu-item ${cssCardMa}">
                <%--<form method="post" action="/bank/manage" class="hidden">--%>
                <%--<input type="hidden" name="${_csrf.parameterName}"--%>
                <%--value="${_csrf.token}"/>--%>
                <%--<button class="hidden" id="form_card_manager_submit"></button>--%>
                <%--</form>--%>
                <%--<a onclick="formSubmit('form_card_manager_submit')">--%>
                <%--<i class="site-menu-icon fa-credit-card" aria-hidden="true"></i>--%>
                <%--<span class="site-menu-title"><spring:message code="menu.card.management"/></span>--%>
                <%--</a>--%>
              <a href="/bank/manage">
                <i class="site-menu-icon fa-credit-card" aria-hidden="true"></i>
                <span class="site-menu-title"><spring:message code="menu.card.management"/></span>
              </a>
            </li>
          </c:if>
          <%--end Card--%>

          <c:if test="${'true' eq MENU_ITEM_SHOW_SETTING_SECURITY}">
            <c:set var="cssSec"
                   value="${param.nav eq 'paySec'? 'active selected' : ''}"/>
            <li class="site-menu-item ${cssSec}">
              <form method="post" action="/system/payment-security" class="hidden">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button class="hidden" id="form_payment_security_submit"></button>
              </form>
              <a href="javascript:void(0)" onclick="formSubmit('form_payment_security_submit')">
                <i class="site-menu-icon wb-settings" aria-hidden="true"></i>
                <span class="site-menu-title"><spring:message code="menu.setting.secutiry"/></span>
                  <%--<span class="site-menu-arrow"></span>--%>
              </a>

            </li>
          </c:if>
          <%--end payment security--%>

          <c:if test="${'true' eq MENU_ITEM_SHOW_TRANSACTION_HISTORY}">
            <c:set var="cssHis"
                   value="${param.nav eq 'historyTrans'? 'active selected' : ''}"/>
            <li class="site-menu-item ${cssHis}">
              <a href="/trans-log/transaction-history">
                <i class="site-menu-icon wb-time" aria-hidden="true"></i>
                <span class="site-menu-title"><spring:message
                    code="menu.transaction.history"/></span></a>
            </li>
            <li class="site-menu-category"></li>
            <li class="site-menu-item hidden">
              <a href="#">
                <i class="site-menu-icon wb-library" aria-hidden="true"></i>
                <span class="site-menu-title"><spring:message code="menu.news"/></span> </a>
            </li>
            <li class="site-menu-item hidden">
              <a href="/troGiup"> <i class="site-menu-icon wb-help-circle" aria-hidden="true"></i>
                <span class="site-menu-title"><spring:message code="menu.helpdesk"/></span> </a>
            </li>
          </c:if>
        </ul>
      </div>
    </div>
  </div>
  <%--<div class="site-menubar-footer">--%>
  <%--<a href="#" class="fold-show" data-placement="top" data-toggle="tooltip"--%>
  <%--data-original-title="Facebook"><span class="fa fa-facebook-square" aria-hidden="true"></span></a>--%>
  <%--<a href="#" data-placement="top" data-toggle="tooltip" data-original-title="Flickr"><span--%>
  <%--class="fa fa-flickr" aria-hidden="true"></span></a>--%>
  <%--<a href="#" data-placement="top" data-toggle="tooltip" data-original-title="Google+"><span--%>
  <%--class="fa fa-google-plus-square" aria-hidden="true"></span></a>--%>
  <%--<a href="#" data-placement="top" data-toggle="tooltip" data-original-title="Youtube"><span--%>
  <%--class="fa fa-youtube-square" aria-hidden="true"></span></a>--%>
  <%--<a href="#" data-placement="top" data-toggle="tooltip" data-original-title="Linked in"><span--%>
  <%--class="fa fa-linkedin-square" aria-hidden="true"></span></a>--%>
  <%--<a href="callto:19001688" class="fold-show" data-placement="top" data-toggle="tooltip"--%>
  <%--data-original-title="Phone Number"><span class="icon pe-call" aria-hidden="true"></span>1900 1688</a>--%>

  <%--</div>--%>
  <div class="p-30">
    <a href="#"><i class="site-menu-icon icon pe-call"></i><spring:message code="common.hotline"/>:<span>1900 1688</span> </a>
  </div>


  <div class="hidden">
    <form method="post" action="/fundin" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="form_fundin_submit"></button>
    </form>
    <form method="post" action="/fundout" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="form_fundout_submit"></button>
    </form>
    <form method="post" action="/transfer/money" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="form_transfer_money_submit"></button>
    </form>
  </div>

</div>
<script>
  function openDialogSecure(param) {
    var modalForm = $("#login-form");
    var loginButton = $("#check-login-button");
    if ("paySec" === param) {
      modalForm.attr("action", "/system/payment-security");
      loginButton.attr("onclick", "login()");
    } else {
      modalForm.attr("action", "/system/payment-security/change-payment-pin");
      loginButton.attr("onclick", "getOTPForSecure()");
    }
    $("#modalPaymentSecurity").modal('show');
  }

  function formSubmit(submitId) {
    $("#".concat(submitId)).trigger('click');
  }
</script>