<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>Thông tin tài khoản doanh nghiệp - <spring:message code="common.company"/></title>
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
  <jsp:param name="nav" value="infoCus"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active">Thông tin tài khoản</li>
    </ol>
    <h1 class="page-title">Thông tin người dùng</h1></div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-xxl-9 col-lg-9 col-md-8 col-sm-12">
        <div class="panel panel-bordered">
          <div class="panel-heading">
            <h3 class="panel-title">Xác thực khách hàng</h3>
            <ul class="panel-actions panel-actions-keep">
              <%--<li><a href="">Hoàn thiện 50%</a></li>--%>
            </ul>
          </div>
          <div class="panel-body py-10">
            <div class="row">
              <div class="col-md-3 col-sm-6 text-center my-15">
                <i class="icon pe-check badge-success br-100 fs40"></i>
                <p class="mb-0">Liên kết tài khoản <i class="fa  fa-question-circle" data-placement="top" data-toggle="tooltip"
                                                      data-original-title="Bạn phải liên kết tài khoản ví điện tử với tài khoản ngân hàng để thực hiện giao dịch trên ví Zota"></i></p>
                <small>Đã liên kết</small>
                <br>
              </div>
              <div class="col-md-3 col-sm-6 text-center my-15">
                <i class="icon pe-close-circle badge-danger br-100 fs40"></i>
                <p class="mb-0">Xác thực email <i class="fa  fa-question-circle" data-placement="top" data-toggle="tooltip"
                                                  data-original-title="Bạn phải xác thực email để có thể sử dụng được phương thức bảo mật thanh toán OTP Email"></i></p>
                <small>Chưa xác thực</small>
                <br>
                <button type="button" class="btn btn-warning btn-sm mt-10"> Gửi yêu cầu</button>
              </div>
              <div class="col-md-3 col-sm-6 text-center my-15">
                <i class="icon pe-attention badge-warning br-100 fs40"></i>
                <p class="mb-0">Xác thực tài khoản <i class="fa  fa-question-circle" data-placement="top" data-toggle="tooltip"
                                                      data-original-title="Bạn phải xác thực tài khoản để có thể sử dụng dịch vụ rút tiền về tài khoản ngân hàng của Zota"></i></p>
                <small>Đã gửi yêu cầu xác thực</small>
                <br>
              </div>
              <div class="col-md-3 col-sm-6 text-center my-15">
                <i class="icon pe-check badge-success br-100 fs40"></i>
                <p class="mb-0">Bảo mật thanh toán <i class="fa  fa-question-circle" data-placement="top" data-toggle="tooltip"
                                                      data-original-title="Khi đăng ký tài khoản ví điện tử, hệ thống đã đăng ký cho bạn dịch vụ bảo mật thanh toán OTP SMS"></i></p>
                <small>Đã đăng ký</small>
                <br>
              </div>
            </div>
          </div>
        </div>
        <div class="panel panel-bordered">
          <div class="panel-heading">
            <h3 class="panel-title">Thông tin tài khoản</h3></div>
          <div class="panel-body py-10">
            <form>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Số ví điện tử doanh nghiệp: </label>
                <div class=" col-sm-9 ">
                  <p class="form-control-plaintext pb-0">0902244419</p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Email doanh nghiệp: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">nguyenvana@gmail.com <a href="#" class="btn btn-primary btn-xs pull-right">Thay đổi</a></p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0"><spring:message code="label.password"/>: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">Cập nhật lần cuối vào 01/01/2018 00:00:00 <a href="#" class="btn btn-primary btn-xs pull-right">Thay đổi</a></p>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
          </div>
        </div>
        <div class="panel panel-bordered">
          <div class="panel-heading">
            <h3 class="panel-title">Thông tin doanh nghiệp</h3></div>
          <div class="panel-body py-10">
            <form>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Người đại diện: </label>
                <div class=" col-sm-9 ">
                  <p class="form-control-plaintext pb-0">Nguyễn Văn A</p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">CMND: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">12345678 </p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Quốc tịch: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">Việt Nam </p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Tên doanh nghiệp: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">HiTech </p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Tên đầy đủ: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">Công ty Cổ phần Truyền thông HiTech </p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Tên giao dịch: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">ABC </p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Ngành nghề kinh doanh: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">Truyền thông </p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Số ĐKKD: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">432634773 </p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Mã số thuế: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">527458881 </p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Địa chỉ ĐKKD: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">18 Trương Định, Hoàng Mai, Hà Nội </p>
                </div>
              </div>
              <div class="form-group row mb-5">
                <label class=" col-sm-3 col-form-label pb-0">Trụ sở chính: </label>
                <div class=" col-sm-9">
                  <p class="form-control-plaintext pb-0">165 Thái Hà, Đống Đa, Hà Nội <a href="#" class="btn btn-sm btn-primary btn-xs pull-right">Cập nhật</a></p>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
          </div>
        </div>
      </div>
      <div class="col-xxl-3 col-lg-3 col-md-4 col-sm-12">

        <!-- information customer -->
        <c:import url="frame_custumer.jsp"/>
        <!-- /information customer -->

        <div class="row">

          <c:import url="../include_component/frame_car_account.jsp"/>

          <c:import url="../include_component/frame_config_policy.jsp"/>
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