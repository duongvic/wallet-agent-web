<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title>Loading - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<div class="page">
  <div class="page-content container-fluid ">
    <div class="example-loading example-well vertical-align text-center" style="margin: 30vh">
      <div class="loader vertical-align-middle loader-grill"></div>
    </div>
  </div>
</div>
<!-- footer -->
<%--<c:import url="../include_page/footer.jsp"/>--%>
<!-- /footer -->
<script src="js/babel-external-helpers.js"></script>
<script src="js/jquery.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/animsition.js"></script>
<script src="js/jquery.mousewheel.js"></script>
<script src="js/jquery-asScrollbar.js"></script>
<script src="js/jquery-asScrollable.js"></script>
<script src="js/jquery-asHoverScroll.js"></script>
<script src="js/switchery.js"></script>
<script src="js/intro.js"></script>
<script src="js/screenfull.js"></script>
<script src="js/jquery-slidePanel.js"></script>
<script src="js/jquery.dataTables.js"></script>
<script src="js/dataTables.bootstrap4.js"></script>
<script src="js/dataTables.fixedHeader.js"></script>
<script src="js/dataTables.fixedColumns.js"></script>
<script src="js/dataTables.rowGroup.js"></script>
<script src="js/dataTables.scroller.js"></script>
<script src="js/dataTables.responsive.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/tmpl.js"></script>
<script src="js/canvas-to-blob.js"></script>
<script src="js/load-image.all.min.js"></script>
<script src="js/jquery.fileupload.js"></script>
<script src="js/jquery.fileupload-process.js"></script>
<script src="js/jquery.fileupload-image.js"></script>
<script src="js/jquery.fileupload-audio.js"></script>
<script src="js/jquery.fileupload-video.js"></script>
<script src="js/jquery.fileupload-validate.js"></script>
<script src="js/jquery.fileupload-ui.js"></script>
<script src="js/dropify.min.js"></script>
<script src="js/responsive.bootstrap4.js"></script>
<script src="js/dataTables.buttons.js"></script>
<script src="js/buttons.html5.js"></script>
<script src="js/buttons.flash.js"></script>
<script src="js/buttons.print.js"></script>
<script src="js/buttons.colVis.js"></script>
<script src="js/buttons.bootstrap4.js"></script>
<script src="js/owl.carousel.js"></script>
<script src="js/slick.js"></script>
<script src="js/Component.js"></script>
<script src="js/Plugin.js"></script>
<script src="js/Base.js"></script>
<script src="js/Config.js"></script>
<script src="js/Menubar.js"></script>
<script src="js/GridMenu.js"></script>
<script src="js/Sidebar.js"></script>
<script src="js/PageAside.js"></script>
<script src="js/menu.js"></script>
<script src="js/colors.js"></script>
<script src="js/tour.js"></script>
<script src="js/Site.js"></script>
<script src="js/asscrollable.js"></script>
<script src="js/slidepanel.js"></script>
<script src="js/switchery.js"></script>
<script src="js/owl-carousel.js"></script>
<script src="js/carousel.js"></script>
<script src="js/datatables.js"></script>
<script src="js/datatable.js"></script>
<script src="js/dropify.js"></script>
<script src="js/uploads.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
    });
  })(document, window, jQuery);
</script>
</body>

</html>