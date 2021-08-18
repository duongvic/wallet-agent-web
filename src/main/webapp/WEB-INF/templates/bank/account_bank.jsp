<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <title><spring:message code="account.bank.title.form"/> - <spring:message code="common.company"/></title>
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
    <jsp:param name="nav" value="themtaikhoan"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
    <div class="page-header">
        <%--<div class="page-header-actions"><a href="/bank/manage">Hủy</a></div>--%>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item"><a href="/bank/manage"><spring:message
                    code="card.manage.label"/></a></li>
            <li class="breadcrumb-item"><a href="/bank/manage"><spring:message
                    code="account.bank.account"/></a></li>
            <li class="breadcrumb-item active"><spring:message code="common.btn.add"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="account.bank.title.form"/></h1>
    </div>
    <div class="page-content container-fluid">
        <div class="panel">
            <div class="panel-heading">
                <h3 class="panel-title"><spring:message code="label.link.bank"/></h3>
            </div>
            <div class="panel-body">
                <c:if test="${(messageError != null)}">
                    <a href="/bank/manage">
                        <small class="text-danger error-message"><i
                                class="fa fa-times-circle"></i>&nbsp;${messageError}</small>
                    </a>
                </c:if>
                <div class="row">
                    <c:forEach items="${listBank}" var="item">
                        <div class="mb-10 item-card" onclick="getValueBank(this)"
                             id="${item.bankCode}">
                            <span class="phoneCharge-choose">
                  <label>
                    <img alt="${item.bankName}" src="/assets/images/bank/${item.bankCode}.png">
                     <p class="hidden" name="bankCode" id="bankCode">${item.bankCode}</p>
                    <input type="hidden" id="hidden_bankCode" name="bankCode" value="${bankCode}">
                  </label>
                </span>
                        </div>

                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="panel">
            <div class="panel-body">
                <div class="row mb-10">
                    <p>
                    </p>
                </div>
                <div class="form-group row">
                    <img src="/assets/images/radioCheck.png" style="max-height: 18px">
                    <label class="col"><spring:message code="account.bank.links.only.once.ewallet"/></label>
                </div>
                <div class="form-group row">
                    <img src="/assets/images/radioCheck.png" style="max-height: 18px">
                    <label class="col"><spring:message code="account.topup.free.link.bank"/></label>
                </div>
                <div class="form-group row">
                    <img src="/assets/images/radioCheck.png" style="max-height: 18px">
                    <label class="col"><spring:message code="account.info.sec.safe"/></label>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="modal fade modal-fall" id="examplePositionCenter" aria-hidden="true"
     aria-labelledby="examplePositionCenter" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-simple modal-center">
        <div class="modal-content bg-0">
            <div class="modal-header">
                <button type="button" class="close close-bg-0" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true" class=""><i class="icon pe-close"></i></span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-6 col-md-6">
                        <div class="panel panel-bordered">
                            <div class="panel-heading text-center">
                                <h3 class="panel-title"><spring:message
                                        code="label.bank.account"/></h3></div>
                            <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
                            <div class="panel-body text-center pt-0">
                                <button type="button" class="btn btn-primary btn-sm"><spring:message
                                        code="common.btn.add"/><i
                                        class="icon wb-arrow-right ml-10"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <div class="panel panel-bordered">
                            <div class="panel-heading text-center">
                                <h3 class="panel-title"><spring:message
                                        code="label.bank.card"/></h3></div>
                            <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
                            <div class="panel-body text-center pt-0">
                                <button type="button" class="btn btn-primary btn-sm"><spring:message
                                        code="common.btn.add"/><i
                                        class="icon wb-arrow-right ml-10"></i></button>
                            </div>
                        </div>
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
  function getValueBank(obj) {
    var x = obj;
    var bankCode;
    var bankName;
    var bankDisplayName;
    var children = x.getElementsByTagName('p');// any tag could be used here..
    for (var i = 0; i < children.length; i++) {
      if (children[i].getAttribute('id') == 'bankCode') // any attribute could be used here
      {
        bankCode = children[i].innerHTML;
        var _nameBank = document.getElementById('hidden_bankCode').value = bankCode;
        console.log(_nameBank);
        if (bankCode == "TPBank") {
          window.location.href = 'https://ebank.tpb.vn/retail/v8/';
        }
        else {
          window.location.href = '/bank/link-bank-insert?_nameBank=' + _nameBank;
        }
      }
    }
  };
  //end
</script>
</html>