<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>Cài đặt nạp nhanh - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<c:import url="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active">Cài đặt nạp nhanh</li>
    </ol>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-xxl-9 col-lg-9 col-md-8 col-sm-12">
        <div class="panel panel-bordered">
          <div class="panel-heading">
            <h3 class="panel-title">Cài đặt nạp tiền điện thoại nhanh</h3></div>
          <div class="panel-body py-10">
            <form class="form-horizontal">
              <div class="form-group row mb-10">
                <label class="col-md-3 form-control-label px-0"><spring:message code="fundin.payee.phone"/>: </label>
                <div class="col-md-9">
                  <div class="price-radio mt-5">
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="sdt1" name="numberdt" checked>
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
              </div>
              <div class="form-group row mb-10">
                <label class="col-md-3 form-control-label px-0"><spring:message code="label.face.value"/>: </label>
                <div class="col-md-9">
                  <div class="price-radio mt-5">
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="g50" name="price">
                      <label for="g50"><span>50.000đ</span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="g100" name="price" checked>
                      <label for="g100"><span>100.000đ</span></label>
                    </div>
                    <div class="radio-custom radio-info radio-inline text-center">
                      <input type="radio" id="g500" name="price">
                      <label for="g500"><span>200.000đ</span></label>
                    </div>
                  </div>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
          </div>
          <div class="panel-footer py-10">
            <div class="row">
              <div class="col-md-9 offset-md-3">
                <a href="#" class="btn btn-primary btn-sm pull-right"> Lưu </a>
                <a href="#" class="btn btn-default  btn-outline btn-sm pull-right mr-10"> <spring:message code="common.btn.back"/> </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xxl-3 col-lg-3 col-md-4 col-sm-12">

        <!-- News  -->
        <c:import url="../include_component/frame_news.jsp"/>
        <!-- /News  -->

        <!-- Bảo mật  -->
        <c:import url="../include_component/frame_config_policy.jsp"/>
        <!-- /Bảo mật  -->

        <!-- Quản lý thẻ/ Tài khoảnQuản lý thẻ/ Tài khoản  -->
        <c:import url="../include_component/frame_car_account.jsp"/>
        <!-- /Quản lý thẻ/ Tài khoản  -->

        <div class="row">
          <div class="col-sm-6 col-md-12">
            <div class="panel panel-bordered">
              <div class="panel-heading">
                <h3 class="panel-title">Câu hỏi thường gặp</h3></div>
              <div class="panel-body py-15 chtg">
                <p class="mb-5"><a href="#">Thời gian xử lý khiếu nại là bao lâu?</a></p>
                <p class="mb-5"><a href="#">Thời gian xử lý khiếu nại là bao lâu?</a></p>
                <p class="mb-5"><a href="#">Thời gian xử lý khiếu nại là bao lâu?</a></p>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-md-12">
            <div class="panel panel-bordered">
              <div class="panel-heading">
                <h3 class="panel-title">Trợ giúp</h3>
                <ul class="panel-actions panel-actions-keep">
                  <li><a href="#">Xem toàn bộ</a></li>
                </ul>
              </div>
              <ul class="list-group list-group-full py-10">
                <li class="list-group-item py-5">
                  <div class="media">
                    <div class="pr-10">
                      <a class="" href="javascript:void(0)"><img class="img-fluid br3" src="/assets/images/placeholder100.png" width="26" alt="..."></a>
                    </div>
                    <div class="media-body">
                      <a href="#">
                        <h5 class="mt-5 mb-5"><spring:message code="account.bank.account"/> <spring:message code="common.company"/></h5></a>
                    </div>
                  </div>
                </li>
                <li class="list-group-item py-5">
                  <div class="media">
                    <div class="pr-10">
                      <a class="" href="javascript:void(0)"><img class="img-fluid br3" src="/assets/images/placeholder100.png" width="26" alt="..."></a>
                    </div>
                    <div class="media-body">
                      <a href="#">
                        <h5 class="mt-5 mb-5">Dịch vụ ví điện tử</h5></a>
                    </div>
                  </div>
                </li>
                <li class="list-group-item py-5">
                  <div class="media">
                    <div class="pr-10">
                      <a class="" href="javascript:void(0)"><img class="img-fluid br3" src="/assets/images/placeholder100.png" width="26" alt="..."></a>
                    </div>
                    <div class="media-body">
                      <a href="#">
                        <h5 class="mt-5 mb-5">Dịch vụ cổng thanh toán</h5></a>
                    </div>
                  </div>
                </li>
                <li class="list-group-item py-5">
                  <div class="media">
                    <div class="pr-10">
                      <a class="" href="javascript:void(0)"> <img class="img-fluid br3" src="/assets/images/placeholder100.png" width="26" alt="..."></a>
                    </div>
                    <div class="media-body">
                      <a href="#">
                        <h5 class="mt-5 mb-5">Hướng dẫn mua hàng</h5></a>
                    </div>
                  </div>
                </li>
              </ul>
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

</html>