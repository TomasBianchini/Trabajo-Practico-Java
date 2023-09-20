<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
		<meta charset="UTF-8">
		<title>Agregar Asiento</title>
	</head>
	<body>
  		<form class="form-signin" action="AsientoServlet?accion=insertar" method="post">
 			<label>ID Avion</label>
    	 	<input id="inputIdAvion" name="inputIdAvion" class="form-control" required autofocus type="text">
 	  	 	
 	  	 	<label>Fila</label>
    	 	<input id="inputFila" name="inputFila" class="form-control" required autofocus type="text">
 		 	
 		 	<label>Numero</label>
    	 	<input id="inputNumero" name="inputNumero" class="form-control" required autofocus type="text">
    	 	<label>Tipo</label>
    	 	<input id="tipo" name="tipo" class="form-control" required autofocus type="text">
 		 	
 		 	<button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
  		</form>
  		<td><a class="bg-danger text-white" href="AsientoServlet"><button type="button" class="btn btn-danger">Cancelar</button></a></td>
	</body>
</html>