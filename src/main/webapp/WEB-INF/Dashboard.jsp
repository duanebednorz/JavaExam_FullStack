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
			<h1>Welcome, ${loggedinuser.first_name}
				${loggedinuser.last_name}</h1>
		</div>
		<div></div>
		<div>
			<a href="/dashboard">Dashboard </a> | <a href="logout">Logout</a>
		</div>
		<hr>
		<div class="col-sm-12">
			<h4>Ideas:</h4>
			<table class="table table-striped">
				<tr class="bg-dark text-light">
					<th>Name</th>
					<th>Created by:</th>
					<th>Number of Likes:</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${allEvents}" var="event">
					<tr>
						<td><a href="/view/${event.id}">${event.event_name}</a></td>
						<td>${event.host.first_name}</td>
						<td>${event.attendees.size()}</td>
						<c:choose>
							<c:when test="${event.attendees.contains(loggedinuser)}">
								<td><a href="/leave/${event.id}">Unlike</a></td>
							</c:when>
							<c:otherwise>
								<td><a href="/join/${event.id}">Like</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</div>
		<hr>
		<h4>
			<a href="/newEvent">Create an Idea</a>
		</h4>


	</div>
</body>
</html>