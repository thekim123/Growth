<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
	<%@ include file="../layout/header.jsp"%>
	<input type="hidden" value="${snsUpdate.id }" id="id">

	<div class="container">

		<table class="table table-striped">
			<thead>
				<tr>
					<th><div class="form-group">
							<label for="usr">Title:</label> <input type="text"
								class="form-control" id="title" value="${snsUpdate.title }">
						</div></th>

				</tr>
			</thead>
			<tbody>
				<tr>
					<td><div class="form-group">
							<label for="comment">Comment:</label>
							<textarea class="form-control" rows="5" id="content">${snsUpdate.content }</textarea>
						</div></td>

				</tr>
				<tr>
				<td align="right">
					<button id="btn-sns-update" class="btn btn-primary">글수정 완료</button>
				</td>

				</tr>

			</tbody>
		</table>
	</div>



	<script type="text/javascript" src="/js/sns/sns.js"></script>
	<%@ include file="../layout/footer.jsp"%>




</body>
</html>