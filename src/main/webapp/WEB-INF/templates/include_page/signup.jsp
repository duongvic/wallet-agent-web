
<script src="/assets/js/babel-external-helpers.js"></script>
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.js"></script>
<script src="/assets/js/app.js"></script><!-- pic iamge -->
<script src="/assets/js/animsition.js"></script>
<script src="/assets/js/jquery.mousewheel.js"></script>
<script src="/assets/js/jquery-asScrollbar.js"></script>
<script src="/assets/js/jquery-asScrollable.js"></script>
<script src="/assets/js/jquery-asHoverScroll.js"></script>
<script src="/assets/js/switchery.js"></script>
<script src="/assets/js/select2.full.min.js"></script>
<%--<script src="/assets/js/typeahead.bundle.js"></script>--%>
<script src="/assets/js/bootstrap-tokenfield.min.js"></script>
<script src="/assets/js/bootstrap-tagsinput.min.js"></script>
<script src="/assets/js/bootstrap-selects.js"></script>
<%--<script src="/assets/js/select2.js"></script>--%>
<%--<script src="/assets/js/bootstrap-tokenfield.js"></script>
<script src="/assets/js/bootstrap-tokenfield.js"></script>--%>
<%--<script src="/assets/js/bootstrap-tagsinput.js"></script>--%>
<%--<script src="/assets/js/bootstrap-select.js"></script>--%>
<script src="/assets/js/intro.js"></script>
<script src="/assets/js/screenfull.js"></script>
<script src="/assets/js/jquery-slidePanel.js"></script>
<script src="/assets/js/jquery.dataTables.js"></script>
<script src="/assets/js/dataTables.bootstrap4.js"></script>
<script src="/assets/js/dataTables.fixedHeader.js"></script>
<script src="/assets/js/dataTables.fixedColumns.js"></script>
<script src="/assets/js/dataTables.rowGroup.js"></script>
<script src="/assets/js/dataTables.scroller.js"></script>
<script src="/assets/js/dataTables.responsive.js"></script>
<script src="/assets/js/jquery-ui.js"></script>
<script src="/assets/js/tmpl.js"></script>
<script src="/assets/js/canvas-to-blob.js"></script>
<script src="/assets/js/load-image.all.min.js"></script>
<script src="/assets/js/jquery.fileupload.js"></script>
<script src="/assets/js/jquery.fileupload-process.js"></script>
<script src="/assets/js/jquery.fileupload-image.js"></script>
<script src="/assets/js/jquery.fileupload-audio.js"></script>
<script src="/assets/js/jquery.fileupload-video.js"></script>
<script src="/assets/js/jquery.fileupload-validate.js"></script>
<script src="/assets/js/jquery.fileupload-ui.js"></script>
<script src="/assets/js/dropify.min.js"></script>
<script src="/assets/js/responsive.bootstrap4.js"></script>
<script src="/assets/js/dataTables.buttons.js"></script>
<script src="/assets/js/buttons.html5.js"></script>
<script src="/assets/js/buttons.flash.js"></script>
<script src="/assets/js/buttons.print.js"></script>
<script src="/assets/js/buttons.colVis.js"></script>
<script src="/assets/js/buttons.bootstrap4.js"></script>
<script src="/assets/js/owl.carousel.js"></script>
<script src="/assets/js/slick.js"></script>
<script src="/assets/js/jquery.webui-popover.min.js"></script>
<script src="/assets/js/jquery.toolbar.js"></script>
<script src="/assets/js/Component.js"></script>
<script src="/assets/js/Plugin.js"></script>
<script src="/assets/js/Base.js"></script>
<script src="/assets/js/Config.js"></script>
<script src="/assets/js/Menubar.js"></script>
<script src="/assets/js/GridMenu.js"></script>
<script src="/assets/js/Sidebar.js"></script>
<script src="/assets/js/PageAside.js"></script>
<script src="/assets/js/menu.js"></script>
<script src="/assets/js/colors.js"></script>
<script src="/assets/js/tour.js"></script>
<script src="/assets/js/Site.js"></script>
<script src="/assets/js/asscrollable.js"></script>
<script src="/assets/js/slidepanel.js"></script>
<%--<script src="/assets/js/switchery.js"></script>--%>
<%--<script src="/assets/js/owl-carousel.js"></script>--%>
<script src="/assets/js/carousel.js"></script>
<script src="/assets/js/datatables.js"></script>
<script src="/assets/js/datatable.js"></script>
<%--<script src="/assets/js/dropify.js"></script>--%>
<script src="/assets/js/uploads.js"></script>
<script src="/assets/js/bootstrap-datepicker.js"></script>
<script src="/assets/js/multi-select.js"></script>
<%--<script src="/assets/js/advanced.js"></script>--%>
<script src="/assets/js/webui-popover.js"></script>
<script src="/assets/js/toolbar.js"></script>
<script src="/assets/js/tooltip-popover.js"></script>

<script src="/assets/development/js/button/button-ripple.js"></script>

<script src="/assets/js/theme.js"></script>
<script src="/assets/js/theme.init.js"></script>

<script>
  (function (document, window, $) {
    'use strict';
    var Site = window.Site;
    $(document).ready(function () {
      Site.run();
    });
  })(document, window, jQuery);

  $(document).ready(function(){
    //  fix select2 title
    $('.select2-selection__rendered').hover(function () {
      $(this).removeAttr('title');
    });

    $('.select2-dropdown--below').hover(function () {
      $(this).css("background-color", "yellow");
    })

    $("[role=log].ui-helper-hidden-accessible").remove();
  });


  //Function show pass
  function mouseoverPass(obj) {
    document.getElementById(obj).type = "text";
  }

  function mouseoutPass(obj) {
    document.getElementById(obj).type = "password";
  }
</script>