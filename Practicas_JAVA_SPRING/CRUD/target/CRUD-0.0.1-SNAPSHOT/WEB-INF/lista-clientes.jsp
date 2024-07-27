<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de alumnos</title>
<%-- <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css"/> --%>
</head>
<body>

<h1>Lista de clientes</h1>

<table border="1">
	<tr>
		<th>Nombre</th>
		<th>Apellido</th>
		<th>Mail</th>
	
	</tr>
	
	<c:forEach var="clienteTemp" items="${clientes}">
		
		<tr>
			<td>${clienteTemp.nombre}</td>
			<td>${clienteTemp.nombre}</td>
			<td>${clienteTemp.nombre}</td>
		
		</tr>


	</c:forEach>	


</table>

<br>

<input type="button" value="AgregarCliente" onclick="window.location.href='muestraFormularioAgregar'; return false"/>





</body>
</html>