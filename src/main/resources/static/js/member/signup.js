$("#username").keyup(function(){
	
	let data = {
			username: $("#username").val()
	}
	$.ajax({
		type: "post",
		url: "/exists",
		data: JSON.stringify(data),
		dataType: "json",
		contentType: "application/json; charset=utf-8"
	}).done(res=>{
			if(!res.data){
				$("#valid").html("사용가능한 아이디입니다.");
				$("#valid").css('color', 'blue');
			} else{
				$("#valid").html("사용불가능한 아이디입니다.");
				$("#valid").css('color', 'red');
			}
		console.log("성공",res);
	}).fail(error=>{
		alert(error.responseJSON.message);
		console.log("오류", error);
	});
});
