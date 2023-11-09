<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
<meta charset="UTF-8">
<title>Agregar Usuario</title>
</head>
<body>
  <form class="form-signin" action="UsuarioServlet?accion=insertarUsuario" method="post">
    	<label for="inputTipoDocumento" class="sr-only">Tipo documento:</label>
        <input id="inputTipoDocumento" name="tipoDocumento" class="form-control" placeholder="Tipo Documento" required="" autofocus="" type="text">
        <label for="inputTipoDocumento" class="sr-only">Numero documento:</label>
        <input id="inputTipoDocumento" name="nroDocumento" class="form-control" placeholder="Numero Documento" required="" autofocus="" type="text">
    
 		 <label for="inputName" class="sr-only">Nombre</label>
    	 <input id="inputName" name="nombre" class="form-control" placeholder="Nombre" required autofocus type="text" >
    	 <label for="inputApellido" class="sr-only">Apellido</label>
    	 <input id="inputApellido" name="apellido" class="form-control" placeholder="Apellido" required autofocus type="text" >
    	 <label for="inputEmail" class="sr-only">Email</label>
    	 <input id="inputEmail" name="email" class="form-control" placeholder="Email" required autofocus type="email">
 		 <label for="inputContrasenia" class="sr-only">Contraseña</label>
    	 <input id="inputContrasenia" name="contrasenia" class="form-control" placeholder="contraseña" required autofocus type="password">
 		 <label for="inputFechaNacimiento" class="sr-only">Fecha de Nacimiento</label>
    	 <input id="inputFechaNacimiento" name="fechaNacimiento" class="form-control" placeholder="Fecha de nacimiento" required autofocus type="date" >
 		 <label for="inputTipo" class="sr-only">Tipo usuario</label>
    	 <input id="inputTipo" name="tipo" class="form-control" placeholder="Tipo usuario" required autofocus type="text" >
 
         <button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
   </form>
   <td><a class="bg-danger text-white" href="UsuarioServlet"><button type="button" class="btn btn-danger">Cancelar</button></a></td>
</body>
</html>