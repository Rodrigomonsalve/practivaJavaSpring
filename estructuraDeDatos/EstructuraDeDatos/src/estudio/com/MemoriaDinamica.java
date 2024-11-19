package estudio.com;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MemoriaDinamica {
	
	
	// LAS LISTAS MANEJAN MEMORIA DINÁMICA, YA QUE NO ES NECESARIO DETERMINAR SU LONGITUD DESDE EL PRINCIPIO.
	// SE DETERMINA EN TIEMPO DE EJECUCIÓN, CUANDO SE HAYA TERMINADO DE INGRESAR SUS VALORES.
	// DE ESTE MODO, CONSUMEN MENOS RECURSOS
	public static void main(String[] args) {
		
		ArrayList<Integer> listaNumerica=new ArrayList<Integer>();
		int x;
		
		
		do {
			
			x=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero: "));
			listaNumerica.add(x);
		
			
		}while(x>=0);
		
		
		
	}
	
	

}
