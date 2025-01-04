package estudio.com;

import java.util.Scanner;
import java.util.Stack;

public class Pilas_MetodosJava {

	public static void main(String[] args) {
		
		Stack<String> pila=new Stack<String>();
		System.out.println("Ingresa 5 palabras");
		Scanner sc=new Scanner(System.in);
		
		for(int i=0;i<5;i++) {
			
			pila.push(sc.next());
		}
		System.out.println("Elementos de la pila: ");
		while(!pila.isEmpty()) {
			
			System.out.println(pila.pop());//EL METODO POP LO QUE HACE ES ELIMINAR EL ÚLTIMO ELEMENTO INGRESADO.
			pila.add(null);//STACK CUENTA CON LOS MISMOS MÉTODOS QUE UN ARRAYLIST. SÓLO AGREGA:
			pila.get(1);   // push, pop, peek,  empty, search
			pila.set(0, null);
		}
	}

}
