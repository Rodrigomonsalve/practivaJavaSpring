<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>


<h2 style="text-align:center">Inicio de sesión</h2>

<!-- ${pageContext.request.contextPath} es la raíz de nuestro proyecto. En nuestro caso es /Security -->
<!-- /autenticacionUsuario es la URL a la que se envían las credenciales enviadas, para que Spring las valide. No es una URL visible. No debe estar en el controlador. Tú la creas -->
<!-- En todo login se debe especificar la URL a la que se enviaran las credenciales ingresadas para su validación. En nuestro caso es /autenticacionUsuario -->
<form:form action="${pageContext.request.contextPath}/autenticacionUsuario" method="POST"> <!-- Para funcionar el pageContext necesita la librería javax.servlet.jsp-api -->

	<input type="text" name="username">Usuario</input>
	<input type="text" name="password">Contraseña</input>
	
	<button type="submitt">Login</button>

</form:form>

</body>
</html>