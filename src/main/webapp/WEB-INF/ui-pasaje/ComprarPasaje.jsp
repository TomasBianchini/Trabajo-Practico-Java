<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Vuelo"%>
<%@page import="entities.Usuario"%>
<%@page import="java.util.HashMap"%>
<%@page import="entities.Asiento"%>
<!DOCTYPE html>
<html data-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%
    	Vuelo vue = (Vuelo)request.getAttribute("Vuelo");
    	Usuario p = (Usuario)request.getSession().getAttribute("usuario");
		HashMap<String,Asiento> asientosDisponibles = (HashMap<String,Asiento>)request.getAttribute("asientosDisponibles");
    %>
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
                    <td> <input type="text" id="dniInput" required class="form-control" placeholder="Dni"></td>
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
                <tr>
			   <tr>
                	 <th>Asiento</th>
                	 <td> 	
                		 <select id="asiento" class="form-control">
							<%if (asientosDisponibles.isEmpty()){%>
								<option> No hay asientos disponibles </option>
							<%}else{%>
						
								<%for (HashMap.Entry<String, Asiento> asi: asientosDisponibles.entrySet()) {%>
		                  	      	<option value="<%= asi.getValue().getFila() + ' ' + asi.getValue().getNumero() + ' ' + asi.getValue().getTipo() %>"><%=asi.getValue().getFila()%> <%=asi.getValue().getNumero()%> <%=asi.getValue().getTipo()%> </option>
		                  		<%} %>
		                  	<%} %>
                   		</select>
					</td>
				</tr>

            </table>
            <a class="bg-primary text-white" href="#" onclick="comprarPasaje()">
    				<button type="button" class="btn btn-primary">Comprar</button>
			</a>
           
            <td><a class="bg-danger text-red" href="VueloServlet"><button type="button" class="btn btn-danger">Cancelar</button></a></td>
        </div>
    </div>
<script>
function comprarPasaje() {

    var asientoInput = document.getElementById("asiento");
	var dniInput = document.getElementById("dniInput");

    var selectedValue = asientoInput.value;
    var dni = dniInput.value;

    if (selectedValue) {
        var dniPasajero = dni;
        var idVuelo = '<%=vue.getIdvuelo()%>';
        var idavion = '<%=vue.getAvion().getIdAvion()%>'
        
        
        var asientoParts = selectedValue.split(' ');
        var fila = asientoParts[0];
        var numero = asientoParts[1];
        var tipo = asientoParts[2];
        
        // Construye la URL con los valores seleccionados
        var url = 'PasajeServlet?accion=compra&dniPasajero=' + dniPasajero + '&idvuelo=' + idVuelo +
            '&fila=' + fila + '&numero=' + numero + '&tipo=' + tipo + '&idavion=' + idavion  ;

        // Redirige a la URL construida
        window.location.href = url;
    } else {
        // Si no se ha seleccionado un valor, muestra un mensaje de error o toma la acción adecuada.
        alert("Por favor, seleccione un asiento antes de comprar.");
    }
}
</script>
</body>

</html>

