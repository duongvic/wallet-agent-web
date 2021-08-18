<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" prefix="ewallet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="panel panel-bordered">
  <div class="panel-body ">
  <div class="form-group row mb-10">
    <h4 class="panel-title pl-0">Thông tin giao dịch</h4>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label>Loại giao dịch:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.epin"/></label>
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label>Sản phẩm:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>Viettel</label>
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.quantity"/>:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>1</label>
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.face.value"/>:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>100.000 Zpoint</label>
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.discount"/></label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>5%</label>
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label>Phí thanh toán:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>0 Zpoint</label>
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label>Tổng thanh toán:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label>96.000 đ</label>
    </div>
  </div>
  <div class="form-group row mb-10 ">
    <div class="col col-sm-6 col-md-6">
      <label>Phương thức thanh toán:</label>
    </div>
    <div class="col col-sm-6 col-md-6">
      <label><spring:message code="label.ewallet"/></label>
    </div>
  </div>
</div>

  <div class="panel-body ">
    <div class="form-group row mb-10">
      <h4 class="panel-title pl-0">Thông tin người dùng</h4>
    </div>
    <div class="form-group row mb-10 ">
      <div class="col col-sm-6 col-md-6">
        <label>Số điện thoại:</label>
      </div>
      <div class="col col-sm-6 col-md-6">
        <label>01653293254</label>
      </div>
    </div>
    <div class="form-group row mb-10 ">
      <div class="col col-sm-6 col-md-6">
        <label>Email:</label>
      </div>
      <div class="col col-sm-6 col-md-6">
        <label>Viet@gmail.com</label>
      </div>
    </div>
  </div>
</div>