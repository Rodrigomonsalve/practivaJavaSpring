package estudio.com;

public class Referencias {

	public static void main(String[] args) {
		
		
		Persona p00=null;
		Persona p0;
		final Persona p1=new Persona("Jose", 40); // AQUI LA VARIABLE YA APUNTA A UN ESPACIO DE MEMORIA DETERMINADO
		Persona p2=new Persona("Ana", 41);
		System.out.println("p00: "+p00);
		//System.out.println("p0: "+p0);//NO SE PUEDE MANIPULAR DE NINGUN MODO PORQUE NO HA SIDO INICIALIZADA, ES DECIR, NO APUNTA A NINGUN ESPACIO DE MEMORIA. 
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p2=p1; // AQUI YA PROVOCAMOS QUE p2 YA APUNTE AL MISMO ESPACIO DE MEMORIA AL QUE APUNTA p1. EL CONTENIDO DEL ESPACIO DE MEMORIA AL CUAL APUNTABA p2, LO ELIMINA EL RECOLECTOR DE BASURA DE JAVA (GARBAGE COLLECTOR).
		//NO ES UNA COPIA. CAMBIA LA REFERENCIA A LA QUE APUNTA.
		System.out.println("Despues de hacer p1=p2");
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		
		// === NO EXISTE EN JAVA
		if(p2==p1) {  //EL OPERADOR == VALIDA SI AMBAS VARIABLES "APUNTAN AL MISMO ESPACIO EN MEMORIA". ESTA FUNCIÓN SÓLO SIRVE PARA OBJETOS, ARRAYS, STRINGS Y LISTAS.
			         // ESTO ES PORQUE LAS VARIABLES PRIMITIVAS NO TIENEN REFERENCIAS. 
			System.out.println("p1 apunta al mismo espacio de memoria que p2");
		}else {
			System.out.println("p1 no apunta al mismo espacio de memoria que p2");
		}
		
		if(p2.equals(p1)) {  // EL METODO EQUALS VALIDA SI AMBAS VARIABLES "TIENEN EL MISMO CONTENIDO", APUNTEN O NO AL MISMO ESPACIO EN MEMORIA.
			System.out.println("p1 tiene el mismo contenido que p2");
		}else {
			System.out.println("p1 no tiene el mismo contenido que p2");
		}
			
		
		
		//FUNCIONA IGUAL CON PRIMITIVOS:
		int i=9;
		int y=10;
		System.out.println("Con enteros: ");
		System.out.println("i: "+i);
		System.out.println("y: " +y);
		y=i;
		System.out.println("Despues de haber hecho y=i");
		System.out.println("i: "+i);
		System.out.println("y: " +y);
		
		//FUNCIONA IGUAL CON ARREGLOS
		
		Persona arr[]=new Persona[3];
		arr[0]=new Persona("Jose", 40);
		arr[1]=new Persona("Pedro", 18);
		arr[2]=new Persona("Paty", 27);
		
		
		for(int j=0;j<arr.length;j++) {
			
			System.out.println(arr[j]);
			
		}
		
		Persona arr1[]=new Persona[3];
		
		for(int e=0;e<arr.length;e++) {
			
			System.out.println(arr1[e]);
			
		}
		arr1=arr;
		
		for(int f=0;f<arr.length;f++) {
			
			System.out.println(arr1[f]);
			
		}
		
		
		int q=1;
		int q2=1;
		
		
		Persona Juan=new Persona("Juan", 42);
		Persona Juan2=new Persona("Juan", 42);
		
		if(q==q2) {
			
			System.out.println("q apunta al mismo espacio de memoria que q2");
			
		}else {
			System.out.println("q no apunta al mismo espacio de memoria que q2");
		}
			
		
		if(Juan==Juan2) {
			
			System.out.println("Juan apunta al mismo espacio de memoria que Juan2");
			
		}else {
			System.out.println("Juan no apunta al mismo espacio de memoria que Juan2");
		}
		
		
		//SI QUIERES COMPARAR VALOR Y TIPO DE DATO EN OBJETOS, PUEDES USAR EL MÉTODO instanceof (objeto instanceof String) JUNTO CON equals. SERÍA IGUAL QUE USAR === EN JAVASCRIPT.
			
			
			
			
		
		

	}

}
