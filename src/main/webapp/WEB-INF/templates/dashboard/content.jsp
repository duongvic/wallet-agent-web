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
  <%--<c:import url="../dialog_modal/pin_code/dialog_helpTransaction.jsp"/>--%>
  <%--<c:import url="../dialog_modal/dialog_choosenNumberMoney.jsp"></c:import>--%>
  <%--<c:import url="../dialog_modal/dialog_chooseCardType.jsp"></c:import>--%>
  <%--end dialog--%>

  <div class="row">

    <%--Dich vụ--%>
    <div class="col-xxl-12 col-lg-12">
      <div class="panel panel-bordered">
        <div class="panel-heading">
          <h3 class="panel-title">DỊCH VỤ TIỆN ÍCH</h3>
        </div>
        <div class="panel-body py-10">
          <form class="form-horizontal">
            <div class="form-group row mb-10">
              <label class="col-md-2 form-control-label px-0"></label>
              <div class="col-md-10">
                <div class="form-group">
                  <div class="row">
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="shortcutsList_btn">
                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_viettelPay" class="input-hidden "
                                 value="viettelPay"
                                 onclick="getServiceName(this)"/>
                          <label for="_viettelPay">
                            <img src="/assets/images/icon/button/viettelPay.png"
                                 alt="_viettelPay" class=""/>
                          </label>
                          <p class="text_btn"><spring:message code="label.fundin.wallet"/><br><spring:message code="label.service.viettel.pay"/></p>
                        </div>


                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_data3G" class="input-hidden "
                                 value="data3G"
                                 onclick="getServiceName(this)"/>
                          <label for="_data3G">
                            <img src="/assets/images/icon/button/3G@3x.png"
                                 alt="_data3G" class=""/>
                          </label>
                          <p class="text_btn">Mua thẻ data</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_epin" class="input-hidden "
                                 value="epin"
                                 onclick="getServiceName(this)"/>
                          <label for="_epin">
                            <img src="/assets/images/icon/button/Epin@3x.png"
                                 alt="_epin" class=""/>
                          </label>
                          <p class="text_btn">Mua mã thẻ</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_topup" class="input-hidden "
                                 value="topup"
                                 onclick="getServiceName(this)"/>
                          <label for="_topup">
                            <img src="/assets/images/icon/button/PhoneTopup@3x.png"
                                 alt="_topup" class=""/>
                          </label>
                          <p class="text_btn">Nạp tiền<br> điện thoại</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_game" class="input-hidden"
                                 value="game"
                                 onclick="getServiceName(this)"/>
                          <label for="_game">
                            <img src="/assets/images/icon/button/GameEpin@3x.png"
                                 alt="_game" class=""/>
                          </label>
                          <p class="text_btn">Thẻ game</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_electric" class="input-hidden"
                                 value="electric"
                                 onclick="getServiceName(this)"/>
                          <label for="_electric">
                            <img src="/assets/images/icon/button/Electricity@3x.png"
                                 alt="_electric" class=""/>
                          </label>
                          <p class="text_btn">Điện</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_water" class="input-hidden"
                                 value="water"
                                 onclick="getServiceName(this)"/>
                          <label for="_water">
                            <img src="/assets/images/icon/button/Water@3x.png"
                                 alt="_water" class=""/>
                          </label>
                          <p class="text_btn">Nước</p>
                        </div>

                        <%--<div class="shortcutItem_btn">--%>
                        <%--<input type="radio" name="tprice_bill_financil"--%>
                        <%--id="VETC" class="input-hidden"--%>
                        <%--value="VETC"--%>
                        <%--onclick="getServiceName(this)"/>--%>
                        <%--<label for="VETC">--%>
                        <%--<img src="/assets/images/icon/button/VETC@3x.png"--%>
                        <%--alt="VETC" class=""/>--%>
                        <%--</label>--%>
                        <%--<p class="text_btn">Phí đường bộ<br> VETC</p>--%>
                        <%--</div>--%>

                        <%--<div class="shortcutItem_btn">--%>
                        <%--<input type="radio" name="tprice_bill_financil"--%>
                        <%--id="_internet" class="input-hidden"--%>
                        <%--value="internet"--%>
                        <%--onclick="getServiceName(this)"/>--%>
                        <%--<label for="_internet">--%>
                        <%--<img src="/assets/images/icon/button/Internet@3x.png"--%>
                        <%--alt="_internet" class=""/>--%>
                        <%--</label>--%>
                        <%--<p class="text_btn">Internet</p>--%>
                        <%--</div>--%>

                        <%--<div class="shortcutItem_btn">--%>
                        <%--<input type="radio" name="tprice_bill_financil"--%>
                        <%--id="_cable_television"--%>
                        <%--class="input-hidden"--%>
                        <%--value="cable-television"--%>
                        <%--onclick="getServiceName(this)"/>--%>
                        <%--<label for="_cable_television">--%>
                        <%--<img src="/assets/images/icon/button/Television@3x.png"--%>
                        <%--alt="_cable_television" class=""/>--%>
                        <%--</label>--%>
                        <%--<p class="text_btn">Truyền hình</p>--%>
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
                    <spring:message code="common.btn.payment"/>
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
  </div>

  <div class="row">
    <%--Dich vụ--%>
    <div class="col-xxl-12 col-lg-12">
      <div class="panel panel-bordered">
        <div class="panel-heading">
          <h3 class="panel-title">DỊCH VỤ TÀI CHÍNH VÀ BẢO HIỂM</h3>
        </div>
        <div class="panel-body py-10">
          <form class="form-horizontal">
            <div class="form-group row mb-10">
              <%--<label class="col-md-2 form-control-label px-0"><spring:message--%>
              <%--code="content.service"/>:--%>
              <%--</label>--%>
              <label class="col-md-2 form-control-label px-0"></label>
              <div class="col-md-8">
                <div class="form-group">
                  <div class="row">
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="shortcutsList_btn">

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="Acs" class="input-hidden "
                                 value="Acs"
                                 onclick="getServiceName(this)"/>
                          <label for="Acs">
                            <img src="/assets/images/icon/button/ACS@3x.png"
                                 alt="Acs" class=""/>
                          </label>
                          <p class="text_btn">ACS</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="AtmOnline" class="input-hidden "
                                 value="AtmOnline"
                                 onclick="getServiceName(this)"/>
                          <label for="AtmOnline">
                            <img src="/assets/images/icon/button/AtmOnline@3x.png"
                                 alt="AtmOnline" class=""/>
                          </label>
                          <p class="text_btn">ATM Online</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="DrDong" class="input-hidden "
                                 value="DrDong"
                                 onclick="getServiceName(this)"/>
                          <label for="DrDong">
                            <img src="/assets/images/icon/button/DrDong@3x.png"
                                 alt="DrDong" class=""/>
                          </label>
                          <p class="text_btn">DrDong</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_FEcredit" class="input-hidden "
                                 value="FEcredit"
                                 onclick="getServiceName(this)"/>
                          <label for="_FEcredit">
                            <img src="/assets/images/icon/FECredit/FEcredit_2x.png"
                                 alt="_FEcredit" class=""/>
                          </label>
                          <p class="text_btn">FECredit</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_Homecredit" class="input-hidden"
                                 value="Homecredit"
                                 onclick="getServiceName(this)"/>
                          <label for="_Homecredit">
                            <img src="/assets/images/icon/payment/Homecredit.png"
                                 alt="_Homecredit" class=""/>
                          </label>
                          <p class="text_btn">Home Credit</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_MCredit" class="input-hidden"
                                 value="MCredit"
                                 onclick="getServiceName(this)"/>
                          <label for="_MCredit">
                            <img src="/assets/images/icon/MCredit/Mcredit@3x.png"
                                 alt="_MCredit" class=""/>
                          </label>
                          <p class="text_btn">M Credit</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_MiraeAsset" class="input-hidden"
                                 value="MiraeAsset"
                                 onclick="getServiceName(this)"/>
                          <label for="_MiraeAsset">
                            <img src="/assets/images/icon/MCredit/Mirae@3x.png"
                                 alt="_MiraeAsset" class=""/>
                          </label>
                          <p class="text_btn">Mirae Asset</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="Maritime" class="input-hidden"
                                 value="Maritime"
                                 onclick="getServiceName(this)"/>
                          <label for="Maritime">
                            <img src="/assets/images/icon/button/Maritime@3x.png"
                                 alt="Maritime" class=""/>
                          </label>
                          <p class="text_btn">Maritime<br> Bank</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="Ocb" class="input-hidden"
                                 value="Ocb"
                                 onclick="getServiceName(this)"/>
                          <label for="Ocb">
                            <img src="/assets/images/icon/OCB/OCB.png"
                                 alt="Ocb" class=""/>
                          </label>
                          <p class="text_btn">OCB Credit</p>
                        </div>

                        <%--<div class="shortcutItem_btn">--%>
                        <%--<input type="radio" name="tprice_bill_financil"--%>
                        <%--id="_Prudential" class="input-hidden"--%>
                        <%--value="Prudential"--%>
                        <%--onclick="getServiceName(this)"/>--%>
                        <%--<label for="_Prudential">--%>
                        <%--<img src="/assets/images/icon/Prudential/Prudential_3x.png"--%>
                        <%--alt="_Prudential" class=""/>--%>
                        <%--</label>--%>
                        <%--<p class="text_btn">Prudential</p>--%>
                        <%--</div>--%>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="_Shinhan" class="input-hidden"
                                 value="Shinhan"
                                 onclick="getServiceName(this)"/>
                          <label for="_Shinhan">
                            <img src="/assets/images/icon/Shinhan/Shinhan_3x.png"
                                 alt="_Shinhan" class=""/>
                          </label>
                          <p class="text_btn">Shinhan</p>
                        </div>

                        <%--<div class="shortcutItem_btn">--%>
                        <%--<input type="radio" name="tprice_bill_financil"--%>
                        <%--id="PTI" class="input-hidden"--%>
                        <%--value="PTI"--%>
                        <%--onclick="getServiceName(this)"/>--%>
                        <%--<label for="PTI">--%>
                        <%--<img src="/assets/images/icon/button/PTI@3x.png"--%>
                        <%--alt="PTI" class=""/>--%>
                        <%--</label>--%>
                        <%--<p class="text_btn">Bảo hiểm PTI</p>--%>
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
                  <button type="button" id="action_services_financial"
                          class="btn btn-primary btn-sm pull-right">
                    <spring:message code="common.btn.payment"/>
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


  </div>

  <%--Dich vụ Internet TV--%>
  <div class="row">
    <div class="col-xxl-12 col-lg-12">
      <div class="panel panel-bordered">
        <div class="panel-heading">
          <h3 class="panel-title">DỊCH VỤ INTERNET VÀ TRUYỀN HÌNH</h3>
        </div>
        <div class="panel-body py-10">
          <form class="form-horizontal">
            <div class="form-group row mb-10">
              <label class="col-md-2 form-control-label px-0"></label>
              <div class="col-md-10">
                <div class="form-group">
                  <div class="row">
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="shortcutsList_btn">

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="FPT" class="input-hidden "
                                 value="FPT"
                                 onclick="getServiceName(this)"/>
                          <label for="FPT">
                            <img src="/assets/images/icon/button/FPT@3x.png"
                                 alt="FPT" class=""/>
                          </label>
                          <p class="text_btn">FPT Internet</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="KPlus" class="input-hidden "
                                 value="KPlus"
                                 onclick="getServiceName(this)"/>
                          <label for="KPlus">
                            <img src="/assets/images/icon/button/Kplus@3x.png"
                                 alt="KPlus" class=""/>
                          </label>
                          <p class="text_btn">K Plus</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="SPT" class="input-hidden "
                                 value="SPT"
                                 onclick="getServiceName(this)"/>
                          <label for="SPT">
                            <img src="/assets/images/icon/button/SPT@3x.png"
                                 alt="SPT" class=""/>
                          </label>
                          <p class="text_btn">SPT Internet</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="SPT_Phone" class="input-hidden"
                                 value="SPT_Phone"
                                 onclick="getServiceName(this)"/>
                          <label for="SPT_Phone">
                            <img src="/assets/images/icon/button/SPT@3x.png"
                                 alt="SPT_Phone" class=""/>
                          </label>
                          <p class="text_btn">Điện thoai cố<br> định SPT
                          </p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="SST" class="input-hidden"
                                 value="SST"
                                 onclick="getServiceName(this)"/>
                          <label for="SST">
                            <img src="/assets/images/icon/button/SST@3x.png"
                                 alt="SST" class=""/>
                          </label>
                          <p class="text_btn">SST Internet</p>
                        </div>


                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer py-10">
              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="button" id="action_bill_internet_tivi"
                          class="btn btn-primary btn-sm pull-right">
                    <spring:message code="common.btn.payment"/>
                    <i class="icon wb-arrow-right ml-10"></i>
                  </button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <%--end Dich vụ Internet TV--%>

  <%--mua sam--%>
  <div class="row">
    <div class="col-xxl-12 col-lg-12">
      <div class="panel panel-bordered">
        <div class="panel-heading">
          <h3 class="panel-title">DỊCH VỤ MUA SẮM</h3>
        </div>
        <div class="panel-body py-10">
          <form class="form-horizontal">
            <div class="form-group row mb-10">
              <label class="col-md-2 form-control-label px-0"></label>
              <div class="col-md-10">
                <div class="form-group">
                  <div class="row">
                    <div class="col-lg-12 col-md-6 col-sm-12">
                      <div class="shortcutsList_btn">

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="DT_MTB" class="input-hidden "
                                 value="dien-thoai-may-tinh-bang"
                                 onclick="getServiceName(this)"/>
                          <label for="DT_MTB">
                            <img src="/assets/images/icon/button/Dienthoai-maytinhbangagentweb.png"
                                 alt="DT_MTB" class=""/>
                          </label>
                          <p class="text_btn">Điện thoại</br>
                            Máy tính bảng</p>

                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="PK_TBS" class="input-hidden "
                                 value="thiet-bi-so-phu-kien-so"
                                 onclick="getServiceName(this)"/>
                          <label for="PK_TBS">
                            <img src="/assets/images/icon/button/Thietbiso-phukiensoagentweb.png"
                                 alt="PK_TBS" class=""/>
                          </label>
                          <p class="text_btn">Thiết bị số</br>
                            Phụ kiện số</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="LT_TBIT" class="input-hidden "
                                 value="laptop-thiet-bi-dien-tu"
                                 onclick="getServiceName(this)"/>
                          <label for="LT_TBIT">
                            <img src="/assets/images/icon/button/Laptop-mayvitinhagentweb.png"
                                 alt="LT_TBIT" class=""/>
                          </label>
                          <p class="text_btn">Laptop</br> Thiết bị điện
                            tử</p>
                        </div>


                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="DT_DL" class="input-hidden"
                                 value="dien-tu-dien-lanh"
                                 onclick="getServiceName(this)"/>
                          <label for="DT_DL">
                            <img src="/assets/images/icon/button/Dientu-dienlanhagentweb.png"
                                 alt="DT_DL" class=""/>
                          </label>
                          <p class="text_btn">Điện tử</br>Điện lạnh</p>
                        </div>

                        <div class="shortcutItem_btn">
                          <input type="radio" name="tprice_bill_financil"
                                 id="DGD" class="input-hidden"
                                 value="dien-gia-dung"
                                 onclick="getServiceName(this)"/>
                          <label for="DGD">
                            <img src="/assets/images/icon/button/Diengiadungagentweb.png"
                                 alt="DGD" class=""/>
                          </label>
                          <p class="text_btn">Điện gia dụng</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer py-10">
              <div class="row">
                <div class="col-md-9 offset-md-3">
                  <button type="button" id="action_shop"
                          class="btn btn-primary btn-sm pull-right">
                    <spring:message code="common.btn.payment"/>
                    <i class="icon wb-arrow-right ml-10"></i>
                  </button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <%--end mua sam--%>


  <div class="hidden">
    <form method="post" action="/pin-code/order-info" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="epin"></button>
    </form>
    <form method="post" action="/card-data/buy-card-3G" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="data3G"></button>
    </form>
    <form method="post" action="/game-code/buy-game-card" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="game"></button>
    </form>
    <form method="post" action="/topup" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="topup"></button>
    </form>
    <form method="post" action="/cashin-viettel-pay" class="hidden">
      <input type="hidden" name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
      <button class="hidden" id="viettelPay"></button>
    </form>
  </div>


