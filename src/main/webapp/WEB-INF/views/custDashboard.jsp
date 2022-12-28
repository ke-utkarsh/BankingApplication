<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*, java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/stylesadmin.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
<title>Customer Dashboard!</title>
</head>
<body>
<centre><h3><a class="nav-link" href="/logout">LOGOUT</a></h3></centre>
	<centre><h1>Welcome ${Username}</h1></centre>
	<div>
	<div class="card mb-4">
		<div class="card-header">
        <i class="fas fa-table me-1"></i>
		<div class="card-body">
			<sql:setDataSource var='dbsource' driver='com.mysql.jdbc.Driver' url="jdbc:mysql://localhost/bank3" user="root" password="Root" />
        	<sql:query dataSource="${dbsource}" var="result">
        		SELECT * FROM user WHERE username="${Username}";
			</sql:query>
			<table id="datatablesSimple">
					<thead>
                        <tr>
                           <th>Your Email Is</th>
                           
                           <th>Account Balance</th>
                         </tr>
                     </thead>
                     <tbody>
                     	<c:forEach var='row' items="${result.rows}">
							<tr>
								
								<td><c:out value="${row.email }"></c:out></td>
								
								<td><c:out value="${row.amount }"></c:out></td>
							</tr>
						</c:forEach>
                     </tbody>
            </table>
		</div>
		</div>
		</div>
	</div>
	<div class="container mt-5">
		<h3 class="text-center">Send Money</h3>
		<form action="transfer" method="POST">
			<div class="form-group">
				<label for="exampleInputUserName"></label> <input
					type="hidden" class="form-control" id="exampleInputUserName"
					aria-describedby="UserNameHelp" placeholder="Enter Amount"
					name="username1" value=${Username }>
				<label for="exampleInputUserName">User Name</label> <input
					type="text" class="form-control" id="exampleInputUserName"
					aria-describedby="UserNameHelp" placeholder="Enter UserName to trasfer money..."
					name="username2">
				<label for="exampleInputUserName">Amount</label> <input
					type="number" class="form-control" id="exampleInputUserName"
					aria-describedby="UserNameHelp" placeholder="Enter Amount"
					name="amount">
			</div>
			<div class ="text-center">
			<button type="submit" class="btn btn-warning">Send</button>
			</div>
		</form>
		<c:if test="${not empty transMsg}">
              <p style="color: green">
              <c:out value="Transaction Successful"></c:out>
              </p>
        </c:if>
        <c:if test="${not empty transErrorMsg}">
              <p style="color: green">
              <c:out value="Can't send to your own account"></c:out>
              </p>
        </c:if>
        
	</div>
	
	<div class="container mt-5">
		<h3 class="text-center">Credit</h3>
		<form action="credit" method="POST">
			<div class="form-group">
				<label for="exampleInputUserName"></label> <input
					type="hidden" class="form-control" id="exampleInputUserName"
					aria-describedby="UserNameHelp" placeholder="Enter Amount"
					name="username" value=${Username }>
				<label for="exampleInputUserName">Amount</label> <input
					type="number" class="form-control" id="exampleInputUserName"
					aria-describedby="UserNameHelp" placeholder="Enter Amount"
					name="amount">
			</div>
			<div class ="text-center">
			<button type="submit" class="btn btn-warning">Credit</button>
			</div>
		</form>
		<c:if test="${not empty credMsg}">
              <p style="color: green">
              <c:out value="Credited Successful"></c:out>
              </p>
        </c:if>
        
	</div>
	
	<div class="container mt-5">
		<h3 class="text-center">Debit</h3>
		<form action="debit" method="POST">
			<div class="form-group">
				<label for="exampleInputUserName"></label> <input
					type="hidden" class="form-control" id="exampleInputUserName"
					aria-describedby="UserNameHelp" placeholder="Enter Amount"
					name="username" value=${Username }>
				<label for="exampleInputUserName">Amount</label> <input
					type="number" class="form-control" id="exampleInputUserName"
					aria-describedby="UserNameHelp" placeholder="Enter Amount"
					name="amount">
			</div>
			<div class ="text-center">
			<button type="submit" class="btn btn-warning">Debit</button>
			</div>
		</form>
		<c:if test="${not empty debtMsg}">
              <p style="color: green">
              <c:out value="Debited Successful"></c:out>
              </p>
        </c:if>
        
	</div>
	
	
</body>