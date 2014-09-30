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
	
	//updating dataset and scatterplot depending on input values
	var wSP = $('#scatterplot').width()-50;
	var hSP = $('#scatterplot').height()-50;
	var wBC = $('#barchart').width();
	var hBC = $('#barchart').height();
	
	refreshScatterPlot(wSP,hSP);
	refreshBarChart(wBC,hBC);
});