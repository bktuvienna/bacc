$(document).ready(function() {
	var myDocker = new wcDocker(document.body);
	if(myDocker) {
		var layoutConfiguration;
		var currentTheme = 'Default';
		
		myDocker.registerPanelType('Values', function(myPanel){
			myPanel.layout().addItem($('<div></div>'));
			myPanel.initSize(200,400);
		});
		
		myDocker.registerPanelType('Scatterplot', function(myPanel){
			myPanel.layout().addItem($('<div></div>'));
			myPanel.initSize(900,100);
		});
		
		myDocker.registerPanelType('Output', function(myPanel){
			myPanel.layout().addItem($('<div></div>'));
			myPanel.initSize(100,100);
		});
		
		myDocker.registerPanelType('Console', function(myPanel){
			myPanel.layout().addItem($('<div></div>'));
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

});