var layout;

$(document).ready(function(){
	//performance measurement start
	var start = window.performance.now();
	
	var height=$(window).height() - $('header').height();
	console.log(height);
	var width=$(window).width;

	
	$('#currentmode').append('default');
	
	//defining the grouping of docked window - vertical vs horizontal
	$('#container').jqxDocking({
		orientation:'horizontal',
		width: width,
	});
		
	$('#container').jqxDocking(
		'enableWindowResize','window1'
	);
	
	$('#container').jqxDocking(
		'showCollapseButton','window1'
	);
	
	$('#container').on('dragEnd',function(event){
		alert('A view has been moved!');
	});
	
	//save/load functionality
	$('#save').click(function(){
		layout=$('#container').jqxDocking('exportLayout');
	});
	
	$('#load').click(function(){
		if(layout)
			$('#container').jqxDocking('importLayout',layout);
		else
			alert('no layout has been saved yet.');
	});
	
	//enable/disable docking
	$('#enable').click(function(){
		$('#container').jqxDocking('enable');
	});
	
	$('#disable').click(function(){
		$('#container').jqxDocking('disable');
	});
	
	var cnt=0;
	var modes = ['default','docked','floating'];
	$('#mode').click(function(){	
		$('#container').jqxDocking('setWindowMode', 'window1',modes[cnt]);
		$('#currentmode').empty().append(modes[cnt]);
		cnt++;
		if(cnt>=3)
			cnt=0;
	});
	//performance measurement end
	var end = window.performance.now();
	
	
	//updating dataset and scatterplot depending on input values
	var margin = {top: 15, right: 15, bottom: 30, left: 40};
	var wSP = $('#scatterplot').width()-margin.left-margin.right;
	var hSP = $('#scatterplot').height()-margin.top-margin.bottom;
	var wBC = $('#barchart').width();
	var hBC = $('#barchart').height();
	
	refreshScatterPlot(margin,wSP,hSP);
	refreshBarChart(wBC,hBC);
	refreshValueTable();
	//performance measurement analysis
	$('#console').append("<br>Javascript code for docking took "+parseInt(end-start)+" ms to execute.");
});