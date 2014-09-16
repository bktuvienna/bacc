//Source for D3 scatterplot(scatterplot): http://alignedleft.com/tutorials/d3/making-a-scatterplot
var dataset = [
                  [ 5,     20 ],
                  [ 480,   90 ],
                  [ 250,   50 ],
                  [ 100,   33 ],
                  [ 330,   95 ],
                  [ 410,   12 ],
                  [ 475,   44 ],
                  [ 25,    67 ],
                  [ 85,    21 ],
                  [ 220,   88 ]
              ];

var w = 500;
var h = 100;	  

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
	var valuesNode = dockManager.dockLeft(scatterplotNode, values,0.25);
	var outputNode = dockManager.dockRight(scatterplotNode,output,0.15);
	var consoleNode = dockManager.dockDown(scatterplotNode,console,0.30);
	
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
		refreshScatterPlot();		
	});	
	refreshScatterPlot();
}

var refreshScatterPlot = function(){
	var svg = d3.select("#vis")
	.append("svg")
	.attr("width",w)
	.attr("height",h);
	
	svg.selectAll("circle")
	.data(dataset)
	.enter()
	.append("circle")
	.attr("cx", function(d) {
		return d[0];
	})
	.attr("cy", function(d) {
		return d[1];
	})
	.attr("r", 5);
}	
			  