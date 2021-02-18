<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/"       var="linkBack"/>
<c:url value="/?action=LoginForm"  var="linkLogin"/>
<c:url value="/?action=Signup" var="signup"/>
<c:import url="header.jsp"></c:import>

<div class="container">
 
	
	<!-- <form action="${signup}" method="post">
		Name: <input type="text" name="name"> <br>
		E-mail: <input type="text" name="email"> <br>
		Password: <input type="password" name="password" value="123"> <br>
		<a href="${linkBack}">Back</a> 
		<input type="submit" value="Signup">
	</form>
	<a href="${linkLogin}">Login</a>  -->
	<form class="form-signin" action="${signup}" method="post"> 
		<h1 class="h1 mb-3 font-weight-normal ">
			<a href="${linkBack}" class="text-decoration-none">
				<img src="img/alvo72x76.png" alt="" width="72" height="72">	<br>
				<span class="text-dark">OKR</span>
			</a>
		</h1>	

		<div class="input-group mb-2">
			<div class="input-group-prepend">
			  <div class="input-group-text"><i class="fa fa-user" aria-hidden="true"></i></div>
			</div>
			<input type="text" class="form-control" id="name" placeholder="name"  name="name">
		</div>

		<div class="input-group mb-2">
			<div class="input-group-prepend">
			  <div class="input-group-text">@</div>
			</div>
			<input type="text" class="form-control" id="inlineFormInputGroup" placeholder="user@email.com"  name="email">
		</div>

		<div class="input-group mb-2">
			<div class="input-group-prepend">
			  <div class="input-group-text"><i class="fa fa-lock" aria-hidden="true"></i></div>
			</div>
			<input type="password" class="form-control" id="inlineFormInputGroup" placeholder="*****"  name="password">
		</div>
		Have login? <a href="${linkLogin}" >Login</a> <br>
		<button type="submit" class="btn btn-primary">Signup</button>
	</form>
</div>

<c:import url="footer.jsp"></c:import>