package algoritmos;

import java.util.Scanner;

public class DescuentosMayoreo {

	public static void main(String[] args) {
		
		
		Scanner sc=new Scanner(System.in);
		int numPanes;
		
		System.out.println("Ingresa el numero de panes a comprar. Recuerda que cada pieza cuesta 5 pesos. Más de 50, 4.5 pesos, y mas de 100, 4 pesos cada pieza:");
		numPanes=sc.nextInt();
		sc.close();
		
		if(numPanes<=50) {
			
			System.out.println("Total a pagar: " + numPanes*5 + " pesos.");
		}
		else if(numPanes>50 && numPanes<=100){
			
			System.out.println("Total a pagar: " + numPanes*4.5 + " pesos.");
			
		}else if(numPanes>100) {
			
			System.out.println("Total a pagar: " + numPanes*4 + " pesos.");
		}
		
		

	}

}
