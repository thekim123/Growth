let init = {

	btn: function() {
		$("#btn-sns-write").click(function() {
			init.write();
		});
		$("#btn-sns-delete").click(function() {
			init.delete();
		});
		$("#page-move-snsupdate").click(function() {
			let id = $("#id").val();
			location.href="/snsUpdate/"+id
		});
		$("#btn-sns-update").click(function() {
			init.update();
		});
	},
	update: function() {
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
			$.ajax({
			type: "PUT",
			url: "/api/snsUpdate/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			alert("수정이 완료되었습니다");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		
	
	},


	write: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
			memberId: $("#memberId").val()
		}

		$.ajax({
			type: "POST",
			url: "/api/snsWrite",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			alert("작성 완료되었습니다");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	delete: function() {
		let id = $("#id").val();
		alert(id)
		$.ajax({
			type: "DELETE",
			url: "/api/snsDelete/" + id,

			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			alert("삭제되었습니다");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
}

init.btn();
