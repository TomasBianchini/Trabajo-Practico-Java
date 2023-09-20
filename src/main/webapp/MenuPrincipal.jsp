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
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        .button-container {
            text-align: center;
        }

        .wide-button {
            display: block;
            width: 300px; /* Aumentamos el ancho de los botones */
            padding: 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            margin: 10px auto;
        }

        .wide-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="references">
        <a href="PasajeroServlet" class="wide-button">Listar pasajero</a>
        <a href="PaisServlet" class="wide-button">Listar pais</a>
        <a href="CiudadServlet" class="wide-button">Listar ciudad</a>
        <a href="AvionServlet" class="wide-button">Listar avion</a>
        <a href="AeropuertoServlet" class="wide-button">Listar aeropuerto</a>
 		<a href="VueloServlet" class="wide-button">Listar vuelo</a>
 		<a href="AsientoServlet" class="wide-button">Listar Asientos</a>
</div>

<!-- Agrega el enlace a Bootstrap JS (opcional si planeas usar componentes interactivos) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
