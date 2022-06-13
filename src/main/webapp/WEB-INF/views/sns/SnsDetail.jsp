<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<input type="hidden" value="${snsDetail.id}" id="id">
${snsDetail.id} <br>
${snsDetail.title}
${snsDetail.content}
${principal.member.id} <br>
${snsDetail.createTime}<br>

<button id="btn-sns-delete">삭제</button> <br>
<button id="page-move-snsupdate">수정</button> <br>
<!-- 댓글 -->
<c:forEach var="replys" items="${snsDetail.replys}">
${replys.content } / ${replys.userid }
</c:forEach>
<!-- 댓글 -->
<input id="content">
<input id="snsId" value="${snsDetail.id}">
<input id="memberId" value="${principal.member.id}">

<button id="btn-reply-write">댓글 쓰기</button>






<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/js/sns/sns.js"></script>
<script type="text/javascript" src="/js/reply/reply.js"></script>

</body>
</html>
<%@ include file="../layout/footer.jsp"%>
