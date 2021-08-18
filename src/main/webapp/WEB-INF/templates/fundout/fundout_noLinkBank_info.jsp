<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.fundout.FundOutNoLinkBankController.FUNDOUT_NOLINKBANK_CONTROLLER" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:url var="fundOutControllerURI" value="<%=FUNDOUT_NOLINKBANK_CONTROLLER%>"></c:url>

<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">
<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <title>Rút tiền không link bank - <spring:message code="common.company"/></title>
    <!-- head libs  -->
    <jsp:include page="../include_page/head.jsp"/>
    <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="foNoLinkbank"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><a href="/fundout"><spring:message
                    code="label.fundout"/> </a> / <a href="#"><spring:message
                    code="lable.fund.out.no.linkBank"/> </a></li>
        </ol>
        <h1 class="page-title"><spring:message code="lable.fund.out.no.linkBank"/></h1>
    </div>
    <div class="page-content container-fluid">
        <div class="mt-30">
            <div class="row">
                <div class="col-md-7">
                    <div class="panel panel-bordered">
                        <div class="panel-body py-10">
                            <form class="form-horizontal" name="foNoLinkBankInfo" method="post"
                                  action="/fundout/no-link-bank/verify">
                                <div class="form-group row mb-10">
                                    <c:if test="${(codeErr != null && codeErr !='Success')}">
                                        <small class="text-danger error-message"><i
                                                class="fa fa-times-circle"></i>&nbsp;${codeErr}
                                        </small>
                                    </c:if>
                                    <div class="full-width">
                                        <img alt="${bankCode}"
                                             src="/assets/images/bank/${_nameBank}.png" align="left"
                                             width="100"
                                             style="margin-right:10px">
                                        <div class="text-left">
                                            <label>${bankDisplayName}</label>
                                            <br>

                                            <input type="hidden" id="bankName" name="bankName"
                                                   value="${bankName}">
                                            <input type="hidden" id="bankDisplayName"
                                                   name="bankDisplayName"
                                                   value="${bankDisplayName}">
                                        </div>
                                        <div class="clr"></div>
                                    </div>
                                </div>
                                <div class="form-group row mb-10">
                                    <div class="col-sm-4 checkbox-custom checkbox-default checkbox-inline">
                                        <input type="checkbox" class="custom-control-input"
                                               id="transferTypeCard"
                                               name="transferType"
                                               value="CARD" ${(transferType == null || transferType eq '' || 'CARD' eq transferType) ? 'checked' : ''}
                                               onclick="changeTransferType('CARD', '${bankCode}')"/>
                                        <label for="transferTypeCard"><spring:message
                                                code="label.pull.out.the.card"/>
                                        </label>
                                    </div>
                                    <div class="col-sm-4 checkbox-custom checkbox-default checkbox-inline">
                                        <input type="checkbox" class="custom-control-input"
                                               id="transferTypeAccount"
                                               name="transferType"
                                               value="ACCOUNT" ${'ACCOUNT' eq transferType ? 'checked' : ''}
                                               onclick="changeTransferType('ACCOUNT', '${bankCode}')"/>
                                        <label for="transferTypeAccount"><spring:message
                                                code="label.withdrawal.account"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group row mb-10">
                                    <input type="text" class="form-control ctk"
                                           placeholder="<spring:message code="account.bank.holder"/>/<spring:message code="account.bank.card.holder"/>"
                                           id="bankHolderName" name="bankHolderName"
                                           value="<sec:authentication property="principal.fullName"></sec:authentication>"
                                           readonly required>
                                </div>
                                <div class="form-group row mb-10 pos-relative">
                                    <input type="text" class="col-md-6 form-control"
                                           placeholder="<spring:message code="account.bank.number"/>/<spring:message code="account.card.number"/>"
                                           id="_soTaiKhoan" name="_soTaiKhoan"
                                           value="${_soTaiKhoan}" required/>
                                    <div id="cardIssueDateForm"
                                         class="col-md-6 form-group row mb-10 ${transferType eq 'ACCOUNT' ? 'hidden' : ''}">
                                        <label class="form-control-label col">Ngày mở thẻ</label>
                                        <div class="">
                                            <input type="text" id="cardIssueDate1"
                                                   name="cardIssueDate1" value="${cardIssueDate1}"
                                                   required class="form-control text-center"
                                                   style="max-width: 50px;">
                                        </div>
                                        <div class="mr-3" style="margin-left: 3px">
                                            <span style="font-size: 20px">⁄</span>
                                        </div>
                                        <div class="">
                                            <input type="text" class="form-control text-center"
                                                   id="cardIssueDate2" name="cardIssueDate2" value="${cardIssueDate2}"
                                                   required style="max-width: 50px;">
                                        </div>
                                        <input type="hidden" id="hidden_cardIssueDate"
                                               name="cardIssueDate"
                                               value="${cardIssueDate}">
                                    </div>
                                    <%--<label--%>
                                    <%--class="form-control-label px-0 label-control-right">${userLogin.username}</label>--%>
                                </div>
                                <div class="form-group row mb-10 pos-relative">
                                    <div class="price-radio mt-5">

                                        <c:forEach begin="0" end="2" varStatus="itemId">
                                            <div class="radio-custom radio-info radio-inline text-center">
                                                <input type="radio" id="radio${itemId.index}"
                                                       name="_soTaiKhoan" class="hidden"
                                                       value="">
                                                <label for="radio${itemId.index}"><span
                                                        id="spanNumber${itemId.index}"
                                                        class="hidden"></span></label>
                                            </div>
                                        </c:forEach>

                                        <div class="radio-custom radio-info radio-inline text-center">
                                            <input type="radio" id="" name="">
                                            <label for=""><span onclick="openSaveBank()"
                                                                class="hidden">...</span></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row mb-10 pos-relative">
                                    <input type="text" class="form-control currency-input"
                                           id="amount" name="amount"
                                           value="${(amount != null && amount ne '') ? amount : '1000000'}"
                                           placeholder="<spring:message code="fundout.money"/>"
                                           required/>
                                    <label class="form-control-label px-0 label-control-right txt-note-pin vnd"><span
                                            id="feeAmount"><spring:message
                                            code="label.fee"/>: 0</span></label>
                                    <input type="hidden" name="feeAmount" id="hidden_feeAmount"
                                           value="${feeAmount}">

                                    <div class="price-radio mt-5">
                                        <div class="radio-custom radio-info radio-inline text-center">
                                            <input type="radio" id="sdtt1" name="numberdt2"
                                                   value="100000" ${amount eq '100000' ? 'checked=/"checked/"' : ''}
                                                   onclick="getSoTienRut(this)">
                                            <label for="sdtt1"><span>100.000 đ</span></label>
                                        </div>
                                        <div class="radio-custom radio-info radio-inline text-center">
                                            <input type="radio" id="sdtt2" name="numberdt2"
                                                   value="200000" ${amount eq '200000' ? 'checked=/"checked/"' : ''}
                                                   onclick="getSoTienRut(this)">
                                            <label for="sdtt2"><span>200.000 đ</span></label>
                                        </div>
                                        <div class="radio-custom radio-info radio-inline text-center">
                                            <input type="radio" id="sdtt3" name="numberdt2"
                                                   value="500000" ${amount eq '500000' ? 'checked=/"checked/"' : ''}
                                                   onclick="getSoTienRut(this)">
                                            <label for="sdtt3"><span>500.000 đ</span></label>
                                        </div>
                                        <div class="radio-custom radio-info radio-inline text-center">
                                            <input type="radio" id="sdtt4" name="numberdt2"
                                                   value="1000000" ${(amount eq null || amount eq '' || amount eq '1000000') ? 'checked=/"checked/"' : ''}
                                                   onclick="getSoTienRut(this)">
                                            <label for="sdtt4"><span>1.000.000 đ </span></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group row mb-10 pos-relative">
                                    <input type="text" class="form-control" name="description"
                                           value="${description}"
                                           placeholder="Lý do"/>
                                </div>

                                <div class="form-group row mb-10 hidden">
                                    <input type="text" class="form-control col-md-9"
                                           name="checkCode" value=""
                                           placeholder="<spring:message code="label.check.code"/>"/>
                                    <label class="form-control-label px-0 col-md-3 text-center"><b>ABCDF</b></label>
                                </div>

                                <div class="form-group row mb-0">
                                    <label class="form-control-label px-0"><spring:message
                                            code="label.amount"/>: <b class="text-amount"
                                                                      id="realAmount"></b></label>
                                    <input type="hidden" id="hidden_realAmount" name="realAmount"
                                           value="${realAmount}">
                                </div>

                                <div class="row mb-10">
                                    <%--<div class="form-group row col-lg-7 col-md-6 col-sm-12 hidden">--%>
                                    <%--<div class="myCheckBox">--%>
                                    <%--<input type="checkbox" value="checked" id="chkSaveInfoBank"--%>
                                    <%--name="chkSaveInfoBank" checked="checked">--%>
                                    <%--<label for="chkSaveInfoBank" style="margin-left: 0.8em;"></label>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-9 col-sm-7 mb-3" style="margin-left: 1em;">--%>
                                    <%--<label class="pull-left" for="chkSaveInfoBank">Lưu thông tin thẻ/tài khoản--%>
                                    <%--ngân hàng--%>
                                    <%--</label>--%>
                                    <%--</div>--%>
                                    <%--</div>--%>
                                    <div class="col-lg-12 col-md-12 col-sm-12 text-right inline-block">
                                        <a href="/fundout"
                                           class="btn btn-default mr-10"><spring:message
                                                code="common.btn.back"/></a>
                                        <button type="submit" class="btn btn-primary btn-sm mr-10"
                                                onclick="getCardIssueDate()"><spring:message
                                                code="label.fundout"/>
                                        </button>

                                    </div>
                                </div>
                                <input type="hidden" name="orderId" value="${orderId}"/>
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <!-- Giao dịch gần nhất -->
                    <c:import
                            url="../include_component/frame_last_transaction_fundOut_noLinkBank.jsp"/>
                    <!-- /Giao dịch gần nhất  -->
                </div>
            </div>
        </div>
    </div>
