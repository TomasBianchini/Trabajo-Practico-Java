<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Pais"%>
<!DOCTYPE html>
<html data-theme="dark">
<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	 	<link rel="stylesheet" href="Styles/Agregar.css">
	<%
    	Pais pais = (Pais)request.getAttribute("Pais");
    %>

<title>Editar Pais</title>
</head>
<body>
	<h1>Editar Pais</h1>
	
	  <form action="PaisServlet?accion=editarPais" method="post">
	  	<input type="hidden" name="idPais" value="<%= pais.getIdPais() %>">
 		<p>Pais: <%= pais.getIdPais() %> </p>
  		 <label for="nombre" >
  		 	Nombre
    	 	<input id="nombre" name="nombre" required type="text" value="<%= pais.getNombre() %>" >
    	 </label>
 		 <div class="grid">
        	 <button type="submit">Actualizar</button>
        	 <a href="PaisServlet"><button type="button">Cancelar</button></a>
  		 </div>
 	 </form>

</body>
</html>