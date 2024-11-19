package estudio.com;

import java.util.Scanner;

//LOS ARREGLOS MANEJAN MEMORIA ESTÁTICA, YA QUE ES INDISPENSABLE ESTABLECER SU LONGITUD Y, POR TANTO, EL 
// ESPACIO DE MEMORIA QUE USARÁN, DESDE EL PRINCIPIO (EN TIEMPO DE COMPILACIÓN)
// USA RECURSOS QUE TAL VEZ NO SERÁN USADOS

// ESTE ESPACIO EN MEMORIA SE UTILIZA Y SE RESERVA DESDE QUE EL PROGRAMA ESTÁ EN EJECUCIÓN. SE USA LA MEMORIA RAM DEL SISTEMA HOST.
// LO ANTERIOR, A PESAR DE QUE, COMO YA SABEMOS, CUALQUIER PROGRAMA EN JAVA SE EJECUTA EN LA JVM, ES DECIR, DE FORMA INDEPEDIENTE AL SISTEMA OPERATIVO.

public class MemoriaEstatica {
	
	public static void main(String[] args) {
		
		int arrayNumeros[]=new int[10];
		Scanner sc=new Scanner(System.in);
		int num;
		int i=0;
		
		do {
			System.out.println("Ingresa un numero: ");
			num=sc.nextInt();
			arrayNumeros[i]=num;
			i++;
			if(i==10)break;
		}while(num>0);  // SI INGRESO UN NUMERO NEGATIVO EL ARREGLO NO TERMIARÁ DE LLENARSE, PERO, AUN ASÍ, YA ESTÁ OCUPANDO UN ESPACIO EN MEMORIA
		
		
	}

}
