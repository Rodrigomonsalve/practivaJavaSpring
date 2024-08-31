<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "practica1.JSP_Class" %> <!-- Todo importación va entre comillas -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- El código Java que se encuentra en archivos jsp se ejecutan automáticamente cuando se invoca el archivo jsp -->
<!-- En el body también puede ir còdigo html como formularios antes o después de declrar un scriptlet, expresiones o declaraciones JSP. También puede ir en un achivo aparte -->
<!-- Es lógico que TODO código Java dentro de archivos jsp también puede crearse en archivos java por separado -->

<%! // Esto es una Declaración. Se utiliza para declarar métodos y variables. Debe cumplir con sintaxis Java. Sin embargo, no se deben declarar clases.

int variable;

public int Cadena(int numero1, int numero2){
	
	variable = numero1 + numero2;
	return variable;
}
 %>
 
 El resultado de la suma es <%=Cadena(10, 8)  // Esto es una Expresión y NO debe cumplir con sitaxis Java. Es arbitrario. Si quieres imprimir un resultado en pantalla, basta con invocar un método, lo que no sucede en sitaxis Java (no utilizamos un system.out ni lo invocamos dentro de un método main)
 
 %>
<h1>
<%
for(int i= 0; i <10; i++){                 // Esto es un scriptlet, y debe cumplir con sintaxis Java. Sólo es funcionalidad Java, que no se declara dentro de algún método.
	out.print("<br>Este el mensaje" + i);  //No podemos utilizar etiquetas HTML dentro de estructura Java, excepto si lo haces como un String.
}											//El out.print ya imprime en el navegador, no en consola.
%>

<%!

public int ResultadoSuma(){                 //Podemos utilizar dentro de un documento jsp, métodos que se encuentren en clases externas siempre que las importemos.
	JSP_Class sumita = new JSP_Class();
	int resultadoSuma = sumita.Suma(10, 15);
	return resultadoSuma;
}

%>

El resultado de la suma es: <%= ResultadoSuma() %>
</h1>

</body>
</html>