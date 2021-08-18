<%@ page
        import="static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_AGENT" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="ID_AGENT" value="<%=ID_AGENT%>"/>
<footer class="site-footer">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="row">
                <div class="col-lg-7 col-md-7 col-sm-12">
                    <div class="site-footer-legal">
                        <nav>
                            <a href="https://zotahelp.freshdesk.com/en/support/solutions/articles/64000236271-giới-thiệu-về-zo-ta"
                               target="_blank"><spring:message code="label.about.me"/></a>
                            <a href="/lienHe" target="_blank"><spring:message
                                    code="label.contact"/></a>
                            <a href="https://zotahelp.freshdesk.com/en/support/solutions/articles/64000228332-chính-sách-hợp-tác"
                               target="_blank"><spring:message code="label.policy"/></a>
                            <a href="https://zotahelp.freshdesk.com/en/support/solutions/articles/64000236291-điều-khoản-sử-dụng-t-o-s-"
                               target="_blank"><spring:message code="label.term"/></a>
                        </nav>
                    </div>
                </div>
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <div class="site-footer-right row">
                        <nav>
                            <a href="tel:0888612468"><i class="icon pe-call"></i><spring:message
                                    code="common.phone.support"/></a>
                            <a href="cskh@zo-ta.com" target="_blank"><i
                                    class="icon pe-mail"></i><spring:message
                                    code="common.mail.support"/> </a>
                            <a class="btn btn-icon btn-pure"
                               href="https://www.facebook.com/ZOTAECOMMERCE" target="_blank"><i
                                    class="icon bd-facebook"></i></a>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${ID_AGENT == userLogin.customerTypeId}">
            <div class="col-lg-12 col-md-12">
                <div class="appstore">
                    <a href="#"><img src="/assets/images/googleplay.png" class="text-center ml-10"></a>
                    <a href="#"><img src="/assets/images/appstore.png" class="text-center"></a>
                    <div class="clr"></div>
                </div>
            </div>
        </c:if>
    </div>
</footer>

<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/babel-external-helpers.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/popper.min.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/bootstrap.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/app.js')}'></script><!-- pic iamge -->--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/animsition.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.mousewheel.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery-asScrollbar.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery-asScrollable.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery-asHoverScroll.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/switchery.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/select2.full.min.js')}'></script>--%>
<%--&lt;%&ndash;<script type="application/javascript" src='${urls.getForLookupPath('/static/js/typeahead.bundle.js')}'></script>&ndash;%&gt;--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/bootstrap-tokenfield.min.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/bootstrap-tagsinput.min.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/bootstrap-selects.js')}'></script>--%>
<%--&lt;%&ndash;<script type="application/javascript" src='${urls.getForLookupPath('/static/js/select2.js')}'></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script type="application/javascript" src='${urls.getForLookupPath('/static/js/bootstrap-tokenfield.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/bootstrap-tokenfield.js')}'></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script type="application/javascript" src='${urls.getForLookupPath('/static/js/bootstrap-tagsinput.js')}'></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script type="application/javascript" src='${urls.getForLookupPath('/static/js/bootstrap-select.js')}'></script>&ndash;%&gt;--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/intro.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/screenfull.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery-slidePanel.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.dataTables.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/dataTables.bootstrap4.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/dataTables.fixedHeader.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/dataTables.fixedColumns.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/dataTables.rowGroup.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/dataTables.scroller.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/dataTables.responsive.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery-ui.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/tmpl.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/canvas-to-blob.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/load-image.all.min.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.fileupload.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.fileupload-process.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.fileupload-image.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.fileupload-audio.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.fileupload-video.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.fileupload-validate.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.fileupload-ui.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/dropify.min.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/responsive.bootstrap4.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/dataTables.buttons.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/buttons.html5.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/buttons.flash.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/buttons.print.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/buttons.colVis.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/buttons.bootstrap4.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/owl.carousel.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/slick.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.webui-popover.min.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/jquery.toolbar.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/Component.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/Plugin.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/Base.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/Config.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/Menubar.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/GridMenu.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/Sidebar.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/PageAside.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/menu.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/colors.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/tour.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/Site.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/asscrollable.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/slidepanel.js')}'></script>--%>
<%--&lt;%&ndash;<script type="application/javascript" src='${urls.getForLookupPath('/static/js/switchery.js')}'></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script type="application/javascript" src='${urls.getForLookupPath('/static/js/owl-carousel.js')}'></script>&ndash;%&gt;--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/carousel.js')}'></script>--%>
<%--&lt;%&ndash;<script type="application/javascript" src='${urls.getForLookupPath('/static/js/datatables.js')}'></script>&ndash;%&gt;--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/datatable.js')}'></script>--%>
<%--&lt;%&ndash;<script type="application/javascript" src='${urls.getForLookupPath('/static/js/dropify.js')}'></script>&ndash;%&gt;--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/uploads.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/bootstrap-datepicker.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/multi-select.js')}'></script>--%>
<%--&lt;%&ndash;<script type="application/javascript" src='${urls.getForLookupPath('/static/js/advanced.js')}'></script>&ndash;%&gt;--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/webui-popover.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/toolbar.js')}'></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/static/js/tooltip-popover.js')}'></script>--%>
<%--<script src="/assets/development/js/button/button-ripple.js"></script>--%>
<%--<script type="application/javascript" src='${urls.getForLookupPath('/assets/development/vendor/multiselect/bootstrap-multiselect.js')}'></script>--%>

