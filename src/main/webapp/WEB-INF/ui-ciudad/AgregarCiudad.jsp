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
<title>Agregar Ciudad</title>
</head>
<body>
  <form class="form-signin" action="CiudadServlet?accion=insertar" method="post">
  		 <label for="inputCodPostal" class="sr-only">Codigo Postal</label>
    	 <input id="inputCodPostal" name="codPostal" class="form-control" placeholder="Codigo postal" required="" autofocus="" type="text">
 		 <label for="inputName" class="sr-only">Nombre</label>
    	 <input id="inputName" name="nombre" class="form-control" placeholder="Nombre" required="" autofocus="" type="text"> 
 	  	 <label for="inputIdPais" class="sr-only">Codigo del Pais</label>
    	 <input id="inputIdPais" name="idPais" class="form-control" placeholder="Codigo del pais" required="" autofocus="" type="text">
 		 <button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
  </form>
</body>
</html>