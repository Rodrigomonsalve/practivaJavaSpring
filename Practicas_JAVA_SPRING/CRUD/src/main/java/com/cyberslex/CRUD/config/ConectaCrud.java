package com.cyberslex.CRUD.config;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.imageio.IIOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ServletBD")
public class ConectaCrud extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public ConectaCrud() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IIOException{
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/pruebashibernate?useSSL=false";
		
		String usuario = "root";
		
		String contra = "";
		
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			PrintWriter out = response.getWriter();
			out.print("Nombre de la BBDD " + jdbcUrl);
			
			Class.forName(driver);
			
			Connection miConexion = DriverManager.getConnection(jdbcUrl, usuario, contra);
			
			out.println("CONECTADO");
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	

}