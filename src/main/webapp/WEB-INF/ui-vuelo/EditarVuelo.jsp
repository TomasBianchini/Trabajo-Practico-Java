<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Vuelo"%>
<!DOCTYPE html>
<html>
<head>
	<%
	    Vuelo vuelo = (Vuelo)request.getAttribute("Vuelo");
    %>
<meta charset="UTF-8">
<title>Editar Vuelo</title>
</head>
<body>
	<h1>Editar Vuelo</h1>	
	  <form class="form-signin" action="VueloServlet?accion=editarVuelo" method="post">
         <label for="idVuelo">ID del Vuelo:</label>
         <input type="text"  name="idVuelo" value="<%= vuelo.getIdvuelo() %>" readonly>
         <br></br>
  		 <label for="inputFechaHoraSalida" class="sr-only">Fecha y hora de Salida: </label>
    	 <input id="inputFechaHoraSalida" name="fechaHoraSalida" class="form-control" required="" autofocus="" type="datetime-local" value="<%=vuelo.getFechaHoraSalida() %>" >
		 <label for="inputFechaHoraLlegada" class="sr-only">Fecha y hora de Llegada: </label>
    	 <input id="inputFechaHoraLlegada" name="fechaHoraLlegada" class="form-control" required="" autofocus="" type="datetime-local" value="<%= vuelo.getFechaHoraLlegada() %>" >
    	 <label for="InputDesde" class="sr-only">Origen: </label>
    	 <input  class="form-control" autofocus="" type="text" value="<%= vuelo.getAeropuertoOrigen().getNombre()%> , <%= vuelo.getAeropuertoOrigen().getCiudad().getNombre()%> , <%= vuelo.getAeropuertoOrigen().getCiudad().getPais().getNombre()%> " readonly>
 		 <label for="InputDesde" class="sr-only">Destino: </label>
    	 <input  class="form-control" autofocus="" type="text" value="<%= vuelo.getAeropuertoDestino().getNombre()%> , <%= vuelo.getAeropuertoDestino().getCiudad().getNombre()%> , <%= vuelo.getAeropuertoDestino().getCiudad().getPais().getNombre()%> " readonly>	
 		 <label for="inputIdAvion" class="sr-only">Id Avion: </label>
    	 <input id="inputIdAvion" name="idAvion" class="form-control" required="" autofocus="" type="text" value="<%= vuelo.getAvion().getIdAvion() %>" >
    	 <label for="inputPrecioGeneral" class="sr-only">Precio General: </label>
    	 <input id="inputPrecioGeneral" name="precioGeneral" class="form-control" required="" autofocus="" type="text" value="<%= vuelo.getPrecioGeneral()%>" >
    	 <label for="inputPrecioPrimeraClase" class="sr-only">Precio Primera Clase: </label>
    	 <input id="inputPrecioPrimeraClase" name="precioPrimeraClase" class="form-control" required="" autofocus="" type="text" value="<%= vuelo.getPrecioPrimeraClase() %>" >
 		 <button class="btn btn-lg btn-primary btn-block" type="submit">Actualizar</button>
 	 </form>
 	 <td><a class="bg-danger text-white" href="VueloServlet"><button type="button" class="btn btn-danger">Cancelar</button></a></td>
</body>
</html>