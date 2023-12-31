<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="entities.Usuario" %>
 <%@ page import="entities.Vuelo" %>
  <%@ page import="java.util.LinkedList" %>
   <%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html lang="en" data-theme="dark">
<head>
  <title>Menu Principal</title>
 	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
 	<link rel="stylesheet" href="Styles/listados.css">
 	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

	<%
    	LinkedList<Vuelo> listaVuelos = (LinkedList<Vuelo>)request.getAttribute("listaVuelos");
 	   Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
 		String message = (String)request.getAttribute("message");
	%>
	<%  if(usu == null){
			request.getRequestDispatcher("index.html").forward(request, response);
	}%>

</head>
<body>

  <nav class="navbar">
  <div > </div>
    <ul class="nav-links">
    <% if (usu.getTipo().equals("admin")) { %>
      <li><a href="UsuarioServlet" >Usuarios</a></li>
      <li><a href="PaisServlet"  >Paises</a></li>
      <li><a href="CiudadServlet" >Ciudades</a></li>
      <li><a href="AvionServlet" >Aviones</a></li>
      <li><a href="AeropuertoServlet"  >Aeropuertos</a></li>
       <% } %>
      <li><a href="VueloServlet"  class="active">Vuelos</a></li>

    
    
    <li role="list" dir="rtl">
      <a aria-haspopup="listbox">Perfil</a>
      <ul >
        
        
      <% if (usu.getTipo().equals("user")) { %>
     	 <li><a href="UsuarioServlet?accion=misPasajes&idUsuario=<%=usu.getIdUsuario()%>">Mis pasajes</a></li>
      <% } %>
   <li><a href="UsuarioServlet?accion=redirecEditar&idUsuario=<%=usu.getIdUsuario()%>" >Cambiar datos</a></li>
 
  <li><a href="UsuarioServlet?accion=cerrarSesion" >Cerrar sesión</a></li>
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

  
<div class="conteiner-table">
	<div class="filter-container">
	  <form>
	    <label for="origenInput">
	    	Origen
	   		 <input type="text" id="origenInput" placeholder="Origen" required>
	    </label>
	    <label for="destinoInput">
	    	Destino
	   <input type="text" id="destinoInput" placeholder="Destino" required>
	    </label>
	 	<button type="button" onclick="filtrarVuelos()" id="filtrarButton">Filtrar</button>
	  </form>
	  
	   <% if (usu.getTipo().equals("admin")) { %>
	  <a href="VueloServlet?accion=redirecAgregarVuelo"><button>Agregar vuelo</button></a>
	   <% } %>
	   
	</div>
  <table role="grid">
  <thead>
    <tr>
        <th scope="col">Origen</th>
        <th scope="col">Destino</th>
        <th scope="col">Sale</th>
        <th scope="col">LLega</th>
        <th scope="col"></th>
        <% if (usu.getTipo().equals("admin")) { %>
        <th scope="col"></th>
        
         <% } %>
    </tr>
  </thead>
  <tbody>
   <%if(listaVuelos == null || listaVuelos.isEmpty()){%>
	  <tr>
	   <td>No hay vuelos disponibles</td></tr>
   <%}else{ %>
    <%for(Vuelo  vue: listaVuelos){ %>
      <tr>
        <td><%=vue.getAeropuertoOrigen().getNombre()%>, <%=vue.getAeropuertoOrigen().getCiudad().getNombre()%>, <%=vue.getAeropuertoOrigen().getCiudad().getPais().getNombre()%></td>
        <td><%=vue.getAeropuertoDestino().getNombre()%>, <%=vue.getAeropuertoDestino().getCiudad().getNombre()%>, <%=vue.getAeropuertoDestino().getCiudad().getPais().getNombre()%></td>
        <td> <time datetime="<%=vue.getFechaHoraSalida()%>">
 				  <%=vue.getFechaHoraSalida().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) %>
			</time> 
		</td>
		<td> <time datetime="<%=vue.getFechaHoraSalida()%>">
 				  <%=vue.getFechaHoraLlegada().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) %>
			</time> 
		</td>
		<% if (usu.getTipo().equals("user")) { %>
    		<td><a  href="PasajeServlet?accion=redirecCompraPasaje&idvuelo=<%=vue.getIdvuelo()%>" role="button" >Comprar</a></td>
        <% } %>
        <% if (usu.getTipo().equals("admin")) { %>
        <td><a href="VueloServlet?accion=redirecEditar&idvuelo=<%=vue.getIdvuelo()%>" role="button" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> <pah fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/></svg></a></td>
        <td><a href="VueloServlet?accion=eliminar&idvuelo=<%=vue.getIdvuelo()%>" role="button" style="background: red;border:green"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16"><path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/></svg></a></td>
  	   <% } %>
  	   
  	  </tr>   
      <%} %>
     <%} %>
     
  </tbody>
  </table>
  
	 <script>
	function filtrarVuelos() {
	    var oInput = document.getElementById("origenInput");
	    var dInput = document.getElementById("destinoInput");
	    
	    var origen = oInput.value;
	    var destino = dInput.value;
		
	    if (origen && destino) {
            console.log(origen);
            var url = 'VueloServlet?accion=filtrar&origen='+origen +'&destino='+destino ;
            window.location.href = url;
        } else if(origen || destino) {
            alert("Por favor, ingrese origen y destino ");
        } else {
             var url = 'VueloServlet';
             window.location.href = url;
        }
	}
	
	
	</script>

</div>
</body>
</html>
