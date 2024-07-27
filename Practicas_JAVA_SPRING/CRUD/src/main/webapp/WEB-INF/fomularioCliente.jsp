<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form:form action="insertaCliente" modelAttribute="cliente" method="POST">

<form:hidden path="id"/>

	<table>
		<tr>
			<td>Nombre</td>
			<td><form:input path="nombre"/></td></tr>
		</tr>
		
		<tr>
			<td>Apellido:</td>
			<td><form:input path="apellido"/></td>
		</tr>
		
		<tr>
			<td>Direccion:</td>
			<td><form:input path="direccion"/></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="Insertar"></td>			
	
	</table>
	
	</form:form>

</body>
</html>