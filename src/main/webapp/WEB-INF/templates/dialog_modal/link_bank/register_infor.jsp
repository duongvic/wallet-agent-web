<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- The Modal -->
<div class="modal fade" id="modalRegisterInfo" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel1" aria-hidden="true">
  <div class="modal-dialog modal-sm modal-center">
      <div class="modal-content">
      <!-- Modal Header -->
      <%--<div class="modal-header">--%>
        <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
      <%--</div>--%>
      <!-- Modal body -->
      <div class="modal-body">
        <div class="row">
          <form method="post" action="/system/payment-security">
            <div class="panel panel-bordered">
              <div class="panel-heading text-center">
                <h3 class="panel-title"><spring:message code="register.online.payment"/> </h3>
              </div>
              <%--<c:if test="${(codeErr != null)}">--%>
              <%--<div class="text-danger error-message form-group row mb-10 " style="margin-left: 2.5em;">--%>
              <%--<small><i class="fa fa-times-circle"></i>&nbsp;${codeErr}--%>
              <%--</small>--%>
              <%--</div>--%>
              <%--</c:if>--%>
              <div class="panel-body text-center">
                <div class="form-group row">
                  <label class="col-sm-12 form-control-label"><spring:message code="register.online.payment.u.must.register.link.bank.account"/> </label>
                </div>
                <div class="form-group row justify-content-center">
                  <button type="button" class="btn btn-warning btn-sm col-sm-11 font-weight-bold" data-toggle="modal" data-dismiss="modal" for="" data-target="#modalRegisterOnlinePayment">
                    <spring:message code="register.payment.online.button"/>
                  </button>
                </div>
                <div class="form-group row justify-content-center">
                  <label class="col-sm-3 form-control-label text-primary font-weight-bold"
                         data-dismiss="modal"
                         aria-label="Close"><spring:message code="common.btn.close"/></label>
                </div>
              </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
          </form>
        </div>
      </div>

      <%--<!-- Modal footer -->--%>
      <%--<div class="modal-footer">--%>
        <%--<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>--%>
      <%--</div>--%>

    </div>
  </div>
</div>

<jsp:include page="./register_online_payment.jsp"></jsp:include>

