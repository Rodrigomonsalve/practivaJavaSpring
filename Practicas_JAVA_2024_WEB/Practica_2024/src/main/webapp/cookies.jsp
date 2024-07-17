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
// Si observamos bien, los objetos request y response en ning�n momento son instanciados. Es como si ya vinieran integrados en jsp. En c�digo Java normal, s� es necesario instanciarlos o ingresarlo como par�metro.
// request es un objeto de la clase HttpServletRequest y response de la clase HttpServletResponse
String ciudad_favorita = request.getParameter("ciudad_favorita");

Cookie laCookie = new Cookie("agencia_viajes.ciudad_favorita", ciudad_favorita);

laCookie.setMaxAge(365*24*60*60);

//Se guarda en el navegador
response.addCookie(laCookie);

%> 

<a href = "agencia_viajes.jsp">Ir a la agencia de viajes</a>

</body>
</html>