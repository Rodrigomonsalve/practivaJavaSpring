<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!-- Hay que recordar que antes tuvimos que importar a librer�a javx-servlet-jsp-jstl -->
<%@ page import="java.util.*, java.sql.*, practica1.Empleado" %>

<!--  UNA DE LAS COSAS QUE PODEMOS VER EN ESTE C�DIGO ES QUE PODEMOS METER LOS SCRIPTLETS TANTO DENTRO DEL BODY DE UN HTML, COMO FUERA DE CUALQUIER ESTRUCTURA HTML, COMO LO VEMOS EN ESTE CASO.-->
<!-- La funci�n de  las jsp tags son ejecutar funcionalidades de java dentro de c�digo html. No es lo mismo que jsp puro, dado que �ste es declarar c�digo java puro dentro de html, mientras que las jso tags fusionan ambos c�digos. Se podr�a decir que es html con la potencia de java. Es java con la estructura visual de html-->
<%

ArrayList<Empleado> datos = new ArrayList<Empleado>();

Class.forName("com.mysql.jdbc.Driver");

try{
	
	Connection myConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
	
	Statement miSentencia = myConexion.createStatement();
	
	String miQuery = "SELECT * FROM EMPLEADOS";
	
	ResultSet rs = miSentencia.executeQuery(miQuery);
	
	
	while (rs.next()){
		datos.add(new Empleado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));	
	}
	
	rs.close();
	myConexion.close();
	
}catch(Exception e){
	e.printStackTrace();
	out.print("Hubo un error");
}

//Se utiliza para poder utilizar el array datos fuera de una estructura java, en este caso, HTML. 
pageContext.setAttribute("losEmpleados", datos);

String cadena = "<h2>Hola</h2>";

out.print(cadena);


%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border="1">

<tr>
	<td>Nombre</td>
	<td>Apellido</td>
	<td>Puesto</td>
	<td>Salario</td>
</tr>


<!-- La etiqueta peso-corchetes sirve para utilizar datos provenientes de c�digo Java-->
<c:forEach var="EmpTemp" items="${losEmpleados}">   <!-- EmpTemp la creamos nosotros de forma arbitraria -->

<tr>
	<td>${EmpTemp.nombre}</td>
	<td>${EmpTemp.apellido}</td>
	<td>${EmpTemp.puesto}</td>
	<td>${EmpTemp.salario}<td>
	
	<c:if test="${EmpTemp.salario<4000}">
		${EmpTemp.salario+5000}
	</c:if>
	
	<c:if test= "${datos.salario>=4000}">
		${EmpTemp.salario}
	</c:if>	
	
		
</tr>

</c:forEach>

</table>

</body>
</html>