</div>


<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->

<c:import url="../dialog_modal/fundout_noLinkBank/dialog_saveBank.jsp"/>

<script src="/assets/js/babel-external-helpers.js"></script>
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.js"></script>
<script src="/assets/js/animsition.js"></script>
<script src="/assets/js/jquery.mousewheel.js"></script>
<script src="/assets/js/jquery-asScrollbar.js"></script>
<script src="/assets/js/jquery-asScrollable.js"></script>
<script src="/assets/js/jquery-asHoverScroll.js"></script>
<script src="/assets/js/switchery.js"></script>
<script src="/assets/js/intro.js"></script>
<script src="/assets/js/screenfull.js"></script>
<script src="/assets/js/jquery-slidePanel.js"></script>
<script src="/assets/js/jquery.dataTables.js"></script>
<script src="/assets/js/dataTables.bootstrap4.js"></script>
<script src="/assets/js/dataTables.fixedHeader.js"></script>
<script src="/assets/js/dataTables.fixedColumns.js"></script>
<script src="/assets/js/dataTables.rowGroup.js"></script>
<script src="/assets/js/dataTables.scroller.js"></script>
<script src="/assets/js/dataTables.responsive.js"></script>
<script src="/assets/js/responsive.bootstrap4.js"></script>
<script src="/assets/js/dataTables.buttons.js"></script>
<script src="/assets/js/buttons.html5.js"></script>
<script src="/assets/js/buttons.flash.js"></script>
<script src="/assets/js/buttons.print.js"></script>
<script src="/assets/js/buttons.colVis.js"></script>
<script src="/assets/js/buttons.bootstrap4.js"></script>
<script src="/assets/js/owl.carousel.js"></script>
<script src="/assets/js/slick.js"></script>
<script src="/assets/js/Component.js"></script>
<script src="/assets/js/Plugin.js"></script>
<script src="/assets/js/Base.js"></script>
<script src="/assets/js/Config.js"></script>
<script src="/assets/js/Menubar.js"></script>
<script src="/assets/js/GridMenu.js"></script>
<script src="/assets/js/Sidebar.js"></script>
<script src="/assets/js/PageAside.js"></script>
<script src="/assets/js/menu.js"></script>
<script src="/assets/js/colors.js"></script>
<script src="/assets/js/tour.js"></script>
<script src="/assets/js/Site.js"></script>
<script src="/assets/js/asscrollable.js"></script>
<script src="/assets/js/slidepanel.js"></script>
<script src="/assets/js/switchery.js"></script>
<script src="/assets/js/owl-carousel.js"></script>
<script src="/assets/js/carousel.js"></script>
<script src="/assets/js/datatables.js"></script>
<script src="/assets/js/datatable.js"></script>
<script src="/assets/development/js/my_format_currency.js"></script>

