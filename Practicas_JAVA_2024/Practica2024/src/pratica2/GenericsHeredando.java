package pratica2;

import java.util.*;


// Significa que todos los métodos o arrays de esta clase que utilicen java generics, al instanciar la clase, y ejecutar estos métodos, sólo podran usar como parámeto el tipo de variables que nosotros queramos, con la condición de que estas variables hereden de Jugador (Ajedrecistas).
// Ejemplo: GenericsHeredando<Ajedrecistas> genericsHeredando = new GenericsHeredando<Ajedrecistas>(); ---> los métodos de esta clase (GenericsHeredando) sólo podrán trabajar con clases que hereden de Jugador, en este caso, Ajedrecistas.
// GenericsHeredando<Parseos> genericsHeredando = new GenericsHeredando<Parseos>(); ---> Esto es erróneo, porque la clase Parseos no hereda de Jugador
public class GenericsHeredando<T extends Jugador> {
	
	//Al instanciar esta clase, a este Array sólo podrán ingresar objetos cuya clase herede de Jugador.
	private ArrayList <T> miembros = new ArrayList<T>();
	
	boolean metodoGenericsHeredando (T jugador) {
		if(miembros.contains(jugador)){
			System.out.println("Jugador ya existe");
			return true;
		}else {
			miembros.add(jugador);
			System.out.print("Jugador " + jugador + "agregado");
			return false;
		}
	}
	
	public Generics<?> metodoGenerico(){  //En lugar del símbolo ? podríamos poner el objeto con el que se va a trabjar, en esta caso "Parseos". Sin embargo, en otros métodos, por ejemplo, que trabajen con estructuras try-catch (metodoGenerico podría contener una estructuta try-catch), en las que sea necesario trabajar con diferentes objetos, vale la pena mejor usar el símbolo ?.
		
		Generics<Parseos> objetoGenerico = new Generics<Parseos>();
		
		return objetoGenerico;
	}
	
	public static void main (String [] args) {
		
		Generics<Parseos> objetoGenerico = new Generics<Parseos>();
		Parseos objetoParseo = new Parseos();
		Jugador objetoJugador = new Jugador();
		
		objetoGenerico.metodoGenerico(objetoParseo );
		Generics.soloMetodoGenerico(objetoJugador);
		
		
	}
	 
}
