package pratica2;

import java.util.*;

//Significa que todos los métodos o arrays de esta clase que utilicen java generics, al instanciar la clase, y ejecutar estos métodos, sólo podran usar como parámeto el tipo de variables que nosotros queramos, con la condición de que estas variables hereden de Jugador (Ajedrecistas).
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
	 
}