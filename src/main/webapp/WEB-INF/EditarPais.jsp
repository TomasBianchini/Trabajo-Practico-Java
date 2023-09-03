<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Pais"%>
<!DOCTYPE html>
<html>
<head>
	<%
    	Pais pais = (Pais)request.getAttribute("Pais");
    %>
<meta charset="UTF-8">
<title>Editar Pais</title>
</head>
<body>
	<h1>Editar Pais</h1>
	
	  <form class="form-signin" action="PaisServlet?accion=editarPais" method="post">
        <label for="idpais">ID del País:</label>
        <input type="text"  name="idPais" value="<%= pais.getIdPais() %>" readonly>
         <br></br>
  		 <label for="inputName" class="sr-only">Nombre del Pais</label>
    	 <input id="inputName" name="nombre" class="form-control" placeholder="Nombre" required="" autofocus="" type="text" value="<%= pais.getNombre() %>" >
 		 <button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>
 	 </form>
 




</body>
</html>