package estudio.com;

import java.util.Scanner;

// UNA LISTA ENLAZADA SE CONFORMA DE "NODOS". CADA NODO ES UN OBJETO QUE APUNTA, YA SEA A OTRO NODO O A NADA(NULL) SI ES EL ÚLTIMO.
public class ListasEnlazadas {

	
		
		Nodo inicio,fin;
		static int cont;
		
		public ListasEnlazadas() {
			inicio=null;
			fin=null;
			
		}
		
		
		public boolean estaVacia() {
			
			return inicio==null;
		}
		
		
		//ES EL METODO PRINCIPAL.
		//SU  FINALIDAD ES INICIALIZAR UN OBJETO, EN NUESTRO CASO, DE TIPO NODO
		public void agregandoAlInicio(String d) {
			
			cont++; // SÓLO TIENE COMO FINALIDAD IMPRIMIR EL NÚMERO DE NODOS AGREGADOS
			
			if(estaVacia()) {  // SE EJECUTA ESTA PARTE DE LA CONDICIONAL CUANDO EL OBJETO  INICIO ES NULL(NO HAY NADA EN LA LISTA)
				inicio=new Nodo(d,inicio);
				fin=inicio;
			}else {
				inicio=new Nodo(d,inicio);
			}
			
			
		}
		
		public void agregandoAlFinal(String d) {
			cont++;
			if(estaVacia()) {
				inicio=new Nodo(d);
				fin=inicio;
			}else {
				fin.siguiente=new Nodo(d);
				fin=fin.siguiente;
			}
			
		}
		
		public static int cantidadDeNodos() {
			
			return cont;
		}
		
		public void mostrarLista() {
			
			Nodo aux=inicio;
			while(aux!=null) {
				System.out.print("["+aux.dato+"]--->");
				aux=aux.siguiente;
			}
		}
		
		public String mostrarPrimerElemento() {
			
			return inicio.dato;
		}
		
		public String mostrarUltimoElemento() {
			
			return fin.dato;
		}
		
		public boolean buscarDato(String d) {
			
			boolean encontrado=false;
			Nodo aux=inicio;
			
			while(encontrado!=true & aux!=null) {
				
				if(d.equals(aux.dato)) {
					
					encontrado=true;
					//System.out.println("El dato "+d+" se encuentra");
				}else {
					
					aux=aux.siguiente;
					//System.out.println("El dato "+d+" no se encuentra");
				}
				
				if(encontrado==true)break;
			}
			
			return encontrado;
			
		}
		
		
		public String eliminarDatosInicio() {
			
			String eliminado=inicio.dato;
			
			if(cont==1) {
				
				inicio=null;
				fin=inicio;
			}else {
				inicio=inicio.siguiente;
			}
			
			cont--;
			return eliminado;
			
		}
		
		public String eliminarDatosFinal() {
			
			String eliminado=fin.dato;
			
			Nodo aux=inicio;
			
			while(aux.siguiente!=fin) {
				aux=aux.siguiente;
				
			}
			cont--;
			fin=aux;
			fin.siguiente=null;
			
			return eliminado;
			
		}
		
		public static void main(String[] args) {
			
			ListasEnlazadas miListaEnlazada=new ListasEnlazadas();
			miListaEnlazada.agregandoAlInicio("Lupita");
			miListaEnlazada.agregandoAlInicio("Ana");
			miListaEnlazada.agregandoAlInicio("Juan");
			miListaEnlazada.agregandoAlInicio("Maria");
			miListaEnlazada.agregandoAlInicio("Rodrigo");
			miListaEnlazada.agregandoAlFinal("Lupita");
			miListaEnlazada.agregandoAlFinal("Pepe");
			miListaEnlazada.mostrarLista();
			System.out.println("Primer elemento: "+miListaEnlazada.mostrarPrimerElemento());
			System.out.println("Último elemento: "+miListaEnlazada.mostrarUltimoElemento());
			System.out.println("Elemento eliminado: "+miListaEnlazada.eliminarDatosInicio());
			miListaEnlazada.mostrarLista();
			System.out.println("Elemento eliminado: "+miListaEnlazada.eliminarDatosFinal());
			miListaEnlazada.mostrarLista();
			Scanner sc=new Scanner(System.in);
				System.out.println("Ingrese el dato a buscar: ");
				String datoBuscado=sc.next();
				
				if(miListaEnlazada.buscarDato(datoBuscado)) {
					System.out.println("El elemento "+datoBuscado+" se encuentra");
				}else {
					System.out.println("El elemento "+datoBuscado+" no se encuentra");
				}
			
			
			if(miListaEnlazada.estaVacia()) {
				
				System.out.println("La lista esta vacia.");
				
			}else {
				
				System.out.println("La lista no esta vacia.");
			}
			
			System.out.println("Elementos en la lista: "+cantidadDeNodos());
			
			
			
			
		}
		

	

}
