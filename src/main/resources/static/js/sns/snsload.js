let page = 0;
let principalId = $("#principalId").val();
snsLoad();

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

function getSnsItem(sns){
	let item = `
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${sns.title }</h4>
				<a href="/sns/${sns.id }" class="btn btn-primary">상세보기</a>
			</div>
		</div>`;
	
	return item;
}


// 스토리 스크롤 페이징
$(window).scroll(()=>{
	let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());
	
	if(checkNum<10 && checkNum>-10){
		page++
		snsLoad();
	}
});
