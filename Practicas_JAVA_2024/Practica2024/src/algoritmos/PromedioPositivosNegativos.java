package algoritmos;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PromedioPositivosNegativos {

	public static void main(String[] args) {
		
		ArrayList<Integer> positivos=new ArrayList<Integer>();
		ArrayList<Integer> negativos=new ArrayList<Integer>();
		ArrayList<Integer> ceros=new ArrayList<Integer>();
		
		//int sumadorCeros=0;
		int sumadorPositivos=0;
		int sumadorNegativos=0;
		int promedioPositivos=0;
		int promedioNegativos=0;
		int num=0;
		
		
		for(int i=0;i>=positivos.size()&&negativos.size()<=i&&num!=10000;i++) {
			num=Integer.parseInt(JOptionPane.showInputDialog("Ingresa una serie de numeros. Al ingresar '10000' verás el promedio de los números negativos, positivos y el número de ceros ingresados"));
			if(num>0&&num!=10000) {
				positivos.add(num);
				sumadorPositivos+=num;
			}else if(num<0&&num!=10000) {
				negativos.add(num);
				sumadorNegativos+=num;
			}else if(num==0&&num!=10000) {
				ceros.add(num);
			}
			
		}
		
		promedioPositivos=sumadorPositivos/positivos.size();
		promedioNegativos=sumadorNegativos/negativos.size();
		
		
		
		System.out.println("El promedio de los números positivos es: "+promedioPositivos);
		System.out.println("El promedio de los números negativos es: "+promedioNegativos);
		System.out.println("El número de ceros ingresado es: "+ceros.size());
		

	}

}