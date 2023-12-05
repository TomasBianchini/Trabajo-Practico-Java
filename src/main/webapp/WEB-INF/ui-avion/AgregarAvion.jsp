<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-theme="dark">
<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	 	<link rel="stylesheet" href="Styles/Agregar.css">
	 	 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	 	<% String message = (String)request.getAttribute("message");%>
<meta charset="UTF-8">
<title>Agregar Avion</title>
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
  <form action="AvionServlet?accion=insertarAvion" method="post">
  		 <label for="marca">
  		 	Marca
    	 	<input id="marca" name="marca" placeholder="Marca" required autofocus type="text">
 		 </label>
 		 <label for="modelo">
 		 	Modelo
    	 	<input id="modelo" name="modelo" placeholder="Modelo" required type="text">
    	 </label>
    	 <label for="anio">
    	 	Año
    	 	<input id="anio" name="anio" placeholder="Año" required type="text">
    	 </label>
    	 <div class="grid">
        	<button type="submit">Agregar</button>
        	<a href="AvionServlet"><button type="button">Cancelar</button></a>
  		 </div>
  </form>

</body>
</html>