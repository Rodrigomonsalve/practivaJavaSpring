package algoritmos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PromedioNumPositivos {

	public static void main(String[] args) {
		
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		int sumador=0;
		
		
		for(int i=0;i<=numeros.size();i++) {
			
			
			
			int num=Integer.parseInt(JOptionPane.showInputDialog("Ingresa un numero positivo. Al ingresar un numero negativo terminará el ciclo y se calculará el promedio de los números ingresados"));
			if(num>=0) {
				numeros.add(num);
				sumador+=num;
			}else {
				int promedio=sumador/numeros.size();
				System.out.println("El promedio es: " + promedio);
			}
			
			
		}
		

	}

}