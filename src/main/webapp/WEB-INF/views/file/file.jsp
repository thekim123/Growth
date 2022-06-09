<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<c:forEach var="file" items="${files.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${file.title }</h4>
				<a href="/file/${file.id }" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>

	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${files.first }">
				<li class="page-item disabled"><a class="page-link" href="?page=${files.number-1 }">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${files.number-1 }">Previous</a></li>
			</c:otherwise>
		</c:choose>
		<li class="page-item"><a class="page-link" href="/file/upload">글쓰기</a></li>
		<c:choose>
			<c:when test="${files.last }">
				<li class="page-item disabled"><a class="page-link" href="?page=${files.number+1 }">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${files.number+1 }">Next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	
	 



</div>
<%@ include file="../layout/footer.jsp"%>

