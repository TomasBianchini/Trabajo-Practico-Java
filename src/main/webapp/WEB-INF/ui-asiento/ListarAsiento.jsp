<%@page import="entities.Asiento"%>
<%@page import="entities.Avion"%>
<%@page import="java.util.HashMap"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html data-theme="dark">
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Lista Asientos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
 	<link rel="stylesheet" href="Styles/listados.css">
 	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<% Avion avi = (Avion)request.getAttribute("avion");
	 String message = (String)request.getAttribute("message");%>
	<% HashMap<String,Asiento> asientos = new HashMap<>();%>
	<% asientos = avi.getAsientos(); %>
</head>
<body>
	<nav class="navbar">
	    <ul class="nav-links">
	      <li><a href="UsuarioServlet" >Usuarios</a></li>
	      <li><a href="PaisServlet"  >Paises</a></li>
	      <li><a href="CiudadServlet" >Ciudades</a></li>
	      <li><a href="AvionServlet" class="active" >Aviones</a></li>
	      <li><a href="AeropuertoServlet" >Aeropuertos</a></li>
	      <li><a href="VueloServlet"  >Vuelos</a></li>
	      <li><a href="UsuarioServlet?accion=cerrarSesion">Cerrar sesión</a></li>
	    </ul>
	</nav>
	
	
<div class="mensaje">
    <% if (message != null && !message.isEmpty()) { %>
        <script>
            window.onload = function() {
                Swal.fire({
                	icon: '<%= message.startsWith("error")? "success" : "error"  %>',
                    title: 'Message',
                    text: '<%= message %>',
                });
            };
        </script>
    <% } %>
</div>
	
	<div class="conteiner-table">
		 <div class="boton">
		    <a href="AvionServlet?accion=redirecAgregarAsiento&idAvion=<%=avi.getIdAvion()%>"><button>Agregar Asiento</button></a>
		    <a href="AvionServlet"><button type="button">Volver Aviones</button></a>
		</div> 
		<table role="grid">
		  <thead>
		    <tr>
			    <th scope="col">Fila</th>
			    <th scope="col">Numero</th>
			    <th scope="col">Tipo</th>
		        <th scope="col"></th>    
		    </tr>
		  </thead>
		  <tbody>
		     <%if(asientos == null || asientos.isEmpty()){%>
				  <tr>
				  	<td>No hay vuelos disponibles</td></tr>
			 <%}else{ %>
			  	<%for (HashMap.Entry<String, Asiento> asi: asientos.entrySet()) {%>
			      <tr>
			        	  <td><%=asi.getValue().getFila()%></td>
			        	  <td><%=asi.getValue().getNumero()%></td>
						  <td><%=asi.getValue().getTipo()%></td>
			        <td><a href="AvionServlet?accion=eliminarAsiento&idAvion=<%=avi.getIdAvion()%>&fila=<%=asi.getValue().getFila()%>&numero=<%=asi.getValue().getNumero()%>" role="button" style="background: red;border:green"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16"><path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/></svg></a></td>	
			      </tr>	
			     <%} %>	     
		     <%} %>	 
		  </tbody>
		</table>
</div> 
</body>
</html>