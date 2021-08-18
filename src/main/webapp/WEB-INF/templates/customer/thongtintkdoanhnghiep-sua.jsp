<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>Cập nhật thông tin tài khoản doanh nghiệp - <spring:message code="common.company"/></title>
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
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"></spring:message></a></li>
      <li class="breadcrumb-item active">Thông tin tài khoản</li>
    </ol>
    <h1 class="page-title">Thông tin người dùng</h1></div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-xxl-9 col-lg-9 col-md-8 col-sm-12">
        <div class="panel panel-bordered">
          <div class="panel-heading">
            <h3 class="panel-title">Sửa thông tin doanh nghiệp</h3></div>
          <div class="panel-body py-10">
            <form class="form-horizontal">
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Người đại diện: </label>
                <div class="col-sm-4"><input type="text" class="form-control"/></div>
              </div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">CMND: </label>
                <div class="col-sm-4"><input type="text" class="form-control"/></div>
                <label class="col-sm-2 form-control-label">Quốc tịch: </label>
                <div class="col-sm-3">
                  <select class="form-control">
                    <option value="">Quốc tịch</option>
                    <option value="vn">Việt Nam</option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Tên doanh nghiệp: </label>
                <div class="col-sm-4"><input type="text" class="form-control"/></div>
                <label class="col-sm-2 form-control-label">Tên giao dịch: </label>
                <div class="col-sm-3"><input type="text" class="form-control"/></div>
              </div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Tên đầy đủ: </label>
                <div class="col-sm-4"><input type="text" class="form-control"/></div>
              </div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Ngành nghề kinh doanh: </label>
                <div class="col-sm-4">
                  <select class="form-control">
                    <option value="">Ngành nghề</option>
                    <option value="truyenthong">Truyền thông</option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Số ĐKKD: </label>
                <div class="col-sm-4"><input type="text" class="form-control"/></div>
              </div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Mã số thuế: </label>
                <div class="col-sm-4"><input type="text" class="form-control"/></div>
              </div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Địa chỉ ĐKKD: </label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" placeholder="Địa chỉ chi tiết"/>
                  <div class="row">
                    <div class="col-sm-4 mt-20">
                      <select class="form-control">
                        <option value="">Tỉnh/Thành Phố</option>
                        <option value="hn">Hà Nội</option>
                        <option value="hcm">Hồ Chí Minh</option>
                      </select>
                    </div>
                    <div class="col-sm-4 mt-20">
                      <select class="form-control">
                        <option value="">Quận/Huyện</option>
                        <option value="hn">Hai Bà Trưng</option>
                        <option value="hcm">Hoàn Kiếm</option>
                      </select>
                    </div>
                    <div class="col-sm-4 mt-20">
                      <select class="form-control">
                        <option value="">Phường/Xã</option>
                        <option value="hn">Đồng Nhân</option>
                        <option value="hcm">Hàng Mã</option>
                      </select>
                    </div>
                  </div>
                </div>
              </div>
              <div class="form-group row">
                <label class="col-sm-3 form-control-label">Trụ sở chính: </label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" placeholder="Địa chỉ chi tiết"/>
                  <div class="row">
                    <div class="col-sm-4 mt-20">
                      <select class="form-control">
                        <option value="">Tỉnh/Thành Phố</option>
                        <option value="hn">Hà Nội</option>
                        <option value="hcm">Hồ Chí Minh</option>
                      </select>
                    </div>
                    <div class="col-sm-4 mt-20">
                      <select class="form-control">
                        <option value="">Quận/Huyện</option>
                        <option value="hn">Hai Bà Trưng</option>
                        <option value="hcm">Hoàn Kiếm</option>
                      </select>
                    </div>
                    <div class="col-sm-4 mt-20">
                      <select class="form-control">
                        <option value="">Phường/Xã</option>
                        <option value="hn">Đồng Nhân</option>
                        <option value="hcm">Hàng Mã</option>
                      </select>
                    </div>
                  </div>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
          </div>
          <div class="panel-footer py-10 text-right">
            <div class="row">
              <div class="col-sm-9 offset-sm-3">
                <a href="/customer/thongTinTaiKhoanDoanhNghiep" class="btn btn-default btn-sm btn-outline"><spring:message code="common.btn.back"/></a>
                <a href="/customer/thongTinTaiKhoanDoanhNghiep" class="btn btn-primary btn-sm ml-10">Cập nhật </a></div>
            </div>
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