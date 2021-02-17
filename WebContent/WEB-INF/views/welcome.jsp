<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="header.jsp"></c:import>
<c:url value="/?action=ObjectiveForm" 		var="linkObjectiveForm"/>
<c:url value="/?action=ObjectiveFormUpdate" var="linkObjectiveFormUpdate"/>
<c:url value="/?action=ObjectiveDelete" 	var="linkObjectiveDelete"/>
<c:url value="/?action=KeyResultForm" 		var="linkKeyResultForm"/>
<c:url value="/?action=KeyResultFormUpdate" var="linkKeyResultFormUpdate"/>
<c:url value="/?action=KeyResultDelete" 	var="linkKeyResultDelete"/>
<c:url value="/?action=StrategyForm" 		var="linkStrategyForm"/>
<c:import url="header.jsp"></c:import>

<div class="container">

	<nav class="navbar navbar-light">
		<div class="navbar-brand">
			<a href="#">
				<img src="img/alvo72x76.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
			</a>
			<a href=""><i class="fa fa-power-off" aria-hidden="true"></i>
			</a>
		</div>
	</nav>
	<span class="text-right">Welcome <b>${user.name}</b>!</span>

	<hr>
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
							<a href="${linkKeyResultDelete}&id=${keyResult.id}">	Delete		</a>
						</li>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
</div>

<c:import url="footer.jsp"></c:import>