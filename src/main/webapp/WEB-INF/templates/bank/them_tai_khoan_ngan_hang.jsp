<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
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
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage"><spring:message code="card.manage.label"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage"><spring:message code="account.bank.account"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="common.btn.add"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="account.bank.title.form"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="panel">
      <div class="panel-heading">
        <h3 class="panel-title"><spring:message code="fundout.bank.linked"/> </h3>
      </div>
      <div class="panel-body">
        <table>
          <c:if test="${(messageError != null)}">
            <tr>
              <a href="/bank/manage">
                <small class="text-danger error-message"><i
                    class="fa fa-times-circle"></i>&nbsp;${messageError}</small>
              </a>

            </tr>
          </c:if>

          <tr>
            <c:forEach items="${listBank}" var="item">
              <td>
                <div class="row col">
                  <div class="mb-10 item-bank" onclick="getValueBank(this)"
                       style="width: 100% !IMPORTANT;"
                       id="${item.bankCode}">
                      <%--<a href="/bank/themTheAtm?_nameBank=${item.bankCode}">--%>
                      <%--<a href="#" onclick="getURL(this)">--%>
                    <span class="phoneCharge-choose">
                  <label>
                    <img alt="${item.bankName}" src="/assets/images/bank/${item.bankCode}.png">
                     <p class="hidden" name="bankCode" id="bankCode">${item.bankCode}</p>
                    <input type="hidden" id="hidden_bankCode" name="bankCode" value="${bankCode}">
                  </label>
                </span>
                      <%--</a>--%>
                  </div>
                </div>
              </td>
            </c:forEach>
          </tr>
        </table>
      </div>
    </div>

    <div class="panel">
      <div class="panel-body">
        <div class="row mb-10">
          <p>Theo yêu cầu của Ngân hàng nhà nước, để sử dụng các dich vụ của Ví điện tử, bạn cần
            thực hiện liên kết thẻ ngân hàng
          </p>
        </div>
        <div class="form-group row">
          <img src="/assets/images/radioCheck.png" style="max-height: 18px">
          <label class="col">Liên kết một lần duy nhất để định danh tài khoản Ví điện
            tử
          </label>
        </div>
        <div class="form-group row">
          <img src="/assets/images/radioCheck.png" style="max-height: 18px">
          <label class="col">Nạp tiền miễn phí từ Ngân hàng liên kết

          </label>
        </div>
        <div class="form-group row">
          <img src="/assets/images/radioCheck.png" style="max-height: 18px">
          <label class="col">Thông tin được bảo mật an toàn tuyệt đối</label>
        </div>
      </div>

    </div>


    <div class="panel hidden">
      <div class="panel-heading">
        <h3 class="panel-title">Ngân hàng nội địa</h3>
      </div>
      <div class="panel-body">
        <div class="row">
          <nav>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheAtm"><img src="/assets/images/placeholder200x120.png"
                                            class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
          </nav>
        </div>
      </div>
    </div>
    <div class="panel hidden">
      <div class="panel-heading">
        <h3 class="panel-title">Ngân hàng quốc tế</h3></div>
      <div class="panel-body">
        <div class="row">
          <nav>
            <a href="/bank/themTheVisa"><img src="/assets/images/placeholder200x120.png"
                                             class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheVisa"><img src="/assets/images/placeholder200x120.png"
                                             class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheVisa"><img src="/assets/images/placeholder200x120.png"
                                             class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
            <a href="/bank/themTheVisa"><img src="/assets/images/placeholder200x120.png"
                                             class="br3 mr-5 ml-10 mb-5 mt-5" width="110"></a>
          </nav>
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
        <button type="button" class="close close-bg-0" data-dismiss="modal" aria-label="Close"><span
            aria-hidden="true" class=""><i class="icon pe-close"></i></span></button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-sm-6 col-md-6">
            <div class="panel panel-bordered">
              <div class="panel-heading text-center">
                <h3 class="panel-title"><spring:message code="label.bank.account"/></h3></div>
              <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
              <div class="panel-body text-center pt-0">
                <button type="button" class="btn btn-primary btn-sm"> <spring:message code="common.btn.add"/><i
                    class="icon wb-arrow-right ml-10"></i></button>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-md-6">
            <div class="panel panel-bordered">
              <div class="panel-heading text-center">
                <h3 class="panel-title"><spring:message code="label.bank.card"/></h3></div>
              <img src="/assets/images/qlythe.png" class="text-center overlay-figure">
              <div class="panel-body text-center pt-0">
                <button type="button" class="btn btn-primary btn-sm"> <spring:message code="common.btn.add"/><i
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
          window.location.href = '/bank/themTheAtm?_nameBank=' + _nameBank;
        }
      }
    }
  };
  //end
</script>
</html>