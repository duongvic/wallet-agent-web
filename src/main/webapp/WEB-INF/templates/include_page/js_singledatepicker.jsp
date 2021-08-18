<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script src="<c:url value="/assets/development/vendor/daterangepicker/moment.min.js"/> "></script>
<script src="<c:url value="/assets/development/vendor/daterangepicker/daterangepicker.js"/> "></script>

<spring:message code="daterange.locale.30daysBeforeLabel" var="_30daysBeforeLabel"/>
<spring:message code="daterange.locale.7daysBeforeLabel" var="_7daysBeforeLabel"/>
<spring:message code="daterange.locale.todayLabel" var="todayLabel"/>
<spring:message code="daterange.locale.fulltime" var="fullTime"/>
<spring:message code="daterange.locale.yesterdayLabel" var="yesterdayLabel"/>
<spring:message code="daterange.locale.thisMonthLabel" var="thisMonthLabel"/>
<spring:message code="daterange.locale.prevMonthLabel" var="prevMonthLabel"/>
<spring:message code="daterange.locale.customRangeLabel" var="customRangeLabel"/>
<spring:message code="daterange.locale.cancelLabel" var="cancelLabel"/>
<spring:message code="daterange.locale.applyLabel" var="applyLabel"/>
<script>
  $(function() {
    $('input[name="birthday"]').daterangepicker({
      singleDatePicker: true,
      showDropdowns: true,
      locale: {
        format: 'DD-MM-YYYY'
      }
    }, function(start, end, label) {
      var years = moment().diff(start, 'years');
      alert("You are " + years + " years old!");
    });
  });
</script>

<link rel="stylesheet"
      href="<c:url value='/assets/development/vendor/daterangepicker/daterangepicker.css'/>">
