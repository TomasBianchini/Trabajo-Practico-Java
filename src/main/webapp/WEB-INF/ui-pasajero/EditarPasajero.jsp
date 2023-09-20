<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Pasajero"%>
<!DOCTYPE html>
<html>
<head>
	<%
		Pasajero pas = (Pasajero)request.getAttribute("Pasajero");
    %>
<meta charset="UTF-8">
<title>Editar Pasajero</title>
</head>
<body>
	<h1>Editar Pasajero</h1>
	
	  <form class="form-signin" action="PasajeroServlet?accion=editarPasajero" method="post">
        <label for="dniPasajero">DNI del Pasajero:</label>
        <input type="text"  name="dniPasajero" value="<%= pas.getDni() %>" readonly>
         <br></br>
 		 <label for="inputName" class="sr-only">Nombre</label>
    	 <input id="inputName" name="nombre" class="form-control" placeholder="Nombre" required autofocus type="text" value="<%= pas.getNombre() %>" >
    	 <label for="inputApellido" class="sr-only">Apellido</label>
    	 <input id="inputApellido" name="apellido" class="form-control" placeholder="Apellido" required autofocus type="text" value="<%= pas.getApellido() %>" >
    	 <label for="inputEmail" class="sr-only">Email</label>
    	 <input id="inputEmail" name="email" class="form-control" placeholder="Email" required autofocus type="email" value="<%= pas.getEmail() %>" >
 		 <label for="inputContrasenia" class="sr-only">Contraseña</label>
    	 <input id="inputContrasenia" name="contrasenia" class="form-control" placeholder="contraseña" required autofocus type="password" value="<%= pas.getContrasenia() %>" >
 		 <button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>
 	 </form>
 <td><a class="bg-danger text-white" href="PasajeroServlet"><button type="button" class="btn btn-danger">Cancelar</button></a></td>

</body>
</html>