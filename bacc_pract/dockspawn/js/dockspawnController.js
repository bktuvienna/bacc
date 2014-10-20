window.onload = function() {
	//performance measurement start
	var start=window.performance.now();
	//converting a div to a dock manager object
	var divDockManager = document.getElementById('dockManager');
	var dockManager = new dockspawn.DockManager(divDockManager);
	dockManager.initialize();
	//dock manager element should fill the entire screen
	var onResized = function(e)
		{
			dockManager.resize(window.innerWidth - (divDockManager.clientLeft + divDockManager.offsetLeft), window.innerHeight - (divDockManager.clientTop + divDockManager.offsetTop));
		}
	window.onresize = onResized;
	onResized(null);
	
	//convert divs to dock spawn panels
	var values = new dockspawn.PanelContainer(document.getElementById('values'),dockManager);
	var scatterplot = new dockspawn.PanelContainer(document.getElementById('scatterplot'),dockManager);
	var output = new dockspawn.PanelContainer(document.getElementById('output'),dockManager);
	var console = new dockspawn.PanelContainer(document.getElementById('console'),dockManager);
	
	//docking panels to each other
	var documentNode = dockManager.context.model.documentManagerNode;
	var scatterplotNode = dockManager.dockFill(documentNode, scatterplot)	
	var valuesNode = dockManager.dockLeft(scatterplotNode, values,0.15);
	var consoleNode = dockManager.dockDown(documentNode,console,0.30);
	var outputNode = dockManager.dockRight(scatterplotNode,output,0.15);

	//updating dataset and scatterplot depending on input values
	var margin = {top: 15, right: 15, bottom: 30, left: 40};
	var wSP = $('#scatterplot').width()-margin.left-margin.right;
	var hSP = $('#scatterplot').height()-margin.top-margin.bottom;
	var wBC = $('#barchart').width();
	var hBC = $('#values').height();
	
	var json;
	
	$("#savestate").on('click',function(){
		json = dockManager.saveState();	
	});
	
	$("#loadstate").on('click',function(){
		if(json)
			dockManager.loadState(json);
		else
			alert("You have to save a state before loading!");
	});
	//performance measurement end
	var end = window.performance.now();
	//workaround for panels under the visualization (resizing of svg element in vis element)
	/*$(window).mouseup(function(){
		$('#vis').empty();
		var wSP = $('#scatterplot').width()-margin.left-margin.right;
		var hSP = $('#scatterplot').height()-margin.top-margin.bottom;
		refreshScatterPlot(margin,wSP,hSP);
	});	*/
	refreshScatterPlot(margin,wSP,hSP);
	refreshBarChart(wBC,hBC);
	refreshValueTable();
	//performance measurement analysis	
	$('#console').append("<br><br>Javascript code for docking took "+parseInt(end-start)+" ms to execute.");
	window.console.log(end-start);
}

			  