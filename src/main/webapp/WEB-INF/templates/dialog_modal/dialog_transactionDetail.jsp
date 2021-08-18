<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal fade" id="modalTransaction" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel1" aria-hidden="true">
  <form action="" class="form-horizontal">
    <div class="modal-dialog modal-center">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span><i
              class="fas fa-times"></i></span></button>
		    <div class="col-xxl-12 col-lg-12">
             <div class = "panel-heading">
           <h3 class="panel-title"><spring:message code="label.fundin"/></h3>
          <ul class="panel-actions panel-actions-keep">
            <li><a href=""><spring:message code="label.setting"/> </a></li>
          </ul>
          </div>
          </div>
        </div>
        <div class="modal-body">

          <div class="form-row">
              <div class="col col-sm-3">
                <label><spring:message code="content.account.fundin"/> :</label>
              </div>
              <div class="col col-sm-9">
                <input type="text" class="form-control" id="account" value= "">
              </div>
          </div>

          <!-- <div class="form-group">
            <div class="form-row">
              <div class="col col-sm-3">
                <label>Số tiền:</label>
              </div>
              <div class="col">
                <label>100.000 đ</label>
              </div>
              <div class="col">
              <div class="price-radio mt-5">
              <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="" name="price">
                    <label for=""><span>...</span></label>
                 </div>
               </div>
              </div>
            </div>
          </div> -->
          <div class="form-row mb-10 pos-relative">
              <div class="col col-sm-3">
                <label><spring:message code="label.amount"/> :</label>
              </div>
              <div class="col col-sm-9">
                <div class="input-group">
                  <input type="text" class="form-control" name="quantity" id="quantity" value="">
                  <div class="input-group-append">
                      <span class="input-group-text"><button type="button"
                                                             class="btn-add fa fa-minus"
                                                             onclick="quantityMinus()"></button></span>
                    <span class="input-group-text"><button type="button" class="btn-add fa fa-plus"
                                                           onclick="quantityPlus()"></button></span>
                  </div>
                </div>
                </div>
              </div>
          </div>
          <div class = form-group>
            <div class="form-row">
              <div class="col col-sm-10">
                <label></label>
              </div>
              <div class="col">
                <button type="submit" class="btn btn-primary btn-sm"><spring:message code="common.btn.next"/><i class="icon wb-arrow-right ml-10"></i></button>
              </div>
            </div>
           </div>
        </div>
        </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
</div>
<script src="/assets/development/js/my_format_currency.js"></script>
<script>
function quantityMinus() {
    var vlQuantity = document.getElementById('quantity');
    var value = vlQuantity.value;
    if ((typeof value === 'undefined') || value === undefined || value === '' || value === null) {
    	value = 0;
    }
    value = parseInt(currencyToNumber(value)) - 10000;
    if (value < 0) {
    	value = 0;
    }
    vlQuantity.value = formatCurrency(value);
  }

  function quantityPlus() {
    var valQuantity = document.getElementById('quantity');
    var value = valQuantity.value;
    if ((typeof value === 'undefined') || value === undefined || value === '' || value === null) {
    	value = 0;
    }
  	value = parseInt(currencyToNumber(value)) + 10000;
  	valQuantity.value = formatCurrency(value);
  }
  $(document).ready(function(){
	$("#modalTransaction").on("show.bs.modal", function(event) {
		var recipient = $("#cardValue").val();
		var modal = $(this);
		modal.find(".modal-body #quantity").val(recipient);
	})
});
  </script>