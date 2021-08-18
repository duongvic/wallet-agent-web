<%@ page import="vn.mog.ewallet.consumer.web.contract.UserLogin" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_IS_MOBILE" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_CUSTOMER" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_MERCHANT" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_AGENT" %>

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
<c:set var="MENU_ITEM_SHOW_PHONE_BATCH_CARD_API" value="<%=MENU_ITEM_SHOW_PHONE_BATCH_CARD_API%>"/>
<c:set var="MENU_ITEM_SHOW_PHONE_TOPUP" value="<%=MENU_ITEM_SHOW_PHONE_TOPUP%>"/>
<c:set var="MENU_ITEM_SHOW_SETTING_SECURITY" value="<%=MENU_ITEM_SHOW_SETTING_SECURITY%>"/>
<c:set var="MENU_ITEM_SHOW_TRANSACTION_HISTORY" value="<%=MENU_ITEM_SHOW_TRANSACTION_HISTORY%>"/>
<c:set var="MENU_ITEM_SHOW_BILL_PAYMENT" value="<%=MENU_ITEM_SHOW_BILL_PAYMENT%>"/>
<c:set var="MENU_ITEM_SHOW_FINANCIAL_SERVICES" value="<%=MENU_ITEM_SHOW_FINANCIAL_SERVICES%>"/>
<c:set var="MENU_ITEM_SHOW_CARD_DASHBOARD" value="<%=MENU_ITEM_SHOW_CARD_DASHBOARD%>"/>
<c:set var="MENU_ITEM_SHOW_CASHIN_VIETTEL_PAY" value="<%=MENU_ITEM_SHOW_CASHIN_VIETTEL_PAY%>"/>
<c:set var="MENU_ITEM_SHOW_CASHOUT_VIETTEL_PAY" value="<%=MENU_ITEM_SHOW_CASHOUT_VIETTEL_PAY%>"/>
<c:set var="MENU_ITEM_SHOW_POINTS_TRANSFER" value="<%=MENU_ITEM_SHOW_POINTS_TRANSFER%>"/>

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
              <span class="site-menu-title"><spring:message
                  code="menu.balance"/></span>
            </a>
            <ul class="site-menu-sub lh18">
              <div class="text-white fs24 pl-25 pt-5">${ewallet:formatNumber(userLogin.balance)}
                <span>Zpoint</span></div>
              <li class="site-menu-item wallet-menu px-20 pt-5">
                <table>
                  <tbody>
                  <tr>
                    <td onclick="formSubmit('form_fundin_submit')">
                      <a class="fold-show" data-placement="bottom"
                         data-toggle="tooltip"
                         data-original-title="<spring:message code="menu.fundin"/>">
                                                <span class="icon pe-plus"
                                                      aria-hidden="true"
                                                      style="color: #fff;"></span>
                      </a>
                      <label onclick="formSubmit('form_fundin_submit')"
                             style="font-size: 100%;"
                             class="text-white"><spring:message
                          code="menu.fundin"/></label>
                    </td>
                    <%--AGENT--%>
                    <td onclick="formSubmit('form_fundout_submit')"
                        class="hidden">
                      <a data-placement="bottom" data-toggle="tooltip"
                         data-original-title="<spring:message code="menu.fundout"/>">
                                                <span class="icon pe-less"
                                                      aria-hidden="true"></span>
                      </a>
                      <label onclick="formSubmit('form_fundout_submit')"><spring:message
                          code="menu.fundout"/></label>
                    </td>
                    <%--end AGENT--%>
                    <c:if test="${ID_CUSTOMER == userLogin.customerTypeId}">
                      <td onclick="formSubmit('form_transfer_money_submit')">
                        <a data-placement="bottom" data-toggle="tooltip"
                           data-original-title="<spring:message code="menu.moneyTranfer"/>">
                                                    <span class="icon pe-repeat"
                                                          aria-hidden="true"></span>
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

          <%--Card Dashboard--%>
          <%--<c:if test="${ID_MERCHANT == userLogin.customerTypeId}">--%>
          <%--<c:if test="${'true' eq MENU_ITEM_SHOW_CARD_DASHBOARD--%>
          <%--&& (userLogin.specialCustomerInfo != null--%>
          <%--|| userLogin.specialCustomerInfoOffline != null)}">--%>
          <%--<c:set var="cssCardDashboard"--%>
          <%--value="${param.nav eq 'cardDashboardMenu' ? 'active selected' : ''}"/>--%>
          <%--<c:set var="cardTypeParam"--%>
          <%--value="${userLogin.specialCustomerInfoOffline != null ? 'typeExport' : 'typeAPI'}"/>--%>
          <%--<li class="site-menu-item ${cssCardDashboard}">--%>
          <%--<form method="post" action="/dashboard/card-dashboard" class="hidden">--%>
          <%--<input type="hidden" name="dashboardType"--%>
          <%--value="${cardTypeParam}"/>--%>
          <%--<input type="hidden" name="${_csrf.parameterName}"--%>
          <%--value="${_csrf.token}"/>--%>
          <%--<button class="hidden" id="form_CardDashboard_submit"></button>--%>
          <%--</form>--%>
          <%--<a onclick="formSubmit('form_CardDashboard_submit')">--%>
          <%--<i class="site-menu-icon fa-truck" style="color: #1F5171" aria-hidden="true"></i>--%>
          <%--<span class="site-menu-title"><spring:message--%>
          <%--code="menu.card.dashboard"/></span>--%>
          <%--</a>--%>
          <%--</li>--%>
          <%--</c:if>--%>
          <%--</c:if>--%>
          <%--end Card Dashboard--%>

          <%--Mã thẻ theo lô--%>
          <c:if test="${ID_MERCHANT == userLogin.customerTypeId}">
            <c:if test="${'true' eq MENU_ITEM_SHOW_PHONE_BATCH_CARD}">
              <li class="site-menu-category"></li>
              <c:set var="cssBatchCards"
                     value="${param.nav eq 'batchCardsMenu' ? 'active selected' : ''}"/>
              <li class="site-menu-item ${cssBatchCards}">
                <form method="post" action="/batch-cards/list" class="hidden">
                  <input type="hidden" name="${_csrf.parameterName}"
                         value="${_csrf.token}"/>
                  <button class="hidden" id="form_batchCards_submit"></button>
                </form>
                <a onclick="formSubmit('form_batchCards_submit')">
                  <i class="site-menu-icon wb-mobile" style="color: #1F5171"
                     aria-hidden="true"></i>
                  <span class="site-menu-title"><spring:message
                      code="menu.batch.cards"/></span>
                </a>
              </li>
            </c:if>
          </c:if>
          <%--end Mua thẻ theo lô--%>

          <%--Mua thẻ qua API--%>
          <c:if test="${ID_MERCHANT == userLogin.customerTypeId}">
            <c:if test="${'true' eq MENU_ITEM_SHOW_PHONE_BATCH_CARD_API}">
              <li class="site-menu-category"></li>
              <c:set var="cssBatchCardsAPI"
                     value="${param.nav eq 'batchCardsMenuAPI' || param.nav eq 'batchCardsMenuAPIN02' ? 'active selected' : ''}"/>
              <li class="site-menu-item ${cssBatchCardsAPI}">
                <c:if test="${userLogin.specialCustomerInfo != null}">
                  <form method="post" action="/batch-cards/api-store/list"
                        class="hidden">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button class="hidden" id="form_batchCardsAPI_submit"></button>
                  </form>
                  <a onclick="formSubmit('form_batchCardsAPI_submit')">
                    <i class="site-menu-icon" style="color: #1F5171"
                       aria-hidden="true">
                      <img src="/assets/development/statis/images/navbar/navigation/api-32x32.png"
                           style="width: 22px;height: 22px;"/>
                    </i>
                    <span class="site-menu-title"><spring:message
                        code="menu.batch.cards.api"/></span>
                  </a>
                </c:if>
                <c:if test="${userLogin.specialCustomerInfoN02 != null}">
                  <form method="post" action="/batch-cards/api-store-n02/list"
                        class="hidden">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button class="hidden" id="form_batchCardsAPIN02_submit"></button>
                  </form>
                  <a onclick="formSubmit('form_batchCardsAPIN02_submit')">
                    <i class="site-menu-icon" style="color: #1F5171"
                       aria-hidden="true">
                      <img src="/assets/development/statis/images/navbar/navigation/api-32x32.png"
                           style="width: 22px;height: 22px;"/>
                    </i>
                    <span class="site-menu-title"><spring:message
                        code="menu.batch.cards.api"/></span>
                  </a>
                </c:if>

              </li>
            </c:if>
          </c:if>
          <%--end Mua thẻ qua API--%>

          <%--Dịch vụ tiện ích--%>
          <li class="site-menu-category"></li>
          <c:set var="serviceSelected" value="${('topUpMenu' eq param.nav || 'pinCodeMenu' eq param.nav || 'codeCardGame' eq param.nav) || param.nav eq 'maTheData'
                                              || param.nav eq 'electric' || param.nav eq 'water' || param.nav eq 'internet'
                                              || param.nav eq 'cable-television' || param.nav eq 'VETC'
                                              || param.nav eq 'menuViettelPay' || param.nav eq 'menuCashOutViettelPay'
                                              || param.nav eq 'menuPointTransfer'}"/>
          <c:set var="cssDichVuTienIch" value="${serviceSelected ? 'open active' : ''}"/>
          <c:set var="cssServiceGeneral" value="${serviceSelected ? 'is-shown' : ''}"/>

          <li class="site-menu-item has-sub ${cssDichVuTienIch} ">
            <a href="javascript:void(0)">
              <i class="site-menu-icon" aria-hidden="true">
                <img src="/assets/images/icon/DichvuTienIch/DichVuTienIch.png"/>
              </i>
              <span class="site-menu-title">Dịch vụ tiện ích</span>
              <span class="site-menu-arrow"></span>
            </a>
            <ul class="site-menu-sub">

              <%--Chuyển điểm--%>
              <c:if test="${
                            userLogin.customerCif == '0000001004'
                            || userLogin.customerCif == ' 0000005363'
                            || userLogin.customerCif == '0000001000'
                            || userLogin.customerCif == '0000001137'
                            || userLogin.customerCif == '0000005370'
                            || userLogin.customerCif == '0000005370'
                            || userLogin.customerCif == '0000001262'
                            || userLogin.customerCif == '0000001144'
                            || userLogin.customerCif == '0000001238'
                            || userLogin.customerCif == '0000000966'
                            || userLogin.customerCif == '0000000957'
                            || userLogin.customerCif == ' 0000000829'
                            || userLogin.customerCif == ' 0000004488'
                            || userLogin.customerCif == ' 0000004404'
                            || userLogin.customerCif == ' 0000005226'
                    }">
                <c:if test="${'true' eq MENU_ITEM_SHOW_POINTS_TRANSFER}">
                  <li class="site-menu-item ${param.nav eq 'menuPointTransfer' ? 'active selected' : ''}">
                    <a onclick="formSubmit('form_points_transfer_submit')">
                      <i class="site-menu-icon" aria-hidden="true">
                        <img src="/assets/images/icon/NapTienDT/NapTienDienThoai.png"/>
                      </i>
                      <span class="site-menu-title"><spring:message
                          code="label.points.transfer"/></span>
                    </a>
                  </li>
                </c:if>
              </c:if>
              <%--end Chuyển điểm--%>

              <%--Nạp tiền viettel pay--%>
              <c:if test="${'true' eq MENU_ITEM_SHOW_CASHIN_VIETTEL_PAY}">
                <li class="site-menu-item ${param.nav eq 'menuViettelPay' ? 'active selected' : ''}">
                  <a onclick="formSubmit('form_cashin_viettel_pay_submit')">
                    <i class="site-menu-icon" aria-hidden="true">
                      <img src="/assets/images/icon/NapTienDT/NapTienDienThoai.png"/>
                    </i>
                    <span class="site-menu-title"><spring:message
                        code="label.fundin.viettel.pay"/></span>
                  </a>
                </li>
              </c:if>
              <%--end Nạp tiền viettel pay--%>

              <%--Rút tiền viettel pay--%>
              <c:if test="${'true' eq MENU_ITEM_SHOW_CASHOUT_VIETTEL_PAY}">
                <li class="site-menu-item ${param.nav eq 'menuCashOutViettelPay' ? 'active selected' : ''}">
                  <a onclick="formSubmit('form_cashout_viettel_pay_submit')">
                    <i class="site-menu-icon" aria-hidden="true">
                      <img src="/assets/images/icon/NapTienDT/NapTienDienThoai.png"/>
                    </i>
                    <span class="site-menu-title"><spring:message
                        code="label.cashout.viettel.pay"/></span>
                  </a>
                </li>
              </c:if>
              <%--end Rút tiền viettel pay--%>

              <%--Nạp tiền điện thoại--%>
              <c:if test="${'true' eq MENU_ITEM_SHOW_PHONE_TOPUP}">
                <li class="site-menu-item ${param.nav eq 'topUpMenu' ? 'active selected' : ''}">
                  <form method="post" action="/topup" class="hidden">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button class="hidden"
                            id="form_top_up_submit"></button>
                  </form>
                  <a onclick="formSubmit('form_top_up_submit')">
                    <i class="site-menu-icon" aria-hidden="true">
                      <img src="/assets/images/icon/NapTienDT/NapTienDienThoai.png"/>
                    </i>
                    <span class="site-menu-title"><spring:message
                        code="menu.topUp"/></span>
                  </a>
                </li>
              </c:if>
              <%--end Nạp tiền điện thoại--%>

              <li class="site-menu-item has-sub ${cssDichVuTienIch}">
                <a href="javascript:void(0)">
                  <i class="site-menu-icon wb-mobile" aria-hidden="true"></i>
                  <span class="site-menu-title">Mã thẻ</span>
                  <span class="site-menu-arrow"></span>
                </a>
                <ul class="site-menu-sub">
                  <%--Nạp tiền điện thoại--%>
                  <%--<c:if test="${'true' eq MENU_ITEM_SHOW_PHONE_TOPUP}">--%>
                  <%--<c:set var="cssTopUp"--%>
                  <%--value="${param.nav eq 'topUpMenu' ? 'active selected '.concat(cssServiceGeneral) : cssServiceGeneral}"/>--%>
                  <%--<li class="site-menu-item ${cssTopUp}">--%>
                  <%--<form method="post" action="/topup" class="hidden">--%>
                  <%--<input type="hidden" name="${_csrf.parameterName}"--%>
                  <%--value="${_csrf.token}"/>--%>
                  <%--<button class="hidden"--%>
                  <%--id="form_top_up_submit"></button>--%>
                  <%--</form>--%>
                  <%--<a onclick="formSubmit('form_top_up_submit')">--%>
                  <%--<i class="site-menu-icon" aria-hidden="true">--%>
                  <%--<img src="/assets/images/icon/NapTienDT/NapTienDienThoai.png"/>--%>
                  <%--</i>--%>

                  <%--<span class="site-menu-title"><spring:message--%>
                  <%--code="menu.topUp"/></span>--%>
                  <%--</a>--%>
                  <%--</li>--%>
                  <%--</c:if>--%>
                  <%--end--%>

                  <%--Mã thẻ điện thoại--%>
                  <c:if test="${'true' eq MENU_ITEM_SHOW_PHONE_EPIN}">
                    <c:set var="cssPinCode"
                           value="${param.nav eq 'pinCodeMenu' ? 'active selected '.concat(cssServiceGeneral) : cssServiceGeneral}"/>
                    <li class="site-menu-item ${cssPinCode}">
                      <form method="post" action="/pin-code/order-info"
                            class="hidden">
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <button class="hidden"
                                id="form_pin_code_submit"></button>
                      </form>
                      <a onclick="formSubmit('form_pin_code_submit')">
                        <i class="site-menu-icon" aria-hidden="true">
                          <img src="/assets/images/icon/MaTheDT/MaTheDienThoai.png"/>
                        </i>

                        <span class="site-menu-title"><spring:message
                            code="menu.epin"/></span>
                      </a>
                    </li>
                  </c:if>
                  <%--end--%>

                  <%--Mã thẻ game--%>
                  <c:if test="${'true' eq MENU_ITEM_SHOW_GAME_EPIN}">
                    <c:set var="cssCodeCardGame"
                           value="${param.nav eq 'codeCardGame' ? 'active selected '.concat(cssServiceGeneral) : cssServiceGeneral}"/>
                    <li class="site-menu-item ${cssCodeCardGame}">
                      <form method="post" action="/game-code/buy-game-card"
                            class="hidden">
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <button class="hidden"
                                id="form_game_card_code_submit"></button>
                      </form>
                      <a onclick="formSubmit('form_game_card_code_submit')">
                        <i class="site-menu-icon" aria-hidden="true">
                          <img src="/assets/images/icon/MaTheGame/MaTheGame.png"/>
                        </i>
                        <span class="site-menu-title"><spring:message
                            code="menu.game.code"/></span>
                      </a>
                    </li>
                  </c:if>
                  <%--end--%>

                  <%--Mã thẻ data--%>
                  <c:set var="cssDataCard3G"
                         value="${param.nav eq 'maTheData' ? 'active selected '.concat(cssServiceGeneral) : cssServiceGeneral}"/>
                  <li class="site-menu-item ${cssDataCard3G}">
                    <form method="post" action="/card-data/buy-card-3G"
                          class="hidden">
                      <input type="hidden" name="${_csrf.parameterName}"
                             value="${_csrf.token}"/>
                      <button class="hidden"
                              id="form_card_data_3G_submit"></button>
                    </form>
                    <a onclick="formSubmit('form_card_data_3G_submit')">
                      <i class="site-menu-icon wb-mobile"
                         aria-hidden="true"></i>
                      <span class="site-menu-title"><spring:message
                          code="label.card.code.data"/></span>
                    </a>
                  </li>
                  </li>
                  <%--end Mã thẻ data--%>
                </ul>
              </li>

              <c:if test="${'true' eq MENU_ITEM_SHOW_BILL_PAYMENT}">
                <li class="site-menu-item ${param.nav eq 'electric' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/bill-payment/electric/management">
                      <%--<i class="site-menu-icon wb-warning" aria-hidden="true"></i>--%>
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/Dien/electric.png"/>
                    <span class="site-menu-title"><spring:message
                        code="service.bill.payment.type.electricity"/></span>
                  </a>
                </li>

                <li class="site-menu-item ${param.nav eq 'water' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/bill-payment/water/management">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/Nuoc/Nuoc.png"/>
                    <span class="site-menu-title"><spring:message
                        code="service.bill.payment.type.water"/></span>
                  </a>
                </li>

                <%--<li class="site-menu-item ${param.nav eq 'VETC' ? 'active selected' : ''}">--%>
                <%--<a class="animsition-link"--%>
                <%--href="${pageContext.request.contextPath}/bill-payment/VETC/management">--%>
                <%--<img class="site-menu-icon" aria-hidden="true"--%>
                <%--src="/assets/images/icon/payment/VETC.png"/>--%>
                <%--<span class="site-menu-title"><spring:message--%>
                <%--code="service.bill.payment.type.VETC"/></span>--%>
                <%--</a>--%>
                <%--</li>--%>

                <%--<li class="site-menu-item ${param.nav eq 'internet' ? 'active selected' : ''}">--%>
                <%--<a class="animsition-link"--%>
                <%--href="${pageContext.request.contextPath}/bill-payment/internet/management">--%>
                <%--<img class="site-menu-icon" aria-hidden="true"--%>
                <%--src="/assets/images/icon/Internet/Internet.png"/>--%>
                <%--<span class="site-menu-title"><spring:message--%>
                <%--code="service.bill.payment.type.internet"/></span>--%>
                <%--</a>--%>
                <%--</li>--%>


                <%--<li class="site-menu-item ${param.nav eq 'cable-television' ? 'active selected' : ''}">--%>
                <%--<a class="animsition-link"--%>
                <%--href="${pageContext.request.contextPath}/bill-payment/cable-television/management">--%>
                <%--<img class="site-menu-icon" aria-hidden="true"--%>
                <%--src="/assets/images/icon/TruyenHinhCap/TruyenHinhCap.png"/>--%>
                <%--<span class="site-menu-title"><spring:message--%>
                <%--code="service.bill.payment.type.cable.television"/></span>--%>
                <%--</a>--%>
                <%--</li>--%>
              </c:if>
            </ul>
          </li>
          <%--end Dịch vụ tiện ích--%>

          <%--Dịch vụ tài chính--%>
          <li class="site-menu-category"></li>
          <c:set var="cssDichVu" value="${param.nav eq 'dv_FEcredit'|| param.nav eq 'dv_Homecredit' ||
                                          param.nav eq 'dv_Acs' || param.nav eq 'dv_OcsBank' || param.nav eq 'dv_Prudential' ||
                                          param.nav eq 'dv_MCredit' ||   param.nav eq 'dv_MiraeAsset' || param.nav eq 'dv_Shinhan'||
                                          param.nav eq 'dv_AtmOnline' ||   param.nav eq 'dv_DrDong' ||
                                          param.nav eq 'dv_Maritime' ||   param.nav eq 'dv_PTI' ? 'open active' : ''}"/>
          <li class="site-menu-item has-sub ${cssDichVu} ">
            <a href="javascript:void(0)">
              <i class="site-menu-icon" aria-hidden="true">
                <img src="/assets/images/icon/DichvuTC/DichVuTaiChinh.png"/>
              </i>
              <span class="site-menu-title">Dịch vụ tài chính</span>
              <span class="site-menu-arrow"></span>
            </a>
            <ul class="site-menu-sub">
              <c:if test="${'true' eq MENU_ITEM_SHOW_FINANCIAL_SERVICES}">
                <%--<c:set var="cssDichVu" value="${param.nav eq 'dv_FEcredit' || param.nav eq 'dv_Homecredit'  ? 'open active' : ''}"/>--%>
                <%--FE--%>
                <li class="site-menu-item ${param.nav eq 'dv_FEcredit' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/financial-services/FEcredit/management">
                      <%--<i class="site-menu-icon  wb-memory" aria-hidden="true"></i>--%>
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/FECredit/FEcredit_3x.png"/>
                    <span class="site-menu-title">FE credit</span></a>
                </li>
                <%--end FE--%>

                <%--HomeCredit--%>
                <li class="site-menu-item ${param.nav eq 'dv_Homecredit' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/financial-services/Homecredit/management">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/HomeCredit/Homecredit.png"/>
                    <span class="site-menu-title">Home credit</span>
                  </a>
                </li>
                <%--end HomeCredit--%>

                <%-- Acs--%>
                <li class="site-menu-item ${param.nav eq 'dv_Acs' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/financial-services/Acs/management">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/ASC/ACS@3x.png"/>
                    <span class="site-menu-title">Acs</span>
                  </a>
                </li>
                <%-- end Acs--%>

                <%--OCSBANK--%>
                <li class="site-menu-item ${param.nav eq 'dv_OcsBank' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/financial-services/Ocb/management">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/OCB/OCB_3x.png"/>
                    <span class="site-menu-title">Ocb Credit</span>
                  </a>
                </li>

                <%--&lt;%&ndash;Prudential&ndash;%&gt;--%>
                <%--<li class="site-menu-item ${param.nav eq 'dv_Prudential' ? 'active selected' : ''}">--%>
                <%--<a class="animsition-link"--%>
                <%--href="${pageContext.request.contextPath}/financial-services/Prudential/management">--%>
                <%--<img class="site-menu-icon" aria-hidden="true"--%>
                <%--src="/assets/images/icon/Prudential/Prudential.png"/>--%>
                <%--<span class="site-menu-title">Prudential</span>--%>
                <%--</a>--%>
                <%--</li>--%>
                <%--&lt;%&ndash;end Prudential&ndash;%&gt;--%>

                <%--Shinhan--%>
                <li class="site-menu-item ${param.nav eq 'dv_Shinhan' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/financial-services/Shinhan/management">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/Shinhan/Shinhan.png"/>
                    <span class="site-menu-title">Shinhan</span>
                  </a>
                </li>
                <%--end Shinhan--%>

                <%--MCredit--%>
                <li class="site-menu-item ${param.nav eq 'dv_MCredit' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/financial-services/MCredit/management">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/MCredit/MCredit.png"/>
                    <span class="site-menu-title">M Credit</span>
                  </a>
                </li>
                <%--end MCredit--%>


                <%--MiraeAsset--%>
                <li class="site-menu-item ${param.nav eq 'dv_MiraeAsset' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/financial-services/MiraeAsset/management">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/MCredit/Mirae@3x.png"/>
                    <span class="site-menu-title">Mirae Asset</span>
                  </a>
                </li>
                <%--end MiraeAsset--%>

                <%--ATM Online--%>
                <li class="site-menu-item ${param.nav eq 'dv_AtmOnline' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/financial-services/AtmOnline/management">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/button/AtmOnline@3x.png"/>
                    <span class="site-menu-title">ATM Online</span>
                  </a>
                </li>
                <%--end ATM Online--%>

                <%--DrDong--%>
                <li class="site-menu-item ${param.nav eq 'dv_DrDong' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/financial-services/DrDong/management">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/button/DrDong@3x.png"/>
                    <span class="site-menu-title">DrDong</span>
                  </a>
                </li>
                <%--end DrDong--%>

                <%--Maritime--%>
                <li class="site-menu-item ${param.nav eq 'dv_Maritime' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="${pageContext.request.contextPath}/financial-services/Maritime/management">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/button/Maritime@3x.png"/>
                    <span class="site-menu-title">Maritime Bank</span>
                  </a>
                </li>
                <%--end Maritime--%>

                <%--PTI--%>
                <%--<li class="site-menu-item ${param.nav eq 'dv_PTI' ? 'active selected' : ''}">--%>
                <%--<a class="animsition-link"--%>
                <%--href="${pageContext.request.contextPath}/financial-services/PTI/management">--%>
                <%--<img class="site-menu-icon" aria-hidden="true"--%>
                <%--src="/assets/images/icon/button/PTI@3x.png"/>--%>
                <%--<span class="site-menu-title">Bảo hiểm PTI</span>--%>
                <%--</a>--%>
                <%--</li>--%>
                <%--end PTI--%>


                </li>
              </c:if>
            </ul>
          </li>
          <%--end Dịch vụ tài chính--%>

          <%--Dịch vụ internet tivi--%>
          <li class="site-menu-category"></li>
          <c:set var="serviceSelected" value="${param.nav eq 'SPT_Phone'
                                              || param.nav eq 'SPT' || param.nav eq 'SST' || param.nav eq 'SPT'
                                              || param.nav eq 'KPlus' || param.nav eq 'FPT'}"/>
          <c:set var="cssDichVuTienIch" value="${serviceSelected ? 'open active' : ''}"/>
          <c:set var="cssServiceGeneral" value="${serviceSelected ? 'is-shown' : ''}"/>

          <li class="site-menu-item has-sub ${cssDichVuTienIch} ">
            <a href="javascript:void(0)">
              <i class="site-menu-icon" aria-hidden="true">
                <img src="/assets/images/icon/DichvuTienIch/DichVuTienIch.png"/>
              </i>
              <span class="site-menu-title">Dịch vụ internet, truyền hình</span>
              <span class="site-menu-arrow"></span>
            </a>
            <ul class="site-menu-sub">
              <li class="site-menu-item has-sub ${cssDichVuTienIch}">
                <a href="javascript:void(0)">
                  <i class="site-menu-icon" aria-hidden="true">
                    <img src="/assets/images/icon/Internet/Internet.png"/>
                  </i>
                  <span class="site-menu-title">Internet</span>
                  <span class="site-menu-arrow"></span>
                </a>
                <ul class="site-menu-sub">

                  <%--FPT--%>
                  <c:set var="cssFPT"
                         value="${param.nav eq 'FPT' ? 'active selected '.concat(cssServiceGeneral) : cssServiceGeneral}"/>
                  <li class="site-menu-item ${cssFPT}">
                    <a class="animsition-link"
                       href="${pageContext.request.contextPath}/bill-payment/FPT/management">
                      <span class="site-menu-title">FPT Internet</span>
                    </a>
                  </li>
                  <%--end FPT --%>

                  <%--SPT--%>
                  <c:set var="cssSPT"
                         value="${param.nav eq 'SPT' ? 'active selected '.concat(cssServiceGeneral) : cssServiceGeneral}"/>
                  <li class="site-menu-item ${cssSPT}">
                    <a class="animsition-link"
                       href="${pageContext.request.contextPath}/bill-payment/SPT/management">
                      <span class="site-menu-title">SPT Internet</span>
                    </a>
                  </li>
                  <%--end SPT --%>

                  <%--SST Internet--%>
                  <c:set var="cssSST"
                         value="${param.nav eq 'SST' ? 'active selected '.concat(cssServiceGeneral) : cssServiceGeneral}"/>
                  <li class="site-menu-item ${cssSST}">
                    <a class="animsition-link"
                       href="${pageContext.request.contextPath}/bill-payment/SST/management">
                      <span class="site-menu-title">SST Internet</span>
                    </a>
                  </li>
                  <%--end SST Internet --%>

                  <%--ĐT cố định SPT--%>
                  <c:set var="cssSPT_Phone"
                         value="${param.nav eq 'SPT_Phone' ? 'active selected '.concat(cssServiceGeneral) : cssServiceGeneral}"/>
                  <li class="site-menu-item ${cssSPT_Phone}">
                    <a class="animsition-link"
                       href="${pageContext.request.contextPath}/bill-payment/SPT_Phone/management">
                      <span class="site-menu-title">ĐT cố định SPT</span>
                    </a>
                  </li>
                  <%--end ĐT cố định SPT --%>

                </ul>
              </li>

              <li class="site-menu-item has-sub ${cssDichVuTienIch}">
                <a href="javascript:void(0)">
                  <i class="site-menu-icon" aria-hidden="true">
                    <img src="/assets/images/icon/TruyenHinhCap/TruyenHinhCap.png"/>
                  </i>
                  <span class="site-menu-title">Truyền hình</span>
                  <span class="site-menu-arrow"></span>
                </a>
                <ul class="site-menu-sub">
                  <%--KPlus--%>
                  <c:set var="cssKPlus"
                         value="${param.nav eq 'KPlus' ? 'active selected '.concat(cssServiceGeneral) : cssServiceGeneral}"/>
                  <li class="site-menu-item ${cssKPlus}">
                    <a class="animsition-link"
                       href="${pageContext.request.contextPath}/bill-payment/KPlus/management">
                      <span class="site-menu-title">K +</span>
                    </a>
                  </li>
                  <%--end KPlus --%>

                </ul>
              </li>
            </ul>
          </li>
          <%--end Dịch vụ internet tivi--%>

          <%--Dịch vụ mua sắm--%>
          <li class="site-menu-category"></li>
          <c:set var="cssDichVuMuaSam" value="${param.nav eq 'DT_MTB'|| param.nav eq 'DT_DL' ||
                                          param.nav eq 'PK_TBS' || param.nav eq 'LT_TBIT' || param.nav eq 'MA_QP'  ? 'open active' : ''}"/>

          <li class="site-menu-item has-sub ${cssDichVuMuaSam} ">
            <a href="javascript:void(0)">
              <i class="site-menu-icon" aria-hidden="true">
                <img src="/assets/images/icon/DichvuTienIch/DichVuTienIch.png"/>
              </i>
              <span class="site-menu-title">Dịch vụ mua sắm</span>
              <span class="site-menu-arrow"></span>
            </a>
            <ul class="site-menu-sub">
              <c:if test="${'true' eq MENU_ITEM_SHOW_BILL_PAYMENT}">

                <li class="site-menu-item ${param.nav eq 'DT_MTB' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="https://mall.zo-ta.com/collections/dien-thoai-may-tinh-bang">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/Internet/DT_MTB.png"/>
                    <span class="site-menu-title"> Điện Thoại - Máy Tính Bảng</span>
                  </a>
                </li>
                <li class="site-menu-item ${param.nav eq 'PK_TBS' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="https://mall.zo-ta.com/collections/thiet-bi-so-phu-kien-so">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/Internet/PK_TBS.png"/>
                    <span class="site-menu-title">Thiết bị số - Phụ kiện số</span>
                  </a>
                </li>
                <li class="site-menu-item ${param.nav eq 'LT_TBIT' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="https://mall.zo-ta.com/collections/laptop-thiet-bi-dien-tu">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/Internet/LT_TBIT.png"/>
                    <span class="site-menu-title"> Laptop - Thiết bị điện tử </span>
                  </a>
                </li>
                <li class="site-menu-item ${param.nav eq 'DT_DL' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="https://mall.zo-ta.com/collections/dien-tu-dien-lanh">
                    <img class="site-menu-icon" aria-hidden="true"
                         src="/assets/images/icon/Internet/DT_DL.png"/>
                    <span class="site-menu-title">Điện Tử - Điện Lạnh</span>
                  </a>
                </li>
                <li class="site-menu-item ${param.nav eq 'MA_QP' ? 'active selected' : ''}">
                  <a class="animsition-link"
                     href="https://mall.zo-ta.com/collections/dien-gia-dung">
                    <i class="site-menu-icon fa fa-camera"
                       aria-hidden="true"></i>
                    <span class="site-menu-title">Điện gia dụng</span>
                  </a>
                </li>
              </c:if>
            </ul>
          </li>
          <%--end Dịch vụ mua sắm--%>


          <%--Lich su giao dich--%>
          <li class="site-menu-category"></li>
          <c:if test="${'true' eq MENU_ITEM_SHOW_TRANSACTION_HISTORY}">
            <c:set var="cssHis"
                   value="${param.nav eq 'historyTrans'? 'active selected' : ''}"/>
            <li class="site-menu-item ${cssHis}">
              <a href="/trans-log/transaction-history">
                <i class="site-menu-icon" aria-hidden="true">
                  <img src="/assets/images/icon/LichSuDG/LichSuGD.png"/>
                </i>

                <span class="site-menu-title"><spring:message
                    code="menu.transaction.history"/></span></a>
            </li>
          </c:if>
          <%--end Lich su giao dich--%>

          <%--Thống kê--%>
          <li class="site-menu-category"></li>
          <c:set var="cssStatistic"
                 value="${param.nav eq 'menu_statistic'? 'active selected' : ''}"/>
          <li class="site-menu-item ${cssStatistic}">
            <a href="/statistic">
              <i class="site-menu-icon" aria-hidden="true">
                <img src="/assets/images/icon/LichSuDG/LichSuGD.png"/>
              </i>

              <span class="site-menu-title"><spring:message
                  code="menu.statistic"/></span></a>
          </li>
          <%--end Lich su giao dich--%>

          <%--Tai khoan--%>
          <%--<c:if test="${'true' eq MENU_ITEM_SHOW_ACCOUNT_INFO}">
            <li class="site-menu-category"></li>
            <c:set var="cssTaiKhoan" value="${param.nav eq 'infoAccountVeirfication'? 'active selected' : ''}"/>
            <li class="site-menu-item has-sub ${cssTaiKhoan} ">
              <a href="javascript:void(0)">
                <i class="site-menu-icon wb-lock" aria-hidden="true"></i>
                <span class="site-menu-title"><spring:message code="menu.left.account"/></span>
                <span class="site-menu-arrow"></span>
              </a>
              <ul class="site-menu-sub">

                <li class="site-menu-item ${cssTaiKhoan}">
                  <a href="${pageContext.request.contextPath}/account/info">
                    <i class="site-menu-icon fa fa-address-card" aria-hidden="true"></i>
                    <span class="site-menu-title"><spring:message code="menu.account.info"/></span>
                    <div class="site-menu-badge"></div>
                  </a>
                </li>

              </ul>
            </li>
          </c:if>--%>
          <%--end Tai khoan--%>

          <%--Cài đặt--%>
          <%--<li class="site-menu-category"></li>--%>
          <%--<c:set var="cssSetting" value="${param.nav eq 'themtaikhoan' || param.nav eq 'themthe'--%>
          <%--|| param.nav eq 'paySec'  ? 'open active' : ''}"/>--%>
          <%--<li class="site-menu-item has-sub ${cssSetting} ">--%>
          <%--<a href="javascript:void(0)">--%>
          <%--<i class="site-menu-icon wb-lock" aria-hidden="true"></i>--%>
          <%--<span class="site-menu-title"><spring:message code="menu.setting"/></span>--%>
          <%--<span class="site-menu-arrow"></span>--%>
          <%--</a>--%>
          <%--<ul class="site-menu-sub">--%>
          <%--&lt;%&ndash;Card&ndash;%&gt;--%>
          <%--<c:if test="${'true' eq MENU_ITEM_SHOW_CARD_MANAGEMENT}">--%>
          <%--&lt;%&ndash;<c:set var="cssCardMa" value="${param.nav eq 'themtaikhoan' ||&ndash;%&gt;--%>
          <%--&lt;%&ndash;param.nav eq 'themthe' ? 'active selected' : ''}"/>&ndash;%&gt;--%>
          <%--<li class="site-menu-item ${param.nav == 'themthe' ? 'active selected' : ''}">--%>
          <%--&lt;%&ndash;<form method="post" action="/bank/manage" class="hidden">&ndash;%&gt;--%>
          <%--&lt;%&ndash;<input type="hidden" name="${_csrf.parameterName}"&ndash;%&gt;--%>
          <%--&lt;%&ndash;value="${_csrf.token}"/>&ndash;%&gt;--%>
          <%--&lt;%&ndash;<button class="hidden" id="form_card_manager_submit"></button>&ndash;%&gt;--%>
          <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
          <%--&lt;%&ndash;<a onclick="formSubmit('form_card_manager_submit')">&ndash;%&gt;--%>
          <%--&lt;%&ndash;<i class="site-menu-icon fa-credit-card" aria-hidden="true"></i>&ndash;%&gt;--%>
          <%--&lt;%&ndash;<span class="site-menu-title"><spring:message code="menu.card.management"/></span>&ndash;%&gt;--%>
          <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
          <%--<a href="/bank/manage">--%>
          <%--<i class="site-menu-icon fa-credit-card" aria-hidden="true"></i>--%>
          <%--<span class="site-menu-title"><spring:message code="menu.card.management"/></span>--%>
          <%--</a>--%>
          <%--</li>--%>
          <%--</c:if>--%>
          <%--&lt;%&ndash;end Card&ndash;%&gt;--%>

          <%--&lt;%&ndash;Payment security&ndash;%&gt;--%>
          <%--<c:if test="${'true' eq MENU_ITEM_SHOW_SETTING_SECURITY}">--%>
          <%--&lt;%&ndash;<c:set var="cssSec" value="${param.nav eq 'paySec'? 'active selected' : ''}"/>&ndash;%&gt;--%>
          <%--<li class="site-menu-item ${param.nav eq 'paySec'? 'active selected' : ''}">--%>
          <%--<form method="post" action="/system/payment-security" class="hidden">--%>
          <%--<input type="hidden" name="${_csrf.parameterName}"--%>
          <%--value="${_csrf.token}"/>--%>
          <%--<button class="hidden" id="form_payment_security_submit"></button>--%>
          <%--</form>--%>
          <%--<a href="javascript:void(0)" onclick="formSubmit('form_payment_security_submit')">--%>
          <%--<i class="site-menu-icon wb-settings" aria-hidden="true"></i>--%>
          <%--<span class="site-menu-title"><spring:message code="menu.setting.secutiry"/></span>--%>
          <%--&lt;%&ndash;<span class="site-menu-arrow"></span>&ndash;%&gt;--%>
          <%--</a>--%>

          <%--</li>--%>
          <%--</c:if>--%>
          <%--&lt;%&ndash;end payment security&ndash;%&gt;--%>

          <%--&lt;%&ndash;Đổi mật khẩu&ndash;%&gt;--%>
          <%--<li class="site-menu-item ${cssSetting}">--%>
          <%--<a href="/customer/password-required">--%>
          <%--<i class="site-menu-icon wb-settings" aria-hidden="true"></i>--%>
          <%--<span class="site-menu-title"><spring:message code="changepass.nav.change.pass"/></span>--%>
          <%--&lt;%&ndash;<span class="site-menu-arrow"></span>&ndash;%&gt;--%>
          <%--</a>--%>
          <%--</li>--%>
          <%--&lt;%&ndash;end Đổi mật khẩu&ndash;%&gt;--%>

          <%--&lt;%&ndash;Đăng xuất&ndash;%&gt;--%>
          <%--<li class="site-menu-item ${cssSetting}">--%>
          <%--<a href="/logout">--%>
          <%--<i class="site-menu-icon icon wb-power" aria-hidden="true"></i>--%>
          <%--<spring:message code="login.sign.out"></spring:message></a>--%>
          <%--</li>--%>
          <%--&lt;%&ndash;end Đăng xuất&ndash;%&gt;--%>
          <%--</ul>--%>
          <%--</li>--%>
          <%--end Cài đặt--%>

          <li class="site-menu-category"></li>
          <li class="site-menu-item">
            <a href="https://zotahelp.freshdesk.com/support/solutions/64000146261"
               target="_blank">
              <i class="site-menu-icon" aria-hidden="true">
                <img src="/assets/images/icon/Trogiup/Trogiup.png"/>
              </i>
              <span class="site-menu-title"><spring:message
                  code="menu.helpdesk"/></span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>

  <ul class="site-menu">
    <li class="site-menu-item">
      <a href="tel:0888612468" style="color: #019cde;">
        <i class="site-menu-icon icon pe-call"></i>
        <span class="site-menu-title"><spring:message code="common.hotline"/></span>
      </a>
    </li>
  </ul>


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
    <form method="post" action="/cashin-viettel-pay" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="form_cashin_viettel_pay_submit"></button>
    </form>
    <form method="post" action="/cashout-viettel-pay" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="form_cashout_viettel_pay_submit"></button>
    </form>
    <form method="post" action="/points-transfer" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="form_points_transfer_submit"></button>
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