$(document).ready(function() {
	var myDocker = new wcDocker(document.body);
	if(myDocker) {
		var layoutConfiguration;
		var currentTheme = 'Default';
		
		myDocker.registerPanelType('Properties', function(myPanel){
			myPanel.layout().addItem($('<div>testproperties</div>'));
			myPanel.initSize(400,400);
		});
		
		myDocker.registerPanelType('Code', function(myPanel){
			myPanel.layout().addItem($('<div>testcode</div>'));
			myPanel.initSize(400,400);
		});
		
		myDocker.registerPanelType('Output', function(myPanel){
			myPanel.layout().addItem($('<div>testoutput</div>'));
			myPanel.initSize(400,400);
		});
		
		var panel1 = myDocker.addPanel('Code',wcDocker.DOCK_BOTTOM, false);
		var panel2 = myDocker.addPanel('Properties',wcDocker.DOCK_LEFT, false);
		var panel3 = myDocker.addPanel('Output',wcDocker.DOCK_RIGHT, false);
		
		 myDocker.basicMenu('.testMenu', [
			{name: 'customMenu1', callback: function(key, opts, panel){alert(key);}},
			{name: 'customMenu2', callback: function(key, opts, panel){alert(key);}},
			{name: 'customMenu3', callback: function(key, opts, panel){alert(key);}}
			], true); 
	}

});