package pratica2;

//import java.util.*;


//Puede ser "T" o cualquier otra palabra al azar
public class Generics<T>{
	
	public int numero = 1;
	String palabra = "Perro";
	
	public String metodoNoGenerico(String palabra) {
		return palabra;
	}
	
	//Como "T" no es realmente nada, al instanciar esta clase, podremos designar el tipo de variable que deberemos pasar como parámetro al ejecutarle el metodoGenerico. Si nos damos cuenta, el parámetro de este método también es T. 
	//La clase también deberá ser genérica.
	//Sin Java Generics el código es más rígido, pues, al instanciar el método sólo podremos pasar como parámetro solo un tipo de dato.
	public T metodoGenerico (T palabra){
		return palabra;
	}
	
	//En este caso, el método es genérico, sin necesidad de que lo sea la clase. T puede ser cualquier tipo de dato.
	public static <T> T soloMetodoGenerico(T palabra) {
		T frase = palabra;
		return frase;
	}
	
	
	
	
	

}