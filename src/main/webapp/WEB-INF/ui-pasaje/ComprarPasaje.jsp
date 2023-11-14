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
 	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="Styles/Agregar.css">
    <title>Detalles del Pasaje</title>
</head>
<body>
    <div class="conteiner-table">
        <h1>Detalles del Vuelo</h1>
        <p>A continuación se muestran los detalles del vuelo:</p>

        <div >
            <table role="grid">
                 <tr>
                    <th>DNI</th>
                    <td> <input type="text" id="dniInput" required  placeholder="Dni"></td>
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
                		 <select id="asiento" >
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
            
         <div class="grid">
             <a  href="#" onclick="comprarPasaje()">
    				<button type="submit" >Comprar</button>
			</a>
        	<a href="VueloServlet"><button type="button" >Cancelar</button></a>
    	</div>
            
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

