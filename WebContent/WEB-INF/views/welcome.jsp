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
<c:url value="/?action=Logout" var="linkLogout"/>
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

	<div class="row">
		<div class="col-8"><h5 class="text-left">Welcome <b>${user.name}</b>!	</h5></div>
		<div class="col-4"><a href="${linkObjectiveForm}" class="btn btn-outline-dark"><i class="fa fa-plus" aria-hidden="true"></i> New</a></div>
	</div>
	
	<c:if test="${not empty message}">
		<div class="alert alert-secondary" role="alert">
			${message}
		</div>
	</c:if>

	<c:forEach items="${listObjective}" var="objective">
	<div class="card text-center">
		<div class="card-header">
			<h5 class="card-title"> <i class="fa fa-rocket" aria-hidden="true"></i> Objective ${objective.id}</h5>
		</div>
		<div class="card-body">
		  <p class="card-text">${objective.description}</p>
		  
		  <c:forEach items="${objective.listKeyResult}" var="keyResult">
		  <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
			<div class="card-header"><i class="fa fa-key" aria-hidden="true"></i></div>
			<div class="card-body">
			  <h5 class="card-title">Key Result ${keyResult.id}</h5>
			  <p class="card-text">${keyResult.description}</p>			  
			</div>
			<div class="card-footer text-right">
				<a href="${linkKeyResultFormUpdate}&id=${keyResult.id}" class="btn btn-outline-light"><i class="fa fa-pencil" aria-hidden="true"></i></a>
				<a href="${linkKeyResultDelete}&id=${keyResult.id}" class="btn btn-outline-light"><i class="fa fa-trash" aria-hidden="true"></i></a>
			</div>
		  </div>
		  </c:forEach>
		  <a href="${linkKeyResultForm}&id=${objective.id}" class="btn btn-outline-dark"><i class="fa fa-plus" aria-hidden="true"></i>New</a>

		</div>
		<div class="card-footer text-right">
			<a href="${linkObjectiveFormUpdate}&id=${objective.id}" class="btn btn-outline-dark"><i class="fa fa-pencil" aria-hidden="true"></i></a>
			<a href="${linkObjectiveDelete}&id=${objective.id}"     class="btn btn-outline-dark"><i class="fa fa-trash" aria-hidden="true"></i></a> 
		</div>
	  </div>
	  <br>
	</c:forEach>
</div>

<c:import url="footer.jsp"></c:import>