<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!-- Se debe usar esta librer�a. Significa que vamos a usar las MVC tags form -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario</title>
</head>


<!-- RELACIONADO AL TEMA DE MVC TAGS. ESTE ES C�DIGO QUE SE INTENTA EXPLICAR -->
<!--  Las MVC tags ayudan a crear estructras dentro del modelo vista controlador -->
<!--  En este caso nos ayuda a llenar un objeto (Alumno) mediante el uso de un formulario, y enviarlo al servidor lleno. Normalmente se usar�a una lista creada con request.getParamValues, pero no nos trae obetos con variable-valor -->
<!-- No es lo mismo que JSP tags. -->
<body>                                                                        
	<form:form action="/MVC_Maven/alumno/procesaFormulario" modelAttribute="elAlumno">  <!-- form:form es una MVC Tag, y usa el atributo modelAttribute para enviar informaci�n m�s f�cil al servidor -->
		
		Nombre:<form:input path="nombre"/>		<!-- En el atributo path, debe ir la variable del objeto en la cual se guardar� el valor. Es el nombre de los m�todos setter y getter sin e set o get y con min�scula -->
			<form:errors path="nombre" style="color:red"/>							
			<br>									<!--  Se realiza lo anterior, porque al aparcer el formulario en panatlla se invocan los m�todos getters y cuando se env�a la informaci�n se ejecutan los m�todos setters, en este caso, de la clase Alumno -->
													<!-- De este modo, si el objeto "elAlumno" ya viniera lleno, aparecer�an los valores en pantalla dentro de los inputs. En nuestro caso, derivado de que viene vac�o, los inputs se ven vac�os  -->
		
		Apellido:<form:input path="apellido"/>
			<br>
		
		Edad:<form:input path="edad"/>
			<form:errors path ="edad" style="color:red"/>
			<br>
			
		Email:<form:input path="email"/>
			<form:errors path="email" style="color:red"/>	
		
		<input type="submit" value="Enviar">
		
	
	</form:form>
</body>
</html>