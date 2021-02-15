<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/?action=GoalForm" var="linkGoalForm"/>
<c:url value="/?action=GoalFormUpdate" var="linkGoalFormUpdate"/>
<c:url value="/?action=GoalDelete" var="linkGoalDelete"/>
<c:url value="/?action=ResultKeyForm" var="linkResultKeyForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<h2>Welcome ${user.name}!</h2>
	<a href="${linkGoalForm}">New</a>
	<c:import url="logout-partial.jsp"></c:import>
	<ul>
		<c:forEach items="${listGoal}" var="goal">
			<li>#${goal.id}: ${goal.description} 
				<a href="${linkGoalFormUpdate}&id=${goal.id}">Edit</a> | 
				<a href="${linkGoalDelete}&id=${goal.id}">Delete</a>  | 
				<a href="${linkResultKeyForm}&id=${goal.id}">New Result Key</a> 
				<ul>
					<c:forEach items="${goal.listResultKey}" var="resultKey">
						<li>${resultKey.description }</li>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
</body>
</html>