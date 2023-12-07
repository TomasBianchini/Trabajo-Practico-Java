<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-theme="dark">
<head>
	 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	 <meta charset="UTF-8">
	 <link rel="stylesheet" href="Styles/Agregar.css">

<title>Agregar Usuario</title>
</head>
<body>
  <form action="singup" method="post">
    <div class="grid">
    	<label for="tipoDocumento">
    		Tipo documento:
       		<input id="tipoDocumento" name="tipoDocumento" placeholder="Tipo Documento" required autofocus type="text">
        </label>
        <label for="nroDocumento">
        	Numero documento:
       		 <input id="nroDocumento" name="nroDocumento" placeholder="Numero Documento" required type="text">
    	</label>
    </div>
    <div class="grid">
 		 <label for="nombre" >
 		 	Nombre
    	 	<input id="nombre" name="nombre" placeholder="Nombre" required type="text" >
    	</label>
    	 <label for="apellido">
    	 	Apellido
    	    <input id="apellido" name="apellido"  placeholder="Apellido" required type="text" >
     	</label>
     </div>
     <div class="grid">
       <label for="fechaNacimiento">
       		Fecha de Nacimiento
 		   <input id="fechaNacimiento" name="fechaNacimiento"  required  type="date" >
  	   </label>
	 </div>
    <label for="email">
    	Email
        <input id="email" name="email"  placeholder="Email" required type="email">
    </label>
 	<label for="contrasenia" >
 		Contraseña
    	<input id="contrasenia" name="contrasenia" placeholder="contraseña" required type="password">
    </label>
	<div class="grid">
         <button type="submit">Aceptar</button>
         <a href="index.html"><button type="button">Cancelar</button></a>
    </div>
   </form>

</body>
</html>