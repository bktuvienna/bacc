//Adapted sources for D3 scatterplot:
//http://alignedleft.com/tutorials/d3/making-a-scatterplot, http://bl.ocks.org/weiglemc/6185069
var dataset = [
                  [ 670,  350, "value1" ],
                  [ 560,  310, "value2" ],
                  [ 500,  280, "value3" ],
                  [ 460,  250, "value4" ],
                  [ 390,  210, "value5" ],
                  [ 310,  160, "value6" ],
                  [ 260,  180, "value7" ],
                  [ 190,  130, "value8" ],
                  [ 150,    80, "value9" ],
                  [ 120,    50, "value10" ]
              ];
var $prevClicked;
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
	
	var zValue = function(d){return d[2];}
	
	var cValue = function(d) { return d[0];}, color = d3.scale.category10();
	
	var svg = d3.select("#vis")
	.append("svg")
	.attr("width",w + 50)
	.attr("height",h + 50)
	.append("g")
	.attr("transform","translate(35,23)");
	
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
	.attr("class",function(d){return d[2]})
	.attr("r", 5)
	.style("fill",function(d){return color(cValue(d));})
	.on('mousedown',function(d){
		//changing radius of circle and highlighting value in table on click of circle
		//saving the previous clicked circle to reset it on the next click
		if($prevClicked){
			$prevClicked
			.attr("r",5);
		}
		$prevClicked = d3.select(this);
		
		//highlight values in tables and barcharts
		highlightValue(zValue(d),color(cValue(d)));
		highlightBars(zValue(d),color(cValue(d)));
		
		//increasing radius of clicked circle
		d3.select(this)
		.attr("r",10);
	});
	/*.on('mousedown',function(d){
		if($prevClicked!=null){
			
		}	
		$prevClicked=d3.select("."+zValue(d));
		$('#output').append("<div style='color:"+color(cValue(d))+"'>"+zValue(d)+" :"+xValue(d) + "/"+yValue(d)+"</div>");
		$('.'+zValue(d)).animate({
			
		})
	});
	.on("mouseover",function(d){
		$('#output').append("<div style='color:"+color(cValue(d))+"'>"+zValue(d)+" :"+xValue(d) + "/"+yValue(d)+"</div>");
		$('#'+zValue(d)).css({'background-color':color(cValue(d)),'transition-duration':'0.5s'});
	})
	.on("mouseout",function(d){
		$('#'+zValue(d)).css('background-color','white');
	});*/
}	