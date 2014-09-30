$(document).ready(function() {
	var myDocker = new wcDocker(document.body);
	if(myDocker) {
		var layoutConfiguration;
		var currentTheme = 'Default';
		
		myDocker.registerPanelType('Values', function(myPanel){
			myPanel.layout().addItem($('<div id="values"><div id="barchart"></div></div>'));
			myPanel.initSize(250,400);
		});
		
		myDocker.registerPanelType('Scatterplot', function(myPanel){
			myPanel.layout().addItem($('<div id="scatterplot"><div id="vis"></div></div>'));
			myPanel.initSize(900,100);
		});
		
		myDocker.registerPanelType('Output', function(myPanel){
			myPanel.layout().addItem($('<div id="output"></div>'));
			myPanel.initSize(100,100);
		});
		
		myDocker.registerPanelType('Console', function(myPanel){
			myPanel.layout().addItem($('<div id="console"></div>'));
			myPanel.initSize(400,350);
		});
		
		var panel1 = myDocker.addPanel('Scatterplot',wcDocker.DOCK_BOTTOM, false);
		var panel3 = myDocker.addPanel('Output',wcDocker.DOCK_RIGHT, false);
		var panel2 = myDocker.addPanel('Console',wcDocker.DOCK_BOTTOM, false);		
		var panel4 = myDocker.addPanel('Values',wcDocker.DOCK_LEFT, false);
		
		 myDocker.basicMenu('.testMenu', [
			{name: 'customMenu1', callback: function(key, opts, panel){alert(key);}},
			{name: 'customMenu2', callback: function(key, opts, panel){alert(key);}},
			{name: 'customMenu3', callback: function(key, opts, panel){alert(key);}}
			], true); 
	}
	$('#output').width($('#output').parent().width());
	$('#output').height($('#output').parent().height());
	$('#console').width($('#console').parent().width());
	$('#console').height($('#console').parent().height());
	
	var wSP = $('#scatterplot').parent().width()-100;
	var hSP = $('#scatterplot').parent().height()-60;
	var wBC = $('#values').parent().width();
	var hBC = $('#values').parent().height()-40;
	refreshScatterPlot(wSP,hSP);
	refreshBarChart(wBC,hBC);

});