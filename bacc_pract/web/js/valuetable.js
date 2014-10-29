var refreshValueTable = function(){
	$('#valuetable').append('<tr><th>x</th><th>y</th><th>Value</th><tr>');
	$.each(dataset, function(i,current){
		$('#valuetable').append('<tr><td>'+current[0]+'</td><td>'+current[1]+'</td><td id="'+current[2]+'">'+current[2]+'</td></tr>');
	});
}

var highlightValue = function(value,color){
	$('#valuetable tr td').css('background-color','white');
	$('#valuetable tr td').css('opacity',0.6);
	$('#'+value+'').parent().children().css('background-color',color);
	$('#'+value+'').parent().children().css('color','black');
	$('#'+value+'').parent().children().css('opacity',1.0);
}