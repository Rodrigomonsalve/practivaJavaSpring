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

//Este c�digo s�lo tiene como finalidad utilizar las cookies que fueron creadas en otro c�digo.

String ciudad_favorita="Madrid";


//Con esta l�enea se traen los valores de la cookies, se traiga alg�n valor del navegador o no, porque la cookie que siempre se va a crear, por default, es una que tiene por nombre JSESSIOND cuyo valor se crea tambi�n de forma autom�tica.
//El JSESSIONID sirve, en Java, para vincular al usuario con una sesi�n.
//Si logra traerse algo den navegador, traer�, entonces 2 cookies (cada una con su nombre y valor). La otra ser� la que hayamos creado nosotros mismos como desarrolladores.
//La cookie que nosotros hayamos creado como desarrolladores se guardar� en el navegador del usuario por el tiempo que nosotros determinemos. Cada que el usuario abra el mismo sitio, �ste podr� usar ese valor.
//Las ccokies se crean aparte, en otro archivo jsp.
Cookie[] lasCookies = request.getCookies();

if(lasCookies != null){
//Esta tambi�n es una forma en la que podemos obtener el valor de las cookies.	

	for(Cookie cookie_temporal : lasCookies){
		
		out.print("Cookies:" + cookie_temporal.getName());   //Dado que las cookies estan guardadas en un array, no le podemos aplicar los m�todos propios de las cookies. Primero hay que recorrer eses array y a acada cookie ahora s�, se le pueden aplicar sus m�todos(getName).
		out.print("Value:" + cookie_temporal.getValue());
			if("agencia_viajes.ciudad_favorita".equals(cookie_temporal.getName())){  //Las cookies siempre se van a manipular desde el nombre de su clave. 
				ciudad_favorita = cookie_temporal.getValue();  
				break; 
		} 
	}
}

%>

<h3>Vuelos a<%= ciudad_favorita %></h3>
<h3>Hoteles en <%= ciudad_favorita %></h3>

</body>
</html>