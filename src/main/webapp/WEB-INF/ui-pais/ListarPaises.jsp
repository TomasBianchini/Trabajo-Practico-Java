<%@page import="entities.Pais"%>
<%@page import="java.util.LinkedList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" data-theme="dark">
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
 	<link rel="stylesheet" href="Styles/listados.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<%
    	LinkedList<Pais> listaPais = (LinkedList<Pais>)request.getAttribute("listaPaises");

        String message = (String)request.getAttribute("message");
    %>

    
    <title>Lista Paises</title>
</head>
<body>
  <nav class="navbar">
	    <ul class="nav-links">
	      <li><a href="UsuarioServlet" >Usuarios</a></li>
	      <li><a href="PaisServlet" class="active" >Paises</a></li>
	      <li><a href="CiudadServlet" >Ciudades</a></li>
	      <li><a href="AvionServlet" >Aviones</a></li>
	      <li><a href="AeropuertoServlet" >Aeropuertos</a></li>
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
	    <a href="PaisServlet?accion=redirecAgregarPais"><button>Agregar pais</button></a>
	</div> 
	<table role="grid">
	  <thead>
	    <tr>
	        <th scope="col">Nombre</th>
			<th scope="col"></th>
			<th scope="col"></th>
	    </tr>
	  </thead>
	  <tbody>
	  <%if(listaPais == null || listaPais.isEmpty()){%>
		  <tr>
		   <td>No hay paises disponibles</td></tr>
 	  <%}else{ %>
		   <%for(Pais pa: listaPais){ %>
		      <tr>
		        <td><%=pa.getNombre() %></td>
		        <td><a href="PaisServlet?accion=redirecEditar&idPais=<%=pa.getIdPais()%>" role="button" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> <pah fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/></svg></a></td>
		        <td><a href="PaisServlet?accion=eliminar&idPais=<%=pa.getIdPais()%>" role="button" style="background: red;border:green"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16"><path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/></svg></a></td>
		      </tr>	 
		   <%} %> 
	   <%} %> 
	  </tbody>
	</table>
</div> 
</body>
</html>