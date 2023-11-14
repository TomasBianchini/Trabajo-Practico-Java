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
	<%
    	LinkedList<Pais> listaPaises = (LinkedList<Pais>)request.getAttribute("listaPaises");
	%>

<title>Agregar Ciudad</title>
</head>
<body>
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
	   		<%for(Pais pais: listaPaises){ %>
	   	    <option value="<%=pais.getNombre()%>"><%=pais.getNombre()%></option>
	   	    <%}%>
	   	 </select>
	</label>
	<div class="grid">
         <button type="submit">Agregar</button>
         <a href="CiudadServlet"><button type="button" >Cancelar</button></a>
    </div>
  </form>
  
<script>
message = request.getAttribute("message");
if(message != null){
 resultado = window.confirm(message);

}

</script>
</body>
</html>