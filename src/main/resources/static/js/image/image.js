
let index = {
		init: function(){
			$("#btn-reply-save").on("click",  ()=>{
				this.replySave();
			}); 
		},
		
		replySave: function(){
			let data = {
					imageId : $("#imageId").val(),
					content: $("#reply-content").val()
			}
			$.ajax({ 
				type: "POST",
				url: `/api/img/reply/`,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json",
			}).done(resp=>{
				alert("댓글작성이 완료되었습니다.");
				location.href = `/image/${data.imageId}`;
			}).fail(error=>{
				console.log("오류", error);
				alert(error.responseJSON.data.content);
			}); 
		}
}
	
function replyDelete(imageId, replyId){
			$.ajax({ 
				type: "DELETE",
				url: `/api/img/reply/${replyId}`,
				dataType: "json"
			}).done(resp=>{
				alert("댓글삭제가 완료되었습니다.");
				location.href = `/image/${imageId}`;
			}).fail(error=>{
				alert(error.responseJSON.data.content);
			}); 
}



index.init();