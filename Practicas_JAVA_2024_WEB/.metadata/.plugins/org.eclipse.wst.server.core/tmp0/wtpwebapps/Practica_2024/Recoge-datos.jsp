<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@ page import="java.sql.*" %>

<% 

//request es unn objeto predefinido, que nos ayuda a traer datos del frontend(lado del cliente) al lado del servidor, desde el atributo "name" de las etiquetas html
// El request o response sólo se pueden usar con jsp.
String nombre = request.getParameter("nombre");
String apellido = request.getParameter("apellido");


Class.forName("com.mysql.jdbc.Driver");

try{
	
	Connection myConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectojsp", "root", "");
	
	Statement mySentencia = myConexion.createStatement();
	
	String myQuery = "INSERT INTO usuarios (nombre, apellido) VALUES ('"+ nombre +"', '" + apellido + "')"; //Existe una forma diferente de hacer esto (llenar una consulta con los datos traidos desde el frontend). Se ve en el jsp Comprueba_usuario.jsp
	
	mySentencia.executeUpdate(myQuery);
	
	out.print("Registro insertado con exito");
	
 }catch(Exception e){
	 e.printStackTrace();
	 out.println("Ha habido un error");
 }



%>

</body>
</html>