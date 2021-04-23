<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${event.host.first_name} Dashboard</title>
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
		<div></div>
	${events}
	<br>
	${allEvents}
	<br>
	${host}
	<br>
	<c:forEach items = "${events}" var="h">
	<th>${h}</th>
	</c:forEach>    
		<div>
			<a href="/dashboard">Dashboard </a> | <a href="logout">Logout</a>
		</div>
		<hr>
		<form action="/search" name="state">
			<div class="form-group">
				<label>Host Name:</label> <input type="search" name="host">
				<input type="submit" value="New Search" class="btn btn-primary" />
			</div>
		</form>
		<hr>
		<div class="col-sm-12">
			<h4>Here are some of the events hosted by ${event.host.first_name}:</h4>
			<table class="table table-striped">
				<tr class="bg-dark text-light">
					<th>Name</th>
					<th>Date</th>
					<th>Location</th>
					<th>Host</th>
					<th>Number attending:</th>
					<th>Action</th>
					<th>Status</th>
				</tr>
				<c:forEach items="${allEvents}" var="event">
					<tr>
						<c:choose>
							<c:when test="${host == event.host}">
								<td><a href="/view/${event.id}">${event.event_name}</a></td>
								<td>${event.event_date}</td>
								<td>${event.event_city} ${event.event_state}</td>
								<td>${event.host.first_name}</td>
								<td>${event.attendees.size()}</td>
								<c:choose>
									<c:when test="${loggedinuser == event.host}">
										<td><a href="/edit/${event.id}">Edit</a> | <a
										href="/delete/${event.id}">Delete </a></td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${event.attendees.contains(loggedinuser)}">
									<td><a href="/leave/${event.id}">Leave</a></td>
									</c:when>
									<c:otherwise>
									<td><a href="/join/${event.id}">Join</a></td>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>