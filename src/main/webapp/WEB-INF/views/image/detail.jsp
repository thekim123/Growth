<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">

	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${image.member.id==principal.member.id }">
		<a href="/image/${image.id }/updateForm" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<br /> <br />
	<div class="item__img">
		<span id="imageId" style="display: none;"><i> ${image.id } </i></span> <span id="memberProfileImage"> <img class="profile-image" src="/upload/${principal.member.profileImageUrl}"
			onerror="this.src='/images/person.jpeg'" /></span> <span id="username"><i> ${image.member.username } </i></span>
	</div>
	<br />
	<div>
		<h3>${image.title }</h3>
	</div>
	<hr />
	<div>
		<img src="/upload/${image.postImageUrl }" />
	</div>
	<hr />
	<div>
		<div>${image.content }</div>
	</div>
	<hr />

	<div class="card">
		<form>
			<input type="hidden" id="memberId" value="${principal.member.id }" /> <input type="hidden" id="imageId" value="${image.id }" />
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button id="btn-reply-save" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>
	<br />

	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="reply-box" class="list-group">
			<c:forEach var="reply" items="${image.replies }">
				<li id="reply-${reply.id }" class="list-group-item d-flex justify-content-between">
					<div>${reply.content }</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : ${reply.member.username } &nbsp;</div>
						<button id="btn-reply-delete" onclick="replyDelete(${image.id},${reply.id })" class="badge">삭제</button>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>

</div>

<script src="/js/image/image.js"></script>
<%@ include file="../layout/footer.jsp"%>

