<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form action ="Sesiones.jsp" method="post">

	<table>
	
		<tr>
			<td><label>Pa�s de origen</label></td>
			<td><input type="checkbox" name="articulos" value="M�xico">M�xico</input></td>     <!-- Es trascendental que en el caso de checkboxes se coloque el atributo de etiqueta "value". Ese es el vaor que se recoger�. Esto no sucede con "text", porque el valor que se recoge es lo que el usuario ingrese -->
			<td><input type="checkbox" name="articulos" value="Espa�a">Espa�a</input></td>
			<td><input type="checkbox" name="articulos" value="Argentina">Argentina</input></td>
			<td><input type="checkbox" name="articulos" value="EU">Estados Unidos</input></td>
			<td><input type="checkbox" name="articulos" value="Colombia">Colombia</input></td>
			<td><input type="text" name="articulos"></input></td>
		</tr>
		
	
		
		<tr>
			<td><button type="submit">Enviar informaci�n</button></td>
		</tr>
	
	
	</table>



</form>


<%@ page import="java.util.*" %>

<%
//SIN EL USO DE SESIONES, LO QUE EL USUARIO INGRESE SE BORRAR� DEL ARRAY CADA QUE INGRESE UN DATO. CON EL USO DE SESIONES, CADA QUE SE INGRESE UN DATO SE GUARDAR� CON LOS ANTERIORES. SIRVE PARA MANTENE UN VAOR O VALORES HASTA QUE LA SESI�N SEA TERMINADA.
//Cualquier valor de una sesi�n, puede ser obtenido en cualquier parte del c�digo.
// Estos valores no se guardan en el navegador del usuario, a diferencia de las cookies.

	List<String> ListaElementos = (List<String>)session.getAttribute("misElementos");

	if(ListaElementos == null){
		
		ListaElementos = new ArrayList<String>();
//Cada sesi�n debe llevar una nombre y un valor. Ese valor puede estar representado por una variable o un array, como es este caso.
		session.setAttribute("misElementos", ListaElementos);
	}
	
	//El m�todo getParameterValues sirve para traer un conjunto de valores, a diferencia del m�todo getParameter.
	String[] elementos = request.getParameterValues("articulos");
	
	if(elementos != null){
		for(String elemTemp : elementos){
			ListaElementos.add(elemTemp);
		}
	}
	
	for(String elemTemp : ListaElementos){
		out.print("<li>" + elemTemp + "</li>");
	}


%>

</body>
</html>