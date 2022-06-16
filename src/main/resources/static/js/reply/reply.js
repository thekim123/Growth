function deleteReply(t) {

	let snsId = t.form.snsId.value
	let id = t.form.replyId.value


	$.ajax({
		type: "DELETE",
		url: "/api/replyDelete/" + id,

		contentType: "application/json; charset=utf-8",
		dataType: "json"

	}).done(function(resp) {
		alert("삭제되었습니다");
		location.href = "/sns/" + snsId;
	}).fail(function(error) {
		alert(JSON.stringify(error));
	});


}


let reply = {
	btn: function() {
		$("#btn-reply-write").click(function() {
			reply.write();


		});

	},


	write: function() {
		let data = {
			content: $("#content").val(),
			snsId: $("#snsId").val(),
			memberId: $("#memberId").val()
		}

		$.ajax({
			type: "POST",
			url: "/api/replyWrite/" + data.snsId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			alert("댓글작성이 완료되었습니다");
			location.href = "/sns/" + data.snsId;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}

}


reply.btn();