<%--<script type="application/javascript" src='${urls.getForLookupPath('/assets/development/js/my_drop_down_button.js')}'></script>--%>
<%--<script src="/assets/development/js/my_format_currency.js"></script>--%>
<%--<script src="/assets/development/js/ajax-function/get-commission.js"></script>--%>

<%--<script type="application/javascript" src='${urls.getForLookupPath('/assets/development/js/daterangepicker.min.js')}'></script>--%>

<%--<script type="application/javascript" src='${urls.getForLookupPath('/assets/development/js/moment.min.js')}'></script>--%>


<jsp:include page="./footer_js.jsp">
    <jsp:param name="include_google_chart" value="${param.include_google_chart}"/>
</jsp:include>

<script>

  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
    });
  })(document, window, jQuery);

  /*$(document).ready(function(){
    //  fix select2 title
    $('.select2-selection__rendered').hover(function () {
      $(this).removeAttr('title');
    });

    $("[role=log].ui-helper-hidden-accessible").remove();
  });*/


  //Function show pass
  // function mouseoverPass(obj) {
  //   document.getElementById(obj).type = "text";
  // }
  //
  // function mouseoutPass(obj) {
  //   document.getElementById(obj).type = "password";
  // }

  //  function validateSTK() {
  //    var x = document.getElementById("validationSTK").value;
  //    var y = x.replace(/[-]+/g, "");
  //    var z = y.replace(/[A-Za-z0-9]{4}(?!$)/g, "\$&" + "-");
  //    document.getElementById("validationSTK").value = z;
  //  }

  /* $(document).ready(function () {
     var token = $("meta[name='_csrf']").attr("content");
     var header = $("meta[name='_csrf_header']").attr("content");
     $.ajaxSetup({
       beforeSend: function(xhr) {
         xhr.setRequestHeader(header, token);
       }
     });

     var searchUrl = location.search;
     if (searchUrl.indexOf('?') > -1) {
       if (searchUrl.indexOf('lang=en') > -1) {
         $("#langEn").attr("href", searchUrl);
         $("#langVi").attr("href", searchUrl.replace('lang=en', "lang=vi"));
       } else if (searchUrl.indexOf('lang=vi') > -1) {
         $("#langEn").attr("href", searchUrl.replace('lang=vi', "lang=en"));
         $("#langVi").attr("href", searchUrl);
       } else {
         $("#langEn").attr("href", searchUrl + '&lang=en');
         $("#langVi").attr("href", searchUrl + '&lang=vi');
       }
     } else {
       $("#langEn").attr("href", searchUrl + '?lang=en');
       $("#langVi").attr("href", searchUrl + '?lang=vi');
     }
   });*/

</script>


