(function (){
  var checkboxes = document.getElementsByClassName("custom-control-input");

  if (checkboxes != null) {
    [].forEach.call(checkboxes, function(checkbox) {
      checkbox.addEventListener('click', function (e) {
          // submit checkbox form
           checkbox.form.submit();
      });
    });
  }
})();