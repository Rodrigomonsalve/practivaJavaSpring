package estudio.com;

import java.util.Scanner;


//LAS PILAS O STACK SON ESTRUCTURAS DE DATOS QUE PERMITEN ALMACENAR Y RECUPERAR DATOS.
//EL MODO DE ACCESO A SUS ELEMENTOS ES DEL TIPO LIFO(Last In, First Out) = ÚLITMO EN ENTRAR, PRIMERO EN SALIR
// CUENTA CON 2 OPERACIONES BASICAS:
//1) Apilar(push)
//2) Desapilar(pop)
//AL EXTREMO SE LE CONOCE COMO "TOPE".
// SIRVE EN TEMAS COMO:
//-RECURSIVIDAD
//-EXPRESIONES ASIMETRICAS
//-ORDENACION

// EL CODIGO QUE VEMOS A CONTIUACIÓN ES EL ALGORITMO DE LAS PILAS SIN USAR METODOS PROPIOS DE JAVA

public class Pilas_Stack {
	
	
	int pilaNumerica[];
	int tope=-1;
	int maximo;
	
	public Pilas_Stack(int maximo) {
		this.maximo=maximo;
		pilaNumerica=new int[maximo];
		
	}

	
	
	public void push() {
		
		Scanner teclado=new Scanner(System.in);
		
		if(tope>=pilaNumerica.length-1) {
			
			System.out.println("La pila esta llena. ");
			
		}else {
			
			tope+=1;
			System.out.println("Ingrese el dato: ");
			pilaNumerica[tope]=teclado.nextInt();
					
			
		}
	}
	
	public int pop() {
		
		if(tope==-1) {
			
			System.out.println("La pila esta vacia. ");
		}else {
			
			System.out.println("Se ha eliminado un elemento de la pila. ");
			pilaNumerica[tope]=0;
			
		} 
		
		return tope--;
	}
	
	public  void mostrarPila() {
		
		for(int tope=4;tope>=0;tope--) {
			
			System.out.println("Datos de la pila: "+ pilaNumerica[tope]);
			
		}
		
	}
	
	public boolean find(int data) {
		
		boolean found=false;
		
		if(!isEmpty()) {
			
			for(int i=(tope-1);i>=0;i--) {
				if(pilaNumerica[i]==data) {
					
					found=true;
					//return found;
				}
				
				//return found;
			}
			
			//return found;
			
		}else {
			System.out.println("No existen datos con ese parametro");
			//return found;
		}
		
		return found;
		
	}
	
	
	private boolean isEmpty() {
		
		if(tope<=0) {
			
			return true;
		}else {
			
			return false;
		}
		
	
	}



	public static void main(String[] args) {
		
		int tam,option;
		int dato;
		Scanner op=new Scanner(System.in);
		System.out.println("Elija el tamaño de la pila");
		tam=op.nextInt();
		Pilas_Stack pilas_Stack =new Pilas_Stack(tam);
		
		//int option;
		
		do {
			
			System.out.println("Menu de la pila: ");
			System.out.println("1. Ingresar elementos(push):");
			System.out.println("2. Eliminar elementos(pop)");
			System.out.println("3. Mostrar la pila completa: ");
			System.out.println("4. Buscar un elemento: ");
			System.out.println("5. Salir");
			System.out.println("Ingrese una opcion: ");
			option=op.nextInt();
			
			switch(option) {
			case 1:
				pilas_Stack.push();
				break;
			case 2:
				pilas_Stack.pop();
				break;
			case 3:
				pilas_Stack.mostrarPila();
				break;
			case 4:
				System.out.println("¿Qué elemento esta buscando?");
				dato=op.nextInt();
				if(pilas_Stack.find(dato)) {
					System.out.println("Elemento encontrado");
				}else {
					System.out.println("Elemento NO encontrado");
				}
				break;
			case 5:
				break;
			default:
				System.out.println("Ingrese una opcion valida");
			}
			
			
		}while(option!=5);


	}

}











