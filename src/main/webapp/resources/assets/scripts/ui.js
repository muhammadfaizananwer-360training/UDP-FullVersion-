var ui = function () {

    return {
  		isoTope:
  		{
  			container:'',
  			filterbutton:'',
  			lastFilter:'*',
  			init:function()
  			{
  				ui.isoTope.container = document.querySelector('#isotope');
  				new Isotope( ui.isoTope.container, {
  					layoutMode: 'fitRows',
  					getSortData:{
  						category: '[data-category]'
  					}
  				});
  			},
  			item:
  			{
  				add:function(elm){
  					ui.isoTope.container.isotope('insert', elm);
  				},
  				close:function(elm){
  					ui.isoTope.container.isotope( 'remove', $(elm).parent() ).isotope('layout');
  				}
  			}
  		}
	}
}();
