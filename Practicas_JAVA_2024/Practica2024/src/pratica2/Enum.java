package pratica2;

import prcatica1.*;

public class Enum extends ClasesYmetodos {
	
	enum Talla{
		extrachico,
		MINI, 
		MEDIANO, 
		GRANDE, 
		EXTRAGRANDE};
		
		static ClasesYmetodos objeto1 = new ClasesYmetodos();
		static ClasesYmetodos objeto3 = objeto1;    //Estoy creando otro objeto idéntico (objeto3 apunta al mismo objeto) a objeto1, con el cual poder trabajar. Se crean 2 objetos diferentes en el "stake", pero ambos apuntan al mismo espacio de memoria en el "Heap".
		static ClasesYmetodos objeto4 = new ClasesYmetodos(6, 7);
		static ClasesYmetodos objeto5 = objeto4;
		
	
	public static void main (String [] args) {
		Talla s = Talla.MINI; // Sólo puedes crear "Tipos" con determinados valores. Talla no es un objeto
		ClasesYmetodos objeto2 = objeto1;
		int variable = objeto1.variable4;
		System.out.println(s);
		System.out.println(variable);
		System.out.println(objeto2);
		
		System.out.println(objeto1);
		System.out.println(objeto3);
		System.out.println(objeto4);
		System.out.println(objeto5);
		
		System.out.println(objeto1.variable);
		System.out.println(objeto3.variable);
		System.out.println(objeto4.variable);
		System.out.println(objeto5.variable);
		
		objeto1 = objeto4;
		
		System.out.println(objeto1);
		System.out.println(objeto4);
		
		System.out.println(objeto1.variable);
		System.out.println(objeto4.variable);
		
	}

}
