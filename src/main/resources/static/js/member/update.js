// (1) 회원정보 수정
function update(memberId, event) {
	event.preventDefault();	// 폼태그 액션을 막기
	
	let data = $("#profileUpdate").serialize();
	
	console.log(data);
	
	$.ajax({		
		type: "put",
		url: `/api/member/${memberId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res=>{
		console.log("성공", res);
		location.href="/";
	}).fail(error=>{
		if(error.data == null){
			alert(error.responseJSON.message);
		}else{
			alert(JSON.stringify(error.responseJSON.data));
		}
	});
}

// (2) 유저 프로필 사진 변경
function profileImageUpdate(principalId){
	$("#memberProfileImageInput").click();
	
	$("#memberProfileImageInput").on("change", (e)=>{
		let f = e.target.files[0];
		
		if(!f.type.match("image.*")){
			alert("이미지를 등록해야 합니다.");
			return;
		}
	
		let profileImageForm = $("#memberProfileImageForm")[0];
		let formData = new FormData(profileImageForm);
		$.ajax({
			type: "put",
			url: `/api/member/${principalId}/profileImageUrl`,
			data: formData,
			contentType: false,
			processData: false,
			encType: "multipart/form-data",
			dataType: "json"
		}).done(res=>{
			let reader = new FileReader();
			reader.onload = (e) => {
				$("#memberProfileImage").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		}).fail(error=>{
			alert(error.responseJSON.data.content);
			console.log("오류", error);
		});
	});
	
}