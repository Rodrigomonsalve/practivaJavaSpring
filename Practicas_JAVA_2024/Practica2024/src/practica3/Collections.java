package practica3;

import java.util.*;


//En este proyecto estamos usando JDBC(Java Database Connectivity) que es el otro nombre para la librería mysql-connector-java. No es está utilizando nada más.

public class Collections {
	
	//Todo objeto puede ser casteado a Lista.
	
	 static ArrayList<String> dulces = new ArrayList<String>(); //En el heap tenemos a la lista dulces, en el steak tenemos una lista vacía.
	 static LinkedList<String> juguetes = new LinkedList<String>();
	 
	 
	 public static void Lista() {
		 dulces.add("tamarindo");  //Para que puedas manipular una lista, forzosamente debe estar inicializada:
		 dulces.add("Ate");			//	 static ArrayList<String> dulces;  ---> esta bien declarada, pero al manipularla (agregarle un elemento) da un error de null pointer exception
		 dulces.add("Cereza");		// 	static ArrayList<String> dulces = new ArrayList<String>(); ---> Forma correcta
		 System.out.println(dulces.size());
		 System.out.println(dulces.get(1));
		 System.out.println(dulces.contains("Churro"));
		 System.out.println(dulces.contains("Ate"));
		 dulces = new ArrayList<String>();  //La lista dulces que está en el heap, ahora apunta a otra lista vacía, diferente a la que fue creada en un inicio.
		 
		 for(int i=0; i<dulces.size(); i++) {
			 System.out.println(dulces.get(i));
		 }
	 }
	 
	 public static void LinkedLista() {
		 juguetes.add("Pelota");
		 juguetes.add("Trompo");
		 System.out.println(juguetes.get(1));
		 System.out.println(juguetes.getFirst());
		 System.out.println(juguetes.size());
		 
		 Iterator<String> i = juguetes.iterator(); //iterator es un método de la clase LinkedList y devuelve un objeto de tipo Iterator
		 while(i.hasNext()) { //hasNext devueve un true o un false
			 System.out.println(i.next()); //next devuelve un String
		 }
		 
		 for(int a=0; a<juguetes.size(); a++) {
			 System.out.println(juguetes.get(a));
		 }
		 
		 
	 }
		 
	 
	 public static void main(String [] args) {
		 Lista();
		 LinkedLista();
	 }

}
