window.onload = function() {
	
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
	var w = $('#scatterplot').width()-50;
	var h = $('#scatterplot').height()-50;
	$('.values').change(function(){
		var tmp = [];
		$('.values').each(function(){
			tmp.push($(this).val());
		});		
		dataset=[];
		for(var i=0, l=tmp.length;i<l;i++){
			var data=[];
			for(var j=0;j<2;j++){
				data.push(tmp[i+j]);
				i=i+j;
			}
			dataset.push(data);
		}
		$('#vis').empty();
		refreshScatterPlot(w,h);		
	});
	
	//workaround for panels under the visualization (resizing of svg element in vis element)
	$(window).mouseup(function(){
		$('#vis').empty();
		w = $('#scatterplot').width()-50;
		h = $('#scatterplot').height()-50;
		refreshScatterPlot(w,h);
	});	
	refreshScatterPlot(w,h);
}

			  