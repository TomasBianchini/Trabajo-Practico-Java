<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="entities.Ciudad" %>
 <%@ page import="java.util.LinkedList" %>
<!DOCTYPE html>
<html data-theme="dark">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="Styles/Agregar.css">
	<%
    	LinkedList<Ciudad> listaCiudades= (LinkedList<Ciudad>)request.getAttribute("listaCiudades");
	%>
<title>Agregar Aeropuerto</title>
</head>
<body>
  <form action="AeropuertoServlet?accion=insertar" method="post">
 		 <label for="nombre" >
 		 	Nombre
    		<input id="nombre" name="nombre" placeholder="Nombre" required autofocus type="text"> 
    	 </label>
 	  	 <label for="descripcion">
 	  	 	Descripcion
    	 	<input id="descripcion" name="descripcion" placeholder="Descripcion" required type="text">
 		 </label>
 		 <label for="nombreCiudad">
	  		 	Ciudad
		 	<select id="nombreCiudad" name="nombreCiudad" required>
		   		<option value="" selected>Elegir ciudad..</option>
		   		<%for(Ciudad ciu: listaCiudades){ %>
		   	    <option value="<%=ciu.getNombre()%>"><%=ciu.getNombre()%></option>
		   	    <%}%>
		   	 </select>
		</label>
 		<div class="grid">
        	<button type="submit">Agregar</button>
        	<a href="AeropuertoServlet"><button type="button" >Cancelar</button></a>
    	</div>
  </form>
</body>
</html>

