package practica3;

import java.sql.*;


//CONSULTA
//En este proyecto estamos usando JDBC(Java Database Connectivity) que es el otro nombre para la librería mysql-connector-java. No es está utilizando nada más.
// JDBC es la API estádar de Java para conectarse a bases de datos relacionales.

// SIEMPRE NECESITAREMOS USAR UN DRIVER PARA CONECTARNOS A LA BASE DE DATOS Y TRADUCIR EL CÓDIGO JAVA A SQL. EN ESTE CASO NO LO ESTAMOS DEFINIENDO EXPLÍCITAMENTE, PORQUE YA ESTA EN EL CLASSPATH.
// DE NO ESTAR, Class.forName("com.mysql.cj.jdbc.Driver")

public class Base_de_datos {
	
	public static void main(String [] args) {
		try{
			
			//Esta conexión puede ir en un método aparte.
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root","");
			//Hay que recordar de DriverManager es un método estático. No lo instanciamos antes de aplicar el método.
			
			//Statament es una clase de la librería java.sql. Todavía no utilizamos jdbc.
			Statement miStatement = miConexion.createStatement();
			
			
			//El objeto de tipo ResultSet sólo se va a ocupar cuando necesitemos obtener datos. De otro modo (eliminar), no va a ser necesario.
			//El método executeQuery sólo sirve para ejecutar consultas. Si quieres manipular datos, como realizar inserciones, debes ejecutar executeUpdate.
			ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM PRODUCTOS"); //miResultSet es un iterador   //Lenguaje SQL puro.
			
			
			//PRIMERO ME TRAJO EL RESULTADO DE TODA LA TABLA, Y LUEGO IMPRIMO LOS VALORES QUE DESEE DE ESA TABLA
			while(miResultSet.next()) { //next ayuda a recorrer la tabla
				System.out.println(miResultSet.getString("NOMBREARTICULO") + "" + miResultSet.getString("CODIGOARTICULO") + ""); //El método a usar va a depender del tipo de dato que se encuentre en la tabla(int --> getInt(), short ---> getShort(), etc) 
			}																													//NOMBREARTICULO y CODIGOARTICULO son el nombre de 2 columnas. Significa: dame el String contenido en la columna NOMBREARTICULO y CODIGOARTICULO. En este caso, como es una estructura repetitiva, se imprimen todos los valores de esas 2 columnas.
			
			
		}catch(Exception e) {
			System.out.println("No conecta!");
			e.printStackTrace();
		}
	}

}
