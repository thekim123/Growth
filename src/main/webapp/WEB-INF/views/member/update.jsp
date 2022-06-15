<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container my-300 bg-light ">
	<div style="height: 30px;"></div>

	<!--프로필셋팅 프로필 사진영역-->
	<form id="memberProfileImageForm">
		<input type="file" name="profileImageFile" style="display: none;" id="memberProfileImageInput">
	</form>
	<div>
		<img class="rounded-circle" id="memberProfileImage" float: left;" src="/upload/${principal.member.profileImageUrl }" onerror="this.src='/images/person.jpeg'"
			onclick="profileImageUpdate(${principal.member.id})" /> 
		<h2>${principal.member.username}</h2>
		<br />
	</div>
	<!--프로필셋팅 아이디영역end-->

	<!--프로필 수정-->
	<form id="profileUpdate" onsubmit="update(${principal.member.id}, event)">
		이름: <input class="form-control" type="text" name="name" placeholder="이름" value="${principal.member.name}" required="required" /> <br /> 유저네임: <input class="form-control" type="text" name="username"
			placeholder="유저네임" value="${principal.member.username }" readonly="readonly" /> <br /> 패스워드: <input class="form-control" type="password" name="password" placeholder="패스워드" autocomplete="off"
			required="required" /> <br /> 이메일: <input class="form-control" type="text" name="email" placeholder="이메일" value="${principal.member.email }" readonly="readonly" /> <br /> 전화번호: <input
			class="form-control" type="text" name="phone" placeholder="전화번호" value="#" /> <br />

		<!--제출버튼-->
		<div class="update-button-area">
			<button class="btn btn-primary">회원정보수정</button>
		</div>
		<!--제출버튼end-->
	</form>
	<!--프로필수정 form end-->
	<div style="height: 30px;"></div>
</div>
<script src="/js/member/update.js"></script>

<%@ include file="../layout/footer.jsp"%>