<%@page import="entities.Avion"%>
<%@page import="java.util.LinkedList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" data-theme="dark">
<head>
	<meta charset="ISO-8859-1">
	
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
<title>Lista Aviones</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
 	<link rel="stylesheet" href="Styles/listados.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

	<% LinkedList<Avion> listaAviones = (LinkedList<Avion>)request.getAttribute("listaAviones");  %>
</head>
<body>


<nav class="navbar">
  <div > </div>
    <ul class="nav-links">
      <li > <a href="UsuarioServlet" >Usuarios</a></li>
      <li><a href="PaisServlet"  >Paises</a></li>
      <li><a href="CiudadServlet" >Ciudades</a></li>
      <li><a href="AvionServlet" class="active">Aviones</a></li>
      <li> <a href="AeropuertoServlet" >Aeropuertos</a></li>
      <li><a href="VueloServlet"  >Vuelos</a></li>
    </ul>
</nav>
<div style="max-width: 1500px; margin: 0 auto; padding: 20px; border-radius: 8px;  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">

 <div class="boton">
    <a href="AvionServlet?accion=AgregarAvion"><button>Agregar Avion</button></a>
</div> 


<table role="grid">
  <thead>
    <tr>
 
        <th scope="col">ID Avion</th>
        <th scope="col">Cantidad de Asientos</th>
        <th scope="col">Marca</th>
        <th scope="col">Modelo</th>
    	<th scope="col">A�o</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
       <%for(Avion avi: listaAviones){ %>
      <tr>

        <td><%=avi.getIdAvion() %> </td>
        <td><%=avi.getAsientos().size() %></td>
        <td><%=avi.getMarca() %></td>
        <td><%=avi.getModelo() %></td>
        <td><%=avi.getAnio() %></td>
        <td><a href="AvionServlet?accion=listarAsientos&idAvion=<%=avi.getIdAvion()%>" role="button" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> <pah fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/></svg></a></td>
        <td><a href="AvionServlet?accion=eliminar&idAvion=<%=avi.getIdAvion()%>" role="button" style="background: red;border:green"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16"><path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/></svg></a></td>

      </tr>
      
      
      <%} %>
     
  </tbody>
  </table>

</div> 

</body>
</html>