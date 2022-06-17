
let index = {
		init: function(){
			$("#btn-delete").on("click", ()=>{
				this.deleteById();
			});
			$("#btn-reply-save").on("click",  ()=>{
				this.replySave();
			}); 
		},
		
		deleteById: function(){
			let id = $("#fileId").text();
			
			$.ajax({
				type: "delete",
				url: "/api/file/"+id,
				dataType: "json"
			}).done(res=>{
				alert("삭제가 완료되었습니다.");
				location.href="/file";
			}).fail(error=>{
				alert("삭제가 실패하였습니다.");
				console.log(error);
			});
		},
		
		replySave: function(){
			let data = {
					fileId : $("#fileId").val(),
					content: $("#reply-content").val()
			}
			$.ajax({ 
				type: "POST",
				url: `/api/file/reply/`,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json",
			}).done(resp=>{
				alert("댓글작성이 완료되었습니다.");
				location.href = `/file/${data.fileId}`;
			}).fail(error=>{
				alert(error.responseJSON.data.content);
			}); 
		}
}
function replyDelete(fileId, replyId){
	$.ajax({ 
		type: "DELETE",
		url: `/api/file/reply/${replyId}`,
		dataType: "json"
	}).done(resp=>{
		alert("댓글삭제가 완료되었습니다.");
		location.href = `/file/${fileId}`;
	}).fail(error=>{
		console.log("오류",error);
		alert(error.responseJSON.data.content);
	}); 
}

index.init();
				