var ui = function () {

    return {
		
		init:function()
		{
			
		},
		
		isoTope:
		{
			container:'',
			filterbutton:'',
			lastFilter:'*',
			init:function()
			{
			
			/*
			
				*/
			/*
				$("#iso-filter-btns").children().each(function(i){
					
					$(this).click(function(e){
						e.preventDefault();
						var filter = $(this).attr('data-filter');
						$(this).addClass("active");
						$("button[data-filter='"+ui.isoTope.lastFilter+"']").removeClass("active");
						ui.isoTope.lastFilter = filter;
						ui.isoTope.container.isotope({ filter: filter });
					});
				});
				*/
				
				ui.isoTope.container = document.querySelector('#isotope');
				
				new Isotope( ui.isoTope.container, {
				
					layoutMode: 'fitRows',
					getSortData:{
						category: '[data-category]'
					}
				});
				//ui.isoTope.filterbutton = document.getElementById("iso-filter-btns");
				//console.log(ui.isoTope.filterbutton.children);
				//ui.isoTope.filterbutton.addEventListener("click", console.log("Click"));
				/*
				ui.isoTope.filterbutton.children.forEach(function(i){
					
					ui.isoTope.filterbutton.onClick(function(e){
						e.preventDefault();
						var filter = ui.isoTope.filterbutton.attr('data-filter');
						ui.isoTope.filterbutton.addClass("active");
						("button[data-filter='"+ui.isoTope.lastFilter+"']").removeClass("active");
						ui.isoTope.lastFilter = filter;
						ui.isoTope.container.isotope({ filter: filter });
					});
				});
				ui.isoTope.filterbutton.onClick(function(e){
						e.preventDefault();
						
						var filter = ui.isoTope.filterbutton.attr('data-filter');
						ui.isoTope.filterbutton.addClass("active");
						("button[data-filter='"+ui.isoTope.lastFilter+"']").removeClass("active");
						ui.isoTope.lastFilter = filter;
						ui.isoTope.container.isotope({ filter: filter });
					},this);
				});
				*/
				/* javascript version
				ui.isoTope.filterbutton.children.each(function(i){
					
					ui.isoTope.filterbutton.onClick(function(e){
						e.preventDefault();
						var filter = ui.isoTope.filterbutton.attr('data-filter');
						ui.isoTope.filterbutton.addClass("active");
						("button[data-filter='"+ui.isoTope.lastFilter+"']").removeClass("active");
						ui.isoTope.lastFilter = filter;
						ui.isoTope.container.isotope({ filter: filter });
					});
				});
				*/
				/*
				var filter = ui.isoTope.filterbutton.attr('data-filter');
						ui.isoTope.filterbutton.addClass("active");
						("button[data-filter='"+ui.isoTope.lastFilter+"']").removeClass("active");
						ui.isoTope.lastFilter = filter;
						ui.isoTope.container.isotope({ filter: filter });
						*/
			
				//ui.isoTope.lastFilter = filter;
				//ui.isoTope.container.isotope({ filter: filter });
				/*
				var sum = 0;
				ui.isoTope.filterbutton = document.getElementById("iso-filter-btns");
				ui.isoTope.filterbutton.children.forEach(function(i){
					
					ui.isoTope.filterbutton.onClick(function(e){
						e.preventDefault();
						
						var filter = ui.isoTope.filterbutton.attr('data-filter');
						ui.isoTope.filterbutton.addClass("active");
						("button[data-filter='"+ui.isoTope.lastFilter+"']").removeClass("active");
						ui.isoTope.lastFilter = filter;
						ui.isoTope.container.isotope({ filter: filter });
					},this);
				});
				*/
				/*
				function(i){
					
					ui.isoTope.filterbutton.onClick(function(e){
						//e.preventDefault();
						var filter = ui.isoTope.filterbutton.attr('data-filter');
						ui.isoTope.filterbutton.addClass("active");
						("button[data-filter='"+ui.isoTope.lastFilter+"']").removeClass("active");
						ui.isoTope.lastFilter = filter;
						ui.isoTope.container.isotope({ filter: filter });
					});
				}*/
				
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