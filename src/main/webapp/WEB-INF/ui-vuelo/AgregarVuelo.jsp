<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="entities.Aeropuerto" %>
 <%@ page import="java.util.LinkedList" %>
<!DOCTYPE html>
<html data-theme="dark">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta charset="UTF-8">
	<link rel="stylesheet" href="Styles/Agregar.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	 <% 
	 	String message = (String)request.getAttribute("message");
    	LinkedList<Aeropuerto> listaAeropuertos= (LinkedList<Aeropuerto>)request.getAttribute("listaAeropuertos");
	%>
<title>Agregar Vuelo</title>
</head>
<body>
	<div class="mensaje">
	    <% if (message != null && !message.isEmpty()) { %>
	        <script>
	            window.onload = function() {
	                Swal.fire({
	                	icon: '<%= message.startsWith("error")? "error" : "success"  %>',
	                    title: 'Message',
	                    text: '<%= message %>',
	                });
	            };
	        </script>
	    <% } %>
	</div>
  <form action="VueloServlet?accion=insertar" method="post">
  		 <label for="idvuelo">
  		 	ID vuelo
    		<input id="idvuelo" name="idvuelo" placeholder="Id vuelo" required autofocus type="text">
    	 </label>
 		 <label for="inputFechaHoraSalida">
 		 	Fecha y hora de salida
    		 <input id="inputFechaHoraSalida" name="fechaHoraSalida"  required type="datetime-local"> 
		</label>
		 <label for="fechaHoraLlegada">
		 	Fecha y hora de llegada
    		<input id="fechaHoraLlegada" name="fechaHoraLlegada" required type="datetime-local"> 
    	 </label>	  
    	 <div class="grid">
    	 	 <label for="nombreAeropuertoOrigen">
	  		 	Aeropuerto de origen
	 			<select id="nombreAeropuertoOrigen" name="nombreAeropuertoOrigen" required>
	   				<option value="" selected>Elegir origen..</option>
	   				   <%if(listaAeropuertos == null || listaAeropuertos.isEmpty()){%>
								<option value="">No hay aeropuertos disponibles</option>
						<%}else{ %>
				   				<%for(Aeropuerto origen: listaAeropuertos){ %>
	   	   							 <option value="<%=origen.getNombre()%>"><%=origen.getNombre()%></option>
	   	   					   <%}%>
	   	   			  <%}%>
	   			</select>
			</label>
			<label for="nombreAeropuertoDestino">
	  		 	Aeropuerto de destino
	 			<select id="nombreAeropuertoDestino" name="nombreAeropuertoDestino" required>
	   				<option value="" selected>Elegir destino..</option>
	   		        <%if(listaAeropuertos == null || listaAeropuertos.isEmpty()){%>
						<option value="">No hay aeropuertos disponibles</option>
					<%}else{ %>
		   				<%for(Aeropuerto destino: listaAeropuertos){ %>
		   	   				 <option value="<%=destino.getNombre()%>"><%=destino.getNombre()%></option>
		   	   			 <%}%>
		   	   		 <%}%>
	   			</select>
			</label>
 		 </div>
 	     <label for="inputIdAvion">Id del avion</label>
    	 <input id="inputIdAvion" name="idAvion" placeholder="id del avion" required type="text"> 	
    
		 <div class="grid">
	    	 <label for="precioGeneral" >
	    	 	Precio General:
	    		<input id="precioGeneral" name="precioGeneral" required type="text"  >
	    	 </label>
	    	 <label for="precioPrimeraClase">
	    	 	Precio Primera Clase: 
	    	 <input id="precioPrimeraClase" name="precioPrimeraClase" required type="text"  >
	    	 </label>
    	 </div>
 		 <div class="grid">
        	<button type="submit">Agregar</button>
        	<a href="VueloServlet"><button type="button" >Cancelar</button></a>
    	</div>
  </form>
</body>
</html>