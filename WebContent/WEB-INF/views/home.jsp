<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/?action=LoginForm" var="linkLogin"/>
<c:url value="/?action=SignupForm" var="linkSignup"/>


<c:import url="header.jsp"></c:import>

<div class="container">
	
	<div class="welcome">
		
		<h1 class="h1 mb-3 font-weight-normal ">
			<a href="#" class="text-decoration-none">
				<img src="img/alvo72x76.png" alt="" width="72" height="72">	<br>
				<span class="text-dark">OKR</span>
			</a>
		</h1>	
		
		<a class="text-dark" href="${linkLogin}">Login</a> | <a class="text-dark" href="${linkSignup}">Sign Up</a>
	</div>
</div>

<c:import url="footer.jsp"></c:import>