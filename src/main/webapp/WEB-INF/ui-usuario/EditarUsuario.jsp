<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Usuario"%>
<!DOCTYPE html>
<html>
<head>
	<%
	Usuario usu = (Usuario)request.getAttribute("Usuario");
    %>
<meta charset="UTF-8">
<title>Editar Usuario</title>
</head>
<body>
	<h1>Editar Usuario</h1>
	
	  <form class="form-signin" action="UsuarioServlet?accion=editarUsuario" method="post">
	    <label for="inputTipoDocumento">Id Usuario:</label>
        <input type="text"  name="idUsuario" value="<%= usu.getIdUsuario() %>" readonly>
        <label for="inputTipoDocumento">Tipo documento:</label>
        <input type="text"  name="tipoDocumento" value="<%= usu.getTipoDocumento() %>" readonly>
        <label for="dniPasajero">Numero de documento:</label>
        <input type="text"  name="nroDocumento" value="<%= usu.getNroDocumento() %>" readonly>
         <br></br>
 		 <label for="inputName" class="sr-only">Nombre</label>
    	 <input id="inputName" name="nombre" class="form-control" placeholder="Nombre" required autofocus type="text" value="<%= usu.getNombre() %>" >
    	 <label for="inputApellido" class="sr-only">Apellido</label>
    	 <input id="inputApellido" name="apellido" class="form-control" placeholder="Apellido" required autofocus type="text" value="<%= usu.getApellido() %>" >
    	 <label for="inputEmail" class="sr-only">Email</label>
    	 <input id="inputEmail" name="email" class="form-control" placeholder="Email" required autofocus type="email" value="<%= usu.getEmail() %>" >
 		 <label for="inputContrasenia" class="sr-only">Contraseña</label>
    	 <input id="inputContrasenia" name="contrasenia" class="form-control" placeholder="contraseña" required autofocus type="password" value="<%= usu.getContrasenia() %>" >
 		 <label for="inputFechaNacimiento" class="sr-only">Fecha de Nacimiento</label>
    	 <input id="inputFechaNacimiento" name="fechaNacimiento" class="form-control" placeholder="Fecha de nacimiento" required autofocus type="text" value="<%= usu.getFechaNacimiento() %>" >
 		 <label for="inputTipo" class="sr-only">Tipo usuario</label>
    	 <input id="inputTipo" name="tipo" class="form-control" placeholder="Tipo usuario" required autofocus type="text" value="<%= usu.getTipo() %>" >
 		
 		 <button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>

 	 </form>
 <td><a class="bg-danger text-white" href="UsuarioServlet"><button type="button" class="btn btn-danger">Cancelar</button></a></td>

</body>
</html>