function DropDown(el) {
  this.dd = el;
  this.placeholder = this.dd.children('span');
  this.opts1 = this.dd.find('ul.dropdown > li span');
  this.opts2 = this.dd.find('ul.dropdown > li p');
  this.val = '';
  this.index = -1;
  this.initEvents();
}


DropDown.prototype = {
  initEvents : function() {
    var obj = this;

    obj.dd.on('click', function(event){
      $(this).toggleClass('active');
      return false;
    });

    obj.opts1.on('click',function(){
      var opt = $(this);
      obj.val = opt.text();
      obj.index = opt.index();
      obj.placeholder.text('Trạng thái: ' + obj.val);
    });

    obj.opts2.on('click',function(){
      var opt = $(this);
      obj.val = opt.text();
      obj.index = opt.index();
      obj.placeholder.text('Trạng thái: ' + obj.val);
    });
  },
  getValue : function() {
    return this.val;
  },
  getIndex : function() {
    return this.index;
  }
}

$(function() {
  var dd = new DropDown( $('#dd') );
  $(document).click(function() {
    // all dropdowns
    $('.wrapper-dropdown-5').removeClass('active');
  });

});