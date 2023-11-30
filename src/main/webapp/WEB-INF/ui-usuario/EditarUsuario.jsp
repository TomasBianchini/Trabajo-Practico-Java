<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Usuario"%>
<!DOCTYPE html>
<html data-theme="dark">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="Styles/Agregar.css">
	<%
	Usuario usu = (Usuario)request.getAttribute("Usuario");
    %>
<title>Editar Usuario</title>
</head>
<body>
	<h1>Editar Usuario</h1>
	
	  <form action="UsuarioServlet?accion=editarUsuario" method="post">
	 	<input type="hidden" name="idUsuario" value="<%= usu.getIdUsuario() %>">
	    <h1>Usuario: <%= usu.getIdUsuario() %></h1>
		<div class="grid">
	        <label for="tipoDocumento">Tipo documento:
	       		<input type="text" id="tipoDocumento" name="tipoDocumento" required value="<%= usu.getTipoDocumento() %>">
	       	</label>
	        <label for="dniPasajero">Numero de documento:
	       		<input type="text"  name="nroDocumento" required value="<%= usu.getNroDocumento() %>" >
	        </label>
		 </div>
		 <div class="grid">
	 		 <label for="inputName">Nombre
	    	 	<input id="inputName" name="nombre" required  type="text" value="<%= usu.getNombre() %>" >
	    	 </label>
	    	 <label for="apellido">Apellido	 
	    		 <input id="apellido" name="apellido" required  type="text" value="<%= usu.getApellido() %>" >
	    	 </label> 
    	 </div>
    	 <label for="fechaNacimiento">Fecha de Nacimiento
    		 <input id="fechaNacimiento" name="fechaNacimiento" required  type="date" value="<%= usu.getFechaNacimiento() %>" >
 		 </label>
      	 <label for="tipo" >
      		Tipo usuario
		    <select id="tipo" name="tipo" required>
		   	  <option value="" selected>Elegir tipo de usuario..</option>
		      <option value="admin">Admin</option>
		      <option value="user">Usuario</option>
		    </select>
    	 </label>
    	 <label for="email">Email
    	 <input id="email" name="email"  placeholder="Email" required  type="email" value="<%= usu.getEmail() %>" >
 		 </label>
 		 <label for="contrasenia">Contraseña
    	 <input id="contrasenia" name="contrasenia" required  type="password" value="<%= usu.getContrasenia() %>" >
		 </label>
		 <div class="grid">
        	<button type="submit">Agregar</button>
        	<a href="UsuarioServlet"><button type="button" >Cancelar</button></a>
    	</div>
 	 </form>
</body>
</html>