<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Aeropuerto"%>
<!DOCTYPE html>
<html data-theme="dark">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta charset="UTF-8">
	<link rel="stylesheet" href="Styles/Agregar.css">
	<%
	Aeropuerto aero = (Aeropuerto)request.getAttribute("Aeropuerto");
    %>

<meta charset="UTF-8">
<title>Editar Aeropuerto</title>
</head>
<body>
	<h1>Editar Aeropuerto</h1>	
	  <form action="AeropuertoServlet?accion=editarAeropuerto" method="post">
	 	 <input type="hidden" name="idAeropuerto" value="<%=aero.getIdAeropuerto() %>">
		 <h1>Ubicación <%=aero.getCiudad().getNombre()%>, <%=aero.getCiudad().getPais().getNombre()%></h1>
  		 <label for="nombre" >	
  		 	 Nombre del Aeropuerto: 
    		 <input id="nombre" name="nombre"   required autofocus type="text" value="<%= aero.getNombre() %>" >
    	 </label>
 		 <label for="descAeropuerto">
 		 	Descripcion del Aeropuerto: 
    	 	<input id="descAeropuerto" name="descAeropuerto"  placeholder="Descripcion" required type="text" value="<%= aero.getDescAeropuerto() %>">
    	 </label>
 		 <div class="grid">
         	<button type="submit">Actualizar</button>
        	<a href="AeropuertoServlet"><button type="button">Cancelar</button></a>
  	     </div>
 	 </form>
</body>
</html>