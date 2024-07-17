package pratica2;

public class Parseos extends Enum {
	
	String cadena = "1";
	int numero = 1;
	
	public void parseo() {
		int entero = Integer.parseInt(cadena);
		
		String palabra = Integer.toString(numero);
		String palabra1 = String.valueOf(numero);
		
		System.out.println(entero);
	}

}
