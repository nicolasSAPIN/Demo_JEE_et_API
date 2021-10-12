<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String userUpdate = request.getParameter("userUpdate");

%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Demo BEETWEEN</title>
		<meta name='viewport' content='width=device-width, initial-scale=1'>
		<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	</head>
	<body class=" bg-dark text-white">
	<header>
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="container-fluid">
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarExample01">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item active">
            <a class="nav-link" aria-current="page" href="">Home</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- Navbar -->

  <!-- Jumbotron -->
  <div class="p-5 text-center bg-">
    <h4 class="mb-3">Demo BEETWEEN</h4>
    <a class="btn btn-info" href="" role="button">Let's GO</a>
  </div>
  <!-- Jumbotron -->
</header>
	
<div class="container">
	<form action="index" method="POST">
	 <div class="row">
		 
		 <div class="col-sm">
				  	<div class="form-group">
						<label for="first_name" class="col-sm-4 col-form-label">first name</label>
						  <input type="text" id="first_name" name="first_name"  class="form-control"  placeholder="first name" value="<c:out value="${userUpdate.firstName}"/>" />
					</div>
					<div class="form-group">
						<label for:"last_name" class="col-sm-4 col-form-label">Last name : </label>	
						<input type="text" id="last_name" name="last_name" class="form-control"   placeholder="Last name" value="<c:out value="${userUpdate.lastName}"/>"/>
					</div>
					<div class="form-group">	
						<label for:"email"  class="col-sm-4 col-form-label">Email : </label>
						<input type="text" id="email" name="email"  class="form-control"  placeholder="Email" value="<c:out value="${userUpdate.email}"/>"/>
					</div>
					<div class="form-group">
						<label for:"phone"  class="col-sm-4 col-form-label">Phone : </label>
						<input type="text" id="phone" name="phone" class="form-control"  placeholder="Phone" value="<c:out value="${userUpdate.phone}"/>"/>	
					</div>
					<div class="form-group">
						<label for:"password"class="col-sm-4 col-form-label" >Password : </label>
						<input type="password" id="password" name="password" class="form-control"  placeholder="Password" value="<c:out value="${userUpdate.password}"/>"/>	
					</div>
					<div class="col-auto d-flex justify-content-end">
						<c:if test="${not empty userUpdate.id}">
							<input type="hidden" value="<c:out value="${userUpdate.id}"/>" name="id">	
							<button type="submit" name="btn_update" style="font-size:24px; color:orange" class="btn btn-link"><i class="material-icons"><font style="vertical-align: inherit;">group_add</font></i></button>
						</c:if>
						<c:if test="${empty userUpdate.id}">
							<button type="submit" name="btn_submit" style="color:green"class="btn btn-link"><font style="vertical-align: inherit;"> </font></font><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">group_add</font></font></i></button>
						</c:if>
						
							<button type="reset" name="btn_reset"  style="color:red" class="btn btn-link"><font style="vertical-align: inherit;"> </font><i class="material-icons"><font style="vertical-align: inherit;"><font style="vertical-align: inherit; border=2">do_not_disturb</font></font></i></button>
						
					</div>
			</div>
		    <div class="listeUsers col-sm text-white">
					<table class="table table-sm text-white ">
							<tr>
								<th scope="col">id</th>
								<th  scope="col">first name</th>
								<th  scope="col">last name</th>
								<th  scope="col">email</th>
								<th  scope="col">phone</th>
								<th  scope="col">password</th>
								<th  scope="col"></th>
								<th  scope="col"></th>
							</tr>
						<c:forEach var="u" items="${listeUsers}">
							<tr class="">
								<td>${u.id}</td>
								<td>${u.firstName}</td>
								<td>${u.lastName}</td>
								<td>${u.email}</td>
								<td>${u.phone}</td>
								<td>${u.password}</td>	
								<td>
									<input type="hidden" value="${u.id}" name="id">
									<button name="btn_updt" class="btn btn-link "><i class='fas fa-edit' style='color:orange; display:inline;'></i></button>
								</td>
								<td>
									<button name="btn_del" class="btn btn-link"><i class='fas fa-times-circle' style='color:red; display:inline;'></i></button>
								</td>
							</tr>
						</c:forEach>
					</table>
			</div>   
	</div>
	</form>		
</div>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	</body>
</html>