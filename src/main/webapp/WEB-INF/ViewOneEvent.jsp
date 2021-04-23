<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event Dashboard</title>
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
		<div class="col-sm-8">
			<h4>Idea Name: ${thisEvent.event_name}</h4>
			<h6>Created by: ${thisEvent.host.first_name} ${thisEvent.host.last_name}</h6>
			<h6>Number of user who like the idea: ${thisEvent.attendees.size()}</h6>
			<table class="table table-striped">
				<tr class="bg-dark text-light">
					<th>Name</th>
				</tr>
				<c:forEach items="${thisEvent.attendees}" var="atn"> 
				<tr>
					<td>${atn.first_name}</td>
				</tr>
				</c:forEach>
			</table>
			<div>
				<p><a href="/edit/${thisEvent.id}">Edit</a> </p>
			</div>
		</div>
		
	</div>

</body>
</html>