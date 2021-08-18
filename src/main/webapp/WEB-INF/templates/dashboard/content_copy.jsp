<%@ page
    import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_AGENT" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet"
           uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<c:set var="ID_AGENT" value="<%=ID_AGENT%>"/>

<div class="col-xxl-9 col-lg-9 col-md-8 col-sm-12">

  <!-- banner widget  -->
  <%--<c:import url="../include_component/frame_banner.jsp"/>--%>
  <!-- /banner widget  -->

  <%--dialog--%>
  <%--<c:import url="../dialog_modal/dialog_choosenFaceValue.jsp"/>--%>
  <c:import url="../dialog_modal/pin_code/dialog_helpTransaction.jsp"/>
  <%--<c:import url="../dialog_modal/dialog_choosenNumberMoney.jsp"></c:import>--%>
  <%--<c:import url="../dialog_modal/dialog_chooseCardType.jsp"></c:import>--%>
  <%--end dialog--%>


  <div class="row">
    <%--Mua mã thẻ--%>
    <div class="col-xxl-6 col-lg-6">
      <div class="panel panel-bordered">
        <div class="panel-heading">
          <h3 class="panel-title"><spring:message code="content.buy.epin"/></h3>
          <%--<ul class="panel-actions panel-actions-keep">--%>
          <%--<li><a href="/fundin/napTienNhanh">Cài đặt</a></li>--%>
          <%--</ul>--%>
        </div>
        <div class="panel-body py-10">
          <form class="form-horizontal" method="post" action="/pin-code/order-info">
            <div class="form-group row mb-10">
              <label class="col-md-4 form-control-label px-0"><spring:message
                  code="content.type.epin"/>:
              </label>
              <div class="col-md-8">
                <div class="form-group">
                  <div class="row">
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="price-radio mt-5 item-responsive">
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('Viettel','')">
                          <input type="radio" id="viettel" name="tenSP" value="Viettel" checked>
                          <label for="viettel"><span>Viettel</span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('MobiFone','')">
                          <input type="radio" id="mobi" name="tenSP" value="MobiFone"> <label
                            for="mobi"><span>MobiFone</span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('Vinaphone','')">
                          <input type="radio" id="vina" name="tenSP" value="Vinaphone"> <label
                            for="vina"><span>Vinaphone</span></label>
                        </div>
                      </div>

                    </div>
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="price-radio mt-5 item-responsive">
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('GMobile','')">
                          <input type="radio" id="gMobile" name="tenSP" value="GMobile"> <label
                            for="gMobile"><span>GMobile</span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('Vietnamobile','')">
                          <input type="radio" id="vietnamobile" name="tenSP" value="Vietnamobile">
                          <label
                              for="vietnamobile"><span>Vietnamobile</span></label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <%--<div class="price-radio mt-5 item-responsive">--%>
                <%--&lt;%&ndash;<div class="radio-custom radio-info radio-inline text-center">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<c:set var="pin_service_name"&ndash;%&gt;--%>
                <%--&lt;%&ndash;value="${pin_code_service_name == null || empty pin_code_service_name ? 'Viettel': pin_code_service_name}"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="radio" id="viettel" name="tenSP"&ndash;%&gt;--%>
                <%--&lt;%&ndash;value="${pin_service_name}" checked>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<label for="viettel"><span>${pin_service_name}</span></label>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--<div class="radio-custom radio-info radio-inline text-center"--%>
                <%--onclick="calculatePinCode('Viettel','')">--%>
                <%--<input type="radio" id="viettel" name="tenSP" value="Viettel" checked>--%>
                <%--<label for="viettel"><span>Viettel</span></label>--%>
                <%--</div>--%>
                <%--<div class="radio-custom radio-info radio-inline text-center"--%>
                <%--onclick="calculatePinCode('MobiFone','')">--%>
                <%--<input type="radio" id="mobi" name="tenSP" value="MobiFone"> <label--%>
                <%--for="mobi"><span>MobiFone</span></label>--%>
                <%--</div>--%>
                <%--<div class="radio-custom radio-info radio-inline text-center"--%>
                <%--onclick="calculatePinCode('Vinaphone','')">--%>
                <%--<input type="radio" id="vina" name="tenSP" value="Vinaphone"> <label--%>
                <%--for="vina"><span>Vinaphone</span></label>--%>
                <%--</div>--%>
                <%--<div class="radio-custom radio-info radio-inline text-center"--%>
                <%--onclick="calculatePinCode('GMobile','')">--%>
                <%--<input type="radio" id="gMobile" name="tenSP" value="GMobile"> <label--%>
                <%--for="gMobile"><span>GMobile</span></label>--%>
                <%--</div>--%>
                <%--<div class="radio-custom radio-info radio-inline text-center"--%>
                <%--onclick="calculatePinCode('Vietnamobile','')">--%>
                <%--<input type="radio" id="vietnamobile" name="tenSP" value="Vietnamobile"> <label--%>
                <%--for="vietnamobile"><span>Vietnamobile</span></label>--%>
                <%--</div>--%>
                <%--</div>--%>
              </div>
            </div>
            <div class="form-group row mb-10">
              <label class="col-md-4 form-control-label px-0"><spring:message
                  code="content.face.value"/>:
              </label>
              <div class="col-md-8">
                <div class="form-group">
                  <div class="row">
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="price-radio mt-5 item-responsive">
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('','10000')">
                          <input type="radio" id="t10" name="tprice_pincode" checked
                                 value="10000"> <label for="t100"><span>10.000
												</span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('','20000')">
                          <input type="radio" id="t20" name="tprice_pincode" value="20000">
                          <label for="t20"><span>20.000 </span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('','50000')">
                          <input type="radio" id="t50" name="tprice_pincode" value="50000">
                          <label for="t50"><span>50.000 </span></label>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="price-radio mt-5 item-responsive">
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('','100000')">
                          <input type="radio" id="t100" name="tprice_pincode"
                                 value="100000"> <label for="t100"><span>100.000
												</span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('','200000')">
                          <input type="radio" id="t200" name="tprice_pincode" value="200000">
                          <label for="t200"><span>200.000 </span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculatePinCode('','500000')">
                          <input type="radio" id="t500" name="tprice_pincode" value="500000">
                          <label for="t500"><span>500.000 </span></label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>


              </div>
            </div>
            <div class="form-group row mb-10">
              <label class="col-4 form-control-label px-0"><spring:message code="content.discount"/>: </label>
              <div class="col-8">
                <div id="discount2" class="green-600 mt-5 fs16">
                  <b>-- %</b>
                </div>
              </div>
            </div>
            <div class="form-group row mb-0">
              <label class="col-4 form-control-label px-0"><spring:message
                  code="content.real.amount"/>: </label>
              <div class="col-8">
                <div id="result2" class="red-600 mt-5 fs16">
                  <b>-- đ</b>
                </div>
              </div>
            </div>
            <div class="panel-footer py-10">
              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit" class="btn btn-primary btn-sm pull-right">
                    <spring:message code="common.btn.next"/>
                    <i class="icon wb-arrow-right ml-10"></i>
                  </button>
                </div>
              </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
          </form>
        </div>
      </div>
    </div>
    <%--end Mua mã thẻ--%>

    <%--Nạp tiền điện thoai--%>
    <div class="col-xxl-6 col-lg-6">
      <div class="panel panel-bordered">
        <div class="panel-heading">
          <h3 class="panel-title"><spring:message code="content.topUp"/></h3>
          <%--<ul class="panel-actions panel-actions-keep">--%>
          <%--<li><a href="/fundin/napTienNhanh">Cài đặt</a></li>--%>
          <%--</ul>--%>
        </div>
        <div class="panel-body py-10">
          <form class="form-horizontal" method="post" action="/topup">
            <div class="form-group row mb-10">
              <label class="col-md-4 form-control-label px-0"><spring:message
                  code="content.topUp.payee.phone"/>:</label>
              <div class="col-md-8">
                <div class="price-radio mt-5 item-responsive">
                  <div class="radio-custom radio-info radio-inline text-center"
                       onclick="calculateTopup('${userLogin.phoneNumber}','')">
                    <input type="radio" id="sdt1" name="phoneNumber"
                           value="${userLogin.phoneNumber}" checked>
                    <label for="sdt1"><span>${userLogin.phoneNumber}</span></label>
                  </div>
                  <c:if test="${listPhoneTopUp !=null}">
                    <c:forEach var="item" items="${listPhoneTopUp}" varStatus="rowId">
                      <div class="radio-custom radio-info radio-inline text-center"
                           onclick="calculateTopup('${item.value}','')">
                        <input type="radio" id="${rowId.count}" name="phoneNumber"
                               value="${item.value}">
                        <label for="${rowId.count}"><span>${item.value}</span></label>
                      </div>
                    </c:forEach>

                  </c:if>
                </div>
              </div>
            </div>

            <div class="form-group row mb-10">
              <label class="col-md-4 form-control-label px-0"><spring:message
                  code="content.face.value"/>:
              </label>
              <div class="col-md-8">
                <div class="form-group">
                  <div class="row" id="mobileFundIn">
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="price-radio mt-5 item-responsive">
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculateTopup('','10000')">
                          <input type="radio" id="g10" name="priceTopup" value="10000"
                                 checked> <label for="g10"><span>10.000</span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculateTopup('','20000')">
                          <input type="radio" id="g20" name="priceTopup" value="20000">
                          <label for="g20"><span>20.000 </span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculateTopup('','50000')">
                          <input type="radio" id="g50" name="priceTopup" value="50000">
                          <label for="g50"><span>50.000 </span></label>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="price-radio mt-5 item-responsive">
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculateTopup('','100000')">
                          <input type="radio" id="g100" name="priceTopup" value="100000">
                          <label for="g100"><span>100.000</span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculateTopup('','200000')">
                          <input type="radio" id="g200" name="priceTopup" value="200000">
                          <label for="g200"><span>200.000 </span></label>
                        </div>
                        <div class="radio-custom radio-info radio-inline text-center"
                             onclick="calculateTopup('','500000')">
                          <input type="radio" id="g500" name="priceTopup" value="500000">
                          <label for="g500"><span>500.000 </span></label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="form-group row mb-10">
              <label class="col-4 form-control-label px-0"><spring:message
                  code="content.discount"/>: </label>
              <div class="col-8">
                <div id="discount1" class="green-600 mt-5 fs16">
                  <b>-- %</b>
                </div>
              </div>
            </div>
            <div class="form-group row mb-0">
              <label class="col-4 form-control-label px-0"><spring:message
                  code="content.real.amount"/>: </label>
              <div class="col-8">
                <div id="result1" class="red-600 mt-5 fs16">
                  <b>-- đ</b>
                </div>
              </div>
            </div>
            <div class="panel-footer py-10">
              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit" class="btn btn-primary btn-sm pull-right">
                    <spring:message code="common.btn.next"/>
                    <i class="icon wb-arrow-right ml-10"></i>
                  </button>
                </div>
              </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
          </form>
        </div>
      </div>
    </div>
    <%--end Nạp tiền điện thoại--%>
  </div>


  <div class="row">
    <%--Dich vụ--%>
    <div class="col-xxl-6 col-lg-6">
      <div class="panel panel-bordered">
        <div class="panel-heading">
          <h3 class="panel-title"><spring:message code="label.bill.payment"/></h3>
        </div>
        <div class="panel-body py-10">
          <form class="form-horizontal">
            <div class="form-group row mb-10">
              <label class="col-md-4 form-control-label px-0"><spring:message
                  code="content.service"/>:
              </label>
              <div class="col-md-8">
                <div class="form-group">
                  <div class="row">
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="price-radio mt-5 item-responsive">
                        <div class="radio-custom radio-info radio-inline text-center">
                          <input type="radio" id="_FEcredit" name="tprice_bill_financil" checked
                                 value="FEcredit" onclick="getServiceName(this)">
                          <label for="_FEcredit"><span>FEcredit</span></label>
                        </div>

                        <div class="radio-custom radio-info radio-inline text-center">
                          <input type="radio" id="_Homecredit" name="tprice_bill_financil"
                                 value="Homecredit" onclick="getServiceName(this)">
                          <label for="_Homecredit"><span>Homecredit</span></label>
                        </div>

                        <div class="radio-custom radio-info radio-inline text-center">
                          <input type="radio" id="_Prudential" name="tprice_bill_financil"
                                 value="Prudential" onclick="getServiceName(this)">
                          <label for="_Prudential"><span>Prudential</span></label>
                        </div>

                      </div>
                    </div>
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="price-radio mt-5 item-responsive">
                        <div class="radio-custom radio-info radio-inline text-center">
                          <input type="radio" id="_electric" name="tprice_bill_financil"
                                 value="electric" onclick="getServiceName(this)">
                          <label for="_electric"><span><spring:message
                                  code="service.bill.payment.type.electricity"/></span></label>
                        </div>

                        <div class="radio-custom radio-info radio-inline text-center">
                          <input type="radio" id="_water" name="tprice_bill_financil" value="water"
                                 onclick="getServiceName(this)">
                          <label for="_water"><span><spring:message
                              code="service.bill.payment.type.water"/></span></label>
                        </div>

                        <div class="radio-custom radio-info radio-inline text-center">
                          <input type="radio" id="_internet" name="tprice_bill_financil"
                                 value="internet" onclick="getServiceName(this)">
                          <label for="_internet"><span><spring:message
                              code="service.bill.payment.type.internet"/></span></label>
                        </div>
                        <%--<div class="radio-custom radio-info radio-inline text-center">--%>
                        <%--<input type="radio" id="_cable_television" name="tprice_bill_financil"--%>
                        <%--value="cable-television" onclick="getServiceName(this)">--%>
                        <%--<label for="_cable_television"><span><spring:message--%>
                        <%--code="service.bill.payment.type.cable.television"/></span></label>--%>
                        <%--</div>--%>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer py-10">
              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="button" id="action_bill_financial"
                          class="btn btn-primary btn-sm pull-right">
                    <spring:message code="common.btn.next"/>
                    <i class="icon wb-arrow-right ml-10"></i>
                  </button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <%--end Dịch vụ--%>

    <%--FundIn--%>
    <div class="col-xxl-6 col-lg-6 hidden">
      <div class="panel panel-bordered">
        <div class="panel-heading">
          <h3 class="panel-title"><spring:message code="content.fundin"/></h3>
          <%--<ul class="panel-actions panel-actions-keep">--%>
          <%--<li><a href="/fundin/napTienNhanh">Cài đặt</a></li>--%>
          <%--</ul>--%>
        </div>
        <div class="panel-body py-10">
          <form class="form-horizontal" method="POST" action="/fundin">
            <div class="form-group row mb-10">
              <label class="col-md-4 form-control-label px-0"><spring:message
                  code="content.account.fundin"/>:</label>
              <div class="col-md-8">
                <input type="hidden" id="accountLogin" name="account" checked
                       value="${(userLogin.phoneNumber)}">
                <label for="accountLogin"
                       class="form-control-label px-0 text-account">${(userLogin.phoneNumber)}</label>
              </div>
            </div>
            <div class="form-group row mb-10">
              <label class="col-md-4 form-control-label px-0"><spring:message
                  code="content.face.value"/>:
              </label>
              <div class="col-md-8">
                <div class="price-radio mt-5 item-responsive">
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="1_g100" name="price" checked value="100000">
                    <label for="1_g100"><span>100.000 </span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="1_g500" name="price" value="200000"> <label
                      for="1_g500"><span>200.000 </span></label>
                  </div>
                  <div class="radio-custom radio-info radio-inline text-center">
                    <input type="radio" id="1_g50" name="price" value="500000"> <label
                      for="1_g50"><span>500.000 </span></label>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer py-10">
              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="submit" class="btn btn-primary btn-sm pull-right">
                    <spring:message code="common.btn.next"/>
                    <i class="icon wb-arrow-right ml-10"></i>
                  </button>
                </div>
              </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
          </form>
        </div>
      </div>
    </div>
    <%--end FundIn--%>
  </div>

  <%--<div class="row">--%>
    <%--&lt;%&ndash;FundOut&ndash;%&gt;--%>
    <%--<c:if test="${ID_AGENT != userLogin.customerTypeId}">--%>
      <%--<div class="col-xxl-6 col-lg-6">--%>
        <%--<div class="panel panel-bordered">--%>
          <%--<div class="panel-heading">--%>
            <%--<h3 class="panel-title"><spring:message code="content.fundout"/></h3>--%>
              <%--&lt;%&ndash;<ul class="panel-actions panel-actions-keep">&ndash;%&gt;--%>
              <%--&lt;%&ndash;<li><a href="/fundin/napTienNhanh">Cài đặt</a></li>&ndash;%&gt;--%>
              <%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
          <%--</div>--%>
          <%--<div class="panel-body py-10">--%>
            <%--<form class="form-horizontal" method="POST" action="/fundout">--%>
              <%--<div class="form-group row mb-10">--%>
                <%--<label class="col-md-4 form-control-label px-0"><spring:message--%>
                    <%--code="content.account.fundout"/> :</label>--%>
                <%--<div class="col-md-8">--%>
                  <%--<input type="hidden" name="account" checked--%>
                         <%--value="${(userLogin.phoneNumber)}">--%>
                  <%--<label class="form-control-label px-0 text-account">${(userLogin.phoneNumber)}--%>
                  <%--</label>--%>
                <%--</div>--%>
              <%--</div>--%>
              <%--<div class="form-group row mb-10">--%>
                <%--<label class="col-md-4 form-control-label px-0"><spring:message--%>
                    <%--code="content.face.value"/>:--%>
                <%--</label>--%>
                <%--<div class="col-md-8">--%>
                  <%--<div class="price-radio mt-5 item-responsive">--%>
                    <%--<div class="radio-custom radio-info radio-inline text-center">--%>
                      <%--<input type="radio" id="1_t100" name="tprice" checked value="100000">--%>
                      <%--<label for="1_t100"><span>100.000 </span></label>--%>
                    <%--</div>--%>
                    <%--<div class="radio-custom radio-info radio-inline text-center">--%>
                      <%--<input type="radio" id="1_t500" name="tprice" value="200000"> <label--%>
                        <%--for="1_t500"><span>200.000 </span></label>--%>
                    <%--</div>--%>
                    <%--<div class="radio-custom radio-info radio-inline text-center">--%>
                      <%--<input type="radio" id="1_t50" name="tprice" value="500000"> <label--%>
                        <%--for="1_t50"><span>500.000 </span></label>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                <%--</div>--%>
              <%--</div>--%>
              <%--<div class="panel-footer py-10">--%>
                <%--<div class="row">--%>
                  <%--<div class="col-md-9 offset-md-3">--%>
                    <%--<button type="submit" class="btn btn-primary btn-sm pull-right">--%>
                      <%--<spring:message code="common.btn.next"/>--%>
                      <%--<i class="icon wb-arrow-right ml-10"></i>--%>
                    <%--</button>--%>
                  <%--</div>--%>
                <%--</div>--%>
              <%--</div>--%>
              <%--<input type="hidden" name="${_csrf.parameterName}"--%>
                     <%--value="${_csrf.token}"/>--%>
            <%--</form>--%>
          <%--</div>--%>
        <%--</div>--%>
      <%--</div>--%>
    <%--</c:if>--%>
    <%--&lt;%&ndash;end FundOut&ndash;%&gt;--%>
  <%--</div>--%>
