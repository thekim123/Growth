<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<article id="snsList">
	
	</article>

	<ul class="pagination justify-content-center">
		
		<div id="sns-write" >
			<input id="sns-write-title"/>
			<textarea id="sns-write-content" rows="5" cols="" placeholder="내용을 입력하세요."></textarea>
			<button id="sns-write-btn" type="button" onClick="snsWrite();">글쓰기</button>		
		</div>
		<li class="page-item"><button type="button" id="btn-sns-write" onClick="snsWriteButton();">▲</button></li>
	</ul>
	
	 


</div>
<script src="/js/sns/snsload.js"></script>
<%@ include file="../layout/footer.jsp"%>

