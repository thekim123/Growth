<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<c:forEach var="files" items="${filelist.content }">
		<div class="card m-3">
			<div class="card-body">
				<h6 class="card-title">${files.filename }&nbsp
					<a href="${files.url }" class="btn btn-primary btn-sm"> <i class="fa-solid fa-download"></i>
					</a>&nbsp
					<!-- Button to Open the Modal -->
					<button type="button" class="btn btn-primary btn-sm" onclick="modalVideo('${files.url}')">
						<i class="fa-solid fa-play"></i>
					</button>
				</h6>
			</div>
		</div>
	</c:forEach>
</div>

<div class="container">
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${filelist.first }">
				<li class="page-item disabled"><a class="page-link" href="?page=${filelist.number - 1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${filelist.number - 1}">Previous</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${filelist.last }">
				<li class="page-item disabled"><a class="page-link" href="?page=${filelist.number + 1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${filelist.number + 1}">Next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>

	<!-- The Modal -->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Video Player</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body text-center">
					<video id="player" controls="controls" width="1024"></video>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
</div>

<%@ include file="../layout/footer.jsp"%>