<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<link rel="stylesheet" type="text/css" href="/css/sns.css">


<div class="container">
	<!-- 
	<article id="snsList"></article>
	 -->

	<c:forEach var="s" items="${sns.content }">
		<div class="media border p-3">
			<div class="media-body">
				<h4>
					<a href="/sns/${s.id }"> 제목 : ${s.title }<small><i>
								작성자 : ${ s.member.username}</i></small>
					</a>
				</h4>

			</div>


		</div>




		<br>

	</c:forEach>


	<form name="form">
		<input type="hidden" value="${s.id }" name="snsId"> <input
			type="hidden" value="${principal.member.id }" name="memberId">

	</form>

	<ul class="pagination justify-content-center">

		<c:choose>
			<c:when test="${sns.first }">
				<li class="page-item disabled"><a class="page-link"
					href="?page=${sns.number - 1 }">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="?page=${sns.number - 1 }">Previous</a></li>

			</c:otherwise>

		</c:choose>

		<c:choose>
			<c:when test="${sns.last }">
				<li class="page-item disabled"><a class="page-link"
					href="?page=${sns.number + 1 }">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="?page=${sns.number + 1 }">Next</a></li>

			</c:otherwise>

		</c:choose>



	</ul>

	<button type="button" class="btn btn-primary  writeButton">글쓰기</button>
	<div id="snsWrite"></div>
</div>


<script type="text/javascript" src="js/sns/sns.js"></script>
<script src="/js/sns/snsEvent.js"></script>
<script src="/js/sns/snsload.js"></script>
<%@ include file="../layout/footer.jsp"%>


