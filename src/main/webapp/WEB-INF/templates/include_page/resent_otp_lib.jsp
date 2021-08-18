<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- js footer  -->
<script src="/assets/development/js/resent_otp_reference.js"></script>
<script src="/assets/development/vendor/jQuery.countdown/jquery.countdown.min.js"></script>
<script src="/assets/development/vendor/daterangepicker/moment.min.js"></script>



<script type="text/javascript">
  var finalDate = moment().add(2, 'minutes').format("YYYY/MM/DD HH:mm:ss");
  $('#clock').countdown(finalDate)
  .on('update.countdown', function (event) {
    var format = '%M:%S';
    if (event.offset.totalDays > 0) {
      format = '%-d day%!d ' + format;
    }
    if (event.offset.weeks > 0) {
      format = '%-w week%!w ' + format;
    }
    $(this).html(event.strftime(format));
  })
  .on('finish.countdown', function (event) {
    $(this).html('<spring:message code="label.limited.otp" />').parent().addClass('disabled');
    $('#resend').attr("style", "display: inline !important");
    $('#resendAjax').attr("style", "display: inline !important");
    $('#countdown').hide();
  });
</script>
<!-- /js footer -->