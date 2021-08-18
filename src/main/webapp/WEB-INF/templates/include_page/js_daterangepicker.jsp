<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script src="<c:url value="/assets/development/vendor/daterangepicker/moment.min.js"/> "></script>
<script src="<c:url value="/assets/development/vendor/daterangepicker/daterangepicker.js"/> "></script>

<spring:message code = "daterange.locale.30daysBeforeLabel" var="_30daysBeforeLabel"/>
<spring:message code = "daterange.locale.7daysBeforeLabel" var="_7daysBeforeLabel"/>
<spring:message code = "daterange.locale.todayLabel" var="todayLabel"/>
<spring:message code = "daterange.locale.fulltime" var="fullTime"/>
<spring:message code = "daterange.locale.yesterdayLabel" var="yesterdayLabel"/>
<spring:message code = "daterange.locale.thisMonthLabel" var="thisMonthLabel"/>
<spring:message code = "daterange.locale.prevMonthLabel" var="prevMonthLabel"/>
<spring:message code = "daterange.locale.customRangeLabel" var="customRangeLabel"/>
<spring:message code = "daterange.locale.cancelLabel" var="cancelLabel"/>
<spring:message code = "daterange.locale.applyLabel" var="applyLabel"/>
<script>
    /*
**
*** Scripts ***
**
*/
    (function ($) {
        'use strict';
        $(document).ready(function () {
            /**== Date time ==**/
            $(function () {
                var start = moment([1970, 0, 1]);
                var end = moment().endOf('day');

                function cb(start, end) {
                  if (start.isSame(moment([1970, 0, 1])) && end.isSame(moment().endOf('day'))) {
                    $('#reportrange span').html('${fullTime}');
                    $('#reservation').val('');
                  } else {
                    $('#reportrange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                    $('#reservation').val(start.format('DD/MM/YYYY HH:mm:ss') + ' - ' + end.format('DD/MM/YYYY HH:mm:ss'));
                  }
                }

                $('#reportrange').daterangepicker({
                    timePicker: false,
                    startDate: start,
                    endDate: end,
                    ranges: {
                        '${fullTime}': [-1],
                        '${todayLabel}': [moment().startOf('day'), moment().endOf('day')],
                        '${yesterdayLabel}': [moment().subtract(1, 'days').startOf('day'), moment().subtract(1, 'days').endOf('day')],
                        '${_7daysBeforeLabel}': [moment().subtract(6, 'days').startOf('day'), moment().endOf('day')],
                        '${_30daysBeforeLabel}': [moment().subtract(29, 'days').startOf('day'), moment().endOf('day')],
                        '${thisMonthLabel}': [moment().startOf('month').startOf('day'), moment().endOf('month').endOf('day')],
                        '${prevMonthLabel}': [moment().subtract(1, 'month').startOf('month').startOf('day'), moment().subtract(1, 'month').endOf('month').endOf('day')]
                    },
                    locale: {
                        format: 'DD/MM/YYYY',
                        applyLabel: '${applyLabel}',
                        cancelLabel: '${cancelLabel}',
                        customRangeLabel: '${customRangeLabel}',
                    },
                }, cb);

                cb(start, end);

            });
        })
    }(jQuery));
</script>

<link rel="stylesheet" href="<c:url value='/assets/development/vendor/daterangepicker/daterangepicker.css'/>">
