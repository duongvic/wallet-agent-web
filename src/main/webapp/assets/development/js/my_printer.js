function printElement() {
  executeAsync(function () {
      window.print();
  });
}

function executeAsync(func) {
  setTimeout(func, 0);
}