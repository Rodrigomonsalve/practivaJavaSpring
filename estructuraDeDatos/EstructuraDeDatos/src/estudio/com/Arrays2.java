package estudio.com;

public class Arrays2 {
	
	public static void main(String[] args) {
		
		//Persona arrayPersonas[] = new Persona[5];
		
		//arrayPersonas[0]=new Persona("Juan", 20);
		
		Persona arrayPersona[]= {new Persona("Lorena", 20), new Persona("Juan", 15), 
				new Persona("Ricardo", 35), new Persona("Jose", 20), new Persona("Patricia", 65),
				new Persona("Cecilia", 40), new Persona("David", 66)};
		
		
		System.out.println("La edad mayor del arreglo es: " + EdadMayor.personaMayor(arrayPersona));
		System.out.println("Los datos del arreglo son: ");
		for(int i=0; i<arrayPersona.length;i++) {
			
			System.out.println("Datos de la persona: " + (i+1)+ "\n"+arrayPersona[i]+"\n");
			
		}
		
		
	}

}
