<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="static vn.mog.ewallet.consumer.web.controller.bank.BankController.CARD_CONTROLLER_INSERT" %>
<%@ page import="static vn.mog.ewallet.consumer.web.controller.bank.BankController.REDIRECT_CARD_MANAGER" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:url var="cardInfoUri" value="<%=CARD_CONTROLLER_INSERT%>"></c:url>
<c:url var="cardManagerURL" value="<%=REDIRECT_CARD_MANAGER%>"></c:url>
<c:if test="${lstCusBankDirect ==null}">
  <div class="col-sm-6 col-md-12">
    <div class="panel panel-bordered">
      <div class="panel-heading">
        <h3 class="panel-title"><spring:message code="car.management"/></h3>
      </div>
      <div class="panel-body text-center pt-0">
        <img style="max-width: 50%;" src="/assets/images/qlythe.png" class="text-center overlay-figure">
        <p class="fs13"><spring:message code ="car.no.acount"/></p>
        <a href="/bank/link-bank-account" class="btn btn-primary btn-sm"><spring:message code ="car.add"/><i
            class="icon wb-arrow-right ml-10"></i></a>
      </div>
    </div>
  </div>
</c:if>

<c:if test="${lstCusBankDirect !=null}">
  <div class="col-sm-6 col-md-12">
    <div class="panel panel-bordered">
      <div class="panel-heading">
        <div class="panel-title pr-3">
          <b><spring:message code="car.management"/></b>
          <%--<div class="pull-right">--%>
            <%--<a class="carousel-control-prev carousel-nav-bottom" href="#exampleCarouselDefault"--%>
               <%--role="button" data-slide="prev">--%>
              <%--<span class="carousel-control-prev-icon wb-chevron-left"--%>
                    <%--aria-hidden="true"></span><span class="sr-only">Previous</span>--%>
            <%--</a>--%>
            <%--<a class="carousel-control-next carousel-nav-bottom" href="#exampleCarouselDefault"--%>
               <%--role="button" data-slide="next">--%>
              <%--<span class="carousel-control-next-icon wb-chevron-right"--%>
                    <%--aria-hidden="true"></span><span class="sr-only">Next</span>--%>
            <%--</a>--%>
          <%--</div>--%>
        </div>
      </div>



      <%--<a href="#"><img src="/assets/images/c-visa.png"--%>
                       <%--class="text-center overlay-figure px-10 pt-10"></a>--%>

      <div class="panel-body text-center pt-6">
        <c:forEach var="item" items="${lstCusBankDirect}">
          <%--<form method="post" action="/bank/manage" class="hidden">--%>
            <%--<input type="hidden" name="${_csrf.parameterName}"--%>
                   <%--value="${_csrf.token}"/>--%>
            <%--<button class="hidden" id="form_linkBankManage_submit"></button>--%>
          <%--</form>--%>
          <%--<a onclick="formSubmit('form_linkBankManage_submit')">--%>
            <%--<div class="card-id mb-10" style="width: 100%; min-height: 43px; border-radius: 0;">--%>
              <%--<div class="name-date mt-10">--%>
                <%--<div class="logo-card-l"><img alt="${item.bankCode}"--%>
                                              <%--src="/assets/images/bank/${item.bankCode}.png">--%>
                <%--</div>--%>
                <%--<div class="date card-number-input">${item.bankAccountNumber}</div>--%>
              <%--</div>--%>
            <%--</div>--%>
          <%--</a>--%>
          <a href="/bank/manage">
            <div class="card-id mb-10" style="width: 100%; min-height: 43px; border-radius: 0;">
              <div class="name-date mt-10">
                <div class="logo-card-l"><img alt="${item.bankCode}"
                                              src="/assets/images/bank/${item.bankCode}.png">
                </div>
                <div class="date card-number-input">${item.bankAccountNumber}</div>
              </div>
            </div>
          </a>
        </c:forEach>
      </div>
    </div>
  </div>
</c:if>