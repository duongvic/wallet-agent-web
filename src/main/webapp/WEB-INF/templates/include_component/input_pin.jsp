<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-lg-12 col-md-8 mb-3 offset-md-0">
  <%--<div class="input-group">--%>

  <div class="center">
    <c:forEach begin="0" end="5" varStatus="step">
      <input type="text" id="otpPin${step.index}" name="pinPassword" class="otp-pin"
             maxlength="1" autocomplete="off" required>
    </c:forEach>

    <input type="hidden" id="paymentSecurityPIN"
           name="paymentSecurityCode"
           class="form-control" required maxlength="6" readonly
           placeholder="<spring:message code="label.enter.code"/>">
  </div>

  <%--</div>--%>
</div>

<script>
  var pins = [];
  $(document).ready(function() {
    var inputPIN = $('#paymentSecurityPIN');
    if (inputPIN !== undefined) {
      $('#otpPin0').focus();
      for(var i = 0; i < 6; ++i) {
        onKeyupPin(i);
      }

      var otpPIN =  $('.otp-pin');
      otpPIN.on("keydown", function (event) {
        if (event.keyCode !== undefined && $.isNumeric(event.key) || event.keyCode === 8) {
          if (event.keyCode !== 8) {
            $(this).val("*");
          }
          return true;
        } else {
          return false;
        }
      });

      otpPIN.click(function() {
        var element = document.getElementById($(this).attr('id'));
        element.setSelectionRange(element.value.length, element.value.length);
      });
    }
  });

  function onKeyupPin(i) {
    // OTP auto next
    $('#otpPin'.concat(i)).on("keyup", function (event) {
      if (event.keyCode !== undefined && $.isNumeric(event.key) || event.keyCode === 8) {
        if (event.keyCode !== 8) {
          $(this).val("*");
          $(this).next().focus();
          $(this).addClass('otp');
          pins[i] = event.key;
        } else {
          pins[i] = undefined;
          $(this).val("");
          $(this).removeClass('otp');
          $(this).prev().focus();
        }
      } else {
        return false;
      }
    });
  }

  $('#confirm-form').on("submit", function () {
    var inputPIN = $('#paymentSecurityPIN');
    if (inputPIN !== undefined) {
      inputPIN.val(pins.toString().replace(/,+/g, ""));
    }
  });
</script>