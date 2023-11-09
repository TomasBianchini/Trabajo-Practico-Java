<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="entities.Usuario" %>
 <%@ page import="entities.Vuelo" %>
  <%@ page import="java.util.LinkedList" %>

<!DOCTYPE html>
<html lang="en" data-theme="dark">
<head>
  <title>Menu Principal</title>
 	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<%
    	LinkedList<Vuelo> listaVuelos = (LinkedList<Vuelo>)request.getAttribute("listaVuelos");
 	   Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
	%>


</head>
<body>
<article>
  <nav class="container-fluid">
    <ul >
      <li > <a href="UsuarioServlet" class="contrast" role="button">Usuarios</a></li>
      <li><a href="PaisServlet" class="contrast" role="button">Paises</a></li>
      <li><a href="CiudadServlet" class="contrast" role="button">Ciudades</a></li>
      <li><a href="AvionServlet" class="contrast" role="button">Aviones</a></li>
      <li> <a href="AeropuertoServlet" class="contrast" role="button">Aeropuertos</a></li>
      <li><a href="VueloServlet" class="contrast" role="button">Vuelos</a></li>
    </ul>
</nav>
</article>
  <table>
  <thead>
    <tr>
        <th scope="col">Origen</th>
        <th scope="col">Destino</th>
        <th scope="col">Sale</th>
        <th scope="col">LLega</th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
    <%for(Vuelo  vue: listaVuelos){ %>
      <tr>
        <td><%=vue.getAeropuertoOrigen().getNombre()%>, <%=vue.getAeropuertoOrigen().getCiudad().getNombre()%>, <%=vue.getAeropuertoOrigen().getCiudad().getPais().getNombre()%></td>
        <td><%=vue.getAeropuertoDestino().getNombre()%>, <%=vue.getAeropuertoDestino().getCiudad().getNombre()%>, <%=vue.getAeropuertoDestino().getCiudad().getPais().getNombre()%></td>
        <td><%=vue.getFechaHoraSalida() %></td>
        <td><%=vue.getFechaHoraLlegada() %></td>
    	<td><a  href="PasajeServlet?accion=compraPasaje&idvuelo=<%=vue.getIdvuelo()%>" role="button">Comprar</a></td>
        <td><a href="VueloServlet?accion=editar&idvuelo=<%=vue.getIdvuelo()%>" role="button"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> <pah fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/></svg></a></td>
        <td><a href="VueloServlet?accion=eliminar&idvuelo=<%=vue.getIdvuelo()%>" role="button"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16"><path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/></svg></a></td>
      
      </tr>
      
      
      <%} %>
     
  </tbody>
  </table>
 <a href="VueloServlet?accion=AgregarVuelo"><button >Agregar vuelo</button></a>
</body>
</html>
