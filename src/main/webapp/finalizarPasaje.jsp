<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="entities.Pasaje" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="en" data-theme="dark">
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
 	<link rel="stylesheet" href="Styles/pasajes.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<title>Lista Usuario</title>
	<%
    	Pasaje pas = (Pasaje)request.getAttribute("Pasaje");
   		String message = (String)request.getAttribute("message"); 
   	%>

</head>
<body>
			<% if(message != null && !message.isEmpty()) {%>
				<span><%= message%> </span>
			<% }%>
			<% if(pas == null) {%>
				<h1>El pasaje no existe</h1>
			<%} else {%>
			 <article class="card">
	        <header>
	    		<hgroup>
  					<h2>Detalles del pasaje</h2>
 			  		<h3>Estado: <%= pas.getEstado()%></h3>
				</hgroup>
	        </header>	
		 	<section class="details">
			<h2>Datos personales:</h2>
			<div class="grid" >		
				<h5>Pasajero
	                  <p><%= pas.getUsuario().getApellido()%>,  <%= pas.getUsuario().getNombre()%> </p></h5>   
	         </div>                        
	         <div class="grid" >
	            <h5>Tipo documento
	            	<p><%= pas.getUsuario().getTipoDocumento()%></p>
	            </h5>
	            <h5>Nro documento
	           		<p><%= pas.getUsuario().getNroDocumento()%></p>
	           	</h5>
	         </div>
			 <h2>Detalles del vuelo:</h2>
			 <div class="grid" >	
				<h5>Origen
	                  <p><%= pas.getVuelo().getAeropuertoOrigen().getCiudad().getNombre()%>, <%=pas.getVuelo().getAeropuertoOrigen().getCiudad().getPais().getNombre()%> </p></h5>
	           <h5>Destino
	                  <p><%= pas.getVuelo().getAeropuertoDestino().getCiudad().getNombre()%>, <%=pas.getVuelo().getAeropuertoDestino().getCiudad().getPais().getNombre()%></p></h5>    
	         </div>                          
	         <div class="grid" >
	            <h5>Fecha y hora de salida
	            	<p><time datetime="<%=pas.getVuelo().getFechaHoraSalida()%>">
	 				  <%=pas.getVuelo().getFechaHoraSalida().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) %>
				</time> </p>
	            </h5>
	            <h5>Fecha y Hora de llegada
	           		<p><time datetime="<%=pas.getVuelo().getFechaHoraSalida()%>">
	 				  <%=pas.getVuelo().getFechaHoraLlegada().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) %>
				</time> </p>
	           	</h5>
	         </div>
	         </section>
	         <% if (pas.getEstado().equals("Confirmado")) { %>
		         <footer> 
		         	<a href="FinalizarPasaje?accion=finalizarPasaje&id=<%=pas.getIdPasaje()%>"><button type="button" >Finalizar pasaje</button></a>
		         </footer>
	       	 <%} %>
		 	</article>
		 	<%} %>
</body>
</html>