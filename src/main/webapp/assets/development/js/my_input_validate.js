// Pattern for email: ^[a-zA-Z0-9.]+@[a-zA-Z0-9]+(?:\\.[a-zA-Z]+)+$
// Pattern for phone number: ^(0|\\+\\([0-9]{2}\\)|\\+ \\([0-9]{2}\\)|\\+\\([0-9]{2}\\) |\\+ \\([0-9]{2}\\) )[0-9]{6,}$

$(document).ready(function () {
    var email_input = $('.email-input');
    if (email_input !== undefined) {
        email_input.attr('pattern', '^[a-zA-Z0-9.]+@[a-zA-Z0-9]+(?:\\.[a-zA-Z]+)+$');
        email_input.attr('title', 'Ví dụ: tenHomMail@mail.com');
    }

    var phone_number_input = $('.phone-number-input');
    if (phone_number_input !== undefined) {
        phone_number_input.attr('pattern', '^(0|\\+\\([0-9]{2}\\)|\\+ \\([0-9]{2}\\)|\\+\\([0-9]{2}\\) |\\+ \\([0-9]{2}\\) )[0-9]{6,}$');
        phone_number_input.attr('title', 'Ví dụ: 0123456... hoặc +(84)123456...');
    }

    // Disable all input except for number.
    var number_only_input = $('.number-only-input');
    if (number_only_input !== undefined) {
        number_only_input.on('keypress', function (event) {
            if (event.keyCode < 48 || event.keyCode > 57) {
                event.preventDefault();
            }
        });
    }
});