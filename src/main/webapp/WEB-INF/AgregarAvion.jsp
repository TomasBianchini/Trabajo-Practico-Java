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
<title>Agregar Avion</title>
</head>
<body>
  <form class="form-signin" action="AvionServlet?accion=insertarAvion" method="post">
  		 <label for="inputCantidadAsientos" class="sr-only">Cantidad de asientos</label>
    	 <input id="inputCantidadAsientos" name="cantAsientos" class="form-control" placeholder="Cantidad de asientos" required="" autofocus="" type="text">
 		 <button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
  </form>
</body>
</html>