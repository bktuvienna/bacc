$(document).ready(function(){
	var height=$(window).height() - $('header').height();
	console.log(height);
	var width=$(window).width;

		
	$('#container').jqxDocking({
		orientation:'horizontal',
		width: width,
		mode:'docked'
	});
	
	
	$('#container').jqxDocking(
		'enableWindowResize','window1'
	);
	

});