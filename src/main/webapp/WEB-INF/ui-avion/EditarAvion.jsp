<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Avion"%>
<!DOCTYPE html>
<html>
<head>
	<%
    	Avion avion = (Avion)request.getAttribute("Avion");
    %>
<meta charset="UTF-8">
<title>Editar Avion</title>
</head>
<body>
	<h1>Editar Avion</h1>
	
	  <form class="form-signin" action="AvionServlet?accion=editarAvion" method="post">
        <label for="idAvion">ID del Avion:</label>
        <input type="text"  name="idAvion" value="<%= avion.getIdAvion() %>" readonly>
         <br></br>
  		 <label for="inputCantidadAsientos" class="sr-only">Cantidad de asientos</label>
    	 <input id="inputCantidadAsientos" name="cantidadAsientos" class="form-control" placeholder="cantidad Asientos" required="" autofocus="" type="number" value="<%= avion.getCantAsientos() %>" >
 		 <button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>
 	 </form>
 




</body>
</html>