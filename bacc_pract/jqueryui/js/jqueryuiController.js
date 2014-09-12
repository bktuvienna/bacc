var myLayout;

$(document).ready(function(){
	$('#container').height($(document).height()-'10em');
	/*$('.panel').draggable({
		snap:true,
		containment:'left',
		containment:'center',
		containment:'right'
	});*/
	myLayout = $('#container').layout({
		// some pane-size settings
		 west__minSize: 100
		, east__size: 300
		, east__minSize: 200
		, east__maxSize: .5 // 50% of layout width
		, center__minWidth: 100
		// some pane animation settings
		, west__animatePaneSizing: false
		, west__fxSpeed_size: "fast" // 'fast' animation when resizing west-pane
		, west__fxSpeed_open: 1000 // 1-second animation when opening west-pane
		, west__fxSettings_open: { easing: "easeOutBounce" } // 'bounce' effect when opening
		, west__fxName_close: "none" // NO animation when closing west-pane
		// enable showOverflow on west-pane so CSS popups will overlap north pane
		, west__showOverflowOnHover: true
		, showDebugMessages: true
		});
});



