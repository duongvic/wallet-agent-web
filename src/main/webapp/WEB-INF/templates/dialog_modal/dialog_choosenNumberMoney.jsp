<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal fade" id="modalChoosenNumberMoney" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel1" aria-hidden="true">
  <form action="" class="form-horizontal">
    <div class="modal-dialog modal-sm modal-center">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span><i
              class="fas fa-times"></i></span></button>
          <h4 class="modal-title modal-title offset-xs-0 offset-sm-2"><spring:message code="label.choose.face.value"/>: </h4>
        </div>
        <div class="modal-body">

          <div class="form-group">
            <div class="form-row">
              <div class="col">
                <label><span onclick="getId(this)" id = "100">100.000 đ</span></label>
              </div>
              <div class="col">
                <label><span onclick="getId(this)" id = "200">200.000 đ</span></label>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="form-row">
              <div class="col">
                <label><span onclick="getId(this)" id = "300">300.000 đ</span></label>
              </div>
              <div class="col">
                <label ><span onclick="getId(this)" id = "400">400.000 đ</span></label>
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="form-row">
              <div class="col">
                <label ><span onclick="getId(this)" id = "500">500.000 đ</span></label>
              </div>
              <div class="col">
                <label ><span onclick="getId(this)" id = "600">600.000 đ</span></label>
              </div>
            </div>
          </div>
          </div>
      </div>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
</div>
<c:import url="../dialog_modal/dialog_transactionDetail.jsp"/>
<script>
function getId(element){
  var x = element.innerHTML;
  var y = document.getElementById("cardValue");
  y.value = x;
}

</script>