(function ($) {
  'use strict';
  $(document).ready(function () {

    $('#invalid').hide();

    $('#resend').on("click", function () {
      var url = $(this).data("url");
      $.ajax({
        type: 'GET',
        url: url,
        success: function (json) {
          if (json !== null && json !== "") {
            if (json.code === 0) {

              $('#invalid').text('Gửi mã OTP thành công');
              $('#invalid').attr("style", "display: inline !important");

              $('#reference').val(json.message);

              $('#countdown').show();
              $('#clock').countdown(get3MinutesFromNow());
              $('#resend').attr("style", "display: none !important");
            } else {
              $('#invalid').text(json.message);
              $('#invalid').attr("style", "display: inline !important");
            }
          }
        },
        error: function (json) {
          $('#invalid').text('Gửi mã OTP xảy ra lỗi');
          $('#invalid').attr("style", "display: inline !important");
        }
      });
    });
  });
}(jQuery));

function get3MinutesFromNow() {
  return new Date(new Date().valueOf() + 2 * 60 * 1000);
}