</div>

<script src="/assets/development/js/my_format_currency.js"></script>
<script src="/assets/development/js/ajax-function-2272020/get_commission.js"></script>

<script>

  $(document).ready(function () {
    getServiceName(this);
  });

  function getServiceName(elem) {
    var _serviceCode = elem.value;
    if (_serviceCode === '' || _serviceCode === undefined) {
      _serviceCode = $("input[name='tprice_bill_financil']:checked").val();
    }
    if (null != _serviceCode) {
      if (_serviceCode == 'FEcredit' || _serviceCode == 'Homecredit' ||
        _serviceCode == 'Prudential' || _serviceCode == 'Shinhan' ||
        _serviceCode == 'Ocb' || _serviceCode == 'Acs' ||
        _serviceCode == 'MiraeAsset' || _serviceCode == 'MCredit' ||
        _serviceCode === 'AtmOnline' || _serviceCode === 'DrDong' ||
        _serviceCode === 'Maritime' || _serviceCode === 'PTI') {
        $('#action_services_financial').attr("onclick",
          "window.location.replace('/financial-services/".concat(_serviceCode).concat(
            "/management')"));
      } else if (_serviceCode == 'electric' || _serviceCode == 'water' || _serviceCode == 'internet'
        || _serviceCode == 'cable-television' || _serviceCode == 'VETC') {
        $('#action_bill_financial').attr("onclick",
          "window.location.replace('/bill-payment/".concat(_serviceCode).concat("/management')"));
      } else if (_serviceCode == 'FPT' || _serviceCode == 'KPlus'
        || _serviceCode == 'SPT' || _serviceCode == 'SPT_Phone' || _serviceCode == 'SST') {
        $('#action_bill_internet_tivi').attr("onclick",
          "window.location.replace('/bill-payment/".concat(_serviceCode).concat("/management')"));
      } else if (_serviceCode == 'epin') {
        $('#action_bill_financial').click(function () {
          formSubmit(_serviceCode);
        });
      } else if (_serviceCode == 'data3G') {
        $('#action_bill_financial').click(function () {
          formSubmit(_serviceCode);
        });
      }
      else if (_serviceCode == 'game') {
        $('#action_bill_financial').click(function () {
          formSubmit(_serviceCode);
        });
      } else if (_serviceCode == 'topup') {
        $('#action_bill_financial').click(function () {
          formSubmit(_serviceCode);
        });

      } else if (_serviceCode == 'dien-gia-dung' || _serviceCode == 'dien-tu-dien-lanh'
        || _serviceCode == 'laptop-thiet-bi-dien-tu' || _serviceCode == 'thiet-bi-so-phu-kien-so'
        || _serviceCode == 'dien-thoai-may-tinh-bang') {
        $('#action_shop').attr("onclick",
          "window.location.href='https://mall.zo-ta.com/collections/".concat(_serviceCode).concat(
            "'"));
      } else if(_serviceCode === 'viettelPay'){
        $('#action_bill_financial').click(function () {
          formSubmit(_serviceCode);
        });
      }
    }
  };

  function formSubmit(submitId) {
    $("#".concat(submitId)).trigger('click');
  }
</script>



