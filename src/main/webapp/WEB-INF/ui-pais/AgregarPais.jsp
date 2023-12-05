<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-theme="dark">
<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	 	<link rel="stylesheet" href="Styles/Agregar.css">
	 	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	 	<% String message = (String)request.getAttribute("message");%>
<meta charset="UTF-8">
<title>Agregar Pais</title>
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
  <form action="PaisServlet?accion=insertar" method="post">
  		 <label for="nombre">Nombre del Pais</label>
    	 <input id="nombre" name="nombre" placeholder="Nombre" required autofocus type="text">
    	 <div class="grid">
        	 <button type="submit">Agregar</button>
        	 <a href="PaisServlet"><button type="button">Cancelar</button></a>
  		  </div>
  </form>
</body>
</html>