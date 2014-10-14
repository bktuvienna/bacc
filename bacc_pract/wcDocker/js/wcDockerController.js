var state;
$(document).ready(function() {
	//wcDocker implementation
	$('#container').height($(window).height() - $('header').height());
	$('#container').width($(window).width());
	var myDocker = new wcDocker($('#container'));
	if(myDocker) {
		var layoutConfiguration;
		var currentTheme = 'Default';
		
		//registering of panels, refreshing necessary if panel gets closed and inserted again
		myDocker.registerPanelType('Values', {
			faicon: 'qrcode',
			onCreate: function(myPanel){
			myPanel.layout().addItem($('<div id="values"><div id="barchart"></div></div>'));
			myPanel.on(wcDocker.EVENT_MOVED, function(){
				alert('Values view was moved');
			});			
			myPanel.initSize(250,400);
			}
		});
		
		myDocker.registerPanelType('Scatterplot', {
			limit:1,
			onCreate: function(myPanel){
			myPanel.layout().addItem($('<div id="scatterplot"><div id="vis"></div></div>'));
			myPanel.initSize(1000,100);
			}
		});
		
		myDocker.registerPanelType('Output', function(myPanel){
			myPanel.layout().addItem($('<div id="output"><table id="valuetable"></table></div>'));
			myPanel.initSize(100,100);
		});
		
		myDocker.registerPanelType('Console', function(myPanel){
			myPanel.layout().addItem($('<div id="console"><button id="savestate">Save Layout</button><button id="loadstate">Load Layout</button><br><br>Right click opens a context menu (either in a panel or on a tab for a panel)<br><br>the Values tab has an icon left to its title<br><br>Scatterplot view can only exist once, whereas all other views can be created multiple times<br><br>moving the values view triggers an alert</div>'),0,0);
			myPanel.layout().addItem($('<div id="griddemo">and there is the possibility for a grid layout in each panel</div>'),1,0);
			myPanel.layout().showGrid(true);
			myPanel.layout().gridSpacing(2);
			myPanel.layout().gridAlternate(true);
			myPanel.initSize(400,200);
		});
		
		var panel1 = myDocker.addPanel('Scatterplot',wcDocker.DOCK_BOTTOM);
		var panel3 = myDocker.addPanel('Output',wcDocker.DOCK_RIGHT);
		var panel2 = myDocker.addPanel('Console',wcDocker.DOCK_BOTTOM);		
		var panel4 = myDocker.addPanel('Values',wcDocker.DOCK_LEFT);
		
		 myDocker.basicMenu('.testMenu', [
			{name: 'customMenu1', callback: function(key, opts, panel){alert(key);}},
			{name: 'customMenu2', callback: function(key, opts, panel){alert(key);}},
			{name: 'customMenu3', callback: function(key, opts, panel){alert(key);}}
			], true);
			
		//save and restore functionality of layout for buttons
		$('#savestate').click(function(){
			console.log('clicked save',state);
			state=myDocker.save();
		});
		
		$('#loadstate').click(function(){
			if(state){
				console.log('clicked load',state);
				myDocker.restore(state);
				refreshContent();
			} else {
				alert('Restoring went wrong!');
			}
		});
	}
	refreshContent();
});

//necessary because of the restore functionality that resets all parameters to the initial values
var refreshContent = function(){
	$('#output').width($('#output').parent().width());
	$('#output').height($('#output').parent().height());
	var conswidth=$('#console').width($('#console').parent().width());
	$('#console').height($('#console').parent().height());
	$('#griddemo').height($('#griddemo').parent().height());
	$('#griddemo').width($('#griddemo').parent().width()-conswidth);
	
	
	var wSP = $('#scatterplot').parent().width()-100;
	var hSP = $('#scatterplot').parent().height()-60;
	var wBC = $('#values').parent().width();
	var hBC = $('#values').parent().height()-40;
		
	refreshScatterPlot(wSP,hSP);
	refreshBarChart(wBC,hBC);
	refreshValueTable();
}