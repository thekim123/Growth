let reply = {
	btn: function(){
		$("#btn-reply-write").click(function(){
			reply.write();
		});
	},
	write:function(){
		let data = {
			content: $("#content").val(),
			snsId: $("#snsId").val(),
			memberId : $("#memberId").val()
		}
		alert(data.snsId)
			$.ajax({
			type: "POST",
			url: "/api/replyWrite",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			alert("댓글작성이 완료되었습니다");
			location.href = "/sns/"+data.snsId;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
	
}

reply.btn();