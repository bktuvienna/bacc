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
	
	var margin = {top: 15, right: 30, bottom: 75, left: 40};
	var wSP=$('.inner-center').width()-margin.left-margin.right;
	var hSP=$('.inner-center').height()-margin.top-margin.bottom;//-$('.inner-center .header').height();
	var wBC=$('.outer-west').width()-10;
	var hBC=$('.outer-west').height()-50;
	refreshScatterPlot(margin,wSP,hSP);
	refreshBarChart(wBC,hBC);
	refreshValueTable();
});



