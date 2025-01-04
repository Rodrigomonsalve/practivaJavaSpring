package Excepciones;

public class EjemploTryCatch {
	
// LAS EXCEPCIONES COMPROBADAS(checked exceptions) SE PUEDEN CONTROLAR, YA SEA, USANDO UN TRY-CATCH, UN THROWS O UNA CONDICIONAL EN LA FIRMA DEL METODO, PERO NO AMBAS AL MISMO TIEMPO, AUNQUE LO PUEDES HACER.
// SUCEDE EXACTAMENTE LO MISMO PARA EXCEPCIONES CONTROLADAS, EXCEPTO POR LA CONDICIONAL, PERO AQUÌ ES OLIGATORIO HACERLO DE ALGUNA DE LAS 2 FORMAS.
// LA "OBLIGATORIEDAD" RADICA EN QUE EL COMPILADOR DE JAVA TE EXIGIRÁ CONTROLARLAS, AUNQUE AMBAS PRODUZCAN EL BLOQUEO DEL CÓDIGO.
// LAS NO CONTROLADAS NACEN DE ERRORES DE PROGRAMACIÓN; LAS CONTROLADAS, NO.
	
	public static void  main(String[] args) {
		
		String numeroComoTexto="123a";
		//Exception a = new Exception("Error: La cadena no se puede convertir a un numero entero");
		
		try {
			
			int numero=Integer.parseInt(numeroComoTexto);
			
			System.out.print("El numero es " + numero);
			
		}catch(Exception a) {
			
			System.err.print("Error: La cadena no se puede convertir a un numero entero" + a);
			
			
		}
		
		System.out.print("Llegamos hasta aca");
		division();
	}
	
	public static void  division() throws ArithmeticException {
		
		int numerador=10;
		int denominador=0;
		//Exception a = new Exception("Error: La cadena no se puede convertir a un numero entero");
		
		try {
			
			int resultado=numerador/denominador;
			
			System.out.print("El resultado es " + resultado);
			
		}catch(Exception a) {
			
			System.err.print("No se puede  dividir" + a);
			
			
		}
		
		System.out.print("Llegamos hasta aca");
	}
}
