$('form').submit(function (e) {
  e.preventDefault();
  var submitElement = $(this).find("[type=submit]");
  if (submitElement === undefined) {
    submitElement = $(this).find("button:not([type])");
  }
  if (submitElement !== undefined) {
    var loadingText = '<i class="fa fa-spinner fa-spin"></i> loading...';
    if (submitElement !== loadingText) {
      submitElement.data('original-text', submitElement.html());
      submitElement.attr("disabled", "disabled");
      submitElement.html(loadingText);
    }
  }

  this.submit();
});