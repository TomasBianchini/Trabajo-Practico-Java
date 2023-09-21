<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Vuelo"%>
<%@page import="entities.Pasajero"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%
    	Vuelo vue = (Vuelo)request.getAttribute("Vuelo");
		Pasajero p = (Pasajero)request.getSession().getAttribute("pasajero");
   		request.getSession().setAttribute("pasajero", p);
    %>
    <title>Detalles del Pasaje</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }

        h1 {
            font-size: 24px;
            color: #333;
        }

        p {
            font-size: 18px;
            color: #555;
        }

        .flight-info {
            margin-top: 20px;
        }

        .flight-info table {
            width: 100%;
            border-collapse: collapse;
        }

        .flight-info th, .flight-info td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        .flight-info th {
            background-color: #f2f2f2;
        }

        .flight-info tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Detalles del Vuelo</h1>
        <p>A continuación se muestran los detalles del vuelo:</p>

        <div class="flight-info">
            <table>
                 <tr>
                    <th>DNI</th>
                    <td><%=p.getDni()%></td>
                </tr>
                <tr>
                    <th>Origen</th>
                    <td><%=vue.getAeropuertoOrigen().getCiudad().getNombre()%> , <%=vue.getAeropuertoOrigen().getCiudad().getPais().getNombre()%></td>
                </tr>
                <tr>
                    <th>Destino</th>
                    <td><%=vue.getAeropuertoDestino().getCiudad().getNombre()%> , <%=vue.getAeropuertoDestino().getCiudad().getPais().getNombre()%></td>
                </tr>
                <tr>
                    <th>Fecha y hora de salida</th>
                    <td><%=vue.getFechaHoraSalida()%></td>
                </tr>
                <tr>
                    <th>Fecha y Hora de llegada</th>
                    <td><%=vue.getFechaHoraLlegada()%></td>
                </tr>
                <tr>
                    <th>Precio General</th>
                    <td><%=vue.getPrecioGeneral()%></td>
                </tr>
                <tr>
                    <th>Precio Primera Clase</th>
                    <td><%=vue.getPrecioPrimeraClase()%></td>
                </tr>
            </table>
            <td><a class="bg-primary text-white" href=""><button type="button" class="btn btn-primary">Comprar Asiento General</button></a></td>
            <td><a class="bg-primary text-white" href=""><button type="button" class="btn btn-primary">Comprar Asiento Primera Clase</button></a></td>
            <td><a class="bg-danger text-red" href="VueloServlet"><button type="button" class="btn btn-danger">Cancelar</button></a></td>
        </div>
    </div>
</body>
</html>