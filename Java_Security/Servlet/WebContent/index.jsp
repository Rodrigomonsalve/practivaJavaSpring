<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Texto1</h1>
<a href="MyServlet?var1=2&var2=6">Creamos una petición tipo GET</a>

<form action="MyServlet"method="post">
	<label>Nombre:</label>
	<input type="text"name="nombre">
	<button type="submit">Enviar</button>

</form>

</body>
</html>