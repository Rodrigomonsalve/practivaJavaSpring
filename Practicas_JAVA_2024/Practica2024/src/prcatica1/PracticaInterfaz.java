package prcatica1;

public class PracticaInterfaz extends ClasesYmetodos implements Interfaz {

	
	
//	@Override //No se puede sobrescribir un método estático, ni heredándolo, ni implementándolo. 
//	public void hablar() { 
//	 System.out.println("Yo hablo");
//		
//	}
	
	@Override
	public void pruebaStatic() {
		System.out.println("Ya no digo hola");
}
	
	public static void main(String [] args) {
		ClasesYmetodos.pruebaFinal(); //No fue necesario instanciar la clase ClasesYmetodos, porque esta método es estático.
		ClasesYmetodos objeto = new ClasesYmetodos();
		//ClasesYmetodos.pruebaStatic();
		objeto.pruebaStatic(); //En este caso sí fue necesario instanciar
		
	}

	//Los métoos usados de una interfaz, no deben llevar la etiqueta Override.
	public void hablar() {
		// TODO Auto-generated method stub
		
	}
	
	//Cuando implementamos, todos los mètodos de la interfaz deben ser incorporados en la clase que implementa. Eso no sucede con la herencia.
		public void caminar() {
			// TODO Auto-generated method stub
			
		}
	

}
