package pratica2;

public class Jugadores extends Enum {
	
	public static void agregaJugadores() {
		GenericsHeredando <Ajedrecistas> agregaJugador = new GenericsHeredando<>();
		Ajedrecistas Rodrigo = new Ajedrecistas();
		agregaJugador.metodoGenericsHeredando(Rodrigo);
	}
	
	public static void main (String [] args) {
		agregaJugadores();
	}

}