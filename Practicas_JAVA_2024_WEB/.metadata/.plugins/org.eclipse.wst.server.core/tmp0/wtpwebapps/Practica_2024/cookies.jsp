<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%

//Este c�digo s�lo tiene como finalidad crear la cookie, pero todav�a no se utiiza. 
String ciudad_favorita = request.getParameter("ciudad_favorita");

Cookie laCookie = new Cookie("agencia_viajes.ciudad_favorita", ciudad_favorita);

laCookie.setMaxAge(365*24*60*60);

//Se guarda en el navegador
response.addCookie(laCookie);

%> 

<a href = "agencia_viajes.jsp">Ir a la agencia de viajes</a>

</body>
</html>