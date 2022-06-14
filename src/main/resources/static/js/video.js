function modalVideo(url) {
	$("#player").html('<source src="'+url+'"></source>' );
	$("#myModal").modal();	
}