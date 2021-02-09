<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/" var="linkBack"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Login </h1>
	
	<form action="">
		E-mail: <input type="text" name="email"> <br>
		Password: <input type="password" name="password"> <br>
		<a href="${linkBack}">Back</a> 
		<input type="submit" value="Login">
	</form>
</body>
</html>