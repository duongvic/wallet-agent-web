<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<div class="modal fade" id="modalCardDetail" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel1" aria-hidden="true">
  <form action="" class="form-horizontal">
    <div class="modal-dialog  modal-center">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span><i
              class="fas fa-times"></i></span></button>
		    <div class="col-xxl-12 col-lg-12">
             <div class = "panel-heading">
           <h3 class="panel-title"><spring:message code="label.epin"/></h3>
          <ul class="panel-actions panel-actions-keep">
            <li><a href=""><spring:message code="label.setting"/></a></li>
          </ul>
          </div>
          </div>
        </div>
        <div class="modal-body">

          <div class="form-row">
              <div class="col col-sm-3">
                <label>Mã thẻ:</label>
              </div>
              <div class="col col-sm-9">
                <select data-plugin="select2" class="form-control" id="typeCard" name="typeCard" onchange="getDiscount('typeCard')">
                  <%--<option value="">Chọn mệnh giá nạp</option>--%>
                  <option value="5">Viettel</option>
                  <option value="15">Mobiphone</option>
                  <option value="5" >VinaPhone</option>
                  <option value="15" >VietnamMobile</option>
                </select>
              </div>
          </div>
          <div class="form-row mb-10 pos-relative">
              <div class="col col-sm-3">
                <label><spring:message code="label.face.value"/> </label>
              </div>
              <div class="col col-sm-9">
                <select data-plugin="select2" class="form-control" id="faceValue" name="faceValue" onchange="thanhToan()">
                  <%--<option value="">Mệnh giá</option>--%>
                  <option value="100000" selected="selected">100.000đ</option>
                  <option value="10000">10.000đ</option>
                  <option value="20000">20.000đ</option>
                  <option value="50000">50.000đ</option>
                  <option value="200000">200.000đ</option>
                  <option value="500000">500.000đ</option>
                </select>
              </div>
              </div>
          </div>
          <div class="form-row">
           <div class="col col-sm-10">
            <div class="form-group row mb-0">
                  <label class="col-md-3 form-control-label"><spring:message code="label.discount"/>: </label>
                   <div class="col-md-9">
                     <div class="green-600 mt-5 fs10" id= "disCount" >5%</div>
                   </div>
                </div>
                </div>
                <div class="col">
                <label></label>
                </div>
            </div>
            <div class="form-row mb-10">
              <div class="col col-sm-10">
                <div class="form-group row mb-0">
              <label class="col-md-3 form-control-label"><spring:message code="label.amount"/>: </label>
              <div class="col-md-9">
                <div class="red-600 mt-5 fs10" id = "realAmount"></div>
              </div>
            </div>
              </div>
              <div class="col">
                <button type="submit" class="btn btn-primary btn-sm"><spring:message code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i></button>
              </div>
            </div>
          </div>
      </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
    </div>
  <c:import url="../dialog_modal/dialog_transactionDetail.jsp"/>
  <script>
	  function getDiscount(obj) {
		    var vlDis = document.getElementById(obj).value;
		    var vlDisCount = document.getElementById('disCount').innerText = Math.abs(parseInt(vlDis))
		        + ' %';
		    var vlMoney = document.getElementById('faceValue').value;
		    var vlQuantity = 1;
		    thanhToan();
		  }
	  function thanhToan() {
		    var realAmount = 0;
		    var discount = parseInt(document.getElementById('disCount').innerText);
		        + ' %';
		    var faceValue = document.getElementById('faceValue').value;
		    var quantity = 1
		    realAmount = parseFloat((faceValue * quantity)* ( 1 - (parseFloat(discount / 100))));
		    document.getElementById('realAmount').innerText = realAmount + ' đ';
		    // get resquest submit
		    document.getElementById('hidden_disCount').value = parseInt(document.getElementById('disCount').innerText);
		    document.getElementById('hidden_realAmount').value = parseInt(document.getElementById('realAmount').innerText);
		  }
	  $(document).ready(function(){
			$("#modalCardDetail").on("show.bs.modal", function(event) {
				var recipient = $(event.relatedTarget).html();
				var modal = $(this);
				modal.find(".col-auto #typeCard").html(recipient);
			})
		});
</script>
  
