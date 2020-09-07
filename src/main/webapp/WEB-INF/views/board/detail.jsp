<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	
		<button class="btn btn-secondary" onclick="history.back()">Back</button>
		<c:if test="${board.user.id == principal.user.id}">
			<a href="/board/${board.id}/updateForm" class="btn btn-warning">Edit</a>
			<button id="btn-delete" class="btn btn-danger">Delete</button>
		</c:if>
		<br></br>
		<div>
			�� ��ȣ : <span id="id"><i>${board.id} </i></span>
			�ۼ��� : <span><i>${board.user.username} </i></span>
		</div>
		<br>
		<div>
			<h3>${board.title}</h3> 
		</div>
 		<hr/>
		<div>
			<div>${board.content}</div>
		</div>
		<hr/>
		
		<div>
		
			<div class="card">
				<div class="card-body"><textarea class="form-control" rows="1"></textarea></div>
				<div class="card-footer"><button class="btn btn-primary">���</button></div>
			</div>
			
			<br>
			<div class="card">
				<div class="card-header">comment list</div>
				<ul id="reply--box" class="list-group">
					<c:forEach var="reply" items="${board.replys}">
						<li id="reply--1" class="list-group-item d-flex justify-content-between">
						  	<div>${reply.content}</div>
						  	<div class="d-flex">
						  		<div class="font-italic">posted by : ${reply.user.username} &nbsp;</div>
						  		<button class="badge">����</button>
						  	</div>
						  </li>
					</c:forEach>
				</ul>
			</div>
		</div>
		
		
</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>




