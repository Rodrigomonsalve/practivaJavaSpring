<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- Considero que este c�digo podr�a funcionar mejor como una sesi�n, utilizando sus m�todos -->
<!-- param.nombreAlumno y mensajeClaro tienen funcionalidades similares. -->

Hola ${param.nombreAlumno} Bienvenido al curso <!-- param sirve para traer informaci�n ingresada por alg�n usuario en una vista. En este caso, nombreAlumno es el name del input -->

<p><br>

<h2>Atenci�n a todos</h2>

	${mensajeClaro}  <!-- mensaje claro es un valor que se guarda en el modelo. Sirve para traer informaci�n ingresada por alg�n usuario en una vista  -->
					<!--  Hay que recordar que la etiqueta peso-corchetes sirve para utilizar datos provenientes de c�digo Java -->
					<!--  En este caso es s�lo un String. Si fuera un objeto, tendriamos que acceder al valor correspondiente. mensajeClaro.xxxxx -->
	
</p>	

</body>
</html>