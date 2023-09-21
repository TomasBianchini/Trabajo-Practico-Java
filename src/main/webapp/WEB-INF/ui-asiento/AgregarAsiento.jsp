<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entities.Avion"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
		<meta charset="UTF-8">
	    <% Avion avi = (Avion)request.getAttribute("avion");%>
		<title>Agregar Asiento</title>
	</head>
	<body>
  		<form class="form-signin" action="AvionServlet?accion=insertarAsiento" method="post">
 			<label>ID Avion</label>
    	 	<input  name="IdAvion" class="form-control" type="text" value="<%= avi.getIdAvion() %>" readonly >
 	  	 	
 	  	 	<label>Fila</label>
    	 	<input id="inputFila" name="inputFila" class="form-control" required autofocus type="text">
 		 	
 		 	<label>Numero</label>
    	 	<input id="inputNumero" name="inputNumero" class="form-control" required autofocus type="text">
    	 	<label>Tipo</label>
    	 	<input id="tipo" name="tipo" class="form-control" required autofocus type="text">
 		 	
 		 	<button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
  		</form>
  		<td><a class="bg-danger text-white" href="AvionServlet?accion=listarAsientos&idAvion=<%=avi.getIdAvion()%>"><button type="button" class="btn btn-danger">Cancelar</button></a></td>
	</body>
</html>