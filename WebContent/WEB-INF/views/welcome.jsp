<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/?action=ObjectiveForm" 		var="linkObjectiveForm"/>
<c:url value="/?action=ObjectiveFormUpdate" var="linkObjectiveFormUpdate"/>
<c:url value="/?action=ObjectiveDelete" 	var="linkObjectiveDelete"/>
<c:url value="/?action=KeyResultForm" 		var="linkKeyResultForm"/>
<c:url value="/?action=KeyResultFormUpdate" var="linkKeyResultFormUpdate"/>
<c:url value="/?action=KeyResultDelete" 	var="linkKeyResultDelete"/>
<c:url value="/?action=StrategyForm" 		var="linkStrategyForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<h2>Welcome ${user.name}!</h2>
	<h5>${message}</h5>
	<a href="${linkObjectiveForm}">New</a>
	<c:import url="logout-partial.jsp"></c:import>
	<ul>
		<c:forEach items="${listObjective}" var="objective">
			<li>
				#${objective.id}: ${objective.description} 
				<a href="${linkObjectiveFormUpdate}&id=${objective.id}">Edit		  </a> | 
				<a href="${linkObjectiveDelete}&id=${objective.id}">    Delete        </a> | 
				<a href="${linkKeyResultForm}&id=${objective.id}">      New Key Result</a> 
				<ul>
					<c:forEach items="${objective.listKeyResult}" var="keyResult">
						<li>
							#${keyResult.id}: ${keyResult.description}
							<a href="${linkKeyResultFormUpdate}&id=${keyResult.id}">Edit		</a> | 
							<a href="${linkKeyResultDelete}&id=${keyResult.id}">	Delete		</a> | 
							<a href="${linkStrategyForm}&id=${keyResult.id}">		New Strategy</a> 
						</li>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
</body>
</html>