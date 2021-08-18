<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib
	uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
	prefix="ewallet"%>

<div class="modal fade" id="modalSaveBank" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel1" aria-hidden="true">
	<form action="" class="form-horizontal">
		<div class="modal-dialog modal-center">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span><i class="fas fa-times"></i></span>
					</button>
					<h4 class="modal-title modal-title">Danh sách thẻ/ Tài khoản
						đã giao dịch</h4>
				</div>
				<div class="modal-body">
					<c:forEach var="item" items="${bankItemByCustomer}">
						<div class="row">
							<div class="col-md-5 col-sm-12">${item.itemHolderName}</div>
							<div class="col-md-5 col-sm-12">${item.itemNumber}</div>
							<div class="col-md-2 col-sm-12">
								<span><i class="fa fa-arrow-circle-right" aria-hidden="true" style="cursor: pointer;" onclick="arrowClick('${item.itemHolderName}', '${item.itemNumber}')"></i></span>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</div>