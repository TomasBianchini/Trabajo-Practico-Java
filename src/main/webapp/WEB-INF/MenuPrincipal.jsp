<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="entities.Pasajero" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <%
    	String email = (String) request.getAttribute("email");
   		String password = (String) request.getAttribute("password");
		Pasajero p = (Pasajero) request.getSession().getAttribute("pasajero");
	%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<title>Menu Principal</title>
</head>
<body>
	<button type="button" class="btn btn-primary btn-lg btn-block">Block level button</button>
	<button type="button" class="btn btn-secondary btn-lg btn-block">Block level button</button>
	<p><%=email%></p>
	<p><%=password%></p>
	<p><%=p.getNombre()%></p>




</body>
</html>