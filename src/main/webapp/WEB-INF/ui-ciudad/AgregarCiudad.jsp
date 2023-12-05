<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="entities.Pais" %>
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
		LinkedList<Pais> listaPaises = (LinkedList<Pais>)request.getAttribute("listaPaises");
	 	String message = (String)request.getAttribute("message");
    	
	%>

<title>Agregar Ciudad</title>
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
  <form action="CiudadServlet?accion=insertar" method="post" >
  	<div class="grid">
		<label for="codPostal">
			 Codigo Postal
		 	 <input id="codPostal" name="codPostal" placeholder="Codigo postal" required type="text">
		</label>
	 	<label for="nombre" >
	 		Nombre
	 	 	<input id="nombre" name="nombre" placeholder="Nombre" required type="text"> 
	 	</label>
	 </div>
	 <label for="pais">
	   	Pais
	 	<select id="pais" name="pais" required>
	   		<option value="" selected>Elegir pais..</option>
	   		<%if(listaPaises == null || listaPaises.isEmpty()){%>
				  <tr>
				   <td>No hay ciudades disponibles</td></tr>
			 <%}else{ %>
		   		<%for(Pais pais: listaPaises){ %>
		   	    <option value="<%=pais.getNombre()%>"><%=pais.getNombre()%></option>
		   	    <%}%>
	   	    <%}%>
	   	 </select>
	</label>
	<div class="grid">
         <button type="submit">Agregar</button>
         <a href="CiudadServlet"><button type="button" >Cancelar</button></a>
    </div>
  </form>

</body>
</html>