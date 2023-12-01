<%@page import="entities.Avion"%>
<%@page import="java.util.LinkedList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" data-theme="dark">
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Lista Aviones</title>
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
 	<link rel="stylesheet" href="Styles/listados.css">
 	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<% LinkedList<Avion> listaAviones = (LinkedList<Avion>)request.getAttribute("listaAviones"); 
	 String message = (String)request.getAttribute("message");
	
	%>
</head>
<body>
	<nav class="navbar">
	    <ul class="nav-links">
	      <li > <a href="UsuarioServlet" >Usuarios</a></li>
	      <li><a href="PaisServlet"  >Paises</a></li>
	      <li><a href="CiudadServlet" >Ciudades</a></li>
	      <li><a href="AvionServlet" class="active">Aviones</a></li>
	      <li> <a href="AeropuertoServlet" >Aeropuertos</a></li>
	      <li><a href="VueloServlet"  >Vuelos</a></li>
	      <li><a href="UsuarioServlet?accion=cerrarSesion">Cerrar sesión</a></li>
	    </ul>
	</nav>
	
	
<div class="mensaje">
    <% if (message != null && !message.isEmpty()) { %>
        <script>
            // Display the message using SweetAlert after the page is fully loaded
            window.onload = function() {
                Swal.fire({
                    icon: '<%= message.startsWith("error")? "success" : "error"  %>',
                    title: 'Message',
                    text: 'NO SE REALIZO LA ACCION, <%= message %>',
                });
            };
        </script>
    <% } %>
</div>
	
	
	<div class="conteiner-table">
		<div class="boton">
		    <a href="AvionServlet?accion=redirecAgregarAvion"><button>Agregar Avion</button></a>
		</div> 
		<table role="grid">
		  <thead>
		    <tr>
		        <th scope="col">ID Avion</th>
		        <th scope="col">Cantidad de Asientos</th>
		        <th scope="col">Marca</th>
		        <th scope="col">Modelo</th>
		    	<th scope="col">Año</th>
		        <th scope="col"></th>
		        <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody>
		  	   <%if(listaAviones == null || listaAviones.isEmpty()){%>
					  <tr>
					   <td>No hay aviones disponibles</td></tr>
			  <%}else{ %>
			      <%for(Avion avi: listaAviones){ %>
				      <tr>	
				        <td><%=avi.getIdAvion() %> </td>
				        <td><%=avi.getAsientos().size() %></td>
				        <td><%=avi.getMarca() %></td>
				        <td><%=avi.getModelo() %></td>
				        <td><%=avi.getAnio() %></td>
				        <td><a href="AvionServlet?accion=listarAsientos&idAvion=<%=avi.getIdAvion()%>" role="button" >Asientos</a></td>
				        <td><a href="AvionServlet?accion=eliminar&idAvion=<%=avi.getIdAvion()%>" role="button" style="background: red;border:green"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16"><path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/></svg></a></td>		
				      </tr>		      		      
			      <%} %>
		      <%} %>		     
		  </tbody>
		  </table>
	</div> 
</body>
</html>