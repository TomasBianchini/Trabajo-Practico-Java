<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Ciudad"%>
<!DOCTYPE html>
<html data-theme="dark">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="Styles/Agregar.css">
	<%
    	Ciudad ciudad = (Ciudad)request.getAttribute("Ciudad");
    %>
<meta charset="UTF-8">
<title>Editar Ciudad</title>
</head>
<body>
	<h1>Editar Ciudad</h1>
	  <form action="CiudadServlet?accion=editarCiudad" method="post">
		 <input type="hidden" name="pais" value="<%= ciudad.getPais().getNombre()%>">
	  	<h1>Pais: <%= ciudad.getPais().getNombre()%></h1>
        <label for="codPostal">
        	Codigo Postal
       		<input type="text" id="codPostal" name="codPostal" required autofocus value="<%=ciudad.getCodPostal() %>">
        </label>
  		<label for="nombre">
  			Nombre
  			<input id="nombre" name="nombre" required type="text" value="<%=ciudad.getNombre() %>" >
  		</label>
 		<div class="grid">
         	<button type="submit">Actualizar</button>
        	<a href="CiudadServlet"><button type="button">Cancelar</button></a>
  		</div>
 	 </form>

</body>
</html>