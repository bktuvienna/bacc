//adapted source from http://bost.ocks.org/mike/bar/
var currentBar;
var refreshBarChart = function(w,h){
	
	var barHeight=45;
	var scaleFactor = (w-5)/d3.max(dataset,function(array){
		return d3.max(array,Number);
	})
	var xValue = function(d){return d[0]*scaleFactor};	
	var yValue = function(d){return d[1]*scaleFactor};
	var zValue = function(d){return d[2]};
	var cValue = function(d) { return d[0];}, color = d3.scale.category10();
	
	var svg=d3.select("#barchart");
	
	var chart=svg.append("svg")
			.attr("width",w)
			.attr("height",h);
	
	var bar=chart.selectAll("g")
			.data(dataset)
			.enter().append("g")
			.attr("class",function(d){return d[2]})
			.attr("transform",function(d,i){return "translate(0,"+i*(barHeight+20)+")";});
	
	bar.append("rect")
		.attr("class","x")
		.attr("width",xValue)
		.attr("height",barHeight/2-1)
		.style("fill","black")
		.style("opacity",0.6);
		
	bar.append("rect")
		.attr("class","y")
		.attr("width",yValue)
		.attr("height",barHeight/2-1)
		.attr("transform","translate(0,"+barHeight/2+")")
		.style("fill","black")
		.style("opacity",0.6);
}

var highlightBars = function(value,color){
	if(currentBar){
		d3.select('.'+currentBar+' .x').style("fill","black");
		d3.select('.'+currentBar+' .y').style("fill","black");
	}
	currentBar = value;
	var barx = d3.select('.'+value+' .x');
	barx.style("fill",color);
	var bary = d3.select('.'+value+' .y');
	bary.style("fill",color);
}