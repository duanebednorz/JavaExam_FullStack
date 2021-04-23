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
			<h1>Dashboard</h1>
		</div>
		<div>
			 <a href="/dashboard">Dashboard</a>
		</div>
		<hr>
		<h1>Login</h1>
		<p class="text-danger">
			<c:out value="${error}" />
		</p>
		<div class="row">
			<div class="col-sm-4">
				<form method="post" action="/login">
					<div class="form-group">
						<label for="email">Email</label> <input type="text" id="email"
							name="email" class="form-control" />
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							id="password" name="password" class="form-control" />
					</div>
					<input type="submit" value="Login!" class="btn btn-primary" />
				</form>
			</div>
		</div>
	</div>
	<div class="container">
		<hr>
		<h1>Register!</h1>
		<div class="row">
			<div class="col-sm-4">
				<form:form method="POST" action="/registration"
					modelAttribute="user">
					<div class="form-group">
						<form:label path="first_name">First Name:</form:label>
						<form:input type="text" path="first_name" class="form-control" />
						<form:errors path="first_name" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="last_name">Last Name:</form:label>
						<form:input type="text" path="last_name" class="form-control" />
						<form:errors path="last_name" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="email">Email:</form:label>
						<form:input type="email" path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="city">City:</form:label>
						<form:input type="text" path="city" class="form-control" />
						<form:errors path="city" class="text-danger" />
						<label>State:</label> <select name="state" class="form-control">
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
							<option value="DE">Delaware</option>
							<option value="DC">District Of Columbia</option>
							<option value="FL">Florida</option>
							<option value="GA">Georgia</option>
							<option value="HI">Hawaii</option>
							<option value="ID">Idaho</option>
							<option value="IL">Illinois</option>
							<option value="IN">Indiana</option>
							<option value="IA">Iowa</option>
							<option value="KS">Kansas</option>
							<option value="KY">Kentucky</option>
							<option value="LA">Louisiana</option>
							<option value="ME">Maine</option>
							<option value="MD">Maryland</option>
							<option value="MA">Massachusetts</option>
							<option value="MI">Michigan</option>
							<option value="MN">Minnesota</option>
							<option value="MS">Mississippi</option>
							<option value="MO">Missouri</option>
							<option value="MT">Montana</option>
							<option value="NE">Nebraska</option>
							<option value="NV">Nevada</option>
							<option value="NH">New Hampshire</option>
							<option value="NJ">New Jersey</option>
							<option value="NM">New Mexico</option>
							<option value="NY">New York</option>
							<option value="NC">North Carolina</option>
							<option value="ND">North Dakota</option>
							<option value="OH">Ohio</option>
							<option value="OK">Oklahoma</option>
							<option value="OR">Oregon</option>
							<option value="PA">Pennsylvania</option>
							<option value="RI">Rhode Island</option>
							<option value="SC">South Carolina</option>
							<option value="SD">South Dakota</option>
							<option value="TN">Tennessee</option>
							<option value="TX">Texas</option>
							<option value="UT">Utah</option>
							<option value="VT">Vermont</option>
							<option value="VA">Virginia</option>
							<option value="WA">Washington</option>
							<option value="WV">West Virginia</option>
							<option value="WI">Wisconsin</option>
							<option value="WY">Wyoming</option>
						</select>
					</div>
					<div class="form-group">
						<form:label path="password">Password:</form:label>
						<form:password path="password" class="form-control" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="passwordConfirmation">Password Confirmation:</form:label>
						<form:password path="passwordConfirmation" class="form-control" />
						<form:errors path="passwordConfirmation" class="text-danger" />
					</div>
					<input type="submit" value="Register!" class="btn btn-primary" />
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>