<%@page import="entities.Pasajero"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.CtrlPasajero"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
<title>Lista Pasajero</title>
  <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    <link href="styles/bootstrap.min.css" rel="stylesheet">
	<%

    	LinkedList<Pasajero> listaPas = (LinkedList<Pasajero>)request.getAttribute("listaPasajero");
    %>
</head>
<body style="background-color:rgb(251, 252, 255);">

<div class="fixed-top">

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <!-- Brand/logo -->
	  <a class="navbar-brand mb-0 h1" href="MenuPrincipal.jsp">Menu Principal</a>
	  
	  <!-- Links -->
	  <ul class="navbar-nav">
	   
	    <li class="nav-item">
	      <a class="nav-link" href="ListaPasajeros.jsp">Pasajeros</a>
	    </li>
	  </ul>
	</nav>
	<br>

<div class="container">
	<div class="mt-4 p-5 bg-info text-white rounded">
	  <h1>Lista de Pasajeros</h1>
	</div>
   <!-- Lista Usuario -->
  <table class="table table-fixed table-condensed">
    <thead class="table-dark">
      <tr>
        <th>Dni Pasajero</th>
        <th>Apellido</th>
        <th>Nombre</th>
        <th>Email</th>
        <th>Eliminar</th>
      </tr>
    </thead>
    <tbody>
    <%for(Pasajero pas: listaPas){ %>
      <tr>
        <td><%=pas.getDni() %> </td>
        <td><%=pas.getApellido() %></td>
        <td><%=pas.getNombre() %></td>
        <td><%=pas.getEmail() %></td>

        
        <td><a class="bg-primary text-white" href="PasajeroServlet?accion=editar&dniPasajero=<%=pas.getDni()%>"><button type="button" class="btn btn-primary">Editar</button></a></td>
        <td><a class="bg-danger text-white" href="PasajeroServlet?accion=eliminar&dniPasajero=<%=pas.getDni()%>"><button type="button" class="btn btn-danger">Eliminar</button></a></td>
      </tr>
      <%} %>
     
    </tbody>
  </table>
  
   <a class="bg-danger text-white" href="PasajeroServlet?accion=AgregarPasajero"><button type="button" class="btn btn-primary">Agregar pasajero</button></a>
	

 

</div>
</div>





<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>




</body>
</html>