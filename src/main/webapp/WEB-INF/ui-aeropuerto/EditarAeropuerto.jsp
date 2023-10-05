<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Aeropuerto"%>
<!DOCTYPE html>
<html>
<head>
	<%
	Aeropuerto aero = (Aeropuerto)request.getAttribute("Aeropuerto");
    %>

<meta charset="UTF-8">
<title>Editar Aeropuerto</title>
</head>
<body>
	<h1>Editar Aeropuerto</h1>	

	  <form class="form-signin" action="AeropuertoServlet?accion=editarAeropuerto" method="post">
         <label for="idAeropuerto">ID del Aeropuerto:</label>
         <input type="text"  name="idAeropuerto" value="<%= aero.getIdAeropuerto() %>" readonly>
         <br></br>
  		 <label for="inputName" class="sr-only">Nombre del Aeropuerto: </label>
    	 <input id="inputName" name="nombre"  placeholder="Nombre" required="" autofocus="" type="text" value="<%= aero.getNombre() %>" >
 		 <label for="inputDescripcion" class="sr-only">Descripcion del Aeropuerto: </label>
    	 <input id="inputDescripcion" name="descAeropuerto"  placeholder="Descripcion" required="" autofocus="" type="text" value="<%= aero.getDescAeropuerto() %>" >
    	 <label for="InputUbicacion" class="sr-only">Ubicación: </label>
    	 <input autofocus="" type="text" value="<%= aero.getCiudad().getNombre()%> , <%= aero.getCiudad().getPais().getNombre()%> " readonly>
 		 <button type="submit">Actualizar</button>
 	 </form>
 	 </div>
 	 <td><a href="AeropuertoServlet"><button type="button" class="btn btn-danger">Cancelar</button></a></td>
</body>
</html>