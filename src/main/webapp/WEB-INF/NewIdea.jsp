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
		<h4>Create an Idea</h4>
		<div class="row">
			<div class="col-sm-4">
				<form:form method="POST" action="/createEvent"
					modelAttribute="newEvent">
					<div class="form-group">
						<form:label path="event_name">Idea Name:</form:label>
						<form:input type="text" path="event_name" class="form-control" />
						<form:errors path="event_name" class="text-danger" />
					</div>
					<input type="submit" value="Create" class="btn btn-primary" />
				</form:form>
			</div>
		</div>


	</div>
</body>
</html>