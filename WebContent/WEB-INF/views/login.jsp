<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url  value="/?action=Login" 		var="linkLogin"/>
<c:url  value="/?action=Home" 		var="linkBack"/>
<c:url value="/?action=SignupForm"  var="linkSignup"/>
<c:url  value="eua.png" 			var="linkLogo"/>
<c:import url="header.jsp"></c:import>

<div class="container">

	<form class="form-signin" action="${linkLogin}" method="post"> 
		<h1 class="h1 mb-3 font-weight-normal ">
			<a href="${linkBack}" class="text-decoration-none">
				<img src="img/alvo72x76.png" alt="" width="72" height="72">	<br>
				<span class="text-dark">OKR</span>
			</a>
		</h1>	

		<div class="input-group mb-2">
			<div class="input-group-prepend">
			  <div class="input-group-text">@</div>
			</div>
			<input type="text" class="form-control" id="inlineFormInputGroup" placeholder="user@email.com"  name="email" value="${email}">
		</div>

		<div class="input-group mb-2">
			<div class="input-group-prepend">
			  <div class="input-group-text"><i class="fa fa-lock" aria-hidden="true"></i></div>
			</div>
			<input type="password" class="form-control" id="inlineFormInputGroup" placeholder="*****"  name="password" value="${password}">
		</div>
		Don't have a login? <a href="${linkSignup}" >Register</a> <br>
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
	
</div>

<c:import url="footer.jsp"></c:import>