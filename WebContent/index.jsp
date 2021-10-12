<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<meta name='viewport' content='width=device-width, initial-scale=1'>
		<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
	</head>
	<body class=" bg-dark text-white">
	<div class="container">
	  <div class="row">
		    <div class="col-sm">
			
				  <div class="form-group">
				<form action="index" method="POST">

						<label for="first_name" class="col-sm-4 col-form-label">first name</label>
						<input type="text" id="first_name" name="first_name"  class="form-control"  placeholder="first name" value=""/>
					</div>
					<div class="form-group">
						<label for:"last_name" class="col-sm-4 col-form-label">Last name : </label>	
						<input type="text" id="last_name" name="last_name" class="form-control"   placeholder="Last name" value=""/>
					</div>
					<div class="form-group">	
						<label for:"email"  class="col-sm-4 col-form-label">Email : </label>
						<input type="text" id="email" name="email"  class="form-control"  placeholder="Email" value=""/>
					</div>
					<div class="form-group">
						<label for:"phone"  class="col-sm-4 col-form-label">Phone : </label>
						<input type="text" id="phone" name="phone" class="form-control"  placeholder="Phone" value=""/>	
					</div>
					<div class="form-group">
						<label for:"password" class="col-sm-4 col-form-label" >Password : </label>
						<input type="text" id="password" name="password" class="form-control"  placeholder="Password" value=""/>	
					</div>
					<div class="col-auto">
						<input type="submit" name="btn_submit"  class="btn btn-primary mb-2" value="Add">
					</div>
					
				</form>
				
			</div>
		    <div class="col-sm text-white">
	
					<table class="table table-sm text-white ">
							<tr>
								<th scope="col">id</th>
								<th  scope="col">first name</th>
								<th  scope="col">last name</th>
								<th  scope="col">email</th>
								<th  scope="col">phone</th>
								<th  scope="col">password</th>
							</tr>
						<c:forEach var="u" items="${listeUsers}">
							
							<tr class="bg-white">
								<td>${u.id}</td>
								<td>${u.firstName}</td>
								<td>${u.lastName}</td>
								<td>${u.email}</td>
								<td>${u.phone}</td>
								<td>${u.password}</td>
													
							<td>
							<form action="index" method="POST">	
							<input type="hidden" value="${u.id}" name="id">
							<button name="del" class="btn btn-link"><i class='fas fa-times-circle' style='font-size:14px;color:red'></i></button>
							</form>	
								
							</tr>
						</c:forEach>
					</table>
			</div>
	</div>
</div>
			
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
			
	
	</body>
</html>