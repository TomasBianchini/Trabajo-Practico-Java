<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Ciudad"%>
<!DOCTYPE html>
<html>
<head>
	<%
    	Ciudad ciudad = (Ciudad)request.getAttribute("Ciudad");
    %>
<meta charset="UTF-8">
<title>Editar Ciudad</title>
</head>
<body>
	<h1>Editar Ciudad</h1>
	
	  <form class="form-signin" action="CiudadServlet?accion=editarCiudad" method="post">
        <label for="idpais">Codigo Postal del Ciudad:</label>
        <input type="text"  name="codPostal" value="<%= ciudad.getCodPostal() %>" readonly>
         <br></br>
  		 <label for="inputName" class="sr-only">Nombre de la ciudad</label>
    	 <input id="inputName" name="nombre" class="form-control" placeholder="Nombre" required="" autofocus="" type="text" value="<%= ciudad.getNombre() %>" >
 		 <button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>
 	 </form>
 
<td><a class="bg-danger text-white" href="CiudadServlet"><button type="button" class="btn btn-danger">Cancelar</button></a></td>


</body>
</html>