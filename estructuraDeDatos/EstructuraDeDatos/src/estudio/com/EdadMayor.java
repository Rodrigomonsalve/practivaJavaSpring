package estudio.com;

public class EdadMayor {
	
	public static int personaMayor(Persona[] persona) {
		
		int mayor;
		
		mayor =  persona[0].getEdad();
		
		int i=1;
		
		while(i<persona.length) {
			
			if(persona[i].getEdad()>mayor) {
				
				mayor=persona[i].getEdad();
			}
			
			i++;
			
		}
		
		return mayor;
		
		
	}

}
