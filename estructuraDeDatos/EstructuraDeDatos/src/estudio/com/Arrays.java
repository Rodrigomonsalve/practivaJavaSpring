package estudio.com;

import javax.swing.*;

public class Arrays {
	

	public static void main(String[] args) {
		
		double[] calif=new double[6];
		int contador=0;
		
		System.out.println("Ingrese las calificaciones: ");
		
		for(int i=0; i<calif.length;i++) {
			calif[i]=Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa la calificacion "+(i+1)));
		}
		
		System.out.println("Mostrar calificaciones aprobatorias ");
		for(int i=0;i<calif.length;i++) {
			
			if(calif[i]>=6) {
				System.out.println(calif[i]);
				contador++;
			}
			
		}
		
		System.out.println("Cantidad de calificaciones aproadas: " + contador);
	}
}
