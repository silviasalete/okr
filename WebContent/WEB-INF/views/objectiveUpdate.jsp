<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="?action=UpdateObjective" var="linkUpdateObjective" />
<c:url value="?action=Welcome" var="linkWelcome" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Objective Update</title>
</head>
<body>
	
	<h1>Update Objective</h1>
	<c:import url="logout-partial.jsp"/>
	<a href="${linkWelcome}">Back</a>
	<form action="${linkUpdateObjective}" method="post">
		Objective: <textarea name="description" rows="5" cols="50">${objective.description}</textarea> <br>
		<input type="hidden" name="userId" value="${userId}">
		<input type="hidden" name=objectiveId value="${objective.id}">
		<input type="submit" value="Update">
	</form>
</body>
</html>