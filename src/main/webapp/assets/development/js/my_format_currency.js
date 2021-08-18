function calculationFee(inputId, discountId, resultId) {
  var percent = parseFloat($(discountId).text()) / 100.0;
  $(resultId).html(
      "<b>"
      + formatCurrency($(inputId).val() - $(inputId).val()
      * percent) + "</b>");
}

function format(currency) {
  if (currency.length > 3) {
    var length = currency.length;
    var newCurrency;
    var remainPart;

    newCurrency = currency.substring(length - 3, length);
    remainPart = currency.substring(0, length - newCurrency.length);
    remainPart = format(remainPart);

    return remainPart + "." + newCurrency;
  } else {
    return currency;
  }
}

function formatCurrency(input) {
  var currency = parseInt(input).toString();

  return format(currency);
}

function formatNumber(input) {
  var number = parseInt(input).toString();

  return format(number);
}

function currencyToNumber(currency) {
  if (currency !== undefined) {
    return currency.replace(/[^0-9]+/g, "");
  }
}

$(".currency-input").each(function () {
  $(this).on('input', function () {
    var numberVal = currencyToNumber($(this).val());
    if (numberVal !== "") {
      numberVal = formatCurrency(numberVal);
    } else {
      numberVal = formatCurrency(0);
    }
    $(this).val(numberVal);
    $(this)[0].selectionStart = $(this)[0].selectionEnd = numberVal.length;
  });
});
$(".card-number-input").each(function () {
  $(this).on('input', function () {
    var y = $(this).val().replace(/[-]+/g, "");
    if (y.length > 20) {
      y = y.substr(0 ,20);
    }
    var z = y.replace(/[^-]{4}(?!$)/g, "\$&" + "-");
    $(this).val(z);
  });
});
$(".card-number-input-nm").each(function () {
  $(this).on('input', function() {
    var y = $(this).val().replace(/[-]+/g, "");
    if (y.length > 20) {
      y = y.substr(0 ,20);
    }
    var z = y.replace(/[^-]{4}(?!$)/g, "\$&" + "-");
    $(this).val(z);
  });
});
$(document).ready(function () {
  // format currency
  var currencyInput = $(".currency-input");
  currencyInput.each(function () {
    if (typeof $(this) === "undefined") {
    } else {
      if (typeof $(this).val() === "undefined") {
      } else {
        var numberVal = currencyToNumber($(this).val());
        if (numberVal !== "") {
          numberVal = formatCurrency(numberVal);
        } else {
          numberVal = formatCurrency(0);
        }
        $(this).val(numberVal);
      }

      if (typeof $(this).html() === "undefined") {
      } else {
        var numberVal = currencyToNumber($(this).html());
        if (numberVal !== "") {
          numberVal = formatCurrency(numberVal);
        } else {
          numberVal = formatCurrency(0);
        }
        $(this).html(numberVal);
      }
    }
  });

  // format number
  var numberInput = $(".number-input");
  numberInput.each(function () {
    if (typeof $(this) === "undefined") {
    } else {
      if (typeof $(this).val() === "undefined") {
      } else {
        var numberVal = currencyToNumber($(this).val());
        if (numberVal !== "") {
          numberVal = formatNumber(numberVal);
        } else {
          numberVal = formatNumber(0);
        }
        $(this).val(numberVal);
      }

      if (typeof $(this).html() === "undefined") {
      } else {
        var numberVal = currencyToNumber($(this).html());
        if (numberVal !== "") {
          numberVal = formatNumber(numberVal);
        } else {
          numberVal = formatNumber(0);
        }
        $(this).html(numberVal);
      }
    }
  });

  // format bank number
  var cardNumberInput = $(".card-number-input");
  cardNumberInput.each(function () {
    if ($(this) === undefined) {
    } else {
      // format bank number value
      if ($(this).val() === undefined) {

      } else {
        var cardNumber = $(this).val().replace(/[-]+/g, "");

        if (cardNumber.length < 16) {
          var firstDigits = cardNumber.substr(0, cardNumber.length - 4);
          var lastDigits = cardNumber.substr(cardNumber.length - 4, 4);
          var firstDigits = firstDigits.replace(/./g, "*");
          cardNumber = firstDigits + lastDigits;
        } else {
          var firstDigits = cardNumber.substr(0, 6);
          var lastDigits = cardNumber.substr(cardNumber.length - 4, 4);
          var middleDigits = cardNumber.substr(6, cardNumber.length - 6 - 4);
          var middleDigits = middleDigits.replace(/./g, "*");
          cardNumber = firstDigits + middleDigits + lastDigits;
        }

        var value = cardNumber.replace(/[^-]{4}(?!$)/g, "\$&" + "-");
        $(this).val(value);
      }
      // format bank number html
      if ($(this).html() === undefined) {

      } else {
        var cardNumber = $(this).html().replace(/[-]+/g, "");

        if (cardNumber.length < 16) {
          var firstDigits = cardNumber.substr(0, cardNumber.length - 4);
          var lastDigits = cardNumber.substr(cardNumber.length - 4, 4);
          var firstDigits = firstDigits.replace(/./g, "*");
          cardNumber = firstDigits + lastDigits;
        } else {
          var firstDigits = cardNumber.substr(0, 6);
          var lastDigits = cardNumber.substr(cardNumber.length - 4, 4);
          var middleDigits = cardNumber.substr(6, cardNumber.length - 6 - 4);
          var middleDigits = middleDigits.replace(/./g, "*");
          cardNumber = firstDigits + middleDigits + lastDigits;
        }

        var value = cardNumber.replace(/[^-]{4}(?!$)/g, "\$&" + "-");
        $(this).html(value);
      }
    }
  });

  // format bank number
  var cardNumberInput = $(".card-number-input-nm");
  cardNumberInput.each(function () {
    if ($(this) === undefined) {
    } else {
      // format bank number value
      if ($(this).val() === undefined) {

      } else {
        var cardNumber = $(this).val().replace(/[-]+/g, "");
        var value = cardNumber.replace(/[^-]{4}(?!$)/g, "\$&" + "-");
        $(this).val(value);
      }
      // format bank number html
      if ($(this).html() === undefined) {

      } else {
        var cardNumber = $(this).html().replace(/[-]+/g, "");
        var value = cardNumber.replace(/[^-]{4}(?!$)/g, "\$&" + "-");
        $(this).html(value);
      }
    }
  });
});
