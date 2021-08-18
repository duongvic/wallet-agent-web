<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="account.bank.card.add.page"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"></jsp:include>
<!-- /menu bar -->

<div class="page page-email">
  <div class="page-header">
    <div class="page-header-actions"></div>
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
          code="dashboard.home"/></a></li>
      <li class="breadcrumb-item"><a href="/bank/manage"><spring:message
          code="label.link.bank"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="account.bank.link.new"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="account.bank.link.new"/></h1></div>
  <div class="page-content">
    <div class="panel">
      <div class="panel-body">
        <div class="mb-20"><img alt="${bankCode}" src="/assets/images/bank/${bankCode}.png"
                                width="100" class="br3 pull-left">
          <h4 class="pull-left mt-15 ml-20" name="bankDisplayName">${bankDisplayName}</h4>
          <div class="clr"></div>
        </div>
        <form id="form-link-bank" method="post" action="/bank/link-bank-insert-confirm">
          <div class="form-group row">
            <label class="col"><spring:message code="account.bank.link.form"/></label>
          </div>
          <div class="form-group row">
            <div class="col offset-3">
              <button id="btCard"
                      class="${param._nameBank eq 'SGTTVNVX' || param._nameBank eq 'ICBVVNVX' ? 'btn-link-form btn-link-form-current' : 'btn-link-form'}"
                      onclick="changeActionButton('btCard','${bankCode}')">
                Thẻ
              </button>
              <c:if test="${param._nameBank ne 'ICBVVNVX'}">
                <button id="btAccount"
                        class="${param._nameBank eq 'BIDVVNVX' ? 'btn-link-form btn-link-form-current' : 'btn-link-form'}" ${param._nameBank ne 'BIDVVNVX' ? 'disabled' : ''}
                        onclick="changeActionButton('btAccount','${bankCode}')">
                  <spring:message code="account.bank.account"/>
                </button>
              </c:if>
              <%--<button class="btn-link-form"><spring:message--%>
                  <%--code="account.bank.customer.code"/></button>--%>
              <%--<button class="btn-link-form">iBanking</button>--%>
            </div>
          </div>

          <c:if test="${codeErr != null}">
            <div class="form-group row">
              <div class="col-sm-4 col-md-3">
                <label class="form-control-label"></label>
              </div>
              <div class="col-sm-8 col-md-9">
                <div class="text-danger error-message mb-10">
                  <small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}</small>
                </div>
              </div>
            </div>
          </c:if>

          <div class="erroAccountNumber"></div>

          <%--thẻ--%>
          <%--<c:if--%>
          <%--test="${param._nameBank !=null && (param._nameBank eq 'SGTTVNVX'  || param._nameBank eq 'ICBVVNVX') || param._nameBank eq 'BIDVVNVX'}">--%>
          <div class="linkItemCard">
            <div class="form-group row">
              <div class="col-sm-4 col-md-3">
                <label class="form-control-label"><spring:message
                    code="account.bank.card.holder"/>:</label>
              </div>
              <div class="col-sm-8 col-md-9">
                <input type="text" disabled class="form-control ctk" id="fullNameCard"
                       name="fullName"
                       value="<sec:authentication property="principal.fullName" />"/>
              </div>
            </div>
            <div class="form-group row">
              <div class="col-sm-4 col-md-3">
                <label class="form-control-label" for="soThe"><spring:message
                    code="account.bank.card.number"/>&nbsp;<strong style="color: red">*</strong> :</label>
              </div>
              <div class="col-sm-8 col-md-9">
                <input type="text" class="form-control card-number-input-nm" id="soThe"
                       value="${accountNumber}" required/>
              </div>
            </div>
            <div id="cardIssueDateForm" class="form-group row mb-10">
              <div class="col-sm-4 col-md-3">
                <label><spring:message code="account.bank.card.effective"/>&nbsp;<strong style="color: red">*</strong> :</label>
              </div>
              <div class="col-sm-8 col-md-9">
                <div class="row col-sm-12 col-md-12">
                  <input type="text" id="cardIssueDate1"
                         required class="form-control text-center" style="max-width: 50px;">
                  <span style="margin-left: 6px; margin-right: 6px; font-size: 20px">⁄</span>
                  <input type="text" class="form-control text-center" id="cardIssueDate2" required
                         style="max-width: 50px;">
                </div>
              </div>
            </div>
          </div>
          <%--</c:if>--%>
          <%--end thẻ--%>

          <%--account bank--%>
          <%--<c:if test="${param._nameBank !=null && param._nameBank eq 'BIDVVNVX'}">--%>
          <div class="linkAccount">
            <div class="form-group row">
              <div class="col-sm-4 col-md-3">
                <label class="form-control-label"><spring:message
                    code="account.bank.holder"/>:</label>
              </div>
              <div class="col-sm-8 col-md-9">
                <input type="text" disabled class="form-control ctk" id="fullNameAccount"
                       name="fullName"
                       value="<sec:authentication property="principal.fullName"></sec:authentication>"/>
              </div>
            </div>
            <div class="form-group row">
              <div class="col-sm-4 col-md-3">
                <label class="form-control-label" for="soTaiKhoan"><spring:message
                    code="account.bank.number"/>&nbsp;<strong style="color: red">*</strong>:</label>
              </div>
              <div class="col-sm-8 col-md-9">
                <input type="text" class="form-control card-number-input" id="soTaiKhoan"
                       value="${accountNumber}" required/>
              </div>
            </div>
            <div class="form-group row">
              <div class="col-sm-4 col-md-3">
                <label class="form-control-label" for="chiNhanh"><spring:message
                    code="account.bank.branch"/>:</label>
              </div>
              <div class="col-sm-8 col-md-9">
                <input type="text" class="form-control" id="chiNhanh"
                       name="branchBank" value="${branchBank}">
              </div>
            </div>
          </div>
          <%--</c:if>--%>
          <%--end account bank--%>

          <c:choose>
            <c:when test="${param._nameBank !=null && param._nameBank ne 'TPBank'}">
              <div class="text-right">
                <a href="/bank/manage" class="btn btn-default mr-10"><spring:message
                    code="common.btn.back"/></a>
                <button type="button" class="btn btn-primary" onclick="getCardIssueDate()">
                  <spring:message code="button.next"/>
                </button>
              </div>
            </c:when>
            <c:otherwise>
              <div class="text-right">
                <a href="https://ebank.tpb.vn/retail/v8/" class="btn btn-default mr-10">Liên kết
                  ngay</a>
              </div>
            </c:otherwise>

          </c:choose>


          <input type="hidden" id="hidden_phoneNumber" name="phoneNumber"
                 value="${(userLogin.phoneNumber)}"/>
          <input type="hidden" id="hidden_fullName" name="fullName" value="${fullName}"/>

          <input type="hidden" id="hidden_bankCode" name="bankCode" value="${bankCode}"/>

          <input type="hidden" id="hidden_branchBank" name="branchBank" value="${branchBank}"/>

          <input type="hidden" id="hidden_accountNumber" name="accountNumber"
                 value="${accountNumber}"/>

          <input type="hidden" id="hidden_cardIssueDate" name="cardIssueDate"
                 value="${cardIssueDate != null || cardIssueDate != '/' ? cardIssueDate : ''}">

          <input type="hidden" id="hidden_ssn" name="ssn" value="${ssn}">

          <input type="hidden" id="hidden_linkType" name="linkType" value="${linkType}">

          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        </form>
      </div>
    </div>
  </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->

