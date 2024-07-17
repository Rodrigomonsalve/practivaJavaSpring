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

String nombre = request.getParameter("usuario");
String contrasena = request.getParameter("contrasena");

//Class.forName("com.mysql.jdbc.Driver");

try{
	
	Connection myConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectojsp", "root", "");
	
	PreparedStatement c_preparada= myConexion.prepareStatement("SELECT * FROM clientes WHERE NOMBRE = ? and CONTRASENA = ?");
	
	c_preparada.setString(1, nombre);
	c_preparada.setString(2, contrasena);
	
	ResultSet myResultSet = c_preparada.executeQuery();
	
	if(myResultSet.absolute(1)){   //El método absolute te ayuda a saber si hay algún registro en alguna fila. En este caso en la 1. Devuelve true o false.
		out.println("Usuario autorizado");
			
		}else{
			//out.println(myResultSet.getString(1));
			//out.println(myResultSet.getString(2));
			out.println("No hay usuarios con esos datos");
	}
	
}catch(Exception e){
	e.printStackTrace();
	out.println("Hubo un error");
	
}

%>

</body>
</html>