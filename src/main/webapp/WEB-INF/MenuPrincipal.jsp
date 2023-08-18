<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="entities.Pasajero" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  
   <%
    	String email = (String) request.getAttribute("email");
   		String password = (String) request.getAttribute("password");
		Pasajero p = (Pasajero)request.getSession().getAttribute("pasajero");
	%>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Menu Principal</title>
  <!-- Agrega el enlace a Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Estilos personalizados */
    body {
      background-color: #f8f9fa;
    }
    .container {
      max-width: 600px;
    }
    .btn-custom {
      background-color: #007bff;
      color: #fff;
      border: none;
      margin-right: 10px;
    }
    .btn-custom:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
      <form class="form-signin" action="PasajeroServlet" method="get">
    	 <p  class="btn btn-custom">Listar Pasajero</p>
         <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
    </form>

<div class="container mt-5">
  <div class="row">
    <div class="col">
      <div class="d-flex justify-content-between">
        <a href="../PasajeroServlet" class="btn btn-custom">Listar pasajero</a>
        <a href="paises.html" class="btn btn-custom">Listar pais</a>
        <a href="ciudades.html" class="btn btn-custom">Listar ciudad</a>
        <a href="aviones.html" class="btn btn-custom">Listar avion</a>
        <a href="aeropuertos.html" class="btn btn-custom">Listar aeropuerto</a>
      </div>
    </div>
  </div>
</div>



<!-- Agrega el enlace a Bootstrap JS (opcional si planeas usar componentes interactivos) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
