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
			<div class="card-body">
				<h4 class="card-title">${sns.title }</h4>
				<div class="card-body bg-light">
					<p class="card-text">${sns.content}</p>
			</div>
		</div>`;
	
	//댓글 작성칸
	item+= `
	<div class="card">
		<div class="card-body">
			<textarea id="snsReplyInput-${sns.id}" class="form-control" rows="1"></textarea>
		</div>
		<div class="card-footer">
			<button type="button" onClick="addReply(${sns.id})" class="btn btn-primary">등록</button>
		</div>
	</div>
	`;
	
	item+=`
		<div id="snsReplyList-${sns.id}" class="card bg-black">
	`;
	console.log(sns);
	sns.replys.forEach((reply)=>{
		item+=`
			<div id="reply-${reply.id }">
				${reply.content }
					작성자 : ${reply.member.username } &nbsp;
			</div>
		`;
	});
	item+=`
		</div>
		</ul>
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


// 4. 댓글 작성하기
function addReply(snsId){
	let replyInput = $(`#snsReplyInput-${snsId}`);
	let replyList = $(`#snsReplyList-${snsId}`);
	
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
		alert(res.data);
		console.log(res.data);
		let content = `
		<div id="snsReplyList-${reply.id}" class="card">
		<li id="reply-${reply.id }" class="list-group-item d-flex justify-content-between">
				<div>${reply.content }</div>
				<div class="d-flex">
					<div class="font-italic">작성자 : ${reply.member.username } &nbsp;</div>
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