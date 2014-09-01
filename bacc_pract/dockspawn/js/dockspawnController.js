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
	var properties = new dockspawn.PanelContainer(document.getElementById('properties'),dockManager);
	var code = new dockspawn.PanelContainer(document.getElementById('code'),dockManager);
	var output = new dockspawn.PanelContainer(document.getElementById('output'),dockManager);
	
	//docking panels to each other
	var documentNode = dockManager.context.model.documentManagerNode;
	var codeNode = dockManager.dockFill(documentNode, code)
	var propertiesNode = dockManager.dockLeft(codeNode, properties,0.15);
	var outputNode = dockManager.dockRight(codeNode,output,0.15);
}