</div>

<script src="/assets/development/js/my_format_currency.js"></script>
<script src="/assets/development/js/ajax-function-2272020/get_commission.js"></script>

<script>
  var current_topup_commission;
  var current_pincode_commission;
  var _value;
  var discount;

  $(document).ready(function () {
    calculateTopup('', '');
    calculatePinCode('', '');
    getServiceName(this);
  });

  function openFaceValue() {
    $('#modalChoosenFaceValue').modal('show')
  }

  function opentChoosenNumberMoney() {
    $('#modalChoosenNumberMoney').modal('show')
  }

  function openChooseCardType() {
    $('#modalChooseCardType').modal('show')
  }

  function onLoadCommissionToModel(commissionType, data, commissionFee, prices) {
    if (commissionType === "top_up") {
      current_topup_commission = commissionFee;
      thanhToan(current_topup_commission, "#discount1", "#result1");
    } else {
      current_pincode_commission = commissionFee;
      thanhToan(current_pincode_commission, "#discount2", "#result2");
    }
  }

  function calculateTopup(phoneNumber, value) {
    var _phoneNumber = phoneNumber;
    _value = value;
    if (_phoneNumber === '') {
      _phoneNumber = $("input[name='phoneNumber']:checked").val();
    }
    if (_value === '') {
      _value = $("input[name='priceTopup']:checked").val();
    } else {
      thanhToan(current_topup_commission, "#discount1", "#result1");
    }
    loadPhoneCommission(_phoneNumber, '', '', '', "${_csrf.parameterName}", "${_csrf.token}");
  }

  function calculatePinCode(carName, value) {
    var _carName = carName;
    _value = value;
    if (_carName === '') {
      _carName = $("input[name='tenSP']:checked").val();
    }
    if (_value === '') {
      _value = $("input[name='tprice_pincode']:checked").val();
    } else {
      thanhToan(current_pincode_commission, "#discount2", "#result2");
    }
    loadCardCommission(_carName, '', '', '', "${_csrf.parameterName}", "${_csrf.token}");
  }

  function thanhToan(commissionFee, discountId, resultId) {
    console.log(discountId);
    $(discountId).html("<b>".concat(commissionFee[_value].commision).concat(" %</b>"));
    $(resultId).html(
        "<b>".concat(formatCurrency(_value)).concat(' ').concat("Zpoint").concat("</b>"));
  }

  function getServiceName(elem) {
    var _serviceCode = elem.value;
    if (_serviceCode === '' || _serviceCode === undefined) {
      _serviceCode = $("input[name='tprice_bill_financil']:checked").val();
    }
    if (null != _serviceCode) {
      if (_serviceCode == 'FEcredit' || _serviceCode == 'Homecredit' || _serviceCode == 'Prudential') {
        $('#action_bill_financial').attr("onclick",
            "window.location.replace('/financial-services/".concat(_serviceCode).concat(
                "/management')"));
      } else if (_serviceCode == 'electric' || _serviceCode == 'water' || _serviceCode == 'internet'
          || _serviceCode == 'cable-television') {
        $('#action_bill_financial').attr("onclick",
            "window.location.replace('/bill-payment/".concat(_serviceCode).concat("/management')"));
      }
    }
  };
</script>



