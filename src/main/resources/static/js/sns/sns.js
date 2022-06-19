let init = {

	btn: function() {
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
		
	
	}
}



init.btn();
