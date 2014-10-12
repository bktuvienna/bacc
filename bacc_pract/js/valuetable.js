var refreshValueTable = function(){
	$('#valuetable').append('<tr><th>x</th><th>y</th><th>Value</th><tr>');
	$.each(dataset, function(i,current){
		$('#valuetable').append('<tr><td>'+current[0]+'</td><td>'+current[1]+'</td><td>'+current[2]+'</td></tr>');
	});
}