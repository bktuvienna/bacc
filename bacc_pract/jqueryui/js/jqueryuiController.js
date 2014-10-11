var myLayout;

$(document).ready(function(){
	var height=$(window).height() - $('header').height();
	$('#container').height(height);
	
	myLayout = $('#container').layout({
		applyDefaultStyles:true,
		center__paneSelector:".outer-center",
		west__paneSelector:".outer-west",
		west__onclose:function(){
			alert('The outer west view has been closed.');
		},
		enableCursorHotkey:true,
		center__childOptions: {
			applyDefaultStyles:true,
			center__paneSelector:".inner-center",
			east__paneSelector:".inner-east",
			east__slidable:true,
			south__paneSelector:".inner-south",
			south__closable:false,
			south__resizable:false,
		}	
	});
	myLayout.addToggleBtn(".toggle-outer-west","west");
	
	var wSP=$('.inner-center').width()-50;
	var hSP=$('.inner-center').height()-100;//-$('.inner-center .header').height();
	var wBC=$('.outer-west').width()-10;
	var hBC=$('.outer-west').height()-50;
	refreshScatterPlot(wSP,hSP);
	refreshBarChart(wBC,hBC);	
});



