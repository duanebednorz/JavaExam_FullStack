<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Event</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	<div class="jumbotron">
			<h1>Welcome ${loggedinuser.first_name}</h1>
		</div>
		<div>
			<a href="/dashboard">Dashboard </a> | <a href="logout">Logout</a>
		</div>
		<hr>
		<h4>Edit: ${event.event_name}</h4>
		<div class="row">
			<div class="col-sm-4">
				<form:form method="POST" action="/events/${event.id}/edit"
					modelAttribute="event">
					<div class="form-group">
						<form:label path="event_name">Content:</form:label>
						<form:input type="text" path="event_name" class="form-control" />
						<form:errors path="event_name" class="text-danger" />
					</div>
					<c:choose>
						<c:when test="${loggedinuser == event.host}">
						<input type="submit" value="Edit" class="btn btn-primary"/>
						<a href="/delete/${event.id}">Delete</a>
					</c:when>
						<c:otherwise>
								<td></td>
						</c:otherwise>
					</c:choose>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>