package algoritmos;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SumaNumeros {

	public static void main(String[] args) {
	
		//Scanner sc=new Scanner(System.in);
		
		//System.out.println("Ingresa los numeros que desees, para sumarlos. Al ingresar el número 0, se ejecutará la suma: ");
		
		ArrayList<Integer> conjunto = new ArrayList<>();
		
		
		
		
//		for(int i=0;i<=conjunto.size()&&sc.nextInt()!=0;i++) {
//			System.out.println(sc.nextInt());
//			int num=sc.nextInt();
//			conjunto.add(num);
//		}
		
		int numAgregado=1;
		for(int i=0;i<=conjunto.size()&&numAgregado!=0;i++) {
			//System.out.println(conjunto.size());
			numAgregado=Integer.parseInt(JOptionPane.showInputDialog("Ingresa los numeros que desees, para sumarlos. Al ingresar el número 0, se ejecutará la suma: "));
			conjunto.add(numAgregado);
		}
		
				System.out.println("Ya terminaste ");
				int sumador=0;
				int num=0;
				
				for(int j=0;j<conjunto.size();j++) {
					num=conjunto.get(j);
					//System.out.println("Valor de posicion "+j+ ": "+conjunto.get(j));
					//System.out.println("Valor de num "+ j+"vez: " +num);
					sumador+=num;
				}
		
				
				System.out.println("La suma de tus valores es: " + sumador);
			
				
			
		
		}
			
		
		

	}

