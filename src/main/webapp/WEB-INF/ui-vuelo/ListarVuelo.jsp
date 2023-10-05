<%@page import="entities.Vuelo"%>
<%@page import="entities.Pasajero"%>
<%@page import="java.util.LinkedList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">

<title>Lista Vuelos</title>
  <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    <link href="styles/bootstrap.min.css" rel="stylesheet">
	<%

    	LinkedList<Vuelo> listaVuelos = (LinkedList<Vuelo>)request.getAttribute("listaVuelos");
    %>
    <%
		Pasajero p = (Pasajero)request.getSession().getAttribute("pasajero");
   		request.getSession().setAttribute("pasajero", p);
	%>
</head>
<body style="background-color:rgb(251, 252, 255);">

<div class="fixed-top">

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <!-- Brand/logo -->
	  <a class="navbar-brand mb-0 h1" href="MenuPrincipal.jsp">Menu Principal</a>
	</nav>
	<br>

<div class="container">
	<div class="mt-4 p-5 bg-info text-white rounded">
	  <h1>Lista de Vuelos</h1>
	</div>
	<p></p>
	<form>
	  <div class="row">
	    <div class="col">
	      <input type="text" id="origenInput" class="form-control" placeholder="Origen">
	    </div>
	    <div class="col">
	      <input type="text" id="destinoInput" class="form-control" placeholder="Destino">
	    </div>
	    <div class="col">
	      <a class="bg-primary text-white" href="#" onclick="filtarVuelos()"><button type="button" class="btn btn-primary">Filtar</button></a>
	    </div>
	  </div>
	</form>
	<p></p>

   <!-- Lista Ciudad -->
  <table class="table table-fixed table-condensed">
    <thead class="table-dark">
      <tr>
        <th>ID Vuelo</th>
        <th>Sale</th>
        <th>LLega</th>
        <th>Origen</th>
        <th>Destino</th>
        <th></th>
        <th></th>
        <th></th>
      </tr>
    </thead>
    <tbody>
    <%for(Vuelo  vue: listaVuelos){ %>
      <tr>
        <td><%=vue.getIdvuelo() %> </td>
        <td><%=vue.getFechaHoraSalida() %></td>
        <td><%=vue.getFechaHoraLlegada() %></td>
        <td><%=vue.getAeropuertoOrigen().getNombre()%>, <%=vue.getAeropuertoOrigen().getCiudad().getNombre()%>, <%=vue.getAeropuertoOrigen().getCiudad().getPais().getNombre()%></td>
        <td><%=vue.getAeropuertoDestino().getNombre()%>, <%=vue.getAeropuertoDestino().getCiudad().getNombre()%>, <%=vue.getAeropuertoDestino().getCiudad().getPais().getNombre()%></td>
        <td><a class="bg-primary text-white" href="PasajeServlet?accion=compraPasaje&idvuelo=<%=vue.getIdvuelo()%>"><button type="button" class="btn btn-primary">Comprar</button></a></td>
        <td><a class="bg-primary text-white" href="VueloServlet?accion=editar&idvuelo=<%=vue.getIdvuelo()%>"><button type="button" class="btn btn-primary"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> <pah fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/></svg></button></a></td>
        <td><a class="bg-danger text-white" href="VueloServlet?accion=eliminar&idvuelo=<%=vue.getIdvuelo()%>"><button type="button" class="btn btn-danger"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16"><path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/></svg></button></a></td>
      </tr>
      <%} %>
     
    </tbody>
  </table>
  
   <a class="bg-danger text-white" href="VueloServlet?accion=AgregarVuelo"><button type="button" class="btn btn-primary">Agregar vuelo</button></a>

</div>
</div>


<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>



<script>
function filtarVuelos() {
    // Obtiene el elemento select
    var oInput = document.getElementById("origenInput");
    var dInput = document.getElementById("destinoInput");
    
    // Obtiene el valor seleccionado
    var origen = oInput.value;
    var destino = dInput.value;

    // Verifica si se ha seleccionado un valor
    if (origen && destino) {

        // Construye la URL con los valores seleccionados
        var url = 'VueloServlet?accion=filtrar&origen=' +origen + '&destino=' + destino ;

        // Redirige a la URL construida
        window.location.href = url;
    } else {
        // Si no se ha seleccionado un valor, muestra un mensaje de error o toma la acción adecuada.
        alert("Por favor, ingrese origen y destino ");
    }
}
</script>














</body>
</html>