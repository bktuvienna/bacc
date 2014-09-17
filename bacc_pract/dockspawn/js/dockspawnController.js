//Sources for D3 scatterplot(scatterplot): http://alignedleft.com/tutorials/d3/making-a-scatterplot, http://bl.ocks.org/weiglemc/6185069
var dataset = [
                  [ 50,     50 ],
                  [ 90,    80 ],
                  [ 190,  130 ],
                  [ 260,  180 ],
                  [ 310,  160 ],
                  [ 390,  210 ],
                  [ 460,  250 ],
                  [ 500,  280 ],
                  [ 560,  310 ],
                  [ 670,  350 ]
              ];


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

	//updating dataset depending on input values
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

//updates scatterplot and draws initial scatterplot
var refreshScatterPlot = function(w,h){
	
	var xValue = function(d){return d[0];},
		xScale = d3.scale.linear().range([0,w]),
		xMap = function(d){return xScale(xValue(d));},
		xAxis = d3.svg.axis().scale(xScale).orient("bottom");
		
	var yValue = function(d){return d[1];},
		yScale = d3.scale.linear().range([h,0]),
		yMap = function(d){return yScale(yValue(d));},
		yAxis = d3.svg.axis().scale(yScale).orient("left");
	
	var cValue = function(d) { return d[0];}, color = d3.scale.category10();
	
	var svg = d3.select("#vis")
	.append("svg")
	.attr("width",w + 50)
	.attr("height",h + 50);
	
	svg.append('g')
	.attr("class","x axis")
	.attr("transform","translate(0," + h +")")
	.call(xAxis)
	.append("text")
	.attr("class","label")
	.attr("x", w)
	.attr("y",-6)
	.style("text-anchor","end")
	.text("X AXIS");
	
	svg.append('g')
	.attr("class","y axis")
	.call(yAxis)
	.append("text")
	.attr("class","label")
	.attr("transform","rotate(-90)")
	.attr("y",6)
	.attr("dy","0.71em")
	.style("text-anchor","end")
	.text("Y AXIS");
		
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
	.attr("r", 5)
	.style("fill",function(d){return color(cValue(d));})
	.on("mouseover",function(d){
		$('#output').append("Hovered :"+xValue(d) + "/"+yValue(d)+"<br>");
	});
}	
			  