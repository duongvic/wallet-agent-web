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
  <jsp:param name="nav" value="pinCode"/>
</jsp:include>
<!-- /menu bar -->


<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="index.html"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><a href="quanlythe.html"><spring:message code="label.epin"/></a></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.epin"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-md-7">
        <div class="panel panel-bordered">
          <div class="panel-body py-10">
            <form class="form-horizontal" method="post" action="/fundin/topup/request">
              <div class="form-group row mb-10">
                <div class="full-width">
                  <h3 class="panel-title pl-0">Thông tin nạp tiền điện thoại</h3>
                  <div class="clr"></div>
                </div>
              </div>
              <div class="form-group row mb-10 pos-relative">
                <input type="text" class="form-control" name="phoneNumber" value="0902244419" />
                <label class="form-control-label px-0 label-control-right">Số điện thoại được nạp</label>
                <div class="price-radio mt-5">
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdt1" name="numberdt" checked="">
                    <label for="sdt1"><span>0902244419</span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdt2" name="numberdt">
                    <label for="sdt2"><span>0936660633</span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdt3" name="numberdt">
                    <label for="sdt3"><span>0989980816</span></label>
                  </div>
                </div>
              </div>
              <div class="form-group row mb-10 pos-relative">
                <input type="text" class="form-control" name="moneyNumber" value="" placeholder="Chọn mệnh giá" />
                <label class="form-control-label px-0 label-control-right"><spring:message code="money.transfer.fee"/>: 0 đ</label>
                <div class="price-radio mt-5">
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt1" name="numberdt2" checked="">
                    <label for="sdtt1"><span>10.000 d</span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt2" name="numberdt2">
                    <label for="sdtt2"><span>50.000d</span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt3" name="numberdt2">
                    <label for="sdtt3"><span>100.000d</span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="sdtt4" name="numberdt2">
                    <label for="sdtt4"><span>200.000d</span></label>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6 pr-0">
                  <div class="col-md-12">
                    <div class="form-group row mb-5">
                      <label class=" col-sm-6 col-form-label pb-0"><spring:message code="label.discount"/>: </label>
                      <div class=" col-sm-6  text-right">
                        <p class="form-control-plaintext pb-0">100,000đ</p>
                      </div>
                    </div>
                    <div class="form-group row mb-5">
                      <label class=" col-sm-6 col-form-label pb-0"><spring:message code="label.amount"/>: </label>
                      <div class=" col-sm-6 text-right">
                        <p class="form-control-plaintext pb-0">5% </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit" class="btn btn-primary btn-sm pull-right"> <spring:message code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i></button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <!-- Giao dịch gần nhất -->
        <c:import url="../include_component/frame_last_transaction.jsp" />
        <!-- /Giao dịch gần nhất  -->
      </div>
    </div>
  </div>
</div>

<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

</html>