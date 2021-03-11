<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="?action=UpdateKeyResult" var="linkUpdateKeyResult" />
<c:url value="?action=Welcome" 		   var="linkWelcome" />
<c:import url="header.jsp"></c:import>
<div class="container">

	<nav class="navbar navbar-light border-bottom">
		<div class="navbar-brand">
			<a href="#">
				<img src="img/alvo72x76.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
			</a>
		</div>
		<h3>OKR</h3>
		<a href="${linkLogout}" class="text-right text-dark"><i class="fa fa-power-off" aria-hidden="true"></i></a>
	</nav>
	<a href="${linkWelcome}" class="text-dark text-left"><i class="fa fa-reply" aria-hidden="true"></i></a>
	<h1>Update KeyResult</h1>
	<!-- <form action="${linkUpdateKeyResult}" method="post">
		KeyResult: <textarea name="description" rows="5" cols="50">${keyResult.description}</textarea> <br>
		<input type="hidden" name="userId" value="${userId}">
		<input type="hidden" name=keyResultId value="${keyResult.id}">
		<input type="submit" value="Update">
	</form> -->
	<form action="${linkUpdateKeyResult}" method="post">
		<div class="form-group">
			<label for="description">Key Result</label>
			<textarea class="form-control" id="description" rows="3" name="description" >${keyResult.description}</textarea>
		</div>		
		<input type="hidden" name="userId"    value="${userId}">
		<input type="hidden" name="keyResultId" value="${keyResult.id}">
		<input type="hidden" name="idObjective" value="${keyResult.idObjective}">
		<button type="submit" class="btn btn-dark mb-2">Update</button>
	</form>
</div>

<c:import url="footer.jsp"></c:import>