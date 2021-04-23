<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
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
			<a href="/new">New Something goes here</a> | <a
				href="/dashboard">Dashboard </a> | <a href ="logout">Logout</a>
		</div>
		<hr>
		<div class="col-sm-8">
			<table class="table table-striped">
				<tr class="bg-dark text-light">
					<th>First Name</th>
					<th>Last Name</th>
					<th>City</th>
					<th>State</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
					<tr>
						<td>${loggedinuser.first_name}</td>
						<td>${loggedinuser.last_name}</td>
						<td>${loggedinuser.city}</td>
						<td>${loggedinuser.state}</td>
						<td>${loggedinuser.email}</td>
						<td>Edit | Delete</td>
					</tr>
			</table>
		</div>
	</div>
</body>
</html>