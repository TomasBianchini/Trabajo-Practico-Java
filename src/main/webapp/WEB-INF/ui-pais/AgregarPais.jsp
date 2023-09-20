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
<title>Agregar Pais</title>
</head>
<body>
  <form class="form-signin" action="PaisServlet?accion=insertar" method="post">
  		 <label for="inputName" class="sr-only">Nombre del Pais</label>
    	 <input id="inputName" name="nombre" class="form-control" placeholder="Nombre" required="" autofocus="" type="text">
 		 <button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
  </form>
  <td><a class="bg-danger text-white" href="PaisServlet"><button type="button" class="btn btn-danger">Cancelar</button></a></td>
</body>
</html>