//adapted source from http://bost.ocks.org/mike/bar/

var refreshBarChart = function(w,h){
	
	var barHeight=45;
	var scaleFactor = (w-5)/d3.max(dataset,function(array){
		return d3.max(array,Number);
	})
	var xValue = function(d){return d[0]*scaleFactor};	
	var yValue = function(d){return d[1]*scaleFactor};
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
		.style("fill","steelblue");
		
	bar.append("rect")
		.attr("class","y")
		.attr("width",yValue)
		.attr("height",barHeight/2-1)
		.attr("transform","translate(0,"+barHeight/2+")")
		.style("fill","darkred");
}	