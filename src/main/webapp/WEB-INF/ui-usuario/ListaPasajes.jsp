<%@page import="entities.Usuario"%>
<%@page import="entities.Pasaje"%>
<%@page import="java.util.LinkedList"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		LinkedList<Pasaje> listaPasajes  = (LinkedList<Pasaje>)request.getAttribute("listaPasajes");
		Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
		String message = (String)request.getAttribute("message");
    %>

</head>
<body>

 <nav class="navbar">
  <div > </div>
    <ul class="nav-links">
    <% if (usu.getTipo().equals("admin")) { %>
      <li><a href="UsuarioServlet" class="active">Usuarios</a></li>
      <li><a href="PaisServlet"  >Paises</a></li>
      <li><a href="CiudadServlet" >Ciudades</a></li>
      <li><a href="AvionServlet" >Aviones</a></li>
      <li><a href="AeropuertoServlet"  >Aeropuertos</a></li>
       <% } %>
      <li><a href="VueloServlet" >Vuelos</a></li>    
    <li role="list" dir="rtl">
      <a aria-haspopup="listbox">Perfil</a>
      <ul >
              <% if (usu.getTipo().equals("user")) { %>
     	 <li><a href="UsuarioServlet?accion=misPasajes&idUsuario=<%=usu.getIdUsuario()%>">Mis pasajes</a></li>
      <% } %>
   <li><a href="UsuarioServlet?accion=redirecEditar&idUsuario=<%=usu.getIdUsuario()%>" >Cambiar datos</a></li>
 
  <li><a href="UsuarioServlet?accion=cerrarSesion" >Cerrar sesi�n</a></li>
      </ul>
    </li>
    
    </ul>
  </nav>	
  
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

    <% if (listaPasajes.isEmpty()) { %>
        <div class="mensajeNoPasaje">
            <p class="mensajeNo">No tienes pasajes comprados</p>
        </div>
    <% }else{ %>
		 <%for(Pasaje pas: listaPasajes){ %>
	   	 <article class="card">
	        <header>
	    		<hgroup>
  					<h1>Detalles del pasaje</h1>
 			  		<h3>Estado: <%= pas.getEstado()%></h3>
				</hgroup>
	        </header>	
		 	<section class="details">
			<h2>Datos personales:</h2>
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
	           	
	           	<div class="grid" >	
				<h5>Asiento
	           		<p><%= pas.getAsiento().getFila()%> <%= pas.getAsiento().getNumero()%></p>
	           		
	           </div> 
	           	
	           	
	           	
	           	
	         </div>
	         </section>
	         <% if (pas.getEstado().equals("Confirmado")) { %>
		         <footer> 
		         	<a href="PasajeServlet?accion=cancelarPasaje&idPasaje=<%= pas.getIdPasaje()%>"><button type="button" >Cancelar pasaje</button></a>
		         </footer>
	       	 <%} %>
		 	</article>
		 	<br></br>
		 	<%} %>
	 	<%} %>

	
</body>
</html>