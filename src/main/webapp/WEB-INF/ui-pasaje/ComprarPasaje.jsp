<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Vuelo"%>
<%@page import="entities.Usuario"%>
<%@page import="java.util.HashMap"%>
<%@page import="entities.Asiento"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html data-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="Styles/Agregar.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <%
    	Vuelo vue = (Vuelo)request.getAttribute("Vuelo");
    	Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
		HashMap<String,Asiento> asientosDisponibles = (HashMap<String,Asiento>)request.getAttribute("asientosDisponibles");   
    %> 
    	 <%	String message = (String)request.getAttribute("message");	%>
    <title>Detalles del Pasaje</title>
</head>
<body>  
	<div class="mensaje">
	    <% if (message != null && !message.isEmpty()) { %>
	        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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

	<form action="PasajeServlet" method="post">
		<input type="hidden" id="idUsuario" name="idUsuario" value="<%= usu.getIdUsuario() %>">
		<input type="hidden"  name="idvuelo" value="<%=vue.getIdvuelo()%>">
		<input type="hidden"  name="idAvion" value="<%= vue.getAvion().getIdAvion() %>">
		<h1>Detalles del pasaje:</h1>
		<h5>Datos personales:</h5>
		<div class="grid" >		
			<h5>Pasajero
                  <p><%= usu.getApellido()%>,  <%= usu.getNombre()%> </p></h5>
           <h5>Fecha de nacimiento
                  <p><%= usu.getFechaNacimiento()%></p></h5>      
         </div>                        
         <div class="grid" >
            <h5>Tipo documento
            	<p><%= usu.getTipoDocumento()%></p>
            </h5>
            <h5>Nro documento
           		<p><%= usu.getNroDocumento()%></p>
           	</h5>
         </div>
		 <h5>Detalles del vuelo:</h5>
		 <div class="grid" >	
			<h5>Origen
                  <p><%= vue.getAeropuertoOrigen().getCiudad().getNombre()%>, <%=vue.getAeropuertoOrigen().getCiudad().getPais().getNombre()%> </p></h5>
           <h5>Destino
                  <p><%= vue.getAeropuertoDestino().getCiudad().getNombre()%>, <%=vue.getAeropuertoDestino().getCiudad().getPais().getNombre()%></p></h5>    
         </div>                          
         <div class="grid" >
            <h5>Fecha y hora de salida
            	<p><time datetime="<%=vue.getFechaHoraSalida()%>">
 				  <%=vue.getFechaHoraSalida().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) %>
			</time> </p>
            </h5>
            <h5>Fecha y Hora de llegada
           		<p><time datetime="<%=vue.getFechaHoraSalida()%>">
 				  <%=vue.getFechaHoraLlegada().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) %>
			</time> </p>
           	</h5>
         </div>
         <div class="grid" >
            <h5>Precio Economico
            	<p><%= vue.getPrecioGeneral() %></p>
            </h5>
            <h5>Precio Ejecutivo
           		<p><%= vue.getPrecioPrimeraClase() %></p>
           	</h5>
         </div>
 		 <label for="asiento">
	  		 	Asientos Disponibles
		 	<select id="asiento" name="asiento" >
							<%if (asientosDisponibles.isEmpty()){%>
								<option> No hay asientos disponibles </option>
							<%}else{%>						
								<%for (HashMap.Entry<String, Asiento> asi: asientosDisponibles.entrySet()) {%>
		                  	      	<option value="<%= asi.getValue().getFila() + ' ' + asi.getValue().getNumero() + ' ' + asi.getValue().getTipo() %>"><%=asi.getValue().getFila()%> <%=asi.getValue().getNumero()%> <%=asi.getValue().getTipo()%> </option>
		                  		<%} %>
		                  	<%} %>
           </select>
		</label>
 		<div class="grid">
 			<%if (!asientosDisponibles.isEmpty()){%>
        		<button type="submit">Comprar</button>
        	<%} %>
        	<a href="VueloServlet"><button type="button" >Cancelar</button></a>
    	</div>
 	 </form>

</body>
</html>

