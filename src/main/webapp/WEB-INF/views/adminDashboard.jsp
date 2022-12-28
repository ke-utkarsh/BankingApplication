<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.*, java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Tables - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/stylesadmin.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
<title>Admin Dashboard</title>
</head>
<body>
<centre><h3><a class="nav-link" href="/logout">LOGOUT</a></h3></centre>
	<div class="card mb-4">
		<div class="card-header">
        <i class="fas fa-table me-1"></i>
        Customer Data Tables
        </div>
        <div class="card-body">
        	<sql:setDataSource var='dbsource' driver='com.mysql.jdbc.Driver' url="jdbc:mysql://localhost/bank3" user="root" password="Root" />
        	<sql:query dataSource="${dbsource}" var="result">
        		SELECT * FROM user WHERE roles='CUSTOMER';
			</sql:query>
			<form>
				<table id="datatablesSimple">
					<thead>
                        <tr>
                           <th>Username</th>
                           <th>Email</th>
                           <th>Password</th>
                           <th>Balance</th>
                         </tr>
                     </thead>
                     <tbody>
                     	<c:forEach var='row' items="${result.rows}">
							<tr>
								<td><c:out value="${row.username }"></c:out></td>
								<td><c:out value="${row.email }"></c:out></td>
								<td><c:out value="${row.password }"></c:out></td>
								<td><c:out value="${row.amount }"></c:out></td>
							</tr>
						</c:forEach>
                     </tbody>
				</table>
			</form>
        </div>
	</div>
	<div class="container mt-5">
		<h3 class="text-center">Delete User</h3>
		<form action="delete" method="POST">
			<div class="form-group">
				<label for="exampleInputUserName">User Name</label> <input
					type="text" class="form-control" id="exampleInputUserName"
					aria-describedby="UserNameHelp" placeholder="Enter UserName"
					name="username">
			</div>
			<div class ="text-center">
			<button type="submit" class="btn btn-warning">Delete</button>
			</div>
		</form>
	</div>
	
	<div class="container mt-5">

		<h3 class="text-center">Register Here</h3>
		<form action="register" method="POST">
			<div class="form-group">
				<label for="exampleInputEmail1">Email address</label> <input
					type="email" class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" placeholder="Enter email" name="email">
			</div>

			<div class="form-group">
				<label for="exampleInputUserName">User Name</label> <input
					type="text" class="form-control" id="exampleInputUserName"
					aria-describedby="UserNameHelp" placeholder="Enter UserName"
					name="username">
			</div>

			<div class="form-group">
				<label for="exampleInputPassword">Password</label> <input
					type="password" class="form-control" id="exampleInputPassword"
					aria-describedby="PasswordHelp" placeholder="Enter Password"
					name="password">
			</div>
			
			<div class="form-group">
				<label for="exampleInputPassword">Initial Balance</label> <input
					type="number" class="form-control" id="exampleInputPassword"
					aria-describedby="PasswordHelp" placeholder="Enter intial balance"
					name="amount">
			</div>
			
			<div class ="text-center">
			<button type="submit" class="btn btn-warning">Submit</button>
			</div>
		</form>


	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</body>