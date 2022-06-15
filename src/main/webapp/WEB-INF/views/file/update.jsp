<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!--파일 업로드페이지 중앙배치-->
<main class="uploadContainer">
	<!--파일업로드 박스-->
	<section class="upload">

		<!--파일업로드 로고-->
		<div class="upload-top">
			<a href="home.html" class=""> <img src="/images/logo.png" alt="">
			</a>
			<p>사진 업로드</p>
		</div>
		<!--파일업로드 로고 end-->

		<!--파일업로드 Form-->
		<form class="upload-form" action="/file/${file.id }/update" method="post" enctype="multipart/form-data">
			<div class="upload-form-detail">
				<input type="text" placeholder="제목" name="title" value="${file.title }"/>
			</div>
			
			<input type="file" name="file" onchange="imageChoose(this)" />
			<div class="upload-img">
				<span id="fileUploadPreview"><i> ${file.postFileUrl }"</i></span>
			</div>

			<!--파일설명 + 업로드버튼-->
			<div class="upload-form-detail">
				<input type="text" placeholder="파일설명" name="content" value="${file.content }"/>
				<button class="cta blue">수정</button>
			</div>
			<!--파일설명end-->

		</form>
		<!--파일업로드 Form-->
	</section>
	<!--파일업로드 박스 end-->
</main>
<br />
<br />

<script src="/js/image/upload.js"></script>
<%@ include file="../layout/footer.jsp"%>