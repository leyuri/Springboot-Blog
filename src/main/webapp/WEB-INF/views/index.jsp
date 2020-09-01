<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ include file="layout/header.jsp"%>

<div class="container">

	<c:forEach var="board" items="${boards}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="#" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>

</div>
<%@ include file="layout/footer.jsp"%>




