<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal fade" id="modalHelpTransaction" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel1" aria-hidden="true">
  <form action="" class="form-horizontal">
    <div class="modal-dialog modal-center">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span><i
              class="fas fa-times"></i></span></button>
          <h4 class="modal-title modal-title offset-xs-0 offset-sm-3 offset-md-4">Yêu cầu hỗ trợ
            giao dịch
          </h4>
        </div>

        <div class="modal-body">

          <div class="form-group">
            <div class="form-row">
              <div class="col col-sm-3">
                <label><spring:message code="account.bank.trans.id"/>:</label>
              </div>
              <div class="col">
                <label>123456</label>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="form-row">
              <div class="col col-sm-3">
                <label>Số tiền:</label>
              </div>
              <div class="col">
                <label>123.456đ</label>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="form-row">
              <div class="col col-sm-3">
                <label>Trạng thái:</label>
              </div>
              <div class="col">
                <label>Đang xử lý</label>
              </div>
            </div>
          </div>

          <div class="form-group purple-border">
            <div class="form-row">
              <div class="col col-sm-3">
                <label>Ghi chú:</label>
              </div>
              <div class="col">
                <textarea name="note" id="note" rows="5" class="full-width"></textarea>
              </div>
            </div>
          </div>

        </div>


        <div class="">
          <div class="form-group">
            <div class="form-row">
              <div class="col col-sm-3">
                <label></label>
              </div>
              <div class="col">
                <button type="submit" class="btn btn-primary btn-sm"> Gửi Email</button>
              </div>
            </div>
          </div>
          <%--<div class="col-sm-offset-2 col-sm-10 no-padding-l">--%>
          <%--<button type="submit" class="btn btn-primary btn-sm"> Gửi Email</button>--%>
          <%--</div>--%>
        </div>

      </div>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
</div>