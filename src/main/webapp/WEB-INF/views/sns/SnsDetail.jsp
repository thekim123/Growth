<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>



	<div class="container">
		<input type="hidden" value="${snsDetail.id}" id="id"> <br>
		<br>

		<p></p>
		<table class="table table-striped">
			<thead>
				<tr align="right">
					<td><h2>제목 : ${snsDetail.title}</h2></td>
				</tr>
			</thead>
			<tbody>
				<tr align="right">
					<td>작성자 : ${snsDetail.member.username} 작성시간 :
						${snsDetail.createTime}</td>
				</tr>

				<tr align="center">
					<td>${snsDetail.content}</td>
				</tr>
				<c:choose>
					<c:when
						test="${snsDetail.member.username == principal.member.username}">
						<tr>
							<td align="right">
								<button id="page-move-snsupdate" class="btn btn-primary">수정</button>
								<button id="btn-sns-delete" class="btn btn-danger">삭제</button>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>








		<form>
			<div class="input-group mb-3">

				<input id="snsId" value="${snsDetail.id}" type="hidden"> <input
					id="memberId" value="${principal.member.id}" type="hidden">
				<input type="text" class="form-control" id="content"
					placeholder="댓글을 써주세요..">
				<div class="input-group-append">
					<button id="btn-reply-write" class="btn btn-primary" type="button">OK</button>

				</div>
			</div>
		</form>

		<div class="container">

			<table class="table">
				<thead>
					<tr>
						<th>댓글 리스트</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="replys" items="${snsDetail.replys}">
						<tr>
							<td>${replys.content } <c:choose>
									<c:when
										test="${replys.memberId.username == principal.member.username}">
										<form name="form">
											<input value="${snsDetail.id }" name="snsId"> <input
												value="${replys.id }" name="replyId">
											<button class="btn btn-danger" onclick="deleteReply(this)">삭제</button>
										</form>


									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>

							</td>
							<td align="right">작성자 : ${replys.memberId.username }</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>



	</div>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/js/sns/sns.js"></script>
	<script type="text/javascript" src="/js/reply/reply.js"></script>
</body>
</html>
<%@ include file="../layout/footer.jsp"%>
