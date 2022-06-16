

$(function() {
	var onOff = false;
	$(".writeButton").click(function() {
		if (onOff) {
			$("#snsWrite").html('<form><input type="text" class="form-control form-control-sm" id="title"><div class="form-group"><label for="comment">Comment:</label><textarea class="form-control" rows="5" id="content" ></textarea></div></form><br><button id="btn-sns-write" class="btn btn-primary">완료</button>')
			$("#btn-sns-write").click(function() {
				let data = {
					title: $("#title").val(),
					content: $("#content").val(),
				}
				alert(data.content)
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
			});
		} else {
			$("#snsWrite").html('');
		}
		onOff = !onOff;
	});

});
