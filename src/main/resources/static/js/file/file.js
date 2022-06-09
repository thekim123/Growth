
let index = {
		init: function(){
			$("#btn-delete").on("click",  ()=>{
					this.deleteById();
			}); 
		},


		deleteById: function(){
			let id = $("#id").text();
			
			$.ajax({ 
				type: "DELETE",
				url: "/api/refer/"+id,
				dataType: "json"
			}).done(function(resp){
				alert("삭제가 완료되었습니다.");
				location.href = "/image";
			}).fail(function(error){
				alert("삭제가 실패하였습니다.");
				alert(JSON.stringify(error));
			}); 
		},
}

index.init();
				