<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-theme="dark">
<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	 	<link rel="stylesheet" href="Styles/Agregar.css">
<meta charset="UTF-8">
<title>Agregar Pais</title>
</head>
<body>
  <form action="PaisServlet?accion=insertar" method="post">
  		 <label for="nombre">Nombre del Pais</label>
    	 <input id="nombre" name="nombre" placeholder="Nombre" required autofocus type="text">
    	 <div class="grid">
        	 <button type="submit">Agregar</button>
        	 <a href="PaisServlet"><button type="button">Cancelar</button></a>
  		  </div>
  </form>
</body>
</html>