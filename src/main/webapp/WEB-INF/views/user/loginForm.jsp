<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">Username:</label> <input type="text"
				class="form-control" placeholder="Enter username" id="username">
		</div>
		
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password"
				class="form-control" placeholder="Enter password" id="password">
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> <input
				class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
		<button id="btn-login" class="btn btn-primary">login</button>
	</form>
</div>

<script src="/blog/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
