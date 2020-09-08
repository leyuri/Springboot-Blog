<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${principal.user.id}" />
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text"  value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
		</div>
		
		
		<!--  
		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="pwd">Password:</label> 
				<input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
			<div class="form-group">
			<label for="email">Email address:</label> 
			<input type="email"  value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email" >
			</div>
		</c:if>
		-->
		
		<!-- oauth 값이 없으면 패스워드를 수정할 수 있게 함, 일반 유저 가입자라면 패스워드와 이메일 수정할 수 있음. -->
		<c:choose>
			<c:when test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="pwd">Password:</label> 
				<input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
			<div class="form-group">
				<label for="email">Email address:</label> 
				<input type="email"  value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email" >
			</div>
			</c:when>
			
			<c:otherwise>
			<div class="form-group">
				<label for="email">Email address:</label> 
				<input type="email"  value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email"  readonly="readonly">
			</div>
			</c:otherwise>
		</c:choose>
		
	</form>
	<button id="btn-update" class="btn btn-primary">Update myInfo</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
