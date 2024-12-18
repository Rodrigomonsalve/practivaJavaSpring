package practica3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

//CONSULTA
//En este proyecto estamos usando JDBC(Java Database Connectivity) que es el otro nombre para la librería mysql-connector-java. No es está utilizando nada más.

public class Base_de_datos2 {
	
	public static void main (String [] args) {
		
		try {
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
			
			//PreparedStatement ya es una clase de la librearía jdbc.
			//PreparedStatent se usa para manipular datos de la base de datos de forma dinámica (?), en sustitución de createStatement.
			PreparedStatement miSentencia=(PreparedStatement) myConnection.prepareStatement(
					"SELECT NOMBREARTICULO, CODIGOARTICULO FROM PRODUCTOS WHERE SECCION =? AND PAISDEORIGEN =?"    //Lenguaje SQL puro.
			);
			
			miSentencia.setString(1, "JUGUETERIA");
			miSentencia.setString(2, "MEXICO");
			
			//Hay que recordar que ResultSet sólo se usa cuando vamos a mostar los datos de la tabla. No es indispensable usarla siempre.
			//El objeto de tipo ResultSet es un iterador del los registros de una tabla, que nos va a servir siempre para poder recorrerla y colocarnos en la posición que queramos, con la ayuda de sus muchos métodos.
			ResultSet rs= miSentencia.executeQuery(); //Ejecuta el query de PreparedStatement y se crea un iterador. Dentro de los parámetros, también podemos colocar un query.
			while(rs.next()){
				System.out.println(rs.getString(1)); // El número es el número de la COLUMNA. Sólo recorre todos los datos de una sola columna y los imprime. El método va a depender del tipo de dato que traiga (getString, getInt, etc
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
