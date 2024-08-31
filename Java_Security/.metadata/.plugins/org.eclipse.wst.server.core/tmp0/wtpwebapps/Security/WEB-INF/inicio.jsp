<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>  <!-- IMPORTANTE COLOCAR ESTA LIBRER�A -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Bienvenido


<!--Spring Security tiene muchas tags que se pueden aprovechar en un jsp-->
<!--En nuestro caso, security:authorize sirve para mostrar determinado contenido s�lo a los roles que queramos-->
<security:authorize access="hasRole('administrador')">

<!-- ${pageContext.request.contextPath} es la ra�z de nuestro proyecto. En nuestro caso es /Security -->


<p>
<a href="${pageContext.request.contextPath}/administradores">Ir a zona de administradores</a>
</p>

</security:authorize>


<security:authorize access="hasRole('ayudante')">

<!-- ${pageContext.request.contextPath} es la ra�z de nuestro proyecto. En nuestro caso es /Security -->


<p>
<a href="${pageContext.request.contextPath}/usuarios">Ir a zona de usuarios</a>
</p>

</security:authorize>


<!-- ${pageContext.request.contextPath} es la ra�z de nuestro proyecto. En nuestro caso es /Security -->
<!-- /logout es la URL para que Spring pueda cerrar sesi�n. No es una URL visible. No debe estar en el controlador. -->
<!-- En toda p�gina se debe especificar la URL para cerrar sesi�n. En nuestro caso es /logout -->
<form:form action="${pageContext.request.contextPath}/logout" method="POST">

	<input type="submit" value="Logout"/>

</form:form>


<!-- Las siguientes l�neas s�lo sirven para estar probando. No deben quedarse -->
<p>
Usuario:<security:authentication property="principal.username"/>
</br>

Rol:<security:authentication property="principal.authorities"/>
</p> 

</body>
</html>