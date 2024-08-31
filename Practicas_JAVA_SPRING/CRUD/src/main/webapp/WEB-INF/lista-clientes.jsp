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
	
	<c:forEach var="clienteTemp" items="${clientes}">   <!-- Hay que recordar que, tratándose de aplicaciones web, la lógica de presentación debe estar en la vista. No se encuenra del lado del sevidor. De alguna forma las jsp tags sustitiyen la lógica de Javascript  -->
														<!-- En este punto, ya se realizó la consulta a la bd (se ejecuto desde el controlador) y el resultado de guardó en un array que viajó a este jsp a través del item "clientes"  -->
		
		<c:url var="linkActualizar" value="/cliente/muestraFormularioActualizar"> <!-- El contenido de este etiqueta completa se ejecuta sólo hasta que se dé clcik en el botón Modificar  -->
																					<!-- Es trascendental notar que la etiqueta completa c:url también se itera, por lo que cada clienteTemp.id también lo hace, al igual que los demás elementos de la lista, con la única diferencia en que clienteTemp.id no se ve en pantalla, porque no está dentro de etiquetas <td> -->
			<c:param name="clienteId" value="${clienteTemp.id}"/>  					<!-- El valor de clienteTemp.id viaja al coontrolador, como parámetro de la url, a la url /cliente/muestraFormularioActualizar y allá se recupera mediante la anotación RequestParam -->
			
		</c:url>
		
		<c:url var="linkEliminar" value="/cliente/eliminar">
		
			<c:param name="clienteId" value="${clienteTemp.id}"></c:param>
		
		</c:url>	
		
		<tr>
			<td>${clienteTemp.nombre}</td>
			<td>${clienteTemp.apellido}</td>
			<td>${clienteTemp.direccion}</td>
			
			<td>
				<a href="${linkActualizar}">  				<!-- Normalmente lo que haríamos al trabajar con "a href" sería apuntar a la URL "/cliente/muestraFormularioActualizar" . Sin embargo, necesitamos pasar al backend (Controlador) el identificador del cliente en concreto que vayamos a modificar. Esto se puede hacer de forma muy sencilla, sin necesidad de pasar todos los valores del cliente en concreto, pasando el id del cliente como parámetro en la URL y de ahí al controlador. -->
					<input type="button" value="Modificar"/> <!-- Para lograr lo anterior, hay que notar que el id, en realidad está en el frontend ya, dentro de item clientes. Sólo hay que pasarlo al backend (Controlador) -->
				</a>
			</td>	
			
			<td>
				<a href="${linkEliminar}">
					<input type="button" value="Eliminar"/>
				</a>
			</td>
		
		</tr>


	</c:forEach>	


</table>

<br>

<input type="button" value="AgregarCliente" onclick="window.location.href='muestraFormularioAgregar'; return false"/>





</body>
</html>