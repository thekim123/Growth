<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/signin" method="post">
		<div class="form-group">
			<label for="username">Username</label> <input name="username" type="text" class="form-control" placeholder="Enter username" id="username">
		</div>

		<div class="form-group">
			<label for="pwd">Password</label> <input name="password" type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="login-button-area">
			<button id="btn-login" class="btn btn-primary" >로그인</button>
		</div>
	</form>
	
	<div class="login__facebook">
		<button onclick="javascript:location.href=`/oauth2/authorization/facebook`">
			<i class="fab fa-facebook-square"></i> <span>Facebook으로 로그인</span>
		</button>
	</div>

</div>

<%@ include file="../layout/footer.jsp"%>

