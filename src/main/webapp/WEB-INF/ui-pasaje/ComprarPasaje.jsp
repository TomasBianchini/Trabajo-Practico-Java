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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="Styles/Agregar.css">
    <%
    	Vuelo vue = (Vuelo)request.getAttribute("Vuelo");
    	Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
		HashMap<String,Asiento> asientosDisponibles = (HashMap<String,Asiento>)request.getAttribute("asientosDisponibles");
    %>
    <title>Detalles del Pasaje</title>
</head>
<body>
	<form onsubmit="comprarPasaje()">
		<input type="hidden" id="idUsuario" name="idUsuario" value="<%= usu.getIdUsuario() %>">
		<h1>Detalles del pasaje:</h1>
		<h5>Datos personales:</h5>
		<div class="grid" >		
			<h5>Pasajero
                  <p><%= usu.getApellido()%> ,  <%= usu.getNombre()%> </p></h5>
           <h5>Fecha de nacimiento
                  <p><%= usu.getFechaNacimiento()%></p></h5>      
         </div>                        
         <div class="grid" >
            <h5>Tipo documento
            	<p><%= usu.getTipoDocumento()%></p>
            </h5>
            <h5>Nro documento
           		<p><%= usu.getNroDocumento()%></p>
           	</h5>
         </div>
		 <h5>Detalles del vuelo:</h5>
		 <div class="grid" >	
			<h5>Origen
                  <p><%= vue.getAeropuertoOrigen().getCiudad().getNombre()%> , <%=vue.getAeropuertoOrigen().getCiudad().getPais().getNombre()%> </p></h5>
           <h5>Destino
                  <p><%= vue.getAeropuertoDestino().getCiudad().getNombre()%> , <%=vue.getAeropuertoDestino().getCiudad().getPais().getNombre()%></p></h5>    
         </div>                          
         <div class="grid" >
            <h5>Fecha y hora de salida
            	<p><%= vue.getFechaHoraSalida() %></p>
            </h5>
            <h5>Fecha y Hora de llegada
           		<p><%= vue.getFechaHoraLlegada() %></p>
           	</h5>
         </div>
         <div class="grid" >
            <h5>Precio General
            	<p><%= vue.getPrecioGeneral() %></p>
            </h5>
            <h5>Precio Primera Clase
           		<p><%= vue.getPrecioPrimeraClase() %></p>
           	</h5>
         </div>
 		 <label for="asiento">
	  		 	Asientos Disponibles
		 	<select id="asiento" name="asiento" >
							<%if (asientosDisponibles.isEmpty()){%>
								<option> No hay asientos disponibles </option>
							<%}else{%>						
								<%for (HashMap.Entry<String, Asiento> asi: asientosDisponibles.entrySet()) {%>
		                  	      	<option value="<%= asi.getValue().getFila() + ' ' + asi.getValue().getNumero() + ' ' + asi.getValue().getTipo() %>"><%=asi.getValue().getFila()%> <%=asi.getValue().getNumero()%> <%=asi.getValue().getTipo()%> </option>
		                  		<%} %>
		                  	<%} %>
           </select>
		</label>
 		<div class="grid">
        	<button type="submit">Agregar</button>
        	<a href="VueloServlet"><button type="button" >Cancelar</button></a>
    	</div>
 	 </form>
 </div>
            <table role="grid">
                 <tr>
                    <th>Datos personales</th>
                </tr>
                <tr>
                    <th>Pasajero</th>
                    <td><%= usu.getApellido()%>,  <%= usu.getNombre()%> </td>
                </tr>
                <tr>
                    <th>Tipo documento</th>
                    <td><%= usu.getTipoDocumento() %> </td>
                </tr>
               	<tr>
                    <th>Nro documento</th>
                    <td><%= usu.getNroDocumento() %> </td>
                </tr>
                <tr>
                    <th>Fecha de nacimiento</th>
                    <td><%= usu.getFechaNacimiento()%> </td>               
                </tr>
                
                <tr>
                    <th>Origen</th>
                    <td><%=vue.getAeropuertoOrigen().getCiudad().getNombre()%>, <%=vue.getAeropuertoOrigen().getCiudad().getPais().getNombre()%></td>
                </tr>
                <tr>
                    <th>Destino</th>
                    <td><%=vue.getAeropuertoDestino().getCiudad().getNombre()%>, <%=vue.getAeropuertoDestino().getCiudad().getPais().getNombre()%></td>
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
     
   
<script>
function comprarPasaje() {

    var asientoInput = document.getElementById("asiento");

    var selectedValue = asientoInput.value;


    if (selectedValue) {
    	var idUsuario = '<%=usu.getIdUsuario()%>';
        var idVuelo = '<%=vue.getIdvuelo()%>' ;
        var idavion = '<%=vue.getAvion().getIdAvion()%>' ;
        
        
        var asientoParts = selectedValue.split(' ');
        var fila = asientoParts[0];
        var numero = asientoParts[1];
        var tipo = asientoParts[2];
        
        // Construye la URL con los valores seleccionados
        var url = 'PasajeServlet?accion=compra&idUsuario=' + idUsuario + '&idvuelo=' + idVuelo +
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

