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
<c:url value="/?action=Logout" 				var="linkLogout"/>
<c:import url="header.jsp"></c:import>

<div class="container">

	<nav class="navbar navbar-light border-bottom">
		<div class="navbar-brand">
			<a href="#">
				<img src="img/alvo72x76.png" width="30" height="30" class="d-inline-block align-top" alt="Image " loading="lazy">
			</a>
		</div>
		<h3>OKR</h3>
		<a href="${linkLogout}" class="text-right text-dark"><i class="fa fa-power-off" aria-hidden="true"></i></a>
	</nav>

	<div class="row">
		<div class="col-8"><h5 class="text-left  mt-2">Welcome <b>${user.name}</b>!	</h5></div>
		<div class="col-4">
			<c:if test="${listObjective.size() != 0}"> 
				<a href="${linkObjectiveForm}" class="btn"><i class="fa fa-plus" aria-hidden="true"></i> New</a>
			</c:if>
		</div>
	</div>
	
	<c:if test="${not empty message}">
		<div class="alert alert-secondary" role="alert">
			${message}
		</div>
	</c:if>

	<c:if test="${listObjective.size() > 0}">
		<c:forEach items="${listObjective}" var="objective">
		<div class="card text-center">
			<div class="card-header">
				<h5 class="card-title text-left"> <i class="fa fa-rocket" aria-hidden="true"></i> ${objective.description}</h5>
			</div>
			<div class="card-body">			
				
			<c:forEach items="${objective.listKeyResult}" var="keyResult">
			<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
				<div class="card-header"><i class="fa fa-key" aria-hidden="true"></i></div>
				<div class="card-body">
				<h5 class="card-title">${keyResult.description}</h5>
				<p class="card-text"></p>			  
				</div>
				<div class="card-footer text-right">
					<a href="${linkKeyResultFormUpdate}&idKeyResult=${keyResult.id}&idObjective=${objective.id}" class="btn btn-outline-light"><i class="fa fa-pencil" aria-hidden="true"></i></a>
					<a href="${linkKeyResultDelete}&idKeyResult=${keyResult.id}&idObjective=${objective.id}"     class="btn btn-outline-light"><i class="fa fa-trash"  aria-hidden="true"></i></a>
				</div>
			</div>
			</c:forEach>
			<c:if test="${objective.listKeyResult.size() == 0}"> 
				<p class="text-center">
					You have no result key created!
				</p>		
			</c:if>

			<a href="${linkKeyResultForm}&id=${objective.id}" class="btn btn-dark"><i class="fa fa-plus" aria-hidden="true"></i>New</a>
			
			</div>
			<div class="card-footer text-right">
				<a href="${linkObjectiveFormUpdate}&id=${objective.id}" class="btn btn-outline-dark"><i class="fa fa-pencil" aria-hidden="true"></i></a>
				<a href="${linkObjectiveDelete}&id=${objective.id}"     class="btn btn-outline-dark"><i class="fa fa-trash" aria-hidden="true"></i></a> 
			</div>
		</div>
		<br>
		</c:forEach> 
	</c:if>
	<c:if test="${listObjective.size() == 0}"> 
	<div class="mt-5">
		<p class="text-center"> 
			<img src="img/bad.png" width="90" height="90" alt="Image bad">
		</p>	
		<p class="text-center">
			You have no goals created! 
		</p>
		<p class="text-center"> 
			<a href="${linkObjectiveForm}" class="btn btn-dark"><i class="fa fa-plus" aria-hidden="true"></i> New</a>
		</p>
	</div>
	</c:if>
</div>

<c:import url="footer.jsp"></c:import>