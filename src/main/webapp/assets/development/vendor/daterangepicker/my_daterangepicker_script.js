/*
**
*** Scripts ***
**
*/
(function ($) {
  'use strict';
  $(document).ready(function () {
    /**== Date time ==**/
    $(function() {
      var start = moment().startOf('month');
      var end = moment().endOf('day');

      function cb(start, end) {
        $('#reportrange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
        $('#reservation').val(start.format('DD/MM/YYYY HH:mm:ss') + ' - ' + end.format('DD/MM/YYYY HH:mm:ss'));
      }

      $('#reportrange').daterangepicker({
        timePicker: false,
        startDate: start,
        endDate: end,
        ranges: {
          'Hôm nay': [moment().startOf('day'), moment().endOf('day')],
          'Hôm qua': [moment().subtract(1, 'days').startOf('day'), moment().subtract(1, 'days').endOf('day')],
          '7 ngày trước': [moment().subtract(6, 'days').startOf('day'), moment().endOf('day')],
          '30 ngày trước': [moment().subtract(29, 'days').startOf('day'), moment().endOf('day')],
          'Tháng này': [moment().startOf('month').startOf('day'), moment().endOf('month').endOf('day')],
          'Tháng trước': [moment().subtract(1, 'month').startOf('month').startOf('day'), moment().subtract(1, 'month').endOf('month').endOf('day')]
        },
        locale: {
        	format: 'DD/MM/YYYY',
        	applyLabel: 'Chọn',
        	cancelLabel: 'Hủy',
        	customRangeLabel: 'Tùy chọn',
        },
      }, cb);

      cb(start, end);

    });
  })
}(jQuery));