let page = 0;
let principalId = $("#principalId").val();
snsLoad();

// 1. sns 로드
function snsLoad(){
	$.ajax({
		url: `/api/sns/?page=${page}`,
		dataType: "json"
	}).done(res=>{
		res.data.content.forEach((sns)=>{
			let snsItem = getSnsItem(sns);
			$("#snsList").append(snsItem);
		});
	}).fail(error=>{
		console.log(error);
	});
}

// 2. sns아이템 가져오기
function getSnsItem(sns){
	//게시글 목록
	let item = `
		<div class="card m-2">
			<div class="card-header d-flex justify-content-between">
				<h5 class="card-title">${sns.title }</h5>
				<button id="btn-sns-delete" class="btn btn-secondary btn-sm" onclick="snsDelete(${sns.id});">삭제</button>
			</div>
			<div class="card-body">
				<div class="card-body bg-light">
					<p class="card-text">${sns.content}</p>
				</div>
			</div>
		`;
	
	item+=`
	<div class="card-header"><small>댓글 리스트</small></div>
	<ul id="reply-box" class="list-group">

		<div id="snsReplyList-${sns.id}" class="card bg-black">

	`;
	sns.replys.forEach((reply)=>{
		item+=`
				<li id="reply-${reply.id }" class="list-group-item d-flex justify-content-between">
					<div>${reply.content }</div>
						<div class="d-flex">
							<h6><small>${reply.member.username } &nbsp;</small></h6>
							<button id="btn-reply-delete" onclick="replyDelete(${reply.id })" class="badge">x</button>
						</div>
				</li>
		`;
	});
	//댓글 작성칸
	item+=`
	<div class="card">
		<div class="card-footer d-flex justify-content-between">
			<textarea id="snsReplyInput-${sns.id}" class="form-control" rows="1"></textarea>
			<button type="button" onClick="addReply(${sns.id})" class="btn btn-outline-dark btn-sm">등록</button>
		</div>
		</div>
	</div>
	
		</div>
		</ul>
		</div>
		<hr/>
		<br/>
	`;
	
	return item;
}


// 3. sns 스크롤 페이징
$(window).scroll(()=>{
	let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());
	
	if(checkNum<10 && checkNum>-10){
		page++
		snsLoad();
	}
});



// 4.sns글쓰기 창 열고 닫기
function snsWriteButton(){
	 let status = $('#sns-write').css('display');
  if (status == 'block') {
      $('#sns-write').hide();
      $('#btn-sns-write').text('▲');
  } else {
      $('#sns-write').show();
      $('#btn-sns-write').text('▲');
  }
}

// 5. sns 글쓰기
function snsWrite() {
	let data = {
		title: $("#sns-write-title").val(),
		content: $("#sns-write-content").val(),
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
}


// 6. sns 삭제하기
function snsDelete(snsId){
	$.ajax({
		type: "DELETE",
		url: "/api/snsDelete/" + snsId,
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(resp) {
		alert("삭제되었습니다");
		location.href = "/";
	}).fail(function(error) {
		alert(JSON.stringify(error));
	});
}


// 7. 댓글 작성하기
function addReply(snsId){
	let replyInput = $(`#snsReplyInput-${snsId}`);
	let replyList = $(`#snsReplyList-${snsId}`);
	console.log(replyInput);
	
	let data={
			snsId: snsId,
			content: replyInput.val()
	}
	if(data.content===""){
		alert("댓글을 작성해주세요!");
		return;
	}
	
	$.ajax({
		type: "post",
		url: "/api/sns/reply",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(res=>{
		let reply = res.data;
		console.log(res.data);
		let content = `
		<div id="snsReplyList-${reply.id}" class="card">
		<li id="reply-${reply.id }" class="list-group-item d-flex justify-content-between">
				<div>${reply.content }</div>
				<div class="d-flex">
					<h6><small>${reply.member.username } &nbsp;</small></h6>
					<button id="btn-reply-delete" onclick="replyDelete(${snsId},${reply.id })" class="badge">삭제</button>
				</div>
			</li>
		</div>
			`;
		replyList.prepend(content);
		
	}).fail(error=>{
		alert(error.responseJSON);
	});
}

// 8. 댓글 삭제하기
function replyDelete(replyId){
	$.ajax({ 
		type: "DELETE",
		url: `/api/sns/reply/${replyId}`,
		dataType: "json"
	}).done(resp=>{
		alert("댓글삭제가 완료되었습니다.");
		location.href = `/sns`;
	}).fail(error=>{
		alert(error.responseJSON.data.content);
	}); 
}
