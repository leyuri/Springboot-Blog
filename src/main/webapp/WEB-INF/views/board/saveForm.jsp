<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="title">Title:</label> <input type="text"
				class="form-control" placeholder="Enter title" id="title">
		</div>

		<div class="form-group">
			<label for="content">Content:</label>
			<textarea class="form-control" rows="5" id="content"></textarea>
		</div>
		
		<button id="btn-save" class="btn btn-primary">Register</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>




