package practica3;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// INSERCIÓN

public class Transacciones {
	
	//Una transacción es un conjunto de varias senetencias SQL (DML) trabajando entre sí.
	//En las clases Base_de_datos.java sólo usamos una sola sentencia.
	
	public static void main (String [] args) {
		
		Connection miConexion = null;
		
		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:330/pruebas", "root", "" );
			
			miConexion.setAutoCommit(false);
			
			Statement miSentencia = miConexion.createStatement();
			
			String instruccionSQL = "INSERT INTO PRODUCTOS VALUES (2, 'PAPAS', 'VERDURAS', 'MEXICO')";   //Como vemos, el lenguaje es SQL puro.
			
			miSentencia.executeUpdate(instruccionSQL);
			
			//ANTES DEL COMMIT, PUEDES PONER EL NÚMERO DE SENTENCIAS QUE QUIERAS.
			
			miConexion.commit();
			
			System.out.println("Datos insertados correctamente");
			
		}catch(SQLException e){
			e.printStackTrace();
			
			
			try {
				//En caso de algún error y que se haya ejecutado alguna sentencia, haces rollback de todo.
				miConexion.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
				
			}
			
		}
	}
	

}
