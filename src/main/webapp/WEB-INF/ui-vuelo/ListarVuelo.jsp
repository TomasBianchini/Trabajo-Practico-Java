<%@page import="entities.Vuelo"%>
<%@page import="entities.Usuario"%>
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