<%--import dialog--%>
<c:import url="../dialog_modal/link_bank/register_infor.jsp"/>
<%--import dialog--%>

<spring:message code="error.account.number" var="errorAccountNumber"/>
<spring:message code="error.bank.card.number" var="errorBankCardNumber"/>
<spring:message code="message.card.issue.date" var="errorCardIssueDate"/>
</body>

<script>
  $(document).ready(function () {
    $('#cardIssueDate1').on('input', function () {
      var val = $(this).val();
      if (val !== null && val !== undefined && val !== '') {
        if (val.length > 2) {
          val = val.substr(0, 2);
        }
        if (val !== '' && isNaN(parseInt(val)) || parseInt(val) > 12) {
          val = 12;
        }

        $(this).val(val);
      }
    });

    $('#cardIssueDate2').on('input', function () {
      var val = $(this).val();
      if (val !== null && val !== undefined && val !== '') {
        if (val.length > 2) {
          val = val.substr(0, 2);
        }

        $(this).val(val);
      }
    });

    $(".btn-link-form").click(function (e) {
      e.preventDefault();
      //thêm class khi thẻ active
      $('.btn-link-form').removeClass('btn-link-form-current');
      $(this).closest('.btn-link-form').addClass('btn-link-form-current');
    });

    var tagBankCode = $('#hidden_bankCode');
    var bankCode = tagBankCode.get(0).value;
    var tagBtCurrent = $('.btn-link-form-current');
    var btCurrent = tagBtCurrent.get(0).id;
    changeActionButton(btCurrent, bankCode);

  });

  function getCardIssueDate() {
    if (document.getElementById('cardIssueDate1').value !== null && document.getElementById(
        'cardIssueDate2').value !== null && document.getElementById('hidden_cardIssueDate')
        !== null) {
      document.getElementById('hidden_cardIssueDate').value
          = document.getElementById('cardIssueDate1').value + "/" + document.getElementById(
          'cardIssueDate2').value
    }
    if (document.getElementById('fullNameCard') !== null && document.getElementById(
        'hidden_fullName') !== null) {
      var _fullNameCard = document.getElementById('fullNameCard').value;
      document.getElementById("hidden_fullName").value = _fullNameCard;
    }
    if (document.getElementById('fullNameAccount') !== null && document.getElementById(
        'hidden_fullName') !== null) {
      var _fullNameAccount = document.getElementById('fullNameAccount').value;
      document.getElementById("hidden_fullName").value = _fullNameAccount;
    }
    if (document.getElementById('chiNhanh').value !== null && document.getElementById(
        'hidden_branchBank') !== null) {
      var _chiNhanh = document.getElementById('chiNhanh').value;
      document.getElementById("hidden_branchBank").value = _chiNhanh;
    }
    checkBankVietTinPaymentOnline();
  }

  function checkBankVietTinPaymentOnline() {
    var accountNumber;
    var cardIssueDate = '';
    var fullName;
    var phoneNumber;
    var bankCode;
    var branchBank;
    var linkType;

    if (document.getElementById('soThe').value !== null && document.getElementById('soThe').value
        !== "") {
      accountNumber = document.getElementById('soThe').value;
      document.getElementById('hidden_accountNumber').value = accountNumber;
    } else {
      accountNumber = document.getElementById('soTaiKhoan').value;
      document.getElementById('hidden_accountNumber').value = accountNumber;
    }
    if (document.getElementById('hidden_fullName') !== null) {
      fullName = document.getElementById("hidden_fullName").value;
    }
    if (document.getElementById('hidden_phoneNumber') !== null) {
      phoneNumber = document.getElementById("hidden_phoneNumber").value;
    }
    if (document.getElementById('hidden_cardIssueDate') !== null) {
      cardIssueDate = document.getElementById('hidden_cardIssueDate').value
    }
    if (document.getElementById('hidden_bankCode') !== null) {
      bankCode = document.getElementById('hidden_bankCode').value
    }
    if (document.getElementById('hidden_branchBank') !== null) {
      branchBank = document.getElementById('hidden_branchBank').value
    }

    linkType = document.getElementById('hidden_linkType').value

    if(linkType=="CARD" && cardIssueDate== "/"){
      var $newAccountError= $("<div id=\"new-account-error\" class=\"form-group row\">\n"
          + "              <div class=\"col-sm-4 col-md-3\">\n"
          + "                <label class=\"form-control-label\"></label>\n"
          + "              </div>\n"
          + "              <div class=\"col-sm-8 col-md-9\">\n"
          + "                <div class=\"text-danger error-message mb-10\">\n"
          + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;" + (linkType === 'ACCOUNT' ? "${errorCardIssueDate}" : "${errorCardIssueDate}") + " </small>\n"
          + "                </div>\n"
          + "              </div>\n"
          + "            </div>")
      // $('.d').after($('.c').text($newAccountError));
      // $newAccountError.insertAfter($('.erroAccountNumber'));

      $('.erroAccountNumber').html($newAccountError);
    }
    else{
      if (accountNumber !== null && accountNumber != '') {
        var formData = {
          "${_csrf.parameterName}": "${_csrf.token}",
          accountNumber: accountNumber,
          fullName: fullName,
          phoneNumber: phoneNumber,
          cardIssueDate: cardIssueDate,
          bankCode: bankCode,
          branchBank: branchBank,
          linkType: linkType
        };

        $.ajax({
          type: "GET",
          contentType: "application/json;charset=utf-8",
          dataType: "json",
          url: "/ajax-controller/check-bank-payment-online",
          beforeSend: function (xhr) {
            if ("${_csrf.parameterName}" && "${_csrf.token}") {
              xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
            }
          },
          data: formData,
          timeout: 60000,
          success: function (data) {
            if (data.value) {
              $('#modalRegisterInfo').modal('show');
            } else {
              $('#form-link-bank').submit();
            }
          },
          error: function (e) {
          }
        });
      }
      else {
        var $newAccountError= $("<div id=\"new-account-error\" class=\"form-group row\">\n"
            + "              <div class=\"col-sm-4 col-md-3\">\n"
            + "                <label class=\"form-control-label\"></label>\n"
            + "              </div>\n"
            + "              <div class=\"col-sm-8 col-md-9\">\n"
            + "                <div class=\"text-danger error-message mb-10\">\n"
            + "                  <small><i class=\"fa fa-times-circle\"></i>&nbsp;" + (linkType === 'ACCOUNT' ? "${errorAccountNumber}" : "${errorBankCardNumber}") + " </small>\n"
            + "                </div>\n"
            + "              </div>\n"
            + "            </div>")
        // $('.d').after($('.c').text($newAccountError));
        // $newAccountError.insertAfter($('.erroAccountNumber'));

        $('.erroAccountNumber').html($newAccountError);
      }
    }

  }

  function changeActionButton(btCurrent, bankCode) {
    if ("btCard" === btCurrent) {
      $(".linkAccount").addClass("hidden");
      $(".linkItemCard").removeClass("hidden");
      $('input[name=linkType]').val('CARD');
      $('#new-account-error').remove();
    }
    if ("btAccount" === btCurrent) {
      $(".linkItemCard").addClass("hidden");
      $(".linkAccount").removeClass("hidden");
      $('input[name=linkType]').val('ACCOUNT');
      $('#new-account-error').remove();
    }

    // if ("BIDVVNVX" !== bankCode) {
    //
    // }
  }
</script>

</html>