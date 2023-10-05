<%@page import="entities.Asiento"%>
<%@page import="entities.Avion"%>
<%@page import="java.util.HashMap"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Lista Asientos</title>
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="styles/signin.css" rel="stylesheet">
    <link href="styles/bootstrap.min.css" rel="stylesheet">
	<% Avion avi = (Avion)request.getAttribute("avion");%>
	<% HashMap<String,Asiento> asientos = new HashMap<>();%>
	<% asientos = avi.getAsientos(); %>
</head>
<body style="background-color:rgb(251, 252, 255);">
	<div class="fixed-top">
	
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <!-- Brand/logo -->
		  <a class="navbar-brand mb-0 h1" href="MenuPrincipal.jsp">Menu Principal</a>
		</nav>
		<br>
	<div class="container" >
	<div class="mt-4 p-5 bg-info text-white rounded">
		<h1>Lista de Asientos del Avion <%=avi.getIdAvion()%></h1>
	</div>
	<table class="table table-fixed table-condensed">
	    <thead class="table-dark">
	      	<tr>
	        	<th>Fila</th>
	        	<th>Numero</th>
	        	<th>Tipo</th>
				<th></th>
	      	</tr>
	    </thead>
  <tbody>

    	<%for (HashMap.Entry<String, Asiento> asi: asientos.entrySet()) {%>
      		<tr>			
        	  <td><%=asi.getValue().getFila()%></td>
        	  <td><%=asi.getValue().getNumero()%></td>
			  <td><%=asi.getValue().getTipo()%></td>
        	  <td>
	        	  <a class="bg-danger text-white" href="AvionServlet?accion=eliminarAsiento&idAvion=<%=avi.getIdAvion()%>&fila=<%=asi.getValue().getFila()%>&numero=<%=asi.getValue().getNumero()%>">
	        		<button type="button" class="btn btn-danger"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16"><path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/></svg></button>
	        	   </a>
        	  </td>
      	   </tr>
        <%}%>
  </tbody>
  			</table>
   			<a class="bg-danger text-white" href="AvionServlet?accion=AgregarAsiento&idAvion=<%=avi.getIdAvion()%>">
   				<button type="button" class="btn btn-primary">Agregar Asiento</button>
   			</a>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>