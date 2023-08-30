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
<title>Agregar Pasajero</title>
</head>
<body>
  <form class="form-signin" action="PasajeroServlet?accion=insertarPasajero" method="post">
  		<label for="inputDni" class="sr-only">DNI</label>
    	 <input id="inputDni" name="dni" class="form-control" placeholder="Dni" required="" autofocus="" type="text">
  		 <label for="inputName" class="sr-only">Nombre</label>
    	 <input id="inputName" name="nombre" class="form-control" placeholder="Nombre" required="" autofocus="" type="text">
  		<label for="inputApellido" class="sr-only">Apellido</label>
    	 <input id="inputApellido" name="apellido" class="form-control" placeholder="Apellido" required="" autofocus="" type="text">
    	 <label for="inputEmail" class="sr-only">Email</label>
    	 <input id="inputEmail" name="email" class="form-control" placeholder="Email" required="" autofocus="" type="email">
      	 <label for="inputPassword" class="sr-only">Contraseña</label>
         <input id="inputPassword" name="contrasenia" class="form-control" placeholder="Contraseña" required="" type="password">
         <button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
   </form>
</body>
</html>