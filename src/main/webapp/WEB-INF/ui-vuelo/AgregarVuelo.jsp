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
<title>Agregar Vuelo</title>
</head>
<body>
  <form class="form-signin" action="VueloServlet?accion=insertar" method="post">
  		 <label for="inputIdvuelo" class="sr-only">ID vuelo</label>
    	 <input id="inputIdvuelo" name="idvuelo" class="form-control" placeholder="Id vuelo" required="" autofocus="" type="text">
 		 <label for="inputFechaHoraSalida" class="sr-only">Fecha y hora de salida</label>
    	 <input id="inputFechaHoraSalida" name="fechaHoraSalida" class="form-control" placeholder=" " required="" autofocus="" type="datetime-local"> 
		 <label for="inputFechaHoraLlegada" class="sr-only">Fecha y hora de llegada</label>
    	 <input id="inputFechaHoraLlegada" name="fechaHoraLlegada" class="form-control" placeholder=" " required="" autofocus="" type="datetime-local"> 	  
    	 <label for="inputAeropuertoOrigen" class="sr-only">Id Aeropuerto de origen</label>
    	 <input id="inputAeropuertoOrigen" name="idAeropuertoOrigen" class="form-control" placeholder="Aeropuerto de origen" required="" autofocus="" type="text">	
 	     <label for="inputAeropuertoDestino" class="sr-only">Id Aeropuerto de destino</label>
    	 <input id="inputAeropuertoDestino" name="idAeropuertoDestino" class="form-control" placeholder="Aeropuerto de destino" required="" autofocus="" type="text">		 
 	     <label for="inputIdAvion" class="sr-only">Id del avion</label>
    	 <input id="inputIdAvion" name="idAvion" class="form-control" placeholder="id del avion" required="" autofocus="" type="text"> 	
 		 <button class="btn btn-lg btn-primary btn-block" type="submit">Agregar</button>
  </form>
</body>
</html>