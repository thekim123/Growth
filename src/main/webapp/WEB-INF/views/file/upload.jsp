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
			<p>파일 업로드</p>
		</div>
		<!--파일업로드 로고 end-->

		<!--파일업로드 Form-->
		<form class="upload-form" action="/file/upload" method="post" enctype="multipart/form-data">
			<div class="upload-form-detail">
				<input type="text" placeholder="제목" name="title" />
			</div>
			

			<!--파일설명-->
			<div class="upload-form-detail">
				<textarea name="content" placeholder="파일설명" rows="5" class="form-control"></textarea>
			</div>
			<!--파일설명end-->
	
			<!-- 파일 업로드 인풋 -->
			<input type="file" name="file" onchange="imageChoose(this)" />
			<!-- 파일 업로드 인풋 end-->

			<button class="btn btn-primary">업로드</button>
		</form>
		<!--파일업로드 Form-->
	</section>
	<!--파일업로드 박스 end-->
</main>
<br />
<br />

<script src="/js/file/upload.js"></script>
<%@ include file="../layout/footer.jsp"%>