<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
      var currencyInput = $(".currency-input");
      currencyInput.val(formatCurrency(currencyToNumber(currencyInput.val())));

      var transferType = '${transferType}';
      var transferTypeCard = $("#transferTypeCard");
      var transferTypeAccount = $("#transferTypeAccount");
      if (transferType === 'CARD') {
        transferTypeCard.prop("checked", true);
        transferTypeAccount.prop("checked", false);
      } else {
        transferTypeCard.prop("checked", false);
        transferTypeAccount.prop("checked", true);
      }

      loadBankHistory(transferType, '${bankCode}');
      thanhToan();

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
    });

    /*Validate inpute number*/
    $('[id^=cardIssueDate1]').keypress(validateNumber);
    $('[id^=cardIssueDate2]').keypress(validateNumber);
    function validateNumber(event) {
      var key = window.event ? event.keyCode : event.which;
      if (event.keyCode === 8 || event.keyCode === 46) {
        return true;
      } else if (key < 48 || key > 57) {
        return false;
      } else {
        return true;
      }
    };
    /*end*/
  })(document, window, jQuery);

  function getSoTaiKhoan(elem) {
    var x = elem.value;
    document.getElementById('_soTaiKhoan').value = x;
  }

  function getSoTienRut(elem) {
    var x = elem.value;
    document.getElementById('amount').value = formatCurrency(x);
    thanhToan();
  }

  function thanhToan() {
    var realAmount = 0;
    var amount = 0;
    if (document.getElementById('amount').value === null) {
      document.getElementById('amount').value = 0;
    }
    amount = parseFloat(currencyToNumber(document.getElementById('amount').value));
    var feeAmount = parseFloat(currencyToNumber(document.getElementById('feeAmount').innerText));
    if (feeAmount > amount) {
      realAmount = 0;
    }
    else {
      realAmount = parseFloat(amount) + parseFloat(feeAmount);
    }

    document.getElementById('realAmount').innerText = formatCurrency(realAmount);
    //get value
    document.getElementById('hidden_realAmount').value = realAmount;
    document.getElementById('hidden_feeAmount').value = feeAmount;
  }

  $('input[type="checkbox"]').on('change', function () {
    $('input[name="' + this.name + '"]').not(this).prop('checked', false);
  });

  function showCardIssueDate(transferType) {
    if ("ACCOUNT" === transferType) {
      $("#cardIssueDateForm").addClass("hidden");
      $("#cardIssueDate1").attr("required", false);
      $("#cardIssueDate2").attr("required", false);
    } else {
      $("#cardIssueDateForm").removeClass("hidden");
      $("#cardIssueDate1").attr("required", true);
      $("#cardIssueDate2").attr("required", true);
    }
  }

  function getCardIssueDate() {
    document.getElementById('hidden_cardIssueDate').value = document.getElementById(
        'cardIssueDate1').value + "/" + document.getElementById('cardIssueDate2').value
  }

  function openSaveBank() {
    $('#modalSaveBank').modal('show')
  }

  function arrowClick(itemHolderName, itemNumber, transferType) {
    //$('#modalSaveBank').modal('hide');
    $("#bankHolderName").val(itemHolderName);
    $("#_soTaiKhoan").val(itemNumber);
    //if (transferType === "CARD") {
    //$("#transferTypeCard").prop("checked", true);
    //$("#transferTypeAccount").prop("checked", false);
    //} else {
    //$("#transferTypeCard").prop("checked", false);
    //$("#transferTypeAccount").prop("checked", true);
    //}
  }

  $("#amount").on("input", function () {
    thanhToan();
  });

  var listCardCached;
  var listAccountCached;

  function changeTransferType(transferType, bankCode) {
//      $("#bankHolderName").val("");
    $("#_soTaiKhoan").val("");
    loadBankHistory(transferType, bankCode);
  }

  function loadBankHistory(transferType, bankCode) {
    if ('CARD' === transferType && listCardCached === undefined
        || 'ACCOUNT' === transferType && listAccountCached === undefined) {
      $.ajax({
        type: "GET",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        async: false,
        url: "/ajax-controller/get-bank-item-by-customer",
        beforeSend: function (xhr) {
          if ("${_csrf.parameterName}" && "${_csrf.token}") {
            xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");
          }
        },
        data: {
          "${_csrf.parameterName}": "${_csrf.token}",
          transferType: transferType,
          bankCode: bankCode
        },
        dataType: 'json',
        timeout: 60000,
        success: function (data) {
          if ('CARD' === transferType) {
            listCardCached = data;
          } else {
            listAccountCached = data;
          }
          loadBankDataToModel(data);
        },
        error: function (e) {
          console.log("ERROR: ", e);
        }
      });
    } else {
      var data;
      if ('CARD' === transferType) {
        data = listCardCached;
      } else {
        data = listAccountCached;
      }
      loadBankDataToModel(data);
    }
    showCardIssueDate(transferType);
  }

  function loadBankDataToModel(data) {
    var valueExist = false;
    for (i = 0; i < 3; ++i) {
      if (data[i] === undefined) {
        $("#radio".concat(i)).addClass('hidden');
        $("#spanNumber".concat(i)).addClass('hidden');
      } else {
        $("#radio".concat(i)).removeClass('hidden');
        $("#spanNumber".concat(i)).removeClass('hidden');

        $("#radio".concat(i)).val(data[i].itemNumber);
        $("#spanNumber".concat(i)).html(data[i].itemNumber);

        if ($("#_soTaiKhoan").val() === $("#spanNumber".concat(i)).html()) {
          $("#radio".concat(i)).attr("checked", true);
          valueExist = true;
        } else {
          $("#radio".concat(i)).attr("checked", false);
        }

        setClickInLoop(i, data);
      }
    }
    if (!valueExist) {
//        $("#bankHolderName").val("");
      $("#_soTaiKhoan").val("");
    }
  }

  function setClickInLoop(index, data) {
    $("#radio".concat(index)).off('click').on('click', function () {
      arrowClick(data[index].itemHolderName, data[index].itemNumber, data[index].itemType);
    });
  }
</script>

</body>

</html>