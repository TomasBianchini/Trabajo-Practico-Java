<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entities.Avion"%>
<!DOCTYPE html>
<html data-theme="dark">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta charset="UTF-8">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<% String message = (String)request.getAttribute("message");%>
	<link rel="stylesheet" href="Styles/Agregar.css">
	    <% Avion avi = (Avion)request.getAttribute("avion");%>
	<title>Agregar Asiento</title>
</head>

<body>
	<div class="mensaje">
	    <% if (message != null && !message.isEmpty()) { %>
	        <script>
	            window.onload = function() {
	                Swal.fire({
	                    icon: '<%= message.startsWith("error")? "error" : "success"  %>',
	                    title: 'Message',
	                    text: '<%= message %>',
	                });
	            };
	        </script>
	    <% } %>
	</div>
	<form action="AvionServlet?accion=insertarAsiento" method="post">
		<input type="hidden" name="IdAvion" value="<%= avi.getIdAvion() %>">
 		<p>Avion: <%= avi.getIdAvion() %> </p>
		<div class="grid">
 	  		<label for="fila">Fila
    	 	<input id="fila" name="fila" required autofocus type="text">
    		</label>
    		
    	 	<label for= "numero">Número
    	 	<input id="numero" name="numero" required  type="text">
    	 	</label>
 		</div>
      	<label for="tipo" >
      		Tipo asiento
		    <select id="tipo" name="tipo" required>
		   	  <option value="" selected>Elegir tipo de asiento..</option>
		      <option value="Economico">Economico</option>
		      <option value="Ejecutivo">Ejecutivo</option>
		    </select>
    	 </label>
 		 <div class="grid">
        	<button type="submit">Agregar</button>
        	<a href="AvionServlet?accion=listarAsientos&idAvion=<%=avi.getIdAvion()%>"><button type="button" >Cancelar</button></a>
    	</div>
  	</form>
</body>
</html>