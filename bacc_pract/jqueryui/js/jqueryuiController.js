var myLayout;

$(document).ready(function(){
	var height=$(window).height() - $('header').height();
	console.log(height + ", "+$.layout.language);
	$('#container').height(height);
	
	myLayout = $('#container').layout({
		center__paneSelector:".outer-center",
		west__paneSelector:".outer-west",
		center__childOptions: {
			center__paneSelector:".inner-center",
			east__paneSelector:".inner-east",
			south__paneSelector:".inner-south",
		}	
	});
	myLayout.addToggleBtn(".toggle-outer-west","west");
});



