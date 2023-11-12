<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Vuelo"%>
<!DOCTYPE html>
<html data-theme="dark">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="Styles/Agregar.css">
	<%
	    Vuelo vuelo = (Vuelo)request.getAttribute("Vuelo");
    %>
<title>Editar Vuelo</title>
</head>
<body>
	<h1>Editar Vuelo</h1>	
	  <form action="VueloServlet?accion=editarVuelo" method="post">
	 	 <input type="hidden" name="idVuelo" value="<%=vuelo.getIdvuelo()%>">
		 <h1>Id Vuelo: <%=vuelo.getIdvuelo()%> </h1>

    	  <h5>Origen: <%= vuelo.getAeropuertoOrigen().getNombre()%> , <%= vuelo.getAeropuertoOrigen().getCiudad().getNombre()%> , <%= vuelo.getAeropuertoOrigen().getCiudad().getPais().getNombre()%>  </h5>

    	 <h5>Destino:<%= vuelo.getAeropuertoDestino().getNombre()%> , <%= vuelo.getAeropuertoDestino().getCiudad().getNombre()%> , <%= vuelo.getAeropuertoDestino().getCiudad().getPais().getNombre()%> </h5>
		 <div class="grid">
	  		 <label for="fechaHoraSalida" >Fecha y hora de Salida: 
	    		 <input id="fechaHoraSalida" name="fechaHoraSalida" required type="datetime-local" value="<%=vuelo.getFechaHoraSalida() %>" >
			 </label>
			 <label for="fechaHoraLlegada" >Fecha y hora de Llegada:
	    	 <input id="fechaHoraLlegada" name="fechaHoraLlegada" required type="datetime-local" value="<%= vuelo.getFechaHoraLlegada() %>" >
	    	</label>
    	</div>
 		 <label for="idAvion">Id Avion: 
    	 	<input id="idAvion" name="idAvion" required type="text" value="<%= vuelo.getAvion().getIdAvion() %>" >
    	 </label>
    	 <div class="grid">
	    	 <label for="precioGeneral">Precio General: 
	    	 	<input id="precioGeneral" name="precioGeneral" required type="text" value="<%= vuelo.getPrecioGeneral()%>" >
	    	 </label>
	    	 <label for="inputPrecioPrimeraClase">Precio Primera Clase: 
	    	  	<input id="inputPrecioPrimeraClase" name="precioPrimeraClase" required type="text" value="<%= vuelo.getPrecioPrimeraClase() %>" >
	 		 </label>
 		 </div>
 		 <div class="grid">
        	<button type="submit">Agregar</button>
        	<a href="VueloServlet"><button type="button" >Cancelar</button></a>
    	</div>
 	 </form>
</body>
</html>