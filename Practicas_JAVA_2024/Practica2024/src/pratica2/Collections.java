package pratica2;

public class Collections {
	
	public void pruebaGenerics() {
		//Sólo admite datos no primitivos
		Generics<Integer> generico = new Generics<Integer>(); 
		
		generico.metodoGenerico(1);
	}

}
