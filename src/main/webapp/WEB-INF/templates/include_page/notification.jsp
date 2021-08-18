<%@ page import="vn.mog.ewallet.consumer.web.contract.UserLogin" %>
<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.AbstractAPIClient.SESSION_ACCOUNT_LOGIN" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/assets/development/js/jquery.3.3.1.min.js"></script>


<%
  String menu = (String) request.getSession().getAttribute("menu");
  UserLogin userLogin = (UserLogin) request.getSession().getAttribute(SESSION_ACCOUNT_LOGIN);
  if (userLogin == null) {
    userLogin = new UserLogin();
  }
  Boolean isPMSaccount = (Boolean) request.getSession().getAttribute("isPMSaccount");
%>
<c:set var="userLogin" value="<%=userLogin%>" scope="application"/>
<c:set var="isPMSaccount" value="<%=isPMSaccount%>" scope="application"/>

<nav class="site-navbar navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="navbar-header" style="background-color: #21292e;">

    <div class="progress skill-bar full-width" id="loader">
      <div class="progress-bar .progress-bar-loading" role="progressbar" aria-valuenow="100"
           aria-valuemin="0" aria-valuemax="100">
        <span class="skill">Loading...<i class="val">100%</i></span>
      </div>
    </div>

    <button type="button"
            class="navbar-toggler hamburger hamburger-close navbar-toggler-left hided"
            data-toggle="menubar">
      <span class="sr-only">Toggle navigation</span>
      <span class="hamburger-bar"></span>
    </button>
    <%--<button type="button" class="navbar-toggler collapsed" data-target="#site-navbar-collapse"--%>
    <%--data-toggle="collapse">--%>
    <%--<i class="icon wb-more-horizontal" aria-hidden="true"></i>--%>
    <%--</button>--%>
    <div class="navbar-brand navbar-brand-center" data-toggle="gridmenu">
      <a href="/dashboard/index"><img class="navbar-brand-logo" src="/assets/images/logo_zota.png"
                                      title="ZO-TA" style="margin-right: 4rem;margin-left: 4rem;">
      </a>
    </div>
    <%--<button type="button" class="navbar-toggler collapsed" data-target="#site-navbar-search"--%>
    <%--data-toggle="collapse">--%>
    <%--<span class="sr-only">Toggle Search</span>--%>
    <%--<i class="icon wb-search" aria-hidden="true"></i>--%>
    <%--</button>--%>
    <%--Chỉ để icon cờ--%>
    <%--<div class="navbar-toggler nav-item dropdown">--%>
    <%--<a class="nav-link fs16" data-toggle="dropdown" href="javascript:void(0)" style="padding-bottom: 0px;padding-top: 0px;padding-right: 0px;padding-left: 0px;line-height: 16px;"--%>
    <%--data-animation="scale-up" aria-expanded="false" role="button">--%>
    <%--<span--%>
    <%--class="flag-icon ${(language_title == null || language_title eq 'vi') ? 'flag-icon-vn' : 'flag-icon-gb'}"></span>--%>
    <%--</a>--%>
    <%--<div class="dropdown-menu" role="menu" style="right: 0px;">--%>

    <%--<c:choose>--%>
    <%--<c:when test="${'true' eq param.secure_page}">--%>
    <%--<form method="post" action="" id="change_lang_form">--%>
    <%--<label class="dropdown-item" role="menuitem" for="lang_en">--%>
    <%--<span class="flag-icon flag-icon-gb"></span>English</label>--%>
    <%--<input type="checkbox" id="lang_en" name="lang" value="en"--%>
    <%--class="hidden" onclick="changeLanguage()">--%>
    <%--<label class="dropdown-item" role="menuitem" for="lang_vi">--%>
    <%--<span class="flag-icon flag-icon-vn"></span>Vietnam</label>--%>
    <%--<input type="checkbox" id="lang_vi" name="lang" value="vi"--%>
    <%--class="hidden" onclick="changeLanguage()">--%>
    <%--<input type="hidden" name="${_csrf.parameterName}"--%>
    <%--value="${_csrf.token}"/>--%>
    <%--</form>--%>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
    <%--<a class="dropdown-item" href="?lang=en" id="langEn"--%>
    <%--role="menuitem"> <span--%>
    <%--class="flag-icon flag-icon-gb"></span>English</a>--%>
    <%--<a class="dropdown-item" href="?lang=vi" id="langVi"--%>
    <%--role="menuitem"> <span--%>
    <%--class="flag-icon flag-icon-vn"></span>Vietnam</a>--%>
    <%--</c:otherwise>--%>
    <%--</c:choose>--%>

    <%--</div>--%>
    <%--</div>--%>
    <%--End chỉ để icon cờ--%>
    <%--<button type="button" class="navbar-toggler collapsed" data-target="#site-navbar-collapse"--%>
    <%--data-toggle="collapse">--%>
    <%--<i class="icon wb-more-horizontal" aria-hidden="true"></i>--%>
    <%--</button>--%>
    <div class="navbar-toggler nav-item dropdown">
      <button class="navbar-toggler" data-toggle="dropdown" href="javascript:void(0)" data-animation="scale-up" aria-expanded="false"
              style="padding-top: 0px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;height: 20px;">
        <%--<span--%>
        <%--class="flag-icon ${(language_title == null || language_title eq 'vi') ? 'flag-icon-vn' : 'flag-icon-gb'}"></span>--%>
        <i class="icon wb-more-horizontal" aria-hidden="true"></i>
      </button>
      <div class="dropdown-menu" role="menu" style="right: 10px;">
        <div class="dropdown-item site-menu" data-plugin="menu" style="padding-bottom: 0px; border-bottom: 1px solid #eee">
          <div class="site-menu-item has-sub ${cssHoaDon}" style="background-color:white">
            <div>
              <i class="site-menu-icon wb-settings" aria-hidden="true"></i>
              <span class="site-menu-title"><spring:message code="label.setting"/></span>
              <div class="site-menu-badge"></div>
            </div>
            <div class="site-menu-sub">
              <div class="site-menu-item ${param.nav eq 'infoAccountVeirfication' ? 'active selected' : ''}" style="background-color:white;">
                <a href="${pageContext.request.contextPath}/account/info">
                  <i class="site-menu-icon fa fa-address-card" aria-hidden="true"></i>
                  <span class="site-menu-title"><spring:message code="menu.account.info"/></span>
                  <div class="site-menu-badge"></div>
                </a>
              </div>

              <c:if test="${isPMSaccount == false}">
              <div class="site-menu-item ${param.nav eq 'infoAccountStoreVeirfication' ? 'active selected' : ''}" style="background-color:white;">
                <a href="${pageContext.request.contextPath}/account/store/info">
                  <i class="site-menu-icon fa fa-address-card" aria-hidden="true"></i>
                  <span class="site-menu-title"><spring:message code="account.store.infor"/></span>
                  <div class="site-menu-badge"></div>
                </a>
              </div>

              <div class="site-menu-item ${param.nav eq 'infoAccountParentVeirfication' ? 'active selected' : ''}" style="background-color:white;">
                <a href="${pageContext.request.contextPath}/account/parent/info">
                  <i class="site-menu-icon fa fa-address-card" aria-hidden="true"></i>
                  <span class="site-menu-title"><spring:message code="menu.account.parent.info"/></span>
                  <div class="site-menu-badge"></div>
                </a>
              </div>
              </c:if>

              <%--Payment security--%>
              <c:if test="${'true' eq MENU_ITEM_SHOW_SETTING_SECURITY}">
                <div class="site-menu-item ${param.nav eq 'paySec' ? 'active selected' : ''}" style="background-color:white;">
                  <form method="post" action="/system/payment-security" class="hidden">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button class="hidden" id="form_payment_security_submit"></button>
                  </form>
                  <a href="javascript:void(0)" onclick="formSubmit('form_payment_security_submit')">
                    <i class="site-menu-icon wb-settings" aria-hidden="true"></i>
                    <span class="site-menu-title"><spring:message code="menu.setting.secutiry"/></span>
                  </a>
                </div>
              </c:if>
              <%--end payment security--%>

              <div class="site-menu-item" style="background-color:white;">
                <a class="dropdown-item" href="/customer/password-required" role="menuitem">
                  <i class="icon wb-lock" aria-hidden="true"></i> <spring:message
                    code="changepass.nav.change.pass"></spring:message></a>
              </div>
              <div class="site-menu-item" style="background-color:white;">
                <a class="dropdown-item" href="/logout" role="menuitem">
                  <i class="icon wb-power" aria-hidden="true"></i> <spring:message
                    code="login.sign.out"></spring:message></a>
              </div>
            </div>
          </div>
        </div>
        <c:choose>
          <c:when test="${'true' eq param.secure_page}">
            <form method="post" action="" id="change_lang_form">
              <label class="dropdown-item" role="menuitem" for="lang_en">
                <span class="flag-icon flag-icon-gb"></span>English</label>
              <input type="checkbox" id="lang_en" name="lang" value="en"
                     class="hidden" onclick="changeLanguage()">
              <label class="dropdown-item" role="menuitem" for="lang_vi">
                <span class="flag-icon flag-icon-vn"></span>Vietnam</label>
              <input type="checkbox" id="lang_vi" name="lang" value="vi"
                     class="hidden" onclick="changeLanguage()">
              <input type="hidden" name="${_csrf.parameterName}"
                     value="${_csrf.token}"/>
            </form>
          </c:when>
          <c:otherwise>
            <div class="dropdown-item site-menu" data-plugin="menu" style="padding-bottom: 0px;">
              <div class="site-menu-item has-sub ${cssHoaDon}" style="background-color:white">
                <div>
                  <i class="site-menu-icon fa fa-address-card" aria-hidden="true"></i>
                  <span class="site-menu-title"><spring:message code="label.languages"/></span>
                  <div class="site-menu-badge"></div>
                </div>
                <div class="site-menu-sub">
                  <div class="site-menu-item" style="background-color:white;">
                    <a class="dropdown-item" href="?lang=en" id="langEn" style="color:rgb(118, 131, 143);"
                       role="menuitem"> <span
                        class="flag-icon flag-icon-gb"></span>English</a>
                  </div>
                  <div class="site-menu-item" style="background-color:white;">
                    <a class="dropdown-item" href="?lang=vi" id="langVi" style="color:rgb(118, 131, 143);"
                       role="menuitem"> <span
                        class="flag-icon flag-icon-vn"></span>Vietnam</a>
                  </div>
                </div>
              </div>
            </div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>

  </div>

  <div class="navbar-container container-fluid">
    <div class="collapse navbar-collapse navbar-collapse-toolbar" id="site-navbar-collapse">
      <ul class="nav navbar-toolbar hidden">
        <li class="nav-item hidden-float" id="toggleMenubar">
          <a class="nav-link" data-toggle="menubar" href="#" role="button">
            <i class="icon hamburger hamburger-arrow-left">
              <span class="sr-only">Toggle menubar</span>
              <span class="hamburger-bar"></span>
            </i>
          </a>
        </li>
        <%--<li class="nav-item hidden-float">--%>
        <%--<a class="nav-link icon wb-search" data-toggle="collapse" href="#"--%>
        <%--data-target="#site-navbar-search" role="button">--%>
        <%--<span class="sr-only">Toggle Search</span>--%>
        <%--</a>--%>
        <%--</li>--%>
      </ul>
      <spring:message code="language" var="language_title"/>
      <ul class="nav navbar-toolbar navbar-right navbar-toolbar-right">
        <li class="nav-item dropdown">
          <a class="nav-link fs16" data-toggle="dropdown" href="javascript:void(0)"
             data-animation="scale-up" aria-expanded="false" role="button">
            <span
                class="flag-icon ${(language_title == null || language_title eq 'vi') ? 'flag-icon-vn' : 'flag-icon-gb'}"></span>
          </a>
          <div class="dropdown-menu" role="menu">

            <c:choose>
              <c:when test="${'true' eq param.secure_page}">
                <form method="post" action="" id="change_lang_form">
                  <label class="dropdown-item" role="menuitem" for="lang_en">
                    <span class="flag-icon flag-icon-gb"></span>English</label>
                  <input type="checkbox" id="lang_en" name="lang" value="en"
                         class="hidden" onclick="changeLanguage()">
                  <label class="dropdown-item" role="menuitem" for="lang_vi">
                    <span class="flag-icon flag-icon-vn"></span>Vietnam</label>
                  <input type="checkbox" id="lang_vi" name="lang" value="vi"
                         class="hidden" onclick="changeLanguage()">
                  <input type="hidden" name="${_csrf.parameterName}"
                         value="${_csrf.token}"/>
                </form>
              </c:when>
              <c:otherwise>
                <a class="dropdown-item" href="?lang=en" id="langEn"
                   role="menuitem"> <span
                    class="flag-icon flag-icon-gb"></span>English</a>
                <a class="dropdown-item" href="?lang=vi" id="langVi"
                   role="menuitem"> <span
                    class="flag-icon flag-icon-vn"></span>Vietnam</a>
              </c:otherwise>
            </c:choose>

          </div>
        </li>
        <%--<li class="nav-item dropdown hidden">--%>
          <%--<a class="nav-link fs16" data-toggle="dropdown" href="javascript:void(0)"--%>
             <%--title="Notifications" aria-expanded="false" data-animation="scale-up"--%>
             <%--role="button">--%>
            <%--<i class="icon wb-bell" aria-hidden="true"></i>--%>
            <%--<span class="badge badge-pill badge-danger up">5</span>--%>
          <%--</a>--%>
          <%--<div class="dropdown-menu dropdown-menu-right dropdown-menu-media" role="menu">--%>
            <%--<div class="dropdown-menu-header">--%>
              <%--<h5>Thông báo</h5> <span class="badge badge-round badge-danger">Tin mới 5</span>--%>
            <%--</div>--%>
            <%--<div class="list-group">--%>
              <%--<div data-role="container">--%>
                <%--<div data-role="content">--%>
                  <%--<a class="list-group-item dropdown-item"--%>
                     <%--href="javascript:void(0)"--%>
                     <%--role="menuitem">--%>
                    <%--<div class="media">--%>
                      <%--<div class="pr-10"><i--%>
                          <%--class="icon wb-order bg-red-600 white icon-circle"--%>
                          <%--aria-hidden="true"></i></div>--%>
                      <%--<div class="media-body">--%>
                        <%--<h6 class="media-heading">A new order has been--%>
                          <%--placed</h6>--%>
                        <%--<time class="media-meta"--%>
                              <%--datetime="2018-06-12T20:50:48+08:00">5 hours--%>
                          <%--ago--%>
                        <%--</time>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                  <%--</a>--%>
                  <%--<a class="list-group-item dropdown-item"--%>
                     <%--href="javascript:void(0)"--%>
                     <%--role="menuitem">--%>
                    <%--<div class="media">--%>
                      <%--<div class="pr-10"><i--%>
                          <%--class="icon wb-user bg-green-600 white icon-circle"--%>
                          <%--aria-hidden="true"></i></div>--%>
                      <%--<div class="media-body">--%>
                        <%--<h6 class="media-heading">Completed the task</h6>--%>
                        <%--<time class="media-meta"--%>
                              <%--datetime="2018-06-11T18:29:20+08:00">2 days--%>
                          <%--ago--%>
                        <%--</time>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                  <%--</a>--%>
                  <%--<a class="list-group-item dropdown-item"--%>
                     <%--href="javascript:void(0)"--%>
                     <%--role="menuitem">--%>
                    <%--<div class="media">--%>
                      <%--<div class="pr-10"><i--%>
                          <%--class="icon wb-settings bg-red-600 white icon-circle"--%>
                          <%--aria-hidden="true"></i></div>--%>
                      <%--<div class="media-body">--%>
                        <%--<h6 class="media-heading">Settings updated</h6>--%>
                        <%--<time class="media-meta"--%>
                              <%--datetime="2018-06-11T14:05:00+08:00">2 days--%>
                          <%--ago--%>
                        <%--</time>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                  <%--</a>--%>
                  <%--<a class="list-group-item dropdown-item"--%>
                     <%--href="javascript:void(0)"--%>
                     <%--role="menuitem">--%>
                    <%--<div class="media">--%>
                      <%--<div class="pr-10"><i--%>
                          <%--class="icon wb-calendar bg-blue-600 white icon-circle"--%>
                          <%--aria-hidden="true"></i></div>--%>
                      <%--<div class="media-body">--%>
                        <%--<h6 class="media-heading">Event started</h6>--%>
                        <%--<time class="media-meta"--%>
                              <%--datetime="2018-06-10T13:50:18+08:00">3 days--%>
                          <%--ago--%>
                        <%--</time>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                  <%--</a>--%>
                  <%--<a class="list-group-item dropdown-item"--%>
                     <%--href="javascript:void(0)"--%>
                     <%--role="menuitem">--%>
                    <%--<div class="media">--%>
                      <%--<div class="pr-10"><i--%>
                          <%--class="icon wb-chat bg-orange-600 white icon-circle"--%>
                          <%--aria-hidden="true"></i></div>--%>
                      <%--<div class="media-body">--%>
                        <%--<h6 class="media-heading">Message received</h6>--%>
                        <%--<time class="media-meta"--%>
                              <%--datetime="2018-06-10T12:34:48+08:00">3 days--%>
                          <%--ago--%>
                        <%--</time>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                  <%--</a>--%>
                <%--</div>--%>
              <%--</div>--%>
            <%--</div>--%>
            <%--<div class="dropdown-menu-footer">--%>
              <%--<a class="dropdown-menu-footer-btn" href="javascript:void(0)"--%>
                 <%--role="button">--%>
                <%--<i class="icon wb-settings" aria-hidden="true"></i>--%>
              <%--</a>--%>
              <%--<a class="dropdown-item" href="javascript:void(0)" role="menuitem">Tất--%>
                <%--cả thông--%>
                <%--báo</a>--%>
            <%--</div>--%>
          <%--</div>--%>
        <%--</li>--%>
        <li class="nav-item dropdown">
          <a class="nav-link navbar-avatar" data-toggle="dropdown" href="#"
             aria-expanded="false"
             data-animation="scale-up" role="button">
            <span class="avatar avatar-online">
                <div id="img-profile">
              <c:if test="${userLogin.s3Url == null && userLogin.dataImage == null}">
                <c:set var="image_avatar" value="/assets/images/no-avatar-big.png"/>
              </c:if>
            <c:if test="${userLogin.s3Url != null}">
              <c:set var="image_avatar" value="${userLogin.s3Url}"/>
            </c:if>
                 <c:if test="${userLogin.dataImage != null}">
                   <c:set var="image_avatar" value="${userLogin.dataImage}"/>
                 </c:if>
            <img src="${image_avatar}" alt="..." style="height:30px"></div>
            <i></i></span> <sec:authentication
              property="principal.username"/>
          </a>
          <div class="dropdown-menu" role="menu">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/account/info" role="menuitem">
              <i class="fa fa-address-card" aria-hidden="true"></i>
              <spring:message code="menu.account.info"></spring:message></a>

            <c:if test="${isPMSaccount == false}">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/account/store/info" role="menuitem">
              <i class="fa fa-address-card" aria-hidden="true"></i>
              <spring:message code="account.store.infor"></spring:message></a>

            <a class="dropdown-item" href="${pageContext.request.contextPath}/account/parent/info" role="menuitem">
              <i class="fa fa-address-card" aria-hidden="true"></i>
              <spring:message code="menu.account.parent.info"></spring:message></a>
            </c:if>
            <%--Payment security--%>
            <c:if test="${'true' eq MENU_ITEM_SHOW_SETTING_SECURITY}">
              <form method="post" action="/system/payment-security" class="hidden">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button class="hidden" id="form_payment_security_submit"></button>
              </form>
              <a class="dropdown-item" href="javascript:void(0)" onclick="formSubmit('form_payment_security_submit')" role="menuitem">
                <i class="site-menu-icon wb-settings" aria-hidden="true"></i>
                <span class="site-menu-title"><spring:message code="menu.setting.secutiry"/></span>
              </a>
            </c:if>
            <%--end payment security--%>

            <a class="dropdown-item" href="/customer/password-required" role="menuitem">
              <i class="icon wb-lock" aria-hidden="true"></i> <spring:message
                code="changepass.nav.change.pass"></spring:message></a>
            <a class="dropdown-item" href="/logout" role="menuitem">
              <i class="icon wb-power" aria-hidden="true"></i> <spring:message
                code="login.sign.out"></spring:message></a>
          </div>
        </li>
      </ul>
    </div>
    <%--<div class="collapse navbar-search-overlap" id="site-navbar-search">--%>
    <%--<form role="search">--%>
    <%--<div class="form-group">--%>
    <%--<div class="input-search"><i class="input-search-icon wb-search"--%>
    <%--aria-hidden="true"></i>--%>
    <%--<input type="text" class="form-control" name="site-search"--%>
    <%--placeholder="Search...">--%>
    <%--<button type="button" class="input-search-close icon wb-close"--%>
    <%--data-target="#site-navbar-search" data-toggle="collapse"--%>
    <%--aria-label="Close"></button>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
    <%--</form>--%>
    <%--</div>--%>
  </div>
  <script>
    $(document).ready(function () {
      changeLocale();
      loadProgress();
    });

    function changeLocale() {
      var searchUrl = window.location.href;
      if (searchUrl.indexOf('?') > -1) {
        if (searchUrl.indexOf('lang=en') > -1) {
          $("#langEn").attr("href", searchUrl);
          $("#langVi").attr("href", searchUrl.replace('lang=en', "lang=vi"));
        } else if (searchUrl.indexOf('lang=vi') > -1) {
          $("#langEn").attr("href", searchUrl.replace('lang=vi', "lang=en"));
          $("#langVi").attr("href", searchUrl);
        } else {
          $("#langEn").attr("href", searchUrl + '&lang=en');
          $("#langVi").attr("href", searchUrl + '&lang=vi');
        }
      } else {
        $("#langEn").attr("href", searchUrl + '?lang=en');
        $("#langVi").attr("href", searchUrl + '?lang=vi');
      }
    };

    function changeLanguage() {
      jQuery('#change_lang_form').submit();
    }

    function loadProgress() {
      $('#loader').hide();
      $('form').submit(function () {
        $('#loader').show();
        var $progress = $('.progress');
        var $progressBar = $('.progress-bar');
        $('.progress .progress-bar').css("width", function () {
              return $(this).attr("aria-valuenow") + "%";
            }
        );
      })
    };

    function formSubmit(submitId) {
      $("#".concat(submitId)).trigger('click');
    }
  </script>
</nav>